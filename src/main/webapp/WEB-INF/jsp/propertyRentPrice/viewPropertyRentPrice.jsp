<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<script type="text/javascript"
	src="/assets-minified/widgets/input-mask/inputmask.js"></script>
<div id="page-content">
	<div class="example-box-wrapper">
		<div id="viewPropertyRentPriceDiv" class="example-box-wrapper">
			<div style="float: left;">
				<h3>
					<spring:message code="viewPropertyRentPrice-form.viewPropertyRentPrice-label" />
				</h3>
			</div>
			<div style="float: right">
				<button id="propertyRentPriceViewBackBt" name="propertyRentPriceViewBackBt" type="back"
					class="btn btn-info"
					onClick="javascript:getUrl('/app/propertyRentPrice/list?reqPage=${reqPage}&pageSize=${pageSize}','page-content-holder');">
					<i class="glyph-icon icon-minus-square"></i>&nbsp;
					<spring:message code="viewPropertyRentPrice-form.back-button" />
				</button>
				<%-- <button id="propertyRentPriceViewDownloadBt" name="propertyRentPriceViewDownloadBt"
					type="download" class="btn btn-warning"
					onClick="window.location='/app/propertyRentPrice/download/pdf?id=${propertyRentPriceForm.id}'">
					<i class="glyph-icon icon-arrow-circle-down"></i>&nbsp;
					<spring:message code="viewPropertyRentPrice-form.download-button" />
				</button> --%>
			</div>
			<br>
			<br>
			<form:form id="viewPropertyRentPriceForm" name="viewPropertyRentPriceForm"
				class="form-horizontal" data-parsley-validate=""
				action="javascript:submitForm('/app/propertyRentPrice/view', 'viewPropertyRentPriceForm', 'page-content-holder')"
				method='POST' modelAttribute="propertyRentPriceForm">
				<input type="hidden" path="<c:out value="${_csrf.parameterName}"/>"
					name="<c:out value="${_csrf.parameterName}"/>"
					value="<c:out value="${_csrf.token}"/>" />
				<div class="divider"></div>
				<h4>
					<span Style="color: blue"><spring:message
							code="viewPropertyRentPrice-form.PropertyRentPriceDetails-label" /></span>
				</h4>
				<div class="divider"></div>
				<div id="viewPropertyRentPriceSlotTypeDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewPropertyRentPrice-form.slotType-label" /> <span class="required">:</span>
					</label>
					<div id="viewPropertyRentPriceSlotTypeDiv" class="col-sm-8 paddingt8">
						${propertyRentPriceForm.slotType}</div>
				</div>
				<div id="viewPropertyRentPricePriceDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewPropertyRentPrice-form.price-label" />:<span
						class="required"></span>
					</label>
					<div id="viewPropertyRentPricePriceDiv" class="col-sm-8 paddingt8">
						${ propertyRentPriceForm.price}</div>
				</div>
				<div id="viewPropertyRentPriceStateDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewPropertyRentPrice-form.State-label" />:<span
						class="required"></span>
					</label>
					<div id="viewPropertyRentPriceStateDiv" class="col-sm-8 paddingt8">
						${ propertyRentPriceForm.state}</div>
				</div>
				<div id="viewPropertyRentPricePropertyIdDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewPropertyRentPrice-form.propertyId-label" /> <span class="required">:</span>
					</label>
					<div id="viewPropertyRentPricePropertyIdDiv" class="col-sm-8 paddingt8">
						${propertyRentPriceForm.propertyName}</div>
				</div>
				<div id="viewPropertyRentPricePurposeIdDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewPropertyRentPrice-form.PurposeId-label" />:<span
						class="required"></span>
					</label>
					<div id="viewPropertyRentPricePurposeIdDiv" class="col-sm-8 paddingt8">
						${ propertyRentPriceForm.purposeName}</div>
				</div>
				<div id="viewPropertyRentPriceOrgIdDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewPropertyRentPrice-form.orgId-label" /> <span class="required">:</span>
					</label>
					<div id="viewPropertyRentPriceOrgIdDiv" class="col-sm-8 paddingt8">
						${propertyRentPriceForm.orgName}</div>
				</div>
				<div id="viewPropertyRentPriceCreatedUserNameDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewPropertyRentPrice-form.createdUserId-label" /><span class="required">:</span>
					</label>
					<div id="viewPropertyRentPriceCreatedUserNameDiv" class="col-sm-8 paddingt8">
						${propertyRentPriceForm.createdUserName}</div>
				</div>
				<div id="viewPropertyRentPriceCreatedTimeDiv" class="form-group">
					<label for="" class="col-sm-2 control-label"> <spring:message
							code="viewPropertyRentPrice-form.createdTime-label" /><span class="required">:</span>
					</label>
					<div id="viewPropertyRentPriceCreatedTimeDiv" class="col-sm-8 paddingt8">
						<fmt:formatDate value="${propertyRentPriceForm.createdTime}"
							pattern="dd-MMM-yyyy HH-mm-ss" />
					</div>
				</div>
				<div class="divider"></div>
				<div id="propertyRentPriceButtonsDiv" class="form-group">
					<div class="col-sm-offset-2 col-sm-8">
						<div class="example-box-wrapper">
							<button id="propertyRentPriceViewBackBt" name="propertyRentPriceViewBackBt" type="back"
								class="btn btn-info"
								onClick="javascript:getUrl('/app/propertyRentPrice/list?reqPage=${reqPage}&pageSize=${pageSize}','page-content-holder');">
								<i class="glyph-icon icon-minus-square"></i>&nbsp;
								<spring:message code="viewPropertyRentPrice-form.back-button" />
							</button>
							<%-- <button id="propertyRentPriceViewDownloadBt" name="propertyRentPriceViewDownloadBt"
								type="download" class="btn btn-warning"
								onClick="window.location='/app/propertyRentPrice/download/pdf?id=${propertyRentPriceForm.id}'">
								<i class="glyph-icon icon-arrow-circle-down"></i>&nbsp;
								<spring:message code="viewPropertyRentPrice-form.download-button" />
							</button> --%>
						</div>
					</div>
				</div>
			</form:form>
		</div>
	</div>
</div>