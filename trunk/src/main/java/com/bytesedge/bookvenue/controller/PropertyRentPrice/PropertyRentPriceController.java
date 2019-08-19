package com.bytesedge.bookvenue.controller.PropertyRentPrice;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.bytesedge.bookvenue.model.EndUser;
import com.bytesedge.bookvenue.model.Organization;
import com.bytesedge.bookvenue.model.Property;
import com.bytesedge.bookvenue.model.PropertyRentPrice;
import com.bytesedge.bookvenue.model.RentPurpose;
import com.bytesedge.bookvenue.model.SessionOrgContext;
import com.bytesedge.bookvenue.model.SlotType;
import com.bytesedge.bookvenue.model.StateType;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@SessionAttributes({"propertyRentPriceForm"})
public class PropertyRentPriceController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(PropertyRentPriceController.class);
	
	private final String requestUriTmpl = "/app/propertyRentPrice/list?reqPage=%d&pageSize=%d";
	
	@Autowired
	private PropertyRentPriceFormValidator validator;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(validator);
	}

	public PropertyRentPriceFormValidator getValidator() {
		return validator;
	}

	public void setValidator(PropertyRentPriceFormValidator validator) {
		this.validator = validator;
	}

	@Override
	
	public void customModalAttributes(Model model) {
		model.addAttribute("AuditOperationType", AuditOperationType.values());
		model.addAttribute("AuditObject", AuditObject.values());
		model.addAttribute("StateType", StateType.values());
		model.addAttribute("SlotType", SlotType.values());
	}

	@SuppressWarnings("unchecked")
	public void preProcessPaginatedList(HttpServletRequest request, final Class<?> clazz, final Long ctxId, 
			final Map<String, Object> map, final Long reqPage, 
			final Long pageSize, final String requestUriTmpl) throws Exception {
		processPaginatedList(PropertyRentPrice.class,request, map, reqPage, pageSize, requestUriTmpl);
		PaginatedResponse paginatedResponse = (PaginatedResponse) map.get("paginatedResponse");
		if(paginatedResponse != null) {
			@SuppressWarnings("unused")
			List<PropertyRentPrice> list = (List<PropertyRentPrice>) paginatedResponse.getResultList();
			if (list != null && !list.isEmpty()) {
				for (PropertyRentPrice propertyRentPrice : list) {
					propertyRentPrice.setOrgName(CacheService.getOrganizationById(ControllerUtil.getContextId(request), propertyRentPrice.getOrgId()).getName());
					propertyRentPrice.setPropertyName(CacheService.getPropertyById(ControllerUtil.getContextId(request), propertyRentPrice.getPropertyId()).getName());
					propertyRentPrice.setPurposeName(CacheService.getPurposeById(ControllerUtil.getContextId(request), propertyRentPrice.getPurposeId()).getName());
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
		return DbService.getInstance().getSetupService().getPropertyRentPriceList( ControllerUtil.getContextId(httpRequest), paginatedRequest);
	}
	
	@RequestMapping(value = "/app/propertyRentPrice/list", method = {RequestMethod.GET})
	public @ResponseBody ModelAndView propertyRentPriceList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="reqPage", required=false) Long reqPage, @RequestParam(value="pageSize", 
			required=false) Long pageSize, Model model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			SessionOrgContext se = ControllerUtil.getValidSessionContext(request);
			if(se != null && se.getOrgId() != null && se.getOrgId() > 0L) {
				customModalAttributes(model);
				preProcessPaginatedList(request, PropertyRentPrice.class, ControllerUtil.getContextId(request), map, reqPage, pageSize, requestUriTmpl);
				return new ModelAndView("propertyRentPrice/propertyRentPriceList", "data", map);	
			} else {
				return new ModelAndView("common/selectOrg", "orgType", "Organisation");
			}
		} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw e;
		}
	}
	
	@RequestMapping(value = "/app/propertyRentPrice/list/export", method = {RequestMethod.GET}, produces = "application/xlsx")
	public @ResponseBody ModelAndView propertyRentPriceListExportXlsx(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="reqPage", required=false) Long reqPage, @RequestParam(value="pageSize", required=false) Long pageSize,
			@RequestParam(value="exportType", required=false) String exportType) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			PaginatedResponse res = processPaginatedList(PropertyRentPrice.class, request, map, reqPage, pageSize, requestUriTmpl);
			if(res != null) {
				map.put("list", res.getResultList());
				map.put("viewClass", PropertyRentPriceXlsxStreamingView.class.getName());
				map.put("fileName", "Shilparamam-PropertyRentPrice-List.xlsx");
				return new ModelAndView("xlxs", "map", map);
			} else {
				map.put("message_error", "Failed to create MS Excel file for PropertyRentPrice report.");
				return new ModelAndView("propertyRentPrice/propertyRentPriceList", "data", map);
			}
		}  catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@RequestMapping(value = "/app/propertyRentPrice/download/pdf", method = {RequestMethod.GET}, produces = "application/pdf")
	public @ResponseBody ModelAndView propertyRentPriceDownloadPdf(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="id", required=true) Long id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			PropertyRentPrice dbPropertyRentPrice = DbService.getInstance().getSetupService().getPropertyRentPriceByCtxIdAndId(ControllerUtil.getContextId(request), id);
			if(dbPropertyRentPrice != null) {
				map.put("obj", dbPropertyRentPrice);
				map.put("viewClass", PropertyRentPricePdfStreamingView.class.getName());
				map.put("fileName", "Shilparamam-PropertyRentPrice-" + id + ".pdf");
				map.put("tmplName", "/WEB-INF/res/tmpl/report/propertyRentPrice_view.pdf");
				return new ModelAndView("pdf", "map", map);
			} else {
				map.put("message_error", "Failed to create PDF file for PropertyRentPrice " + id);
				preProcessPaginatedList(request, PropertyRentPrice.class, ControllerUtil.getContextId(request), map, requestUriTmpl);
				return new ModelAndView("propertyRentPrice/propertyRentPriceList", "data", map);
			}
		}  catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@RequestMapping(value = "/app/propertyRentPrice/create/form", method = {RequestMethod.GET})
	public @ResponseBody ModelAndView createPropertyRentPriceForm(HttpServletRequest request, Model model) throws Exception {
		try {
			PropertyRentPrice propertyRentPriceForm = new PropertyRentPrice();
			
			// Rest Captcha
			CaptchaServlet.resetCaptcha(request);
			List<Property> propertyList = DbService.getInstance().getSetupService().getPropertyListByCtxId(ControllerUtil.getContextId(request));
			List<RentPurpose> purposeList = DbService.getInstance().getSetupService().getRentPurposeListByCtxId(ControllerUtil.getContextId(request));
			model.addAttribute("propertyList", propertyList);
			model.addAttribute("purposeList", purposeList);
			customModalAttributes(model);
			return new ModelAndView("propertyRentPrice/createPropertyRentPrice", "propertyRentPriceForm", propertyRentPriceForm);
		}  catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		// Incase of erros Return to Lising Page
		Map<String, Object> map = new HashMap<String, Object>();
		preProcessPaginatedList(request, PropertyRentPrice.class, ControllerUtil.getContextId(request), map, requestUriTmpl); 
		return new ModelAndView("propertyRentPrice/propertyRentPriceList", "data", map);
	}
	
	@RequestMapping(value = "/app/propertyRentPrice/create", method = {RequestMethod.POST})
	public @ResponseBody ModelAndView createPropertyRentPrice(HttpServletRequest request, 
			@ModelAttribute("propertyRentPriceForm") @Valid PropertyRentPrice propertyRentPriceForm,
			BindingResult result, Model model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if(result.hasErrors()) {
				customModalAttributes(model);
				return new ModelAndView("propertyRentPrice/createPropertyRentPrice", "propertyRentPriceForm", propertyRentPriceForm);
			} else {
				this.updateContextDoProperties(request, propertyRentPriceForm);
				
				// Update org Id
				Context context = CacheService.getContextByUrl(request.getServerName());
				if(context != null) {
					Organization org = CacheService.getOrgByCtxId(context.getId());
					if(org != null) {
						propertyRentPriceForm.setOrgId(org.getId());
					}
				}
				PropertyRentPrice dbObject = (PropertyRentPrice) DbService.getInstance().getSetupService().saveOrUpdatePropertyRentPrice(propertyRentPriceForm);
				
				ObjectMapper jsonApi = new ObjectMapper();
				this.saveAuditObject(request, null, jsonApi.writeValueAsString(dbObject), AuditOperationType.CREATE, AuditObject.PropertyRentPrice);
				
				preProcessPaginatedList(request, PropertyRentPrice.class, ControllerUtil.getContextId(request), map, requestUriTmpl);
				map.put("message_success", "A new PropertyRentPrice " + dbObject.getId() +" has been created.");
				return new ModelAndView("propertyRentPrice/propertyRentPriceList", "data", map);
			}
		}  catch (Exception e) {
			logger.error(e.getMessage(), e);
		// In case of errors Return to Listing Page
		map.put("message_error", "Failed to create a new PropertyRentPrice.");
		preProcessPaginatedList(request, PropertyRentPrice.class, ControllerUtil.getContextId(request), map, requestUriTmpl);
		return new ModelAndView("propertyRentPrice/propertyRentPriceList", "data", map);
	}
		}
	
	@RequestMapping(value = "/app/propertyRentPrice/update/form", method = { RequestMethod.GET })
	public @ResponseBody ModelAndView updatePropertyRentPriceForm(HttpServletRequest request,
			@RequestParam(value = "id") Long id, @RequestParam(value = "reqPage") Long reqPage,
			@RequestParam(value = "pageSize") Long pageSize, Model model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// Global forwarding parameters
			model.addAttribute("reqPage", reqPage);
			model.addAttribute("pageSize", pageSize);
			if (id > 0) {
				try {
					List<Property> propertyList = DbService.getInstance().getSetupService().getPropertyListByCtxId(ControllerUtil.getContextId(request));
					List<RentPurpose> purposeList = DbService.getInstance().getSetupService().getRentPurposeListByCtxId(ControllerUtil.getContextId(request));
					model.addAttribute("propertyList", propertyList);
					model.addAttribute("purposeList", purposeList);
					customModalAttributes(model);
					// Load the Object with context id
					PropertyRentPrice dbPropertyRentPrice = DbService.getInstance().getSetupService()
							.getPropertyRentPriceByCtxIdAndId(new Long(1), id);
					if (dbPropertyRentPrice != null) {
						customModalAttributes(model);
						return new ModelAndView("propertyRentPrice/updatePropertyRentPrice", "propertyRentPriceForm", dbPropertyRentPrice);
					}
					// Check permissions
				} catch (Exception e) {
					logger.error("Failed to delete PropertyRentPrice", e);
				}
			}

			// Return to Listing page with error
			map.put("message_error", "PropertyRentPrice being updated does not exists on the System.");
			preProcessPaginatedList(request, PropertyRentPrice.class, ControllerUtil.getContextId(request), map, reqPage,
					pageSize, requestUriTmpl);

			// Rest Captcha
			CaptchaServlet.resetCaptcha(request);
			return new ModelAndView("propertyRentPrice/propertyRentPriceList", "data", map);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}

	@RequestMapping(value = "/app/propertyRentPrice/update", method = { RequestMethod.POST })
	public @ResponseBody ModelAndView updatePropertyRentPrice(HttpServletRequest request,
			@ModelAttribute("propertyRentPriceForm") @Valid PropertyRentPrice propertyRentPriceForm, BindingResult result,
			@RequestParam(value = "reqPage") Long reqPage, @RequestParam(value = "pageSize") Long pageSize, Model model)
					throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// Global forwarding parameters
			model.addAttribute("reqPage", reqPage);
			model.addAttribute("pageSize", pageSize);
			if (result.hasErrors()) {
				customModalAttributes(model);
				return new ModelAndView("propertyRentPrice/updatePropertyRentPrice", "propertyRentPriceForm", propertyRentPriceForm);
			} else {
				PropertyRentPrice dbPropertyRentPrice = DbService.getInstance().getSetupService()
						.getPropertyRentPriceByCtxIdAndId(ControllerUtil.getContextId(request), propertyRentPriceForm.getId());
				if (dbPropertyRentPrice != null) {
					ObjectMapper jsonApi = new ObjectMapper();
					String oldDbObject = jsonApi.writeValueAsString(dbPropertyRentPrice);

					this.updateContextDoProperties(request, propertyRentPriceForm);
					dbPropertyRentPrice.setCgst(propertyRentPriceForm.getCgst());
					dbPropertyRentPrice.setSgst(propertyRentPriceForm.getSgst());
					dbPropertyRentPrice.setServiceTax(propertyRentPriceForm.getServiceTax());
					dbPropertyRentPrice.setUpdatedUserId(propertyRentPriceForm.getUpdatedUserId());
					dbPropertyRentPrice.setUpdatedTime(propertyRentPriceForm.getUpdatedTime());
					PropertyRentPrice dbObject = (PropertyRentPrice) DbService.getInstance().getSetupService()
							.saveOrUpdatePropertyRentPrice(dbPropertyRentPrice);
					this.saveAuditObject(request, oldDbObject, jsonApi.writeValueAsString(dbObject),
							AuditOperationType.UPDATE, AuditObject.PropertyRentPrice);
					if (dbObject != null) {
						map.put("message_success", "A PropertyRentPrice " + dbObject.getId() + " updated.");
					} else {
						map.put("message_error", "Updating PropertyRentPrice " + dbPropertyRentPrice.getId() + " failed.");
					}

					preProcessPaginatedList(request, PropertyRentPrice.class, ControllerUtil.getContextId(request), map,
							reqPage, pageSize, requestUriTmpl);
					return new ModelAndView("propertyRentPrice/propertyRentPriceList", "data", map);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		map.put("message_error", "Updating PropertyRentPrice failed.");
		preProcessPaginatedList(request, PropertyRentPrice.class, ControllerUtil.getContextId(request), map, reqPage,
				pageSize, requestUriTmpl);
		return new ModelAndView("propertyRentPrice/propertyRentPriceList", "data", map);
	}

	@RequestMapping(value = "/app/propertyRentPrice/view/form", method = {RequestMethod.GET})
	public @ResponseBody ModelAndView viewUserForm(HttpServletRequest request,
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
					PropertyRentPrice dbPropertyRentPrice = DbService.getInstance().getSetupService().getPropertyRentPriceByCtxIdAndId(new Long(1), id);
					if(dbPropertyRentPrice != null) {
						dbPropertyRentPrice.setCreatedUserName(CacheService.getUserById(ControllerUtil.getContextId(request), dbPropertyRentPrice.getCreatedUserId()).getDisplayName());
						dbPropertyRentPrice.setOrgName(CacheService.getOrganizationById(ControllerUtil.getContextId(request), dbPropertyRentPrice.getOrgId()).getName());
						dbPropertyRentPrice.setPropertyName(CacheService.getPropertyById(ControllerUtil.getContextId(request), dbPropertyRentPrice.getPropertyId()).getName());
						dbPropertyRentPrice.setPurposeName(CacheService.getPurposeById(ControllerUtil.getContextId(request), dbPropertyRentPrice.getPurposeId()).getName());
					
					}	// Rest Captcha
						CaptchaServlet.resetCaptcha(request);
						List<Property> propertyList = DbService.getInstance().getSetupService().getPropertyListByCtxId(ControllerUtil.getContextId(request));
						List<Property> propertyListFiltered = new ArrayList<Property>();
						if(propertyList != null) {
							for(Property p : propertyList) {
									propertyListFiltered.add(p);
								}
						return new ModelAndView("propertyRentPrice/viewPropertyRentPrice", "propertyRentPriceForm", dbPropertyRentPrice);
					}
					// Check permissions
				} catch (Exception e) {
					logger.error("Failed to view Property rent price", e);
				}
			}
			
			// Return to Listing page with error
			map.put("message_error", "Property rent price being view does not exists on the System.");
			preProcessPaginatedList(request, PropertyRentPrice.class, ControllerUtil.getContextId(request), map,reqPage, pageSize, requestUriTmpl);
			return new ModelAndView("propertyRentPrice/propertyRentPriceList", "data", map);
		}  catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
	
}