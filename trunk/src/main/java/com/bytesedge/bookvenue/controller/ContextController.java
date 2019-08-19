package com.bytesedge.bookvenue.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bytesedge.bookvenue.common.CacheService;
import com.bytesedge.bookvenue.dto.OrgDto;
import com.bytesedge.bookvenue.model.Context;
import com.bytesedge.bookvenue.model.Organization;
import com.bytesedge.bookvenue.model.SessionOrgContext;
import com.bytesedge.bookvenue.model.User;

@Controller
@Scope("session")
public class ContextController {
	private static final Logger logger = LoggerFactory.getLogger(ContextController.class);

	@RequestMapping(value = "/app/context", method = {RequestMethod.GET})
	public @ResponseBody ModelAndView context(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="pid", required=false) Long pid,
			@RequestParam(value="view", required=false) String view,
			@RequestParam(value="finalyze", required=false) Boolean finalyze) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Context context = CacheService.getContextByUrl(request.getServerName());
			List<OrgDto> pList = null;
			
			if(view != null){
				map.put("view", view);
			}
			
			HttpSession session = (HttpSession)request.getSession();
			if(pid != null) {
				session.setAttribute("ctx.pid", pid);
			}
			
			if(context != null) {
				// Validate the context
				Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				if (principal != null && principal instanceof UserDetails) {
					String loginName = ((UserDetails)principal).getUsername();
					if(loginName != null) {
						User user = CacheService.getUserByLoginName(loginName);
						if(user != null) {
							Organization org = CacheService.getOrganizationById(context.getId(), user.getOrgId());
						}
					}
				}
				
				// Finalyze
				if(finalyze != null && finalyze.booleanValue()) {
					SessionOrgContext sc = (SessionOrgContext) session.getAttribute("sessionOrgContext");
					if(sc != null) {
						sc.reset();
					} else {
						sc = new SessionOrgContext();
					}
					
					// Fill current values
					sc.setCtxId(context.getId());
					Long sessionPanchayat = (Long) session.getAttribute("ctx.pid");
					
					
					// Update the Session
					session.setAttribute("sessionOrgContext", sc);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("message_error", "Failed to load Widget list");
		}
		
		if(finalyze != null) {
			map.put("finalyze", finalyze.toString());
		} else {
			map.put("finalyze", "false");
		}
		return new ModelAndView("dashboard/contextMenu", "data", map);
	}
}