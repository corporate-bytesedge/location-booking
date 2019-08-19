<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<script type="text/javascript"
	src="/assets-minified/widgets/input-mask/inputmask.js"></script>

<script type="text/javascript">
	/* Input masks */

	$(function() {
		"use strict";
		$(".input-mask").inputmask();
	});
</script>

<div id="page-content">
	<c:if test="${not empty message_error}">
		<div id="propertyRentPriceActionFailedMsgDiv"
			class="example-box-wrapper">
			<div class="alert alert-danger">
				<div class="bg-red alert-icon">
					<i class="glyph-icon icon-check"></i>
				</div>
				<div class="alert-content">
					<h4 class="alert-title">Action Failed</h4>
					<p>${message_error}</p>
				</div>
			</div>
		</div>
	</c:if>
	<div id="createPropertyRentPriceDiv" class="example-box-wrapper">
		<div style="float: left">
			<h3>
				<spring:message
					code="createPropertyRentPrice-form.createPropertyRentPrice-label" />
			</h3>
		</div>
		<div style="float: right">
			<button id="createPropertyRentPriceBackBt"
				name="createPropertyRentPriceBackBt" class="btn btn-info"
				onClick="javascript:loadContent('/app/propertyRentPrice/list','page-content-holder');">
				<i class="glyph-icon icon-minus-square"></i>&nbsp;
				<spring:message code="createPropertyRentPrice-form.back-button" />
			</button>
		</div>
	</div>
	<div class="divider"></div>
	<form:form id="createPropertyRentPriceForm" class="form-horizontal"
		data-parsley-validate=""
		action="javascript:submitForm('/app/propertyRentPrice/create', 'createPropertyRentPriceForm', 'page-content-holder')"
		method='POST' modelAttribute="propertyRentPriceForm">
		<div id="createPropertyRentPriceSlotTypeDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="createPropertyRentPrice-form.slotType-label" /> <span
				class="required">*</span>
			</label>
			<div id="createPropertyRentPriceSlotTypeDiv" class="col-sm-8">
				<form:select path="slotType" class="form-control" required="true">
					<c:forEach items="${SlotType}" var="ev">
						<form:option value="${ev}">
							<spring:message code="SlotType.${ev}" />
						</form:option>
					</c:forEach>
				</form:select>
				<form:errors path="slotType" class="parsley-required" />
			</div>
		</div>
		<div id="createPropertyRentPricePriceDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="createPropertyRentPrice-form.price-label" /> <span
				class="required">*</span>
			</label>
			<div id="createPropertyRentPricePriceDiv" class="col-sm-8">
				<form:input class="form-control" type="text"
					placeholder="Enter name" path="price" maxlength="64"
					required="true" />
				<form:errors path="price" class="parsley-required" />
			</div>
		</div>
		<div id="createPropertyRentPriceStateTypeDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="createPropertyRentPrice-form.state-label" /> <span
				class="required">*</span>
			</label>
			<div id="createPropertyRentPriceStateTypeDiv" class="col-sm-8">
				<form:select path="state" class="form-control" required="true">
					<c:forEach items="${StateType}" var="ev">
						<form:option value="${ev}">
							<spring:message code="StateType.${ev}" />
						</form:option>
					</c:forEach>
				</form:select>
				<form:errors path="state" class="parsley-required" />
			</div>
		</div>
		<div id="createPropertyRentPricePropertyIdDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="createPropertyRentPrice-form.propertyId-label" /> <span class="required">*</span>
			</label>
			<div id="createPropertyRentPricePropertyIdDiv" class="col-sm-8">
				<form:select class="form-control" multiple="false" path="propertyId"
					maxlength="1" required="true">
					<c:forEach var="obj" items="${propertyList}">
						<form:option id="${obj.id}" value="${obj.id}">${obj.name}</form:option>
					</c:forEach>
				</form:select>
				<form:errors path="propertyId" class="parsley-required" />
			</div>
		</div>
		<div id="createPropertyRentPricePurposeIdDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="createPropertyRentPrice-form.PurposeId-label" /> <span class="required">*</span>
			</label>
			<div id="createPropertyRentPricePurposeIdDiv" class="col-sm-8">
				<form:select class="form-control" multiple="false" path="purposeId"
					maxlength="1" required="true">
					<c:forEach var="obj" items="${purposeList}">
						<form:option id="${obj.id}" value="${obj.id}">${obj.name}</form:option>
					</c:forEach>
				</form:select>
				<form:errors path="purposeId" class="parsley-required" />
			</div>
		</div>
		<div id="createPropertyRentPriceCgstDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="createPropertyRentPrice-form.cgst-label" /> <span
				class="required">*</span>
			</label>
			<div id="createPropertyRentPriceCgstDiv" class="col-sm-8">
				<form:input class="form-control" type="text"
					placeholder="Enter name" path="cgst" maxlength="32"
					required="true" />
				<form:errors path="cgst" class="parsley-required" />
			</div>
		</div>
		<div id="createPropertyRentPriceSgstDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="createPropertyRentPrice-form.sgst-label" /> <span
				class="required">*</span>
			</label>
			<div id="createPropertyRentPriceSgstDiv" class="col-sm-8">
				<form:input class="form-control" type="text"
					placeholder="Enter name" path="sgst" maxlength="32"
					required="true" />
				<form:errors path="sgst" class="parsley-required" />
			</div>
		</div>
		<div id="createPropertyRentPriceServiceTaxDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="createPropertyRentPrice-form.serviceTax-label" /> <span
				class="required">*</span>
			</label>
			<div id="createPropertyRentPriceServiceTaxDiv" class="col-sm-8">
				<form:input class="form-control" type="text"
					placeholder="Enter name" path="serviceTax" maxlength="32"
					required="true" />
				<form:errors path="serviceTax" class="parsley-required" />
			</div>
		</div>
		<%-- <div id="createPropertyRentPriceCapthaDiv" class="form-group">
			<div class="col-sm-2 control-label">
				<img id="newCaptha" alt="Loading"
					src="/apu/captcha.jpg?ts=${javaDate.time}%>" width="120"
					height="48" /> <span title="Refresh and try another"
					onClick="javascript:refreshCaptcha('newCaptha');"> <i
					class="glyph-icon icon-refresh"
					style="cursor: pointer; padding-bottom: 10px;"></i>
				</span>
			</div>
			<br>
			<div id="createPropertyRentPriceCapthaDiv" class="col-sm-2">
				<span class="input-group-addon addon-inside bg-white font-primary"
					style="margin-left: 15px;"> <i
					class="glyph-icon icon-linecons-key"></i>
				</span>
				<form:input type="text" class="form-control" path="captchaText"
					maxlength="6" placeholder="Captcha" required="true" />
				<form:errors path="captchaText" class="parsley-required" />
			</div>
		</div> --%>
		<div class="divider"></div>
		<div id="createPropertyRentPriceButtonsDiv" class="form-group">
			<div class="col-sm-offset-2 col-sm-8">
				<div class="example-box-wrapper">
					<button id="propertyRentPriceCreateSubmitBt"
						name="propertyRentPriceCreateSubmitBt" type="submit"
						class="btn btn-success">
						<i class="glyph-icon icon-plus-square"></i>&nbsp;
						<spring:message code="createPropertyRentPrice-form.submit-button" />
					</button>
					<button id="propertyRentPriceCreateRestBt"
						name="propertyRentPriceCreateResetBt" type="reset"
						class="btn btn-warning">
						<i class="glyph-icon icon-minus-square"></i>&nbsp;
						<spring:message code="createPropertyRentPrice-form.reset-button" />
					</button>
					<button id="propertyRentPriceCreateBackBt"
						name="propertyRentPriceCreateBackBt" type="button"
						class="btn btn-info"
						onClick="javascript:getUrl('/app/propertyRentPrice/list','page-content-holder');">
						<i class="glyph-icon icon-minus-square"></i>&nbsp;
						<spring:message code="createPropertyRentPrice-form.back-button" />
					</button>
				</div>
			</div>
		</div>
	</form:form>
</div>
