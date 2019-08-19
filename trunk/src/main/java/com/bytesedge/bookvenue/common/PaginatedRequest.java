package com.bytesedge.bookvenue.common;

public class PaginatedRequest {
	static final public Long DEFAULT_PAGE_SIZE = new Long(25);
	private Long reqPage = new Long(1);
	private Long pageSize = DEFAULT_PAGE_SIZE;
	private String searchString = null;
	private String sortColumn = null;
	private Class<?> clazz = null;
	
	public Long getReqPage() {
		return reqPage;
	}

	public void setReqPage(Long reqPage) {
		this.reqPage = reqPage;
	}

	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	public String getSortColumn() {
		return sortColumn;
	}

	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}

	public Long getPageSize() {
		return pageSize;
	}

	public void setPageSize(Long pageSize) {
		this.pageSize = pageSize;
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}
}