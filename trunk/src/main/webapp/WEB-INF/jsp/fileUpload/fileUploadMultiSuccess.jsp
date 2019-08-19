<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<div id="page-content">

<div class="form-container">
	<h1>Spring 4 MVC Multi File Upload Example</h1>
	<form:form method="POST" modelAttribute="multiFileBucket"
		enctype="multipart/form-data" class="form-horizontal">

		<c:forEach var="v" varStatus="vs" items="${multiFileBucket.files}">
			<form:input type="file" path="files[${vs.index}].file"
				id="files[${vs.index}].file" class="form-control input-sm" />
			<div class="has-error">
				<form:errors path="files[${vs.index}].file" class="help-inline" />
			</div>
		</c:forEach>
		<br />
		<div class="row">
			<div class="form-actions floatRight">
				<input type="submit" value="Upload" class="btn btn-primary btn-sm">
			</div>
		</div>
	</form:form>
</div>
</div>