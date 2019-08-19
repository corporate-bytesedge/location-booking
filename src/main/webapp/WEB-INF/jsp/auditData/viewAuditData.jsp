<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<div id="page-content">
	<div class="example-box-wrapper">
		<div id="viewAuditDataDiv" class="example-box-wrapper">
			<div style="float: left;">
				<h3>
					<spring:message code="viewAuditData-form.viewAuditData-label" />
				</h3>
			</div>
			<div style="float: right">
				<button id="auditDataViewBackBt" name="auditDataViewBackBt"
					type="button" class="btn btn-info"
					onClick="javascript:getUrl('/app/auditData/list?reqPage=${reqPage}&pageSize=${pageSize}','page-content-holder');">
					<i class="glyph-icon icon-minus-square"></i>&nbsp;
					<spring:message code="viewAuditData-form.back-label" />
				</button>
				<%-- <button id="auditDataViewDownloadBt" name="auditDataViewDownloadBt"
					type="button" class="btn btn-info"
					onClick="window.location='/app/auditData/download/pdf?id=${auditDataForm.id}'">
					<i class="glyph-icon icon-arrow-circle-down"></i>&nbsp;
					<spring:message code="viewAuditData-form.download-label" />
				</button> --%>
			</div>
			<br> <br>
			<div class="divider"></div>
			<form:form id="viewAuditDataForm" name="viewAuditDataForm"
				class="form-horizontal" data-parsley-validate=""
				action="javascript:submitForm('/app/auditData/view', 'viewAuditDataForm', 'page-content-holder')"
				method='POST' modelAttribute="auditDataForm">
				<input type="hidden" path="<c:out value="${_csrf.parameterName}"/>"
					name="<c:out value="${_csrf.parameterName}"/>"
					value="<c:out value="${_csrf.token}"/>" />
				<h4>
					<span Style="color: blue"><spring:message
							code="viewAuditData-form.AuditDetails-label" /></span>
				</h4>

				<div class="divider"></div>
				<div id="viewAuditDataOpeartionDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewAuditData-form.operation-label" /> <span
						class="required"> :</span></label>
					<div id="viewAuditDataOpeartionDiv" class="col-sm-8 paddingt8">${auditDataForm.operation}</div>
				</div>
				<div id="viewAuditDataAuditObjectDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewAuditData-form.auditObject-label" /> <span
						class="required"> :</span></label>
					<div id="viewAuditDataAuditObjectDiv" class="col-sm-8 paddingt8">${auditDataForm.auditObject}</div>
				</div>
				<div id="viewAuditDataSrcDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewAuditData-form.srcAudit-label" /><span
						class="required">:</span>
					</label>
					<div id="viewAuditDataSrcDiv" class="col-sm-8 paddingt8">${auditDataForm.src}</div>
				</div>
				<div id="viewAuditDataDstAuditDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewAuditData-form.dstAudit-label" /> <span
						class="required">:</span>
					</label>
					<div id="viewAuditDataDstAuditDiv" class="col-sm-8 paddingt8">${auditDataForm.dst}</div>
				</div>
				<div id="viewAuditDataCreatedTimeDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewAuditData-form.CreatedTime-label" /> <span
						class="required">:</span>
					</label>
					<div id="viewAuditDataCreatedTimeDiv" class="col-sm-8 paddingt8">
						<fmt:formatDate value="${auditDataForm.createdTime}"
							pattern="dd-MM-yyyy" />
					</div>
				</div>
				<div id="viewAuditDataCreatedUserNameDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewAuditData-form.createdUserName-label" /><span
						class="required">:</span>
					</label>
					<div id="viewAuditDataCreatedUserNameDiv"
						class="col-sm-8 paddingt8">${auditDataForm.createdUserName}</div>
				</div>
				<div class="divider"></div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-8">
						<div class="example-box-wrapper">
							<button id="auditDataViewBackBt" name="auditDataViewBackBt"
								type="button" class="btn btn-info"
								onClick="javascript:getUrl('/app/auditData/list?reqPage=${reqPage}&pageSize=${pageSize}','page-content-holder');">
								<i class="glyph-icon icon-minus-square"></i>&nbsp;
								<spring:message code="viewAuditData-form.back-label" />
							</button>
							<%-- <button id="auditDataViewDownloadBt"
								name="auditDataViewDownloadBt" type="button"
								class="btn btn-info"
								onClick="window.location='/app/auditData/download/pdf?id=${auditDataForm.id}'">
								<i class="glyph-icon icon-arrow-circle-down"></i>&nbsp;
								<spring:message code="viewAuditData-form.download-label" />
							</button> --%>
						</div>
					</div>
				</div>
			</form:form>
		</div>
	</div>
</div>