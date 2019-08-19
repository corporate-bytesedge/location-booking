<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<style>
	.pagination{
 margin-top: 15px;
}
</style>
<div id="page-content">
	<div id="listLoginHistoryDiv" class="example-box-wrapper">
		<h3>
			<spring:message code="loginHistoryList-form.loginHistory-label"/>
			<span class="badge bg-red">${data.objCount}</span>
		</h3>
		
		<div class="divider"></div>
		<c:if test="${not empty data.message_success}">
			<div id="loginHistoryActionSuccessMsgDiv" class="example-box-wrapper">
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
		        	fadeOutDiv('loginHistoryActionSuccessMsgDiv');
				</script>
		    </div>
   		</c:if>
   		<c:if test="${not empty data.message_error}">
			<div id="loginHistoryActionFailedMsgDiv" class="example-box-wrapper">
		        <div id="loginHistoryalertDiv" class="alert alert-danger">
		            <div class="bg-red alert-icon">
		                <i class="glyph-icon icon-check"></i>
		            </div>
		            <div class="alert-content">
		                <h4 class="alert-title">Action Failed</h4>
		                <p>${data.message_error}</p>
		            </div>
		        </div>
		        <script type="text/javascript">
		        	fadeOutDiv('loginHistoryActionFailedMsgDiv');
				</script>
		    </div>
   		</c:if>
    		
    	<div id="loginHistoryButtonDiv" style="margin-bottom:60px;">
			<div style="float:right">
				<button id="loginHistoryRefreshBt" name="loginHistoryRefreshBt" class="btn btn-azure" onClick="javascript:loadContent('/app/loginHistory/list','page-content-holder');">
					<i class="glyph-icon icon-refresh"></i>&nbsp;<spring:message code="loginHistoryList-form.refresh-button"/>
				</button>
			</div>
        </div>
		<%@include file="/WEB-INF/jsp/common/paginatedNavBar.jsp" %>
		<div id="surevyDeviceFormDiv" class="example-box-wrapper">
			<table class="table table-striped table-bordered" id="loginHistoryListTable" name="loginHistoryListTable">
				<thead>
					<tr>
					<c:if test="${not empty data.paginatedResponse.resultList}">
						</c:if>
						<th><spring:message code="loginHistoryList-form.orgId-label"/></th>
						<th><spring:message code="loginHistoryList-form.loginName-label"/></th>
						<th><spring:message code="loginHistoryList-form.loginTime-label"/></th>
						<%-- <th><spring:message code="loginHistoryList-form.logoutTime-label"/></th> --%>
						<th><spring:message code="loginHistoryList-form.logoutType-label"/></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${not empty data.paginatedResponse.resultList}">
						<c:forEach var="obj" items="${data.paginatedResponse.resultList}">
							<tr>
								
								<td>${obj.orgName}</td>
								<td>${obj.loginName }</td>
								<td><fmt:formatDate value="${obj.loginTime}" pattern="dd-MMM-yyyy HH:mm:ss" /></td>
<%-- 								<td><fmt:formatDate value="${obj.logoutTime}" pattern="dd-MMM-yyyy HH:mm:ss" /></td> --%>
								<td><spring:message code="loginHistoryList-form.logoutType.${obj.logoutType}-label"/></td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>		
	</div>
</div>
