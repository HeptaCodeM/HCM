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
<!--begin::Global Javascript Bundle(mandatory for all pages)-->
<script src="assets/plugins/global/plugins.bundle.js"></script>
<script src="assets/js/scripts.bundle.js"></script>
<!--end::Global Javascript Bundle-->
<script>// Frame-busting to prevent site from being loaded within a frame without permission (click-jacking) if (window.top != window.self) { window.top.location.replace(window.self.location.href); }</script>

<title>MAIN TEST PAGE</title>
</head>

<body id="kt_app_body" data-kt-app-layout="dark-sidebar" data-kt-app-header-fixed="true" data-kt-app-sidebar-enabled="true" data-kt-app-sidebar-fixed="true" data-kt-app-sidebar-hoverable="true" data-kt-app-sidebar-push-header="true" data-kt-app-sidebar-push-toolbar="true" data-kt-app-sidebar-push-footer="true" data-kt-app-toolbar-enabled="true" class="app-default">
		<script>var defaultThemeMode = "light"; var themeMode; if ( document.documentElement ) { if ( document.documentElement.hasAttribute("data-bs-theme-mode")) { themeMode = document.documentElement.getAttribute("data-bs-theme-mode"); } else { if ( localStorage.getItem("data-bs-theme") !== null ) { themeMode = localStorage.getItem("data-bs-theme"); } else { themeMode = defaultThemeMode; } } if (themeMode === "system") { themeMode = window.matchMedia("(prefers-color-scheme: dark)").matches ? "dark" : "light"; } document.documentElement.setAttribute("data-bs-theme", themeMode); }</script>
		<div class="d-flex flex-column flex-root app-root" id="kt_app_root">
			<div class="app-page flex-column flex-column-fluid" id="kt_app_page">
				<div id="kt_app_header" class="app-header">
					<div class="app-container container-fluid d-flex align-items-stretch justify-content-between" id="kt_app_header_container">
						<div class="d-flex align-items-center d-lg-none ms-n3 me-2" title="Show sidebar menu">
							<div class="btn btn-icon btn-active-color-primary w-35px h-35px" id="kt_app_sidebar_mobile_toggle">
								<i class="ki-duotone ki-abstract-14 fs-1">
									<span class="path1"></span>
									<span class="path2"></span>
								</i>
							</div>
						</div>
						<div class="d-flex align-items-center flex-grow-1 flex-lg-grow-0">
							<a href="file:///C:/Users/kjwkj/Desktop/html/keen_v3.0.6/demo1/index.html" class="d-lg-none">
								<img alt="HCM" src="image/favicon.ico" class="theme-light-show h-30px">
							</a>
						</div>
						<div class="d-flex align-items-stretch justify-content-between flex-lg-grow-1" id="kt_app_header_wrapper">
							<!--begin::Menu wrapper-->
							<div class="app-header-menu app-header-mobile-drawer align-items-stretch" data-kt-drawer="true" data-kt-drawer-name="app-header-menu" data-kt-drawer-activate="{default: true, lg: false}" data-kt-drawer-overlay="true" data-kt-drawer-width="225px" data-kt-drawer-direction="end" data-kt-drawer-toggle="#kt_app_header_menu_toggle" data-kt-swapper="true" data-kt-swapper-mode="{default: &#39;append&#39;, lg: &#39;prepend&#39;}" data-kt-swapper-parent="{default: &#39;#kt_app_body&#39;, lg: &#39;#kt_app_header_wrapper&#39;}">
								<!--begin::Menu-->
								<div class="menu menu-rounded menu-column menu-lg-row my-5 my-lg-0 align-items-stretch fw-semibold px-2 px-lg-0" id="kt_app_header_menu" data-kt-menu="true">
									<!--begin:Menu item-->
									<div data-kt-menu-trigger="{default: &#39;click&#39;, lg: &#39;hover&#39;}" data-kt-menu-placement="bottom-start" class="menu-item here menu-here-bg menu-lg-down-accordion me-0 me-lg-2">
										<!--begin:Menu link-->
										<span class="menu-link">
											<span class="menu-title">상단메뉴</span>
											<span class="menu-arrow d-lg-none"></span>
										</span>
										<!--end:Menu link-->
										
									</div>
									<!--begin:Menu item-->
									<div data-kt-menu-trigger="{default: &#39;click&#39;, lg: &#39;hover&#39;}" data-kt-menu-placement="bottom-start" class="menu-item menu-lg-down-accordion me-0 me-lg-2">
										<!--begin:Menu link-->
										<span class="menu-link">
											<span class="menu-title">인사관리</span>
											<span class="menu-arrow d-lg-none"></span>
										</span>
										<!--end:Menu link-->
										<!--begin:Menu sub-->
									</div>
									<!--end:Menu item-->
									<!--begin:Menu item-->
									<div data-kt-menu-trigger="{default: &#39;click&#39;, lg: &#39;hover&#39;}" data-kt-menu-placement="bottom-start" class="menu-item menu-lg-down-accordion menu-sub-lg-down-indention me-0 me-lg-2">
										<!--begin:Menu link-->
										<span class="menu-link">
											<span class="menu-title">일정관리</span>
											<span class="menu-arrow d-lg-none"></span>
										</span>
										
									</div>
									<!--end:Menu item-->
									<!--begin:Menu item-->
									<div data-kt-menu-trigger="{default: &#39;click&#39;, lg: &#39;hover&#39;}" data-kt-menu-placement="bottom-start" class="menu-item menu-lg-down-accordion me-0 me-lg-2">
										<!--begin:Menu link-->
										<span class="menu-link">
											<span class="menu-title" id="signTree">전자결재</span>
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
									<a href="#" class="btn btn-icon btn-custom btn-icon-muted btn-active-light btn-active-color-primary w-35px h-35px w-md-40px h-md-40px" data-kt-menu-trigger="{default:'click', lg: 'hover'}" data-kt-menu-attach="parent" data-kt-menu-placement="bottom-end">
										<i class="ki-duotone ki-night-day theme-light-show fs-1">
											<span class="path1"></span>
											<span class="path2"></span>
											<span class="path3"></span>
											<span class="path4"></span>
											<span class="path5"></span>
											<span class="path6"></span>
											<span class="path7"></span>
											<span class="path8"></span>
											<span class="path9"></span>
											<span class="path10"></span>
										</i>
										<i class="ki-duotone ki-moon theme-dark-show fs-1">
											<span class="path1"></span>
											<span class="path2"></span>
										</i>
									</a>
									<!--begin::Menu toggle-->
									<!--begin::Menu-->
									<div class="menu menu-sub menu-sub-dropdown menu-column menu-rounded menu-title-gray-700 menu-icon-gray-500 menu-active-bg menu-state-color fw-semibold py-4 fs-base w-150px" data-kt-menu="true" data-kt-element="theme-mode-menu" style="">
										<!--begin::Menu item-->
										<div class="menu-item px-3 my-0">
											<a href="#" class="menu-link px-3 py-2 active" data-kt-element="mode" data-kt-value="light">
												<span class="menu-icon" data-kt-element="icon">
													<i class="ki-duotone ki-night-day fs-2">
														<span class="path1"></span>
														<span class="path2"></span>
														<span class="path3"></span>
														<span class="path4"></span>
														<span class="path5"></span>
														<span class="path6"></span>
														<span class="path7"></span>
														<span class="path8"></span>
														<span class="path9"></span>
														<span class="path10"></span>
													</i>
												</span>
												<span class="menu-title">Light</span>
											</a>
										</div>
										<!--end::Menu item-->
										<!--begin::Menu item-->
										<div class="menu-item px-3 my-0">
											<a href="#" class="menu-link px-3 py-2" data-kt-element="mode" data-kt-value="dark">
												<span class="menu-icon" data-kt-element="icon">
													<i class="ki-duotone ki-moon fs-2">
														<span class="path1"></span>
														<span class="path2"></span>
													</i>
												</span>
												<span class="menu-title">Dark</span>
											</a>
										</div>
										<!--end::Menu item-->
										<!--begin::Menu item-->
										<div class="menu-item px-3 my-0">
											<a href="#" class="menu-link px-3 py-2" data-kt-element="mode" data-kt-value="system">
												<span class="menu-icon" data-kt-element="icon">
													<i class="ki-duotone ki-screen fs-2">
														<span class="path1"></span>
														<span class="path2"></span>
														<span class="path3"></span>
														<span class="path4"></span>
													</i>
												</span>
												<span class="menu-title">System</span>
											</a>
										</div>
										<!--end::Menu item-->
									</div>
									<!--end::Menu-->
								</div>
						
								<!--begin::User menu-->
								<div class="app-navbar-item ms-2 ms-1 ms-lg-3" id="kt_header_user_menu_toggle">
									<!--begin::Menu wrapper-->
									<div class="cursor-pointer symbol symbol-35px symbol-md-40px" data-kt-menu-trigger="{default: 'click', lg: 'hover'}" data-kt-menu-attach="parent" data-kt-menu-placement="bottom-end">
										<img src="image/300-3.jpg" alt="user">
									</div>
								</div>
								<!--end::User menu-->
						</div>
					</div>
					<!--end::Header container-->
				</div>
				<!--end::Header-->
				<!--begin::Wrapper-->
				<div class="app-wrapper flex-column flex-row-fluid" id="kt_app_wrapper">
					<!--begin::Sidebar-->
					<div id="kt_app_sidebar" class="app-sidebar flex-column" data-kt-drawer="true" data-kt-drawer-name="app-sidebar" data-kt-drawer-activate="{default: true, lg: false}" data-kt-drawer-overlay="true" data-kt-drawer-width="225px" data-kt-drawer-direction="start" data-kt-drawer-toggle="#kt_app_sidebar_mobile_toggle">
						<!--begin::Logo-->
						<div class="app-sidebar-logo px-6" id="kt_app_sidebar_logo">
							<!--begin::Logo image-->
							<a href="#">
								<img alt="HCM" src="image/favicon.ico" class="h-30px app-sidebar-logo-default">HCM
							</a>
							<!--end::Logo image-->
							<!--begin::Sidebar toggle-->
							<div id="kt_app_sidebar_toggle" class="app-sidebar-toggle btn btn-icon btn-sm h-30px w-30px rotate" data-kt-toggle="true" data-kt-toggle-state="active" data-kt-toggle-target="body" data-kt-toggle-name="app-sidebar-minimize">
								<i class="ki-duotone ki-double-left fs-2 rotate-180">
									<span class="path1"></span>
									<span class="path2"></span>
								</i>
							</div>
							<!--end::Sidebar toggle-->
						</div>
						<!--end::Logo-->
						<!--begin::sidebar menu-->
						<div class="app-sidebar-menu overflow-hidden flex-column-fluid">
							<!--begin::Menu wrapper-->
							<div id="kt_app_sidebar_menu_wrapper" class="app-sidebar-wrapper">
								<!--begin::Scroll wrapper-->
								<div id="kt_app_sidebar_menu_scroll" class="hover-scroll-y my-5 mx-3" data-kt-scroll="true" data-kt-scroll-activate="true" data-kt-scroll-height="auto" data-kt-scroll-dependencies="#kt_app_sidebar_logo, #kt_app_sidebar_footer" data-kt-scroll-wrappers="#kt_app_sidebar_menu" data-kt-scroll-offset="5px" data-kt-scroll-save-state="true" style="height: 1097px;">
									<!--begin::Menu-->
									<div class="menu menu-column menu-rounded menu-sub-indention fw-semibold" id="#kt_app_sidebar_menu" data-kt-menu="true" data-kt-menu-expand="false">
										<!--begin:Menu item-->
										
										<!--end:Menu item-->
										<!--begin:Menu item-->
										<div data-kt-menu-trigger="click" class="menu-item pt-5 menu-item menu-accordion">
											<!--begin:Menu content-->
											<span class="menu-link">
												<span class="menu-icon">
														<i class="ki-duotone ki-address-book fs-2">
															<span class="path1"></span>
															<span class="path2"></span>
															<span class="path3"></span>
														</i>
													</span>
													<span class="menu-heading fw-bold text-uppercase fs-7 menu-title">인사관리</span>
													<span class="menu-arrow"></span>
											</span>
											<!--end:Menu content-->
										<!--end:Menu item-->
										<!--begin:Menu item-->
										<div class="menu-sub menu-sub-accordion" kt-hidden-height="242" style="display: none; overflow: hidden;">
										<div  data-kt-menu-trigger="click" class="menu-item menu-accordion">
											<!--begin:Menu link-->
											<span class="menu-link">
												<span class="menu-icon">
													<i class="ki-duotone ki-address-book fs-2">
														<span class="path1"></span>
														<span class="path2"></span>
														<span class="path3"></span>
													</i>
												</span>
												<span class="menu-title">기능대분류 1</span>
												<span class="menu-arrow"></span>
											</span>
											<!--end:Menu link-->
											<!--begin:Menu sub-->
											<div class="menu-sub menu-sub-accordion" kt-hidden-height="242" style="display: none; overflow: hidden;">
												<!--begin:Menu item-->
												<div class="menu-item">
													<!--begin:Menu link-->
													<a class="menu-link" href="file:///C:/Users/kjwkj/Desktop/html/keen_v3.0.6/demo1/pages/user-profile/overview.html">
														<span class="menu-bullet">
															<span class="bullet bullet-dot"></span>
														</span>
														<span class="menu-title">기능소분류 1</span>
													</a>
													<!--end:Menu link-->
												</div>
												<!--end:Menu item-->
												<!--begin:Menu item-->
												<div class="menu-item">
													<!--begin:Menu link-->
													<a class="menu-link" href="file:///C:/Users/kjwkj/Desktop/html/keen_v3.0.6/demo1/pages/user-profile/projects.html">
														<span class="menu-bullet">
															<span class="bullet bullet-dot"></span>
														</span>
														<span class="menu-title">기능소분류 2</span>
													</a>
													<!--end:Menu link-->
												</div>
												<!--end:Menu item-->
												<!--begin:Menu item-->
												<div class="menu-item">
													<!--begin:Menu link-->
													<a class="menu-link" href="file:///C:/Users/kjwkj/Desktop/html/keen_v3.0.6/demo1/pages/user-profile/campaigns.html">
														<span class="menu-bullet">
															<span class="bullet bullet-dot"></span>
														</span>
														<span class="menu-title">기능소분류 3</span>
													</a>
													<!--end:Menu link-->
												</div>
												<!--end:Menu item-->
												<!--begin:Menu item-->
												<div class="menu-item">
													<!--begin:Menu link-->
													<a class="menu-link" href="file:///C:/Users/kjwkj/Desktop/html/keen_v3.0.6/demo1/pages/user-profile/documents.html">
														<span class="menu-bullet">
															<span class="bullet bullet-dot"></span>
														</span>
														<span class="menu-title">기능소분류 4</span>
													</a>
													<!--end:Menu link-->
												</div>
												<!--end:Menu item-->
												<!--begin:Menu item-->
												<div class="menu-item">
													<!--begin:Menu link-->
													<a class="menu-link" href="file:///C:/Users/kjwkj/Desktop/html/keen_v3.0.6/demo1/pages/user-profile/followers.html">
														<span class="menu-bullet">
															<span class="bullet bullet-dot"></span>
														</span>
														<span class="menu-title">기능소분류 5</span>
													</a>
													<!--end:Menu link-->
												</div>
												<!--end:Menu item-->
												<!--begin:Menu item-->
												<div class="menu-item">
													<!--begin:Menu link-->
													<a class="menu-link" href="file:///C:/Users/kjwkj/Desktop/html/keen_v3.0.6/demo1/pages/user-profile/activity.html">
														<span class="menu-bullet">
															<span class="bullet bullet-dot"></span>
														</span>
														<span class="menu-title">기능소분류 6</span>
													</a>
													<!--end:Menu link-->
												</div>
												<!--end:Menu item-->
											</div>
											<!--end:Menu sub-->
										</div>
										
										<!--begin:Menu item-->
										<div data-kt-menu-trigger="click" class="menu-item menu-accordion">
											<!--begin:Menu link-->
											<span class="menu-link">
												<span class="menu-icon">
													<i class="ki-duotone ki-address-book fs-2">
														<span class="path1"></span>
														<span class="path2"></span>
														<span class="path3"></span>
													</i>
												</span>
												<span class="menu-title">기능대분류 2</span>
												<span class="menu-arrow"></span>
											</span>
											<!--end:Menu link-->
											<!--begin:Menu sub-->
											<div class="menu-sub menu-sub-accordion" kt-hidden-height="242" style="display: none; overflow: hidden;">
												<!--begin:Menu item-->
												<div class="menu-item">
													<!--begin:Menu link-->
													<a class="menu-link" href="file:///C:/Users/kjwkj/Desktop/html/keen_v3.0.6/demo1/pages/user-profile/overview.html">
														<span class="menu-bullet">
															<span class="bullet bullet-dot"></span>
														</span>
														<span class="menu-title">기능소분류 1</span>
													</a>
													<!--end:Menu link-->
												</div>
												<!--end:Menu item-->
												<!--begin:Menu item-->
												<div class="menu-item">
													<!--begin:Menu link-->
													<a class="menu-link" href="file:///C:/Users/kjwkj/Desktop/html/keen_v3.0.6/demo1/pages/user-profile/projects.html">
														<span class="menu-bullet">
															<span class="bullet bullet-dot"></span>
														</span>
														<span class="menu-title">기능소분류 2</span>
													</a>
													<!--end:Menu link-->
												</div>
												<!--end:Menu item-->
												<!--begin:Menu item-->
												<div class="menu-item">
													<!--begin:Menu link-->
													<a class="menu-link" href="file:///C:/Users/kjwkj/Desktop/html/keen_v3.0.6/demo1/pages/user-profile/campaigns.html">
														<span class="menu-bullet">
															<span class="bullet bullet-dot"></span>
														</span>
														<span class="menu-title">기능소분류 3</span>
													</a>
													<!--end:Menu link-->
												</div>
												<!--end:Menu item-->
												<!--begin:Menu item-->
												<div class="menu-item">
													<!--begin:Menu link-->
													<a class="menu-link" href="file:///C:/Users/kjwkj/Desktop/html/keen_v3.0.6/demo1/pages/user-profile/documents.html">
														<span class="menu-bullet">
															<span class="bullet bullet-dot"></span>
														</span>
														<span class="menu-title">기능소분류 4</span>
													</a>
													<!--end:Menu link-->
												</div>
												<!--end:Menu item-->
												<!--begin:Menu item-->
												<div class="menu-item">
													<!--begin:Menu link-->
													<a class="menu-link" href="file:///C:/Users/kjwkj/Desktop/html/keen_v3.0.6/demo1/pages/user-profile/followers.html">
														<span class="menu-bullet">
															<span class="bullet bullet-dot"></span>
														</span>
														<span class="menu-title">기능소분류 5</span>
													</a>
													<!--end:Menu link-->
												</div>
												<!--end:Menu item-->
												<!--begin:Menu item-->
												<div class="menu-item">
													<!--begin:Menu link-->
													<a class="menu-link" href="file:///C:/Users/kjwkj/Desktop/html/keen_v3.0.6/demo1/pages/user-profile/activity.html">
														<span class="menu-bullet">
															<span class="bullet bullet-dot"></span>
														</span>
														<span class="menu-title">기능소분류 6</span>
													</a>
													<!--end:Menu link-->
												</div>
												<!--end:Menu item-->
											</div>
											<!--end:Menu sub-->
										</div>
									</div>
									</div>
									</div>		
									<!--end::Menu-->
									
									
									<!--begin::Menu-->
									<div class="menu menu-column menu-rounded menu-sub-indention fw-semibold" id="#kt_app_sidebar_menu" data-kt-menu="true" data-kt-menu-expand="false">
										<!--begin:Menu item-->
										
										<!--end:Menu item-->
										<!--begin:Menu item-->
										<div data-kt-menu-trigger="click" class="menu-item pt-5 menu-item menu-accordion">
											<!--begin:Menu content-->
											<span class="menu-link">
												<span class="menu-icon">
														<i class="ki-duotone ki-address-book fs-2">
															<span class="path1"></span>
															<span class="path2"></span>
															<span class="path3"></span>
														</i>
													</span>
													<span class="menu-heading fw-bold text-uppercase fs-7 menu-title">일정관리</span>
													<span class="menu-arrow"></span>
											</span>
											<!--end:Menu content-->
										<!--end:Menu item-->
										<!--begin:Menu item-->
										<div class="menu-sub menu-sub-accordion" kt-hidden-height="242" style="display: none; overflow: hidden;">
										<div  data-kt-menu-trigger="click" class="menu-item menu-accordion">
											<!--begin:Menu link-->
											<span class="menu-link">
												<span class="menu-icon">
													<i class="ki-duotone ki-address-book fs-2">
														<span class="path1"></span>
														<span class="path2"></span>
														<span class="path3"></span>
													</i>
												</span>
												<span class="menu-title">기능대분류 1</span>
												<span class="menu-arrow"></span>
											</span>
											<!--end:Menu link-->
											<!--begin:Menu sub-->
											<div class="menu-sub menu-sub-accordion" kt-hidden-height="242" style="display: none; overflow: hidden;">
												<!--begin:Menu item-->
												<div class="menu-item">
													<!--begin:Menu link-->
													<a class="menu-link" href="file:///C:/Users/kjwkj/Desktop/html/keen_v3.0.6/demo1/pages/user-profile/overview.html">
														<span class="menu-bullet">
															<span class="bullet bullet-dot"></span>
														</span>
														<span class="menu-title">기능소분류 1</span>
													</a>
													<!--end:Menu link-->
												</div>
												<!--end:Menu item-->
												<!--begin:Menu item-->
												<div class="menu-item">
													<!--begin:Menu link-->
													<a class="menu-link" href="file:///C:/Users/kjwkj/Desktop/html/keen_v3.0.6/demo1/pages/user-profile/projects.html">
														<span class="menu-bullet">
															<span class="bullet bullet-dot"></span>
														</span>
														<span class="menu-title">기능소분류 2</span>
													</a>
													<!--end:Menu link-->
												</div>
												<!--end:Menu item-->
												<!--begin:Menu item-->
												<div class="menu-item">
													<!--begin:Menu link-->
													<a class="menu-link" href="file:///C:/Users/kjwkj/Desktop/html/keen_v3.0.6/demo1/pages/user-profile/campaigns.html">
														<span class="menu-bullet">
															<span class="bullet bullet-dot"></span>
														</span>
														<span class="menu-title">기능소분류 3</span>
													</a>
													<!--end:Menu link-->
												</div>
												<!--end:Menu item-->
												<!--begin:Menu item-->
												<div class="menu-item">
													<!--begin:Menu link-->
													<a class="menu-link" href="file:///C:/Users/kjwkj/Desktop/html/keen_v3.0.6/demo1/pages/user-profile/documents.html">
														<span class="menu-bullet">
															<span class="bullet bullet-dot"></span>
														</span>
														<span class="menu-title">기능소분류 4</span>
													</a>
													<!--end:Menu link-->
												</div>
												<!--end:Menu item-->
												<!--begin:Menu item-->
												<div class="menu-item">
													<!--begin:Menu link-->
													<a class="menu-link" href="file:///C:/Users/kjwkj/Desktop/html/keen_v3.0.6/demo1/pages/user-profile/followers.html">
														<span class="menu-bullet">
															<span class="bullet bullet-dot"></span>
														</span>
														<span class="menu-title">기능소분류 5</span>
													</a>
													<!--end:Menu link-->
												</div>
												<!--end:Menu item-->
												<!--begin:Menu item-->
												<div class="menu-item">
													<!--begin:Menu link-->
													<a class="menu-link" href="file:///C:/Users/kjwkj/Desktop/html/keen_v3.0.6/demo1/pages/user-profile/activity.html">
														<span class="menu-bullet">
															<span class="bullet bullet-dot"></span>
														</span>
														<span class="menu-title">기능소분류 6</span>
													</a>
													<!--end:Menu link-->
												</div>
												<!--end:Menu item-->
											</div>
											<!--end:Menu sub-->
										</div>
										
										<!--begin:Menu item-->
										<div data-kt-menu-trigger="click" class="menu-item menu-accordion">
											<!--begin:Menu link-->
											<span class="menu-link">
												<span class="menu-icon">
													<i class="ki-duotone ki-address-book fs-2">
														<span class="path1"></span>
														<span class="path2"></span>
														<span class="path3"></span>
													</i>
												</span>
												<span class="menu-title">기능대분류 2</span>
												<span class="menu-arrow"></span>
											</span>
											<!--end:Menu link-->
											<!--begin:Menu sub-->
											<div class="menu-sub menu-sub-accordion" kt-hidden-height="242" style="display: none; overflow: hidden;">
												<!--begin:Menu item-->
												<div class="menu-item">
													<!--begin:Menu link-->
													<a class="menu-link" href="file:///C:/Users/kjwkj/Desktop/html/keen_v3.0.6/demo1/pages/user-profile/overview.html">
														<span class="menu-bullet">
															<span class="bullet bullet-dot"></span>
														</span>
														<span class="menu-title">기능소분류 1</span>
													</a>
													<!--end:Menu link-->
												</div>
												<!--end:Menu item-->
												<!--begin:Menu item-->
												<div class="menu-item">
													<!--begin:Menu link-->
													<a class="menu-link" href="file:///C:/Users/kjwkj/Desktop/html/keen_v3.0.6/demo1/pages/user-profile/projects.html">
														<span class="menu-bullet">
															<span class="bullet bullet-dot"></span>
														</span>
														<span class="menu-title">기능소분류 2</span>
													</a>
													<!--end:Menu link-->
												</div>
												<!--end:Menu item-->
												<!--begin:Menu item-->
												<div class="menu-item">
													<!--begin:Menu link-->
													<a class="menu-link" href="file:///C:/Users/kjwkj/Desktop/html/keen_v3.0.6/demo1/pages/user-profile/campaigns.html">
														<span class="menu-bullet">
															<span class="bullet bullet-dot"></span>
														</span>
														<span class="menu-title">기능소분류 3</span>
													</a>
													<!--end:Menu link-->
												</div>
												<!--end:Menu item-->
												<!--begin:Menu item-->
												<div class="menu-item">
													<!--begin:Menu link-->
													<a class="menu-link" href="file:///C:/Users/kjwkj/Desktop/html/keen_v3.0.6/demo1/pages/user-profile/documents.html">
														<span class="menu-bullet">
															<span class="bullet bullet-dot"></span>
														</span>
														<span class="menu-title">기능소분류 4</span>
													</a>
													<!--end:Menu link-->
												</div>
												<!--end:Menu item-->
												<!--begin:Menu item-->
												<div class="menu-item">
													<!--begin:Menu link-->
													<a class="menu-link" href="file:///C:/Users/kjwkj/Desktop/html/keen_v3.0.6/demo1/pages/user-profile/followers.html">
														<span class="menu-bullet">
															<span class="bullet bullet-dot"></span>
														</span>
														<span class="menu-title">기능소분류 5</span>
													</a>
													<!--end:Menu link-->
												</div>
												<!--end:Menu item-->
												<!--begin:Menu item-->
												<div class="menu-item">
													<!--begin:Menu link-->
													<a class="menu-link" href="file:///C:/Users/kjwkj/Desktop/html/keen_v3.0.6/demo1/pages/user-profile/activity.html">
														<span class="menu-bullet">
															<span class="bullet bullet-dot"></span>
														</span>
														<span class="menu-title">기능소분류 6</span>
													</a>
													<!--end:Menu link-->
												</div>
												<!--end:Menu item-->
											</div>
											<!--end:Menu sub-->
										</div>
									</div>
									</div>
									</div>		
									<!--end::Menu-->
								
								<!--begin::Menu-->
									<div class="menu menu-column menu-rounded menu-sub-indention fw-semibold" id="#kt_app_sidebar_menu" data-kt-menu="true" data-kt-menu-expand="false">
										<!--begin:Menu item-->
										
										<!--end:Menu item-->
										<!--begin:Menu item-->
										<div data-kt-menu-trigger="click" class="menu-item pt-5 menu-item menu-accordion">
											<!--begin:Menu content-->
											<span class="menu-link">
												<span class="menu-icon">
														<i class="ki-duotone ki-address-book fs-2">
															<span class="path1"></span>
															<span class="path2"></span>
															<span class="path3"></span>
														</i>
													</span>
													<span class="menu-heading fw-bold text-uppercase fs-7 menu-title">전자결재</span>
													<span class="menu-arrow"></span>
											</span>
											<!--end:Menu content-->
										<!--end:Menu item-->
										<!--begin:Menu item-->
										<div class="menu-sub menu-sub-accordion" kt-hidden-height="242" style="display: none; overflow: hidden;">
										<div  data-kt-menu-trigger="click" class="menu-item menu-accordion">
											<!--begin:Menu link-->
											<span class="menu-link">
												<span class="menu-icon">
													<i class="ki-duotone ki-address-book fs-2">
														<span class="path1"></span>
														<span class="path2"></span>
														<span class="path3"></span>
													</i>
												</span>
												<span class="menu-title">기능대분류 1</span>
												<span class="menu-arrow"></span>
											</span>
											<!--end:Menu link-->
											<!--begin:Menu sub-->
											<div class="menu-sub menu-sub-accordion" kt-hidden-height="242" style="display: none; overflow: hidden;">
												<!--begin:Menu item-->
												<div class="menu-item">
													<!--begin:Menu link-->
													<a class="menu-link" href="file:///C:/Users/kjwkj/Desktop/html/keen_v3.0.6/demo1/pages/user-profile/overview.html">
														<span class="menu-bullet">
															<span class="bullet bullet-dot"></span>
														</span>
														<span class="menu-title">기능소분류 1</span>
													</a>
													<!--end:Menu link-->
												</div>
												<!--end:Menu item-->
												<!--begin:Menu item-->
												<div class="menu-item">
													<!--begin:Menu link-->
													<a class="menu-link" href="file:///C:/Users/kjwkj/Desktop/html/keen_v3.0.6/demo1/pages/user-profile/projects.html">
														<span class="menu-bullet">
															<span class="bullet bullet-dot"></span>
														</span>
														<span class="menu-title">기능소분류 2</span>
													</a>
													<!--end:Menu link-->
												</div>
												<!--end:Menu item-->
												<!--begin:Menu item-->
												<div class="menu-item">
													<!--begin:Menu link-->
													<a class="menu-link" href="file:///C:/Users/kjwkj/Desktop/html/keen_v3.0.6/demo1/pages/user-profile/campaigns.html">
														<span class="menu-bullet">
															<span class="bullet bullet-dot"></span>
														</span>
														<span class="menu-title">기능소분류 3</span>
													</a>
													<!--end:Menu link-->
												</div>
												<!--end:Menu item-->
												<!--begin:Menu item-->
												<div class="menu-item">
													<!--begin:Menu link-->
													<a class="menu-link" href="file:///C:/Users/kjwkj/Desktop/html/keen_v3.0.6/demo1/pages/user-profile/documents.html">
														<span class="menu-bullet">
															<span class="bullet bullet-dot"></span>
														</span>
														<span class="menu-title">기능소분류 4</span>
													</a>
													<!--end:Menu link-->
												</div>
												<!--end:Menu item-->
												<!--begin:Menu item-->
												<div class="menu-item">
													<!--begin:Menu link-->
													<a class="menu-link" href="file:///C:/Users/kjwkj/Desktop/html/keen_v3.0.6/demo1/pages/user-profile/followers.html">
														<span class="menu-bullet">
															<span class="bullet bullet-dot"></span>
														</span>
														<span class="menu-title">기능소분류 5</span>
													</a>
													<!--end:Menu link-->
												</div>
												<!--end:Menu item-->
												<!--begin:Menu item-->
												<div class="menu-item">
													<!--begin:Menu link-->
													<a class="menu-link" href="file:///C:/Users/kjwkj/Desktop/html/keen_v3.0.6/demo1/pages/user-profile/activity.html">
														<span class="menu-bullet">
															<span class="bullet bullet-dot"></span>
														</span>
														<span class="menu-title">기능소분류 6</span>
													</a>
													<!--end:Menu link-->
												</div>
												<!--end:Menu item-->
											</div>
											<!--end:Menu sub-->
										</div>
										
										<!--begin:Menu item-->
										<div data-kt-menu-trigger="click" class="menu-item menu-accordion">
											<!--begin:Menu link-->
											<span class="menu-link">
												<span class="menu-icon">
													<i class="ki-duotone ki-address-book fs-2">
														<span class="path1"></span>
														<span class="path2"></span>
														<span class="path3"></span>
													</i>
												</span>
												<span class="menu-title">기능대분류 2</span>
												<span class="menu-arrow"></span>
											</span>
											<!--end:Menu link-->
											<!--begin:Menu sub-->
											<div class="menu-sub menu-sub-accordion" kt-hidden-height="242" style="display: none; overflow: hidden;">
												<!--begin:Menu item-->
												<div class="menu-item">
													<!--begin:Menu link-->
