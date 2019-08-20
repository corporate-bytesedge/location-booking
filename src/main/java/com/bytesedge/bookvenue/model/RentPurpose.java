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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Divya
 */
/*
 * This form shows the different purposes
 */
@Entity
@Table(name = "lb_rent_purpose")
@DiscriminatorColumn(name = "class_code", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "rent_purpose")
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class RentPurpose extends OrgDo implements Auditable, Indexable {
	/*
	 * name of the purpose
	 */
	private String name;
	/*
	 * Description of that purpose
	 */
	private String descr;
	/*
	 * status of that purpose
	 */
	private StateType state = StateType.Active;
	/*
	 * Transient fields
	 */
	private String createdUsername;
	private String updatedUsername;
	private String orgName;

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "descr")
	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "state")
	public StateType getState() {
		return state;
	}

	public void setState(StateType state) {
		this.state = state;
	}

	@Transient
	public String getCreatedUsername() {
		return createdUsername;
	}

	public void setCreatedUsername(String createdUsername) {
		this.createdUsername = createdUsername;
	}

	@Transient
	public String getUpdatedUsername() {
		return updatedUsername;
	}

	public void setUpdatedUsername(String updatedUsername) {
		this.updatedUsername = updatedUsername;
	}

	@Transient
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
}