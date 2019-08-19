package com.bytesedge.bookvenue.controller.totalStatus;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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
import com.bytesedge.bookvenue.model.TotalStatus;
import com.bytesedge.bookvenue.model.User;

@Controller
@SessionAttributes({"totalStatusForm"})
public class TotalStatusController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(TotalStatusController.class);

	private final String requestUriTmpl = "/app/totalStatus/list?reqPage=%d&pageSize=%d";

	@Autowired
	private TotalStatusFormValidator validator;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(validator);
	}

	public TotalStatusFormValidator getValidator() {
		return validator;
	}

	public void setValidator(TotalStatusFormValidator validator) {
		this.validator = validator;
	}

	@Override
	public void customModalAttributes(Model model) {
		model.addAttribute("AuditOperationType", AuditOperationType.values());
		model.addAttribute("AuditObject", AuditObject.values());
	}

	@Override
	public PaginatedResponse getPaginatedListing(HttpServletRequest httpRequest, PaginatedRequest paginatedRequest, final Map<String, Object> map) throws Exception {
		return DbService.getInstance().getSetupService().getTotalStatusList( ControllerUtil.getContextId(httpRequest), paginatedRequest);
	}
	@SuppressWarnings("unchecked")
	@Override
	public void preProcessPaginatedList(HttpServletRequest request, Class<?> clazz,
			Long ctxId, Map<String, Object> map, Long reqPage, Long pageSize, String requestUriTmpl) throws Exception {
		processPaginatedList(clazz, request, map, reqPage, pageSize, requestUriTmpl);
		PaginatedResponse paginatedResponse = (PaginatedResponse) map.get("paginatedResponse");
		if (paginatedResponse != null) {
			List<TotalStatus> list = (List<TotalStatus>) paginatedResponse.getResultList();
			if (list != null && !list.isEmpty()) {
				for (TotalStatus totalStatus : list) {
				}
			}
		}
	}

	@RequestMapping(value = "/app/totalStatus/list/export", method = {RequestMethod.GET}, produces = "application/xlsx")
	public @ResponseBody ModelAndView totalStatusListExportXlsx(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="reqPage", required=false) Long reqPage, @RequestParam(value="pageSize", required=false) Long pageSize,
			@RequestParam(value="exportType", required=false) String exportType) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			PaginatedResponse res = processPaginatedList(TotalStatus.class, request, map, reqPage, pageSize, requestUriTmpl);
			if(res != null) {
				map.put("list", res.getResultList());
				map.put("viewClass",  TotalStatusXlsxStreamingView.class.getName());
				map.put("fileName", "Shilparamam-Total-Booking-List.xlsx");
				return new ModelAndView("xlxs", "map", map);
			} else {
				map.put("message_error", "Failed to create MS Excel file for TotalStatus data report.");
				return new ModelAndView("totalStatus/totalStatusList", "data", map);
			}
		}  catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@RequestMapping(value = "/app/totalStatus/download/pdf", method = {RequestMethod.GET}, produces = "application/pdf")
	public @ResponseBody ModelAndView totalStatusDownloadPdf(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="id", required=true) Long id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			TotalStatus dbTotalStatus = DbService.getInstance().getSetupService().getTotalStatusByCtxIdAndId(ControllerUtil.getContextId(request), id);
			if(dbTotalStatus != null) {
				map.put("obj", dbTotalStatus);
				map.put("viewClass", TotalStatusPdfStreamingView.class.getName());
				map.put("fileName", "Shilparamam-TotalStatus-" + id + ".pdf");
				map.put("tmplName", "/WEB-INF/res/tmpl/report/totalStatus_view.pdf");
				return new ModelAndView("pdf", "map", map);
			} else {
				map.put("message_error", "Failed to create PDF file for Management Booking " + id);
				preProcessPaginatedList(request, TotalStatus.class, ControllerUtil.getContextId(request), map, requestUriTmpl);
				return new ModelAndView("totalStatus/totalStatusList", "data", map);
			}
		}  catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}

	
	@RequestMapping(value = "/app/totalStatus/list", method = {RequestMethod.GET})
	public @ResponseBody ModelAndView  totalStatusList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="reqPage", required=false) Long reqPage, @RequestParam(value="pageSize", 
			required=false) Long pageSize) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			processPaginatedList(TotalStatus.class, request, map, reqPage, pageSize, requestUriTmpl);
			return new ModelAndView("totalStatus/totalStatusList", "data", map);
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
	

	@RequestMapping(value = "/app/totalStatus/view/form", method = {RequestMethod.GET})
	public @ResponseBody ModelAndView viewTotalStatusFormForm(HttpServletRequest request,
			@RequestParam(value="id") Long id,
			@RequestParam(value="reqPage") Long reqPage,
			@RequestParam(value="pageSize") Long pageSize,Model model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if(id > 0) {
				model.addAttribute("reqPage", reqPage);
				model.addAttribute("pageSize", pageSize);
				try {
					customModalAttributes(model);
					// Load the Object with context id
					TotalStatus dbTotalStatus = DbService.getInstance().getSetupService().getTotalStatusByCtxIdAndId(ControllerUtil.getContextId(request), id);
					if(dbTotalStatus != null) {
						dbTotalStatus.setCreatedUserName(CacheService.getUserById(ControllerUtil.getContextId(request), dbTotalStatus.getCreatedUserId()).getDisplayName());
						User updatedUser = CacheService.getUserById(ControllerUtil.getContextId(request), dbTotalStatus.getUpdatedUserId());
						if(updatedUser != null) {
							dbTotalStatus.setUpdatedUserName(updatedUser.getDisplayName());
						}
					}

					// Rest Captcha
					CaptchaServlet.resetCaptcha(request);
					return new ModelAndView("totalStatus/viewTotalStatus", "totalStatusForm", dbTotalStatus);
				}
				catch (Exception e) {
					logger.error("Failed to view Booking ", e);
				}

			}

			// Return to Listing page with error
			map.put("message_error", "Management Boking  being view does not exists on the System.");
			preProcessPaginatedList(request, TotalStatus.class, ControllerUtil.getContextId(request), map, reqPage, pageSize,requestUriTmpl);
			return new ModelAndView("totalStatus/totalStatusList", "data", map);
		}  catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}

	
}