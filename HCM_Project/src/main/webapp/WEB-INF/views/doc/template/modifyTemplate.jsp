<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/menu/headerInfo.jsp"%>
<title>DOC메인화면</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/themes/default/style.min.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css">
</head>
<%@include file="/WEB-INF/views/menu/header.jsp"%>
<body id="kt_app_body" data-kt-app-layout="dark-sidebar"
	data-kt-app-header-fixed="true" data-kt-app-sidebar-enabled="true"
	data-kt-app-sidebar-fixed="true" data-kt-app-sidebar-hoverable="true"
	data-kt-app-sidebar-push-header="true"
	data-kt-app-sidebar-push-toolbar="true"
	data-kt-app-sidebar-push-footer="true"
	data-kt-app-toolbar-enabled="true" class="app-default">

	<div class="app-wrapper flex-column flex-row-fluid"> <!-- 전체페이지 감싸기 -->
		<!-- 메인페이지 -->
			<div class="app-toolbar py-3 py-lg-6">
				<div id="kt_app_toolbar_container" class="app-container container-fluid d-flex flex-stack">
					<!--begin::Page title-->
					<div class="page-title d-flex flex-column justify-content-center flex-wrap me-3">
						<!--begin::Title-->
						<h1 class="page-heading d-flex text-gray-900 fw-bold fs-3 flex-column justify-content-center my-0">글작성</h1>
						<!--end::Title-->
					</div>
					<!--end::Page title-->
				</div>		
			</div>
			<div class="app-content flex-column-fluid">
				<!-- 내용 시작 -->
				<div id="kt_app_content" class="app-content flex-column-fluid">
				
				<!-- 컨테이너 -->
					<div class="app-container container-fluid">
					
					<!-- row -->
					<!-- col -->
						<div class="card card-flush h-md-50 mb-xl-10">
							<div class="card-header pt-5">
								<h3 class="card-title text-gray-800 fw-bold">작성</h3>
							</div>
							<div class="separator separator-dashed my-3"></div>	
							<div class="card-body pt-5">
							
							
							
		<h1>템플릿 수정</h1>
		<form action="./updateTemplate.do" method="post" >
			<div id="category">
			결재 구분 : <select id="selectCategory" name="sica_cd"></select>
		</div>
			<h3>제목 <input type="text" name="sidt_temp_name" value="${temDto.sidt_temp_name }"></h3>
			<input type="hidden" name="sidt_temp_cd" value="${temDto.sidt_temp_cd }">
			<input type="hidden" id="sica_cd" value="${temDto.sica_cd }">
			<textarea id="editor" name="sidt_temp_content">${temDto.sidt_temp_content}</textarea>
		<div class="btn">
<!-- 		<input type="button" onclick="updateTemplate()" value="수정완료"> -->
		<input type="submit" value="수정완료"> 
		</div>
		</form>
		</div>
						</div>
						
					</div>
				</div>
				<!-- 내용 끝 -->
			</div>
		</div>

	<%@include file="/WEB-INF/views/menu/docSideMenu.jsp"%>
</body>

<script type="text/javascript" src="/ckeditor5/build/ckeditor.js"></script>
<script type="module" src="/ckeditor5/sample/script.js"></script>
<script type="text/javascript" src="/js/doc/template.js"></script>
</html>