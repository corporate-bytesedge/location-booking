package com.bytesedge.bookvenue.controller.totalStatus;

import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bytesedge.bookvenue.model.EndUser;
import com.bytesedge.bookvenue.model.TotalStatus;
import com.bytesedge.bookvenue.view.PdfStreamingViewIntf;
import com.lowagie.text.pdf.PdfStamper;

public class TotalStatusPdfStreamingView implements PdfStreamingViewIntf {
	
	@SuppressWarnings("unchecked")
	@Override
	public void mergePdfDocument(Map<String, Object> model, PdfStamper stamper, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> map = (Map<String, Object>) model.get("map");
				// Load the Object with context id
		EndUser managementBooking = (EndUser) map.get("obj");
				
					stamper.setFormFlattening(true);
					stamper.getAcroFields().setField("name",managementBooking.getName());
					stamper.getAcroFields().setField("email",managementBooking.getEmail());
					stamper.getAcroFields().setField("mobileNumber", managementBooking.getMobileNumber());
					stamper.getAcroFields().setField("venue", managementBooking.getPropertyName());
					String pattern = "dd-MMM-yyyy HH:mm:ss";
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
					stamper.getAcroFields().setField("bookingDate", simpleDateFormat.format(managementBooking.getBookingDate()));
					stamper.getAcroFields().setField("purpose", managementBooking.getPurposeName());
					stamper.getAcroFields().setField("referredBy", managementBooking.getReferredBy());
					stamper.getAcroFields().setField("createdUsername",managementBooking.getCreatedUserName());
					stamper.getAcroFields().setField("createdTime",simpleDateFormat.format(managementBooking.getCreatedTime()));
					stamper.getAcroFields().setField("updatedUsername",managementBooking.getUpdatedUserName());
					stamper.getAcroFields().setField("updatedTime", simpleDateFormat.format(managementBooking.getUpdatedTime()));
					
					
	}
	}

