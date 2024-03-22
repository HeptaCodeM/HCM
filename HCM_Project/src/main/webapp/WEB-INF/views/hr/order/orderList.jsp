<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<%@include file="/WEB-INF/views/menu/headerInfo.jsp" %>
	<title>HCM GroupWare</title>

	<style type="text/css">
	.table th {  vertical-align:middle; text-align:center !important; background-color:#F9F9F9; font-weight:600; }
	#searchOrderList { text-align:center; }
	</style>
	<script src="/js/hr/order.js" type="text/javascript"></script>
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
						<h1 class="page-heading d-flex text-gray-900 fw-bold fs-3 flex-column justify-content-center my-0">인사관리 > 인사발령관리 > 인사발령현황</h1>
						<!--end::Title-->
					</div>
					<!--end::Page title-->
				</div>		
			</div>
			<div class="app-content flex-column-fluid">
				<!-- 내용 시작 -->
				<div class="app-content flex-column-fluid">
					<div class="app-container container-fluid">
						<div class="card card-flush h-md-50 mb-xl-10">
							<div class="card-header pt-5">
								<h3 class="card-title text-gray-800 fw-bold">인사발령 현황리스트</h3>
							</div>
							<div class="separator separator-dashed my-3"></div>	
							<div class="card-body pt-5">
								<div class="table-responsive">
									<table id="searchOrderList" class="table table-row-bordered gy-5">
										<thead>
											<tr class="fw-semibold fs-6 text-muted">
												<th>발령번호</th>
												<th>발령일자</th>
												<th>발령구분</th>
												<th>이전부서</th>
												<th>발령부서</th>
												<th>이전직위</th>
												<th>발령직위</th>
												<th>이전직책</th>
												<th>발령직책</th>
												<th>진행구분</th>
											</tr>
										</thead>
									</table>
								</div>
								
							</div>
						</div>
					</div>
				</div>
				<!-- 내용 끝 -->
				
			</div>
		</div>
			
<script type="text/javascript">
$(function(){ 
	orderSearchList()
});
</script>
<%@include file="/WEB-INF/views/menu/hrSideMenu.jsp" %>		
</body>
</html>