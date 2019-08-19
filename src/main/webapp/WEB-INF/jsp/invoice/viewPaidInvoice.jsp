<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<style>
	.wrapper-div{
	margin: 40px 40px 40px 40px !important;
}


.page-data{
	margin-top: 20px;
}
</style>

	<div class="example-box-wrapper page-data">
		<div id="viewInvoiceDiv" class="example-box-wrapper wrapper-div">
			<div class="text-centrCls">
				<h3>
					<spring:message code="viewInvoice-form.viewInvoice-label" />
				</h3>
			</div>
			<div class="divider"></div>
			<div style="float: right;">
				<button id="invoiceViewBackBt" name="invoiceViewBackBt" type="back"
					class="btn btn-info"
					onClick="javascript:getUrl('/app/transactionStatus/invoice/success?reqPage=${reqPage}&pageSize=${pageSize}','page-content-holder');">
					<i class="glyph-icon icon-minus-square"></i>&nbsp;
					<spring:message code="viewInvoice-form.back-button" />
				</button>
			</div>
			<br>
			<br>
			<form:form id="viewInvoiceForm" name="viewInvoiceForm"
				class="form-horizontal" data-parsley-validate=""
				action="javascript:submitForm('/apu/invoice/view', 'viewInvoiceForm', 'page-content-holder')"
				method='POST' modelAttribute="invoiceForm">
				<input type="hidden" path="<c:out value="${_csrf.parameterName}"/>"
					name="<c:out value="${_csrf.parameterName}"/>"
					value="<c:out value="${_csrf.token}"/>" />
				<h4>
					<span Style="color: blue"><spring:message
							code="viewInvoice-form.InvoiceDetails-label" /></span>
				</h4>
				<div id="viewInvoiceServiceTaxDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewInvoice-form.sgst-label" />:<span
						class="required"></span>
					</label>
					<div id="viewInvoiceServiceTaxDiv" class="col-sm-8 paddingt8">
						${ invoiceForm.sgst}</div>
				</div>
				<div id="viewInvoiceServiceTaxDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewInvoice-form.cgst-label" />:<span
						class="required"></span>
					</label>
					<div id="viewInvoiceServiceTaxDiv" class="col-sm-8 paddingt8">
						${ invoiceForm.cgst}</div>
				</div>
				<div id="viewInvoiceServiceTaxDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewInvoice-form.serviceTax-label" />:<span
						class="required"></span>
					</label>
					<div id="viewInvoiceServiceTaxDiv" class="col-sm-8 paddingt8">
						${ invoiceForm.serviceTax}</div>
				</div>
				<div id="viewInvoiceRentPriceDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewInvoice-form.rentPrice-label" /> <span class="required">:</span>
					</label>
					<div id="viewInvoiceRentPriceDiv" class="col-sm-8 paddingt8">
						${invoiceForm.price}</div>
				</div>
				<div id="viewInvoiceTotalPriceDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewInvoice-form.totalPrice-label" /> <span class="required">:</span>
					</label>
					<div id="viewInvoiceTotalPriceDiv" class="col-sm-8 paddingt8">
						${invoiceForm.total}</div>
				</div>
				<div id="viewInvoiceCreatedTimeDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewInvoice-form.createdTime-label" /><span class="required">:</span>
					</label>
					<div id="viewInvoiceCreatedTimeDiv" class="col-sm-8 paddingt8">
						<fmt:formatDate value="${invoiceForm.createdTime}"
							pattern="dd-MMM-yyyy HH-mm-ss" />
					</div>
				</div>
				<div class="divider"></div>
				<div id="invoiceButtonsDiv" class="form-group">
					<div class="col-sm-offset-2 col-sm-8">
						<div class="example-box-wrapper">
							<button id="invoiceViewBackBt" name="invoiceViewBackBt" type="button"
								class="btn btn-info"
								onClick="javascript:getUrl('/app/transactionStatus/invoice/success?reqPage=${reqPage}&pageSize=${pageSize}','page-content-holder');">
								<i class="glyph-icon icon-minus-square"></i>&nbsp;
								<spring:message code="viewInvoice-form.back-button" />
							</button>
						</div>
					</div>
				</div>
			</form:form>
		</div>
	</div>
