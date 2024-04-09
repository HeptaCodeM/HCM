<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	
	<meta name="description" content="The most advanced Bootstrap Admin Theme on Bootstrap Market trusted by over 4,000 beginners and professionals. Multi-demo, Dark Mode, RTL support. Grab your copy now and get life-time updates for free." />
	<meta name="keywords" content="keen, bootstrap, bootstrap 5, bootstrap 4, admin themes, web design, figma, web development, free templates, free admin themes, bootstrap theme, bootstrap template, bootstrap dashboard, bootstrap dak mode, bootstrap button, bootstrap datepicker, bootstrap timepicker, fullcalendar, datatables, flaticon" />
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<!-- cache 삭제 -->
	<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate">
	<meta http-equiv="Pragma" content="no-cache">
	<meta http-equiv="Expires" content="0">

	<link rel="canonical" href="https://preview.keenthemes.com/keen" />
	<link rel="shortcut icon" href="/image/logoMini-removebg-preview.png" />
	<!--begin::Fonts(mandatory for all pages)-->
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Inter:300,400,500,600,700" />
	<!--end::Fonts-->
	<!--begin::Global Stylesheets Bundle(mandatory for all pages)-->
	<link href="/assets/plugins/global/plugins.bundle.css" rel="stylesheet" type="text/css" />
	<link href="/assets/css/style.bundle.css" rel="stylesheet" type="text/css" />
	<!--end::Global Stylesheets Bundle-->
	<style type="text/css">
		.fv-row {display: flex;}
		.fv-row>div { width:20%; font-weight:700; line-height: 43px; }
		.fv-row.auth{display: none;}
		.fv-row.auth>div{ line-height:65px; }
		.fv-row.auth>input{ width:40%; }
		.fv-row.auth>button { width:35%; margin-left:5%; }
		
		@media (max-width: 990px) {
			.auth-num { width: 61px !important; }
		}
	</style>

	<title>비밀번호 찾기</title>
	<script src="/js/common/common.js" type="text/javascript"></script>
	<script src="/js/login/login.js" type="text/javascript"></script>
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
		<div class="d-flex flex-lg-row-fluid w-lg-50 bgi-size-cover bgi-position-center" style="background-image: url(/assets/media/misc/auth-bg.png)">
			<!--begin::Content-->
			<div class="d-flex flex-column flex-center p-6 p-lg-10 w-100" style="background-color: #344672;">
				<!--begin::Image-->
				<img class="d-none d-lg-block mx-auto w-300px w-lg-75 w-xl-500px mb-10" style="margin-top: 32.5px;" src="/image/logo-removebg-preview.png" alt="" />
				<!--end::Image-->
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
					<form class="form w-100" method="post" name="initPwdForm" id="loginForm">
						<!--begin::Heading-->
						<div class="text-center mb-11">
							<!--begin::Title-->
							<h1 class="text-gray-900 fw-bolder mb-3">비밀번호 초기화</h1>
							<!--end::Title-->
						</div>
						<!--begin::Heading-->
						<!--begin::Separator-->
						<div class="separator separator-content my-14">
							<span class="w-200px text-gray-500 fw-semibold fs-7">기본정보 입력</span>
						</div>
						<!--end::Separator-->

						<!--begin::Input group=-->
						<div class="fv-row mb-8">
							<div>사번</div>
							<input type="text" placeholder="사번 입력" name="empl_id" id="empl_id" autocomplete="off" class="form-control"/>
						</div>
						<div class="fv-row mb-8">
							<div>성명</div>
							<input type="text" placeholder="성명 입력" name="empl_name" id="empl_name" autocomplete="off" class="form-control"/>
						</div>
						<div class="fv-row mb-8">
							<div>이메일</div>
							<input type="text" placeholder="이메일 입력" name="empl_email" id="empl_email" autocomplete="off" class="form-control"/>
						</div>
						<!--end::Input group=-->

						<div class="fv-row mb-8 auth" id="auth-area">
							<div class="auth-num" style="width:73px;">인증번호</div>
							<input type="text" placeholder="인증번호 입력" name="authnum" id="authnum" autocomplete="off" class="form-control" maxlength="4" />
							<button type="button" class="btn btn-primary" onclick="initPwdAuthNumCheck()">인증번호<br>확인</button>
						</div>
						<div class="fv-row mb-8" style="display:none;" id="auth-area-time">
							<div>제한시간</div>
							<div id="time"></div>
						</div>
						<!--end::Wrapper-->
						<!--begin::Submit button-->
						<div class="d-grid mb-4" id="send-area">
							<button type="button" class="btn btn-primary" onclick="initPwdAuthNumSend()">
								<!--begin::Indicator label-->
								<span class="indicator-label">인증번호 발송</span>
								<!--end::Indicator label-->
								
							</button>
						</div>
						<div class="d-grid mb-10">
							<button type="button" class="btn btn-success" onclick="location.href='/login/login.do';">
								<!--begin::Indicator label-->
								<span class="indicator-label">돌아가기</span>
								<!--end::Indicator label-->
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
<script>var hostUrl = "/assets/";</script>
<!--begin::Global Javascript Bundle(mandatory for all pages)-->
<script src="/assets/plugins/global/plugins.bundle.js"></script>
<script src="/assets/js/scripts.bundle.js"></script>
<!--end::Global Javascript Bundle-->
<!--end::Javascript-->

</html>