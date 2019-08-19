package com.bytesedge.bookvenue.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.Hibernate;

/*
 * @author Divya
 */
@MappedSuperclass
@SuppressWarnings("serial")
public class Persistent implements Serializable {

	private long id;
	private long version;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Version
	@Column(name = "version", nullable = false)
	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public boolean equals(Object obj) {
		if (obj == null)
			return false;

		if (!(obj instanceof Persistent))
			return false;

		Class<?> src = Hibernate.getClass(this);
		Class<?> dst = Hibernate.getClass(obj);
		if (!src.getClass().getName().equals(dst.getClass().getName())) {return false;}
		return (!checkPersisted()) ? false : (id == ((Persistent) obj).getId());
	}

	@Override
	public int hashCode() {
		final int seed = 32;
		int result = 1;
		result = seed * result + String.valueOf(id).hashCode();
		return result;
	}

	@Transient
	public final boolean checkPersisted() {
		return (id <= 0) ? false : true;
	}

}
