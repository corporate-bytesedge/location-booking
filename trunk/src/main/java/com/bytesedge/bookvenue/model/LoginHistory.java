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
import javax.persistence.Transient;

/**
 * @author Divya
 */
/*
 * login history stores the each login details
 */
@SuppressWarnings("serial")
@Entity
@DiscriminatorColumn(name = "class_code", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "login_history")
@Table(name = "lb_login_history")
public class LoginHistory extends BaseDo {

	/*
	 * userId is nothing but in which the id of user login into the website
	 */
	private Long userId;
	/*
	 * orgId stores the id of organization
	 */
	private Long orgId;
	/*
	 * logintime shows the time when the user login in to site
	 */
	private Date loginTime;
	/*
	 * logouttime shows the time when the user logout in to site
	 */
	private Date logoutTime;

	// 1 - normal logout, 2 - forced logout (session timeout, server shutdown,
	// etc.)
	private static LoginHistoryLogoutType logoutType = LoginHistoryLogoutType.Manual;
	/*
	 * sessionId is nothing but the id of the session
	 */
	private String sessionId;
	/*
	 * ctxId is an id of the url
	 */
	private Long ctxId;
	/*
	 * login name is nothing but identity to login
	 */
	private String loginName;
	/*
	 * Transient fields
	 */
	private String orgName;

	public LoginHistory() {
	}

	@Column(name = "ctx_id")
	public Long getCtxId() {
		return ctxId;
	}

	public void setCtxId(Long ctxId) {
		this.ctxId = ctxId;
	}

	@Column(name = "login_name")
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "login_time")
	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "logout_time")
	public Date getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(Date logoutTime) {
		this.logoutTime = logoutTime;
	}

	@Column(name = "logout_type")
	public LoginHistoryLogoutType getLogoutType() {
		return logoutType;
	}

	public void setLogoutType(LoginHistoryLogoutType logoutType) {
		LoginHistory.logoutType = logoutType;
	}

	@Column(name = "session_id")
	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	@Column(name = "user_id")
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

}
