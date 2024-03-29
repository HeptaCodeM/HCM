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
						<h1 class="page-heading d-flex text-gray-900 fw-bold fs-3 flex-column justify-content-center my-0">서명관리</h1>
						<!--end::Title-->
					</div>
					<!--end::Page title-->
				</div>		
			</div>
			<div class="app-content flex-column-fluid">
			
					<!-- 내용 시작 -->
					<div id="kt_app_content" class="app-content flex-column-fluid">
						<div class="app-container container-fluid">
							<div class="card-flush h-md-50 mb-xl-10" style="background-color: white; text-align: center; width: 90%; margin: 0px auto;">
								<div class="card-header pt-5">
									<h3 class="card-title text-gray-800 fw-bold">보유중인 서명</h3><br>
								</div> 
								<div class="separator separator-dashed my-3"></div>	<br>
							<c:forEach var="sign" items="${signList}" varStatus="var">
								<div class="card-body pt-5" style="display: inline-block; width: 320px;" >
									<div class="border border-gray-400" style="height: 240px;">
									<img src="${sign.emsi_sign_img}"><br>
									 </div><br>
									<div class="card-footer" style="margin: 0px auto;">
										<c:if test="${sign.emsi_setflag eq 'Y'}">
											<span class="badge badge-info">대표</span>
										</c:if>
										<input id="seq" type="hidden" value="${sign.emsi_seq}">
										<input name="signDefault" type="checkbox" class="form-check-input" onclick="chkOnly(this)" value="${sign.emsi_seq}">
										<span class="badge ">${sign.emsi_title}</span>
							    	</div>
								</div>
								
							</c:forEach>
							<br><br>
							</div>
						</div>
					<div style="text-align: center;">
						<button class="btn btn-light-primary btn-color-gray-600" data-bs-toggle="modal" data-bs-target="#kt_modal_3">서명추가</button>
						<button class="btn btn-light-primary btn-color-gray-600" id="defaultBtn">기본서명설정</button>
						<button class="btn btn-light-primary btn-color-gray-600" id="delBtn">삭제</button>
					</div>
					</div>
			</div>
		</div>
		<div class="modal fade" tabindex="-1" id="kt_modal_3">
		<div class="modal-dialog">
			<div class="modal-content position-absolute">
				<div class="modal-header">
					<h5 class="modal-title">서명 추가</h5>

					<div class="btn btn-icon btn-sm btn-active-light-primary ms-2"
						data-bs-dismiss="modal" aria-label="Close">
						<i class="ki-duotone ki-cross fs-2x"><span class="path1"></span><span
							class="path2"></span></i>
					</div>
				</div>

				<div class="modal-body" style="margin: 0px auto;">

						<canvas id="signpad" width="320px" height="240px" class="border border-gray-400"></canvas>
						<br><br>
						<input class="form-control form-control-solid" type="text" name="emsi_title" id="emsi_title" placeholder="서명 제목을 입력하세요">
						<br>
						<input type="hidden"name="empl_id" id="empl_id" value="${userInfoVo.empl_id}">
						<input type="hidden" name="emsi_create_id" id="emsi_create_id" value="${userInfoVo.empl_id}">

				</div>

				<div class="modal-footer" style="margin: 0px auto;">
					<button class="btn btn-light-primary btn-color-gray-600" id="save">저장</button>
					<button class="btn btn-light-primary btn-color-gray-600" id="clear">지우기</button>
					<button class="btn btn-light-primary btn-color-gray-600" data-bs-dismiss="modal">취소</button>
				</div>
			</div>
		</div>
	</div>			
<%@include file="/WEB-INF/views/menu/docSideMenu.jsp" %>		
</body>
<script src="https://cdnjs.cloudflare.com/ajax/libs/signature_pad/1.5.3/signature_pad.min.js"></script>
<script type="text/javascript" src="/js/doc/signManagement.js"></script>
</html>