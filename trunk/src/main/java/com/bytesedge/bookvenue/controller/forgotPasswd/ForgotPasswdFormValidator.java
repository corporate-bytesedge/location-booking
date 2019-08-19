package com.bytesedge.bookvenue.controller.forgotPasswd;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.bytesedge.bookvenue.common.DbService;
import com.bytesedge.bookvenue.controller.FormValidator;
import com.bytesedge.bookvenue.model.ForgotPasswd;
import com.bytesedge.bookvenue.model.User;
import com.bytesedge.bookvenue.util.FormatValidatorUtil;
import com.bytesedge.bookvenue.util.StringUtil;



@Component
public class ForgotPasswdFormValidator extends FormValidator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ForgotPasswd.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object model, Errors errors) {
		ForgotPasswd forgotPasswdForm = (ForgotPasswd) model;

		// Validate Captcha if required
		validateCaptcha(forgotPasswdForm, errors);
		

		if (forgotPasswdForm.getMobileNumber().length() != 10 || !FormatValidatorUtil.validateMobileNumber(forgotPasswdForm.getMobileNumber())) {
			errors.rejectValue("mobileNumber", "error.input.mobileNumber","Mobile Number doesn't exit .");	
		}else if(forgotPasswdForm.getId() <= 0){
			
			try {
				
				User dbObject = (User) DbService.getInstance().getUserService().getUserByLoginName(forgotPasswdForm.getEmail());
				
					String dbMobileNumber = dbObject.getMobileNumber();
					String formMobileNumber = forgotPasswdForm.getMobileNumber();
					if (!dbMobileNumber.equals(formMobileNumber)) {
						errors.rejectValue("mobileNumber", "error.input.mobileNumber", "Mobile number didn't found on our database.");					}
				} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (StringUtil.isEmpty(forgotPasswdForm.getEmail()) ||  !FormatValidatorUtil.validateEmailId(forgotPasswdForm.getEmail())) {
			errors.rejectValue("email", "error.input.email", "eMail is required.");
		}else if(forgotPasswdForm.getId() <= 0){
			User dbObject;
			try {
				dbObject = (User) DbService.getInstance().getUserService().getUserByLoginName(forgotPasswdForm.getEmail());
				if (dbObject == null) {
					errors.rejectValue("email", "error.input.login", "Email doesn't exists.");
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		if(!StringUtil.isEmpty(forgotPasswdForm.getPassword()) && !StringUtil.isEmpty(forgotPasswdForm.getConfirmPassword())
				&& !forgotPasswdForm.getConfirmPassword().equals(forgotPasswdForm.getPassword())  ) {
			errors.rejectValue("confirmPassword", "error.input.confirmPassword","Password and Confirm Password should match.");	

				
			}
		

	}

	
}

