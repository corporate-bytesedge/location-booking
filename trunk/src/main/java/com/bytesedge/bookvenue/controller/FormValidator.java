package com.bytesedge.bookvenue.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.bytesedge.bookvenue.model.ContextDo;

@Component
public abstract class FormValidator implements Validator {
    protected static final String LATITUDE_PATTERN="^(\\+|-)?(?:90(?:(?:\\.0{1,6})?)|(?:[0-9]|[1-8][0-9])(?:(?:\\.[0-9]{1,6})?))$";
    protected static final String LONGITUDE_PATTERN="^(\\+|-)?(?:180(?:(?:\\.0{1,6})?)|(?:[0-9]|[1-9][0-9]|1[0-7][0-9])(?:(?:\\.[0-9]{1,6})?))$";

	@Autowired
	private HttpServletRequest context;
	
	public void validateCaptcha(ContextDo dao, Errors errors) {
		if(!CaptchaFilter.verify(context)) {
			dao.setCaptchaText("");
			errors.rejectValue("captchaText", "error.captcha", "Enter a valid captcha");
		}
		dao.setCaptchaText("");
	}

	public HttpServletRequest getContext() {
		return context;
	}

	public void setContext(HttpServletRequest context) {
		this.context = context;
	}
	
}