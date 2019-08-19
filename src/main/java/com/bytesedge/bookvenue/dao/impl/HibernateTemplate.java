package com.bytesedge.bookvenue.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.SessionHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.bytesedge.bookvenue.common.DaoException;
import com.bytesedge.bookvenue.model.Persistent;

@SuppressWarnings("deprecation")
@Component("hibernateTemplate")
public class HibernateTemplate {

	private static final Logger log = LoggerFactory.getLogger(HibernateTemplate.class);
	
	@Autowired(required=true)
	private SessionFactory sessionFactory;
	private String queryCacheRegion;
	private boolean cacheQueries = false;
	private int fetchSize = 0;
	private int maxResults = 0;

	public HibernateTemplate() {

	}

	public HibernateTemplate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public HibernateTemplate(SessionFactory sessionFactory, boolean allowCreate) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public Session getSession() {
		return getCurrentSession();
	}

	public void saveOrUpdate(final Persistent entity) throws DataAccessException {
		getCurrentSession().saveOrUpdate(entity);
	}

	public void saveOrUpdate(final String entityName, final Persistent entity) throws DataAccessException {
		getCurrentSession().saveOrUpdate(entityName, entity);
	}

	public void saveOrUpdateAll(final Collection<? extends Persistent> entities) throws DataAccessException {
		for (Persistent entity : entities) {
			saveOrUpdate(entity);
		}
	}

	public void delete(final Persistent entity) throws DataAccessException {
		getCurrentSession().delete(entity);
	}

	@SuppressWarnings({ "unused", "rawtypes" })
	public void deleteAll(Collection entities) throws DataAccessException {
		for (Object entity : entities) {
			getCurrentSession().delete(entity);
			if (entity instanceof Persistent) {
				Persistent perEntity = (Persistent) entity;
			}
		}
	}

	public Object merge(final Persistent entity) throws DataAccessException {
		Object returns = getCurrentSession().merge(entity);
		return returns;

	}

	public Object merge(String entityName, final Persistent entity)	throws DataAccessException {
		Object returns = getCurrentSession().merge(entityName, entity);
		return returns;

	}

	public Serializable save(final Persistent entity) throws DataAccessException {
		Serializable returns = getCurrentSession().save(entity);
		return returns;
	}

	public Object save(final String entityName, Object entity) throws DaoException {
		try {
			return getSession().save(entityName, entity);
		} catch (HibernateException e) {
			throw new DaoException("Fail to save object.", e);
		}
	}

	public void update(final Persistent entity) throws DataAccessException {
		getCurrentSession().update(entity);
	}

	@SuppressWarnings({  "rawtypes" })
	public List find(Query query) throws DataAccessException {
		return query.list();
	}

	@SuppressWarnings("rawtypes")
	public List find(String queryString) throws DaoException {
		return find(queryString, (Object[]) null);
	}

	@SuppressWarnings("rawtypes")
	public List find(String queryString, Object value) throws DaoException {
		return find(queryString, new Object[] { value });
	}
	
	@SuppressWarnings("rawtypes")
	public List find(final String queryString, int maxCount) throws DaoException {
		return find(queryString, (Object[]) null, maxCount);
	}

	@SuppressWarnings({ "rawtypes" })
	public List find(final String queryString, final List<Object> values) throws DaoException {
		try {
			Query query = getSession().createQuery(queryString);
			prepareQuery(query);
			if (values != null) {
				for (int i = 0; i < values.size(); i++) {
					query.setParameter(i, values.get(i));
				}
			}
			return query.list();
		} catch (HibernateException e) {
			throw new DaoException("Fail to execute query.", e);
		}
	}

	@SuppressWarnings({  "rawtypes" })
	public List find(final String queryString, final List<Object> values,int maxCount) throws DaoException {
		try {
			Query query = getSession().createQuery(queryString);
			prepareQuery(query);
			if (values != null) {
				for (int i = 0; i < values.size(); i++) {
					query.setParameter(i, values.get(i));
				}
			}
			if (maxCount > 0)
				query.setMaxResults(maxCount);
			return query.list();
		} catch (HibernateException e) {
			throw new DaoException("Fail to execute query.", e);
		}
	}

	public String getQueryCacheRegion() {
		return this.queryCacheRegion;
	}

	public boolean isCacheQueries() {
		return this.cacheQueries;
	}

	public int getFetchSize() {
		return this.fetchSize;
	}

	/**
	 * Return the maximum number of rows specified for this HibernateTemplate.
	 */
	public int getMaxResults() {
		return this.maxResults;
	}

