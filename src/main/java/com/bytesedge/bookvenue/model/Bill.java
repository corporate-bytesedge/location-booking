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
@Entity
@Table(name = "lb_bill")
@DiscriminatorColumn(name = "class_code", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "bill")
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
/**
 * Bill class is used to generate the bill or overall details of that booked
 * venue
 **/
public class Bill extends PropertyDo implements Auditable, Indexable {

	// end user fields
	private String applicationId;
	private String name;

	private String otp;
	private String password;
	private String email;
	private IdProofType type = IdProofType.AADHAAR;
	private String idProof;
	private String userName;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date date;
	private Long propertyId;
	private Long purposeId;
	private String addr;
	private String mobileNumber;
	private String orgName;
	private String createdUserName;
	private String updatedUserName;
	private String propertyName;
	private String purposeName;
	private Float price = 0.00F;
	private Float serviceTax = 2.84F;
	private Float txCharge = 1.00F;
	private SlotType slotType = SlotType.WEEKDAY;
	private String paymentUrl;
	private Float total = 0.00F;
	private Float cgst = 9.00F;
	private Float sgst = 9.00F;
	private String referedBy;

	// * invoice fileds
	private String applicationIdInvoice;
	private Long invoiceId;
	private IdProofType typeInvoice = IdProofType.AADHAAR;
	private String idProofInvoice;
	private String addrInvoice;
	private Long pRPIdInvoice;
	private Float totalPriceInvoice = 0.00F;
	private Float rentPriceInvoice = 0.00F;
	private Float cgstInvoice = 9.00F;
	private Float sgstInvoice = 9.00F;
	private PaymentStatus statusInvoice = PaymentStatus.PENDING;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date dateInvoice;
	private String clientCodeInvoice;
	private String nameInvoice;
	private String emailInvoice;
	private Float surChargeInvoice = 0.00F;
	private String mobileNumberInvoice;
	private String purposeNameInvoice;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date expiryDate;
	private Float depositPrice;
	

	// payment gateway response fields
	private String namePgRes;
	private String emailPgRes;
	private String datePgRes;
	private String addrPgRes;
	private String mobileNumberPgRes;
	private Float surChargePgRes = 0.00F;
	private String discrPgRes;
	private String cardNumberPgRes;
	private String merTxnPgRes;
	private String mmpTxnPgRes;
	private String prodPgRes;
	private String bnkTxnPgRes;
	private String authCodePgRes;
	private String fCodePgRes;
	private String clientCodePgRes;
	private String bankNamePgRes;
	private String ipgTxnIdPgRes;
	private String merIdPgRes;
	private String descPgRes;
	private String udf9PgRes;
	private String udf5PgRes;
	private String udf6PgRes;
	private String resSignPgRes;
	private String amtPgRes;
	private String createdUserNamePgRes;
	private String updatedUserNamePgRes;
	private PaymentMode paymentModePgRes = PaymentMode.PAYMENTGATEWAY;
	private PaymentStatus paymentStatusPgRes = PaymentStatus.PAID;

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

	@Column(name = "otp")
	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
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
	public String getIdProof() {
		return idProof;
	}

	public void setIdProof(String idProof) {
		this.idProof = idProof;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "property_id")
	public Long getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(Long propertyId) {
		this.propertyId = propertyId;
	}

	@Column(name = "purpose_id")
	public Long getPurposeId() {
		return purposeId;
	}

	public void setPurposeId(Long purposeId) {
		this.purposeId = purposeId;
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

	@Transient
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	@Transient
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "username")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	@Column(name = "price")
	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	@Transient
	public SlotType getSlotType() {
		return slotType;
	}

	public void setSlotType(SlotType slotType) {
		this.slotType = slotType;
	}

	@Transient
	public String getPaymentUrl() {
		return paymentUrl;
	}

	public void setPaymentUrl(String paymentUrl) {
		this.paymentUrl = paymentUrl;
	}

	@Column(name = "txsc")
	public Float getTxCharge() {
		return txCharge;
	}

	public void setTxCharge(Float txCharge) {
		this.txCharge = txCharge;
	}

	@Column(name = "total")
	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
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

	// invoice

	@Column(name = "client_code_in")
	public String getClientCodeInvoice() {
		return clientCodeInvoice;
	}

	public void setClientCodeInvoice(String clientCodeInvoice) {
		this.clientCodeInvoice = clientCodeInvoice;
	}

	@Transient
	public String getPurposeNameInvoice() {
		return purposeNameInvoice;
	}

	public void setPurposeNameInvoice(String purposeNameInvoice) {
		this.purposeNameInvoice = purposeNameInvoice;
	}

	@Column(name = "cgst_in")
	public Float getCgstInvoice() {
		return cgstInvoice;
	}

	public void setCgstInvoice(Float cgstInvoice) {
		this.cgstInvoice = cgstInvoice;
	}

	@Column(name = "sgst_in")
	public Float getSgstInvoice() {
		return sgstInvoice;
	}

