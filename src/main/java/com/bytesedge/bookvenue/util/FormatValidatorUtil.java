package com.bytesedge.bookvenue.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class FormatValidatorUtil {
	public static final String SESSION_CAPTCHA_KEY = "session.captcha.key";

	public static boolean validateEmailId(String email) {
		try {
			final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
					+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
			
			Pattern pattern = Pattern.compile(EMAIL_PATTERN);
			Matcher matcher = pattern.matcher(email);
			return matcher.matches();
		} catch (Exception e) {
			// ignore
		}
		return false;
	}
	
	
	public static boolean validateName(String name) {
		try {
			final String NAME_PATTERN = "^([a-zA-Z](\\s)*[\\.]*)*[!@#$*&()\\-`.+,/\"]*([a-zA-Z](\\s)*[\\.]*)*$";
			
			Pattern pattern = Pattern.compile(NAME_PATTERN);
			Matcher matcher = pattern.matcher(name);
			return matcher.matches();
		} catch (Exception e) {
			// ignore
		}
		return false;
	}
	
	public static boolean validateMobileNumber(String mobileNumber) {
		if(mobileNumber == null) {
			return false;
		}
		
		if(mobileNumber.length() != 10) {
			return false;
		}
		
		try {
			final String MOBILENUMBER_PATTERN = "^(?:(?:\\+|0{0,2})91(\\s*[\\-]\\s*)?|[0]?)?[56789]\\d{9}$";
			
			Pattern pattern = Pattern.compile(MOBILENUMBER_PATTERN);
			Matcher matcher = pattern.matcher(mobileNumber);
			return matcher.matches();
		} catch (Exception e) {
			// ignore
		}
		return false;
	}

	public static boolean validateAadharNumber(String aadharNumber){
        Pattern aadharPattern = Pattern.compile("\\d{12}");
        boolean isValidAadhar = aadharPattern.matcher(aadharNumber).matches();
        if(isValidAadhar){
            isValidAadhar = VerhoeffAlgorithm.validateVerhoeff(aadharNumber);
        }
        return isValidAadhar;
    }
	
	public static boolean validatePanNumber(String panNumber){
		try{
		Pattern panPattern = Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]{1}");
		Matcher matcher = panPattern.matcher(panNumber);
		return matcher.matches();
	}catch (Exception e) {
		return false;
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