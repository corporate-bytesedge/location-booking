package com.bytesedge.bookvenue.controller.Property;

import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bytesedge.bookvenue.common.CacheService;
import com.bytesedge.bookvenue.controller.ControllerUtil;
import com.bytesedge.bookvenue.model.Property;
import com.bytesedge.bookvenue.model.User;
import com.bytesedge.bookvenue.view.PdfStreamingViewIntf;
import com.lowagie.text.pdf.PdfStamper;

public class PropertyPdfStreamingView implements PdfStreamingViewIntf {
	
	@SuppressWarnings("unchecked")
	@Override
	public void mergePdfDocument(Map<String, Object> model, PdfStamper stamper, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> map = (Map<String, Object>) model.get("map");
				// Load the Object with context id
				Property property = (Property) map.get("obj");
				if(property != null) {
					property.setCreatedUserName(CacheService.getUserById(ControllerUtil.getContextId(request), property.getCreatedUserId()).getDisplayName());
					User updatedUser = CacheService.getUserById(ControllerUtil.getContextId(request), property.getUpdatedUserId());
					if(updatedUser != null) {
						property.setUpdatedUserName(updatedUser.getDisplayName());
					}
					stamper.setFormFlattening(true);
					stamper.getAcroFields().setField("propertyId",property.getPropertyId());
					stamper.getAcroFields().setField("uniqueId", property.getUniqueId());
					stamper.getAcroFields().setField("name", property.getName());
					stamper.getAcroFields().setField("state", property.getState().name());
					stamper.getAcroFields().setField("description", property.getDescr());
					stamper.getAcroFields().setField("createdUsername", property.getCreatedUserName());
					String pattern = "dd-MMM-yyyy HH:mm:ss";
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
					stamper.getAcroFields().setField("createdTime", simpleDateFormat.format(property.getCreatedTime()));
					stamper.getAcroFields().setField("updatedUsername", property.getUpdatedUserName());
					if(property.getUpdatedTime() != null) {
					stamper.getAcroFields().setField("updatedTime", simpleDateFormat.format(property.getUpdatedTime()));
			}
			}
	}
	}

