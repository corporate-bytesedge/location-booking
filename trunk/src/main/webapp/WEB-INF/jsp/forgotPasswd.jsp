<html lang="en">
<head>
<meta charset="UTF-8">
<!--[if gte IE 9]><meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1'><![endif]-->
<!--[if IE 8]><meta http-equiv="X-UA-Compatible" content="IE=8"><![endif]-->
<title>Shilparamam</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"><!-- Favicons -->
<link rel="shortcut icon" href="/assets/images/icons/favicon.png">
<!-- CSS -->
<link rel="stylesheet" type="text/css" href="/assets-minified/main.css">
<link rel="stylesheet" type="text/css" href="/css/common.css">
<link rel="stylesheet" type="text/css"
	href="/css/responsiveDashboard.css">
<link rel="stylesheet" type="text/css"
	href="/assets-minified/widgets/modal/modal.css">
<link rel="stylesheet" type="text/css"
	href="/css/jquery-confirm.min.css">

<!-- JS Core -->

<script type="text/javascript" src="/assets-minified/js-core/jquery-core.js"></script>
<script type="text/javascript" src="/assets-minified/js-core.js"></script>
<script type="text/javascript" src="/assets-minified/all-demo.js"></script>
<script type="text/javascript" src="/js/js-common.js"></script>
<script type="text/javascript" src="/assets-minified/widgets/modal/modal.js"></script>
<script type="text/javascript" src="/js/jquery-confirm.min.js"></script>
<script type="text/javascript" src="/js/Chart.min.js"></script>
<script type="text/javascript" src="/js/loadingoverlay.min-7938e101f7288eafea5a9c9f0cb373a8835c480484ce2938ef9134304c62b119.js"></script>
	<link rel="stylesheet" type="text/css" href="/assets-minified/widgets/datepicker-ui/datepicker.css">
<script type="text/javascript" src="/assets-minified/widgets/datepicker-ui/datepicker.js"></script>

<script type="text/javascript">
	$(window).load(function() {
		setTimeout(function() {
			$('#loading').fadeOut(400, "linear");
		}, 300);
	});

	$('.count').each(function() {
		$(this).prop('Counter', 0).animate({
			Counter : $(this).text()
		}, {
			duration : 4000,
			easing : 'swing',
			step : function(now) {
				$(this).text(Math.ceil(now));
			}
		});
	});
</script>
<style>
#loading {
	position: fixed;
	width: 100%;
	height: 100%;
	left: 0;
	top: 0;
	right: 0;
	bottom: 0;
	display: block;
	background: #fff;
	z-index: 10000;
}

#loading img {
	position: absolute;
	top: 50%;
	left: 50%;
	margin: -23px 0 0 -23px;
}

.venue-section {
	padding: 70px 0 40px 0!importanr;
}

.contact-li li a {
	line-height: 12px;
}

.logo-slp {
	width: 200px;
	height: 57px;
	margin-bottom: -8px;
}

.about-section {
	padding: 50px 0 50px 0;
	box-shadow: 4px 2px lightgrey;
}

.about-section h2 {
	text-align: center;
	padding-bottom: 40px;
	letter-spacing: 5px;
}

.abt-img {
	border-radius: 3px;
}

.small-padding-one {
	text-align: center;
}

.stats-section {
	padding: 40px 0 80px 0;
	text-align: center;
	background-image: url("/img/dot-bg.jpg");
}

.center-img {
	display: block;
	margin-left: auto;
	margin-right: auto;
	width: 50%;
}

.img-size {
	float: left;
	list-style: none;
	position: relative;
	width: 200px;
	margin-right: 30px;
}

.h3-text h3 {
	font-size: 30px;
	font-weight: 300;
	line-height: 48px;
}

.about-over {
	display: flex;
	justify-content: space-between;
	align-items: center;
	font-size: 16px;
	text-align: justify;
}

.venue-box {
	box-shadow: 1px 0px 34px 1px rgba(0, 0, 0, 0.13), 0px 15px 34px 1px
		rgba(0, 0, 0, 0.13)
}

.gallary-section h2 {
	text-align: center;
	padding-bottom: 40px;
	letter-spacing: 5px;
}

.venue-box img {
	border: 0px !important;
}

.venue-box img:hover {
	box-shadow: 0px !important;
}

.business-bar-text i {
	padding-left: 30px;
}

.business-bar {
	background-color: #033b4a;
}

.business-bar-btn {
	margin-top: 3px;
}

.business-bar-btn {
	padding-left: 130px !important;
}

.btn-font {
	font-size: 16px !important;
}

.business-bar-text {
	line-height: 20px;
	padding: 10px 0 10px 30px;
	color: white;
}

.main-header {
	
}

.header-color {
	background-color: white;
	position: fixed;
	width: 100%;
	z-index: 9;
}

.header-data {
	border-bottom: 2px solid lavender;
}

.footer-desc {
	padding-left: 30px;
}

.img-size-one {
	width: 200px;
	margin-top: -20px !important;
	margin: 0 -25px;
}

.footer-desc p {
	padding-top: 5px !important;
	text-align: justify;
	text-justify: inter-word;
	margin-left: -15px !important;
	color: rgba(255, 255, 255, .4);
}

.loginbtn {
	padding: 0px 20px 7px 20px;
}

.know-more {
	margin: -25px 145px 0 0px;
}

.margn-btm {
	margin-bottom:100px;
}

