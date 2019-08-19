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
	<div class="example-box-wrapper">
		<div id="updatePropertyDiv" class="example-box-wrapper">
			<div style="float: left">
				<h3>
					<spring:message code="updateProperty-form.updateProperty-label" />
				</h3>
			</div>
			<div style="float: right">
				<button id="propertyUpdateBackBt" name="propertyUpdateBackBt" type="button"
					class="btn btn-info"
					onClick="javascript:getUrl('/app/property/list?reqPage=${reqPage}&pageSize=${pageSize}','page-content-holder');">
					<i class="glyph-icon icon-minus-square"></i>&nbsp;
					<spring:message code="updateProperty-form.back-button" />
				</button>
			</div>
		</div>
		<div class="divider"></div>
		<form:form id="updatePropertyForm" name="updatePropertyForm"
			class="form-horizontal" data-parsley-validate=""
			action="javascript:submitForm('/app/property/update?reqPage=${reqPage}&pageSize=${pageSize}', 'updatePropertyForm', 'page-content-holder')"
			method='POST' modelAttribute="propertyForm">
			<input type="hidden" path="<c:out value="${_csrf.parameterName}"/>"
				name="<c:out value="${_csrf.parameterName}"/>"
				value="<c:out value="${_csrf.token}"/>" />
			<div id="updatePropertyStateDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="updateProperty-form.state-label" /> <span class="required">*</span>
			</label>
			<div id="updatePropertyGenderDiv" class="col-sm-8">
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
		<div id="updatePropertyNameDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="updateProperty-form.Name-label" /> <span class="required">*</span>
			</label>
			<div id="updatePropertyNameDiv" class="col-sm-8">
				<form:input class="form-control" type="text"
					placeholder="Enter name" path="name" maxlength="64" required="true" />
				<form:errors path="name" class="parsley-required" />
			</div>
		</div>
		<div id="updatePropertyVenueDaysLimitDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="updateProperty-form.veueDaysLimit-label" /> <span
				class="required">*</span>
			</label>
			<div id="updatePropertyVenueDaysLimitDiv" class="col-sm-8">
				<form:input class="form-control" type="text"
					placeholder="Enter no. of days to block" path="venueDaysLimit" maxlength="16"
					required="true" />
				<form:errors path="venueDaysLimit" class="parsley-required" />
			</div>
		</div>
		<div id="updatePropertyUniqueIdDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="updateProperty-form.uniqueId-label" /> <span
				class="required">*</span>
			</label>
			<div id="updatePropertyUniqueIdDiv" class="col-sm-8">
				<form:input class="form-control" type="text"
					placeholder="Enter unique Id" path="uniqueId" maxlength="16"
					required="true" />
				<form:errors path="uniqueId" class="parsley-required" />
			</div>
		</div>
		<div id="updatePropertyDescrDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="updateProperty-form.descr-label" /> <span class="required">*</span>
			</label>
			<div id="updatePropertyDescrDiv" class="col-sm-8">
				<form:textarea class="form-control" path="descr"
					placeholder="Enter description" maxlength="1024"
					data-parsley-trigger="keyup" data-parsley-length="[20, 1024]"
					data-parsley-validation-threshold="10"></form:textarea>
				<form:errors path="descr" class="parsley-required" />
			</div>
		</div>
			<%-- <div id="updatePropertyCapthaDiv" class="form-group">
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
				<div id="updatePropertyCapthaDiv" class="col-sm-2">
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
			<div id="propertyButtonsDiv" class="form-group">
				<div class="col-sm-offset-2 col-sm-8">
					<div class="example-box-wrapper">
						<button id="propertyUpdateSubmitBt" name="propertyUpdateSubmitBt"
							type="submit" class="btn btn-success">
							<i class="glyph-icon icon-plus-square"></i>&nbsp;
							<spring:message code="updateProperty-form.submit-button" />
						</button>
						<button id="propertyUpdateResetBt" name="propertyUpdateResetBt"
							type="reset" class="btn btn-warning">
							<i class="glyph-icon icon-minus-square"></i>&nbsp;
							<spring:message code="updateProperty-form.reset-button" />
						</button>
						<button id="propertyUpdateBackBt" name="propertyUpdateBackBt"
							type="button" class="btn btn-info"
							onClick="javascript:getUrl('/app/property/list?reqPage=${reqPage}&pageSize=${pageSize}','page-content-holder');">
							<i class="glyph-icon icon-minus-square"></i>&nbsp;
							<spring:message code="updateProperty-form.back-button" />
						</button>
					</div>
				</div>
			</div>
		</form:form>
	</div>
</div>
</div>