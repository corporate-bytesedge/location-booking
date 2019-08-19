package com.bytesedge.bookvenue.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.bytesedge.bookvenue.common.CacheService;
import com.bytesedge.bookvenue.model.Context;
import com.bytesedge.bookvenue.model.Organization;
import com.bytesedge.bookvenue.model.SessionOrgContext;

public class ControllerUtil {
	public static void populateHomePageContextData(Context context, Map<String, Object> map) {
		map.put("ctxName", context.getName());
		map.put("ctxSupportEmail", context.getSupportEmailId());
		map.put("ctxSupportPhone", context.getSupportPhoneNumber());
		map.put("ctxSupportMobile", context.getSupportMobileNumber());
	}
	
	public static String getContextCode(HttpServletRequest request) {
		HttpSession session = (HttpSession)request.getSession();
		if(session != null) {
			return (String) session.getAttribute("ctxCode");
		}
		return null;
	}
	
	public static Long getContextId(HttpServletRequest request) {
		HttpSession session = (HttpSession)request.getSession();
		if(session != null) {
			return (Long) session.getAttribute("ctxId");
		}
		return null;
	}

	public static Long getUserId(HttpServletRequest request) {
		HttpSession session = (HttpSession)request.getSession();
		if(session != null) {
			return (Long) session.getAttribute("userId");
		}
		return null;
	}

	public static Long getUserOrgId(HttpServletRequest request) {
		HttpSession session = (HttpSession)request.getSession();
		if(session != null) {
			return (Long) session.getAttribute("userOrgId");
		}
		return null;
	}

	public static SessionOrgContext getSessionContext(HttpServletRequest request) {
		HttpSession session = (HttpSession)request.getSession();
		if(session != null && session.getAttribute("sessionOrgContext") != null) {
			return (SessionOrgContext) session.getAttribute("sessionOrgContext");
		}
		return null;
	}
	
	public static SessionOrgContext getValidSessionContext(HttpServletRequest request) {
		HttpSession session = (HttpSession)request.getSession();
		if(session != null) {
			SessionOrgContext sc = (SessionOrgContext) session.getAttribute("sessionOrgContext");
			if(sc != null) {
				return sc;
			}
		}
		return null;
	}

	public static Organization getCurrentSessionContextOrg(HttpServletRequest request) throws Exception {
		HttpSession session = (HttpSession)request.getSession();
		if(session != null) {
			Long ctxId = getContextId(request);
			if(ctxId != null) {
				SessionOrgContext sc = (SessionOrgContext) session.getAttribute("sessionOrgContext");
				if(sc != null) {
				} else {
					// load the data based on users orgId
					return CacheService.getOrganizationById(ctxId, (Long) session.getAttribute("userOrgId"));
				}
			}
		}
		return null;
	}
	
	public static Long getOrgId(HttpServletRequest request) throws Exception {
		HttpSession session = (HttpSession)request.getSession();
		if(session != null) {
			Long ctxId = (Long) session.getAttribute("ctxId");
			if (ctxId != null) {
				Organization org = CacheService.getOrgByCtxId(ctxId);
		 		if (org != null) {
		 			return org.getId();
		 		}
			}
			
		}
		return null;
	}			
}