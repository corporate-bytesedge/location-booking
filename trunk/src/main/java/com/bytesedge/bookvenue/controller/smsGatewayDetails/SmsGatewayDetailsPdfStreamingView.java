package com.bytesedge.bookvenue.controller.smsGatewayDetails;

import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bytesedge.bookvenue.common.CacheService;
import com.bytesedge.bookvenue.controller.ControllerUtil;
import com.bytesedge.bookvenue.model.SmsGatewayDetails;
import com.bytesedge.bookvenue.model.User;
import com.bytesedge.bookvenue.view.PdfStreamingViewIntf;
import com.lowagie.text.pdf.PdfStamper;

public class SmsGatewayDetailsPdfStreamingView implements PdfStreamingViewIntf {

	@SuppressWarnings("unchecked")
	@Override
	public void mergePdfDocument(Map<String, Object> model, PdfStamper stamper, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> map = (Map<String, Object>) model.get("map");
		// Load the Object with context id
		SmsGatewayDetails smsGatewayDetails = (SmsGatewayDetails) map.get("obj");
		if (smsGatewayDetails != null) {
			smsGatewayDetails.setCreatedUserName(CacheService
					.getUserById(ControllerUtil.getContextId(request), smsGatewayDetails.getCreatedUserId())
					.getDisplayName());
			User updatedUser = CacheService.getUserById(ControllerUtil.getContextId(request),
					smsGatewayDetails.getUpdatedUserId());
			if (updatedUser != null) {
				smsGatewayDetails.setUpdatedUserName(updatedUser.getDisplayName());
			}
			stamper.setFormFlattening(true);
			stamper.getAcroFields().setField("sender", smsGatewayDetails.getSenderId());
			stamper.getAcroFields().setField("routeId", smsGatewayDetails.getRoute());
			stamper.getAcroFields().setField("createdUsername", smsGatewayDetails.getCreatedUserName());
			String pattern = "dd-MMM-yyyy HH:mm:ss";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			stamper.getAcroFields().setField("createdTime",
					simpleDateFormat.format(smsGatewayDetails.getCreatedTime()));
			stamper.getAcroFields().setField("updatedUsername", smsGatewayDetails.getUpdatedUserName());
			if (smsGatewayDetails.getUpdatedTime() != null) {
				stamper.getAcroFields().setField("updatedTime",
						simpleDateFormat.format(smsGatewayDetails.getUpdatedTime()));
			}
		}
	}
}
