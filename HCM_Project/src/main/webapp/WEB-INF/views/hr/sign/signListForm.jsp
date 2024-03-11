<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/menu/headerInfo.jsp" %>
<title>서명리스트화면</title>
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
						<h1 class="page-heading d-flex text-gray-900 fw-bold fs-3 flex-column justify-content-center my-0">서명</h1>
						<!--end::Title-->
					</div>
					<!--end::Page title-->
				</div>		
			</div>
			<div class="app-content flex-column-fluid">
			
				<c:forEach var="signList" items="${signList}" varStatus="var">
					<!-- 내용 시작 -->
					<div id="kt_app_content" class="app-content flex-column-fluid">
						<div class="app-container container-fluid">
							<div class="card card-flush h-md-50 mb-xl-10">
								<div class="card-header pt-5">
									<h3 class="card-title text-gray-800 fw-bold">NO.${var.count} &nbsp;${signList.getEmsi_title()}</h3>
								</div>
								<div class="separator separator-dashed my-3"></div>	
								<div class="card-body pt-5" >
										<img src="${signList.getEmsi_sign_img()}"><br>
								</div>
								<div class="card-footer">
									<a class="btn btn-primary me-10" href="./delThisSign.do?emsi_seq=${signList.getEmsi_seq()}">삭제</a>
							    </div>
							</div>
						</div>
					</div>
					<!-- 내용 끝 -->
				</c:forEach>
				<div class="app-container container-fluid">
					<div>
						<button class="btn btn-primary me-10" type="button" onclick="location.href='./insertSignForm.do'">서명추가</button>
					</div>
				</div>
			</div>
		</div>
			
<%@include file="/WEB-INF/views/menu/hrSideMenu.jsp" %>		
</body>
</html>