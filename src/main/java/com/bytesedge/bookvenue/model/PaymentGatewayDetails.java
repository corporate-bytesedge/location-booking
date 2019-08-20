package com.bytesedge.bookvenue.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author divya
 */
/*
 * It stores the payment gateway details
 */
@Entity
@Table(name = "lb_payment_gateway_details")
@DiscriminatorColumn(name = "class_code", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "payment_gateway_details")
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class PaymentGatewayDetails extends OrgDo implements Auditable, Indexable {
	/*
	 * Login ID obtained on registration of Merchant URL's and IP
	 */
	private String login;
	/*
	 * Password obtained on registration of Merchant URL's and IP
	 */
	private String password;
	/*
	 * reqHashKey is a request key
	 */
	private String reqHashKey;
	/*
	 * resHashKey is a response key
	 */
	private String resHashKey;
	/*
	 * txnCurr is a currency
	 */
	private String txnCurr;
	/*
	 * status shows the login is Active or Inactive
	 */
	private StateType status = StateType.Active;
	/*
	 * NB Fund Transfer in this parameter
	 */
	private String tType;
	/*
	 * Product ID as decided and approved by the Merchant
	 */
	private String prodId;
	/*
	 * merchantUrl is a required url generating by signature using the above
	 * fields
	 */
	private String merchantUrl;
	/*
	 * ClientCode
	 */
	private String clientCode;
	private String createdUserName;
	private String updatedUserName;
	private String orgName;

	@Column(name = "mer_url")
	public String getMerchantUrl() {
		return merchantUrl;
	}

	public void setMerchantUrl(String merchantUrl) {
		this.merchantUrl = merchantUrl;
	}

	@Column(name = "login")
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Column(name = "password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "req_hash_key")
	public String getReqHashKey() {
		return reqHashKey;
	}

	public void setReqHashKey(String reqHashKey) {
		this.reqHashKey = reqHashKey;
	}

	@Column(name = "res_hash_key")
	public String getResHashKey() {
		return resHashKey;
	}

	public void setResHashKey(String resHashKey) {
		this.resHashKey = resHashKey;
	}

	@Column(name = "txn_curr")
	public String getTxnCurr() {
		return txnCurr;
	}

	public void setTxnCurr(String txnCurr) {
		this.txnCurr = txnCurr;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	public StateType getStatus() {
		return status;
	}

	public void setStatus(StateType status) {
		this.status = status;
	}

	@Column(name = "ttype")
	public String getTtype() {
		return tType;
	}

	public void setTtype(String tType) {
		this.tType = tType;
	}

	@Column(name = "prodid")
	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	@Transient
	public String getClientCode() {
		return clientCode;
	}

	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
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
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
}