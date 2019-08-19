<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>My Panchayat</title>
<style>
body {
	position: relative;
}

#Elected {
	padding-top: 50px;
	height: 500px;
	color: #fff;
	background-color: #1E88E5;
}

#Functional {
	padding-top: 50px;
	height: 500px;
	color: #fff;
	background-color: #673ab7;
}

#Panchayat {
	padding-top: 50px;
	height: 500px;
	color: #fff;
	background-color: #ff9800;
}

#Non-Panchayat {
	padding-top: 50px;
	height: 500px;
	color: #fff;
	background-color: #00bcd4;
}

#Yellow {
	padding-top: 50px;
	height: 500px;
	color: #fff;
	background-color: #009688;
}

#Gallary {
	padding-top: 50px;
	height: 500px;
	color: #fff;
	background-color: #ccc;
}
</style>
</head>
<body data-spy="scroll" data-target=".navbar" data-offset="50">

	<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#myNavbar">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">MY PANCHAYAT</a>
		</div>
		<div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav flright">
					<li><a href="#Elected" title="Elected Wing"><span><spring:message
									code='home-page.submenu.Electedwing' /></span></a></li>
					<li><a href="#Functional" title="Functional Committe"><span><spring:message
									code='home-page.submenu.Functionalcommitte' /></span></a></li>
					<li><a href="#Panchayat" title="Panchayat Staff"><span><spring:message
									code='home-page.submenu.Nonpanchayatstaff' /></span></a></li>
					<li><a href="#Non-Panchayat" title="Non-Panchayat Staff"><span><spring:message
									code='home-page.submenu.Panchayatstaff' /></span></a></li>
					<li><a href="#Yellow" title="Yellow Pages"><span><spring:message
									code='home-page.submenu.Yellowpages' /></span></a></li>
					<li><a href="#Gallary" title="Gallery"><span><spring:message
									code='home-page.submenu.Gallery' /></span></a></li>
				</ul>
			</div>
		</div>
	</div>
	</nav>

	<div id="Elected" class="container-fluid">
		<h1>Elected Wing</h1>
		<p>Try to scroll this section and look at the navigation bar while
			scrolling! Try to scroll this section and look at the navigation bar
			while scrolling!</p>
		<p>Try to scroll this section and look at the navigation bar while
			scrolling! Try to scroll this section and look at the navigation bar
			while scrolling!</p>
	</div>

	<div id="Functional" class="container-fluid">
		<h1>Functional Committe</h1>
		<p>Try to scroll this section and look at the navigation bar while
			scrolling! Try to scroll this section and look at the navigation bar
			while scrolling!</p>
		<p>Try to scroll this section and look at the navigation bar while
			scrolling! Try to scroll this section and look at the navigation bar
			while scrolling!</p>
	</div>

	<div id="Panchayat" class="container-fluid">
		<h1>Panchayat Staff</h1>
		<p>Try to scroll this section and look at the navigation bar while
			scrolling! Try to scroll this section and look at the navigation bar
			while scrolling!</p>
		<p>Try to scroll this section and look at the navigation bar while
			scrolling! Try to scroll this section and look at the navigation bar
			while scrolling!</p>
	</div>

	<div id="Non-Panchayat" class="container-fluid">
		<h1>Non-Panchayat Staff</h1>
		<p>Try to scroll this section and look at the navigation bar while
			scrolling! Try to scroll this section and look at the navigation bar
			while scrolling!</p>
		<p>Try to scroll this section and look at the navigation bar while
			scrolling! Try to scroll this section and look at the navigation bar
			while scrolling!</p>
	</div>
	<div id="Yellow" class="container-fluid">
		<h1>Yellow Pages</h1>
		<p>Try to scroll this section and look at the navigation bar while
			scrolling! Try to scroll this section and look at the navigation bar
			while scrolling!</p>
		<p>Try to scroll this section and look at the navigation bar while
			scrolling! Try to scroll this section and look at the navigation bar
			while scrolling!</p>
	</div>
	<div id="Gallary" class="container-fluid">
		<h1>Gallary</h1>
		<p>Try to scroll this section and look at the navigation bar while
			scrolling! Try to scroll this section and look at the navigation bar
			while scrolling!</p>
		<p>Try to scroll this section and look at the navigation bar while
			scrolling! Try to scroll this section and look at the navigation bar
			while scrolling!</p>
	</div>

</body>
</body>
</html>