<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>

<div class="content-box mrg15B">
	<h3 class="content-box-header bg-black text-left">
		<i class="glyph-icon icon-rss-square"></i> Revenue Projections
	</h3>
	<div class="content-box-wrapper">
		<canvas id="barCanvar${data.graphId}" height="120"></canvas>
		<script type="text/javascript">
			var ctx = document.getElementById("barCanvar${data.graphId}").getContext('2d');
			var myChart = new Chart(ctx, ${data.graphData});
		</script>
	</div>
</div>