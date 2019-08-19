<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<div id="page-content">
	<div class="example-box-wrapper">
		<div id="viewOrganizationDiv" class="example-box-wrapper">
		<div style="float: left">
			<h3>
				<spring:message code="viewOrganization-form.viewOrganization-label" />
			</h3>
			</div>
			<div style="float: right">
		<button id="organizationViewBackBt" name="organizationViewBackBt"
								type="button" class="btn btn-info"
								onClick="javascript:getUrl('/app/organization/list?reqPage=${reqPage}&pageSize=${pageSize}','page-content-holder');">
								<i class="glyph-icon icon-minus-square"></i>&nbsp;
								<spring:message code="viewOrganization-form.back-button" />
							</button>
				<%-- <button id="organizationViewDownloadBt" name="organizationViewDownloadBt"
					type="download" class="btn btn-info"
					onClick="window.location='/app/organization/download/pdf?id=${organizationForm.id}'">
					<i class="glyph-icon icon-arrow-circle-down"></i>&nbsp;
					<spring:message code="viewOrganization-form.download-button" />
				</button> --%>
			</div><br><br>
			<div class="divider"></div>
			<form:form id="viewOrganizationForm" name="viewOrganizationForm"
				class="form-horizontal" data-parsley-validate=""
				action="javascript:submitForm('/app/organization/view', 'viewOrganizationForm', 'page-content-holder')"
				method='POST' modelAttribute="organizationForm">
				<input type="hidden" path="<c:out value="${_csrf.parameterName}"/>"
					name="<c:out value="${_csrf.parameterName}"/>"
					value="<c:out value="${_csrf.token}"/>" />
				<h4>
					<span Style="color: blue"><spring:message
							code="viewOrganization-form.PanchayatDetails-label" /></span>
				</h4>
				<div class="divider"></div>
				<div id="viewOrganizationNameDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewOrganization-form.name-label" /> <span
						class="required">:</span>
					</label>
					<div id="viewOrganizationNameDiv" class="col-sm-8 paddingt8">
						${organizationForm.name}</div>
				</div>
				<div id="viewOraganizationPhoneNumberDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewOrganization-form.phoneNumber-label" /> <span
						class="required">:</span>
					</label>
					<div id="viewOraganizationPhoneNumberDiv" class="col-sm-8 paddingt8">
						${organizationForm.phoneNumber}</div>
				</div>
				<div id="viewOrganizationMobileNumberDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewOrganization-form.mobileNumber-label" /> <span
						class="required">:</span>
					</label>
					<div id="viewOrganizationMobileNumberDiv" class="col-sm-8 paddingt8">
						${organizationForm.mobileNumber}</div>
				</div>
				<div id="viewOrganizationEmailDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewOrganization-form.emailId-label" /> <span
						class="required">:</span>
					</label>
					<div id="viewOrganizationEmailIdDiv" class="col-sm-8 paddingt8">
						${organizationForm.emailId}</div>
				</div>
				<div id="viewOrganizationCreatedUserNameDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewOrganization-form.createdUserId-label" /><span
						class="required">:</span>
					</label>
					<div id="viewOrganizationCreatedUserNameDiv"
						class="col-sm-8 paddingt8">
						${organizationForm.createdUserName}</div>
				</div>
				<div id="viewOrganizationCreatedTimeDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewOrganization-form.createdTime-label" /><span
						class="required">:</span>
					</label>
					<div id="viewOrganizationCreatedTimeDiv" class="col-sm-8 paddingt8">
						<fmt:formatDate value="${organizationForm.createdTime}"
							pattern="dd-MMM-yyyy HH-mm-ss" />
					</div>
				</div>
				<div id="viewOrganizationUpdatedUserNameDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewOrganization-form.updatedUserId-label" /><span
						class="required">:</span>
					</label>
					<div id="viewOrganizationUpdatedUserNameDiv"
						class="col-sm-8 paddingt8">
						${organizationForm.updatedUserName}</div>
				</div>
				<div id="viewOrganizationUpdatedTimeDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewOrganization-form.updatedTime-label" /><span
						class="required">:</span>
					</label>
					<div id="viewOrganizationUpdatedTimeDiv" class="col-sm-8 paddingt8">
						<fmt:formatDate value="${organizationForm.updatedTime}"
							pattern="dd-MMM-yyyy HH-mm-ss" />
					</div>
				</div>
				<div class="divider"></div>
				<div id="viewOrganizationButtonsDiv" class="form-group">
					<div class="col-sm-offset-2 col-sm-8">
						<div class="example-box-wrapper">
							<button id="organizationViewBackBt" name="organizationViewBackBt"
								type="button" class="btn btn-info"
								onClick="javascript:getUrl('/app/organization/list?reqPage=${reqPage}&pageSize=${pageSize}','page-content-holder');">
								<i class="glyph-icon icon-minus-square"></i>&nbsp;
								<spring:message code="viewOrganization-form.back-button" />
							</button>
						<%-- 	<button id="organizationViewDownloadBt" name="organizationViewDownloadBt"
					type="download" class="btn btn-info"
					onClick="window.location='/app/organization/download/pdf?id=${organizationForm.id}'">
					<i class="glyph-icon icon-arrow-circle-down"></i>&nbsp;
					<spring:message code="viewOrganization-form.download-button" />
				</button> --%>
						</div>
					</div>
				</div>
			</form:form>
		</div>
	</div>
</div>