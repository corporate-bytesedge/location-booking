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

<!-- JS Core -->
<script type="text/javascript" src="/assets-minified/js-core/jquery-core.js"></script>
<script type="text/javascript" src="/assets-minified/js-core.js"></script>
<script type="text/javascript" src="/assets-minified/all-demo.js"></script>
<script type="text/javascript" src="/js/js-common.js"></script>
<script type="text/javascript" src="/assets-minified/widgets/modal/modal.js"></script>
<script type="text/javascript" src="/js/jquery-confirm.min.js"></script>
<script type="text/javascript" src="/js/Chart.min.js"></script>
<script type="text/javascript" src="/js/loadingoverlay.min-7938e101f7288eafea5a9c9f0cb373a8835c480484ce2938ef9134304c62b119.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<style type="text/css">
.first-name {
	border: 0;
	border-bottom: 2px solid #ddd;
	box-shadow: none;
	border-radius: 0px;
	width: 100%;
	margin-bottom: 35px;
}

.text-area-data {
	border: 0;
	border-bottom: 2px solid #ddd;
	box-shadow: none;
	border-radius: 0px;
	width: 100%;
}

.contact-us {
	padding-bottom: 10px;
	font-size: 40px;
	font-family: raleway;
}

.locate-add {
	font-size: 17px;
	color: #084a79;
}

.list-none {
	list-style: none;
	margin: 10px 0;
}

.contact-locate {
	padding-bottom: 10px;
	font-size: 40px;
	font-family: raleway;
	padding-left: 30px;
}

.submit-btn {
	color: #fff;
	background-color: #337ab7;
	border-color: #2e6da4;
	padding: 10px 20px 30px 20px;;
	border-radius: 2px;
	margin-top: 25px;
	margin-bottom: 30px;
}

.map-wrapper {
	margin: 160px 30px 30px 30px;
	overflow: hidden;
}

.img-size-one {
	width: 200px;
	margin-top: -40px !important;
	margin: 0 -25px;
}


.contact-row-section {
	margin-top: 100px;
}
.contact-section{
	margin: 0 30px 0 30px;
}
.header-data {
	border-bottom: 2px solid lavender;
}
.main-nav li a{
 text-decoration:none;
}

.container {
    width: 100%;
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
	<div class="container">
		<div class="map-wrapper">
			<iframe
				src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3824.6797901843374!2d81.52284891423984!3d16.542255688590203!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3a37d2af0a25c4ab%3A0x931a18f48118df6!2sBytesedge!5e0!3m2!1sen!2sin!4v1548782237395"
				width="100%" height="250" frameborder="0" style="border: 0"
				allowfullscreen></iframe>
		</div>
	</div>
	<div class="contact-section">
		<div class="container">
			<div class="row">
				<div class="col-md-6">
					<h2 class="contact-us">Contact Us</h2>
					<div class="row contact-row-section">
						<div class="col-md-6">
							<input type="email" class="form-control first-name" id="email"
								placeholder="Name*" name="email"> <input type="email"
								class="form-control first-name" id="email" placeholder="Phone"
								name="email">
						</div>
						<div class="col-md-6">
							<input type="email" class="form-control first-name" id="email"
								placeholder="E-Mail*" name="email"> <input type="email"
								class="form-control first-name" id="email"
								placeholder="City/Town" name="email">
						</div>
						<div class="col-md-12" style="padding: 0px">
							<textarea class="form-control text-area-data" rows="5"
								id="comment" placeholder="Message*"></textarea>
						</div>
						<button type="button"
							class="btn btn-primary submit-btn pull-right">Submit</button>
					</div>
				</div>
				<div class="col-md-6">
					<h2 class="contact-locate">We are located in</h2>
					<ul>
						<li class="locate-add list-none"><strong>Shilpakala
								Vedika</strong></li>
						<li class="list-none">Hi Tech City Main Road,</li>
						<li class="locate-add list-none"><strong>Madhapur,</strong></li>
						<li class="list-none">Hyderabad. Telangana, INDIA 500 081</li>
						<li class="locate-add list-none"><strong>Email:
								info@shilpakalacedhika.com</strong></li>
						<li class="list-none">Mobile No: +91 8886652004</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
<div>
	<%@ include file="/WEB-INF/jsp/footer.jsp"%>
</div>
</body>
</html>