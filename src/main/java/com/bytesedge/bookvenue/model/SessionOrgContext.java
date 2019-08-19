package com.bytesedge.bookvenue.model;

import java.io.Serializable;

/**
 * @author Divya
 */
@SuppressWarnings("serial")
public class SessionOrgContext implements Serializable {
	private Long ctxId;
	private Long orgId;
	
	public void reset() {
		ctxId = null;
		
	}
	
	public Long currentOrgId() {
		return null;
	}

	public Long getCtxId() {
		return ctxId;
	}

	public void setCtxId(Long ctxId) {
		this.ctxId = ctxId;
	}

	

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
}