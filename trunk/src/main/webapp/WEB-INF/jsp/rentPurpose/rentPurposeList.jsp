<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<script>
function lockRentPurpose(tableId) {
		var checkList = "";
		var tbl = document.getElementById(tableId)
		if(tbl != null) {
			for (var bxs = tbl.getElementsByTagName("input"), j = bxs.length; j--;) {
				if (bxs[j].type == "checkbox" && bxs[j].checked) {
					if(bxs[j].id.valueOf() != 'rentPurposeListTableHeaderCheckbox') {
						checkList = checkList + bxs[j].id + ",";
					}
				}
			}
		}
		if(checkList == "") {
			alert('Please select at least one RentPurpose to perform Lock operation');
		} else {
			getUrl('/app/rentPurpose/lock?idCsv=' + checkList, 'page-content-holder');
		}
	};
	
</script>
<style>
	.pagination{
 margin-top: 15px;
}
</style>

<div id="page-content">
	<div id="listRentPurposeDiv" class="example-box-wrapper">
		<h3>
			<spring:message code="rentPurposeList-form.rentPurpose-label" />
				<span class="badge bg-red">${data.objCount}</span>
		</h3>
		<div class="divider"></div>
			<c:if test="${not empty data.message_success}">
				<div id="rentPurposeActionSuccessMsgDiv" class="example-box-wrapper">
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
				        	fadeOutDiv('rentPurposeActionSuccessMsgDiv');
						</script>
				</div>
			</c:if>
			<c:if test="${not empty data.message_error}">
				<div id="rentPurposeActionFailedMsgDiv" class="example-box-wrapper">
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
				        	fadeOutDiv('rentPurposeActionFailedMsgDiv');
						</script>
				</div>
			</c:if>
    	<div id="rentPurposeButtonDiv" style="margin-bottom:60px;">
			<div style="float:left;">
				<button id="rentPurposeCreateBt" name="rentPurposeCreateBt" class="btn btn-success" onClick="javascript:createObject('rentPurposeListTable', 'rentPurposeListTableHeaderCheckbox', 'RentPurpose', '/app/rentPurpose/create/form', 'page-content-holder');">
					<i class="glyph-icon icon-plus-square"></i>&nbsp;
					<spring:message code="rentPurposeList-form.create-button" />
				</button>
				<button id="rentPurposeViewBt" name="rentPurposeViewBt" class="btn btn-purple" onClick="javascript:viewObject('rentPurposeListTable', 'rentPurposeListTableHeaderCheckbox', 'RentPurpose', '/app/rentPurpose/view/form?reqPage=${data.paginatedResponse.reqPage}&pageSize=${data.paginatedResponse.pageSize}&id=', 'page-content-holder');">
					<i class="glyph-icon icon-file"></i>&nbsp;<spring:message code="rentPurposeList-form.view-button"/>
					</button>
				<button id="rentPurposeUpdateBt" name="rentPurposeeUpdateBt" class="btn btn-info" onClick="javascript:updateObject('rentPurposeListTable', 'rentPurposeListTableHeaderCheckbox', 'RentPurpose', '/app/rentPurpose/update/form?reqPage=${data.paginatedResponse.reqPage}&pageSize=${data.paginatedResponse.pageSize}&id=', 'page-content-holder');">
					<i class="glyph-icon icon-pencil-square"></i>&nbsp;<spring:message code="rentPurposeList-form.update-button"/>
					</button>
				<button id="rentPurposeExportBt" name="rentPurposeExportBt" class="btn btn-primary" onClick="window.location='/app/rentPurpose/list/export?reqPage=${data.paginatedResponse.reqPage}'">
					<i class="glyph-icon icon-arrow-down"></i>&nbsp;<spring:message code="rentPurposeList-form.export-button"/>
				</button>
			</div>
			<div style="float:right">
				<button id="rentPurposeRefreshBt" name="rentPurposeRefreshBt" class="btn btn-azure" onClick="javascript:loadContent('/app/rentPurpose/list','page-content-holder');">
					<i class="glyph-icon icon-refresh"></i>&nbsp;<spring:message code="rentPurposeList-form.refresh-button"/>
				</button>
			</div>
        </div>
        <%@include file="/WEB-INF/jsp/common/paginatedNavBar.jsp" %>
		<div id="rentPurposeFormDiv" class="example-box-wrapper">
			<table class="table table-striped table-bordered" id="rentPurposeListTable" name="rentPurposeListTable">
				<thead>
					<tr>
					<c:if test="${not empty data.paginatedResponse.resultList}">
						<th><input type="checkbox" id="rentPurposeListTableHeaderCheckbox" class="custom-checkbox" onclick="checkAllTableCheckbox(this, 'rentPurposeListTable')"></th>
						</c:if>
						<th><spring:message code="rentPurposeList-form.Name-label"/></th>
						<th><spring:message code="rentPurposeList-form.State-label"/></th>
						<th><spring:message code="rentPurposeList-form.Description-label"/></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${not empty data.paginatedResponse.resultList}">
						<c:forEach var="obj" items="${data.paginatedResponse.resultList}">
							<tr>
								<td align="center">
									<input type="checkbox" id="${obj.id}" class="custom-checkbox">
								</td>
								<td>${obj.name}</td>
								<td><spring:message code="StateType.${obj.state}" /></td>
								<td>${obj.descr}</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>		
	</div>
</div>
