package com.bytesedge.bookvenue.controller.smsGatewayDetails;

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
import com.bytesedge.bookvenue.model.SmsGatewayDetails;
import com.bytesedge.bookvenue.model.StateType;
import com.bytesedge.bookvenue.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@SessionAttributes({ "smsGatewayDetailsForm" })
public class SmsGatewayDetailsController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(SmsGatewayDetailsController.class);

	private final String requestUriTmpl = "/app/smsGatewayDetails/list?reqPage=%d&pageSize=%d";

	@Autowired
	private SmsGatewayDetailsFormValidator validator;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(validator);
	}

	public SmsGatewayDetailsFormValidator getValidator() {
		return validator;
	}

	public void setValidator(SmsGatewayDetailsFormValidator validator) {
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
			final Map<String, Object> map, final Long reqPage, final Long pageSize, final String requestUriTmpl)
					throws Exception {
		processPaginatedList(SmsGatewayDetails.class, request, map, reqPage, pageSize, requestUriTmpl);
		PaginatedResponse paginatedResponse = (PaginatedResponse) map.get("paginatedResponse");
		if (paginatedResponse != null) {
			List<SmsGatewayDetails> list = (List<SmsGatewayDetails>) paginatedResponse.getResultList();
			if (list != null && !list.isEmpty()) {
				for (SmsGatewayDetails smsGatewayDetails : list) {
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
	public PaginatedResponse getPaginatedListing(HttpServletRequest httpRequest, PaginatedRequest paginatedRequest,
			final Map<String, Object> map) throws Exception {
		return DbService.getInstance().getSetupService()
				.getSmsGatewayDetailsList(ControllerUtil.getContextId(httpRequest), paginatedRequest);
	}

	@RequestMapping(value = "/app/smsGatewayDetails/list", method = { RequestMethod.GET })
	public @ResponseBody ModelAndView smsGatewayDetailsList(HttpServletRequest request,
			HttpServletResponse response, @RequestParam(value = "reqPage", required = false) Long reqPage,
			@RequestParam(value = "pageSize", required = false) Long pageSize, Model model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			customModalAttributes(model);
			preProcessPaginatedList(request, SmsGatewayDetails.class, ControllerUtil.getContextId(request), map,
					reqPage, pageSize, requestUriTmpl);
			return new ModelAndView("smsGatewayDetails/smsGatewayDetailsList", "data", map);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}

	@RequestMapping(value = "/app/smsGatewayDetails/list/export", method = {
			RequestMethod.GET }, produces = "application/xlsx")
	public @ResponseBody ModelAndView smsGatewayDetailsListExportXlsx(HttpServletRequest request,
			HttpServletResponse response, @RequestParam(value = "reqPage", required = false) Long reqPage,
			@RequestParam(value = "pageSize", required = false) Long pageSize,
			@RequestParam(value = "exportType", required = false) String exportType) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			PaginatedResponse res = processPaginatedList(SmsGatewayDetails.class, request, map, reqPage, pageSize,
					requestUriTmpl);
			if (res != null) {
				map.put("list", res.getResultList());
				map.put("viewClass", SmsGatewayDetailsXlsxStreamingView.class.getName());
				map.put("fileName", "Shilparamam-SmsGatewayDetails-List.xlsx");
				return new ModelAndView("xlxs", "map", map);
			} else {
				map.put("message_error", "Failed to create MS Excel file for SmsGatewayDetails report.");
				return new ModelAndView("smsGatewayDetails/smsGatewayDetailsList", "data", map);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}

	@RequestMapping(value = "/app/smsGatewayDetails/download/pdf", method = {
			RequestMethod.GET }, produces = "application/pdf")
	public @ResponseBody ModelAndView smsGatewayDetailsDownloadPdf(HttpServletRequest request,
			HttpServletResponse response, @RequestParam(value = "id", required = true) Long id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			SmsGatewayDetails dbSmsGatewayDetails = DbService.getInstance().getSetupService()
					.getSmsGatewayDetailsByCtxIdAndId(ControllerUtil.getContextId(request), id);
			if (dbSmsGatewayDetails != null) {
				map.put("obj", dbSmsGatewayDetails);
				map.put("viewClass", SmsGatewayDetailsPdfStreamingView.class.getName());
				map.put("fileName", "Shilparamam-SmsGatewayDetails-" + id + ".pdf");
				map.put("tmplName", "/WEB-INF/res/tmpl/report/SmsGatewayDetails_view.pdf");
				return new ModelAndView("pdf", "map", map);
			} else {
				map.put("message_error", "Failed to create PDF file for SmsGatewayDetails " + id);
				preProcessPaginatedList(request, SmsGatewayDetails.class, ControllerUtil.getContextId(request), map,
						requestUriTmpl);
				return new ModelAndView("smsGatewayDetails/smsGatewayDetailsList", "data", map);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}

	@RequestMapping(value = "/app/smsGatewayDetails/create/form", method = { RequestMethod.GET })
	public @ResponseBody ModelAndView createSmsGatewayDetailsForm(HttpServletRequest request, Model model)
			throws Exception {
		try {
			SmsGatewayDetails smsGatewayDetailsForm = new SmsGatewayDetails();

			// Rest Captcha
			CaptchaServlet.resetCaptcha(request);
			customModalAttributes(model);
			return new ModelAndView("smsGatewayDetails/createSmsGatewayDetails", "smsGatewayDetailsForm",
					smsGatewayDetailsForm);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		// Incase of erros Return to Lising Page
		Map<String, Object> map = new HashMap<String, Object>();
		preProcessPaginatedList(request, SmsGatewayDetails.class, ControllerUtil.getContextId(request), map,
				requestUriTmpl);
		return new ModelAndView("smsGatewayDetails/smsGatewayDetailsList", "data", map);
	}

	@RequestMapping(value = "/app/smsGatewayDetails/create", method = { RequestMethod.POST })
	public @ResponseBody ModelAndView createSmsGatewayDetails(HttpServletRequest request,
			@ModelAttribute("smsGatewayDetailsForm") @Valid SmsGatewayDetails smsGatewayDetailsForm,
			BindingResult result, Model model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (result.hasErrors()) {
				customModalAttributes(model);
				return new ModelAndView("smsGatewayDetails/createSmsGatewayDetails",
						"smsGatewayDetailsForm", smsGatewayDetailsForm);
			} else {
				this.updateContextDoProperties(request, smsGatewayDetailsForm);
				Context context = CacheService.getContextByUrl(request.getServerName());
				if (context != null) {
					Organization org = CacheService.getOrgByCtxId(context.getId());
					if (org != null) {
						smsGatewayDetailsForm.setOrgId(org.getId());
					}
				}
				SmsGatewayDetails dbObject = (SmsGatewayDetails) DbService.getInstance().getSetupService()
						.saveOrUpdateSmsGatewayDetails(smsGatewayDetailsForm);
				ObjectMapper jsonApi = new ObjectMapper();
				this.saveAuditObject(request, null, jsonApi.writeValueAsString(dbObject), AuditOperationType.CREATE,
						AuditObject.SmsGatewayDetails);
				preProcessPaginatedList(request, SmsGatewayDetails.class, ControllerUtil.getContextId(request), map,
						requestUriTmpl);
				map.put("message_success", "A new SmsGatewayDetails " + dbObject.getId() + " has been created.");
				return new ModelAndView("smsGatewayDetails/smsGatewayDetailsList", "data", map);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			// In case of errors Return to Listing Page
			map.put("message_error", "Failed to create a new SmsGatewayDetails.");
			preProcessPaginatedList(request, SmsGatewayDetails.class, ControllerUtil.getContextId(request), map,
					requestUriTmpl);
			return new ModelAndView("smsGatewayDetails/smsGatewayDetailsList", "data", map);
		}
	}

	@RequestMapping(value = "/app/smsGatewayDetails/update/form", method = { RequestMethod.GET })
	public @ResponseBody ModelAndView updateSmsGatewayDetailsForm(HttpServletRequest request,
			@RequestParam(value = "id") Long id, @RequestParam(value = "reqPage") Long reqPage,
			@RequestParam(value = "pageSize") Long pageSize, Model model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// Global forwarding parameters
			model.addAttribute("reqPage", reqPage);
			model.addAttribute("pageSize", pageSize);
			if (id > 0) {
				try {
					// Load the Object with context id
					SmsGatewayDetails dbSmsGatewayDetails = DbService.getInstance().getSetupService()
							.getSmsGatewayDetailsByCtxIdAndId(new Long(1), id);
					if (dbSmsGatewayDetails != null) {
						customModalAttributes(model);
						return new ModelAndView("smsGatewayDetails/updateSmsGatewayDetails",
								"smsGatewayDetailsForm", dbSmsGatewayDetails);
					}
					// Check permissions
				} catch (Exception e) {
					logger.error("Failed to delete SmsGatewayDetails", e);
				}
			}

			// Return to Listing page with error
			map.put("message_error", "SmsGatewayDetails being updated does not exists on the System.");
			preProcessPaginatedList(request, SmsGatewayDetails.class, ControllerUtil.getContextId(request), map,
					reqPage, pageSize, requestUriTmpl);

			// Rest Captcha
			CaptchaServlet.resetCaptcha(request);
			return new ModelAndView("smsGatewayDetails/smsGatewayDetailsList", "data", map);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}

	@RequestMapping(value = "/app/smsGatewayDetails/update", method = { RequestMethod.POST })
	public @ResponseBody ModelAndView updateSmsGatewayDetails(HttpServletRequest request,
			@ModelAttribute("smsGatewayDetailsForm") @Valid SmsGatewayDetails smsGatewayDetailsForm,
			BindingResult result, @RequestParam(value = "reqPage") Long reqPage,
			@RequestParam(value = "pageSize") Long pageSize, Model model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// Global forwarding parameters
			model.addAttribute("reqPage", reqPage);
			model.addAttribute("pageSize", pageSize);
			if (result.hasErrors()) {
				customModalAttributes(model);
				return new ModelAndView("smsGatewayDetails/updateSmsGatewayDetails",
						"smsGatewayDetailsForm", smsGatewayDetailsForm);
			} else {
				SmsGatewayDetails dbSmsGatewayDetails = DbService.getInstance().getSetupService()
						.getSmsGatewayDetailsByCtxIdAndId(ControllerUtil.getContextId(request),
								smsGatewayDetailsForm.getId());
				if (dbSmsGatewayDetails != null) {
					ObjectMapper jsonApi = new ObjectMapper();
					String oldDbObject = jsonApi.writeValueAsString(dbSmsGatewayDetails);

					this.updateContextDoProperties(request, smsGatewayDetailsForm);
					dbSmsGatewayDetails.setSenderId(smsGatewayDetailsForm.getSenderId());
					dbSmsGatewayDetails.setRoute(smsGatewayDetailsForm.getRoute());
					dbSmsGatewayDetails.setUpdatedUserId(smsGatewayDetailsForm.getUpdatedUserId());
					dbSmsGatewayDetails.setUpdatedTime(smsGatewayDetailsForm.getUpdatedTime());
					SmsGatewayDetails dbObject = (SmsGatewayDetails) DbService.getInstance().getSetupService()
							.saveOrUpdateSmsGatewayDetails(dbSmsGatewayDetails);
					this.saveAuditObject(request, oldDbObject, jsonApi.writeValueAsString(dbObject),
							AuditOperationType.UPDATE, AuditObject.SmsGatewayDetails);
					if (dbObject != null) {
						map.put("message_success", "A SmsGatewayDetails " + dbObject.getId() + " updated.");
					} else {
						map.put("message_error",
								"Updating SmsGatewayDetails " + dbSmsGatewayDetails.getId() + " failed.");
					}

					preProcessPaginatedList(request, SmsGatewayDetails.class, ControllerUtil.getContextId(request),
							map, reqPage, pageSize, requestUriTmpl);
					return new ModelAndView("smsGatewayDetails/smsGatewayDetailsList", "data", map);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		map.put("message_error", "Updating SmsGatewayDetails failed.");
		preProcessPaginatedList(request, SmsGatewayDetails.class, ControllerUtil.getContextId(request), map,
				reqPage, pageSize, requestUriTmpl);
		return new ModelAndView("smsGatewayDetails/smsGatewayDetailsList", "data", map);
	}

	@RequestMapping(value = "/app/smsGatewayDetails/view/form", method = { RequestMethod.GET })
	public @ResponseBody ModelAndView viewSmsGatewayDetailsForm(HttpServletRequest request,
			@RequestParam(value = "id") Long id, @RequestParam(value = "reqPage") Long reqPage,
			@RequestParam(value = "pageSize") Long pageSize, Model model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// Global forwarding parameters
			model.addAttribute("reqPage", reqPage);
			model.addAttribute("pageSize", pageSize);
			if (id > 0) {
				try {
					// Load the Object with context id
					SmsGatewayDetails dbSmsGatewayDetails = DbService.getInstance().getSetupService()
							.getSmsGatewayDetailsByCtxIdAndId(new Long(1), id);
					if (dbSmsGatewayDetails != null) {
						customModalAttributes(model);
						dbSmsGatewayDetails
								.setCreatedUserName(CacheService.getUserById(ControllerUtil.getContextId(request),
										dbSmsGatewayDetails.getCreatedUserId()).getDisplayName());
						User updatedUser = CacheService.getUserById(ControllerUtil.getContextId(request),
								dbSmsGatewayDetails.getUpdatedUserId());
						if (updatedUser != null) {
							dbSmsGatewayDetails.setUpdatedUserName(updatedUser.getDisplayName());
						}
						return new ModelAndView("smsGatewayDetails/viewSmsGatewayDetails",
								"smsGatewayDetailsForm", dbSmsGatewayDetails);
					}
					// Check permissions
				} catch (Exception e) {
					logger.error("Failed to view SmsGatewayDetails", e);
				}
			}

			// Return to Listing page with error
			map.put("message_error", "SmsGatewayDetails being view does not exists on the System.");
			preProcessPaginatedList(request, SmsGatewayDetails.class, ControllerUtil.getContextId(request), map,
					reqPage, pageSize, requestUriTmpl);

			// Rest Captcha
			CaptchaServlet.resetCaptcha(request);
			return new ModelAndView("smsGatewayDetails/smsGatewayDetailsList", "data", map);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
}