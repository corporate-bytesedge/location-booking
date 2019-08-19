package com.bytesedge.bookvenue.controller.user;

import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bytesedge.bookvenue.common.CacheService;
import com.bytesedge.bookvenue.controller.ControllerUtil;
import com.bytesedge.bookvenue.model.User;
import com.bytesedge.bookvenue.model.User;
import com.bytesedge.bookvenue.view.PdfStreamingViewIntf;
import com.lowagie.text.pdf.PdfStamper;

public class UserPdfStreamingView implements PdfStreamingViewIntf {
	
	@SuppressWarnings("unchecked")
	@Override
	public void mergePdfDocument(Map<String, Object> model, PdfStamper stamper, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> map = (Map<String, Object>) model.get("map");
				// Load the Object with context id
				User user = (User) map.get("obj");
				if(user != null) {
					user.setCreatedUserName(CacheService.getUserById(ControllerUtil.getContextId(request), user.getCreatedUserId()).getDisplayName());
					User updatedUser = CacheService.getUserById(ControllerUtil.getContextId(request), user.getUpdatedUserId());
					if(updatedUser != null) {
						user.setUpdatedUserName(updatedUser.getDisplayName());
					}
					stamper.setFormFlattening(true);
					stamper.getAcroFields().setField("loginName", user.getLoginName());
					stamper.getAcroFields().setField("accountStatus", user.getAccountState().name());
					stamper.getAcroFields().setField("email", user.getEmail());
					stamper.getAcroFields().setField("name", user.getDisplayName());
					stamper.getAcroFields().setField("gender", user.getGender().name());
					stamper.getAcroFields().setField("aadhaarNumber", user.getAadharId());
					stamper.getAcroFields().setField("mobileNumber", user.getMobileNumber());
					String pattern = "dd-MMM-yyyy HH:mm:ss";
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
					stamper.getAcroFields().setField("dateOfBirth", simpleDateFormat.format(user.getDob()));
					stamper.getAcroFields().setField("designation", user.getDesignation());
					stamper.getAcroFields().setField("roleName", user.getRoleName());
					if(user.getAuthFailed() != null){
					stamper.getAcroFields().setField("authFailed", user.getAuthFailed().toString());
					}
					if(user.getAuthFailedTime() != null){
					stamper.getAcroFields().setField("authFailedTime", user.getAuthFailedTime().toString());
			
					}
					stamper.getAcroFields().setField("createdUsername", user.getCreatedUserName());
					stamper.getAcroFields().setField("createdTime", simpleDateFormat.format(user.getCreatedTime()));
					stamper.getAcroFields().setField("updatedUsername", user.getUpdatedUserName());
					if(user.getUpdatedTime() != null) {
					stamper.getAcroFields().setField("updatedTime", simpleDateFormat.format(user.getUpdatedTime()));
			}
					if(user.getLockedTime() != null){
						stamper.getAcroFields().setField("lockedTime", simpleDateFormat.format(user.getLockedTime()));
					}
				}
	
			}
		}

