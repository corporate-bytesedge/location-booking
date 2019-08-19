<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<script type="text/javascript">
	function openNewURLInTheSameWindow() {
		window.open('${endUserForm.paymentUrl}',"_self");
	};
	openNewURLInTheSameWindow();
</script>