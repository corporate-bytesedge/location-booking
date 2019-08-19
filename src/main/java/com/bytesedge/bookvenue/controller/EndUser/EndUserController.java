package com.bytesedge.bookvenue.controller.EndUser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.poi.ss.formula.functions.Replace;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.bytesedge.bookvenue.common.SmsServiceFactory;
import com.bytesedge.bookvenue.controller.BaseController;
import com.bytesedge.bookvenue.controller.CaptchaServlet;
import com.bytesedge.bookvenue.controller.ControllerUtil;
import com.bytesedge.bookvenue.controller.Invoice.InvoiceFailedXlsxStreamingView;
import com.bytesedge.bookvenue.model.AuditObject;
import com.bytesedge.bookvenue.model.AuditOperationType;
import com.bytesedge.bookvenue.model.BookingType;
import com.bytesedge.bookvenue.model.Context;
import com.bytesedge.bookvenue.model.EndUser;
import com.bytesedge.bookvenue.model.IdProofType;
import com.bytesedge.bookvenue.model.Marquee;
import com.bytesedge.bookvenue.model.MarqueeType;
import com.bytesedge.bookvenue.model.Organization;
import com.bytesedge.bookvenue.model.PaymentGatewayDetails;
import com.bytesedge.bookvenue.model.PaymentMode;
import com.bytesedge.bookvenue.model.PaymentStatus;
import com.bytesedge.bookvenue.model.Property;
import com.bytesedge.bookvenue.model.PropertyRentPrice;
import com.bytesedge.bookvenue.model.RentPurpose;
import com.bytesedge.bookvenue.model.SessionOrgContext;
import com.bytesedge.bookvenue.model.SlotType;
import com.bytesedge.bookvenue.model.StateType;
import com.bytesedge.bookvenue.model.UserState;
import com.bytesedge.bookvenue.pg.PaymentGatewayReq;
import com.bytesedge.bookvenue.pg.PaymentGatewaySignatureGenerateAtom;
import com.bytesedge.bookvenue.util.UUIDUtil;

