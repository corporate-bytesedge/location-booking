package com.bytesedge.bookvenue.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.bytesedge.bookvenue.common.DaoException;
import com.bytesedge.bookvenue.common.PaginatedRequest;
import com.bytesedge.bookvenue.common.PaginatedResponse;
import com.bytesedge.bookvenue.dao.UserDao;
import com.bytesedge.bookvenue.model.Organization;
import com.bytesedge.bookvenue.model.Persistent;
import com.bytesedge.bookvenue.model.User;
import com.bytesedge.bookvenue.model.UserRole;
import com.bytesedge.bookvenue.model.UserState;

@Component
@Repository("userDao")
public final class UserDaoImpl extends DaoImpl implements UserDao {

	@Override
	@SuppressWarnings("unchecked")
	public User getUserByLoginName(String loginName) throws DaoException, Exception {
		List<User> list = getHibernateTemplate().find("from User o where o.loginName = ? ", new Object[] { loginName });
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public User getUser(long id) throws DaoException, Exception {
		List<User> list = getHibernateTemplate().find("from User o where o.id = ? ", new Object[] { id });
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<User> getUserList() throws DaoException, Exception {
		List<User> list = getHibernateTemplate().find("from User");
		return list;
	}

	@Override
	@SuppressWarnings("unchecked")
	public UserRole getUserRole(final Long ctxId, final Long id) throws DaoException, Exception {
		List<UserRole> list = getHibernateTemplate().find("from UserRole ur where ur.ctxId = ? and ur.id = ?",
				new Object[] { ctxId, id });
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Organization getOrganizationById(Long ctxId, Long id) throws DaoException, Exception {
		List<Organization> list = getHibernateTemplate().find("from Organization ur where ur.ctxId = ? and ur.id = ?",
				new Object[] { ctxId, id });
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<User> getAssigneeUserList(Long ctxId) throws DaoException, Exception {
		return getHibernateTemplate().find(
				"from User u where u.ctxId = ? and u.roleId in (select ur.id from UserRole ur where ur.ctxId = ? and ur.complaintManage=1)",
				new Object[] { ctxId, ctxId });
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<UserRole> getUserRoleList(Long contextId) throws DaoException, Exception {
		return getHibernateTemplate().find("from UserRole ur where ur.ctxId = ? ", new Object[] { contextId });
	}

	@SuppressWarnings("unchecked")
	@Override
	public User getUserByIdAndCtxId(Long id, Long ctxId) throws Exception {
		List<User> list = getHibernateTemplate().find("from User u where u.id=? and u.ctxId = ?",
				new Object[] { id, ctxId });
		return list.size() > 0 ? list.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PaginatedResponse getUserList(Long ctxId, PaginatedRequest req) throws Exception {
		@SuppressWarnings("unused")
		StringBuilder query = new StringBuilder();
		Long offSet = new Long(0);
		offSet = (req.getReqPage() * req.getPageSize());
		PaginatedResponse result = new PaginatedResponse();
		// Update the Request
		result.setClazz(req.getClazz());
		result.setReqPage(req.getReqPage());
		result.setPageSize(req.getPageSize());
		result.setSearchString(req.getSearchString());

		// Build Criteria
		@SuppressWarnings("deprecation")
		Criteria crit = getHibernateTemplate().getSession().createCriteria(req.getClazz());
		crit.add(Restrictions.eq("ctxId", ctxId));
		crit.addOrder(Order.desc("id"));
		crit.setProjection(Projections.rowCount());

		Long totalSize = (Long) crit.uniqueResult();
		if (totalSize != null && totalSize > 0) {
			crit.setProjection(null);
			crit.setFirstResult(offSet.intValue());
			crit.setMaxResults(req.getPageSize().intValue());

			List<? extends Persistent> list = crit.list();
			if (list != null && !list.isEmpty()) {
				result.setResultList(list);
				result.setTotalSize(totalSize);
			} else {
				result.setTotalSize(new Long(0));
			}
		} else {
			result.setTotalSize(new Long(0));
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public UserRole getUserRoleByIdAndCtxId(Long id, Long ctxId) throws Exception {
		List<UserRole> list = getHibernateTemplate().find("from UserRole ur where ur.id = ? and ur.ctxId = ?",
				new Object[] { id, ctxId });
		return list.size() > 0 ? list.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PaginatedResponse getUserRoleList(Long ctxId, PaginatedRequest req) throws Exception {
		@SuppressWarnings("unused")
		StringBuilder query = new StringBuilder();
		Long offSet = new Long(0);
		offSet = (req.getReqPage() * req.getPageSize());
		PaginatedResponse result = new PaginatedResponse();
		// Update the Request
		result.setClazz(req.getClazz());
		result.setReqPage(req.getReqPage());
		result.setPageSize(req.getPageSize());
		result.setSearchString(req.getSearchString());

		// Build Criteria
		@SuppressWarnings("deprecation")
		Criteria crit = getHibernateTemplate().getSession().createCriteria(req.getClazz());
		crit.add(Restrictions.eq("ctxId", ctxId));
		crit.addOrder(Order.desc("id"));
		crit.setProjection(Projections.rowCount());

		Long totalSize = (Long) crit.uniqueResult();
		if (totalSize > 0) {
			crit.setProjection(null);
			crit.setFirstResult(offSet.intValue());
			crit.setMaxResults(req.getPageSize().intValue());

			List<? extends Persistent> list = crit.list();
			if (list != null && !list.isEmpty()) {
				result.setResultList(list);
				result.setTotalSize(totalSize);
			} else {
				result.setTotalSize(new Long(0));
			}
		} else {
			result.setTotalSize(new Long(0));
		}
		return result;
	}

	@Override
	public void saveOrUpdateUserAuthFailedAndState(User user, Long authFailed, UserState state) throws Exception {
		user.setAuthFailed(authFailed);
		user.setAccountState(state);
		getHibernateTemplate().saveOrUpdate(user);
	}

	@Override
	public User getUserByCtxIdAndLoginName(Long ctxId, String loginName) throws DaoException {
		@SuppressWarnings("unchecked")
		List<User> list = getHibernateTemplate().find("from User u where u.ctxId = ? and u.loginName = ?",
				new Object[] { ctxId, loginName });
		return list.size() > 0 ? list.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public UserRole getUserRoleByCtxIdAndName(Long ctxId, String name) throws Exception {
		List<UserRole> list = getHibernateTemplate().find("from UserRole ur where ur.ctxId = ? and ur.name = ? ",
				new Object[] { ctxId, name });
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public List<UserRole> getUserRoleListByCtxId(Long ctxId) throws Exception {
		@SuppressWarnings("unchecked")
		List<UserRole> list = getHibernateTemplate().find("from UserRole ur where ur.ctxId = ?",
				new Object[] { ctxId });
		return list.size() > 0 ? list : null;
	}
}