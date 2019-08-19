<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<script type="text/javascript"
	src="/assets-minified/widgets/input-mask/inputmask.js"></script>

<script type="text/javascript">
    /* Input masks */

    $(function() { "use strict";
        $(".input-mask").inputmask();
    });

</script>

<div id="page-content">
	<c:if test="${not empty message_error}">
		<div id="propertyActionFailedMsgDiv" class="example-box-wrapper">
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
	<div id="createPropertyDiv" class="example-box-wrapper">
		<div style="float: left">
			<h3>
				<spring:message code="createProperty-form.createProperty-label" />
			</h3>
		</div>
		<div style="float: right">
			<button id="createPropertyBackBt" name="createPropertyBackBt"
				class="btn btn-info"
				onClick="javascript:loadContent('/app/property/list','page-content-holder');">
				<i class="glyph-icon icon-minus-square"></i>&nbsp;
				<spring:message code="createProperty-form.back-button" />
			</button>
		</div>
	</div>
	<div class="divider"></div>
	<form:form id="createPropertyForm" class="form-horizontal"
		data-parsley-validate=""
		action="javascript:submitForm('/app/property/create', 'createPropertyForm', 'page-content-holder')"
		method='POST' modelAttribute="propertyForm">
		<div id="createPropertyStateDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="createProperty-form.state-label" /> <span class="required">*</span>
			</label>
			<div id="createPropertyGenderDiv" class="col-sm-8">
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
		<div id="createPropertyNameDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="createProperty-form.Name-label" /> <span class="required">*</span>
			</label>
			<div id="createPropertyNameDiv" class="col-sm-8">
				<form:input class="form-control" type="text"
					placeholder="Enter name" path="name" maxlength="64" required="true" />
				<form:errors path="name" class="parsley-required" />
			</div>
		</div>
		<div id="createPropertyPropertyIdDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="createProperty-form.propertyId-label" /> <span
				class="required">*</span>
			</label>
			<div id="createPropertyPropertyIdDiv" class="col-sm-8">
				<form:input class="form-control" type="text"
					placeholder="Enter unique Id" path="propertyId" maxlength="16"
					required="true" />
				<form:errors path="propertyId" class="parsley-required" />
			</div>
		</div>
		<div id="createPropertyVenueDaysLimitDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="createProperty-form.veueDaysLimit-label" /> <span
				class="required">*</span>
			</label>
			<div id="createPropertyVenueDaysLimitDiv" class="col-sm-8">
				<form:input class="form-control" type="text"
					placeholder="Enter no. of days to block" path="venueDaysLimit" maxlength="16"
					required="true" />
				<form:errors path="venueDaysLimit" class="parsley-required" />
			</div>
		</div>
		<div id="createPropertyUniqueIdDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="createProperty-form.uniqueId-label" /> <span
				class="required">*</span>
			</label>
			<div id="createPropertyUniqueIdDiv" class="col-sm-8">
				<form:input class="form-control" type="text"
					placeholder="Enter unique Id" path="uniqueId" maxlength="16"
					required="true" />
				<form:errors path="uniqueId" class="parsley-required" />
			</div>
		</div>
		<div id="createPropertyDescrDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="createProperty-form.descr-label" /> <span class="required">*</span>
			</label>
			<div id="createPropertyDescrDiv" class="col-sm-8">
				<form:textarea class="form-control" path="descr"
					placeholder="Enter description" maxlength="1024"
					data-parsley-trigger="keyup" data-parsley-length="[20, 1024]"
					data-parsley-validation-threshold="10"></form:textarea>
				<form:errors path="descr" class="parsley-required" />
			</div>
		</div>
		<%-- <div id="createPropertyCapthaDiv" class="form-group">
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
			<div id="createPropertyCapthaDiv" class="col-sm-2">
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
		<div id="createPropertyButtonsDiv" class="form-group">
			<div class="col-sm-offset-2 col-sm-8">
				<div class="example-box-wrapper">
					<button id="propertyCreateSubmitBt" name="propertyCreateSubmitBt"
						type="submit" class="btn btn-success">
						<i class="glyph-icon icon-plus-square"></i>&nbsp;
						<spring:message code="createProperty-form.submit-button" />
					</button>
					<button id="propertyCreateRestBt" name="propertyCreateResetBt"
						type="reset" class="btn btn-warning">
						<i class="glyph-icon icon-minus-square"></i>&nbsp;
						<spring:message code="createProperty-form.reset-button" />
					</button>
					<button id="propertyCreateBackBt" name="propertyCreateBackBt"
						type="button" class="btn btn-info"
						onClick="javascript:getUrl('/app/property/list','page-content-holder');">
						<i class="glyph-icon icon-minus-square"></i>&nbsp;
						<spring:message code="createProperty-form.back-button" />
					</button>
				</div>
			</div>
		</div>
	</form:form>
</div>
