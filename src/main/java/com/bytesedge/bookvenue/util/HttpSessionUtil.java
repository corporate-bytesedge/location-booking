package com.bytesedge.bookvenue.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class HttpSessionUtil {
	public static final String SESSION_CAPTCHA_KEY = "session.captcha.key";

	public static String getCaptchaText(HttpServletRequest request) {
		try {
			HttpSession session = request.getSession(false);
			return (String) session.getAttribute(SESSION_CAPTCHA_KEY);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static String getCaptchaTextAndReset(HttpServletRequest request) {
		try {
			HttpSession session = request.getSession(false);
			String sessionCaptchaString = (String) session.getAttribute(SESSION_CAPTCHA_KEY);
			session.setAttribute(SESSION_CAPTCHA_KEY, System.currentTimeMillis());
			return sessionCaptchaString;
		} catch (Exception e) {
			return null;
		}
	}
}