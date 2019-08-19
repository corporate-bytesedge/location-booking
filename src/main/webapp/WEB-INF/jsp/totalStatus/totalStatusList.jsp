<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<style>
.pagination {
	margin-top: 15px;
}
</style>

<div id="page-content">
	<div id="listTotalStatusDiv" class="example-box-wrapper">
		<h3>
			<spring:message
				code="totalStatusList-form.totalStatus-label" />
			<span class="badge bg-red">${data.objCount}</span>
		</h3>
		<div class="divider"></div>
		<c:if test="${not empty data.message_success}">
			<div id="totalStatusActionSuccessMsgDiv"
				class="example-box-wrapper">
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
					fadeOutDiv('totalStatusActionSuccessMsgDiv');
				</script>
			</div>
		</c:if>
		<c:if test="${not empty data.message_error}">
			<div id="totalStatusActionFailedMsgDiv"
				class="example-box-wrapper">
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
					fadeOutDiv('totalStatusActionFailedMsgDiv');
				</script>
			</div>
		</c:if>
		<div id="totalStatusButtonDiv" style="margin-bottom: 60px;">
			<div style="float: left;">
				<button id="totalStatusViewBt" name="totalStatusViewBt"
					class="btn btn-purple"
					onClick="javascript:viewObject('totalStatusListTable', 'totalStatusListTableHeaderCheckbox', 'TotalStatus', '/app/totalStatus/view/form?reqPage=${data.paginatedResponse.reqPage}&pageSize=${data.paginatedResponse.pageSize}&id=', 'page-content-holder');">
					<i class="glyph-icon icon-file"></i>&nbsp;
					<spring:message code="totalStatusList-form.view-button" />
				</button>
				<button id="totalStatusExportBt"
					name="totalStatusExportBt" class="btn btn-primary"
					onClick="window.location='/app/totalStatus/list/export?reqPage=${data.paginatedResponse.reqPage}'">
					<i class="glyph-icon icon-arrow-down"></i>&nbsp;
					<spring:message code="totalStatusList-form.export-button" />
				</button>
			</div>
			<div style="float: right">
				<button id="totalStatusRefreshBt"
					name="totalStatusRefreshBt" class="btn btn-azure"
					onClick="javascript:loadContent('/app/totalStatus/list','page-content-holder');">
					<i class="glyph-icon icon-refresh"></i>&nbsp;
					<spring:message code="totalStatusList-form.refresh-button" />
				</button>
			</div>
		</div>
		<%@include file="/WEB-INF/jsp/common/paginatedNavBar.jsp"%>
		<div id="totalStatusFormDiv" class="example-box-wrapper">
			<table class="table table-striped table-bordered"
				id="totalStatusListTable" name="totalStatusListTable">
				<thead>
					<tr>
						<c:if test="${not empty data.paginatedResponse.resultList}">
							<th><input type="checkbox"
								id="totalStatusListTableHeaderCheckbox"
								class="custom-checkbox"
								onclick="checkAllTableCheckbox(this, 'totalStatusListTable')"></th>
						</c:if>
						<th><spring:message code="totalStatusList-form.name-label" /></th>
						<th><spring:message code="totalStatusList-form.emailId-label" /></th>
						<th><spring:message code="totalStatusList-form.mobileNo-label" /></th>
						<th><spring:message code="totalStatusList-form.venue-label" /></th>
						<th><spring:message code="totalStatusList-form.venueDate-label" /></th>
						<th><spring:message code="totalStatusList-form.purpose-label" /></th>
						<th><spring:message code="totalStatusList-form.referredBy-label" /></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${not empty data.paginatedResponse.resultList}">
						<c:forEach var="obj" items="${data.paginatedResponse.resultList}">
							<tr>
								<td align="center"><input type="checkbox" id="${obj.id}"
									class="custom-checkbox"></td>
								<td>${obj.name}</td>
								<td>${obj.emailId}</td>
								<td>${obj.mobileNo}</td>
								<td><spring:message code="VenueType.${obj.venue}" /></td>
								<td><fmt:formatDate value="${obj.venueDate}" pattern="dd-MMM-yyyy" /></td>
								<td><spring:message code="PurposeType.${obj.purpose}" /></td>
								<td>${obj.referredBy}</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>
	</div>
</div>
