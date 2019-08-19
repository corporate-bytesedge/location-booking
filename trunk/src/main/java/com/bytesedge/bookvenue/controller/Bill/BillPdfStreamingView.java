package com.bytesedge.bookvenue.controller.Bill;

import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bytesedge.bookvenue.common.CacheService;
import com.bytesedge.bookvenue.controller.ControllerUtil;
import com.bytesedge.bookvenue.model.Bill;
import com.bytesedge.bookvenue.model.EndUser;
import com.bytesedge.bookvenue.model.User;
import com.bytesedge.bookvenue.util.CurrencyUtil;
import com.bytesedge.bookvenue.view.PdfStreamingViewIntf;
import com.lowagie.text.pdf.PdfStamper;

public class BillPdfStreamingView implements PdfStreamingViewIntf {

	@SuppressWarnings("unchecked")
	@Override
	public void mergePdfDocument(Map<String, Object> model, PdfStamper stamper, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> map = (Map<String, Object>) model.get("map");
		// Load the Object with context id
		EndUser bill = (EndUser) map.get("obj");
		if (bill != null) {
			bill.setPropertyName(
					CacheService.getPropertyById(ControllerUtil.getContextId(request), bill.getPropertyId()).getName());
			bill.setPurposeName(
					CacheService.getPurposeById(ControllerUtil.getContextId(request), bill.getPurposeId()).getName());
			bill.setCreatedUserName(CacheService
					.getUserById(ControllerUtil.getContextId(request), bill.getCreatedUserId()).getDisplayName());
			User updatedUser = CacheService.getUserById(ControllerUtil.getContextId(request), bill.getUpdatedUserId());
			if (updatedUser != null) {
				bill.setCreatedUserName(updatedUser.getDisplayName());
			}
			stamper.setFormFlattening(true);
			stamper.getAcroFields().setField("invId", bill.getApplicationId());
			stamper.getAcroFields().setField("date", bill.getBookingDate().toString());
			stamper.getAcroFields().setField("addr", bill.getAddr());
			stamper.getAcroFields().setField("name", bill.getName());
			stamper.getAcroFields().setField("mobile", bill.getMobileNumber());
			stamper.getAcroFields().setField("idProof", bill.getIdProof());
			stamper.getAcroFields().setField("email", bill.getEmail());
			stamper.getAcroFields().setField("venue", bill.getPropertyName());
			stamper.getAcroFields().setField("purpose", bill.getPurposeName());
			stamper.getAcroFields().setField("subtotal", Float.valueOf(bill.getPrice()).toString());
			stamper.getAcroFields().setField("price", Float.valueOf(bill.getPrice()).toString());
			stamper.getAcroFields().setField("total", Float.valueOf(bill.getTotal()).toString());
			stamper.getAcroFields().setField("amt", Float.valueOf(bill.getTotal()).toString());
			stamper.getAcroFields().setField("word", CurrencyUtil.convertToIndianCurrency(bill.getAmtPgRes()));
			stamper.getAcroFields().setField("cgst", bill.getCgst().toString());
			stamper.getAcroFields().setField("sgst", bill.getSgst().toString());
			stamper.getAcroFields().setField("serviceTax",bill.getServiceTax().toString());
			String pattern = "dd-MMM-yyyy";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			stamper.getAcroFields().setField("bookingDate", simpleDateFormat.format(bill.getCreatedTime()));
		}
	}
}
