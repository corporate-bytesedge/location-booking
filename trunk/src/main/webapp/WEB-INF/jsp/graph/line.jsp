<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>

<div class="content-box mrg15B">
	<h3 class="content-box-header bg-black text-left">
		<i class="glyph-icon icon-rss-square"></i> ${data.graphLabel}
	</h3>
	<div class="content-box-wrapper">
		<canvas id="lineCanvas${data.graphId}" height="120"></canvas>
		<script type="text/javascript">
			var ctx = document.getElementById("lineCanvas${data.graphId}").getContext('2d');
			var myChart = new Chart(ctx, ${data.graphData});
		</script>
	</div>
</div>