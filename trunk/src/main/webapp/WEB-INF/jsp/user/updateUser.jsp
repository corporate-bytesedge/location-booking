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
		<div id="updateUserDiv" class="example-box-wrapper">
			<div style="float: left">
				<h3>
					<spring:message code="updateUser-form.updateUser-label" />
				</h3>
			</div>
			<div style="float: right">
				<button id="userUpdateBackBt" name="userUpdateBackBt" type="button"
					class="btn btn-info"
					onClick="javascript:getUrl('/app/user/list?reqPage=${reqPage}&pageSize=${pageSize}','page-content-holder');">
					<i class="glyph-icon icon-minus-square"></i>&nbsp;
					<spring:message code="updateUser-form.back-button" />
				</button>
			</div>
		</div>
		<div class="divider"></div>
		<form:form id="updateUserForm" name="updateUserForm"
			class="form-horizontal" data-parsley-validate=""
			action="javascript:submitForm('/app/user/update?reqPage=${reqPage}&pageSize=${pageSize}', 'updateUserForm', 'page-content-holder')"
			method='POST' modelAttribute="userForm">
			<input type="hidden" path="<c:out value="${_csrf.parameterName}"/>"
				name="<c:out value="${_csrf.parameterName}"/>"
				value="<c:out value="${_csrf.token}"/>" />
			<div id="updateUserAccountStateDiv" class="form-group">
				<label for="" path="type_label" class="col-sm-2 control-label">
					<spring:message code="createUser-form.accountState-label" /> <span
					class="required">*</span>
				</label>
				<div id="updateUserAccountStateDiv" class="col-sm-8">
					<form:select path="accountState" class="form-control"
						required="true">
						<c:forEach items="${UserState}" var="ev">
							<form:option value="${ev}">
								<spring:message code="UserState.${ev}" />
							</form:option>
						</c:forEach>
					</form:select>
					<form:errors path="accountState" class="parsley-required" />
				</div>
			</div>
			<div id="updateUserEmailDiv" class="form-group">
				<label for="" class="col-sm-2 control-label"> <spring:message
						code="updateUser-form.email-label" /> <span class="required">*</span>
				</label>
				<div id="updateUserEmailDiv" class="col-sm-8">
					<form:input class="form-control" type="text" path="email"
						maxlength="24" required="true" readonly="true" />
					<form:errors path="email" class="parsley-required" />
				</div>
			</div>
			<div id="updateUserDisplayNameDiv" class="form-group">
				<label for="" class="col-sm-2 control-label"> <spring:message
						code="updateUser-form.displayName-label" /> <span
					class="required">*</span>
				</label>
				<div id="updateUserDisplayNameDiv" class="col-sm-8">
					<form:input class="form-control" type="text" path="displayName"
						maxlength="32" required="true" readonly="true" />
					<form:errors path="displayName" class="parsley-required" />
				</div>
			</div>
			<div id="updateUserGenderDiv" class="form-group">
				<label for="" class="col-sm-2 control-label"> <spring:message
						code="updateUser-form.gender-label" /> <span class="required">*</span>
				</label>
				<div id="updateUserGenderDiv" class="col-sm-8">
					<form:select path="gender" class="form-control" readonly="true"
						required="true">
						<c:forEach items="${GenderType}" var="ev">
							<form:option value="${ev}">
								<spring:message code="GenderType.${ev}" />
							</form:option>
						</c:forEach>
					</form:select>
					<form:errors path="gender" class="parsley-required" />
				</div>
			</div>
			<div id="updateUserAadharIdDiv" class="form-group">
				<label for="" class="col-sm-2 control-label"> <spring:message
						code="updateUser-form.aadharId-label" /> <span class="required">*</span>
				</label>
				<div id="updateUserAadharIdDiv" class="col-sm-8">
					<form:input type="text" class=" input-mask form-control"
						path="aadharId" maxlength="12" required="true" readonly="true"
						onkeypress="return AllowOnlyNumbers(event);"
						data-inputmask="&apos;mask&apos;:&apos;999999999999&apos;" />
					<form:errors path="aadharId" class="parsley-required" />
				</div>
			</div>
			<div id="updateUserMobileNumberDiv" class="form-group">
				<label for="" class="col-sm-2 control-label"> <spring:message
						code="updateUser-form.mobileNumber-label" /> <span
					class="required">*</span>
				</label>
				<div id="updateUserMobileNumberDiv" class="col-sm-8">
					<form:input type="text" class=" input-mask form-control"
						onkeypress="return AllowOnlyNumbers(event);" path="mobileNumber"
						maxlength="10" required="true" readonly="true"
						data-inputmask="&apos;mask&apos;:&apos;9999999999&apos;" />
					<form:errors path="mobileNumber" class="parsley-required" />
				</div>
			</div>
			<div id="updateUserDobDiv" class="form-group">
				<label for="" class="col-sm-2 control-label"> <spring:message
						code="updateUser-form.dob-label" />
				</label>
				<div id="updateUserDobDiv" class="col-sm-8">
					<span class="input-group-addon addon-inside bg-white font-primary"
						style="margin-left: 10px;"> <i
						class="glyph-icon icon-calendar"></i>
					</span>
					<form:input type="text" class="bootstrap-datepicker form-control"
						maxlength="10" path="dob" data-date-format="dd/MM/yyyy"
						required="true" style="width:200px" readonly="true"/>
					<form:errors path="dob" class="parsley-required" />
				</div>
			</div>
			<div id="updateUserDesignationDiv" class="form-group">
				<label for="" class="col-sm-2 control-label"> <spring:message
						code="updateUser-form.designation-label" /> <span
					class="required">*</span>
				</label>
				<div id="updateUserDesignationDiv" class="col-sm-8">
					<form:input class="form-control" type="text" path="designation"
						maxlength="32" required="true" readonly="true" />
					<form:errors path="designation" class="parsley-required" />
				</div>
			</div>
			<div id="updateUserRoleIdDiv" class="form-group">
				<label for="" class="col-sm-2 control-label"> <spring:message
						code="updateUser-form.roleId-label" /> <span class="required">*</span>
				</label>
				<div id="updateUserRoleIdDiv" class="col-sm-8">
					<form:select class="form-control" multiple="false" path="roleId"
						maxlength="1" required="true" readonly="true">
						<c:forEach var="obj" items="${userRoleList}">
							<form:option id="${obj.id}" value="${obj.id}">${obj.name}</form:option>
						</c:forEach>
					</form:select>
					<form:errors path="roleId" class="parsley-required" />
				</div>
			</div>
			<div class="divider"></div>
			<div id="userButtonsDiv" class="form-group">
				<div class="col-sm-offset-2 col-sm-8">
					<div class="example-box-wrapper">
						<button id="userUpdateSubmitBt" name="userUpdateSubmitBt"
							type="submit" class="btn btn-success">
							<i class="glyph-icon icon-plus-square"></i>&nbsp;
							<spring:message code="updateUser-form.submit-button" />
						</button>
						<button id="userUpdateResetBt" name="userUpdateResetBt"
							type="reset" class="btn btn-warning">
							<i class="glyph-icon icon-minus-square"></i>&nbsp;
							<spring:message code="updateUser-form.reset-button" />
						</button>
						<button id="userUpdateBackBt" name="userUpdateBackBt"
							type="button" class="btn btn-info"
							onClick="javascript:getUrl('/app/user/list?reqPage=${reqPage}&pageSize=${pageSize}','page-content-holder');">
							<i class="glyph-icon icon-minus-square"></i>&nbsp;
							<spring:message code="updateUser-form.back-button" />
						</button>
					</div>
				</div>
			</div>
		</form:form>
	</div>
</div>
</div>