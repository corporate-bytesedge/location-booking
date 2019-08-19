<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<script>
function lockProperty(tableId) {
		var checkList = "";
		var tbl = document.getElementById(tableId)
		if(tbl != null) {
			for (var bxs = tbl.getElementsByTagName("input"), j = bxs.length; j--;) {
				if (bxs[j].type == "checkbox" && bxs[j].checked) {
					if(bxs[j].id.valueOf() != 'propertyListTableHeaderCheckbox') {
						checkList = checkList + bxs[j].id + ",";
					}
				}
			}
		}
		if(checkList == "") {
			alert('Please select at least one Property to perform Lock operation');
		} else {
			getUrl('/app/property/lock?idCsv=' + checkList, 'page-content-holder');
		}
	};
	
</script>
<style>
	.pagination{
 margin-top: 15px;
}
</style>

<div id="page-content">
	<div id="listPropertyDiv" class="example-box-wrapper">
		<h3>
			<spring:message code="propertyList-form.property-label" />
				<span class="badge bg-red">${data.objCount}</span>
		</h3>
		<div class="divider"></div>
			<c:if test="${not empty data.message_success}">
				<div id="propertyActionSuccessMsgDiv" class="example-box-wrapper">
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
				        	fadeOutDiv('propertyActionSuccessMsgDiv');
						</script>
				</div>
			</c:if>
			<c:if test="${not empty data.message_error}">
				<div id="propertyActionFailedMsgDiv" class="example-box-wrapper">
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
				        	fadeOutDiv('propertyActionFailedMsgDiv');
						</script>
				</div>
			</c:if>
    	<div id="propertyButtonDiv" style="margin-bottom:60px;">
			<div style="float:left;">
				<button id="propertyCreateBt" name="propertyCreateBt" class="btn btn-success" onClick="javascript:createObject('propertyListTable', 'propertyListTableHeaderCheckbox', 'Property', '/app/property/create/form', 'page-content-holder');">
					<i class="glyph-icon icon-plus-square"></i>&nbsp;
					<spring:message code="propertyList-form.create-button" />
				</button>
				<button id="propertyViewBt" name="propertyViewBt" class="btn btn-purple" onClick="javascript:viewObject('propertyListTable', 'propertyListTableHeaderCheckbox', 'Property', '/app/property/view/form?reqPage=${data.paginatedResponse.reqPage}&pageSize=${data.paginatedResponse.pageSize}&id=', 'page-content-holder');">
					<i class="glyph-icon icon-file"></i>&nbsp;<spring:message code="propertyList-form.view-button"/>
					</button>
				<button id="propertyUpdateBt" name="propertyeUpdateBt" class="btn btn-info" onClick="javascript:updateObject('propertyListTable', 'propertyListTableHeaderCheckbox', 'Property', '/app/property/update/form?reqPage=${data.paginatedResponse.reqPage}&pageSize=${data.paginatedResponse.pageSize}&id=', 'page-content-holder');">
					<i class="glyph-icon icon-pencil-square"></i>&nbsp;<spring:message code="propertyList-form.update-button"/>
					</button>
				<button id="propertyExportBt" name="propertyExportBt" class="btn btn-primary" onClick="window.location='/app/property/list/export?reqPage=${data.paginatedResponse.reqPage}'">
					<i class="glyph-icon icon-arrow-down"></i>&nbsp;<spring:message code="propertyList-form.export-button"/>
				</button>
			</div>
			<div style="float:right">
				<button id="propertyRefreshBt" name="propertyRefreshBt" class="btn btn-azure" onClick="javascript:loadContent('/app/property/list','page-content-holder');">
					<i class="glyph-icon icon-refresh"></i>&nbsp;<spring:message code="propertyList-form.refresh-button"/>
				</button>
			</div>
        </div>
        <%@include file="/WEB-INF/jsp/common/paginatedNavBar.jsp" %>
		<div id="propertyFormDiv" class="example-box-wrapper">
			<table class="table table-striped table-bordered" id="propertyListTable" name="propertyListTable">
				<thead>
					<tr>
					<c:if test="${not empty data.paginatedResponse.resultList}">
						<th><input type="checkbox" id="propertyListTableHeaderCheckbox" class="custom-checkbox" onclick="checkAllTableCheckbox(this, 'propertyListTable')"></th>
						</c:if>
						<%-- <th><spring:message code="propertyList-form.PropertyId-label"/></th>
						<th><spring:message code="propertyList-form.UniqueId-label"/></th> --%>
						<th><spring:message code="propertyList-form.Name-label"/></th>
						<th><spring:message code="propertyList-form.State-label"/></th>
						<th><spring:message code="propertyList-form.Description-label"/></th>
						<th><spring:message code="propertyList-form.DaysLimit-label"/></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${not empty data.paginatedResponse.resultList}">
						<c:forEach var="obj" items="${data.paginatedResponse.resultList}">
							<tr>
								<td align="center">
									<input type="checkbox" id="${obj.id}" class="custom-checkbox">
								</td>
								<%-- <td>${obj.propertyId}</td>
								<td>${obj.uniqueId}</td> --%>
								<td>${obj.name}</td>
								<td><spring:message code="StateType.${obj.state}" /></td>
								<td>${obj.descr}</td>
								<td>${obj.venueDaysLimit}</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>		
	</div>
</div>
