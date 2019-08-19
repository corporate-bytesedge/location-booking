<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" href="/assets-minified/widgets/datepicker-ui/datepicker.css">
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
	<div id="backAddBookingDiv" class="example-box-wrapper wrapper-div">
		<div class="text-center text-centrCls">
			<h3>
				<spring:message code="backAddBooking-form.backAddBooking-label" />
			</h3>
		</div>
		<marquee direction="right" style="margin:20px 0 20px 0;font-size:16px;">${addBookingForm.marquee}</marquee>
	</div>
	<form:form id="backAddBookingForm" class="form-horizontal"
		data-parsley-validate=""
		action="javascript:submitForm('/app/addBooking/create', 'backAddBookingForm',  'page-content-holder')"
		method='POST' modelAttribute="addBookingForm">
		<div id="backAddBookingPropertyIdDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="backAddBooking-form.propertyId-label" /> <span class="required">*</span>
			</label>
			<div id="backAddBookingPropertyIdDiv" class="col-sm-8">
				<form:select  onchange="changeVenue()" id="venueId" class="validate btn btn-default" path="propertyId" required="true" >
				    <form:option value="0" selected="true" disabled="true" hidden="true">Select a Venue</form:option> 
					<form:option value="1" >Sampradaya Vedika</form:option>
					<form:option value="2" >Rock Heights</form:option>
					<form:option value="3" >Mountain Heights</form:option>
					<form:option value="4" >Shilpaseema</form:option>
				</form:select> 
				<form:errors path="propertyId" class="parsley-required" />
			</div>
		</div>
		
		
		<div  id="backAddBookingDateDiv"  class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="backAddBooking-form.date-label" />
			</label>
			<div id="backAddBookingDateDiv" class="col-sm-8">
			<span class="input-group-addon addon-inside bg-white font-primary"
					style="margin-left: 10px;"> <i
					class="glyph-icon icon-calendar"></i>
				</span>
				<form:input type="text" class=" form-control"  id="bootstrap-datepicker" placeholder="DD/MM/YYYY" maxlength="10"
					 path="bookingDate" required="true" style = "width:200px"
					 data-inputmask="&apos;mask&apos;:&apos;99-99-9999&apos;"/>
				<form:errors path="bookingDate" class="parsley-required" />
				</div>
		</div>
		<div id="backAddBookingVenueDaysLimitDiv test" class="form-group"
			hidden="">
			<label for="" class="col-sm-2 control-label">days<span
				class="required">*</span>
			</label>
			<div id="backAddBookingVenueDaysLimitDiv" class="col-sm-8">
				<form:input type="text" class="form-control" id="daysLimit"
					path="venueDaysLimit" maxlength="12" required="true" readOnly="true" />
				<form:errors path="venueDaysLimit" class="parsley-required" />
			</div>
		</div>
		<div id="backEndUserBookingDatesDiv hidingDiv" class="form-group" hidden="">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="createEndUser-form.bookingDates-label" /> <span
				class="required">*</span>
			</label>
			<div id="backEndUserBookingDatesDiv" class="col-sm-8">
				<form:input type="text" class="form-control" id="blockDates"
					 path="billList" maxlength="12"
					required="true" readOnly = "true" />
				<form:errors path="billList" class="parsley-required" />
			</div>
		</div> 
		<div id="backAddBookingPurposeIdDiv" class="form-group">
			<label for="" class="col-sm-2 control-label" > <spring:message
					code="backAddBooking-form.purposeId-label" /> <span
				class="required ">*</span>
			</label>
			
			<div id="backAddBookingPurposeIdDiv" class="col-sm-8">
				<form:select class="validate btn btn-default" path="purposeId" required="true" >
								    <form:option value="0" disabled="true">Select a Purpose</form:option> 
									<form:option value="1" >Marriage</form:option>
									<form:option value="2" >Birthday</form:option>
									<form:option value="3" >Engagement</form:option>
									<form:option value="4" >Corporate Functions</form:option>
									<form:option value="5" >Others</form:option>
								</form:select> 
				<form:errors path="purposeId" class="parsley-required" />
			</div>
		</div> 	
		
		<div id="backAddBookingNameDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="backAddBooking-form.name-label" /> <span class="required">*</span>
			</label>
			<div id="backAddBookingNameDiv" class="col-sm-8">
				<form:input class="form-control" type="text"
					placeholder="Enter name" path="name" maxlength="128"
					required="true" />
				<form:errors path="name" class="parsley-required" />
			</div>
		</div>
		<div id="backAddBookingEmailDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="backAddBooking-form.email-label" /> <span class="required">*</span>
			</label>
			<div id="backAddBookingEmailDiv" class="col-sm-8">
				<form:input class="form-control" type="text"
					placeholder="Enter email id" path="email" maxlength="64"
					required="true" />
				<form:errors path="email" class="parsley-required" />
			</div>
		</div>
		<div id="backAddBookingMobileNumberDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="backAddBooking-form.mobileNumber-label" /> <span
				class="required">*</span>
			</label>
			<div id="backAddBookingMobileNumberDiv" class="col-sm-8">
				<form:input type="text" class=" input-mask form-control"
					placeholder="Enter 10 digit mobile number"
					onkeypress="return AllowOnlyNumbers(event);" path="mobileNumber"
					maxlength="10" required="true"
					data-inputmask="&apos;mask&apos;:&apos;9999999999&apos;" />
				<form:errors path="mobileNumber" class="parsley-required" />
			</div>
		</div>
		<div id="backAddBookingIdproofDiv" class="form-group">
				<label for="" class="col-sm-2 control-label"> <spring:message
						code="backAddBooking-form.idProof-label" /> <span
					class="required">*</span>
				</label>
				<div id="backAddBookingIdproofDiv" class="col-sm-8">
					<form:select path="idProofType" class="form-control" required="true">
						<c:forEach items="${IdProofType}" var="ev">
							<form:option value="${ev}">
								<spring:message code="IdProofType.${ev}" />
							</form:option>
						</c:forEach>
					</form:select>
					<form:errors path="idProofType" class="parsley-required" />
				</div>
			</div>
		<div id="backAddBookingIdProofIdDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="backAddBooking-form.idProofId-label" /> <span class="required">*</span>
			</label>
			<div id="backAddBookingIdProofIdDiv" class="col-sm-8">
				<form:input type="text" class=" input-mask form-control"
					placeholder="Enter idproof number" path="idProof"
					maxlength="12" required="true" />
				<form:errors path="idProof" class="parsley-required" />
			</div>
		</div>
		
		<%-- <div  id="backAddBookingDateDiv"  class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="backAddBooking-form.date-label" />
			</label>
			<div id="backAddBookingDateDiv" class="col-sm-8">
			<span class="input-group-addon addon-inside bg-white font-primary"
					style="margin-left: 10px;"> <i
					class="glyph-icon icon-calendar"></i>
				</span>
				<form:input type="text" class="bootstrap-datepicker form-control"  maxlength="10"
					 path="bookingDate" required="true" style = "width:200px"/>
				<form:errors path="bookingDate" class="parsley-required" />
				</div>
		</div> --%>
		
		
		
		<div id="backAddBookingPaymentModeDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="backAddBooking-form.paymentMode-label" /> <span class="required">*</span>
			</label>
			<div id="backAddBookingPaymentModeDiv" class="col-sm-8">
				<form:input class="form-control" type="text"
					placeholder="Enter payment mode" path="paymentMode" maxlength="32"
					required="true" />
				<form:errors path="paymentMode" class="parsley-required" /> 
			</div>
		</div>
		<div id="backAddBookingAddrDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="backAddBooking-form.addr-label" /> <span class="required">*</span>
			</label>
			<div id="backAddBookingAddrDiv" class="col-sm-8">
				<form:textarea class="form-control" path="addr"
					placeholder="Enter address" maxlength="1024"
					data-parsley-trigger="keyup" data-parsley-length="[20, 1024]"
					data-parsley-validation-threshold="10"></form:textarea>
				<form:errors path="addr" class="parsley-required" />
			</div>
		</div>
		<div id="backAddBookingReferredByDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="backAddBooking-form.referredBy-label" /> 
			</label>
			<div id="backAddBookingReferredByDiv" class="col-sm-8">
				<form:input class="form-control" type="text"
					placeholder="Enter referred by" path="referredBy" maxlength="64"
					/>
				<form:errors path="referredBy" class="parsley-required" />
			</div>
		</div>
		<div class="divider"></div>
		<div id="backAddBookingButtonsDiv" class="form-group">
			<div class="col-sm-offset-2 col-sm-8">
				<div class="example-box-wrapper">
					<button id="addBookingBackSubmitBt" name="addBookingBackSubmitBt"
						type="submit" class="btn btn-success">
						<i class="glyph-icon icon-plus-square"></i>&nbsp;
						<spring:message code="backAddBooking-form.submit-button" />
					</button>
					<button id="addBookingBackRestBt" name="addBookingBackResetBt" type="reset"
						class="btn btn-warning">
						<i class="glyph-icon icon-minus-square"></i>&nbsp;
						<spring:message code="backAddBooking-form.reset-button" />
					</button>
				</div>
			</div>
		</div>
	</form:form>
