<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>

<style>
.text-centrCls h3 {
	color: initial;
	font-size: 18px;
	font-weight: 550;
	font-size: 25px;
	text-align: center;
}
.form-top{
	margin-right: 30px;
	float: right;
}
</style>
<div class="center-content">
	<div class="example-box-wrapper">
		<div id="viewAddBookingDiv" class="example-box-wrapper"
			style="margin-top: 30px;">
			<div class="text-centrCls">
				<h3>
					<spring:message code="viewAddBooking-form.viewAddBooking-label" />
				</h3>
			</div>
			<div id="addBookingButtonsDiv" class="form-top">
					<div class="col-sm-offset-2 col-sm-8">
						<div class="example-box-wrapper">
							<button id="addBookingViewBackBt" name="addBookingViewBackBt"
								type="button" class="btn btn-info"
								onClick="javascript:loadContent('/app/addBooking/list?un=${addBookingForm.userName}', 'page-content-holder')">
								<i class="glyph-icon icon-minus-square"></i>&nbsp;
								<spring:message code="viewAddBooking-form.back-button" />
							</button>
						</div>
					</div>
				</div>
			<br> <br>
			<form:form id="viewAddBookingForm" name="viewAddBookingForm"
				class="form-horizontal" data-parsley-validate=""
				action="javascript:submitForm('/app/addBooking/view', 'viewAddBookingForm', 'page-content-holder')"
				method='POST' modelAttribute="addBookingForm">
				<input type="hidden" path="<c:out value="${_csrf.parameterName}"/>"
					name="<c:out value="${_csrf.parameterName}"/>"
					value="<c:out value="${_csrf.token}"/>" />
				<div id="viewAddBookingApplicationIdDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewAddBooking-form.applicationId-label" /> <span
						class="required">:</span>
					</label>
					<div id="viewAddBookingApplicationIdDiv" class="col-sm-8 paddingt8">${ addBookingForm.applicationId}</div>
				</div>
				<div id="viewAddBookingNameDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewAddBooking-form.Name-label" /> <span class="required">:</span>
					</label>
					<div id="viewAddBookingNameDiv" class="col-sm-8 paddingt8">${ addBookingForm.name}</div>
				</div>
				<div id="viewAddBookingEmailDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewAddBooking-form.email-label" /> <span class="required">:</span>
					</label>
					<div id="viewAddBookingEmailDiv" class="col-sm-8 paddingt8">
						${addBookingForm.email}</div>
				</div>
				<div id="viewAddBookingTypeDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewAddBooking-form.type-label" />:<span class="required"></span>
					</label>
					<div id="viewAddBookingTypeDiv" class="col-sm-8 paddingt8">${ addBookingForm.idProofType}</div>
				</div>

				<div id="viewAddBookingIdProofDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewAddBooking-form.idProof-label" /> <span class="required">:</span>
					</label>
					<div id="viewAddBookingIdProofDiv" class="col-sm-8 paddingt8">
						${addBookingForm.idProof}</div>
				</div>
				<div id="viewAddBookingMobileNumberDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewAddBooking-form.mobileNumber-label" /> <span
						class="required">:</span>
					</label>
					<div id="viewAddBookingMobileNumberDiv" class="col-sm-8 paddingt8">
						${addBookingForm.mobileNumber}</div>
				</div>
				<div id="viewAddBookingDateDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewAddBooking-form.date-label" /> <span class="required">:</span>
					</label>
					<div id="viewAddBookingDateDiv" class="col-sm-8 paddingt8">
						<fmt:formatDate value="${addBookingForm.bookingDate}" pattern="dd-MMM-yyyy" />
					</div>
				</div>
				<div id="viewAddBookingPropertyNameDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewAddBooking-form.propertyName-label" /> <span
						class="required">:</span>
					</label>
					<div id="viewAddBookingPropertyNameDiv" class="col-sm-8 paddingt8">
						${addBookingForm.propertyName}</div>
				</div>
				<div id="viewAddBookingPurposeNameDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewAddBooking-form.purpose-label" /> <span class="required">:</span>
					</label>
					<div id="viewAddBookingPropertyNameDiv" class="col-sm-8 paddingt8">
						${addBookingForm.purposeName}</div>
				</div>
				<div id="viewAddBookingPaymentModeDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewAddBooking-form.PaymentMode-label" /> <span class="required">:</span>
					</label>
					<div id="viewAddBookingPaymentModeDiv" class="col-sm-8 paddingt8">${addBookingForm.paymentMode}</div>
				</div>
				<div id="viewAddBookingAddressDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewAddBooking-form.address-label" /> <span class="required">:</span>
					</label>
					<div id="viewAddBookingAddressDiv" class="col-sm-8 paddingt8">
						${addBookingForm.addr}</div>
				</div>
				<div id="viewAddBookingReferbyDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewAddBooking-form.referby-label" /> <span class="required">:</span>
					</label>
					<div id="viewAddBookingTotalDiv" class="col-sm-8 paddingt8">
						${addBookingForm.referredBy}</div>
				</div>
				<div class="divider"></div>
				<div id="addBookingButtonsDiv" class="form-group">
					<div class="col-sm-offset-2 col-sm-8">
						<div class="example-box-wrapper">
							<button id="addBookingViewBackBt" name="addBookingViewBackBt"
								type="button" class="btn btn-info"
								onClick="javascript:loadContent('/app/addBooking/list?un=${addBookingForm.userName}', 'page-content-holder')">
								<i class="glyph-icon icon-minus-square"></i>&nbsp;
								<spring:message code="viewAddBooking-form.back-button" />
							</button>
						</div>
					</div>
				</div>
			</form:form>
		</div>
	</div>
</div>
