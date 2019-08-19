<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<script type="text/javascript" src="/assets-minified/widgets/input-mask/inputmask.js"></script>
<div id="page-content">
	<div class="example-box-wrapper">
		<div id="updateSmsGatewayDetailsDiv" class="example-box-wrapper">
			<div style="float: left">
				<h3>
					<spring:message
						code="updateSmsGatewayDetails-form.updateSmsGatewayDetails-label" />
				</h3>
			</div>
			<div style="float: right">
				<button id="smsGatewayDetailsUpdateBackBt"
					name="smsGatewayDetailsUpdateBackBt" type="button"
					class="btn btn-info"
					onClick="javascript:getUrl('/app/smsGatewayDetails/list?reqPage=${reqPage}&pageSize=${pageSize}','page-content-holder');">
					<i class="glyph-icon icon-minus-square"></i>&nbsp;
					<spring:message code="updateSmsGatewayDetails-form.back-button" />
				</button>
			</div>
		</div>
		<div class="divider"></div>
		<form:form id="updateSmsGatewayDetailsForm"
			name="updateSmsGatewayDetailsForm" class="form-horizontal"
			data-parsley-validate=""
			action="javascript:submitForm('/app/smsGatewayDetails/update?reqPage=${reqPage}&pageSize=${pageSize}', 'updateSmsGatewayDetailsForm', 'page-content-holder')"
			method='POST' modelAttribute="smsGatewayDetailsForm">
			<input type="hidden" path="<c:out value="${_csrf.parameterName}"/>"
				name="<c:out value="${_csrf.parameterName}"/>"
				value="<c:out value="${_csrf.token}"/>" />

		<div id="updateSmsGatewayDetailsSenderIdDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="updateSmsGatewayDetails-form.senderId-label" /> <span
				class="required">*</span>
			</label>
			<div id="updateSmsGatewayDetailsSenderIdDiv" class="col-sm-8">
				<form:input class="form-control" type="text"
					placeholder="Enter Sender Id" path="senderId" maxlength="16"
					required="true" />
				<form:errors path="senderId" class="parsley-required" />
			</div>
		</div>
		<div id="updateSmsGatewayDetailsRouteIdDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="updateSmsGatewayDetails-form.route-label" /> <span
				class="required">*</span>
			</label>
			<div id="updateSmsGatewayDetailsRouteIdDiv" class="col-sm-8">
				<form:input class="form-control" type="text"
					placeholder="Enter Route Id" path="route"
					maxlength="12" required="true" />
				<form:errors path="route" class="parsley-required" />
			</div>
		</div>
			<div class="divider"></div>
			<div id="smsGatewayDetailsButtonsDiv" class="form-group">
				<div class="col-sm-offset-2 col-sm-8">
					<div class="example-box-wrapper">
						<button id="smsGatewayDetailsUpdateSubmitBt"
							name="smsGatewayDetailsUpdateSubmitBt" type="submit"
							class="btn btn-success">
							<i class="glyph-icon icon-plus-square"></i>&nbsp;
							<spring:message
								code="updateSmsGatewayDetails-form.submit-button" />
						</button>
						<button id="smsGatewayDetailsUpdateResetBt"
							name="smsGatewayDetailsUpdateResetBt" type="reset"
							class="btn btn-warning">
							<i class="glyph-icon icon-minus-square"></i>&nbsp;
							<spring:message
								code="updateSmsGatewayDetails-form.reset-button" />
						</button>
						<button id="smsGatewayDetailsUpdateBackBt"
							name="smsGatewayDetailsUpdateBackBt" type="button"
							class="btn btn-info"
							onClick="javascript:getUrl('/app/smsGatewayDetails/list?reqPage=${reqPage}&pageSize=${pageSize}','page-content-holder');">
							<i class="glyph-icon icon-minus-square"></i>&nbsp;
							<spring:message
								code="updateSmsGatewayDetails-form.back-button" />
						</button>
					</div>
				</div>
			</div>
		</form:form>
	</div>
</div>
</div>