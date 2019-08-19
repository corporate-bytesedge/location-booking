package com.bytesedge.bookvenue.auth;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.bytesedge.bookvenue.common.CacheService;
import com.bytesedge.bookvenue.common.DbService;
import com.bytesedge.bookvenue.model.LoginHistory;

@Component
public class AuthSuccessListener implements ApplicationListener<InteractiveAuthenticationSuccessEvent> {
	private static Logger logger = LoggerFactory.getLogger(AuthSuccessListener.class);
	
	@Override
	public void onApplicationEvent(InteractiveAuthenticationSuccessEvent event) {
		User userDetails = (User) event.getAuthentication().getPrincipal();
		LoginHistory lh = new LoginHistory();
		com.bytesedge.bookvenue.model.User user;
		try {
			String username = userDetails.getUsername();
			user = CacheService.getUserByLoginName(userDetails.getUsername());
			if(user != null) {
				lh.setCtxId(user.getCtxId());
				lh.setLoginName(userDetails.getUsername());
				lh.setLoginTime(new Date());
				lh.setUserId(user.getId());
				lh.setOrgId(user.getOrgId());
				lh.setSessionId("test");
				
				// Save to DB
				DbService.getInstance().getUserService().saveOrUpdateLoginHistory(lh);
				if(user.getAuthFailed() != null && user.getAuthFailed() > 0L){
					com.bytesedge.bookvenue.model.User dbUser = DbService.getInstance().getUserService().getUserByLoginName(username);
					if(dbUser != null) {
						if(dbUser.getAuthFailed() > 0L) {
							user.setAuthFailed(0L);
							DbService.getInstance().getUserService().saveOrUpdateUser(dbUser);
							CacheService.removeKey(CacheService.KEY_USER_BY_LOGIN_NAME + userDetails.getUsername());
						}
					}
				}
			}
		}catch (Exception e) {
			logger.error("Error " + e.getMessage(), e);
		}
	}
}