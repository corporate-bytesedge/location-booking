package com.bytesedge.bookvenue.controller;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;

import com.bytesedge.bookvenue.common.CacheService;
import com.bytesedge.bookvenue.common.DbService;
import com.bytesedge.bookvenue.common.PaginatedRequest;
import com.bytesedge.bookvenue.common.PaginatedResponse;
import com.bytesedge.bookvenue.model.AuditData;
import com.bytesedge.bookvenue.model.AuditObject;
import com.bytesedge.bookvenue.model.AuditOperationType;
import com.bytesedge.bookvenue.model.Context;
import com.bytesedge.bookvenue.model.ContextDo;
import com.bytesedge.bookvenue.model.OrgDo;
import com.bytesedge.bookvenue.model.Organization;
import com.bytesedge.bookvenue.model.SessionOrgContext;
import com.bytesedge.bookvenue.model.User;

abstract public class BaseController {
	private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

	/**
	 * Usage springMessage.getMessage(String code, null, request.getLocale())
	 */
	@Autowired
	protected MessageSource springMessage;

	public MessageSource getSpringMessage() {
		return springMessage;
	}

	public void setSpringMessage(MessageSource springMessage) {
		this.springMessage = springMessage;
	}

	abstract public void customModalAttributes(Model model);

	public void saveAuditObject(HttpServletRequest request, String src, String dst, 
			AuditOperationType oprType, AuditObject objType) throws Exception {
		AuditData ad = new AuditData();
		updateOptionalOrgDoProperties(request, ad);
		ad.setSrc(src);
		ad.setDst(dst);
		ad.setOperation(oprType);
		ad.setAuditObject(objType);
		DbService.getInstance().getSetupService().saveOrUpdate(ad);
	}

	public void updateContextDoProperties(HttpServletRequest request, ContextDo contextDo) throws Exception {
		contextDo.setCtxId(ControllerUtil.getContextId(request));

		if(contextDo.getId() > 0) {
			contextDo.setUpdatedTime(new Date());
			contextDo.setUpdatedUserId(ControllerUtil.getUserId(request));
		} else {
			contextDo.setCreatedTime(new Date());
			contextDo.setCreatedUserId(ControllerUtil.getUserId(request));
		}
	}

	public void updateOrgDoProperties(HttpServletRequest request, OrgDo orgDo) throws Exception {
		updateContextDoProperties(request, orgDo);
		
		SessionOrgContext se = ControllerUtil.getValidSessionContext(request);
		if(se == null || se.getOrgId() == null || se.getOrgId() == 0L ) {
			throw new Exception("Invalid Organization");				
		} else {
			orgDo.setOrgId(se.getOrgId());
		}
		
	}

	public void updateOptionalOrgDoProperties(HttpServletRequest request, OrgDo orgDo) throws Exception {
		updateContextDoProperties(request, orgDo);

		SessionOrgContext se = ControllerUtil.getValidSessionContext(request);
		if(se == null || se.getOrgId() == null || se.getOrgId() == 0L ) {
			throw new Exception("Invalid Organization");				
		} else {
			orgDo.setOrgId(se.getOrgId());
		}
	}

	public void preProcessPaginatedList(HttpServletRequest request, final Class<?> clazz, final Long ctxId, 
			final Map<String, Object> map, final String requestUriTmpl) throws Exception {
		preProcessPaginatedList(request, clazz, ctxId, map, new Long(0), new Long(PaginatedRequest.DEFAULT_PAGE_SIZE), requestUriTmpl);
	}

	abstract public void preProcessPaginatedList(HttpServletRequest request, final Class<?> clazz, final Long ctxId, 
			final Map<String, Object> map, final Long reqPage, 
			final Long pageSize, final String requestUriTmpl) throws Exception;

	public PaginatedResponse processPaginatedList(final Class<?> clazz, final HttpServletRequest request, final Map<String, Object> map, String requestUriTmpl) throws Exception {
		return processPaginatedList(clazz, request, map, new Long(0), new Long(PaginatedRequest.DEFAULT_PAGE_SIZE), requestUriTmpl);
	}

