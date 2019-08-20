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
/*
 * stores the roles of an user
 */
@Entity
@Table(name = "lb_user_role")
@DiscriminatorColumn(name = "class_code", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "user_role")
@SuppressWarnings("serial")
public class UserRole extends ContextDo implements Auditable, Indexable {
	public static final String PREFIX = "ROLE_";
	/*
	 * name of a role
	 */
	private String name;
	/*
	 * Transient fields
	 */
	private String createdUserName;
	private String updatedUserName;

	public static final String ADMIN = PREFIX + "ADMIN";
	private Boolean admin = Boolean.FALSE;

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "admin")
	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
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