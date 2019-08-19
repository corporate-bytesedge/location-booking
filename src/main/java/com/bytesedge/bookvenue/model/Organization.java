package com.bytesedge.bookvenue.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Divya
 */
@Entity
@Table(name = "sbv_organization")
@DiscriminatorColumn(name = "class_code", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "organization")
@SuppressWarnings("serial")
public class Organization extends ContextDo implements Auditable, Indexable {
	/*
	 * name is the name of the organization or property
	 */
	private String name;
	/*
	 * phone number of that organization
	 */
	private String phoneNumber;
	/*
	 * mobile number of that organization
	 */
	private String mobileNumber;
	/*
	 * email if that organization
	 */
	private String emailId;
	/*
	 * Transient fields
	 */
	private String createdUserName;
	private String updatedUserName;

	@Column(name = "org_name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "support_phone_number")
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Column(name = "support_mobile_number")
	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@Column(name = "support_email_id")
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
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