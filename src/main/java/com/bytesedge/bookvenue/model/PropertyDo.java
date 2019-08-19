package com.bytesedge.bookvenue.model;

/*
 * @author Divya
 */
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@SuppressWarnings("serial")
public abstract class PropertyDo extends OrgDo {
	private Long propertyId;

	@Column(name = "property_id")
	public Long getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(Long propertyId) {
		this.propertyId = propertyId;
	}
}
