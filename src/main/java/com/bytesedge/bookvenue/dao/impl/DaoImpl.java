package com.bytesedge.bookvenue.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.bytesedge.bookvenue.common.DaoException;
import com.bytesedge.bookvenue.dao.Dao;
import com.bytesedge.bookvenue.model.Persistent;

@SuppressWarnings("deprecation")
@Component
@Repository("dao")
public class DaoImpl extends HibernateDaoSupport implements Dao {

	@SuppressWarnings("rawtypes")
	public HibernatePage getHibernatePage(String queryString, String countQuery, int pageNo, int pageSize)
			throws DaoException {
		return getHibernatePage(queryString, countQuery, (Object[]) null, pageNo, pageSize);
	}

	@SuppressWarnings("rawtypes")
	public HibernatePage getHibernatePage(String queryString, String countQuery, Object parameter, int pageNo,
			int pageSize) throws DaoException {
		return getHibernatePage(queryString, countQuery, parameter != null ? new Object[] { parameter } : null, pageNo,
				pageSize);
	}

	@SuppressWarnings("rawtypes")
	public HibernatePage getHibernatePage(String queryString, String countQuery, List parameters, int pageNo,
			int pageSize) throws DaoException {
		return getHibernatePage(queryString, countQuery, parameters.toArray(), pageNo, pageSize);
	}

	@SuppressWarnings({ "rawtypes", "deprecation" })
	public HibernatePage getHibernatePage(String selectQueryString, String countQueryString, Object[] parameters,
			int pageNo, int pageSize) throws DaoException {
		try {
			Query selectQuery = getSession().createQuery(selectQueryString);
			selectQuery.setCacheable(true);
			Query countQuery = getSession().createQuery(countQueryString);
			countQuery.setCacheable(true);
			if (parameters != null) {
				for (int i = 0; i < parameters.length; i++) {
					selectQuery.setParameter(i, parameters[i]);
					countQuery.setParameter(i, parameters[i]);
				}
			}
			return new HibernatePage(selectQuery, countQuery, pageNo, pageSize);
		} catch (HibernateException e) {
			throw new DaoException("Fail to execute query.", e);
		}
	}

	@SuppressWarnings({ "rawtypes", "deprecation" })
	public HibernatePage getHibernatePageWithCache(String selectQueryString, String countQueryString,
			Object[] parameters, int pageNo, int pageSize, Boolean IsCacheable) throws DaoException {
		try {
			Query selectQuery = getSession().createQuery(selectQueryString);
			selectQuery.setCacheable(IsCacheable);
			Query countQuery = getSession().createQuery(countQueryString);
			if (parameters != null) {
				for (int i = 0; i < parameters.length; i++) {
					selectQuery.setParameter(i, parameters[i]);
					countQuery.setParameter(i, parameters[i]);
				}
			}
			return new HibernatePage(selectQuery, countQuery, pageNo, pageSize);
		} catch (HibernateException e) {
			throw new DaoException("Fail to execute query.", e);
		}
	}

	@SuppressWarnings("rawtypes")
	public HibernatePage getHibernatePage(Criteria selectCriteria, Criteria countCriteria, int pageNo, int pageSize)
			throws DaoException {
		try {
			return new HibernatePage(selectCriteria, countCriteria, pageNo, pageSize);
		} catch (HibernateException e) {
			throw new DaoException("Fail to execute query.", e);
		}
	}

	@SuppressWarnings({ "deprecation", "rawtypes" })
	public Query createHibernateQuery(final String query, final Object[] params) {
		Query selectQuery = getSession().createQuery(query);
		selectQuery.setCacheable(true);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				selectQuery.setParameter(i, params[i]);
			}
		}
		return selectQuery;
	}

	@SuppressWarnings("deprecation")
	public Object getUniqueResult(String queryString, Object... values) throws DaoException {
		try {
			@SuppressWarnings("rawtypes")
			Query query = getSession().createQuery(queryString);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					query.setParameter(i, values[i]);
				}
			}
			return query.uniqueResult();
		} catch (HibernateException e) {
			throw new DaoException("Fail to execute query.", e);
		}
	}

	@SuppressWarnings("deprecation")
	public void executeUpdate(String queryString, final Object[] params) throws DaoException {
		try {
			@SuppressWarnings("rawtypes")
			SQLQuery query = getSession().createSQLQuery(queryString);
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
			query.executeUpdate();
		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public void flush() throws DaoException {
		try {
			getSession().flush();
		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public void merge(Persistent perObj) throws DaoException {
		try {
			getSession().merge(perObj);
		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public Persistent saveOrUpdate(Persistent perObj) throws DaoException {
		getHibernateTemplate().saveOrUpdate(perObj.getClass().getName(), perObj);
		return perObj;
	}
	
	@Override
	public Persistent saveOrUpdate(String entityName, Persistent perObj) throws DaoException {
		getHibernateTemplate().saveOrUpdate(entityName, perObj);
		return perObj;
	}

	@Override
	public void delete(Persistent perObj) throws DaoException {
		getHibernateTemplate().delete(perObj);
	}

	protected boolean isNotEmpty(List<?> objects) {
		return (objects != null && objects.size() > 0);
	}

	@SuppressWarnings("unchecked")
	protected <T> List<T> find(String queryString, Object[] values) throws DaoException {
		return getHibernateTemplate().find(queryString, values);
	}

	protected Persistent findOne(String queryString, Object[] values) throws DaoException {
		List<Persistent> objects = find(queryString, values);
		return (isNotEmpty(objects)) ? objects.get(0) : null;
	}

	@Override
	public Persistent get(Class<? extends Persistent> entityClass, Serializable id) throws DaoException {
		Persistent obj = (Persistent) getHibernateTemplate().get(entityClass, id);
		return obj;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Persistent> getAll(Class<? extends Persistent> entityClass) throws DaoException {
		return getHibernateTemplate().find("from " + entityClass.getSimpleName() + " perObj");
	}

}