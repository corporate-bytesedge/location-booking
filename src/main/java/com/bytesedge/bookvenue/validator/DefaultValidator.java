package com.bytesedge.bookvenue.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;

public class DefaultValidator extends Validator {
	private static Logger logger = LoggerFactory.getLogger(DefaultValidator.class.getName());

	@Override
	public boolean supports(Class arg0) {
		return arg0.equals(Object.class);
	}

	@Override
	public void validate(Object command, Errors errors) {

	}
}
