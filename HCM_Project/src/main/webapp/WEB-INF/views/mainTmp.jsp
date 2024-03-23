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
		<div class="app-container container-fluid">
			<div class="row gx-5 gx-xl-10">
			
				<!--begin::Col-->
				<div class="col-xxl-6 mb-5 mb-xl-10">
					<div class="card shadow-sm">
					    <div class="card-header">
					        <h3 class="card-title">공지사항</h3>
					        <div class="card-toolbar">
					            <button type="button" class="btn btn-sm btn-light">
					                Action
					            </button>
					        </div>
					    </div>
					    <div class="card-body">
					    	공사중입니다!
					    </div>
					    <div class="card-footer">
					        Footer
					    </div>
					</div>
				</div>
				<!--end::Col-->
				
				<!--begin::Col-->
				<div class="col-xl-6 mb-5 mb-xl-10">
					<div class="card shadow-sm">
					    <div class="card-header">
					        <h3 class="card-title">Title</h3>
					        <div class="card-toolbar">
					            <button type="button" class="btn btn-sm btn-light">
					                Action
					            </button>
					        </div>
					    </div>
					    <div class="card-body">
					        Lorem Ipsum is simply dummy text...
					    </div>
					    <div class="card-footer">
					        Footer
					    </div>
					</div>
				</div>
				<!--end::Col-->
				
			</div>	
			
			<div class="row gx-5 gx-xl-10">
			
				<!--begin::Col-->
				<div class="col-xxl-6 mb-5 mb-xl-10">
					<div class="card shadow-sm">
					    <div class="card-header">
					        <h3 class="card-title">Title</h3>
					        <div class="card-toolbar">
					            <button type="button" class="btn btn-sm btn-light">
					                Action
					            </button>
					        </div>
					    </div>
					    <div class="card-body">
					        Lorem Ipsum is simply dummy text...
					    </div>
					    <div class="card-footer">
					        Footer
					    </div>
					</div>
				</div>
				<!--end::Col-->
				
				<!--begin::Col-->
				<div class="col-xl-6 mb-5 mb-xl-10">
					<div class="card shadow-sm">
					    <div class="card-header">
					        <h3 class="card-title">Title</h3>
					        <div class="card-toolbar">
					            <button type="button" class="btn btn-sm btn-light">
					                Action
					            </button>
					        </div>
					    </div>
					    <div class="card-body">
					        Lorem Ipsum is simply dummy text...
					    </div>
					    <div class="card-footer">
					        Footer
					    </div>
					</div>
				</div>
				<!--end::Col-->
				
			</div>	
		</div>
	</div>
	
	
	
</div>		
		
		
<%@include file="/WEB-INF/views/menu/mainSideMenu.jsp"%>	
</body>
</html>