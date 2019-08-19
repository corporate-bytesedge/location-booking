<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp"%>
<html lang="en">
<head>
<link rel="shortcut icon" href="/favicon.png" type="image/x-icon" />

<meta charset="UTF-8">
<title>Booking Venue</title>
<meta name="description" content="">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link rel="shortcut icon" href="/assets/images/icons/favicon.png">	

<!-- JS Core -->
<script type="text/javascript" src="/assets-minified/js-core.js"></script>


<script type="text/javascript">
	$(window).load(function() {
		setTimeout(function() {
			$('#loading').fadeOut(400, "linear");
		}, 300);
	});
	
	 function refreshCaptcha(divId) {
		document.getElementById(divId).src = '/apu/captcha.jpg?' + new Date();
	};
</script>
<style type="text/css">
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
</style>


<link rel="stylesheet" type="text/css"
	href="/assets-minified/main.css">

</head>
<body onload="document.getElementById('username').focus();">
	<div id="loading">
		<img src="/assets/images/spinner/loader-dark.gif" alt="Loading...">
	</div>

	<style type="text/css">
body {
	overflow: hidden;
	height: 100% !important;
}
</style>
	<div class="center-vertical">
		<div class="center-content">

			<form id="loginForm" class="col-md-4 center-margin" action="/apu/login" method='POST' >
				<input type="hidden"
					name="<c:out value="${_csrf.parameterName}"/>"
					value="<c:out value="${_csrf.token}"/>" />
				<div id="login-form" class="content-box modal-content">
					<div class="content-box-wrapper pad20A">
						<div class="form-group">
							<label for="exampleInputEmail1"><spring:message code="login-form.username"/>:</label>
							<div class="input-group input-group-lg">
								<span
									class="input-group-addon addon-inside bg-white font-primary">
									<i class="glyph-icon icon-envelope-o"></i>
								</span> <input type="email" class="form-control"
									id="username" name="username" placeholder="Email ID">
							</div>
						</div>
						<div class="form-group">
							<label for="exampleInputPassword1"><spring:message code="login-form.Password"/>:</label>
							<div class="input-group input-group-lg">
								<span
									class="input-group-addon addon-inside bg-white font-primary">
									<i class="glyph-icon icon-unlock-alt"></i>
								</span> <input type="password" class="form-control"
									id="password" name="password" placeholder="Password">
							</div>
						</div>
						
						<div class="form-group">
							<label for="exampleInputEmail1"><spring:message code="login-form.captcha"/>:</label>
							<div class="input-group input-group-lg">
								<img id="newCaptha" alt="Captcha" src="/apu/captcha.jpg" width="120" height="48"/>
								<span title="Refresh and try another" onClick="javascript:refreshCaptcha('newCaptha');"
									onload="javascript:refreshCaptcha('newCaptha');">
									<i class="glyph-icon icon-refresh" style="cursor: pointer;"></i>
								</span>
								<input type="text" class="form-control"
									id="captchaText" name="captchaText" placeholder="Captcha">
							</div>
						</div>
						
						<div class="row">
							<div class="checkbox-primary col-md-6" style="height: 20px;">
								<label> <input type="hidden" id="loginCheckbox1"
									class="custom-checkbox">
								</label>
							</div>
							<div class="text-right col-md-6">
								<a href="#" class="switch-button" switch-target="#login-forgot"
									switch-parent="#login-form" title="Recover password"><spring:message code="login-form.forgot-password"/></a>
							</div>
						</div>
					</div>
					<div class="button-pane">
						<button type="submit" class="btn btn-block btn-primary"><spring:message code="login-form.submit-button"/></button>
					</div>
				</div>

				<div id="login-forgot" class="content-box modal-content hide">
					<div class="content-box-wrapper pad20A">

						<div class="form-group">
							<label for="exampleInputEmail1">Email address:</label>
							<div class="input-group">
								<span class="input-group-addon addon-inside bg-gray"> <i
									class="glyph-icon icon-envelope-o"></i>
								</span> <input type="email" class="form-control"
									id="exampleInputEmail1" placeholder="Enter email">
							</div>
						</div>
					</div>
					<div class="button-pane text-center">
						<button type="submit" class="btn btn-md btn-primary">Recover
							Password</button>
						<a href="#" class="btn btn-md btn-link switch-button"
							switch-target="#login-form" switch-parent="#login-forgot"
							title="Cancel">Cancel</a>
					</div>
				</div>

			</form>

		</div>
	</div>
</body>
</html>