@Controller
@SessionAttributes({ "endUserForm" })
public class EndUserController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(EndUserController.class);
	private final String requestUriTmpl = "/app/endUser/list?reqPage=%d&pageSize=%d";
	private final String otpTemplate = "OTP - Please use the OTP #@OTP@# to register to Shilparamam Venue Booking Application. This OTP will expire in 10 minutes.";
	private final String smsTemplate = "SMS - You are booking venue successfully done.";

	@Autowired
	private EndUserFormValidator validator;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(validator);
	}

	public EndUserFormValidator getValidator() {
		return validator;
	}

	public void setValidator(EndUserFormValidator validator) {
		this.validator = validator;
	}

	@Override
	public void customModalAttributes(Model model) {
		model.addAttribute("AuditOperationType", AuditOperationType.values());
		model.addAttribute("AuditObject", AuditObject.values());
		model.addAttribute("IdProofType", IdProofType.values());
		model.addAttribute("PaymentStatus", PaymentStatus.values());
		model.addAttribute("PaymentMode", PaymentMode.values());
		model.addAttribute("BookingType", BookingType.values());
		model.addAttribute("SlotType", SlotType.values());
		model.addAttribute("StateType", StateType.values());
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
				for (EndUser endUser : list) {
					endUser.setOrgName(CacheService
							.getOrganizationById(ControllerUtil.getContextId(request), endUser.getOrgId()).getName());
					endUser.setPropertyName(CacheService
							.getPropertyById(ControllerUtil.getContextId(request), endUser.getPropertyId()).getName());
					endUser.setPurposeName(CacheService
							.getPurposeById(ControllerUtil.getContextId(request), endUser.getPurposeId()).getName());
				}
			}
		}
	}

	/*@InitBinder
	public void bindingPreparation(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		CustomDateEditor orderDateEditor = new CustomDateEditor(dateFormat, true);
		binder.registerCustomEditor(Date.class, orderDateEditor);
	}*/

	@RequestMapping(value = "/app/endUser/list", method = { RequestMethod.GET })
	public @ResponseBody ModelAndView endUserList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "reqPage", required = false) Long reqPage,
			@RequestParam(value = "pageSize", required = false) Long pageSize, Model model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			SessionOrgContext se = ControllerUtil.getValidSessionContext(request);
			if (se != null && se.getOrgId() != null && se.getOrgId() > 0L) {
				customModalAttributes(model);
				preProcessPaginatedList(request, EndUser.class, ControllerUtil.getContextId(request), map, reqPage,
						pageSize, requestUriTmpl);
				return new ModelAndView("endUser/endUserList", "data", map);
			} else {
				return new ModelAndView("common/selectOrg", "orgType", "Organisation");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}

	@RequestMapping(value = "/app/endUser/list/export", method = { RequestMethod.GET }, produces = "application/xlsx")
	public @ResponseBody ModelAndView endUserListExportXlsx(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "reqPage", required = false) Long reqPage,
			@RequestParam(value = "pageSize", required = false) Long pageSize,
			@RequestParam(value = "exportType", required = false) String exportType) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			PaginatedResponse res = processPaginatedList(EndUser.class, request, map, reqPage, pageSize,
					requestUriTmpl);
			if (res != null) {
				map.put("list", res.getResultList());
				map.put("viewClass", EndUserXlsxStreamingView.class.getName());
				map.put("fileName", "Shilparamam-EndUser-List.xlsx");
				return new ModelAndView("xlxs", "map", map);
			} else {
				map.put("message_error", "Failed to create MS Excel file for EndUser report.");
				return new ModelAndView("endUser/endUserList", "data", map);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public PaginatedResponse getPaginatedListing(HttpServletRequest httpRequest, PaginatedRequest paginatedRequest,
			final Map<String, Object> map) throws Exception {
		return DbService.getInstance().getSetupService().getEndUserList(ControllerUtil.getContextId(httpRequest),
				paginatedRequest);
	}

	@RequestMapping(value = "/apu/endUserBill/download/pdf", method = {
			RequestMethod.GET }, produces = "application/pdf")
	public @ResponseBody ModelAndView endUserDownloadPdf(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "id", required = true) Long id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			EndUser dbEndUser = DbService.getInstance().getSetupService()
					.getEndUserByCtxIdAndId(ControllerUtil.getContextId(request), id);
			if (dbEndUser != null) {
				map.put("obj", dbEndUser);
				map.put("viewClass", EndUserPdfStreamingView.class.getName());
				map.put("fileName", "Shilparamam-Invoice-" + id + ".pdf");
				map.put("tmplName", "/WEB-INF/res/tmpl/report/bill_view.pdf");
				return new ModelAndView("pdf", "map", map);
			} else {
				map.put("message_error", "Failed to create PDF file for EndUser " + id);
				preProcessPaginatedList(request, EndUser.class, ControllerUtil.getContextId(request), map,
						requestUriTmpl);
				return new ModelAndView("endUser/endUserList", "data", map);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}

	@RequestMapping(value = "/apu/registrationPage", method = { RequestMethod.GET })
	public @ResponseBody ModelAndView registrationPage(HttpServletRequest request, Model model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		Context context = CacheService.getContextByUrl(request.getServerName());
		if(context != null) {
			ControllerUtil.populateHomePageContextData(context, map);
			
			// Add a Parameter to HttpSession
			HttpSession session = (HttpSession)request.getSession();
			session.setAttribute("ctxCode", context.getCode());
			session.setAttribute("ctxId", context.getId());
			// marquee text
			Marquee marquee = DbService.getInstance().getSetupService().getLatestMarqueeByCtxIdAndOrgIdAndType(
					ControllerUtil.getContextId(request), ControllerUtil.getOrgId(request), MarqueeType.REGISTERPAGE);
			if (marquee.getText() != null) {
				model.addAttribute("marqueeText", marquee.getText());
				logger.error("Marquee text:" + marquee.getText());
			} else {
				map.put("message_error", "Failed to load  a marquee text");
			}
			
			return new ModelAndView("registrationPage", "data", map);
		}
		return new ModelAndView("defaultLogin", "data", map);
	}

	@RequestMapping(value = "/apu/endUser/create/form", method = { RequestMethod.GET })
	public @ResponseBody ModelAndView createEndUserForm(HttpServletRequest request,
			@RequestParam(value = "venueId", required = false) Long venueId, Model model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			EndUser endUserForm = new EndUser();
			/*Marquee marquee = DbService.getInstance().getSetupService().getLatestMarqueeByCtxIdAndOrgIdAndType(
					ControllerUtil.getContextId(request), ControllerUtil.getOrgId(request), MarqueeType.REGISTERPAGE);
			if (marquee.getText() != null) {
				endUserForm.setMarquee(marquee.getText());
				logger.error("Marquee text:" + marquee.getText());
			} else {
				map.put("message_error", "Failed to load  a marquee text");
			}*/
			
		// Bill dates
			Property dbObj = DbService.getInstance().getSetupService().getPropertyByCtxIdAndId(ControllerUtil.getContextId(request), venueId);
			if(dbObj != null) {
				endUserForm.setVenueDaysLimit(dbObj.getVenueDaysLimit());
			}
			List<EndUser> filteredBillList = new ArrayList<EndUser>();
			List<EndUser> billList = DbService.getInstance().getSetupService().getBlockedBillByCtxIdAndOrgIdAndPropertyId(
					ControllerUtil.getContextId(request), ControllerUtil.getOrgId(request), venueId);
			String pattern = "dd-MM-yyyy";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			if (billList != null) {
				for (EndUser bill : billList) {
					if (bill.getPaymentStatus() == PaymentStatus.PAID){
						filteredBillList.add(bill);
						if(endUserForm.getBillList() == null)
							endUserForm.setBillList(simpleDateFormat.format(bill.getBookingDate()));
						else
							endUserForm.setBillList(endUserForm.getBillList()+ ", " + simpleDateFormat.format(bill.getBookingDate()));
					logger.error("Bill:Exp-Dates:" + simpleDateFormat.format(bill.getBookingDate()));
				}
			}
			}
			model.addAttribute("billList", filteredBillList);
			customModalAttributes(model);
			return new ModelAndView("endUser/createEndUser", "endUserForm", endUserForm);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		// Incase of erros Return to Lising Page
		return new ModelAndView("endUser/endUserList", "data", map);
	}

	@RequestMapping(value = "/apu/endUser/otp", method = { RequestMethod.POST })
	public @ResponseBody ModelAndView otp(HttpServletRequest request,
			@ModelAttribute("endUserForm")  @Valid EndUser endUserForm, BindingResult result, Model model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (result.hasErrors()) {
				customModalAttributes(model);
				return new ModelAndView("endUser/endUserOtp", "endUserForm", endUserForm);
			} else {
				logger.error("un=" + endUserForm.getUserName());
				logger.error("OTP=" + endUserForm.getPassword());
				if (!(endUserForm.getUserName() == null)) {
					// load EndUser by username
					EndUser dbObject = DbService.getInstance().getSetupService().getEndUserByCtxIdAndOrgIdAndUserName(
							ControllerUtil.getContextId(request), endUserForm.getOrgId(), endUserForm.getUserName());
					if (dbObject.getOtp().equals(endUserForm.getPassword())) {
						dbObject.setPropertyName(CacheService
								.getPropertyById(ControllerUtil.getContextId(request), dbObject.getPropertyId())
								.getName());
						dbObject.setPurposeName(CacheService
								.getPurposeById(ControllerUtil.getContextId(request), dbObject.getPurposeId())
								.getName());
						this.updateContextDoProperties(request, endUserForm);
						PropertyRentPrice prp = DbService.getInstance().getSetupService()
								.getPropertyRentPriceByCtxIdAndOrgIdAndPropertyIdAndPurposeId(
										ControllerUtil.getContextId(request), ControllerUtil.getOrgId(request),
										endUserForm.getPropertyId(), endUserForm.getPurposeId());
						final String invoicePrefix = "INV-" + Year.now().getValue() + "-";
						Long maxId = DbService.getInstance().getSetupService().getApplicationDocMaxId();
						if (maxId == null) {
							maxId = 0L;
						}
						maxId = maxId + 1;
						endUserForm.setApplicationId(invoicePrefix + maxId);
						// Checking Current booking Date is weekday Or weekend
						Calendar c1 = Calendar.getInstance();
						c1.setTime(dbObject.getBookingDate());
						if ((c1.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
								|| (Calendar.DAY_OF_WEEK == Calendar.SUNDAY)) {
							dbObject.setPrice(DbService.getInstance().getSetupService()
									.getPropertyRentPriceByCtxIdAndOrgIdAndPropertyIdAndPurposeIdAndSlotType(
											ControllerUtil.getContextId(request), endUserForm.getOrgId(),
											dbObject.getPropertyId(), dbObject.getPurposeId(), SlotType.WEEKEND).getPrice());
						} else {
							dbObject.setPrice(DbService.getInstance().getSetupService()
									.getPropertyRentPriceByCtxIdAndOrgIdAndPropertyIdAndPurposeIdAndSlotType(
											ControllerUtil.getContextId(request), endUserForm.getOrgId(),
											dbObject.getPropertyId(), dbObject.getPurposeId(), SlotType.WEEKDAY).getPrice());
						}
						dbObject.setServiceTax((prp.getServiceTax() * dbObject.getPrice()) / 100);
						dbObject.setCgst((prp.getCgst() * dbObject.getPrice()) / 100);
						dbObject.setSgst((prp.getSgst() * dbObject.getPrice()) / 100);
						dbObject.setTotal(dbObject.getPrice()+ (((prp.getCgst() + prp.getSgst() + prp.getServiceTax()) * dbObject.getPrice())	/ 100));
						dbObject.setPropertyRentPriceId(prp.getId());
						PaymentGatewayDetails pgd = DbService.getInstance().getSetupService()
								.getPaymentGatewayDetailsByCtxIdAndOrgId(ControllerUtil.getContextId(request),
										endUserForm.getOrgId());
						PaymentGatewayReq req = PaymentGatewaySignatureGenerateAtom.getPaymentGatewayReq(
								pgd.getReqHashKey(), pgd.getLogin(), pgd.getPassword(), pgd.getTtype(), pgd.getProdId(),
								dbObject.getTotal(), pgd.getTxnCurr(),
								dbObject.getPrice() * dbObject.getServiceTax() / 100.00F, dbObject.getUserName(),
								Long.toString(System.currentTimeMillis()), new Date(System.currentTimeMillis()),
								dbObject.getName(), pgd.getMerchantUrl(), dbObject.getEmail(),
								dbObject.getMobileNumber());
						dbObject.setPaymentUrl(req.getPaymentUrl());
						logger.error(req.getPaymentUrl());
						dbObject.setPaymentUrl(req.getPaymentUrl());
						DbService.getInstance().getSetupService().saveOrUpdateEndUser(dbObject);
						// email sent
						/*Map<String, Object> attr = new HashMap<String, Object>();
						attr.put("invoice", dbObject);
						attr.put("server_scheme", request.getScheme());
						attr.put("server_hostname", request.getServerName());
						attr.put("server_port", request.getServerPort());
						EmailService.getInstance().sendEmail(dbObject.getEmail(), "endUser/invoice.vm",
								"Invoice with name " + dbObject.getName() + " has been created", attr);
						SmsServiceFactory.getInstance().getService().sendSms(dbObject.getMobileNumber(),
								smsTemplate.replace("Helloooo", smsTemplate));*/
						//return new ModelAndView("endUser/viewEndUser", "endUserForm", dbObject);
						Long id=dbObject.getId();
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
								return new ModelAndView("endUser/loadPaymentGateway", "endUserForm", dbEndUser);
							}else {
								// send to OTP form with required parameters
								return new ModelAndView("endUser/otpEndUser", "endUserForm", endUserForm);
							}
						} else {
							// send to OTP form with required parameters
							return new ModelAndView("endUser/otpEndUser", "endUserForm", endUserForm);
						}
						
					} else {
						// send to OTP form with required parameters
						return new ModelAndView("endUser/otpEndUser", "endUserForm", endUserForm);
					}
				} else {
					// Error page Terminal Transaction
					return new ModelAndView("endUser/createEndUser", "endUserForm", endUserForm);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			// In case of errors Return to Listing Page
			map.put("message_error", "Failed to create a new EndUser.");

			// Error page Terminal Transaction
			return new ModelAndView("common/error");
		}
	}

	private static int getRandomNumberInRange(int min, int max) {
		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

	@RequestMapping(value = "/apu/endUser/reSendOtp", method = { RequestMethod.GET })
	public void createEndUser(HttpServletRequest request,
			@RequestParam(value = "username", required = true) String username) throws Exception {
		try {
			EndUser dbObject = (EndUser) DbService.getInstance().getSetupService().getEndUserByCtxIdAndOrgIdAndUserName(
					ControllerUtil.getContextId(request), ControllerUtil.getOrgId(request), username);
			if (dbObject != null) {
				dbObject.setOtp(Integer.toString(getRandomNumberInRange(100000, 999999)));
				dbObject = (EndUser) DbService.getInstance().getSetupService().saveOrUpdateEndUser(dbObject);
				SmsServiceFactory.getInstance().getService().sendSms(dbObject.getMobileNumber(),
						otpTemplate.replace("#@OTP@#", dbObject.getOtp()));
				logger.error("OTP = " + dbObject.getOtp());
				logger.error("Date = " + dbObject.getBookingDate());
			} else {
				logger.error("Inavlid End user : " + username);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	@RequestMapping(value = "/apu/endUser/create", method = { RequestMethod.POST })
	public @ResponseBody ModelAndView createEndUser(HttpServletRequest request,
			@ModelAttribute("endUserForm") @Valid EndUser endUserForm,
			BindingResult result, Model model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (result.hasErrors()) {
				Marquee marquee = DbService.getInstance().getSetupService().getLatestMarqueeByCtxIdAndOrgIdAndType(
						ControllerUtil.getContextId(request), ControllerUtil.getOrgId(request),
						MarqueeType.REGISTERPAGE);
				if (marquee.getText() != null) {
					endUserForm.setMarquee(marquee.getText());
					logger.error("Marquee text:" + marquee.getText());
				} else {
					map.put("message_error", "Failed to load  a marquee text");
				}
				// Bill dates
				List<EndUser> filteredBillList = new ArrayList<EndUser>();
				List<EndUser> billList = DbService.getInstance().getSetupService().getBlockedBillByCtxIdAndOrgId(
						ControllerUtil.getContextId(request), ControllerUtil.getOrgId(request));
				String pattern = "dd-MM-yyyy";
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
				if (billList != null) {
					for (EndUser bill : billList) {
						if (bill.getPaymentStatus() == PaymentStatus.PAID) {
							filteredBillList.add(bill);
							if (endUserForm.getBillList() == null)
								endUserForm.setBillList(simpleDateFormat.format(bill.getBookingDate()));
							else
								endUserForm.setBillList(endUserForm.getBillList() + ", "
										+ simpleDateFormat.format(bill.getBookingDate()));
							logger.error("Bill:Exp-Dates:" + simpleDateFormat.format(bill.getBookingDate()));
						}
					}
				}
				model.addAttribute("billList", filteredBillList);
				customModalAttributes(model);
				return new ModelAndView("endUser/createEndUser", "endUserForm", endUserForm);
			} else {
				this.updateContextDoProperties(request, endUserForm);
				final String invoicePrefix = "INV-" + Year.now().getValue() + "-";
				Long maxId = DbService.getInstance().getSetupService().getApplicationDocMaxId();
				if (maxId == null) {
					maxId = 0L;
				}
				maxId = maxId + 1;
				endUserForm.setApplicationId(invoicePrefix + maxId);

				// Craeted User Id
				endUserForm.setCreatedUserId(1L);
				// Update org Id
				Context context = CacheService.getContextByUrl(request.getServerName());
				if (context != null) {
					Organization org = CacheService.getOrgByCtxId(context.getId());
					if (org != null) {
						endUserForm.setOrgId(org.getId());
					}
				}
				// Generate and set a unique String
				endUserForm.setUserName(UUIDUtil.getUuid(24));
				logger.error("Date = " + endUserForm.getBookingDate());
				endUserForm.setCtxId(ControllerUtil.getContextId(request));
				EndUser dbObject = (EndUser) DbService.getInstance().getSetupService().saveOrUpdateEndUser(endUserForm);
				dbObject.setPurposeName(CacheService.getPurposeById(ControllerUtil.getContextId(request), dbObject.getPurposeId()).getName());
				dbObject.setPropertyName(CacheService.getPropertyById(ControllerUtil.getContextId(request), dbObject.getPropertyId()).getName());
				map.put("message_success", "A new EndUser " + dbObject.getId() + " has been created.");
				return new ModelAndView("endUser/viewEndUser", "endUserForm", dbObject);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			// In case of errors Return to Listing Page
			map.put("message_error", "Failed to create a new EndUser.");
			return new ModelAndView("endUser/createEndUser", "data", map);
		}
	}

	@RequestMapping(value = "/app/endUser/view/form", method = { RequestMethod.GET })
	public @ResponseBody ModelAndView viewEndUserForm(HttpServletRequest request, @RequestParam(value = "id") Long id,
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
					return new ModelAndView("endUser/viewEUser", "endUserForm", dbEndUser);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		map.put("message_error", "Failed to create PDF file for EndUser " + id);
		preProcessPaginatedList(request, EndUser.class, ControllerUtil.getContextId(request), map, requestUriTmpl);
		return new ModelAndView("endUser/endUserList", "data", map);
	}
	
	@RequestMapping(value = "/apu/sendOtp", method = { RequestMethod.GET })
	public @ResponseBody ModelAndView otpEndUserForm(HttpServletRequest request,
			@RequestParam(value = "un", required = false) String un, Model model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			EndUser dbObj = DbService.getInstance().getSetupService().getEndUserByCtxIdAndOrgIdAndUserName(ControllerUtil.getContextId(request), ControllerUtil.getOrgId(request), un);
			if(dbObj != null){
				// Send OTP
				dbObj.setOtp(Integer.toString(getRandomNumberInRange(100000, 999999)));
				// Send OTP
				String mobileNumber = dbObj.getMobileNumber();
				if (!mobileNumber.startsWith("+91")) {
					mobileNumber = "+91" + mobileNumber;
				}
				DbService.getInstance().getSetupService().saveOrUpdate(dbObj);
				SmsServiceFactory.getInstance().getService().sendSms(mobileNumber,
						otpTemplate.replace("#@OTP@#", dbObj.getOtp()));
				logger.error("OTP = " + dbObj.getOtp());
			}
			customModalAttributes(model);
			return new ModelAndView("endUser/endUserOtp","endUserForm", dbObj);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		// Incase of erros Return to Lising Page
		return new ModelAndView("common/error");
	}
	
	@RequestMapping(value = "/apu/endUser/back/form", method = { RequestMethod.GET })
	public @ResponseBody ModelAndView backEndUserForm(HttpServletRequest request, 
			@RequestParam(value = "un", required = true) String un, Model model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			EndUser backForm = new EndUser();
			CaptchaServlet.resetCaptcha(request);
			backForm.setBookingType(BookingType.REGISTER);
			Marquee marquee = DbService.getInstance().getSetupService().getLatestMarqueeByCtxIdAndOrgIdAndType(ControllerUtil.getContextId(request), ControllerUtil.getOrgId(request), MarqueeType.REGISTERPAGE);
			if(marquee != null){
				backForm.setMarquee(marquee.getText());
				logger.error("Marquee text:"+marquee.getText());
			}else
			{
				map.put("message_error", "Failed to load the marquee text");
			}
			EndUser endUser = DbService.getInstance().getSetupService().getEndUserByCtxIdAndOrgIdAndUserName(ControllerUtil.getContextId(request), ControllerUtil.getOrgId(request), un);
			if(endUser != null) {
				customModalAttributes(model);
				return new ModelAndView("endUser/backCreateEndUser", "endUser", endUser);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		// Incase of erros Return to Lising Page
		return new ModelAndView("endUser/createEndUser", "data", map);
		
	}

	@RequestMapping(value = "/app/endUser/delete", method = { RequestMethod.GET })
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
		return new ModelAndView("endUser/endUserList", "data", map);
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/apu/loadPaymentGateway", method = { RequestMethod.GET })
	public @ResponseBody ModelAndView loadPaymentGateway(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "id") Long id, Model model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// Global forwarding parameters
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
					return new ModelAndView("endUser/loadPaymentGateway", "endUserForm", dbEndUser);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		map.put("message_error", "Failed to create PDF file for EndUser " + id);
		return new ModelAndView("endUser/endUserList", "data", map);
	}
	
	@RequestMapping(value = "/app/canceledTransactions/list", method = { RequestMethod.GET })
	public @ResponseBody ModelAndView canceledTransactionsList(HttpServletRequest request, HttpServletResponse response,
			 Model model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			SessionOrgContext se = ControllerUtil.getValidSessionContext(request);
			if (se != null && se.getOrgId() != null && se.getOrgId() > 0L) {
				customModalAttributes(model);
				List<EndUser> endUserList = DbService.getInstance().getSetupService().getEndUserListByCtxId(ControllerUtil.getContextId(request));
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
					
				}
				return new ModelAndView("endUser/canceledTransactionsList", "data", map);
			} else {
				return new ModelAndView("common/selectOrg", "orgType", "Organisation");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@RequestMapping(value = "/app/cancelled/list/export", method = {RequestMethod.GET}, produces = "application/xlsx")
	public @ResponseBody ModelAndView endUserCancelledListExportXlsx(HttpServletRequest request, HttpServletResponse response,
			//@RequestParam(value="reqPage", required=false) Long reqPage, 
			//@RequestParam(value="pageSize", required=false) Long pageSize,
			@RequestParam(value="exportType", required=false) String exportType) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
			try {
				map.put("viewClass", EndUserCancelledXlsxStreamingView.class.getName());
				map.put("fileName", "Shilparamam-Cancelled Transactions-List.xlsx");
				return new ModelAndView("xlxs", "map", map);
			} catch (Exception e) { 
				logger.error(e.getMessage(), e);
				throw e;
			}
		}
	
	
	@RequestMapping(value = "/apu/endUser/getVenueDetails", method = { RequestMethod.GET })
	public @ResponseBody JSONObject endUserGetVenueDetails(HttpServletRequest request,
			@RequestParam(value = "venueId", required = false) Long venueId, Model model) throws Exception {
		JSONObject venueDetails = new JSONObject();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			EndUser endUserForm = new EndUser();
			
			
		// Bill dates
			Property dbObj = DbService.getInstance().getSetupService().getPropertyByCtxIdAndId(ControllerUtil.getContextId(request), venueId);
			if(dbObj != null) {
				endUserForm.setVenueDaysLimit(dbObj.getVenueDaysLimit());
			}
			//List<EndUser> filteredBillList = new ArrayList<EndUser>();
			List<EndUser> billList = DbService.getInstance().getSetupService().getBlockedBillByCtxIdAndOrgIdAndPropertyId(
					ControllerUtil.getContextId(request), ControllerUtil.getOrgId(request), venueId);
			String pattern = "dd-MM-yyyy";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			if (billList != null) {
				for (EndUser bill : billList) {
					if (bill.getPaymentStatus() == PaymentStatus.PAID){
						//filteredBillList.add(bill);
						if(endUserForm.getBillList() == null)
							endUserForm.setBillList(simpleDateFormat.format(bill.getBookingDate()));
						else
							endUserForm.setBillList(endUserForm.getBillList()+ ", " + simpleDateFormat.format(bill.getBookingDate()));
					logger.error("Bill:Exp-Dates:" + simpleDateFormat.format(bill.getBookingDate()));
				}
			}
			}
			//model.addAttribute("billList", filteredBillList);
			customModalAttributes(model);
			
			venueDetails.put("venueDaysLimit", endUserForm.getVenueDaysLimit());
			venueDetails.put("billList", endUserForm.getBillList());
			return venueDetails;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		// Incase of erros Return to Lising Page
		return venueDetails;
	}

}
