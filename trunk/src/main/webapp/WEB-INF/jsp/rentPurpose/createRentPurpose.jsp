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
		<div id="rentPurposeActionFailedMsgDiv" class="example-box-wrapper">
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
	<div id="createRentPurposeDiv" class="example-box-wrapper">
		<div style="float: left">
			<h3>
				<spring:message code="createRentPurpose-form.createRentPurpose-label" />
			</h3>
		</div>
		<div style="float: right">
			<button id="createRentPurposeBackBt" name="createRentPurposeBackBt"
				class="btn btn-info"
				onClick="javascript:loadContent('/app/rentPurpose/list','page-content-holder');">
				<i class="glyph-icon icon-minus-square"></i>&nbsp;
				<spring:message code="createRentPurpose-form.back-button" />
			</button>
		</div>
	</div>
	<div class="divider"></div>
	<form:form id="createRentPurposeForm" class="form-horizontal"
		data-parsley-validate=""
		action="javascript:submitForm('/app/rentPurpose/create', 'createRentPurposeForm', 'page-content-holder')"
		method='POST' modelAttribute="rentPurposeForm">
		<div id="createRentPurposeStateDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="createRentPurpose-form.state-label" /> <span class="required">*</span>
			</label>
			<div id="createRentPurposeStateTypeDiv" class="col-sm-8">
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
		<div id="createRentPurposeNameDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="createRentPurpose-form.Name-label" /> <span class="required">*</span>
			</label>
			<div id="createRentPurposeNameDiv" class="col-sm-8">
				<form:input class="form-control" type="text"
					placeholder="Enter name" path="name" maxlength="64" required="true" />
				<form:errors path="name" class="parsley-required" />
			</div>
		</div>
		<div id="createRentPurposeDescrDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="createRentPurpose-form.descr-label" /> <span class="required">*</span>
			</label>
			<div id="createRentPurposeDescrDiv" class="col-sm-8">
				<form:textarea class="form-control" path="descr"
					placeholder="Enter description" maxlength="1024"
					data-parsley-trigger="keyup" data-parsley-length="[20, 1024]"
					data-parsley-validation-threshold="10"></form:textarea>
				<form:errors path="descr" class="parsley-required" />
			</div>
		</div>
		<div class="divider"></div>
		<div id="createRentPurposeButtonsDiv" class="form-group">
			<div class="col-sm-offset-2 col-sm-8">
				<div class="example-box-wrapper">
					<button id="rentPurposeCreateSubmitBt" name="rentPurposeCreateSubmitBt"
						type="submit" class="btn btn-success">
						<i class="glyph-icon icon-plus-square"></i>&nbsp;
						<spring:message code="createRentPurpose-form.submit-button" />
					</button>
					<button id="rentPurposeCreateRestBt" name="rentPurposeCreateResetBt"
						type="reset" class="btn btn-warning">
						<i class="glyph-icon icon-minus-square"></i>&nbsp;
						<spring:message code="createRentPurpose-form.reset-button" />
					</button>
					<button id="rentPurposeCreateBackBt" name="rentPurposeCreateBackBt"
						type="button" class="btn btn-info"
						onClick="javascript:getUrl('/app/rentPurpose/list','page-content-holder');">
						<i class="glyph-icon icon-minus-square"></i>&nbsp;
						<spring:message code="createRentPurpose-form.back-button" />
					</button>
				</div>
			</div>
		</div>
	</form:form>
</div>
