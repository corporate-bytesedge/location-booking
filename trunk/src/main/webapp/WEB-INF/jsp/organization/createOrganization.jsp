<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<div id="page-content">
<c:if test="${not empty message_error}">
				<div id="organizationActionFailedMsgDiv" class="example-box-wrapper">
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
	<div id="createOrganizationDiv" class="example-box-wrapper">
	<div style="float:left">
		<h3>
			<spring:message
				code="createOrganization-form.createOrganization-label" />
		</h3>
		</div>
		<div style="float: right">
			<button id="createOrganizationBackBt"
				name="createOrganizationBackBt" class="btn btn-info"
				onClick="javascript:loadContent('/app/organization/list','page-content-holder');">
				<i class="glyph-icon icon-minus-square"></i>&nbsp;
				<spring:message code="createOrganization-form.back-button" />
			</button>
		</div>
	</div>
		<div class="divider"></div>
		<form:form id="createOrganizationForm" class="form-horizontal"
			data-parsley-validate=""
			action="javascript:submitForm('/app/organization/create', 'createOrganizationForm', 'page-content-holder')"
			method='POST' modelAttribute="organizationForm">
		<div id="createOrganizationTypeDiv" class="form-group">
			<label for="" path="type_label" class="col-sm-2 control-label">
				<spring:message code="createOrganization-form.type-label" /> <span
				class="required">*</span>
			</label>
			<div class="col-sm-8">
				<form:select path="type" class="form-control" required="true">
					<c:forEach items="${OrgType}" var="ev">
						<form:option value="${ev}">
							<spring:message code="OrgType.${ev}" />
						</form:option>
					</c:forEach>
				</form:select>
				<form:errors path="type" class="parsley-required" />
			</div>
		</div>
		<div id="createOrganizationNameDiv" class="form-group">
				<label for="" class="col-sm-2 control-label"> <spring:message
						code="createOrganization-form.name-label" /> <span
					class="required">*</span>
				</label>
				<div id="createOrganizationNameDiv" class="col-sm-8">
					<form:input class="form-control" type="text" path="name"
						maxlength="32" required="true" />
					<form:errors path="name" class="parsley-required" />
				</div>
			</div>
			<div id="createOraganizationPhoneNumberDiv" class="form-group">
				<label for="" class="col-sm-2 control-label"> <spring:message
						code="createOrganization-form.phoneNumber-label" /> <span
					class="required">*</span>
				</label>
				<div class="col-sm-8">
					<form:input type="number" class="form-control" 
						path="phoneNumber" maxlength="11" required="true" />
					<form:errors path="phoneNumber" class="parsley-required" />
				</div>
			</div>
			<div id="createOrganizationMobileNumberDiv" class="form-group">
				<label for="" class="col-sm-2 control-label"> <spring:message
						code="createOrganization-form.mobileNumber-label" /> <span
					class="required">*</span>
				</label>
				<div class="col-sm-8">
					<form:input type="number" class="form-control" 
						path="mobileNumber" maxlength="10" required="true" />
					<form:errors path="mobileNumber" class="parsley-required" />
				</div>
			</div>
			<div id="createOrganizationEmailDiv" class="form-group">
				<label for="" class="col-sm-2 control-label"> <spring:message
						code="createOrganization-form.emailId-label" /> <span
					class="required">*</span>
				</label>
				<div id="createOrganizationEmailIdDiv" class="col-sm-8">
					<form:input class="form-control" type="text" path="emailId"
						maxlength="25" required="true" />
					<form:errors path="emailId" class="parsley-required" />
				</div>
			</div>
			<div id="createOrganizationCapthaDiv" class="form-group">
			<div class="col-sm-2 control-label">
				<img id="newCaptha" alt="Loading"
					src="/apu/captcha.jpg?ts=${javaDate.time}%>" width="120"
					height="48" /> <span title="Refresh and try another"
					onClick="javascript:refreshCaptcha('newCaptha');"> <i
					class="glyph-icon icon-refresh"
					style="cursor: pointer; padding-bottom: 10px;"></i>
				</span>
			</div><br>
			<div id="organizationcapthaDiv" class="col-sm-2">
				<span class="input-group-addon addon-inside bg-white font-primary"
					style="margin-left: 5px;"> <i
					class="glyph-icon icon-linecons-key"></i>
				</span> <input type="text" class="form-control" id="captchaText"
					name="captchaText" placeholder="Captcha" required>
				<form:errors path="captchaText" class="parsley-required" />
			</div>
			</div>
			<div class="divider"></div>
			<div id="createOrganizationButtonsDiv" class="form-group">
				<div class="col-sm-offset-2 col-sm-8">
					<div class="example-box-wrapper">
						<button id="organizationCreateSubmitBt"
							name="organizationCreateSubmitBt" type="submit"
							class="btn btn-success">
							<i class="glyph-icon icon-plus-square"></i>&nbsp;
							<spring:message code="createOrganization-form.submit-button" />
						</button>
						<button id="organizationCreateResetBt"
							name="organizationCreateResetBt" type="reset"
							class="btn btn-warning">
							<i class="glyph-icon icon-minus-square"></i>&nbsp;
							<spring:message code="createOrganization-form.reset-button" />
						</button>
						<button id="organizationCreateBackBt"
							name="organizationCreateBackBt" type="button" class="btn btn-info"
							onClick="javascript:getUrl('/app/organization/list','page-content-holder');">
							<i class="glyph-icon icon-minus-square"></i>&nbsp;
							<spring:message code="createOrganization-form.back-button" />
						</button>
					</div>
				</div>
			</div>
	</form:form>
</div>
</div>
