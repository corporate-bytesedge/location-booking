package com.bytesedge.bookvenue.controller.PropertyRentPrice;

import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bytesedge.bookvenue.common.CacheService;
import com.bytesedge.bookvenue.controller.ControllerUtil;
import com.bytesedge.bookvenue.model.PropertyRentPrice;
import com.bytesedge.bookvenue.model.User;
import com.bytesedge.bookvenue.view.PdfStreamingViewIntf;
import com.lowagie.text.pdf.PdfStamper;

public class PropertyRentPricePdfStreamingView implements PdfStreamingViewIntf {
	
	@SuppressWarnings("unchecked")
	@Override
	public void mergePdfDocument(Map<String, Object> model, PdfStamper stamper, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> map = (Map<String, Object>) model.get("map");
				// Load the Object with context id
				PropertyRentPrice propertyRentPrice = (PropertyRentPrice) map.get("obj");
				if(propertyRentPrice != null) {
					propertyRentPrice.setCreatedUserName(CacheService.getUserById(ControllerUtil.getContextId(request), propertyRentPrice.getCreatedUserId()).getDisplayName());
					User updatedUser = CacheService.getUserById(ControllerUtil.getContextId(request), propertyRentPrice.getUpdatedUserId());
					if(updatedUser != null) {
						propertyRentPrice.setUpdatedUserName(updatedUser.getDisplayName());
					}
					stamper.setFormFlattening(true);
					stamper.getAcroFields().setField("slotType",propertyRentPrice.getSlotType().name());
					stamper.getAcroFields().setField("price",propertyRentPrice.getPrice().toString());
					stamper.getAcroFields().setField("status",propertyRentPrice.getState().name());
					stamper.getAcroFields().setField("propertyName",propertyRentPrice.getPropertyName());
					stamper.getAcroFields().setField("purposeName",propertyRentPrice.getPurposeName());
					stamper.getAcroFields().setField("organizationName",propertyRentPrice.getOrgName());
					stamper.getAcroFields().setField("createdUsername", propertyRentPrice.getCreatedUserName());
					String pattern = "dd-MMM-yyyy HH:mm:ss";
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
					stamper.getAcroFields().setField("createdTime", simpleDateFormat.format(propertyRentPrice.getCreatedTime()));
					stamper.getAcroFields().setField("updatedUsername", propertyRentPrice.getUpdatedUserName());
					if(propertyRentPrice.getUpdatedTime() != null) {
					stamper.getAcroFields().setField("updatedTime", simpleDateFormat.format(propertyRentPrice.getUpdatedTime()));
			}
			}
	}
	}

