<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<style>
.pagination {
margin-top: 15px;
}
</style>

<div id="page-content">
<div id="listMarqueeDiv" class="example-box-wrapper">
<h3>
<spring:message code="marqueeList-form.marquee-label" />
<span class="badge bg-red">${data.objCount}</span>
</h3>
<div class="divider"></div>
<c:if test="${not empty data.message_success}">
<div id="marqueeActionSuccessMsgDiv" class="example-box-wrapper">
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
fadeOutDiv('marqueeActionSuccessMsgDiv');
</script>
</div>
</c:if>
<c:if test="${not empty data.message_error}">
<div id="marqueeActionFailedMsgDiv" class="example-box-wrapper">
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
fadeOutDiv('marqueeActionFailedMsgDiv');
</script>
</div>
</c:if>
<div id="marqueeButtonDiv" style="margin-bottom: 60px;">
<div style="float: left;">
<button id="marqueeCreateBt" name="marqueeCreateBt"
class="btn btn-success"
onClick="javascript:createObject('marqueeListTable', 'marqueeListTableHeaderCheckbox', 'Marquee', '/app/marquee/create/form', 'page-content-holder');">
<i class="glyph-icon icon-plus-square"></i>&nbsp;
<spring:message code="marqueeList-form.create-button" />
</button>
<button id="marqueeUpdateBt" name="marqueeeUpdateBt"
class="btn btn-info"
onClick="javascript:updateObject('marqueeListTable', 'marqueeListTableHeaderCheckbox', 'Marquee', '/app/marquee/update/form?reqPage=${data.paginatedResponse.reqPage}&pageSize=${data.paginatedResponse.pageSize}&id=', 'page-content-holder');">
<i class="glyph-icon icon-pencil-square"></i>&nbsp;
<spring:message code="marqueeList-form.update-button"/>
</button>
</div>
<div style="float: right">
<button id="marqueeRefreshBt" name="marqueeRefreshBt"
class="btn btn-azure"
onClick="javascript:loadContent('/app/marquee/list','page-content-holder');">
<i class="glyph-icon icon-refresh"></i>&nbsp;
<spring:message code="marqueeList-form.refresh-button" />
</button>
</div>
</div>
<%@include file="/WEB-INF/jsp/common/paginatedNavBar.jsp"%>
<div id="marqueeFormDiv" class="example-box-wrapper">
<table class="table table-striped table-bordered"
id="marqueeListTable" name="marqueeListTable">
<thead>
<tr>
<c:if test="${not empty data.paginatedResponse.resultList}">
<th><input type="checkbox"
id="marqueeListTableHeaderCheckbox" class="custom-checkbox"
onclick="checkAllTableCheckbox(this, 'marqueeListTable')"></th>
</c:if>
<th><spring:message code="marqueeList-form.id-label" /></th>
<th><spring:message code="marqueeList-form.Type-label" /></th>
<th><spring:message code="marqueeList-form.Text-label" /></th>
</tr>
</thead>
<tbody>
<c:if test="${not empty data.paginatedResponse.resultList}">
<c:forEach var="obj" items="${data.paginatedResponse.resultList}">
<tr>
<td align="center"><input type="checkbox" id="${obj.id}"
class="custom-checkbox"></td>
<td>${obj.id}</td>
<td><spring:message code="MarqueeType.${obj.type}" /></td>
<td>${obj.text}</td>
</tr>
</c:forEach>
</c:if>
</tbody>
</table>
</div>
</div>
</div>