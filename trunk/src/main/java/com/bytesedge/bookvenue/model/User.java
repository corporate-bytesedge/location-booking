package com.bytesedge.bookvenue.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Divya
 */
/*
 * Its stores the user details who registered
 */
@Entity
@Table(name = "lb_user")
@DiscriminatorColumn(name = "class_code", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "user")
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class User extends OrgDo implements Auditable, Indexable {
	/*
	 * Email address of a user
	 */
	private String loginName;
	/*
	 * password
	 */
	private String loginPassword;
	/*
	 * status of that user
	 */
	private UserState accountState = UserState.ACTIVE;
	/*
	 * email of the user
	 */
	private String email;
	/*
	 * name of the user
	 */
	private String displayName;
	/*
	 * gender
	 */
	private GenderType gender = GenderType.MALE;
	/*
	 * aadhaar id of the user
	 */
	private String aadharId;
	/*
	 * mobile number of the user
	 */
	private String mobileNumber;
	/*
	 * date of birth of the user
	 */
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date dob;
	/*
	 * authentication failed time of user
	 */
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date authFailedTime;
	/*
	 * locked time of the user
	 */
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date lockedTime;
	/*
	 * designation of the user
	 */
	private String designation;
	/*
	 * Role of the user like for example admin, user etc..
	 */
	private Long roleId;
	/*
	 * Transient fields
	 */
	private String orgName;
	private String roleName;
	private Long authFailed;
	private String createdUserName;
	private String updatedUserName;

	@Column(name = "login_name")
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Column(name = "login_password")
	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "account_state")
	public UserState getAccountState() {
		return accountState;
	}

	public void setAccountState(UserState accountState) {
		this.accountState = accountState;
	}

	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "display_name")
	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	@Column(name = "aadhar_id")
	public String getAadharId() {
		return aadharId;
	}

	public void setAadharId(String aadharId) {
		this.aadharId = aadharId;
	}

	@Column(name = "mobile_number")
	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dob")
	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	@Column(name = "designation")
	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "gender")
	public GenderType getGender() {
		return gender;
	}

	public void setGender(GenderType gender) {
		this.gender = gender;
	}

	@Column(name = "role_id")
	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	@Column(name = "auth_failed")
	public Long getAuthFailed() {
		return authFailed;
	}

	public void setAuthFailed(Long authFailed) {
		this.authFailed = authFailed;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "auth_failed_time")
	public Date getAuthFailedTime() {
		return authFailedTime;
	}

	public void setAuthFailedTime(Date authFailedTime) {
		this.authFailedTime = authFailedTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "locked_time")
	public Date getLockedTime() {
		return lockedTime;
	}

	public void setLockedTime(Date lockedTime) {
		this.lockedTime = lockedTime;
	}

	@Transient
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	@Transient
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
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