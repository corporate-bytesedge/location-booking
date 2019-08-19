package com.bytesedge.bookvenue.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Divya
 */
@Entity
@Table(name = "sbv_context")
@DiscriminatorColumn(name = "class_code", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "context")
@SuppressWarnings("serial")

/** This class shows details to access the website **/
public class Context extends BaseDo implements Auditable, Indexable {

	/** url defines the IP address to connect the site **/
	private String url;

	/** code defines the unique identification of the url **/
	private String code;

	/** name shows the name of the site **/
	private String name;

	/** phone number of a support team **/
	private String supportPhoneNumber;

	/** mobile number of the support team **/
	private String supportMobileNumber;

	/** email of a support team **/
	private String supportEmailId;

	/** time when it was created **/
	private Date createdTime;

	/** time when it was updated **/
	private Date updatedTime;

	@Column(name = "url")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String orgName) {
		this.name = orgName;
	}

	@Column(name = "support_phone_number")
	public String getSupportPhoneNumber() {
		return supportPhoneNumber;
	}

	public void setSupportPhoneNumber(String supportPhoneNumber) {
		this.supportPhoneNumber = supportPhoneNumber;
	}

	@Column(name = "support_mobile_number")
	public String getSupportMobileNumber() {
		return supportMobileNumber;
	}

	public void setSupportMobileNumber(String supportMobileNumber) {
		this.supportMobileNumber = supportMobileNumber;
	}

	@Column(name = "support_email_id")
	public String getSupportEmailId() {
		return supportEmailId;
	}

	public void setSupportEmailId(String supportEmailId) {
		this.supportEmailId = supportEmailId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_time")
	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	@Column(name = "code")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}