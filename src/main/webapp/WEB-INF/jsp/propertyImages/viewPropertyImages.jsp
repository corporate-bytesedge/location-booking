<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<div id="page-content">
	<div class="example-box-wrapper">
		<div id="viewPropertyImagesDiv" class="example-box-wrapper">
		<div style="float: left">
			<h3>
				<spring:message code="viewPropertyImages-form.viewPropertyImages-label" />
			</h3>
			</div>
			<div style="float: right">
		<button id="propertyImagesViewBackBt" name="propertyImagesViewBackBt"
								type="button" class="btn btn-info"
								onClick="javascript:getUrl('/app/propertyImages/list?reqPage=${reqPage}&pageSize=${pageSize}','page-content-holder');">
								<i class="glyph-icon icon-minus-square"></i>&nbsp;
								<spring:message code="viewPropertyImages-form.back-button" />
							</button>
			</div><br><br>
			<div class="divider"></div>
			<form:form id="viewPropertyImagesForm" name="viewPropertyImagesForm"
				class="form-horizontal" data-parsley-validate=""
				action="javascript:submitForm('/app/propertyImages/view', 'viewPropertyImagesForm', 'page-content-holder')"
				method='POST' modelAttribute="propertyImagesForm">
				<input type="hidden" path="<c:out value="${_csrf.parameterName}"/>"
					name="<c:out value="${_csrf.parameterName}"/>"
					value="<c:out value="${_csrf.token}"/>" />
				<h4>
					<span Style="color: blue"><spring:message
							code="viewPropertyImages-form.propertyImageDetails-label" /></span>
				</h4>
				<div class="divider"></div>
				<div id="viewPropertyImagesNameDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewPropertyImages-form.name-label" /> <span
						class="required">:</span>
					</label>
					<div id="viewPropertyImagesNameDiv" class="col-sm-8 paddingt8">
						${propertyImagesForm.name}</div>
				</div>
				<div id="viewOraganizationDescrDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewPropertyImages-form.descr-label" /> <span
						class="required">:</span>
					</label>
					<div id="viewOraganizationDescrDiv" class="col-sm-8 paddingt8">
						${propertyImagesForm.descr}</div>
				</div>
				<div id="viewPropertyImagesCreatedUserNameDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewPropertyImages-form.createdUserId-label" /><span
						class="required">:</span>
					</label>
					<div id="viewPropertyImagesCreatedUserNameDiv"
						class="col-sm-8 paddingt8">
						${propertyImagesForm.createdUserName}</div>
				</div>
				<div id="viewPropertyImagesCreatedTimeDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewPropertyImages-form.createdTime-label" /><span
						class="required">:</span>
					</label>
					<div id="viewPropertyImagesCreatedTimeDiv" class="col-sm-8 paddingt8">
						<fmt:formatDate value="${propertyImagesForm.createdTime}"
							pattern="dd-MMM-yyyy HH-mm-ss" />
					</div>
				</div>
				<div id="viewPropertyImagesUpdatedUserNameDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewPropertyImages-form.updatedUserId-label" /><span
						class="required">:</span>
					</label>
					<div id="viewPropertyImagesUpdatedUserNameDiv"
						class="col-sm-8 paddingt8">
						${propertyImagesForm.updatedUserName}</div>
				</div>
				<div id="viewPropertyImagesUpdatedTimeDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewPropertyImages-form.updatedTime-label" /><span
						class="required">:</span>
					</label>
					<div id="viewPropertyImagesUpdatedTimeDiv" class="col-sm-8 paddingt8">
						<fmt:formatDate value="${propertyImagesForm.updatedTime}"
							pattern="dd-MMM-yyyy HH-mm-ss" />
					</div>
				</div>
				<div class="divider"></div>
				<div id="viewPropertyImagesButtonsDiv" class="form-group">
					<div class="col-sm-offset-2 col-sm-8">
						<div class="example-box-wrapper">
							<button id="propertyImagesViewBackBt" name="propertyImagesViewBackBt"
								type="button" class="btn btn-info"
								onClick="javascript:getUrl('/app/propertyImages/list?reqPage=${reqPage}&pageSize=${pageSize}','page-content-holder');">
								<i class="glyph-icon icon-minus-square"></i>&nbsp;
								<spring:message code="viewPropertyImages-form.back-button" />
							</button>
							<%-- <button id="propertyImagesViewDownloadBt" name="propertyImagesViewDownloadBt"
					type="download" class="btn btn-info"
					onClick="window.location='/app/propertyImages/download/pdf?id=${propertyImagesForm.id}'">
					<i class="glyph-icon icon-arrow-circle-down"></i>&nbsp;
					<spring:message code="viewPropertyImages-form.download-button" />
				</button> --%>
						</div>
					</div>
				</div>
			</form:form>
		</div>
	</div>
</div>