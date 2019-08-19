package com.bytesedge.bookvenue.controller.RentPurpose;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.bytesedge.bookvenue.controller.FormValidator;
import com.bytesedge.bookvenue.model.RentPurpose;
import com.bytesedge.bookvenue.util.StringUtil;

@Component
public class RentPurposeFormValidator extends FormValidator {

	@Override
	public boolean supports(Class<?> clazz) {
		return RentPurpose.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object model, Errors errors) {
		RentPurpose rentPurposeForm = (RentPurpose) model;

		// Validate Captcha if required
		//validateCaptcha(rentPurposeForm, errors);
		if (!StringUtil.isEmpty(rentPurposeForm.getDescr())) {
			if (rentPurposeForm.getDescr().length() > 1024 || rentPurposeForm.getDescr().length() < 6) {
				errors.rejectValue("descr", "error.input.rentPurpose.create-form.descr", "Description is required.");
			}
		}
		if (StringUtil.isEmpty(rentPurposeForm.getName()) || rentPurposeForm.getName().length() > 64
				|| rentPurposeForm.getName().length() < 1) {
			errors.rejectValue("name", "error.input.rentPurpose.create-form.name", "Name is required.");
		}

	}
}