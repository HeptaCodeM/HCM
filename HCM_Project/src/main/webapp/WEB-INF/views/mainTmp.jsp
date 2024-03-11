<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/menu/headerInfo.jsp" %>
<title>HCM메인화면</title>
</head>
<%@include file="/WEB-INF/views/menu/header.jsp" %>
<body id="kt_app_body" data-kt-app-layout="dark-sidebar"
		data-kt-app-header-fixed="true" data-kt-app-sidebar-enabled="true"
		data-kt-app-sidebar-fixed="true" data-kt-app-sidebar-hoverable="true"
		data-kt-app-sidebar-push-header="true"
		data-kt-app-sidebar-push-toolbar="true"
		data-kt-app-sidebar-push-footer="true"
		data-kt-app-toolbar-enabled="true" class="app-default">

		<div class="app-wrapper flex-column flex-row-fluid">
			<div class="app-toolbar py-3 py-lg-6">
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
				<div class="app-content flex-column-fluid ">
					<div class="app-container container-fluid">
						<div class="card card-flush h-md-50">
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
				
				<div class="app-container container-fluid">
					<div class="row gx-5 gx-xl-10">
						<!--begin::Col-->
						<div class="col-xxl-6 mb-5 mb-xl-10">
							<!--begin::Chart widget 8-->
							<div class="card card-flush h-xl-100">
								<!--begin::Header-->
								<div class="card-header pt-5">
									<!--begin::Title-->
									<h3 class="card-title align-items-start flex-column">
										<span class="card-label fw-bold text-gray-900">Performance
											Overview</span> <span class="text-gray-500 mt-1 fw-semibold fs-6">Users
											from all channels</span>
									</h3>
									<!--end::Title-->
								</div>
								<!--end::Header-->
								<!--begin::Body-->
								<div class="card-body pt-6">
									<!--begin::Tab content-->
									<div class="tab-content"></div>
									<!--end::Tab content-->
								</div>
								<!--end::Body-->
							</div>
							<!--end::Chart widget 8-->
						</div>
						<!--end::Col-->
						<!--begin::Col-->
						<div class="col-xl-6 mb-5 mb-xl-10">
							<!--begin::Chart widget 36-->
							<div class="card card-flush overflow-hidden h-xl-100">
								<!--begin::Header-->
								<div class="card-header pt-5">
									<!--begin::Title-->
									<h3 class="card-title align-items-start flex-column">
										<span class="card-label fw-bold text-gray-900">Performance</span> 
										<span class="text-gray-500 mt-1 fw-semibold fs-6">1,046 Inbound Calls today</span>
									</h3>
									<!--end::Title-->
								</div>
								<!--end::Header-->
								<!--begin::Card body-->
								<div class="card-body d-flex align-items-end p-0">
	
								</div>
								<!--end::Card body-->
							</div>
							<!--end::Chart widget 36-->
						</div>
						<!--end::Col-->
					</div>
				</div>
			</div>
		</div>
			
<%@include file="/WEB-INF/views/menu/mainSideMenu.jsp"%>		
</body>
</html>