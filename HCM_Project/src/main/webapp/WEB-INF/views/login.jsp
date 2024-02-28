<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<meta name="description" content="The most advanced Bootstrap Admin Theme on Bootstrap Market trusted by over 4,000 beginners and professionals. Multi-demo, Dark Mode, RTL support. Grab your copy now and get life-time updates for free." />
<meta name="keywords" content="keen, bootstrap, bootstrap 5, bootstrap 4, admin themes, web design, figma, web development, free templates, free admin themes, bootstrap theme, bootstrap template, bootstrap dashboard, bootstrap dak mode, bootstrap button, bootstrap datepicker, bootstrap timepicker, fullcalendar, datatables, flaticon" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="canonical" href="https://preview.keenthemes.com/keen" />
<link rel="shortcut icon" href="assets/media/logos/favicon.ico" />
<!--begin::Fonts(mandatory for all pages)-->
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Inter:300,400,500,600,700" />
<!--end::Fonts-->
<!--begin::Vendor Stylesheets(used for this page only)-->
<link href="assets/plugins/custom/fullcalendar/fullcalendar.bundle.css" rel="stylesheet" type="text/css" />
<link href="assets/plugins/custom/datatables/datatables.bundle.css" rel="stylesheet" type="text/css" />
<!--end::Vendor Stylesheets-->
<!--begin::Global Stylesheets Bundle(mandatory for all pages)-->
<link href="assets/plugins/global/plugins.bundle.css" rel="stylesheet" type="text/css" />
<link href="assets/css/style.bundle.css" rel="stylesheet" type="text/css" />
<!--end::Global Stylesheets Bundle-->
<script>// Frame-busting to prevent site from being loaded within a frame without permission (click-jacking) if (window.top != window.self) { window.top.location.replace(window.self.location.href); }</script>


<title>Login 페이지</title>
</head>

