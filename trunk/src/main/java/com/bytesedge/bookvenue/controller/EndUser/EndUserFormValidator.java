package com.bytesedge.bookvenue.controller.EndUser;

import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.bytesedge.bookvenue.controller.FormValidator;
import com.bytesedge.bookvenue.model.EndUser;
import com.bytesedge.bookvenue.model.IdProofType;
import com.bytesedge.bookvenue.util.FormatValidatorUtil;
import com.bytesedge.bookvenue.util.StringUtil;

@Component
public class EndUserFormValidator extends FormValidator {

	@Override
	public boolean supports(Class<?> clazz) {
		return EndUser.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object model, Errors errors) {
		EndUser endUserForm = (EndUser) model;
		
		// Validate Captcha if required
		//validateCaptcha(endUserForm, errors);
		if (StringUtil.isEmpty(endUserForm.getName()) || endUserForm.getName().length() > 64
				|| endUserForm.getName().length() < 1  || !FormatValidatorUtil.validateName(endUserForm.getName())) {
			errors.rejectValue("name", "error.input.endUser.create-form.name", "Enter valid name.");
		}
		if (StringUtil.isEmpty(endUserForm.getEmail()) ||  !FormatValidatorUtil.validateEmailId(endUserForm.getEmail())) {
			errors.rejectValue("email", "error.input.endUser.create-form.email", "Enter valid Email.");
		}
		if (endUserForm.getBookingDate() == null || endUserForm.getBookingDate().before(new Date())) {
			errors.rejectValue("bookingDate", "error.input.endUser.create-form.date", "Enter valid Date.");
		}
		if(endUserForm.getIdProofType() != null){
			if(endUserForm.getIdProofType() == IdProofType.AADHAAR){
				if (StringUtil.isEmpty(endUserForm.getIdProof()) ||  !FormatValidatorUtil.validateAadharNumber(endUserForm.getIdProof())) {
					errors.rejectValue("idProof", "error.input.endUser.create-form.aadhaar", "Aadhaar is required.");
				}
			 else if(endUserForm.getIdProofType() == IdProofType.PAN){
				if (StringUtil.isEmpty(endUserForm.getIdProof()) ||  !FormatValidatorUtil.validatePanNumber(endUserForm.getIdProof())) {
					errors.rejectValue("idProof", "error.input.endUser.create-form.pan", "Pan is required.");
				}
			} else if(endUserForm.getIdProofType() == IdProofType.VOTERID){
				if (StringUtil.isEmpty(endUserForm.getIdProof()) ||  endUserForm.getIdProof().length() !=10) {
					errors.rejectValue("idProof", "error.input.endUser.create-form.voterId", "Voter id is required.");
				}
			} else if(endUserForm.getIdProofType() == IdProofType.DRIVINGLICENSE){
				if (StringUtil.isEmpty(endUserForm.getIdProof()) ||  endUserForm.getIdProof().length() < 10) {
					errors.rejectValue("idProof", "error.input.endUser.create-form.drivingLicense", "Driving license is required.");
				}
			} 
			} else {
				errors.rejectValue("idProofType", "error.input.endUser.create-form.aadhaar", "ID proof type is required.");
			}
		}
		if (endUserForm.getMobileNumber().length() != 10 || !FormatValidatorUtil.validateMobileNumber(endUserForm.getMobileNumber())) {
			errors.rejectValue("mobileNumber", "error.input.endUser.create-form.mobileNumber", "Enter vailid Moblile number.");
		}
		if (StringUtil.isEmpty(endUserForm.getAddr()) || endUserForm.getAddr().length() > 255
				|| endUserForm.getAddr().length() < 5) {
			errors.rejectValue("addr", "error.input.endUser.create-form.addr", "Enter valid address.");
		}
	}
}
