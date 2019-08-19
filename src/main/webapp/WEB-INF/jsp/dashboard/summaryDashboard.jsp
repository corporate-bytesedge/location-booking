<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<div id="page-content" style="padding-bottom: 3%;">
	<div id="summaryDashboardButtonDiv" style="margin-bottom: 10px;">
		<div style="float: left" class="dashboard-Name">
			<h3>
				<spring:message code="summaryDashboard.title" />
			</h3>
		</div>
		<div style="float: right" class="refreshFloat">
			<button id="summaryDashboardRefreshBt"
				name="summaryDashboardRefreshBt" class="btn btn-azure"
				onClick="javascript:loadContent('/app/dashboard/summary','page-content-holder');">
				<i class="glyph-icon icon-refresh"></i>&nbsp;
				<spring:message code="summaryDashboard.refresh-button" />
			</button>
		</div>
		<div style="clear: both; padding-top: 3px;">
			<div class="divider"></div>
		</div>
	</div>

	<div id="page-content" style="background: #fff;">

		<div class="row">
			<div class="col-lg-4">
				<div class="tile-box tile-box-alt bg-blue">
					<div class="tile-header">Completed Bookings</div>
					<div class="tile-content-wrapper">
						<i class="glyph-icon icon-inbox"></i>
						<div class="tile-content">
							<i class="glyph-icon icon-caret-up font-red"></i> 
							${data.pastBillCount}
						</div>
					</div>
					<a href="javascript:loadContent('/app/report/bill/past','page-content-holder');" class="tile-footer"> view details <i
						class="glyph-icon icon-arrow-right"></i>
					</a>
				</div>

			</div>

			<div class="col-lg-4">

				<div class="tile-box tile-box-alt bg-green">
					<div class="tile-header">Today Bookings</div>
					<div class="tile-content-wrapper">
						<i class="glyph-icon icon-cloud-download"></i>
						<div class="tile-content">
							<i class="glyph-icon icon-arrow-up font-green"></i>
							${data.todayBillCount}
						</div>
					</div>
					<a href="javascript:loadContent('/app/report/bill/today','page-content-holder');" class="tile-footer"> view details <i
						class="glyph-icon icon-arrow-right"></i>
					</a>
				</div>

			</div>

			<div class="col-lg-4">

				<div class="tile-box tile-box-alt bg-red" title="">
					<div class="tile-header">UpComing Bookings</div>
					<div class="tile-content-wrapper">
						<i class="glyph-icon icon-credit-card"></i>
						<div class="tile-content">
							<i class="glyph-icon icon-arrow-up font-green"></i>
							${data.futureBillCount}
						</div>
					</div>
					<a href="javascript:loadContent('/app/report/bill/future','page-content-holder');" class="tile-footer"> view details <i
						class="glyph-icon icon-arrow-right"></i>
					</a>
				</div>

			</div>

		</div>

	</div>
</div>
