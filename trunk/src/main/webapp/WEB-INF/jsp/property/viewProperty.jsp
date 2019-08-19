<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<div id="page-content">
	<div class="example-box-wrapper">
		<div id="viewPropertyDiv" class="example-box-wrapper">
			<div style="float: left;">
				<h3>
					<spring:message code="viewProperty-form.viewProperty-label" />
				</h3>
			</div>
			<div style="float: right">
				<button id="propertyViewBackBt" name="propertyViewBackBt" type="back"
					class="btn btn-info"
					onClick="javascript:getUrl('/app/property/list?reqPage=${reqPage}&pageSize=${pageSize}','page-content-holder');">
					<i class="glyph-icon icon-minus-square"></i>&nbsp;
					<spring:message code="viewProperty-form.back-button" />
				</button>
				<%-- <button id="propertyViewDownloadBt" name="propertyViewDownloadBt"
					type="download" class="btn btn-warning"
					onClick="window.location='/app/property/download/pdf?id=${propertyForm.id}'">
					<i class="glyph-icon icon-arrow-circle-down"></i>&nbsp;
					<spring:message code="viewProperty-form.download-button" />
				</button> --%>
			</div>
			<br>
			<br>
			<form:form id="viewPropertyForm" name="viewPropertyForm"
				class="form-horizontal" data-parsley-validate=""
				action="javascript:submitForm('/app/property/view', 'viewPropertyForm', 'page-content-holder')"
				method='POST' modelAttribute="propertyForm">
				<input type="hidden" path="<c:out value="${_csrf.parameterName}"/>"
					name="<c:out value="${_csrf.parameterName}"/>"
					value="<c:out value="${_csrf.token}"/>" />
				<div class="divider"></div>
				<h4>
					<span Style="color: blue"><spring:message
							code="viewProperty-form.PropertyDetails-label" /></span>
				</h4>
				<div class="divider"></div>
				<div id="viewPropertyPropertyIdDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewProperty-form.propertyId-label" /> <span class="required">:</span>
					</label>
					<div id="viewPropertyPropertyIdDiv" class="col-sm-8 paddingt8">
						${propertyForm.propertyId}</div>
				</div>
				<div id="viewPropertyUniqueIdDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewProperty-form.uniqueId-label" /> <span class="required">:</span>
					</label>
					<div id="viewPropertyUniqueIdDiv" class="col-sm-8 paddingt8">
						${propertyForm.uniqueId}</div>
				</div>
				<div id="viewPropertyNameDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewProperty-form.name-label" /> <span class="required">:</span>
					</label>
					<div id="viewPropertyNameDiv" class="col-sm-8 paddingt8">${ propertyForm.name}</div>
				</div>
				<div id="viewPropertyStateDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewProperty-form.State-label" />:<span
						class="required"></span>
					</label>
					<div id="viewStateDiv" class="col-sm-8 paddingt8">
						${ propertyForm.state}</div>
				</div>
				<div id="viewPropertyDaysLimitDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewProperty-form.DaysLimit-label" />:<span
						class="required"></span>
					</label>
					<div id="viewDaysLimitDiv" class="col-sm-8 paddingt8">
						${ propertyForm.venueDaysLimit}</div>
				</div>
				<div id="viewPropertyDescrDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewProperty-form.descr-label" /> <span class="required">:</span>
					</label>
					<div id="viewPropertyEmailDiv" class="col-sm-8 paddingt8">
						${propertyForm.descr}</div>
				</div>
				<div id="viewPropertyCreatedUserNameDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewProperty-form.createdUserId-label" /><span class="required">:</span>
					</label>
					<div id="viewPropertyCreatedUserNameDiv" class="col-sm-8 paddingt8">
						${propertyForm.createdUserName}</div>
				</div>
				<div id="viewPropertyCreatedTimeDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewProperty-form.createdTime-label" /><span class="required">:</span>
					</label>
					<div id="viewPropertyCreatedTimeDiv" class="col-sm-8 paddingt8">
						<fmt:formatDate value="${propertyForm.createdTime}"
							pattern="dd-MMM-yyyy HH-mm-ss" />
					</div>
				</div>
				<div id="viewPropertyUpdatedUserNameDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewProperty-form.updatedUserId-label" /><span class="required">:</span>
					</label>
					<div id="viewPropertyUpdatedUserNameDiv" class="col-sm-8 paddingt8">
						${propertyForm.updatedUserName}</div>
				</div>
				<div id="viewPropertyUpdatedTimeDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewProperty-form.updatedTime-label" /><span class="required">:</span>
					</label>
					<div id="viewPropertyUpdatedTimeDiv" class="col-sm-8 paddingt8">
						<fmt:formatDate value="${propertyForm.updatedTime}"
							pattern="dd-MMM-yyyy HH-mm-ss" />
					</div>
				</div>
				<div class="divider"></div>
				<div id="propertyButtonsDiv" class="form-group">
					<div class="col-sm-offset-2 col-sm-8">
						<div class="example-box-wrapper">
							<button id="propertyViewBackBt" name="propertyViewBackBt" type="back"
								class="btn btn-info"
								onClick="javascript:getUrl('/app/property/list?reqPage=${reqPage}&pageSize=${pageSize}','page-content-holder');">
								<i class="glyph-icon icon-minus-square"></i>&nbsp;
								<spring:message code="viewProperty-form.back-button" />
							</button>
							<%-- <button id="propertyViewDownloadBt" name="propertyViewDownloadBt"
								type="download" class="btn btn-warning"
								onClick="window.location='/app/property/download/pdf?id=${propertyForm.id}'">
								<i class="glyph-icon icon-arrow-circle-down"></i>&nbsp;
								<spring:message code="viewProperty-form.download-button" />
							</button> --%>
						</div>
					</div>
				</div>
			</form:form>
		</div>
	</div>
</div>