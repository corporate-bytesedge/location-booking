package com.bytesedge.bookvenue.dao;

import java.util.List;

import com.bytesedge.bookvenue.common.PaginatedRequest;
import com.bytesedge.bookvenue.common.PaginatedResponse;
import com.bytesedge.bookvenue.model.Organization;

public interface OrgDao extends Dao {
	public Organization getOrganizationByIdAndCtxId(Long id, Long ctxId) throws Exception;
	public PaginatedResponse getOrganizationList(Long ctxId, PaginatedRequest req) throws Exception;
	public Organization getOrganizationByCtxIdAndId(Long contextId, Long id) throws Exception;
	public Organization getNextOrganizationByCtxIdAndId(Long ctxId, Long id) throws Exception;
	public List<Organization> getOrganizationByCtxId(Long ctxId) throws Exception;
	public Organization getOrganizationByName(Long ctxId, String name) throws Exception;
}