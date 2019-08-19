package com.bytesedge.bookvenue.common;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.bytesedge.bookvenue.cache.RedisCacheService;
import com.bytesedge.bookvenue.cache.RedisObjectLoader;
import com.bytesedge.bookvenue.model.Context;
import com.bytesedge.bookvenue.model.EndUser;
import com.bytesedge.bookvenue.model.Invoice;
import com.bytesedge.bookvenue.model.Organization;
import com.bytesedge.bookvenue.model.PaymentGatewayDetails;
import com.bytesedge.bookvenue.model.Property;
import com.bytesedge.bookvenue.model.RentPurpose;
import com.bytesedge.bookvenue.model.User;
import com.bytesedge.bookvenue.model.UserRole;

@Component
public class CacheService {
	private static Logger log = Logger.getLogger(RedisCacheService.class);
	
	public static final String KEY_ORG_BY_ID = "OrgById_";
	public static final String KEY_ORG_BY_NAME = "OrgByIdName_";
	public static final String KEY_USER_ROLE_BY_ID = "UserRoleById_";
	public static final String KEY_USER_BY_LOGIN_NAME = "UserByLoginName_";
	public static final String KEY_ORG_BY_CTX_ID = "OrgByCtxId_";
	public static final String KEY_USER_BY_ID = "UserById_";
	public static final String KEY_USER_ROLE_LIST_BY_USER_ID = "UserRoleListByUserId_";
	public static final String KEY_USER_ROLE_LIST_BY_ROLE_ID = "UserRoleListByRoleId_";
	public static final String KEY_ORG_ROLE_LIST_BY_ORG_ID = "OrgRoleListByOrgId_";
	public static final String KEY_USER_ROLE_LIST_BY_USER_LOGIN_NAME = "UserRoleListByUserLoginName_";
	public static final String KEY_CTX_BY_URL = "CtxByUrl_";
	public static final String KEY_PROPERTY_BY_ID = "PropertyById_";
	public static final String KEY_PURPOSE_BY_ID = "PurposeById_";
	public static final String KEY_PROPERTY_BY_NAME ="PropertyByIdName_";
	public static final String KEY_PAYMENT_GATEWAY_DETAILS_BY_ID = "PaymentGatewayDetailsById_";
	public static final String KEY_END_USER_BY_ID = "EndUserById_";
	
	public static final int DAYS_29 = 29 * 24 * 60 * 60;
	public static final int DAYS_22 = 22 * 24 * 60 * 60;
	public static final int DAYS_10 = 10 * 24 * 60 * 60;
	public static final int DAYS_7 = 7 * 24 * 60 * 60;

	private static Random random = new Random();

	public static int getRandomDays(int days) {
		return random.nextInt(days) * 24 * 60 * 60;
	}

	public static int getRandomHours(int hours) {
		return random.nextInt(hours) * 60 * 60;
	}

	public static int getRandomMins(int mins) {
		return random.nextInt(mins) * 60;
	}

	public static int getNextRandom(int freq) {
		return random.nextInt(freq);
	}
	
	public static void removeKey(String key) {
		try {
			RedisCacheService.removeKey(key);
		} catch (Exception e) {
			log.error("Failed to delete key" + e.getMessage(), e);
		}
	}

	public static Collection<GrantedAuthority> getGrantedAuthorityByRoleId(final Long ctxId, final Long id) throws Exception {
		return new RedisObjectLoader<Collection<GrantedAuthority>>() {
			@Override
			public Class<?> getClazz() {
				return Collection.class;
			}

			public int getTTL() {
				return DAYS_29 + getRandomHours(24) + getRandomMins(60);
			}

			@Override
			public String getKey() {
				return KEY_USER_ROLE_LIST_BY_ROLE_ID + id;
			}

			@Override
			public boolean isValid(Collection<GrantedAuthority> obj) {
				if (obj != null) {
					return true;
				}
				return false;
			}

			@Override
			public Collection<GrantedAuthority> loadObject() throws Exception {
				return CommonUtil.getGrantedAuthorityByUserRole(
						DbService.getInstance().getUserService().getUserRole(ctxId, id));
			}
		}.process();
	}
	
	public static Collection<GrantedAuthority> getGrantedAuthorityUserScopeByOrgId(final Long ctxId, final Long id) throws Exception {
		return new RedisObjectLoader<Collection<GrantedAuthority>>() {
			@Override
			public Class<?> getClazz() {
				return Collection.class;
			}

			public int getTTL() {
				return DAYS_29 + getRandomHours(24) + getRandomMins(60);
			}

			@Override
			public String getKey() {
				return KEY_ORG_ROLE_LIST_BY_ORG_ID + id;
			}

			@Override
			public boolean isValid(Collection<GrantedAuthority> obj) {
				if (obj != null) {
					return true;
				}
				return false;
			}

			@Override
			public Collection<GrantedAuthority> loadObject() throws Exception {
				return CommonUtil.getGrantedAuthorityUserScopeByOrgId(
						DbService.getInstance().getUserService().getOrganizationById(ctxId, id));
			}
		}.process();
	}
	
