package com.bytesedge.bookvenue.controller.Bill;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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
import com.bytesedge.bookvenue.controller.Invoice.InvoiceXlsxStreamingView;
import com.bytesedge.bookvenue.model.AuditObject;
import com.bytesedge.bookvenue.model.AuditOperationType;
import com.bytesedge.bookvenue.model.Bill;
import com.bytesedge.bookvenue.model.EndUser;
import com.bytesedge.bookvenue.model.IdProofType;
import com.bytesedge.bookvenue.model.PaymentMode;
import com.bytesedge.bookvenue.model.SessionOrgContext;
import com.bytesedge.bookvenue.model.TransactionState;
import com.bytesedge.bookvenue.model.User;

@Controller
@SessionAttributes({ "billForm" })
public class BillController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(BillController.class);

	private final String requestUriTmpl = "/app/bill/list?reqPage=%d&pageSize=%d";

	@Override
	public void customModalAttributes(Model model) {
		model.addAttribute("AuditOperationType", AuditOperationType.values());
		model.addAttribute("AuditObject", AuditObject.values());
		model.addAttribute("PaymentMode", PaymentMode.values());
		model.addAttribute("TxnStat", TransactionState.values());
		model.addAttribute("IdProofType", IdProofType.values());
	}

	@SuppressWarnings("unchecked")
	public void preProcessPaginatedList(HttpServletRequest request, final Class<?> clazz, final Long ctxId,
			final Map<String, Object> map, final Long reqPage, final Long pageSize, final String requestUriTmpl)
					throws Exception {
		processPaginatedList(EndUser.class, request, map, reqPage, pageSize, requestUriTmpl);
		PaginatedResponse paginatedResponse = (PaginatedResponse) map.get("paginatedResponse");
		if (paginatedResponse != null) {
			List<EndUser> list = (List<EndUser>) paginatedResponse.getResultList();
			if (list != null && !list.isEmpty()) {
				for (EndUser bill : list) {
					bill.setOrgName(CacheService
							.getOrganizationById(ControllerUtil.getContextId(request), bill.getOrgId()).getName());
					bill.setPropertyName(CacheService
							.getPropertyById(ControllerUtil.getContextId(request), bill.getPropertyId()).getName());
					bill.setPurposeName(CacheService
							.getPurposeById(ControllerUtil.getContextId(request), bill.getPurposeId()).getName());
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
		String filterAttribute = (String) map.get("filterAttribute");
		
		String filterFromDate = (String) map.get("filterFromDate");
		String filterToDate = (String) map.get("filterToDate");
		Long filterVenue = (Long) map.get("filterVenue");
		
		if (filterAttribute != null && filterAttribute.equals("past")) {
			// Change Query
			return DbService.getInstance().getSetupService().getPastBillList(ControllerUtil.getContextId(httpRequest),
					paginatedRequest);
		} else if (filterAttribute != null && filterAttribute.equals("today")) {
			// Change Query
			return DbService.getInstance().getSetupService().getTodayBillList(ControllerUtil.getContextId(httpRequest),
					paginatedRequest);
		} else if (filterAttribute != null && filterAttribute.equals("future")) {
			// Change Query
			return DbService.getInstance().getSetupService().getFutureBillList(ControllerUtil.getContextId(httpRequest),
					paginatedRequest);
		}else if(filterFromDate != null && filterToDate != null && filterVenue != null) {
			return DbService.getInstance().getSetupService().getEndUserListByCtxIdAndFromDateAndToDateAndVenue(ControllerUtil.getContextId(httpRequest),
					paginatedRequest, filterFromDate , filterToDate, filterVenue );
		} else {
			return DbService.getInstance().getSetupService().getBillList(ControllerUtil.getContextId(httpRequest),
					paginatedRequest);
		}
	}
  
	@RequestMapping(value = "/app/bill/list", method = { RequestMethod.GET })
	public @ResponseBody ModelAndView billList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "reqPage", required = false) Long reqPage,
			@RequestParam(value = "pageSize", required = false) Long pageSize, Model model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			SessionOrgContext se = ControllerUtil.getValidSessionContext(request);
			if (se != null && se.getOrgId() != null && se.getOrgId() > 0L) {
				customModalAttributes(model);
				map.put("filterAttribute", "all");
				map.put("filterFromDate", null);
				map.put("filterToDate", null);
				map.put("filterVenue", null);
				preProcessPaginatedList(request, EndUser.class, ControllerUtil.getContextId(request), map, reqPage,
						pageSize, requestUriTmpl);
				return new ModelAndView("bill/billList", "data", map);
			} else {
				return new ModelAndView("common/selectOrg", "orgType", "Organisation");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}

	@RequestMapping(value = "/app/report/bill/past", method = { RequestMethod.GET })
	public @ResponseBody ModelAndView pastList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "reqPage", required = false) Long reqPage,
			@RequestParam(value = "pageSize", required = false) Long pageSize, Model model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			SessionOrgContext se = ControllerUtil.getValidSessionContext(request);
			if (se != null && se.getOrgId() != null && se.getOrgId() > 0L) {
				customModalAttributes(model);
				map.put("filterAttribute", "past");
				map.put("filterFromDate", null);
				map.put("filterToDate", null);
				map.put("filterVenue", null);
				preProcessPaginatedList(request, EndUser.class, ControllerUtil.getContextId(request), map, reqPage,
						pageSize, requestUriTmpl);
				return new ModelAndView("bill/billPastList", "data", map);
			} else {
				return new ModelAndView("common/selectOrg", "orgType", "Organisation");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}

	@RequestMapping(value = "/app/report/bill/today", method = { RequestMethod.GET })
	public @ResponseBody ModelAndView todayList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "reqPage", required = false) Long reqPage,
			@RequestParam(value = "pageSize", required = false) Long pageSize, Model model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			SessionOrgContext se = ControllerUtil.getValidSessionContext(request);
			if (se != null && se.getOrgId() != null && se.getOrgId() > 0L) {
				customModalAttributes(model);
				map.put("filterAttribute", "today");
				map.put("filterFromDate", null);
				map.put("filterToDate", null);
				map.put("filterVenue", null);
				preProcessPaginatedList(request, EndUser.class, ControllerUtil.getContextId(request), map, reqPage,
						pageSize, requestUriTmpl);
				return new ModelAndView("bill/billTodayList", "data", map);
			} else {
				return new ModelAndView("common/selectOrg", "orgType", "Organisation");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}

	@RequestMapping(value = "/app/report/bill/future", method = { RequestMethod.GET })
	public @ResponseBody ModelAndView futureList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "reqPage", required = false) Long reqPage,
			@RequestParam(value = "pageSize", required = false) Long pageSize, Model model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			SessionOrgContext se = ControllerUtil.getValidSessionContext(request);
			if (se != null && se.getOrgId() != null && se.getOrgId() > 0L) {
				customModalAttributes(model);
				map.put("filterAttribute", "future");
				map.put("filterFromDate", null);
				map.put("filterToDate", null);
				map.put("filterVenue", null);
				preProcessPaginatedList(request, EndUser.class, ControllerUtil.getContextId(request), map, reqPage,
						pageSize, requestUriTmpl);
				return new ModelAndView("bill/billFutureList", "data", map);
			} else {
				return new ModelAndView("common/selectOrg", "orgType", "Organisation");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}

	/*@RequestMapping(value = "/app/bill/list/export", method = { RequestMethod.GET }, produces = "application/xlsx")
	public @ResponseBody ModelAndView billListExportXlsx(HttpServletRequest request, HttpServletResponse response,
			//@RequestParam(value = "reqPage", required = false) Long reqPage,
			//@RequestParam(value = "pageSize", required = false) Long pageSize,
			@RequestParam(value = "exportType", required = false) String exportType) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("viewClass", BillXlsxStreamingView.class.getName());
			map.put("fileName", "Shilparamam-Bill-List.xlsx");
			return new ModelAndView("xlxs", "map", map);
		} catch (Exception e) { 
			logger.error(e.getMessage(), e);
			throw e;
		}
	}*/
	
	
	
	@RequestMapping(value = "/app/bill/list/export", method = {RequestMethod.GET}, produces = "application/xlsx")
	public @ResponseBody ModelAndView invoiceListExportXlsx(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="reqPage", required=false) Long reqPage, @RequestParam(value="pageSize", required=false) Long pageSize,
			@RequestParam(value="exportType", required=false) String exportType) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			PaginatedResponse res = processPaginatedList(EndUser.class, request, map, reqPage, pageSize, requestUriTmpl);
			if(res != null) {
				map.put("list", res.getResultList());
				map.put("viewClass", BillXlsxStreamingView.class.getName());
				map.put("fileName", "Shilparamam-Tax Reports-List.xlsx");
				return new ModelAndView("xlxs", "map", map);
			} else {
				map.put("message_error", "Failed to create MS Excel file for Invoice report.");
				return new ModelAndView("bill/billList", "data", map);
			}
		}  catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
	
	

	@RequestMapping(value = "/app/bill/download/pdf", method = { RequestMethod.GET }, produces = "application/pdf")
	public @ResponseBody ModelAndView billDownloadPdf(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "id", required = true) Long id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			EndUser dbBill = DbService.getInstance().getSetupService()
					.getBillByCtxIdAndId(ControllerUtil.getContextId(request), id);
			if (dbBill != null) {
				map.put("obj", dbBill);
				map.put("viewClass", BillPdfStreamingView.class.getName());
				map.put("fileName", "Shilparamam-" + id + ".pdf");
				map.put("tmplName", "/WEB-INF/res/tmpl/report/bill_view.pdf");
				return new ModelAndView("pdf", "map", map);
			} else {
				map.put("message_error", "Failed to create PDF file for Bill " + id);
				preProcessPaginatedList(request, EndUser.class, ControllerUtil.getContextId(request), map, requestUriTmpl);
				return new ModelAndView("bill/billList", "data", map);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}

	@RequestMapping(value = "/app/bill/view/form", method = { RequestMethod.GET })
	public @ResponseBody ModelAndView viewBillForm(HttpServletRequest request, @RequestParam(value = "id") Long id,
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
					EndUser dbBill = DbService.getInstance().getSetupService().getBillByCtxIdAndId(new Long(1), id);
					if (dbBill != null) {
						dbBill.setCreatedUserName(CacheService
								.getUserById(ControllerUtil.getContextId(request), dbBill.getCreatedUserId())
								.getDisplayName());
						dbBill.setPropertyName(CacheService
								.getPropertyById(ControllerUtil.getContextId(request), dbBill.getPropertyId())
								.getName());
						dbBill.setPurposeName(CacheService
								.getPurposeById(ControllerUtil.getContextId(request), dbBill.getPurposeId()).getName());
						User updatedUser = CacheService.getUserById(ControllerUtil.getContextId(request),
								dbBill.getUpdatedUserId());
						if (updatedUser != null) {
							dbBill.setCreatedUserName(updatedUser.getDisplayName());
						}
						customModalAttributes(model);
						return new ModelAndView("bill/viewBill", "billForm", dbBill);
					}
					// Check permissions
				} catch (Exception e) {
					logger.error("Failed to view Bill", e);
				}
			}

			// Return to Listing page with error
			map.put("message_error", "Bill being view does not exists on the System.");
			preProcessPaginatedList(request, EndUser.class, ControllerUtil.getContextId(request), map, reqPage, pageSize,
					requestUriTmpl);

			// Rest Captcha
			CaptchaServlet.resetCaptcha(request);
			return new ModelAndView("bill/billList", "data", map);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
	
	
	
	@RequestMapping(value = "/app/bill/view/future/form", method = { RequestMethod.GET })
	public @ResponseBody ModelAndView viewFutureBillForm(HttpServletRequest request, @RequestParam(value = "id") Long id,
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
					EndUser dbBill = DbService.getInstance().getSetupService().getBillByCtxIdAndId(new Long(1), id);
					if (dbBill != null) {
						dbBill.setCreatedUserName(CacheService
								.getUserById(ControllerUtil.getContextId(request), dbBill.getCreatedUserId())
								.getDisplayName());
						dbBill.setPropertyName(CacheService
								.getPropertyById(ControllerUtil.getContextId(request), dbBill.getPropertyId())
								.getName());
						dbBill.setPurposeName(CacheService
								.getPurposeById(ControllerUtil.getContextId(request), dbBill.getPurposeId()).getName());
						User updatedUser = CacheService.getUserById(ControllerUtil.getContextId(request),
								dbBill.getUpdatedUserId());
						if (updatedUser != null) {
							dbBill.setCreatedUserName(updatedUser.getDisplayName());
						}
						customModalAttributes(model);
						return new ModelAndView("bill/viewFutureBill", "billForm", dbBill);
					}
					// Check permissions
				} catch (Exception e) {
					logger.error("Failed to view Bill", e);
				}
			}

			// Return to Listing page with error
			map.put("message_error", "Bill being view does not exists on the System.");
			preProcessPaginatedList(request, EndUser.class, ControllerUtil.getContextId(request), map, reqPage, pageSize,
					requestUriTmpl);

			// Rest Captcha
			CaptchaServlet.resetCaptcha(request);
			return new ModelAndView("bill/billFutureList", "data", map);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		
		
		
		
	}
	
	
	
	@RequestMapping(value = "/app/bill/view/today/form", method = { RequestMethod.GET })
	public @ResponseBody ModelAndView viewTodayBillForm(HttpServletRequest request, @RequestParam(value = "id") Long id,
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
					EndUser dbBill = DbService.getInstance().getSetupService().getBillByCtxIdAndId(new Long(1), id);
					if (dbBill != null) {
						dbBill.setCreatedUserName(CacheService
								.getUserById(ControllerUtil.getContextId(request), dbBill.getCreatedUserId())
								.getDisplayName());
						dbBill.setPropertyName(CacheService
								.getPropertyById(ControllerUtil.getContextId(request), dbBill.getPropertyId())
								.getName());
						dbBill.setPurposeName(CacheService
								.getPurposeById(ControllerUtil.getContextId(request), dbBill.getPurposeId()).getName());
						User updatedUser = CacheService.getUserById(ControllerUtil.getContextId(request),
								dbBill.getUpdatedUserId());
						if (updatedUser != null) {
							dbBill.setCreatedUserName(updatedUser.getDisplayName());
						}
						customModalAttributes(model);
						return new ModelAndView("bill/viewTodayBill", "billForm", dbBill);
					}
					// Check permissions
				} catch (Exception e) {
					logger.error("Failed to view Bill", e);
				}
			}

			// Return to Listing page with error
			map.put("message_error", "Bill being view does not exists on the System.");
			preProcessPaginatedList(request, EndUser.class, ControllerUtil.getContextId(request), map, reqPage, pageSize,
					requestUriTmpl);

			// Rest Captcha
			CaptchaServlet.resetCaptcha(request);
			return new ModelAndView("bill/billTodayList", "data", map);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}	
		
	}
	
	@RequestMapping(value = "/app/bill/view/past/form", method = { RequestMethod.GET })
	public @ResponseBody ModelAndView viewPastBillForm(HttpServletRequest request, @RequestParam(value = "id") Long id,
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
					EndUser dbBill = DbService.getInstance().getSetupService().getBillByCtxIdAndId(new Long(1), id);
					if (dbBill != null) {
						dbBill.setCreatedUserName(CacheService
								.getUserById(ControllerUtil.getContextId(request), dbBill.getCreatedUserId())
								.getDisplayName());
						dbBill.setPropertyName(CacheService
								.getPropertyById(ControllerUtil.getContextId(request), dbBill.getPropertyId())
								.getName());
						dbBill.setPurposeName(CacheService
								.getPurposeById(ControllerUtil.getContextId(request), dbBill.getPurposeId()).getName());
						User updatedUser = CacheService.getUserById(ControllerUtil.getContextId(request),
								dbBill.getUpdatedUserId());
						if (updatedUser != null) {
							dbBill.setCreatedUserName(updatedUser.getDisplayName());
						}
						customModalAttributes(model);
						return new ModelAndView("bill/viewPastBill", "billForm", dbBill);
					}
					// Check permissions
				} catch (Exception e) {
					logger.error("Failed to view Bill", e);
				}
			}

			// Return to Listing page with error
			map.put("message_error", "Bill being view does not exists on the System.");
			preProcessPaginatedList(request, EndUser.class, ControllerUtil.getContextId(request), map, reqPage, pageSize,
					requestUriTmpl);

			// Rest Captcha
			CaptchaServlet.resetCaptcha(request);
			return new ModelAndView("bill/billPastList", "data", map);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
			
	}
	@RequestMapping(value = "/app/filterted/list", method = { RequestMethod.GET })
	public @ResponseBody ModelAndView filtertedList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "fromDate", required = false) String fromDate,
			@RequestParam(value = "toDate", required = false) String toDate,
			@RequestParam(value = "venue", required = false) Long venue,
			@RequestParam(value = "reqPage", required = false) Long reqPage,
			@RequestParam(value = "pageSize", required = false) Long pageSize,
			 Model model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			SessionOrgContext se = ControllerUtil.getValidSessionContext(request);
			if (se != null && se.getOrgId() != null && se.getOrgId() > 0L) {
				customModalAttributes(model);
				/*List<EndUser> endUserList = DbService.getInstance().getSetupService().getEndUserListByCtxIdAndFromDateAndToDateAndVenue(ControllerUtil.getContextId(request),fromDate , toDate, venue );
				if(endUserList != null){
					for (EndUser endUser : endUserList) {
						endUser.setOrgName(CacheService
								.getOrganizationById(ControllerUtil.getContextId(request), endUser.getOrgId()).getName());
						endUser.setPropertyName(CacheService
								.getPropertyById(ControllerUtil.getContextId(request), endUser.getPropertyId()).getName());
						endUser.setPurposeName(CacheService
								.getPurposeById(ControllerUtil.getContextId(request), endUser.getPurposeId()).getName());
					model.addAttribute("list",endUserList);
					}
					
				}*/
				map.put("filterFromDate", fromDate);
				map.put("filterToDate", toDate);
				map.put("filterVenue", venue);
				preProcessPaginatedList(request, EndUser.class, ControllerUtil.getContextId(request), map, reqPage, pageSize,
						requestUriTmpl);
				return new ModelAndView("bill/billList", "data", map);
			} else {
				return new ModelAndView("common/selectOrg", "orgType", "Organisation");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@RequestMapping(value = "/app/comingBill/list/export", method = { RequestMethod.GET }, produces = "application/xlsx")
	public @ResponseBody ModelAndView commingBillListExportXlsx(HttpServletRequest request, HttpServletResponse response,
			//@RequestParam(value = "reqPage", required = false) Long reqPage,
			//@RequestParam(value = "pageSize", required = false) Long pageSize,
			@RequestParam(value = "exportType", required = false) String exportType) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("viewClass", ComingBillXlsxStreamingView.class.getName());
			map.put("fileName", "Shilparamam-Bill-List.xlsx");
			return new ModelAndView("xlxs", "map", map);
		} catch (Exception e) { 
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@RequestMapping(value = "/app/completedBill/list/export", method = { RequestMethod.GET }, produces = "application/xlsx")
	public @ResponseBody ModelAndView completedBillListExportXlsx(HttpServletRequest request, HttpServletResponse response,
			//@RequestParam(value = "reqPage", required = false) Long reqPage,
			//@RequestParam(value = "pageSize", required = false) Long pageSize,
			@RequestParam(value = "exportType", required = false) String exportType) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("viewClass", CompletedBillXlsxStreamingView.class.getName());
			map.put("fileName", "Shilparamam-Bill-List.xlsx");
			return new ModelAndView("xlxs", "map", map);
		} catch (Exception e) { 
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@RequestMapping(value = "/app/todayBill/list/export", method = { RequestMethod.GET }, produces = "application/xlsx")
	public @ResponseBody ModelAndView todayBillListExportXlsx(HttpServletRequest request, HttpServletResponse response,
			//@RequestParam(value = "reqPage", required = false) Long reqPage,
			//@RequestParam(value = "pageSize", required = false) Long pageSize,
			@RequestParam(value = "exportType", required = false) String exportType) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("viewClass", TodayBillXlsxStreamingView.class.getName());
			map.put("fileName", "Shilparamam-Bill-List.xlsx");
			return new ModelAndView("xlxs", "map", map);
		} catch (Exception e) { 
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
}