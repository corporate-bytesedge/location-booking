package com.bytesedge.bookvenue.controller.marquee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
import com.bytesedge.bookvenue.model.Context;
import com.bytesedge.bookvenue.model.Marquee;
import com.bytesedge.bookvenue.model.MarqueeType;
import com.bytesedge.bookvenue.model.Organization;
import com.bytesedge.bookvenue.model.StateType;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@SessionAttributes({ "marqueeForm" })
public class MarqueeController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(MarqueeController.class);

	private final String requestUriTmpl = "/app/marquee/list?reqPage=%d&pageSize=%d";


	@Override
	public void customModalAttributes(Model model) {
		model.addAttribute("AuditOperationType", AuditOperationType.values());
		model.addAttribute("AuditObject", AuditObject.values());
		model.addAttribute("MarqueeType", MarqueeType.values());
	}

	@SuppressWarnings("unchecked")
	public void preProcessPaginatedList(HttpServletRequest request, final Class<?> clazz, final Long ctxId,
			final Map<String, Object> map, final Long reqPage, final Long pageSize, final String requestUriTmpl)
					throws Exception {
		processPaginatedList(Marquee.class, request, map, reqPage, pageSize, requestUriTmpl);
		PaginatedResponse paginatedResponse = (PaginatedResponse) map.get("paginatedResponse");
		if (paginatedResponse != null) {
			List<Marquee> list = (List<Marquee>) paginatedResponse.getResultList();
			if (list != null && !list.isEmpty()) {
				for (Marquee marquee : list) {
				}
			}
		}
	}

	@Override
	public PaginatedResponse getPaginatedListing(HttpServletRequest httpRequest, PaginatedRequest paginatedRequest,
			final Map<String, Object> map) throws Exception {
		return DbService.getInstance().getSetupService()
				.getMarqueeList(ControllerUtil.getContextId(httpRequest), paginatedRequest);
	}

	@RequestMapping(value = "/app/marquee/list", method = { RequestMethod.GET })
	public @ResponseBody ModelAndView marqueeList(HttpServletRequest request,
			HttpServletResponse response, @RequestParam(value = "reqPage", required = false) Long reqPage,
			@RequestParam(value = "pageSize", required = false) Long pageSize, Model model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			customModalAttributes(model);
			preProcessPaginatedList(request, Marquee.class, ControllerUtil.getContextId(request), map,
					reqPage, pageSize, requestUriTmpl);
			return new ModelAndView("marquee/marqueeList", "data", map);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}


	@RequestMapping(value = "/app/marquee/create/form", method = { RequestMethod.GET })
	public @ResponseBody ModelAndView createMarqueeForm(HttpServletRequest request, Model model)
			throws Exception {
		try {
			Marquee marqueeForm = new Marquee();

			// Rest Captcha
			CaptchaServlet.resetCaptcha(request);
			customModalAttributes(model);
			return new ModelAndView("marquee/createMarquee", "marqueeForm",
					marqueeForm);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		// Incase of erros Return to Lising Page
		Map<String, Object> map = new HashMap<String, Object>();
		preProcessPaginatedList(request, Marquee.class, ControllerUtil.getContextId(request), map,
				requestUriTmpl);
		return new ModelAndView("marquee/marqueeList", "data", map);
	}

	@RequestMapping(value = "/app/marquee/create", method = { RequestMethod.POST })
	public @ResponseBody ModelAndView createMarquee(HttpServletRequest request,
			@ModelAttribute("marqueeForm") @Valid Marquee marqueeForm,
			BindingResult result, Model model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (result.hasErrors()) {
				customModalAttributes(model);
				return new ModelAndView("marquee/createMarquee",
						"marqueeForm", marqueeForm);
			} else {
				this.updateContextDoProperties(request, marqueeForm);
				Context context = CacheService.getContextByUrl(request.getServerName());
				if (context != null) {
					Organization org = CacheService.getOrgByCtxId(context.getId());
					if (org != null) {
						marqueeForm.setOrgId(org.getId());
					}
				}
				marqueeForm.setUpdatedTime(marqueeForm.getCreatedTime());
				Marquee dbObject = (Marquee) DbService.getInstance().getSetupService()
						.saveOrUpdateMarquee(marqueeForm);
				ObjectMapper jsonApi = new ObjectMapper();
				this.saveAuditObject(request, null, jsonApi.writeValueAsString(dbObject), AuditOperationType.CREATE,
						AuditObject.Marquee);
				preProcessPaginatedList(request, Marquee.class, ControllerUtil.getContextId(request), map,
						requestUriTmpl);
				map.put("message_success", "A new Marquee " + dbObject.getId() + " has been created.");
				return new ModelAndView("marquee/marqueeList", "data", map);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			// In case of errors Return to Listing Page
			map.put("message_error", "Failed to create a new Marquee.");
			preProcessPaginatedList(request, Marquee.class, ControllerUtil.getContextId(request), map,
					requestUriTmpl);
			return new ModelAndView("marquee/marqueeList", "data", map);
		}
	}

	@RequestMapping(value = "/app/marquee/update/form", method = { RequestMethod.GET })
	public @ResponseBody ModelAndView updateMarqueeForm(HttpServletRequest request,
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
					Marquee dbMarquee = DbService.getInstance().getSetupService()
							.getMarqueeByCtxIdAndOrgIdAndId(ControllerUtil.getContextId(request), ControllerUtil.getUserOrgId(request), id);
					if (dbMarquee != null) {
						customModalAttributes(model);
						return new ModelAndView("marquee/updateMarquee",
								"marqueeForm", dbMarquee);
					}
					// Check permissions
				} catch (Exception e) {
					logger.error("Failed to delete Marquee", e);
				}
			}

			// Return to Listing page with error
			map.put("message_error", "Marquee being updated does not exists on the System.");
			preProcessPaginatedList(request, Marquee.class, ControllerUtil.getContextId(request), map,
					reqPage, pageSize, requestUriTmpl);

			// Rest Captcha
			CaptchaServlet.resetCaptcha(request);
			return new ModelAndView("marquee/marqueeList", "data", map);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}

	@RequestMapping(value = "/app/marquee/update", method = { RequestMethod.POST })
	public @ResponseBody ModelAndView updateMarquee(HttpServletRequest request,
			@ModelAttribute("marqueeForm") @Valid Marquee marqueeForm,
			BindingResult result, @RequestParam(value = "reqPage") Long reqPage,
			@RequestParam(value = "pageSize") Long pageSize, Model model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// Global forwarding parameters
			model.addAttribute("reqPage", reqPage);
			model.addAttribute("pageSize", pageSize);
			if (result.hasErrors()) {
				customModalAttributes(model);
				return new ModelAndView("marquee/updateMarquee",
						"marqueeForm", marqueeForm);
			} else {
				Marquee dbMarquee = DbService.getInstance().getSetupService()
						.getMarqueeByCtxIdAndOrgIdAndId(ControllerUtil.getContextId(request), marqueeForm.getOrgId(),
								marqueeForm.getId());
				if (dbMarquee != null) {
					ObjectMapper jsonApi = new ObjectMapper();
					String oldDbObject = jsonApi.writeValueAsString(dbMarquee);

					this.updateContextDoProperties(request, marqueeForm);
					dbMarquee.setType(marqueeForm.getType());
					dbMarquee.setText(marqueeForm.getText());
					dbMarquee.setUpdatedUserId(marqueeForm.getUpdatedUserId());
					dbMarquee.setUpdatedTime(marqueeForm.getUpdatedTime());
					Marquee dbObject = (Marquee) DbService.getInstance().getSetupService()
							.saveOrUpdateMarquee(dbMarquee);
					this.saveAuditObject(request, oldDbObject, jsonApi.writeValueAsString(dbObject),
							AuditOperationType.UPDATE, AuditObject.Marquee);
					if (dbObject != null) {
						map.put("message_success", "A Marquee " + dbObject.getId() + " updated.");
					} else {
						map.put("message_error",
								"Updating Marquee " + dbMarquee.getId() + " failed.");
					}

					preProcessPaginatedList(request, Marquee.class, ControllerUtil.getContextId(request),
							map, reqPage, pageSize, requestUriTmpl);
					return new ModelAndView("marquee/marqueeList", "data", map);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		map.put("message_error", "Updating Marquee failed.");
		preProcessPaginatedList(request, Marquee.class, ControllerUtil.getContextId(request), map,
				reqPage, pageSize, requestUriTmpl);
		return new ModelAndView("marquee/marqueeList", "data", map);
	}
}