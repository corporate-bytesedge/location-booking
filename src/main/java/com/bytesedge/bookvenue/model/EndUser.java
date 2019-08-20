package com.bytesedge.bookvenue.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
 * * @author divya
 */
@Entity
@Table(name = "lb_end_user")
@DiscriminatorColumn(name = "class_code", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "end_user")
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class EndUser extends OrgDo implements Auditable, Indexable {
	
	//Create user 
	
	/**
	 * Application id is a unique id generated by system
	 */
	private String applicationId;
	/**
	 * Name is a name of the user
	 */
	private String name;
	/**
	 * OTP is for detect to check the correct user
	 */
	private String otp;
	/**
	 * Email is for user
	 */
	private String email;
	/**
	 * Type is for user identification
	 */
	private IdProofType idProofType= IdProofType.AADHAAR;
	/**
	 * Id proof is a user id proof number
	 */
	private String idProof;
	/**
	 * Username is a unique number for user identification in a DB
	 */
	private String userName;
	/**
	 * Mobile number of user
	 */
	private String mobileNumber;
	/**
	 * Booking date is a date of book a hall
	 */
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private Date bookingDate;
	/**
	 * Property id is a id of venue hall 
	 */
	private Long propertyId;
	/**
	 * Purpose id is a id of purpose 
	 */
	private Long purposeId;
	/**
	 * Price of a venue hall
	 */
	private Float price = 0.00F;
	/**
	 * Service tax is a surcharge of venue hall 
	 */
	private Float serviceTax = 0.00F;
	/**
	 * Total is a total amount including all taxes
	 * toal = price + (price * cgst)/100 + (price * sgst)/100 + (price * serviceTax)/100
	 */
	private Float total = 0.00F;
	/**
	 * CGST is a type of tax charge
	 */
	private Float cgst = 0.00F;
	/**
	 * SGST is a type of tax charge
	 */
	private Float sgst = 0.00F;
	/**
	 * Address of a user
	 */
	private String addr;
	/**
	 * Referred by is reference to book this venue hall
	 */
	private String referredBy;
	/**
	 * Booking type is nothing to check whether its login or without login the user to book a venue hall 
	 */
	private BookingType bookingType  = BookingType.REGISTER;
	/**
	 * Payment status shows the status of amount is paid or pending or other
	 */
	private PaymentStatus paymentStatus = PaymentStatus.PENDING;
	/**
	 * Payment mode is a form of way to pay the amount
	 */
	private String paymentMode;
	/**
	 * User type is for booking status nothing but that booking is conformed are canceled
	 */
	private UserState userState = UserState.ACTIVE;
	//invoice details
	/**
	 * pRPId is property rent price id is based to find the property(venue) exact price
	 */
	private Long propertyRentPriceId;
	/**
	 * Expiry date for invoice
	 */
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private Date expiryDate;

	
	//  payment gateway details to generate bill
	
	/**
	 * Name in a payment gateway response field
	 */
	private String namePgRes;
	/**
	 * Date in a payment gateway response field
	 */
	private String txDatePgRes;
	/**
	 * Surcharge in a payment gateway response field
	 */
	private Float surChargePgRes = 0.00F;
	/**
	 * Discriminator in a discr1 
	 */
	private String discrPgRes;
	/**
	 * Card number in a discriminator
	 */
	private String cardNumberPgRes;
	/**
	 * merchant transaction in a payment gateway response field
	 */
	private String merTxnPgRes;
	/**
	 * mmpTxn in a payment gateway response field
	 */
	private String mmpTxnPgRes;
	/**
	 * prod in a payment gateway response field
	 */
	private String prodIdPgRes;
	/**
	 * bnkTxn in a payment gateway response field
	 */
	private String bankTxnIdPgRes;
	/**
	 * auth code in a payment gateway response field
	 */
	private String authCodePgRes;
	/**
	 * fCode in a payment gateway response field
	 */
	private String fCodePgRes;
	/**
	 * clientCode in a payment gateway response field
	 */
	private String clientCodePgRes;
	/**
	 * bankName in a payment gateway response field
	 */
	private String bankNamePgRes;
	/**
	 * ipgTxn in a payment gateway response field
	 */
	private String ipgTxnIdPgRes;
	/**
	 * merId in a payment gateway response field 
	 */
	private String merIdPgRes;
	/**
	 * desc in a payment gateway response field
	 */
	private String descPgRes;
	/**
	 * udf9 in a payment gateway response field
	 */
	private String udf9PgRes;
	/**
	 * udf5 in a payment gateway response field
	 */
	private String udf5PgRes;
	/**
	 * udf6 in a  payment gateway response field
	 */
	private String udf6PgRes;
	/**
	 * resSign in a payment gateway response field
	 */
	private String resSignPgRes;
	/**
	 * amt in a payment gateway response field
	 */
	private String amtPgRes;
	
	// Transient
	/**
	 * Organization name
	 */
	private String orgName;
	/**
	 * created username of who create this venue hall
	 */
	private String createdUserName;
	/**
	 * updated username 
	 */
	private String updatedUserName;
	/**
	 * property or venue hall name
	 */
	private String propertyName;
	/**
	 * OTP Confirmation attribute, no need to store
	 */
	private String password;
	/**
	 * purpose name 
	 */
	private String purposeName;
	/**
	 * Marquee is a content scroller text at register page
	 */
	private String marquee;
	/**
	 * Slot type shows the booking date is a weekday or week end
	 */
	private SlotType slotType = SlotType.WEEKDAY;
	/**
	 * Payment url is a url to navigate the payment page
	 */
	private String paymentUrl;
	/*
	 * property rent price name is a rent property name
	 */
	private String propertyRentPriceName;
	
	// generate the getter & setter methods and map to dataBase columns
	private String billList;
	private Long venueDaysLimit; 
	private String fromDate;
	
	private String toDate;

	@Column( name = "application_id")
	public String getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
	
	@Column( name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column( name = "otp")
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	
	@Column( name = "email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name = "id_proof_type")
	public IdProofType getIdProofType() {
		return idProofType;
	}
	public void setIdProofType(IdProofType idProofType) {
		this.idProofType = idProofType;
	}
	
	@Column( name = "id_proof")
	public String getIdProof() {
		return idProof;
	}
	public void setIdProof(String idProof) {
		this.idProof = idProof;
	}
	
	@Column( name = "user_name")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column( name = "mobile_number")
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Temporal(TemporalType.DATE)
	@Column( name = "booking_date")
	public Date getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}
	
	@Column( name = "property_id")
	public Long getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(Long propertyId) {
		this.propertyId = propertyId;
	}
	
	@Column (name = "purpose_id")
	public Long getPurposeId() {
		return purposeId;
	}
	public void setPurposeId(Long purposeId) {
		this.purposeId = purposeId;
	}
	
	@Column( name = "price")
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	
	@Column( name = "service_tax")
	public Float getServiceTax() {
		return serviceTax;
	}
	public void setServiceTax(Float serviceTax) {
		this.serviceTax = serviceTax;
	}
	
	@Column( name  = "total")
	public Float getTotal() {
		return total;
	}
	public void setTotal(Float total) {
		this.total = total;
	}
	
	@Column( name = "cgst")
	public Float getCgst() {
		return cgst;
	}
	public void setCgst(Float cgst) {
		this.cgst = cgst;
	}
	
	@Column( name = "sgst")
	public Float getSgst() {
		return sgst;
	}
	public void setSgst(Float sgst) {
		this.sgst = sgst;
	}
	
	@Column( name = "addr")
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	@Column( name = "referred_by")
	public String getReferredBy() {
		return referredBy;
	}
	public void setReferredBy(String referredBy) {
		this.referredBy = referredBy;
	}
	
	@Enumerated(EnumType.STRING)
	@Column( name = "booking_type")
	public BookingType getBookingType() {
		return bookingType;
	}
	public void setBookingType(BookingType bookingType) {
		this.bookingType = bookingType;
	}
	
	@Enumerated(EnumType.STRING)
	@Column( name = "payment_status")
	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	
	@Column( name = "payment_mode")
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	
	@Enumerated(EnumType.STRING)
	@Column( name = "user_state")
	public UserState getUserState() {
		return userState;
	}
	public void setUserState(UserState userState) {
		this.userState = userState;
	}
	
	@Column( name = "property_rent_price_id")
	public Long getPropertyRentPriceId() {
		return propertyRentPriceId;
	}
	public void setPropertyRentPriceId(Long propertyRentPriceId) {
		this.propertyRentPriceId = propertyRentPriceId;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column( name = "expiry_date")
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	@Column( name = "name_pg_res")
	public String getNamePgRes() {
		return namePgRes;
	}
	public void setNamePgRes(String namePgRes) {
		this.namePgRes = namePgRes;
	}
	
	@Column( name = "txn_date_pg_res")
	public String getTxDatePgRes() {
		return txDatePgRes;
	}
	public void setTxDatePgRes(String txDatePgRes) {
		this.txDatePgRes = txDatePgRes;
	}
	
	@Column( name = "sur_charge_pg_res")
	public Float getSurChargePgRes() {
		return surChargePgRes;
	}
	public void setSurChargePgRes(Float surChargePgRes) {
		this.surChargePgRes = surChargePgRes;
	}
	
	@Column( name = "discr_pg_res")
	public String getDiscrPgRes() {
		return discrPgRes;
	}
	public void setDiscrPgRes(String discrPgRes) {
		this.discrPgRes = discrPgRes;
	}
	
	@Column( name = "card_number_pg_res")
	public String getCardNumberPgRes() {
		return cardNumberPgRes;
	}
	public void setCardNumberPgRes(String cardNumberPgRes) {
		this.cardNumberPgRes = cardNumberPgRes;
	}
	
	@Column( name = "mer_txn_pg_res")
	public String getMerTxnPgRes() {
		return merTxnPgRes;
	}
	public void setMerTxnPgRes(String merTxnPgRes) {
		this.merTxnPgRes = merTxnPgRes;
	}
	
	@Column( name = "mmp_txn_pg_res")
	public String getMmpTxnPgRes() {
		return mmpTxnPgRes;
	}
	public void setMmpTxnPgRes(String mmpTxnPgRes) {
		this.mmpTxnPgRes = mmpTxnPgRes;
	}
	
	@Column( name = "prod_id_pg_res")
	public String getProdIdPgRes() {
		return prodIdPgRes;
	}
	public void setProdIdPgRes(String prodIdPgRes) {
		this.prodIdPgRes = prodIdPgRes;
	}
	
	@Column( name = "bank_txn_id_pg_res")
	public String getBankTxnIdPgRes() {
		return bankTxnIdPgRes;
	}
	public void setBankTxnIdPgRes(String bankTxnIdPgRes) {
		this.bankTxnIdPgRes = bankTxnIdPgRes;
	}
	
	@Column( name = "auth_code_pg_res")
	public String getAuthCodePgRes() {
		return authCodePgRes;
	}
	public void setAuthCodePgRes(String authCodePgRes) {
		this.authCodePgRes = authCodePgRes;
	}
	
	@Column( name = "f_code_pg_res")
	public String getfCodePgRes() {
		return fCodePgRes;
	}
	public void setfCodePgRes(String fCodePgRes) {
		this.fCodePgRes = fCodePgRes;
	}
	
	@Column( name = "client_code_pg_res")
	public String getClientCodePgRes() {
		return clientCodePgRes;
	}
	public void setClientCodePgRes(String clientCodePgRes) {
		this.clientCodePgRes = clientCodePgRes;
	}
	
	@Column( name = "bank_name_pg_res")
	public String getBankNamePgRes() {
		return bankNamePgRes;
	}
	public void setBankNamePgRes(String bankNamePgRes) {
		this.bankNamePgRes = bankNamePgRes;
	}
	
	@Column( name = "ipg_txn_id_pg_res")
	public String getIpgTxnIdPgRes() {
		return ipgTxnIdPgRes;
	}
	public void setIpgTxnIdPgRes(String ipgTxnIdPgRes) {
		this.ipgTxnIdPgRes = ipgTxnIdPgRes;
	}
	
	@Column( name = "mer_id_pg_res")
	public String getMerIdPgRes() {
		return merIdPgRes;
	}
	public void setMerIdPgRes(String merIdPgRes) {
		this.merIdPgRes = merIdPgRes;
	}
	
	@Column( name = "desc_pg_res")
	public String getDescPgRes() {
		return descPgRes;
	}
	public void setDescPgRes(String descPgRes) {
		this.descPgRes = descPgRes;
	}
	
	@Column( name = "udf9_pg_res")
	public String getUdf9PgRes() {
		return udf9PgRes;
	}
	public void setUdf9PgRes(String udf9PgRes) {
		this.udf9PgRes = udf9PgRes;
	}
	
	@Column( name = "udf5_pg_res")
	public String getUdf5PgRes() {
		return udf5PgRes;
	}
	public void setUdf5PgRes(String udf5PgRes) {
		this.udf5PgRes = udf5PgRes;
	}
	
	@Column( name = "udf6_pg_res")
	public String getUdf6PgRes() {
		return udf6PgRes;
	}
	public void setUdf6PgRes(String udf6PgRes) {
		this.udf6PgRes = udf6PgRes;
	}
	
	@Column( name = "res_sign_pg_res")
	public String getResSignPgRes() {
		return resSignPgRes;
	}
	public void setResSignPgRes(String resSignPgRes) {
		this.resSignPgRes = resSignPgRes;
	}
	
	@Column( name = "amt_pg_res")
	public String getAmtPgRes() {
		return amtPgRes;
	}
	public void setAmtPgRes(String amtPgRes) {
		this.amtPgRes = amtPgRes;
	}
	
	@Transient
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
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
	public String getMarquee() {
		return marquee;
	}
	public void setMarquee(String marquee) {
		this.marquee = marquee;
	}
	
	@Transient
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Transient
	public SlotType getSlotType() {
		return slotType;
	}
	public void setSlotType(SlotType slotType) {
		this.slotType = slotType;
	}
	
	@Column( name = "payment_url")
	public String getPaymentUrl() {
		return paymentUrl;
	}
	public void setPaymentUrl(String paymentUrl) {
		this.paymentUrl = paymentUrl;
	}
	
	@Transient
	public String getBillList() {
		return billList;
	}
	public void setBillList(String billList) {
		this.billList = billList;
	}
	
	@Transient
	public String getPropertyRentPriceName() {
		return propertyRentPriceName;
	}
	public void setPropertyRentPriceName(String propertyRentPriceName) {
		this.propertyRentPriceName = propertyRentPriceName;
	}
	@Transient
	public Long getVenueDaysLimit() {
		return venueDaysLimit;
	}
	public void setVenueDaysLimit(Long venueDaysLimit) {
		this.venueDaysLimit = venueDaysLimit;
	}
	@Transient
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	@Transient
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	}