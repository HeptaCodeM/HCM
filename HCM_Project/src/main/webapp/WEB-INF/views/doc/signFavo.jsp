<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/menu/headerInfo.jsp" %>
<title>DOC메인화면</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/themes/default/style.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css">
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
						<h1 class="page-heading d-flex text-gray-900 fw-bold fs-3 flex-column justify-content-center my-0">결재선 관리</h1>
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
							<div class="card-header pt-5">
								<h3 class="card-title text-gray-800 fw-bold">소제목? 들어갑니다</h3>
							</div>
							<div class="separator separator-dashed my-3"></div>	
							<div class="card-body pt-5">
								<div class="app-main flex-column flex-row-fluid" id="kt_app_main">
						<!--begin::Content wrapper-->
						<div class="d-flex flex-column flex-column-fluid">
							<!--begin::Toolbar-->
							<div id="kt_app_content" class="app-content flex-column-fluid">
							
							<div class="container">
								
							</div>
							
							<div class="col-sm-5 sidenav">
							<input type="hidden" value="20230108" id="empl_id">
								<div>
									즐겨찾는 결재자<br> 
									<select id="apprList" style="width: 250px;">
										<option selected>결재자를 선택해주세요</option>
									</select>
									<button id="insBtn">추가</button>
									<button id="delBtn">삭제</button>
									<br><br><br>
								</div>
								<div class="input-group">
									<input type="text" id="schName" spellcheck="false" class="form-control" placeholder="검색할 사원을 입력하세요"> 
									<span class="input-group-btn">
										<button class="btn btn-default btn-sm" type="button" id="schBtn">
											<span class="glyphicon glyphicon-search"></span>
										</button>
									</span>
								</div>
							<div id="jstree"></div>
							
							</div>
							<div class="col-sm-5 sidenav">
								<div>
								결재라인 선택<br>
									<select id="apprLineList" style="width: 250px;">
										<option selected>결재선을 선택해주세요</option>
									</select>
									<button id="selectApprLine">적용</button>
									<button id="delLineBtn">삭제</button>
									<br><br><br>
									</div>
								<form method="post">
									<div class="input-group" style="width: 300px;">
										<input type="text" readonly class="form-control" id="first" value="">
										<span class="input-group-btn">
											<button class="btn btn-danger btn-sm cancelBtn">X</button>
										</span>
									</div>
									<hr>
									<div class="input-group" style="width: 300px;">
										<input type="text" readonly class="form-control" id="second" value="">
										<span class="input-group-btn">
											<button class="btn btn-danger btn-sm cancelBtn">X</button>
										</span>
									</div>
									<hr>
									<div class="input-group" style="width: 300px;">
										<input type="text" readonly class="form-control" id="third" value="">
										<span class="input-group-btn">
											<button class="btn btn-danger btn-sm cancelBtn">X</button>
										</span>
									</div>
									<hr>
									<div>
										<input type="button" class="btn btn-sm btn-info" id="accept" value="결재선 추가"> 
										<input type="button" class="btn btn-sm btn-info" value="결재선 관리" onclick="signFavo()">
									</div>
								</form>
							</div>
							</div>
							
							
						</div>
							</div>
						</div>
					</div>
				</div>
				<!-- 내용 끝 -->
			</div>
		</div>
		</div>
			
<%@include file="/WEB-INF/views/menu/docSideMenu.jsp" %>		
</body>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/jstree.min.js"></script>
<script type="text/javascript" src="../js/signTree.js"></script>
<script type="text/javascript" src="../js/signFavo.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</html>