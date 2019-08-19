package com.bytesedge.bookvenue.dao.impl;

import java.util.List;

public interface PartialPage<E> {

	public List<E> getList();

	public int getTotalResults();

	public int getFirstResultNumber();

	public int getLastResultNumber();

	public int getLastPageNumber();

	public int getNextPageNumber();

	public int getPreviousPageNumber();

	public int getTotalPages();

	public int getPageNo();

	public int getPageSize();

	public boolean isNextPage();

	public boolean hasNextPage();
}
