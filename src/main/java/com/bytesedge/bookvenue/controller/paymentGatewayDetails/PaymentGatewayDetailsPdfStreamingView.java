package com.bytesedge.bookvenue.controller.paymentGatewayDetails;

import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bytesedge.bookvenue.common.CacheService;
import com.bytesedge.bookvenue.controller.ControllerUtil;
import com.bytesedge.bookvenue.model.PaymentGatewayDetails;
import com.bytesedge.bookvenue.model.User;
import com.bytesedge.bookvenue.view.PdfStreamingViewIntf;
import com.lowagie.text.pdf.PdfStamper;

public class PaymentGatewayDetailsPdfStreamingView implements PdfStreamingViewIntf {

	@SuppressWarnings("unchecked")
	@Override
	public void mergePdfDocument(Map<String, Object> model, PdfStamper stamper, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> map = (Map<String, Object>) model.get("map");
		// Load the Object with context id
		PaymentGatewayDetails paymentGatewayDetails = (PaymentGatewayDetails) map.get("obj");
		if (paymentGatewayDetails != null) {
			paymentGatewayDetails.setCreatedUserName(CacheService
					.getUserById(ControllerUtil.getContextId(request), paymentGatewayDetails.getCreatedUserId())
					.getDisplayName());
			User updatedUser = CacheService.getUserById(ControllerUtil.getContextId(request),
					paymentGatewayDetails.getUpdatedUserId());
			if (updatedUser != null) {
				paymentGatewayDetails.setUpdatedUserName(updatedUser.getDisplayName());
			}
			stamper.setFormFlattening(true);
			stamper.getAcroFields().setField("status", paymentGatewayDetails.getStatus().name());
			stamper.getAcroFields().setField("login", paymentGatewayDetails.getLogin());
			stamper.getAcroFields().setField("password", paymentGatewayDetails.getPassword());
			stamper.getAcroFields().setField("reqHashKey", paymentGatewayDetails.getReqHashKey());
			stamper.getAcroFields().setField("resHashKey", paymentGatewayDetails.getResHashKey());
			stamper.getAcroFields().setField("ttype", paymentGatewayDetails.getTtype());
			stamper.getAcroFields().setField("prodid", paymentGatewayDetails.getProdId());
			stamper.getAcroFields().setField("currency", paymentGatewayDetails.getTxnCurr());
			stamper.getAcroFields().setField("createdUsername", paymentGatewayDetails.getCreatedUserName());
			String pattern = "dd-MMM-yyyy HH:mm:ss";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			stamper.getAcroFields().setField("createdTime",
					simpleDateFormat.format(paymentGatewayDetails.getCreatedTime()));
			stamper.getAcroFields().setField("updatedUsername", paymentGatewayDetails.getUpdatedUserName());
			if (paymentGatewayDetails.getUpdatedTime() != null) {
				stamper.getAcroFields().setField("updatedTime",
						simpleDateFormat.format(paymentGatewayDetails.getUpdatedTime()));
			}
		}
	}
}
