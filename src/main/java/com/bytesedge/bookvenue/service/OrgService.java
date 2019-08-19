package com.bytesedge.bookvenue.service;

import com.bytesedge.bookvenue.common.PaginatedRequest;
import com.bytesedge.bookvenue.common.PaginatedResponse;
import com.bytesedge.bookvenue.model.Organization;

public interface OrgService {
	public PaginatedResponse getOrganizationList(Long ctxId, PaginatedRequest req) throws Exception;
	public Organization saveOrUpdateOrganization (Organization  Organization  ) throws Exception;
	public Organization getOrganizationByIdAndCtxId(Long id, Long ctxId) throws Exception;
	public Organization getOrganizationByCtxIdAndId(Long contextId, Long id) throws Exception;
	public Organization getNextOrganizationByCtxIdAndId(Long ctxId, Long id) throws Exception;
	public Organization getOrganizationByName(Long ctxId, String name) throws Exception;
}
