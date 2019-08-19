package com.bytesedge.bookvenue.controller.Property;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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
import com.bytesedge.bookvenue.model.Context;
import com.bytesedge.bookvenue.model.Organization;
import com.bytesedge.bookvenue.model.Property;
import com.bytesedge.bookvenue.model.StateType;
import com.bytesedge.bookvenue.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@SessionAttributes({"propertyForm"})
public class PropertyController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(PropertyController.class);
	
	private final String requestUriTmpl = "/app/property/list?reqPage=%d&pageSize=%d";
	
	@Autowired
	private PropertyFormValidator validator;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(validator);
	}

	public PropertyFormValidator getValidator() {
		return validator;
	}

	public void setValidator(PropertyFormValidator validator) {
		this.validator = validator;
	}

	@Override
	
	public void customModalAttributes(Model model) {
		model.addAttribute("AuditOperationType", AuditOperationType.values());
		model.addAttribute("AuditObject", AuditObject.values());
		model.addAttribute("StateType", StateType.values());
	}

	@SuppressWarnings("unchecked")
	public void preProcessPaginatedList(HttpServletRequest request, final Class<?> clazz, final Long ctxId, 
			final Map<String, Object> map, final Long reqPage, 
			final Long pageSize, final String requestUriTmpl) throws Exception {
		processPaginatedList(Property.class,request, map, reqPage, pageSize, requestUriTmpl);
		PaginatedResponse paginatedResponse = (PaginatedResponse) map.get("paginatedResponse");
		if(paginatedResponse != null) {
			List<Property> list = (List<Property>) paginatedResponse.getResultList();
			if(list != null && !list.isEmpty()) {
				for(Property property : list) {
					property.setDescr(property.getDescr().substring(0,  property.getDescr().length() > 24 ? 24 : property.getDescr().length()) + "...");			
					}
			}
		}
	}

	@InitBinder
	public void bindingPreparation(WebDataBinder binder) {
	  DateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY HH:mm");
	  CustomDateEditor orderDateEditor = new CustomDateEditor(dateFormat, true);
	  binder.registerCustomEditor(Date.class, orderDateEditor);
	}
	
	@Override
	public PaginatedResponse getPaginatedListing(HttpServletRequest httpRequest, PaginatedRequest paginatedRequest, final Map<String, Object> map) throws Exception {
		return DbService.getInstance().getSetupService().getPropertyList( ControllerUtil.getContextId(httpRequest), paginatedRequest);
	}
	
	@RequestMapping(value = "/app/property/list", method = {RequestMethod.GET})
	public @ResponseBody ModelAndView propertyList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="reqPage", required=false) Long reqPage, @RequestParam(value="pageSize", 
			required=false) Long pageSize, Model model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			customModalAttributes(model);
			preProcessPaginatedList(request, Property.class, ControllerUtil.getContextId(request), map, reqPage, pageSize, requestUriTmpl);
			return new ModelAndView("property/propertyList", "data", map);
		} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw e;
		}
	}
	
	@RequestMapping(value = "/app/property/list/export", method = {RequestMethod.GET}, produces = "application/xlsx")
	public @ResponseBody ModelAndView propertyListExportXlsx(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="reqPage", required=false) Long reqPage, @RequestParam(value="pageSize", required=false) Long pageSize,
			@RequestParam(value="exportType", required=false) String exportType) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			PaginatedResponse res = processPaginatedList(Property.class, request, map, reqPage, pageSize, requestUriTmpl);
			if(res != null) {
				map.put("list", res.getResultList());
				map.put("viewClass", PropertyXlsxStreamingView.class.getName());
				map.put("fileName", "Shilparamam-Property-List.xlsx");
				return new ModelAndView("xlxs", "map", map);
			} else {
				map.put("message_error", "Failed to create MS Excel file for Property report.");
				return new ModelAndView("property/propertyList", "data", map);
			}
		}  catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@RequestMapping(value = "/app/property/download/pdf", method = {RequestMethod.GET}, produces = "application/pdf")
	public @ResponseBody ModelAndView propertyDownloadPdf(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="id", required=true) Long id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Property dbProperty = DbService.getInstance().getSetupService().getPropertyByCtxIdAndId(ControllerUtil.getContextId(request), id);
			if(dbProperty != null) {
				map.put("obj", dbProperty);
				map.put("viewClass", PropertyPdfStreamingView.class.getName());
				map.put("fileName", "Shilparamam-Property-" + id + ".pdf");
				map.put("tmplName", "/WEB-INF/res/tmpl/report/property_view.pdf");
				return new ModelAndView("pdf", "map", map);
			} else {
				map.put("message_error", "Failed to create PDF file for Property " + id);
				preProcessPaginatedList(request, Property.class, ControllerUtil.getContextId(request), map, requestUriTmpl);
				return new ModelAndView("property/propertyList", "data", map);
			}
		}  catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@RequestMapping(value = "/app/property/create/form", method = {RequestMethod.GET})
	public @ResponseBody ModelAndView createPropertyForm(HttpServletRequest request, Model model) throws Exception {
		try {
			Property propertyForm = new Property();
			
			// Rest Captcha
			CaptchaServlet.resetCaptcha(request);
			customModalAttributes(model);
			return new ModelAndView("property/createProperty", "propertyForm", propertyForm);
		}  catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		// Incase of erros Return to Lising Page
		Map<String, Object> map = new HashMap<String, Object>();
		preProcessPaginatedList(request, Property.class, ControllerUtil.getContextId(request), map, requestUriTmpl); 
		return new ModelAndView("property/propertyList", "data", map);
	}
	
	@RequestMapping(value = "/app/property/create", method = {RequestMethod.POST})
	public @ResponseBody ModelAndView createProperty(HttpServletRequest request, 
			@ModelAttribute("propertyForm") @Valid Property propertyForm,
			BindingResult result, Model model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if(result.hasErrors()) {
				customModalAttributes(model);
				return new ModelAndView("property/createProperty", "propertyForm", propertyForm);
			} else {
				this.updateContextDoProperties(request, propertyForm);
				Context context = CacheService.getContextByUrl(request.getServerName());
				if(context != null) {
					Organization org = CacheService.getOrgByCtxId(context.getId());
					if(org != null) {
						propertyForm.setOrgId(org.getId());
					}
				}
				Property dbObject = (Property) DbService.getInstance().getSetupService().saveOrUpdateProperty(propertyForm);
				ObjectMapper jsonApi = new ObjectMapper();
				this.saveAuditObject(request, null, jsonApi.writeValueAsString(dbObject), AuditOperationType.CREATE, AuditObject.Property);
				preProcessPaginatedList(request, Property.class, ControllerUtil.getContextId(request), map, requestUriTmpl);
				map.put("message_success", "A new Property " + dbObject.getId() +" has been created.");
				return new ModelAndView("property/propertyList", "data", map);
			}
		}  catch (Exception e) {
			logger.error(e.getMessage(), e);
		// In case of errors Return to Listing Page
		map.put("message_error", "Failed to create a new Property.");
		preProcessPaginatedList(request, Property.class, ControllerUtil.getContextId(request), map, requestUriTmpl);
		return new ModelAndView("property/propertyList", "data", map);
	}
		}
	@RequestMapping(value = "/app/property/update/form", method = {RequestMethod.GET})
	public @ResponseBody ModelAndView updatePropertyForm(HttpServletRequest request,
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
					Property dbProperty = DbService.getInstance().getSetupService().getPropertyByCtxIdAndId(new Long(1), id);
					if(dbProperty != null) {
						customModalAttributes(model);
						return new ModelAndView("property/updateProperty", "propertyForm", dbProperty);
					}
					// Check permissions
				} catch (Exception e) {
					logger.error("Failed to delete Property", e);
				}
			}
			
			// Return to Listing page with error
			map.put("message_error", "Property being updated does not exists on the System.");
			preProcessPaginatedList(request, Property.class, ControllerUtil.getContextId(request), map,reqPage, pageSize, requestUriTmpl);
			
			// Rest Captcha
			CaptchaServlet.resetCaptcha(request);
			return new ModelAndView("property/propertyList", "data", map);
		}  catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@RequestMapping(value = "/app/property/update", method = {RequestMethod.POST})
	public @ResponseBody ModelAndView updateProperty(HttpServletRequest request,
			@ModelAttribute("propertyForm") @Valid Property propertyForm,
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
				return new ModelAndView("property/updateProperty", "propertyForm", propertyForm);
			} else {
				Property dbProperty = DbService.getInstance().getSetupService().getPropertyByCtxIdAndId(ControllerUtil.getContextId(request), propertyForm.getId());
				if(dbProperty != null) {
					ObjectMapper jsonApi = new ObjectMapper();
					String oldDbObject = jsonApi.writeValueAsString(dbProperty);
					
					this.updateContextDoProperties(request, propertyForm);
					dbProperty.setUniqueId(propertyForm.getUniqueId());
					dbProperty.setDescr(propertyForm.getDescr());
					dbProperty.setName(propertyForm.getName());
					dbProperty.setState(propertyForm.getState());
					dbProperty.setUpdatedUserId(propertyForm.getUpdatedUserId());
					dbProperty.setUpdatedTime(propertyForm.getUpdatedTime());
					Property dbObject = (Property) DbService.getInstance().getSetupService().saveOrUpdateProperty(dbProperty);
					this.saveAuditObject(request, oldDbObject, jsonApi.writeValueAsString(dbObject),
							AuditOperationType.UPDATE, AuditObject.Property);
					if(dbObject != null) {
						map.put("message_success", "A Property " + dbObject.getId() + " updated.");
					} else {
						map.put("message_error", "Updating Property " + dbProperty.getId() + " failed.");
					}
					
					preProcessPaginatedList(request, Property.class, ControllerUtil.getContextId(request), map,reqPage, pageSize, requestUriTmpl);
					return new ModelAndView("property/propertyList", "data", map);
				}
			}
		}  catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		map.put("message_error", "Updating Property failed.");
		preProcessPaginatedList(request, Property.class, ControllerUtil.getContextId(request), map,reqPage, pageSize, requestUriTmpl);
		return new ModelAndView("property/propertyList", "data", map);
	}
	
	@RequestMapping(value = "/app/property/view/form", method = {RequestMethod.GET})
	public @ResponseBody ModelAndView viewPropertyForm(HttpServletRequest request,
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
					Property dbProperty = DbService.getInstance().getSetupService().getPropertyByCtxIdAndId(new Long(1), id);
					if(dbProperty != null) {
						customModalAttributes(model);
						dbProperty.setOrgName(CacheService.getOrganizationById(ControllerUtil.getContextId(request), dbProperty.getOrgId()).getName());
						dbProperty.setCreatedUserName(CacheService.getUserById(ControllerUtil.getContextId(request), dbProperty.getCreatedUserId()).getDisplayName());
						User updatedUser = CacheService.getUserById(ControllerUtil.getContextId(request), dbProperty.getUpdatedUserId());
						if(updatedUser != null) {
							dbProperty.setUpdatedUserName(updatedUser.getDisplayName());
						}
						return new ModelAndView("property/viewProperty", "propertyForm", dbProperty);
					}
					// Check permissions
				} catch (Exception e) {
					logger.error("Failed to view Property", e);
				}
			}
			
			// Return to Listing page with error
			map.put("message_error", "Property being view does not exists on the System.");
			preProcessPaginatedList(request, Property.class, ControllerUtil.getContextId(request), map,reqPage, pageSize, requestUriTmpl);
			
			// Rest Captcha
			CaptchaServlet.resetCaptcha(request);
			return new ModelAndView("property/propertyList", "data", map);
		}  catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
}