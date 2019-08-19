package com.bytesedge.bookvenue.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.bytesedge.bookvenue.controller.CaptchaServlet;

public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		if (!request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}
		
		// Validate the Captcha
		String inputCaptchaString = request.getParameter("captchaText");
		if(inputCaptchaString != null) {
			String sessionCaptchaString = (String) request.getSession(false).getAttribute(CaptchaServlet.SESSION_CAPTCHA_KEY);
			if(sessionCaptchaString != null) {
				if(!inputCaptchaString.equals(sessionCaptchaString)) {
					throw new AuthenticationServiceException("Captcha did not matched");
				}
			} else {
				throw new AuthenticationServiceException("Invalid Captcha");
			}
		} else {
			throw new AuthenticationServiceException("Invalid Captcha");
		}

		String username = obtainUsername(request);
		String password = obtainPassword(request);

		if (username == null) {
			username = "";
		}

		if (password == null) {
			password = "";
		}

		username = username.trim();

		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
				username, password);

		// Allow subclasses to set the "details" property
		setDetails(request, authRequest);

		return this.getAuthenticationManager().authenticate(authRequest);
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		super.unsuccessfulAuthentication(request, response, failed);
	}

}