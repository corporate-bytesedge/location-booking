<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<div id="page-content">
	<div class="example-box-wrapper">
		<div id="updatePropertyImagesDiv" class="example-box-wrapper">
			<div style="float: left">
				<h3>
					<spring:message
						code="updatePropertyImages-form.updatePropertyImages-label" />
				</h3>
			</div>
			<div style="float: right">
				<button id="propertyImagesUpdateBackBt"
					name="propertyImagesUpdateBackBt" type="button" class="btn btn-info"
					onClick="javascript:getUrl('/app/propertyImages/list?reqPage=${reqPage}&pageSize=${pageSize}','page-content-holder');">
					<i class="glyph-icon icon-minus-square"></i>&nbsp;
					<spring:message code="updatePropertyImages-form.back-button" />
				</button>
			</div>
		</div>
		<div class="divider"></div>
		<form:form id="updatePropertyImagesForm" name="updatePropertyImagesForm"
			class="form-horizontal" data-parsley-validate=""
			action="javascript:submitForm('/app/propertyImages/update', 'updatePropertyImagesForm', 'page-content-holder')"
			method='POST' modelAttribute="propertyImagesForm">
			<input type="hidden" path="<c:out value="${_csrf.parameterName}"/>"
				name="<c:out value="${_csrf.parameterName}"/>"
				value="<c:out value="${_csrf.token}"/>" />
			<div id="updatePropertyImagesNameDiv" class="form-group">
				<label for="" class="col-sm-2 control-label"> <spring:message
						code="updatePropertyImages-form.name-label" /> <span
					class="required">*</span>
				</label>
				<div id="updatePropertyImagesNameDiv" class="col-sm-8">
					<form:input class="form-control" type="text" path="name"
						maxlength="32" required="true" />
					<form:errors path="name" class="parsley-required" />
				</div>
			</div>
			<div id="updatePropertyImagesDescrDiv" class="form-group">
				<label for="" class="col-sm-2 control-label"> <spring:message
						code="updatePropertyImages-form.descr-label" /> <span
					class="required">*</span>
				</label>
				<div id="updatePropertyImagesDescrDiv" class="col-sm-8">
					<form:input class="form-control" type="text" path="descr"
						maxlength="1024" required="true" />
					<form:errors path="descr" class="parsley-required" />
				</div>
			</div>
			<div id="updatePropertyImagesCapthaDiv" class="form-group">
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
				<div id="propertyImagescapthaDiv" class="col-sm-2">
					<span class="input-group-addon addon-inside bg-white font-primary"
						style="margin-left: 15px;"> <i
						class="glyph-icon icon-linecons-key"></i>
					</span> <input type="text" class="form-control" id="captchaText"
						name="captchaText" placeholder="Captcha" required>
					<form:errors path="captchaText" class="parsley-required" />
				</div>
			</div>
			<div class="divider"></div>
			<div id="updatePropertyImagesButtonsDiv" class="form-group">
				<div class="col-sm-offset-2 col-sm-8">
					<div class="example-box-wrapper">
						<button id="propertyImagesUpdateSubmitBt"
							name="propertyImagesUpdateSubmitBt" type="submit"
							class="btn btn-success">
							<i class="glyph-icon icon-plus-square"></i>&nbsp;
							<spring:message code="updatePropertyImages-form.submit-button" />
						</button>
						<button id="propertyImagesUpdateRestBt"
							name="propertyImagesUpdateResetBt" type="reset"
							class="btn btn-warning">
							<i class="glyph-icon icon-minus-square"></i>&nbsp;
							<spring:message code="updatePropertyImages-form.reset-button" />
						</button>
						<button id="propertyImagesUpdateBackBt"
							name="propertyImagesUpdateBackBt" type="button"
							class="btn btn-info"
							onClick="javascript:getUrl('/app/propertyImages/list?reqPage=${reqPage}&pageSize=${pageSize}','page-content-holder');">
							<i class="glyph-icon icon-minus-square"></i>&nbsp;
							<spring:message code="updatePropertyImages-form.back-button" />
						</button>
					</div>
				</div>
			</div>
		</form:form>
	</div>
</div>
</div>