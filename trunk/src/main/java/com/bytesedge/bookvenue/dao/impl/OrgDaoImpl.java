package com.bytesedge.bookvenue.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.bytesedge.bookvenue.common.PaginatedRequest;
import com.bytesedge.bookvenue.common.PaginatedResponse;
import com.bytesedge.bookvenue.dao.OrgDao;
import com.bytesedge.bookvenue.model.Organization;
import com.bytesedge.bookvenue.model.Persistent;

@Component
@Repository("orgDao")
public final class OrgDaoImpl extends DaoImpl implements OrgDao {
	@SuppressWarnings("unchecked")
	@Override
	public Organization getOrganizationByIdAndCtxId(Long id, Long ctxId) throws Exception {
		List<Organization> list = getHibernateTemplate().find("from Organization o where o.id = ? and o.ctxId = ?", new Object[]{id, ctxId});
		return list.size() > 0 ? list.get(0) : null;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Organization> getOrganizationByCtxId(Long ctxId) throws Exception {
		List<Organization> list = getHibernateTemplate().find("from Organization o where o.ctxId = ? ", new Object[]{ctxId});
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public PaginatedResponse getOrganizationList(Long ctxId, PaginatedRequest req) throws Exception {
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
				@SuppressWarnings({  "deprecation" })
		Criteria crit = getHibernateTemplate().getSession().createCriteria(req.getClazz());
		crit.add( Restrictions.eq("ctxId", ctxId));
		
		crit.addOrder(Order.desc("id"));
		crit.setProjection(Projections.rowCount());
		
		Long totalSize = (Long)crit.uniqueResult();
		if(totalSize > 0) {
			crit.setProjection(null);
			crit.setFirstResult(offSet.intValue());
			crit.setMaxResults(req.getPageSize().intValue());
			
			List<? extends Persistent> list = crit.list();
			if(list != null && !list.isEmpty()) {
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
	public Organization getOrganizationByCtxIdAndId(Long ctxId, Long id) throws Exception {
		List<Organization> list = getHibernateTemplate().find("from Organization o where o.ctxId = ? and o.id = ? ", new Object[]{ctxId, id});
		return list.size() > 0 ? list.get(0) : null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Organization getNextOrganizationByCtxIdAndId(Long ctxId, Long id) throws Exception {
		List<Organization> list = getHibernateTemplate().find("from Organization o where o.ctxId = ?  and o.id > ? order by o.id asc ", new Object[]{ctxId, id});
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Organization getOrganizationByName(Long ctxId, String name) throws Exception {
		List<Organization> list = getHibernateTemplate().find("from Organization od where od.ctxId = ? and od.name = ? ", new Object[]{ctxId, name});
		return list.size() > 0 ? list.get(0) : null;
	}
	
}