<!DOCTYPE html>
<%@ include file="../taglibs.jsp"%>
<html lang="en">
<head>
<title>Shilparamam - ${data.ctxName} - Shilparamam</title>

<meta charset="UTF-8">
<!--[if gte IE 9]><meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1'><![endif]-->
<!--[if IE 8]><meta http-equiv="X-UA-Compatible" content="IE=8"><![endif]-->
<meta name="description" content="Booking Venue Information System">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

<!-- Favicons -->
<link rel="shortcut icon" href="/assets/images/icons/favicon.png">

<!-- CSS -->
<link rel="stylesheet" type="text/css" href="/assets-minified/main.css">
<link rel="stylesheet" type="text/css" href="/css/common.css">
<link rel="stylesheet" type="text/css" href="/css/responsiveDashboard.css">
<link rel="stylesheet" type="text/css" href="/assets-minified/widgets/modal/modal.css">
<link rel="stylesheet" type="text/css" href="/css/jquery-confirm.min.css">

<!-- JS Core -->
<script type="text/javascript" src="/assets-minified/js-core/jquery-core.js"></script>
<script type="text/javascript" src="/assets-minified/js-core.js"></script>
<script type="text/javascript" src="/assets-minified/all-demo.js"></script>
<script type="text/javascript" src="/js/jquery.form.min.js"></script>
<script type="text/javascript" src="/js/js-common.js"></script>
<script type="text/javascript"
	src="/assets-minified/widgets/modal/modal.js"></script>
<script type="text/javascript" src="/js/jquery-confirm.min.js"></script>
<script type="text/javascript" src="/js/Chart.min.js"></script>
<script type="text/javascript" src="/assets-minified/widgets/datepicker-ui/datepicker.js"></script>
<script type="text/javascript"
	src="/js/loadingoverlay.min-7938e101f7288eafea5a9c9f0cb373a8835c480484ce2938ef9134304c62b119.js"></script>
<script src="https://raw.githubusercontent.com/jquery-form/form/master/src/jquery.form.js"></script> 
<script type="text/javascript">
	$(window).load(function() {
		setTimeout(function() {
			$('#loading').fadeOut(400, "linear");
		}, 300);
	});
</script>
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


