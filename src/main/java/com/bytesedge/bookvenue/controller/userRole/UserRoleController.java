package com.bytesedge.bookvenue.controller.userRole;

import java.util.HashMap;
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
import com.bytesedge.bookvenue.common.PaginatedRequest;
import com.bytesedge.bookvenue.common.PaginatedResponse;
import com.bytesedge.bookvenue.controller.BaseController;
import com.bytesedge.bookvenue.controller.CaptchaServlet;
import com.bytesedge.bookvenue.controller.ControllerUtil;
import com.bytesedge.bookvenue.model.AuditObject;
import com.bytesedge.bookvenue.model.AuditOperationType;
import com.bytesedge.bookvenue.model.User;
import com.bytesedge.bookvenue.model.UserRole;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@SessionAttributes({"userRoleForm"})
public class UserRoleController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(UserRoleController.class);

	private final String requestUriTmpl = "/app/userRole/list?reqPage=%d&pageSize=%d";

	@Autowired
	private UserRoleFormValidator validator;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(validator);
	}

	public UserRoleFormValidator getValidator() {
		return validator;
	}

	public void setValidator(UserRoleFormValidator validator) {
		this.validator = validator;
	}

	@Override
	public void customModalAttributes(Model model) {
		model.addAttribute("AuditOperationType", AuditOperationType.values());
		model.addAttribute("AuditObject", AuditObject.values());
	}

	@Override
	public PaginatedResponse getPaginatedListing(HttpServletRequest httpRequest, PaginatedRequest paginatedRequest, final Map<String, Object> map) throws Exception {
		return DbService.getInstance().getUserService().getUserRoleList( ControllerUtil.getContextId(httpRequest), paginatedRequest);
	}

	@Override
	public void preProcessPaginatedList(HttpServletRequest request, Class<?> clazz, Long ctxId, Map<String, Object> map,
			Long reqPage, Long pageSize, String requestUriTmpl) throws Exception {
		// TODO Auto-generated method stub
	}

	@RequestMapping(value = "/app/userRole/list", method = {RequestMethod.GET})
	public @ResponseBody ModelAndView  userRoleList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="reqPage", required=false) Long reqPage, @RequestParam(value="pageSize", 
			required=false) Long pageSize) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			processPaginatedList(UserRole.class, request, map, reqPage, pageSize, requestUriTmpl);
			return new ModelAndView("userRole/userRoleList", "data", map);
		}  catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}

	
	@RequestMapping(value = "/app/userRole/list/export", method = {RequestMethod.GET}, produces = "application/xlsx")
	public @ResponseBody ModelAndView userRoleListExportXlsx(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="reqPage", required=false) Long reqPage, @RequestParam(value="pageSize", required=false) Long pageSize,
			@RequestParam(value="exportType", required=false) String exportType) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			PaginatedResponse res = processPaginatedList(UserRole.class, request, map, reqPage, pageSize, requestUriTmpl);
			if(res != null) {
				map.put("list", res.getResultList());
				map.put("viewClass",  UserRoleXlsxStreamingView.class.getName());
				map.put("fileName", "Shilparamam-UserRole-List.xlsx");
				return new ModelAndView("xlxs", "map", map);
			} else {
				map.put("message_error", "Failed to create MS Excel file for UserRole report.");
				return new ModelAndView("userRole/userRoleList", "data", map);
			}
		}  catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}

	@RequestMapping(value = "/app/userRole/download/pdf", method = {RequestMethod.GET}, produces = "application/pdf")
	public @ResponseBody ModelAndView userRoleDownloadPdf(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="id", required=true) Long id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			UserRole dbUserRole = DbService.getInstance().getUserService().getUserRoleByIdAndCtxId(id,
					ControllerUtil.getContextId(request));
			if(dbUserRole != null) {
				dbUserRole.setCreatedUserName(CacheService.getUserById(ControllerUtil.getContextId(request), dbUserRole.getCreatedUserId()).getDisplayName());
				User updatedUser = CacheService.getUserById(ControllerUtil.getContextId(request), dbUserRole.getUpdatedUserId());
				if(updatedUser != null) {
					dbUserRole.setUpdatedUserName(updatedUser.getDisplayName());
				}
				map.put("obj", dbUserRole);
				map.put("viewClass", UserRolePdfStreamingView.class.getName());
				map.put("fileName", "Shilparamam-UserRole-" + id + ".pdf");
				map.put("tmplName", "/WEB-INF/res/tmpl/report/userRole_view.pdf");
				return new ModelAndView("pdf", "map", map);
			} else {
				map.put("message_error", "Failed to create PDF file for UserRole " + id);
				preProcessPaginatedList(request, UserRole.class, ControllerUtil.getContextId(request), map, requestUriTmpl);
				return new ModelAndView("userRole/userRoleList", "data", map);
			}
		}  catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}

	@RequestMapping(value = "/app/userRole/create/form", method = {RequestMethod.GET})
	public @ResponseBody ModelAndView createUserRoleForm(HttpServletRequest request, Model model) throws Exception {
		try {
			UserRole userRoleForm = new UserRole();

			// Rest Captcha
			CaptchaServlet.resetCaptcha(request);
			customModalAttributes(model);
			return new ModelAndView("userRole/createUserRole", "userRoleForm", userRoleForm);
		}  catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		// Incase of erros Return to Lising Page
		Map<String, Object> map = new HashMap<String, Object>();
		processPaginatedList(UserRole.class, request, map, requestUriTmpl); 
		return new ModelAndView("userRole/userRoleList", "data", map);
	}

	@RequestMapping(value = "/app/userRole/create", method = {RequestMethod.POST})
	public @ResponseBody ModelAndView createUserRole(HttpServletRequest request, 
			@ModelAttribute("userRoleForm") @Valid UserRole userRoleForm,
			BindingResult result, Model model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if(result.hasErrors()) {
				customModalAttributes(model);
				return new ModelAndView("userRole/createUserRole", "userRoleForm", userRoleForm);
			} else {
				// Pre process the formBean
				UserRole dbObject = (UserRole) DbService.getInstance().getUserService().getUserRoleByCtxIdAndName(ControllerUtil.getContextId(request), userRoleForm.getName());
				if(dbObject != null){
					customModalAttributes(model);
					model.addAttribute("message_error", "A User Role with given Name already exists");
					return new ModelAndView("userRole/createUserRole", "userRoleForm", userRoleForm);
				}
				this.updateContextDoProperties(request, userRoleForm);
				UserRole dbObj = (UserRole) DbService.getInstance().getUserService().saveOrUpdateUserRole(userRoleForm);

				ObjectMapper jsonApi = new ObjectMapper();
				this.saveAuditObject(request, null, jsonApi.writeValueAsString(dbObject), AuditOperationType.CREATE, AuditObject.UserRole);


				processPaginatedList(UserRole.class, request, map, requestUriTmpl);
				customModalAttributes(model);
				map.put("message_success", "A new UserRole " + dbObj.getId() +" has been created.");
				return new ModelAndView("userRole/userRoleList", "data", map);
			}  
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			// In case of errors Return to Listing Page
			map.put("message_error", "Failed to create a new UserRole.");
			processPaginatedList(UserRole.class, request, map, requestUriTmpl);
			customModalAttributes(model);
			return new ModelAndView("userRole/userRoleList", "data", map);
		}
	}
	@RequestMapping(value = "/app/userRole/update/form", method = {RequestMethod.GET})
	public @ResponseBody ModelAndView updateUserRoleForm(HttpServletRequest request,
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
					UserRole dbUserRole = DbService.getInstance().getUserService().getUserRoleByIdAndCtxId(id, ControllerUtil.getContextId(request));
					if(dbUserRole != null) {
						// Rest Captcha
						CaptchaServlet.resetCaptcha(request);
						return new ModelAndView("userRole/updateUserRole", "userRoleForm", dbUserRole);
					}
					// Check permissions
				} catch (Exception e) {
					logger.error("Failed to update UserRole", e);
				}
			}

			// Return to Listing page with error
			map.put("message_error", "UserRole being updated does not exists on the System.");
			processPaginatedList(UserRole.class, request, map,reqPage, pageSize, requestUriTmpl);
			return new ModelAndView("userRole/userRoleList", "data", map);
		}  catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}

	@RequestMapping(value = "/app/userRole/update", method = {RequestMethod.POST})
	public @ResponseBody ModelAndView updateUserRole(HttpServletRequest request,
			@ModelAttribute("userRoleForm") @Valid UserRole userRoleForm,
			BindingResult result,
			@RequestParam(value="reqPage") Long reqPage,
			@RequestParam(value="pageSize") Long pageSize,Model model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// Global forwarding parameters
			model.addAttribute("reqPage", reqPage);
			model.addAttribute("pageSize", pageSize);
			if(result.hasErrors()) {
				return new ModelAndView("userRole/updateUserRole", "userRoleForm", userRoleForm);
			} else {
				// Pre process the formBean
				UserRole dbUserRole = DbService.getInstance().getUserService().getUserRoleByIdAndCtxId(userRoleForm.getId(),
						ControllerUtil.getContextId(request));
				if(dbUserRole != null) {
					ObjectMapper jsonApi = new ObjectMapper();
					String oldDbObject = jsonApi.writeValueAsString(dbUserRole);

					this.updateContextDoProperties(request, dbUserRole);
					dbUserRole.setName(userRoleForm.getName());
					dbUserRole.setAdmin(userRoleForm.getAdmin());
					UserRole dbObject = (UserRole) DbService.getInstance().getUserService().saveOrUpdateUserRole(dbUserRole);
					this.saveAuditObject(request, oldDbObject, jsonApi.writeValueAsString(dbObject), AuditOperationType.UPDATE, AuditObject.UserRole);
					if(dbObject != null) {
						map.put("message_success", "A UserRole " + dbObject.getName() + " updated.");
					} else {
						map.put("message_error", "Updating UserRole" + dbUserRole.getName() + " failed.");
					}
					processPaginatedList(UserRole.class,request, map,reqPage, pageSize, requestUriTmpl);
					return new ModelAndView("userRole/userRoleList", "data", map);
				}
			}
		}  catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		map.put("message_error", "Updating UserRole failed.");
		processPaginatedList(UserRole.class, request, map,reqPage, pageSize, requestUriTmpl);
		return new ModelAndView("userRole/userRoleList", "data", map);
	}

	@RequestMapping(value = "/app/userRole/view/form", method = {RequestMethod.GET})
	public @ResponseBody ModelAndView viewUserRoleForm(HttpServletRequest request,
			@RequestParam(value="id") Long id,
			@RequestParam(value="reqPage") Long reqPage,
			@RequestParam(value="pageSize") Long pageSize,Model model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// Global forwarding parameters
			model.addAttribute("reqPage", reqPage);
			model.addAttribute("pageSize", pageSize);
			if(id > 0) {
				try {
					// Load the Object with context id
					UserRole dbUserRole = DbService.getInstance().getUserService().getUserRoleByIdAndCtxId(id, ControllerUtil.getContextId(request));
					if(dbUserRole != null) {
						dbUserRole.setCreatedUserName(CacheService.getUserById(ControllerUtil.getContextId(request), dbUserRole.getCreatedUserId()).getDisplayName());
						User updatedUser = CacheService.getUserById(ControllerUtil.getContextId(request), dbUserRole.getUpdatedUserId());
						if(updatedUser != null) {
							dbUserRole.setUpdatedUserName(updatedUser.getDisplayName());
						}
						// Rest Captcha
						CaptchaServlet.resetCaptcha(request);
						return new ModelAndView("userRole/viewUserRole", "userRoleForm", dbUserRole);
					}
					// Check permissions
				} catch (Exception e) {
					logger.error("Failed to view UserRole", e);
				}
			}

			// Return to Listing page with error
			map.put("message_error", "UserRole being view does not exists on the System.");
			processPaginatedList(UserRole.class, request, map,reqPage, pageSize, requestUriTmpl);
			return new ModelAndView("userRole/userRoleList", "data", map);
		}  catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
}