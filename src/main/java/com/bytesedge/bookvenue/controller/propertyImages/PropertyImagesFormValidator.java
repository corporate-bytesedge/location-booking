package com.bytesedge.bookvenue.controller.propertyImages;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.bytesedge.bookvenue.controller.FormValidator;
import com.bytesedge.bookvenue.model.PropertyImages;
import com.bytesedge.bookvenue.util.StringUtil;

@Component
public class PropertyImagesFormValidator extends FormValidator {

	@Override
	public boolean supports(Class<?> clazz) {
		return PropertyImages.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object model, Errors errors) {
		PropertyImages propertyImagesForm = (PropertyImages) model;
		
		// Validate Captcha if required
		validateCaptcha(propertyImagesForm, errors);
		
		if (StringUtil.isEmpty(propertyImagesForm.getName()) || propertyImagesForm.getName().length() > 32
				|| propertyImagesForm.getName().length() < 1) {
			errors.rejectValue("name", "error.input.propertyImages.create-form.name", "Name is required.");
		}
		
	}
}