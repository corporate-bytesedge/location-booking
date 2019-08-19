<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
<style type="text/css">
	.card-div-center {
		    margin: 0 auto;
    display: block;
    }
    .textcenter{
    	text-align:center;
    }
    .main-footer{
    	    position: absolute;
    margin: auto;
    bottom: 0;
    width: 100%;
    }
    #page-content-holder {
    	    margin-top: 140px;
    }
</style>

<div class="container">
	<div class="row">
		<div class="col-md-offset-4 col-md-4">
		   <h2 class="textcenter">Invoice not found</h2>
				<button id="invoiceViewBackBt" name="invoiceViewBackBt" type="back"
					class="btn btn-info"
					onClick="javascript:getUrl('/app/invoice/find/form?reqPage=${reqPage}&pageSize=${pageSize}','page-content-holder');">
					<i class="glyph-icon icon-minus-square"></i>&nbsp;
					Back
				</button>
		</div>
	</div>
</div>