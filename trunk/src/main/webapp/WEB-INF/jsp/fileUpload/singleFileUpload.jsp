<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<div id="page-content">
<script type="text/javascript">
	$(document).ready(function() {
	  $('#file').on('change', function(evt) {
	    if(this.files[0].size > 3145728) {
	    	$('#submitBtn').disabled = true;
	    	$('#submitBtn').innerHtml = "File size should not be more than 3 MB";
	    	alert('File size should not be more than 3 MB' + $('#submitBtn').innerHtml);
	    } else {
	    	$('#submitBtn').disabled = false;
	    	$('#submitBtn').innerHtml = "Start Upload";
	    }
	  });
	});
	
</script>

<div class="form-container">
	<form:form method="POST" modelAttribute="fileBucket" name="fileBucket" id="singleUploadForm"
		onsubmit="return false;"
		action="/app/singleUpload"
		enctype="multipart/form-data" class="form-horizontal">

		<div class="row">
			<div class="form-group col-md-12">
				<label class="col-md-3 control-lable" for="file">Upload a File (Maximum size 3 MB)</label>
				<div class="col-md-7">
					<form:input type="file" path="file" id="file" class="form-control input-sm" />
					<div class="has-error">
						<form:errors path="file" class="help-inline" />
					</div>
				</div>
			</div>
		</div>
		
		<button id="submitBtn" name="submitBtn"
			type="submit" class="btn btn-success" 
			onclick="javascript:submitForm('/app/singleUpload', 'singleUploadForm', 'page-content-holder', 'multipart/form-data')"
			>
			<i class="glyph-icon icon-plus-square"></i>&nbsp;
			Select a File
		</button>

	</form:form>
</div>
</div>