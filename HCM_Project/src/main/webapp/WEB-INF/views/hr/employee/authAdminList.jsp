<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<%@include file="/WEB-INF/views/menu/headerInfo.jsp" %>
	<title>HCM GroupWare</title>
	<script src="/js/hr/employee.js" type="text/javascript"></script>
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
						<h1 class="page-heading d-flex text-gray-900 fw-bold fs-3 flex-column justify-content-center my-0">인사관리 > 조직관리 > 권한관리</h1>
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
								<h3 class="card-title text-gray-800 fw-bold">권한관리</h3>
							</div>
							<div class="separator separator-dashed my-3"></div>	
							<div class="card-body pt-5">
								<div class="table-responsive">
									<table class="table table-hover table-rounded table-striped border gy-7 gs-7">
										<thead>
											<tr class="fw-semibold fs-6 text-gray-800 border-bottom-2 border-gray-200">
												<th>No</th>
												<th>사번</th>
												<th>성명</th>
												<th>부서</th>
												<th>직위</th>
												<th>직책</th>
												<th>권한명</th>
											</tr>
										</thead>	
										<tbody>
											<c:forEach var="list" items="${lists}" varStatus="vs">
												<tr style="cursor: pointer;" onclick="location.href='/hr/employee/modifyAuthAdmin.do?empl_id=${list.empl_id}'" class="py-5 fw-semibold  border-bottom border-gray-300 fs-6">
													<td>${fn:length(lists)-vs.index}</td>
													<td>${list.empl_id}</td>
													<td>${list.empl_name}</td>
													<td>${list.empl_dept_nm}</td>
													<td>${list.empl_rank_nm}</td>
													<td>${list.empl_position_nm}</td>
													<td>${list.empl_auth_nm}</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
								
							</div>
							<div class="card-footer" style="text-align:right;">
								<a href="/hr/employee/registAuthAdmin.do" class="btn btn-primary">권한추가</a>
						    </div>
						</div>
					</div>
				</div>
				<!-- 내용 끝 -->
				
			</div>
		</div>
			
<%@include file="/WEB-INF/views/menu/hrSideMenu.jsp" %>		
</body>
</html>