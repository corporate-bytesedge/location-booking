package com.bytesedge.bookvenue.auth;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

import com.bytesedge.bookvenue.common.CacheService;
import com.bytesedge.bookvenue.common.DbService;
import com.bytesedge.bookvenue.model.LoginHistory;
import com.bytesedge.bookvenue.model.UserState;

@Component
public class AuthFailedListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {
	private static Logger logger = LoggerFactory.getLogger(AuthFailedListener.class);
	
	@Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
		LoginHistory lh = new LoginHistory();
		com.bytesedge.bookvenue.model.User user = null;
		String userName = null;
		try {
			if(((UsernamePasswordAuthenticationToken)event.getSource()).getPrincipal() instanceof org.springframework.security.core.userdetails.User) {
				org.springframework.security.core.userdetails.User userdetails = (org.springframework.security.core.userdetails.User)((UsernamePasswordAuthenticationToken)event.getSource()).getPrincipal();
				user = CacheService.getUserByLoginName(userdetails.getUsername());
				userName = user.getLoginName();
			} else if(((UsernamePasswordAuthenticationToken)event.getSource()).getPrincipal() instanceof String) {
				userName = (String)((UsernamePasswordAuthenticationToken)event.getSource()).getPrincipal();
				user = CacheService.getUserByLoginName(userName);
			}
			
			if(user != null) {
				lh.setCtxId(user.getCtxId());
				lh.setLoginName(userName);
				lh.setLoginTime(new Date());
				lh.setUserId(user.getId());
				lh.setOrgId(user.getOrgId());
				lh.setSessionId("test");
				
				// Save to DB
				DbService.getInstance().getUserService().saveOrUpdateLoginHistory(lh);
				com.bytesedge.bookvenue.model.User dbUser = DbService.getInstance().getUserService().getUserByLoginName(userName);
				if(dbUser != null) {
					dbUser.setAuthFailedTime(new Date());
					dbUser.setAuthFailed(user.getAuthFailed() == null ? 1 : user.getAuthFailed() + 1);
					if(dbUser.getAuthFailed() >= 9) {
						dbUser.setLockedTime(new Date()); 
						DbService.getInstance().getUserService().saveOrUpdateUserAuthFailedAndState(dbUser, dbUser.getAuthFailed() + 1, UserState.LOCKED);
					} else { 
						DbService.getInstance().getUserService().saveOrUpdateUserAuthFailedAndState(dbUser, dbUser.getAuthFailed() + 1, UserState.ACTIVE);
					}
					CacheService.removeKey(CacheService.KEY_USER_BY_LOGIN_NAME + userName);	
				}
			}
		} catch (Exception e) {
			logger.error("Error " + e.getMessage(), e);
		}
    }
}