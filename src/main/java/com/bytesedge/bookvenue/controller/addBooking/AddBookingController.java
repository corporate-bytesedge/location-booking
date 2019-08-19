package com.bytesedge.bookvenue.controller.addBooking;

import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import com.bytesedge.bookvenue.common.EmailService;
import com.bytesedge.bookvenue.common.PaginatedRequest;
import com.bytesedge.bookvenue.common.PaginatedResponse;
import com.bytesedge.bookvenue.controller.BaseController;
import com.bytesedge.bookvenue.controller.CaptchaServlet;
import com.bytesedge.bookvenue.controller.ControllerUtil;
import com.bytesedge.bookvenue.controller.EndUser.EndUserXlsxStreamingView;
import com.bytesedge.bookvenue.model.AuditObject;
import com.bytesedge.bookvenue.model.AuditOperationType;
import com.bytesedge.bookvenue.model.BookingType;
import com.bytesedge.bookvenue.model.Context;
import com.bytesedge.bookvenue.model.EndUser;
import com.bytesedge.bookvenue.model.IdProofType;
import com.bytesedge.bookvenue.model.Marquee;
import com.bytesedge.bookvenue.model.MarqueeType;
import com.bytesedge.bookvenue.model.Organization;
import com.bytesedge.bookvenue.model.PaymentMode;
import com.bytesedge.bookvenue.model.PaymentStatus;
import com.bytesedge.bookvenue.model.Property;
import com.bytesedge.bookvenue.model.PropertyRentPrice;
import com.bytesedge.bookvenue.model.RentPurpose;
import com.bytesedge.bookvenue.model.SessionOrgContext;
import com.bytesedge.bookvenue.model.SlotType;
import com.bytesedge.bookvenue.model.StateType;
import com.bytesedge.bookvenue.model.UserState;
import com.bytesedge.bookvenue.util.UUIDUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@SessionAttributes({ "addBookingForm" })
public class AddBookingController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(AddBookingController.class);

	private final String requestUriTmpl = "/app/addBooking/list?reqPage=%d&pageSize=%d";

	@Autowired
	private AddBookingFormValidator validator;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(validator);
	}

	public AddBookingFormValidator getValidator() {
		return validator;
	}

	public void setValidator(AddBookingFormValidator validator) {
		this.validator = validator;
	}

	@Override
	public void customModalAttributes(Model model) {
		model.addAttribute("AuditOperationType", AuditOperationType.values());
		model.addAttribute("AuditObject", AuditObject.values());
		model.addAttribute("IdProofType", IdProofType.values());
		model.addAttribute("PaymentStatus", PaymentStatus.values());
		model.addAttribute("PaymentMode", PaymentMode.values());
		model.addAttribute("StateType", StateType.values());
		model.addAttribute("MarqueeType", MarqueeType.values());
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
				for (EndUser addBooking : list) {
					addBooking.setCreatedUserName(CacheService
							.getUserById(ControllerUtil.getContextId(request),  addBooking.getCreatedUserId()).getDisplayName());
					addBooking.setOrgName(CacheService
							.getOrganizationById(ControllerUtil.getContextId(request), addBooking.getOrgId())
							.getName());
					addBooking.setPropertyName(CacheService
							.getPropertyById(ControllerUtil.getContextId(request), addBooking.getPropertyId())
							.getName());
					addBooking.setPurposeName(CacheService
							.getPurposeById(ControllerUtil.getContextId(request), addBooking.getPurposeId()).getName());
				}
			}
		}
	}
	
	@RequestMapping(value = "/app/addBooking/download/pdf", method = {
			RequestMethod.GET }, produces = "application/pdf")
	public @ResponseBody ModelAndView addBookingDownloadPdf(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "id", required = true) Long id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			EndUser dbAddBooking = DbService.getInstance().getSetupService().getAddBookingByCtxIdAndOrgIdAndId(
					ControllerUtil.getContextId(request), ControllerUtil.getUserOrgId(request), id);
			if (dbAddBooking != null) {
				map.put("obj", dbAddBooking);
				map.put("viewClass", AddBookingPdfStreamingView.class.getName());
				map.put("fileName", "Shilparamam-AddBooking-" + id + ".pdf");
				map.put("tmplName", "/WEB-INF/res/tmpl/report/addBooking_view.pdf");
				return new ModelAndView("pdf", "map", map);
			} else {
				
				map.put("message_error", "Failed to create PDF file for Management Booking " + id);
				preProcessPaginatedList(request, EndUser.class, ControllerUtil.getContextId(request), map,
						requestUriTmpl);
				return new ModelAndView("addBooking/addBookingList", "data", map);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
	
	
	@RequestMapping(value = "/app/addBooking/list/export", method = { RequestMethod.GET }, produces = "application/xlsx")
	public @ResponseBody ModelAndView addBookingListExportXlsx(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "reqPage", required = false) Long reqPage,
			@RequestParam(value = "pageSize", required = false) Long pageSize,
			@RequestParam(value = "exportType", required = false) String exportType) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			PaginatedResponse res = processPaginatedList(EndUser.class, request, map, reqPage, pageSize,
					requestUriTmpl);
			if (res != null) {
				map.put("list", res.getResultList());
				map.put("viewClass", AddBookingXlsxStreamingView.class.getName());
				map.put("fileName", "Shilparamam-AddBooking-List.xlsx");
				return new ModelAndView("xlxs", "map", map);
			} else {
				map.put("message_error", "Failed to create MS Excel file for EndUser report.");
				return new ModelAndView("addBooking/addBookingList", "data", map);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
	
	

	@RequestMapping(value = "/app/addBooking/create/form", method = { RequestMethod.GET })
	public @ResponseBody ModelAndView createAddBookingForm(HttpServletRequest request,
			@RequestParam(value = "venueId", required = false) Long venueId, Model model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			EndUser addBookingForm = new EndUser();
			/*//CaptchaServlet.resetCaptcha(request);
			List<Property> filteredList = new ArrayList<Property>();
			List<Property> propertyList = DbService.getInstance().getSetupService()
					.getPropertyListByCtxId(ControllerUtil.getContextId(request));
			if (propertyList != null) {
				for (Property p : propertyList) {
					if (p.getState() == StateType.Active) {
						filteredList.add(p);
					}
				}
			}
			customModalAttributes(model);
			model.addAttribute("propertyList", filteredList);

			List<RentPurpose> purposeList = DbService.getInstance().getSetupService()
					.getRentPurposeListByCtxId(ControllerUtil.getContextId(request));
			List<RentPurpose> purposeListFiltered = new ArrayList<RentPurpose>();
			if (propertyList != null) {
				for (RentPurpose rp : purposeList) {
					if (rp.getState() == StateType.Active)
						purposeListFiltered.add(rp);
				}
			}
			model.addAttribute("purposeList", purposeListFiltered);*/
			addBookingForm.setBookingType(BookingType.MANAGEMENT);
			Marquee marquee = DbService.getInstance().getSetupService().getLatestMarqueeByCtxIdAndOrgIdAndType(ControllerUtil.getContextId(request), ControllerUtil.getOrgId(request), MarqueeType.DASHBOARD);
			if(marquee != null){
				addBookingForm.setMarquee(marquee.getText());
				logger.error("Marquee text:"+marquee.getText());
			}else
			{
				map.put("message_error", "Failed to load  a marquee text");
			}
			Property dbObj = DbService.getInstance().getSetupService().getPropertyByCtxIdAndId(ControllerUtil.getContextId(request), venueId);
			if(dbObj != null) {
				addBookingForm.setVenueDaysLimit(dbObj.getVenueDaysLimit());
			}
			// Bill dates
						List<EndUser> filteredBillList = new ArrayList<EndUser>();
						List<EndUser> billList = DbService.getInstance().getSetupService().getBlockedBillByCtxIdAndOrgIdAndPropertyId(ControllerUtil.getContextId(request), ControllerUtil.getOrgId(request), venueId);
						String pattern = "dd-MM-yyyy";
						SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);					
							if (billList != null) {
							for (EndUser bill : billList) {
								if (bill.getPaymentStatus() == PaymentStatus.PAID){
									filteredBillList.add(bill);
									if(addBookingForm.getBillList() == null)
										addBookingForm.setBillList(simpleDateFormat.format(bill.getBookingDate()));
									else
										addBookingForm.setBillList(addBookingForm.getBillList()+ ", " + simpleDateFormat.format(bill.getBookingDate()));
								logger.error("Bill:Exp-Dates:" + simpleDateFormat.format(bill.getBookingDate()));
							}
						}
						}
						model.addAttribute("billList", filteredBillList);
			customModalAttributes(model);
			return new ModelAndView("addBooking/createAddBooking", "addBookingForm", addBookingForm);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		// Incase of erros Return to Lising Page
		return new ModelAndView("addBooking/addBookingList", "data", map);
	}

	@RequestMapping(value = "/app/addBooking/create", method = { RequestMethod.POST })
	public @ResponseBody ModelAndView createAddBooking(HttpServletRequest request,
			@ModelAttribute("addBookingForm") @Valid EndUser addBookingForm, BindingResult result, Model model)
					throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (result.hasErrors()) {
				List<Property> filteredList = new ArrayList<Property>();
				List<Property> propertyList = DbService.getInstance().getSetupService()
						.getPropertyListByCtxId(ControllerUtil.getContextId(request));
				if (propertyList != null) {
					for (Property p : propertyList) {
						if (p.getState() == StateType.Active) {
							filteredList.add(p);
						}
					}
				}
				customModalAttributes(model);
				model.addAttribute("propertyList", filteredList);

				List<RentPurpose> purposeList = DbService.getInstance().getSetupService()
						.getRentPurposeListByCtxId(ControllerUtil.getContextId(request));
				List<RentPurpose> purposeListFiltered = new ArrayList<RentPurpose>();
				if (propertyList != null) {
					for (RentPurpose rp : purposeList) {
						if (rp.getState() == StateType.Active)
							purposeListFiltered.add(rp);
					}
				}
				model.addAttribute("purposeList", purposeListFiltered);
				customModalAttributes(model);
				return new ModelAndView("addBooking/createAddBooking", "addBookingForm", addBookingForm);
			} 
			else{
			// Update org Id
				Context context = CacheService.getContextByUrl(request.getServerName());
				if(context != null) {
					Organization org = CacheService.getOrgByCtxId(context.getId());
					if(org != null) {
						addBookingForm.setOrgId(org.getId());
					}
				}
				// Pre process the formBean
				customModalAttributes(model);
				this.updateContextDoProperties(request, addBookingForm);
				// Compose other data
				final String invoicePrefix = "INV-" + Year.now().getValue() + "-";
				Long maxId = DbService.getInstance().getSetupService().getApplicationDocMaxId();
				if (maxId == null) {
					maxId = 0L;
				}
				maxId = maxId + 1;
				addBookingForm.setApplicationId(invoicePrefix + maxId);
				
				PropertyRentPrice price = null;
				Calendar c1 = Calendar.getInstance();
			    c1.setTime(addBookingForm.getBookingDate());
			    if ((c1.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)|| (Calendar.DAY_OF_WEEK == Calendar.SUNDAY)) {
			    	price = DbService.getInstance().getSetupService().getPropertyRentPriceByCtxIdAndOrgIdAndPropertyIdAndPurposeIdAndSlotType(ControllerUtil.getContextId(request), addBookingForm.getOrgId(), addBookingForm.getPropertyId(), addBookingForm.getPurposeId(), SlotType.WEEKEND); 
				} else {
					price = DbService.getInstance().getSetupService().getPropertyRentPriceByCtxIdAndOrgIdAndPropertyIdAndPurposeIdAndSlotType(ControllerUtil.getContextId(request), addBookingForm.getOrgId(), addBookingForm.getPropertyId(), addBookingForm.getPurposeId(), SlotType.WEEKDAY); 
				}
			    addBookingForm.setPrice(price.getPrice());
			    
			   	addBookingForm.setServiceTax((price.getServiceTax() * price.getPrice())/100);
			    addBookingForm.setCgst((price.getCgst() * price.getPrice())/ 100);
			    addBookingForm.setSgst((price.getSgst() * price.getPrice())/ 100);
			    addBookingForm.setTotal(price.getPrice()+(((price.getCgst() + price.getSgst() + price.getServiceTax()) * price.getPrice()) / 100));
			    
				// Create End User
				EndUser obj = new EndUser();
				obj.setApplicationId(addBookingForm.getApplicationId());
				obj.setId(addBookingForm.getId());
				obj.setCtxId(addBookingForm.getCtxId());
				obj.setName(addBookingForm.getName());
				obj.setIdProof(addBookingForm.getIdProof());
				obj.setIdProofType(addBookingForm.getIdProofType());
				obj.setBookingDate(addBookingForm.getBookingDate());
				obj.setBookingType(addBookingForm.getBookingType());
				obj.setMobileNumber(addBookingForm.getMobileNumber());
				obj.setEmail(addBookingForm.getEmail());
				obj.setPropertyId(addBookingForm.getPropertyId());
				obj.setPurposeId(addBookingForm.getPurposeId());
				obj.setCgst(addBookingForm.getCgst());
				obj.setPrice(addBookingForm.getPrice());
				obj.setSgst(addBookingForm.getSgst());
				obj.setTotal(addBookingForm.getTotal());
				obj.setServiceTax(addBookingForm.getServiceTax());
				obj.setAddr(addBookingForm.getAddr());
				obj.setReferredBy(addBookingForm.getReferredBy());
				obj.setOrgId(addBookingForm.getOrgId());
				obj.setCreatedUserId(addBookingForm.getCreatedUserId());
				obj.setPropertyName(addBookingForm.getPropertyName());
				obj.setPurposeName(addBookingForm.getPurposeName());
				obj.setPaymentUrl(addBookingForm.getPaymentUrl());
				obj.setUserName(UUIDUtil.getUuid(24));
				obj.setOtp("1234");
				obj.setMarquee(addBookingForm.getMarquee());
				obj.setCreatedTime(new Date());
				obj.setPaymentMode(addBookingForm.getPaymentMode());
				//obj.setPaymentStatus(addBookingForm.getPaymentStatus());
				EndUser dbEndUserObj = (EndUser) DbService.getInstance().getSetupService().saveOrUpdate(obj);
				dbEndUserObj.setPropertyName(CacheService.getPropertyById(ControllerUtil.getContextId(request), dbEndUserObj.getPropertyId()).getName());
				dbEndUserObj.setPurposeName(CacheService.getPurposeById(ControllerUtil.getContextId(request), dbEndUserObj.getPurposeId()).getName());
				ObjectMapper jsonApi = new ObjectMapper();
				this.saveAuditObject(request, null, jsonApi.writeValueAsString(dbEndUserObj), AuditOperationType.CREATE, AuditObject.EndUser);
				
				return new ModelAndView("addBooking/viewAddBooking", "addBookingForm", dbEndUserObj);
		}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ModelAndView("addBooking/addBookingList", "data", map);
		}
	}
	
