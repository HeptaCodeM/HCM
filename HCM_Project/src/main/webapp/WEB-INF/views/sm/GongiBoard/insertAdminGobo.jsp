<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/menu/headerInfo.jsp" %>
<title>HCM GroupWare</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  

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
						<h1 class="page-heading d-flex text-gray-900 fw-bold fs-3 flex-column justify-content-center my-0">공지사항 글 작성</h1>
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
							<div class="contatiner">
					      <form id="insertForm">
					         <div class="form-group">
					            <label for="title">제목</label>
					            <input class="form-control" id="gobo_title"  name="gobo_title">
					         </div>
					         <div class="form-group">
					            <label for="id">내용</label>
					            <textarea class="form-control" rows="5" id="editor"  name="gobo_content" required="required"></textarea>
					         </div>
					         <div class="form-group">
					            <label for="id">비고</label>
					            <textarea class="form-control" rows="1" id="gobo_bigo" name="gobo_bigo" required="required"></textarea>
					         </div>
					         <div>
					         </div>
					            <input type="button" class="btn btn-success" value="글작성"  id="savebutton">
					            <input type="reset" class="btn btn-danger" value="글초기화" id="resetbutton">
					            <input type="button" class="btn btn-info" value="뒤로가기" onclick="history.back(-1)">
					      </form>
					   </div>
							</div>
						</div>
					</div>
				</div>
				<!-- 내용 끝 -->
			</div>
		</div>
			
<%@include file="/WEB-INF/views/menu/smSideMenu.jsp"%>	
<script type="text/javascript" src="/ckeditor5/build/ckeditor.js"></script>
<script type="text/javascript" src="/js/sm/smart.js"></script>
<script type="module" src="/ckeditor5/sample/script.js"></script>

</body>

</html>