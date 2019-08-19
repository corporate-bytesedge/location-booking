<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<style>
	.modal {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  padding-top: 100px; /* Location of the box */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  background-color: rgb(0,0,0); /* Fallback color */
  background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
}
.modal-content {
  position: relative;
  background-color: #fefefe;
  margin: auto;
  padding: 0;
  border: 1px solid #888;
  width: 80%;
  box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2),0 6px 20px 0 rgba(0,0,0,0.19);
  -webkit-animation-name: animatetop;
  -webkit-animation-duration: 0.4s;
  animation-name: animatetop;
  animation-duration: 0.4s
}
@-webkit-keyframes animatetop {
  from {top:-300px; opacity:0} 
  to {top:0; opacity:1}
}

@keyframes animatetop {
  from {top:-300px; opacity:0}
  to {top:0; opacity:1}
}

/* The Close Button */
.close {
  color: white;
  float: right;
  font-size: 28px;
  font-weight: bold;
}

.close:hover,
.close:focus {
  color: #000;
  text-decoration: none;
  cursor: pointer;
}

.modal-header {
  padding: 2px 16px;
  background-color: #5cb85c;
  color: white;
}

.modal-body {
	padding: 2px 16px;
	font-size: 15px;
	}

.modal-footer {
  padding: 2px 16px;
  background-color: #5cb85c;
  color: white;
}
</style>

<div id="createEndUserDiv" class="example-box-wrapper wrapper-div">
		<div class="text-center text-centrCls" style=" margin-top: 0px !important;">
			<h3 class="h3-text"><spring:message code="createEndUser-form.createEndUser-label" /></h3>
		</div>
