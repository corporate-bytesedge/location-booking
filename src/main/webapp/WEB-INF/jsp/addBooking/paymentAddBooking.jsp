<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<div id="page-content-holder">
	<div id="page-content page-data">
		<c:if test="${not empty message_error}">
			<div id="addBookingActionFailedMsgDiv" class="example-box-wrapper">
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
		<div id="createAddBookingDiv" class="example-box-wrapper wrapper-div">
			<div class="text-center text-centrCls">
				<h3>
					<spring:message code="createAddBooking-form.createAddBooking-label" />
				</h3>
			</div>
			<marquee>${data.mar}</marquee>
		</div>
		<form:form id="createAddBookingForm" class="form-horizontal"
			data-parsley-validate=""
			action="javascript:submitForm('/app/addBooking/payment', 'createAddBookingForm',  'page-content-holder')"
			method='POST' modelAttribute="addBookingForm">
			<div id="createAddBookingUsernameDiv" class="form-group">
				<label for="" class="col-sm-2 control-label"> <spring:message
						code="createAddBooking-form.username-label" /> <span
					class="required">*</span>
				</label>
				<div id="createAddBookingUsernameDiv" class="col-sm-8">
					<form:input class="form-control" type="text"
						placeholder="Enter username" path="userName" maxlength="32"
						required="true" readOnly="true" />
					<form:errors path="userName" class="parsley-required" />
				</div>
			</div>
			<div id="createAddBookingPaymentModeDiv" class="form-group">
				<label for="" class="col-sm-2 control-label"> <spring:message
						code="createAddBooking-form.paymentMode-label" /> <span
					class="required">*</span>
				</label>
				<div id="createAddBookingPaymentModeDiv" class="col-sm-8">
					<form:select path="paymentMode" class="form-control"
						required="true">
						<c:forEach items="${PaymentMode}" var="ev">
							<form:option value="${ev}">
								<spring:message code="PaymentMode.${ev}" />
							</form:option>
						</c:forEach>
					</form:select>
					<form:errors path="paymentMode" class="parsley-required" />
				</div>
			</div>
			<div id="createAddBookingPaymentStatusDiv" class="form-group">
				<label for="" class="col-sm-2 control-label"> <spring:message
						code="createAddBooking-form.paymentStatus-label" /> <span
					class="required">*</span>
				</label>
				<div id="createAddBookingPaymentStatusDiv" class="col-sm-8">
					<form:select path="paymentStatus" class="form-control"
						required="true">
						<c:forEach items="${PaymentStatus}" var="ev">
							<form:option value="${ev}">
								<spring:message code="PaymentStatus.${ev}" />
							</form:option>
						</c:forEach>
					</form:select>
					<form:errors path="paymentStatus" class="parsley-required" />
				</div>
			</div>
			<div id="createAddBookingBankNameDiv" class="form-group">
				<label for="" class="col-sm-2 control-label"> <spring:message
						code="createAddBooking-form.bankName-label" /> <span
					class="required">*</span>
				</label>
				<div id="createAddBookingBankNameDiv" class="col-sm-8">
					<form:input class="form-control" type="text"
						placeholder="Enter bank name" path="bankNamePgRes" maxlength="32"
						required="true" />
					<form:errors path="bankNamePgRes" class="parsley-required" />
				</div>
			</div>
			<div id="createAddBookingCardNumberDiv" class="form-group">
				<label for="" class="col-sm-2 control-label"> <spring:message
						code="createAddBooking-form.CardNumber-label" /> <span
					class="required">*</span>
				</label>
				<div id="createAddBookingCardNumberDiv" class="col-sm-8">
					<form:input class="form-control" type="text"
						placeholder="Enter number" path="cardNumberPgRes" maxlength="64"
						required="true" />
					<form:errors path="cardNumberPgRes" class="parsley-required" />
				</div>
			</div>
			<div id="createAddBookingAmountDiv" class="form-group">
				<label for="" class="col-sm-2 control-label"> <spring:message
						code="createAddBooking-form.amount-label" /> <span
					class="required">*</span>
				</label>
				<div id="createAddBookingAmountDiv" class="col-sm-8">
					<form:input class="form-control" type="text" path="total"
						maxlength="64" required="true" readOnly="true" />
					<form:errors path="total" class="parsley-required" />
				</div>
			</div>
			<%-- <div id="createAddBookingCapthaDiv" class="form-group">
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
				<div id="createAddBookingCapthaDiv" class="col-sm-2">
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
			<div id="createAddBookingButtonsDiv" class="form-group">
				<div class="col-sm-offset-2 col-sm-8">
					<div class="example-box-wrapper">
						<button id="addBookingCreateSubmitBt"
							name="addBookingCreateSubmitBt" type="submit"
							class="btn btn-success">
							<i class="glyph-icon icon-plus-square"></i>&nbsp;
							<spring:message code="createAddBooking-form.submit-button" />
						</button>
						<button id="addBookingCreateRestBt" name="addBookingCreateResetBt"
							type="reset" class="btn btn-warning">
							<i class="glyph-icon icon-minus-square"></i>&nbsp;
							<spring:message code="createAddBooking-form.reset-button" />
						</button>
					</div>
				</div>
			</div>
		</form:form>
	</div>
</div>
