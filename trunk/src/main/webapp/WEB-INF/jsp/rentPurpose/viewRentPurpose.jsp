<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<div id="page-content">
	<div class="example-box-wrapper">
		<div id="viewRentPurposeDiv" class="example-box-wrapper">
			<div style="float: left;">
				<h3>
					<spring:message code="viewRentPurpose-form.viewRentPurpose-label" />
				</h3>
			</div>
			<div style="float: right">
				<button id="rentPurposeViewBackBt" name="rentPurposeViewBackBt"
					type="back" class="btn btn-info"
					onClick="javascript:getUrl('/app/rentPurpose/list?reqPage=${reqPage}&pageSize=${pageSize}','page-content-holder');">
					<i class="glyph-icon icon-minus-square"></i>&nbsp;
					<spring:message code="viewRentPurpose-form.back-button" />
				</button>
				<%-- <button id="rentPurposeViewDownloadBt"
					name="rentPurposeViewDownloadBt" type="download"
					class="btn btn-warning"
					onClick="window.location='/app/rentPurpose/download/pdf?id=${rentPurposeForm.id}'">
					<i class="glyph-icon icon-arrow-circle-down"></i>&nbsp;
					<spring:message code="viewRentPurpose-form.download-button" />
				</button> --%>
			</div>
			<br> <br>
			<form:form id="viewRentPurposeForm" name="viewRentPurposeForm"
				class="form-horizontal" data-parsley-validate=""
				action="javascript:submitForm('/app/rentPurpose/view', 'viewRentPurposeForm', 'page-content-holder')"
				method='POST' modelAttribute="rentPurposeForm">
				<input type="hidden" path="<c:out value="${_csrf.parameterName}"/>"
					name="<c:out value="${_csrf.parameterName}"/>"
					value="<c:out value="${_csrf.token}"/>" />
				<div class="divider"></div>
				<h4>
					<span Style="color: blue"><spring:message
							code="viewRentPurpose-form.RentPurposeDetails-label" /></span>
				</h4>
				<div class="divider"></div>
				<div id="viewRentPurposeNameDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewRentPurpose-form.name-label" /> <span class="required">:</span>
					</label>
					<div id="viewRentPurposeNameDiv" class="col-sm-8 paddingt8">${ rentPurposeForm.name}</div>
				</div>
				<div id="viewRentPurposeStateDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewRentPurpose-form.State-label" />:<span
						class="required"></span>
					</label>
					<div id="viewStateDiv" class="col-sm-8 paddingt8">${ rentPurposeForm.state}</div>
				</div>
				<div id="viewRentPurposeDescrDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewRentPurpose-form.descr-label" /> <span
						class="required">:</span>
					</label>
					<div id="viewRentPurposeEmailDiv" class="col-sm-8 paddingt8">
						${rentPurposeForm.descr}</div>
				</div>
				<div id="viewRentPurposeOrgNameDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewRentPurpose-form.orgName-label" /> <span
						class="required">:</span>
					</label>
					<div id="viewRentPurposeOrgNameDiv" class="col-sm-8 paddingt8">
						${rentPurposeForm.orgName}</div>
				</div>
				<div id="viewRentPurposeCreatedUserNameDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewRentPurpose-form.createdUserId-label" /><span
						class="required">:</span>
					</label>
					<div id="viewRentPurposeCreatedUserNameDiv"
						class="col-sm-8 paddingt8">
						${rentPurposeForm.createdUsername}</div>
				</div>
				<div id="viewRentPurposeCreatedTimeDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewRentPurpose-form.createdTime-label" /><span
						class="required">:</span>
					</label>
					<div id="viewRentPurposeCreatedTimeDiv" class="col-sm-8 paddingt8">
						<fmt:formatDate value="${rentPurposeForm.createdTime}"
							pattern="dd-MMM-yyyy HH-mm-ss" />
					</div>
				</div>
				<div id="viewRentPurposeUpdatedUserNameDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewRentPurpose-form.updatedUserId-label" /><span
						class="required">:</span>
					</label>
					<div id="viewRentPurposeUpdatedUserNameDiv"
						class="col-sm-8 paddingt8">
						${rentPurposeForm.updatedUsername}</div>
				</div>
				<div id="viewRentPurposeUpdatedTimeDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewRentPurpose-form.updatedTime-label" /><span
						class="required">:</span>
					</label>
					<div id="viewRentPurposeUpdatedTimeDiv" class="col-sm-8 paddingt8">
						<fmt:formatDate value="${rentPurposeForm.updatedTime}"
							pattern="dd-MMM-yyyy HH-mm-ss" />
					</div>
				</div>
				<div class="divider"></div>
				<div id="rentPurposeButtonsDiv" class="form-group">
					<div class="col-sm-offset-2 col-sm-8">
						<div class="example-box-wrapper">
							<button id="rentPurposeViewBackBt" name="rentPurposeViewBackBt"
								type="back" class="btn btn-info"
								onClick="javascript:getUrl('/app/rentPurpose/list?reqPage=${reqPage}&pageSize=${pageSize}','page-content-holder');">
								<i class="glyph-icon icon-minus-square"></i>&nbsp;
								<spring:message code="viewRentPurpose-form.back-button" />
							</button>
							<%-- <button id="rentPurposeViewDownloadBt"
								name="rentPurposeViewDownloadBt" type="download"
								class="btn btn-warning"
								onClick="window.location='/app/rentPurpose/download/pdf?id=${rentPurposeForm.id}'">
								<i class="glyph-icon icon-arrow-circle-down"></i>&nbsp;
								<spring:message code="viewRentPurpose-form.download-button" />
							</button> --%>
						</div>
					</div>
				</div>
			</form:form>
		</div>
	</div>
</div>