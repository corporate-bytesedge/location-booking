package com.bytesedge.bookvenue.controller.paymentGatewayDetails;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.bytesedge.bookvenue.controller.FormValidator;
import com.bytesedge.bookvenue.model.PaymentGatewayDetails;
import com.bytesedge.bookvenue.util.FormatValidatorUtil;
import com.bytesedge.bookvenue.util.StringUtil;

@Component
public class PaymentGatewayDetailsFormValidator extends FormValidator {

	@Override
	public boolean supports(Class<?> clazz) {
		return PaymentGatewayDetails.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object model, Errors errors) {
		PaymentGatewayDetails paymentGatewayDetailsForm = (PaymentGatewayDetails) model;

		// Validate Captcha if required
		validateCaptcha(paymentGatewayDetailsForm, errors);
		if (StringUtil.isEmpty(paymentGatewayDetailsForm.getPassword())
				|| paymentGatewayDetailsForm.getPassword().length() > 127) {
			errors.rejectValue("loginPassword", "error.input.paymentGatewayDetails.create-form.loginPassword",
					"LoginPassword is required.");
		}
	}
}