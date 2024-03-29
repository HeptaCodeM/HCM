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
					<h1 class="page-heading d-flex text-gray-900 fw-bold fs-3 flex-column justify-content-center my-0">인사관리 > 조직관리
						> 간편로그인 관리</h1>
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

							<table class="table">
								<c:if test="${snsInfo eq ''}">
								<tr>
									<td>
										<div class="row g-3">
											<div class="d-grid">
												<a href="javascript:void(0);" onclick="window.open('${naverSnsUrl}','naverLogin','width=500,height=500,toolbars=no,statebar=no')" class="btn btn-flex btn-outline btn-text-gray-700 btn-active-color-primary bg-state-light flex-center text-nowrap w-100">
												<img alt="Logo" src="/image/miniNaver.png" class="theme-light-show h-15px me-3">Naver 간편로그인 등록</a>
											</div>
										</div>
									</td>
								</tr>
								</c:if>
								<c:if test="${snsInfo ne ''}">
								<tr>
									<td>
										<div class="row g-3">
											<div class="d-grid">
												<a href="javascript:void(0);" onclick="location.href='/hr/employee/delNaverSns.do';" class="btn btn-flex btn-outline btn-text-gray-700 btn-active-color-primary bg-state-light flex-center text-nowrap w-100">
												<img alt="Logo" src="/image/miniNaver.png" class="theme-light-show h-15px me-3">Naver 간편로그인 삭제</a>
											</div>
										</div>
									</td>
								</tr>
								<tr>
									<td>
										<fmt:formatDate var="snsRegDate" value="${snsInfo.emsn_create_dt}" pattern="yyyy-MM-dd HH:mm:ss" />
										등록일시 : ${snsRegDate}
									</td>
								</tr>
								</c:if>
							</table>
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