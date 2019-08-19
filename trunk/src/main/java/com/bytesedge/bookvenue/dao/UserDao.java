package com.bytesedge.bookvenue.dao;

import java.util.List;

import com.bytesedge.bookvenue.common.DaoException;
import com.bytesedge.bookvenue.common.PaginatedRequest;
import com.bytesedge.bookvenue.common.PaginatedResponse;
import com.bytesedge.bookvenue.model.Organization;
import com.bytesedge.bookvenue.model.User;
import com.bytesedge.bookvenue.model.UserRole;
import com.bytesedge.bookvenue.model.UserState;

public interface UserDao extends Dao {
	public User getUserByLoginName(String loginName) throws DaoException, Exception;
	public User getUser(long id) throws DaoException, Exception;
	public List<User> getUserList() throws DaoException, Exception;
	public UserRole getUserRole(final Long ctxId, final Long id) throws DaoException, Exception;
	public Organization getOrganizationById(Long ctxId, Long id) throws DaoException, Exception;
	public List<User> getAssigneeUserList(Long ctxId) throws DaoException, Exception;
	public List<UserRole> getUserRoleList(Long contextId) throws DaoException, Exception;
	
	public UserRole getUserRoleByIdAndCtxId(Long id, Long ctxId) throws Exception;
	public PaginatedResponse getUserRoleList(Long ctxId, PaginatedRequest req) throws Exception;
	public User getUserByIdAndCtxId(Long id, Long ctxId) throws Exception;
	public PaginatedResponse getUserList(Long ctxId, PaginatedRequest req) throws Exception;
	public void saveOrUpdateUserAuthFailedAndState(User user, Long authFailed, UserState state) throws Exception;
	
	public User getUserByCtxIdAndLoginName(Long ctxId, String loginName) throws DaoException;
	public UserRole getUserRoleByCtxIdAndName(Long ctxId, String name) throws Exception;
	public List<UserRole> getUserRoleListByCtxId(Long ctxId)throws Exception;
	
}