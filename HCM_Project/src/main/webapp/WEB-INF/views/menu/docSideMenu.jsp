<!--begin::Wrapper-->
<div class="app-wrapper flex-column flex-row-fluid" id="kt_app_wrapper">
	<!--begin::Sidebar-->
	<div id="kt_app_sidebar" class="app-sidebar flex-column" data-kt-drawer="true" data-kt-drawer-name="app-sidebar" data-kt-drawer-activate="{default: true, lg: false}" data-kt-drawer-overlay="true" data-kt-drawer-width="225px" data-kt-drawer-direction="start" data-kt-drawer-toggle="#kt_app_sidebar_mobile_toggle">
		<!--begin::Logo-->
		<div class="app-sidebar-logo px-6" style="justify-content: center;" id="kt_app_sidebar_logo">
			<!--begin::Logo image-->
			<a href="/">
				<img class="mainMenuLog" alt="HCM" src="/image/logo-removebg-preview.png">
			</a>
			<!--end::Logo image-->
		</div>
		<!--end::Logo-->
		<!--begin::sidebar menu-->
		<!--begin::Menu wrapper-->
		<div id="kt_app_sidebar_menu_wrapper" class="app-sidebar-wrapper">
			<!--begin::Scroll wrapper-->
			<div id="kt_app_sidebar_menu_scroll" class="hover-scroll-y my-5 mx-3" data-kt-scroll="true" data-kt-scroll-activate="true" data-kt-scroll-height="auto" data-kt-scroll-dependencies="#kt_app_sidebar_logo, #kt_app_sidebar_footer" data-kt-scroll-wrappers="#kt_app_sidebar_menu" data-kt-scroll-offset="5px" data-kt-scroll-save-state="true" style="height: 1097px;">
			<%@include file="/WEB-INF/views/menu/docMenu.jsp" %>
			</div>
			<!--end::Scroll wrapper-->
		</div>
		<!--end::Menu wrapper-->
	</div>
</div>