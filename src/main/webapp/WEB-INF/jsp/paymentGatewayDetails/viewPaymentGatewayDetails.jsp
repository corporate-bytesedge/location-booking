<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<div id="page-content">
	<div class="example-box-wrapper">
		<div id="viewPaymentGatewayDetailsDiv" class="example-box-wrapper">
			<div style="float: left;">
				<h3>
					<spring:message
						code="viewPaymentGatewayDetails-form.viewPaymentGatewayDetails-label" />
				</h3>
			</div>
			<div style="float: right">
				<button id="paymentGatewayDetailsViewBackBt"
					name="paymentGatewayDetailsViewBackBt" type="back"
					class="btn btn-info"
					onClick="javascript:getUrl('/app/paymentGatewayDetails/list?reqPage=${reqPage}&pageSize=${pageSize}','page-content-holder');">
					<i class="glyph-icon icon-minus-square"></i>&nbsp;
					<spring:message code="viewPaymentGatewayDetails-form.back-button" />
				</button>
				<%-- <button id="paymentGatewayDetailsViewDownloadBt"
					name="paymentGatewayDetailsViewDownloadBt" type="download"
					class="btn btn-warning"
					onClick="window.location='/app/paymentGatewayDetails/download/pdf?id=${paymentGatewayDetailsForm.id}'">
					<i class="glyph-icon icon-arrow-circle-down"></i>&nbsp;
					<spring:message
						code="viewPaymentGatewayDetails-form.download-button" />
				</button> --%>
			</div>
			<br> <br>
			<form:form id="viewPaymentGatewayDetailsForm"
				name="viewPaymentGatewayDetailsForm" class="form-horizontal"
				data-parsley-validate=""
				action="javascript:submitForm('/app/paymentGatewayDetails/view', 'viewPaymentGatewayDetailsForm', 'page-content-holder')"
				method='POST' modelAttribute="paymentGatewayDetailsForm">
				<input type="hidden" path="<c:out value="${_csrf.parameterName}"/>"
					name="<c:out value="${_csrf.parameterName}"/>"
					value="<c:out value="${_csrf.token}"/>" />
				<div class="divider"></div>
				<div id="viewPaymentGatewayDetailsStatusDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewPaymentGatewayDetails-form.paymentGatewayDetailsStatus-label" />
						<span class="required">:</span>
					</label>
					<div id="viewPaymentGatewayDetailsStatusDiv"
						class="col-sm-8 paddingt8">
						${paymentGatewayDetailsForm.status}</div>
				</div>
				<div id="viewPaymentGatewayDetailsLoginDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewPaymentGatewayDetails-form.login-label" /> <span
						class="required">:</span>
					</label>
					<div id="viewPaymentGatewayDetailsLoginDiv"
						class="col-sm-8 paddingt8">${ paymentGatewayDetailsForm.login}</div>
				</div>
				<div id="viewPaymentGatewayDetailsPasswordDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewPaymentGatewayDetails-form.Password-label" />:<span
						class="required"></span>
					</label>
					<div id="viewPaymentGatewayDetailsPasswordDiv"
						class="col-sm-8 paddingt8">${ paymentGatewayDetailsForm.password}</div>
				</div>
				<div id="viewPaymentGatewayDetailsCurrencyDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewPaymentGatewayDetails-form.currency-label" /> <span
						class="required">:</span>
					</label>
					<div id="viewPaymentGatewayDetailsCurrencyDiv"
						class="col-sm-8 paddingt8">
						${paymentGatewayDetailsForm.txnCurr}</div>
				</div>
				<div id="viewPaymentGatewayDetailsReqHashKeyDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewPaymentGatewayDetails-form.reqHashKey-label" /> <span
						class="required">:</span>
					</label>
					<div id="viewPaymentGatewayDetailsReqHashKeyDiv"
						class="col-sm-8 paddingt8">
						${paymentGatewayDetailsForm.reqHashKey}</div>
				</div>
				<div id="viewPaymentGatewayDetailsResHashKeyDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewPaymentGatewayDetails-form.resHashKey-label" /> <span
						class="required">:</span>
					</label>
					<div id="viewPaymentGatewayDetailsResHashKeyDiv"
						class="col-sm-8 paddingt8">
						${paymentGatewayDetailsForm.resHashKey}</div>
				</div>
				<div id="viewPaymentGatewayDetailsTtypeDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewPaymentGatewayDetails-form.ttype-label" /> <span
						class="required">:</span>
					</label>
					<div id="viewPaymentGatewayDetailsTtypeDiv"
						class="col-sm-8 paddingt8">
						${paymentGatewayDetailsForm.ttype}</div>
				</div>
				<div id="viewPaymentGatewayDetailsProdidDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewPaymentGatewayDetails-form.prodid-label" /> <span
						class="required">:</span>
					</label>
					<div id="viewPaymentGatewayDetailsProdidDiv"
						class="col-sm-8 paddingt8">
						${paymentGatewayDetailsForm.prodId}</div>
				</div>
				<div id="viewPaymentGatewayDetailsUrlDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewPaymentGatewayDetails-form.merUrl-label" /> <span
						class="required">:</span>
					</label>
					<div id="viewPaymentGatewayDetailsUrlDiv"
						class="col-sm-8 paddingt8">
						${paymentGatewayDetailsForm.merchantUrl}</div>
				</div>
				<div id="viewPaymentGatewayDetailsCreatedUserNameDiv"
					class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewPaymentGatewayDetails-form.createdUserId-label" /><span
						class="required">:</span>
					</label>
					<div id="viewPaymentGatewayDetailsCreatedUserNameDiv"
						class="col-sm-8 paddingt8">
						${paymentGatewayDetailsForm.createdUserName}</div>
				</div>
				<div id="viewPaymentGatewayDetailsCreatedTimeDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewPaymentGatewayDetails-form.createdTime-label" /><span
						class="required">:</span>
					</label>
					<div id="viewPaymentGatewayDetailsCreatedTimeDiv"
						class="col-sm-8 paddingt8">
						<fmt:formatDate value="${paymentGatewayDetailsForm.createdTime}"
							pattern="dd-MMM-yyyy HH-mm-ss" />
					</div>
				</div>
				<div id="viewPaymentGatewayDetailsUpdatedUserNameDiv"
					class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewPaymentGatewayDetails-form.updatedUserId-label" /><span
						class="required">:</span>
					</label>
					<div id="viewPaymentGatewayDetailsUpdatedUserNameDiv"
						class="col-sm-8 paddingt8">
						${paymentGatewayDetailsForm.updatedUserName}</div>
				</div>
				<div id="viewPaymentGatewayDetailsUpdatedTimeDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewPaymentGatewayDetails-form.updatedTime-label" /><span
						class="required">:</span>
					</label>
					<div id="viewPaymentGatewayDetailsUpdatedTimeDiv"
						class="col-sm-8 paddingt8">
						<fmt:formatDate value="${paymentGatewayDetailsForm.updatedTime}"
							pattern="dd-MMM-yyyy HH-mm-ss" />
					</div>
				</div>
				<div class="divider"></div>
				<div id="paymentGatewayDetailsButtonsDiv" class="form-group">
					<div class="col-sm-offset-2 col-sm-8">
						<div class="example-box-wrapper">
							<button id="paymentGatewayDetailsViewBackBt"
								name="paymentGatewayDetailsViewBackBt" type="back"
								class="btn btn-info"
								onClick="javascript:getUrl('/app/paymentGatewayDetails/list?reqPage=${reqPage}&pageSize=${pageSize}','page-content-holder');">
								<i class="glyph-icon icon-minus-square"></i>&nbsp;
								<spring:message
									code="viewPaymentGatewayDetails-form.back-button" />
							</button>
							<%-- <button id="paymentGatewayDetailsViewDownloadBt"
								name="paymentGatewayDetailsViewDownloadBt" type="download"
								class="btn btn-warning"
								onClick="window.location='/app/paymentGatewayDetails/download/pdf?id=${paymentGatewayDetailsForm.id}'">
								<i class="glyph-icon icon-arrow-circle-down"></i>&nbsp;
								<spring:message
									code="viewPaymentGatewayDetails-form.download-button" />
							</button> --%>
						</div>
					</div>
				</div>
			</form:form>
		</div>
	</div>
</div>