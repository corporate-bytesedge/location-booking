package com.bytesedge.bookvenue.controller.Invoice;

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
import com.bytesedge.bookvenue.controller.Bill.CompletedBillXlsxStreamingView;
import com.bytesedge.bookvenue.model.AuditObject;
import com.bytesedge.bookvenue.model.AuditOperationType;
import com.bytesedge.bookvenue.model.Bill;
import com.bytesedge.bookvenue.model.EndUser;
import com.bytesedge.bookvenue.model.IdProofType;
import com.bytesedge.bookvenue.model.Invoice;
import com.bytesedge.bookvenue.model.PaymentStatus;
import com.bytesedge.bookvenue.model.PropertyRentPrice;
import com.bytesedge.bookvenue.model.SessionOrgContext;
import com.bytesedge.bookvenue.model.User;
import com.sun.mail.imap.protocol.Status;

@Controller
@SessionAttributes({"invoiceForm"})
public class InvoiceController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(InvoiceController.class);
	
	private final String requestUriTmpl = "/app/invoice/list?reqPage=%d&pageSize=%d";
	
	@Override
	public void customModalAttributes(Model model) {
		model.addAttribute("AuditOperationType", AuditOperationType.values());
		model.addAttribute("AuditObject", AuditObject.values());
		model.addAttribute("IdProofType", IdProofType.values());
		model.addAttribute("PaymentStatus", PaymentStatus.values());
	}

	@SuppressWarnings("unchecked")
	public void preProcessPaginatedList(HttpServletRequest request, final Class<?> clazz, final Long ctxId, 
			final Map<String, Object> map, final Long reqPage, 
			final Long pageSize, final String requestUriTmpl) throws Exception {
		processPaginatedList(EndUser.class,request, map, reqPage, pageSize, requestUriTmpl);
		PaginatedResponse paginatedResponse = (PaginatedResponse) map.get("paginatedResponse");
		if(paginatedResponse != null) {
			List<EndUser> list = (List<EndUser>) paginatedResponse.getResultList();
			if(list != null && !list.isEmpty()) {
				for(EndUser invoice : list) {
					invoice.setPropertyName(CacheService.getPropertyById(ControllerUtil.getContextId(request), invoice.getPropertyId()).getName());
					invoice.setPurposeName(CacheService.getPurposeById(ControllerUtil.getContextId(request), invoice.getPurposeId()).getName());
					invoice.setCreatedUserName(CacheService.getUserById(ControllerUtil.getContextId(request), invoice.getCreatedUserId()).getDisplayName());
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
		String filterAttribute = (String) map.get("filterAttribute");
		if(filterAttribute != null && filterAttribute.equals("paid")) {
			// Change Query
			return DbService.getInstance().getSetupService().getSuccessInvoiceList(ControllerUtil.getContextId(httpRequest), paginatedRequest);
		} else if(filterAttribute != null && filterAttribute.equals("pending")) {
			// Change Query
			return DbService.getInstance().getSetupService().getPendingInvoiceList(ControllerUtil.getContextId(httpRequest), paginatedRequest);
		} else if(filterAttribute != null && filterAttribute.equals("failed")) {
			// Change Query
			return DbService.getInstance().getSetupService().getFailInvoiceList(ControllerUtil.getContextId(httpRequest), paginatedRequest);
		} else {
			return DbService.getInstance().getSetupService().getInvoiceList(ControllerUtil.getContextId(httpRequest), paginatedRequest);
		}
	}
		
	@RequestMapping(value = "/app/invoice/list", method = {RequestMethod.GET})
	public @ResponseBody ModelAndView invoiceList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="reqPage", required=false) Long reqPage, @RequestParam(value="pageSize", 
			required=false) Long pageSize, Model model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			SessionOrgContext se = ControllerUtil.getValidSessionContext(request);
			if(se != null && se.getOrgId() != null && se.getOrgId() > 0L) {
				customModalAttributes(model);
				preProcessPaginatedList(request, EndUser.class, ControllerUtil.getContextId(request), map, reqPage, pageSize, requestUriTmpl);
				return new ModelAndView("invoice/invoiceList", "data", map);	
			} else {
				return new ModelAndView("common/selectOrg", "orgType", "Organisation");
			}
		} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw e;
		}
	}
	
	@RequestMapping(value = "/app/invoice/list/export", method = {RequestMethod.GET}, produces = "application/xlsx")
	public @ResponseBody ModelAndView invoiceListExportXlsx(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="reqPage", required=false) Long reqPage, @RequestParam(value="pageSize", required=false) Long pageSize,
			@RequestParam(value="exportType", required=false) String exportType) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			PaginatedResponse res = processPaginatedList(EndUser.class, request, map, reqPage, pageSize, requestUriTmpl);
			if(res != null) {
				map.put("list", res.getResultList());
				map.put("viewClass", InvoiceXlsxStreamingView.class.getName());
				map.put("fileName", "Shilparamam-Invoice-List.xlsx");
				return new ModelAndView("xlxs", "map", map);
			} else {
				map.put("message_error", "Failed to create MS Excel file for Invoice report.");
				return new ModelAndView("invoice/invoiceList", "data", map);
			}
		}  catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@RequestMapping(value = "/app/invoice/download/pdf", method = {RequestMethod.GET}, produces = "application/pdf")
	public @ResponseBody ModelAndView invoiceDownloadPdf(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="id", required=true) Long id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			EndUser dbInvoice = DbService.getInstance().getSetupService().getEndUserByCtxIdAndId(ControllerUtil.getContextId(request), id);
			if(dbInvoice != null) {
				map.put("obj", dbInvoice);
				map.put("viewClass", InvoicePdfStreamingView.class.getName());
				map.put("fileName", "Shilparamam-Invoice-" + id + ".pdf");
				map.put("tmplName", "/WEB-INF/res/tmpl/report/invoice_view.pdf");
				return new ModelAndView("pdf", "map", map);
			} else {
				map.put("message_error", "Failed to create PDF file for Invoice " + id);
				preProcessPaginatedList(request, EndUser.class, ControllerUtil.getContextId(request), map, requestUriTmpl);
				return new ModelAndView("invoice/invoiceList", "data", map);
			}
		}  catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@RequestMapping(value = "/app/transactionStatus/invoice/success", method = { RequestMethod.GET })
	public @ResponseBody ModelAndView successList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "reqPage", required = false) Long reqPage,
			@RequestParam(value = "pageSize", required = false) Long pageSize, Model model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			SessionOrgContext se = ControllerUtil.getValidSessionContext(request);
			if(se != null && se.getOrgId() != null && se.getOrgId() > 0L) {
				customModalAttributes(model);
				map.put("filterAttribute", "paid");
				preProcessPaginatedList(request, EndUser.class, ControllerUtil.getContextId(request), map, reqPage, pageSize, requestUriTmpl);
				return new ModelAndView("invoice/invoicePaidList", "data", map);	
			} else {
				return new ModelAndView("common/selectOrg", "orgType", "Organisation");
			}
		} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw e;
		}
	}		

	@RequestMapping(value = "/app/transactionStatus/invoice/pending", method = { RequestMethod.GET })
	public @ResponseBody ModelAndView pendingList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "reqPage", required = false) Long reqPage,
			@RequestParam(value = "pageSize", required = false) Long pageSize, Model model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			SessionOrgContext se = ControllerUtil.getValidSessionContext(request);
			if(se != null && se.getOrgId() != null && se.getOrgId() > 0L) {
				customModalAttributes(model);
				map.put("filterAttribute", "pending");
				preProcessPaginatedList(request, EndUser.class, ControllerUtil.getContextId(request), map, reqPage, pageSize, requestUriTmpl);
				return new ModelAndView("invoice/invoicePendingList", "data", map);	
			} else {
				return new ModelAndView("common/selectOrg", "orgType", "Organisation");
			}
		} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw e;
		}
	}		

	@RequestMapping(value = "/app/transactionStatus/invoice/failed", method = { RequestMethod.GET })
	public @ResponseBody ModelAndView failedList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "reqPage", required = false) Long reqPage,
			@RequestParam(value = "pageSize", required = false) Long pageSize, Model model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			SessionOrgContext se = ControllerUtil.getValidSessionContext(request);
			if(se != null && se.getOrgId() != null && se.getOrgId() > 0L) {
				customModalAttributes(model);
				map.put("filterAttribute", "failed");
				preProcessPaginatedList(request, EndUser.class, ControllerUtil.getContextId(request), map, reqPage, pageSize, requestUriTmpl);
				return new ModelAndView("invoice/invoiceFailedList", "data", map);	
			} else {
				return new ModelAndView("common/selectOrg", "orgType", "Organisation");
			}
		} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw e;
				
		}
	}		
	

	@RequestMapping(value = "/app/invoice/view/form", method = {RequestMethod.GET})
	public @ResponseBody ModelAndView viewInvoiceForm(HttpServletRequest request,
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
					EndUser dbInvoice = DbService.getInstance().getSetupService().getEndUserByCtxIdAndId(new Long(1), id);
					if(dbInvoice != null) {
						customModalAttributes(model);
						dbInvoice.setCreatedUserName(CacheService.getUserById(ControllerUtil.getContextId(request), dbInvoice.getCreatedUserId()).getDisplayName());
						User updatedUser = CacheService.getUserById(ControllerUtil.getContextId(request), dbInvoice.getUpdatedUserId());
						if(updatedUser != null) {
							dbInvoice.setUpdatedUserName(updatedUser.getDisplayName());
						}
						preProcessPaginatedList(request, EndUser.class, ControllerUtil.getContextId(request), map, reqPage, pageSize, requestUriTmpl);
						return new ModelAndView("invoice/viewInvoice", "invoiceForm", dbInvoice);
					}
					// Check permissions
				} catch (Exception e) {
					logger.error("Failed to view Invoice", e);
				}
			}
			
			// Return to Listing page with error
			map.put("message_error", "Invoice being view does not exists on the System.");
			preProcessPaginatedList(request, EndUser.class, ControllerUtil.getContextId(request), map,reqPage, pageSize, requestUriTmpl);
			
			// Rest Captcha
			CaptchaServlet.resetCaptcha(request);
			return new ModelAndView("invoice/invoiceList", "data", map);
		}  catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
	
	
	@RequestMapping(value = "/app/invoice/view/paid/form", method = {RequestMethod.GET})
	public @ResponseBody ModelAndView viewPaidInvoiceForm(HttpServletRequest request,
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
					EndUser dbInvoice = DbService.getInstance().getSetupService().getEndUserByCtxIdAndId(new Long(1), id);
					if(dbInvoice != null) {
						customModalAttributes(model);
						dbInvoice.setCreatedUserName(CacheService.getUserById(ControllerUtil.getContextId(request), dbInvoice.getCreatedUserId()).getDisplayName());
						User updatedUser = CacheService.getUserById(ControllerUtil.getContextId(request), dbInvoice.getUpdatedUserId());
						if(updatedUser != null) {
							dbInvoice.setUpdatedUserName(updatedUser.getDisplayName());
						}
						preProcessPaginatedList(request, EndUser.class, ControllerUtil.getContextId(request), map, reqPage, pageSize, requestUriTmpl);
						return new ModelAndView("invoice/viewPaidInvoice", "invoiceForm", dbInvoice);
					}
					// Check permissions
				} catch (Exception e) {
					logger.error("Failed to view Invoice", e);
				}
			}
			
			// Return to Listing page with error
			map.put("message_error", "Invoice being view does not exists on the System.");
			preProcessPaginatedList(request, EndUser.class, ControllerUtil.getContextId(request), map,reqPage, pageSize, requestUriTmpl);
			
			// Rest Captcha
			CaptchaServlet.resetCaptcha(request);
			return new ModelAndView("invoice/invoicePaidList", "data", map);
		}  catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@RequestMapping(value = "/app/invoice/view/failed/form", method = {RequestMethod.GET})
	public @ResponseBody ModelAndView viewFailedInvoiceForm(HttpServletRequest request,
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
					EndUser dbInvoice = DbService.getInstance().getSetupService().getEndUserByCtxIdAndId(new Long(1), id);
					if(dbInvoice != null) {
						customModalAttributes(model);
						dbInvoice.setCreatedUserName(CacheService.getUserById(ControllerUtil.getContextId(request), dbInvoice.getCreatedUserId()).getDisplayName());
						User updatedUser = CacheService.getUserById(ControllerUtil.getContextId(request), dbInvoice.getUpdatedUserId());
						if(updatedUser != null) {
							dbInvoice.setUpdatedUserName(updatedUser.getDisplayName());
						}
						preProcessPaginatedList(request, EndUser.class, ControllerUtil.getContextId(request), map, reqPage, pageSize, requestUriTmpl);
						return new ModelAndView("invoice/viewFailedInvoice", "invoiceForm", dbInvoice);
					}
					// Check permissions
				} catch (Exception e) {
					logger.error("Failed to view Invoice", e);
				}
			}
			
			// Return to Listing page with error
			map.put("message_error", "Invoice being view does not exists on the System.");
			preProcessPaginatedList(request, EndUser.class, ControllerUtil.getContextId(request), map,reqPage, pageSize, requestUriTmpl);
			
			// Rest Captcha
			CaptchaServlet.resetCaptcha(request);
			return new ModelAndView("invoice/invoiceFailedList", "data", map);
		}  catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@RequestMapping(value = "/app/invoice/view/pending/form", method = {RequestMethod.GET})
	public @ResponseBody ModelAndView viewPendingInvoiceForm(HttpServletRequest request,
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
					EndUser dbInvoice = DbService.getInstance().getSetupService().getEndUserByCtxIdAndId(new Long(1), id);
					if(dbInvoice != null) {
						customModalAttributes(model);
						dbInvoice.setCreatedUserName(CacheService.getUserById(ControllerUtil.getContextId(request), dbInvoice.getCreatedUserId()).getDisplayName());
						User updatedUser = CacheService.getUserById(ControllerUtil.getContextId(request), dbInvoice.getUpdatedUserId());
						if(updatedUser != null) {
							dbInvoice.setUpdatedUserName(updatedUser.getDisplayName());
						}
						preProcessPaginatedList(request, EndUser.class, ControllerUtil.getContextId(request), map, reqPage, pageSize, requestUriTmpl);
						return new ModelAndView("invoice/viewPendingInvoice", "invoiceForm", dbInvoice);
					}
					// Check permissions
				} catch (Exception e) {
					logger.error("Failed to view Invoice", e);
				}
			}
			
			// Return to Listing page with error
			map.put("message_error", "Invoice being view does not exists on the System.");
			preProcessPaginatedList(request, EndUser.class, ControllerUtil.getContextId(request), map,reqPage, pageSize, requestUriTmpl);
			
			// Rest Captcha
			CaptchaServlet.resetCaptcha(request);
			return new ModelAndView("invoice/invoicePendingList", "data", map);
		}  catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@RequestMapping(value = "/app/invoice/find/form", method = { RequestMethod.GET })
	public @ResponseBody ModelAndView createFindInvoiceForm(HttpServletRequest request, Model model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			EndUser invoiceForm = new EndUser();
			return new ModelAndView("invoice/findInvoice", "invoiceForm", invoiceForm);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		// Incase of erros Return to Lising Page
		return new ModelAndView("invoice/invoiceList", "data", map);
	}
	
	
	@RequestMapping(value = "/app/invoice/findInvoiceId", method = { RequestMethod.POST })
	public @ResponseBody ModelAndView createFindInvoice(HttpServletRequest request,
			@ModelAttribute("invoiceForm") EndUser invoiceForm, BindingResult result, Model model)
					throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			
			EndUser dbObject = DbService.getInstance().getSetupService().getEndUserByCtxIdAndOrgIdAndApplicationId(ControllerUtil.getContextId(request), ControllerUtil.getOrgId(request), invoiceForm.getApplicationId());
			dbObject.setPropertyName(CacheService.getPropertyById(ControllerUtil.getContextId(request), dbObject.getPropertyId()).getName());
			dbObject.setPurposeName(CacheService.getPurposeById(ControllerUtil.getContextId(request), dbObject.getPurposeId()).getName());
			
			if(dbObject != null){
				customModalAttributes(model);
			}
		
			preProcessPaginatedList(request, EndUser.class, ControllerUtil.getContextId(request), map,requestUriTmpl);
			return new ModelAndView("invoice/invoiceResult", "invoiceForm", dbObject);
			
				
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ModelAndView("invoice/NoInvoice", "data", map);
		}
	}
	
	
	
	@RequestMapping(value = "/app/invoice/paid/list/export", method = {RequestMethod.GET}, produces = "application/xlsx")
	public @ResponseBody ModelAndView invoicePaidListExportXlsx(HttpServletRequest request, HttpServletResponse response,
			//@RequestParam(value="reqPage", required=false) Long reqPage, 
			//@RequestParam(value="pageSize", required=false) Long pageSize,
			@RequestParam(value="exportType", required=false) String exportType) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
			try {
				map.put("viewClass", InvoicePaidXlsxStreamingView.class.getName());
				map.put("fileName", "Shilparamam-Paid-List.xlsx");
				return new ModelAndView("xlxs", "map", map);
			} catch (Exception e) { 
				logger.error(e.getMessage(), e);
				throw e;
			}
		}
	
	@RequestMapping(value = "/app/invoice/pending/list/export", method = {RequestMethod.GET}, produces = "application/xlsx")
	public @ResponseBody ModelAndView invoicePendingListExportXlsx(HttpServletRequest request, HttpServletResponse response,
			//@RequestParam(value="reqPage", required=false) Long reqPage, 
			//@RequestParam(value="pageSize", required=false) Long pageSize,
			@RequestParam(value="exportType", required=false) String exportType) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
			try {
				map.put("viewClass", InvoicePendingXlsxStreamingView.class.getName());
				map.put("fileName", "Shilparamam-Pending-List.xlsx");
				return new ModelAndView("xlxs", "map", map);
			} catch (Exception e) { 
				logger.error(e.getMessage(), e);
				throw e;
			}
		}
	
	@RequestMapping(value = "/app/invoice/failed/list/export", method = {RequestMethod.GET}, produces = "application/xlsx")
	public @ResponseBody ModelAndView invoiceFailedListExportXlsx(HttpServletRequest request, HttpServletResponse response,
			//@RequestParam(value="reqPage", required=false) Long reqPage, 
			//@RequestParam(value="pageSize", required=false) Long pageSize,
			@RequestParam(value="exportType", required=false) String exportType) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
			try {
				map.put("viewClass", InvoiceFailedXlsxStreamingView.class.getName());
				map.put("fileName", "Shilparamam-Failed-List.xlsx");
				return new ModelAndView("xlxs", "map", map);
			} catch (Exception e) { 
				logger.error(e.getMessage(), e);
				throw e;
			}
		}
	
	
	
}