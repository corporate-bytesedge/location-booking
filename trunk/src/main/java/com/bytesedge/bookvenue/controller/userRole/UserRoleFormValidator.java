package com.bytesedge.bookvenue.controller.userRole;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.bytesedge.bookvenue.controller.FormValidator;
import com.bytesedge.bookvenue.model.UserRole;
import com.bytesedge.bookvenue.util.StringUtil;

@Component
public class UserRoleFormValidator extends FormValidator {

	@Override
	public boolean supports(Class<?> clazz) {
		return UserRole.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object model, Errors errors) {
		UserRole userRoleForm = (UserRole) model;
		
		// Validate Captcha if required
		validateCaptcha(userRoleForm, errors);
		
		if (StringUtil.isEmpty(userRoleForm.getName())|| userRoleForm.getName().length() > 32) {
			errors.rejectValue("name", "error.input.userRole.create-form.Name", "Name is required.");
		}
	}
}