</div>
<div class="divider"></div>
	<form:form id="createEndUserForm" onsubmit="return checkForm(this);" class="form-horizontal form-marr" data-parsley-validate=""
		action="javascript:submitForm('/apu/endUser/create', 'createEndUserForm',  'page-content-holder')"
		method='POST' modelAttribute="endUserForm">
		
		<div id="createEndUserPropertyIdDiv" class="form-group" >
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="createEndUser-form.propertyId-label" /> <span
				class="required" >*</span>
			</label>
			<div id="createEndUserPropertyIdDiv" class="col-sm-8">
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
		
		<div id="createEndUserBookingDatesDiv test" class="form-group"
			hidden="">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="createEndUser-form.bookingDates-label" /> <span
				class="required">*</span>
			</label>
			<div id="createEndUserBookingDatesDiv" class="col-sm-8">
				<form:input type="text" class="form-control" id="blockDates"
					path="billList" maxlength="12" required="true" readOnly="true" />
				<form:errors path="billList" class="parsley-required" />
			</div>
		</div>
		
		<div id="createEndUserVenueDaysLimitDiv test" class="form-group"
			hidden="">
			<label for="" class="col-sm-2 control-label">days<span
				class="required">*</span>
			</label>
			<div id="createEndUserVenueDaysLimitDiv" class="col-sm-8">
				<form:input type="text" class="form-control" id="daysLimit"
					path="venueDaysLimit" maxlength="12" required="true" readOnly="true" />
				<form:errors path="venueDaysLimit" class="parsley-required" />
			</div>
		</div>
		<div id="createEnduserVenueDateDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="createEndUser-form.date-label" /> <span
				class="required">*</span>
			</label>
			<div id="createEnduserVenueDateDiv" class="col-sm-8 input-group date">
		<span class="glyphicon glyphicon-calendar"></span>
				<form:input class="bootstrap-datepicker form-control" type="text"
					placeholder="DD/MM/YYYY" path="bookingDate"  maxlength="10"
					required="true"
					data-inputmask="&apos;mask&apos;:&apos;99-99-9999&apos;" />
				<form:errors path="bookingDate" class="parsley-required" />
			</div>
		</div>
		 <div id="createEndUserPurposeIdDiv" class="form-group">
			<label for="" class="col-sm-2 control-label" > <spring:message
					code="createEndUser-form.purposeId-label" /> <span
				class="required ">*</span>
			</label>
			<div id="createEndUserPurposeIdDiv" class="col-sm-8">
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
		<div id="createEndUserNameDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="createEndUser-form.name-label" /> <span class="required">*</span>
			</label>
			<div id="createEndUserNameDiv" class="col-sm-8">
				<form:input class="form-control" type="text"
					placeholder="Enter Name" path="name" maxlength="128"
					required="true" />
				<form:errors path="name" class="parsley-required" />
			</div>
		</div>
		<div id="createEndUserEmailDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="createEndUser-form.email-label" /> <span class="required">*</span>
			</label>
			<div id="createEndUserEmailDiv" class="col-sm-8">
				<form:input class="form-control" type="text"
					placeholder="Enter email id" path="email" maxlength="64"
					required="true" />
				<form:errors path="email" class="parsley-required" />
			</div>
		</div>
		<div id="createEndUserMobileNumberDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="createEndUser-form.mobileNumber-label" /> <span
				class="required">*</span>
			</label>
			<div id="createEndUserMobileNumberDiv" class="col-sm-8">
				<form:input type="text" class=" input-mask form-control"
					placeholder="Enter 10 digit mobile number"
					onkeypress="return AllowOnlyNumbers(event);" path="mobileNumber"
					maxlength="10" required="true"
					data-inputmask="&apos;mask&apos;:&apos;9999999999&apos;" />
				<form:errors path="mobileNumber" class="parsley-required" />
			</div>
		</div>
		<div id="createEndUserIdproofDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="createEndUser-form.idProof-label" /> <span class="required">*</span>
			</label>
			<div id="createEndUserIdproofDiv" class="col-sm-8">
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
		<div id="createEndUserIdProofIdDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="createEndUser-form.idProofId-label" /> <span
				class="required">*</span>
			</label>
			<div id="createEndUserIdProofIdDiv" class="col-sm-8">
				<form:input type="text" class=" input-mask form-control"
					placeholder="Enter id proof number" path="idProof" maxlength="12"
					required="true" />
				<form:errors path="idProof" class="parsley-required" />
			</div>
		</div>
		<div id="createEndUserAddrDiv" class="form-group">
			<label for="" class="col-sm-2 control-label"> <spring:message
					code="createEndUser-form.addr-label" /> <span class="required">*</span>
			</label>
			<div id="createEndUserAddrDiv" class="col-sm-8">
				<form:textarea class="form-control" path="addr"
					placeholder="Enter address" maxlength="1024"
					data-parsley-trigger="keyup" data-parsley-length="[20, 1024]"
					data-parsley-validation-threshold="10"></form:textarea>
				<form:errors path="addr" class="parsley-required" />
			</div>
		</div>
		<div id="CreteEndUserCheckBoxDiv">
			<div>
				<input type="checkbox" name="terms">I accepted terms and conditions 
				<a href="#" style="margin-left:10px;text-decoration: underline;" id="myBtn">T&C Help</a>
			</div>
		</div>
		
		<div id="myModal" class="modal">
		  <div class="modal-content">
		  <span class="close">&times;</span>
			    <div class="modal-body">
			     <h2 style="color:red;margin:20px 0;">Terms & Conditions</h2>
			      <ul style="list-style-type:circle;">
			      	<li>Only the above empanelled vendors are permitted to supply the material. No outside vendor will be allowed. </li>
			      	<li>The cost of damages if any will be deducted and the balance will be refunded, within 24 hours of conclusion of function.</li>
			      	<li>All the payment shall be made in online only.</li>
			      	<li>Booking of the venue will be confirmed only on receipt of the total amount of Hire charges and permissions obtained and produced from the police (law and order and traffic), Excise dept. etc.</li>
			      	<li>Cooking has to be done at specified areas only.</li>
			      	<li>The premises require to be kept neat and clean. No usage of plastics.</li>
			      	<li>Modifications to the existing ambiance of the Venue/Hall not allowed. Erection of Temporary Sheds and any erection of artificial decoration with
			      	 POP/ Fiber which spoil the natural and ethnic ambiance will not be allowed. </li>
			      	<li>	No fire works shall be allowed as there are thatched roof hutments all over Shilparamam.</li>
			      	<li>Political, religious meetings and banners are not permitted. </li>
			      	<li>The Shilparamam authority will have the right to stop any activity if noticed contrary to the above instructions.</li>
			      	<li>The activities are to be closed by 11.00 pm maximum as per the orders of the police commissioner.</li>
			      	<li>The plantations and the trees in the premises shall not be damaged or destroyed.</li>
			      	<li>Display of any kind of products as a part of the product promotion or brand promotion will not be permitted either 
			      		in the form of flex, banners or any other kind.</li>
			      	<li>As per the orders of Supreme Court of India, using of Music system (45 decibels) will be allowed up 
			      	to 10.00pm with the prior permission of the Assistant Commissioner of Police Madhapur region. 
			      		Volume of the music should be confined only to
			      	 the inside premises alone. No D.J is permitted. Cinematic dances and club dances are strictly prohibited.</li>
			      	<li>The event manager/organizer has to get the prior approval of the layout of the utilization of the area of the proposed activities
			      	 from the Special Officer Shilparamam well in advance before the execution of works on ground. </li>
			      	<li>The hirer/organizer is responsible for any loss/ damage either to the person or property,
			      	 whether by his own fault or of his agent/event manager/ contractor / guest engaged for the purpose in organizing the function and
			      	 he is liable to all actions. The actual cost will be recovered from the caution deposit.</li>
			      	<li>Cleaning of the premises and shifting of the leftover food and material shall have to be done immediately after the function is over i.e. on the same night. 
			      	No leftovers shall appear in the premises after the function.</li>
			      	<li>No Tandoor batty and Ice etc., will be allowed on the lawns. They should be placed at the designated areas only.</li>
			      	<li>The caterer has to provide adequate garbage bins for wet and dry garbage and make sure that they are placed at the designated areas.</li>
			      	<li>Caterers and the organizers/ hirers should take all precautionary measures for up keep of the area keeping in view the environment and pollution issues.</li>
			      	<li>Spitting and smoking anywhere in the premises is strictly prohibited.</li>
			      	<li>Halogens and high power lights shall not be tied to the trees and shrubs either at the top or at the bottom of the trees.</li>
			      	<li>Pet animals are not allowed.</li>
			      	<li>The cars and the service vehicles will be allowed 3hours before the function and they shall be parked at the designated place only.
				The designated officer / security personnel of the Shilparamam shall always have the access to the venue.</li>
			      	<li>In the event of violation of any of the rules cited, the management has got every right to cancel the allotment,
			      	 even at the eleventh hour and black list hirer/organizer or caterer for using the Shilparamam in future. The entire deposit amount will be forfeited.</li>
			      	<li>In the event of cancellations, deduction will be made as indicated below and the balance will be refunded.</li>
			      </ul>
			      <ol>
			      	<li>Prior to 15days of the event- 25% of the Hire Charges.</li>
			      	<li>Prior to 5 days of the event- 35% of the Hire Charges.</li>
			      	<li>Prior to 3 days of the event- 40% of the Hire Charges.</li>
			      	<li>Prior to 2 days of the event- 60% of the Hire Charges.</li>
			      	<li>Prior to 1 day of the event- 75% of the Hire Charges.</li>
			      	<li>After the day of the event- 100% of the Hire Charges.</li>
			      </ol>
			      <ul style="list-style-type:circle;">
			      	<li>The Party is responsible for taking the required permissions like POLICE-PPL-IPRS and to bring the necessary Permission / NOC from Entertainment tax issued by the Telangana State
			      	 Commercial Tax Department and submit the same at our office at least 2 days before the event date.</li>
			      	<li>Serving of liquor shall not be allowed without valid license.</li>
			      	<li>The Tariff for 12 hours only including setup and clearance time.</li>
			      	<li>Hirer of the venue should select and inform the vendor at least five days before the Event date.</li>
			      </ul>
			      <h3 style="color:green;">Shilparamam has setup the following empanelled Tent house material suppliers for the events and the programmes being carried on in its premises.</h3>
			       <ol>
			       		<li style="color:red;"><h4>Mr. Surat Singh Malhotra</h4></li>
			       		<p style="margin-top:10px;">Namdhari Tent House & Decorators</p>
			       		<span>8-2-584/7, </span><span>Road No:9, </span><span>Banjara Hills,</span><span>Hyderabad-500034</span><br>
			       		<span>Ph: 98490-40609, </span><span>9391434445, </span><span>040-23351990/ 23357287</span><br>
			       		<span>Email: nth@namdharievents.com </span>
			       		
			       		<li style="margin-top:20px;color:red;">Mr.Praveen Kumar Agarwal</li>
			       		<p style="margin-top:10px;">M/s Pavan Supplying Company</p>
			       		<span>H.no: 1-8-303/16/A, </span><br>
			       		<span>Sindhi colony, </span><span>9P.G road, </span><span>Secunderabad-500003</span><br>
			       		<span>Ph: 9246337188, </span><span>9246521072 </span><br>
			       		<span>Email:praveen7188@gmail.com</span>
			       		
			       		<li style="margin-top:20px;color:red;">Mr.M Zaheeruddin</li>
			       		<p style="margin-top:10px;">M/s Cyber Tent House</p>
			       		<p>C/o M&S Bottling Company</p>
			       		<span>Plot No: 64,</span><span>Street No.1,</span><span>Sagar Society,</span><span>Road No.2,</span><br>
			       		<span>Banjara Hills ,</span><span>Hyderabad-500034</span><br>
			       		<span>Ph: 9849036397, </span><span>9246828891</span><br>
			       		<span>Email: zaheercyber@yahoo.com</span>
			       	</ol> 
			       	<ol>
			       		<h4 style="margin-top:10px;">Electrical:</h4>
			       		
			       		<li style="margin-top:20px;color:red;">Mr.Praveen Kumar Agarwal</li>
			       		<p style="margin-top:10px;">M/s Pavan Supplying Company</p>
			       		<span>H.no: 1-8-303/16/A, </span><br>
			       		<span>Sindhi colony, </span><span>9P.G road, </span><span>Secunderabad-500003</span><br>
			       		<span>Ph: 9246337188, </span><span>9246521072 </span><br>
			       		<span>Email:praveen7188@gmail.com</span>
			       	</ol>
			       		
			       
			    </div>
		  </div>
		</div>
		
		
			
		<div class="divider"></div>
		<div id="createEndUserButtonsDiv" class="form-group">
			<div class="col-sm-offset-2 col-sm-8">
				<div class="example-box-wrapper">
					<button id="endUserCreateSubmitBt" name="endUserCreateSubmitBt"
						type="submit" class="btn btn-success">
						<i class="glyph-icon icon-plus-square"></i>&nbsp;
						<spring:message code="createEndUser-form.submit-button" />
					</button>
					<button id="endUserCreateRestBt" name="endUserCreateResetBt"
						type="reset" class="btn btn-warning">
						<i class="glyph-icon icon-minus-square"></i>&nbsp;
						<spring:message code="createEndUser-form.reset-button" />
					</button>
				</div>
			</div>
		</div>
	
	</form:form>
