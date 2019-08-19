<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<div id="page-content">
<c:if test="${not empty message_error}">
			<div id="userRoleActionFailedMsgDiv" class="example-box-wrapper">
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
	<div id="createUserRoleDiv" class="example-box-wrapper">
	<div style="float:left">
		<h3>
			<spring:message code="createUserRole-form.createUserRole-label" />
		</h3>
		</div>
		<div style="float: right">
			<button id="createUserRoleBackBt"
				name="createUserRoleBackBt" class="btn btn-info"
				onClick="javascript:loadContent('/app/userRole/list','page-content-holder');">
				<i class="glyph-icon icon-minus-square"></i>&nbsp;
				<spring:message code="createUserRole-form.back-button" />
			</button>
		</div>
	</div>
		<div class="divider"></div>
		<form:form id="createUserRoleForm" class="form-horizontal"
			data-parsley-validate=""
			action="javascript:submitForm('/app/userRole/create', 'createUserRoleForm', 'page-content-holder')"
			method='POST' modelAttribute="userRoleForm">
			<div id="createUserRoleNameDiv" class="form-group">
				<label for="" class="col-sm-2 control-label"> <spring:message
						code="createUserRole-form.name-label" /> <span class="required">*</span>
				</label>
				<div id="createUserRoleNameDiv" class="col-sm-8">
					<form:input class="form-control" type="text" path="name"
						maxlength="32" required="true" />
					<form:errors path="name" class="parsley-required" />
				</div>
			</div>
			<div id="createUserRoleAdminDiv" class="form-group">
				<label for="" class="col-sm-2 control-label"> <spring:message
						code="createUserRole-form.admin-label" /> <span class="required">*</span>
				</label>
				<div class="col-sm-8">
					<div class="checkbox-inline">
						<form:checkbox path="admin" class="custom-checkbox" value="True" />
					</div>
					<form:errors path="admin" class="parsley-required" />
				</div>
			</div>
			<div id="createUserRoleCapthaDiv" class="form-group">
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
				<div id="createUserRoleCapthaDiv" class="col-sm-2">
					<span class="input-group-addon addon-inside bg-white font-primary"
						style="margin-left: 5px;"> <i
						class="glyph-icon icon-linecons-key"></i>
					</span>
					<form:input type="text" class="form-control" path="captchaText"
						maxlength="6" placeholder="Captcha" required="true" />
					<form:errors path="captchaText" class="parsley-required" />
				</div>
			</div>
			<div class="divider"></div>
			<div id="createUserRoleButtonsDiv" class="form-group">
				<div class="col-sm-offset-2 col-sm-8">
					<div class="example-box-wrapper">
						<button id="userRoleCreateSubmitBt" name="userRoleCreateSubmitBt"
							type="submit" class="btn btn-success">
							<i class="glyph-icon icon-plus-square"></i>&nbsp;
							<spring:message code="createUserRole-form.submit-button" />
						</button>
						<button id="userRoleCreateResetBt" name="userRoleCreateResetBt"
							type="reset" class="btn btn-warning">
							<i class="glyph-icon icon-minus-square"></i>&nbsp;
							<spring:message code="createUserRole-form.reset-button" />
						</button>
						<button id="userRoleCreateBackBt" name="userRoleCreateBackBt"
							type="button" class="btn btn-info"
							onClick="javascript:getUrl('/app/userRole/list','page-content-holder');">
							<i class="glyph-icon icon-minus-square"></i>&nbsp;
							<spring:message code="createUserRole-form.back-button" />
						</button>
					</div>
				</div>
			</div>
		</form:form>
	</div>
</div>