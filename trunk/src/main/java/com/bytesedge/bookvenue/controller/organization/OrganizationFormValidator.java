package com.bytesedge.bookvenue.controller.organization;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.bytesedge.bookvenue.controller.FormValidator;
import com.bytesedge.bookvenue.model.Organization;
import com.bytesedge.bookvenue.util.FormatValidatorUtil;
import com.bytesedge.bookvenue.util.StringUtil;

@Component
public class OrganizationFormValidator extends FormValidator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Organization.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object model, Errors errors) {
		Organization organizationForm = (Organization) model;
		
		// Validate Captcha if required
		validateCaptcha(organizationForm, errors);
		
		if (StringUtil.isEmpty(organizationForm.getName()) || organizationForm.getName().length() > 32
				|| organizationForm.getName().length() < 1) {
			errors.rejectValue("name", "error.input.organization.create-form.name", "Name is required.");
		}
		if (!FormatValidatorUtil.validateMobileNumber(organizationForm.getMobileNumber())) {
			errors.rejectValue("mobileNumber", "error.input.organization.create-form.mobileNumber", "Mobile Number is required.");
		}
		if (StringUtil.isEmpty(organizationForm.getPhoneNumber()) || organizationForm.getPhoneNumber().length() !=11) {
			errors.rejectValue("phone Number", "error.input.organization.create-form.phone Number", "Phone Number is required.");
		}
		if (StringUtil.isEmpty(organizationForm.getEmailId()) ||  !FormatValidatorUtil.validateEmailId(organizationForm.getEmailId())) {
			errors.rejectValue("emailId", "error.input.user.create-form.emailId", "EmailId is required.");
		}
	}
}