<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="canonical" href="https://preview.keenthemes.com/keen" />
<link rel="shortcut icon" href="/assets/media/logos/favicon.ico" />
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
<!--begin::Global Javascript Bundle(mandatory for all pages)-->
<script src="assets/plugins/global/plugins.bundle.js"></script>
<script src="assets/js/scripts.bundle.js"></script>
<!--end::Global Javascript Bundle-->

<!--begin::Javascript-->
<script>var hostUrl = "/assets/";</script>
<!--begin::Vendors Javascript(used for this page only)-->
<script src="assets/plugins/custom/fullcalendar/fullcalendar.bundle.js"></script>
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
<script src="assets/plugins/custom/datatables/datatables.bundle.js"></script>
<!--end::Vendors Javascript-->
<!--begin::Custom Javascript(used for this page only)-->
<script src="assets/js/widgets.bundle.js"></script>
<script src="assets/js/custom/apps/chat/chat.js"></script>
<script src="assets/js/custom/utilities/modals/upgrade-plan.js"></script>
<script src="assets/js/custom/utilities/modals/create-campaign.js"></script>
<script src="assets/js/custom/utilities/modals/users-search.js"></script>
<!--end::Custom Javascript-->
<!--end::Javascript-->
<title>Main//Manu[Include]</title>
</head>
<body>
	<div id="kt_app_body" data-kt-app-layout="dark-sidebar"
		data-kt-app-header-fixed="true" data-kt-app-sidebar-enabled="true"
		data-kt-app-sidebar-fixed="true" data-kt-app-sidebar-hoverable="true"
		data-kt-app-sidebar-push-header="true"
		data-kt-app-sidebar-push-toolbar="true"
		data-kt-app-sidebar-push-footer="true"
		data-kt-app-toolbar-enabled="true" class="app-default">
		<div id="kt_app_header" class="app-header">
			<div
				class="app-container container-fluid d-flex align-items-stretch justify-content-between"
				id="kt_app_header_container">
				<div class="d-flex align-items-center d-lg-none ms-n3 me-2"
					title="Show sidebar menu">
					<div class="btn btn-icon btn-active-color-primary w-35px h-35px"
						id="kt_app_sidebar_mobile_toggle">
						<i class="ki-duotone ki-abstract-14 fs-1"> <span class="path1"></span>
							<span class="path2"></span>
						</i>
					</div>
				</div>
				<div class="d-flex align-items-center flex-grow-1 flex-lg-grow-0">
					<a
						href="file:///C:/Users/kjwkj/Desktop/html/keen_v3.0.6/demo1/index.html"
						class="d-lg-none"> <img alt="HCM" src="image/favicon.ico"
						class="theme-light-show h-30px">
					</a>
				</div>
				<div
					class="d-flex align-items-stretch justify-content-between flex-lg-grow-1"
					id="kt_app_header_wrapper">
					<!--begin::Menu wrapper-->
					<div
						class="app-header-menu app-header-mobile-drawer align-items-stretch"
						data-kt-drawer="true" data-kt-drawer-name="app-header-menu"
						data-kt-drawer-activate="{default: true, lg: false}"
						data-kt-drawer-overlay="true" data-kt-drawer-width="225px"
						data-kt-drawer-direction="end"
						data-kt-drawer-toggle="#kt_app_header_menu_toggle"
						data-kt-swapper="true"
						data-kt-swapper-mode="{default: &#39;append&#39;, lg: &#39;prepend&#39;}"
						data-kt-swapper-parent="{default: &#39;#kt_app_body&#39;, lg: &#39;#kt_app_header_wrapper&#39;}">
						<!--begin::Menu-->
						<div
							class="menu menu-rounded menu-column menu-lg-row my-5 my-lg-0 align-items-stretch fw-semibold px-2 px-lg-0"
							id="kt_app_header_menu" data-kt-menu="true">
							<!--begin:Menu item-->
							<div
								data-kt-menu-trigger="{default: &#39;click&#39;, lg: &#39;hover&#39;}"
								data-kt-menu-placement="bottom-start"
								class="menu-item here menu-here-bg menu-lg-down-accordion me-0 me-lg-2">
								<!--begin:Menu link-->
								<span class="menu-link"> <span class="menu-title">상단메뉴</span>
									<span class="menu-arrow d-lg-none"></span>
								</span>
								<!--end:Menu link-->

							</div>
							<!--begin:Menu item-->
							<div
								data-kt-menu-trigger="{default: &#39;click&#39;, lg: &#39;hover&#39;}"
								data-kt-menu-placement="bottom-start"
								class="menu-item menu-lg-down-accordion me-0 me-lg-2">
								<!--begin:Menu link-->
								<span class="menu-link"> <span class="menu-title">인사관리</span>
									<span class="menu-arrow d-lg-none"></span>
								</span>
								<!--end:Menu link-->
								<!--begin:Menu sub-->
							</div>
							<!--end:Menu item-->
							<!--begin:Menu item-->
							<div
								data-kt-menu-trigger="{default: &#39;click&#39;, lg: &#39;hover&#39;}"
								data-kt-menu-placement="bottom-start"
								class="menu-item menu-lg-down-accordion menu-sub-lg-down-indention me-0 me-lg-2">
								<!--begin:Menu link-->
								<span class="menu-link"> <span class="menu-title">일정관리</span>
									<span class="menu-arrow d-lg-none"></span>
								</span>

							</div>
							<!--end:Menu item-->
							<!--begin:Menu item-->
							<div
								data-kt-menu-trigger="{default: &#39;click&#39;, lg: &#39;hover&#39;}"
								data-kt-menu-placement="bottom-start"
								class="menu-item menu-lg-down-accordion me-0 me-lg-2">
								<!--begin:Menu link-->
								<span class="menu-link"> <span class="menu-title">전자결재</span>
									<span class="menu-arrow d-lg-none"></span>
								</span>
							</div>
							<!--end:Menu item-->
						</div>
						<!--end::Menu-->
					</div>
				</div>
				<!--end::Header wrapper-->
				<div class="app-navbar flex-shrink-0">

					<div class="app-navbar-item ms-1 ms-lg-3">
						<!--begin::Menu toggle-->
						<a href="#"
							class="btn btn-icon btn-custom btn-icon-muted btn-active-light btn-active-color-primary w-35px h-35px w-md-40px h-md-40px"
							data-kt-menu-trigger="{default:'click', lg: 'hover'}"
							data-kt-menu-attach="parent" data-kt-menu-placement="bottom-end">
							<i class="ki-duotone ki-night-day theme-light-show fs-1"> <span
								class="path1"></span> <span class="path2"></span> <span
								class="path3"></span> <span class="path4"></span> <span
								class="path5"></span> <span class="path6"></span> <span
								class="path7"></span> <span class="path8"></span> <span
								class="path9"></span> <span class="path10"></span>
						</i> <i class="ki-duotone ki-moon theme-dark-show fs-1"> <span
								class="path1"></span> <span class="path2"></span>
						</i>
						</a>
						<!--begin::Menu toggle-->
						<!--begin::Menu-->
						<div
							class="menu menu-sub menu-sub-dropdown menu-column menu-rounded menu-title-gray-700 menu-icon-gray-500 menu-active-bg menu-state-color fw-semibold py-4 fs-base w-150px"
							data-kt-menu="true" data-kt-element="theme-mode-menu" style="">
							<!--begin::Menu item-->
							<div class="menu-item px-3 my-0">
								<a href="#" class="menu-link px-3 py-2 active"
									data-kt-element="mode" data-kt-value="light"> <span
									class="menu-icon" data-kt-element="icon"> <i
										class="ki-duotone ki-night-day fs-2"> <span class="path1"></span>
											<span class="path2"></span> <span class="path3"></span> <span
											class="path4"></span> <span class="path5"></span> <span
											class="path6"></span> <span class="path7"></span> <span
											class="path8"></span> <span class="path9"></span> <span
											class="path10"></span>
									</i>
								</span> <span class="menu-title">Light</span>
								</a>
							</div>
							<!--end::Menu item-->
							<!--begin::Menu item-->
							<div class="menu-item px-3 my-0">
								<a href="#" class="menu-link px-3 py-2" data-kt-element="mode"
									data-kt-value="dark"> <span class="menu-icon"
									data-kt-element="icon"> <i
										class="ki-duotone ki-moon fs-2"> <span class="path1"></span>
											<span class="path2"></span>
									</i>
								</span> <span class="menu-title">Dark</span>
								</a>
							</div>
							<!--end::Menu item-->
							<!--begin::Menu item-->
							<div class="menu-item px-3 my-0">
								<a href="#" class="menu-link px-3 py-2" data-kt-element="mode"
									data-kt-value="system"> <span class="menu-icon"
									data-kt-element="icon"> <i
										class="ki-duotone ki-screen fs-2"> <span class="path1"></span>
											<span class="path2"></span> <span class="path3"></span> <span
											class="path4"></span>
									</i>
								</span> <span class="menu-title">System</span>
								</a>
							</div>
							<!--end::Menu item-->
						</div>
						<!--end::Menu-->
					</div>

					<!--begin::User menu-->
					<div class="app-navbar-item ms-2 ms-1 ms-lg-3"
						id="kt_header_user_menu_toggle">
						<!--begin::Menu wrapper-->
						<div class="cursor-pointer symbol symbol-35px symbol-md-40px"
							data-kt-menu-trigger="{default: 'click', lg: 'hover'}"
							data-kt-menu-attach="parent" data-kt-menu-placement="bottom-end">
							<img src="image/300-3.jpg" alt="user">
						</div>
					</div>
					<!--end::User menu-->
				</div>
			</div>
			<!--end::Header container-->
		</div>
		<!--end::Header-->
		
		<div class="app-wrapper flex-column flex-row-fluid">
			<div class="d-flex flex-column flex-column-fluid app-toolbar py-3 py-lg-6">
				<div id="kt_app_toolbar_container" class="app-container container-fluid d-flex flex-stack">
					<!--begin::Page title-->
					<div class="page-title d-flex flex-column justify-content-center flex-wrap me-3">
						<!--begin::Title-->
						<h1 class="page-heading d-flex text-gray-900 fw-bold fs-3 flex-column justify-content-center my-0">제목이 여기에 들어가요!</h1>
						<!--end::Title-->
					</div>
					<!--end::Page title-->
				</div>		
			</div>
			
			<div class="app-content flex-column-fluid">
				<!-- 내용 시작 -->
				<div id="kt_app_content" class="app-content flex-column-fluid">
					<div class="app-container container-fluid">
						<div class="card card-flush h-md-50 mb-xl-10">
							<div class="card-header pt-5">
								<h3 class="card-title text-gray-800 fw-bold">소제목? 들어갑니다</h3>
							</div>
							<div class="separator separator-dashed my-3"></div>	
							<div class="card-body pt-5">
								대충 여기에 내용이 들어가요!<br>
								대충 여기에 내용이 들어가요!<br>
								대충 여기에 내용이 들어가요!<br>
							</div>
						</div>
					</div>
				</div>
				<!-- 내용 끝 -->
			</div>
		</div>

		<!--begin::Wrapper-->
		<div id="kt_app_sidebar" class="app-sidebar flex-column"
			data-kt-drawer="true" data-kt-drawer-name="app-sidebar"
			data-kt-drawer-activate="{default: true, lg: false}"
			data-kt-drawer-overlay="true" data-kt-drawer-width="225px"
			data-kt-drawer-direction="start"
			data-kt-drawer-toggle="#kt_app_sidebar_mobile_toggle">
			<!--begin::Logo-->
			<div class="app-sidebar-logo px-6" id="kt_app_sidebar_logo">
				<!--begin::Logo image-->
				<a href="#"> <img alt="HCM" src="image/favicon.ico"
					class="h-30px app-sidebar-logo-default">HCM
				</a>
				<!--end::Logo image-->
				<!--begin::Sidebar toggle-->
				<!-- <div id="kt_app_sidebar_toggle"
					class="app-sidebar-toggle btn btn-icon btn-sm h-30px w-30px rotate"
					data-kt-toggle="true" data-kt-toggle-state="active"
					data-kt-toggle-target="body"
					data-kt-toggle-name="app-sidebar-minimize">
					<i class="ki-duotone ki-double-left fs-2 rotate-180"> <span
						class="path1"></span> <span class="path2"></span>
					</i>
				</div> -->
				<!--end::Sidebar toggle-->
			</div>
			<!--end::Logo-->
			<!--begin::sidebar menu-->
			<div class="app-sidebar-menu overflow-hidden flex-column-fluid">
				<!--begin::Menu-->
				<div class="menu menu-column menu-rounded menu-sub-indention fw-semibold" id="#kt_app_sidebar_menu" data-kt-menu="true" data-kt-menu-expand="false">
					<!--begin:Menu item-->
					<div data-kt-menu-trigger="click"
						class="menu-item pt-5 menu-item menu-accordion">
						<!--begin:Menu content-->
						<span class="menu-link"> <span class="menu-icon"> <i
								class="ki-duotone ki-address-book fs-2"> <span class="path1"></span>
									<span class="path2"></span> <span class="path3"></span>
							</i>
						</span> <span class="menu-heading fw-bold text-uppercase fs-7 menu-title">인사관리</span>
							<span class="menu-arrow"></span>
						</span>
						<!--begin:Menu item-->
						<div class="menu-sub menu-sub-accordion" kt-hidden-height="242"
							style="display: none; overflow: hidden;">
							<div data-kt-menu-trigger="click"
								class="menu-item menu-accordion">
								<!--begin:Menu link-->
								<span class="menu-link"> <span class="menu-icon">
										<i class="ki-duotone ki-address-book fs-2"> <span
											class="path1"></span> <span class="path2"></span> <span
											class="path3"></span>
									</i>
								</span> <span class="menu-title">기능대분류 1</span> <span
									class="menu-arrow"></span>
								</span>
								<!--end:Menu link-->
								<!--begin:Menu sub-->
								<div class="menu-sub menu-sub-accordion" kt-hidden-height="242"
									style="display: none; overflow: hidden;">
									<!--begin:Menu item-->
									<div class="menu-item">
										<!--begin:Menu link-->
										<a class="menu-link"
											href="file:///C:/Users/kjwkj/Desktop/html/keen_v3.0.6/demo1/pages/user-profile/overview.html">
											<span class="menu-bullet"> <span
												class="bullet bullet-dot"></span>
										</span> <span class="menu-title">기능소분류 1</span>
										</a>
										<!--end:Menu link-->
									</div>
									<!--end:Menu item-->
									<!--begin:Menu item-->
									<div class="menu-item">
										<!--begin:Menu link-->
										<a class="menu-link"
											href="file:///C:/Users/kjwkj/Desktop/html/keen_v3.0.6/demo1/pages/user-profile/projects.html">
											<span class="menu-bullet"> <span
												class="bullet bullet-dot"></span>
										</span> <span class="menu-title">기능소분류 2</span>
										</a>
										<!--end:Menu link-->
									</div>
									<!--end:Menu item-->
								</div>
								<!--end:Menu sub-->
							</div>
						</div>
					</div>
					<!--end::Menu-->
				</div>
				<!--end::Menu wrapper-->
			</div>
		</div>
	</div>
</body>
</html>