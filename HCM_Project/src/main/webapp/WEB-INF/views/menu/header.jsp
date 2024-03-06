<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
						href="/mainTmp.do"
						class="d-lg-none"> <img alt="HCM" src="/image/favicon.ico"
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
								<a href="/mainTmp.do">
									<span class="menu-link"> <span class="menu-title">메인화면</span>
										<span class="menu-arrow d-lg-none"></span>
									</span>
								</a>

							</div>
							<!--begin:Menu item-->
							<div
								data-kt-menu-trigger="{default: &#39;click&#39;, lg: &#39;hover&#39;}"
								data-kt-menu-placement="bottom-start"
								class="menu-item menu-lg-down-accordion me-0 me-lg-2">
								<a href="/hr/hrMainTmp.do">
									<span class="menu-link"> <span class="menu-title">인사관리</span>
										<span class="menu-arrow d-lg-none"></span>
									</span>
								</a>
								<!--begin:Menu sub-->
							</div>
							<!--end:Menu item-->
							<!--begin:Menu item-->
							<div
								data-kt-menu-trigger="{default: &#39;click&#39;, lg: &#39;hover&#39;}"
								data-kt-menu-placement="bottom-start"
								class="menu-item menu-lg-down-accordion menu-sub-lg-down-indention me-0 me-lg-2">
								<a href="/sm/smMainTmp.do">
									<span class="menu-link"> <span class="menu-title">일정관리</span>
										<span class="menu-arrow d-lg-none"></span>
									</span>
								</a>

							</div>
							<!--end:Menu item-->
							<!--begin:Menu item-->
							<div
								data-kt-menu-trigger="{default: &#39;click&#39;, lg: &#39;hover&#39;}"
								data-kt-menu-placement="bottom-start"
								class="menu-item menu-lg-down-accordion me-0 me-lg-2">
								<a href="/doc/docMainTmp.do">
									<span class="menu-link"> <span class="menu-title">전자결재</span>
										<span class="menu-arrow d-lg-none"></span>
									</span>
								</a>
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
							<img src="/image/300-3.jpg" alt="user">
						</div>
					</div>
					<!--end::User menu-->
				</div>
			</div>
			<!--end::Header container-->
		</div>
		<!--end::Header-->
</html>