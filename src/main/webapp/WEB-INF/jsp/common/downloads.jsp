<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" href="/assets/images/icons/favicon.png">

<!-- CSS -->
<link rel="stylesheet" type="text/css" href="/assets-minified/themes/bratilius/frontend/color.css">
<link rel="stylesheet" type="text/css" href="/assets-minified/main.css">
<link rel="stylesheet" type="text/css" href="/css/common.css">
<link rel="stylesheet" type="text/css" href="/css/responsiveDashboard.css">
<link rel="stylesheet" type="text/css" href="/assets-minified/widgets/modal/modal.css">
<link rel="stylesheet" type="text/css" href="/css/jquery-confirm.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">

<!-- JS Core -->
<script type="text/javascript" src="/assets-minified/js-core/jquery-core.js"></script>
<script type="text/javascript" src="/assets-minified/js-core.js"></script>
<script type="text/javascript" src="/assets-minified/all-demo.js"></script>
<script type="text/javascript" src="/js/js-common.js"></script>
<script type="text/javascript" src="/assets-minified/widgets/modal/modal.js"></script>
<script type="text/javascript" src="/js/jquery-confirm.min.js"></script>
<script type="text/javascript" src="/js/Chart.min.js"></script>
<script type="text/javascript" src="/js/loadingoverlay.min-7938e101f7288eafea5a9c9f0cb373a8835c480484ce2938ef9134304c62b119.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

<style type="text/css">
.hr-line{
  		border-bottom: 1px dashed black;
  		width: 30%;
  		display:flex;
  	}
  	.btn-cls{
  	 	margin-top: 15px;
  	}
  	.btn-cls button{
  		padding: 8px 20px 8px 20px;
  	}
  	.fonth3 h3{
  		font-family: raleway;
  	}

.img-size-one {
	width: 200px;
	margin-top: -40px !important;
	margin: 0 -25px;
}

.header-data {
    border-bottom: 2px solid lavender;
}
.fonth3{
	margin-bottom:100px;
}

.container {
    width: 100%;
}
.text-center-text {
	text-align: center !important;
	margin-top:120px !important;
}


</style>


<script>
$(document).ready(function(){
  $(".hidden-desktop").click(function(){
    $(".main-nav.nav-alt").toggle();
  });
});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ContactUs</title>
</head>
<body>
<div>
	<%@ include file="/WEB-INF/jsp/header.jsp"%>
</div>
	<div id=" container">
		<div class="">
		<h2 class="text-center-text">Shilpakalavedhika Downloads</h2>
		<hr class="hr-line">

		<div class="row fonth3">
			<div class="col-md-offset-1 col-md-8">
				<h3>Shilpakalavedika Venue Booking Application Form 2019<span><img src="/img/new-one.gif"></span></h3>
			</div>

			<div class="col-md-3 btn-cls">
				<button type="button" class="btn btn-danger">Download</button>
			</div>

			<div class="col-md-offset-1 col-md-8">
				<h3>Shilpakalavedika Venue Tarrif Card 2019</h3>
			</div>

			<div class="col-md-3 btn-cls">
				<button type="button" class="btn btn-danger">Download</button>
			</div>

			<div class="col-md-offset-1 col-md-8">
				<h3>Shilpakalavedika No Objection Certificate</h3>
			</div>

			<div class="col-md-3 btn-cls">
				<button type="button" class="btn btn-danger">Download</button>
			</div>

			<div class="col-md-offset-1 col-md-8">
				<h3>Tenders for Dining Hall Water Proof Roofing work</h3>
			</div>

			<div class="col-md-3 btn-cls">
				<button type="button" class="btn btn-danger">Download</button>
			</div>
		</div>
	</div>
<div>
	<%@ include file="/WEB-INF/jsp/footer.jsp"%>
</div>
</body>
</html>