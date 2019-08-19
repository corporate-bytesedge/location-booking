package com.bytesedge.bookvenue.common;

import java.util.ArrayList;
import java.util.List;

import com.bytesedge.bookvenue.model.Persistent;

public class PaginatedResponse {
	private PaginatedRequest request = null;
	private List<? extends Persistent> resultList = null;
	private List<String> pageUrlList = null;

	private Long reqPage = new Long(1);
	private Long pageSize = PaginatedRequest.DEFAULT_PAGE_SIZE;
	private String firstPageUrl = null;
	private String lastPageUrl = null;
	private String prvPageUrl = null;
	private String nxtPageUrl = null;
	private String prvSetUrl = null;
	private String nxtSetUrl = null;
	private Long currentSetStartPage = null;
	private Long currentSetEndPage = null;
	private String summary = null;
	private Long totalSize = null;
	private String searchString = null;
	private Class<?> clazz = null;
	private Long totalPageCount = null;
	private String requestUriTmpl = null; 
	
	public void addPageUrl(String url) {
		if(pageUrlList == null) {
			pageUrlList = new ArrayList<String>();
		}
		if(url != null) {
			pageUrlList.add(url);
		}
	}

	public List<? extends Persistent> getResultList() {
		return resultList;
	}

	public void setResultList(List<? extends Persistent> resultList) {
		this.resultList = resultList;
	}

	public List<String> getPageUrlList() {
		return pageUrlList;
	}

	public void setPageUrlList(List<String> pageUrlList) {
		this.pageUrlList = pageUrlList;
	}

	public PaginatedRequest getRequest() {
		return request;
	}

	public void setRequest(PaginatedRequest request) {
		this.request = request;
	}

	public Long getReqPage() {
		return reqPage;
	}

	public void setReqPage(Long reqPage) {
		this.reqPage = reqPage;
	}

	public Long getPageSize() {
		return pageSize;
	}

	public void setPageSize(Long pageSize) {
		this.pageSize = pageSize;
	}

	public String getPrvPageUrl() {
		return prvPageUrl;
	}

	public void setPrvPageUrl(String prvPageUrl) {
		this.prvPageUrl = prvPageUrl;
	}

	public String getNxtPageUrl() {
		return nxtPageUrl;
	}

	public void setNxtPageUrl(String nxtPageUrl) {
		this.nxtPageUrl = nxtPageUrl;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Long getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(Long totalSize) {
		this.totalSize = totalSize;
	}

	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}

	public String getPrvSetUrl() {
		return prvSetUrl;
	}

	public void setPrvSetUrl(String prvSetUrl) {
		this.prvSetUrl = prvSetUrl;
	}

	public String getNxtSetUrl() {
		return nxtSetUrl;
	}

	public void setNxtSetUrl(String nxtSetUrl) {
		this.nxtSetUrl = nxtSetUrl;
	}

	public String getFirstPageUrl() {
		return firstPageUrl;
	}

	public void setFirstPageUrl(String firstPageUrl) {
		this.firstPageUrl = firstPageUrl;
	}

	public String getLastPageUrl() {
		return lastPageUrl;
	}

	public void setLastPageUrl(String lastPageUrl) {
		this.lastPageUrl = lastPageUrl;
	}

	public Long getCurrentSetStartPage() {
		return currentSetStartPage;
	}

	public void setCurrentSetStartPage(Long currentSetStartPage) {
		this.currentSetStartPage = currentSetStartPage;
	}

	public Long getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(Long totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	public Long getCurrentSetEndPage() {
		return currentSetEndPage;
	}

	public void setCurrentSetEndPage(Long currentSetEndPage) {
		this.currentSetEndPage = currentSetEndPage;
	}

	public String getRequestUriTmpl() {
		return requestUriTmpl;
	}

	public void setRequestUriTmpl(String requestUriTmpl) {
		this.requestUriTmpl = requestUriTmpl;
	}

}