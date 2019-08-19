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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author divya
 */
/*
 * property rent price shows the price of book a venue and including taxes
 */
@Entity
@Table(name = "sbv_property_rent_price")
@DiscriminatorColumn(name = "class_code", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "property_rent_price")
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class PropertyRentPrice extends PropertyDo implements Auditable, Indexable {
	/*
	 * slotType shows the type of the day like weekday or weekend
	 */
	private SlotType slotType = SlotType.NONE;
	/*
	 * start time
	 */
	private Date startTime;
	/*
	 * end time
	 */
	private Date endTime;
	/*
	 * price of the venue
	 */
	private Float price;
	/*
	 * service tax of that venue
	 */
	private Float serviceTax = 0.00F;
	/*
	 * cgst is a type of tax
	 */
	private Float cgst = 0.00F;
	/*
	 * sgst is a type of tax
	 */
	private Float sgst = 0.00F;
	/*
	 * igst is a type of tax
	 */
	private Float igst = (cgst + sgst);
	/*
	 * status of that property
	 */
	private StateType state = StateType.Active;
	/*
	 * id of the purpose
	 */
	private Long purposeId;
	/*
	 * Transient fields
	 */
	private String createdUserName;
	private String updatedUserName;
	private String propertyName;
	private String purposeName;
	private String orgName;

	@Enumerated(EnumType.STRING)
	@Column(name = "slot_type")
	public SlotType getSlotType() {
		return slotType;
	}

	public void setSlotType(SlotType slotType) {
		this.slotType = slotType;
	}

	@Column(name = "price")
	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "state")
	public StateType getState() {
		return state;
	}

	public void setState(StateType state) {
		this.state = state;
	}

	@Column(name = "purpose_id")
	public Long getPurposeId() {
		return purposeId;
	}

	public void setPurposeId(Long purposeId) {
		this.purposeId = purposeId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_time")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_time")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Column(name = "cgst")
	public Float getCgst() {
		return cgst;
	}

	public void setCgst(Float cgst) {
		this.cgst = cgst;
	}

	@Column(name = "sgst")
	public Float getSgst() {
		return sgst;
	}

	public void setSgst(Float sgst) {
		this.sgst = sgst;
	}

	@Transient
	public Float getIgst() {
		return igst;
	}

	public void setIgst(Float igst) {
		this.igst = igst;
	}

	@Column(name = "service_tax")
	public Float getServiceTax() {
		return serviceTax;
	}

	public void setServiceTax(Float serviceTax) {
		this.serviceTax = serviceTax;
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

	@Transient
	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	@Transient
	public String getPurposeName() {
		return purposeName;
	}

	public void setPurposeName(String purposeName) {
		this.purposeName = purposeName;
	}

	@Transient
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
}