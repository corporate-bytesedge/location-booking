package com.bytesedge.bookvenue.service;

import java.util.List;

import com.bytesedge.bookvenue.common.PaginatedRequest;
import com.bytesedge.bookvenue.common.PaginatedResponse;
import com.bytesedge.bookvenue.model.LoginHistory;
import com.bytesedge.bookvenue.model.Organization;
import com.bytesedge.bookvenue.model.User;
import com.bytesedge.bookvenue.model.UserRole;
import com.bytesedge.bookvenue.model.UserState;

public interface UserService {
	public User getUserByLoginName(String loginName) throws Exception;
	public User getUser(long id) throws Exception;
	public User saveOrUpdateUser(User user) throws Exception;
	public void deleteUser(User user) throws Exception;
	public List<User> getUserList() throws Exception;
	public UserRole getUserRole(final Long ctxId, final Long id) throws Exception;
	public Organization getOrganizationById(Long ctxId, Long id) throws Exception;
	public List<User> getAssigneeUserList(Long ctxId) throws Exception;
	public List<UserRole> getUserRoleList(Long contextId) throws Exception;
	
	public PaginatedResponse getUserList(Long ctxId, PaginatedRequest req) throws Exception;
	public User getUserByIdAndCtxId(Long id, Long ctxId) throws Exception;
	
	public PaginatedResponse getUserRoleList(Long ctxId, PaginatedRequest req) throws Exception;
	public UserRole saveOrUpdateUserRole (UserRole   UserRole  ) throws Exception;
	public UserRole getUserRoleByIdAndCtxId(Long id, Long ctxId) throws Exception;
	public LoginHistory saveOrUpdateLoginHistory(LoginHistory lh)throws Exception;
	
	public void saveOrUpdateUserAuthFailedAndState(User user, Long authFailed, UserState state) throws Exception;
	
	public User getUserByCtxIdAndLoginName(Long contextId, String loginName) throws Exception;
	public UserRole getUserRoleByCtxIdAndName(Long contextId, String name) throws Exception;
	public List<UserRole> getUserRoleListByCtxId(Long ctxId) throws Exception;
	
}
