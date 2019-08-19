package com.bytesedge.bookvenue.controller.forgotPasswd;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.bytesedge.bookvenue.auth.PasswordEncoder;
import com.bytesedge.bookvenue.common.DbService;
import com.bytesedge.bookvenue.common.EmailService;
import com.bytesedge.bookvenue.common.PaginatedRequest;
import com.bytesedge.bookvenue.common.PaginatedResponse;
import com.bytesedge.bookvenue.common.SmsServiceFactory;
import com.bytesedge.bookvenue.common.SmsServiceHorizon;
import com.bytesedge.bookvenue.controller.BaseController;
import com.bytesedge.bookvenue.controller.CaptchaServlet;
import com.bytesedge.bookvenue.model.AuditObject;
import com.bytesedge.bookvenue.model.AuditOperationType;
import com.bytesedge.bookvenue.model.ForgotPasswd;
import com.bytesedge.bookvenue.model.User;
import com.bytesedge.bookvenue.util.StringUtil;
import com.bytesedge.bookvenue.util.UUIDUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("unused")
@Controller
@SessionAttributes({ "forgotPasswdForm" })
public class ForgotPasswdController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(ForgotPasswdController.class);
	private final String otpTemplate = "OTP - Please use the OTP #@OTP@# to register. This OTP will expire in 5 minutes.";
	private final String smsTemplate = "SMS - Successfully registered.";

	@Autowired
	private ForgotPasswdFormValidator validator;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(validator);
	}

	public ForgotPasswdFormValidator getValidator() {
		return validator;
	}

	public void setValidator(ForgotPasswdFormValidator validator) {
		this.validator = validator;
	}

	@Override
	public void customModalAttributes(Model model) {
		model.addAttribute("AuditOperationType", AuditOperationType.values());
		model.addAttribute("AuditObject", AuditObject.values());
	}

	@RequestMapping(value = "/apu/forgotPasswd", method = { RequestMethod.GET })
	public @ResponseBody ModelAndView forgotPasswd(HttpServletRequest request, Model model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		return new ModelAndView("forgotPasswd", "data", map);
	}

	@RequestMapping(value = "/apu/forgotPasswd/create/form", method = { RequestMethod.GET })
	public @ResponseBody ModelAndView createForgotPasswdForm(HttpServletRequest request, Model model) throws Exception {
		try {
			ForgotPasswd forgotPasswdForm = new ForgotPasswd();
			customModalAttributes(model);
			CaptchaServlet.resetCaptcha(request);
			return new ModelAndView("forgotPasswd/createForgotPasswd", "forgotPasswdForm", forgotPasswdForm);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		// Incase of erros Return to Lising Page
		Map<String, Object> map = new HashMap<String, Object>();
		return new ModelAndView("error/error", "data", map);
	}

	@RequestMapping(value = "/apu/forgotPasswd/create", method = { RequestMethod.POST })
	public @ResponseBody ModelAndView createForgotPasswd(HttpServletRequest request,
			@ModelAttribute("forgotPasswdForm") @Valid ForgotPasswd forgotPasswdForm, BindingResult result, Model model)
					throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (result.hasErrors()) {
				customModalAttributes(model);
				for (Object objError : result.getAllErrors()) {
					logger.error("Error:" + objError.toString());
				}
				return new ModelAndView("forgotPasswd/createForgotPasswd", "forgotPasswdForm", forgotPasswdForm);
			} else {
				User dbUser = DbService.getInstance().getUserService().getUserByLoginName(forgotPasswdForm.getEmail());
				if (dbUser != null)
					if (dbUser.getLoginName().equals(forgotPasswdForm.getEmail())
							&& dbUser.getMobileNumber().equals(forgotPasswdForm.getMobileNumber())) {
						this.updateContextDoProperties(request, forgotPasswdForm);
						forgotPasswdForm.setCreatedUserId(1L);
						forgotPasswdForm.setOrgId(1L);
						// Send OTP
						forgotPasswdForm.setOtp(Integer.toString(getRandomNumberInRange(100000, 999999)));
						forgotPasswdForm.setUsername(UUIDUtil.getUuid(24));
						// Send OTP
						String mobileNumber = forgotPasswdForm.getMobileNumber();
						if (!mobileNumber.startsWith("+91")) {
							mobileNumber = "+91" + mobileNumber;
						}
						// send OTP
						// SmsServiceFactory.getInstance().getService().sendSms(mobileNumber,otpTemplate.replace("#@OTP@#", forgotPasswdForm.getOtp()), forgotPasswdForm.getOtp(), forgotPasswdForm.getEmail());
						
						SmsServiceFactory.getInstance().getService().sendSms(mobileNumber,
								otpTemplate.replace("#@OTP@#", forgotPasswdForm.getOtp()));
						logger.error("OTP = " + forgotPasswdForm.getOtp());
						ForgotPasswd dbObject = (ForgotPasswd) DbService.getInstance().getSetupService()
								.saveOrUpdateForgotPasswd(forgotPasswdForm);
						return new ModelAndView("forgotPasswd/forgotPasswdOtp", "forgotPasswdForm", dbObject);
					} else{
						map.put("message_error", "User not found");
						return new ModelAndView("forgotPasswd/createForgotPasswd", "data", map);
					}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			// In case of errors Return to Listing Page
			map.put("message_error", "Failed to find user.");
			return new ModelAndView("error/error", "data", map);
		}
		return new ModelAndView("forgotPasswd/createForgotPasswd", "data", map);
	}

	@RequestMapping(value = "/apu/forgotPasswd/otp", method = { RequestMethod.POST })
	public @ResponseBody ModelAndView otp(HttpServletRequest request,
			@ModelAttribute("forgotPasswdForm")  @Valid ForgotPasswd forgotPasswdForm, BindingResult result, Model model)
					throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (!(forgotPasswdForm.getUsername() == null)) {
				if (result.hasErrors()) {
					customModalAttributes(model);
					return new ModelAndView("forgotPasswd/forgotPasswdOtp", "forgotPasswdForm", forgotPasswdForm);
				} else {
					logger.error("un=" + forgotPasswdForm.getCreatedUserName());
					logger.error("OTP=" + forgotPasswdForm.getPassword());
					CaptchaServlet.resetCaptcha(request);
					// load ForgotPasswd by username
					ForgotPasswd dbObject = DbService.getInstance().getSetupService()
							.getForgotPasswdByEmail(forgotPasswdForm.getEmail());
					if (dbObject.getOtp() != null && dbObject.getOtp().equals(forgotPasswdForm.getPswd())) {
						return new ModelAndView("forgotPasswd/updateForgotPassword", "forgotPasswdForm", dbObject);
					} else {
						result.addError(new ObjectError("pswd", "OTP did not mach"));
						return new ModelAndView("forgotPasswd/forgotPasswdOtp", "forgotPasswdForm", forgotPasswdForm);
					}
				}
			} else {
				result.addError(new ObjectError("Username", "Username is not found"));
				return new ModelAndView("forgotPasswd/createForgotPasswd", "forgotPasswdForm", forgotPasswdForm);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			// In case of errors Return to Listing Page
			map.put("message_error", "Failed to create a new ForgotPasswd.");
			return new ModelAndView("error/error", "data", map);
		}
	}

	private static int getRandomNumberInRange(int min, int max) {
		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

	@RequestMapping(value = "/apu/forgotPasswd/update", method = { RequestMethod.POST })
	public @ResponseBody ModelAndView updateForgotPasswrd(HttpServletRequest request,
			@ModelAttribute("forgotPasswdForm") @Valid  ForgotPasswd forgotPasswdForm, BindingResult result, Model model)
					throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (result.hasErrors()) {
				customModalAttributes(model);
				result.addError(new ObjectError("password", "Password and Confirm Password should match"));
				return new ModelAndView("forgotPasswd/updateForgotPassword", "forgotPasswdForm", forgotPasswdForm);
			} else {
				/*if(!StringUtil.isEmpty(forgotPasswdForm.getPassword()) && !StringUtil.isEmpty(forgotPasswdForm.getConfirmPassword())
					&& !forgotPasswdForm.getConfirmPassword().equals(forgotPasswdForm.getPassword())  ) {
					customModalAttributes(model);
					return new ModelAndView("forgotPasswd/updateForgotPassword", "forgotPasswdForm", forgotPasswdForm);
				}*/
				
				User dbUser = DbService.getInstance().getUserService().getUserByLoginName(forgotPasswdForm.getEmail());
				if (dbUser != null) {
					ObjectMapper jsonApi = new ObjectMapper();
					String oldDbObject = jsonApi.writeValueAsString(dbUser);
					PasswordEncoder encoder = new PasswordEncoder();
					dbUser.setLoginPassword(encoder.encode(forgotPasswdForm.getPassword()));
					User dbUserObject = (User) DbService.getInstance().getUserService().saveOrUpdateUser(dbUser);
					// Remove Forgot password object
					ForgotPasswd dbForgotPasswdObject = DbService.getInstance().getSetupService()
							.getForgotPasswdByEmail(forgotPasswdForm.getEmail());
					if(dbForgotPasswdObject != null) {
						DbService.getInstance().getSetupService().delete(dbForgotPasswdObject);
					}
					// send OTP
					//SmsServiceHorizon.sendSms(dbUser.getMobileNumber(),	smsTemplate.replace("Helloooo", smsTemplate));
					// Send eMail
					EmailService.sendEmail(dbUser.getEmail(), "******* WELCOME TO SHILPARAMAM ********",
							"Name : " + dbUser.getDisplayName());
					// Pre process the formBean
					/*this.saveAuditObject(request, oldDbObject,  jsonApi.writeValueAsString(dbUserObject), AuditOperationType.UPDATE,
							AuditObject.ForgotPasswd);*/
					customModalAttributes(model);
					map.put("message_success", "A new ForgotPasswd " + dbUserObject.getId() + " has been created.");
					return new ModelAndView("successPasswdUpdate","forgotPasswdForm", forgotPasswdForm);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ModelAndView("error/error");
		}
		return new ModelAndView("error/error");
	}

	@Override
	public void preProcessPaginatedList(HttpServletRequest request, Class<?> clazz, Long ctxId, Map<String, Object> map,
			Long reqPage, Long pageSize, String requestUriTmpl) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PaginatedResponse getPaginatedListing(HttpServletRequest request, PaginatedRequest req,
			Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
}
