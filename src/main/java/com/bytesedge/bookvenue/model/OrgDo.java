package com.bytesedge.bookvenue.model;

/*
 * @author Divya
 */
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@SuppressWarnings("serial")
public abstract class OrgDo extends ContextDo {
	/*
	 * id of that organization
	 */
	private Long orgId;

	@Column(name = "org_id")
	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
}
