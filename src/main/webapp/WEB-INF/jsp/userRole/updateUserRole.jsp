<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<div id="page-content">
	<div id="updateUserRoleDiv" class="example-box-wrapper">
		<div style="float: left">
			<h3>
				<spring:message code="updateUserRole-form.updateUserRole-label" />
			</h3>
		</div>
		<div style="float: right">
			<button id="userRoleUpdateBackBt" name="userRoleUpdateBackBt"
				type="button" class="btn btn-info"
				onClick="javascript:getUrl('/app/userRole/list?reqPage=${reqPage}&pageSize=${pageSize}','page-content-holder');">
				<i class="glyph-icon icon-minus-square"></i>&nbsp;
				<spring:message code="updateUserRole-form.back-button" />
			</button>
		</div>
	</div>
	<div class="divider"></div>
	<div class="example-box-wrapper">
		<form:form id="updateUserRoleForm" name="updateUserRoleForm"
			class="form-horizontal" data-parsley-validate=""
			action="javascript:submitForm('/app/userRole/update?reqPage=${reqPage}&pageSize=${pageSize}', 'updateUserRoleForm', 'page-content-holder')"
			method='POST' modelAttribute="userRoleForm">
			<input type="hidden" path="<c:out value="${_csrf.parameterName}"/>"
				name="<c:out value="${_csrf.parameterName}"/>"
				value="<c:out value="${_csrf.token}"/>" />
			<div id="updateUserRoleNameDiv" class="form-group">
				<label for="" class="col-sm-2 control-label"> <spring:message
						code="updateUserRole-form.name-label" /> <span class="required">*</span>
				</label>
				<div id="updateUserRoleNameDiv" class="col-sm-8">
					<form:input class="form-control" type="text" path="name"
						maxlength="32" required="true" />
					<form:errors path="name" class="parsley-required" />
				</div>
			</div>
			<div id="updateUserRoleAdminDiv" class="form-group">
				<label for="" class="col-sm-2 control-label"> <spring:message
						code="updateUserRole-form.admin-label" /> <span class="required">*</span>
				</label>
				<div class="col-sm-8">
					<div class="checkbox-inline">
						<form:checkbox path="admin" class="custom-checkbox" value="True" />
					</div>
					<form:errors path="admin" class="parsley-required" />
				</div>
			</div>
			<div id="updateUserRoleCapthaDiv" class="form-group">
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
				<div id="updateUserRoleCapthaDiv" class="col-sm-2">
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
			<div id="updateUserRoleButtonsDiv" class="form-group">
				<div class="col-sm-offset-2 col-sm-8">
					<div class="example-box-wrapper">
						<button id="userRoleUpdateSubmitBt" name="userRoleUpdateSubmitBt"
							type="submit" class="btn btn-success">
							<i class="glyph-icon icon-plus-square"></i>&nbsp;
							<spring:message code="updateUserRole-form.submit-button" />
						</button>
						<button id="userRoleUpdateResetBt" name="userRoleUpdateResetBt"
							type="reset" class="btn btn-warning">
							<i class="glyph-icon icon-minus-square"></i>&nbsp;
							<spring:message code="updateUserRole-form.reset-button" />
						</button>
						<button id="userRoleUpdateBackBt" name="userRoleUpdateBackBt"
							type="button" class="btn btn-info"
							onClick="javascript:getUrl('/app/userRole/list?reqPage=${reqPage}&pageSize=${pageSize}','page-content-holder');">
							<i class="glyph-icon icon-minus-square"></i>&nbsp;
							<spring:message code="updateUserRole-form.back-button" />
						</button>
					</div>
				</div>
			</div>
		</form:form>
	</div>
</div>
</div>