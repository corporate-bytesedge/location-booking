package com.bytesedge.bookvenue.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bytesedge.bookvenue.common.CacheService;
import com.bytesedge.bookvenue.model.Context;
import com.bytesedge.bookvenue.service.SetupService;

@Controller
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private SetupService setupService = null;

	@RequestMapping(value = "/apu/loginError", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody ModelAndView loginError(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error", "AUTH_FAILED");
		try {
			Context context = CacheService.getContextByUrl(request.getServerName());
			if(context != null) {
				ControllerUtil.populateHomePageContextData(context, map);
				
				return new ModelAndView("home", "data", map);
			} else {
				return new ModelAndView("defaultHome", "data", map);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}

	public SetupService getSetupService() {
		return setupService;
	}

	public void setSetupService(SetupService setupService) {
		this.setupService = setupService;
	}

}