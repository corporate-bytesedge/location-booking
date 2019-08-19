<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Shilparamam</title>
	<link rel="shortcut icon" href="/assets/images/icons/favicon.png" type="image/x-icon"></link>
	<style type="text/css">
		.loading {
		  position: absolute;
		  top: 50%;
		  left: 50%;
		}
		.loading-bar {
		  display: inline-block;
		  width: 4px;
		  height: 18px;
		  border-radius: 4px;
		  animation: loading 1s ease-in-out infinite;
		}
		.loading-bar:nth-child(1) {
		  background-color: #3498db;
		  animation-delay: 0;
		}
		.loading-bar:nth-child(2) {
		  background-color: #c0392b;
		  animation-delay: 0.09s;
		}
		.loading-bar:nth-child(3) {
		  background-color: #f1c40f;
		  animation-delay: .18s;
		}
		.loading-bar:nth-child(4) {
		  background-color: #27ae60;
		  animation-delay: .27s;
		}
		
		@keyframes loading {
		  0% {
		    transform: scale(1);
		  }
		  20% {
		    transform: scale(1, 2.2);
		  }
		  40% {
		    transform: scale(1);
		  }
		}
	</style>
</head>
<body>
	<div id="indexDiv" style="width: 100%; text-align: center; margin-top: 125px;">
	<div class="loading">
	  <div class="loading-bar"></div>
	  <div class="loading-bar"></div>
	  <div class="loading-bar"></div>
	  <div class="loading-bar"></div>
	</div>
	</div>
	<script type="text/javascript">
		window.location.href = '${redirectURL}/apu/home';
	</script>
</body>
</html>