<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<script>
function lockUser(tableId) {
		var checkList = "";
		var tbl = document.getElementById(tableId)
		if(tbl != null) {
			for (var bxs = tbl.getElementsByTagName("input"), j = bxs.length; j--;) {
				if (bxs[j].type == "checkbox" && bxs[j].checked) {
					if(bxs[j].id.valueOf() != 'userListTableHeaderCheckbox') {
						checkList = checkList + bxs[j].id + ",";
					}
				}
			}
		}
		if(checkList == "") {
			alert('Please select at least one User to perform Lock operation');
		} else {
			getUrl('/app/user/lock?idCsv=' + checkList, 'page-content-holder');
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
			<spring:message code="userList-form.user-label" />
				<span class="badge bg-red">${data.objCount}</span>
		</h3>
		<div class="divider"></div>
			<c:if test="${not empty data.message_success}">
				<div id="userActionSuccessMsgDiv" class="example-box-wrapper">
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
				        	fadeOutDiv('userActionSuccessMsgDiv');
						</script>
				</div>
			</c:if>
			<c:if test="${not empty data.message_error}">
				<div id="userActionFailedMsgDiv" class="example-box-wrapper">
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
				        	fadeOutDiv('userActionFailedMsgDiv');
						</script>
				</div>
			</c:if>
    	<div id="userButtonDiv" style="margin-bottom:60px;">
			<div style="float:left;">
				<button id="userCreateBt" name="userCreateBt" class="btn btn-success" onClick="javascript:createObject('userListTable', 'userListTableHeaderCheckbox', 'User', '/app/user/create/form', 'page-content-holder');">
					<i class="glyph-icon icon-plus-square"></i>&nbsp;
					<spring:message code="userList-form.create-button" />
				</button>
				<button id="userViewBt" name="userViewBt" class="btn btn-purple" onClick="javascript:viewObject('userListTable', 'userListTableHeaderCheckbox', 'User', '/app/user/view/form?reqPage=${data.paginatedResponse.reqPage}&pageSize=${data.paginatedResponse.pageSize}&id=', 'page-content-holder');">
					<i class="glyph-icon icon-file"></i>&nbsp;<spring:message code="userList-form.view-button"/>
					</button>
				<button id="userUpdateBt" name="usereUpdateBt" class="btn btn-info" onClick="javascript:updateObject('userListTable', 'userListTableHeaderCheckbox', 'User', '/app/user/update/form?reqPage=${data.paginatedResponse.reqPage}&pageSize=${data.paginatedResponse.pageSize}&id=', 'page-content-holder');">
					<i class="glyph-icon icon-pencil-square"></i>&nbsp;<spring:message code="userList-form.update-button"/>
					</button>
				<button id="userLockBt" name="userLockBt" class="btn btn-danger" onClick="javascript:lockUser('userListTable');">
					<i class="glyph-icon icon-linecons-lock"></i>&nbsp;<spring:message code="userList-form.lock-button"/>
				</button>
				<button id="userExportBt" name="userExportBt" class="btn btn-primary" onClick="window.location='/app/user/list/export?reqPage=${data.paginatedResponse.reqPage}'">
					<i class="glyph-icon icon-arrow-down"></i>&nbsp;<spring:message code="userList-form.export-button"/>
				</button>
			</div>
			<div style="float:right">
				<button id="userRefreshBt" name="userRefreshBt" class="btn btn-azure" onClick="javascript:loadContent('/app/user/list','page-content-holder');">
					<i class="glyph-icon icon-refresh"></i>&nbsp;<spring:message code="userList-form.refresh-button"/>
				</button>
			</div>
        </div>
        <%@include file="/WEB-INF/jsp/common/paginatedNavBar.jsp" %>
		<div id="userFormDiv" class="example-box-wrapper">
			<table class="table table-striped table-bordered" id="userListTable" name="userListTable">
				<thead>
					<tr>
					<c:if test="${not empty data.paginatedResponse.resultList}">
						<th><input type="checkbox" id="userListTableHeaderCheckbox" class="custom-checkbox" onclick="checkAllTableCheckbox(this, 'userListTable')"></th>
						</c:if>
						<th><spring:message code="userList-form.OrgId-label"/></th>
						<th><spring:message code="userList-form.displayName-label"/></th>
						<th><spring:message code="userList-form.gender-label"/></th>
						<th><spring:message code="userList-form.email-label"/></th>
						<th><spring:message code="userList-form.accountState-label"/></th>
						<th><spring:message code="userList-form.aadharId-label"/></th>
						<th><spring:message code="userList-form.mobileNumber-label"/></th>
						<th><spring:message code="userList-form.dob-label"/></th>
						<th><spring:message code="userList-form.designation-label"/></th>
						<th><spring:message code="userList-form.RoleName-label"/></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${not empty data.paginatedResponse.resultList}">
						<c:forEach var="obj" items="${data.paginatedResponse.resultList}">
							<tr>
								<td align="center">
									<input type="checkbox" id="${obj.id}" class="custom-checkbox">
								</td>
								<td>${obj.id}</td>
								<td>${obj.displayName}</td>
								<td><spring:message code="GenderType.${obj.gender}" /></td>
								<td>${obj.email}</td>
								<td><spring:message code="UserState.${obj.accountState}"/></td>
								<td>${obj.aadharId}</td>
								<td>${obj.mobileNumber}</td>
								<td><fmt:formatDate value="${obj.dob}" pattern="dd-MMM-yyyy" /></td>
								<td>${obj.designation}</td>
								<td>${obj.roleName}</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>		
	</div>
</div>
