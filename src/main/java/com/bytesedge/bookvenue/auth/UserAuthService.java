package com.bytesedge.bookvenue.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bytesedge.bookvenue.common.CacheService;
import com.bytesedge.bookvenue.common.DaoException;
import com.bytesedge.bookvenue.model.User;
import com.bytesedge.bookvenue.model.UserState;

@Component
public class UserAuthService implements UserDetailsService, ApplicationContextAware {
	private static Logger log = LoggerFactory.getLogger(UserAuthService.class);

	@SuppressWarnings("unused")
	@Autowired
	private ApplicationContext appContext = null;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = Throwable.class)
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException, DataAccessException {
		try {
			if(name == null) {
				log.error("Invalid username:" + name);
				throw new UsernameNotFoundException("Invalid Username");
			}
			
			// pre processing
			name = name.trim();
			
			User user = CacheService.getUserByLoginName(name.trim());
			if(user == null) {
				log.error("No user found with username:" + name);
				throw new UsernameNotFoundException("Invalid username");
			}
			
			if(user.getAccountState() != UserState.ACTIVE) {
				log.error("Inative user found with username: " + name);
				throw new UsernameNotFoundException("Inative User");
			}
			
			// Permissions
			final Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
			grantedAuthorities.addAll(CacheService.getGrantedAuthorityByRoleId(user.getCtxId(), user.getRoleId()));
			
			// Org type
			grantedAuthorities.addAll(CacheService.getGrantedAuthorityUserScopeByOrgId(user.getCtxId(), user.getOrgId()));
			
			return new org.springframework.security.core.userdetails.User(user.getLoginName(),
					user.getLoginPassword(), grantedAuthorities);
		} catch (DaoException e) {
			log.error(e.getMessage(), e.getCause());
			throw new UsernameNotFoundException(e.getMessage(), e);
		} catch(Throwable t) {
			log.error(t.getMessage(), t.getCause());
			throw new UsernameNotFoundException(t.getMessage(), t.getCause());
		}
	}

	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		this.appContext = arg0;
	}
}