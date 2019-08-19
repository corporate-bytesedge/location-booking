<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<style>
	.pagination{
 margin-top: 15px;
}
</style>

<div id="page-content">
	<div id="listSmsGatewayDetailsDiv" class="example-box-wrapper">
		<h3>
			<spring:message code="smsGatewayDetailsList-form.smsGatewayDetails-label" />
				<span class="badge bg-red">${data.objCount}</span>
		</h3>
		<div class="divider"></div>
			<c:if test="${not empty data.message_success}">
				<div id="smsGatewayDetailsActionSuccessMsgDiv" class="example-box-wrapper">
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
				        	fadeOutDiv('smsGatewayDetailsActionSuccessMsgDiv');
						</script>
				</div>
			</c:if>
			<c:if test="${not empty data.message_error}">
				<div id="smsGatewayDetailsActionFailedMsgDiv" class="example-box-wrapper">
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
				        	fadeOutDiv('smsGatewayDetailsActionFailedMsgDiv');
						</script>
				</div>
			</c:if>
    	<div id="smsGatewayDetailsButtonDiv" style="margin-bottom:60px;">
			<div style="float:left;">
				<button id="smsGatewayDetailsCreateBt" name="smsGatewayDetailsCreateBt" class="btn btn-success" onClick="javascript:createObject('smsGatewayDetailsListTable', 'smsGatewayDetailsListTableHeaderCheckbox', 'SmsGatewayDetails', '/app/smsGatewayDetails/create/form', 'page-content-holder');">
					<i class="glyph-icon icon-plus-square"></i>&nbsp;
					<spring:message code="smsGatewayDetailsList-form.create-button" />
				</button>
				<button id="smsGatewayDetailsViewBt" name="smsGatewayDetailsViewBt" class="btn btn-purple" onClick="javascript:viewObject('smsGatewayDetailsListTable', 'smsGatewayDetailsListTableHeaderCheckbox', 'SmsGatewayDetails', '/app/smsGatewayDetails/view/form?reqPage=${data.paginatedResponse.reqPage}&pageSize=${data.paginatedResponse.pageSize}&id=', 'page-content-holder');">
					<i class="glyph-icon icon-file"></i>&nbsp;<spring:message code="smsGatewayDetailsList-form.view-button"/>
					</button>
				<button id="smsGatewayDetailsUpdateBt" name="smsGatewayDetailseUpdateBt" class="btn btn-info" onClick="javascript:updateObject('smsGatewayDetailsListTable', 'smsGatewayDetailsListTableHeaderCheckbox', 'SmsGatewayDetails', '/app/smsGatewayDetails/update/form?reqPage=${data.paginatedResponse.reqPage}&pageSize=${data.paginatedResponse.pageSize}&id=', 'page-content-holder');">
					<i class="glyph-icon icon-pencil-square"></i>&nbsp;<spring:message code="smsGatewayDetailsList-form.update-button"/>
					</button>
				<button id="smsGatewayDetailsExportBt" name="smsGatewayDetailsExportBt" class="btn btn-primary" onClick="window.location='/app/smsGatewayDetails/list/export?reqPage=${data.paginatedResponse.reqPage}'">
					<i class="glyph-icon icon-arrow-down"></i>&nbsp;<spring:message code="smsGatewayDetailsList-form.export-button"/>
				</button>
			</div>
			<div style="float:right">
				<button id="smsGatewayDetailsRefreshBt" name="smsGatewayDetailsRefreshBt" class="btn btn-azure" onClick="javascript:loadContent('/app/smsGatewayDetails/list','page-content-holder');">
					<i class="glyph-icon icon-refresh"></i>&nbsp;<spring:message code="smsGatewayDetailsList-form.refresh-button"/>
				</button>
			</div>
        </div>
        <%@include file="/WEB-INF/jsp/common/paginatedNavBar.jsp" %>
		<div id="smsGatewayDetailsFormDiv" class="example-box-wrapper">
			<table class="table table-striped table-bordered" id="smsGatewayDetailsListTable" name="smsGatewayDetailsListTable">
				<thead>
					<tr>
					<c:if test="${not empty data.paginatedResponse.resultList}">
						<th><input type="checkbox" id="smsGatewayDetailsListTableHeaderCheckbox" class="custom-checkbox" onclick="checkAllTableCheckbox(this, 'smsGatewayDetailsListTable')"></th>
						</c:if>
						<th><spring:message code="smsGatewayDetailsList-form.senderId-label"/></th>
						<th><spring:message code="smsGatewayDetailsList-form.routeId-label"/></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${not empty data.paginatedResponse.resultList}">
						<c:forEach var="obj" items="${data.paginatedResponse.resultList}">
							<tr>
								<td align="center">
									<input type="checkbox" id="${obj.id}" class="custom-checkbox">
								</td>
								<td>${obj.senderId}</td>
								<td>${obj.route}</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>		
	</div>
</div>
