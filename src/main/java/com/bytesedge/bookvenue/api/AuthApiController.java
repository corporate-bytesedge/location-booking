package com.bytesedge.bookvenue.api;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.bytesedge.bookvenue.common.CacheService;
import com.bytesedge.bookvenue.common.PaginatedRequest;
import com.bytesedge.bookvenue.common.PaginatedResponse;
import com.bytesedge.bookvenue.controller.BaseController;
import com.bytesedge.bookvenue.controller.ControllerUtil;
import com.bytesedge.bookvenue.dto.ApiBaseResponse;
import com.bytesedge.bookvenue.dto.ApiValidationError;
import com.bytesedge.bookvenue.model.User;
import com.bytesedge.bookvenue.model.UserRole;
import com.bytesedge.bookvenue.service.SetupService;

@Controller
public class AuthApiController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(AuthApiController.class);

	@Autowired
	private SetupService setupService = null;
	
	public SetupService getSetupService() {
		return setupService;
	}

	public void setSetupService(SetupService setupService) {
		this.setupService = setupService;
	}
	
	@RequestMapping(value = "/api/v1/auth/login", 
			method = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST}, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody ResponseEntity<?> apiAdminLogin(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		ApiBaseResponse apiRes = new ApiBaseResponse();
		try {
			// Authentication success, set the context parameters
			initCtx(request, response);
		} catch (Exception e) {
			logger.error("API login request failed. " + e.getMessage(), e);
			apiRes.add(new ApiValidationError("error", "error.processing", e.getMessage()));
		}
		
		if(!apiRes.hasErrors()) {
			return new ResponseEntity<ApiBaseResponse>(apiRes, HttpStatus.OK);
		} else {
			return new ResponseEntity<ApiBaseResponse>(apiRes, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/api/v1/auth/logout", 
			method = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST}, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody ResponseEntity<?> apiAdminLogout(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		try {
			SecurityContextHolder.clearContext();
			HttpSession session = request.getSession(false);
	        if (session != null) {
	            session.invalidate();
	        }
		} catch (Exception e) {
			logger.error("API loginout request failed. " + e.getMessage(), e);
		}
		return new ResponseEntity<String>(new String("OK"), HttpStatus.OK);
	}

	@RequestMapping(value = "/api/v1/auth/device/login", 
			method = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST}, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody ResponseEntity<?> apiLogin(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		ApiBaseResponse apiRes = new ApiBaseResponse();
			
				// Process user role
				User user = CacheService.getUserById(ControllerUtil.getContextId(request), ControllerUtil.getUserId(request));
				if(user != null) {
					UserRole userRole = CacheService.getUserRoleById(ControllerUtil.getContextId(request), user.getRoleId());
					if(userRole != null) {
						apiRes.setRole(userRole.getName());
						
						// process ctx info
				} else {
					apiRes.add(new ApiValidationError("user", "user.not.found", "User not found"));
				}
			} else {
				// Object not found
				apiRes.add(new ApiValidationError("object", "object.not.found", "Object not found"));
			}
			return new ResponseEntity<ApiBaseResponse>(apiRes, HttpStatus.BAD_REQUEST);
		}
	

	@RequestMapping(value = "/api/v1/auth/device/logout", 
			method = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST}, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody ResponseEntity<?> apiLogout(HttpServletRequest request) throws Exception {
		try {
			SecurityContextHolder.clearContext();
			HttpSession session = request.getSession(false);
	        if (session != null) {
	            session.invalidate();
	        }
	        
		} catch (Exception e) {
			logger.error("APi logout request failed. " + e.getMessage(), e);
		}
		return new ResponseEntity<String>(new String("OK"), HttpStatus.OK);
	}

	@Override
	public void customModalAttributes(Model model) {
	}

	@Override
	public void preProcessPaginatedList(HttpServletRequest request, Class<?> clazz, Long ctxId, Map<String, Object> map,
			Long reqPage, Long pageSize, String requestUriTmpl) throws Exception {
	}

	@Override
	public PaginatedResponse getPaginatedListing(HttpServletRequest request, PaginatedRequest req,
			Map<String, Object> map) throws Exception {
		return null;
	}

}