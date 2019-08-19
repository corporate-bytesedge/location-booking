package com.bytesedge.bookvenue.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.ui.Model;

@MappedSuperclass
@SuppressWarnings("serial")
public abstract class ContextDo extends BaseDo {

	/** ctxId is a unique id **/
	private Long ctxId;

	/** captcha text is asix letter text to validate the form **/
	private String captchaText;

	/** time when it was created **/
	private Date createdTime;

	/** time when it was updated **/
	private Date updatedTime;

	/**
	 * createdUserId is a id, who created with their login either user ,admin Or
	 * management
	 **/
	private Long createdUserId;

	/**
	 * updatedUserId is a id, who updated with their login either user ,admin Or
	 * management
	 **/
	private Long updatedUserId;

	@Column(name = "ctx_id")
	public Long getCtxId() {
		return ctxId;
	}

	public void setCtxId(Long ctxId) {
		this.ctxId = ctxId;
	}

	@Transient
	public String getCaptchaText() {
		return captchaText;
	}

	public void setCaptchaText(String captchaText) {
		this.captchaText = captchaText;
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

	@Column(name = "created_user_id")
	public Long getCreatedUserId() {
		return createdUserId;
	}

	public void setCreatedUserId(Long createdUserId) {
		this.createdUserId = createdUserId;
	}

	@Column(name = "updated_user_id")
	public Long getUpdatedUserId() {
		return updatedUserId;
	}

	public void setUpdatedUserId(Long updatedUserId) {
		this.updatedUserId = updatedUserId;
	}

	public void customModalAttributes(Model model) {
		// TODO Auto-generated method stub

	}

}
