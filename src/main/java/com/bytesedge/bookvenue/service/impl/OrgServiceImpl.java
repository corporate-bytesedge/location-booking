package com.bytesedge.bookvenue.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bytesedge.bookvenue.common.PaginatedRequest;
import com.bytesedge.bookvenue.common.PaginatedResponse;
import com.bytesedge.bookvenue.dao.OrgDao;
import com.bytesedge.bookvenue.model.Organization;
import com.bytesedge.bookvenue.service.OrgService;

@Component
@Service("orgService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = Throwable.class)
public class OrgServiceImpl extends BaseServiceImpl implements OrgService {
	private static Logger logger = LoggerFactory.getLogger(OrgServiceImpl.class);
	
	@Autowired
	private OrgDao orgDao;

	public OrgDao getOrgDao() {
		return orgDao;
	}

	public void setOrgDao(OrgDao orgDao) {
		this.orgDao = orgDao;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Throwable.class)
	public Organization saveOrUpdateOrganization(Organization organization) throws Exception {
		return (Organization) this.orgDao.saveOrUpdate(organization);
	}

	@Override
	public Organization getOrganizationByIdAndCtxId(Long id, Long ctxId) throws Exception {
		return this.orgDao.getOrganizationByIdAndCtxId(id, ctxId);
	}

	@Override
	public PaginatedResponse getOrganizationList(Long ctxId, PaginatedRequest req) throws Exception {
		return this.orgDao.getOrganizationList(ctxId, req);
	}

	@Override
	public Organization getOrganizationByCtxIdAndId(Long contextId, Long id) throws Exception {
        return this.orgDao.getOrganizationByCtxIdAndId(contextId, id);
	}

	@Override
	public Organization getNextOrganizationByCtxIdAndId(Long ctxId, Long id) throws Exception {
        return this.orgDao.getNextOrganizationByCtxIdAndId(ctxId,id);
	}

	@Override
	public Organization getOrganizationByName(Long ctxId, String name) throws Exception {
		return this.orgDao.getOrganizationByName(ctxId, name);
	}

}