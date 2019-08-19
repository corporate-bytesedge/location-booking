<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<style>
	.pagination{
 margin-top: 15px;
}
</style>
<div id="page-content">
	<div id="listPropertyImagesDiv" class="example-box-wrapper">
		<h3>
			<spring:message code="propertyImagesList-form.propertyImages-label" />
				<span class="badge bg-red">${data.objCount}</span>
		</h3>
		<div class="divider"></div>
			<c:if test="${not empty data.message_success}">
				<div id="propertyImagesActionSuccessMsgDiv" class="example-box-wrapper">
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
				        	fadeOutDiv('propertyImagesActionSuccessMsgDiv');
						</script>
				</div>
			</c:if>
			<c:if test="${not empty data.message_error}">
				<div id="propertyImagesActionFailedMsgDiv" class="example-box-wrapper">
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
				        	fadeOutDiv('propertyImagesActionFailedMsgDiv');
						</script>
				</div>
			</c:if>
			<div style="margin-bottom: 60px;">
				<div style="float: left;">
				<button id="propertyImagesCreateBt" name="propertyImagesCreateBt" class="btn btn-success" onClick="javascript:createObject('propertyImagesListTable', 'propertyImagesListTableHeaderCheckbox', 'PropertyImages', '/app/propertyImages/create/form', 'page-content-holder');">
					<i class="glyph-icon icon-plus-square"></i>&nbsp;
					<spring:message code="propertyImagesList-form.create-button" />
				</button>
				<button id="propertyImagesViewBt" name="propertyImagesViewBt" class="btn btn-purple" onClick="javascript:viewObject('propertyImagesListTable', 'propertyImagesListTableHeaderCheckbox', 'PropertyImages', '/app/propertyImages/view/form?reqPage=${data.paginatedResponse.reqPage}&pageSize=${data.paginatedResponse.pageSize}&id=', 'page-content-holder');">
					<i class="glyph-icon icon-file"></i>&nbsp;<spring:message code="propertyImagesList-form.view-button"/>
					</button>
					<button id="propertyImagesUpdateBt" name="propertyImagesUpdateBt" class="btn btn-info" onClick="javascript:updateObject('propertyImagesListTable', 'propertyImagesListTableHeaderCheckbox', 'PropertyImages', '/app/propertyImages/update/form?reqPage=${data.paginatedResponse.reqPage}&pageSize=${data.paginatedResponse.pageSize}&id=', 'page-content-holder');">
					<i class="glyph-icon icon-pencil-square"></i>&nbsp;<spring:message code="propertyImagesList-form.update-button"/>
					</button>
					<button id="propertyImagesExportBt" name="propertyImagesExportBt" class="btn btn-primary" onClick="window.location='/app/propertyImages/list/export?reqPage=${data.paginatedResponse.reqPage}'">
						<i class="glyph-icon icon-arrow-down"></i>&nbsp;
							<spring:message code="propertyImagesList-form.export-button" />
					</button>
				</div>
				<div style="float:right">
				<button id="propertyImagesRefreshBt" name="propertyImagesRefreshBt" class="btn btn-azure" onClick="javascript:loadContent('/app/propertyImages/list','page-content-holder');">
					<i class="glyph-icon icon-refresh"></i>&nbsp;<spring:message code="propertyImagesList-form.refresh-button"/>
				</button>
			</div>
			</div>
			<%@include file="/WEB-INF/jsp/common/paginatedNavBar.jsp" %>
			<div class="example-box-wrapper">
				<table class="table table-striped table-bordered" id="propertyImagesListTable" name="propertyImagesListTable">
					<thead>
						<tr>
						<c:if test="${not empty data.paginatedResponse.resultList}">
							<th><input type="checkbox" id="propertyImagesListTableHeaderCheckbox" class="custom-checkbox"	onclick="checkAllTableCheckbox(this, 'propertyImagesListTable')"></th>
							</c:if>
							<th><spring:message code="propertyImagesList-form.id-label" /></th>
							<th><spring:message code="propertyImagesList-form.name-label" /></th>
							<th><spring:message	code="propertyImagesList-form.path-label" /></th>
							<th><spring:message	code="propertyImagesList-form.size-label" /></th>

						</tr>
					</thead>
						<tbody>
							<c:if test="${not empty data.paginatedResponse.resultList}">
								<c:forEach var="obj" items="${data.paginatedResponse.resultList}">
									<tr>
										<td align="center"><input type="checkbox" id="${obj.id}" class="custom-checkbox"></td>
										<td>${obj.id }</td>
										<td>${obj.name }</td>
										<td>${obj.path}</td>
										<td>${obj.size}</td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
				</table>
			</div>
		</div>
	</div>
