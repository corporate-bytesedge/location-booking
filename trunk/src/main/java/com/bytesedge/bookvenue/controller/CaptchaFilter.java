package com.bytesedge.bookvenue.controller;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.bytesedge.bookvenue.util.HttpSessionUtil;

public class CaptchaFilter extends UsernamePasswordAuthenticationFilter {
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
				return null;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		final HttpServletRequest request = (HttpServletRequest) req;
		final HttpServletResponse response = (HttpServletResponse) res;
		// 01 Captcha validation
		String captchaSessionString = HttpSessionUtil.getCaptchaTextAndReset(request);
		if(captchaSessionString != null) {
			String captchaInputString = request.getParameter(CaptchaServlet.SESSION_CAPTCHA_KEY);
			if(!captchaSessionString.equals(captchaInputString)) {
				throw new ServletException("CAPTCHA_ERROR");
			}
		} else {
			throw new ServletException("CAPTCHA_ERROR");
		}
		chain.doFilter(request, response);
	}
	
	public static boolean verify(HttpServletRequest request) {
		String inputCaptchaString = (String) request.getParameter(CaptchaServlet.INPUT_CAPTCHA_KEY);
		if(inputCaptchaString != null) {
			String sessionCaptchaString = HttpSessionUtil.getCaptchaTextAndReset(request);;
			if(sessionCaptchaString != null) {
				if(inputCaptchaString.equals(sessionCaptchaString)) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}