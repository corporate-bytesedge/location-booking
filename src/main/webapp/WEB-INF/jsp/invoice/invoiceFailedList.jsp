<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<script>
function lockInvoice(tableId) {
		var checkList = "";
		var tbl = document.getElementById(tableId)
		if(tbl != null) {
			for (var bxs = tbl.getElementsByTagName("input"), j = bxs.length; j--;) {
				if (bxs[j].type == "checkbox" && bxs[j].checked) {
					if(bxs[j].id.valueOf() != 'invoiceListTableHeaderCheckbox') {
						checkList = checkList + bxs[j].id + ",";
					}
				}
			}
		}
		if(checkList == "") {
			alert('Please select at least one Invoice to perform Lock operation');
		} else {
			getUrl('/app/invoice/lock?idCsv=' + checkList, 'page-content-holder');
		}
	};
	
</script>
<style>
	.pagination{
 margin-top: 15px;
}
</style>

<div id="page-content">
	<div id="listInvoiceDiv" class="example-box-wrapper">
		<h3>
			<spring:message code="invoiceList-form.failedinvoice-label" />
				<span class="badge bg-red">${data.objCount}</span>
		</h3>
		<div class="divider"></div>
			<c:if test="${not empty data.message_success}">
				<div id="invoiceActionSuccessMsgDiv" class="example-box-wrapper">
					<div class="alert alert-success">
						<div class="bg-green alert-icon">
							<i class="glyph-icon icon-check"></i>
						</div>
						<div class="alert-content">
							<h4 class="alert-title">Action Success</h4>
							<p>${data.message_success}</p>
						</div>
					</div>
						<script type="text/javascript">
				        	fadeOutDiv('invoiceActionSuccessMsgDiv');
						</script>
				</div>
			</c:if>
			<c:if test="${not empty data.message_error}">
				<div id="invoiceActionFailedMsgDiv" class="example-box-wrapper">
					<div class="alert alert-danger">
						<div class="bg-red alert-icon">
							<i class="glyph-icon icon-check"></i>
						</div>
						<div class="alert-content">
							<h4 class="alert-title">Action Failed</h4>
							<p>${data.message_error}</p>
						</div>
					</div>
						<script type="text/javascript">
				        	fadeOutDiv('invoiceActionFailedMsgDiv');
						</script>
				</div>
			</c:if>
    	<div id="invoiceButtonDiv" style="margin-bottom:60px;">
			<div style="float:left;">
				 <button id="invoiceViewBt" name="invoiceViewBt" class="btn btn-purple" onClick="javascript:viewObject('invoiceListTable', 'invoiceListTableHeaderCheckbox', 'Invoice', '/app/invoice/view/failed/form?reqPage=${data.paginatedResponse.reqPage}&pageSize=${data.paginatedResponse.pageSize}&id=', 'page-content-holder');">
					<i class="glyph-icon icon-file"></i>&nbsp;<spring:message code="invoiceList-form.view-button"/>
					</button>
				<button id="invoiceExportBt" name="invoiceExportBt" class="btn btn-primary" onClick="window.location='/app/invoice/failed/list/export'">
					<i class="glyph-icon icon-arrow-down"></i>&nbsp;<spring:message code="invoiceList-form.export-button"/>
				</button>
			</div>
			<div style="float:right">
				<button id="invoiceRefreshBt" name="invoiceRefreshBt" class="btn btn-azure" onClick="javascript:loadContent('/app/transactionStatus/invoice/failed','page-content-holder');">
					<i class="glyph-icon icon-refresh"></i>&nbsp;<spring:message code="invoiceList-form.refresh-button"/>
				</button>
			</div>
        </div>
        <%@include file="/WEB-INF/jsp/common/paginatedNavBar.jsp" %>
		<div id="invoiceFormDiv" class="example-box-wrapper">
			<table class="table table-striped table-bordered" id="invoiceListTable" name="invoiceListTable">
				<thead>
					<tr>
					<c:if test="${not empty data.paginatedResponse.resultList}">
						 <th><input type="checkbox" id="invoiceListTableHeaderCheckbox" class="custom-checkbox" onclick="checkAllTableCheckbox(this, 'invoiceListTable')"></th> 
						 </c:if>
						<th><spring:message code="invoiceList-form.Property-label"/></th>
						<th><spring:message code="invoiceList-form.Purpose-label"/></th>
						<th><spring:message code="invoiceList-form.status-label"/></th>
						<th><spring:message code="invoiceList-form.TotalPrice-label"/></th>
						<th><spring:message code="invoiceList-form.date-label"/></th>
						<th><spring:message code="invoiceList-form.RentPrice-label"/></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${not empty data.paginatedResponse.resultList}">
						<c:forEach var="obj" items="${data.paginatedResponse.resultList}">
							<tr>
							<td align="center">
									<input type="checkbox" id="${obj.id}" class="custom-checkbox">
								</td> 
								<td>${obj.propertyName}</td>
								<td>${obj.purposeName}</td>
								<td><spring:message code="PaymentStatus.${obj.paymentStatus}" /></td>
								<td>${obj.total}</td>
								<td>${obj.bookingDate}</td>
								<td>${obj.price}</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>		
	</div>
</div>
