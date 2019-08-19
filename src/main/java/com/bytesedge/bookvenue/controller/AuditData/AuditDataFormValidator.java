package com.bytesedge.bookvenue.controller.AuditData;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.bytesedge.bookvenue.controller.FormValidator;
import com.bytesedge.bookvenue.model.AuditData;

@Component
public class AuditDataFormValidator extends FormValidator {

	@Override
	public boolean supports(Class<?> clazz) {
		return AuditData.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object model, Errors errors) {
		AuditData auditDataForm = (AuditData) model;

		// Validate Captcha if required
		validate(auditDataForm, errors);

		if (auditDataForm.getOperation() == null) {
			errors.rejectValue("operation", "error.input.auditDatar.create-form.operation", "operation is required.");
		}
		if (auditDataForm.getAuditObject() == null) {
			errors.rejectValue("auditObject", "error.input.auditDatar.create-form.auditObject", "Name of application is required.");
		}
		if (auditDataForm.getSrc() == null) {
			errors.rejectValue("Src", "error.input.auditDatar.create-form.Src", "Src is required.");
		}

		if (auditDataForm.getDst() == null) {
			errors.rejectValue("Dst", "error.input.auditDatar.create-form.Dst", "Dst is required.");
		}
		
		if (auditDataForm.getCreatedTime() == null) {
			errors.rejectValue("UpdatedTime", "error.input.auditDatar.create-form.UpdatedTime", "UpdatedTime is required.");
		}

		if (auditDataForm.getCreatedUserName() == null) {
			errors.rejectValue("CreatedUserName", "error.input.auditDatar.create-form.CreatedUserName", "CreatedUserName is required.");
		}

		if (auditDataForm.getUpdatedTime() == null) {
			errors.rejectValue("UpdatedTime", "error.input.auditDatar.create-form.UpdatedTime", "UpdatedTime is required.");
		}

		if (auditDataForm.getUpdatedUserName() == null) {
			errors.rejectValue("UpdatedUserName", "error.input.auditDatar.create-form.UpdatedUserName", "UpdatedUserName is required.");
		}

	}
}