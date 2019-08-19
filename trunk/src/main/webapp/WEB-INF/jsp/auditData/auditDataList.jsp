<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>

<script>
	function lockUser(tableId) {
		var checkList = "";
		var tbl = document.getElementById(tableId)
		if (tbl != null) {
			for (var bxs = tbl.getElementsByTagName("input"), j = bxs.length; j--;) {
				if (bxs[j].type == "checkbox" && bxs[j].checked) {
					if (bxs[j].id.valueOf() != 'auditDataListTableHeaderCheckbox') {
						checkList = checkList + bxs[j].id + ",";
					}
				}
			}
		}
		if (checkList == "") {
			alert('Please select at least one User to perform Lock operation');
		} else {
			getUrl('/app/auditData/lock?idCsv=' + checkList,
					'page-content-holder');
		}
	};
</script>
<style>
	.pagination{
 margin-top: 15px;
}
</style>
<div id="page-content">
	<div id="listUserDiv" class="example-box-wrapper">
		<h3>
			<spring:message code="auditDataList-form.auditData-label" />
			<span class="badge bg-red">${data.objCount}</span>
		</h3>
		<div class="divider"></div>
		<c:if test="${not empty data.message_success}">
			<div id="auditDataActionSuccessMsgDiv" class="example-box-wrapper">
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
					fadeOutDiv('auditDataActionSuccessMsgDiv');
				</script>
			</div>
		</c:if>
		<c:if test="${not empty data.message_error}">
			<div id="auditDataActionFailedMsgDiv" class="example-box-wrapper">
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
					fadeOutDiv('auditDataActionFailedMsgDiv');
				</script>
			</div>
		</c:if>
		<div id="auditDataButtonDiv" style="margin-bottom: 60px;">
			<div style="float: left;">

				<button id="auditDataViewBt" name="auditDataViewBt"
					class="btn btn-purple"
					onClick="javascript:viewObject('auditDataListTable', 'auditDataListTableHeaderCheckbox', 'AuditData', '/app/auditData/view/form?reqPage=${data.paginatedResponse.reqPage}&pageSize=${data.paginatedResponse.pageSize}&id=', 'page-content-holder');">
					<i class="glyph-icon icon-file"></i>&nbsp;
					<spring:message code="auditDataList-form.view-button" />
				</button>
				<button id="auditDataExportBt" name="auditDataExportBt"
					class="btn btn-primary"
					onClick="window.location='/app/auditData/list/export?reqPage=${data.paginatedResponse.reqPage}'">
					<i class="glyph-icon icon-arrow-down"></i>&nbsp;
					<spring:message code="auditDataList-form.export-button" />
				</button>
			</div>
			<div style="float: right">
				<button id="auditDataRefreshBt" name="auditDataRefreshBt"
					class="btn btn-azure"
					onClick="javascript:loadContent('/app/auditData/list','page-content-holder');">
					<i class="glyph-icon icon-refresh"></i>&nbsp;
					<spring:message code="auditDataList-form.refresh-button" />
				</button>
			</div>
		</div>
	<%@include file="/WEB-INF/jsp/common/paginatedNavBar.jsp" %>
		<div id="auditDataFormDiv" class="example-box-wrapper">
			<table class="table table-striped table-bordered"
				id="auditDataListTable" name="auditDataListTable">
				<thead>
					<tr>
						<c:if test="${not empty data.paginatedResponse.resultList}">
							<th><input type="checkbox"
								id="auditDataListTableHeaderCheckbox" class="custom-checkbox"
								onclick="checkAllTableCheckbox(this, 'auditDataListTable')"></th>
						</c:if>
						<th><spring:message code="auditDataList-form.operation-label" /></th>
						<th><spring:message code="auditDataList-form.auditObject-label" /></th>
						<th><spring:message code="auditDataList-form.createdTime-label" /></th>
						<th><spring:message code="auditDataList-form.createdUserId-label" /></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${not empty data.paginatedResponse.resultList}">
						<c:forEach var="obj" items="${data.paginatedResponse.resultList}">
							<tr>
								<td align="center"><input type="checkbox" id="${obj.id}"
									class="custom-checkbox"></td>
								<td>${obj.operation}</td>
								<td>${obj.auditObject}</td>
								<td><fmt:formatDate value="${obj.createdTime}"
										pattern="dd-MM-yyyy" /></td>
								<td>${obj.createdUserName}</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>
	</div>
</div>
