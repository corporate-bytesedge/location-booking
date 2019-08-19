<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<div id="page-content">
<div id="viewUserRoleDiv" class="example-box-wrapper">
<div style="float: left;">
		<h3><spring:message code="viewUserRole-form.viewUserRole-label"/></h3></div>
		<div style="float: right">
				<button id="userRoleViewBackBt" name="userRoleViewBackBt"
							type="back" class="btn btn-info"
							onClick="javascript:getUrl('/app/userRole/list?reqPage=${reqPage}&pageSize=${pageSize}','page-content-holder');">
							<i class="glyph-icon icon-minus-square"></i>&nbsp;
							<spring:message code="viewUserRole-form.back-button" />
						</button>
				<%-- <button id="userRoleViewDownloadBt" name="userRoleViewDownloadBt"
					type="download" class="btn btn-warning"
					onClick="window.location='/app/userRole/download/pdf?id=${userRoleForm.id}'">
					<i class="glyph-icon icon-arrow-circle-down"></i>&nbsp;
					<spring:message code="viewUserRole-form.download-button" />
				</button> --%>
			</div>
			<br>
			<br>
		<div class="divider"></div>
	<div class="example-box-wrapper">
		<form:form id="viewUserRoleForm" name="viewUserRoleForm" class="form-horizontal" data-parsley-validate="" action="javascript:submitForm('/app/userRole/view', 'viewUserRoleForm', 'page-content-holder')" method='POST' modelAttribute="userRoleForm">
			<input type="hidden" path="<c:out value="${_csrf.parameterName}"/>"	name="<c:out value="${_csrf.parameterName}"/>" value="<c:out value="${_csrf.token}"/>" />
					<h4><span Style="color:blue"><spring:message code="viewUserRole-form.UserRoleDetails-label"/></span></h4>
				<div class="divider"></div>
				<div id="viewUserRoleNameDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewUserRole-form.name-label" /> <span class="required">:</span>
					</label>
					<div id="viewUserRoleNameDiv" class="col-sm-8 paddingt8">
						${userRoleForm.name}</div>
				</div>
			<div id="viewUserRoleAdminDiv" class="form-group">
				<label for="" class="col-sm-2 control-label"> <spring:message
						code="viewUserRole-form.admin-label" /> <span class="required">:</span>
				</label>
				<div class="col-sm-8 paddingt8">
					${userRoleForm.admin}	
			</div></div>
			<div id="viewUserRoleCreatedUserNameDiv" class="form-group">
						<label for="" class="col-sm-2 control-label"> 
							<spring:message	code="viewUserRole-form.createdUserId-label" /> <span class="required">:</span>
						</label>
							<div id="viewUserRoleCreatedUserNameDiv" class="col-sm-8 paddingt8">
								${userRoleForm.createdUserName}
							</div>
			</div>
			<div id="viewUserRoleCreatedTimeDiv" class="form-group">
						<label for="" class="col-sm-2 control-label"> 
							<spring:message	code="viewUserRole-form.createdTime-label" /><span class="required">:</span>
						</label>
							<div id="viewUserRoleCreatedTimeDiv" class="col-sm-8 paddingt8">
								<fmt:formatDate value="${userRoleForm.createdTime}" pattern="dd-MMM-yyyy HH-mm-ss" />
							</div>
					</div>
					<div id="viewUserRoleUpdatedUserNameDiv" class="form-group">
						<label for="" class="col-sm-2 control-label"> 
							<spring:message	code="viewUserRole-form.updatedUserId-label" /><span class="required">:</span>
						</label>
							<div id="viewUserRoleUpdatedUserNameDiv" class="col-sm-8 paddingt8">
								${userRoleForm.updatedUserName}
							</div>
					</div>
					<div id="viewUserRoleUpdatedTimeDiv" class="form-group">
						<label for="" class="col-sm-2 control-label"> 
							<spring:message	code="viewUserRole-form.updatedTime-label" /><span class="required">:</span>
						</label>
							<div id="viewUserRoleUpdatedTimeDiv" class="col-sm-8 paddingt8">
								<fmt:formatDate value="${userRoleForm.updatedTime}" pattern="dd-MMM-yyyy HH-mm-ss" />
							</div>
					</div>
			<div class="divider"></div>
			<div id="viewUserRoleButtonsDiv" class="form-group">
				<div class="col-sm-offset-2 col-sm-8">
					<div class="example-box-wrapper">
						<button id="userRoleViewBackBt" name="userRoleViewBackBt"
							type="back" class="btn btn-info"
							onClick="javascript:getUrl('/app/userRole/list?reqPage=${reqPage}&pageSize=${pageSize}','page-content-holder');">
							<i class="glyph-icon icon-minus-square"></i>&nbsp;
							<spring:message code="viewUserRole-form.back-button" />
						</button>
					<%-- 	<button id="userRoleViewDownloadBt" name="userRoleViewDownloadBt"
					type="download" class="btn btn-warning"
					onClick="window.location='/app/userRole/download/pdf?id=${userRoleForm.id}'">
					<i class="glyph-icon icon-arrow-circle-down"></i>&nbsp;
					<spring:message code="viewUserRole-form.download-button" />
				</button> --%>
					</div>
				</div>
			</div>
			</form:form>
		</div>
	</div>
	</div>