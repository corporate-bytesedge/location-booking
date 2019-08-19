<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<!-- Favicons -->
<link rel="shortcut icon" href="/assets/images/icons/favicon.png">


<!--<link rel="stylesheet" type="text/css" href="/assets-minified/widgets/owlcarousel/owlcarousel.css">-->
<script type="text/javascript" src="/assets-minified/widgets/owlcarousel/owlcarousel.js"></script>
<script type="text/javascript" src="/assets-minified/widgets/owlcarousel/owlcarousel-demo.js"></script>
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
<style>
@media screen and (min-width: 767px) {
	#mobileShowdiv {
		background-color: black;
		color: white;
		border-style: none;
	}
}

@media only screen and (max-width:415px) {
	#mobileShowdiv {
		margin-right: -1px;
	}
}

@media only screen and (max-width:393px) {
	#mobileShowdiv {
		margin-right: -5px;
		margin-top: 10px;
	}
}

@media only screen and (max-width:389px) {
	#mobileShowdiv {
		margin-right: -6px;
		margin-top: 10px;
	}
}

@media only screen and (max-width:375px) {
}

@media only screen and (max-width:320px) {
}

#header-left {
	float: left
}

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

.autoHscroll {
	overflow-x: scroll;
}

.pancselect {
	font-size: 25px;
}


