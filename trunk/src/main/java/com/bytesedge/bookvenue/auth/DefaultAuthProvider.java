package com.bytesedge.bookvenue.auth;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;

public class DefaultAuthProvider extends DaoAuthenticationProvider {

	@Override
	public void additionalAuthenticationChecks(org.springframework.security.core.userdetails.UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		super.additionalAuthenticationChecks(userDetails, authentication);
	}
}
