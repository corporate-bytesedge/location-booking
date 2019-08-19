<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Shilparamam</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<script type="text/javascript" src="/assets-minified/js-core.js"></script>
<script type="text/javascript" src="/js/js-common.js"></script>

<link rel="stylesheet" type="text/css" href="/assets-minified/main.css">
<link rel="stylesheet" type="text/css" href="/assets-minified/themes/bratilius/default/framework-color.css">
<link rel="stylesheet" type="text/css" href="/assets-minified/themes/bratilius/border-radius.css">
<link rel="stylesheet" type="text/css" href="/assets-minified/themes/bratilius/frontend/color.css">
<link rel="stylesheet" type="text/css" href="/assets-minified/helpers/colors.css">
<link rel="shortcut icon" href="/assets/images/icons/favicon.png">
<link rel="stylesheet" type="text/css" href="/assets-minified/frontend-elements/frontend-elements-all.css">
<link rel="stylesheet" type="text/css" href="/assets-minified/frontend-elements/frontend-responsive.css">
<link rel="stylesheet" type="text/css" href="/assets-minified/widgets/owlcarousel/owlcarousel.css">

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
.img-size-one {
    width: 200px;
    margin-top: -40px !important;
    margin: 0 -30px;
}

.h3-text h3 {
	font-size: 30px;
	font-weight: 300;
	line-height: 48px;
}

.about-over {
	justify-content: space-between;
	align-items: center;
	font-size: 16px;
	text-align: justify;
}

.venue-box {
	box-shadow: 1px 0px 34px 1px rgba(0, 0, 0, 0.13), 0px 15px 34px 1px
		rgba(0, 0, 0, 0.13);
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


#createEndUserDiv {
	margin-top: 100px !important;
}

.header-data {
	border-bottom: 2px solid lavender;
}



.know-more {
	margin: -25px 145px 0 0px;
}
.book-btn a{
	float:right;
	margin:0px !important;
}
.box-marr {
	margin:0 100px 0 100px;
}
.gallary-marr {
	margin:0 100px 0 100px;
}
.photo-line {
	text-align: center;
    display: block;
	}


</style>

<script>
$(document).ready(function(){
  $(".hidden-desktop").click(function(){
    $(".main-nav.nav-alt").toggle();
  });
});
</script>

</head>
<body>
<div>
	<%@ include file="/WEB-INF/jsp/header.jsp"%>
