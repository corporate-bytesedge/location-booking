package com.bytesedge.bookvenue.controller.loginHistory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import com.bytesedge.bookvenue.controller.ControllerUtil;
import com.bytesedge.bookvenue.model.EndUser;
import com.bytesedge.bookvenue.model.LoginHistory;

@Controller
@SessionAttributes({ "loginHistoryForm" })
public class LoginHistoryController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(LoginHistoryController.class);

	private final String requestUriTmpl = "/app/loginHistory/list?reqPage=%d&pageSize=%d";

	@Override
	public void customModalAttributes(Model model) {
		// TODO Auto-generated method stub

	}

	@Override
	public PaginatedResponse getPaginatedListing(HttpServletRequest httpRequest, PaginatedRequest paginatedRequest,
			final Map<String, Object> map) throws Exception {
			return DbService.getInstance().getSetupService().getLoginHistoryList(ControllerUtil.getContextId(httpRequest), paginatedRequest);
	}
	@SuppressWarnings("unchecked")
	public void preProcessPaginatedList(HttpServletRequest request, final Class<?> clazz, final Long ctxId,
			final Map<String, Object> map, final Long reqPage, final Long pageSize, final String requestUriTmpl)
					throws Exception {
		processPaginatedList(LoginHistory.class, request, map, reqPage, pageSize, requestUriTmpl);
		PaginatedResponse paginatedResponse = (PaginatedResponse) map.get("paginatedResponse");
		if (paginatedResponse != null) {
			List<LoginHistory> list = (List<LoginHistory>) paginatedResponse.getResultList();
			if (list != null && !list.isEmpty()) {
				for (LoginHistory loginHistory : list) {
					loginHistory.setOrgName(CacheService
							.getOrganizationById(ControllerUtil.getContextId(request), loginHistory.getOrgId()).getName());
				}
			}
		}
		
	
}
	
	@RequestMapping(value = "/app/loginHistory/list", method = { RequestMethod.GET })
	public @ResponseBody ModelAndView loginHistoryList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "reqPage", required = false) Long reqPage,
			@RequestParam(value = "pageSize", required = false) Long pageSize) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			preProcessPaginatedList(request, LoginHistory.class, ControllerUtil.getContextId(request), map, reqPage, pageSize, requestUriTmpl);
			return new ModelAndView("loginHistory/loginHistoryList", "data", map);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}

}