@media screen and (min-width: 1050px){
	.main-header .container {
   	 height: 70px !important;
	}
}


</style>

<link rel="stylesheet" type="text/css" href="/assets-minified/main.css">
<link rel="stylesheet" type="text/css"
	href="/assets-minified/themes/bratilius/default/framework-color.css">
<link rel="stylesheet" type="text/css"
	href="/assets-minified/themes/bratilius/border-radius.css">
<link rel="stylesheet" type="text/css"
	href="/assets-minified/themes/bratilius/frontend/color.css">
<link rel="stylesheet" type="text/css"
	href="/assets-minified/helpers/colors.css">

</head>
<body>
<div class="margn-btm">
	<div class="main-header-fix">
	<div class="featured-area featured-box header-data"
		data-0="background-position: 50% 0px;"
		data-350="background-position: 50% -200px;">
		<div class="main-header header-fixed " data-0="padding: 12px 0;"
			data-250="padding: 8px 0;">
			<div class="container clearfix header">
				<a href="#" class="hidden-desktop" id="responsive-menu" title="">
					<i class="glyph-icon icon-align-justify"></i>
				</a> <a href="#" class="logo " title="Shilpakala vedika"><img
					src="/img/logo.png" class="logo-slp" alt="slp" /></a>
				<ul class="main-nav nav-alt">
					<li><a href="/apu/home" title="Home"> Home </a>
					<li><a href="#" title="Gallery"> Gallery <i
							class="glyph-icon icon-chevron-down"></i>
					</a>
						<ul>
							<li><a href="#" title="Gallery"><span>Gallery</span></a></li>
							<li><a href="#" title="Videos"><span>Videos</span></a></li>
						</ul></li>
					<li><a href="/apu/homeBooking/bookVenue" title="Booking Venue"> Booking Venue
					</a>
						<ul>
							<li><a href="/apu/homeBooking/bookVenue" title="Hero area 1"><span>
										Shilpakala vedika </span></a></li>
						</ul></li>
					<li><a href="/apu/contact/contactUs" title="Contact Us"> Contact Us </a>
					<li><a href="/apu/contact/downloads" title="Downloads"> Downloads </a>
				</ul>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
    /* Datepicker bootstrap */
    function initDate() {
    	var array = $('#blockDates').val().split(', ');
    	$('.bootstrap-datepicker').datepicker({
    		minDate : new Date(),
    		maxDate: "+3M+14d",
    		dateFormat: "dd-mm-yy",
    		altFormat : "dd-mm-yyyy",
	    	 beforeShowDay: function(date){
    	        var string = jQuery.datepicker.formatDate('dd-mm-yy', date);
    	        return [ array.indexOf(string) == -1 ]
    	    }
    	}).bind("change",function(){
            var minValue = $(this).val();
    	})
    }
</script>

</div>
<div id="page-content-holder">
	<script type="text/javascript">
		getUrl('/apu/forgotPasswd/create/form', 'page-content-holder');
	</script>
</div>

<div class="main-footer inverse clearfix bg-black">
		<div class="container clearfix">
			<div class="col-md-5">
				<div class="footer-desc">
					<a class="logo" href="#" title=""
						style="text-transform: uppercase; color: white"><img src="/img/logo.png" alt="logo" class="img-size-one"></a>
						<br><br>
					<p class="wrapper-p">Keep
						up on for our Services always Shilparamam Cultural programs and
						Shoping Festivals. Enter your e-mail and subscribe to our
						newsletter.</p>
				</div>
			</div>
			<div class="col-md-2">
				<h3 class="footer-header">Quick Links</h3>
				<ul>
					<li><a href="#" title="">Downloads</a></li>
					<li><a href="#" title="">Route Map</a></li>
					<li><a href="#" title="">Guidelines</a></li>
				</ul>
			</div>
			<div class="col-md-2">
				<h3 class="footer-header">Useful Links</h3>
				<ul>
					<li><a href="#" title="">Contact Us</a></li>
					<li><a href="#" title="">Party Venue</a></li>
					<li><a href="#" title="">Gallery</a></li>
					<li><a href="#" title="">Videos</a></li>
				</ul>
			</div>
			<div class="col-md-3">
				<h3 class="footer-header">Contact Us</h3>
				<ul class="contact-li">
					<li><a href="#" title="">Special Officer - Shilparamam </a></li>
					<li><a href="#" title="">Hi Tech City Main Road, Madhapur,</a>
					</li>
					<li><a href="#" title="">Hyderabad. Telangana, INDIA 500
							081</a></li>
					<li><a href="#" title="">Phone No: +91(40)64518164</a></li>
					<li><a href="#" title="">Mobile No: +91 8886652004</a></li>
					<li><a href="#" title="">Email: info@shilpakalacedhika.com</a>
					</li>
				</ul>
			</div>

		</div>
		<div class="footer-pane">
			<div class="container clearfix">
				<div class="logo logo-one" style="color: rgba(255, 255, 255, .4);">&copy;
					Copyright 2019. All Rights Reserved. Shilpakalavedhika</div>

				<ul class="footer-nav">
					<li><a href="#" title="Portfolio">SiteMap</a></li>
					<li><a href="#" title="Layout">Privacy Policy</a></li>
					<li><a href="#" title="Elements">ContactUs</a></li>
				</ul>

			</div>
		</div>
	</div>
	<script type="text/javascript" src="/assets-minified/all-demo.js"></script>

</body>	