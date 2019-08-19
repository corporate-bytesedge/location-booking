package com.bytesedge.bookvenue.controller.RentPurpose;

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
import com.bytesedge.bookvenue.model.RentPurpose;
import com.bytesedge.bookvenue.model.SessionOrgContext;
import com.bytesedge.bookvenue.model.StateType;
import com.bytesedge.bookvenue.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@SessionAttributes({ "rentPurposeForm" })
public class RentPurposeController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(RentPurposeController.class);

	private final String requestUriTmpl = "/app/rentPurpose/list?reqPage=%d&pageSize=%d";

	@Autowired
	private RentPurposeFormValidator validator;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(validator);
	}

	public RentPurposeFormValidator getValidator() {
		return validator;
	}

	public void setValidator(RentPurposeFormValidator validator) {
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
		processPaginatedList(RentPurpose.class, request, map, reqPage, pageSize, requestUriTmpl);
		PaginatedResponse paginatedResponse = (PaginatedResponse) map.get("paginatedResponse");
		if (paginatedResponse != null) {
			List<RentPurpose> list = (List<RentPurpose>) paginatedResponse.getResultList();
			if (list != null && !list.isEmpty()) {
				for (RentPurpose rentPurpose : list) {
					rentPurpose.setDescr(rentPurpose.getDescr().substring(0,
							rentPurpose.getDescr().length() > 24 ? 24 : rentPurpose.getDescr().length()) + "...");
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
		return DbService.getInstance().getSetupService().getRentPurposeList(ControllerUtil.getContextId(httpRequest),
				paginatedRequest);
	}

	@RequestMapping(value = "/app/rentPurpose/list", method = { RequestMethod.GET })
	public @ResponseBody ModelAndView rentPurposeList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "reqPage", required = false) Long reqPage,
			@RequestParam(value = "pageSize", required = false) Long pageSize, Model model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			SessionOrgContext se = ControllerUtil.getValidSessionContext(request);
			if (se != null && se.getOrgId() != null && se.getOrgId() > 0L) {
				preProcessPaginatedList(request, RentPurpose.class, ControllerUtil.getContextId(request), map, reqPage,
						pageSize, requestUriTmpl);
				return new ModelAndView("rentPurpose/rentPurposeList", "data", map);
			} else {
				return new ModelAndView("common/selectOrg", "orgType", "Organisation");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}

	@RequestMapping(value = "/app/rentPurpose/list/export", method = {
			RequestMethod.GET }, produces = "application/xlsx")
	public @ResponseBody ModelAndView rentPurposeListExportXlsx(HttpServletRequest request,
			HttpServletResponse response, @RequestParam(value = "reqPage", required = false) Long reqPage,
			@RequestParam(value = "pageSize", required = false) Long pageSize,
			@RequestParam(value = "exportType", required = false) String exportType) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			PaginatedResponse res = processPaginatedList(RentPurpose.class, request, map, reqPage, pageSize,
					requestUriTmpl);
			if (res != null) {
				map.put("list", res.getResultList());
				map.put("viewClass", RentPurposeXlsxStreamingView.class.getName());
				map.put("fileName", "Shilparamam-RentPurpose-List.xlsx");
				return new ModelAndView("xlxs", "map", map);
			} else {
				map.put("message_error", "Failed to create MS Excel file for RentPurpose report.");
				return new ModelAndView("rentPurpose/rentPurposeList", "data", map);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}

	@RequestMapping(value = "/app/rentPurpose/download/pdf", method = {
			RequestMethod.GET }, produces = "application/pdf")
	public @ResponseBody ModelAndView rentPurposeDownloadPdf(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "id", required = true) Long id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			RentPurpose dbRentPurpose = DbService.getInstance().getSetupService()
					.getRentPurposeByCtxIdAndId(ControllerUtil.getContextId(request), id);
			if (dbRentPurpose != null) {
				map.put("obj", dbRentPurpose);
				map.put("viewClass", RentPurposePdfStreamingView.class.getName());
				map.put("fileName", "Shilparamam-RentPurpose-" + id + ".pdf");
				map.put("tmplName", "/WEB-INF/res/tmpl/report/rentPurpose_view.pdf");
				return new ModelAndView("pdf", "map", map);
			} else {
				map.put("message_error", "Failed to create PDF file for RentPurpose " + id);
				preProcessPaginatedList(request, RentPurpose.class, ControllerUtil.getContextId(request), map,
						requestUriTmpl);
				return new ModelAndView("rentPurpose/rentPurposeList", "data", map);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}

	@RequestMapping(value = "/app/rentPurpose/create/form", method = { RequestMethod.GET })
	public @ResponseBody ModelAndView createRentPurposeForm(HttpServletRequest request, Model model) throws Exception {
		try {
			RentPurpose rentPurposeForm = new RentPurpose();

			// Rest Captcha
			CaptchaServlet.resetCaptcha(request);
			customModalAttributes(model);
			return new ModelAndView("rentPurpose/createRentPurpose", "rentPurposeForm", rentPurposeForm);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		// Incase of erros Return to Lising Page
		Map<String, Object> map = new HashMap<String, Object>();
		preProcessPaginatedList(request, RentPurpose.class, ControllerUtil.getContextId(request), map, requestUriTmpl);
		return new ModelAndView("rentPurpose/rentPurposeList", "data", map);
	}

	@RequestMapping(value = "/app/rentPurpose/create", method = { RequestMethod.POST })
	public @ResponseBody ModelAndView createRentPurpose(HttpServletRequest request,
			@ModelAttribute("rentPurposeForm") @Valid RentPurpose rentPurposeForm, BindingResult result, Model model)
					throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (result.hasErrors()) {
				customModalAttributes(model);
				return new ModelAndView("rentPurpose/createRentPurpose", "rentPurposeForm", rentPurposeForm);
			} else {
				this.updateOrgDoProperties(request, rentPurposeForm);
				RentPurpose dbObject = (RentPurpose) DbService.getInstance().getSetupService()
						.saveOrUpdateRentPurpose(rentPurposeForm);

				ObjectMapper jsonApi = new ObjectMapper();
				this.saveAuditObject(request, null, jsonApi.writeValueAsString(dbObject), AuditOperationType.CREATE,
						AuditObject.RentPurpose);

				preProcessPaginatedList(request, RentPurpose.class, ControllerUtil.getContextId(request), map,
						requestUriTmpl);
				map.put("message_success", "A new RentPurpose " + dbObject.getId() + " has been created.");
				return new ModelAndView("rentPurpose/rentPurposeList", "data", map);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			// In case of errors Return to Listing Page
			map.put("message_error", "Failed to create a new RentPurpose.");
			preProcessPaginatedList(request, RentPurpose.class, ControllerUtil.getContextId(request), map,
					requestUriTmpl);
			return new ModelAndView("rentPurpose/rentPurposeList", "data", map);
		}
	}

	@RequestMapping(value = "/app/rentPurpose/update/form", method = { RequestMethod.GET })
	public @ResponseBody ModelAndView updateRentPurposeForm(HttpServletRequest request,
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
					RentPurpose dbRentPurpose = DbService.getInstance().getSetupService()
							.getRentPurposeByCtxIdAndId(new Long(1), id);
					if (dbRentPurpose != null) {
						customModalAttributes(model);
						return new ModelAndView("rentPurpose/updateRentPurpose", "rentPurposeForm", dbRentPurpose);
					}
					// Check permissions
				} catch (Exception e) {
					logger.error("Failed to delete RentPurpose", e);
				}
			}

			// Return to Listing page with error
			map.put("message_error", "RentPurpose being updated does not exists on the System.");
			preProcessPaginatedList(request, RentPurpose.class, ControllerUtil.getContextId(request), map, reqPage,
					pageSize, requestUriTmpl);

			// Rest Captcha
			CaptchaServlet.resetCaptcha(request);
			return new ModelAndView("rentPurpose/rentPurposeList", "data", map);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}

	@RequestMapping(value = "/app/rentPurpose/update", method = { RequestMethod.POST })
	public @ResponseBody ModelAndView updateRentPurpose(HttpServletRequest request,
			@ModelAttribute("rentPurposeForm") @Valid RentPurpose rentPurposeForm, BindingResult result,
			@RequestParam(value = "reqPage") Long reqPage, @RequestParam(value = "pageSize") Long pageSize, Model model)
					throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// Global forwarding parameters
			model.addAttribute("reqPage", reqPage);
			model.addAttribute("pageSize", pageSize);
			if (result.hasErrors()) {
				customModalAttributes(model);
				return new ModelAndView("rentPurpose/updateRentPurpose", "rentPurposeForm", rentPurposeForm);
			} else {
				RentPurpose dbRentPurpose = DbService.getInstance().getSetupService()
						.getRentPurposeByCtxIdAndId(ControllerUtil.getContextId(request), rentPurposeForm.getId());
				if (dbRentPurpose != null) {
					ObjectMapper jsonApi = new ObjectMapper();
					String oldDbObject = jsonApi.writeValueAsString(dbRentPurpose);

					this.updateContextDoProperties(request, rentPurposeForm);
					dbRentPurpose.setDescr(rentPurposeForm.getDescr());
					dbRentPurpose.setName(rentPurposeForm.getName());
					dbRentPurpose.setState(rentPurposeForm.getState());
					dbRentPurpose.setUpdatedUserId(rentPurposeForm.getUpdatedUserId());
					dbRentPurpose.setUpdatedTime(rentPurposeForm.getUpdatedTime());
					RentPurpose dbObject = (RentPurpose) DbService.getInstance().getSetupService()
							.saveOrUpdateRentPurpose(dbRentPurpose);
					this.saveAuditObject(request, oldDbObject, jsonApi.writeValueAsString(dbObject),
							AuditOperationType.UPDATE, AuditObject.RentPurpose);
					if (dbObject != null) {
						map.put("message_success", "A RentPurpose " + dbObject.getId() + " updated.");
					} else {
						map.put("message_error", "Updating RentPurpose " + dbRentPurpose.getId() + " failed.");
					}

					preProcessPaginatedList(request, RentPurpose.class, ControllerUtil.getContextId(request), map,
							reqPage, pageSize, requestUriTmpl);
					return new ModelAndView("rentPurpose/rentPurposeList", "data", map);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		map.put("message_error", "Updating RentPurpose failed.");
		preProcessPaginatedList(request, RentPurpose.class, ControllerUtil.getContextId(request), map, reqPage,
				pageSize, requestUriTmpl);
		return new ModelAndView("rentPurpose/rentPurposeList", "data", map);
	}

	@RequestMapping(value = "/app/rentPurpose/view/form", method = { RequestMethod.GET })
	public @ResponseBody ModelAndView viewUserForm(HttpServletRequest request, @RequestParam(value = "id") Long id,
			@RequestParam(value = "reqPage") Long reqPage, @RequestParam(value = "pageSize") Long pageSize, Model model)
					throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// Global forwarding parameters
			model.addAttribute("reqPage", reqPage);
			model.addAttribute("pageSize", pageSize);

			if (id > 0) {
				try {
					// Load the Object with context id
					RentPurpose dbRentPurpose = DbService.getInstance().getSetupService()
							.getRentPurposeByCtxIdAndId(new Long(1), id);
					if (dbRentPurpose != null) {
						dbRentPurpose.setCreatedUsername(CacheService
								.getUserById(ControllerUtil.getContextId(request), dbRentPurpose.getCreatedUserId())
								.getDisplayName());
						dbRentPurpose.setOrgName(CacheService
								.getOrganizationById(ControllerUtil.getContextId(request), dbRentPurpose.getOrgId())
								.getName());
						User updatedUser = CacheService.getUserById(ControllerUtil.getContextId(request), dbRentPurpose.getUpdatedUserId());
						if(updatedUser != null) {
							dbRentPurpose.setUpdatedUsername(updatedUser.getDisplayName());
						}

					} // Rest Captcha
					CaptchaServlet.resetCaptcha(request);
					return new ModelAndView("rentPurpose/viewRentPurpose", "rentPurposeForm", dbRentPurpose);
				} // Check permissions
				catch (Exception e) {
					logger.error("Failed to view Rent purpose", e);
				}
			}

			// Return to Listing page with error
			map.put("message_error", "Rent purpose being view does not exists on the System.");
			preProcessPaginatedList(request, RentPurpose.class, ControllerUtil.getContextId(request), map, reqPage,
					pageSize, requestUriTmpl);
			return new ModelAndView("rentPurpose/rentPurposeList", "data", map);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
}
