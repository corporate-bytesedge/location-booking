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
		<div id="updateRentPurposeDiv" class="example-box-wrapper">
			<div style="float: left">
				<h3>
					<spring:message code="updateRentPurpose-form.updateRentPurpose-label" />
				</h3>
			</div>
			<div style="float: right">
				<button id="rentPurposeUpdateBackBt" name="rentPurposeUpdateBackBt" type="button"
					class="btn btn-info"
					onClick="javascript:getUrl('/app/rentPurpose/list?reqPage=${reqPage}&pageSize=${pageSize}','page-content-holder');">
					<i class="glyph-icon icon-minus-square"></i>&nbsp;
					<spring:message code="updateRentPurpose-form.back-button" />
				</button>
			</div>
		</div>
		<div class="divider"></div>
		<form:form id="updateRentPurposeForm" name="updateRentPurposeForm"
			class="form-horizontal" data-parsley-validate=""
			action="javascript:submitForm('/app/rentPurpose/update?reqPage=${reqPage}&pageSize=${pageSize}', 'updateRentPurposeForm', 'page-content-holder')"
			method='POST' modelAttribute="rentPurposeForm">
			<input type="hidden" path="<c:out value="${_csrf.parameterName}"/>"
				name="<c:out value="${_csrf.parameterName}"/>"
				value="<c:out value="${_csrf.token}"/>" />
			<div id="updateRentPurposeStateDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="updateRentPurpose-form.state-label" /> <span class="required">*</span>
			</label>
			<div id="updateRentPurposeGenderDiv" class="col-sm-8">
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
		<div id="updateRentPurposeNameDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="updateRentPurpose-form.Name-label" /> <span class="required">*</span>
			</label>
			<div id="updateRentPurposeNameDiv" class="col-sm-8">
				<form:input class="form-control" type="text"
					placeholder="Enter name" path="name" maxlength="64" required="true" />
				<form:errors path="name" class="parsley-required" />
			</div>
		</div>
		<div id="updateRentPurposeDescrDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="updateRentPurpose-form.descr-label" /> <span class="required">*</span>
			</label>
			<div id="updateRentPurposeDescrDiv" class="col-sm-8">
				<form:textarea class="form-control" path="descr"
					placeholder="Enter description" maxlength="1024"
					data-parsley-trigger="keyup" data-parsley-length="[20, 1024]"
					data-parsley-validation-threshold="10"></form:textarea>
				<form:errors path="descr" class="parsley-required" />
			</div>
		</div>
			<%-- <div id="updateRentPurposeCapthaDiv" class="form-group">
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
				<div id="updateRentPurposeCapthaDiv" class="col-sm-2">
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
			<div id="rentPurposeButtonsDiv" class="form-group">
				<div class="col-sm-offset-2 col-sm-8">
					<div class="example-box-wrapper">
						<button id="rentPurposeUpdateSubmitBt" name="rentPurposeUpdateSubmitBt"
							type="submit" class="btn btn-success">
							<i class="glyph-icon icon-plus-square"></i>&nbsp;
							<spring:message code="updateRentPurpose-form.submit-button" />
						</button>
						<button id="rentPurposeUpdateResetBt" name="rentPurposeUpdateResetBt"
							type="reset" class="btn btn-warning">
							<i class="glyph-icon icon-minus-square"></i>&nbsp;
							<spring:message code="updateRentPurpose-form.reset-button" />
						</button>
						<button id="rentPurposeUpdateBackBt" name="rentPurposeUpdateBackBt"
							type="button" class="btn btn-info"
							onClick="javascript:getUrl('/app/rentPurpose/list?reqPage=${reqPage}&pageSize=${pageSize}','page-content-holder');">
							<i class="glyph-icon icon-minus-square"></i>&nbsp;
							<spring:message code="updateRentPurpose-form.back-button" />
						</button>
					</div>
				</div>
			</div>
		</form:form>
	</div>
</div>
</div>