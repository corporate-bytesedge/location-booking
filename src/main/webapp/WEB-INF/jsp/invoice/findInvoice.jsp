<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
   
<div id="page-content-holder">
<div id="page-content page-data">
	<div id="createAddBookingDiv" class="example-box-wrapper wrapper-div">
		<div class="text-center text-centrCls">
			<h3>
				<%-- <spring:message code="createAddBooking-form.createAddBooking-label" /> --%>
			</h3>
		</div>
	</div>
	<form:form id="invoiceForm" class="form-horizontal"
		action="javascript:submitForm('/app/invoice/findInvoiceId', 'invoiceForm',  'page-content-holder')"
		method='POST' modelAttribute="invoiceForm">
      <div id="invoiceIdDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="invoiceIdDiv-form.invoiceId-label" /> <span class="required">*</span>
			</label>
			<div id="invoiceIdDiv" class="col-sm-4">
				<form:input class="form-control" type="text" id="applicationId" 
					placeholder="Enter invoiceId" path="applicationId" maxlength="128"
					required="true" />
			</div>
			<button id="invoiceIdSubmitBt" name="invoiceIdSubmitBt"
						type="submit" class="col-sm-2 btn btn-success">
						<i class="glyph-icon icon-plus-square"></i>&nbsp;
						<spring:message code="invoiceIdSubmitBt-form.submit-button" />
					</button>	
		</div>	
		
	</form:form>
</div>
</div>
<!-- <script>
function getApplicationId() {
	alert("test");
	var applicationId = $("#applicationId").val();
	$.ajax({
		type : 'GET',
		url : '/app/invoice/find/form?applicationId=' + applicationId,
		success : function(returnedData) {
			$("#page-content-holder").html(returnedData);
		},
		error : function(returnedData) {
		}
	});
}
</script>
 -->