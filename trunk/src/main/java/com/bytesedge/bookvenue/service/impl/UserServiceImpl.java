package com.bytesedge.bookvenue.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bytesedge.bookvenue.common.PaginatedRequest;
import com.bytesedge.bookvenue.common.PaginatedResponse;
import com.bytesedge.bookvenue.dao.UserDao;
import com.bytesedge.bookvenue.model.LoginHistory;
import com.bytesedge.bookvenue.model.Organization;
import com.bytesedge.bookvenue.model.User;
import com.bytesedge.bookvenue.model.UserRole;
import com.bytesedge.bookvenue.model.UserState;
import com.bytesedge.bookvenue.service.UserService;

@Component
@Service("userService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = Throwable.class)
public class UserServiceImpl extends BaseServiceImpl implements UserService {
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public User getUserByLoginName(String loginName) throws Exception {
		return userDao.getUserByLoginName(loginName);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Throwable.class)
	public User saveOrUpdateUser(User user) throws Exception {
		return (User) userDao.saveOrUpdate(user);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Throwable.class)
	public void deleteUser(User user) throws Exception {
		userDao.delete(user);
	}

	@Override
	public User getUser(long id) throws Exception {
		return userDao.getUser(id);
	}

	@Override
	public List<User> getUserList() throws Exception {
		return userDao.getUserList();
	}

	@Override
	public UserRole getUserRole(final Long ctxId, final Long id) throws Exception {
		return userDao.getUserRole(ctxId, id);
	}

	@Override
	public Organization getOrganizationById(Long ctxId, Long id) throws Exception {
		return userDao.getOrganizationById(ctxId, id);
	}

	@Override
	public List<User> getAssigneeUserList(Long ctxId) throws Exception {
		return userDao.getAssigneeUserList(ctxId);
	}

	@Override
	public List<UserRole> getUserRoleList(Long contextId) throws Exception {
		return userDao.getUserRoleList(contextId);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Throwable.class)
	public UserRole saveOrUpdateUserRole(UserRole userRole) throws Exception {
		return (UserRole) this.userDao.saveOrUpdate(userRole);
	}

	@Override
	public UserRole getUserRoleByIdAndCtxId(Long id, Long ctxId) throws Exception {
		return this.userDao.getUserRoleByIdAndCtxId(id, ctxId);
	}

	@Override
	public PaginatedResponse getUserRoleList(Long ctxId, PaginatedRequest req) throws Exception {
		return this.userDao.getUserRoleList(ctxId, req);
	}
	@Override
	public User getUserByIdAndCtxId(Long id, Long ctxId) throws Exception {
		return this.userDao.getUserByIdAndCtxId(id, ctxId);
	}

	@Override
	public PaginatedResponse getUserList(Long ctxId, PaginatedRequest req) throws Exception {
		return this.userDao.getUserList(ctxId, req);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Throwable.class)
	public LoginHistory saveOrUpdateLoginHistory(LoginHistory lh) throws Exception {
		return (LoginHistory) this.userDao.saveOrUpdate(lh);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Throwable.class)
	public void saveOrUpdateUserAuthFailedAndState(User user, Long authFailed, UserState state) throws Exception {
			 userDao.saveOrUpdateUserAuthFailedAndState(user, authFailed, state);
		
	}

	@Override
	public User getUserByCtxIdAndLoginName(Long contextId, String loginName) throws Exception {
		return this.userDao.getUserByCtxIdAndLoginName(contextId, loginName);
	}

	@Override
	public UserRole getUserRoleByCtxIdAndName(Long contextId, String name) throws Exception {
		return this.userDao.getUserRoleByCtxIdAndName(contextId, name);
	}
	
	@Override
	public List<UserRole> getUserRoleListByCtxId(Long ctxId) throws Exception {
		return this.userDao.getUserRoleListByCtxId(ctxId);
	}
}