	public static Collection<GrantedAuthority> getGrantedAuthorityByUserId(final Long id) throws Exception {
		return new RedisObjectLoader<Collection<GrantedAuthority>>() {
			@Override
			public Class<?> getClazz() {
				return Collection.class;
			}

			public int getTTL() {
				return DAYS_29 + getRandomHours(24) + getRandomMins(60);
			}

			@Override
			public String getKey() {
				return KEY_USER_ROLE_LIST_BY_USER_ID + id;
			}

			@Override
			public boolean isValid(Collection<GrantedAuthority> obj) {
				if (obj != null) {
					return true;
				}
				return false;
			}

			@Override
			public Collection<GrantedAuthority> loadObject() throws Exception {
				User user = DbService.getInstance().getUserService().getUser(id);
				if(user != null) {
					return CommonUtil.getGrantedAuthorityByUserRole(
							DbService.getInstance().getUserService().getUserRole(user.getCtxId(), user.getRoleId()));
				} else {
					return null;
				}
			}
		}.process();
	}
	
	public static Collection<GrantedAuthority> getGrantedAuthorityByUserLoginName(final String loginName) throws Exception {
		return new RedisObjectLoader<Collection<GrantedAuthority>>() {
			@Override
			public Class<?> getClazz() {
				return Collection.class;
			}

			public int getTTL() {
				return DAYS_29 + getRandomHours(24) + getRandomMins(60);
			}

			@Override
			public String getKey() {
				return KEY_USER_ROLE_LIST_BY_USER_LOGIN_NAME + loginName;
			}

			@Override
			public boolean isValid(Collection<GrantedAuthority> obj) {
				if (obj != null) {
					return true;
				}
				return false;
			}

			@Override
			public Collection<GrantedAuthority> loadObject() throws Exception {
				User user = DbService.getInstance().getUserService().getUserByLoginName(loginName);
				if(user != null) {
					return CommonUtil.getGrantedAuthorityByUserRole(
							DbService.getInstance().getUserService().getUserRole(user.getCtxId(), user.getRoleId()));
				} else {
					return null;
				}
			}
		}.process();
	}
	
	public static UserRole getUserRoleById(final Long ctxId, final Long id) throws Exception {
		return new RedisObjectLoader<UserRole>() {
			@Override
			public Class<?> getClazz() {
				return UserRole.class;
			}

			public int getTTL() {
				return DAYS_29 + getRandomHours(24) + getRandomMins(60);
			}

			@Override
			public String getKey() {
				return KEY_USER_ROLE_BY_ID + id;
			}

			@Override
			public boolean isValid(UserRole obj) {
				if (obj != null) {
					return true;
				}
				return false;
			}

			@Override
			public UserRole loadObject() throws Exception {
				return DbService.getInstance().getUserService().getUserRole(ctxId, id);
			}
		}.process();
	}
	
	public static Organization getOrganizationByName(final Long ctxId, final String name) throws Exception {
		return new RedisObjectLoader<Organization>() {
			@Override
			public Class<?> getClazz() {
				return Organization.class;
			}

			public int getTTL() {
				return DAYS_29 + getRandomHours(24) + getRandomMins(60);
			}

			@Override
			public String getKey() {
				return KEY_ORG_BY_NAME + name ;
			}

			@Override
			public boolean isValid(Organization obj) {
				if (obj != null) {
					return true;
				}
				return false;
			}

			@Override
			public Organization loadObject() throws Exception {
				return DbService.getInstance().getOrgService().getOrganizationByName(ctxId, name);
			}
		}.process();
	}
	
	public static Organization getOrganizationById(final Long ctxId, final Long id) throws Exception {
		return new RedisObjectLoader<Organization>() {
			@Override
			public Class<?> getClazz() {
				return Organization.class;
			}

			public int getTTL() {
				return DAYS_29 + getRandomHours(24) + getRandomMins(60);
			}

			@Override
			public String getKey() {
				return KEY_ORG_BY_ID + id;
			}

			@Override
			public boolean isValid(Organization obj) {
				if (obj != null) {
					return true;
				}
				return false;
			}

			@Override
			public Organization loadObject() throws Exception {
				return DbService.getInstance().getUserService().getOrganizationById(ctxId, id);
			}
		}.process();
	}
	
	public static User getUserByLoginName(final String loginName) throws Exception {
		return new RedisObjectLoader<User>() {
			@Override
			public Class<?> getClazz() {
				return User.class;
			}

			public int getTTL() {
				return DAYS_29 + getRandomHours(24) + getRandomMins(60);
			}

			@Override
			public String getKey() {
				return KEY_USER_BY_LOGIN_NAME + loginName;
			}

			@Override
			public boolean isValid(User obj) {
				if (obj != null) {
					return true;
				}
				return false;
			}

			@Override
			public User loadObject() throws Exception {
				return DbService.getInstance().getUserService().getUserByLoginName(loginName);
			}
		}.process();
	}
	
