<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Shilparamam </title>
<meta name="description" content="">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link rel="shortcut icon" href="/favicon.png" type="image/x-icon" />
<link rel="shortcut icon" href="/assets/images/icons/favicon.png">
<link rel="stylesheet" type="text/css" href="/assets-minified/main.css">
<script type="text/javascript" src="/assets-minified/js-core.js"></script>
<script type="text/javascript"
	src="/assets-minified/widgets/parsley/parsley.js"></script>

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

.frame {
	overflow: hidden;
}

.zoomin img {
	height: 351px;
    width: auto;
    border-radius: 5px;
    margin-top: 10px;
	-webkit-transition: all 2s ease;
	-moz-transition: all 2s ease;
	-ms-transition: all 2s ease;
	transition: all 2s ease;
}

/* .zoomin img:hover {
	width: 320px;
}
 */
body {
	overflow: hidden;
	height: 100% !important;
}

@media only screen and (min-width: 991px) {
	.book-btn {
		margin-top: -35px;
	}
}

@media only screen and (min-width: 600px) {
	.box-marr {
		margin: 0px !important;
	}
}
</style>
</head>
<body onload="document.getElementById('username').focus();">
	<div id="loading">
		<img src="/assets/images/spinner/loader-dark.gif" alt="Loading...">
	</div>

	<div class="center-vertical content-Center">
		<div class="center-content">
			<form id="loginForm" class="col-md-6 col-xs-12 center-margin"
				action="/apu/login" method='POST' data-parsley-validate="">
				<h3 class="text-center pad5B">
					<img src="/img/logo.png" alt="BE" height="110px"
						width="232px"
						style="background-color: #ffffff; margin-bottom: -40px; margin-top: 10px; cursor: pointer;"
						onclick="window.open('/apu/home')">
				</h3><br><br>
				<div class="content-box border  border-blue-alt clearfix">
					<div class="state-map col-md-6" style="margin-top: 10px;">
						<div style="clear: both;">
							<div style="width: 100%; text-align: center;"></div>
							<input type="hidden"
								name="<c:out value="${_csrf.parameterName}"/>"
								value="<c:out value="${_csrf.token}"/>" />
							<div class="zoomin frame">
								<img src="/img/gallery-18.jpg" id="srmImage"
									class="img-responsive" name="srmImage" width="300px;"
									height="300px" />
							</div>
						</div>
					</div>
					<div class="col-md-6 login-options">
						<div class="">
							<div id="login-form">
								<div class="pad20A">
									<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION && 
										fn:indexOf(SPRING_SECURITY_LAST_EXCEPTION, 'GET') == -1}">
										<ul class="parsley-errors-list filled" id="ln_error">
											<li class="parsley-required"><c:out
													value="${SPRING_SECURITY_LAST_EXCEPTION.message}" /></li>
										</ul>
									</c:if>
									<div class="form-group">
										<div class="input-group input-group-lg">
											<span
												class="input-group-addon addon-inside bg-white font-primary">
												<i class="glyph-icon  icon-envelope-o"></i>
											</span> <input type="email" class="form-control" id="username"
												name="username"
												placeholder="<spring:message code='login-page.username'/>"
												required>
										</div>
									</div>
									<div class="form-group">
										<div class="input-group input-group-lg">
											<span
												class="input-group-addon addon-inside bg-white font-primary">
												<i class="glyph-icon icon-unlock-alt"></i>
											</span> <input type="password" class="form-control" id="password"
												name="password"
												placeholder="<spring:message code='login-page.password'/>"
												required>
										</div>
									</div>
									<div class="form-group">
										<div style="float: left" class="captcha-text">
											<div class="input-group input-group-lg" style="margin-left: -7px;">
												<img id="newCaptha" alt="Captcha" src="/apu/captcha.jpg"
													width="120" height="48" /> <span
													title="Refresh and try another"
													onClick="javascript:refreshCaptcha('newCaptha');"
													onload="javascript:refreshCaptcha('newCaptha');"> <i
													class="glyph-icon icon-refresh"
													style="cursor: pointer; padding-bottom: 10px;float: right;"></i>
												</span>
											</div>
										</div>
										<div style="float: right; width: 136px; margin-left: 6px; margin-bottom: 10px;"
											class="captchroght">
											<div class="input-group input-group-lg" style="margin-top: 10px;">
												<span
													class="input-group-addon addon-inside bg-white font-primary">
													<i class="glyph-icon icon-linecons-key"></i>
												</span> <input type="text" class="form-control" id="captchaText"
													name="captchaText"
													placeholder="<spring:message code='login-page-captcha'/>"
													required>
											</div>
										</div>
									</div>									<div class="row">

										<div
											style="float: right; padding-bottom: 15px; padding-right: 12px;">
											<div class="text-right">
												<a href="/apu/forgotPasswd" title="Recover password"><spring:message
														code="login-page.fogotpassword" /></a>
											</div>
										</div>
									</div>
									<div class="form-group">
										<button type="submit" class="btn btn-block btn-blue-alt">
											<spring:message code='login-page.submit' />
										</button>
									</div>
								</div>
							</div>

						</div>
					</div>
				</div>

			</form>
		</div>
	</div>

</body>
</html>
