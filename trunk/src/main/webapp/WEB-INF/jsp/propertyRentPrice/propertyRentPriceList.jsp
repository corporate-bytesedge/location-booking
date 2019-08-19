<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<style>
	.pagination{
 margin-top: 15px;
}
</style>

<div id="page-content">
	<div id="listPropertyRentPriceDiv" class="example-box-wrapper">
		<h3>
			<spring:message code="propertyRentPriceList-form.propertyRentPrice-label" />
				<span class="badge bg-red">${data.objCount}</span>
		</h3>
		<div class="divider"></div>
			<c:if test="${not empty data.message_success}">
				<div id="propertyRentPriceActionSuccessMsgDiv" class="example-box-wrapper">
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
				        	fadeOutDiv('propertyRentPriceActionSuccessMsgDiv');
						</script>
				</div>
			</c:if>
			<c:if test="${not empty data.message_error}">
				<div id="propertyRentPriceActionFailedMsgDiv" class="example-box-wrapper">
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
				        	fadeOutDiv('propertyRentPriceActionFailedMsgDiv');
						</script>
				</div>
			</c:if>
    	<div id="propertyRentPriceButtonDiv" style="margin-bottom:60px;">
			<div style="float:left;">
				<button id="propertyRentPriceCreateBt" name="propertyRentPriceCreateBt" class="btn btn-success" onClick="javascript:createObject('propertyRentPriceListTable', 'propertyRentPriceListTableHeaderCheckbox', 'PropertyRentPrice', '/app/propertyRentPrice/create/form', 'page-content-holder');">
					<i class="glyph-icon icon-plus-square"></i>&nbsp;
					<spring:message code="propertyRentPriceList-form.create-button" />
				</button>
				<button id="propertyRentPriceViewBt" name="propertyRentPriceViewBt" class="btn btn-purple" onClick="javascript:viewObject('propertyRentPriceListTable', 'propertyRentPriceListTableHeaderCheckbox', 'PropertyRentPrice', '/app/propertyRentPrice/view/form?reqPage=${data.paginatedResponse.reqPage}&pageSize=${data.paginatedResponse.pageSize}&id=', 'page-content-holder');">
					<i class="glyph-icon icon-file"></i>&nbsp;<spring:message code="propertyRentPriceList-form.view-button"/>
					</button>
					<button id="propertyRentPriceUpdateBt" name="propertyRentPriceeUpdateBt" class="btn btn-info" onClick="javascript:updateObject('propertyRentPriceListTable', 'propertyRentPriceListTableHeaderCheckbox', 'PaymentGatewayDetails', '/app/propertyRentPrice/update/form?reqPage=${data.paginatedResponse.reqPage}&pageSize=${data.paginatedResponse.pageSize}&id=', 'page-content-holder');">
					<i class="glyph-icon icon-pencil-square"></i>&nbsp;<spring:message code="propertyRentPriceList-form.update-button"/>
					</button>
				<button id="propertyRentPriceExportBt" name="propertyRentPriceExportBt" class="btn btn-primary" onClick="window.location='/app/propertyRentPrice/list/export?reqPage=${data.paginatedResponse.reqPage}'">
					<i class="glyph-icon icon-arrow-down"></i>&nbsp;<spring:message code="propertyRentPriceList-form.export-button"/>
				</button>
			</div>
			<div style="float:right">
				<button id="propertyRentPriceRefreshBt" name="propertyRentPriceRefreshBt" class="btn btn-azure" onClick="javascript:loadContent('/app/propertyRentPrice/list','page-content-holder');">
					<i class="glyph-icon icon-refresh"></i>&nbsp;<spring:message code="propertyRentPriceList-form.refresh-button"/>
				</button>
			</div>
        </div>
        <%@include file="/WEB-INF/jsp/common/paginatedNavBar.jsp" %>
		<div id="propertyRentPriceFormDiv" class="example-box-wrapper">
			<table class="table table-striped table-bordered" id="propertyRentPriceListTable" name="propertyRentPriceListTable">
				<thead>
					<tr>
					<c:if test="${not empty data.paginatedResponse.resultList}">
						<th><input type="checkbox" id="propertyRentPriceListTableHeaderCheckbox" class="custom-checkbox" onclick="checkAllTableCheckbox(this, 'propertyRentPriceListTable')"></th>
						</c:if>
						<th><spring:message code="propertyRentPriceList-form.slotType-label"/></th>
						<th><spring:message code="propertyRentPriceList-form.price-label"/></th>
						<th><spring:message code="propertyRentPriceList-form.State-label"/></th>
						<th><spring:message code="propertyRentPriceList-form.propertyRentPriceId-label"/></th>
						<th><spring:message code="propertyRentPriceList-form.purposeId-label"/></th>
						<th><spring:message code="propertyRentPriceList-form.cgst-label"/></th>
						<th><spring:message code="propertyRentPriceList-form.sgst-label"/></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${not empty data.paginatedResponse.resultList}">
						<c:forEach var="obj" items="${data.paginatedResponse.resultList}">
							<tr>
								<td align="center">
									<input type="checkbox" id="${obj.id}" class="custom-checkbox">
								</td>
								<td><spring:message code="SlotType.${obj.slotType}" /></td>
								<td>${obj.price}</td>
								<td><spring:message code="StateType.${obj.state}" /></td>
								<td>${obj.propertyName}</td>
								<td>${obj.purposeName}</td>
								<td>${obj.cgst}</td>
								<td>${obj.sgst}</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>		
	</div>
</div>
