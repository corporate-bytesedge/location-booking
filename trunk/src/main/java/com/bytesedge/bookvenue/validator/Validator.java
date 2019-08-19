package com.bytesedge.bookvenue.validator;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public abstract class Validator implements org.springframework.validation.Validator {

	protected HttpServletRequest getRequest() {
		ServletRequestAttributes reqAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		return reqAttributes.getRequest();
	}

	public static boolean isNULL(final String str) {
		return str == null || str.matches("^\\s*$");
	}

	public static boolean isValidEmail(final String email) {
		return isNULL(email) ? false : EmailValidator.getInstance().isValid(email);
	}

	public boolean isValidPasswd(final String passwd) {
		// TODO implement
		return true;
	}
}
