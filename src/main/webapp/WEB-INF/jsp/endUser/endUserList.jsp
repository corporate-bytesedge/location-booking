<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<script>
function lockEndendUser(tableId) {
		var checkList = "";
		var tbl = document.getElementById(tableId)
		if(tbl != null) {
			for (var bxs = tbl.getElementsByTagName("input"), j = bxs.length; j--;) {
				if (bxs[j].type == "checkbox" && bxs[j].checked) {
					if(bxs[j].id.valueOf() != 'eendUseristTableHeaderCheckbox') {
						checkList = checkList + bxs[j].id + ",";
					}
				}
			}
		}
		if(checkList == "") {
			alert('Please select at least one EndUser to perform Lock operation');
		} else {
			getUrl('/app/enduendUserk?idCsv=' + checkList, 'page-content-holder');
		}
	};
	
</script>
<style>
	.pagination{
 margin-top: 15px;
}
</style>
<div id="page-content">
	<div id="listEndUsersDiv" class="example-box-wrapper">
		<h3>
			<spring:message code="endUserList-form.endUser-label" />
				<span class="badge bg-red">${data.objCount}</span>
		</h3>
		<div class="divider"></div>
			<c:if test="${not empty data.message_success}">
				<div id="endUserActionSuccessMsgDiv" class="example-box-wrapper">
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
				        	fadeOutDiv('endUserActionSuccessMsgDiv');
						</script>
				</div>
			</c:if>
			<c:if test="${not empty data.message_error}">
				<div id="endUserActionFailedMsgDiv" class="example-box-wrapper">
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
				        	fadeOutDiv('endUserActionFailedMsgDiv');
						</script>
				</div>
			</c:if>
    	<div id="endUserButtonDiv" style="margin-bottom:60px;">
			<div style="float:left;">
				<%-- <button id="endUserViewBt" name="endUserViewBt" class="btn btn-purple" onClick="javascript:viewObject('endUserListTable', 'endUserListTableHeaderCheckbox', 'Endusers', '/app/endUser/view/form?reqPage=${data.paginatedResponse.reqPage}&pageSize=${data.paginatedResponse.pageSize}&id=', 'page-content-holder');">
					<i class="glyph-icon icon-file"></i>&nbsp;<spring:message code="endUserList-form.view-button"/>
					</button> --%>
					<button id="endUserRemoveBt" name="endUserRemoveBt" class="btn btn-danger" onClick="javascript:removeObject('endUserListTable', 'endUserListTableHeaderCheckbox', 'Endusers', '/app/endUser/delete?idCsv=', 'page-content-holder');">
						<i class="glyph-icon icon-minus-square"></i>&nbsp;<spring:message code="endUserList-form.remove-button"/>
					</button>
				<button id="endUserExportBt" name="endUserExportBt" class="btn btn-primary" onClick="window.location='/app/endUser/list/export?reqPage=${data.paginatedResponse.reqPage}'">
					<i class="glyph-icon icon-arrow-down"></i>&nbsp;<spring:message code="endUserList-form.export-button"/>
				</button>
			</div>
			<div style="float:right">
				<button id="endUserRefreshBt" name="endUserRefreshBt" class="btn btn-azure" onClick="javascript:loadContent('/app/endUser/list','page-content-holder');">
					<i class="glyph-icon icon-refresh"></i>&nbsp;<spring:message code="endUserList-form.refresh-button"/>
				</button>
			</div>
        </div>
        <%@include file="/WEB-INF/jsp/common/paginatedNavBar.jsp" %>
		<div id="endUserFormDiv" class="example-box-wrapper">
			<table class="table table-striped table-bordered" id="endUserListTable" name="endUserListTable">
				<thead>
					<tr>
					<c:if test="${not empty data.paginatedResponse.resultList}">
						<th><input type="checkbox" id="endUserListTableHeaderCheckbox" class="custom-checkbox" onclick="checkAllTableCheckbox(this, 'endUserListTable')"></th>
						</c:if>
						<th><spring:message code="endUserList-form.applicationId-label"/></th>
						<th><spring:message code="endUserList-form.name-label"/></th>
						<th><spring:message code="endUserList-form.email-label"/></th>
						<th><spring:message code="endUserList-form.type-label"/></th>
						<th><spring:message code="endUserList-form.idProof-label"/></th>
						<th><spring:message code="endUserList-form.mobileNumber-label"/></th>
						<th><spring:message code="endUserList-form.date-label"/></th>
						<th><spring:message code="endUserList-form.propertyName-label"/></th>
						<th><spring:message code="endUserList-form.purposeName-label"/></th>
						<th><spring:message code="endUserList-form.BookingDate-label"/></th>
						<th><spring:message code="endUserList-form.paymentStatus-label"/></th>
						<th><spring:message code="endUserList-form.address-label"/></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${not empty data.paginatedResponse.resultList}">
						<c:forEach var="obj" items="${data.paginatedResponse.resultList}">
							<tr>
								<td align="center">
									<input type="checkbox" id="${obj.id}" class="custom-checkbox">
								</td>
								<td>${obj.applicationId}</td>
								<td>${obj.name}</td>
								<td>${obj.email}</td>
								<td><spring:message code="IdProofType.${obj.idProofType}" /></td>
								<td>${obj.idProof}</td>
								<td>${obj.mobileNumber}</td>
								<td><fmt:formatDate value="${obj.bookingDate}" pattern="dd/MM/yyyy" /></td>
								<td>${obj.propertyName}</td>
								<td>${obj.purposeName}</td>
								<td><fmt:formatDate value="${obj.createdTime}" pattern="dd/MM/yyyy" /></td>
								<td>${obj.paymentStatus}</td>
								<td>${obj.addr}</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>		
	</div>
</div>
