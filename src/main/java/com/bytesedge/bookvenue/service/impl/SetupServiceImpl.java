package com.bytesedge.bookvenue.service.impl;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bytesedge.bookvenue.common.PaginatedRequest;
import com.bytesedge.bookvenue.common.PaginatedResponse;
import com.bytesedge.bookvenue.dao.SetupDao;
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
import com.bytesedge.bookvenue.service.SetupService;

@Component
@Service("setupService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = Throwable.class)
public class SetupServiceImpl extends BaseServiceImpl implements SetupService {

	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(SetupServiceImpl.class);

	@Autowired
	private SetupDao setupDao;

	public SetupDao getSetupDao() {
		return setupDao;
	}

	public void setSetupDao(SetupDao setupDao) {
		this.setupDao = setupDao;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = Throwable.class)
	public Context getContext(String url) throws Exception {
		return this.setupDao.getContext(url);
	}

	@Override
	public PaginatedResponse getLoginHistoryList(Long ctxId, PaginatedRequest req) throws Exception {
		return this.setupDao.getLoginHistoryList(ctxId, req);
	}

	@Override
	public List<LoginHistory> getLoginHistorySet(Long ctxId, int pageSize) throws Exception {
		return this.setupDao.getLoginHistorySet(ctxId, pageSize);
	}

	@Override
	public PaginatedResponse getAuditDataList(Long ctxId, PaginatedRequest req) throws Exception {
		return this.setupDao.getAuditDataList(ctxId, req);
	}

	@Override
	public AuditData getAuditDataByIdAndCtxId(Long id, Long contextId) throws Exception {
		return this.setupDao.getAuditDataByIdAndCtxId(id, contextId);
	}

	@Override
	public List<AuditData> getAuditDataSet(Long ctxId, int pageSize) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Organization> getOrgByCtxId(Long ctxId) throws Exception {
		return this.setupDao.getOrgByCtxId(ctxId);
	}

	@Override
	public Property getPropertyByCtxIdAndId(Long ctxId, Long id) throws Exception {
		return this.setupDao.getPropertyByCtxIdAndId(ctxId, id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Throwable.class)
	public Property saveOrUpdateProperty(Property property) throws Exception {
		return (Property) setupDao.saveOrUpdate(property);
	}


	@Override
	public PaginatedResponse getPropertyList(Long ctxId, PaginatedRequest req) throws Exception {
		return this.setupDao.getPropertyList(ctxId, req);
	}

	@Override
	public PaginatedResponse getPropertyImagesList(Long ctxId, PaginatedRequest req) throws Exception {
		return this.setupDao.getPropertyImagesList(ctxId, req);
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Throwable.class)
	public PropertyImages saveOrUpdatePropertyImages(PropertyImages propertyImagesForm) throws Exception {
		return (PropertyImages) this.setupDao.saveOrUpdate(propertyImagesForm);
	}

	@Override
	public PropertyImages getPropertyImagesByIdAndCtxId(Long id, Long ctxId) throws Exception {
		return this.setupDao.getPropertyImagesByIdAndCtxId(id, ctxId);
	}

	@Override
	public PaginatedResponse getManagementBookingList(Long ctxId, PaginatedRequest req) throws Exception {
		return this.setupDao.getManagementBookingList(ctxId, req);
	}

	@Override
	public PaginatedResponse getBookingRegistrationList(Long ctxId, PaginatedRequest req) throws Exception {
		return this.setupDao.getBookingRegistrationList(ctxId, req);
	}

	@Override
	public PaginatedResponse getEndUserOtpList(Long ctxId, PaginatedRequest req) throws Exception {
		return this.setupDao.getEndUserOtpList(ctxId, req);
	}

	@Override
	public PaginatedResponse getRentPurposeList(Long ctxId, PaginatedRequest req) throws Exception {
		return this.setupDao.getRentPurposeList(ctxId, req);
	}

	@Override
	public RentPurpose getRentPurposeByCtxIdAndId(Long ctxId, Long id) throws Exception {
		return this.setupDao.getRentPurposeByCtxIdAndId(ctxId, id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Throwable.class)
	public RentPurpose saveOrUpdateRentPurpose(RentPurpose rentPurposeForm) throws Exception {
		return (RentPurpose) this.setupDao.saveOrUpdate(rentPurposeForm);
	}

	@Override
	public PaginatedResponse getPropertyRentPriceList(Long ctxId, PaginatedRequest req) throws Exception {
		return this.setupDao.getPropertyRentPriceList(ctxId, req);
	}

	@Override
	public PropertyRentPrice getPropertyRentPriceByCtxIdAndId(Long ctxId, Long id) throws Exception {
		return this.setupDao.getPropertyRentPriceByCtxIdAndId(ctxId, id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Throwable.class)
	public PropertyRentPrice saveOrUpdatePropertyRentPrice(PropertyRentPrice propertyRentPriceForm) throws Exception {
		return (PropertyRentPrice) this.setupDao.saveOrUpdate(propertyRentPriceForm);
	}

	@Override
	public PaginatedResponse getInvoiceList(Long ctxId, PaginatedRequest req) throws Exception {
		return this.setupDao.getInvoiceList(ctxId, req);
	}

	@Override
	public Invoice getInvoiceByCtxIdAndId(Long ctxId, Long id) throws Exception {
		return this.setupDao.getInvoiceByCtxIdAndId(ctxId, id);
	}
	
	@Override
	public EndUser getBillByCtxIdAndId(Long ctxId, Long id) throws Exception {
		return this.setupDao.getBillByCtxIdAndId(ctxId, id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Throwable.class)
	public Bill saveOrUpdateBill(Bill billForm) throws Exception {
		return (Bill) this.setupDao.saveOrUpdate(billForm);
	}

	@Override
	public PaginatedResponse getEndUserList(Long ctxId, PaginatedRequest req) throws Exception {
		return this.setupDao.getEndUserList(ctxId, req);
	}

	//mangement booking list
	@Override
	public PaginatedResponse getAddBookingList(Long ctxId, PaginatedRequest req) throws Exception {
		return this.setupDao.getAddBookingList(ctxId, req);
	}
	//end of management booking list
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Throwable.class)
	public EndUser saveOrUpdateEndUser(EndUser endUserForm) throws Exception {
		return (EndUser) this.setupDao.saveOrUpdate(endUserForm);
	}

	@Override
	public List<Property> getPropertyListByCtxId(Long ctxId) throws Exception {
		return this.setupDao.getPropertyListByCtxId(ctxId);
	}


	@Override
	public List<RentPurpose> getRentPurposeListByCtxId(Long ctxId) throws Exception {
		return this.setupDao.getRentPurposeListByCtxId(ctxId);
	}

	@Override
	public EndUser getEndUserByCtxIdAndId(Long ctxId, Long id) throws Exception {
		return this.setupDao.getEndUserByCtxIdAndId(ctxId, id);
	}

	@Override
	public EndUser getEndUserByCtxIdAndOrgIdAndUserName(Long ctxId, Long orgId, String username) throws Exception {
		return this.setupDao.getEndUserByCtxIdAndOrgIdAndUserName(ctxId, orgId, username);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Throwable.class)
	public void deleteEndUser(EndUser enduser) throws Exception {
		this.setupDao.delete(enduser);
		
	}

	@Override
	public Property getPropertyByName(Long ctxId, String name) throws Exception {
		return this.setupDao.getPropertyByName(ctxId, name);
	}

	@Override
	public PropertyRentPrice getPropertyRentPriceByCtxIdAndOrgIdAndPropertyIdAndPurposeIdAndSlotType(Long ctxId, Long OrgId, Long propertyId, Long purposeId, SlotType slotType) throws Exception {
		return this.setupDao.getPropertyRentPriceByCtxIdAndOrgIdAndPropertyIdAndPurposeIdAndSlotType(ctxId, OrgId, propertyId, purposeId, slotType);
	}

	@Override
	public List<Invoice> getInvoiceListByCtxId(Long ctxId) throws Exception {
		return this.setupDao.getInvoiceListByCtxId(ctxId);
	}

	@Override
	public PaginatedResponse getPaymentGatewayDetailsList(Long ctxId, PaginatedRequest req) throws Exception {
		return this.setupDao.getPaymentGatewayDetailsList(ctxId, req);
	}

	@Override
	public PaymentGatewayDetails getPaymentGatewayDetailsByCtxIdAndId(Long ctxId, Long id) throws Exception {
		return this.setupDao.getPaymentGatewayDetailsByCtxIdAndId(ctxId, id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Throwable.class)
	public PaymentGatewayDetails saveOrUpdatePaymentGatewayDetails(PaymentGatewayDetails paymentGatewayDetailsForm)	throws Exception {
		return (PaymentGatewayDetails) this.setupDao.saveOrUpdate(paymentGatewayDetailsForm);
	}

	@Override
	public PaginatedResponse getTotalStatusList(Long ctxId, PaginatedRequest req) throws Exception {
		return this.setupDao.getTotalStatusList(ctxId, req);
	}

	@Override
	public TotalStatus getTotalStatusByCtxIdAndId(Long ctxId, Long id) throws Exception {
		return this.setupDao.getTotalStatusByCtxIdAndId(ctxId, id);
	}

	@Override
	public Long getApplicationDocMaxId() throws Exception {
		return this.setupDao.getApplicationDocMaxId();
	}
	
	@Override
	public PaymentGatewayDetails getPaymentGatewayDetailsByCtxIdAndOrgId(Long ctxId, long orgId) throws Exception {
		return this.setupDao.getPaymentGatewayDetailsByCtxIdAndOrgId(ctxId, orgId);
	}

	@Override
	public String getEndUserByCtxIdAndName(Long ctxId, String name) throws Exception {
		return this.setupDao.getEndUserByCtxIdAndName(ctxId, name);
	}

	@Override
	public PaginatedResponse getSmsGatewayDetailsList(Long ctxId, PaginatedRequest req) throws Exception {
		return this.setupDao.getSmsGatewayDetailsList(ctxId, req);
	}

	@Override
	public SmsGatewayDetails getSmsGatewayDetailsByCtxIdAndId(Long ctxId, Long id) throws Exception {
		return this.setupDao.getSmsGatewayDetailsByCtxIdAndId(ctxId, id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Throwable.class)
	public SmsGatewayDetails saveOrUpdateSmsGatewayDetails(SmsGatewayDetails smsGatewayDetailsForm) throws Exception {
		return (SmsGatewayDetails) this.setupDao.saveOrUpdate(smsGatewayDetailsForm);
	}

	@Override
	public PaginatedResponse getBillList(Long ctxId, PaginatedRequest req) throws Exception {
		return this.setupDao.getBillList(ctxId, req);
	}

	@Override
	public EndUser getEndUserByCtxIdAndIdAndDate(Long ctxId, long id, Date bookDate) throws Exception {
		return this.setupDao.getEndUserByCtxIdAndIdAndDate(ctxId, id, bookDate);
	}

	@Override
	public PaymentGatewayDetails getPaymentGatewayDetailsByClientCode(String clientcode) throws Exception {
		return this.setupDao.getPaymentGatewayDetailsByClientCode(clientcode);
	}

	@Override
	public EndUser getEndUserByUsername(String clientcode) throws Exception {
		return this.setupDao.getEndUserByUsername(clientcode);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Throwable.class)
	public Invoice saveOrUpdateInvoice(Invoice invoice) throws Exception {
		return (Invoice) this.setupDao.saveOrUpdate(invoice);
	}

	@Override
	public Invoice getInvoiceByCtxIdAndClientCode(Long ctxId, String clientcode) throws Exception {
		return this.setupDao.getInvoiceByCtxIdAndClientCode(ctxId, clientcode);
	}

	@Override
	public PaginatedResponse getPastBillList(Long ctxId, PaginatedRequest req) throws Exception {
		return this.setupDao.getPastBillList(ctxId, req);
	}

	@Override
	public PaginatedResponse getTodayBillList(Long ctxId, PaginatedRequest req) throws Exception {
		return this.setupDao.getTodayBillList(ctxId, req);
	}


	@Override
	public PaginatedResponse getFutureBillList(Long ctxId, PaginatedRequest req) throws Exception {
		return this.setupDao.getFutureBillList(ctxId, req);
	}

	@Override
	public PropertyRentPrice getPropertyRentPriceByCtxIdAndOrgIdAndPropertyIdAndPurposeId(Long ctxId, Long orgId, Long propertyId, Long purposeId)
			throws Exception {
		return this.setupDao.getPropertyRentPriceByCtxIdAndOrgIdAndPropertyIdAndPurposeId(ctxId, orgId, propertyId, purposeId);
	}

	@Override
	public PaginatedResponse getSuccessInvoiceList(Long ctxId, PaginatedRequest req) throws Exception {
		return this.setupDao.getSuccessInvoiceList(ctxId, req);
	}

	@Override
	public PaginatedResponse getPendingInvoiceList(Long ctxId, PaginatedRequest req) throws Exception {
		return this.setupDao.getPendingInvoiceList(ctxId, req);
	}
	
	@Override
	public PaginatedResponse getFailInvoiceList(Long ctxId, PaginatedRequest req) throws Exception {
		return this.setupDao.getFailInvoiceList(ctxId, req);
	}

	@Override
	public Long getPastBillCount(Long ctxId, Long long1) throws Exception {
		return this.setupDao.getPastBillCount(ctxId, long1);
	}

	@Override
	public Long getTodayBillCount(Long ctxId, Long long1) throws Exception {
		return this.setupDao.getTodayBillCount(ctxId, long1);
	}

	@Override
	public Long getFutureBillCount(Long ctxId, Long orgId) throws Exception {
		return this.setupDao.getFutureBillCount(ctxId, orgId);
	}

	@Override
	public List<EndUser> getBlockedInvoiceByCtxIdAndOrgId(Long ctxId, Long orgId) throws Exception {
		return this.setupDao.getBlockedInvoiceByCtxIdAndOrgId(ctxId, orgId);
	}

	@Override
	public List<EndUser> getBlockedBillByCtxIdAndOrgId(Long ctxId, Long orgId) throws Exception {
		return this.setupDao.getBlockedBillByCtxIdAndOrgId(ctxId, orgId);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Throwable.class)
	public Marquee saveOrUpdateMarquee(Marquee marquee) throws Exception {
		return (Marquee) this.setupDao.saveOrUpdate(marquee);
	}

	@Override
	public PaginatedResponse getMarqueeList(Long ctxId, PaginatedRequest req) throws Exception {
		return this.setupDao.getMarqueeList(ctxId, req);
	}
	
	@Override
	public Marquee getMarqueeByCtxIdAndOrgIdAndId(Long ctxId, Long orgId, long id) throws Exception {
		return this.setupDao.getMarqueeByCtxIdAndOrgIdAndId(ctxId, orgId, id);
	}

	@Override
	public Marquee getLatestMarqueeByCtxIdAndOrgIdAndType(Long ctxId, Long orgId, MarqueeType type)
			throws Exception {
		return this.setupDao.getLatestMarqueeByCtxIdAndOrgIdAndType(ctxId, orgId, type);
	}

	@Override
	public EndUser getAddBookingByCtxIdAndOrgIdAndId(Long ctxId, long orgId, Long id)throws Exception {
		return this.setupDao.getAddBookingByCtxIdAndOrgIdAndId(ctxId, orgId, id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Throwable.class)
	public EndUser saveOrUpdateAddBooking(EndUser addBookingForm) throws Exception {
		return (EndUser) this.setupDao.saveOrUpdate(addBookingForm);
	}

	@Override
	public Invoice getInvoiceByCtxIdAndOrgIdAndClientCode(Long ctxId, Long orgId, String username) throws Exception {
		return this.setupDao.getInvoiceByCtxIdAndOrgIdAndClientCode(ctxId, orgId, username);
	}

	@Override
	public EndUser getAddBookingByCtxIdAndOrgIdAndUsername(Long ctxId, Long orgId, String username) throws Exception {
		return this.setupDao.getAddBookingByCtxIdAndOrgIdAndUsername(ctxId, orgId, username);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Throwable.class)
	public ForgotPasswd saveOrUpdateForgotPasswd(@Valid ForgotPasswd forgotPasswdForm) throws Exception {
		return (ForgotPasswd) this.setupDao.saveOrUpdate(forgotPasswdForm);
	}

	@Override
	public ForgotPasswd getForgotPasswdByEmail(String email) throws Exception {
		return this.setupDao.getForgotPasswdByEmail(email);

	}

	@Override
	public List<EndUser> getBlockedBillByCtxIdAndOrgIdAndPropertyId(Long ctxId, Long orgId, Long venueId)
			throws Exception {
		return this.setupDao.getBlockedBillByCtxIdAndOrgIdAndPropertyId(ctxId, orgId, venueId);
	}
	
	@Override
	public EndUser getEndUserByCtxIdAndOrgIdAndApplicationId(Long ctxId, Long orgId, String applicationId)
			throws Exception {
		return this.setupDao.getEndUserByCtxIdAndOrgIdAndApplicationId(ctxId, orgId, applicationId);
	}

	@Override
	public List<EndUser> getEndUserListByCtxId(Long ctxId) throws Exception {
		return this.setupDao.getEndUserListByCtxId(ctxId);
	}
	
	@Override
	public PaginatedResponse getEndUserListByCtxIdAndFromDateAndToDateAndVenue(Long ctxId, PaginatedRequest req, String fromDate, String toDate,
			Long venue) throws Exception {
		return this.setupDao.getEndUserListByCtxIdAndFromDateAndToDateAndVenue(ctxId, req, fromDate, toDate, venue);
	}

	@Override
	public List<EndUser> getEndUserPaidList() throws Exception {
		return this.setupDao.getEndUserPaidList();
	}

	@Override
	public List<EndUser> getEndUserComingBookingList() throws Exception {
		return this.setupDao.getEndUserComingBookingList();
	}

	@Override
	public List<EndUser> getEndUserCompletedBookingList() throws Exception {
		return this.setupDao.getEndUserCompletedBookingList();
	}

	@Override
	public List<EndUser> getEndUserTodayBookingList() throws Exception {
		return this.setupDao.getEndUserTodayBookingList();
	}
	
	@Override
	public List<EndUser> getInvoicePaidList() throws Exception {
		return this.setupDao.getInvoicePaidList();
	}

	@Override
	public List<EndUser> getInvoicePendingList() throws Exception {
		return this.setupDao.getInvoicePendingList();
	}

	@Override
	public List<EndUser> getInvoiceFailedList() throws Exception {
		return this.setupDao.getInvoiceFailedList();
	}

	@Override
	public List<EndUser> getEndUserCancelledList() throws Exception {
		return this.setupDao.getEndUserCancelledList();
	}

}