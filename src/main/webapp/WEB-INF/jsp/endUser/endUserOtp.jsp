<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<style>
 .wrapper-div{
	    
}

.main-footer {
	    margin-top: 135px;
    display: inline-block;
        width: 100%;

}
.otp-mar{
	margin-bottom:30px;
}
.text-centrCls {
	text-align: center;
    margin-bottom: 85px;
}
#createEndUserOtpBackBt {
	margin: 0 20px 20px 0;
}
.float-logo {
	float: left;
	margin-left: 30px;
}

.float-btn {
	float: right;
	margin-right:30px;
}
.footer-pane {
    margin-top: 65px;
    }


</style>
	<div id="createEndUserOtpDiv" class="example-box-wrapper wrapper-div">
		<div class="text-centrCls">
			<h3>
				<spring:message code="createEndUserOtp-form.createEndUserOtp-label" />
			</h3>
		</div>
		<%-- <div style="float: right;">
			<button id="createEndUserOtpBackBt" name="createEndUserOtpBackBt"
				class="btn btn-info"
				onClick="javascript:history.go()">
				<i class="glyph-icon icon-minus-square"></i>&nbsp;
				<spring:message code="createEndUserOtp-form.back-button" />
			</button>
		</div> --%>
	</div>
	<div style="float: center">
	<form:form id="endUserOtp" class="form-horizontal"
		data-parsley-validate=""
		action="javascript:submitForm('/apu/endUser/otp', 'endUserOtp', 'page-content-holder')"
		method='POST' modelAttribute="endUserForm">
		<form:input class="form-control" type="hidden" placeholder="Enter OTP"
					path="userName" maxlength="64" required="true" />
		<div id="createEndUserOtpMobileNumberDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="createEndUserOtp-form.mobileNumber-label" /> <span
				class="required">*</span>
			</label>
			<div id="createEndUserOtpMobileNumberDiv" class="col-sm-8">
				<form:input type="text" class=" input-mask form-control"
					placeholder="Enter 10 digit mobile number"
					onkeypress="return AllowOnlyNumbers(event);" path="mobileNumber"
					maxlength="10" required="true" readOnly="true"
					data-inputmask="&apos;mask&apos;:&apos;9999999999&apos;" />
				<form:errors path="mobileNumber" class="parsley-required" />
			</div>
		</div>
		<div id="createEndUserOtpOTPDiv" class="form-group otp-mar">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="createEndUserOtp-form.OTP-label" /> <span class="required">*</span>
			</label>
			<div id="createEndUserOtpOTPDiv" class="col-sm-8">
				<form:input class="form-control" type="text" placeholder="Enter OTP"
					path="password" maxlength="6" required="true" />
				<form:errors path="password" class="parsley-required" />
			</div>
		</div>
		<%-- <div id="createEndUserOtpCapthaDiv" class="form-group">
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
			<div id="createEndUserOtpCapthaDiv" class="col-sm-2">
				<span class="input-group-addon addon-inside bg-white font-primary"
					style="margin-left: 15px;"> <i
					class="glyph-icon icon-linecons-key"></i>
				</span>
				<form:input type="text" class="form-control" path="captchaText"
					maxlength="6" placeholder="Captcha" required="true" />
				<form:errors path="captchaText" class="parsley-required" />
			</div>
		</div> --%>
		<div class="divider"></div>
		<div id="createEndUserOtpButtonsDiv" class="form-group">
			<div class="col-sm-offset-2 col-sm-8">
				<div class="example-box-wrapper">
					<button id="endUserOtpCreateSubmitBt"
						name="endUserOtpCreateSubmitBt" type="submit"
						class="btn btn-success">
						<i class="glyph-icon icon-plus-square"></i>&nbsp;
						<spring:message code="createEndUserOtp-form.submit-button" />
					</button>
					<%-- <button id="endUserOtpCreateResetBt" name="endUserOtpCreateResetBt"
						type="reset" class="btn btn-warning">
						<i class="glyph-icon icon-minus-square"></i>&nbsp;
						<spring:message code="createEndUserOtp-form.reset-button" />
					</button> --%>
					<%-- <button id="endUserOtpReSendBt" name="endUserOtpReSendBt"
						type="button" class="btn btn-light"
						onClick="javascript:callUrl('/apu/endUser/reSendOtp?userName=${endUserForm.userName}')">
						<i class="glyph-icon icon-plus-square"></i>&nbsp;
						<spring:message code="createEndUserOtp-form.resend-button" />
					</button>	 --%>
				</div>
			</div>
		</div>
	</form:form>
</div>
