package com.bytesedge.bookvenue.controller.organization;

import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bytesedge.bookvenue.common.CacheService;
import com.bytesedge.bookvenue.controller.ControllerUtil;
import com.bytesedge.bookvenue.model.Organization;
import com.bytesedge.bookvenue.model.User;
import com.bytesedge.bookvenue.view.PdfStreamingViewIntf;
import com.lowagie.text.pdf.PdfStamper;

public class OrganizationPdfStreamingView implements PdfStreamingViewIntf {
	
	@SuppressWarnings("unchecked")
	@Override
	public void mergePdfDocument(Map<String, Object> model, PdfStamper stamper, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> map = (Map<String, Object>) model.get("map");
				// Load the Object with context id
		Organization organization =(Organization) map.get("obj");
		if(organization != null) {
			organization.setCreatedUserName(CacheService.getUserById(ControllerUtil.getContextId(request), organization.getCreatedUserId()).getDisplayName());
			User updatedUser = CacheService.getUserById(ControllerUtil.getContextId(request), organization.getUpdatedUserId());
			if(updatedUser != null) {
				organization.setUpdatedUserName(updatedUser.getDisplayName());
			}
					stamper.setFormFlattening(true);
					stamper.getAcroFields().setField("name", organization.getName());
					stamper.getAcroFields().setField("phoneNumber", organization.getPhoneNumber());
					stamper.getAcroFields().setField("mobileNumber", organization.getMobileNumber());
					stamper.getAcroFields().setField("emailId", organization.getEmailId());
					stamper.getAcroFields().setField("createdUserName", organization.getCreatedUserName());
					String pattern = "dd-MMM-yyyy HH:mm:ss";
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
					stamper.getAcroFields().setField("createdTime", simpleDateFormat.format(organization.getCreatedTime()));
					stamper.getAcroFields().setField("updatedUserName", organization.getUpdatedUserName());
					if(organization.getUpdatedTime() != null) {
					stamper.getAcroFields().setField("updatedTime", simpleDateFormat.format(organization.getUpdatedTime()));
			}
				}
			}
		}