	public PaginatedResponse processPaginatedList(final Class<?> clazz, final HttpServletRequest request, final Map<String, Object> map, Long reqPage, 
			final Long pageSize, final String requestUriTmpl) throws Exception {
		PaginatedResponse paginatedResponse = null;
		try {
			PaginatedRequest req = new PaginatedRequest();
			req.setClazz(clazz);
			if(pageSize != null) {
				req.setPageSize(pageSize);
			} else {
				req.setPageSize(new Long(PaginatedRequest.DEFAULT_PAGE_SIZE));
			}

			if(reqPage == null) {
				reqPage = new Long(0);
			}
			req.setReqPage(reqPage);

			paginatedResponse = getPaginatedListing(request, req, map);
			if(paginatedResponse != null && paginatedResponse.getTotalSize() != null) {
				paginatedResponse.setRequestUriTmpl(requestUriTmpl);

				// Pre-process the response
				Long totalPageCount = paginatedResponse.getTotalSize()/paginatedResponse.getPageSize();
				totalPageCount = totalPageCount + 1;
				Long mod = paginatedResponse.getTotalSize()%paginatedResponse.getPageSize();
				if(mod == 0) {
					
					totalPageCount = totalPageCount - 1;
				}
				paginatedResponse.setTotalPageCount(totalPageCount);

				if(totalPageCount > 1) {
					paginatedResponse.setFirstPageUrl(String.format(requestUriTmpl, 0,
							paginatedResponse.getPageSize()));
					paginatedResponse.setLastPageUrl(String.format(requestUriTmpl, totalPageCount-1,
							paginatedResponse.getPageSize()));
				}

				paginatedResponse.setCurrentSetStartPage((((reqPage)/10) * 10));
				paginatedResponse.setCurrentSetEndPage((paginatedResponse.getCurrentSetStartPage() + 10) < totalPageCount ?
						paginatedResponse.getCurrentSetStartPage() + 10 : totalPageCount);

				if(totalPageCount >= 10 && reqPage >= 10) {
					paginatedResponse.setPrvSetUrl(String.format(requestUriTmpl, (((reqPage - 10)/10) * 10),
							paginatedResponse.getPageSize()));
				}

				if(totalPageCount >= 10 && (totalPageCount - reqPage) > 10) {
					paginatedResponse.setNxtSetUrl(String.format(requestUriTmpl, (((reqPage + 10)/10) * 10),
							paginatedResponse.getPageSize()));
				}					

				if(totalPageCount > 1 && paginatedResponse.getReqPage().longValue() > 0 ) {
					paginatedResponse.setPrvPageUrl(String.format(requestUriTmpl, paginatedResponse.getReqPage()-1,
							paginatedResponse.getPageSize()));
				}

				if(totalPageCount > 1 && paginatedResponse.getReqPage().longValue() < totalPageCount.longValue() - 1 ) {
					paginatedResponse.setNxtPageUrl(String.format(requestUriTmpl, paginatedResponse.getReqPage() + 1,
							paginatedResponse.getPageSize()));
				}

				for(long i= paginatedResponse.getCurrentSetStartPage(); i< ((paginatedResponse.getCurrentSetStartPage() + 10) < totalPageCount ? (paginatedResponse.getCurrentSetStartPage() + 10) : totalPageCount) ; i++) {
					paginatedResponse.addPageUrl(String.format(requestUriTmpl, i, paginatedResponse.getPageSize()));
				}

				StringBuilder summary = new StringBuilder();
				summary.append("Displaying Page ")
				.append(paginatedResponse.getReqPage() + 1).append("/").append(totalPageCount)
				.append(", Rows from ").append((paginatedResponse.getReqPage()*paginatedResponse.getPageSize())+1)
				.append(" to ")
				.append((paginatedResponse.getTotalSize() > ((paginatedResponse.getReqPage()+1)*paginatedResponse.getPageSize()) ?
						((paginatedResponse.getReqPage()+1)*paginatedResponse.getPageSize()) : paginatedResponse.getTotalSize()))
				.append(". Total ").append(paginatedResponse.getTotalSize());
				paginatedResponse.setSummary(summary.toString());

				map.put("objCount", paginatedResponse.getTotalSize());
				map.put("paginatedResponse", paginatedResponse);
			} else {
				map.put("objCount", 0);
			}
		}  catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}

		return paginatedResponse;
	}

	abstract public PaginatedResponse getPaginatedListing(HttpServletRequest request, PaginatedRequest req,
			final Map<String, Object> map) throws Exception;

	protected final void initCtx(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			// Add a Parameter to HttpSession
			Context context = CacheService.getContextByUrl(request.getServerName());
			if(context != null) {
				HttpSession session = (HttpSession)request.getSession();
				session.setAttribute("ctxCode", context.getCode());
				session.setAttribute("ctxId", context.getId());

				// Set User Id to session
				try {
					Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
					if (principal != null && principal instanceof UserDetails) {
						String loginName = ((UserDetails)principal).getUsername();
						if(loginName != null) {
							User user = CacheService.getUserByLoginName(loginName);
							if(user != null) {
								session.setAttribute("userId", user.getId());
								session.setAttribute("userName", user.getDisplayName());
								session.setAttribute("userDesignation", user.getDesignation());
								session.setAttribute("userOrgId", user.getOrgId());

								SessionOrgContext sc = (SessionOrgContext) session.getAttribute("sessionOrgContext");
								if(sc == null) {
									sc = new SessionOrgContext();
									Organization org = CacheService.getOrganizationById(context.getId(), user.getOrgId());
									if(org != null) {
										sc.setOrgId(org.getId());
										session.setAttribute("sessionOrgContext", sc);
									}
								}
							}
						}
					}
				} catch (Exception e) {
					logger.error("Failed to build the session org. " +  e.getMessage(), e);
					throw e;
				}
			}
		} catch (Exception e) {
			logger.error("Failed to build the session org. " + e.getMessage(), e);
			throw e;
		}
	}

	protected final void addSessionAttribute(HttpServletRequest request, HttpServletResponse response, String name, Object val) throws Exception {
		try {
			// Add a Parameter to HttpSession
			Context context = CacheService.getContextByUrl(request.getServerName());
			if(context != null) {
				HttpSession session = (HttpSession)request.getSession();
				session.setAttribute(name, val);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
}
