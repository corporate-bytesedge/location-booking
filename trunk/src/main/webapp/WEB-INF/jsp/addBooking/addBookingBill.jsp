<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>
  <!-- <title>Shilparamam</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>-->
 <style>
  	.main-div {
	margin: 0 40px;
}

.main-section {
	margin-top: 30px;
	border-bottom: 2px solid gray;
}

.logo {
	width: 220px;
	height: 120px;
	margin: 0 auto;
	margin-top: -47px;
}

.invoice-section {
	margin-top: 20px;
	border-bottom: 2px solid gray;
	padding-bottom: 20px;
}

.details-section h4 {
	background: lightgray;
	padding: 5px;
	margin-bottom: 20px;
	padding-bottom: 20px;
}

.table {
	border-top: 2px solid gray;
}

.table-section {
	margin-top: 30px;
}

.terms-section {
	margin-top: 60px;
	margin-bottom: 40px;
}

.management-section h4 {
	float: right;
	margin-bottom: 30px;
}

.button-section {
	margin-left: 50px;
	margin-top: 37px;
}
.logo-icon{
	    width: 180px;
    height: 75px;
    margin: 0 auto;
    margin-top: -10px;
}

#toPrint{
	background:white;
	padding-top:20px !important;
}
  </style>
  <div class="container">
    	  <button type="button" class="btn btn-primary" onClick="javascript:getUrl('/app/dashboard/summary?reqPage=${reqPage}&pageSize=${pageSize}','page-content-holder');"  title="Back">Back</button>
    	  <button type="button" class="btn btn-danger" onClick="javascript:downloadPdf('toPrint')">Download</button>
  </div>
	<div class="" id="toPrint">
		<div class="main-div">
			<div class="row main-section" style="margin-bottom: 30px;">
			<div class="col-md-4 col-sm-4">
					<img src="/img/logo.png" alt="bill-view"
						class="img-responsive logo-icon">
				</div>
				
				<div class="col-md-4 col-sm-4">
					<h3 class="text-center">INVOICE</h3>
				</div>
				<div class="col-md-4 col-sm-4">
					<p>
						<b>Shilparamam.in</b>
					</p>
					<p>Hitech city,Main road,</p>
					<p>Hyderabad-500081,India.</p>
				</div>
			</div>
			<div class="row invoice-section">
				<div class="col-md-7 col-sm-7">
					<div class="col-md-3 col-sm-3">
						<p>Invoice Id</p>
						<p>Invoice Date</p>
						<p>Booking Date</p>
					</div>
					<div class="col-md-9 col-sm-9">
						<p>: ${addBookingForm.applicationId}</p>
						<p>: ${addBookingForm.createdTime}</p>
						<p>: ${addBookingForm.bookingDate}</p>
					</div>
				</div>
				<div class="col-md-5 col-sm-5">
					<div class="col-md-3 col-sm-3">
						<p>
							<b></b>Address
						</p>
					</div>
					<div class="col-md-9 col-sm-9">
						<p>
							<b></b>: ${addBookingForm.addr}
						</p>
					</div>
				</div>
			</div>
			<div class="row details-section">
				<h4 style="background: lightgrey;padding: 7px 0 7px 10px;
				">Bill To</h4>
				<div class="col-md-2 col-sm-2">
					<p>Name</p>
					<p>Mobile No</p>
					<p>Id Proof</p>
					<p>Email</p>
					<p>Referred By</p>
				</div>
				<div class="col-md-10 col-sm-10">
					<p>: ${addBookingForm.name}</p>
					<p>: ${addBookingForm.mobileNumber}</p>
					<p>: ${addBookingForm.idProof}</p>
					<p>: ${addBookingForm.email}</p>
					<p>: ${addBookingForm.referredBy}</p>
				</div>
			</div>
			<table class="table table-bordered details-border">
				<tr>
					<th>S.No</th>
					<th>Venue</th>
					<th>Purpose</th>
					<th>Price</th>
					<th>CGST</th>
					<th>SGST</th>
					<th>Amount</th>
				</tr>
				<tr>
					<td>1</td>
					<td>${addBookingForm.propertyName}</td>
					<td>${addBookingForm.purposeName}</td>
					<td>${addBookingForm.price}</td>
					<td>${addBookingForm.cgst}</td>
					<td>${addBookingForm.sgst}</td>
					<td>${addBookingForm.total}</td>
				</tr>
			</table>
			<div class="row table-section">
				<!-- <div class="col-md-6 col-sm-6">
					<div class="col-md-4 col-sm-4">
						<p>Total in Words :</p>
					</div>
					<div class="col-md-8 col-sm-8">
						<p>Total in Words Total in Words Total in Words Total in Words
						</p>
					</div>
				</div> -->
				<div class="col-md-6 col-sm-6">
					<div class="col-md-5 col-sm-5">
						<p>Sub Total</p>
						<p>CGST</p>
						<p>SGST</p>
						<p>Technical charges</p>
						<p>Total</p>
					</div>
					<div class="col-md-7 col-sm-7">
						<p>: ${addBookingForm.price}</p>
						<p>: ${addBookingForm.cgst}</p>
						<p>: ${addBookingForm.sgst}</p>
						<p>: ${addBookingForm.serviceTax}</p>
						<p>: ${addBookingForm.total}</p>
					</div>
				</div>
			</div>
			<div class="terms-section">
			<h4>Terms & Conditions</h4>
				<ol>
					<li>The agenda of the events should be handed over to the
						management of Shilparamam before two days of the event</li>
					<li>The stage Arrangements plan / Decoration plan /
						Advertisement plan of the even should be as per the Specifications
						and Rules of the Shilparamam auditorium</li>

				</ol>
			</div>
			<div class="management-section">
				<h4 class="float-right">SHILPARAMAM MANAGEMENT</h4>
			</div>

		</div>

	</div>


	<script>
		function downloadPdf(divID) {
			//console.log("adlfglhafs");
			var disp_setting = "toolbar=yes,location=no,";
			disp_setting += "directories=yes,menubar=yes,";
			disp_setting += "scrollbars=yes,width=850, height=600, left=100, top=25";
			var content_vlue = document.getElementById(divID).innerHTML;
			var docprint = window.open('_blank', "", disp_setting);
			docprint.document.open();
			docprint.document
					.write('<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"');
			docprint.document
					.write('"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">');
			docprint.document
					.write('<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">');
			docprint.document.write('<head><title>Invoice</title>');
			docprint.document
					.write('<link rel="stylesheet" href="/css/bootstrap-3.4.0.min.css">');
			docprint.document
					.write('\x3Cscript type="text/javascript" src="/js/jquery-3.4.1.min.js">\x3C/script>');
			docprint.document
					.write('\x3Cscript type="text/javascript" src="/js/html2canvas.js">\x3C/script>');
			docprint.document
					.write('\x3Cscript type="text/javascript" src="/js/jspdf-debug.js">\x3C/script>');
			docprint.document
					.write('\x3Cscript src="/js/bootstrap-3.4.0.min.js">\x3C/script>');
			docprint.document
					.write('\x3Cscript src="/js/invoice_pdf_download.js">\x3C/script>');

			docprint.document
					.write("</head><body style='margin:10px 20px 0 20px;' >");

			docprint.document.write(content_vlue);
			docprint.document.write('</body></html>');
			//docprint.document.close();
			//window.print();
			docprint.focus();
			var doc = new jsPDF('p', 'pt', 'a4');
			doc.addHTML(document.getElementById("toPrint"), function() {
				doc.save('invoice.pdf');
			});
		}
	</script>
	<script type="text/javascript" src="/js/html2canvas.js"></script>
	<script type="text/javascript" src="/js/jspdf-debug.js"></script>




