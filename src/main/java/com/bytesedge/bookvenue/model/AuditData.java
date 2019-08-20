package com.bytesedge.bookvenue.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author divya
 */
@Entity
@Table(name = "lb_audit_data")
@DiscriminatorColumn(name = "class_code", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "audit_data")
@SuppressWarnings("serial")

/**
 * Audit data is one application which maintains a record of other applications
 * operations in a project
 **/
public class AuditData extends OrgDo {

	/** operation shows which action performed in a form **/
	private AuditOperationType operation = AuditOperationType.CREATE;

	/**
	 * auditObject is a class defines the name of the class can using the object
	 **/
	private AuditObject auditObject = AuditObject.None;

	/**
	 * src is nothing but the data present in a table before modification or any
	 * actions like create and update
	 **/
	private String src;

	/** dst is nothing but the data after modified in that form **/
	private String dst;

	/** orgId shows the organization is nothing but a property or a venue **/
	private Long orgId;

	/** Transient fields **/
	/** orgName shows name of the venue **/
	private String orgName;

	/**
	 * Transient fields returns name of the user who was created the entries
	 **/
	private String createdUserName;
	private String updatedUserName;

	@Enumerated(EnumType.STRING)
	@Column(name = "operation")
	public AuditOperationType getOperation() {
		return operation;
	}

	public void setOperation(AuditOperationType operation) {
		this.operation = operation;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "name_of_application")
	public AuditObject getAuditObject() {
		return auditObject;
	}

	public void setAuditObject(AuditObject auditObject) {
		this.auditObject = auditObject;
	}

	@Column(name = "src")
	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	@Column(name = "dst")
	public String getDst() {
		return dst;
	}

	public void setDst(String dst) {
		this.dst = dst;
	}

	@Column(name = "org_id")
	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	@Transient
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	@Transient
	public String getCreatedUserName() {
		return createdUserName;
	}

	public void setCreatedUserName(String createdUserName) {
		this.createdUserName = createdUserName;
	}

	@Transient
	public String getUpdatedUserName() {
		return updatedUserName;
	}

	public void setUpdatedUserName(String updatedUserName) {
		this.updatedUserName = updatedUserName;
	}

}