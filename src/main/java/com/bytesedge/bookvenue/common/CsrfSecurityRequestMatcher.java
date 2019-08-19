package com.bytesedge.bookvenue.common;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class CsrfSecurityRequestMatcher implements RequestMatcher {
	private Pattern allowedMethods = Pattern.compile("^(GET|HEAD|TRACE|OPTIONS)$");
	private RegexRequestMatcher unprotectedMatcher1 = new RegexRequestMatcher("^(/api/).*$", null);
	private RegexRequestMatcher unprotectedMatcher2 = new RegexRequestMatcher("^(/apu/ext/).*$", null);

	@Override
	public boolean matches(HttpServletRequest request) {
		if (allowedMethods.matcher(request.getMethod()).matches()) {
			return false;
		}
		
		if(unprotectedMatcher1.matches(request) || unprotectedMatcher2.matches(request)) {
			return false;
		} else {
			return true;
		}
	}
}