.pancselect {
	font-size: 25px;
}
</style>
</head>
<body>
	<div id="loading">
		<img src="/assets/images/spinner/loader-dark.gif" alt="Loading...">
	</div>

	<div id="sb-site">
		<div id="page-wrapper">
			<div id="page-header" class="clearfix">
				<div id="header-logo" class="rm-transition">
					<a href="#" class="tooltip-button hidden-desktop"
						title="Navigation Menu" id="responsive-open-menu"> <i
						class="glyph-icon icon-align-justify"></i>
					</a> <a id="collapse-sidebar" href="#" title=""> <i
						class="glyph-icon icon-chevron-left"></i>
					</a>
				</div>
				<!-- #header-logo -->

				<div id="header-logo-label">
					<div class="dropdown textDisplay"></div>
					<div class="dropdown textDisplay">
						<span><font size=5 class="fontSize" color="#ffffff">&nbsp;SHILPARAMAM</font></span>
					</div>
				</div>
				<div id="header-right">
					<div class="user-profile">
						<a href="#" title="" class="clearfix" data-toggle="dropdown">
							<span> <c:if
									test="${pageContext.request.userPrincipal.name != null}">
									<span><font="size:3; color:#fffff;">${data.userName}</font></span>
									<i class="glyph-icon icon-user"></i>
								</c:if>
						</span>
						</a>
						<div class="dropdown-menu pad0B float-right profileLogout">
							<div class="box-sm widthProfile">
								<div class="login-box clearfix">
									<!-- <div class="user-img">
										<a href="#" title="" class="change-img">Change photo</a> <img
											src="/apu/image/user/profile/${data.userId}.png" alt="">
									</div> -->
									<div class="user-info">
										<span> <c:if test="${data.userName != null}">
												${data.userName}
											</c:if> <c:if test="${data.userDesignation != null}">
												<i>${data.userDesignation}</i>
											</c:if>
										</span> <!-- <a
											href="javascript:loadContent('/app/singleUploadForm','page-content-holder');"
											title="">Edit profile</a> -->
									</div>
								</div>

								<div class="pad5A button-pane button-pane-alt text-center">
									<c:url value="/apu/logout" var="logoutUrl" />
									<form id="logout" action="${logoutUrl}" method="post">
										<input type="hidden" name="${_csrf.parameterName}"
											value="${_csrf.token}" />
									</form>
									<a href="javascript:document.getElementById('logout').submit()"
										class="btn display-block font-normal btn-danger"> <i
										class="glyph-icon icon-power-off"></i> <spring:message
											code="main-dashboard.logout" />
									</a>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div id="header-left-1">
					<div class="modal bs-example-modal-sm" tabindex="-1" role="dialog"
						aria-labelledby="mySmallModalLabel" aria-hidden="true">
						<div class="modal-dialog modal-sm">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">&times;</button>
								</div>
								<div class="modal-body">
									<div id="contextMenuMobileDiv">
										<script type="text/javascript">
											getUrl('/app/context?view=mobile%ts=${javaDate.time}',
													'contextMenuMobileDiv');
											refreshCurrentContent();
										</script>
									</div>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">Close</button>
									<button type="button" class="btn btn-primary">Save
										changes</button>
								</div>
							</div>
						</div>
					</div>
				</div>

			</div>
			<!-- #page-header -->

			<div id="page-sidebar" class="rm-transition">
				<div id="page-sidebar-wrapper">
					<div id="sidebar-menu">
						<ul>
							<li id="DashboardLi" class="hoverList"><a
								href="javascript:loadContent('/app/dashboard/summary','page-content-holder');highLightSidebarIcon('DashboardLi');"
								title="Dashboard"> <i class="glyph-icon icon-desktop"></i> <span><spring:message
											code="mainDashboard-form.Dashboard-label" /></span>
							</a></li>
							<li id="AddBookingLi" class="hoverList"><a
								href="javascript:getUrl('/app/addBooking/create/form','page-content-holder','initDate');highLightSidebarIcon('AddBookingLi');"
								title="Add Booking"> <i class="glyph-icon icon-pencil"></i> <span><spring:message
											code="mainDashboard-form.addBooking-label" /></span>
							</a></li>
							<li id="ManagementbookingDashboardLi" class="hoverList"><a
								href="javascript:loadContent('/app/addBooking/list','page-content-holder');highLightSidebarIcon('ManagementbookingDashboardLi');"
								title="Management Booking"> <i
									class="glyph-icon icon-institution"></i> <span><spring:message
											code="mainDashboard-form.managementBooking-label" /></span>
							</a></li>
							<li id="TaxReportsLi" class="hoverList1"><a
											href="javascript:loadContent('/app/bill/list','page-content-holder');highLightSidebarIcon('TaxReportsLi');"
											title="Bill"> <i class="glyph-icon icon-list-alt"></i><span><spring:message
														code="mainDashboard-form.bill-label" /></span>
										</a></li>
							
										<li id="CompletedBookingsLi" class="hoverList1"><a
											href="javascript:loadContent('/app/report/bill/past','page-content-holder');highLightSidebarIcon('CompletedBookingsLi');"
											title="Reports"><i class="glyph-icon icon-list-alt"></i> <span><spring:message
														code="mainDashboard-form.past-label" /></span>
										</a></li>
										<li id="TodayBookingsLi" class="hoverList1"><a
											href="javascript:loadContent('/app/report/bill/today','page-content-holder');highLightSidebarIcon('TodayBookingsLi');"
											title="Reports"> <i class="glyph-icon icon-list-alt"></i><span><spring:message
														code="mainDashboard-form.today-label" /></span>
										</a></li>
										<li id="ComingBookingsLi" class="hoverList1"><a
											href="javascript:loadContent('/app/report/bill/future','page-content-holder');highLightSidebarIcon('ComingBookingsLi');"
											title="Reports"><i class="glyph-icon icon-list-alt"></i> <span><spring:message
														code="mainDashboard-form.future-label" /></span>
										</a></li>
										<li id="TransactionsLi" class="hoverList"><a href="#"
									title="Reports"> <i class="glyph-icon icon-ge"></i>
										<span><spring:message
												code="mainDashboard-form.failedTransaction-label" /></span>
								</a>
									<ul>
										<li class="hoverList1"><a
											href="javascript:loadContent('/app/transactionStatus/invoice/success','page-content-holder');highLightSidebarIcon('TransactionsLi');"
											title="Failed Transaction"> <span><spring:message
														code="mainDashboard-form.success-label" /></span>
										</a></li>
										<li class="hoverList1"><a
											href="javascript:loadContent('/app/transactionStatus/invoice/pending','page-content-holder');highLightSidebarIcon('TransactionsLi');"
											title="Reports"> <span><spring:message
														code="mainDashboard-form.pending-label" /></span>
										</a></li>
										<li class="hoverList1"><a
											href="javascript:loadContent('/app/transactionStatus/invoice/failed','page-content-holder');highLightSidebarIcon('TransactionsLi');"
											title="Reports"> <span><spring:message
														code="mainDashboard-form.fail-label" /></span>
										</a></li>
							</ul>
							</li>
							<li id="TotalStatusLi" class="hoverList"><a
								href="javascript:loadContent('/app/endUser/list','page-content-holder');highLightSidebarIcon('TotalStatusLi');"
								title="Total Status"> <i class="glyph-icon icon-table"></i>
									<span><spring:message
											code="mainDashboard-form.totalStatus-label" /></span>
							</a></li>
							<li id="InvoiceLi" class="hoverList1"><a
											href="javascript:loadContent('/app/invoice/find/form','page-content-holder');highLightSidebarIcon('InvoiceLi');"
											title="Invoice">  <i class="glyph-icon icon-download"></i><span><spring:message
														code="mainDashboard-form.invoice-label" /></span>
										</a></li> 
							<li id="SMSGatewayDetailsLi" class="hoverList"><a
								href="javascript:loadContent('/app/smsGatewayDetails/list','page-content-holder');highLightSidebarIcon('SMSGatewayDetailsLi');"
								title="SMS Gateway Details"> <i
									class="glyph-icon icon-clock-os-circle"></i> <span><spring:message
											code="mainDashboard-form.smsGatewayDetails-label" /></span>
							</a></li>
							<li id="PaymentGatewayDetailsDashboardLi" class="hoverList"><a
								href="javascript:loadContent('/app/paymentGatewayDetails/list','page-content-holder');highLightSidebarIcon('PaymentGatewayDetailsDashboardLi');"
								title="Payment Gateway Details"> <i
									class="glyph-icon icon-rupee"></i> <span><spring:message
											code="mainDashboard-form.paymentGatewayDetailsDashboardLi-label" /></span>
							</a></li>
							
							<li id="canceledTransactionsDashboardLi" class="hoverList"><a
								href="javascript:loadContent('/app/canceledTransactions/list','page-content-holder');highLightSidebarIcon('canceledTransactionsDashboardLi');"
								title="Canceled Transactions"> <i
									class="glyph-icon icon-clock-os-circle"></i> <span><spring:message
											code="mainDashboard-form.canceledTransactions-label" /></span>
							</a></li> 
							
							<!--  <li id="PropertyImagesLi" class="hoverList"><a
								href="javascript:loadContent('/app/propertyImages/list','page-content-holder');highLightSidebarIcon('PropertyImagesLi');"
								title="Property Images"> <i class="glyph-icon icon-file-picture-o"></i>
									<span><spring:message
											code="mainDashboard-form.propertyImages-label" /></span>
							</a></li>-->
							<security:authorize access="hasAnyRole('ROLE_ADMIN')">
								<li id="adminDashboardLi" class="hoverList"><a href="#"
									title="Administration"> <i class="glyph-icon icon-ge"></i>
										<span><spring:message
												code="mainDashboard-form.Administration-label" /></span>
								</a>
									<ul>
									 <li class="hoverList1"><a
											href="javascript:loadContent('/app/marquee/list','page-content-holder');highLightSidebarIcon('adminDashboardLi');"
											title="marquee"> <span><spring:message
														code="mainDashboard-form.marquee-label" /></span>
										</a></li> 
										<%-- <li class="hoverList1"><a
											href="javascript:loadContent('/app/endUser/list','page-content-holder');highLightSidebarIcon('adminDashboardLi');"
											title="Bill"> <span><spring:message
														code="mainDashboard-form.endUser-label" /></span>
										</a></li>
										
										 --%>
										<li class="hoverList1"><a
											href="javascript:loadContent('/app/rentPurpose/list','page-content-holder');highLightSidebarIcon('adminDashboardLi');"
											title="Rent purpose"> <span><spring:message
														code="mainDashboard-form.rentPurpose-label" /></span>
										</a></li>
										<li class="hoverList1"><a
											href="javascript:loadContent('/app/propertyRentPrice/list','page-content-holder');highLightSidebarIcon('adminDashboardLi');"
											title="Property rent price"> <span><spring:message
														code="mainDashboard-form.propertyRentPrice-label" /></span>
										</a></li>
										<li class="hoverList1"><a
											href="javascript:loadContent('/app/property/list','page-content-holder');highLightSidebarIcon('adminDashboardLi');"
											title="Property"> <span><spring:message
														code="mainDashboard-form.property-label" /></span>
										</a></li>
										<li class="hoverList1"><a
											href="javascript:loadContent('/app/user/list','page-content-holder');highLightSidebarIcon('adminDashboardLi');"
											title="User"> <span><spring:message
														code="mainDashboard-form.Users-label" /></span>
										</a></li>
										<li class="hoverList1"><a
											href="javascript:loadContent('/app/userRole/list','page-content-holder');highLightSidebarIcon('adminDashboardLi');"
											title="User Role"> <span><spring:message
														code="mainDashboard-form.UserRolelabel" /></span>
										</a></li>
										<li class=hoverList1><a
											href="javascript:loadContent('/app/loginHistory/list','page-content-holder');highLightSidebarIcon('adminDashboardLi');"
											title="Login History"> <span><spring:message
														code="mainDashboard-form.LoginHistorylabel" /></span>
										</a></li>
										<li class="hoverList1"><a
											href="javascript:loadContent('/app/organization/list','page-content-holder');highLightSidebarIcon('adminDashboardLi');"
											title="Organization"> <span><spring:message
														code="mainDashboard-form.Panchayats-label" /></span>
										</a></li>
										<li class="hoverList1"><a
											href="javascript:loadContent('/app/auditData/list','page-content-holder');highLightSidebarIcon('adminDashboardLi');"
											title="Audit Data"> <span><spring:message
														code="mainDashboard-form.auditData-label" /></span>
										</a></li>
									</ul></li>
							</security:authorize>
						</ul>

					</div>

				</div>
			</div>

			<div id="page-content-wrapper" class="rm-transition">
				<div class="row" id="contextMenuBodyDiv">
					<script type="text/javascript">
						getUrl('/app/context', 'contextMenuBodyDiv');
						refreshCurrentContent();
					</script>
				</div>
				<div class="row autoHscroll">
					<div id="page-content-holder"
						onload="javascript:renderProgressbar()">
						<script type="text/javascript">
							$(function() {
								$('body').addClass('sidebar-collapsed');
							});
						</script>
					</div>
				</div>
			</div>

		</div>
	</div>
</body>
</html>