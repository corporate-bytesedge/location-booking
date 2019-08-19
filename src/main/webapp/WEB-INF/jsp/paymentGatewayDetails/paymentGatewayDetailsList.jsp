<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<style>
	.pagination{
 margin-top: 15px;
}
</style>

<div id="page-content">
	<div id="listPaymentGatewayDetailsDiv" class="example-box-wrapper">
		<h3>
			<spring:message code="paymentGatewayDetailsList-form.paymentGatewayDetails-label" />
				<span class="badge bg-red">${data.objCount}</span>
		</h3>
		<div class="divider"></div>
			<c:if test="${not empty data.message_success}">
				<div id="paymentGatewayDetailsActionSuccessMsgDiv" class="example-box-wrapper">
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
				        	fadeOutDiv('paymentGatewayDetailsActionSuccessMsgDiv');
						</script>
				</div>
			</c:if>
			<c:if test="${not empty data.message_error}">
				<div id="paymentGatewayDetailsActionFailedMsgDiv" class="example-box-wrapper">
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
				        	fadeOutDiv('paymentGatewayDetailsActionFailedMsgDiv');
						</script>
				</div>
			</c:if>
    	<div id="paymentGatewayDetailsButtonDiv" style="margin-bottom:60px;">
			<div style="float:left;">
				<button id="paymentGatewayDetailsCreateBt" name="paymentGatewayDetailsCreateBt" class="btn btn-success" onClick="javascript:createObject('paymentGatewayDetailsListTable', 'paymentGatewayDetailsListTableHeaderCheckbox', 'PaymentGatewayDetails', '/app/paymentGatewayDetails/create/form', 'page-content-holder');">
					<i class="glyph-icon icon-plus-square"></i>&nbsp;
					<spring:message code="paymentGatewayDetailsList-form.create-button" />
				</button>
				<button id="paymentGatewayDetailsViewBt" name="paymentGatewayDetailsViewBt" class="btn btn-purple" onClick="javascript:viewObject('paymentGatewayDetailsListTable', 'paymentGatewayDetailsListTableHeaderCheckbox', 'PaymentGatewayDetails', '/app/paymentGatewayDetails/view/form?reqPage=${data.paginatedResponse.reqPage}&pageSize=${data.paginatedResponse.pageSize}&id=', 'page-content-holder');">
					<i class="glyph-icon icon-file"></i>&nbsp;<spring:message code="paymentGatewayDetailsList-form.view-button"/>
					</button>
				<button id="paymentGatewayDetailsUpdateBt" name="paymentGatewayDetailseUpdateBt" class="btn btn-info" onClick="javascript:updateObject('paymentGatewayDetailsListTable', 'paymentGatewayDetailsListTableHeaderCheckbox', 'PaymentGatewayDetails', '/app/paymentGatewayDetails/update/form?reqPage=${data.paginatedResponse.reqPage}&pageSize=${data.paginatedResponse.pageSize}&id=', 'page-content-holder');">
					<i class="glyph-icon icon-pencil-square"></i>&nbsp;<spring:message code="paymentGatewayDetailsList-form.update-button"/>
					</button>
				<button id="paymentGatewayDetailsExportBt" name="paymentGatewayDetailsExportBt" class="btn btn-primary" onClick="window.location='/app/paymentGatewayDetails/list/export?reqPage=${data.paginatedResponse.reqPage}'">
					<i class="glyph-icon icon-arrow-down"></i>&nbsp;<spring:message code="paymentGatewayDetailsList-form.export-button"/>
				</button>
			</div>
			<div style="float:right">
				<button id="paymentGatewayDetailsRefreshBt" name="paymentGatewayDetailsRefreshBt" class="btn btn-azure" onClick="javascript:loadContent('/app/paymentGatewayDetails/list','page-content-holder');">
					<i class="glyph-icon icon-refresh"></i>&nbsp;<spring:message code="paymentGatewayDetailsList-form.refresh-button"/>
				</button>
			</div>
        </div>
        <%@include file="/WEB-INF/jsp/common/paginatedNavBar.jsp" %>
		<div id="paymentGatewayDetailsFormDiv" class="example-box-wrapper">
			<table class="table table-striped table-bordered" id="paymentGatewayDetailsListTable" name="paymentGatewayDetailsListTable">
				<thead>
					<tr>
					<c:if test="${not empty data.paginatedResponse.resultList}">
						<th><input type="checkbox" id="paymentGatewayDetailsListTableHeaderCheckbox" class="custom-checkbox" onclick="checkAllTableCheckbox(this, 'paymentGatewayDetailsListTable')"></th>
						</c:if>
						<th><spring:message code="paymentGatewayDetailsList-form.Login-label"/></th>
						<th><spring:message code="paymentGatewayDetailsList-form.Password-label"/></th>
						<th><spring:message code="paymentGatewayDetailsList-form.State-label"/></th>
						<th><spring:message code="paymentGatewayDetailsList-form.Currency-label"/></th>
						<th><spring:message code="paymentGatewayDetailsList-form.ReqHashKey-label"/></th>
						<th><spring:message code="paymentGatewayDetailsList-form.ResHashKey-label"/></th>
						<th><spring:message code="paymentGatewayDetailsList-form.Ttype-label"/></th>
						<th><spring:message code="paymentGatewayDetailsList-form.Prodid-label"/></th>
						<th><spring:message code="paymentGatewayDetailsList-form.MerUrl-label"/></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${not empty data.paginatedResponse.resultList}">
						<c:forEach var="obj" items="${data.paginatedResponse.resultList}">
							<tr>
								<td align="center">
									<input type="checkbox" id="${obj.id}" class="custom-checkbox">
								</td>
								<td>${obj.login}</td>
								<td>${obj.password}</td>
								<td><spring:message code="StateType.${obj.status}" /></td>
								<td>${obj.txnCurr}</td>
								<td>${obj.reqHashKey}</td>
								<td>${obj.resHashKey}</td>
								<td>${obj.ttype}</td>
								<td>${obj.prodId}</td>
								<td>${obj.merchantUrl}</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>		
	</div>
</div>
