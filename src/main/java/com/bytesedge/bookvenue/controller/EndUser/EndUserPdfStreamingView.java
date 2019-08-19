package com.bytesedge.bookvenue.controller.EndUser;

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

public class EndUserPdfStreamingView implements PdfStreamingViewIntf {
	;
	@SuppressWarnings("unchecked")
	@Override
	public void mergePdfDocument(Map<String, Object> model, PdfStamper stamper, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> map = (Map<String, Object>) model.get("map");
				// Load the Object with context id
				EndUser endUsers = (EndUser) map.get("obj");
				if(endUsers != null) {
					endUsers.setPropertyName(CacheService
							.getPropertyById(ControllerUtil.getContextId(request), endUsers.getPropertyId()).getName());
					endUsers.setPurposeName(CacheService
							.getPurposeById(ControllerUtil.getContextId(request), endUsers.getPurposeId()).getName());
					endUsers.setCreatedUserName(CacheService.getUserById(ControllerUtil.getContextId(request), endUsers.getCreatedUserId()).getDisplayName());
					User updatedUser = CacheService.getUserById(ControllerUtil.getContextId(request), endUsers.getUpdatedUserId());
					if(updatedUser != null) {
						endUsers.setUpdatedUserName(updatedUser.getDisplayName());
					}
					stamper.setFormFlattening(true);
					stamper.getAcroFields().setField("email", endUsers.getEmail());
					stamper.getAcroFields().setField("name", endUsers.getName());
					stamper.getAcroFields().setField("type", endUsers.getIdProofType().toString());
					stamper.getAcroFields().setField("idProof", endUsers.getIdProof());
					stamper.getAcroFields().setField("venue", endUsers.getPropertyName());
					stamper.getAcroFields().setField("purpose", endUsers.getPurposeName());
					stamper.getAcroFields().setField("mobileNumber", endUsers.getMobileNumber());
					stamper.getAcroFields().setField("price", endUsers.getPrice().toString());
					stamper.getAcroFields().setField("subTotal", endUsers.getPrice().toString());
					stamper.getAcroFields().setField("address", endUsers.getAddr());
					stamper.getAcroFields().setField("total", endUsers.getTotal().toString());
					stamper.getAcroFields().setField("totalPrice", endUsers.getTotal().toString());
					stamper.getAcroFields().setField("amt", endUsers.getTotal().toString());
					stamper.getAcroFields().setField("cgst", endUsers.getCgst().toString());
					stamper.getAcroFields().setField("sgst", endUsers.getSgst().toString());
					stamper.getAcroFields().setField("cgsts", endUsers.getCgst().toString());
					stamper.getAcroFields().setField("sgsts", endUsers.getSgst().toString());
					stamper.getAcroFields().setField("invoiceId", endUsers.getApplicationId());
					stamper.getAcroFields().setField("serviceTax", endUsers.getServiceTax().toString());
					if(endUsers.getTotal() != null){
					stamper.getAcroFields().setField("word", CurrencyUtil.convertToIndianCurrency(endUsers.getTotal().toString()));
					}
					String pattern = "dd-MMM-yyyy";
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
					String pattern1 ="dd-MM-YYYY";
					SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(pattern1);
					stamper.getAcroFields().setField("bookingDate", simpleDateFormat.format(endUsers.getBookingDate()));
					stamper.getAcroFields().setField("createdUsername", endUsers.getCreatedUserName());
					stamper.getAcroFields().setField("createdTime", simpleDateFormat1.format(endUsers.getCreatedTime()));
					stamper.getAcroFields().setField("updatedUsername", endUsers.getUpdatedUserName());
					if(endUsers.getUpdatedTime() != null) {
					stamper.getAcroFields().setField("updatedTime", simpleDateFormat.format(endUsers.getUpdatedTime()));
					}
				}
				}
			}
		

