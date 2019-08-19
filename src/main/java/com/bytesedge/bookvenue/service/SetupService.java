package com.bytesedge.bookvenue.service;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.bytesedge.bookvenue.common.PaginatedRequest;
import com.bytesedge.bookvenue.common.PaginatedResponse;
import com.bytesedge.bookvenue.model.AuditData;
import com.bytesedge.bookvenue.model.Bill;
import com.bytesedge.bookvenue.model.Context;
import com.bytesedge.bookvenue.model.EndUser;
import com.bytesedge.bookvenue.model.ForgotPasswd;
import com.bytesedge.bookvenue.model.Invoice;
import com.bytesedge.bookvenue.model.LoginHistory;
import com.bytesedge.bookvenue.model.Marquee;
import com.bytesedge.bookvenue.model.MarqueeType;
import com.bytesedge.bookvenue.model.Organization;
import com.bytesedge.bookvenue.model.PaymentGatewayDetails;
import com.bytesedge.bookvenue.model.Property;
import com.bytesedge.bookvenue.model.PropertyImages;
import com.bytesedge.bookvenue.model.PropertyRentPrice;
import com.bytesedge.bookvenue.model.RentPurpose;
import com.bytesedge.bookvenue.model.SlotType;
import com.bytesedge.bookvenue.model.SmsGatewayDetails;
import com.bytesedge.bookvenue.model.TotalStatus;

public interface SetupService extends BaseService {
	public Context getContext(String url) throws Exception;
	public List<AuditData> getAuditDataSet(Long ctxId, int pageSize) throws Exception;
	
	public PaginatedResponse getLoginHistoryList(Long ctxId, PaginatedRequest req) throws Exception;
	public List<LoginHistory> getLoginHistorySet(Long ctxId, int pageSize) throws Exception;
	
	public PaginatedResponse getAuditDataList(Long ctxId, PaginatedRequest req) throws Exception;
	public AuditData getAuditDataByIdAndCtxId(Long id, Long ctxtId)throws Exception;
	
	public List<Organization> getOrgByCtxId(Long ctxId) throws Exception;
	
	public Property getPropertyByCtxIdAndId(Long ctxId, Long id)throws Exception;
	public Property saveOrUpdateProperty(Property property)throws Exception;
	public PaginatedResponse getPropertyList(Long ctxId, PaginatedRequest req)throws Exception;
	
	public PaginatedResponse getPropertyImagesList(Long ctxId, PaginatedRequest req) throws Exception;
	public PropertyImages saveOrUpdatePropertyImages(PropertyImages propertyImagesForm) throws Exception;
	public PropertyImages getPropertyImagesByIdAndCtxId(Long id, Long ctxId) throws Exception;
	public PaginatedResponse getManagementBookingList(Long ctxId, PaginatedRequest req) throws Exception;
	public PaginatedResponse getBookingRegistrationList(Long ctxId, PaginatedRequest req) throws Exception;
	
	public PaginatedResponse getEndUserOtpList(Long ctxId, PaginatedRequest req)throws Exception;
	
	public PaginatedResponse getRentPurposeList(Long ctxId, PaginatedRequest req)throws Exception;
	public RentPurpose getRentPurposeByCtxIdAndId(Long ctxId, Long id)throws Exception;
	public RentPurpose saveOrUpdateRentPurpose(RentPurpose rentPurposeForm)throws Exception;
	
	public PaginatedResponse getPropertyRentPriceList(Long ctxId, PaginatedRequest req)throws Exception;
	public PropertyRentPrice getPropertyRentPriceByCtxIdAndId(Long ctxId, Long id)throws Exception;
	public PropertyRentPrice saveOrUpdatePropertyRentPrice(PropertyRentPrice propertyRentPriceForm)throws Exception;
	
	public PaginatedResponse getInvoiceList(Long ctxId, PaginatedRequest req)throws Exception;
	public Invoice getInvoiceByCtxIdAndId(Long ctxId, Long id)throws Exception;
	
	public EndUser getBillByCtxIdAndId(Long ctxId, Long id)throws Exception;
	public Bill saveOrUpdateBill(Bill billForm)throws Exception;
	
