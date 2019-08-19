
package com.bytesedge.bookvenue.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.bytesedge.bookvenue.common.PaginatedRequest;
import com.bytesedge.bookvenue.common.PaginatedResponse;
import com.bytesedge.bookvenue.dao.SetupDao;
import com.bytesedge.bookvenue.model.AuditData;
import com.bytesedge.bookvenue.model.BookingType;
import com.bytesedge.bookvenue.model.Context;
import com.bytesedge.bookvenue.model.EndUser;
import com.bytesedge.bookvenue.model.ForgotPasswd;
import com.bytesedge.bookvenue.model.Invoice;
import com.bytesedge.bookvenue.model.LoginHistory;
import com.bytesedge.bookvenue.model.Marquee;
import com.bytesedge.bookvenue.model.MarqueeType;
import com.bytesedge.bookvenue.model.Organization;
import com.bytesedge.bookvenue.model.PaymentGatewayDetails;
import com.bytesedge.bookvenue.model.PaymentStatus;
import com.bytesedge.bookvenue.model.Persistent;
import com.bytesedge.bookvenue.model.Property;
import com.bytesedge.bookvenue.model.PropertyImages;
import com.bytesedge.bookvenue.model.PropertyRentPrice;
import com.bytesedge.bookvenue.model.RentPurpose;
import com.bytesedge.bookvenue.model.SlotType;
import com.bytesedge.bookvenue.model.SmsGatewayDetails;
import com.bytesedge.bookvenue.model.TotalStatus;
import com.bytesedge.bookvenue.model.UserState;

@Component
@Repository("setupDao")
public final class SetupDaoImpl extends DaoImpl implements SetupDao {

