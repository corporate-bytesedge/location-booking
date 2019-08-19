package com.bytesedge.bookvenue.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * @author divya
 */
@MappedSuperclass
@SuppressWarnings("serial")
public abstract class BaseDo extends Persistent implements Cloneable {

	private String classCode;

	@Column(name = "class_code", insertable = false, updatable = false)
	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
}
