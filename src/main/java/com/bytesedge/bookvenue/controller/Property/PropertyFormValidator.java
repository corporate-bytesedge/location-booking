package com.bytesedge.bookvenue.controller.Property;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.bytesedge.bookvenue.controller.FormValidator;
import com.bytesedge.bookvenue.model.Property;
import com.bytesedge.bookvenue.util.StringUtil;

@Component
public class PropertyFormValidator extends FormValidator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Property.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object model, Errors errors) {
		Property propertyForm = (Property) model;
		
		// Validate Captcha if required
		//validateCaptcha(propertyForm, errors);
		if (StringUtil.isEmpty(propertyForm.getUniqueId()) || propertyForm.getUniqueId().length() > 16
				|| propertyForm.getUniqueId().length() < 1) {
			errors.rejectValue("uniqueId", "error.input.property.create-form.uniqueId", "Unique id is required.");
		}
		if (!StringUtil.isEmpty(propertyForm.getDescr())) {
			if(propertyForm.getDescr().length() > 1024 || propertyForm.getDescr().length() <6){
			errors.rejectValue("descr", "error.input.property.create-form.descr", "Description is required.");
			}
		}
		if (StringUtil.isEmpty(propertyForm.getName()) || propertyForm.getName().length() > 64
				|| propertyForm.getName().length() < 1) {
			errors.rejectValue("name", "error.input.property.create-form.name", "Name is required.");
		}
		
	}
}