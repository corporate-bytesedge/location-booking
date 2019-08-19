<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<style>
	.pagination{
 margin-top: 15px;
}
</style>
<div id="page-content">
	<div id="listBillDiv" class="example-box-wrapper">
		<h3>
			<spring:message code="billList-form.bill-label" />
				<span class="badge bg-red">${data.objCount}</span>
		</h3>
		<div class="divider"></div>
			<c:if test="${not empty data.message_success}">
				<div id="billActionSuccessMsgDiv" class="example-box-wrapper">
					<div class="alert alert-success">
						<div class="bg-green alert-icon">
							<i class="glyph-icon icon-check"></i>
						</div>
						<div class="alert-content">
							<h4 class="alert-title">Action Success</h4>
							<p>${data.message_success}</p>
						</div>
					</div>
						<script type="text/javascript">
				        	fadeOutDiv('billActionSuccessMsgDiv');
						</script>
				</div>
			</c:if>
			<c:if test="${not empty data.message_error}">
				<div id="billActionFailedMsgDiv" class="example-box-wrapper">
					<div class="alert alert-danger">
						<div class="bg-red alert-icon">
							<i class="glyph-icon icon-check"></i>
						</div>
						<div class="alert-content">
							<h4 class="alert-title">Action Failed</h4>
							<p>${data.message_error}</p>
						</div>
					</div>
						<script type="text/javascript">
				        	fadeOutDiv('billActionFailedMsgDiv');
						</script>
				</div>
			</c:if>
			
			<div class="row">
			<div id="billButtonDiv" style="margin-bottom:60px;">
			<div id="createEnduserVenueDateDiv" class="col-sm-3 input-group date">
				<input class="bootstrap-datepicker form-control" type="text" id="fromDate"
					value="${data.fromDate}"placeholder="From Date" maxlength="10"
					required="true"
					data-inputmask="&apos;mask&apos;:&apos;99-99-9999&apos;" />
			</div>
			<div id="createEnduserVenueDateDiv" class="col-sm-3 input-group date">
				<span class="glyphicon glyphicon-calendar"></span>
				<input class="bootstrap-datepicker form-control" type="text" id="todate"
					value="${data.toDate}" placeholder="To Date" maxlength="10"
					required="true"
					data-inputmask="&apos;mask&apos;:&apos;99-99-9999&apos;" />
			</div>
			<div id="createEndUserNameDiv" class="col-sm-3">
					<select  id="venue" class="validate btn btn-default"  required="true" >
				    <option value="0" selected="true">Select a Venue</option> 
					<option value="1" >Sampradhaya vedika</option>
					<option value="2" >Rock Heights</option>
					<option value="3" >Mountain Heights</option>
					<option value="4" >Shilpa Seema</option>
				</select> 
			</div>
			<button id="endUserCreateSubmitBt"name="endUserCreateSubmitBt" onclick="getfilteredList();"
						type="submit" class="btn btn-success">
						<i class="glyph-icon icon-plus-square"></i>&nbsp;
						<spring:message code="createEndUser-form.submit-button" />
					</button>
			</div>
			</div>
			
    	<div id="billButtonDiv" style="margin-bottom:60px;">
			<div style="float:left;">
				<%-- <button id="billViewBt" name="billViewBt" class="btn btn-purple" onClick="javascript:viewObject('billListTable', 'billListTableHeaderCheckbox', 'Bill', '/app/bill/view/form?reqPage=${data.paginatedResponse.reqPage}&pageSize=${data.paginatedResponse.pageSize}&id=', 'page-content-holder');">
					<i class="glyph-icon icon-file"></i>&nbsp;<spring:message code="billList-form.view-button"/>
					</button> --%>
				<button id="billExportBt" name="billExportBt" class="btn btn-primary" onClick="window.location='/app/bill/list/export'">
					<i class="glyph-icon icon-arrow-down"></i>&nbsp;<spring:message code="billList-form.export-button"/>
				</button>
			</div>
			<div style="float:right">
				<button id="billRefreshBt" name="billRefreshBt" class="btn btn-azure" onClick="javascript:loadContent('/app/bill/list','page-content-holder');">
					<i class="glyph-icon icon-refresh"></i>&nbsp;<spring:message code="billList-form.refresh-button"/>
				</button>
			</div>
        </div>
        <%@include file="/WEB-INF/jsp/common/paginatedNavBar.jsp" %>
		<div id="billFormDiv" class="example-box-wrapper">
			<table class="table table-striped table-bordered" id="billListTable" name="billListTable">
				<thead>
					<tr>
					<c:if test="${not empty data.paginatedResponse.resultList}">
						<!-- <th><input type="checkbox" id="billListTableHeaderCheckbox" class="custom-checkbox" onclick="checkAllTableCheckbox(this, 'billListTable')"></th>
						 --></c:if>
						<th><spring:message code="billList-form.applicationId-label"/></th>
						<th><spring:message code="billList-form.name-label"/></th>
						<%-- <th><spring:message code="billList-form.email-label"/></th> --%>
						<th><spring:message code="billList-form.mobileNumber-label"/></th>
						<th><spring:message code="billList-form.type-label"/></th>
						<%-- <th><spring:message code="billList-form.idProof-label"/></th>
						 --%><th><spring:message code="billList-form.date-label"/></th>
						<th><spring:message code="billList-form.venue-label"/></th>
						<th><spring:message code="billList-form.purpose-label"/></th>
						<th><spring:message code="billList-form.price-label"/></th>
						<%-- <th><spring:message code="billList-form.cgst-label"/></th>
						<th><spring:message code="billList-form.sgst-label"/></th> --%>
						<th><spring:message code="billList-form.total-label"/></th>
						<%-- <th><spring:message code="billList-form.paymentMode-label"/></th> --%>
						<th><spring:message code="billList-form.paymentStatus-label"/></th>
						<th><spring:message code="billList-form.createdTime-label"/></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${not empty data.paginatedResponse.resultList}">
						<c:forEach var="obj" items="${data.paginatedResponse.resultList}">
							<tr>
								<%-- <td align="center">
									<input type="checkbox" id="${obj.id}" class="custom-checkbox">
								</td> --%>
								<td>${obj.applicationId}</td>
								<td>${obj.name}</td>
								<%-- <td>${obj.email}</td> --%>
								<td>${obj.mobileNumber}</td>
								<td>${obj.idProofType}</td>
								<%-- <td>${obj.idProof}</td> --%>
								<td><fmt:formatDate value="${obj.bookingDate}" pattern="dd-MMM-yyyy" /></td>
								<td>${obj.propertyName}</td>
								<td>${obj.purposeName}</td>
								<td>${obj.price}</td>
								<%-- <td>${obj.cgst}</td>
								<td>${obj.sgst}</td> --%>
								<td>${obj.total}</td>
								<%-- <td>${obj.paymentMode}</td> --%>
								<td>${obj.paymentStatus}</td>
								<td><fmt:formatDate value="${obj.createdTime}" pattern="dd-MMM-yyyy" /></td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>		
	</div>
