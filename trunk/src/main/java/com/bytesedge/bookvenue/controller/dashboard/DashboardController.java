package com.bytesedge.bookvenue.controller.dashboard;

import java.time.Year;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bytesedge.bookvenue.common.CacheService;
import com.bytesedge.bookvenue.common.DbService;
import com.bytesedge.bookvenue.controller.ControllerUtil;
import com.bytesedge.bookvenue.model.Context;
import com.bytesedge.bookvenue.model.Organization;
import com.bytesedge.bookvenue.model.SessionOrgContext;
import com.bytesedge.bookvenue.model.User;

@Controller
@Scope("session")
public class DashboardController {
	private static final Logger logger = LoggerFactory.getLogger(DashboardController.class);
	
	@RequestMapping(value = "/app/dashboard", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody ModelAndView dashboard(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// Add a Parameter to HttpSession
			Context context = CacheService.getContextByUrl(request.getServerName());
			if(context != null) {
				ControllerUtil.populateHomePageContextData(context, map);
				
				HttpSession session = (HttpSession)request.getSession();
				session.setAttribute("ctxCode", context.getCode());
				session.setAttribute("ctxId", context.getId());
				
				// Set User Id to session
				try {
					Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
					if (principal != null && principal instanceof UserDetails) {
						String loginName = ((UserDetails)principal).getUsername();
						if(loginName != null) {
							User user = CacheService.getUserByLoginName(loginName);
							if(user != null) {
								session.setAttribute("userId", user.getId());
								session.setAttribute("userName", user.getDisplayName());
								session.setAttribute("userDesignation", user.getDesignation());
								session.setAttribute("userOrgId", user.getOrgId());
								map.put("userName", user.getDisplayName());
								map.put("userDesignation", user.getDesignation());
								map.put("userId", user.getId());
								
								SessionOrgContext sc = (SessionOrgContext) session.getAttribute("sessionOrgContext");
								if(sc == null) {
									sc = new SessionOrgContext();
									Organization org = CacheService.getOrganizationById(context.getId(), user.getOrgId());
									if(org != null) {
											sc.setOrgId(org.getId());
										}
										session.setAttribute("sessionOrgContext", sc);
								}
							}
						}
					}
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
			}
			
			return new ModelAndView("dashboard/mainDashboard", "data", map);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}

	@RequestMapping(value = "/app/dashboard/summary", method = {RequestMethod.GET})
	public @ResponseBody ModelAndView summaryDashboard(HttpServletRequest request, HttpServletResponse response, 
			Model model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Long pastBillCount = DbService.getInstance().getSetupService().getPastBillCount(ControllerUtil.getContextId(request),
					ControllerUtil.getUserOrgId(request));
			Long todayBillCount = DbService.getInstance().getSetupService().getTodayBillCount(ControllerUtil.getContextId(request),
					ControllerUtil.getUserOrgId(request));
			Long futureBillCount = DbService.getInstance().getSetupService().getFutureBillCount(ControllerUtil.getContextId(request),
					ControllerUtil.getUserOrgId(request));
			if( pastBillCount != null){
				map.put("pastBillCount", pastBillCount);
			}else
			{
				map.put("message_error", "Failed to load Widget list");
			}if( todayBillCount != null){
				map.put("todayBillCount", todayBillCount);
			}else
			{
				map.put("message_error", "Failed to load Widget list");
			}if( futureBillCount != null){
				map.put("futureBillCount", futureBillCount);
			}else
			{
				map.put("message_error", "Failed to load Widget list");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("message_error", "Failed to load Widget list");
		}
		return new ModelAndView("dashboard/summaryDashboard", "data", map);
	}
}