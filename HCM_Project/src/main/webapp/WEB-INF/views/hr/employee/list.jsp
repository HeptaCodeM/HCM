<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/menu/headerInfo.jsp" %>
<style type="text/css">
	.searchEmpInput{
		width: 400px;
		height: 40px;
	}
	
	.searchEmpSelect{
		width: 250px;
		height: 40px;
	}
	.cardFlexSearch{
		display: flex;
	}
</style>
<title>조직관리</title>
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
						<h1 class="page-heading d-flex text-gray-900 fw-bold fs-3 flex-column justify-content-center my-0">임직원 정보</h1>
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
							<div class="card-body pt-5">
								<div class="cardFlexSearch">
									<span>
										<select class="form-select form-select-solid searchEmpSelect">
											<option value="">분류</option>
											<option value="">사원번호</option>
											<option value="">전화번호</option>
										</select>
									</span>&nbsp;&nbsp;
									
									<span>
										<select class="form-select form-select-solid searchEmpSelect">
											<option value="">재직기간</option>
											<option value="">???</option>
										</select>
									</span>&nbsp;&nbsp;
									
									<span>
										<input type="text" class="form-control form-control-solid searchEmpInput" id="" name="" placeholder="검색">
									</span>&nbsp;&nbsp;
								
									<button class="btn btn-primary btnLg me-10" id="">검색</button>
								</div>
								<div>
									<div class="form-check form-check-custom form-check-solid">
										부서&nbsp;&nbsp;
										<c:forEach var="dt" items="${deptList}">
											<span>  
												<input class="form-check-input" type="checkbox" value="" id=""/>&nbsp;
												${dt.getCoco_name()}
											</span>&nbsp;
										</c:forEach>
									</div>
									
									<div class="form-check form-check-custom form-check-solid">
										직위&nbsp;&nbsp;
										<c:forEach var="rk" items="${rankList}">
											<span>
												<input class="form-check-input" type="checkbox" value="" id=""/>&nbsp;
												${rk.getCoco_name()}
											</span>&nbsp;
										</c:forEach>
									</div>
									
									<div class="form-check form-check-custom form-check-solid">
										직책&nbsp;&nbsp;
										<c:forEach var="pn" items="${positionList}">
											<span>
												<input class="form-check-input" type="checkbox" value="" id=""/>&nbsp;
												${pn.getCoco_name()}
											</span>&nbsp;
										</c:forEach>
									</div>
								</div>
							</div>
						</div>
						
						<div class="card card-flush h-md-50 mb-xl-10">
							<div class="card-body pt-5 table-responsive">
								<table id="emplListTable" class="table table-hover table-rounded table-flush">
									<thead>
										<tr class="fw-semibold fs-7 border-bottom border-gray-200 py-4">
											<th>사번</th>
											<th>성명</th>
											<th>부서명</th>
											<th>직위</th>
											<th>직책</th>
										</tr>
									</thead>
									<tbody>
									<c:forEach items="${lists}" var="emp">
										<tr class="py-5 fw-semibold  border-bottom border-gray-300 fs-6" style="cursor: pointer;" onclick="location.href='/hr/employee/modify.do?empl_id=${emp.empl_id}'">
											<td>${emp.empl_id}</td>
											<td>${emp.empl_name}</td>
											<td>${emp.coco_name_dnm}</td>
											<td>${emp.coco_name_rnm}</td>
											<td>${emp.coco_name_pnm}</td>
										</tr>
									</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
						
						
					</div>
				</div>
				<!-- 내용 끝 -->
			</div>
		</div>
			
<%@include file="/WEB-INF/views/menu/hrSideMenu.jsp" %>		
</body>
<script type="text/javascript">

	$(document).ready(function(){
		$('#emplListTable').DataTable({
	        lengthChange: false,
	        info: false
		});
	});

	
	
</script>
</html>