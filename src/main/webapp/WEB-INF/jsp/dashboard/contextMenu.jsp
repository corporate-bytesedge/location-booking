<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<style>
#desktopDropDowns {
	width: 100%;
	padding-left: 70px;
	padding-top: 8px;
	padding-bottom: 8px;
	background-color: #F5F7F9;
}
.hideDiv{
	display:none;
}

.dataDispay {
	    padding-top: 8px;
}
.showDiv{
	display:block;
}
.marginDiv{
	margin-top:20px;
}
@media screen and (max-width: 600px) {
	#desktopDropDowns {
	}
}
@media screen and (max-width:1200px){
	#desktopDropDowns{
		display:block;
		}
}

</style>
<script>
 $(document).ready(function(){
    $("#changeButton").click(function(){
        $("#contextMenuViewModeDiv").toggle();
    });
});

function buttonToggleAndrefreshCurrentContent() {
	buttonToggle();
	refreshCurrentContent();
} 
</script>

<script type="text/javascript">
	var currentDiv = "contextMenuViewModeDiv";
	 function toggleDiv(id) {
		var div1 = document.getElementById(id);
	} 

	function buttonToggle() {
		toggleDiv("contextMenuEditModeDiv");
		toggleDiv("contextMenuViewModeDiv");
	};
</script> 

 <script>
	$(document).ready(function(){
		$(".submitData").click(function(){
			$(".showDiv").toggle();
		});
		
		$(".submitData").click(function(){
			$(".hideDiv").toggle();
		})
	});
	
</script> 
<c:choose>
    <c:when test="${data.finalyze == 'false'}">
		<div id="contextMenuEditModeDiv" class="showDiv" >
    </c:when>
    <c:otherwise>
    	<div id="contextMenuEditModeDiv" class="hideDiv" >
    </c:otherwise>
</c:choose>
</div>

<c:choose>
    <c:when test="${data.finalyze == 'false'}">
		<div id="contextMenuEditModeDiv" class="hideDiv" >
    </c:when>
    <c:otherwise>
    	<div id="contextMenuEditModeDiv" class="showDiv" >
    </c:otherwise>
</c:choose>
</div>

