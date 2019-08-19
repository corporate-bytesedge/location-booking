package com.bytesedge.bookvenue.core;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.bytesedge.bookvenue.cache.RedisCacheService;

@Controller
@SuppressWarnings("serial")
public class StartupServlet extends HttpServlet {
	public static final Logger logger = LoggerFactory.getLogger(StartupServlet.class);
	
	public static final String DIR_DATA_REPO = "/data/";
	
	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		// Initialize the Redis Services
		/*
		try {
			logger.error("MSG: Trying to init RedisCacheService");
			RedisCacheService.init();
		} catch (Exception e) {
			logger.error("Failed to init the RedisCacheService", e);
		}
		
		try {
			logger.error("MSG: Trying to init RedisMqService");
			RedisMqService.init();
		} catch (Exception e) {
			logger.error("Failed to init the RedisMqService", e);
		}
		*/

		try {
			logger.error("MSG: Trying to init RedisCacheService");
			RedisCacheService.init();
		} catch (Exception e) {
			logger.error("Failed to init the RedisCacheService", e);
		}
		
	}
}
