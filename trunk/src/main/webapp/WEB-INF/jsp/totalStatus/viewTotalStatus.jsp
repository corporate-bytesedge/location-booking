<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<div id="page-content">
	<div class="example-box-wrapper">
		<div id="viewTotalStatusDiv" class="example-box-wrapper">
			<div style="float: left;">
				<h3>
					<spring:message code="viewTotalStatus-form.viewTotalStatus-label" />
				</h3>
			</div>
			<div style="float: right">
				<button id="totalStatusViewBackBt" name="totalStatusViewBackBt" type="back"
					class="btn btn-info"
					onClick="javascript:getUrl('/app/totalStatus/list?reqPage=${reqPage}&pageSize=${pageSize}','page-content-holder');">
					<i class="glyph-icon icon-minus-square"></i>&nbsp;
					<spring:message code="viewTotalStatus-form.back-button" />
				</button>
				<%-- <button id="totalStatusViewDownloadBt" name="totalStatusViewDownloadBt"
					type="download" class="btn btn-warning"
					onClick="window.location='/app/totalStatus/download/pdf?id=${totalStatusForm.id}'">
					<i class="glyph-icon icon-arrow-circle-down"></i>&nbsp;
					<spring:message code="viewTotalStatus-form.download-button" />
				</button> --%>
			</div>
			<br>
			<br>
			<form:form id="viewTotalStatusForm" name="viewTotalStatusForm"
				class="form-horizontal" data-parsley-validate=""
				action="javascript:submitForm('/app/totalStatus/view', 'viewTotalStatusForm', 'page-content-holder')"
				method='POST' modelAttribute="totalStatusForm">
				<input type="hidden" path="<c:out value="${_csrf.parameterName}"/>"
					name="<c:out value="${_csrf.parameterName}"/>"
					value="<c:out value="${_csrf.token}"/>" />
				<div class="divider"></div>
				<h4>
					<span Style="color: blue"><spring:message
							code="viewTotalStatus-form.TotalStatusDetails-label" /></span>
				</h4>
				<div class="divider"></div>
				<div id="viewTotalStatusTotalStatusNameDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewTotalStatus-form.name-label" /> <span class="required">:</span>
					</label>
					<div id="viewTotalStatusNameDiv" class="col-sm-8 paddingt8">
						${totalStatusForm.name}</div>
				</div>
				<div id="viewTotalStatusEmailIdDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewTotalStatus-form.emailId-label" /><span class="required">:</span>
					</label>
					<div id="viewTotalStatusEmailIdDiv" class="col-sm-8 paddingt8">
						${totalStatusForm.emailId}</div>
				</div>
				<div id="viewTotalStatusMobileNoDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewTotalStatus-form.mobileNo-label" /> <span class="required">:</span>
					</label>
					<div id="viewTotalStatusMobileNoDiv" class="col-sm-8 paddingt8">
					${ totalStatusForm.mobileNo}</div>
				</div>
				<div id="viewTotalStatusVenueDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewTotalStatus-form.venue-label" />:<span
						class="required"></span>
					</label>
					<div id="viewTotalStatusVenueDiv" class="col-sm-8 paddingt8">
						<spring:message
							code="totalStatusList-form.venue.${totalStatusForm.venue}-label" /></div>
				</div>
				<div id="viewTotalStatusVenueDateDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewTotalStatus-form.venueDate-label" /><span class="required">:</span>
					</label>
					<div id="viewTotalStatusVenueDateDiv" class="col-sm-8 paddingt8">
						${totalStatusForm.venueDate}</div>
				</div>
				<div id="viewTotalStatusPurposeDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewTotalStatus-form.purpose-label" />:<span
						class="required"></span>
					</label>
					<div id="viewTotalStatusPurposeDiv" class="col-sm-8 paddingt8">
						<spring:message
							code="totalStatusList-form.purpose.${totalStatusForm.purpose}-label" /></div>
				</div>
				<div id="viewTotalStatusReferredByDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewTotalStatus-form.referredBy-label" /> <span class="required">:</span>
					</label>
					<div id="viewTotalStatusReferredByDiv" class="col-sm-8 paddingt8">
						${totalStatusForm.referredBy}</div>
				</div>
				<div id="viewTotalStatusCreatedUserNameDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewTotalStatus-form.createdUserId-label" /><span class="required">:</span>
					</label>
					<div id="viewTotalStatusCreatedUserNameDiv" class="col-sm-8 paddingt8">
						${totalStatusForm.createdUserName}</div>
				</div>
				<div id="viewTotalStatusCreatedTimeDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewTotalStatus-form.createdTime-label" /><span class="required">:</span>
					</label>
					<div id="viewTotalStatusCreatedTimeDiv" class="col-sm-8 paddingt8">
						<fmt:formatDate value="${totalStatusForm.createdTime}"
							pattern="dd-MMM-yyyy HH-mm-ss" />
					</div>
				</div>
				<div id="viewTotalStatusUpdatedUserNameDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewTotalStatus-form.updatedUserId-label" /><span class="required">:</span>
					</label>
					<div id="viewTotalStatusUpdatedUserNameDiv" class="col-sm-8 paddingt8">
						${totalStatusForm.updatedUserName}</div>
				</div>
				<div id="viewTotalStatusUpdatedTimeDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewTotalStatus-form.updatedTime-label" /><span class="required">:</span>
					</label>
					<div id="viewTotalStatusUpdatedTimeDiv" class="col-sm-8 paddingt8">
						<fmt:formatDate value="${totalStatusForm.updatedTime}"
							pattern="dd-MMM-yyyy HH-mm-ss" />
					</div>
				</div>
				<div class="divider"></div>
				<div id="totalStatusButtonsDiv" class="form-group">
					<div class="col-sm-offset-2 col-sm-8">
						<div class="example-box-wrapper">
							<button id="totalStatusViewBackBt" name="totalStatusViewBackBt" type="back"
								class="btn btn-info"
								onClick="javascript:getUrl('/app/totalStatus/list?reqPage=${reqPage}&pageSize=${pageSize}','page-content-holder');">
								<i class="glyph-icon icon-minus-square"></i>&nbsp;
								<spring:message code="viewTotalStatus-form.back-button" />
							</button>
							<%-- <button id="totalStatusViewDownloadBt" name="totalStatusViewDownloadBt"
								type="download" class="btn btn-warning"
								onClick="window.location='/app/totalStatus/download/pdf?id=${totalStatusForm.id}'">
								<i class="glyph-icon icon-arrow-circle-down"></i>&nbsp;
								<spring:message code="viewTotalStatus-form.download-button" />
							</button> --%>
						</div>
					</div>
				</div>
			</form:form>
		</div>
	</div>
</div>