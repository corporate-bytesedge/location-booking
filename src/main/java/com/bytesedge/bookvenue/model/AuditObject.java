package com.bytesedge.bookvenue.model;

/**
 * @author divya
 */
/**
 * AuditObject is name of the applications having audit operation like create
 * and update only
 **/
public enum AuditObject {

	None, Organization, User, UserRole, Property, PropertyImages, EndUserOtp, RentPurpose, PropertyRentPrice, Bill, EndUser, PaymentGatewayDetails, SmsGatewayDetails, Marquee, AddBooking,ForgotPasswd;

}
