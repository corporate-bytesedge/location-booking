package com.bytesedge.bookvenue.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bytesedge.bookvenue.common.DaoException;
import com.bytesedge.bookvenue.dao.Dao;
import com.bytesedge.bookvenue.dao.impl.PartialPage;
import com.bytesedge.bookvenue.model.Persistent;
import com.bytesedge.bookvenue.service.BaseService;

@Component
@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = Throwable.class)
public class BaseServiceImpl implements BaseService {
	
	@Autowired
	private Dao dao;
	
	public Dao getDao() {
		return dao;
	}

	public void setDao(Dao dao) {
		this.dao = dao;
	}

	@SuppressWarnings("rawtypes")
	protected PartialPage toPartialPage(final List results) {
		return toPartialPage(results,1,results.size(),results.size());
	}

	@SuppressWarnings("rawtypes")
	public PartialPage toPartialPage(final List results,final int page,final int pageSize,final int totalResults){
		return new PartialPage() {

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

			public List getList() {
				return results;
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
				if(pageSize > 0){
					return (totalResults + (pageSize - 1)) / pageSize;
				}else{
					return 1;
				}
			}

			public int getPageNo() {
				return page;
			}

			public int getPageSize() {
				return pageSize;
			}
		};
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Throwable.class)
	public Persistent saveOrUpdate(Persistent perObj) throws DaoException {
		return dao.saveOrUpdate(perObj);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Throwable.class)
	public void delete(Persistent perObj) throws DaoException {
		dao.delete(perObj);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Throwable.class)
	public void flush() throws DaoException {
		dao.flush();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Throwable.class)
	public void merge(Persistent perObj) throws DaoException {
		dao.merge(perObj);
	}

	@Override
	public Persistent get(Class entityClass, Serializable id) throws DaoException {
		return dao.get(entityClass, id);
	}

	@Override
	public List<Persistent> getAll(Class entityClass) throws DaoException {
		return dao.getAll(entityClass);
	}
}