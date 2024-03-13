<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/menu/headerInfo.jsp"%>
<title>DOC메인화면</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/themes/default/style.min.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css">
<link rel="stylesheet"
	href="../ckeditor5/sample/template.css">
<style type="text/css">
	.wrapper-form,
	.wrapper-form > li {
		display: flex;
		flex-wrap: wrap;
		align-content: center;
		position: relative;
	}
	.whole-row{
	
	}
/* 	div.title{ */
/* 		width : 119px; */
/* 	} */
	div.form{
		width: 100%;
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

	<div class="app-wrapper flex-column flex-row-fluid"> <!-- 전체페이지 감싸기 -->
		<!-- 메인페이지 -->
			<div class="app-toolbar py-3 py-lg-6">
				<div id="kt_app_toolbar_container" class="app-container container-fluid d-flex flex-stack">
					<!--begin::Page title-->
					<div class="page-title d-flex flex-column justify-content-center flex-wrap me-3">
						<!--begin::Title-->
						<h1 class="page-heading d-flex text-gray-900 fw-bold fs-3 flex-column justify-content-center my-0">기안서 작성</h1>
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
								<h3 class="card-title text-gray-800 fw-bold">기안서 작성</h3>
							</div>
							<div class="separator separator-dashed my-3"></div>	
							<div class="card-body pt-5">
							
			<!-- ---------------------------- 내 용 입 력 -------------------------------- -->		
			<div>
			<form>
<!-- 				<div class="title">일자</div>
				<div class="form"><input type="date" name="sidb_doc_writedt"></div>
				<div class="title">알림여부</div>
				<div class="form"><input type="checkbox" name="sidb_doc_alflag"></div>
				<div class="title input-group mb-5">
					<span class="input-group">제목</span>
					<input type="text" name="sidb_doc_title" class="form-control">
				</div>
				<div class="title">결재라인</div>
				<div><input type="text"></div>
				<div class="title">참조</div> 
				<div><input type="text"></div>
				<div class="title">결재유형</div>
				<div>
					<select id="selectCategory" name="sica_cd"></select>
				</div>
				<div class="title">템플릿양식</div>
				<div><input type="text" name="sidt_temp_cd"></div>
				<div class="title">첨부파일</div>
				<div><input type="text"></div> -->
			<textarea id="editor" name="sidb_doc_content"></textarea>
			
			<div>
				<button>임시저장</button>
				<button>등록</button>
			</div>
			</form>
			</div>
	
			
			<!-- ---------------------------- 내 용 입 력 -------------------------------- -->

						</div>
						</div>
						
					</div>
				</div>
				<!-- 내용 끝 -->
			</div>
		</div>

	<%@include file="/WEB-INF/views/menu/docSideMenu.jsp"%>
</body>

<script type="text/javascript" src="../ckeditor5/build/ckeditor.js"></script>
<script type="module" src="../ckeditor5/sample/script.js"></script>
<script type="text/javascript" src="../js/template.js"></script>
</html>			