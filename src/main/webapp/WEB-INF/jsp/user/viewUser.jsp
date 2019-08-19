<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<div id="page-content">
	<div class="example-box-wrapper">
		<div id="viewUserDiv" class="example-box-wrapper">
			<div style="float: left;">
				<h3>
					<spring:message code="viewUser-form.viewUser-label" />
				</h3>
			</div>
			<div style="float: right">
				<button id="userViewBackBt" name="userViewBackBt" type="back"
					class="btn btn-info"
					onClick="javascript:getUrl('/app/user/list?reqPage=${reqPage}&pageSize=${pageSize}','page-content-holder');">
					<i class="glyph-icon icon-minus-square"></i>&nbsp;
					<spring:message code="viewUser-form.back-button" />
				</button>
				<%-- <button id="userViewDownloadBt" name="userViewDownloadBt"
					type="download" class="btn btn-warning"
					onClick="window.location='/app/user/download/pdf?id=${userForm.id}'">
					<i class="glyph-icon icon-arrow-circle-down"></i>&nbsp;
					<spring:message code="viewUser-form.download-button" />
				</button> --%>
			</div>
			<br>
			<br>
			<form:form id="viewUserForm" name="viewUserForm"
				class="form-horizontal" data-parsley-validate=""
				action="javascript:submitForm('/app/user/view', 'viewUserForm', 'page-content-holder')"
				method='POST' modelAttribute="userForm">
				<input type="hidden" path="<c:out value="${_csrf.parameterName}"/>"
					name="<c:out value="${_csrf.parameterName}"/>"
					value="<c:out value="${_csrf.token}"/>" />
				<div class="divider"></div>
				<h4>
					<span Style="color: blue"><spring:message
							code="viewUser-form.UserDetails-label" /></span>
				</h4>
				<div class="divider"></div>
				<div id="viewUserLoginNameDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="createUser-form.loginName-label" /> <span class="required">:</span>
					</label>
					<div id="viewUserLoginNameDiv" class="col-sm-8 paddingt8">${ userForm.loginName}</div>
				</div>
				<div id="viewUserAccountStateDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="createUser-form.accountState-label" />:<span
						class="required"></span>
					</label>
					<div id="viewUserAccountStateDiv" class="col-sm-8 paddingt8">
						${ userForm.accountState}</div>
				</div>
				<div id="viewUserEmailDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewUser-form.email-label" /> <span class="required">:</span>
					</label>
					<div id="viewUserEmailDiv" class="col-sm-8 paddingt8">
						${userForm.email}</div>
				</div>
				<div id="viewUserDisplayNameDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewUser-form.displayName-label" /> <span class="required">:</span>
					</label>
					<div id="viewUserDisplayNameDiv" class="col-sm-8 paddingt8">
						${userForm.displayName}</div>
				</div>
				<div id="viewUserGenderDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewUser-form.gender-label" /> <span class="required">:</span>
					</label>
					<div id="viewUserGenderDiv" class="col-sm-8 paddingt8">
						${userForm.gender}</div>
				</div>
				<div id="viewUserAadharIdDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewUser-form.aadharId-label" /> <span class="required">:</span>
					</label>
					<div id="viewUserAadharIdDiv" class="col-sm-8 paddingt8">
						${userForm.aadharId}</div>
				</div>
				<div id="viewUserMobileNumberDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewUser-form.mobileNumber-label" /> <span
						class="required">:</span>
					</label>
					<div id="viewUserMobileNumberDiv" class="col-sm-8 paddingt8">
						${userForm.mobileNumber}</div>
				</div>
				<div id="viewUserDobDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewUser-form.dob-label" /> <span class="required">:</span>
					</label>
					<div class="col-sm-8 paddingt8">
						<fmt:formatDate value="${userForm.dob}" pattern="dd-MMM-yyyy" />
					</div>
				</div>
				<div id="viewUserDesignationDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewUser-form.designation-label" /> <span class="required">:</span>
					</label>
					<div id="viewUserDesignationDiv" class="col-sm-8 paddingt8">
						${userForm.designation}</div>
				</div>
				<div id="viewUserRoleNameDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewUser-form.roleName-label" /> <span class="required">:</span>
					</label>
					<div id="viewUserRoleNameDiv" class="col-sm-8 paddingt8">
						${userForm.roleName}</div>
				</div>
				<%-- <div id="viewUserAuthFailedDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewUser-form.authFailed-label" /> <span class="required">:</span>
					</label>
					<div id="viewUserAuthFailedDiv" class="col-sm-8 paddingt8">
						${userForm.authFailed}</div>
				</div>
				<div id="viewUserAuthFailedTimeDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewUser-form.authFailedTime-label" /> <span class="required">:</span>
					</label>
					<div id="viewUserAuthFailedTimeDiv" class="col-sm-8 paddingt8">
						${userForm.authFailedTime}</div>
				</div> --%>
				<div id="viewUserCreatedUserNameDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewUser-form.createdUserId-label" /><span class="required">:</span>
					</label>
					<div id="viewUserCreatedUserNameDiv" class="col-sm-8 paddingt8">
						${userForm.createdUserName}</div>
				</div>
				<div id="viewUserCreatedTimeDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewUser-form.createdTime-label" /><span class="required">:</span>
					</label>
					<div id="viewUserCreatedTimeDiv" class="col-sm-8 paddingt8">
						<fmt:formatDate value="${userForm.createdTime}"
							pattern="dd-MMM-yyyy HH-mm-ss" />
					</div>
				</div>
				<div id="viewUserUpdatedUserNameDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewUser-form.updatedUserId-label" /><span class="required">:</span>
					</label>
					<div id="viewUserUpdatedUserNameDiv" class="col-sm-8 paddingt8">
						${userForm.updatedUserName}</div>
				</div>
				<div id="viewUserUpdatedTimeDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewUser-form.updatedTime-label" /><span class="required">:</span>
					</label>
					<div id="viewUserUpdatedTimeDiv" class="col-sm-8 paddingt8">
						<fmt:formatDate value="${userForm.updatedTime}"
							pattern="dd-MMM-yyyy HH-mm-ss" />
					</div>
				</div>
				<%-- <div id="viewUserLockedTimeDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewUser-form.lockedTime-label" /><span class="required">:</span>
					</label>
					<div id="viewUserLockedTimeDiv" class="col-sm-8 paddingt8">
						${userForm.lockedTime}</div>
				</div> --%>

				<div class="divider"></div>
				<div id="userButtonsDiv" class="form-group">
					<div class="col-sm-offset-2 col-sm-8">
						<div class="example-box-wrapper">
							<button id="userViewBackBt" name="userViewBackBt" type="back"
								class="btn btn-info"
								onClick="javascript:getUrl('/app/user/list?reqPage=${reqPage}&pageSize=${pageSize}','page-content-holder');">
								<i class="glyph-icon icon-minus-square"></i>&nbsp;
								<spring:message code="viewUser-form.back-button" />
							</button>
							<%-- <button id="userViewDownloadBt" name="userViewDownloadBt"
								type="download" class="btn btn-warning"
								onClick="window.location='/app/user/download/pdf?id=${userForm.id}'">
								<i class="glyph-icon icon-arrow-circle-down"></i>&nbsp;
								<spring:message code="viewUser-form.download-button" />
							</button> --%>
						</div>
					</div>
				</div>
			</form:form>
		</div>
	</div>
</div>