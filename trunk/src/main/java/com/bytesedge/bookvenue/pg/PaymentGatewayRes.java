package com.bytesedge.bookvenue.pg;

public class PaymentGatewayRes {
	String paymentUrl;
	String reqSign;
	public String getPaymentUrl() {
		return paymentUrl;
	}
	public void setPaymentUrl(String paymentUrl) {
		this.paymentUrl = paymentUrl;
	}
	public String getReqSign() {
		return reqSign;
	}
	public void setReqSign(String reqSign) {
		this.reqSign = reqSign;
	}
	
	
}