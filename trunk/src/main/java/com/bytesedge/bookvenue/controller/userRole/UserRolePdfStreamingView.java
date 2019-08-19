package com.bytesedge.bookvenue.controller.userRole;

import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bytesedge.bookvenue.common.CacheService;
import com.bytesedge.bookvenue.controller.ControllerUtil;
import com.bytesedge.bookvenue.model.User;
import com.bytesedge.bookvenue.model.UserRole;
import com.bytesedge.bookvenue.model.UserRole;
import com.bytesedge.bookvenue.view.PdfStreamingViewIntf;
import com.lowagie.text.pdf.PdfStamper;

public class UserRolePdfStreamingView implements PdfStreamingViewIntf {
	
	@SuppressWarnings("unchecked")
	@Override
	public void mergePdfDocument(Map<String, Object> model, PdfStamper stamper, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> map = (Map<String, Object>) model.get("map");
				// Load the Object with context id
				UserRole userRole = (UserRole) map.get("obj");
				if(userRole != null) {
					userRole.setCreatedUserName(CacheService.getUserById(ControllerUtil.getContextId(request), userRole.getCreatedUserId()).getDisplayName());
					User updatedUser = CacheService.getUserById(ControllerUtil.getContextId(request), userRole.getUpdatedUserId());
					if(updatedUser != null) {
						userRole.setUpdatedUserName(updatedUser.getDisplayName());
					}
					stamper.setFormFlattening(true);
					stamper.getAcroFields().setField("name",userRole.getName());
					stamper.getAcroFields().setField("administration", userRole.getAdmin().toString());
					stamper.getAcroFields().setField("createdUsername", userRole.getCreatedUserName());
					String pattern = "dd-MMM-yyyy HH:mm:ss";
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
					stamper.getAcroFields().setField("createdTime", simpleDateFormat.format(userRole.getCreatedTime()));
					stamper.getAcroFields().setField("updatedUsername", userRole.getUpdatedUserName());
					if(userRole.getUpdatedTime() != null) {
					stamper.getAcroFields().setField("updatedTime", simpleDateFormat.format(userRole.getUpdatedTime()));
			}
			}
			}
		}

