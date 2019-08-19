package com.bytesedge.bookvenue.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bytesedge.bookvenue.common.CacheService;
import com.bytesedge.bookvenue.model.Context;

@Controller
@Scope("session")
public class DefaultController {

	private static final Logger logger = LoggerFactory.getLogger(DefaultController.class);

	@RequestMapping(value = "/apu/home", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody ModelAndView home(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
				return new ModelAndView("home", "data", map);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}

	@RequestMapping(value = "/apu/loginPage", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody ModelAndView loginPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Context context = CacheService.getContextByUrl(request.getServerName());
			if(context != null) {
				ControllerUtil.populateHomePageContextData(context, map);
				
				// Add a Parameter to HttpSession
				HttpSession session = (HttpSession)request.getSession();
				session.setAttribute("ctxCode", context.getCode());
				session.setAttribute("ctxId", context.getId());
				
				return new ModelAndView("login", "data", map);
			} else {
				return new ModelAndView("defaultLogin", "data", map);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
	
	
	
	

	@RequestMapping(value = "/apu/error", method = RequestMethod.GET)
	public ModelAndView error(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			return new ModelAndView("error/error", "data", map);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}

	@RequestMapping(value = "/apu/error400", method = RequestMethod.GET)
	public ModelAndView error400(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			return new ModelAndView("error/error400", "data", map);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}

	@RequestMapping(value = "/apu/error404", method = RequestMethod.GET)
	public ModelAndView error404(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			return new ModelAndView("error/error404", "data", map);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}

	@RequestMapping(value = "/apu/error500", method = RequestMethod.GET)
	public ModelAndView error500(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			return new ModelAndView("error/error500", "data", map);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}

	@RequestMapping(value = "/apu/eula", method = RequestMethod.GET)
	public ModelAndView eula(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			return new ModelAndView("common/eula", "data", map);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}

	@RequestMapping(value = "/apu/contact", method = RequestMethod.GET)
	public ModelAndView contact(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			return new ModelAndView("common/contactUs", "data", map);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}

	@RequestMapping(value = "/apu/tnc", method = RequestMethod.GET)
	public ModelAndView tnc(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			return new ModelAndView("common/tnc", "data", map);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@RequestMapping(value = "/apu/gallery", method = RequestMethod.GET)
	public ModelAndView gallery(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			return new ModelAndView("common/gallery", "data", map);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@RequestMapping(value = "/apu/downloads", method = RequestMethod.GET)
	public ModelAndView downloads(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			return new ModelAndView("common/downloads", "data", map);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@RequestMapping(value = "/apu/routeMap", method = RequestMethod.GET)
	public ModelAndView routeMap(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			return new ModelAndView("common/routeMap", "data", map);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
	
	
	@RequestMapping(value = "/apu/header", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody ModelAndView header(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
				return new ModelAndView("header", "data", map);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@RequestMapping(value = "/apu/footer", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody ModelAndView footer(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
				return new ModelAndView("footer", "data", map);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
	
}