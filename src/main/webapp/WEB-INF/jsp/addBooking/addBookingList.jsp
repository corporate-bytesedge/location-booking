<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<script>
function lockEndaddBooking(tableId) {
		var checkList = "";
		var tbl = document.getElementById(tableId)
		if(tbl != null) {
			for (var bxs = tbl.getElementsByTagName("input"), j = bxs.length; j--;) {
				if (bxs[j].type == "checkbox" && bxs[j].checked) {
					if(bxs[j].id.valueOf() != 'addBookingistTableHeaderCheckbox') {
						checkList = checkList + bxs[j].id + ",";
					}
				}
			}
		}
		if(checkList == "") {
			alert('Please select at least one addBooking to perform Lock operation');
		} else {
			getUrl('/app/addBooking?idCsv=' + checkList, 'page-content-holder');
		}
	};
	
</script>
<style>
	.pagination{
 margin-top: 15px;
}
</style>
<div id="page-content">
	<div id="listaddBookingsDiv" class="example-box-wrapper">
		<h3>
			<spring:message code="addBookingList-form.addBooking-label" />
				<span class="badge bg-red">${data.objCount}</span>
		</h3>
		<div class="divider"></div>
			<c:if test="${not empty data.message_success}">
				<div id="addBookingActionSuccessMsgDiv" class="example-box-wrapper">
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
				        	fadeOutDiv('addBookingActionSuccessMsgDiv');
						</script>
				</div>
			</c:if>
			<c:if test="${not empty data.message_error}">
				<div id="addBookingActionFailedMsgDiv" class="example-box-wrapper">
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
				        	fadeOutDiv('addBookingActionFailedMsgDiv');
						</script>
				</div>
			</c:if>
    	<div id="addBookingButtonDiv" style="margin-bottom:60px;">
			<div style="float:left;">
				<%-- <button id="addBookingViewBt" name="addBookingViewBt" class="btn btn-purple" onClick="javascript:viewObject('addBookingListTable', 'addBookingListTableHeaderCheckbox', 'addBookings', '/app/addBooking/view?reqPage=${data.paginatedResponse.reqPage}&pageSize=${data.paginatedResponse.pageSize}&id=', 'page-content-holder');">
					<i class="glyph-icon icon-file"></i>&nbsp;<spring:message code="addBookingList-form.view-button"/>
					</button> --%>
					<%-- <button id="addBookingRemoveBt" name="addBookingRemoveBt" class="btn btn-danger" onClick="javascript:removeObject('addBookingListTable', 'addBookingListTableHeaderCheckbox', 'addBookings', '/app/addBooking/delete?idCsv=', 'page-content-holder');">
						<i class="glyph-icon icon-minus-square"></i>&nbsp;<spring:message code="addBookingList-form.remove-button"/>
					</button> --%>
				<button id="addBookingExportBt" name="addBookingExportBt" class="btn btn-primary" onClick="window.location='/app/addBooking/list/export?reqPage=${data.paginatedResponse.reqPage}'">
					<i class="glyph-icon icon-arrow-down"></i>&nbsp;<spring:message code="addBookingList-form.export-button"/>
				</button>
			</div>
			<div style="float:right">
				<button id="addBookingRefreshBt" name="addBookingRefreshBt" class="btn btn-azure" onClick="javascript:loadContent('/app/addBooking/list','page-content-holder');">
					<i class="glyph-icon icon-refresh"></i>&nbsp;<spring:message code="addBookingList-form.refresh-button"/>
				</button>
			</div>
        </div>
        <%@include file="/WEB-INF/jsp/common/paginatedNavBar.jsp" %>
		<div id="addBookingFormDiv" class="example-box-wrapper">
			<table class="table table-striped table-bordered" id="addBookingListTable" name="addBookingListTable">
				<thead>
					<tr>
					<c:if test="${not empty data.paginatedResponse.resultList}">
						<!-- <th><input type="checkbox" id="addBookingListTableHeaderCheckbox" class="custom-checkbox" onclick="checkAllTableCheckbox(this, 'addBookingListTable')"></th>
						 --></c:if>
						<th><spring:message code="addBookingList-form.applicationId-label"/></th>
						<th><spring:message code="addBookingList-form.name-label"/></th>
						<th><spring:message code="addBookingList-form.email-label"/></th>
						<th><spring:message code="addBookingList-form.type-label"/></th>
						<th><spring:message code="addBookingList-form.idProof-label"/></th>
						<th><spring:message code="addBookingList-form.mobileNumber-label"/></th>
						<th><spring:message code="addBookingList-form.date-label"/></th>
						<th><spring:message code="addBookingList-form.propertyName-label"/></th>
						<th><spring:message code="addBookingList-form.purposeName-label"/></th>
						<th><spring:message code="addBookingList-form.paymentMode-label"/></th>
						<th><spring:message code="addBookingList-form.address-label"/></th>
						<th><spring:message code="addBookingList-form.bookedBy-label"/></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${not empty data.paginatedResponse.resultList}">
						<c:forEach var="obj" items="${data.paginatedResponse.resultList}">
							<tr>
								<%-- <td align="center">
									<input type="checkbox" id="${obj.id}" class="custom-checkbox">
								</td> --%>
								<td>${obj.applicationId}</td>
								<td>${obj.name}</td>
								<td>${obj.email}</td>
								<td><spring:message code="IdProofType.${obj.idProofType}" /></td>
								<td>${obj.idProof}</td>
								<td>${obj.mobileNumber}</td>
								<td><fmt:formatDate value="${obj.bookingDate}" pattern="dd/MM/yyyy" /></td>
								<td>${obj.propertyName}</td>
								<td>${obj.purposeName}</td>
								<td>${obj.paymentMode}</td>
								<td>${obj.addr}</td>
								<td>${obj.createdUserName}</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>		
	</div>
</div>
