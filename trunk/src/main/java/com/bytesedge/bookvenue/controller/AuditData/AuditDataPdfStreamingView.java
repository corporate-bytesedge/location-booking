package com.bytesedge.bookvenue.controller.AuditData;

import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bytesedge.bookvenue.common.CacheService;
import com.bytesedge.bookvenue.controller.ControllerUtil;
import com.bytesedge.bookvenue.model.AuditData;
import com.bytesedge.bookvenue.view.PdfStreamingViewIntf;
import com.lowagie.text.pdf.PdfStamper;

public class AuditDataPdfStreamingView implements PdfStreamingViewIntf {
	
	@SuppressWarnings("unchecked")
	@Override
	public void mergePdfDocument(Map<String, Object> model, PdfStamper stamper, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> map = (Map<String, Object>) model.get("map");
				// Load the Object with context id
				AuditData auditData = (AuditData) map.get("obj");
				if(auditData != null) {
					auditData.setCreatedUserName(CacheService.getUserById(ControllerUtil.getContextId(request), auditData.getCreatedUserId()).getDisplayName());
				}
				stamper.setFormFlattening(true);
				stamper.getAcroFields().setField("operation", auditData.getOperation().name());
				stamper.getAcroFields().setField("auditObject", auditData.getAuditObject().name());
				stamper.getAcroFields().setField("src", auditData.getSrc());
				stamper.getAcroFields().setField("dst", auditData.getDst().toString());
				String pattern = "dd-MM-yyyy ";
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			    stamper.getAcroFields().setField("createdTime", simpleDateFormat.format(auditData.getCreatedTime()));
				stamper.getAcroFields().setField("createdUserName", auditData.getCreatedUserName().toString());
	
	}
}
		

