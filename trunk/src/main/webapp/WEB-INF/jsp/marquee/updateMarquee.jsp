<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<div id="page-content">
	<div class="example-box-wrapper">
		<div id="updateMarqueeDiv" class="example-box-wrapper">
			<div style="float: left">
				<h3>
					<spring:message code="updateMarquee-form.updateMarquee-label" />
				</h3>
			</div>
			<div style="float: right">
				<button id="marqueeUpdateBackBt" name="marqueeUpdateBackBt"
					type="button" class="btn btn-info"
					onClick="javascript:getUrl('/app/marquee/list?reqPage=${reqPage}&pageSize=${pageSize}','page-content-holder');">
					<i class="glyph-icon icon-minus-square"></i>&nbsp;
					<spring:message code="updateMarquee-form.back-button" />
				</button>
			</div>
		</div>
		<div class="divider"></div>
		<form:form id="updateMarqueeForm" name="updateMarqueeForm"
			class="form-horizontal" data-parsley-validate=""
			action="javascript:submitForm('/app/marquee/update?reqPage=${reqPage}&pageSize=${pageSize}', 'updateMarqueeForm', 'page-content-holder')"
			method='POST' modelAttribute="marqueeForm">
			<input type="hidden" path="<c:out value="${_csrf.parameterName}"/>"
				name="<c:out value="${_csrf.parameterName}"/>"
				value="<c:out value="${_csrf.token}"/>" />
			<div id="updateMarqueeTypeDiv" class="form-group">
				<label for="" class="col-sm-2 control-label"> <spring:message
						code="updateMarquee-form.type-label" /> <span class="required">*</span>
				</label>
				<div id="updateMarqueeTypeDiv" class="col-sm-8">
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
			<div id="updateMarqueeTextDiv" class="form-group">
				<label for="" class="col-sm-2 control-label"> <spring:message
						code="updateMarquee-form.text-label" /> <span class="required">*</span>
				</label>
				<div id="updateMarqueeTextDiv" class="col-sm-8">
					<form:textarea class="form-control" path="text"
						placeholder="Enter text" maxlength="1024"
						data-parsley-trigger="keyup" data-parsley-length="[20, 1024]"
						data-parsley-validation-threshold="10"></form:textarea>
					<form:errors path="text" class="parsley-required" />
				</div>
			</div>
			<%--<div id="updateMarqueeCapthaDiv" class="form-group">
				<div class="col-sm-2 control-label">
					<img id="newCaptha" alt="Loading"
						src="/apu/captcha.jpg?ts=${javaDate.time}" width="120"
						height="48" /> <span title="Refresh and try another"
						onClick="javascript:refreshCaptcha('newCaptha');"> <i
						class="glyph-icon icon-refresh"
						style="cursor: pointer; padding-bottom: 10px;"></i>
					</span>
				</div>
				<br>
				<div id="updateMarqueeCapthaDiv" class="col-sm-2">
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
			<div id="marqueeButtonsDiv" class="form-group">
				<div class="col-sm-offset-2 col-sm-8">
					<div class="example-box-wrapper">
						<button id="marqueeUpdateSubmitBt" name="marqueeUpdateSubmitBt"
							type="submit" class="btn btn-success">
							<i class="glyph-icon icon-plus-square"></i>&nbsp;
							<spring:message code="updateMarquee-form.submit-button" />
						</button>
						<button id="marqueeUpdateResetBt" name="marqueeUpdateResetBt"
							type="reset" class="btn btn-warning">
							<i class="glyph-icon icon-minus-square"></i>&nbsp;
							<spring:message code="updateMarquee-form.reset-button" />
						</button>
						<button id="marqueeUpdateBackBt" name="marqueeUpdateBackBt"
							type="button" class="btn btn-info"
							onClick="javascript:getUrl('/app/marquee/list?reqPage=${reqPage}&pageSize=${pageSize}','page-content-holder');">
							<i class="glyph-icon icon-minus-square"></i>&nbsp;
							<spring:message code="updateMarquee-form.back-button" />
						</button>
					</div>
				</div>
			</div>
		</form:form>
	</div>
</div>
</div>