	@SuppressWarnings({ "rawtypes" })
	protected void prepareQuery(Query queryObject) {
		if (isCacheQueries()) {
			queryObject.setCacheable(true);
			if (getQueryCacheRegion() != null) {
				queryObject.setCacheRegion(getQueryCacheRegion());
			}
		}
		if (getFetchSize() > 0) {
			queryObject.setFetchSize(getFetchSize());
		}
		if (getMaxResults() > 0) {
			queryObject.setMaxResults(getMaxResults());
		}
		applyTransactionTimeout(queryObject, getSessionFactory());
	}


	@SuppressWarnings({  "rawtypes" })
	private static void applyTransactionTimeout(Query query, SessionFactory sessionFactory) {
		if (sessionFactory != null) {
			SessionHolder sessionHolder =
					(SessionHolder) TransactionSynchronizationManager.getResource(sessionFactory);
			if (sessionHolder != null && sessionHolder.hasTimeout()) {
				query.setTimeout(sessionHolder.getTimeToLiveInSeconds());
			}
		}
	}

	@SuppressWarnings("rawtypes")
	public List find(final String queryString, final Object[] values) throws DaoException {
		return find(queryString, values, -1);
	}

	@SuppressWarnings("rawtypes")
	public List find(final String queryString, final Object[] values,int maxCount) throws DaoException {
		return find(queryString, values, 0, maxCount);
	}
	
	@SuppressWarnings({ "rawtypes" })
	public List find(final String queryString, final Object[] values,int offSet, int maxCount) throws DaoException {
		Query query = null;
		try {
			query = getSession().createQuery(queryString);
			prepareQuery(query);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					query.setParameter(i, values[i]);
				}
			}
			if(offSet > 0) {
				query.setFirstResult(offSet);	
			}
			
			if (maxCount > 0) {
				query.setMaxResults(maxCount);
			}
			return query.list();
		} catch (HibernateException e) {
			log.error(e.getMessage(),e);
			throw new DaoException("Fail to execute query." + e.getMessage(), e.getCause());
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object get(Class entityClass, Serializable id) throws DaoException {
		return getSession().get(entityClass, id);
	}

	public Object get(String entityName, Serializable id) throws DaoException {
		return  getSession().get(entityName, id);
	}

	// -------------------------------------------------------------------------
	// Convenience query methods for iteratation
	// -------------------------------------------------------------------------

	@SuppressWarnings("rawtypes")
	public Iterator iterate(String queryString) throws DaoException {
		return iterate(queryString, (Object[]) null);
	}

	@SuppressWarnings("rawtypes")
	public Iterator iterate(String queryString, Object value) throws DaoException {
		return iterate(queryString, new Object[] { value });
	}

	@SuppressWarnings({ "rawtypes" })
	public Iterator iterate(final String queryString, final Object[] values) throws DaoException {
		try {
			Query query = getSession().createQuery(queryString);
			prepareQuery(query);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					query.setParameter(i, values[i]);
				}
			}
			return query.iterate();
		} catch (HibernateException e) {
			throw new DaoException("Fail to execute query.", e);
		}
	}

	@SuppressWarnings("rawtypes")
	public void closeIterator(Iterator it) throws DaoException {
		try {
			Hibernate.close(it);
		} catch (HibernateException e) {
			throw new DaoException("Fail to close iterator.", e);
		}
	}

	public int bulkUpdate(String queryString) throws DataAccessException {
		return bulkUpdate(queryString, (Object[]) null);
	}

	public int bulkUpdate(String queryString, Object value) throws DataAccessException {
		return bulkUpdate(queryString, new Object[] { value });
	}

	@SuppressWarnings({  "rawtypes" })
	public int bulkUpdate(final String queryString, final Object... values) throws DataAccessException {
		Query queryObject = getSession().createQuery(queryString);
		prepareQuery(queryObject);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				queryObject.setParameter(i, values[i]);
			}
		}
		return queryObject.executeUpdate();
	}

	public void persist(final Object entity) throws DaoException {
		try {
			getSession().persist(entity);
		} catch (HibernateException e) {
			throw new DaoException("Fail to close iterator.", e);
		}
	}

	public void flush() throws DaoException {
		getSession().flush();
	}

	public void refresh(final Object entity) throws DaoException {
		refresh(entity);
	}

	public <T> T execute(HibernateCallback<T> action) throws DaoException {
		try {
			T result = action.doInHibernate(getSession());
			return result;
		} catch (HibernateException ex) {
			throw new DaoException("Fail to run query.", ex);
		} catch (SQLException ex) {
			throw new DaoException("Fail to run query.", ex);
		} catch (RuntimeException ex) {
			throw ex; // Callback code threw application exception...
		}
	}

	public <T> T load(Class<T> entityClass, Serializable id) throws DataAccessException {
		return (T) getSession().load(entityClass, id);
	}

	public Object load(String entityName, Serializable id) throws DataAccessException {
		return getSession().load(entityName, id);
	}

}
