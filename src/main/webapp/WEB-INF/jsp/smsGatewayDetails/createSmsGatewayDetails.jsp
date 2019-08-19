<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<div id="page-content">
	<c:if test="${not empty message_error}">
		<div id="smsGatewayDetailsActionFailedMsgDiv"
			class="example-box-wrapper">
			<div class="alert alert-danger">
				<div class="bg-red alert-icon">
					<i class="glyph-icon icon-check"></i>
				</div>
				<div class="alert-content">
					<h4 class="alert-title">Action Failed</h4>
					<p>${message_error}</p>
				</div>
			</div>
		</div>
	</c:if>
	<div id="createSmsGatewayDetailsDiv" class="example-box-wrapper">
		<div style="float: left">
			<h3>
				<spring:message
					code="createSmsGatewayDetails-form.createSmsGatewayDetails-label" />
			</h3>
		</div>
		<div style="float: right">
			<button id="createSmsGatewayDetailsBackBt"
				name="createSmsGatewayDetailsBackBt" class="btn btn-info"
				onClick="javascript:loadContent('/app/smsGatewayDetails/list','page-content-holder');">
				<i class="glyph-icon icon-minus-square"></i>&nbsp;
				<spring:message code="createSmsGatewayDetails-form.back-button" />
			</button>
		</div>
	</div>
	<div class="divider"></div>
	<form:form id="createSmsGatewayDetailsForm" class="form-horizontal"
		data-parsley-validate=""
		action="javascript:submitForm('/app/smsGatewayDetails/create', 'createSmsGatewayDetailsForm', 'page-content-holder')"
		method='POST' modelAttribute="smsGatewayDetailsForm">
		<div id="createSmsGatewayDetailsSenderIdDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="createSmsGatewayDetails-form.senderId-label" /> <span
				class="required">*</span>
			</label>
			<div id="createSmsGatewayDetailsSenderIdDiv" class="col-sm-8">
				<form:input class="form-control" type="text"
					placeholder="Enter Sender Id" path="senderId" maxlength="16"
					required="true" />
				<form:errors path="senderId" class="parsley-required" />
			</div>
		</div>
		<div id="createSmsGatewayDetailsRouteIdDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="createSmsGatewayDetails-form.route-label" /> <span
				class="required">*</span>
			</label>
			<div id="createSmsGatewayDetailsRouteIdDiv" class="col-sm-8">
				<form:input class="form-control" type="text"
					placeholder="Enter Route Id" path="route"
					maxlength="12" required="true" />
				<form:errors path="route" class="parsley-required" />
			</div>
		</div>
		<div class="divider"></div>
		<div id="createSmsGatewayDetailsButtonsDiv" class="form-group">
			<div class="col-sm-offset-2 col-sm-8">
				<div class="example-box-wrapper">
					<button id="smsGatewayDetailsCreateSubmitBt"
						name="smsGatewayDetailsCreateSubmitBt" type="submit"
						class="btn btn-success">
						<i class="glyph-icon icon-plus-square"></i>&nbsp;
						<spring:message
							code="createSmsGatewayDetails-form.submit-button" />
					</button>
					<button id="smsGatewayDetailsCreateRestBt"
						name="smsGatewayDetailsCreateResetBt" type="reset"
						class="btn btn-warning">
						<i class="glyph-icon icon-minus-square"></i>&nbsp;
						<spring:message
							code="createSmsGatewayDetails-form.reset-button" />
					</button>
					<button id="smsGatewayDetailsCreateBackBt"
						name="smsGatewayDetailsCreateBackBt" type="button"
						class="btn btn-info"
						onClick="javascript:getUrl('/app/smsGatewayDetails/list','page-content-holder');">
						<i class="glyph-icon icon-minus-square"></i>&nbsp;
						<spring:message
							code="createSmsGatewayDetails-form.back-button" />
					</button>
				</div>
			</div>
		</div>
	</form:form>
</div>