	public PaginatedResponse getAddBookingList(Long ctxId, PaginatedRequest req)throws Exception;
	public PaginatedResponse getEndUserList(Long ctxId, PaginatedRequest req)throws Exception;
	public EndUser saveOrUpdateEndUser(EndUser endUserForm)throws Exception;
	public List<Property> getPropertyListByCtxId(Long ctxId)throws Exception;
	public List<RentPurpose> getRentPurposeListByCtxId(Long ctxId)throws Exception;
	public EndUser getEndUserByCtxIdAndId(Long ctxId, Long id)throws Exception;
	public EndUser getEndUserByCtxIdAndOrgIdAndUserName(Long ctxId, Long orgId, String username)throws Exception;
	public void deleteEndUser(EndUser enduser)throws Exception;
	public Property getPropertyByName(Long ctxId, String name) throws Exception;
	public PropertyRentPrice getPropertyRentPriceByCtxIdAndOrgIdAndPropertyIdAndPurposeIdAndSlotType(Long ctxId, Long orgId, Long propertyId, Long purposeId, SlotType slotType)throws Exception;
	public List<Invoice> getInvoiceListByCtxId(Long ctxId)throws Exception;
	public PaginatedResponse getPaymentGatewayDetailsList(Long ctxId, PaginatedRequest req)throws Exception;
	public PaymentGatewayDetails getPaymentGatewayDetailsByCtxIdAndId(Long ctxId, Long id)throws Exception;
	public PaymentGatewayDetails saveOrUpdatePaymentGatewayDetails(PaymentGatewayDetails paymentGatewayDetailsForm)throws Exception;
	public PaginatedResponse getTotalStatusList(Long ctxId, PaginatedRequest req) throws Exception;
	public TotalStatus getTotalStatusByCtxIdAndId( Long ctxId, Long id) throws Exception;
	public Long getApplicationDocMaxId() throws Exception;
	public PaymentGatewayDetails getPaymentGatewayDetailsByCtxIdAndOrgId(Long ctxId, long orgId)throws Exception;
	public String getEndUserByCtxIdAndName(Long ctxId, String name) throws Exception;
	public PaginatedResponse getSmsGatewayDetailsList(Long ctxId, PaginatedRequest req)throws Exception;
	public SmsGatewayDetails getSmsGatewayDetailsByCtxIdAndId(Long ctxId, Long id)throws Exception;
	public SmsGatewayDetails saveOrUpdateSmsGatewayDetails(SmsGatewayDetails smsGatewayDetailsForm)throws Exception;
	public PaginatedResponse getBillList(Long ctxId, PaginatedRequest req)throws Exception;
	public EndUser getEndUserByCtxIdAndIdAndDate(Long ctxId, long id, Date bookDate)throws Exception;
	public PaymentGatewayDetails getPaymentGatewayDetailsByClientCode(String clientcode)throws Exception;
	public EndUser getEndUserByUsername(String clientcode)throws Exception;
	public Invoice saveOrUpdateInvoice(Invoice invoice)throws Exception;
	public Invoice getInvoiceByCtxIdAndClientCode(Long ctxId, String clientcode)throws Exception;
	public PaginatedResponse getPastBillList(Long ctxId, PaginatedRequest req)throws Exception;
	public PaginatedResponse getTodayBillList(Long ctxId, PaginatedRequest req)throws Exception;
	public PaginatedResponse getFutureBillList(Long ctxId, PaginatedRequest req)throws Exception;
	public PropertyRentPrice getPropertyRentPriceByCtxIdAndOrgIdAndPropertyIdAndPurposeId(Long ctxId, Long orgId, Long propertyId, Long purposeId)throws Exception;
	public PaginatedResponse getSuccessInvoiceList(Long ctxId, PaginatedRequest req)throws Exception;
	public PaginatedResponse getPendingInvoiceList(Long ctxId, PaginatedRequest req)throws Exception;
	public PaginatedResponse getFailInvoiceList(Long ctxId, PaginatedRequest req)throws Exception;
	public Long getPastBillCount(Long ctxId, Long orgId)throws Exception;
	public Long getTodayBillCount(Long ctxId, Long orgId)throws Exception;
	public Long getFutureBillCount(Long ctxId, Long orgId)throws Exception;
	public List<EndUser> getBlockedInvoiceByCtxIdAndOrgId(Long ctxId, Long orgId)throws Exception;
	public List<EndUser> getBlockedBillByCtxIdAndOrgId(Long ctxId, Long orgId)throws Exception;
	public Marquee saveOrUpdateMarquee(Marquee marquee)throws Exception;
	public PaginatedResponse getMarqueeList(Long ctxId, PaginatedRequest req)throws Exception;
	public Marquee getMarqueeByCtxIdAndOrgIdAndId(Long ctxId, Long orgId, long id)throws Exception;
	public Marquee getLatestMarqueeByCtxIdAndOrgIdAndType(Long ctxId, Long orgId, MarqueeType type)throws Exception;
	public EndUser getAddBookingByCtxIdAndOrgIdAndId(Long ctxId, long orgId, Long id)throws Exception;
	public EndUser saveOrUpdateAddBooking(EndUser addBookingForm)throws Exception;
	public Invoice getInvoiceByCtxIdAndOrgIdAndClientCode(Long ctxId, Long orgId, String username)throws Exception;
	public EndUser getAddBookingByCtxIdAndOrgIdAndUsername(Long ctxId, Long orgId, String username)throws Exception;
	public ForgotPasswd saveOrUpdateForgotPasswd(@Valid ForgotPasswd forgotPasswdForm) throws Exception;
	public ForgotPasswd getForgotPasswdByEmail(String email) throws Exception;
	public List<EndUser> getBlockedBillByCtxIdAndOrgIdAndPropertyId(Long ctxId, Long orgId, Long venueId)throws Exception;
	public EndUser getEndUserByCtxIdAndOrgIdAndApplicationId(Long ctxId, Long orgId, String applicationId)throws Exception;
	public List<EndUser> getEndUserListByCtxId(Long ctxId)throws Exception;
	public PaginatedResponse getEndUserListByCtxIdAndFromDateAndToDateAndVenue(Long ctxId, PaginatedRequest req, String fromDate, String toDate,
			Long venue)throws Exception;
	public List<EndUser> getEndUserPaidList()throws Exception;
	public List<EndUser> getEndUserComingBookingList()throws Exception;
	public List<EndUser> getEndUserCompletedBookingList()throws Exception;
	public List<EndUser> getEndUserTodayBookingList()throws Exception;
	public List<EndUser> getInvoicePaidList()throws Exception;
	public List<EndUser> getInvoicePendingList()throws Exception;
	public List<EndUser> getInvoiceFailedList() throws Exception;
	public List<EndUser> getEndUserCancelledList()throws Exception;
}