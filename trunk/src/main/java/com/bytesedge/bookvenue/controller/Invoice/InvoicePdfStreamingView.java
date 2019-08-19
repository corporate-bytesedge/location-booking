package com.bytesedge.bookvenue.controller.Invoice;

import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bytesedge.bookvenue.common.CacheService;
import com.bytesedge.bookvenue.controller.ControllerUtil;
import com.bytesedge.bookvenue.model.Invoice;
import com.bytesedge.bookvenue.model.User;
import com.bytesedge.bookvenue.view.PdfStreamingViewIntf;
import com.lowagie.text.pdf.PdfStamper;

public class InvoicePdfStreamingView implements PdfStreamingViewIntf {
	
	@SuppressWarnings("unchecked")
	@Override
	public void mergePdfDocument(Map<String, Object> model, PdfStamper stamper, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> map = (Map<String, Object>) model.get("map");
				// Load the Object with context id
				Invoice invoice = (Invoice) map.get("obj");
				if(invoice != null) {
					invoice.setCreatedUserName(CacheService.getUserById(ControllerUtil.getContextId(request), invoice.getCreatedUserId()).getDisplayName());
					User updatedUser = CacheService.getUserById(ControllerUtil.getContextId(request), invoice.getUpdatedUserId());
					if(updatedUser != null) {
						invoice.setUpdatedUserName(updatedUser.getDisplayName());
					}
					stamper.setFormFlattening(true);
					stamper.getAcroFields().setField("invoiceId", invoice.getApplicationId());
					stamper.getAcroFields().setField("name", invoice.getName());
					stamper.getAcroFields().setField("email", invoice.getEmail());
					stamper.getAcroFields().setField("idProofType", invoice.getType().name());
					stamper.getAcroFields().setField("idProof", invoice.getIdproof());
					stamper.getAcroFields().setField("mobileNumber", invoice.getMobileNumber());
					stamper.getAcroFields().setField("venue", invoice.getPropertyName());
					stamper.getAcroFields().setField("purpose", invoice.getPurposeName());
					String pattern1 = "dd-MMM-yyyy HH:mm:ss";
					SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(pattern1);
					stamper.getAcroFields().setField("bookingDate",simpleDateFormat1.format( invoice.getDate()));
					stamper.getAcroFields().setField("address", invoice.getAddr());
					stamper.getAcroFields().setField("pRPId",invoice.getpRPId().toString());
					stamper.getAcroFields().setField("createdUsername", invoice.getCreatedUserName());
					String pattern = "dd-MMM-yyyy HH:mm:ss";
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
					stamper.getAcroFields().setField("createdTime", simpleDateFormat.format(invoice.getCreatedTime()));
					stamper.getAcroFields().setField("updatedUsername", invoice.getUpdatedUserName());
					if(invoice.getUpdatedTime() != null) {
					stamper.getAcroFields().setField("updatedTime", simpleDateFormat.format(invoice.getUpdatedTime()));
			}
			}
	}
	}

