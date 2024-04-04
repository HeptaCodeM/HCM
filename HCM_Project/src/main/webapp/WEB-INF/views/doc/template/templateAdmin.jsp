<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/menu/headerInfo.jsp"%>
<title>관리자메뉴</title>
<style type="text/css">
.btnMd {
    width: 120px;
    height: 35px;
    line-height: 12px;
    position: absolute;
    right: 55px;
}

</style>
</head>
<%@include file="/WEB-INF/views/menu/header.jsp"%>
<body id="kt_app_body" data-kt-app-layout="dark-sidebar"
	data-kt-app-header-fixed="true" data-kt-app-sidebar-enabled="true"
	data-kt-app-sidebar-fixed="true" data-kt-app-sidebar-hoverable="true"
	data-kt-app-sidebar-push-header="true"
	data-kt-app-sidebar-push-toolbar="true"
	data-kt-app-sidebar-push-footer="true"
	data-kt-app-toolbar-enabled="true" class="app-default">

	<div class="app-wrapper flex-column flex-row-fluid">
		<div class="app-toolbar py-3 py-lg-6">
			<div id="kt_app_toolbar_container"
				class="app-container container-fluid d-flex flex-stack">
				<!-- Page Title -->
				<div class="page-title d-flex flex-column justify-content-center flex-wrap me-3">
					<h1 class="page-heading d-flex text-gray-900 fw-bold fs-3 flex-column justify-content-center my-0">
						관리자 메뉴
					</h1>
				</div>
				<!-- Page Title -->
			</div>
		</div>
		<div class="app-content flex-column-fluid">
			<div id="kt_app_content" class="app-content flex-column-fluid">
				<div class="app-container container-fluid">
					<div class="card card-flush h-md-50 mb-xl-10">
						<!-- Page Title -->
						<div class="card-header pt-5">
							<h3 class="card-title text-gray-800 fw-bold">템플릿 리스트</h3>
						</div>
						<!-- Page Title -->
						<div class="separator separator-dashed my-3"></div>
						<div class="card-body pt-5">
							<!-- Content -->
							<div class="table-responsive">
								<button class="writeTemplate btn btn-primary btnMd" >템플릿 등록</button>
							</div>
							<div class="card-body pt-5">
							<div class="table-responsive">
							<table class="table table-hover table-rounded table-striped border gy-7 gs-7" style="margin-top: 40px;">
								<thead>
									<tr class="fw-semibold fs-6 text-gray-800 border-bottom-2 border-gray-200">
										<th>템플릿코드</th>
										<th>템플릿명</th>
										<th>활성여부</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="lst" items="${lst}" varStatus="vs">
										<tr style="cursor: pointer;" class="py-5 fw-semibold  border-bottom border-gray-300 fs-6">
											<td>${lst.sidt_temp_cd}</td>
											<td>
												<a href="/doc/detailTemplateAdmin.do?sidt_temp_cd=${lst.sidt_temp_cd}">${lst.sidt_temp_name}</a>
											</td>
											<td>${lst.sidt_temp_flag}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<%@include file="/WEB-INF/views/menu/docSideMenu.jsp"%>
</body>
<script type="text/javascript">
	var writebtn = document.querySelector('.writeTemplate');
	writebtn.addEventListener("click", function() {
		window.location.href = "/doc/writeTemplateAdmin.do";
	});
</script>
</html>