package com.bytesedge.bookvenue.controller.smsGatewayDetails;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.bytesedge.bookvenue.controller.FormValidator;
import com.bytesedge.bookvenue.model.SmsGatewayDetails;

@Component
public class SmsGatewayDetailsFormValidator extends FormValidator {

	@Override
	public boolean supports(Class<?> clazz) {
		return SmsGatewayDetails.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object model, Errors errors) {
		SmsGatewayDetails smsGatewayDetailsForm = (SmsGatewayDetails) model;

		// Validate Captcha if required
		//validateCaptcha(smsGatewayDetailsForm, errors);
	}
}