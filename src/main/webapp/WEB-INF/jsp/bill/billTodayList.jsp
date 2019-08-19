<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<style>
	.pagination{
 margin-top: 15px;
}
</style>
<div id="page-content">
	<div id="listBillDiv" class="example-box-wrapper">
		<h3>
			<spring:message code="billList-form.todaybill-label" />
				<span class="badge bg-red">${data.objCount}</span>
		</h3>
		<div class="divider"></div>
			<c:if test="${not empty data.message_success}">
				<div id="billActionSuccessMsgDiv" class="example-box-wrapper">
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
				        	fadeOutDiv('billActionSuccessMsgDiv');
						</script>
				</div>
			</c:if>
			<c:if test="${not empty data.message_error}">
				<div id="billActionFailedMsgDiv" class="example-box-wrapper">
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
				        	fadeOutDiv('billActionFailedMsgDiv');
						</script>
				</div>
			</c:if>
    	<div id="billButtonDiv" style="margin-bottom:60px;">
			<div style="float:left;">
				<%-- <button id="billViewBt" name="billViewBt" class="btn btn-purple" onClick="javascript:viewObject('billListTable', 'billListTableHeaderCheckbox', 'Bill', '/app/bill/view/today/form?reqPage=${data.paginatedResponse.reqPage}&pageSize=${data.paginatedResponse.pageSize}&id=', 'page-content-holder');">
					<i class="glyph-icon icon-file"></i>&nbsp;<spring:message code="billList-form.view-button"/>
					</button> --%>
				<button id="billExportBt" name="billExportBt" class="btn btn-primary" onClick="window.location='/app/todayBill/list/export'">
					<i class="glyph-icon icon-arrow-down"></i>&nbsp;<spring:message code="billList-form.export-button"/>
				</button>
			</div>
			<div style="float:right">
				<button id="billRefreshBt" name="billRefreshBt" class="btn btn-azure" onClick="javascript:loadContent('/app/bill/list/today','page-content-holder');">
					<i class="glyph-icon icon-refresh"></i>&nbsp;<spring:message code="billList-form.refresh-button"/>
				</button>
			</div>
        </div>
        <%@include file="/WEB-INF/jsp/common/paginatedNavBar.jsp" %>
		<div id="billFormDiv" class="example-box-wrapper">
			<table class="table table-striped table-bordered" id="billListTable" name="billListTable">
				<thead>
					<tr>
					<c:if test="${not empty data.paginatedResponse.resultList}">
						<!-- <th><input type="checkbox" id="billListTableHeaderCheckbox" class="custom-checkbox" onclick="checkAllTableCheckbox(this, 'billListTable')"></th>
						 --></c:if>
						<th><spring:message code="billList-form.applicationId-label"/></th>
						<th><spring:message code="billList-form.name-label"/></th>
						<%-- <th><spring:message code="billList-form.email-label"/></th> --%>
						<th><spring:message code="billList-form.mobileNumber-label"/></th>
						<th><spring:message code="billList-form.type-label"/></th>
						<%-- <th><spring:message code="billList-form.idProof-label"/></th>
						 --%><th><spring:message code="billList-form.date-label"/></th>
						<th><spring:message code="billList-form.venue-label"/></th>
						<th><spring:message code="billList-form.purpose-label"/></th>
						<th><spring:message code="billList-form.price-label"/></th>
						<%-- <th><spring:message code="billList-form.cgst-label"/></th>
						<th><spring:message code="billList-form.sgst-label"/></th> --%>
						<th><spring:message code="billList-form.total-label"/></th>
						<%-- <th><spring:message code="billList-form.paymentMode-label"/></th> --%>
						<th><spring:message code="billList-form.paymentStatus-label"/></th>
						<th><spring:message code="billList-form.createdTime-label"/></th>
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
								<%-- <td>${obj.email}</td> --%>
								<td>${obj.mobileNumber}</td>
								<td>${obj.idProofType}</td>
								<%-- <td>${obj.idProof}</td> --%>
								<td><fmt:formatDate value="${obj.bookingDate}" pattern="dd-MMM-yyyy" /></td>
								<td>${obj.propertyName}</td>
								<td>${obj.purposeName}</td>
								<td>${obj.price}</td>
								<%-- <td>${obj.cgst}</td>
								<td>${obj.sgst}</td> --%>
								<td>${obj.total}</td>
								<%-- <td>${obj.paymentMode}</td> --%>
								<td>${obj.paymentStatus}</td>
								<td><fmt:formatDate value="${obj.createdTime}" pattern="dd-MMM-yyyy" /></td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>		
	</div>
</div>