</div>
<div id="page-content-container">
		<div id="homeSpacingDiv" style="margin-left: 0px; margin-right: 0px;"></div>
		<div id="homeCarouselDiv">
			<div class="">
				<div>
					<img class="img-full" src="/img/ShilpaCover.jpg">
				</div>
			</div>
		</div>

		<div class="venue-section">
			<h2>Shilparamam</h2>
			<div class="row" style="padding-bottom: 40px;">
				<div class="col-md-6 col-sm-12 col-xs-12">
					<div class="thumbnail venue-box box-marr">
						<img src="/img/hall-one.jpg" alt="hall">
						<div class="caption">
							<h3 style="padding-left: 10px">Shilpakala Vedika</h3>
							<p style="text-align: justify; padding: 15px 10px 16px 10px;">Shilpakala
								Vedika is a terracotta auditorium and convention centre located
								in Hyderabad, Telangana. The auditorium is 60,000 sq ft (5,600
								m2). Shilpa means sculpture, kala means art, and vedika means
								platform. Hence it is an art sculpture platform. The convention
								center includes an auditorium, which is flexible in design and
								offers a variety of configuration selections, and is widely
								known for Telugu film audio release functions.</p>
							<div class="main-btn-div" style="padding: 20px 10px 45px 10px;">
								<div class="col-md-6">
									<a href="/apu/homeBooking/bookVenue" class="btn btn-success Know now" role="button">T&C
									</a>
								</div>
								<div class="col-md-6 book-btn">
									<a href="/apu/registrationPage" class="btn btn-success Book now" role="button" style="margin: 0px !important;">Book
										Now</a>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="thumbnail venue-box box-marr">
						<img src="/img/hall-two.jpg" alt="hall">
						<div class="caption">
							<h3 style="padding-left: 10px">Dining Hall</h3>
							<p style="text-align: justify; padding: 15px 10px 16px 10px;">Shilpakala
								Vedika is a terracotta auditorium and convention centre located
								in Hyderabad, Telangana. The auditorium is 60,000 sq ft (5,600
								m2). Shilpa means sculpture, kala means art, and vedika means
								platform. Hence it is an art sculpture platform. The convention
								center includes an auditorium, which is flexible in design and
								offers a variety of configuration selections, and is widely
								known for Telugu film audio release functions.</p>
							<div class="main-btn-div" style="padding: 20px 10px 45px 10px;">
								<div class="col-md-6">
									<a href="/apu/homeBooking/bookVenue" class="btn btn-success Know now" role="button">T&C
									</a>
								</div>
								<div class="col-md-6 book-btn">
									<a href="/apu/registrationPage" class="btn btn-success Book now" role="button" style="margin: 0px !important;">Book
										Now</a>
								</div>
							</div>
							</div>
					</div>
				</div>
			</div>
		</div>
		<div class="gallary-section">
			<h2>Gallery</h2>
			<div class="row col-sm-12">
				<div class="col-md-6" style="padding-bottom: 40px;">
					<div class="thumbnail gallary-marr">
						<i class="fa fa-image photoImg"></i> <a href="/apu/gallery"
							class="photo-line">Photo Gallery</a>
					</div>
				</div>

				<div class=" col-md-6">
					<div class="thumbnail gallary-marr">
						<i class="fa fa-youtube photoImg"></i> <a href="/apu/gallery"
							class="photo-line">Video Gallery</a>
					</div>
				</div>
			</div>
		</div>

		<div class="about-section">
			<h2>Overview</h2>
			<div class="container">
				<div class="row about-over">
					<div class="col-md-6">
						<img src="/img/about.jpg" alt="hall"
							class="img-responsive abt-img">
					</div>
					<div class="col-md-6">
						<p style="margin: 100px 20px 0px 20px;">
							Keep up on for our Services always Shilparamam Cultural programs
							and Shoping Festivals. Enter your e-mail and subscribe to our
							newsletter. Keep up on for our Services always Shilparamam
							Cultural programs and Shoping Festivals. Enter your e-mail and
							subscribe to our newsletter. Keep up on for our Services always
							Shilparamam Cultural programs and Shoping Festivals. Enter your
							e-mail and subscribe to our newsletter. Keep up on for our
							Services always Shilparamam Cultural programs and Shoping
							Festivals. Enter your e-mail and subscribe to our newsletter.<br>
						</p>
					</div>
				</div>
			</div>
		</div>

		<div class="stats-section">
			<div class="row">
				<div class="col-md-4 h3-text">
					<h3>
						1000 + </h3> <br><h4> <span style="color: #5cb847 !important;">EVENTS</span></h4>
					
				</div>

				<div class="col-md-4 h3-text">
					<h3>
						2000 + </h3><span>Capacity(small size)</span><br><h4><span style="color: #5cb847 !important;">SHILPAKALA VEDIKA</span>
					</h4>
				</div>

				<div class="col-md-4 h3-text">
					<h3>
						500 + </h3><span>Capacity(small size)</span><br> <h4><span style="color: #5cb847 !important;">DINING
							HALL</span>
					</h4>
				</div>
			</div>
		</div>
<div>
	<%@ include file="/WEB-INF/jsp/footer.jsp"%>
</div>
	</div>
<script type="text/javascript" src="/assets-minified/all-demo.js"></script>
<script type="text/javascript" src="/assets-minified/widgets/skycons/skycons.js"></script>
<script type="text/javascript" src="/assets-minified/widgets/charts/piegage/piegage.js"></script>
<script type="text/javascript" src="/assets-minified/widgets/charts/piegage/piegage-demo.js"></script>
<script type="text/javascript" src="/assets-minified/widgets/charts/sparklines/sparklines.js"></script>
<script type="text/javascript" src="/assets-minified/widgets/charts/sparklines/sparklines-demo.js"></script>
<script type="text/javascript" src="/assets-minified/widgets/owlcarousel/owlcarousel.js"></script>
<script type="text/javascript" src="/assets-minified/widgets/owlcarousel/owlcarousel-demo.js"></script>
<script type="text/javascript" src="/assets-minified/widgets/skrollr/skrollr.js"></script>
<script type="text/javascript" src="/assets-minified/widgets/sticky/sticky.js"></script>

</body>
</html>