<!-- 													<a class="menu-link" href="file:///C:/Users/kjwkj/Desktop/html/keen_v3.0.6/demo1/pages/user-profile/overview.html"> -->
														<a class="menu-link" href="./template.do">
														<span class="menu-bullet">
															<span class="bullet bullet-dot"></span>
														</span>
														<span class="menu-title">기능소분류 1</span>
													</a>
													<!--end:Menu link-->
												</div>
												<!--end:Menu item-->
												<!--begin:Menu item-->
												<div class="menu-item">
													<!--begin:Menu link-->
													<a class="menu-link" href="file:///C:/Users/kjwkj/Desktop/html/keen_v3.0.6/demo1/pages/user-profile/projects.html">
														<span class="menu-bullet">
															<span class="bullet bullet-dot"></span>
														</span>
														<span class="menu-title">기능소분류 2</span>
													</a>
													<!--end:Menu link-->
												</div>
												<!--end:Menu item-->
												<!--begin:Menu item-->
												<div class="menu-item">
													<!--begin:Menu link-->
													<a class="menu-link" href="file:///C:/Users/kjwkj/Desktop/html/keen_v3.0.6/demo1/pages/user-profile/campaigns.html">
														<span class="menu-bullet">
															<span class="bullet bullet-dot"></span>
														</span>
														<span class="menu-title">기능소분류 3</span>
													</a>
													<!--end:Menu link-->
												</div>
												<!--end:Menu item-->
												<!--begin:Menu item-->
												<div class="menu-item">
													<!--begin:Menu link-->
													<a class="menu-link" href="file:///C:/Users/kjwkj/Desktop/html/keen_v3.0.6/demo1/pages/user-profile/documents.html">
														<span class="menu-bullet">
															<span class="bullet bullet-dot"></span>
														</span>
														<span class="menu-title">기능소분류 4</span>
													</a>
													<!--end:Menu link-->
												</div>
												<!--end:Menu item-->
												<!--begin:Menu item-->
												<div class="menu-item">
													<!--begin:Menu link-->
													<a class="menu-link" href="file:///C:/Users/kjwkj/Desktop/html/keen_v3.0.6/demo1/pages/user-profile/followers.html">
														<span class="menu-bullet">
															<span class="bullet bullet-dot"></span>
														</span>
														<span class="menu-title">기능소분류 5</span>
													</a>
													<!--end:Menu link-->
												</div>
												<!--end:Menu item-->
												<!--begin:Menu item-->
												<div class="menu-item">
													<!--begin:Menu link-->
													<a class="menu-link" href="file:///C:/Users/kjwkj/Desktop/html/keen_v3.0.6/demo1/pages/user-profile/activity.html">
														<span class="menu-bullet">
															<span class="bullet bullet-dot"></span>
														</span>
														<span class="menu-title">기능소분류 6</span>
													</a>
													<!--end:Menu link-->
												</div>
												<!--end:Menu item-->
											</div>
											<!--end:Menu sub-->
										</div>
									</div>
									</div>
									</div>		
									<!--end::Menu-->
									
								
								
								</div>
								<!--end::Scroll wrapper-->
							</div>
							<!--end::Menu wrapper-->
						</div>
					</div>
					
					
					<div class="app-main flex-column flex-row-fluid" id="kt_app_main">
						<!--begin::Content wrapper-->
						<div class="d-flex flex-column flex-column-fluid">
							<!--begin::Toolbar-->
							<div id="kt_app_toolbar" class="app-toolbar py-3 py-lg-6">
								<!--begin::Toolbar container-->
								<div id="kt_app_toolbar_container" class="app-container container-fluid d-flex flex-stack">
									<!--begin::Page title-->
									<div class="page-title d-flex flex-column justify-content-center flex-wrap me-3">
										<!--begin::Title-->
										<h1 class="page-heading d-flex text-gray-900 fw-bold fs-3 flex-column justify-content-center my-0">Default</h1>
										<!--end::Title-->
										<!--begin::Breadcrumb-->
										<ul class="breadcrumb breadcrumb-separatorless fw-semibold fs-7 my-0 pt-1">
											<!--begin::Item-->
											<li class="breadcrumb-item text-muted">
												<a href="file:///C:/Users/kjwkj/Desktop/html/keen_v3.0.6/demo1/index.html" class="text-muted text-hover-primary">Home</a>
											</li>
											<!--end::Item-->
											<!--begin::Item-->
											<li class="breadcrumb-item">
												<span class="bullet bg-gray-500 w-5px h-2px"></span>
											</li>
											<!--end::Item-->
											<!--begin::Item-->
											<li class="breadcrumb-item text-muted">Dashboards</li>
										</ul>
									</div>
								</div>
							</div>
							<!-- 내용 시작 -->
							<div id="kt_app_content" class="app-content flex-column-fluid">
								<div class="app-container container-fluid">
									<div class="card card-flush h-md-50 mb-xl-10">
										<div class="card-header pt-5">
											<h3 class="card-title text-gray-800 fw-bold">Text</h3>
										</div>
										<div class="separator separator-dashed my-3"></div>	
										<div class="card-body pt-5">
											<div class="text-white">text-white</div>
											<div class="text-primary">text-primary</div>
											<div class="text-secondary">text-secondary</div>
											<div class="text-light">text-light</div>
											<div class="text-success">text-success</div>
											<div class="text-info">text-info</div>
											<div class="text-warning">text-warning</div>
											<div class="text-danger">text-danger</div>
											<div class="text-dark">text-dark</div>
											<div class="text-muted">text-muted</div>
											<div class="text-gray-100">text-gray-100</div>
											<div class="text-gray-200">text-gray-200</div>
											<div class="text-gray-300">text-gray-300</div>
											<div class="text-gray-400">text-gray-400</div>
											<div class="text-gray-500">text-gray-500</div>
											<div class="text-gray-600">text-gray-600</div>
											<div class="text-gray-700">text-gray-700</div>
											<div class="text-gray-800">text-gray-800</div>
											<div class="text-gray-900">text-gray-900</div>
										</div>
									</div>
								</div>
							</div>
							<!-- 내용 끝 -->
							
							
							<!-- 내용 시작 -->
							<div id="kt_app_content" class="app-content flex-column-fluid">
								<div class="app-container container-fluid">
									<div class="card card-flush h-md-50 mb-xl-10">
										<div class="card-header pt-5">
											<h3 class="card-title text-gray-800 fw-bold">Text2</h3>
										</div>
										<div class="separator separator-dashed my-3"></div>	
										<div class="card-body pt-5">
											<div class="text-inverse-primary bg-primary">text-inverse-primary bg-primary</div>
											<div class="text-inverse-secondary bg-secondary">text-inverse-secondary bg-secondary</div>
											<div class="text-inverse-light bg-light">text-inverse-light bg-light</div>
											<div class="text-inverse-success bg-success">text-inverse-success bg-success</div>
											<div class="text-inverse-info bg-info">text-inverse-info bg-info</div>
											<div class="text-inverse-warning bg-warning">text-inverse-warning bg-warning</div>
											<div class="text-inverse-danger bg-danger">text-inverse-danger bg-danger</div>
											<div class="text-inverse-dark bg-dark">text-inverse-dark bg-dark</div>
										</div>
									</div>
								</div>
							</div>
							<!-- 내용 끝 -->
							
							<!-- 내용 시작 -->
							<div id="kt_app_content" class="app-content flex-column-fluid">
								<div class="app-container container-fluid">
									<div class="card card-flush h-md-50 mb-xl-10">
										<div class="card-header pt-5">
											<h3 class="card-title text-gray-800 fw-bold">배경설정</h3>
										</div>
										<div class="separator separator-dashed my-3"></div>	
										<div class="card-body pt-5">
											<div class="bg-light">bg-light</div>
											<div class="bg-primary">bg-primary</div>
											<div class="bg-secondary">bg-secondary</div>
											<div class="bg-success">bg-success</div>
											<div class="bg-info">bg-info</div>
											<div class="bg-warning">bg-warning</div>
											<div class="bg-danger">bg-danger</div>
											<div class="bg-dark">bg-dark</div>
											<div class="bg-white">bg-white</div>
											<div class="bg-body">bg-body</div>
										</div>
									</div>
								</div>
							</div>
							<!-- 내용 끝 -->
							
							<!-- 내용 시작 -->
							<div id="kt_app_content" class="app-content flex-column-fluid">
								<div class="app-container container-fluid">
									<div class="card card-flush h-md-50 mb-xl-10">
										<div class="card-header pt-5">
											<h3 class="card-title text-gray-800 fw-bold">배경설정(호버)</h3>
										</div>
										<div class="separator separator-dashed my-3"></div>	
										<div class="card-body pt-5">
											<div class="bg-hover-light text-hover-inverse-light">bg-hover-light text-hover-inverse-light</div>
											<div class="bg-hover-primary text-hover-inverse-primary">bg-hover-primary text-hover-inverse-primary</div>
											<div class="bg-hover-secondary text-hover-inverse-secondary">bg-hover-secondary text-hover-inverse-secondary</div>
											<div class="bg-hover-success text-hover-inverse-success">bg-hover-success text-hover-inverse-success</div>
											<div class="bg-hover-info text-hover-inverse-info">bg-hover-info text-hover-inverse-info</div>
											<div class="bg-hover-warning text-hover-inverse-warning">bg-hover-warning text-hover-inverse-warning</div>
											<div class="bg-hover-danger text-hover-inverse-danger">bg-hover-danger text-hover-inverse-danger</div>
											<div class="bg-hover-dark text-hover-inverse-dark">bg-hover-dark text-hover-inverse-dark</div>
										</div>
									</div>
								</div>
							</div>
							<!-- 내용 끝 -->
							
							<!-- 내용 시작 -->
							<div id="kt_app_content" class="app-content flex-column-fluid">
								<div class="app-container container-fluid">
									<div class="card card-flush h-md-50 mb-xl-10">
										<div class="card-header pt-5">
											<h3 class="card-title text-gray-800 fw-bold">Border</h3>
										</div>
										<div class="separator separator-dashed my-3"></div>	
										<div class="card-body pt-5">
											<div class="card-body pt-5">
												<div class="border">
												    border
												</div>
											</div>
											
											<div class="card-body pt-5">
												<div class="border-gray-300 border-dotted">
												    border-gray-300 border-dotted
												</div>
											</div>
											
											<div class="card-body pt-5">
												<div class="border-gray-300 border-dashed">
												    border-gray-300 border-dashed
												</div>
											</div>
											
											<div class="card-body pt-5">
												<div class="border-gray-300 border-bottom-dashed">
												    border-gray-300 border-bottom-dashed
												</div>
											</div>
											
											<div class="card-body pt-5">
												<div class="border-gray-300 border-end-dashed">
												    border-gray-300 border-end-dashed
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<!-- 내용 끝 -->
							
							<!-- 내용 시작 -->
							<div id="kt_app_content" class="app-content flex-column-fluid">
								<div class="app-container container-fluid">
									<div class="card card-flush h-md-50 mb-xl-10">
										<div class="card-header pt-5">
											<h3 class="card-title text-gray-800 fw-bold">Border(hover)</h3>
										</div>
										<div class="separator separator-dashed my-3"></div>	
										<div class="card-body pt-5">
										
											<div class="card-body pt-5">
												<div class="border border-hover">
												   	border border-hover
												</div>
											</div>
											
											<div class="card-body pt-5">
												<div class="border border-gray-500 border-hover">
												    border border-gray-500 border-hover
												</div>
											</div>
											
											<div class="card-body pt-5">
												<div class="border border-primary border-hover">
												    border border-primary border-hover
												</div>
											</div>
											
										</div>
									</div>
								</div>
							</div>
							<!-- 내용 끝 -->
							
							<!-- 내용 시작 -->
							<div id="kt_app_content" class="app-content flex-column-fluid">
								<div class="app-container container-fluid">
									<div class="card card-flush h-md-50 mb-xl-10">
										<div class="card-header pt-5">
											<h3 class="card-title text-gray-800 fw-bold">input</h3>
										</div>
										<div class="separator separator-dashed my-3"></div>	
										<div class="card-body pt-5">
										
											<div class="card-body pt-5">
												<h1>form-control form-control-solid</h1>
												<input type="text" class="form-control form-control-solid" placeholder="name@example.com"/>
											</div>
											
										</div>
									</div>
								</div>
							</div>
							<!-- 내용 끝 -->
							
							<!-- 내용 시작 -->
							<div id="kt_app_content" class="app-content flex-column-fluid">
								<div class="app-container container-fluid">
									<div class="card card-flush h-md-50 mb-xl-10">
										<div class="card-header pt-5">
											<h3 class="card-title text-gray-800 fw-bold">Select Box</h3>
										</div>
										<div class="separator separator-dashed my-3"></div>	
										<div class="card-body pt-5">
										
											<div class="card-body pt-5">
												<select class="form-select" aria-label="Select example">
												    <option>Open this select menu</option>
												    <option value="1">One</option>
												    <option value="2">Two</option>
												    <option value="3">Three</option>
												</select>
											</div>
											
											<div class="card-body pt-5">
												<select class="form-select form-select-solid" aria-label="Select example">
												    <option>Open this select menu</option>
												    <option value="1">One</option>
												    <option value="2">Two</option>
												    <option value="3">Three</option>
												</select>
											</div>
											
											
											
										</div>
									</div>
								</div>
							</div>
							<!-- 내용 끝 -->
							
							<!-- 내용 시작 -->
							<div id="kt_app_content" class="app-content flex-column-fluid">
								<div class="app-container container-fluid">
									<div class="card card-flush h-md-50 mb-xl-10">
										<div class="card-header pt-5">
											<h3 class="card-title text-gray-800 fw-bold">check box</h3>
										</div>
										<div class="separator separator-dashed my-3"></div>	
										<div class="card-body pt-5">
											<div class="card-body pt-5">
												<div class="form-check">
												    <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" checked />
												    <label class="form-check-label" for="flexCheckChecked">
												        Checked state
												    </label>
												</div>
											</div>
											
											<div class="card-body pt-5">
												<div class="form-check form-check-custom form-check-solid">
												    <input class="form-check-input" type="checkbox" value="1" disabled id="flexCheckDisabled" />
												    <label class="form-check-label" for="flexCheckDisabled">
												        Disabled state
												    </label>
												</div>
											</div>
											
											
											<div class="card-body pt-5">
												<div class="form-check form-check-custom form-check-solid form-check-lg">
												    <input class="form-check-input" type="checkbox" value="" id="flexCheckboxLg"/>
												    <label class="form-check-label" for="flexCheckboxLg">
												        Large checkbox
												    </label>
												</div>
											</div>
											
											<div class="card-body pt-5">
												<div class="form-check form-check-custom form-check-solid form-check-lg">
												    <input class="form-check-input" type="checkbox" value="" id="flexCheckboxLg"  />
												    <label class="form-check-label" for="flexCheckboxSm">
												        Large radio
												    </label>
												</div>
											</div>
											
											<div class="card-body pt-5">
												<div class="form-check form-check-custom form-check-solid form-check-sm">
												    <input class="form-check-input" type="checkbox" value="" id="flexRadioLg"/>
												    <label class="form-check-label" for="flexRadioLg">
												        Small checkbox
												    </label>
												</div>
											</div>
											
											<div class="card-body pt-5">
												<div class="form-check form-check-custom form-check-solid me-10">
												    <input class="form-check-input h-40px w-40px" type="checkbox" value="" id="flexCheckbox40"/>
												    <label class="form-check-label" for="flexCheckbox40">
												        40px
												    </label>
												</div>
											</div>
											
											
										</div>
									</div>
								</div>
							</div>
							<!-- 내용 끝 -->
							
							<!-- 내용 시작 -->
							<div id="kt_app_content" class="app-content flex-column-fluid">
								<div class="app-container container-fluid">
									<div class="card card-flush h-md-50 mb-xl-10">
										<div class="card-header pt-5">
											<h3 class="card-title text-gray-800 fw-bold">Input Group</h3>
										</div>
										<div class="separator separator-dashed my-3"></div>	
										<div class="card-body pt-5">
											<!--begin::Input group-->
											<div class="input-group input-group-solid mb-5">
											    <span class="input-group-text" id="basic-addon1">@</span>
											    <input type="text" class="form-control" placeholder="Username" aria-label="Username" aria-describedby="basic-addon1"/>
											</div>
											<!--end::Input group-->
											
											<!--begin::Input group-->
											<div class="input-group input-group-solid mb-5">
											    <input type="text" class="form-control" placeholder="Recipient's username" aria-label="Recipient's username" aria-describedby="basic-addon2"/>
											    <span class="input-group-text" id="basic-addon2">@example.com</span>
											</div>
											<!--end::Input group-->
											
											<!--begin::Input group-->
											<label for="basic-url" class="form-label">Your vanity URL</label>
											<div class="input-group input-group-solid mb-5">
											    <span class="input-group-text" id="basic-addon3">https://example.com/users/</span>
											    <input type="text" class="form-control" id="basic-url" aria-describedby="basic-addon3"/>
											</div>
											<!--end::Input group-->
											
											<!--begin::Input group-->
											<div class="input-group input-group-solid mb-5">
											    <span class="input-group-text">$</span>
											    <input type="text" class="form-control" aria-label="Amount (to the nearest dollar)"/>
											    <span class="input-group-text">.00</span>
											</div>
											<!--end::Input group-->
											
											<!--begin::Input group-->
											<div class="input-group input-group-solid mb-5">
											    <input type="text" class="form-control" placeholder="Username" aria-label="Username"/>
											    <span class="input-group-text">@</span>
											    <input type="text" class="form-control" placeholder="Server" aria-label="Server"/>
											</div>
											<!--end::Input group-->
											
											<!--begin::Input group-->
											<div class="input-group input-group-solid">
											    <span class="input-group-text">With textarea</span>
											    <textarea class="form-control" aria-label="With textarea"></textarea>
											</div>
											<!--end::Input group-->
										</div>
									</div>
								</div>
							</div>
							<!-- 내용 끝 -->
							
							<!-- 내용 시작 -->
							<div id="kt_app_content" class="app-content flex-column-fluid">
								<div class="app-container container-fluid">
									<div class="card card-flush h-md-50 mb-xl-10">
										<div class="card-header pt-5">
											<h3 class="card-title text-gray-800 fw-bold">Input</h3>
										</div>
										<div class="separator separator-dashed my-3"></div>	
										<div class="card-body pt-5">
											<label class="form-label">Basic example</label>
											<input type="text" class="form-control" id="kt_docs_maxlength_basic" maxlength="10" />
											<span class="fs-6 text-muted">The badge will show up by default when the remaining chars are 3 or less</span>		
										</div>
										<script type="text/javascript">
											$('#kt_docs_maxlength_basic').maxlength({
											    warningClass: "badge badge-warning",
											    limitReachedClass: "badge badge-success"
											});
										</script>
										
										
										<div class="card-body pt-5">
											<label class="form-label">Custom text example</label>
											<input type="text" class="form-control" id="kt_docs_maxlength_custom_text" maxlength="20" />
											<span class="fs-6 text-muted">Display custom text on input focus</span>	
										</div>
										<script type="text/javascript">
											$('#kt_docs_maxlength_custom_text').maxlength({
											    threshold: 20,
											    warningClass: "badge badge-danger",
											    limitReachedClass: "badge badge-success",
											    separator: ' of ',
											    preText: 'You have ',
											    postText: ' chars remaining.',
											    validate: true
											});
										</script>
										
										<div class="card-body pt-5">
											<label class="form-label">Textarea example</label>
											<textarea class="form-control" id="kt_docs_maxlength_textarea" maxlength="20" placeholder="" rows="6"></textarea>
											<span class="fs-6 text-muted">Bootstrap maxlength supports textarea as well as inputs</span>
										</div>
										<script type="text/javascript">
											$('#kt_docs_maxlength_textarea').maxlength({
											    warningClass: "badge badge-primary",
											    limitReachedClass: "badge badge-success"
											});
										</script>
										
									</div>
								</div>
							</div>
							<!-- 내용 끝 -->
							
							<!-- 내용 시작 -->
							<div id="kt_app_content" class="app-content flex-column-fluid">
								<div class="app-container container-fluid">
									<div class="card card-flush h-md-50 mb-xl-10">
										<div class="card-header pt-5">
											<h3 class="card-title text-gray-800 fw-bold">Button</h3>
										</div>
										<div class="separator separator-dashed my-3"></div>	
										<div class="card-body pt-5">
											<h2>확인 / 취소</h2>
											<a href="#" class="btn btn-secondary me-10">btn btn-secondary me-10</a>
											<a href="#" class="btn btn-primary me-10">btn btn-primary me-10</a>
										</div>
										
										<div class="card-body pt-5">
										<h2>전송(Submit)</h2>
											<button type="button" class="btn btn-primary me-10" id="kt_button_1">
											    <span class="indicator-label">
											        Submit
											    </span>
											    <span class="indicator-progress">
											        Please wait... <span class="spinner-border spinner-border-sm align-middle ms-2"></span>
											    </span>
											</button>
										</div>
										<script type="text/javascript">
											// Element to indecate
											var button = document.querySelector("#kt_button_1");
	
											// Handle button click event
											button.addEventListener("click", function() {
											    // Activate indicator
											    button.setAttribute("data-kt-indicator", "on");
	
											    // Disable indicator after 3 seconds
											    setTimeout(function() {
											        button.removeAttribute("data-kt-indicator");
											    }, 3000);
											});
										</script>
										
									</div>
								</div>
							</div>
							<!-- 내용 끝 -->
							
							<!-- 내용 시작 -->
							<div id="kt_app_content" class="app-content flex-column-fluid">
								<div class="app-container container-fluid">
									<div class="card card-flush h-md-50 mb-xl-10">
										<div class="card-header pt-5">
											<h3 class="card-title text-gray-800 fw-bold">Accordion</h3>
										</div>
										<div class="separator separator-dashed my-3"></div>	
										<div class="card-body pt-5">
											<!--begin::Accordion-->
											<div class="accordion" id="kt_accordion_1">
											    <div class="accordion-item">
											        <h2 class="accordion-header" id="kt_accordion_1_header_1">
											            <button class="accordion-button fs-4 fw-semibold" type="button" data-bs-toggle="collapse" data-bs-target="#kt_accordion_1_body_1" aria-expanded="true" aria-controls="kt_accordion_1_body_1">
											                Accordion Item #1
											            </button>
											        </h2>
											        <div id="kt_accordion_1_body_1" class="accordion-collapse collapse show" aria-labelledby="kt_accordion_1_header_1" data-bs-parent="#kt_accordion_1">
											            <div class="accordion-body">
											                ...
											            </div>
											        </div>
											    </div>
											
											    <div class="accordion-item">
											        <h2 class="accordion-header" id="kt_accordion_1_header_2">
											            <button class="accordion-button fs-4 fw-semibold collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#kt_accordion_1_body_2" aria-expanded="false" aria-controls="kt_accordion_1_body_2">
											            Accordion Item #2
											            </button>
											        </h2>
											        <div id="kt_accordion_1_body_2" class="accordion-collapse collapse" aria-labelledby="kt_accordion_1_header_2" data-bs-parent="#kt_accordion_1">
											            <div class="accordion-body">
											                ...
											            </div>
											        </div>
											    </div>
											
											    <div class="accordion-item">
											        <h2 class="accordion-header" id="kt_accordion_1_header_3">
											            <button class="accordion-button fs-4 fw-semibold collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#kt_accordion_1_body_3" aria-expanded="false" aria-controls="kt_accordion_1_body_3">
											            Accordion Item #3
											            </button>
											        </h2>
											        <div id="kt_accordion_1_body_3" class="accordion-collapse collapse" aria-labelledby="kt_accordion_1_header_3" data-bs-parent="#kt_accordion_1">
											            <div class="accordion-body">
											               ...
											            </div>
											        </div>
											    </div>
											</div>
											<!--end::Accordion-->
										</div>
									</div>
								</div>
							</div>
							<!-- 내용 끝 -->
							
							
							<!-- 내용 시작 -->
							<div id="kt_app_content" class="app-content flex-column-fluid">
								<div class="app-container container-fluid">
									<div class="card card-flush h-md-50 mb-xl-10">
										<div class="card-header pt-5">
											<h3 class="card-title text-gray-800 fw-bold">Modal</h3>
										</div>
										<div class="separator separator-dashed my-3"></div>	
										
										<div class="card-body pt-5">
											<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#kt_modal_1">
											   	일반모달창
											</button>
											
											<div class="modal fade" tabindex="-1" id="kt_modal_1">
											    <div class="modal-dialog">
											        <div class="modal-content">
											
											            <div class="modal-body">
											                <p>한글입니다.</p>
											            </div>
											
											            <div class="modal-footer">
											                <button type="button" class="btn btn-secondary me-10" data-bs-dismiss="modal">Close</button>
											                <button type="button" class="btn btn-primary me-10">등록</button>
											            </div>
											        </div>
											    </div>
											</div>
										</div>
										
										
										<div class="card-body pt-5">
											<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#kt_modal_3">
											    움직이는 모달
											</button>
											
											<div class="modal fade" tabindex="-1" id="kt_modal_3">
											    <div class="modal-dialog">
											        <div class="modal-content position-absolute">
											            <div class="modal-header">
											                <h5 class="modal-title">Modal title</h5>
											
											                <!--begin::Close-->
											                <div class="btn btn-icon btn-sm btn-active-light-primary ms-2" data-bs-dismiss="modal" aria-label="Close">
											                    <i class="ki-duotone ki-cross fs-2x"><span class="path1"></span><span class="path2"></span></i>
											                </div>
											                <!--end::Close-->
											            </div>
											
											            <div class="modal-body">
											                <p>Modal body text goes here.</p>
											            </div>
											
											            <div class="modal-footer">
											                <button type="button" class="btn btn-secondary me-10" data-bs-dismiss="modal">Close</button>
											                <button type="button" class="btn btn-primary me-10">Save changes</button>
											            </div>
											        </div>
											    </div>
											</div>
										</div>
										<script type="text/javascript">
										// Make the DIV element draggable:
										var element = document.querySelector('#kt_modal_3');
										dragElement(element);

										function dragElement(elmnt) {
										    var pos1 = 0, pos2 = 0, pos3 = 0, pos4 = 0;
										    if (elmnt.querySelector('.modal-content')) {
										        // if present, the header is where you move the DIV from:
										        elmnt.querySelector('.modal-content').onmousedown = dragMouseDown;
										    } else {
										        // otherwise, move the DIV from anywhere inside the DIV:
										        elmnt.onmousedown = dragMouseDown;
										    }

										    function dragMouseDown(e) {
										        e = e || window.event;
										        // get the mouse cursor position at startup:
										        pos3 = e.clientX;
										        pos4 = e.clientY;
										        document.onmouseup = closeDragElement;
										        // call a function whenever the cursor moves:
										        document.onmousemove = elementDrag;
										    }

										    function elementDrag(e) {
										        e = e || window.event;
										        // calculate the new cursor position:
										        pos1 = pos3 - e.clientX;
										        pos2 = pos4 - e.clientY;
										        pos3 = e.clientX;
										        pos4 = e.clientY;
										        // set the element's new position:
										        elmnt.style.top = (elmnt.offsetTop - pos2) + "px";
										        elmnt.style.left = (elmnt.offsetLeft - pos1) + "px";
										    }

										    function closeDragElement() {
										        // stop moving when mouse button is released:
										        document.onmouseup = null;
										        document.onmousemove = null;
										    }
										}
										</script>
										
										
									</div>
								</div>
							</div>
							<!-- 내용 끝 -->
							
							<!-- 내용 시작 -->
							<div id="kt_app_content" class="app-content flex-column-fluid">
								<div class="app-container container-fluid">
									<div class="card card-flush h-md-50 mb-xl-10">
										<div class="card-header pt-5">
											<h3 class="card-title text-gray-800 fw-bold">Date</h3>
										</div>
										<div class="separator separator-dashed my-3"></div>	
										
										<div class="card-body pt-5">
											<div class="input-group" id="kt_td_picker_custom_icons" data-td-target-input="nearest" data-td-target-toggle="nearest">
											    <input id="kt_td_picker_custom_icons_input" type="text" class="form-control" data-td-target="#kt_td_picker_custom_icons"/>
											    <span class="input-group-text" data-td-target="#kt_td_picker_custom_icons" data-td-toggle="datetimepicker">
											        <i class="ki-duotone ki-calendar fs-2"><span class="path1"></span><span class="path2"></span></i>
											    </span>
											</div>
										</div>
										<script type="text/javascript">
											new tempusDominus.TempusDominus(document.getElementById("kt_td_picker_custom_icons"), {
											    display: {
											    icons: {
											        time: "ki-outline ki-time fs-1",
											        date: "ki-outline ki-calendar fs-1",
											        up: "ki-outline ki-up fs-1",
											        down: "ki-outline ki-down fs-1",
											        previous: "ki-outline ki-left fs-1",
											        next: "ki-outline ki-right fs-1",
											        today: "ki-outline ki-check fs-1",
											        clear: "ki-outline ki-trash fs-1",
											        close: "ki-outline ki-cross fs-1",
											    },
											    buttons: {
											        today: true,
											        clear: true,
											        close: true,
											    },
											    }
											});
										</script>
										
										<div class="card-body pt-5">
											<div class="row">
											    <div class="col-sm-6">
											        <label for="kt_td_picker_linked_1_input" class="form-label">From</label>
											        <div class="input-group log-event" id="kt_td_picker_linked_1" data-td-target-input="nearest" data-td-target-toggle="nearest">
											            <input id="kt_td_picker_linked_1_input" type="text" class="form-control" data-td-target="#kt_td_picker_linked_1"/>
											            <span class="input-group-text" data-td-target="#kt_td_picker_linked_1" data-td-toggle="datetimepicker">
											                <i class="ki-duotone ki-calendar fs-2"><span class="path1"></span><span class="path2"></span></i>
											            </span>
											        </div>
											    </div>
											    <div class="col-sm-6">
											        <label for="kt_td_picker_linked_2_input" class="form-label">To</label>
											        <div class="input-group log-event" id="kt_td_picker_linked_2" data-td-target-input="nearest" data-td-target-toggle="nearest">
											            <input id="kt_td_picker_linked_2_input" type="text" class="form-control" data-td-target="#kt_td_picker_linked_2"/>
											            <span class="input-group-text" data-td-target="#kt_td_picker_linked_2" data-td-toggle="datetimepicker">
											                <i class="ki-duotone ki-calendar fs-2"><span class="path1"></span><span class="path2"></span></i>
											            </span>
											        </div>
											    </div>
											</div>
										</div>
										<script type="text/javascript">
											const linkedPicker1Element = document.getElementById("kt_td_picker_linked_1");
											const linked1 = new tempusDominus.TempusDominus(linkedPicker1Element);
											const linked2 = new tempusDominus.TempusDominus(document.getElementById("kt_td_picker_linked_2"), {
											    useCurrent: false,
											});
	
											//using event listeners
											linkedPicker1Element.addEventListener(tempusDominus.Namespace.events.change, (e) => {
											    linked2.updateOptions({
											        restrictions: {
											        minDate: e.detail.date,
											        },
											    });
											});
	
											//using subscribe method
											const subscription = linked2.subscribe(tempusDominus.Namespace.events.change, (e) => {
											    linked1.updateOptions({
											        restrictions: {
											        maxDate: e.date,
											        },
											    });
											});
										</script>
										
										<div class="card-body pt-5">
											<div class="mb-0">
											    <label class="form-label">Example</label>
											    <input class="form-control form-control-solid" placeholder="Pick date rage" id="kt_daterangepicker_2"/>
											</div>
										</div>
										<script type="text/javascript">
										$("#kt_daterangepicker_2").daterangepicker({
										    timePicker: true,
										    startDate: moment().startOf("hour"),
										    endDate: moment().startOf("hour").add(32, "hour"),
										    locale: {
										        format: "M/DD hh:mm A"
										    }
										});
										</script>
										
										
									</div>
								</div>
							</div>
							<!-- 내용 끝 -->
							
							
						</div>
						<!--end::Content wrapper-->
						<!--begin::Footer-->
						<div id="kt_app_footer" class="app-footer">
							<!--begin::Footer container-->
							<div class="app-container container-fluid d-flex flex-column flex-md-row flex-center flex-md-stack py-3">
								<!--begin::Copyright-->
								<div class="text-gray-900 order-2 order-md-1">
									<span class="text-muted fw-semibold me-1">2023©</span>
									<a href="https://keenthemes.com/" target="_blank" class="text-gray-800 text-hover-primary">Keenthemes</a>
								</div>
							</div>
							<!--end::Footer container-->
						</div>
						<!--end::Footer-->
					</div>
					<!--end:::Main-->
				</div>
				<!--end::Wrapper-->
			</div>
			<!--end::Page-->
		</div>
</body>


<!--begin::Javascript-->
<script>var hostUrl = "assets/";</script>
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
<script type="text/javascript" src="./js/signTree.js"></script>
</html>