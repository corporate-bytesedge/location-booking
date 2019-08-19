<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>

<style>
	.pagination{
float: left;
    margin-top: 25px;
}
</style>
<div id="page-content">
	<div id="listUserRoleDiv" class="example-box-wrapper">
		<h3>
			<spring:message code="userRoleList-form.userRole-label" />
			<span class="badge bg-red">${data.objCount}</span>
		</h3>
		<div class="divider"></div>
		<c:if test="${not empty data.message_success}">
			<div id="userRoleActionSuccessMsgDiv" class="example-box-wrapper">
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
				        	fadeOutDiv('userRoleActionSuccessMsgDiv');
						</script>
			</div>
		</c:if>
		<c:if test="${not empty data.message_error}">
			<div id="userRoleActionFailedMsgDiv" class="example-box-wrapper">
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
				        	fadeOutDiv('userRoleActionFailedMsgDiv');
						</script>
			</div>
		</c:if>
		<div style="margin-bottom: 60px;">
			<div style="float: left;">
				<button id="userRoleCreateBt" name="userRoleCreateBt" class="btn btn-success" onClick="javascript:createObject('userRoleListTable', 'userRoleListTableHeaderCheckbox', 'User Role', '/app/userRole/create/form', 'page-content-holder');">
					<i class="glyph-icon icon-plus-square"></i>&nbsp;
					<spring:message code="userRoleList-form.create-button" />
				</button>
				<button id="userRoleViewBt" name="userRoleViewBt" class="btn btn-purple" onClick="javascript:viewObject('userRoleListTable', 'userRoleListTableHeaderCheckbox', 'User Role', '/app/userRole/view/form?reqPage=${data.paginatedResponse.reqPage}&pageSize=${data.paginatedResponse.pageSize}&id=', 'page-content-holder');">
					<i class="glyph-icon icon-file"></i>&nbsp;<spring:message code="userRoleList-form.view-button"/>
					</button>
				<button id="userRoleUpdateBt" name="userRoleUpdateBt" class="btn btn-info" onClick="javascript:updateObject('userRoleListTable', 'userRoleListTableHeaderCheckbox', 'User Role', '/app/userRole/update/form?reqPage=${data.paginatedResponse.reqPage}&pageSize=${data.paginatedResponse.pageSize}&id=', 'page-content-holder');">
					<i class="glyph-icon icon-pencil-square"></i>&nbsp;<spring:message code="userRoleList-form.update-button"/>
					</button>
				<button id="userRoleExportBt" name="userRoleExportBt"
					class="btn btn-primary"
					onClick="window.location='/app/userRole/list/export?reqPage=${data.paginatedResponse.reqPage}'">
					<i class="glyph-icon icon-arrow-down"></i>&nbsp;
					<spring:message code="userRoleList-form.export-button" />
				</button>
			</div>
			<div style="float:right">
				<button id="userRoleRefreshBt" name="userRoleRefreshBt" class="btn btn-azure" onClick="javascript:loadContent('/app/userRole/list','page-content-holder');">
					<i class="glyph-icon icon-refresh"></i>&nbsp;<spring:message code="userRoleList-form.refresh-button"/>
				</button>
			</div>
		</div>
		<%@include file="/WEB-INF/jsp/common/paginatedNavBar.jsp" %>
		<div class="example-box-wrapper">
			<table class="table table-striped table-bordered"
				id="userRoleListTable" name="userRoleListTable">
				<thead>
					<tr>
					<c:if test="${not empty data.paginatedResponse.resultList}">
						<th><input type="checkbox"
							id="userRoleListTableHeaderCheckbox" class="custom-checkbox"
							onclick="checkAllTableCheckbox(this, 'userRoleListTable')"></th>
						</c:if>
						<th><spring:message code="userRoleList-form.id-label" /></th>
						<th><spring:message code="userRoleList-form.name-label" /></th>
						<th><spring:message code="userRoleList-form.admin-label" /></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${not empty data.paginatedResponse.resultList}">
						<c:forEach var="obj" items="${data.paginatedResponse.resultList}">
							<tr>
								<td><input type="checkbox" id="${obj.id}" class="custom-checkbox"></td>
								<td>${obj.id}</td>
								<td>${obj.name}</td>
								<td>
									<c:choose>
									    <c:when test="${obj.admin}">
									       <i class="glyph-icon icon-check"/>
									    </c:when>
									    <c:otherwise>
									        <i class="glyph-icon icon-remove"/>
									    </c:otherwise>
									</c:choose>
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>
	</div>
</div>
