package com.bytesedge.bookvenue.common;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.matcher.RequestMatcher;

public class CsrfApiSecurityRequestMatcher implements RequestMatcher {
	@Override
	public boolean matches(HttpServletRequest request) {
		return false;
	}
}