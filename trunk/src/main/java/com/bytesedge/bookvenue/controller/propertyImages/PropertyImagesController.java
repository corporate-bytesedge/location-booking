package com.bytesedge.bookvenue.controller.propertyImages;



import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.bytesedge.bookvenue.common.CacheService;
import com.bytesedge.bookvenue.common.DbService;
import com.bytesedge.bookvenue.common.PaginatedRequest;
import com.bytesedge.bookvenue.common.PaginatedResponse;
import com.bytesedge.bookvenue.controller.BaseController;
import com.bytesedge.bookvenue.controller.CaptchaServlet;
import com.bytesedge.bookvenue.controller.ControllerUtil;
import com.bytesedge.bookvenue.model.AuditObject;
import com.bytesedge.bookvenue.model.AuditOperationType;
import com.bytesedge.bookvenue.model.PropertyImages;
import com.bytesedge.bookvenue.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@SessionAttributes({"propertyImagesForm"})
public class PropertyImagesController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(PropertyImagesController.class);
	
	private final String requestUriTmpl = "/app/propertyImages/list?reqPage=%d&pageSize=%d";
	
	@Autowired
	private PropertyImagesFormValidator validator;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(validator);
	}
	
	public PropertyImagesFormValidator getValidator() {
		return validator;
	}

	public void setValidator(PropertyImagesFormValidator validator) {
		this.validator = validator;
	}

	@Override
	public void customModalAttributes(Model model) {
		
		model.addAttribute("AuditOperationType", AuditOperationType.values());
		model.addAttribute("AuditObject", AuditObject.values());
	}
	
	@Override
	public PaginatedResponse getPaginatedListing(HttpServletRequest httpRequest, PaginatedRequest paginatedRequest, final Map<String, Object> map) throws Exception {
			return DbService.getInstance().getSetupService().getPropertyImagesList( ControllerUtil.getContextId(httpRequest), paginatedRequest);
	}
	
	@RequestMapping(value = "/app/propertyImages/list", method = {RequestMethod.GET})
	public @ResponseBody ModelAndView  propertyImagesList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="reqPage", required=false) Long reqPage, @RequestParam(value="pageSize", 
			required=false) Long pageSize) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			processPaginatedList(PropertyImages.class, request, map, reqPage, pageSize, requestUriTmpl);
			return new ModelAndView("propertyImages/propertyImagesList", "data", map);
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
	@RequestMapping(value = "/app/propertyImages/create/form", method = {RequestMethod.GET})
	public @ResponseBody ModelAndView createPropertyImagesForm(HttpServletRequest request, Model model) throws Exception {
		try {
			PropertyImages propertyImagesForm = new PropertyImages();
				CaptchaServlet.resetCaptcha(request);
				customModalAttributes(model);
				return new ModelAndView("propertyImages/createPropertyImages", "propertyImagesForm", propertyImagesForm);	
		}  catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		// Incase of erros Return to Lising Page
		Map<String, Object> map = new HashMap<String, Object>();
		preProcessPaginatedList(request, PropertyImages.class, ControllerUtil.getContextId(request), map, 0L, PaginatedRequest.DEFAULT_PAGE_SIZE, requestUriTmpl); 
		return new ModelAndView("propertyImages/propertyImagesList", "data", map);
	}
	
	@RequestMapping(value = "/app/propertyImages/create", method = {RequestMethod.POST})
	public @ResponseBody ModelAndView createPropertyImages(HttpServletRequest request, 
			@ModelAttribute("propertyImagesForm") @Valid PropertyImages propertyImagesForm,
			BindingResult result, Model model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if(result.hasErrors()) {
				customModalAttributes(model);
				return new ModelAndView("propertyImages/createPropertyImages", "propertyImagesForm", propertyImagesForm);
			} else {
				// Pre process the formBean
				this.updateContextDoProperties(request, propertyImagesForm);
				PropertyImages dbObject = (PropertyImages) DbService.getInstance().getSetupService().saveOrUpdatePropertyImages(propertyImagesForm);
				ObjectMapper jsonApi = new ObjectMapper();
				this.saveAuditObject(request, null, jsonApi.writeValueAsString(dbObject), AuditOperationType.CREATE, AuditObject.PropertyImages);
				processPaginatedList(PropertyImages.class, request, map, 0L, PaginatedRequest.DEFAULT_PAGE_SIZE, requestUriTmpl);
				map.put("message_success", "A new Property Image with id " + dbObject.getId() +" has been created.");
				return new ModelAndView("propertyImages/propertyImagesList", "data", map);
			}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				// In case of errors Return to Listing Page
				map.put("message_error", "Failed to create a new Property image.");
				processPaginatedList(PropertyImages.class, request, map, 0L, PaginatedRequest.DEFAULT_PAGE_SIZE, requestUriTmpl);
				return new ModelAndView("propertyImages/propertyImagesList", "data", map);
			}
	}
	
	@RequestMapping(value = "/app/propertyImages/update/form", method = {RequestMethod.GET})
	public @ResponseBody ModelAndView updatePropertyImagesForm(HttpServletRequest request,
			@RequestParam(value="id") Long id, 
			@RequestParam(value="reqPage") Long reqPage,
			@RequestParam(value="pageSize") Long pageSize,
			Model model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// Global forwarding parameters
			model.addAttribute("reqPage", reqPage);
			model.addAttribute("pageSize", pageSize);
			if(id > 0) {
				try {
					// Load the Object with context id
					PropertyImages dbPropertyImages = DbService.getInstance().getSetupService().getPropertyImagesByIdAndCtxId(id, ControllerUtil.getContextId(request));
					if(dbPropertyImages != null) {
						// Rest Captcha
						CaptchaServlet.resetCaptcha(request);
						customModalAttributes(model);
						return new ModelAndView("propertyImages/updatePropertyImages", "propertyImagesForm", dbPropertyImages);
					}
					// Check permissions
				} catch (Exception e) {
					logger.error("Failed to Update Property Images", e);
				}
			}
			
			// Return to Listing page with error
			map.put("message_error", "Property Images being updated does not exists on the System.");
			preProcessPaginatedList(request, PropertyImages.class, ControllerUtil.getContextId(request), map,reqPage, pageSize, requestUriTmpl);
			return new ModelAndView("propertyImages/propertyImagesList", "data", map);
		}  catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@RequestMapping(value = "/app/propertyImages/update", method = {RequestMethod.POST})
	public @ResponseBody ModelAndView updatePropertyImages(HttpServletRequest request,
			@ModelAttribute("propertyImagesForm") @Valid PropertyImages propertyImagesForm,
			BindingResult result,
			@RequestParam(value="reqPage") Long reqPage,
			@RequestParam(value="pageSize") Long pageSize,
			Model model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// Global forwarding parameters
			model.addAttribute("reqPage", reqPage);
			model.addAttribute("pageSize", pageSize);
			if(result.hasErrors()) {
				customModalAttributes(model);
				return new ModelAndView("propertyImages/updatePropertyImages", "propertyImagesForm", propertyImagesForm);
			} else {
				// Pre process the formBean
				PropertyImages dbPropertyImages = DbService.getInstance().getSetupService().getPropertyImagesByIdAndCtxId(propertyImagesForm.getId(),
						ControllerUtil.getContextId(request));
				if(dbPropertyImages != null) {
					ObjectMapper jsonApi = new ObjectMapper();
					String oldDbObject = jsonApi.writeValueAsString(dbPropertyImages);
					
					this.updateContextDoProperties(request, dbPropertyImages);
					dbPropertyImages.setName(propertyImagesForm.getName());
					dbPropertyImages.setDescr(propertyImagesForm.getDescr());
					PropertyImages dbObject = (PropertyImages) DbService.getInstance().getSetupService().saveOrUpdatePropertyImages(dbPropertyImages);
					this.saveAuditObject(request, oldDbObject, jsonApi.writeValueAsString(dbObject),AuditOperationType.UPDATE, AuditObject.User);
					
					if(dbObject != null) {
						map.put("message_success", "A User " + dbObject.getName() + " updated.");
					} else {
						map.put("message_error", "Updating User" + dbPropertyImages.getName() + " failed.");
					}
					preProcessPaginatedList(request, PropertyImages.class, ControllerUtil.getContextId(request), map,reqPage, pageSize, requestUriTmpl);
					return new ModelAndView("propertyImages/propertyImagesList", "data", map);
				}
			}
		}  catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		map.put("message_error", "Updating User failed.");
		preProcessPaginatedList(request, PropertyImages.class, ControllerUtil.getContextId(request), map,reqPage, pageSize, requestUriTmpl);
		return new ModelAndView("propertyImages/propertyImagesList", "data", map);
	}
	
	@RequestMapping(value = "/app/propertyImages/view/form", method = {RequestMethod.GET})
	public @ResponseBody ModelAndView viewPropertyImagesFormForm(HttpServletRequest request,
			@RequestParam(value="id") Long id,
			@RequestParam(value="reqPage") Long reqPage,
			@RequestParam(value="pageSize") Long pageSize,Model model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if(id > 0) {
				model.addAttribute("reqPage", reqPage);
				model.addAttribute("pageSize", pageSize);
				try {
					
					// Load the Object with context id
					PropertyImages dbPropertyImages = DbService.getInstance().getSetupService().getPropertyImagesByIdAndCtxId(id, ControllerUtil.getContextId(request) );
					if(dbPropertyImages != null) {
						dbPropertyImages.setCreatedUserName(CacheService.getUserById(ControllerUtil.getContextId(request), dbPropertyImages.getCreatedUserId()).getDisplayName());
					}
						// Rest Captcha
						CaptchaServlet.resetCaptcha(request);
						return new ModelAndView("propertyImages/viewPropertyImages", "propertyImagesForm", dbPropertyImages);
				}
				catch (Exception e) {
					logger.error("Failed to viewed Property Image ", e);
				}
				
			}
			
			// Return to Listing page with error
			map.put("message_error", "Property Image  being view does not exists on the System.");
			preProcessPaginatedList(request, PropertyImages.class, ControllerUtil.getContextId(request), map, reqPage, pageSize,requestUriTmpl);
			return new ModelAndView("propertyImages/propertyImagesList", "data", map);
		}  catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void preProcessPaginatedList(HttpServletRequest request, Class<?> clazz,
			Long ctxId, Map<String, Object> map, Long reqPage, Long pageSize, String requestUriTmpl) throws Exception {
		processPaginatedList(clazz, request, map, reqPage, pageSize, requestUriTmpl);
	}
}