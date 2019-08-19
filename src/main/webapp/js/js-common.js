// Global variables
var curSlideBarLiId = 'mainDashboardLi';

function AddressCopy(){
	
	if($("#addressCheck").prop('checked') == true){
		$("#permanentAddress").val($("#presentAddress").val());
	}else{
		$("#permanentAddress").val("");
	}
}

function AddressCopy1(){
	
	if($("#addressCheck1").prop('checked') == true){
		$("#permenentAddress1").val($("#presentAddress1").val());
	}else{
		$("#permenentAddress").val("");
	}
}

function highLightSidebarIcon(liId) {
	if(curSlideBarLiId == liId) {
		return;
	}
	
	var pli = document.getElementById(curSlideBarLiId)
	if(pli != null) {
		pli.style="background-color:#F5F7F9;";
	}
	
	var li = document.getElementById(liId)
	if(li != null) {
		li.style="background-color:#FFFFFF;";
		curSlideBarLiId = liId;
	}
	
	
	$(function() {
		$('body').addClass('sidebar-collapsed');
	});
	
};

function getUrl(uri, divId, callBackForm) {
	$('#' + divId).LoadingOverlay("show", {
	    background  : "rgba(220, 220, 220, 0.5)"
	});
	$.ajax({
		type : 'GET',
		url : uri,
		success : function(returnedData) {
			$('#' + divId).LoadingOverlay("hide", true);
			$('#' + divId).html(returnedData);
			if(callBackForm == 'initDate'){
				initDate();
			}
		},
		error : function(returnedData) {
			$('#' + divId).LoadingOverlay("hide", true);
		}
	});
};

function getDataFromUrl(uri) {
	return $.ajax({
		type : 'GET',
		url : uri
	});
};

function callUrl(uri) {
	$.ajax({
		type : 'GET',
		url : uri
	});
};

function submitForm(url, formId, divId) {
	var formValues = {};
	try {
		formValues = $('#' + formId).serialize()
	} catch (e) {
	}
	$('#' + divId).LoadingOverlay("show", {
	    background  : "rgba(220, 220, 220, 0.5)"
	});
	$.ajax({
		type : 'POST',
		url : url,
		data : formValues,
		success : function(responseText) {
			$('#' + divId).html(responseText);
			$('#' + divId).LoadingOverlay("hide", true);
		},
		error : function(returnedData) {
			$('#' + divId).LoadingOverlay("hide", true);
		}
	});
};

function submitForm(url, formId, divId, contentTypeVal) {
	var formValues = {};
	try {
		formValues = $('#' + formId).serialize()
	} catch (e) {
	}
	$('#' + divId).LoadingOverlay("show", {
	    background  : "rgba(220, 220, 220, 0.5)"
	});
	$.ajax({
		type : 'POST',
		url : url,
		data : formValues,
		contentType	: contentTypeVal,
		success : function(responseText) {
			$('#' + divId).html(responseText);
			$('#' + divId).LoadingOverlay("hide", true);
		},
		error : function(returnedData) {
			$('#' + divId).LoadingOverlay("hide", true);
		}
	});
};

function submitFileForm(url, formId, divId, contentTypeVal) {
	var formValues = {};
	 var options = { 
		type : 'post',
		target:	'#' + divId, 
        success: function(responseText, status) {
            $('#' + divId).html(responseText);
        }
    };
	$('#' + formId).ajax(options);
	return false;
};

var lastCtxContentUrl = '/app/dashboard/summary';
var lastCtxContentDiv = 'page-content-holder';
function loadContent(uri, divId) {
	lastCtxContentUrl = uri;
	lastCtxContentDiv = divId;
	getUrl(uri, divId);
}

function checkAllTableCheckbox(bx, tableName) {
	for (var tbls = document.getElementsByName(tableName), i = tbls.length; i--;)
		for (var bxs = tbls[i].getElementsByTagName("input"), j = bxs.length; j--;)
			if (bxs[j].type == "checkbox")
				bxs[j].checked = bx.checked;
};

function fadeOutDiv(divId) {
	$('#' + divId).delay(3000).fadeOut(2000);
};

function refreshCurrentContent() {
	if(lastCtxContentUrl != null && lastCtxContentDiv != null) {
		var retVal = getDataFromUrl('/app/context?finalyze=true');
		retVal.success(function (data) {
			getUrl(lastCtxContentUrl, lastCtxContentDiv);
		});
	}
};

function refreshCaptcha(divId) {
	document.getElementById(divId).src = '/apu/captcha.jpg?ts=' + new Date();
};


