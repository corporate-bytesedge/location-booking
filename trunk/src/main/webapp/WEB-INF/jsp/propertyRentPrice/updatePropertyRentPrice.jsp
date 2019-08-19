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
	<div class="example-box-wrapper">
		<div id="updatePropertyRentPriceDiv" class="example-box-wrapper">
			<div style="float: left">
				<h3>
					<spring:message
						code="updatePropertyRentPrice-form.updatePropertyRentPrice-label" />
				</h3>
			</div>
			<div style="float: right">
				<button id="propertyRentPriceUpdateBackBt"
					name="propertyRentPriceUpdateBackBt" type="button"
					class="btn btn-info"
					onClick="javascript:getUrl('/app/propertyRentPrice/list?reqPage=${reqPage}&pageSize=${pageSize}','page-content-holder');">
					<i class="glyph-icon icon-minus-square"></i>&nbsp;
					<spring:message code="updatePropertyRentPrice-form.back-button" />
				</button>
			</div>
		</div>
		<div class="divider"></div>
		<form:form id="updatePropertyRentPriceForm"
			name="updatePropertyRentPriceForm" class="form-horizontal"
			data-parsley-validate=""
			action="javascript:submitForm('/app/propertyRentPrice/update?reqPage=${reqPage}&pageSize=${pageSize}', 'updatePropertyRentPriceForm', 'page-content-holder')"
			method='POST' modelAttribute="propertyRentPriceForm">
			<input type="hidden" path="<c:out value="${_csrf.parameterName}"/>"
				name="<c:out value="${_csrf.parameterName}"/>"
				value="<c:out value="${_csrf.token}"/>" />
				<div id="updatePropertyRentPriceSlotTypeDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="updatePropertyRentPrice-form.slotType-label" /> <span
				class="required">*</span>
			</label>
			<div id="updatePropertyRentPriceSlotTypeDiv" class="col-sm-8">
				<form:select path="slotType" class="form-control" required="true" readonly="true">
					<c:forEach items="${SlotType}" var="ev">
						<form:option value="${ev}">
							<spring:message code="SlotType.${ev}" />
						</form:option>
					</c:forEach>
				</form:select>
				<form:errors path="slotType" class="parsley-required" />
			</div>
		</div>
		<div id="updatePropertyRentPricePriceDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="updatePropertyRentPrice-form.price-label" /> <span
				class="required">*</span>
			</label>
			<div id="updatePropertyRentPricePriceDiv" class="col-sm-8">
				<form:input class="form-control" type="text"
					placeholder="Enter name" path="price" maxlength="64"
					required="true"/>
				<form:errors path="price" class="parsley-required" />
			</div>
		</div>
		<div id="updatePropertyRentPriceStateTypeDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="updatePropertyRentPrice-form.state-label" /> <span
				class="required">*</span>
			</label>
			<div id="updatePropertyRentPriceStateTypeDiv" class="col-sm-8">
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
		<div id="updatePropertyRentPricePropertyIdDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="updatePropertyRentPrice-form.propertyId-label" /> <span class="required">*</span>
			</label>
			<div id="updatePropertyRentPricePropertyIdDiv" class="col-sm-8">
				<form:select class="form-control" multiple="false" path="propertyId"
					maxlength="1" required="true" readonly="true">
					<c:forEach var="obj" items="${propertyList}">
						<form:option id="${obj.id}" value="${obj.id}">${obj.name}</form:option>
					</c:forEach>
				</form:select>
				<form:errors path="propertyId" class="parsley-required" />
			</div>
		</div>
		<div id="updatePropertyRentPricePurposeIdDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="updatePropertyRentPrice-form.PurposeId-label" /> <span class="required">*</span>
			</label>
			<div id="updatePropertyRentPricePurposeIdDiv" class="col-sm-8">
				<form:select class="form-control" multiple="false" path="purposeId"
					maxlength="1" required="true" readonly="true">
					<c:forEach var="obj" items="${purposeList}">
						<form:option id="${obj.id}" value="${obj.id}">${obj.name}</form:option>
					</c:forEach>
				</form:select>
				<form:errors path="purposeId" class="parsley-required" />
			</div>
		</div>
		<div id="updatePropertyRentPriceCgstDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="updatePropertyRentPrice-form.cgst-label" /> <span
				class="required">*</span>
			</label>
			<div id="updatePropertyRentPriceCgstDiv" class="col-sm-8">
				<form:input class="form-control" type="text"
					placeholder="Enter name" path="cgst" maxlength="32"
					required="true" />
				<form:errors path="cgst" class="parsley-required" />
			</div>
		</div>
		<div id="updatePropertyRentPriceSgstDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="updatePropertyRentPrice-form.sgst-label" /> <span
				class="required">*</span>
			</label>
			<div id="updatePropertyRentPriceSgstDiv" class="col-sm-8">
				<form:input class="form-control" type="text"
					placeholder="Enter name" path="sgst" maxlength="32"
					required="true" />
				<form:errors path="sgst" class="parsley-required" />
			</div>
		</div>
		<div id="updatePropertyRentPriceServiceTaxDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="updatePropertyRentPrice-form.serviceTax-label" /> <span
				class="required">*</span>
			</label>
			<div id="updatePropertyRentPriceServiceTaxDiv" class="col-sm-8">
				<form:input class="form-control" type="text"
					placeholder="Enter name" path="serviceTax" maxlength="32"
					required="true" />
				<form:errors path="serviceTax" class="parsley-required" />
			</div>
		</div>
			<%-- <div id="updatePropertyRentPriceCapthaDiv" class="form-group">
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
				<div id="updatePropertyRentPriceCapthaDiv" class="col-sm-2">
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
			<div id="propertyRentPriceButtonsDiv" class="form-group">
				<div class="col-sm-offset-2 col-sm-8">
					<div class="example-box-wrapper">
						<button id="propertyRentPriceUpdateSubmitBt"
							name="propertyRentPriceUpdateSubmitBt" type="submit"
							class="btn btn-success">
							<i class="glyph-icon icon-plus-square"></i>&nbsp;
							<spring:message
								code="updatePropertyRentPrice-form.submit-button" />
						</button>
						<button id="propertyRentPriceUpdateResetBt"
							name="propertyRentPriceUpdateResetBt" type="reset"
							class="btn btn-warning">
							<i class="glyph-icon icon-minus-square"></i>&nbsp;
							<spring:message
								code="updatePropertyRentPrice-form.reset-button" />
						</button>
						<button id="propertyRentPriceUpdateBackBt"
							name="propertyRentPriceUpdateBackBt" type="button"
							class="btn btn-info"
							onClick="javascript:getUrl('/app/propertyRentPrice/list?reqPage=${reqPage}&pageSize=${pageSize}','page-content-holder');">
							<i class="glyph-icon icon-minus-square"></i>&nbsp;
							<spring:message
								code="updatePropertyRentPrice-form.back-button" />
						</button>
					</div>
				</div>
			</div>
		</form:form>
	</div>
</div>
</div>