package com.bytesedge.bookvenue.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

public class BasicAuthProvider extends DaoAuthenticationProvider {
	private static final Logger logger = LoggerFactory.getLogger(BasicAuthProvider.class);

	@Override
	public void additionalAuthenticationChecks(org.springframework.security.core.userdetails.UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		additionalAuthenticationChecksImpl(userDetails, authentication);
	}
	
    protected void additionalAuthenticationChecksImpl(UserDetails userDetails,
            UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		if (authentication.getCredentials() == null) {
			logger.error("Authentication failed: no credentials provided");

			throw new BadCredentialsException(messages.getMessage(
					"AbstractUserDetailsAuthenticationProvider.badCredentials",
					"Bad credentials"));
		}
    }
}
