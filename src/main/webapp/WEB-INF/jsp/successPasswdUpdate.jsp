<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp"%>
<html lang="en">
<head>
<title>Shilparamam - ${data.ctxName} - Shilparamam</title>

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
</head>

<div class="container">
	<div class="row">
		<div class="col-md-offset-4 col-md-4">
		   <h2 class="textcenter">Welcome to Shilparamam</h2>
			<p class="textcenter">Successfully updated your password please login</p>
				<a href="/apu/loginPage" style="text-align: center;display: flex;">
			  		<button type="submit" class="btn btn-info" style="margin: 30px auto;"><i class="ft-unlock"></i>Login</button>
			   </a>
		</div>
	</div>
</div>
</html>