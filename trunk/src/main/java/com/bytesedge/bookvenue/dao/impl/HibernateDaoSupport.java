package com.bytesedge.bookvenue.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.support.DaoSupport;

public abstract class HibernateDaoSupport extends DaoSupport {

	@Autowired(required=true)
	private HibernateTemplate hibernateTemplate;

	public final void setSessionFactory(SessionFactory sessionFactory) {
		if (this.hibernateTemplate == null || sessionFactory != this.hibernateTemplate.getSessionFactory()) {
			this.hibernateTemplate = createHibernateTemplate(sessionFactory);
		}
	}

	protected HibernateTemplate createHibernateTemplate(SessionFactory sessionFactory) {
		return new HibernateTemplate(sessionFactory);
	}

	public final SessionFactory getSessionFactory() {
		return (this.hibernateTemplate != null ? this.hibernateTemplate.getSessionFactory() : null);
	}

	public final void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public final HibernateTemplate getHibernateTemplate() {
		return this.hibernateTemplate;
	}

	@Override
	protected final void checkDaoConfig() {
		if (this.hibernateTemplate == null) {
			throw new IllegalArgumentException("'sessionFactory' or 'hibernateTemplate' is required");
		}
	}

	protected final Session getSession() throws DataAccessResourceFailureException, IllegalStateException {
		return this.getHibernateTemplate().getCurrentSession();
	}

	protected final Session getSession(boolean allowCreate) throws DataAccessResourceFailureException, IllegalStateException {
		return this.getHibernateTemplate().getCurrentSession();
	}
}
