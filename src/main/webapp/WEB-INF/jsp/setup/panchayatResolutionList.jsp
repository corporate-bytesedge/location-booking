<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<div id="page-content">
	<h3 class="title-hero">Panchayat Resolution</h3>
	<div class="example-box-wrapper">
		<button class="btn btn-success">
			<i class="glyph-icon icon-plus-square"></i>&nbsp;Add
		</button>
		<button class="btn btn-info">
			<i class="glyph-icon icon-pencil-square"></i>&nbsp;Edit
		</button>
		<button class="btn btn-warning">
			<i class="glyph-icon icon-minus-square"></i>&nbsp;Lock
		</button>
		<table class="table table-striped table-bordered">
			<thead>
				<tr>
					<th>Panchayat</th>
					<th>Subject</th>
					<th>Description</th>
					<th>Date</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty data.panchayatResolutionList}">
					<c:forEach var="obj" items="${data.panchayatResolutionList}">
						<tr>
							<td>${obj.orgId }</td>
							<td>${obj.subject }</td>
							<td>${obj.descr}</td>
							<td>${obj.date}</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>

	</div>
</div>
