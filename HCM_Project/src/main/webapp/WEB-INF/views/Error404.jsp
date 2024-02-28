<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="canonical" href="https://preview.keenthemes.com/keen" />
<link rel="shortcut icon" href="assets/media/logos/favicon.ico" />
<!--begin::Fonts(mandatory for all pages)-->
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Inter:300,400,500,600,700" />
<!--end::Fonts-->
<!--begin::Global Stylesheets Bundle(mandatory for all pages)-->
<link href="assets/plugins/global/plugins.bundle.css" rel="stylesheet" type="text/css" />
<link href="assets/css/style.bundle.css" rel="stylesheet" type="text/css" />
<!--end::Global Stylesheets Bundle-->
<script>// Frame-busting to prevent site from being loaded within a frame without permission (click-jacking) if (window.top != window.self) { window.top.location.replace(window.self.location.href); }</script>

<title>404ERROR</title>
</head>
<body id="kt_body" class="app-blank app-blank bgi-size-cover bgi-position-center bgi-no-repeat">
<!--begin::Theme mode setup on page load-->
<script>

var defaultThemeMode = "light"; var themeMode; 
if ( document.documentElement ) { 
	if ( document.documentElement.hasAttribute("data-bs-theme-mode")) { 
		themeMode = document.documentElement.getAttribute("data-bs-theme-mode"); 
	} else {
		if ( localStorage.getItem("data-bs-theme") !== null ) {
			themeMode = localStorage.getItem("data-bs-theme"); 
		} else { themeMode = defaultThemeMode; } 
	} 
		if (themeMode === "system") { 
			themeMode = window.matchMedia("(prefers-color-scheme: dark)").matches ? "dark" : "light"; 
		} document.documentElement.setAttribute("data-bs-theme", themeMode); 
	}

</script>
<!--end::Theme mode setup on page load-->
<!--begin::Root-->
<div class="d-flex flex-column flex-root" id="kt_app_root">
<!--begin::Page bg image-->

<style>

	body { 
		background-image: url('assets/media/auth/bg13.jpg'); 
	} 
	
	[data-bs-theme="dark"] body { 
		background-image: url('assets/media/auth/bg13-dark.jpg'); 
	}
	
</style>

<!--end::Page bg image-->
<!--begin::Authentication - Signup Welcome Message -->
<div class="d-flex flex-column flex-center flex-column-fluid">
	<!--begin::Content-->
	<div class="d-flex flex-column flex-center text-center p-10">
		<!--begin::Wrapper-->
		<div class="card card-flush w-lg-650px py-5">
			<div class="card-body py-15 py-lg-20">
				<!--begin::Title-->
				<h1 class="fw-bolder fs-2hx text-gray-900 mb-4">Oops!</h1>
				<!--end::Title-->
				<!--begin::Text-->
				<div class="fw-semibold fs-6 text-gray-500 mb-7">페이지를 찾을 수 없습니다 관리자에게 문의하세요.</div>
				<!--end::Text-->
				<!--begin::Illustration-->
				<div class="mb-3">
					<img src="assets/media/auth/404.png" class="mw-100 mh-300px theme-light-show" alt="" />
					<img src="assets/media/auth/404-dark.png" class="mw-100 mh-300px theme-dark-show" alt="" />
				</div>
				<!--end::Illustration-->
				<!--begin::Link-->
				<div class="mb-0">
					<a href="./main.do" class="btn btn-sm btn-primary">메인페이지로</a>
				</div>
				<!--end::Link-->
			</div>
		</div>
		<!--end::Wrapper-->
	</div>
	<!--end::Content-->
</div>
<!--end::Authentication - Signup Welcome Message-->
</div>
<!--end::Root-->
<!--begin::Javascript-->
<script>var hostUrl = "assets/";</script>
<!--begin::Global Javascript Bundle(mandatory for all pages)-->
<script src="assets/plugins/global/plugins.bundle.js"></script>
<script src="assets/js/scripts.bundle.js"></script>
<!--end::Global Javascript Bundle-->
<!--end::Javascript-->
</body>
<!--end::Body-->
</html>