<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<style>
 .wrapper-div{
	    
}
.otp-mar{
	margin-bottom:30px;
}
.text-centrCls {
	text-align: center;
    margin-bottom: 20px;
}
#createEndUserOtpBackBt {
	margin: 0 20px 20px 0;
}
</style>
	<div id="updateForgotPasswordDiv" class="example-box-wrapper wrapper-div">
		<div class="text-centrCls">
			<h3>
				<spring:message code="updateForgotPassword-form.updateForgotPassword-label" />
			</h3>
		</div>
		<%-- <div style="float: right;">
			<button id="createEndUserOtpBackBt" name="createEndUserOtpBackBt"
				class="btn btn-info"
				onClick="javascript:history.go()">
				<i class="glyph-icon icon-minus-square"></i>&nbsp;
				<spring:message code="createEndUserOtp-form.back-button" />
			</button>
		</div>--%>
	</div>
	<div style="float: center">
	<form:form id="updateForgotPassword" class="form-horizontal"
		data-parsley-validate=""
		action="javascript:submitForm('/apu/forgotPasswd/update', 'updateForgotPassword', 'page-content-holder')"
		method='POST' modelAttribute="forgotPasswdForm">
		<%-- <form:input class="form-control" type="hidden" placeholder="Enter OTP"
					path="userName" maxlength="64" required="true" />--%>
		
		<div id="updateForgotPasswordEmailDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="updateForgotPassword-form.email-label" /> <span class="required">*</span>
			</label>
			<div id="updateForgotPasswordEmailDiv" class="col-sm-8 paddingt8">${forgotPasswdForm.email}</div>
		</div>
		<div id="updateForgotPasswordMobileNumberDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="updateForgotPassword-form.mobileNumber-label" /> <span
				class="required">*</span>
			</label>
			<div id="updateForgotPasswordEmailDiv" class="col-sm-8 paddingt8">${forgotPasswdForm.mobileNumber}</div>
		</div>
		<%-- <div id="createEndUserOtpOTPDiv" class="form-group otp-mar">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="createEndUserOtp-form.OTP-label" /> <span class="required">*</span>
			</label>
			<div id="createEndUserOtpOTPDiv" class="col-sm-8">
				<form:input class="form-control" type="text" placeholder="Enter OTP"
					path="password" maxlength="6" required="true" />
				<form:errors path="password" class="parsley-required" />
			</div>
		</div>--%>
		<div id="updateForgotPasswordPasswdDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="updateForgotPassword-form.password-label" /> <span class="required">*</span>
			</label>
			<div id="updateForgotPasswordPasswdDiv" class="col-sm-8">
				<form:input class="form-control" type="text"
					placeholder="Enter password" path="password" maxlength="64"
					required="true" />
				<form:errors path="password" class="parsley-required" />
			</div>
		</div>
		<div id="updateForgotPasswordCPasswdDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="updateForgotPassword-form.confirmPassword-label" /> <span class="required">*</span>
			</label>
			<div id="updateForgotPasswordCPasswdDiv" class="col-sm-8">
				<form:input class="form-control" type="text"
					placeholder="Enter confirm password" path="confirmPassword" maxlength="64"
					required="true" />
				<form:errors path="confirmPassword" class="parsley-required" />
			</div>
		</div>
		<div id="updateForgotPasswordCapthaDiv" class="form-group">
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
			<div id="updateForgotPasswordCapthaDiv" class="col-sm-2">
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
		<div id="updateForgotPasswordButtonsDiv" class="form-group">
			<div class="col-sm-offset-2 col-sm-8">
				<div class="example-box-wrapper">
					<button id="updateForgotPasswordCreateSubmitBt"
						name="updateForgotPasswordCreateSubmitBt" type="submit"
						class="btn btn-success">
						<i class="glyph-icon icon-plus-square"></i>&nbsp;
						<spring:message code="updateForgotPassword-form.submit-button" />
					</button>
					<%-- <button id="ForgotPasswdCreateResetBt" name="ForgotPasswdCreateResetBt"
						type="reset" class="btn btn-warning">
						<i class="glyph-icon icon-minus-square"></i>&nbsp;
						<spring:message code="ForgotPasswd-form.reset-button" />
					</button>
					<button id="ReSendBt" name="endUserOtpReSendBt"
						type="button" class="btn btn-light"
						onClick="javascript:callUrl('/apu/endUser/reSendOtp?userName=${endUserForm.userName}')">
						<i class="glyph-icon icon-plus-square"></i>&nbsp;
						<spring:message code="createEndUserOtp-form.resend-button" />
					</button>	--%>
				</div>
			</div>
		</div>
	</form:form>
</div>