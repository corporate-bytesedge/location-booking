package com.bytesedge.bookvenue.controller.paymentGatewayDetails;

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
import com.bytesedge.bookvenue.model.PaymentGatewayDetails;
import com.bytesedge.bookvenue.model.StateType;
import com.bytesedge.bookvenue.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@SessionAttributes({ "paymentGatewayDetailsForm" })
public class PaymentGatewayDetailsController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(PaymentGatewayDetailsController.class);

	private final String requestUriTmpl = "/app/paymentGatewayDetails/list?reqPage=%d&pageSize=%d";

	@Autowired
	private PaymentGatewayDetailsFormValidator validator;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(validator);
	}

	public PaymentGatewayDetailsFormValidator getValidator() {
		return validator;
	}

	public void setValidator(PaymentGatewayDetailsFormValidator validator) {
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
		processPaginatedList(PaymentGatewayDetails.class, request, map, reqPage, pageSize, requestUriTmpl);
		PaginatedResponse paginatedResponse = (PaginatedResponse) map.get("paginatedResponse");
		if (paginatedResponse != null) {
			List<PaymentGatewayDetails> list = (List<PaymentGatewayDetails>) paginatedResponse.getResultList();
			if (list != null && !list.isEmpty()) {
				for (PaymentGatewayDetails paymentGatewayDetails : list) {
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
				.getPaymentGatewayDetailsList(ControllerUtil.getContextId(httpRequest), paginatedRequest);
	}

	@RequestMapping(value = "/app/paymentGatewayDetails/list", method = { RequestMethod.GET })
	public @ResponseBody ModelAndView paymentGatewayDetailsList(HttpServletRequest request,
			HttpServletResponse response, @RequestParam(value = "reqPage", required = false) Long reqPage,
			@RequestParam(value = "pageSize", required = false) Long pageSize, Model model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			customModalAttributes(model);
			preProcessPaginatedList(request, PaymentGatewayDetails.class, ControllerUtil.getContextId(request), map,
					reqPage, pageSize, requestUriTmpl);
			return new ModelAndView("paymentGatewayDetails/paymentGatewayDetailsList", "data", map);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}

	@RequestMapping(value = "/app/paymentGatewayDetails/list/export", method = {
			RequestMethod.GET }, produces = "application/xlsx")
	public @ResponseBody ModelAndView paymentGatewayDetailsListExportXlsx(HttpServletRequest request,
			HttpServletResponse response, @RequestParam(value = "reqPage", required = false) Long reqPage,
			@RequestParam(value = "pageSize", required = false) Long pageSize,
			@RequestParam(value = "exportType", required = false) String exportType) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			PaginatedResponse res = processPaginatedList(PaymentGatewayDetails.class, request, map, reqPage, pageSize,
					requestUriTmpl);
			if (res != null) {
				map.put("list", res.getResultList());
				map.put("viewClass", PaymentGatewayDetailsXlsxStreamingView.class.getName());
				map.put("fileName", "Shilparamam-PaymentGatewayDetails-List.xlsx");
				return new ModelAndView("xlxs", "map", map);
			} else {
				map.put("message_error", "Failed to create MS Excel file for PaymentGatewayDetails report.");
				return new ModelAndView("paymentGatewayDetails/paymentGatewayDetailsList", "data", map);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}

	@RequestMapping(value = "/app/paymentGatewayDetails/download/pdf", method = {
			RequestMethod.GET }, produces = "application/pdf")
	public @ResponseBody ModelAndView paymentGatewayDetailsDownloadPdf(HttpServletRequest request,
			HttpServletResponse response, @RequestParam(value = "id", required = true) Long id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			PaymentGatewayDetails dbPaymentGatewayDetails = DbService.getInstance().getSetupService()
					.getPaymentGatewayDetailsByCtxIdAndId(ControllerUtil.getContextId(request), id);
			if (dbPaymentGatewayDetails != null) {
				map.put("obj", dbPaymentGatewayDetails);
				map.put("viewClass", PaymentGatewayDetailsPdfStreamingView.class.getName());
				map.put("fileName", "Shilparamam-PaymentGatewayDetails-" + id + ".pdf");
				map.put("tmplName", "/WEB-INF/res/tmpl/report/PaymentGatewayDetails_view.pdf");
				return new ModelAndView("pdf", "map", map);
			} else {
				map.put("message_error", "Failed to create PDF file for PaymentGatewayDetails " + id);
				preProcessPaginatedList(request, PaymentGatewayDetails.class, ControllerUtil.getContextId(request), map,
						requestUriTmpl);
				return new ModelAndView("paymentGatewayDetails/paymentGatewayDetailsList", "data", map);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}

	@RequestMapping(value = "/app/paymentGatewayDetails/create/form", method = { RequestMethod.GET })
	public @ResponseBody ModelAndView createPaymentGatewayDetailsForm(HttpServletRequest request, Model model)
			throws Exception {
		try {
			PaymentGatewayDetails paymentGatewayDetailsForm = new PaymentGatewayDetails();

			// Rest Captcha
			CaptchaServlet.resetCaptcha(request);
			customModalAttributes(model);
			return new ModelAndView("paymentGatewayDetails/createPaymentGatewayDetails", "paymentGatewayDetailsForm",
					paymentGatewayDetailsForm);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		// Incase of erros Return to Lising Page
		Map<String, Object> map = new HashMap<String, Object>();
		preProcessPaginatedList(request, PaymentGatewayDetails.class, ControllerUtil.getContextId(request), map,
				requestUriTmpl);
		return new ModelAndView("paymentGatewayDetails/paymentGatewayDetailsList", "data", map);
	}

	@RequestMapping(value = "/app/paymentGatewayDetails/create", method = { RequestMethod.POST })
	public @ResponseBody ModelAndView createPaymentGatewayDetails(HttpServletRequest request,
			@ModelAttribute("paymentGatewayDetailsForm") @Valid PaymentGatewayDetails paymentGatewayDetailsForm,
			BindingResult result, Model model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (result.hasErrors()) {
				customModalAttributes(model);
				return new ModelAndView("paymentGatewayDetails/createPaymentGatewayDetails",
						"paymentGatewayDetailsForm", paymentGatewayDetailsForm);
			} else {
				this.updateContextDoProperties(request, paymentGatewayDetailsForm);
				Context context = CacheService.getContextByUrl(request.getServerName());
				if (context != null) {
					Organization org = CacheService.getOrgByCtxId(context.getId());
					if (org != null) {
						paymentGatewayDetailsForm.setOrgId(org.getId());
					}
				}
				PaymentGatewayDetails dbObject = (PaymentGatewayDetails) DbService.getInstance().getSetupService()
						.saveOrUpdatePaymentGatewayDetails(paymentGatewayDetailsForm);
				ObjectMapper jsonApi = new ObjectMapper();
				this.saveAuditObject(request, null, jsonApi.writeValueAsString(dbObject), AuditOperationType.CREATE,
						AuditObject.PaymentGatewayDetails);
				preProcessPaginatedList(request, PaymentGatewayDetails.class, ControllerUtil.getContextId(request), map,
						requestUriTmpl);
				map.put("message_success", "A new PaymentGatewayDetails " + dbObject.getId() + " has been created.");
				return new ModelAndView("paymentGatewayDetails/paymentGatewayDetailsList", "data", map);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			// In case of errors Return to Listing Page
			map.put("message_error", "Failed to create a new PaymentGatewayDetails.");
			preProcessPaginatedList(request, PaymentGatewayDetails.class, ControllerUtil.getContextId(request), map,
					requestUriTmpl);
			return new ModelAndView("paymentGatewayDetails/paymentGatewayDetailsList", "data", map);
		}
	}

	@RequestMapping(value = "/app/paymentGatewayDetails/update/form", method = { RequestMethod.GET })
	public @ResponseBody ModelAndView updatePaymentGatewayDetailsForm(HttpServletRequest request,
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
					PaymentGatewayDetails dbPaymentGatewayDetails = DbService.getInstance().getSetupService()
							.getPaymentGatewayDetailsByCtxIdAndId(new Long(1), id);
					if (dbPaymentGatewayDetails != null) {
						customModalAttributes(model);
						return new ModelAndView("paymentGatewayDetails/updatePaymentGatewayDetails",
								"paymentGatewayDetailsForm", dbPaymentGatewayDetails);
					}
					// Check permissions
				} catch (Exception e) {
					logger.error("Failed to delete PaymentGatewayDetails", e);
				}
			}

			// Return to Listing page with error
			map.put("message_error", "PaymentGatewayDetails being updated does not exists on the System.");
			preProcessPaginatedList(request, PaymentGatewayDetails.class, ControllerUtil.getContextId(request), map,
					reqPage, pageSize, requestUriTmpl);

			// Rest Captcha
			CaptchaServlet.resetCaptcha(request);
			return new ModelAndView("paymentGatewayDetails/paymentGatewayDetailsList", "data", map);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}

	@RequestMapping(value = "/app/paymentGatewayDetails/update", method = { RequestMethod.POST })
	public @ResponseBody ModelAndView updatePaymentGatewayDetails(HttpServletRequest request,
			@ModelAttribute("paymentGatewayDetailsForm") @Valid PaymentGatewayDetails paymentGatewayDetailsForm,
			BindingResult result, @RequestParam(value = "reqPage") Long reqPage,
			@RequestParam(value = "pageSize") Long pageSize, Model model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// Global forwarding parameters
			model.addAttribute("reqPage", reqPage);
			model.addAttribute("pageSize", pageSize);
			if (result.hasErrors()) {
				customModalAttributes(model);
				return new ModelAndView("paymentGatewayDetails/updatePaymentGatewayDetails",
						"paymentGatewayDetailsForm", paymentGatewayDetailsForm);
			} else {
				PaymentGatewayDetails dbPaymentGatewayDetails = DbService.getInstance().getSetupService()
						.getPaymentGatewayDetailsByCtxIdAndId(ControllerUtil.getContextId(request),
								paymentGatewayDetailsForm.getId());
				if (dbPaymentGatewayDetails != null) {
					ObjectMapper jsonApi = new ObjectMapper();
					String oldDbObject = jsonApi.writeValueAsString(dbPaymentGatewayDetails);

					this.updateContextDoProperties(request, paymentGatewayDetailsForm);
					dbPaymentGatewayDetails.setStatus(paymentGatewayDetailsForm.getStatus());
					dbPaymentGatewayDetails.setLogin(paymentGatewayDetailsForm.getLogin());
					dbPaymentGatewayDetails.setPassword(paymentGatewayDetailsForm.getPassword());
					dbPaymentGatewayDetails.setReqHashKey(paymentGatewayDetailsForm.getReqHashKey());
					dbPaymentGatewayDetails.setResHashKey(paymentGatewayDetailsForm.getResHashKey());
					dbPaymentGatewayDetails.setTxnCurr(paymentGatewayDetailsForm.getTxnCurr());
					dbPaymentGatewayDetails.setTtype(paymentGatewayDetailsForm.getTtype());
					dbPaymentGatewayDetails.setProdId(paymentGatewayDetailsForm.getProdId());
					dbPaymentGatewayDetails.setMerchantUrl(paymentGatewayDetailsForm.getMerchantUrl());
					dbPaymentGatewayDetails.setUpdatedUserId(paymentGatewayDetailsForm.getUpdatedUserId());
					dbPaymentGatewayDetails.setUpdatedTime(paymentGatewayDetailsForm.getUpdatedTime());
					PaymentGatewayDetails dbObject = (PaymentGatewayDetails) DbService.getInstance().getSetupService()
							.saveOrUpdatePaymentGatewayDetails(dbPaymentGatewayDetails);
					this.saveAuditObject(request, oldDbObject, jsonApi.writeValueAsString(dbObject),
							AuditOperationType.UPDATE, AuditObject.PaymentGatewayDetails);
					if (dbObject != null) {
						map.put("message_success", "A PaymentGatewayDetails " + dbObject.getId() + " updated.");
					} else {
						map.put("message_error",
								"Updating PaymentGatewayDetails " + dbPaymentGatewayDetails.getId() + " failed.");
					}

					preProcessPaginatedList(request, PaymentGatewayDetails.class, ControllerUtil.getContextId(request),
							map, reqPage, pageSize, requestUriTmpl);
					return new ModelAndView("paymentGatewayDetails/paymentGatewayDetailsList", "data", map);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		map.put("message_error", "Updating PaymentGatewayDetails failed.");
		preProcessPaginatedList(request, PaymentGatewayDetails.class, ControllerUtil.
				getContextId(request), map,
				reqPage, pageSize, requestUriTmpl);
		return new ModelAndView("paymentGatewayDetails/paymentGatewayDetailsList", "data", map);
	}

	@RequestMapping(value = "/app/paymentGatewayDetails/view/form", method = { RequestMethod.GET })
	public @ResponseBody ModelAndView viewPaymentGatewayDetailsForm(HttpServletRequest request,
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
					PaymentGatewayDetails dbPaymentGatewayDetails = DbService.getInstance().getSetupService()
							.getPaymentGatewayDetailsByCtxIdAndId(new Long(1), id);
					if (dbPaymentGatewayDetails != null) {
						customModalAttributes(model);
						dbPaymentGatewayDetails
								.setOrgName(CacheService.getOrganizationById(ControllerUtil.getContextId(request),
										dbPaymentGatewayDetails.getOrgId()).getName());
						dbPaymentGatewayDetails
								.setCreatedUserName(CacheService.getUserById(ControllerUtil.getContextId(request),
										dbPaymentGatewayDetails.getCreatedUserId()).getDisplayName());
						User updatedUser = CacheService.getUserById(ControllerUtil.getContextId(request),
								dbPaymentGatewayDetails.getUpdatedUserId());
						if (updatedUser != null) {
							dbPaymentGatewayDetails.setUpdatedUserName(updatedUser.getDisplayName());
						}
						return new ModelAndView("paymentGatewayDetails/viewPaymentGatewayDetails",
								"paymentGatewayDetailsForm", dbPaymentGatewayDetails);
					}
					// Check permissions
				} catch (Exception e) {
					logger.error("Failed to view PaymentGatewayDetails", e);
				}
			}

			// Return to Listing page with error
			map.put("message_error", "PaymentGatewayDetails being view does not exists on the System.");
			preProcessPaginatedList(request, PaymentGatewayDetails.class, ControllerUtil.getContextId(request), map,
					reqPage, pageSize, requestUriTmpl);

			// Rest Captcha
			CaptchaServlet.resetCaptcha(request);
			return new ModelAndView("paymentGatewayDetails/paymentGatewayDetailsList", "data", map);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
}