	public static User getUserById(final Long ctxId, final Long id) throws Exception {
		return new RedisObjectLoader<User>() {
			@Override
			public Class<?> getClazz() {
				return User.class;
			}

			public int getTTL() {
				return DAYS_29 + getRandomHours(24) + getRandomMins(60);
			}

			@Override
			public String getKey() {
				return KEY_USER_BY_ID + id;
			}

			@Override
			public boolean isValid(User obj) {
				if (obj != null) {
					return true;
				}
				return false;
			}

			@Override
			public User loadObject() throws Exception {
				return DbService.getInstance().getUserService().getUserByIdAndCtxId(id, ctxId);
			}
		}.process();
	}

	public static Context getContextByUrl(String url) throws Exception {
		return new RedisObjectLoader<Context>() {
			@Override
			public Class<?> getClazz() {
				return Context.class;
			}

			public int getTTL() {
				return DAYS_29 + getRandomHours(24) + getRandomMins(60);
			}

			@Override
			public String getKey() {
				return KEY_CTX_BY_URL + url;
			}

			@Override
			public boolean isValid(Context obj) {
				if (obj != null) {
					return true;
				}
				return false;
			}

			@Override
			public Context loadObject() throws Exception {
				return DbService.getInstance().getSetupService().getContext(url);
			}
		}.process();
	}

	public static Organization getOrgByCtxId(Long ctxId) throws Exception {
		return new RedisObjectLoader<Organization>() {
			@Override
			public Class<?> getClazz() {
				return Organization.class;
			}

			public int getTTL() {
				return DAYS_29 + getRandomHours(24) + getRandomMins(60);
			}

			@Override
			public String getKey() {
				return KEY_ORG_BY_CTX_ID + ctxId;
			}

			@Override
			public boolean isValid(Organization obj) {
				if (obj != null) {
					return true;
				}
				return false;
			}

			@Override
			public Organization loadObject() throws Exception {
				List<Organization> list = DbService.getInstance().getSetupService().getOrgByCtxId(ctxId);
				if(list != null && !list.isEmpty()) {
					return list.get(0);
				}
				return null;
			}
		}.process();
	}
	
	public static Property getPropertyById(final Long ctxId, final Long id) throws Exception {
		return new RedisObjectLoader<Property>() {
			@Override
			public Class<?> getClazz() {
				return Property.class;
			}

			public int getTTL() {
				return DAYS_29 + getRandomHours(24) + getRandomMins(60);
			}

			@Override
			public String getKey() {
				return KEY_PROPERTY_BY_ID + id;
			}

			@Override
			public boolean isValid(Property obj) {
				if (obj != null) {
					return true;
				}
				return false;
			}

			@Override
			public Property loadObject() throws Exception {
				return DbService.getInstance().getSetupService().getPropertyByCtxIdAndId(ctxId, id);
			}
		}.process();
	}

	public static RentPurpose getPurposeById(final Long ctxId, final Long id) throws Exception {
		return new RedisObjectLoader<RentPurpose>() {
			@Override
			public Class<?> getClazz() {
				return RentPurpose.class;
			}

			public int getTTL() {
				return DAYS_29 + getRandomHours(24) + getRandomMins(60);
			}

			@Override
			public String getKey() {
				return KEY_PURPOSE_BY_ID + id;
			}

			@Override
			public boolean isValid(RentPurpose obj) {
				if (obj != null) {
					return true;
				}
				return false;
			}

			@Override
			public RentPurpose loadObject() throws Exception {
				return DbService.getInstance().getSetupService().getRentPurposeByCtxIdAndId(ctxId, id);
			}
		}.process();
	}

	public static Invoice getInvoiceByCtxId(long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static PaymentGatewayDetails getPaymentGatewayDetailsById(final Long ctxId, final Long id) throws Exception {
		return new RedisObjectLoader<PaymentGatewayDetails>() {
			@Override
			public Class<?> getClazz() {
				return PaymentGatewayDetails.class;
			}

			public int getTTL() {
				return DAYS_29 + getRandomHours(24) + getRandomMins(60);
			}

			@Override
			public String getKey() {
				return KEY_PAYMENT_GATEWAY_DETAILS_BY_ID + id;
			}

			public boolean isValid(PaymentGatewayDetails obj) {
				if (obj != null) {
					return true;
				}
				return false;
			}

			@Override
			public PaymentGatewayDetails loadObject() throws Exception {
				return DbService.getInstance().getSetupService().getPaymentGatewayDetailsByCtxIdAndId(ctxId, id);
			}
		}.process();
	}

}