function AllowOnlyNumbers(e) {

    e = (e) ? e : window.event;
    var key = null;
    var charsKeys = [
        97, // a  Ctrl + a Select All
        65, // A Ctrl + A Select All
        99, // c Ctrl + c Copy
        67, // C Ctrl + C Copy
        118, // v Ctrl + v paste
        86, // V Ctrl + V paste
        115, // s Ctrl + s save
        83, // S Ctrl + S save
        112, // p Ctrl + p print
        80 // P Ctrl + P print
    ];

    var specialKeys = [
    8, // backspace
    9, // tab
    27, // escape
    13, // enter
    35, // Home & shiftKey +  #
    36, // End & shiftKey + $
    37, // left arrow &  shiftKey + %
    39, //right arrow & '
    46, // delete & .
    45 //Ins &  -
    ];

    key = e.keyCode ? e.keyCode : e.which ? e.which : e.charCode;

    //console.log("e.charCode: " + e.charCode + ", " + "e.which: " + e.which + ", " + "e.keyCode: " + e.keyCode);
    //console.log(String.fromCharCode(key));

    // check if pressed key is not number 
    if (key && key < 48 || key > 57) {

        //Allow: Ctrl + char for action save, print, copy, ...etc
        if ((e.ctrlKey && charsKeys.indexOf(key) != -1) ||
            //Fix Issue: f1 : f12 Or Ctrl + f1 : f12, in Firefox browser
            (navigator.userAgent.indexOf("Firefox") != -1 && ((e.ctrlKey && e.keyCode && e.keyCode > 0 && key >= 112 && key <= 123) || (e.keyCode && e.keyCode > 0 && key && key >= 112 && key <= 123)))) {
            return true
        }
            // Allow: Special Keys
        else if (specialKeys.indexOf(key) != -1) {
            //Fix Issue: right arrow & Delete & ins in FireFox
            if ((key == 39 || key == 45 || key == 46)) {
                return (navigator.userAgent.indexOf("Firefox") != -1 && e.keyCode != undefined && e.keyCode > 0);
            }
                //DisAllow : "#" & "$" & "%"
            else if (e.shiftKey && (key == 35 || key == 36 || key == 37)) {
                return false;
            }
            else {
                return true;
            }
        }
        else {
            return false;
        }
    }
    else {
        return true;
       }
    }

function AllowOnlyDecimals(e) {

    e = (e) ? e : window.event;
    var key = null;
    var charsKeys = [
        97, // a  Ctrl + a Select All
        65, // A Ctrl + A Select All
        99, // c Ctrl + c Copy
        67, // C Ctrl + C Copy
        118, // v Ctrl + v paste
        86, // V Ctrl + V paste
        115, // s Ctrl + s save
        83, // S Ctrl + S save
        112, // p Ctrl + p print
        80 // P Ctrl + P print
    ];

    var specialKeys = [
    8, // backspace
    9, // tab
    27, // escape
    13, // enter
    35, // Home & shiftKey +  #
    36, // End & shiftKey + $
    37, // left arrow &  shiftKey + %
    39, //right arrow & '
    46, // delete & .
    45 //Ins &  -
    ];

    key = e.keyCode ? e.keyCode : e.which ? e.which : e.charCode;

    //console.log("e.charCode: " + e.charCode + ", " + "e.which: " + e.which + ", " + "e.keyCode: " + e.keyCode);
    //console.log(String.fromCharCode(key));

    // check if pressed key is not number 
    if (key && key < 48 || key > 57) {

        //Allow: Ctrl + char for action save, print, copy, ...etc
        if ((e.ctrlKey && charsKeys.indexOf(key) != -1) ||
            //Fix Issue: f1 : f12 Or Ctrl + f1 : f12, in Firefox browser
            (navigator.userAgent.indexOf("Firefox") != -1 && ((e.ctrlKey && e.keyCode && e.keyCode > 0 && key >= 112 && key <= 123) || (e.keyCode && e.keyCode > 0 && key && key >= 112 && key <= 123)))) {
            return true
        }
            // Allow: Special Keys
        else if (specialKeys.indexOf(key) != -1) {
            //Fix Issue: right arrow & Delete & ins in FireFox
            if ((key == 39 || key == 45 )) {
                return (navigator.userAgent.indexOf("Firefox") != -1 && e.keyCode != undefined && e.keyCode > 0);
            }
                //DisAllow : "#" & "$" & "%"
            else if (e.shiftKey && (key == 35 || key == 36 || key == 37)) {
                return false;
            }
            else {
                return true;
            }
        }
        else {
            return false;
        }
    }
    else {
        return true;
       }
    }