<script>
function checkForm(form)
{
	if(!form.terms.checked)
		{
			alert("Please accept the terms and conditions");
			form.terms.focus();
			return false;
		}
	return true;
}

function changeVenue() {
	var venueId = $("#venueId").val();
	if(venueId > 0){
	$.ajax({
		type : 'GET',
		url : '/apu/endUser/create/form?venueId=' + venueId,
		success : function(returnedData) {
			
			$("#page-content-holder").html(returnedData);
			$("#venueId").val(venueId);
			
			//$('#blockDates').val(returnedData.billList);
			//$('#daysLimit').val(returnedData.venueDaysLimit);
			
			var array = $('#blockDates').val().split(', ');
			var days =$('#daysLimit').val();
			
			//var array = returnedData.billList.split(', ');
			//var days =returnedData.venueDaysLimit;
	    	$('.bootstrap-datepicker').datepicker({
	    		minDate : '+1d',
	    		maxDate: days,
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

function hideDiv() {
	document.getElementById('hidingDiv').hidden = "hidden";
}

var modal = document.getElementById("myModal");
var btn = document.getElementById("myBtn");
var span = document.getElementsByClassName("close")[0];
	btn.onclick = function() 
	{
		modal.style.display = "block";
	}
	span.onclick = function() 
	{
		modal.style.display = "none";
	}
window.onclick = function(event){
	if (event.target == modal) 
	{
	 modal.style.display = "none";
	}
}


</script>