.img-size-one {
	width: 200px;
	margin-top: -40px !important;
	margin: 0 -25px;
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

.page-data {
	margin: 0 40px;
}

.wrapper-div {
	margin: 40px 40px 40px 40px !important;
}

.text-centrCls h3 {
	color: initial;
	font-size: 18px;
	font-weight: 550;
	font-size: 25px;
}

.carosaul-img {
	height: 500px;
}

.sample{
margin : 100px 0;

}
.row-mrgn{
  		margin-bottom: 50px;
  	}
  	.hr-line{
  		border: 1px solid orangered;
    width: 25%;
    margin-bottom: 50px;
  	}
  	.heading{
    	margin-top: 50px;
    }
  .carousel-inner > .item > img,
  .carousel-inner > .item > a > img {
    width: 90%;
     margin: auto;
     height: 400px;
  }
  .row-top-mrng{
      margin-top: 50px;
  }



@media screen and (max-width: 1050px){
.img-size-one {
    width: 200px;
    margin-top: -20px !important;
    margin: 0px -25px;
}
}

.card {
	margin-bottom:20px;

}

</style>

<script>
$(document).ready(function(){
  $(".hidden-desktop").click(function(){
    $(".main-nav.nav-alt").toggle();
  });
});
</script>

<script>
$(document).ready(function(){
  $("#myCarousel").carousel();
    
  $(".item1").click(function(){
    $("#myCarousel").carousel(0);
  });
  $(".item2").click(function(){
    $("#myCarousel").carousel(1);
  });
  $(".item3").click(function(){
    $("#myCarousel").carousel(2);
  });
  $(".item4").click(function(){
    $("#myCarousel").carousel(3);
  });
    
  $(".left").click(function(){
    $("#myCarousel").carousel("prev");
  });
  $(".right").click(function(){
    $("#myCarousel").carousel("next");
  });
});
</script>
<div>
	<%@ include file="/WEB-INF/jsp/header.jsp"%>
</div>
<div class="container">
	<h2 class="text-center heading"><b>Shilpakala Vedhika Photo Gallery</b></h2>
		<hr class="hr-line">
		<div class="row row-mrgn row-top-mrng">
			<div id="myCarousel" class="carousel slide">
		    <ol class="carousel-indicators">
		      <li class="item1 active"></li>
		      <li class="item2"></li>
		      <li class="item3"></li>
		    </ol>
    <div class="carousel-inner" role="listbox">
	      <div class="item active">
	        <img src="/img/shilpaka.jpg" alt="slp" width="100%" class="img-responsive">
	      </div>
	      <div class="item">
	        <img src="/img/shilpaka.jpg" alt="slp" width="100%" class="img-responsive">
	      </div>
	      <div class="item">
	        <img src="/img/shilpaka.jpg" alt="slp" width="100%" class="img-responsive">
	      </div>
    </div>
    <a class="left carousel-control" href="#myCarousel" role="button">
      <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#myCarousel" role="button">
      <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
      <span class="sr-only">Next</span>
    </a>

  </div>
</div>

		<div class="row row-mrgn">
			<div class="col-md-4 col-sm-6">
				<div class="card">
					<img src="/img/gallery-1.jpg" class="img-responsive" >
				</div>
			</div>
			<div class="col-md-4 col-sm-6">
				<div class="card">
					<img src="/img/gallery-2.jpg" class="img-responsive" >
				</div>
			</div>
			<div class="col-md-4 col-sm-6">
				<div class="card">
					<img src="/img/gallery-3.jpg" class="img-responsive">
				</div>
			</div>
		</div>	

		<div class="row row-mrgn">
			<div class="col-md-4 col-sm-6">
				<div class="card">
					<img src="/img/gallery-4.jpg" class="img-responsive" >
				</div>
			</div>
			<div class="col-md-4 col-sm-6">
				<div class="card">
					<img src="/img/gallery-5.jpg" class="img-responsive">
				</div>
			</div>
			<div class="col-md-4 col-sm-6">
				<div class="card">
					<img src="/img/gallery-6.jpg" class="img-responsive">
				</div>
			</div>
		</div>	

		<div class="row row-mrgn">
			<div class="col-md-4 col-sm-6">
				<div class="card">
					<img src="/img/gallery-7.jpg" class="img-responsive" >
				</div>
			</div>
			<div class="col-md-4 col-sm-6">
				<div class="card">
					<img src="/img/gallery-8.jpg" class="img-responsive" >
				</div>
			</div>
			<div class="col-md-4 col-sm-6">
				<div class="card">
					<img src="/img/gallery-9.jpg" class="img-responsive" >
				</div>
			</div>
		</div>	
		
		<div class="row row-mrgn">
			<div class="col-md-4 col-sm-6">
				<div class="card">
					<img src="/img/gallery-10.jpg" class="img-responsive" >
				</div>
			</div>
			<div class="col-md-4 col-sm-6">
				<div class="card">
					<img src="/img/gallery-11.jpg" class="img-responsive" >
				</div>
			</div>
			<div class="col-md-4 col-sm-6">
				<div class="card">
					<img src="/img/gallery-12.jpg" class="img-responsive" >
				</div>
			</div>
		</div>	
		
		<div class="row row-mrgn">
			<div class="col-md-4 col-sm-6">
				<div class="card">
					<img src="/img/gallery-13.jpg" class="img-responsive" >
				</div>
			</div>
			<div class="col-md-4 col-sm-6">
				<div class="card">
					<img src="/img/gallery-14.jpg" class="img-responsive" >
				</div>
			</div>
			<div class="col-md-4 col-sm-6">
				<div class="card">
					<img src="/img/gallery-15.jpg" class="img-responsive" >
				</div>
			</div>
		</div>
		
		<div class="row row-mrgn">
			<div class="col-md-4 col-sm-6">
				<div class="card">
					<img src="/img/gallery-16.jpg" class="img-responsive" >
				</div>
			</div>
			<div class="col-md-4 col-sm-6">
				<div class="card">
					<img src="/img/gallery-17.jpg" class="img-responsive" >
				</div>
			</div>
			<div class="col-md-4 col-sm-6">
				<div class="card">
					<img src="/img/gallery-18.jpg" class="img-responsive" >
				</div>
			</div>
		</div>
		
		<div class="row row-mrgn">
			<div class="col-md-4 col-sm-6">
				<div class="card">
					<img src="/img/gallery-19.jpg" class="img-responsive" >
				</div>
			</div>
			<div class="col-md-4 col-sm-6">
				<div class="card">
					<img src="/img/gallery-20.jpg" class="img-responsive" >
				</div>
			</div>
		</div>
		
</div>
<div>
	<%@ include file="/WEB-INF/jsp/footer.jsp"%>
</div>