package com.bytesedge.bookvenue.controller.PropertyRentPrice;

import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.bytesedge.bookvenue.controller.FormValidator;
import com.bytesedge.bookvenue.model.PropertyRentPrice;
import com.bytesedge.bookvenue.util.StringUtil;

@Component
public class PropertyRentPriceFormValidator extends FormValidator {

	@Override
	public boolean supports(Class<?> clazz) {
		return PropertyRentPrice.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object model, Errors errors) {
		PropertyRentPrice propertyRentPriceForm = (PropertyRentPrice) model;
		
		// Validate Captcha if required
		//validateCaptcha(propertyRentPriceForm, errors);
	}
}