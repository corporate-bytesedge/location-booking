package com.bytesedge.bookvenue.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytesedge.bookvenue.common.DaoException;

@SuppressWarnings({ "unchecked", "deprecation" })
public class HibernatePage<E> implements PartialPage<E> {

	private static Logger logger = LoggerFactory.getLogger(HibernatePage.class);

	private List<E> results;

	private int pageSize;

	private int page;

	private int totalResults = -1;

	public HibernatePage() {
		results = new ArrayList<E>();
	}
	
	public HibernatePage(List<E> results , int page,int pageSize,int totalResults){
		this.results=results;
		this.page=page;
		this.pageSize=pageSize;
		this.totalResults=totalResults;
		
	}
	
	public HibernatePage(Criteria selectCriteria, Criteria countCriteria, int page, int pageSize) throws DaoException {
		if (page <= 0)
			throw new DaoException("Invalid page number: " + page + ".");

		if (pageSize <= 0)
			throw new DaoException("Invalid page size: " + page + ".");

		this.page = page;
		this.pageSize = pageSize;
		try {
			this.totalResults = ((Long) countCriteria.uniqueResult()).intValue();
			/*
			 * We set the max results to one more than the specfied pageSize to
			 * determine if any more results exist (i.e. if there is a next page
			 * to display). The result set is trimmed down to just the pageSize
			 * before being displayed later (in getList()).
			 */
			results = selectCriteria.setFirstResult((page - 1) * pageSize).setMaxResults(pageSize + 1).list();
		} catch (HibernateException e) {
			throw new DaoException("Failed to execute query.", e);
		}

	}

	/**
	 * Construct a new Page.
	 * 
	 * @param query
	 *            the Hibernate Query
	 * @param page
	 *            the page number
	 * @param pageSize
	 *            the number of results to display on the page
	 * @throws DaoException
	 */
	@SuppressWarnings({ "rawtypes" })
	public HibernatePage(Query selectQuery, Query countQuery, int page, int pageSize) throws DaoException {
		if (page <= 0)
			throw new DaoException("Invalid page number: " + page + ".");

		if (pageSize <= 0)
			throw new DaoException("Invalid page size: " + page + ".");

		this.page = page;
		this.pageSize = pageSize;
		try {
			if (countQuery.uniqueResult() == null) {
				this.totalResults = 0;
			} else {
				this.totalResults = ((Long) countQuery.uniqueResult()).intValue();
			}
			/*
			 * We set the max results to one more than the specfied pageSize to
			 * determine if any more results exist (i.e. if there is a next page
			 * to display). The result set is trimmed down to just the pageSize
			 * before being displayed later (in getList()).
			 */
			results = selectQuery.setFirstResult((page - 1) * pageSize).setMaxResults(pageSize + 1).list();
		} catch (HibernateException e) {
			logger.error(e.getMessage(), e);	
			throw new DaoException("Failed to execute query.", e);
		}

	}

	public boolean isFirstPage() {
		return page == 1;
	}

	public boolean isLastPage() {
		return page >= getLastPageNumber();
	}

	public boolean hasNextPage() {
		return results != null && results.size() > pageSize;
	}
	
	public boolean isNextPage() {
		return results != null && results.size() > pageSize;
	}

	public boolean hasPreviousPage() {
		return page > 1;
	}

	public int getLastPageNumber() {
		double totalResults = new Integer(getTotalResults()).doubleValue();
		int mod = (int) (totalResults % pageSize);
		return mod == 0 ? new Double(Math.floor(totalResults / pageSize)).intValue() : new Double(Math.floor(totalResults / pageSize) + 1).intValue();
	}

	public List<E> getList() {
		/*
		 * Since we retrieved one more than the specified pageSize when the
		 * class was constructed, we now trim it down to the pageSize if a next
		 * page exists.
		 */
		return hasNextPage() ? results.subList(0, pageSize) : results;
	}

	public int getTotalResults() {
		return totalResults;
	}

	public int getFirstResultNumber() {
		return (page - 1) * pageSize + 1;
	}

	public int getLastResultNumber() {
		int fullPage = getFirstResultNumber() + pageSize - 1;
		return getTotalResults() < fullPage ? getTotalResults() : fullPage;
	}

	public int getNextPageNumber() {
		return page + 1;
	}

	public int getPreviousPageNumber() {
		return page - 1;
	}

	public int getTotalPages() {
		return (pageSize > 0)?((totalResults + (pageSize - 1)) / pageSize):0;
	}

	public int getPageNo() {
		return page;
	}

	public int getPageSize() {
		return pageSize;
	}
}
