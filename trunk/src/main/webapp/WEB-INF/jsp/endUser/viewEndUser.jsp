<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>

<style>
.text-centrCls h3 {
	font-weight: 550;
    font-size: 25px;
    padding-left: 180px;
    margin-bottom: 5px;
}

@media screen and (max-width: 1050px){
	.margn-btm {
		margin-bottom: 0px;
	}
}

@media screen and (max-width: 992px){
	.text-centrCls h3 {
		padding-left: 20px;
	}
}
.float-logo {
	float: left;
	margin-left: 30px;
}

.float-btn {
	float: right;
	margin-right:30px;
}
</style>

<div class="center-content">
	<div class="example-box-wrapper card">
			<div class="">
				<div id="viewEndUserDiv" class="example-box-wrapper"
			style="margin-top: 30px;">
			
			<br> <br>
			<form:form id="viewEndUserForm" name="viewEndUserForm"
				class="form-horizontal" data-parsley-validate=""
				action="javascript:submitForm('/apu/endUser/otp', 'viewEndUserForm', 'page-content-holder')"
				method='POST' modelAttribute="endUserForm">
				<div class="text-centrCls">
				<h3>
					<spring:message code="viewEndUser-form.viewEndUser-label" />
				</h3>
			</div>
				<input type="hidden" path="<c:out value="${_csrf.parameterName}"/>"
					name="<c:out value="${_csrf.parameterName}"/>"
					value="<c:out value="${_csrf.token}"/>" />
				<div id="viewEndUserApplicationIdDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewEndUser-form.applicationId-label" /> <span
						class="required">:</span>
					</label>
					<div id="viewEndUserApplicationIdDiv" class="col-sm-8 paddingt8">${ endUserForm.applicationId}</div>
				</div>
				<div id="viewEndUserNameDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewEndUser-form.Name-label" /> <span class="required">:</span>
					</label>
					<div id="viewEndUserNameDiv" class="col-sm-8 paddingt8">${ endUserForm.name}</div>
				</div>
				<div id="viewEndUserEmailDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewEndUser-form.email-label" /> <span class="required">:</span>
					</label>
					<div id="viewEndUserEmailDiv" class="col-sm-8 paddingt8">
						${endUserForm.email}</div>
				</div>
				<div id="viewEndUserTypeDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewEndUser-form.type-label" />:<span class="required"></span>
					</label>
					<div id="viewEndUserTypeDiv" class="col-sm-8 paddingt8">${ endUserForm.idProofType}</div>
				</div>

				<div id="viewEndUserIdProofDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewEndUser-form.idProof-label" /> <span class="required">:</span>
					</label>
					<div id="viewEndUserIdProofDiv" class="col-sm-8 paddingt8">
						${endUserForm.idProof}</div>
				</div>
				<div id="viewEndUserMobileNumberDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewEndUser-form.mobileNumber-label" /> <span
						class="required">:</span>
					</label>
					<div id="viewEndUserMobileNumberDiv" class="col-sm-8 paddingt8">
						${endUserForm.mobileNumber}</div>
				</div>
				<div id="viewEndUserDateDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewEndUser-form.date-label" /> <span class="required">:</span>
					</label>
					<div id="viewEndUserDateDiv" class="col-sm-8 paddingt8">
						<fmt:formatDate value="${endUserForm.bookingDate}" pattern="dd-MMM-yyyy" />
					</div>
				</div>
				<div id="viewEndUserPropertyNameDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewEndUser-form.propertyName-label" /> <span
						class="required">:</span>
					</label>
					<div id="viewEndUserPropertyNameDiv" class="col-sm-8 paddingt8">
						${endUserForm.propertyName}</div>
				</div>
				<div id="viewEndUserPurposeNameDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewEndUser-form.purpose-label" /> <span class="required">:</span>
					</label>
					<div id="viewEndUserPropertyNameDiv" class="col-sm-8 paddingt8">
						${endUserForm.purposeName}</div>
				</div>
				<div id="viewEndUserAddressDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewEndUser-form.address-label" /> <span class="required">:</span>
					</label>
					<div id="viewEndUserAddressDiv" class="col-sm-8 paddingt8">
						${endUserForm.addr}</div>
				</div>
				<div class="divider"></div>
				<div id="endUserButtonsDiv" class="form-group">
					<div class="col-sm-offset-2 col-sm-8">
						<div class="example-box-wrapper">
							<button id="endUserViewBackBt" name="endUserViewBackBt"
								type="button" class="btn btn-info"
								onClick="javascript:getUrl('/apu/endUser/back/form?un=${endUserForm.userName}', 'page-content-holder','initDate')">
								<i class="glyph-icon icon-minus-square"></i>&nbsp;
								<spring:message code="viewEndUser-form.back-button" />
							</button> 
							<button id="endUserViewBackBt" name="endUserViewBackBt"
								type="button" class="btn btn-info"
								onClick="javascript:loadContent('/apu/sendOtp?un=${endUserForm.userName}', 'page-content-holder')">
								<i class="glyph-icon icon-minus-square"></i>&nbsp;
								Confirm
							</button>
					</button>
						</div>
					</div>
				</div>
			</form:form>
		</div>
			</div>
	</div>
</div>
