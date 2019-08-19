package com.bytesedge.bookvenue.controller.addBooking;

import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.bytesedge.bookvenue.controller.FormValidator;
import com.bytesedge.bookvenue.model.EndUser;
import com.bytesedge.bookvenue.model.IdProofType;
import com.bytesedge.bookvenue.util.FormatValidatorUtil;
import com.bytesedge.bookvenue.util.StringUtil;

@Component
public class AddBookingFormValidator extends FormValidator {

	@Override
	public boolean supports(Class<?> clazz) {
		return EndUser.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object model, Errors errors) {
		EndUser addBookingForm = (EndUser) model;

		// Validate Captcha if required
		//validateCaptcha(addBookingForm, errors);

		if (StringUtil.isEmpty(addBookingForm.getName()) || addBookingForm.getName().length() > 64
				|| addBookingForm.getName().length() < 1 || !FormatValidatorUtil.validateName(addBookingForm.getName())) {
			errors.rejectValue("name", "error.input.name", "Enter valid Name.");
		}
		if (addBookingForm.getBookingDate() == null || addBookingForm.getBookingDate().before(new Date())) {
			errors.rejectValue("bookingDate", "error.input.bookingDate", "Date is required.");
		}
		if (!FormatValidatorUtil.validateMobileNumber(addBookingForm.getMobileNumber())) {
			errors.rejectValue("mobileNumber", "error.input.mobileNo", "Enter valid Mobile number.");
		}
		if (StringUtil.isEmpty(addBookingForm.getEmail()) ||  !FormatValidatorUtil.validateEmailId(addBookingForm.getEmail())) {
			errors.rejectValue("email", "error.input.emailId", "Enter valid Email id.");
		}
		
		/*if(addBookingForm.getIdProofType() != null){
			if(addBookingForm.getIdProofType() == IdProofType.AADHAAR){
				if (!FormatValidatorUtil.validateAadharNumber(addBookingForm.getIdProof())) {
					errors.rejectValue("idProof", "error.input.aadhaar", "Aadhaar is required.");
				}
			 else if(addBookingForm.getIdProofType() == IdProofType.PAN){
				if (!FormatValidatorUtil.validatePanNumber(addBookingForm.getIdProof())) {
					errors.rejectValue("idProof", "error.input.pan", "Pan is required.");
				}
			} else if(addBookingForm.getIdProofType() == IdProofType.VOTERID){
				if ( addBookingForm.getIdProof().length() !=10) {
					errors.rejectValue("idProof", "error.input.voterId", "Voter id is required.");
				}
			} else if(addBookingForm.getIdProofType() == IdProofType.DRIVINGLICENSE){
				if (addBookingForm.getIdProof().length() < 10) {
					errors.rejectValue("idProof", "error.input.drivingLicense", "Driving license is required.");
				}
			}
			} else {
				errors.rejectValue("idProofType", "error.input.aadhaar", "ID proof type is required.");
			}
		}*/
		if (StringUtil.isEmpty(addBookingForm.getAddr()) || addBookingForm.getAddr().length() > 255
				|| addBookingForm.getAddr().length() < 5) {
			errors.rejectValue("addr", "error.input.endUser.create-form.addr", "Enter valid address.");
		}
		/*if (addBookingForm.getReferredBy().length() > 64 || addBookingForm.getReferredBy().length() < 1 || !FormatValidatorUtil.validateName(addBookingForm.getReferredBy())) {
			errors.rejectValue("referredBy", "error.input.name", "Enter valid Name.");
		}*/
	}
	
}