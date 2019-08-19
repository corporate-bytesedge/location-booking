package com.bytesedge.bookvenue.controller.organization;

import java.util.HashMap;
import java.util.List;
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
import com.bytesedge.bookvenue.model.Organization;
import com.bytesedge.bookvenue.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@SessionAttributes({"organizationForm"})
public class OrganizationController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(OrganizationController.class);
	
	private final String requestUriTmpl = "/app/organization/list?reqPage=%d&pageSize=%d";
	
	@Autowired
	private OrganizationFormValidator validator;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(validator);
	}
	
	public OrganizationFormValidator getValidator() {
		return validator;
	}

	public void setValidator(OrganizationFormValidator validator) {
		this.validator = validator;
	}

	@Override
	public void customModalAttributes(Model model) {
		model.addAttribute("AuditOperationType", AuditOperationType.values());
		model.addAttribute("AuditObject", AuditObject.values());		
	}
	
	
	@SuppressWarnings("unchecked")
	public void preProcessPaginatedList(HttpServletRequest request, final Class<?> clazz, final Long ctxId, 
			final Map<String, Object> map, final Long reqPage, 
			final Long pageSize, final String requestUriTmpl) throws Exception {
		processPaginatedList(Organization.class, request, map, reqPage, pageSize, requestUriTmpl);
		PaginatedResponse paginatedResponse = (PaginatedResponse) map.get("paginatedResponse");
		if(paginatedResponse != null) {
			List<Organization> list = (List<Organization>) paginatedResponse.getResultList();
			if(list != null && !list.isEmpty()) {
				for(Organization organization : list) {
					organization.setPhoneNumber(organization.getPhoneNumber().replaceAll("\\d(?=(?:\\D*\\d){4})", "*"));
					organization.setMobileNumber(organization.getMobileNumber().replaceAll("\\d(?=(?:\\D*\\d){4})", "*"));
				}
			}
		}
	}
	
	@Override
	public PaginatedResponse getPaginatedListing(HttpServletRequest httpRequest, PaginatedRequest paginatedRequest, final Map<String, Object> map) throws Exception {
			return DbService.getInstance().getOrgService().getOrganizationList( ControllerUtil.getContextId(httpRequest), paginatedRequest);
	}
	
	@RequestMapping(value = "/app/organization/list", method = {RequestMethod.GET})
	public @ResponseBody ModelAndView  organizationList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="reqPage", required=false) Long reqPage, @RequestParam(value="pageSize", 
			required=false) Long pageSize) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			preProcessPaginatedList(request, Organization.class, ControllerUtil.getContextId(request), map, reqPage, pageSize, requestUriTmpl); 
			return new ModelAndView("organization/organizationList", "data", map);
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@RequestMapping(value = "/app/organization/list/export", method = {RequestMethod.GET}, produces = "application/xlsx")
	public @ResponseBody ModelAndView organizationListExportXlsx(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="reqPage", required=false) Long reqPage, @RequestParam(value="pageSize", required=false) Long pageSize,
			@RequestParam(value="exportType", required=false) String exportType) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			PaginatedResponse res = processPaginatedList(Organization.class, request, map, reqPage, pageSize, requestUriTmpl);
			if(res != null) {
				map.put("list", res.getResultList());
				map.put("viewClass",  OrganizationXlsxStreamingView.class.getName());
				map.put("fileName", "Shilparamam-Organization-List.xlsx");
				return new ModelAndView("xlxs", "map", map);
			} else {
				map.put("message_error", "Failed to create MS Excel file for Panchayat data report.");
				return new ModelAndView("organization/organizationList", "data", map);
			}
		}  catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@RequestMapping(value = "/app/organization/download/pdf", method = {RequestMethod.GET}, produces = "application/pdf")
	public @ResponseBody ModelAndView organizationDownloadPdf(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="id", required=true) Long id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Organization dbOrganization = DbService.getInstance().getOrgService().getOrganizationByIdAndCtxId(id,
					ControllerUtil.getContextId(request));
			if(dbOrganization != null) {
				map.put("obj", dbOrganization);
				map.put("viewClass", OrganizationPdfStreamingView.class.getName());
				map.put("fileName", "Shilparamam-Organization-" + id + ".pdf");
				map.put("tmplName", "/WEB-INF/res/tmpl/report/panchayat_view.pdf");
				return new ModelAndView("pdf", "map", map);
			} else {
				map.put("message_error", "Failed to create PDF file for Organization " + id);
				preProcessPaginatedList(request, Organization.class, ControllerUtil.getContextId(request), map, requestUriTmpl);
				return new ModelAndView("organization/organizationList", "data", map);
			}
		}  catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@RequestMapping(value = "/app/organization/create/form", method = {RequestMethod.GET})
	public @ResponseBody ModelAndView createOrganizationForm(HttpServletRequest request, Model model) throws Exception {
		try {
			Organization organizationForm = new Organization();
			customModalAttributes(model);
				return new ModelAndView("organization/createOrganization", "organizationForm", organizationForm);	
		}  catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		// Incase of erros Return to Lising Page
		Map<String, Object> map = new HashMap<String, Object>();
		preProcessPaginatedList(request, Organization.class, ControllerUtil.getContextId(request), map, requestUriTmpl); 
		return new ModelAndView("organization/organizationList", "data", map);
	}
	
	@RequestMapping(value = "/app/organization/create", method = {RequestMethod.POST})
	public @ResponseBody ModelAndView createOrganization(HttpServletRequest request, 
			@ModelAttribute("organizationForm") @Valid Organization organizationForm,
			BindingResult result, Model model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if(result.hasErrors()) {
				customModalAttributes(model);
				return new ModelAndView("organization/createOrganization", "organizationForm", organizationForm);
			} else {
				Organization dbObject = (Organization) DbService.getInstance().getOrgService().getOrganizationByCtxIdAndId(ControllerUtil.getContextId(request), organizationForm.getId());
				if(dbObject != null){
					customModalAttributes(model);
					model.addAttribute("message_error", "A Panchayat data with given Org id already exists");
					return new ModelAndView("organization/createOrganization", "organizationForm", organizationForm);
				}
				this.updateContextDoProperties(request, organizationForm);
				Organization dbObj = (Organization) DbService.getInstance().getOrgService().saveOrUpdateOrganization(organizationForm);
			
				ObjectMapper jsonApi = new ObjectMapper();
				this.saveAuditObject(request, null, jsonApi.writeValueAsString(dbObject), AuditOperationType.CREATE, AuditObject.Organization);
				
				preProcessPaginatedList(request, Organization.class, ControllerUtil.getContextId(request), map, requestUriTmpl);
				customModalAttributes(model);
				map.put("message_success", "A new Panchayat data with Org id " + dbObj.getId()+" has been created.");
				return new ModelAndView("organization/organizationList", "data", map);
			}
		}  catch (Exception e) {
			logger.error(e.getMessage(), e);
		// In case of errors Return to Listing Page
		map.put("message_error", "Failed to create a new Panchayat data.");
		preProcessPaginatedList(request, Organization.class, ControllerUtil.getContextId(request), map, requestUriTmpl);
		return new ModelAndView("organization/organizationList", "data", map);
	}
}
	
	@RequestMapping(value = "/app/organization/update/form", method = {RequestMethod.GET})
	public @ResponseBody ModelAndView updateOrganizationForm(HttpServletRequest request, Model model,
			@RequestParam(value="id") Long id,
			@RequestParam(value="reqPage") Long reqPage,
			@RequestParam(value="pageSize") Long pageSize) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// Global forwarding parameters
			model.addAttribute("reqPage", reqPage);
			model.addAttribute("pageSize", pageSize);
			if(id > 0) {
				try {
					// Load the Object with context id
					Organization dbOrganization = DbService.getInstance().getOrgService().getOrganizationByIdAndCtxId(id, ControllerUtil.getContextId(request));
					if(dbOrganization != null) {
						// Rest Captcha
						CaptchaServlet.resetCaptcha(request);
						customModalAttributes(model);
						return new ModelAndView("organization/updateOrganization", "organizationForm", dbOrganization);
					}
					// Check permissions
				} catch (Exception e) {
					logger.error("Failed to delete Panchayat data", e);
				}
			}
			
			// Return to Listing page with error
			map.put("message_error", "Panchayat data being updated does not exists on the System.");
			preProcessPaginatedList(request, Organization.class, ControllerUtil.getContextId(request), map, reqPage, pageSize, requestUriTmpl);
			return new ModelAndView("organization/organizationList", "data", map);
		}  catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@RequestMapping(value = "/app/organization/update", method = {RequestMethod.POST})
	public @ResponseBody ModelAndView updateOrganization(HttpServletRequest request,
			@ModelAttribute("organizationForm") @Valid Organization organizationForm, Model model,
			BindingResult result,
			@RequestParam(value="reqPage", required=false, defaultValue = "0") Long reqPage,
			@RequestParam(value="pageSize", required=false, defaultValue = "25") Long pageSize) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// Global forwarding parameters
			model.addAttribute("reqPage", reqPage);
			model.addAttribute("pageSize", pageSize);
			if(result.hasErrors()) {
				customModalAttributes(model);
				return new ModelAndView("organization/updateOrganization", "organizationForm", organizationForm);
			} else {
				// Pre process the formBean
				Organization dbOrganization = DbService.getInstance().getOrgService().getOrganizationByIdAndCtxId(organizationForm.getId(),
						ControllerUtil.getContextId(request));
				if(dbOrganization != null) {
					ObjectMapper jsonApi = new ObjectMapper();
					String oldDbObject = jsonApi.writeValueAsString(dbOrganization);
					
					this.updateContextDoProperties(request, dbOrganization);
					dbOrganization.setName(organizationForm.getName());
					dbOrganization.setPhoneNumber(organizationForm.getPhoneNumber());
					dbOrganization.setMobileNumber(organizationForm.getMobileNumber());
					dbOrganization.setEmailId(organizationForm.getEmailId());
					Organization dbObject = (Organization) DbService.getInstance().getOrgService().saveOrUpdateOrganization(dbOrganization);
					this.saveAuditObject(request, oldDbObject, jsonApi.writeValueAsString(dbObject),
							AuditOperationType.UPDATE, AuditObject.Organization);
					if(dbObject != null) {
						map.put("message_success", "A Panchayat data with Org id " + dbObject.getId() + " is updated.");
					} else {
						map.put("message_error", "Updating Panchayat data with Org id " + dbOrganization.getId() + " is failed.");
					}
					
					preProcessPaginatedList(request, Organization.class, ControllerUtil.getContextId(request), map, reqPage, pageSize, requestUriTmpl);
					customModalAttributes(model);
					return new ModelAndView("organization/organizationList", "data", map);
				}
			}
		}  catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		map.put("message_error", "Updating Panchayat data failed.");
		preProcessPaginatedList(request, Organization.class, ControllerUtil.getContextId(request), map, reqPage, pageSize, requestUriTmpl);
		customModalAttributes(model);
		return new ModelAndView("organization/organizationList", "data", map);
	}
	
	@RequestMapping(value = "/app/organization/view/form", method = {RequestMethod.GET})
	public @ResponseBody ModelAndView viewOrganizationForm(HttpServletRequest request,
			@RequestParam(value="id") Long id,@RequestParam(value="reqPage") Long reqPage,
			@RequestParam(value="pageSize") Long pageSize,Model model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// Global forwarding parameters
			model.addAttribute("reqPage", reqPage);
			model.addAttribute("pageSize", pageSize);
			if(id > 0) {
				try {
					// Load the Object with context id
					Organization dbOrganization = DbService.getInstance().getOrgService().getOrganizationByIdAndCtxId(id, new Long(1));
					if(dbOrganization != null) {
						dbOrganization.setCreatedUserName(CacheService.getUserById(ControllerUtil.getContextId(request), dbOrganization.getCreatedUserId()).getDisplayName());
						dbOrganization.setMobileNumber(dbOrganization.getMobileNumber().replaceAll("\\d(?=(?:\\D*\\d){4})", "*"));
						dbOrganization.setPhoneNumber(dbOrganization.getPhoneNumber().replaceAll("\\d(?=(?:\\D*\\d){4})", "*"));
						User updatedUser = CacheService.getUserById(ControllerUtil.getContextId(request), dbOrganization.getUpdatedUserId());
						if(updatedUser != null) {
							dbOrganization.setUpdatedUserName(updatedUser.getDisplayName());
						}

						// Rest Captcha
						CaptchaServlet.resetCaptcha(request);
						return new ModelAndView("organization/viewOrganization", "organizationForm", dbOrganization);
					}
					// Check permissions
				} catch (Exception e) {
					logger.error("Failed to viewed Panchayat data", e);
				}
				
			}
			
			// Return to Listing page with error
			map.put("message_error", "Panchayat data being view does not exists on the System.");
			preProcessPaginatedList(request, Organization.class, ControllerUtil.getContextId(request), map, reqPage, pageSize, requestUriTmpl);
			return new ModelAndView("organization/organizationList", "data", map);
		}  catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
}