
package com.bytesedge.bookvenue.controller.AuditData;

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
import com.bytesedge.bookvenue.model.AuditData;
import com.bytesedge.bookvenue.model.AuditObject;
import com.bytesedge.bookvenue.model.AuditOperationType;
@Controller
@SessionAttributes({"auditDataForm"})
public class AuditDataController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(AuditDataController.class);

	private final String requestUriTmpl = "/app/auditData/list?reqPage=%d&pageSize=%d";

	@Autowired
	private AuditDataFormValidator validator;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(validator);
	}

	public AuditDataFormValidator getValidator() {
		return validator;
	}

	public void setValidator(AuditDataFormValidator validator) {
		this.validator = validator;
	}
	@Override
	public void customModalAttributes(Model model) {
		model.addAttribute("AuditOperationType", AuditOperationType.values());
		model.addAttribute("AuditObject", AuditObject.values());
	}

	@Override
	public PaginatedResponse getPaginatedListing(HttpServletRequest httpRequest, PaginatedRequest paginatedRequest, final Map<String, Object> map) throws Exception {
			return DbService.getInstance().getSetupService().getAuditDataList( ControllerUtil.getContextId(httpRequest), paginatedRequest);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void preProcessPaginatedList(HttpServletRequest request, final Class<?> clazz, final Long ctxId, 
			final Map<String, Object> map, final Long reqPage, 
			final Long pageSize, final String requestUriTmpl) throws Exception {
		processPaginatedList(AuditData.class, request, map, reqPage, pageSize, requestUriTmpl);
		PaginatedResponse paginatedResponse = (PaginatedResponse) map.get("paginatedResponse");
		if(paginatedResponse != null) {
			List<AuditData> list = (List<AuditData>) paginatedResponse.getResultList();
			if(list != null && !list.isEmpty()) {
				for(AuditData auditData : list) {
					auditData.setCreatedUserName(CacheService.getUserById(ControllerUtil.getContextId(request), auditData.getCreatedUserId()).getDisplayName());
				}
			}
		}
	}

	@RequestMapping(value = "/app/auditData/list", method = {RequestMethod.GET})
	public @ResponseBody ModelAndView auditDataList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="reqPage", required=false) Long reqPage, @RequestParam(value="pageSize", 
			required=false) Long pageSize, Model model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			preProcessPaginatedList(request, AuditData.class, ControllerUtil.getContextId(request), map, reqPage, pageSize, requestUriTmpl);
			return new ModelAndView("auditData/auditDataList", "data", map);
		}  catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@RequestMapping(value = "/app/auditData/list/export", method = {RequestMethod.GET}, produces = "application/xlsx")
	public @ResponseBody ModelAndView auditDataListExportXlsx(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="reqPage", required=false) Long reqPage, @RequestParam(value="pageSize", required=false) Long pageSize,
			@RequestParam(value="exportType", required=false) String exportType) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			PaginatedResponse res = processPaginatedList(AuditData.class, request, map, reqPage, pageSize, requestUriTmpl);
			if(res != null) {
				map.put("list", res.getResultList());
				map.put("viewClass",  AuditDataXlsxStreamingView.class.getName());
				map.put("fileName", "Shilparamam-AuditData-List.xlsx");
				return new ModelAndView("xlxs", "map", map);
			} else {
				map.put("message_error", "Failed to create MS Excel file for auditData  report.");
				return new ModelAndView("auditData/auditDataList", "data", map);
			}
		}  catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}

	@RequestMapping(value = "/app/auditData/download/pdf", method = {RequestMethod.GET}, produces = "application/pdf")
	public @ResponseBody ModelAndView auditDataDownloadPdf(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="id", required=true) Long id,Model model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			AuditData dbAuditData = DbService.getInstance().getSetupService().getAuditDataByIdAndCtxId(id,
					ControllerUtil.getContextId(request));
			if(dbAuditData != null) {
				map.put("obj", dbAuditData);
				map.put("viewClass", AuditDataPdfStreamingView.class.getName());
				map.put("fileName", "Shilparamam-AuditData-" + id + ".pdf");
				map.put("tmplName", "/WEB-INF/res/tmpl/report/auditData_view.pdf");
				customModalAttributes(model);
				return new ModelAndView("pdf", "map", map);
			} else {
				map.put("message_error", "Failed to create PDF file for AuditData " + id);
				preProcessPaginatedList(request, AuditData.class, ControllerUtil.getContextId(request), map, requestUriTmpl);
				return new ModelAndView("auditData/auditDataList", "data", map);
			}
		}  catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}

	@RequestMapping(value = "/app/auditData/view/form", method = {RequestMethod.GET})
	public @ResponseBody ModelAndView viewAuditDataForm(HttpServletRequest request,
			@RequestParam(value="id") Long id,@RequestParam(value="reqPage") Long reqPage,
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
					AuditData dbAuditData = DbService.getInstance().getSetupService().getAuditDataByIdAndCtxId(id, ControllerUtil.getContextId(request));
					if(dbAuditData != null) {
						dbAuditData.setCreatedUserName(CacheService.getUserById(ControllerUtil.getContextId(request), dbAuditData.getCreatedUserId()).getDisplayName());
						customModalAttributes(model);
						return new ModelAndView("auditData/viewAuditData", "auditDataForm", dbAuditData);
					}
					// Check permissions
				} catch (Exception e) {
					logger.error("Failed to view AuditData", e);
				}
			}
			// Return to Listing page with error
			map.put("message_error", "AuditData being viewed does not exists on the System.");
			preProcessPaginatedList(request, AuditData.class, ControllerUtil.getContextId(request), map,reqPage, pageSize, requestUriTmpl);
			// Rest Captcha
			CaptchaServlet.resetCaptcha(request);
			customModalAttributes(model);
			return new ModelAndView("auditData/auditDataList", "data", map);
		}  catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}

}