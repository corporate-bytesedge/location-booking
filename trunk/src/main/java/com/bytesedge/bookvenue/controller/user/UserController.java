package com.bytesedge.bookvenue.controller.user;

import java.util.ArrayList;
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

import com.bytesedge.bookvenue.auth.PasswordEncoder;
import com.bytesedge.bookvenue.common.CacheService;
import com.bytesedge.bookvenue.common.DbService;
import com.bytesedge.bookvenue.common.EmailService;
import com.bytesedge.bookvenue.common.PaginatedRequest;
import com.bytesedge.bookvenue.common.PaginatedResponse;
import com.bytesedge.bookvenue.controller.BaseController;
import com.bytesedge.bookvenue.controller.CaptchaServlet;
import com.bytesedge.bookvenue.controller.ControllerUtil;
import com.bytesedge.bookvenue.model.AuditObject;
import com.bytesedge.bookvenue.model.AuditOperationType;
import com.bytesedge.bookvenue.model.GenderType;
import com.bytesedge.bookvenue.model.User;
import com.bytesedge.bookvenue.model.UserRole;
import com.bytesedge.bookvenue.model.UserState;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@SessionAttributes({"userForm"})
public class UserController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	private final String requestUriTmpl = "/app/user/list?reqPage=%d&pageSize=%d";
	
	@Autowired
	private UserFormValidator validator;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(validator);
	}
	
	public UserFormValidator getValidator() {
		return validator;
	}

	public void setValidator(UserFormValidator validator) {
		this.validator = validator;
	}

	@Override
	public void customModalAttributes(Model model) {
		model.addAttribute("UserState", UserState.values());
		model.addAttribute("AuditOperationType", AuditOperationType.values());
		model.addAttribute("AuditObject", AuditObject.values());
		model.addAttribute("GenderType", GenderType.values());
	}
	
	@Override
	public PaginatedResponse getPaginatedListing(HttpServletRequest httpRequest, PaginatedRequest paginatedRequest, final Map<String, Object> map) throws Exception {
		return DbService.getInstance().getUserService().getUserList( ControllerUtil.getContextId(httpRequest), paginatedRequest);
	}

	
	@SuppressWarnings("unchecked")
	public void preProcessPaginatedList(HttpServletRequest request, final Class<?> clazz, final Long ctxId, 
			final Map<String, Object> map, final Long reqPage, 
			final Long pageSize, final String requestUriTmpl) throws Exception {
		processPaginatedList(User.class, request, map, reqPage, pageSize, requestUriTmpl);
		PaginatedResponse paginatedResponse = (PaginatedResponse) map.get("paginatedResponse");
		if(paginatedResponse != null) {
			List<User> userList = (List<User>) paginatedResponse.getResultList();
			if(userList != null && !userList.isEmpty()) {
				for(User user : userList) {
					user.setAadharId(user.getAadharId().replaceAll("\\d(?=(?:\\D*\\d){4})", "*"));
					user.setMobileNumber(user.getMobileNumber().replaceAll("\\d(?=(?:\\D*\\d){4})", "*"));
					user.setRoleName(CacheService.getUserRoleById(ControllerUtil.getContextId(request), user.getRoleId()).getName());
				}
			}
		}
	}
	
	@RequestMapping(value = "/app/user/list", method = {RequestMethod.GET})
	public @ResponseBody ModelAndView  userList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="reqPage", required=false) Long reqPage, @RequestParam(value="pageSize", 
			required=false) Long pageSize) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			preProcessPaginatedList(request, User.class, ControllerUtil.getContextId(request), map, reqPage, pageSize, requestUriTmpl);
			return new ModelAndView("user/userList", "data", map);
		}  catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@RequestMapping(value = "/app/user/list/export", method = {RequestMethod.GET}, produces = "application/xlsx")
	public @ResponseBody ModelAndView userListExportXlsx(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="reqPage", required=false) Long reqPage, @RequestParam(value="pageSize", required=false) Long pageSize,
			@RequestParam(value="exportType", required=false) String exportType) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			PaginatedResponse res = processPaginatedList(User.class,request, map, reqPage, pageSize, requestUriTmpl);
			if(res != null) {
				map.put("list", res.getResultList());
				map.put("viewClass",  UserXlsxStreamingView.class.getName());
				map.put("fileName", "Shilparamam-User-List.xlsx");
				return new ModelAndView("xlxs", "map", map);
			} else {
				map.put("message_error", "Failed to create MS Excel file for User report.");
				return new ModelAndView("user/userList", "data", map);
			}
		}  catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@RequestMapping(value = "/app/user/download/pdf", method = {RequestMethod.GET}, produces = "application/pdf")
	public @ResponseBody ModelAndView userDownloadPdf(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="id", required=true) Long id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			User dbUser = DbService.getInstance().getUserService().getUserByIdAndCtxId(id,
					ControllerUtil.getContextId(request));
			if(dbUser != null) {
				dbUser.setRoleName(CacheService.getUserRoleById(ControllerUtil.getContextId(request), dbUser.getRoleId()).getName());
				dbUser.setCreatedUserName(CacheService.getUserById(ControllerUtil.getContextId(request), dbUser.getCreatedUserId()).getDisplayName());
				User updatedUser = CacheService.getUserById(ControllerUtil.getContextId(request), dbUser.getUpdatedUserId());
				if(updatedUser != null) {
					dbUser.setUpdatedUserName(updatedUser.getDisplayName());
				}

				map.put("obj", dbUser);
				map.put("viewClass", UserPdfStreamingView.class.getName());
				map.put("fileName", "Shilparamam-User-" + id + ".pdf");
				map.put("tmplName", "/WEB-INF/res/tmpl/report/user_view.pdf");
				return new ModelAndView("pdf", "map", map);
			} else {
				map.put("message_error", "Failed to create PDF file for User " + id);
				preProcessPaginatedList(request, User.class, ControllerUtil.getContextId(request), map, requestUriTmpl);
				return new ModelAndView("user/userList", "data", map);
			}
		}  catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
	
	
	@RequestMapping(value = "/app/user/create/form", method = {RequestMethod.GET})
	public @ResponseBody ModelAndView createUserForm(HttpServletRequest request, Model model) throws Exception {
		try {
			User userForm = new User();
			
			// Rest Captcha
			CaptchaServlet.resetCaptcha(request);
			
				List<UserRole> userRoleList = DbService.getInstance().getUserService().getUserRoleListByCtxId(ControllerUtil.getContextId(request));
				List<UserRole> userRoleListFiltered = new ArrayList<UserRole>();
				if(userRoleList != null) {
					for(UserRole ur : userRoleList) {
						if(!ur.getName().equals("Admin")) {
							userRoleListFiltered.add(ur);
						}
					}
					model.addAttribute("userRoleList", userRoleListFiltered);
				}
				customModalAttributes(model);
				return new ModelAndView("user/createUser", "userForm", userForm);
		}  catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		// Incase of erros Return to Lising Page
		Map<String, Object> map = new HashMap<String, Object>();
		preProcessPaginatedList(request, User.class, ControllerUtil.getContextId(request), map, requestUriTmpl); 
		return new ModelAndView("user/userList", "data", map);
	}
	
	@RequestMapping(value = "/app/user/create", method = {RequestMethod.POST})
	public @ResponseBody ModelAndView createUser(HttpServletRequest request, 
			@ModelAttribute("userForm") @Valid User userForm,
			BindingResult result, Model model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if(result.hasErrors()) {
				List<UserRole> userRoleList = DbService.getInstance().getUserService().getUserRoleList(ControllerUtil.getContextId(request));
				if(userRoleList != null) {
					model.addAttribute("userRoleList", userRoleList);
				}
				customModalAttributes(model);
				return new ModelAndView("user/createUser", "userForm", userForm);
			} else {
			
			// Pre process the formBean
			this.updateOptionalOrgDoProperties(request, userForm);
			PasswordEncoder encoder = new PasswordEncoder();
			userForm.setLoginPassword(encoder.encode(userForm.getLoginPassword()));
			User dbObject = (User) DbService.getInstance().getUserService().getUserByCtxIdAndLoginName(ControllerUtil.getContextId(request), userForm.getLoginName());
			if(dbObject != null) {
				customModalAttributes(model);
				model.addAttribute("message_error", "A User with given Login Name already exists");
				List<UserRole> userRoleList = DbService.getInstance().getUserService().getUserRoleList(ControllerUtil.getContextId(request));
				if(userRoleList != null) {
					model.addAttribute("userRoleList", userRoleList);
				}
				return new ModelAndView("user/createUser", "userForm", userForm);
			}
			
			User dbObj = (User) DbService.getInstance().getUserService().saveOrUpdateUser(userForm);
			
			ObjectMapper jsonApi = new ObjectMapper();
			this.saveAuditObject(request, null, jsonApi.writeValueAsString(dbObject), AuditOperationType.CREATE, AuditObject.User);
			
			preProcessPaginatedList(request, User.class, ControllerUtil.getContextId(request), map, requestUriTmpl);
			map.put("message_success", "A new User " + dbObj.getId() +" has been created.");
			
			// Send a confirmation email
			dbObj.setRoleName(CacheService.getUserRoleById(ControllerUtil.getContextId(request), dbObj.getRoleId()).getName());
			Map<String, Object> attr = new HashMap<String, Object>();
			attr.put("user", dbObj);
			attr.put("server_scheme", request.getScheme());
			attr.put("server_hostname", request.getServerName());
			attr.put("server_port", request.getServerPort());
			EmailService.getInstance().sendEmail(dbObj.getEmail(), "user/userCreated.vm", "User " + dbObj.getDisplayName() + " has been created", attr);
			return new ModelAndView("user/userList", "data", map);
			}
		}  catch (Exception e) {
			logger.error(e.getMessage(), e);
			
			// In case of errors Return to Listing Page
			map.put("message_error", "Failed to create a new User.");
			preProcessPaginatedList(request, User.class, ControllerUtil.getContextId(request), map, requestUriTmpl);
			return new ModelAndView("user/userList", "data", map);
		}
	}
	
	@RequestMapping(value = "/app/user/update/form", method = {RequestMethod.GET})
	public @ResponseBody ModelAndView updateUserForm(HttpServletRequest request,
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
					User dbUser = DbService.getInstance().getUserService().getUserByIdAndCtxId(id, new Long(1));
					if(dbUser != null) {
						// Rest Captcha
						CaptchaServlet.resetCaptcha(request);
						customModalAttributes(model);
						List<UserRole> userRoleList = DbService.getInstance().getUserService().getUserRoleList(ControllerUtil.getContextId(request));
						if(userRoleList != null) {
							model.addAttribute("userRoleList", userRoleList);
						}
						return new ModelAndView("user/updateUser", "userForm", dbUser);
					}
					// Check permissions
				} catch (Exception e) {
					logger.error("Failed to delete User", e);
				}
			}
			
			// Return to Listing page with error
			map.put("message_error", "User being updated does not exists on the System.");
			preProcessPaginatedList(request, User.class, ControllerUtil.getContextId(request), map,reqPage, pageSize, requestUriTmpl);
			return new ModelAndView("user/userList", "data", map);
		}  catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
	
	@RequestMapping(value = "/app/user/update", method = {RequestMethod.POST})
	public @ResponseBody ModelAndView updateUser(HttpServletRequest request,
			@ModelAttribute("userForm") @Valid User userForm,
			BindingResult result,
			@RequestParam(value="reqPage") Long reqPage,
			@RequestParam(value="pageSize") Long pageSize,
			Model model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// Global forwarding parameters
			model.addAttribute("reqPage", reqPage);
			model.addAttribute("pageSize", pageSize);
			if(result.hasErrors()) {
				customModalAttributes(model);
				List<UserRole> userRoleList = DbService.getInstance().getUserService().getUserRoleList(ControllerUtil.getContextId(request));
				if(userRoleList != null) {
					model.addAttribute("userRoleList", userRoleList);
				}
				return new ModelAndView("user/updateUser", "userForm", userForm);
			} else {
				// Pre process the formBean
				User dbUser = DbService.getInstance().getUserService().getUserByIdAndCtxId(userForm.getId(),
						ControllerUtil.getContextId(request));
				if(dbUser != null) {
					ObjectMapper jsonApi = new ObjectMapper();
					String oldDbObject = jsonApi.writeValueAsString(dbUser);
					
					this.updateContextDoProperties(request, dbUser);
					dbUser.setAccountState(userForm.getAccountState());
					dbUser.setEmail(userForm.getEmail());
					dbUser.setDisplayName(userForm.getDisplayName());
					dbUser.setGender(userForm.getGender());
					dbUser.setAadharId(userForm.getAadharId());
					dbUser.setMobileNumber(userForm.getMobileNumber());
					dbUser.setDob(userForm.getDob());
					dbUser.setDesignation(userForm.getDesignation());
					dbUser.setRoleId(userForm.getRoleId());
					User dbObject = (User) DbService.getInstance().getUserService().saveOrUpdateUser(dbUser);
					this.saveAuditObject(request, oldDbObject, jsonApi.writeValueAsString(dbObject),AuditOperationType.UPDATE, AuditObject.User);
					
					if(dbUser.getAccountState() == UserState.LOCKED) {
						dbUser.setLockedTime(new Date());
					}
					if(dbObject != null) {
						map.put("message_success", "A User " + dbObject.getLoginName() + " updated.");
					} else {
						map.put("message_error", "Updating User" + dbUser.getLoginName() + " failed.");
					}
					preProcessPaginatedList(request, User.class, ControllerUtil.getContextId(request), map,reqPage, pageSize, requestUriTmpl);
					Map<String, Object> attr = new HashMap<String, Object>();
					attr.put("user", dbObject);
					attr.put("server_scheme", request.getScheme());
					attr.put("server_hostname", request.getServerName());
					attr.put("server_port", request.getServerPort());
					EmailService.getInstance().sendEmail(dbObject.getEmail(), "user/userUpdated.vm", "User " + dbObject.getDisplayName() + " has been updated", attr);
					return new ModelAndView("user/userList", "data", map);
				}
			}
		}  catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		map.put("message_error", "Updating User failed.");
		preProcessPaginatedList(request, User.class, ControllerUtil.getContextId(request), map,reqPage, pageSize, requestUriTmpl);
		return new ModelAndView("user/userList", "data", map);
	}
	
	@RequestMapping(value = "/app/user/lock", method = {RequestMethod.GET})
	public @ResponseBody ModelAndView lockUser(HttpServletRequest request,
			@RequestParam(value="idCsv") String idCsv) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if(idCsv != null) {
				String[] idArray = idCsv.split(",");
				if(idArray.length > 0) {
					for(String idStr : idArray) {
						try {
							Long id = Long.parseLong(idStr);
							// Load the Object with context id
							User user = DbService.getInstance().getUserService().getUserByIdAndCtxId(id, new Long(1));
							if(user != null) {
								user.setAccountState(UserState.LOCKED);
								DbService.getInstance().getUserService().saveOrUpdateUser(user);
								
								map.put("message_scuuess", "Successfully deleted the requested User.");
							}
							// Check permissions
						} catch (Exception e) {
							map.put("message_error", "Locking User failed.");
							logger.error("Failed to lock User", e);
						}
					}
				}
			}
			preProcessPaginatedList(request, User.class, ControllerUtil.getContextId(request), map, requestUriTmpl);
			
			return new ModelAndView("user/userList", "data", map);
		}  catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
	@RequestMapping(value = "/app/user/view/form", method = {RequestMethod.GET})
	public @ResponseBody ModelAndView viewUserForm(HttpServletRequest request,
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
					User dbUser = DbService.getInstance().getUserService().getUserByIdAndCtxId(id, new Long(1));
					if(dbUser != null) {
						dbUser.setAadharId(dbUser.getAadharId().replaceAll("\\w(?=\\w{4})", "*"));
						dbUser.setMobileNumber(dbUser.getMobileNumber().replaceAll("\\w(?=\\w{4})", "*"));
						dbUser.setRoleName(CacheService.getUserRoleById(ControllerUtil.getContextId(request), dbUser.getRoleId()).getName());
						dbUser.setCreatedUserName(CacheService.getUserById(ControllerUtil.getContextId(request), dbUser.getCreatedUserId()).getDisplayName());
						User updatedUser = CacheService.getUserById(ControllerUtil.getContextId(request), dbUser.getUpdatedUserId());
						if(updatedUser != null) {
							dbUser.setUpdatedUserName(updatedUser.getDisplayName());
						}
						
						// Rest Captcha
						CaptchaServlet.resetCaptcha(request);
						List<UserRole> userRoleList = DbService.getInstance().getUserService().getUserRoleList(ControllerUtil.getContextId(request));
						if(userRoleList != null) {
							model.addAttribute("userRoleList", userRoleList);
						}
						return new ModelAndView("user/viewUser", "userForm", dbUser);
					}
					// Check permissions
				} catch (Exception e) {
					logger.error("Failed to view User", e);
				}
			}
			
			// Return to Listing page with error
			map.put("message_error", "User being view does not exists on the System.");
			preProcessPaginatedList(request, User.class, ControllerUtil.getContextId(request), map,reqPage, pageSize, requestUriTmpl);
			return new ModelAndView("user/userList", "data", map);
		}  catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
	
}