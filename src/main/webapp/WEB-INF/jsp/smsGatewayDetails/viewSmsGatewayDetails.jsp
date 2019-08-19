<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<script type="text/javascript"
	src="/assets-minified/widgets/input-mask/inputmask.js"></script>
<div id="page-content">
	<div class="example-box-wrapper">
		<div id="viewSmsGatewayDetailsDiv" class="example-box-wrapper">
			<div style="float: left;">
				<h3>
					<spring:message
						code="viewSmsGatewayDetails-form.viewSmsGatewayDetails-label" />
				</h3>
			</div>
			<div style="float: right">
				<button id="smsGatewayDetailsViewBackBt"
					name="smsGatewayDetailsViewBackBt" type="back"
					class="btn btn-info"
					onClick="javascript:getUrl('/app/smsGatewayDetails/list?reqPage=${reqPage}&pageSize=${pageSize}','page-content-holder');">
					<i class="glyph-icon icon-minus-square"></i>&nbsp;
					<spring:message code="viewSmsGatewayDetails-form.back-button" />
				</button>
				<%-- <button id="smsGatewayDetailsViewDownloadBt"
					name="smsGatewayDetailsViewDownloadBt" type="download"
					class="btn btn-warning"
					onClick="window.location='/app/smsGatewayDetails/download/pdf?id=${smsGatewayDetailsForm.id}'">
					<i class="glyph-icon icon-arrow-circle-down"></i>&nbsp;
					<spring:message
						code="viewSmsGatewayDetails-form.download-button" />
				</button> --%>
			</div>
			<br> <br>
			<form:form id="viewSmsGatewayDetailsForm"
				name="viewSmsGatewayDetailsForm" class="form-horizontal"
				data-parsley-validate=""
				action="javascript:submitForm('/app/smsGatewayDetails/view', 'viewSmsGatewayDetailsForm', 'page-content-holder')"
				method='POST' modelAttribute="smsGatewayDetailsForm">
				<input type="hidden" path="<c:out value="${_csrf.parameterName}"/>"
					name="<c:out value="${_csrf.parameterName}"/>"
					value="<c:out value="${_csrf.token}"/>" />
				<div class="divider"></div>
				<div id="viewSmsGatewayDetailsLoginDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewSmsGatewayDetails-form.senderId-label" /> <span
						class="required">:</span>
					</label>
					<div id="viewSmsGatewayDetailsLoginDiv"
						class="col-sm-8 paddingt8">${smsGatewayDetailsForm.senderId}</div>
				</div>
				<div id="viewSmsGatewayDetailsPasswordDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewSmsGatewayDetails-form.route-label" />:<span
						class="required"></span>
					</label>
					<div id="viewSmsGatewayDetailsPasswordDiv"
						class="col-sm-8 paddingt8">${smsGatewayDetailsForm.route}</div>
				</div>
				<div id="viewSmsGatewayDetailsCreatedUserNameDiv"
					class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewSmsGatewayDetails-form.createdUserId-label" /><span
						class="required">:</span>
					</label>
					<div id="viewSmsGatewayDetailsCreatedUserNameDiv"
						class="col-sm-8 paddingt8">
						${smsGatewayDetailsForm.createdUserName}</div>
				</div>
				<div id="viewSmsGatewayDetailsCreatedTimeDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewSmsGatewayDetails-form.createdTime-label" /><span
						class="required">:</span>
					</label>
					<div id="viewSmsGatewayDetailsCreatedTimeDiv"
						class="col-sm-8 paddingt8">
						<fmt:formatDate value="${smsGatewayDetailsForm.createdTime}"
							pattern="dd-MMM-yyyy HH-mm-ss" />
					</div>
				</div>
				<div id="viewSmsGatewayDetailsUpdatedUserNameDiv"
					class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewSmsGatewayDetails-form.updatedUserId-label" /><span
						class="required">:</span>
					</label>
					<div id="viewSmsGatewayDetailsUpdatedUserNameDiv"
						class="col-sm-8 paddingt8">
						${smsGatewayDetailsForm.updatedUserName}</div>
				</div>
				<div id="viewSmsGatewayDetailsUpdatedTimeDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewSmsGatewayDetails-form.updatedTime-label" /><span
						class="required">:</span>
					</label>
					<div id="viewSmsGatewayDetailsUpdatedTimeDiv"
						class="col-sm-8 paddingt8">
						<fmt:formatDate value="${smsGatewayDetailsForm.updatedTime}"
							pattern="dd-MMM-yyyy HH-mm-ss" />
					</div>
				</div>
				<div class="divider"></div>
				<div id="smsGatewayDetailsButtonsDiv" class="form-group">
					<div class="col-sm-offset-2 col-sm-8">
						<div class="example-box-wrapper">
							<button id="smsGatewayDetailsViewBackBt"
								name="smsGatewayDetailsViewBackBt" type="back"
								class="btn btn-info"
								onClick="javascript:getUrl('/app/smsGatewayDetails/list?reqPage=${reqPage}&pageSize=${pageSize}','page-content-holder');">
								<i class="glyph-icon icon-minus-square"></i>&nbsp;
								<spring:message
									code="viewSmsGatewayDetails-form.back-button" />
							</button>
							<%-- <button id="smsGatewayDetailsViewDownloadBt"
								name="smsGatewayDetailsViewDownloadBt" type="download"
								class="btn btn-warning"
								onClick="window.location='/app/smsGatewayDetails/download/pdf?id=${smsGatewayDetailsForm.id}'">
								<i class="glyph-icon icon-arrow-circle-down"></i>&nbsp;
								<spring:message
									code="viewSmsGatewayDetails-form.download-button" />
							</button> --%>
						</div>
					</div>
				</div>
			</form:form>
		</div>
	</div>
</div>