<body id="kt_body" class="app-blank app-blank">
<!--begin::Theme mode setup on page load-->
<script>var defaultThemeMode = "light"; var themeMode; if ( document.documentElement ) { if ( document.documentElement.hasAttribute("data-bs-theme-mode")) { themeMode = document.documentElement.getAttribute("data-bs-theme-mode"); } else { if ( localStorage.getItem("data-bs-theme") !== null ) { themeMode = localStorage.getItem("data-bs-theme"); } else { themeMode = defaultThemeMode; } } if (themeMode === "system") { themeMode = window.matchMedia("(prefers-color-scheme: dark)").matches ? "dark" : "light"; } document.documentElement.setAttribute("data-bs-theme", themeMode); }</script>
<!--end::Theme mode setup on page load-->
<!--begin::Root-->
<div class="d-flex flex-column flex-root" id="kt_app_root">
	<!--begin::Authentication - Sign-in -->
	<div class="d-flex flex-column flex-lg-row flex-column-fluid">
		<!--begin::Aside-->
		<div class="d-flex flex-lg-row-fluid w-lg-50 bgi-size-cover bgi-position-center" style="background-image: url(assets/media/misc/auth-bg.png)">
			<!--begin::Content-->
			<div class="d-flex flex-column flex-center p-6 p-lg-10 w-100">
				<!--begin::Logo-->
				<a href="./main.do" class="mb-0 mb-lg-20">
					<img alt="Logo" src="image/naverLogo.svg" class="h-40px h-lg-50px" />
				</a>
				<!--end::Logo-->
				<!--begin::Image-->
				<img class="d-none d-lg-block mx-auto w-300px w-lg-75 w-xl-500px mb-10 mb-lg-20" src="image/naverworks.png" alt="" />
				<!--end::Image-->
				<!--begin::Title-->
				<h1 class="d-none d-lg-block text-white fs-2qx fw-bold text-center mb-7">출근하세요 빨리...</h1>
				<!--end::Title-->
				<!--begin::Text-->
				<div class="d-none d-lg-block text-white fs-base text-center">
					여기에는<br>
					대충<br>
					설명이<br>
					들어가요!
				</div>
				<!--end::Text-->
			</div>
			<!--end::Content-->
		</div>
		<!--begin::Aside-->
		<!--begin::Body-->
		<div class="d-flex flex-column flex-lg-row-fluid w-lg-50 p-10">
			<!--begin::Form-->
			<div class="d-flex flex-center flex-column flex-lg-row-fluid">
				<!--begin::Wrapper-->
				<div class="w-lg-500px p-10">
					<!--begin::Form-->
					<form class="form w-100" novalidate="novalidate" id="kt_sign_in_form" data-kt-redirect-url="index.html" action="#">
						<!--begin::Heading-->
						<div class="text-center mb-11">
							<!--begin::Title-->
							<h1 class="text-gray-900 fw-bolder mb-3">로그인</h1>
							<!--end::Title-->
							<!--begin::Subtitle-->
							<div class="text-gray-500 fw-semibold fs-6">소셜 로그인</div>
							<!--end::Subtitle=-->
						</div>
						<!--begin::Heading-->
						<!--begin::Login options-->
						<div class="row g-3 mb-9">
							<!--begin::Col-->
							<div class="col-md-6">
								<!--begin::Google link=-->
								<a href="#" class="btn btn-flex btn-outline btn-text-gray-700 btn-active-color-primary bg-state-light flex-center text-nowrap w-100">
								<img alt="Logo" src="assets/media/svg/brand-logos/google-icon.svg" class="h-15px me-3" />Google 계정으로 로그인</a>
								<!--end::Google link=-->
							</div>
							<!--end::Col-->
							<!--begin::Col-->
							<div class="col-md-6">
								<!--begin::Google link=-->
								<a href="#" class="btn btn-flex btn-outline btn-text-gray-700 btn-active-color-primary bg-state-light flex-center text-nowrap w-100">
								<img alt="Logo" src="image/miniNaver.png" class="theme-light-show h-15px me-3" />Naver 계정으로 로그인</a>
								<!--end::Google link=-->
							</div>
							<!--end::Col-->
						</div>
						<!--end::Login options-->
						<!--begin::Separator-->
						<div class="separator separator-content my-14">
							<span class="w-200px text-gray-500 fw-semibold fs-7">HCM 계정로그인</span>
						</div>
						<!--end::Separator-->
						<!--begin::Input group=-->
						<div class="fv-row mb-8">
							<!--begin::Email-->
							<input type="text" placeholder="아이디" name="id" autocomplete="off" class="form-control bg-transparent" />
							<!--end::Email-->
						</div>
						<!--end::Input group=-->
						<div class="fv-row mb-3">
							<!--begin::Password-->
							<input type="password" placeholder="비밀번호" name="password" autocomplete="off" class="form-control bg-transparent" />
							<!--end::Password-->
						</div>
						<!--end::Input group=-->
						<!--begin::Wrapper-->
						<div class="d-flex flex-stack flex-wrap gap-3 fs-base fw-semibold mb-8">
							<div></div>
							<!--begin::Link-->
							<a href="authentication/layouts/corporate/reset-password.html" class="link-primary">비밀번호찾기</a>
							<!--end::Link-->
						</div>
						<!--end::Wrapper-->
						<!--begin::Submit button-->
						<div class="d-grid mb-10">
							<button type="submit" id="kt_sign_in_submit" class="btn btn-primary">
								<!--begin::Indicator label-->
								<span class="indicator-label">로그인</span>
								<!--end::Indicator label-->
								<!--begin::Indicator progress-->
								<span class="indicator-progress">로그인중... 
								<span class="spinner-border spinner-border-sm align-middle ms-2"></span></span>
								<!--end::Indicator progress-->
							</button>
						</div>
						<!--end::Submit button-->
					</form>
					<!--end::Form-->
				</div>
				<!--end::Wrapper-->
			</div>
			<!--end::Form-->
			<!--begin::Footer-->
			<div class="d-flex flex-center flex-wrap px-5">
			</div>
			<!--end::Footer-->
		</div>
		<!--end::Body-->
	</div>
	<!--end::Authentication - Sign-in-->
</div>
	
</body>
<!--end::Body-->

<!--begin::Javascript-->
<script>var hostUrl = "assets/";</script>
<!--begin::Global Javascript Bundle(mandatory for all pages)-->
<script src="assets/plugins/global/plugins.bundle.js"></script>
<script src="assets/js/scripts.bundle.js"></script>
<!--end::Global Javascript Bundle-->
<!--begin::Custom Javascript(used for this page only)-->
<script src="assets/js/custom/authentication/sign-in/general.js"></script>
<!--end::Custom Javascript-->
<!--end::Javascript-->

</html>