function createObject(tableId, headerCheckBox, objType, url, divId) {
	var objId = "";
	var res=0;
	var tbl = document.getElementById(tableId)
	if(tbl != null) {
		for (var bxs = tbl.getElementsByTagName("input"), j = bxs.length; j--;) {
			if (bxs[j].type == "checkbox" && bxs[j].checked) {
				if(bxs[j].id.valueOf() != headerCheckBox) {
					objId = bxs[j].id;
					res++;
				}
			}
		}
	}
	if(res=='') {
		getUrl(url + objId, divId);
	} else {
		alert('You cannot perform create operation on Selected item. Please dont select any of items and try again');
	}
};

function updateObject(tableId, headerCheckBox, objType, url, divId) {
	var objId = "";
	var res=0;
	var tbl = document.getElementById(tableId)
	if(tbl != null) {
		for (var bxs = tbl.getElementsByTagName("input"), j = bxs.length; j--;) {
			if (bxs[j].type == "checkbox" && bxs[j].checked) {
				if(bxs[j].id.valueOf() != headerCheckBox) {
					objId = bxs[j].id;
					res++;
				}
			}
		}
	}
	if(res > 1) {
		alert('Please select only one ' + objType + ' to perform Update operation');
	} else if(res == 1) {
		getUrl(url + objId, divId);
	} else {
		alert('Please select one ' + objType + ' to perform Update operation');
	}
};

function removeObject(tableId, headerCheckBox, objType, url, divId) {
	var checkList = "";
	var res=0;
	var tbl = document.getElementById(tableId)
	if(tbl != null) {
		for (var bxs = tbl.getElementsByTagName("input"), j = bxs.length; j--;) {
			if (bxs[j].type == "checkbox" && bxs[j].checked) {
				if(bxs[j].id.valueOf() != headerCheckBox) {
					checkList = checkList + bxs[j].id + ",";
					res++;
				}
			}
		}
	}
	if(checkList == '' ) {
		alert('Please select atleast one ' + objType + ' to perform Remove operation');
	} else{
		$.confirm({
		    title: 'Confirm!',
		    content: 'Do you want to remove ' + res + ' ' + objType + '(s)?',
		    buttons: {
		        confirm: function () {
		        	getUrl(url + checkList, divId);
		        },
		        cancel: function () {
		        }
		    }
		});
	}
};

function viewObject(tableId, headerCheckBox, objType, url, divId) {
	var objId = "";
	var res=0;
	var tbl = document.getElementById(tableId)
	if(tbl != null) {
		for (var bxs = tbl.getElementsByTagName("input"), j = bxs.length; j--;) {
			if (bxs[j].type == "checkbox" && bxs[j].checked) {
				if(bxs[j].id.valueOf() != headerCheckBox) {
					objId = bxs[j].id;
					res++;
				}
			}
		}
	}
	if(res > 1) {
		alert('Please select only one ' + objType + ' to perform view operation');
	} else if(res == 1) {
		getUrl(url + objId, divId);
	} else {
		alert('Please select one ' + objType + ' to perform view operation');
	}
};

function viewInvoiceObject(tableId, headerCheckBox, objType, url, divId) {
	var objId = "";
	var year = ""
	var res=0;
	var tbl = document.getElementById(tableId)
	if(tbl != null) {
		for (var bxs = tbl.getElementsByTagName("input"), j = bxs.length; j--;) {
			if (bxs[j].type == "checkbox" && bxs[j].checked) {
				if(bxs[j].id.valueOf() != headerCheckBox) {
					objId = bxs[j].id;
					year = bxs[j].getAttribute("data-year");
					res++;
				}
			}
		}
	}
	if(res > 1) {
		alert('Please select only one ' + objType + ' to perform view operation');
	} else if(res == 1) {
		getUrl(url + objId + '&year=' + year, divId);
	} else {
		alert('Please select one ' + objType + ' to perform view operation');
	}
};

function getToPageUrl(pageListUrlTmpl, elementId, pageSize, divId) {
	reqPage = document.getElementById(elementId).value;
	reqPage = parseInt(reqPage) - 1;
	pageListUrlTmpl = pageListUrlTmpl.replace("%d", reqPage);
	pageListUrlTmpl = pageListUrlTmpl.replace("%d", pageSize);
	return getUrl(pageListUrlTmpl, divId);
}