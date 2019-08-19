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
	<div class="example-box-wrapper">
		<div id="updatePaymentGatewayDetailsDiv" class="example-box-wrapper">
			<div style="float: left">
				<h3>
					<spring:message
						code="updatePaymentGatewayDetails-form.updatePaymentGatewayDetails-label" />
				</h3>
			</div>
			<div style="float: right">
				<button id="paymentGatewayDetailsUpdateBackBt"
					name="paymentGatewayDetailsUpdateBackBt" type="button"
					class="btn btn-info"
					onClick="javascript:getUrl('/app/paymentGatewayDetails/list?reqPage=${reqPage}&pageSize=${pageSize}','page-content-holder');">
					<i class="glyph-icon icon-minus-square"></i>&nbsp;
					<spring:message code="updatePaymentGatewayDetails-form.back-button" />
				</button>
			</div>
		</div>
		<div class="divider"></div>
		<form:form id="updatePaymentGatewayDetailsForm"
			name="updatePaymentGatewayDetailsForm" class="form-horizontal"
			data-parsley-validate=""
			action="javascript:submitForm('/app/paymentGatewayDetails/update?reqPage=${reqPage}&pageSize=${pageSize}', 'updatePaymentGatewayDetailsForm', 'page-content-holder')"
			method='POST' modelAttribute="paymentGatewayDetailsForm">
			<input type="hidden" path="<c:out value="${_csrf.parameterName}"/>"
				name="<c:out value="${_csrf.parameterName}"/>"
				value="<c:out value="${_csrf.token}"/>" />
			<div id="updatePaymentGatewayDetailsStateDiv" class="form-group">
				<label for="" class="col-sm-2 control-label"> <spring:message
						code="updatePaymentGatewayDetails-form.state-label" /> <span
					class="required">*</span>
				</label>
				<div id="updatePaymentGatewayDetailsGenderDiv" class="col-sm-8">
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
			<div id="updatePaymentGatewayDetailsLoginDiv" class="form-group">
				<label for="" class="col-sm-2 control-label"> <spring:message
						code="updatePaymentGatewayDetails-form.login-label" /> <span
					class="required">*</span>
				</label>
				<div id="updatePaymentGatewayDetailsLoginDiv" class="col-sm-8">
					<form:input class="form-control" type="text"
						placeholder="Enter login credentials" path="login" maxlength="16"
						required="true" />
					<form:errors path="login" class="parsley-required" />
				</div>
			</div>
			<div id="updatePaymentGatewayDetailsPasswordDiv" class="form-group">
				<label for="" class="col-sm-2 control-label"> <spring:message
						code="updatePaymentGatewayDetails-form.password-label" /> <span
					class="required">*</span>
				</label>
				<div id="updatePaymentGatewayDetailsPasswordDiv" class="col-sm-8">
					<form:input class="form-control" type="text"
						placeholder="Enter login credentials" path="password"
						maxlength="128" required="true" />
					<form:errors path="password" class="parsley-required" />
				</div>
			</div>
			<div id="updatePaymentGatewayDetailsPaymentGatewayDetailsCurrencyDiv"
				class="form-group">
				<label for="" class="col-sm-2 control-label"> <spring:message
						code="updatePaymentGatewayDetails-form.currency-label" /> <span
					class="required">*</span>
				</label>
				<div
					id="updatePaymentGatewayDetailsPaymentGatewayDetailsCurrencyDiv"
					class="col-sm-8">
					<form:input class="form-control" type="text"
						placeholder="Enter currency" path="txnCurr" maxlength="16"
						required="true" />
					<form:errors path="txnCurr" class="parsley-required" />
				</div>
			</div>
			<div id="updatePaymentGatewayDetailsReqHKDiv" class="form-group">
				<label for="" class="col-sm-2 control-label"> <spring:message
						code="updatePaymentGatewayDetails-form.reqHashKey-label" /> <span
					class="required">*</span>
				</label>
				<div id="updatePaymentGatewayDetailsReqHKDiv" class="col-sm-8">
					<form:input class="form-control" type="text"
						placeholder="Enter Required hash key" path="reqHashKey"
						maxlength="16" required="true" />
					<form:errors path="reqHashKey" class="parsley-required" />
				</div>
			</div>
			<div id="updatePaymentGatewayDetailsResHKDiv" class="form-group">
				<label for="" class="col-sm-2 control-label"> <spring:message
						code="updatePaymentGatewayDetails-form.resHashKey-label" /> <span
					class="required">*</span>
				</label>
				<div id="updatePaymentGatewayDetailsResHKDiv" class="col-sm-8">
					<form:input class="form-control" type="text"
						placeholder="Enter Response hash key" path="resHashKey"
						maxlength="16" required="true" />
					<form:errors path="resHashKey" class="parsley-required" />
				</div>
			</div>
			<div id="updatePaymentGatewayDetailsTtypeDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="updatePaymentGatewayDetails-form.ttype-label" /> <span
				class="required">*</span>
			</label>
			<div id="updatePaymentGatewayDetailsResHKDiv" class="col-sm-8">
				<form:input class="form-control" type="text"
					placeholder="Enter ttype" path="ttype"
					maxlength="64" required="true" />
				<form:errors path="ttype" class="parsley-required" />
			</div>
		</div>
		<div id="updatePaymentGatewayDetailsProdidDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="updatePaymentGatewayDetails-form.prodid-label" /> <span
				class="required">*</span>
			</label>
			<div id="updatePaymentGatewayDetailsProdidDiv" class="col-sm-8">
				<form:input class="form-control" type="text"
					placeholder="Enter prodid" path="prodId"
					maxlength="32" required="true" />
				<form:errors path="prodId" class="parsley-required" />
			</div>
		</div>
		<div id="updatePaymentGatewayDetailsMerchantUrlDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="updatePaymentGatewayDetails-form.merUrl-label" /> <span
				class="required">*</span>
			</label>
			<div id="updatePaymentGatewayDetailsMerchantUrlDiv" class="col-sm-8">
				<form:input class="form-control" type="text"
					placeholder="Enter url" path="merchantUrl"
					maxlength="64" required="true" />
				<form:errors path="merchantUrl" class="parsley-required" />
			</div>
		</div>
			<div id="updatePaymentGatewayDetailsCapthaDiv" class="form-group">
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
				<div id="updatePaymentGatewayDetailsCapthaDiv" class="col-sm-2">
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
			<div id="paymentGatewayDetailsButtonsDiv" class="form-group">
				<div class="col-sm-offset-2 col-sm-8">
					<div class="example-box-wrapper">
						<button id="paymentGatewayDetailsUpdateSubmitBt"
							name="paymentGatewayDetailsUpdateSubmitBt" type="submit"
							class="btn btn-success">
							<i class="glyph-icon icon-plus-square"></i>&nbsp;
							<spring:message
								code="updatePaymentGatewayDetails-form.submit-button" />
						</button>
						<button id="paymentGatewayDetailsUpdateResetBt"
							name="paymentGatewayDetailsUpdateResetBt" type="reset"
							class="btn btn-warning">
							<i class="glyph-icon icon-minus-square"></i>&nbsp;
							<spring:message
								code="updatePaymentGatewayDetails-form.reset-button" />
						</button>
						<button id="paymentGatewayDetailsUpdateBackBt"
							name="paymentGatewayDetailsUpdateBackBt" type="button"
							class="btn btn-info"
							onClick="javascript:getUrl('/app/paymentGatewayDetails/list?reqPage=${reqPage}&pageSize=${pageSize}','page-content-holder');">
							<i class="glyph-icon icon-minus-square"></i>&nbsp;
							<spring:message
								code="updatePaymentGatewayDetails-form.back-button" />
						</button>
					</div>
				</div>
			</div>
		</form:form>
	</div>
</div>
</div>