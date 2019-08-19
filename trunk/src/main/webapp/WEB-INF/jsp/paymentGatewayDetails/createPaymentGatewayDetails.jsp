<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<script type="text/javascript"
	src="/assets-minified/widgets/input-mask/inputmask.js"></script>

<script type="text/javascript">
	/* Input masks */

	$(function() {
		"use strict";
		$(".input-mask").inputmask();
	});
</script>

<div id="page-content">
	<c:if test="${not empty message_error}">
		<div id="paymentGatewayDetailsActionFailedMsgDiv"
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
	<div id="createPaymentGatewayDetailsDiv" class="example-box-wrapper">
		<div style="float: left">
			<h3>
				<spring:message
					code="createPaymentGatewayDetails-form.createPaymentGatewayDetails-label" />
			</h3>
		</div>
		<div style="float: right">
			<button id="createPaymentGatewayDetailsBackBt"
				name="createPaymentGatewayDetailsBackBt" class="btn btn-info"
				onClick="javascript:loadContent('/app/paymentGatewayDetails/list','page-content-holder');">
				<i class="glyph-icon icon-minus-square"></i>&nbsp;
				<spring:message code="createPaymentGatewayDetails-form.back-button" />
			</button>
		</div>
	</div>
	<div class="divider"></div>
	<form:form id="createPaymentGatewayDetailsForm" class="form-horizontal"
		data-parsley-validate=""
		action="javascript:submitForm('/app/paymentGatewayDetails/create', 'createPaymentGatewayDetailsForm', 'page-content-holder')"
		method='POST' modelAttribute="paymentGatewayDetailsForm">
		<div id="createPaymentGatewayDetailsStateDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="createPaymentGatewayDetails-form.state-label" /> <span
				class="required">*</span>
			</label>
			<div id="createPaymentGatewayDetailsGenderDiv" class="col-sm-8">
				<form:select path="status" class="form-control" required="true">
					<c:forEach items="${StateType}" var="ev">
						<form:option value="${ev}">
							<spring:message code="StateType.${ev}" />
						</form:option>
					</c:forEach>
				</form:select>
				<form:errors path="status" class="parsley-required" />
			</div>
		</div>
		<div id="createPaymentGatewayDetailsLoginDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="createPaymentGatewayDetails-form.login-label" /> <span
				class="required">*</span>
			</label>
			<div id="createPaymentGatewayDetailsLoginDiv" class="col-sm-8">
				<form:input class="form-control" type="text"
					placeholder="Enter login credentials" path="login" maxlength="16"
					required="true" />
				<form:errors path="login" class="parsley-required" />
			</div>
		</div>
		<div id="createPaymentGatewayDetailsPasswordDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="createPaymentGatewayDetails-form.password-label" /> <span
				class="required">*</span>
			</label>
			<div id="createPaymentGatewayDetailsPasswordDiv" class="col-sm-8">
				<form:input class="form-control" type="text"
					placeholder="Enter login credentials" path="password"
					maxlength="128" required="true" />
				<form:errors path="password" class="parsley-required" />
			</div>
		</div>
		<div id="createPaymentGatewayDetailsPaymentGatewayDetailsCurrencyDiv"
			class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="createPaymentGatewayDetails-form.currency-label" /> <span
				class="required">*</span>
			</label>
			<div id="createPaymentGatewayDetailsPaymentGatewayDetailsCurrencyDiv"
				class="col-sm-8">
				<form:input class="form-control" type="text"
					placeholder="Enter currency" path="txnCurr" maxlength="16"
					required="true" />
				<form:errors path="txnCurr" class="parsley-required" />
			</div>
		</div>
		<div id="createPaymentGatewayDetailsReqHKDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="createPaymentGatewayDetails-form.reqHashKey-label" /> <span
				class="required">*</span>
			</label>
			<div id="createPaymentGatewayDetailsReqHKDiv" class="col-sm-8">
				<form:input class="form-control" type="text"
					placeholder="Enter Required hash key" path="reqHashKey"
					maxlength="16" required="true" />
				<form:errors path="reqHashKey" class="parsley-required" />
			</div>
		</div>
		<div id="createPaymentGatewayDetailsResHKDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="createPaymentGatewayDetails-form.resHashKey-label" /> <span
				class="required">*</span>
			</label>
			<div id="createPaymentGatewayDetailsResHKDiv" class="col-sm-8">
				<form:input class="form-control" type="text"
					placeholder="Enter Response hash key" path="resHashKey"
					maxlength="16" required="true" />
				<form:errors path="resHashKey" class="parsley-required" />
			</div>
		</div>
		<div id="createPaymentGatewayDetailsTtypeDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="createPaymentGatewayDetails-form.ttype-label" /> <span
				class="required">*</span>
			</label>
			<div id="createPaymentGatewayDetailsResHKDiv" class="col-sm-8">
				<form:input class="form-control" type="text"
					placeholder="Enter ttype" path="ttype"
					maxlength="64" required="true" />
				<form:errors path="ttype" class="parsley-required" />
			</div>
		</div>
		<div id="createPaymentGatewayDetailsProdidDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="createPaymentGatewayDetails-form.prodid-label" /> <span
				class="required">*</span>
			</label>
			<div id="createPaymentGatewayDetailsProdidDiv" class="col-sm-8">
				<form:input class="form-control" type="text"
					placeholder="Enter prodid" path="prodId"
					maxlength="32" required="true" />
				<form:errors path="prodId" class="parsley-required" />
			</div>
		</div>
		<div id="createPaymentGatewayDetailsMerchantUrlDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="createPaymentGatewayDetails-form.merUrl-label" /> <span
				class="required">*</span>
			</label>
			<div id="createPaymentGatewayDetailsMerchantUrlDiv" class="col-sm-8">
				<form:input class="form-control" type="text"
					placeholder="Enter url" path="merchantUrl"
					maxlength="64" required="true" />
				<form:errors path="merchantUrl" class="parsley-required" />
			</div>
		</div>
		<div id="createPaymentGatewayDetailsCapthaDiv" class="form-group">
			<div class="col-sm-2 control-label">
				<img id="newCaptha" alt="Loading"
					src="/apu/captcha.jpg?ts=${javaDate.time}%>" width="120"
					height="48" /> <span title="Refresh and try another"
					onClick="javascript:refreshCaptcha('newCaptha');"> <i
					class="glyph-icon icon-refresh"
					style="cursor: pointer; padding-bottom: 10px;"></i>
				</span>
			</div>
			<br>
			<div id="createPaymentGatewayDetailsCapthaDiv" class="col-sm-2">
				<span class="input-group-addon addon-inside bg-white font-primary"
					style="margin-left: 15px;"> <i
					class="glyph-icon icon-linecons-key"></i>
				</span>
				<form:input type="text" class="form-control" path="captchaText"
					maxlength="6" placeholder="Captcha" required="true" />
				<form:errors path="captchaText" class="parsley-required" />
			</div>
		</div>
		<div class="divider"></div>
		<div id="createPaymentGatewayDetailsButtonsDiv" class="form-group">
			<div class="col-sm-offset-2 col-sm-8">
				<div class="example-box-wrapper">
					<button id="paymentGatewayDetailsCreateSubmitBt"
						name="paymentGatewayDetailsCreateSubmitBt" type="submit"
						class="btn btn-success">
						<i class="glyph-icon icon-plus-square"></i>&nbsp;
						<spring:message
							code="createPaymentGatewayDetails-form.submit-button" />
					</button>
					<button id="paymentGatewayDetailsCreateRestBt"
						name="paymentGatewayDetailsCreateResetBt" type="reset"
						class="btn btn-warning">
						<i class="glyph-icon icon-minus-square"></i>&nbsp;
						<spring:message
							code="createPaymentGatewayDetails-form.reset-button" />
					</button>
					<button id="paymentGatewayDetailsCreateBackBt"
						name="paymentGatewayDetailsCreateBackBt" type="button"
						class="btn btn-info"
						onClick="javascript:getUrl('/app/paymentGatewayDetails/list','page-content-holder');">
						<i class="glyph-icon icon-minus-square"></i>&nbsp;
						<spring:message
							code="createPaymentGatewayDetails-form.back-button" />
					</button>
				</div>
			</div>
		</div>
	</form:form>
</div>
