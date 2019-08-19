package com.bytesedge.bookvenue.controller.totalStatus;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.bytesedge.bookvenue.controller.FormValidator;
import com.bytesedge.bookvenue.model.TotalStatus;
import com.bytesedge.bookvenue.util.FormatValidatorUtil;
import com.bytesedge.bookvenue.util.StringUtil;

@Component
public class TotalStatusFormValidator extends FormValidator {

	@Override
	public boolean supports(Class<?> clazz) {
		return TotalStatus.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object model, Errors errors) {
		TotalStatus managementBookingForm = (TotalStatus) model;

		// Validate Captcha if required
		validateCaptcha(managementBookingForm, errors);

		if (StringUtil.isEmpty(managementBookingForm.getName()) || managementBookingForm.getName().length() > 64
				|| managementBookingForm.getName().length() < 1) {
			errors.rejectValue("name", "error.input.ManagementBooking.create-form.name", "Name is required.");
		}
		if (!FormatValidatorUtil.validateMobileNumber(managementBookingForm.getMobileNo())) {
			errors.rejectValue("mobileNo", "error.input.ManagementBooking.create-form.mobileNo", "Mobile number  is required.");
		}
		if (StringUtil.isEmpty(managementBookingForm.getEmailId()) ||  !FormatValidatorUtil.validateEmailId(managementBookingForm.getEmailId())) {
			errors.rejectValue("emailId", "error.input.ManagementBooking.create-form.emailId", "Email is required.");
		}
	}
	
}