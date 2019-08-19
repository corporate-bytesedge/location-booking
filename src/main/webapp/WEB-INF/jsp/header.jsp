<style>
		.business-bar-text {
	line-height: 20px;
	padding: 10px 0 10px 30px;
	color: white;
}

.main-header {
	
}

#page-content-holder {
	margin-top: -250px;
}

.header-color {
	background-color: white;
	position: fixed;
	width: 100%;
	z-index: 9;
}
.logo-slp {
	width: 200px;
	height: 57px;
	margin-bottom: -8px;
}
.hidden-desktop {
	color: black !important;
    margin-top: -30px;
    font-size: 30px !important;
	}
	.loginbtn {
	padding: 0px 20px 7px 20px;
}
	
	
	
@media screen and (max-width: 1050px){
	.main-nav.nav-alt{
	    background: lightgray !important;
	}
}

@media (max-width: 375px){
.hidden-desktop {
    margin-top: -5px !important;
    margin-bottom: -30px;
}
}
.main-header-fix {
	position: fixed;
    z-index: 999;
    width: 100%;
    top: 0px;
}
</style>
	
<div class="main-header-fix">
	<div class="main-header header-fixed " data-0="padding: 12px 0;"
		data-250="padding: 8px 0;">
		<div class="container clearfix header">
			<a href="#" class="hidden-desktop" id="responsive-menu" title="">
				<i class="glyph-icon icon-align-justify"></i>
			</a> <a href="#" class="logo " title="Shilpakala vedika"><img
				src="/img/logo.png" class="logo-slp" alt="slp" /></a>
			<ul class="main-nav nav-alt">
				<li><a href="/apu/home" title="Home"> Home </a>
				<li><a href="/apu/gallery" title="Gallery"> Gallery </a>
				</li>
				<li><a href="/apu/homeBooking/bookVenue" title="Booking Guidelines"> Booking Guidelines</a>
				</li>
				<li><a href="/apu/contact/contactUs" title="Contact Us">
						Contact Us </a>
				<li><a href="/apu/contact/downloads" title="Downloads">
						Downloads </a>
				<li><a target="_blank" href="/apu/registrationPage">
						<button type="button" class="btn btn-success loginbtn">
							<spring:message code='home-page.topmenu.Login' />
						</button>
				</a></li>
			</ul>
		</div>
	</div>
</div>