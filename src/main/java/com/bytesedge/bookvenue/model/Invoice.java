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
 * @author divya
 */
/*
 * This class is used to generate the invoice of a book a venue
 */
@Entity
@Table(name = "lb_invoice")
@DiscriminatorColumn(name = "class_code", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "invoice")
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class Invoice extends PropertyDo implements Auditable, Indexable {

	/*
	 * application id is an id generated for each invoice
	 */
	private String applicationId;
	/*
	 * idProofType is an enum class defines the different id proof for a user
	 */
	private IdProofType type = IdProofType.AADHAAR;
	/*
	 * idproof is a number of that idProofType
	 */
	private String idProof;
	/*
	 * addr is the address of the user
	 */
	private String addr;
	/*
	 * pRPId is an id of property rent price for getting the price of a property
	 */
	private Long pRPId;
	/*
	 * purpose id is an id of a purposes like marriage, birthday..
	 */
	private Long purposeId;
	/*
	 * total price is a price that includes price with all taxes
	 */
	private Float totalPrice = 0.0F;
	/*
	 * rent price shows only price of that hall
	 */
	private Float rentPrice = 0.0F;
	/*
	 * cgst is a one type of tax
	 */
	private Float cgst = 0.00F;
	/*
	 * sgst is a one type tax
	 */
	private Float sgst = 0.00F;
	/*
	 * surCharge is a one type tax
	 */
	private Float surCharge = 0.00F;
	/*
	 * status includes the status of paying amount
	 */
	private PaymentStatus status = PaymentStatus.PENDING;
	/*
	 * This date indicates the booking date of a venue
	 */
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date date;
	/*
	 * Expiry date when expires the booking date
	 */
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date expiryDate;
	/*
	 * clientCode is a string that generates a unique random string for
	 * identification of an invoice
	 */
	private String clientCode;
	/*
	 * name is name of the user
	 */
	private String name;
	/*
	 * email is an email of an user
	 */
	private String email;
	/*
	 * mobile number is an user mobile number
	 */
	private String mobileNumber;

	/* Transient fields */
	private String createdUserName;
	private String updatedUserName;
	private String propertyName;
	private String orgName;
	private String purposeName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "expiry_time")
	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	@Column(name = "client_code")
	public String getClientCode() {
		return clientCode;
	}

	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}

	@Transient
	public String getPurposeName() {
		return purposeName;
	}

	public void setPurposeName(String purposeName) {
		this.purposeName = purposeName;
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "p_rent_price_id")
	public Long getpRPId() {
		return pRPId;
	}

	public void setpRPId(Long pRPId) {
		this.pRPId = pRPId;
	}

	@Column(name = "total_price")
	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Column(name = "rent_price")
	public float getRentPrice() {
		return rentPrice;
	}

	public void setRentPrice(float rentPrice) {
		this.rentPrice = rentPrice;
	}

	@Column(name = "application_id")
	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "id_proof_type")
	public IdProofType getType() {
		return type;
	}

	public void setType(IdProofType type) {
		this.type = type;
	}

	@Column(name = "proof_id")
	public String getIdproof() {
		return idProof;
	}

	public void setIdproof(String idProof) {
		this.idProof = idProof;
	}

	@Column(name = "addr")
	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	@Column(name = "mobile_number")
	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	public PaymentStatus getStatus() {
		return status;
	}

	public void setStatus(PaymentStatus status) {
		this.status = status;
	}

	@Column(name = "sur_charge")
	public Float getSurCharge() {
		return surCharge;
	}

	public void setSurCharge(Float surCharge) {
		this.surCharge = surCharge;
	}

	public Long getPurposeId() {
		return purposeId;
	}

	public void setPurposeId(Long purposeId) {
		this.purposeId = purposeId;
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
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

}