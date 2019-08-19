<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<!--<link rel="stylesheet" type="text/css" href="assets-minified/widgets/datepicker/datepicker.css">-->
<script type="text/javascript" src="/assets-minified/widgets/datepicker/datepicker.js"></script>
<script type="text/javascript">
    /* Datepicker bootstrap */

    $(function() { "use strict";
        $('.bootstrap-datepicker').bsdatepicker({
            format: 'dd-MM-yyyy'
        });
    });
</script>
<div id="page-content">
	<c:if test="${not empty message_error}">
		<div id="userActionFailedMsgDiv" class="example-box-wrapper">
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
	<div id="createUserDiv" class="example-box-wrapper">
		<div style="float: left">
			<h3>
				<spring:message code="createUser-form.createUser-label" />
			</h3>
		</div>
		<div style="float: right">
			<button id="createUserBackBt" name="createUserBackBt"
				class="btn btn-info"
				onClick="javascript:loadContent('/app/user/list','page-content-holder');">
				<i class="glyph-icon icon-minus-square"></i>&nbsp;
				<spring:message code="createUser-form.back-button" />
			</button>
		</div>
	</div>
	<div class="divider"></div>
	<form:form id="createUserForm" class="form-horizontal"
		data-parsley-validate=""
		action="javascript:submitForm('/app/user/create', 'createUserForm', 'page-content-holder')"
		method='POST' modelAttribute="userForm">
		<div id="createUserLoginNameDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="createUser-form.loginName-label" /> <span class="required">*</span>
			</label>
			<div id="createUserLoginNameDiv" class="col-sm-8">
				<form:input class="form-control" type="text"
					placeholder="Enter email" path="loginName" maxlength="128"
					required="true" />
				<form:errors path="loginName" class="parsley-required" />
			</div>
		</div>
		<div id="createUserLoginPasswordDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="createUser-form.loginPassword-label" /> <span
				class="required">*</span>
			</label>
			<div id="createUserLoginPasswordDiv" class="col-sm-8">
				<form:input class="form-control" type="text"
					placeholder="Enter password" path="loginPassword" maxlength="128"
					required="true" />
				<form:errors path="loginPassword" class="parsley-required" />
			</div>
		</div>
		<div id="createUserEmailDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="createUser-form.email-label" /> <span class="required">*</span>
			</label>
			<div id="createUserEmailDiv" class="col-sm-8">
				<form:input class="form-control" type="text"
					placeholder="Enter email id" path="email" maxlength="64"
					required="true" />
				<form:errors path="email" class="parsley-required" />
			</div>
		</div>
		<div id="createUserDisplayNameDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="createUser-form.displayName-label" /> <span class="required">*</span>
			</label>
			<div id="createUserDisplayNameDiv" class="col-sm-8">
				<form:input class="form-control" type="text"
					placeholder="Enter name" path="displayName" maxlength="128"
					required="true" />
				<form:errors path="displayName" class="parsley-required" />
			</div>
		</div>
		<div id="createUserGenderDiv" class="form-group">
				<label for="" class="col-sm-2 control-label"> <spring:message
						code="createUser-form.gender-label" /> <span
					class="required">*</span>
				</label>
				<div id="createUserGenderDiv" class="col-sm-8">
					<form:select path="gender" class="form-control" required="true">
						<c:forEach items="${GenderType}" var="ev">
							<form:option value="${ev}">
								<spring:message code="GenderType.${ev}" />
							</form:option>
						</c:forEach>
					</form:select>
					<form:errors path="gender" class="parsley-required" />
				</div>
			</div>
		<div id="createUserAadharIdDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="createUser-form.aadharId-label" /> <span class="required">*</span>
			</label>
			<div id="createUserAadharIdDiv" class="col-sm-8">
				<form:input type="text" class=" input-mask form-control"
					placeholder="Enter aadhaar number"
					onkeypress="return AllowOnlyNumbers(event);" path="aadharId"
					maxlength="12" required="true"
					data-inputmask="&apos;mask&apos;:&apos;999999999999&apos;" />
				<form:errors path="aadharId" class="parsley-required" />
			</div>
		</div>
		<div id="createUserMobileNumberDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="createUser-form.mobileNumber-label" /> <span
				class="required">*</span>
			</label>
			<div id="createUserMobileNumberDiv" class="col-sm-8">
				<form:input type="text" class=" input-mask form-control"
					placeholder="Enter 10 digit mobile number"
					onkeypress="return AllowOnlyNumbers(event);" path="mobileNumber"
					maxlength="10" required="true"
					data-inputmask="&apos;mask&apos;:&apos;9999999999&apos;" />
				<form:errors path="mobileNumber" class="parsley-required" />
			</div>
		</div>
		<div  id="createUserDobDiv"  class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="createUser-form.dob-label" />
			</label>
			<div id="createUserDobDiv" class="col-sm-8">
			<span class="input-group-addon addon-inside bg-white font-primary"
					style="margin-left: 10px;"> <i
					class="glyph-icon icon-calendar"></i>
				</span>
				<form:input type="text" class="bootstrap-datepicker form-control"  maxlength="10"
					 path="dob" data-date-format="dd/MM/yyyy" required="true" style = "width:200px"/>
				<form:errors path="dob" class="parsley-required" />
				</div>
		</div>
		<div id="createUserDesignationDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="createUser-form.designation-label" /> <span class="required">*</span>
			</label>
			<div id="createUserDesignationDiv" class="col-sm-8">
				<form:input class="form-control" type="text"
					placeholder="Enter designation" path="designation" maxlength="64"
					required="true" />
				<form:errors path="designation" class="parsley-required" />
			</div>
		</div>
		<div id="createUserRoleIdDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="createUser-form.roleId-label" /> <span class="required">*</span>
			</label>
			<div id="createUserRoleIdDiv" class="col-sm-8">
				<form:select class="form-control" multiple="false" path="roleId"
					maxlength="1" required="true">
					<c:forEach var="obj" items="${userRoleList}">
						<form:option id="${obj.id}" value="${obj.id}">${obj.name}</form:option>
					</c:forEach>
				</form:select>
				<form:errors path="roleId" class="parsley-required" />
			</div>
		</div>
		<div class="divider"></div>
		<div id="createUserButtonsDiv" class="form-group">
			<div class="col-sm-offset-2 col-sm-8">
				<div class="example-box-wrapper">
					<button id="userCreateSubmitBt" name="userCreateSubmitBt"
						type="submit" class="btn btn-success">
						<i class="glyph-icon icon-plus-square"></i>&nbsp;
						<spring:message code="createUser-form.submit-button" />
					</button>
					<button id="userCreateRestBt" name="userCreateResetBt" type="reset"
						class="btn btn-warning">
						<i class="glyph-icon icon-minus-square"></i>&nbsp;
						<spring:message code="createUser-form.reset-button" />
					</button>
					<button id="userCreateBackBt" name="userCreateBackBt" type="button"
						class="btn btn-info"
						onClick="javascript:getUrl('/app/user/list','page-content-holder');">
						<i class="glyph-icon icon-minus-square"></i>&nbsp;
						<spring:message code="createUser-form.back-button" />
					</button>
				</div>
			</div>
		</div>
	</form:form>
</div>
