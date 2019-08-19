<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<div id="page-content">
	<h3 class="title-hero">Organizations</h3>
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
					<th>Name</th>
					<th>Type</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty data.organizationsList}">
					<c:forEach var="obj" items="${data.organizationsList}">
						<tr>
							<td>${obj.name }</td>
							<td>${obj.type }</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>

	</div>
</div>
