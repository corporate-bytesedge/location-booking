package com.bytesedge.bookvenue.controller.user;

import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.bytesedge.bookvenue.controller.FormValidator;
import com.bytesedge.bookvenue.model.User;
import com.bytesedge.bookvenue.util.FormatValidatorUtil;
import com.bytesedge.bookvenue.util.StringUtil;

@Component
public class UserFormValidator extends FormValidator {

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object model, Errors errors) {
		User userForm = (User) model;

		// Validate Captcha if required
		//validateCaptcha(userForm, errors);

		if (StringUtil.isEmpty(userForm.getLoginName()) || userForm.getLoginName().length() > 127) {
			errors.rejectValue("loginName", "error.input.user.create-form.loginName", "LoginName is required.");
		}
		if (StringUtil.isEmpty(userForm.getLoginPassword())  || userForm.getLoginPassword().length()> 127) {
			errors.rejectValue("loginPassword", "error.input.user.create-form.loginPassword", "LoginPassword is required.");
		}
		if (StringUtil.isEmpty(userForm.getEmail()) ||  !FormatValidatorUtil.validateEmailId(userForm.getEmail())) {
			errors.rejectValue("email", "error.input.user.create-form.email", "Email is required.");
		}
		if (StringUtil.isEmpty(userForm.getDisplayName()) ||  userForm.getDisplayName().length() > 32 || !FormatValidatorUtil.validateName(userForm.getDisplayName())) {
			errors.rejectValue("displayName", "error.input.user.create-form.displayName", "DisplayName is required.");
		}
		if (userForm.getGender()== null) {
			errors.rejectValue("gender", "error.input.user.create-form.gender", "Gender is required.");
		}
		if (!FormatValidatorUtil.validateAadharNumber(userForm.getAadharId())) {
			errors.rejectValue("aadharId", "error.input.user.create-form.aadharId", "AadharId is required.");
		}
		if (userForm.getMobileNumber().length() != 10 || !FormatValidatorUtil.validateMobileNumber(userForm.getMobileNumber())) {
			errors.rejectValue("mobileNumber", "error.input.user.create-form.mobileNumber", "Mobile Number is required.");
		}
		if (userForm.getDob() == null || userForm.getDob().after(new Date())) {
			errors.rejectValue("dob", "error.input.user.create-form.dob", "Dob is required.");
		}
		if (StringUtil.isEmpty(userForm.getDesignation()) ||  userForm.getDesignation().length() > 63) {
			errors.rejectValue("designation", "error.input.user.create-form.designation", "Designation is required.");
		}
		if (userForm.getRoleId() == null || userForm.getRoleId() < 1) {
			errors.rejectValue("roleId", "error.input.user.create-form.roleId", "RoleId is required.");
		}
	}
}