	@Override
	@SuppressWarnings("unchecked")
	public Context getContext(String url) throws Exception {
		List<Context> list = getHibernateTemplate().find("from Context o where o.url = ? ", new Object[] { url });
		return list.size() > 0 ? list.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Organization> getOrganizationsSet(Long ctxId, int pageSize) throws Exception {
		List<Organization> list = getHibernateTemplate().find("from Organization oz  order by oz.id desc",
				new Object[] { ctxId, pageSize });
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PaginatedResponse getLoginHistoryList(Long ctxId, PaginatedRequest req) throws Exception {
		Long offSet = new Long(0);
		offSet = (req.getReqPage() * req.getPageSize());

		PaginatedResponse result = new PaginatedResponse();
		// Update the Request
		result.setClazz(req.getClazz());
		result.setReqPage(req.getReqPage());
		result.setPageSize(req.getPageSize());
		result.setSearchString(req.getSearchString());

		// Build Criteria
		@SuppressWarnings("deprecation")
		Criteria crit = getHibernateTemplate().getSession().createCriteria(req.getClazz());
		crit.add(Restrictions.eq("ctxId", ctxId));

		crit.addOrder(Order.desc("id"));
		crit.setProjection(Projections.rowCount());

		Long totalSize = (Long) crit.uniqueResult();
		if (totalSize > 0) {
			crit.setProjection(null);
			crit.setFirstResult(offSet.intValue());
			crit.setMaxResults(req.getPageSize().intValue());

			List<? extends Persistent> list = crit.list();
			if (list != null && !list.isEmpty()) {
				result.setResultList(list);
				result.setTotalSize(totalSize);
			} else {
				result.setTotalSize(new Long(0));
			}
		} else {
			result.setTotalSize(new Long(0));
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LoginHistory> getLoginHistorySet(Long ctxId, int pageSize) throws Exception {
		List<LoginHistory> list = getHibernateTemplate()
				.find("from LoginHistory lh where lh.ctxId=? order by lh.id desc", new Object[] { ctxId }, pageSize);
		return list;

	}

	@Override
	@SuppressWarnings("unchecked")
	public PaginatedResponse getAuditDataList(Long ctxId, PaginatedRequest req) throws Exception {
		Long offSet = new Long(0);
		offSet = (req.getReqPage() * req.getPageSize());

		PaginatedResponse result = new PaginatedResponse();
		// Update the Request
		result.setClazz(req.getClazz());
		result.setReqPage(req.getReqPage());
		result.setPageSize(req.getPageSize());
		result.setSearchString(req.getSearchString());

		// Build Criteria
		@SuppressWarnings("deprecation")
		Criteria crit = getHibernateTemplate().getSession().createCriteria(req.getClazz());
		crit.add(Restrictions.eq("ctxId", ctxId));
		crit.addOrder(Order.desc("id"));
		crit.addOrder(Order.desc("updatedTime"));
		crit.setProjection(Projections.rowCount());
		Long totalSize = (Long) crit.uniqueResult();
		if (totalSize != null && totalSize > 0) {
			crit.setProjection(null);
			crit.setFirstResult(offSet.intValue());
			crit.setMaxResults(req.getPageSize().intValue());
			List<? extends Persistent> list = crit.list();
			if (list != null && !list.isEmpty()) {
				result.setResultList(list);
				result.setTotalSize(totalSize);
			} else {
				result.setTotalSize(new Long(0));
			}
		} else {
			result.setTotalSize(new Long(0));
		}
		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public AuditData getAuditDataByIdAndCtxId(Long id, Long contextId) throws Exception {
		List<AuditData> list = getHibernateTemplate().find("from AuditData wa where wa.ctxId = ? and wa.id = ? ",
				new Object[] { contextId, id });
		return list.size() > 0 ? list.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Organization> getOrgByCtxId(Long ctxId) throws Exception {
		return getHibernateTemplate().find("from Organization o where o.ctxId = ?", new Object[] { ctxId });
	}

	@SuppressWarnings("unchecked")
	@Override
	public Property getPropertyByCtxIdAndId(Long ctxId, Long id) throws Exception {
		List<Property> list = getHibernateTemplate().find("from Property p where  p.ctxId = ? and p.id = ?  ",
				new Object[] { ctxId, id });
		return list.size() > 0 ? list.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PaginatedResponse getPropertyList(Long ctxId, PaginatedRequest req) throws Exception {
		Long offSet = new Long(0);
		offSet = (req.getReqPage() * req.getPageSize());

		PaginatedResponse result = new PaginatedResponse();
		// Update the Request
		result.setClazz(req.getClazz());
		result.setReqPage(req.getReqPage());
		result.setPageSize(req.getPageSize());
		result.setSearchString(req.getSearchString());

		// Build Criteria
		@SuppressWarnings("deprecation")
		Criteria crit = getHibernateTemplate().getSession().createCriteria(req.getClazz());
		crit.add(Restrictions.eq("ctxId", ctxId));
		crit.addOrder(Order.desc("id"));
		crit.addOrder(Order.desc("updatedTime"));
		crit.setProjection(Projections.rowCount());
		Long totalSize = (Long) crit.uniqueResult();
		if (totalSize != null && totalSize > 0) {
			crit.setProjection(null);
			crit.setFirstResult(offSet.intValue());
			crit.setMaxResults(req.getPageSize().intValue());
			List<? extends Persistent> list = crit.list();
			if (list != null && !list.isEmpty()) {
				result.setResultList(list);
				result.setTotalSize(totalSize);
			} else {
				result.setTotalSize(new Long(0));
			}
		} else {
			result.setTotalSize(new Long(0));
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PropertyImages getPropertyImagesByIdAndCtxId(Long id, Long ctxId) throws Exception {
		List<PropertyImages> list = getHibernateTemplate()
				.find("from PropertyImages pi where pi.id = ? and pi.ctxId = ? ", new Object[] { id, ctxId });
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public PaginatedResponse getManagementBookingList(Long ctxId, PaginatedRequest req) throws Exception {
		Long offSet = new Long(0);
		offSet = (req.getReqPage() * req.getPageSize());

		PaginatedResponse result = new PaginatedResponse();
		// Update the Request
		result.setClazz(req.getClazz());
		result.setReqPage(req.getReqPage());
		result.setPageSize(req.getPageSize());
		result.setSearchString(req.getSearchString());

		// Build Criteria
		@SuppressWarnings("deprecation")
		Criteria crit = getHibernateTemplate().getSession().createCriteria(req.getClazz());
		crit.add(Restrictions.eq("ctxId", ctxId));

		crit.addOrder(Order.desc("id"));
		crit.addOrder(Order.desc("updatedTime"));
		crit.setProjection(Projections.rowCount());

		Long totalSize = (Long) crit.uniqueResult();
		if (totalSize > 0) {
			crit.setProjection(null);
			crit.setFirstResult(offSet.intValue());
			crit.setMaxResults(req.getPageSize().intValue());

			@SuppressWarnings("unchecked")
			List<? extends Persistent> list = crit.list();
			if (list != null && !list.isEmpty()) {
				result.setResultList(list);
				result.setTotalSize(totalSize);
			} else {
				result.setTotalSize(new Long(0));
			}
		} else {
			result.setTotalSize(new Long(0));
		}
		return result;

	}

	@Override
	public PaginatedResponse getPropertyImagesList(Long ctxId, PaginatedRequest req) throws Exception {
		Long offSet = new Long(0);
		offSet = (req.getReqPage() * req.getPageSize());

		PaginatedResponse result = new PaginatedResponse();
		// Update the Request
		result.setClazz(req.getClazz());
		result.setReqPage(req.getReqPage());
		result.setPageSize(req.getPageSize());
		result.setSearchString(req.getSearchString());

		// Build Criteria
		@SuppressWarnings("deprecation")
		Criteria crit = getHibernateTemplate().getSession().createCriteria(req.getClazz());
		crit.add(Restrictions.eq("ctxId", ctxId));

		crit.addOrder(Order.desc("id"));
		crit.setProjection(Projections.rowCount());

		Long totalSize = (Long) crit.uniqueResult();
		if (totalSize > 0) {
			crit.setProjection(null);
			crit.setFirstResult(offSet.intValue());
			crit.setMaxResults(req.getPageSize().intValue());

			@SuppressWarnings("unchecked")
			List<? extends Persistent> list = crit.list();
			if (list != null && !list.isEmpty()) {
				result.setResultList(list);
				result.setTotalSize(totalSize);
			} else {
				result.setTotalSize(new Long(0));
			}
		} else {
			result.setTotalSize(new Long(0));
		}
		return result;
	}

	@Override
	public PaginatedResponse getBookingRegistrationList(Long ctxId, PaginatedRequest req) throws Exception {
		Long offSet = new Long(0);
		offSet = (req.getReqPage() * req.getPageSize());

		PaginatedResponse result = new PaginatedResponse();
		// Update the Request
		result.setClazz(req.getClazz());
		result.setReqPage(req.getReqPage());
		result.setPageSize(req.getPageSize());
		result.setSearchString(req.getSearchString());

		// Build Criteria
		@SuppressWarnings("deprecation")
		Criteria crit = getHibernateTemplate().getSession().createCriteria(req.getClazz());
		crit.add(Restrictions.eq("ctxId", ctxId));

		crit.addOrder(Order.desc("id"));
		crit.setProjection(Projections.rowCount());

		Long totalSize = (Long) crit.uniqueResult();
		if (totalSize > 0) {
			crit.setProjection(null);
			crit.setFirstResult(offSet.intValue());
			crit.setMaxResults(req.getPageSize().intValue());

			@SuppressWarnings("unchecked")
			List<? extends Persistent> list = crit.list();
			if (list != null && !list.isEmpty()) {
				result.setResultList(list);
				result.setTotalSize(totalSize);
			} else {
				result.setTotalSize(new Long(0));
			}
		} else {
			result.setTotalSize(new Long(0));
		}
		return result;
	}

	@Override
	public PaginatedResponse getEndUserOtpList(Long ctxId, PaginatedRequest req) throws Exception {
		Long offSet = new Long(0);
		offSet = (req.getReqPage() * req.getPageSize());

		PaginatedResponse result = new PaginatedResponse();
		// Update the Request
		result.setClazz(req.getClazz());
		result.setReqPage(req.getReqPage());
		result.setPageSize(req.getPageSize());
		result.setSearchString(req.getSearchString());

		// Build Criteria
		@SuppressWarnings("deprecation")
		Criteria crit = getHibernateTemplate().getSession().createCriteria(req.getClazz());
		crit.add(Restrictions.eq("ctxId", ctxId));

		crit.addOrder(Order.desc("id"));
		crit.setProjection(Projections.rowCount());

		Long totalSize = (Long) crit.uniqueResult();
		if (totalSize > 0) {
			crit.setProjection(null);
			crit.setFirstResult(offSet.intValue());
			crit.setMaxResults(req.getPageSize().intValue());

			@SuppressWarnings("unchecked")
			List<? extends Persistent> list = crit.list();
			if (list != null && !list.isEmpty()) {
				result.setResultList(list);
				result.setTotalSize(totalSize);
			} else {
				result.setTotalSize(new Long(0));
			}
		} else {
			result.setTotalSize(new Long(0));
		}
		return result;
	}

	@Override
	public PaginatedResponse getRentPurposeList(Long ctxId, PaginatedRequest req) throws Exception {
		Long offSet = new Long(0);
		offSet = (req.getReqPage() * req.getPageSize());

		PaginatedResponse result = new PaginatedResponse();
		// Update the Request
		result.setClazz(req.getClazz());
		result.setReqPage(req.getReqPage());
		result.setPageSize(req.getPageSize());
		result.setSearchString(req.getSearchString());

		// Build Criteria
		@SuppressWarnings("deprecation")
		Criteria crit = getHibernateTemplate().getSession().createCriteria(req.getClazz());
		crit.add(Restrictions.eq("ctxId", ctxId));

		crit.addOrder(Order.desc("id"));
		crit.setProjection(Projections.rowCount());

		Long totalSize = (Long) crit.uniqueResult();
		if (totalSize > 0) {
			crit.setProjection(null);
			crit.setFirstResult(offSet.intValue());
			crit.setMaxResults(req.getPageSize().intValue());

			@SuppressWarnings("unchecked")
			List<? extends Persistent> list = crit.list();
			if (list != null && !list.isEmpty()) {
				result.setResultList(list);
				result.setTotalSize(totalSize);
			} else {
				result.setTotalSize(new Long(0));
			}
		} else {
			result.setTotalSize(new Long(0));
		}
		return result;
	}

	@Override
	public PaginatedResponse getPropertyRentPriceList(Long ctxId, PaginatedRequest req) throws Exception {
		Long offSet = new Long(0);
		offSet = (req.getReqPage() * req.getPageSize());

		PaginatedResponse result = new PaginatedResponse();
		// Update the Request
		result.setClazz(req.getClazz());
		result.setReqPage(req.getReqPage());
		result.setPageSize(req.getPageSize());
		result.setSearchString(req.getSearchString());

		// Build Criteria
		@SuppressWarnings("deprecation")
		Criteria crit = getHibernateTemplate().getSession().createCriteria(req.getClazz());
		crit.add(Restrictions.eq("ctxId", ctxId));

		crit.addOrder(Order.desc("id"));
		crit.setProjection(Projections.rowCount());

		Long totalSize = (Long) crit.uniqueResult();
		if (totalSize > 0) {
			crit.setProjection(null);
			crit.setFirstResult(offSet.intValue());
			crit.setMaxResults(req.getPageSize().intValue());

			@SuppressWarnings("unchecked")
			List<? extends Persistent> list = crit.list();
			if (list != null && !list.isEmpty()) {
				result.setResultList(list);
				result.setTotalSize(totalSize);
			} else {
				result.setTotalSize(new Long(0));
			}
		} else {
			result.setTotalSize(new Long(0));
		}
		return result;
	}

	@Override
	public PaginatedResponse getInvoiceList(Long ctxId, PaginatedRequest req) throws Exception {
		Long offSet = new Long(0);
		offSet = (req.getReqPage() * req.getPageSize());

		PaginatedResponse result = new PaginatedResponse();
		// Update the Request
		result.setClazz(req.getClazz());
		result.setReqPage(req.getReqPage());
		result.setPageSize(req.getPageSize());
		result.setSearchString(req.getSearchString());

		// Build Criteria
		@SuppressWarnings("deprecation")
		Criteria crit = getHibernateTemplate().getSession().createCriteria(req.getClazz());
		crit.add(Restrictions.eq("ctxId", ctxId));

		crit.addOrder(Order.desc("id"));
		crit.setProjection(Projections.rowCount());

		Long totalSize = (Long) crit.uniqueResult();
		if (totalSize > 0) {
			crit.setProjection(null);
			crit.setFirstResult(offSet.intValue());
			crit.setMaxResults(req.getPageSize().intValue());

			@SuppressWarnings("unchecked")
			List<? extends Persistent> list = crit.list();
			if (list != null && !list.isEmpty()) {
				result.setResultList(list);
				result.setTotalSize(totalSize);
			} else {
				result.setTotalSize(new Long(0));
			}
		} else {
			result.setTotalSize(new Long(0));
		}
		return result;
	}

	@Override
	public PaginatedResponse getEndUserList(Long ctxId, PaginatedRequest req) throws Exception {
		Long offSet = new Long(0);
		offSet = (req.getReqPage() * req.getPageSize());

		PaginatedResponse result = new PaginatedResponse();
		// Update the Request
		result.setClazz(req.getClazz());
		result.setReqPage(req.getReqPage());
		result.setPageSize(req.getPageSize());
		result.setSearchString(req.getSearchString());

		// Build Criteria
		@SuppressWarnings("deprecation")
		Criteria crit = getHibernateTemplate().getSession().createCriteria(req.getClazz());
		crit.add(Restrictions.eq("ctxId", ctxId));
		crit.add(Restrictions.eq("userState", UserState.ACTIVE));

		crit.addOrder(Order.desc("id"));
		crit.setProjection(Projections.rowCount());

		Long totalSize = (Long) crit.uniqueResult();
		if (totalSize > 0) {
			crit.setProjection(null);
			crit.setFirstResult(offSet.intValue());
			crit.setMaxResults(req.getPageSize().intValue());

			@SuppressWarnings("unchecked")
			List<? extends Persistent> list = crit.list();
			if (list != null && !list.isEmpty()) {
				result.setResultList(list);
				result.setTotalSize(totalSize);
			} else {
				result.setTotalSize(new Long(0));
			}
		} else {
			result.setTotalSize(new Long(0));
		}
		return result;
	}

	// management booking list

	@Override
	public PaginatedResponse getAddBookingList(Long ctxId, PaginatedRequest req) throws Exception {
		Long offSet = new Long(0);
		offSet = (req.getReqPage() * req.getPageSize());

		PaginatedResponse result = new PaginatedResponse();
		// Update the Request
		result.setClazz(req.getClazz());
		result.setReqPage(req.getReqPage());
		result.setPageSize(req.getPageSize());
		result.setSearchString(req.getSearchString());

		// Build Criteria
		@SuppressWarnings("deprecation")
		Criteria crit = getHibernateTemplate().getSession().createCriteria(req.getClazz());
		crit.add(Restrictions.eq("ctxId", ctxId));
		crit.add(Restrictions.eq("userState", UserState.ACTIVE));
		crit.add(Restrictions.eq("bookingType", BookingType.MANAGEMENT));

		crit.addOrder(Order.desc("id"));
		crit.setProjection(Projections.rowCount());

		Long totalSize = (Long) crit.uniqueResult();
		if (totalSize > 0) {
			crit.setProjection(null);
			crit.setFirstResult(offSet.intValue());
			crit.setMaxResults(req.getPageSize().intValue());

			@SuppressWarnings("unchecked")
			List<? extends Persistent> list = crit.list();
			if (list != null && !list.isEmpty()) {
				result.setResultList(list);
				result.setTotalSize(totalSize);
			} else {
				result.setTotalSize(new Long(0));
			}
		} else {
			result.setTotalSize(new Long(0));
		}
		return result;
	}

	// end of the management booking list

	@SuppressWarnings("unchecked")
	@Override
	public PropertyRentPrice getPropertyRentPriceByCtxIdAndId(Long ctxId, Long id) throws Exception {
		List<PropertyRentPrice> list = getHibernateTemplate()
				.find("from PropertyRentPrice prp where prp.ctxId = ? and prp.id = ?  ", new Object[] { ctxId, id });
		return list.size() > 0 ? list.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Invoice getInvoiceByCtxIdAndId(Long ctxId, Long id) throws Exception {
		List<Invoice> list = getHibernateTemplate().find("from Invoice i where i.ctxId = ? and i.id = ?  ",
				new Object[] { ctxId, id });
		return list.size() > 0 ? list.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public EndUser getBillByCtxIdAndId(Long ctxId, Long id) throws Exception {
		List<EndUser> list = getHibernateTemplate().find("from EndUser b where b.ctxId = ? and b.id = ?  ",
				new Object[] { ctxId, id });
		return list.size() > 0 ? list.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public RentPurpose getRentPurposeByCtxIdAndId(Long ctxId, Long id) throws Exception {
		List<RentPurpose> list = getHibernateTemplate().find("from RentPurpose rp where rp.ctxId = ? and rp.id = ?  ",
				new Object[] { ctxId, id });
		return list.size() > 0 ? list.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Property> getPropertyListByCtxId(Long ctxId) throws Exception {
		List<Property> list = getHibernateTemplate().find("from Property p where p.ctxId = ?", new Object[] { ctxId });
		return list.size() > 0 ? list : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RentPurpose> getRentPurposeListByCtxId(Long ctxId) throws Exception {
		List<RentPurpose> list = getHibernateTemplate().find("from RentPurpose rp where rp.ctxId = ?",
				new Object[] { ctxId });
		return list.size() > 0 ? list : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public EndUser getEndUserByCtxIdAndId(Long ctxId, Long id) throws Exception {
		List<EndUser> list = getHibernateTemplate().find("from EndUser eu where eu.ctxId = ? and eu.id = ?  ",
				new Object[] { ctxId, id });
		return list.size() > 0 ? list.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public EndUser getEndUserByCtxIdAndOrgIdAndUserName(Long ctxId, Long orgId, String username) throws Exception {
		List<EndUser> list = getHibernateTemplate().find(
				"from EndUser eu where eu.ctxId = ? and eu.orgId = ? and eu.userName = ?  ",
				new Object[] { ctxId, orgId, username });
		return list.size() > 0 ? list.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Property getPropertyByName(Long ctxId, String name) throws Exception {
		List<Property> list = getHibernateTemplate().find("from Property pn where pn.ctxId = ? and pn.name = ? ",
				new Object[] { ctxId, name });
		return list.size() > 0 ? list.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PropertyRentPrice getPropertyRentPriceByCtxIdAndOrgIdAndPropertyIdAndPurposeIdAndSlotType(Long ctxId,
			Long orgId, Long propertyId, Long purposeId, SlotType slotType) throws Exception {
		List<PropertyRentPrice> list = getHibernateTemplate().find(
				"from PropertyRentPrice prp where prp.ctxId = ? and prp.orgId = ? and prp.propertyId = ? and prp.purposeId = ? and prp.slotType = ? ",
				new Object[] { ctxId, orgId, propertyId, purposeId, slotType });
		return list.size() > 0 ? list.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Invoice> getInvoiceListByCtxId(Long ctxId) throws Exception {
		List<Invoice> list = getHibernateTemplate().find("from Invoice i where i.ctxId = ?", new Object[] { ctxId });
		return list.size() > 0 ? list : null;
	}

	@SuppressWarnings("deprecation")
	@Override
	public PaginatedResponse getPaymentGatewayDetailsList(Long ctxId, PaginatedRequest req) throws Exception {
		Long offSet = new Long(0);
		offSet = (req.getReqPage() * req.getPageSize());

		PaginatedResponse result = new PaginatedResponse();
		// Update the Request
		result.setClazz(req.getClazz());
		result.setReqPage(req.getReqPage());
		result.setPageSize(req.getPageSize());
		result.setSearchString(req.getSearchString());

		// Build Criteria
		Criteria crit = getHibernateTemplate().getSession().createCriteria(req.getClazz());
		crit.add(Restrictions.eq("ctxId", ctxId));
		crit.addOrder(Order.desc("id"));
		crit.addOrder(Order.desc("updatedTime"));
		crit.setProjection(Projections.rowCount());
		Long totalSize = (Long) crit.uniqueResult();
		if (totalSize != null && totalSize > 0) {
			crit.setProjection(null);
			crit.setFirstResult(offSet.intValue());
			crit.setMaxResults(req.getPageSize().intValue());
			@SuppressWarnings("unchecked")
			List<? extends Persistent> list = crit.list();
			if (list != null && !list.isEmpty()) {
				result.setResultList(list);
				result.setTotalSize(totalSize);
			} else {
				result.setTotalSize(new Long(0)); 
			}
		} else {
			result.setTotalSize(new Long(0));
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PaymentGatewayDetails getPaymentGatewayDetailsByCtxIdAndId(Long ctxId, Long id) throws Exception {
		List<PaymentGatewayDetails> list = getHibernateTemplate().find(
				"from PaymentGatewayDetails pgd where pgd.ctxId = ? and pgd.id = ?  ", new Object[] { ctxId, id });
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public PaginatedResponse getTotalStatusList(Long ctxId, PaginatedRequest req) throws Exception {
		Long offSet = new Long(0);
		offSet = (req.getReqPage() * req.getPageSize());

		PaginatedResponse result = new PaginatedResponse();
		// Update the Request
		result.setClazz(req.getClazz());
		result.setReqPage(req.getReqPage());
		result.setPageSize(req.getPageSize());
		result.setSearchString(req.getSearchString());

		// Build Criteria
		@SuppressWarnings("deprecation")
		Criteria crit = getHibernateTemplate().getSession().createCriteria(req.getClazz());
		crit.add(Restrictions.eq("ctxId", ctxId));
		crit.addOrder(Order.desc("id"));
		crit.addOrder(Order.desc("updatedTime"));
		crit.setProjection(Projections.rowCount());
		Long totalSize = (Long) crit.uniqueResult();
		if (totalSize != null && totalSize > 0) {
			crit.setProjection(null);
			crit.setFirstResult(offSet.intValue());
			crit.setMaxResults(req.getPageSize().intValue());
			@SuppressWarnings("unchecked")
			List<? extends Persistent> list = crit.list();
			if (list != null && !list.isEmpty()) {
				result.setResultList(list);
				result.setTotalSize(totalSize);
			} else {
				result.setTotalSize(new Long(0));
			}
		} else {
			result.setTotalSize(new Long(0));
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TotalStatus getTotalStatusByCtxIdAndId(Long ctxId, Long id) throws Exception {
		List<TotalStatus> list = getHibernateTemplate().find("from TotalStatus pgd where ts.ctxId = ? and ts.id = ?  ",
				new Object[] { ctxId, id });
		return list.size() > 0 ? list.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Long getApplicationDocMaxId() throws Exception {
		List<Long> list = getHibernateTemplate().find("select max(id) from EndUser");
		return list.size() > 0 ? list.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PaymentGatewayDetails getPaymentGatewayDetailsByCtxIdAndOrgId(Long ctxId, long orgId) throws Exception {
		List<PaymentGatewayDetails> list = getHibernateTemplate().find(
				"from PaymentGatewayDetails pgd where pgd.ctxId = ? and pgd.orgId = ?  ",
				new Object[] { ctxId, orgId });
		return list.size() > 0 ? list.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getEndUserByCtxIdAndName(Long ctxId, String name) throws Exception {
		List<String> list = getHibernateTemplate().find("from EndUser eui where eui.ctxId = ? and eui.name = ?  ",
				new Object[] { ctxId, name });
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public PaginatedResponse getSmsGatewayDetailsList(Long ctxId, PaginatedRequest req) throws Exception {
		Long offSet = new Long(0);
		offSet = (req.getReqPage() * req.getPageSize());

		PaginatedResponse result = new PaginatedResponse();
		// Update the Request
		result.setClazz(req.getClazz());
		result.setReqPage(req.getReqPage());
		result.setPageSize(req.getPageSize());
		result.setSearchString(req.getSearchString());

		// Build Criteria
		@SuppressWarnings("deprecation")
		Criteria crit = getHibernateTemplate().getSession().createCriteria(req.getClazz());
		crit.add(Restrictions.eq("ctxId", ctxId));
		crit.addOrder(Order.desc("id"));
		crit.addOrder(Order.desc("updatedTime"));
		crit.setProjection(Projections.rowCount());
		Long totalSize = (Long) crit.uniqueResult();
		if (totalSize != null && totalSize > 0) {
			crit.setProjection(null);
			crit.setFirstResult(offSet.intValue());
			crit.setMaxResults(req.getPageSize().intValue());
			@SuppressWarnings("unchecked")
			List<? extends Persistent> list = crit.list();
			if (list != null && !list.isEmpty()) {
				result.setResultList(list);
				result.setTotalSize(totalSize);
			} else {
				result.setTotalSize(new Long(0));
			}
		} else {
			result.setTotalSize(new Long(0));
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public SmsGatewayDetails getSmsGatewayDetailsByCtxIdAndId(Long ctxId, Long id) throws Exception {
		List<SmsGatewayDetails> list = getHibernateTemplate()
				.find("from SmsGatewayDetails sgd where sgd.ctxId = ? and sgd.id = ? ", new Object[] { ctxId, id });
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public PaginatedResponse getBillList(Long ctxId, PaginatedRequest req) throws Exception {
		Long offSet = new Long(0);
		offSet = (req.getReqPage() * req.getPageSize());

		PaginatedResponse result = new PaginatedResponse();
		// Update the Request
		result.setClazz(req.getClazz());
		result.setReqPage(req.getReqPage());
		result.setPageSize(req.getPageSize());
		result.setSearchString(req.getSearchString());

		// Build Criteria
		@SuppressWarnings("deprecation")
		Criteria crit = getHibernateTemplate().getSession().createCriteria(req.getClazz());
		crit.add(Restrictions.eq("ctxId", ctxId));
		crit.add(Restrictions.eq("paymentStatus", PaymentStatus.PAID));
		crit.add(Restrictions.eq("userState", UserState.ACTIVE));


		crit.addOrder(Order.desc("id"));
		crit.setProjection(Projections.rowCount());

		Long totalSize = (Long) crit.uniqueResult();
		if (totalSize > 0) {
			crit.setProjection(null);
			crit.setFirstResult(offSet.intValue());
			crit.setMaxResults(req.getPageSize().intValue());

			@SuppressWarnings("unchecked")
			List<? extends Persistent> list = crit.list();
			if (list != null && !list.isEmpty()) {
				result.setResultList(list);
				result.setTotalSize(totalSize);
			} else {
				result.setTotalSize(new Long(0));
			}
		} else {
			result.setTotalSize(new Long(0));
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public EndUser getEndUserByCtxIdAndIdAndDate(Long ctxId, long id, Date bookDate) throws Exception {
		List<EndUser> list = getHibernateTemplate().find(
				"from EndUser eu where eu.ctxId = ? and eu.id = ?  and bookDate = 'after(new Date())'",
				new Object[] { ctxId, id, bookDate });
		return list.size() > 0 ? list.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PaymentGatewayDetails getPaymentGatewayDetailsByClientCode(String clientcode) throws Exception {
		List<PaymentGatewayDetails> list = getHibernateTemplate()
				.find("from PaymentGatewayDetails pgd where pgd.clientcode = ? ", new Object[] { clientcode });
		return list.size() > 0 ? list.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public EndUser getEndUserByUsername(String clientcode) throws Exception {
		List<EndUser> list = getHibernateTemplate().find("from EndUser eu where eu.userName = ? ",
				new Object[] { clientcode });
		return list.size() > 0 ? list.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Invoice getInvoiceByCtxIdAndClientCode(Long ctxId, String clientcode) throws Exception {
		List<Invoice> list = getHibernateTemplate().find("from Invoice ivc where ivc.ctxId = ? and ivc.clientCode = ? ",
				new Object[] { ctxId, clientcode });
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public PaginatedResponse getPastBillList(Long ctxId, PaginatedRequest req) throws Exception {
		Long offSet = new Long(0);
		offSet = (req.getReqPage() * req.getPageSize());
		PaginatedResponse result = new PaginatedResponse();
		// Update the Request
		result.setClazz(req.getClazz());
		result.setReqPage(req.getReqPage());
		result.setPageSize(req.getPageSize());
		result.setSearchString(req.getSearchString());

		// Build Criteria
		@SuppressWarnings("deprecation")
		Criteria crit = getHibernateTemplate().getSession().createCriteria(req.getClazz());
		crit.add(Restrictions.eq("ctxId", ctxId));
		crit.add(Restrictions.eq("paymentStatus", PaymentStatus.PAID));

		Calendar calStart = new GregorianCalendar();
		calStart.setTime(new Date());
		calStart.set(Calendar.HOUR_OF_DAY, 0);
		calStart.set(Calendar.MINUTE, 0);
		calStart.set(Calendar.SECOND, 0);
		calStart.set(Calendar.MILLISECOND, 0);
		Date date000000 = calStart.getTime();

		crit.add(Restrictions.lt("bookingDate", date000000));
		System.out.println("past d1" + date000000);

		crit.addOrder(Order.desc("id"));
		crit.setProjection(Projections.rowCount());

		Long totalSize = (Long) crit.uniqueResult();
		if (totalSize > 0) {
			crit.setProjection(null);
			crit.setFirstResult(offSet.intValue());
			crit.setMaxResults(req.getPageSize().intValue());

			@SuppressWarnings("unchecked")
			List<? extends Persistent> list = crit.list();
			if (list != null && !list.isEmpty()) {
				result.setResultList(list);
				result.setTotalSize(totalSize);
			} else {
				result.setTotalSize(new Long(0));
			}
		} else {
			result.setTotalSize(new Long(0));
		}
		return result;
	}

	@Override
	public PaginatedResponse getTodayBillList(Long ctxId, PaginatedRequest req) throws Exception {
		Long offSet = new Long(0);
		offSet = (req.getReqPage() * req.getPageSize());
		PaginatedResponse result = new PaginatedResponse();
		// Update the Request
		result.setClazz(req.getClazz());
		result.setReqPage(req.getReqPage());
		result.setPageSize(req.getPageSize());
		result.setSearchString(req.getSearchString());

		// Build Criteria
		@SuppressWarnings("deprecation")
		Criteria crit = getHibernateTemplate().getSession().createCriteria(req.getClazz());
		crit.add(Restrictions.eq("ctxId", ctxId));
		crit.add(Restrictions.eq("paymentStatus", PaymentStatus.PAID));

		Calendar calStart = new GregorianCalendar();
		calStart.setTime(new Date());
		calStart.set(Calendar.HOUR_OF_DAY, 0);
		calStart.set(Calendar.MINUTE, 0);
		calStart.set(Calendar.SECOND, 0);
		calStart.set(Calendar.MILLISECOND, 0);
		Date date000000 = calStart.getTime();

		Calendar calEnd = new GregorianCalendar();
		calEnd.setTime(new Date());
		calEnd.set(Calendar.HOUR_OF_DAY, 23);
		calEnd.set(Calendar.MINUTE, 59);
		calEnd.set(Calendar.SECOND, 59);
		calEnd.set(Calendar.MILLISECOND, 999);
		Date date235959 = calEnd.getTime();

		crit.add(Restrictions.ge("bookingDate", date000000));
		crit.add(Restrictions.le("bookingDate", date235959));

		System.out.println("cur d1" + date000000);
		System.out.println("cur d2" + date235959);

		crit.addOrder(Order.desc("id"));
		crit.setProjection(Projections.rowCount());

		Long totalSize = (Long) crit.uniqueResult();
		if (totalSize > 0) {
			crit.setProjection(null);
			crit.setFirstResult(offSet.intValue());
			crit.setMaxResults(req.getPageSize().intValue());

			@SuppressWarnings("unchecked")
			List<? extends Persistent> list = crit.list();
			if (list != null && !list.isEmpty()) {
				result.setResultList(list);
				result.setTotalSize(totalSize);
			} else {
				result.setTotalSize(new Long(0));
			}
		} else {
			result.setTotalSize(new Long(0));
		}
		return result;
	}

	@Override
	public PaginatedResponse getFutureBillList(Long ctxId, PaginatedRequest req) throws Exception {
		Long offSet = new Long(0);
		offSet = (req.getReqPage() * req.getPageSize());
		PaginatedResponse result = new PaginatedResponse();
		// Update the Request
		result.setClazz(req.getClazz());
		result.setReqPage(req.getReqPage());
		result.setPageSize(req.getPageSize());
		result.setSearchString(req.getSearchString());

		// Build Criteria
		@SuppressWarnings("deprecation")
		Criteria crit = getHibernateTemplate().getSession().createCriteria(req.getClazz());
		crit.add(Restrictions.eq("ctxId", ctxId));
		crit.add(Restrictions.eq("paymentStatus", PaymentStatus.PAID));

		Calendar calEnd = new GregorianCalendar();
		calEnd.setTime(new Date());
		calEnd.set(Calendar.HOUR_OF_DAY, 23);
		calEnd.set(Calendar.MINUTE, 59);
		calEnd.set(Calendar.SECOND, 59);
		calEnd.set(Calendar.MILLISECOND, 999);
		Date date235959 = calEnd.getTime();

		crit.add(Restrictions.gt("bookingDate", date235959));
		System.out.println("fut d1" + date235959);

		crit.addOrder(Order.desc("id"));
		crit.setProjection(Projections.rowCount());

		Long totalSize = (Long) crit.uniqueResult();
		if (totalSize > 0) {
			crit.setProjection(null);
			crit.setFirstResult(offSet.intValue());
			crit.setMaxResults(req.getPageSize().intValue());

			@SuppressWarnings("unchecked")
			List<? extends Persistent> list = crit.list();
			if (list != null && !list.isEmpty()) {
				result.setResultList(list);
				result.setTotalSize(totalSize);
			} else {
				result.setTotalSize(new Long(0));
			}
		} else {
			result.setTotalSize(new Long(0));
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PropertyRentPrice getPropertyRentPriceByCtxIdAndOrgIdAndPropertyIdAndPurposeId(Long ctxId, Long orgId,
			Long propertyId, Long purposeId) throws Exception {
		List<PropertyRentPrice> list = getHibernateTemplate().find(
				"from PropertyRentPrice prp where prp.ctxId = ? and prp.orgId = ?  and prp.propertyId = ? and prp.purposeId = ? ",
				new Object[] { ctxId, orgId, propertyId, purposeId });
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public PaginatedResponse getSuccessInvoiceList(Long ctxId, PaginatedRequest req) throws Exception {
		Long offSet = new Long(0);
		offSet = (req.getReqPage() * req.getPageSize());

		PaginatedResponse result = new PaginatedResponse();
		// Update the Request
		result.setClazz(req.getClazz());
		result.setReqPage(req.getReqPage());
		result.setPageSize(req.getPageSize());
		result.setSearchString(req.getSearchString());

		// Build Criteria
		@SuppressWarnings("deprecation")
		Criteria crit = getHibernateTemplate().getSession().createCriteria(req.getClazz());
		crit.add(Restrictions.eq("ctxId", ctxId));
		crit.add(Restrictions.eq("paymentStatus", PaymentStatus.PAID));

		crit.addOrder(Order.desc("id"));
		crit.addOrder(Order.desc("updatedTime"));
		crit.setProjection(Projections.rowCount());
		Long totalSize = (Long) crit.uniqueResult();
		if (totalSize != null && totalSize > 0) {
			crit.setProjection(null);
			crit.setFirstResult(offSet.intValue());
			crit.setMaxResults(req.getPageSize().intValue());
			@SuppressWarnings("unchecked")
			List<? extends Persistent> list = crit.list();
			if (list != null && !list.isEmpty()) {
				result.setResultList(list);
				result.setTotalSize(totalSize);
			} else {
				result.setTotalSize(new Long(0));
			}
		} else {
			result.setTotalSize(new Long(0));
		}
		return result;
	}

	@Override
	public PaginatedResponse getPendingInvoiceList(Long ctxId, PaginatedRequest req) throws Exception {
		Long offSet = new Long(0);
		offSet = (req.getReqPage() * req.getPageSize());

		PaginatedResponse result = new PaginatedResponse();
		// Update the Request
		result.setClazz(req.getClazz());
		result.setReqPage(req.getReqPage());
		result.setPageSize(req.getPageSize());
		result.setSearchString(req.getSearchString());

		// Build Criteria
		@SuppressWarnings("deprecation")
		Criteria crit = getHibernateTemplate().getSession().createCriteria(req.getClazz());
		crit.add(Restrictions.eq("ctxId", ctxId));
		crit.add(Restrictions.eq("paymentStatus", PaymentStatus.PENDING));
		crit.addOrder(Order.desc("id"));
		crit.addOrder(Order.desc("updatedTime"));
		crit.setProjection(Projections.rowCount());
		Long totalSize = (Long) crit.uniqueResult();
		if (totalSize != null && totalSize > 0) {
			crit.setProjection(null);
			crit.setFirstResult(offSet.intValue());
			crit.setMaxResults(req.getPageSize().intValue());
			@SuppressWarnings("unchecked")
			List<? extends Persistent> list = crit.list();
			if (list != null && !list.isEmpty()) {
				result.setResultList(list);
				result.setTotalSize(totalSize);
			} else {
				result.setTotalSize(new Long(0));
			}
		} else {
			result.setTotalSize(new Long(0));
		}
		return result;
	}

	@Override
	public PaginatedResponse getFailInvoiceList(Long ctxId, PaginatedRequest req) throws Exception {
		Long offSet = new Long(0);
		offSet = (req.getReqPage() * req.getPageSize());

		PaginatedResponse result = new PaginatedResponse();
		// Update the Request
		result.setClazz(req.getClazz());
		result.setReqPage(req.getReqPage());
		result.setPageSize(req.getPageSize());
		result.setSearchString(req.getSearchString());

		// Build Criteria
		@SuppressWarnings("deprecation")
		Criteria crit = getHibernateTemplate().getSession().createCriteria(req.getClazz());
		crit.add(Restrictions.eq("ctxId", ctxId));
		crit.add(Restrictions.eq("paymentStatus", PaymentStatus.FAILED));
		crit.addOrder(Order.desc("id"));
		crit.addOrder(Order.desc("updatedTime"));
		crit.setProjection(Projections.rowCount());
		Long totalSize = (Long) crit.uniqueResult();
		if (totalSize != null && totalSize > 0) {
			crit.setProjection(null);
			crit.setFirstResult(offSet.intValue());
			crit.setMaxResults(req.getPageSize().intValue());
			@SuppressWarnings("unchecked")
			List<? extends Persistent> list = crit.list();
			if (list != null && !list.isEmpty()) {
				result.setResultList(list);
				result.setTotalSize(totalSize);
			} else {
				result.setTotalSize(new Long(0));
			}
		} else {
			result.setTotalSize(new Long(0));
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Long getPastBillCount(Long ctxId, Long orgId) throws Exception {
		List<Long> list = getHibernateTemplate().find(
				"select count (*) from EndUser eub where eub.ctxId = ? and eub.orgId = ? and eub.bookingDate < current_date and eub.paymentStatus = ?",
				new Object[] { ctxId, orgId, PaymentStatus.PAID });
		return list.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Long getTodayBillCount(Long ctxId, Long orgId) throws Exception {
		List<Long> list = getHibernateTemplate().find(
				"select count (*) from EndUser b where b.ctxId = ? and b.orgId = ? and b.bookingDate = current_date and b.paymentStatus = ? ",
				new Object[] { ctxId, orgId, PaymentStatus.PAID });
		return list.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Long getFutureBillCount(Long ctxId, Long orgId) throws Exception {
		List<Long> list = getHibernateTemplate().find(
				"select count (*) from EndUser b where b.ctxId = ? and b.orgId = ? and b.bookingDate > current_date and b.paymentStatus = ? ",
				new Object[] { ctxId, orgId, PaymentStatus.PAID });
		return list.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EndUser> getBlockedInvoiceByCtxIdAndOrgId(Long ctxId, Long orgId) throws Exception {
		List<EndUser> list = getHibernateTemplate().find(
				"from EndUser eui where eui.ctxId = ? and eui.orgId = ? and eui.createdTime > ? ",
				new Object[] { ctxId, orgId, (new Date(System.currentTimeMillis() + (10 * 60 * 60 * 1000))) });
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EndUser> getBlockedBillByCtxIdAndOrgId(Long ctxId, Long orgId) throws Exception {
		List<EndUser> list = getHibernateTemplate().find(
				"from EndUser eub where eub.ctxId = ? and eub.orgId = ? and eub.bookingDate > ? ",
				new Object[] { ctxId, orgId, (new Date(System.currentTimeMillis())) });
		return list;
	}

	@Override
	public PaginatedResponse getMarqueeList(Long ctxId, PaginatedRequest req) throws Exception {
		Long offSet = new Long(0);
		offSet = (req.getReqPage() * req.getPageSize());

		PaginatedResponse result = new PaginatedResponse();
		// Update the Request
		result.setClazz(req.getClazz());
		result.setReqPage(req.getReqPage());
		result.setPageSize(req.getPageSize());
		result.setSearchString(req.getSearchString());

		// Build Criteria
		@SuppressWarnings("deprecation")
		Criteria crit = getHibernateTemplate().getSession().createCriteria(req.getClazz());
		crit.add(Restrictions.eq("ctxId", ctxId));
		crit.addOrder(Order.desc("id"));
		crit.addOrder(Order.desc("updatedTime"));
		crit.setProjection(Projections.rowCount());
		Long totalSize = (Long) crit.uniqueResult();
		if (totalSize != null && totalSize > 0) {
			crit.setProjection(null);
			crit.setFirstResult(offSet.intValue());
			crit.setMaxResults(req.getPageSize().intValue());
			List<? extends Persistent> list = crit.list();
			if (list != null && !list.isEmpty()) {
				result.setResultList(list);
				result.setTotalSize(totalSize);
			} else {
				result.setTotalSize(new Long(0));
			}
		} else {
			result.setTotalSize(new Long(0));
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Marquee getMarqueeByCtxIdAndOrgIdAndId(Long ctxId, Long orgId, long id) throws Exception {
		List<Marquee> list = getHibernateTemplate().find(
				"from Marquee mt where mt.ctxId = ? and mt.orgId = ?  and mt.id=? ", new Object[] { ctxId, orgId, id });
		return list.size() > 0 ? list.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Marquee getLatestMarqueeByCtxIdAndOrgIdAndType(Long ctxId, Long orgId, MarqueeType type) throws Exception {
		List<Marquee> list = getHibernateTemplate().find(
				"from Marquee mt where m"
						+ "t.ctxId = ? and mt.orgId = ?  and mt.type = ? order by mt.createdTime desc",
				new Object[] { ctxId, orgId, type });
		return list.size() > 0 ? list.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public EndUser getAddBookingByCtxIdAndOrgIdAndId(Long ctxId, long orgId, Long id) throws Exception {
		List<EndUser> list = getHibernateTemplate().find(
				"from EndUser eu where eu.ctxId = ? and eu.orgId = ? and eu.id = ? ",
				new Object[] { ctxId, orgId, id });
		return list.size() > 0 ? list.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Invoice getInvoiceByCtxIdAndOrgIdAndClientCode(Long ctxId, Long orgId, String clientCode) throws Exception {
		List<Invoice> list = getHibernateTemplate().find(
				"from Invoice inv where inv.ctxId = ? and inv.orgId = ? and inv.clientCode = ? ",
				new Object[] { ctxId, orgId, clientCode });
		return list.size() > 0 ? list.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public EndUser getAddBookingByCtxIdAndOrgIdAndUsername(Long ctxId, Long orgId, String username) throws Exception {
		List<EndUser> list = getHibernateTemplate().find(
				"from EndUser eu where eu.ctxId = ? and eu.orgId = ? and eu.userName = ? ",
				new Object[] { ctxId, orgId, username });
		return list.size() > 0 ? list.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ForgotPasswd getForgotPasswdByEmail(String email) throws Exception {
		List<ForgotPasswd> list = getHibernateTemplate().find("from ForgotPasswd fp where fp.email = ? ",new Object[] { email });
		return list.size() > 0 ? list.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EndUser> getBlockedBillByCtxIdAndOrgIdAndPropertyId(Long ctxId, Long orgId, Long venueId)
			throws Exception {
	
		List<EndUser> list = getHibernateTemplate().find(
				"from EndUser eub where eub.ctxId = ? and eub.orgId = ? and eub.propertyId=? and eub.bookingDate > ? and eub.userState = ?  and eub.paymentStatus=? ",
				new Object[] { ctxId, orgId, venueId, (new Date(System.currentTimeMillis())), UserState.ACTIVE , PaymentStatus.PAID});
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public EndUser getEndUserByCtxIdAndOrgIdAndApplicationId(Long ctxId, Long orgId, String applicationId)
			throws Exception {
		List<EndUser> list = getHibernateTemplate().find(
				"from EndUser eu where eu.ctxId = ? and eu.orgId = ? and eu.applicationId = ? ",
				new Object[] { ctxId, orgId, applicationId });
		return list.size() > 0 ? list.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EndUser> getEndUserListByCtxId(Long ctxId) throws Exception {
		List<EndUser> list = getHibernateTemplate().find(
				"from EndUser eu where eu.ctxId = ? and eu.userState = ? and eu.paymentStatus = ? ",
				new Object[] { ctxId, UserState.INACTIVE, PaymentStatus.PAID});
		return list;
	}
	@SuppressWarnings("unchecked")
	@Override
	public PaginatedResponse getEndUserListByCtxIdAndFromDateAndToDateAndVenue(Long ctxId, PaginatedRequest req, String fromDate, String toDate,
			Long venue) throws Exception {
		/*List<EndUser> list = getHibernateTemplate().find(
				"from EndUser eu where eu.ctxId = ? and eu.propertyId = ? and eu.userState = ? and eu.paymentStatus = ? ",
				new Object[] { ctxId,venue, UserState.ACTIVE, PaymentStatus.PAID});
		return list;*/
		
		
		
		Long offSet = new Long(0);
		offSet = (req.getReqPage() * req.getPageSize());
		PaginatedResponse result = new PaginatedResponse();
		// Update the Request
		result.setClazz(req.getClazz());
		result.setReqPage(req.getReqPage());
		result.setPageSize(req.getPageSize());
		result.setSearchString(req.getSearchString());

		// Build Criteria
		@SuppressWarnings("deprecation")
		Criteria crit = getHibernateTemplate().getSession().createCriteria(req.getClazz());
		crit.add(Restrictions.eq("ctxId", ctxId));
		crit.add(Restrictions.eq("paymentStatus", PaymentStatus.PAID));
		crit.add(Restrictions.eq("userState", UserState.ACTIVE));
		if(venue != 0L)
			crit.add(Restrictions.eq("propertyId", venue));
		
		Date fromDate1 = new SimpleDateFormat("yyyy-MM-dd").parse(fromDate);
		Date toDate1 = new SimpleDateFormat("yyyy-MM-dd").parse(toDate);
		
		crit.add(Restrictions.ge("bookingDate", fromDate1)); 
		crit.add(Restrictions.lt("bookingDate", toDate1));

		crit.addOrder(Order.desc("id"));
		crit.setProjection(Projections.rowCount());

		Long totalSize = (Long) crit.uniqueResult();
		if (totalSize > 0) {
			crit.setProjection(null);
			crit.setFirstResult(offSet.intValue());
			crit.setMaxResults(req.getPageSize().intValue());

			@SuppressWarnings("unchecked")
			List<? extends Persistent> list = crit.list();
			if (list != null && !list.isEmpty()) {
				result.setResultList(list);
				result.setTotalSize(totalSize);
			} else {
				result.setTotalSize(new Long(0));
			}
		} else {
			result.setTotalSize(new Long(0));
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EndUser> getEndUserPaidList() throws Exception {
		List<EndUser> list = getHibernateTemplate().find(
				"from EndUser eu where eu.userState = ? and eu.paymentStatus = ? and eu.bookingDate >? ",
				new Object[] {UserState.ACTIVE, PaymentStatus.PAID, (new Date(System.currentTimeMillis()))});
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EndUser> getEndUserComingBookingList() throws Exception {
		List<EndUser> list = getHibernateTemplate().find(
				"from EndUser eu where eu.userState = ? and eu.paymentStatus = ? and eu.bookingDate > ?  ",
				new Object[] {UserState.ACTIVE, PaymentStatus.PAID, (new Date(System.currentTimeMillis()))});
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EndUser> getEndUserCompletedBookingList() throws Exception {
		List<EndUser> list = getHibernateTemplate().find(
				"from EndUser eu where eu.userState = ? and eu.paymentStatus = ? and eu.bookingDate < ?  ",
				new Object[] {UserState.ACTIVE, PaymentStatus.PAID, (new Date(System.currentTimeMillis()))});
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EndUser> getEndUserTodayBookingList() throws Exception {
		List<EndUser> list = getHibernateTemplate().find(
				"from EndUser eu where eu.userState = ? and eu.paymentStatus = ? and eu.bookingDate = ?  ",
				new Object[] {UserState.ACTIVE, PaymentStatus.PAID, (new Date(System.currentTimeMillis()))});
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EndUser> getInvoicePaidList() throws Exception {
		List<EndUser> list = getHibernateTemplate().find(
				"from EndUser eu where eu.userState = ? and eu.paymentStatus = ? ",
				new Object[] {UserState.ACTIVE, PaymentStatus.PAID});
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EndUser> getInvoicePendingList() throws Exception {
		List<EndUser> list = getHibernateTemplate().find(
				"from EndUser eu where eu.userState = ? and eu.paymentStatus = ? ",
				new Object[] {UserState.ACTIVE, PaymentStatus.PENDING});
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EndUser> getInvoiceFailedList() throws Exception {
		List<EndUser> list = getHibernateTemplate().find(
				"from EndUser eu where eu.userState = ? and eu.paymentStatus = ? ",
				new Object[] {UserState.ACTIVE, PaymentStatus.FAILED});
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EndUser> getEndUserCancelledList() throws Exception {
		List<EndUser> list = getHibernateTemplate().find(
				"from EndUser eu where eu.userState = ? and eu.paymentStatus = ? ",
				new Object[] {UserState.INACTIVE, PaymentStatus.PAID});
		return list;
	}
}
