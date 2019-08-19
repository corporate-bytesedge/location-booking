package com.bytesedge.bookvenue.controller.RentPurpose;

import java.text.SimpleDateFormat;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bytesedge.bookvenue.model.RentPurpose;
import com.bytesedge.bookvenue.view.PdfStreamingViewIntf;
import com.lowagie.text.pdf.PdfStamper;

public class RentPurposePdfStreamingView implements PdfStreamingViewIntf {

	@SuppressWarnings("unchecked")
	@Override
	public void mergePdfDocument(Map<String, Object> model, PdfStamper stamper, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> map = (Map<String, Object>) model.get("map");
		// Load the Object with context id
		RentPurpose rentPurpose = (RentPurpose) map.get("obj");
		stamper.setFormFlattening(true);
		stamper.getAcroFields().setField("name", rentPurpose.getName());
		stamper.getAcroFields().setField("state", rentPurpose.getState().name());
		stamper.getAcroFields().setField("description", rentPurpose.getDescr());
		stamper.getAcroFields().setField("organization", rentPurpose.getOrgName());
		stamper.getAcroFields().setField("createdUsername", rentPurpose.getCreatedUsername());
		String pattern = "dd-MMM-yyyy HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		stamper.getAcroFields().setField("createdTime", simpleDateFormat.format(rentPurpose.getCreatedTime()));
		stamper.getAcroFields().setField("updatedUsername", rentPurpose.getUpdatedUsername());
		stamper.getAcroFields().setField("updatedTime", simpleDateFormat.format(rentPurpose.getUpdatedTime()));
	}
}
