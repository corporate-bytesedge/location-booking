<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp"%>
<html lang="en">
<head>
	<link rel="shortcut icon" href="/favicon.png" type="image/x-icon" />
	
	<meta charset="UTF-8">
	<title>PRMS</title>
	<meta name="description" content="">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<link rel="shortcut icon" href="/assets/images/icons/favicon.png">
    <script type="text/javascript" src="/assets-minified/js-core.js"></script>
	<link rel="stylesheet" type="text/css" href="/assets-minified/main.css">
	
	<script type="text/javascript">
	    $(window).load(function(){
	        setTimeout(function() {
	            $('#loading').fadeOut( 400, "linear" );
	        }, 300);
	    });
	</script>
	<style>
	    #loading {position: fixed;width: 100%;height: 100%;left: 0;top: 0;right: 0;bottom: 0;display: block;background: #fff;z-index: 10000;}
	    #loading img {position: absolute;top: 50%;left: 50%;margin: -23px 0 0 -23px;}
	</style>
	<style type="text/css">
		body {
			padding: 20;
			margin: 0;
		}
		
		.handHover {
			cursor: pointer;
			cursor: hand;
		}
		
		.borderCl {
			border-style: solid;
			border-color: black;
		}
		
		.center {
			margin: auto;
			width: 40%;
			padding: 10px;
			text-align: center;
		}
		
		img {
			display: block;
			margin: auto;
		}
</style>
</head>
<body style="padding:20px;">
    <div class="">
    	<div class="center">
    		<img src="/img/pris_logo.png" alt="PRMS" height="48px" width="96px"> <h3>Panchayat Raj Administration Management System</h3>
    	</div>
	    <div class="container">
            <div class="row">
                <div class="col-md-4">
                    <div class="pricing-box content-box handHover borderCl" onclick="window.open('https://pris.ap.gov.in','_blank');">
                        <h3 class="bg-black pricing-title">Andhra Pradesh</h3>
                        <div class="pricing-specs">
                            <img src="/img/state/ap-map.png" />
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="pricing-box content-box handHover borderCl" onclick="window.open('https://pris.ts.gov.in','_blank');">
                        <h3 class="bg-black pricing-title">Telangana</h3>
                        <div class="pricing-specs">
                            <img src="/img/state/ts-map.png" />
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="pricing-box content-box handHover borderCl" onclick="">
                        <h3 class="bg-black pricing-title">Karnataka(Upcoming...)</h3>
                        <div class="pricing-specs">
                            <img src="/img/state/ka-map.png" />
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>