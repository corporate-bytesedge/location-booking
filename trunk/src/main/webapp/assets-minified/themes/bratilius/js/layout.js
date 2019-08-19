function layoutFormatter() {
	setTimeout(function() {
		var a = $(window).height(), b = $("#page-header").height(), c = $(
				"#page-nav").height(), d = a - b, e = a - b - c;
		$("#page-sidebar").height(d), $("#page-sidebar-wrapper").height(d), $(
				"#page-content").css("min-height", e - 2)
	}, 499)
}
function init_page_transitions() {
	var a = [ ".pt-page-moveFromLeft", "pt-page-moveFromRight",
			"pt-page-moveFromTop", "pt-page-moveFromBottom", "pt-page-fade",
			"pt-page-moveFromLeftFade", "pt-page-moveFromRightFade",
			"pt-page-moveFromTopFade", "pt-page-moveFromBottomFade",
			"pt-page-scaleUp", "pt-page-scaleUpCenter", "pt-page-flipInLeft",
			"pt-page-flipInRight", "pt-page-flipInBottom", "pt-page-flipInTop",
			"pt-page-rotatePullRight", "pt-page-rotatePullLeft",
			"pt-page-rotatePullTop", "pt-page-rotatePullBottom",
			"pt-page-rotateUnfoldLeft", "pt-page-rotateUnfoldRight",
			"pt-page-rotateUnfoldTop", "pt-page-rotateUnfoldBottom" ];
	for ( var b in a) {
		var c = a[b];
		if ($(".add-transition").hasClass(c))
			return $(".add-transition").addClass(c + "-init page-transition"),
					void setTimeout(function() {
						$(".add-transition").removeClass(
								c + " " + c + "-init page-transition")
					}, 1200)
	}
}
		$(window).resize(function() {
			layoutFormatter()
		}),
		$(document).on("ready", function() {
			$("#page-sidebar-wrapper").niceScroll({
				horizrailenabled : !1,
				cursorborder : "0",
				cursorwidth : "6px",
				cursorcolor : "#dde5ed",
				zindex : "5555",
				autohidemode : !0,
				bouncescroll : !0,
				mousescrollstep : "40",
				scrollspeed : "100",
				background : "#f5f7f9",
				cursoropacitymax : "0.6",
				cursorborderradius : "0"
			}), $("#page-sidebar-wrapper").getNiceScroll().resize()
		}),
		$(document)
				.ready(
						function() {
									layoutFormatter(),
									$(function() {
										$("#responsive-open-menu").click(
												function() {
													$("#page-sidebar").toggle()
												})
									}),
									$(function() {
										$("#sidebar-menu > ul").superclick({
											animation : {
												height : "show"
											},
											animationOut : {
												height : "hide"
											}
										})
									}),
									$(function() {
										$("#collapse-sidebar")
												.click(
														function() {
																	$(
																			"#page-sidebar, #page-content-wrapper, #header-logo")
																			.removeClass(
																					"rm-transition"),
																	$("body")
																			.toggleClass(
																					"sidebar-collapsed"),
																	$(
																			".glyph-icon",
																			this)
																			.toggleClass(
																					"icon-chevron-right")
																			.toggleClass(
																					"icon-chevron-left")
														})
									})
						}), $(document).ready(function() {
			init_page_transitions()
		});