<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/menu/headerInfo.jsp"%>
<title>HCM GroupWare</title>

<style type="text/css">
.table th {
	vertical-align: middle;
}

.form-check-input.radio {
	margin-right: 10px;
}

.form-check-input.radio.last {
	margin-left: 30px;
}
</style>

</head>
<%@include file="/WEB-INF/views/menu/header.jsp"%>
<body id="kt_app_body" data-kt-app-layout="dark-sidebar" data-kt-app-header-fixed="true"
	data-kt-app-sidebar-enabled="true" data-kt-app-sidebar-fixed="true" data-kt-app-sidebar-hoverable="true"
	data-kt-app-sidebar-push-header="true" data-kt-app-sidebar-push-toolbar="true" data-kt-app-sidebar-push-footer="true"
	data-kt-app-toolbar-enabled="true" class="app-default">

	<div class="app-wrapper flex-column flex-row-fluid">
		<div class="app-toolbar py-3 py-lg-6">
			<div id="kt_app_toolbar_container" class="app-container container-fluid d-flex flex-stack">
				<!--begin::Page title-->
				<div class="page-title d-flex flex-column justify-content-center flex-wrap me-3">
					<!--begin::Title-->
					<h1 class="page-heading d-flex text-gray-900 fw-bold fs-3 flex-column justify-content-center my-0">인사관리 > 근태관리
						> 출퇴근 등록</h1>
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
						<div class="card-header pt-5" style="display: block; margin:100px auto 120px; text-align:center;">
							<c:if test="${commuteInTime eq null || commuteInTime eq ''}">
								<a href="/hr/commute/registCommuteOk.do?mobile=${mobile}" class="btn btn-primary fs-3x">
									<i class="ki-duotone ki-entrance-left fs-5x">
										<span class="path1"></span>
										<span class="path2"></span>
										<span class="path3"></span>
										<span class="path4"></span>
									</i>
									출근등록
								</a>
							</c:if>
							<c:if test="${commuteInTime ne null && commuteInTime ne ''}">
								<div style="padding:20px 0;">
									<h4>출근등록 : <fmt:formatDate value="${commuteInTime}" pattern="yyyy-MM-dd HH:mm:ss" /></h4>
								</div>
								<c:if test="${commuteOutTime ne null && commuteOutTime ne ''}">
								<div style="padding:0 0 20px;">
									<h4>퇴근등록 : <fmt:formatDate value="${commuteOutTime}" pattern="yyyy-MM-dd HH:mm:ss" /></h4>
								</div>
								</c:if>
								<a href="/hr/commute/registCommuteOk.do" class="btn btn-primary fs-3x">
									<i class="ki-duotone ki-exit-right fs-5x">
										<span class="path1"></span>
										<span class="path2"></span>
										<span class="path3"></span>
										<span class="path4"></span>
									</i>
									퇴근등록
								</a>
							</c:if>
						</div>
					</div>
				</div>
			</div>
			<!-- 내용 끝 -->
		</div>
	</div>

	<%@include file="/WEB-INF/views/menu/hrSideMenu.jsp"%>
</body>
</html>