</div>
<script>
	
	 
	function getfilteredList(){
		var fromDate = $("#fromDate").val();
		var toDate = $("#todate").val();
		var venue = $("#venue").val();
		
		if(fromDate !="" && toDate != "" && venue != ""){
          	var fromDateAr = fromDate.split('-');
          	fromDate1 = fromDateAr[2] + '-' + fromDateAr[1] + '-' + fromDateAr[0];
          	var toDateAr = toDate.split('-');
          	toDate1 = toDateAr[2] + '-' + toDateAr[1] + '-' + toDateAr[0];
			$.ajax({
				type : 'GET',
				url : '/app/filterted/list?venue=' + venue+ '&fromDate='+ fromDate1 + '&toDate=' +toDate1 ,
				success : function(returnedData) {
					$("#page-content-holder").html(returnedData);
					$("#fromDate").val(fromDate);
					$("#todate").val(toDate);
					$("#venue").val(venue);
				}
			});
		}else{
			alert("Please select all fields");
		}
	}
	
	$( function() {
		var fromDate = $("#fromDate").val();
		var toDate = $("#todate").val();
		    $( "#fromDate" ).datepicker({
		    	dateFormat: "dd-mm-yy",
		    		onSelect: function (selected) {
			             var dt = $("#fromDate").val();
			             $("#todate").datepicker("option", "minDate", dt);
			         }
		    	
		    });
		  } );
	 
	 $( function() {
		    $( "#todate" ).datepicker({
		    	dateFormat: "dd-mm-yy",
		    		onSelect: function (selected) {
			             var dt = $("#todate").val();
			             $("#fromDate").datepicker("option", "maxDate", dt);
			         }
		    });
		  } );
	 	
	 
	 
	 
	 
	 
	</script>