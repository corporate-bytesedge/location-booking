<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<div id="page-content">
	<div class="example-box-wrapper">
		<div id="viewBillDiv" class="example-box-wrapper">
			<div style="float: left;">
				<h3>
					<spring:message code="viewBill-form.viewBill-label" />
				</h3>
			</div>
			<div style="float: right">
				<button id="billViewBackBt" name="billViewBackBt" type="button"
					class="btn btn-info"
					onClick="javascript:getUrl('/app/report/bill/future?reqPage=${reqPage}&pageSize=${pageSize}','page-content-holder');">
					<i class="glyph-icon icon-minus-square"></i>&nbsp;
					<spring:message code="viewBill-form.back-button" />
				</button>
				<%-- <button id="billViewDownloadBt" name="billViewDownloadBt"
					type="button" class="btn btn-warning"
					onClick="window.location='/app/bill/download/pdf?id=${billForm.id}'">
					<i class="glyph-icon icon-arrow-circle-down"></i>&nbsp;
					<spring:message code="viewBill-form.download-button" />
				</button> --%>
			</div>
			<br>
			<br>
			<form:form id="viewBillForm" name="viewBillForm"
				class="form-horizontal" data-parsley-validate=""
				action="javascript:submitForm('/app/report/bill/future', 'viewBillForm', 'page-content-holder')"
				method='POST' modelAttribute="billForm">
				<input type="hidden" path="<c:out value="${_csrf.parameterName}"/>"
					name="<c:out value="${_csrf.parameterName}"/>"
					value="<c:out value="${_csrf.token}"/>" />
				<%-- <div class="divider"></div>
				 <h4>
					<span Style="color: blue"><spring:message
							code="viewBill-form.BillDetails-label" /></span>
				</h4> 
				<div class="divider"></div> --%>
				<div id="viewBillInvoiceIdDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewBill-form.invoiceId-label" /> <span class="required">:</span>
					</label>
					<div id="viewBillInvoiceIdDiv" class="col-sm-8 paddingt8">
						${billForm.applicationId}</div>
				</div>
				<div id="viewBillBookingDateDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewBill-form.bookingDate-label" /><span class="required">:</span>
					</label>
					<div id="viewBillBookingDateDiv" class="col-sm-8 paddingt8">
						<fmt:formatDate value="${billForm.bookingDate}"
							pattern="dd-MMM-yyyy"/>
					</div>
				</div>
				<div id="viewBillNameDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewBill-form.name-label" /><span class="required">:</span>
					</label>
					<div id="viewBillNameDiv" class="col-sm-8 paddingt8">${billForm.name}</div>
				</div>
				<div id="viewBillEmailDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewBill-form.email-label" /><span class="required">:</span>
					</label>
					<div id="viewBillEmailDiv" class="col-sm-8 paddingt8">${billForm.email}</div>
				</div>
				<div id="viewBillMobileNumberDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewBill-form.mobileNumber-label" /><span class="required">:</span>
					</label>
					<div id="viewBillMobileNumberDiv" class="col-sm-8 paddingt8">${billForm.mobileNumber}</div>
				</div>
				<div id="viewBillIDproofTypeDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewBill-form.type-label" /><span class="required">:</span>
					</label>
					<div id="viewBillIDproofTypeDiv" class="col-sm-8 paddingt8">${billForm.idProofType}</div>
				</div>
				<div id="viewBillIDproofDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewBill-form.idproof-label" /><span class="required">:</span>
					</label>
					<div id="viewBillIDproofDiv" class="col-sm-8 paddingt8">${billForm.idProof}</div>
				</div>
				<div id="viewBillVenueDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewBill-form.venue-label" /> <span class="required">:</span>
					</label>
					<div id="viewBillVenueDiv" class="col-sm-8 paddingt8">
						${billForm.propertyName}</div>
				</div>
				<div id="viewBillPurposeDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewBill-form.purpose-label" /> <span class="required">:</span>
					</label>
					<div id="viewBillPurposeDiv" class="col-sm-8 paddingt8">
						${billForm.purposeName}</div>
				</div>
				<div id="viewBillRentPriceDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewBill-form.rentPrice-label" /> <span class="required">:</span>
					</label>
					<div id="viewBillRentPriceDiv" class="col-sm-8 paddingt8">
						${billForm.price}</div>
				</div>
				<div id="viewBillCgstDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewBill-form.cgst-label" /> <span class="required">:</span>
					</label>
					<div id="viewBillCgstDiv" class="col-sm-8 paddingt8">
						${billForm.cgst}</div>
				</div>
				<div id="viewBillSgstDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewBill-form.Sgst-label" /> <span class="required">:</span>
					</label>
					<div id="viewBillSgstDiv" class="col-sm-8 paddingt8">
						${billForm.sgst}</div>
				</div>
				
				 <div id="viewBillServiceTaxDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewBill-form.servicetax-label" /><span class="required">:</span>
					</label>
					<div id="viewBillServiceTaxDiv" class="col-sm-8 paddingt8">
						${billForm.serviceTax}</div>
				</div>
				<div id="viewBillTotalPriceDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewBill-form.totalPrice-label" /> <span class="required">:</span>
					</label>
					<div id="viewBillTotalPriceDiv" class="col-sm-8 paddingt8">
						${billForm.total}</div>
				</div>
				<div id="viewBillAddressDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewBill-form.addr-label" /> <span class="required">:</span>
					</label>
					<div id="viewBillAddressDiv" class="col-sm-8 paddingt8">
						${billForm.addr}</div>
				</div>
				<div id="viewBillCreatedTimeDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewBill-form.createdTime-label" /><span class="required">:</span>
					</label>
					<div id="viewBillCreatedTimeDiv" class="col-sm-8 paddingt8">
						<fmt:formatDate value="${billForm.createdTime}"
							pattern="dd-MMM-yyyy" />
					</div>
				</div>
				<div class="divider"></div>
				<div id="billButtonsDiv" class="form-group">
					<div class="col-sm-offset-2 col-sm-8">
						<div class="example-box-wrapper">
							<button id="billViewBackBt" name="billViewBackBt" type="button"
								class="btn btn-info"
								onClick="javascript:getUrl('/app/report/bill/future?reqPage=${reqPage}&pageSize=${pageSize}','page-content-holder');">
								<i class="glyph-icon icon-minus-square"></i>&nbsp;
								<spring:message code="viewBill-form.back-button" />
							</button>
<%-- 							<button id="billViewDownloadBt" name="billViewDownloadBt"
								type="button" class="btn btn-warning"
								onClick="window.location='/app/bill/download/pdf?id=${billForm.id}'">
								<i class="glyph-icon icon-arrow-circle-down"></i>&nbsp;
								<spring:message code="viewBill-form.download-button" />
							</button>
 --%>						</div>
					</div>
				</div>
			</form:form>
		</div>
	</div>
</div>