/*	@RequestMapping(value = "/app/addBooking/paymentAddBooking/form", method = {RequestMethod.GET})
	public @ResponseBody ModelAndView paymentAddBookingForm(HttpServletRequest request, 
			@RequestParam(value = "un", required = true) String un, Model model) throws Exception {
		try {
			CaptchaServlet.resetCaptcha(request);
			customModalAttributes(model);

			EndUser endUser = DbService.getInstance().getSetupService().getEndUserByCtxIdAndOrgIdAndUserName(ControllerUtil.getContextId(request), ControllerUtil.getOrgId(request), un);
			if(endUser != null) {
				return new ModelAndView("addBooking/paymentAddBooking", "endUser", endUser);
			}
		}  catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return new ModelAndView("common/error");
	}

	*/
	
	@RequestMapping(value = "/app/addBooking/paymentAddBooking/form", method = {RequestMethod.GET})
	public @ResponseBody ModelAndView paymentAddBookingForm(HttpServletRequest request, 
			@RequestParam(value = "un", required = true) String un,
	@ModelAttribute("addBookingForm")  EndUser addBookingForm, Model model) throws Exception {
		try {
			customModalAttributes(model);
			this.updateContextDoProperties(request, addBookingForm);
			
			EndUser endUser = DbService.getInstance().getSetupService().getEndUserByCtxIdAndOrgIdAndUserName(ControllerUtil.getContextId(request), addBookingForm.getOrgId(), addBookingForm.getUserName());
			if(endUser != null){
			/*EndUser bill = new EndUser();
			this.updateContextDoProperties(request, bill);*/
			
			endUser.setCtxId(addBookingForm.getCtxId());
			endUser.setOrgId(addBookingForm.getOrgId());
			endUser.setPropertyId(addBookingForm.getPropertyId());
			endUser.setPurposeId(addBookingForm.getPurposeId());
			endUser.setApplicationId(addBookingForm.getApplicationId());
			endUser.setName(addBookingForm.getName());
			endUser.setOtp(addBookingForm.getOtp());
			endUser.setEmail(addBookingForm.getEmail());
			endUser.setIdProofType(addBookingForm.getIdProofType());
			endUser.setIdProof(addBookingForm.getIdProof());
			endUser.setUserName(addBookingForm.getUserName());
	    	endUser.setBookingDate(addBookingForm.getBookingDate());
	    	endUser.setAddr(addBookingForm.getAddr());
	    	endUser.setMobileNumber(addBookingForm.getMobileNumber());
	    	endUser.setPrice(addBookingForm.getPrice());
	    	endUser.setSlotType(addBookingForm.getSlotType());
	    	endUser.setPaymentUrl(addBookingForm.getPaymentUrl());
	    	endUser.setTotal(addBookingForm.getTotal());
	    	endUser.setPrice(addBookingForm.getPrice());
	    	endUser.setPropertyName(addBookingForm.getPropertyName());
	    	endUser.setPurposeName(addBookingForm.getPurposeName());
	    	endUser.setCgst(addBookingForm.getCgst());
	    	endUser.setSgst(addBookingForm.getSgst());
	    	endUser.setServiceTax(addBookingForm.getServiceTax());
	    	endUser.setMerIdPgRes("NA");
	    	endUser.setMmpTxnPgRes("NA");
	    	endUser.setMerTxnPgRes("NA");
	    	endUser.setProdIdPgRes("NA");
	    	endUser.setBankTxnIdPgRes("NA");
	    	endUser.setfCodePgRes("NA");
	    	endUser.setUserName(addBookingForm.getUserName());
	    	endUser.setBankNamePgRes(addBookingForm.getBankNamePgRes());
	    	endUser.setAuthCodePgRes("NA");
	    	endUser.setIpgTxnIdPgRes("NA");
	    	endUser.setDescPgRes("NA");
	    	endUser.setUdf9PgRes("NA");
	    	endUser.setSurChargePgRes(Float.valueOf("2.48"));
	    	endUser.setCardNumberPgRes(addBookingForm.getCardNumberPgRes());
	    	endUser.setUdf5PgRes("NA");
	    	endUser.setUdf6PgRes("NA");
	    	endUser.setDiscrPgRes("NA");
	    	endUser.setAmtPgRes("NA");
	    	endUser.setResSignPgRes("NA");
	    	endUser.setCreatedUserId(addBookingForm.getCreatedUserId());
	    	endUser.setCreatedTime(addBookingForm.getCreatedTime());
	    	endUser.setPaymentMode(addBookingForm.getPaymentMode());
	    	endUser.setReferredBy(addBookingForm.getReferredBy());
	    	endUser.setPaymentStatus(PaymentStatus.PAID);
	    	endUser.setBookingType(BookingType.MANAGEMENT);
	    	DbService.getInstance().getSetupService().saveOrUpdate(endUser);
			
	    	if(endUser != null){
	    		// Next page  with required parameters
	    		System.out.println("success");
	    		//email sent
		    	Map<String, Object> attr = new HashMap<String, Object>();
				attr.put("bill", endUser);
				attr.put("server_scheme", request.getScheme());
				attr.put("server_hostname", request.getServerName());
				attr.put("server_port", request.getServerPort());
				EmailService.getInstance().sendEmail(endUser.getEmail(), "endUser/bill.vm", "Bill with name " + endUser.getName() + " has been created", attr);
				return new ModelAndView("addBooking/addBookingBill", "addBookingForm", endUser);
				
			}
		}  
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return new ModelAndView("common/error");
	}
	
	


	/*@Override
	public PaginatedResponse getPaginatedListing(HttpServletRequest request, PaginatedRequest req,
			Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}*/

	
	@RequestMapping(value = "/app/addBooking/back/form", method = { RequestMethod.GET })
	public @ResponseBody ModelAndView backAddBookingForm(HttpServletRequest request, EndUser addBookingForm,
			@RequestParam(value = "un", required = true) String un, Model model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			EndUser backForm = new EndUser();
			CaptchaServlet.resetCaptcha(request);
			/*List<Property> filteredList = new ArrayList<Property>();
			List<Property> propertyList = DbService.getInstance().getSetupService()
					.getPropertyListByCtxId(ControllerUtil.getContextId(request));
			if (propertyList != null) {
				for (Property p : propertyList) {
					if (p.getState() == StateType.Active) {
						filteredList.add(p);
					}
				}
			}
			customModalAttributes(model);
			model.addAttribute("propertyList", filteredList);

			List<RentPurpose> purposeList = DbService.getInstance().getSetupService()
					.getRentPurposeListByCtxId(ControllerUtil.getContextId(request));
			List<RentPurpose> purposeListFiltered = new ArrayList<RentPurpose>();
			if (propertyList != null) {
				for (RentPurpose rp : purposeList) {
					if (rp.getState() == StateType.Active)
						purposeListFiltered.add(rp);
				}
			}
			model.addAttribute("purposeList", purposeListFiltered);
			*/
			customModalAttributes(model);
			backForm.setBookingType(BookingType.MANAGEMENT);
			Marquee marquee = DbService.getInstance().getSetupService().getLatestMarqueeByCtxIdAndOrgIdAndType(ControllerUtil.getContextId(request), ControllerUtil.getOrgId(request), MarqueeType.REGISTERPAGE);
			if(marquee != null){
				backForm.setMarquee(marquee.getText());
				logger.error("Marquee text:"+marquee.getText());
			}else
			{
				map.put("message_error", "Failed to load  a marquee text");
			}
			EndUser endUser = DbService.getInstance().getSetupService().getEndUserByCtxIdAndOrgIdAndUserName(ControllerUtil.getContextId(request), ControllerUtil.getOrgId(request), un);
			if(endUser != null) {
				return new ModelAndView("addBooking/backAddBooking", "endUser", endUser);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		// Incase of erros Return to Lising Page
		return new ModelAndView("addBooking/addBookingList", "data", map);
		
	}
		
		//management booking list
		@RequestMapping(value = "/app/addBooking/list", method = { RequestMethod.GET })
		public @ResponseBody ModelAndView addBookingList(HttpServletRequest request, HttpServletResponse response,
				@RequestParam(value = "reqPage", required = false) Long reqPage,
				@RequestParam(value = "pageSize", required = false) Long pageSize, Model model) throws Exception {
			Map<String, Object> map = new HashMap<String, Object>();
			try {
					customModalAttributes(model);
					preProcessPaginatedList(request, EndUser.class, ControllerUtil.getContextId(request), map, reqPage,
							pageSize, requestUriTmpl);
					return new ModelAndView("addBooking/addBookingList", "data", map);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw e;
			}
			
		}
		@Override
		public PaginatedResponse getPaginatedListing(HttpServletRequest httpRequest, PaginatedRequest paginatedRequest,
				final Map<String, Object> map) throws Exception {
			return DbService.getInstance().getSetupService().getAddBookingList(ControllerUtil.getContextId(httpRequest),
					paginatedRequest);
		}
		
		
		//
		
		
		@RequestMapping(value = "/app/addBooking/view", method = { RequestMethod.GET })
		public @ResponseBody ModelAndView viewaddBookingForm(HttpServletRequest request, @RequestParam(value = "id") Long id,
				@RequestParam(value = "reqPage") Long reqPage, @RequestParam(value = "pageSize") Long pageSize, Model model)
						throws Exception {
			Map<String, Object> map = new HashMap<String, Object>();
			try {
				// Global forwarding parameters
				model.addAttribute("reqPage", reqPage);
				model.addAttribute("pageSize", pageSize);
				if (id > 0) {
					// Load the Object with context id
					EndUser dbEndUser = DbService.getInstance().getSetupService()
							.getEndUserByCtxIdAndId(ControllerUtil.getContextId(request), id);
					if (dbEndUser != null) {
						dbEndUser.setPropertyName(CacheService
								.getPropertyById(ControllerUtil.getContextId(request), dbEndUser.getPropertyId())
								.getName());
						dbEndUser.setPurposeName(CacheService
								.getPurposeById(ControllerUtil.getContextId(request), dbEndUser.getPurposeId()).getName());
						map.put("bookingType", DbService.getInstance().getSetupService().getEndUserByCtxIdAndId(ControllerUtil.getContextId(request), id));
						return new ModelAndView("addBooking/viewABooking", "addBookingForm", dbEndUser);
					}
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
			map.put("message_error", "Failed to create PDF file for EndUser " + id);
			preProcessPaginatedList(request, EndUser.class, ControllerUtil.getContextId(request), map, requestUriTmpl);
			return new ModelAndView("addBooking/addBookingList", "data", map);
		}

		@RequestMapping(value = "/app/addBooking/delete", method = { RequestMethod.GET })
		public @ResponseBody ModelAndView deleteEndUser(HttpServletRequest request,
				@RequestParam(value = "idCsv") String idCsv) throws Exception {
			Map<String, Object> map = new HashMap<String, Object>();
			try {
				if (idCsv != null) {
					String[] idArray = idCsv.split(",");
					if (idArray.length > 0) {
						for (String idStr : idArray) {
							try {
								Long id = Long.parseLong(idStr);
								// Load the Object with context id
								EndUser endUser = DbService.getInstance().getSetupService()
										.getEndUserByCtxIdAndId(ControllerUtil.getContextId(request), id);
								if (endUser != null) {
									endUser.setUserState(UserState.INACTIVE);
									DbService.getInstance().getSetupService().saveOrUpdateEndUser(endUser);
									//DbService.getInstance().getSetupService().deleteEndUser(endUser);
									map.put("message_scuuess", "Successfully deleted the requested Demand device.");
								}
								// Check permissions
							} catch (Exception e) {
								map.put("message_error", "Deleting End User failed.");
								logger.error("Failed to delete End User", e);
							}
						}
					}
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
			preProcessPaginatedList(request, EndUser.class, ControllerUtil.getContextId(request), map, requestUriTmpl);
			return new ModelAndView("addBooking/addBookingList", "data", map);
		}
		
}