<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<style>
	.pagination{
 margin-top: 15px;
}
</style>
<div id="page-content">
	<div id="listOrganizationDiv" class="example-box-wrapper">
		<h3>
			<spring:message code="organizationList-form.organization-label" />
				<span class="badge bg-red">${data.objCount}</span>
		</h3>
		<div class="divider"></div>
			<c:if test="${not empty data.message_success}">
				<div id="organizationActionSuccessMsgDiv" class="example-box-wrapper">
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
				        	fadeOutDiv('organizationActionSuccessMsgDiv');
						</script>
				</div>
			</c:if>
			<c:if test="${not empty data.message_error}">
				<div id="organizationActionFailedMsgDiv" class="example-box-wrapper">
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
				        	fadeOutDiv('organizationActionFailedMsgDiv');
						</script>
				</div>
			</c:if>
			<div style="margin-bottom: 60px;">
				<div style="float: left;">
				<button id="organizationViewBt" name="organizationViewBt" class="btn btn-purple" onClick="javascript:viewObject('organizationListTable', 'organizationListTableHeaderCheckbox', 'Organization', '/app/organization/view/form?reqPage=${data.paginatedResponse.reqPage}&pageSize=${data.paginatedResponse.pageSize}&id=', 'page-content-holder');">
					<i class="glyph-icon icon-file"></i>&nbsp;<spring:message code="organizationList-form.view-button"/>
					</button>
					<button id="organizationUpdateBt" name="organizationUpdateBt" class="btn btn-info" onClick="javascript:updateObject('organizationListTable', 'organizationListTableHeaderCheckbox', 'Organization', '/app/organization/update/form?reqPage=${data.paginatedResponse.reqPage}&pageSize=${data.paginatedResponse.pageSize}&id=', 'page-content-holder');">
					<i class="glyph-icon icon-pencil-square"></i>&nbsp;<spring:message code="organizationList-form.update-button"/>
					</button>
					<button id="organizationExportBt" name="organizationExportBt" class="btn btn-primary" onClick="window.location='/app/organization/list/export?reqPage=${data.paginatedResponse.reqPage}'">
						<i class="glyph-icon icon-arrow-down"></i>&nbsp;
							<spring:message code="organizationList-form.export-button" />
					</button>
				</div>
				<div style="float:right">
				<button id="organizationRefreshBt" name="organizationRefreshBt" class="btn btn-azure" onClick="javascript:loadContent('/app/organization/list','page-content-holder');">
					<i class="glyph-icon icon-refresh"></i>&nbsp;<spring:message code="organizationList-form.refresh-button"/>
				</button>
			</div>
			</div>
			<%@include file="/WEB-INF/jsp/common/paginatedNavBar.jsp" %>
			<div class="example-box-wrapper">
				<table class="table table-striped table-bordered" id="organizationListTable" name="organizationListTable">
					<thead>
						<tr>
						<c:if test="${not empty data.paginatedResponse.resultList}">
							<th><input type="checkbox" id="organizationListTableHeaderCheckbox" class="custom-checkbox"	onclick="checkAllTableCheckbox(this, 'organizationListTable')"></th>
							</c:if>
							<th><spring:message code="organizationList-form.id-label" /></th>
							<th><spring:message code="organizationList-form.name-label" /></th>
							<th><spring:message	code="organizationList-form.phoneNumber-label" /></th>
							<th><spring:message	code="organizationList-form.mobileNumber-label" /></th>
							<th><spring:message	code="organizationList-form.emailId-label" /></th>

						</tr>
					</thead>
						<tbody>
							<c:if test="${not empty data.paginatedResponse.resultList}">
								<c:forEach var="obj" items="${data.paginatedResponse.resultList}">
									<tr>
										<td align="center"><input type="checkbox" id="${obj.id}" class="custom-checkbox"></td>
										<td>${obj.id }</td>
										<td>${obj.name }</td>
										<td>${obj.phoneNumber}</td>
										<td>${obj.mobileNumber}</td>
										<td>${obj.emailId}</td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
				</table>
			</div>
		</div>
	</div>
