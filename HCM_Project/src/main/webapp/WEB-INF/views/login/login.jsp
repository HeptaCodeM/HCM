<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<link rel="canonical" href="https://preview.keenthemes.com/keen" />
	<link rel="shortcut icon" href="/image/logoMini-removebg-preview.png" />
	<!--begin::Fonts(mandatory for all pages)-->
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Inter:300,400,500,600,700" />
	<!--end::Fonts-->
	<!--begin::Vendor Stylesheets(used for this page only)-->
	<link href="/assets/plugins/custom/datatables/datatables.bundle.css" rel="stylesheet" type="text/css" />
	<!--end::Vendor Stylesheets-->
	<!--begin::Global Stylesheets Bundle(mandatory for all pages)-->
	<link href="/assets/plugins/global/plugins.bundle.css" rel="stylesheet" type="text/css" />
	<link href="/assets/css/style.bundle.css" rel="stylesheet" type="text/css" />
	<!--end::Global Stylesheets Bundle-->
	
	<!-- 사용CSS오버라이딩 -->
	<link href="/css/common.css" rel="stylesheet" type="text/css" />
	<!-- 사용CSS오버라이딩 -->
	
	
	<!--begin::Global Javascript Bundle(mandatory for all pages)-->
	<script src="/assets/plugins/global/plugins.bundle.js"></script>
	<script src="/assets/js/scripts.bundle.js"></script>
	<!--end::Global Javascript Bundle-->
	
	<!--begin::Javascript-->
	<script>var hostUrl = "/assets/";</script>
	<!--begin::Vendors Javascript(used for this page only)-->
	<!-- <script src="/assets/plugins/custom/fullcalendar/fullcalendar.bundle.js"></script> -->
	<script src="https://cdn.amcharts.com/lib/5/index.js"></script>
	<script src="https://cdn.amcharts.com/lib/5/xy.js"></script>
	<script src="https://cdn.amcharts.com/lib/5/percent.js"></script>
	<script src="https://cdn.amcharts.com/lib/5/radar.js"></script>
	<script src="https://cdn.amcharts.com/lib/5/themes/Animated.js"></script>
	<script src="https://cdn.amcharts.com/lib/5/map.js"></script>
	<script src="https://cdn.amcharts.com/lib/5/geodata/worldLow.js"></script>
	<script src="https://cdn.amcharts.com/lib/5/geodata/continentsLow.js"></script>
	<script src="https://cdn.amcharts.com/lib/5/geodata/usaLow.js"></script>
	<script src="https://cdn.amcharts.com/lib/5/geodata/worldTimeZonesLow.js"></script>
	<script src="https://cdn.amcharts.com/lib/5/geodata/worldTimeZoneAreasLow.js"></script>
	<script src="/assets/plugins/custom/datatables/datatables.bundle.js"></script>
	<!--end::Vendors Javascript-->
	<!--begin::Custom Javascript(used for this page only)-->
	<script src="/assets/js/widgets.bundle.js"></script>
	<script src="/assets/js/custom/utilities/modals/upgrade-plan.js"></script>
	<script src="/assets/js/custom/utilities/modals/create-campaign.js"></script>
	<script src="/assets/js/custom/utilities/modals/users-search.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
	<script src="/js/common/common.js"></script>
	<!--end::Custom Javascript-->
	<!--end::Javascript-->

	<title>HCM GroupWare</title>
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
					<form class="form w-100" method="post" name="loginForm" id="loginForm" action="/login">
						<!--begin::Heading-->
						<div class="text-center mb-11">
							<!--begin::Title-->
							<h1 class="text-gray-900 fw-bolder mb-3">
								<c:if test="${mobile eq 'Y'}">
									Mobile
								</c:if>
								<c:if test="${mobile eq 'N'}">
									PC
								</c:if>
								로그인
							</h1>
							<!--end::Title-->
							<div>
								<h2 style="color:red;"><c:out value="${error}" /></h2>
								<h2 style="color:red;"><c:out value="${logout}" /></h2>
							</div>
						</div>
						<!--begin::Heading-->
						<!--begin::Separator-->
						<div class="separator separator-content my-14">
							<span class="w-200px text-gray-500 fw-semibold fs-7">HCM 계정로그인</span>
						</div>
						<!--end::Separator-->
						<!--begin::Input group=-->
						<div class="fv-row mb-8">
							<!--begin::Email-->
							<!-- <input type="text" placeholder="아이디" name="username" autocomplete="off" class="form-control bg-transparent" value="20220101" enc="on" /> -->
							<input type="text" placeholder="아이디" name="username" autocomplete="off" class="form-control bg-transparent" value="${loginMap.id}" />
							<!--end::Email-->
						</div>
						<!--end::Input group=-->
						<div class="fv-row mb-3">
							<!--begin::Password-->
							<!-- <input type="password" placeholder="비밀번호" name="password" autocomplete="off" class="form-control bg-transparent" value="20220101" enc="on" /> -->
							<input type="password" placeholder="비밀번호" name="password" autocomplete="off" class="form-control bg-transparent" value="${loginMap.pw}" />
							<!--end::Password-->
						</div>
						<!--end::Input group=-->
						<!--begin::Wrapper-->
						<div class="d-flex flex-stack flex-wrap gap-3 fs-base fw-semibold mb-8">
							<div class="form-check" style="float:left;margin-right:20px;">
							    <input class="form-check-input" type="checkbox" name="autoLogin" id="remember">
							    <label class="form-check-label" for="remember">자동로그인</label>
							</div>

							<!--begin::Link-->
							<a href="/login/restPwd.do" class="link-primary">비밀번호 초기화</a>
							<!--end::Link-->
						</div>
						<!--end::Wrapper-->
						<!--begin::Submit button-->
						<div class="d-grid mb-5">
							<!-- <button type="button" class="btn btn-primary" onclick="doSubmit()"> -->
							<button class="btn btn-primary">
								<!--begin::Indicator label-->
								<span class="indicator-label">로그인</span>
								<!--end::Indicator label-->
							</button>
						</div>
						<!--end::Submit button-->

						<!-- 사이트간 위변조 방지를 위한 CSRF 처리 -->
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
					</form>
					<!--end::Form-->

					<!--begin::Login options-->
					<div class="row g-3 mb-9">
						<!--begin::Col-->
						<div class="d-grid">
							<!--begin::Google link=-->
							<a href="javascript:void(0);" onclick="window.open('${naverSnsUrl}','naverLogin','width=500,height=500,toolbars=no,statebar=no')" class="btn btn-flex btn-outline btn-text-gray-700 btn-active-color-primary bg-state-light flex-center text-nowrap w-100">
							<img alt="Logo" src="/image/miniNaver.png" class="theme-light-show h-15px me-3" />Naver 계정으로 로그인</a>
							<!--end::Google link=-->
						</div>
						<!--end::Col-->
					</div>
					<!--end::Login options-->

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

</html>