</div>
</div>
<script>

function initDate() {
	var array = $('#blockDates').val().split(', ');
	//var days =$('#daysLimit').val();
	//alert("Hi");
	$('#bootstrap-datepicker').datepicker({
		minDate : '+1d',
		//maxDate: days,
		dateFormat: "dd-mm-yy",
		altFormat : "dd-mm-yyyy",
    	 beforeShowDay: function(date){
	        var string = jQuery.datepicker.formatDate('dd-mm-yy', date);
	        return [ array.indexOf(string) == -1 ]
	    }
	}).bind("change",function(){
        var minValue = $(this).val();
	})
}

function changeVenue() {
	var venueId = $("#venueId").val();
	if(venueId > 0){
	$.ajax({
		type : 'GET',
		url : '/app/addBooking/create/form?venueId=' + venueId,
		success : function(returnedData) {
			
			$("#page-content-holder").html(returnedData);
			$("#venueId").val(venueId);
			var array = $('#blockDates').val().split(', ');
			//var days =$('#daysLimit').val();
			//alert(days);
	    	$('#bootstrap-datepicker').datepicker({
	    		minDate : '+1d',
	    		//maxDate: days,
	    		dateFormat: "dd-mm-yy",
	    		altFormat : "dd-mm-yyyy",
		    	 beforeShowDay: function(date){
	    	        var string = jQuery.datepicker.formatDate('dd-mm-yy', date);
	    	        return [ array.indexOf(string) == -1 ]
	    	    }
	    	}).bind("change",function(){
	            var minValue = $(this).val();
	    	})
		},
		error : function(returnedData) {
		}
	});
	}
}

</script>

