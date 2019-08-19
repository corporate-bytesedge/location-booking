package com.bytesedge.bookvenue.controller;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeBookingController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	
	@RequestMapping(value = "/apu/homeBooking/bookVenue", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody ModelAndView bookVenue(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
						
				return new ModelAndView("homeBooking/bookVenue", "data", map);
			}
	
	

}