	public void setSgstInvoice(Float sgstInvoice) {
		this.sgstInvoice = sgstInvoice;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_in")
	public Date getDateInvoice() {
		return dateInvoice;
	}

	public void setDateInvoice(Date dateInvoice) {
		this.dateInvoice = dateInvoice;
	}

	@Column(name = "p_rent_price_id")
	public Long getpRPIdInvoice() {
		return pRPIdInvoice;
	}

	public void setpRPIdInvoice(Long pRPIdInvoice) {
		this.pRPIdInvoice = pRPIdInvoice;
	}

	@Column(name = "total_price")
	public Float getTotalPriceInvoice() {
		return totalPriceInvoice;
	}

	public void setTotalPriceInvoice(Float totalPriceInvoice) {
		this.totalPriceInvoice = totalPriceInvoice;
	}

	@Column(name = "rent_price")
	public float getRentPriceInvoice() {
		return rentPriceInvoice;
	}

	public void setRentPriceInvoice(float rentPriceInvoice) {
		this.rentPriceInvoice = rentPriceInvoice;
	}

	@Column(name = "application_id_in")
	public String getApplicationIdInvoice() {
		return applicationIdInvoice;
	}

	public void setApplicationIdInvoice(String applicationIdInvoice) {
		this.applicationIdInvoice = applicationIdInvoice;
	}

	@Column(name = "name_in")
	public String getNameInvoice() {
		return nameInvoice;
	}

	public void setNameInvoice(String nameInvoice) {
		this.nameInvoice = nameInvoice;
	}

	@Column(name = "email_in")
	public String getEmailInvoice() {
		return emailInvoice;
	}

	public void setEmailInvoice(String emailInvoice) {
		this.emailInvoice = emailInvoice;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "id_proof_type_in")
	public IdProofType getTypeInvoice() {
		return typeInvoice;
	}

	public void setTypeInvoice(IdProofType typeInvoice) {
		this.typeInvoice = typeInvoice;
	}

	@Column(name = "proof_id_in")
	public String getIdProofInvoice() {
		return idProofInvoice;
	}

	public void setIdProofInvoice(String idProofInvoice) {
		this.idProofInvoice = idProofInvoice;
	}

	@Column(name = "addr_in")
	public String getAddrInvoice() {
		return addrInvoice;
	}

	public void setAddrInvoice(String addrInvoice) {
		this.addrInvoice = addrInvoice;
	}

	@Column(name = "mobile_number_in")
	public String getMobileNumberInvoice() {
		return mobileNumberInvoice;
	}

	public void setMobileNumberInvoice(String mobileNumberInvoice) {
		this.mobileNumberInvoice = mobileNumberInvoice;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	public PaymentStatus getStatusInvoice() {
		return statusInvoice;
	}

	public void setStatusInvoice(PaymentStatus statusInvoice) {
		this.statusInvoice = statusInvoice;
	}

	// payment response

	@Column(name = "udf1")
	public String getNamePgRes() {
		return namePgRes;
	}

	public void setNamePgRes(String namePgRes) {
		this.namePgRes = namePgRes;
	}

	@Column(name = "udf2")
	public String getEmailPgRes() {
		return emailPgRes;
	}

	public void setEmailPgRes(String emailPgRes) {
		this.emailPgRes = emailPgRes;
	}

	@Column(name = "udf3")
	public String getMobileNumberPgRes() {
		return mobileNumberPgRes;
	}

	public void setMobileNumberPgRes(String mobileNumberPgRes) {
		this.mobileNumberPgRes = mobileNumberPgRes;
	}

	@Column(name = "date_p")
	public String getDatePgRes() {
		return datePgRes;
	}

	public void setDatePgRes(String datePgRes) {
		this.datePgRes = datePgRes;
	}

	@Transient
	public Float getSurChargePgRes() {
		return surChargePgRes;
	}

	public void setSurChargePgRes(Float surChargePgRes) {
		this.surChargePgRes = surChargePgRes;
	}

	@Column(name = "discr1")
	public String getDiscrPgRes() {
		return discrPgRes;
	}

	public void setDiscrPgRes(String discrPgRes) {
		this.discrPgRes = discrPgRes;
	}

	@Column(name = "card_number")
	public String getCardNumberPgRes() {
		return cardNumberPgRes;
	}

	public void setCardNumberPgRes(String cardNumberPgRes) {
		this.cardNumberPgRes = cardNumberPgRes;
	}

	@Column(name = "mer_txn")
	public String getMerTxnPgRes() {
		return merTxnPgRes;
	}

	public void setMerTxnPgRes(String merTxnPgRes) {
		this.merTxnPgRes = merTxnPgRes;
	}

	@Column(name = "mmp_txn")
	public String getMmpTxnPgRes() {
		return mmpTxnPgRes;
	}

	public void setMmpTxnPgRes(String mmpTxnPgRes) {
		this.mmpTxnPgRes = mmpTxnPgRes;
	}

	@Column(name = "prod")
	public String getProdPgRes() {
		return prodPgRes;
	}

	public void setProdPgRes(String prodPgRes) {
		this.prodPgRes = prodPgRes;
	}

	@Column(name = "bnk_txn")
	public String getBnkTxnPgRes() {
		return bnkTxnPgRes;
	}

	public void setBnkTxnPgRes(String bnkTxnPgRes) {
		this.bnkTxnPgRes = bnkTxnPgRes;
	}

	@Column(name = "auth_code")
	public String getAuthCodePgRes() {
		return authCodePgRes;
	}

	public void setAuthCodePgRes(String authCodePgRes) {
		this.authCodePgRes = authCodePgRes;
	}

	@Column(name = "f_code")
	public String getfCodePgRes() {
		return fCodePgRes;
	}

	public void setfCodePgRes(String fCodePgRes) {
		this.fCodePgRes = fCodePgRes;
	}

	@Column(name = "client_code")
	public String getClientCodePgRes() {
		return clientCodePgRes;
	}

	public void setClientCodePgRes(String clientCodePgRes) {
		this.clientCodePgRes = clientCodePgRes;
	}

	@Column(name = "bnk_name")
	public String getBankNamePgRes() {
		return bankNamePgRes;
	}

	public void setBankNamePgRes(String bankNamePgRes) {
		this.bankNamePgRes = bankNamePgRes;
	}

	@Column(name = "ipg_txn_id")
	public String getIpgTxnIdPgRes() {
		return ipgTxnIdPgRes;
	}

	public void setIpgTxnIdPgRes(String ipgTxnIdPgRes) {
		this.ipgTxnIdPgRes = ipgTxnIdPgRes;
	}

	@Column(name = "mer_id")
	public String getMerIdPgRes() {
		return merIdPgRes;
	}

	public void setMerIdPgRes(String merIdPgRes) {
		this.merIdPgRes = merIdPgRes;
	}

	@Column(name = "desc1")
	public String getDescPgRes() {
		return descPgRes;
	}

	public void setDescPgRes(String descPgRes) {
		this.descPgRes = descPgRes;
	}

	@Transient
	public String getUdf9PgRes() {
		return udf9PgRes;
	}

	public void setUdf9PgRes(String udf9PgRes) {
		this.udf9PgRes = udf9PgRes;
	}

	@Transient
	public String getUdf5PgRes() {
		return udf5PgRes;
	}

	public void setUdf5PgRes(String udf5PgRes) {
		this.udf5PgRes = udf5PgRes;
	}

	@Transient
	public String getUdf6PgRes() {
		return udf6PgRes;
	}

	public void setUdf6PgRes(String udf6PgRes) {
		this.udf6PgRes = udf6PgRes;
	}

	@Column(name = "res_sig")
	public String getResSignPgRes() {
		return resSignPgRes;
	}

	public void setResSignPgRes(String resSignPgRes) {
		this.resSignPgRes = resSignPgRes;
	}

	@Transient
	public String getCreatedUserNamePgRes() {
		return createdUserNamePgRes;
	}

	public void setCreatedUserNamePgRes(String createdUserNamePgRes) {
		this.createdUserNamePgRes = createdUserNamePgRes;
	}

	@Transient
	public String getUpdatedUserNamePgRes() {
		return updatedUserNamePgRes;
	}

	public void setUpdatedUserNamePgRes(String updatedUserNamePgRes) {
		this.updatedUserNamePgRes = updatedUserNamePgRes;
	}

	@Column(name = "udf4")
	public String getAddrPgRes() {
		return addrPgRes;
	}

	public void setAddrPgRes(String addrPgRes) {
		this.addrPgRes = addrPgRes;
	}

	@Column(name = "amt")
	public String getAmtPgRes() {
		return amtPgRes;
	}

	public void setAmtPgRes(String amtPgRes) {
		this.amtPgRes = amtPgRes;
	}

	@Column(name = "sur_charge_in")
	public Float getSurChargeInvoice() {
		return surChargeInvoice;
	}

	public void setSurChargeInvoice(Float surChargeInvoice) {
		this.surChargeInvoice = surChargeInvoice;
	}

	@Column(name = "invoice_id")
	public Long getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(Long invoiceId) {
		this.invoiceId = invoiceId;
	}

	@Column(name = "expiry_time")
	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	@Column(name = "service_tax")
	public Float getServiceTax() {
		return serviceTax;
	}

	public void setServiceTax(Float serviceTax) {
		this.serviceTax = serviceTax;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "payment_mode")
	public PaymentMode getPaymentModePgRes() {
		return paymentModePgRes;
	}

	public void setPaymentModePgRes(PaymentMode paymentModePgRes) {
		this.paymentModePgRes = paymentModePgRes;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "payment_status")
	public PaymentStatus getPaymentStatusPgRes() {
		return paymentStatusPgRes;
	}

	public void setPaymentStatusPgRes(PaymentStatus paymentStatusPgRes) {
		this.paymentStatusPgRes = paymentStatusPgRes;
	}

	@Column(name = "referred_by")
	public String getReferedBy() {
		return referedBy;
	}

	public void setReferedBy(String referedBy) {
		this.referedBy = referedBy;
	}

	@Column(name = "deposit_price_in")
	public Float getDepositPrice() {
		return depositPrice;
	}

	public void setDepositPrice(Float depositPrice) {
		this.depositPrice = depositPrice;
	}

}