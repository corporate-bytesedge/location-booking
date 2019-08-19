$(function(){
	console.log("fcs");
	alert("ghjkl");
	var doc = new jsPDF('p','pt','a4');doc.addHTML(document.body,function() {doc.save('invoice.pdf');});
});