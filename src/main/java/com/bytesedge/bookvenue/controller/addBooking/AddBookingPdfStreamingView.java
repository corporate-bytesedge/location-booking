package com.bytesedge.bookvenue.controller.addBooking;

import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bytesedge.bookvenue.common.CacheService;
import com.bytesedge.bookvenue.controller.ControllerUtil;
import com.bytesedge.bookvenue.model.EndUser;
import com.bytesedge.bookvenue.model.User;
import com.bytesedge.bookvenue.util.CurrencyUtil;
import com.bytesedge.bookvenue.view.PdfStreamingViewIntf;
import com.lowagie.text.pdf.PdfStamper;

public class AddBookingPdfStreamingView implements PdfStreamingViewIntf {

	@SuppressWarnings("unchecked")
	@Override
	public void mergePdfDocument(Map<String, Object> model, PdfStamper stamper, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> map = (Map<String, Object>) model.get("map");
		// Load the Object with context id
		EndUser addBooking = (EndUser) map.get("obj");
		if (addBooking != null) {
			addBooking.setPropertyName(
					CacheService.getPropertyById(ControllerUtil.getContextId(request), addBooking.getPropertyId()).getName());
			addBooking.setPurposeName(
					CacheService.getPurposeById(ControllerUtil.getContextId(request), addBooking.getPurposeId()).getName());
			addBooking.setCreatedUserName(CacheService
					.getUserById(ControllerUtil.getContextId(request), addBooking.getCreatedUserId()).getDisplayName());
		stamper.setFormFlattening(true);
		stamper.getAcroFields().setField("name", addBooking.getName());
		stamper.getAcroFields().setField("email", addBooking.getEmail());
		stamper.getAcroFields().setField("word",
				CurrencyUtil.convertToIndianCurrency(addBooking.getTotal().toString()));
		String pattern = "dd-MMM-yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		stamper.getAcroFields().setField("bookingDate", simpleDateFormat.format(addBooking.getBookingDate()));
		stamper.getAcroFields().setField("purpose", addBooking.getPurposeName());
		stamper.getAcroFields().setField("referredBy", addBooking.getReferredBy());
		stamper.getAcroFields().setField("createdTime", simpleDateFormat.format(addBooking.getCreatedTime()));
		if(addBooking.getTotal() != null){
		stamper.getAcroFields().setField("subTotal", addBooking.getPrice().toString());
		}
		stamper.getAcroFields().setField("name", addBooking.getName());
		stamper.getAcroFields().setField("type", addBooking.getIdProofType().toString());
		stamper.getAcroFields().setField("idProof", addBooking.getIdProof());
		stamper.getAcroFields().setField("venue", addBooking.getPropertyName());
		stamper.getAcroFields().setField("purpose", addBooking.getPurposeName());
		stamper.getAcroFields().setField("mobileNumber", addBooking.getMobileNumber());
		stamper.getAcroFields().setField("price", addBooking.getPrice().toString());
		stamper.getAcroFields().setField("address", addBooking.getAddr());
		stamper.getAcroFields().setField("total", addBooking.getTotal().toString());
		stamper.getAcroFields().setField("invoiceId", addBooking.getApplicationId());
		stamper.getAcroFields().setField("cgst", addBooking.getCgst().toString());
		stamper.getAcroFields().setField("sgst", addBooking.getSgst().toString());
		stamper.getAcroFields().setField("amt", addBooking.getTotal().toString());
		stamper.getAcroFields().setField("serviceTax",addBooking.getServiceTax().toString());
		stamper.getAcroFields().setField("cgsts", addBooking.getCgst().toString());
		stamper.getAcroFields().setField("sgsts", addBooking.getSgst().toString());
		

	}
	}
	}
