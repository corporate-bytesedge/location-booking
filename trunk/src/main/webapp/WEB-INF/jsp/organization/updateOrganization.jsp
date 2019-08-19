<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<div id="page-content">
	<div class="example-box-wrapper">
		<div id="updateOrganizationDiv" class="example-box-wrapper">
			<div style="float: left">
				<h3>
					<spring:message
						code="updateOrganization-form.updateOrganization-label" />
				</h3>
			</div>
			<div style="float: right">
				<button id="organizationUpdateBackBt"
					name="organizationUpdateBackBt" type="button" class="btn btn-info"
					onClick="javascript:getUrl('/app/organization/list?reqPage=${reqPage}&pageSize=${pageSize}','page-content-holder');">
					<i class="glyph-icon icon-minus-square"></i>&nbsp;
					<spring:message code="updateOrganization-form.back-button" />
				</button>
			</div>
		</div>
		<div class="divider"></div>
		<form:form id="updateOrganizationForm" name="updateOrganizationForm"
			class="form-horizontal" data-parsley-validate=""
			action="javascript:submitForm('/app/organization/update', 'updateOrganizationForm', 'page-content-holder')"
			method='POST' modelAttribute="organizationForm">
			<input type="hidden" path="<c:out value="${_csrf.parameterName}"/>"
				name="<c:out value="${_csrf.parameterName}"/>"
				value="<c:out value="${_csrf.token}"/>" />
			<div id="updateOrganizationNameDiv" class="form-group">
				<label for="" class="col-sm-2 control-label"> <spring:message
						code="updateOrganization-form.name-label" /> <span
					class="required">*</span>
				</label>
				<div id="updateOrganizationNameDiv" class="col-sm-8">
					<form:input class="form-control" type="text" path="name"
						maxlength="32" required="true" />
					<form:errors path="name" class="parsley-required" />
				</div>
			</div>
			<div id="updateOraganizationPhoneNumberDiv" class="form-group">
				<label for="" class="col-sm-2 control-label"> <spring:message
						code="updateOrganization-form.phoneNumber-label" /> <span
					class="required">*</span>
				</label>
				<div class="col-sm-8">
					<form:input type="number" class="input-mask form-control"
						onkeypress="return AllowOnlyNumbers(event);" path="phoneNumber"
						maxlength="11" required="true" />
					<form:errors path="phoneNumber" class="parsley-required" />
				</div>
			</div>
			<div id="updateOrganizationMobileNumberDiv" class="form-group">
				<label for="" class="col-sm-2 control-label"> <spring:message
						code="updateOrganization-form.mobileNumber-label" /> <span
					class="required">*</span>
				</label>
				<div class="col-sm-8">
					<form:input type="number" class="input-mask form-control"
						onkeypress="return AllowOnlyNumbers(event);" path="mobileNumber"
						maxlength="10" required="true" />
					<form:errors path="mobileNumber" class="parsley-required" />
				</div>
			</div>
			<div id="updateOrganizationEmailDiv" class="form-group">
				<label for="" class="col-sm-2 control-label"> <spring:message
						code="updateOrganization-form.emailId-label" /> <span
					class="required">*</span>
				</label>
				<div id="updateOrganizationEmailIdDiv" class="col-sm-8">
					<form:input class="form-control" type="text" path="emailId"
						maxlength="24" required="true" />
					<form:errors path="emailId" class="parsley-required" />
				</div>
			</div>
			<div id="updateOrganizationCapthaDiv" class="form-group">
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
				<div id="organizationcapthaDiv" class="col-sm-2">
					<span class="input-group-addon addon-inside bg-white font-primary"
						style="margin-left: 15px;"> <i
						class="glyph-icon icon-linecons-key"></i>
					</span> <input type="text" class="form-control" id="captchaText"
						name="captchaText" placeholder="Captcha" required>
					<form:errors path="captchaText" class="parsley-required" />
				</div>
			</div>
			<div class="divider"></div>
			<div id="updateOrganizationButtonsDiv" class="form-group">
				<div class="col-sm-offset-2 col-sm-8">
					<div class="example-box-wrapper">
						<button id="organizationUpdateSubmitBt"
							name="organizationUpdateSubmitBt" type="submit"
							class="btn btn-success">
							<i class="glyph-icon icon-plus-square"></i>&nbsp;
							<spring:message code="updateOrganization-form.submit-button" />
						</button>
						<button id="organizationUpdateRestBt"
							name="organizationUpdateResetBt" type="reset"
							class="btn btn-warning">
							<i class="glyph-icon icon-minus-square"></i>&nbsp;
							<spring:message code="updateOrganization-form.reset-button" />
						</button>
						<button id="organizationUpdateBackBt"
							name="organizationUpdateBackBt" type="button"
							class="btn btn-info"
							onClick="javascript:getUrl('/app/organization/list?reqPage=${reqPage}&pageSize=${pageSize}','page-content-holder');">
							<i class="glyph-icon icon-minus-square"></i>&nbsp;
							<spring:message code="updateOrganization-form.back-button" />
						</button>
					</div>
				</div>
			</div>
		</form:form>
	</div>
</div>
</div>