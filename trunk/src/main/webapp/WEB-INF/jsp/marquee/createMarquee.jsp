<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<div id="page-content">
	<c:if test="${not empty message_error}">
		<div id="marqueeActionFailedMsgDiv" class="example-box-wrapper">
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
	<div id="createMarqueeDiv" class="example-box-wrapper">
		<div style="float: left">
			<h3>
				<spring:message code="createMarquee-form.createMarquee-label" />
			</h3>
		</div>
		<div style="float: right">
			<button id="createMarqueeBackBt" name="createMarqueeBackBt"
				class="btn btn-info"
				onClick="javascript:loadContent('/app/marquee/list','page-content-holder');">
				<i class="glyph-icon icon-minus-square"></i>&nbsp;
				<spring:message code="createMarquee-form.back-button" />
			</button>
		</div>
	</div>
	<div class="divider"></div>
	<form:form id="createMarqueeForm" class="form-horizontal"
		data-parsley-validate=""
		action="javascript:submitForm('/app/marquee/create', 'createMarqueeForm', 'page-content-holder')"
		method='POST' modelAttribute="marqueeForm">
		<div id="createMarqueeTypeDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="createMarquee-form.type-label" /> <span class="required">*</span>
			</label>
			<div id="createMarqueeTypeDiv" class="col-sm-8">
				<form:select path="type" class="form-control" required="true">
					<c:forEach items="${MarqueeType}" var="ev">
						<form:option value="${ev}">
							<spring:message code="MarqueeType.${ev}" />
						</form:option>
					</c:forEach>
				</form:select>
				<form:errors path="type" class="parsley-required" />
			</div>
		</div>
		<div id="createMarqueeTextDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="createMarquee-form.type-label" /> <span class="required">*</span>
			</label>
			<div id="createMarqueeTextDiv" class="col-sm-8">
				<form:textarea class="form-control" path="text"
					placeholder="Enter text" maxlength="1024"
					data-parsley-trigger="keyup" data-parsley-length="[20, 1024]"
					data-parsley-validation-threshold="10"></form:textarea>
				<form:errors path="text" class="parsley-required" />
			</div>
		</div>
		<div id="createMarqueeCapthaDiv" class="form-group">
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
			<div id="createMarqueeCapthaDiv" class="col-sm-2">
				<span class="input-group-addon addon-inside bg-white font-primary"
					style="margin-left: 15px;"> <i
					class="glyph-icon icon-linecons-key"></i>
				</span>
				<form:input type="text" class="form-control" path="captchaText"
					maxlength="6" placeholder="Captcha" required="true" />
				<form:errors path="captchaText" class="parsley-required" />
			</div>
		</div>
		<div class="divider"></div>
		<div id="createMarqueeButtonsDiv" class="form-group">
			<div class="col-sm-offset-2 col-sm-8">
				<div class="example-box-wrapper">
					<button id="marqueeCreateSubmitBt" name="marqueeCreateSubmitBt"
						type="submit" class="btn btn-success">
						<i class="glyph-icon icon-plus-square"></i>&nbsp;
						<spring:message code="createMarquee-form.submit-button" />
					</button>
					<button id="marqueeCreateRestBt" name="marqueeCreateResetBt"
						type="reset" class="btn btn-warning">
						<i class="glyph-icon icon-minus-square"></i>&nbsp;
						<spring:message code="createMarquee-form.reset-button" />
					</button>
					<button id="marqueeCreateBackBt" name="marqueeCreateBackBt"
						type="button" class="btn btn-info"
						onClick="javascript:getUrl('/app/marquee/list','page-content-holder');">
						<i class="glyph-icon icon-minus-square"></i>&nbsp;
						<spring:message code="createMarquee-form.back-button" />
					</button>
				</div>
			</div>
		</div>
	</form:form>
</div>
