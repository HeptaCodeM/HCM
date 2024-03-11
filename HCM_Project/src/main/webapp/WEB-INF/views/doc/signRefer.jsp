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
<style type="text/css">
th,td {
	text-align: center;
}
td>input {
	text-align: center;
	height: 28px;
}
td>button {
	padding: 1px;
}
td>img {
	height: 32px;
}
</style>
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
						<h1 class="page-heading d-flex text-gray-900 fw-bold fs-3 flex-column justify-content-center my-0">참조 등록</h1>
						<!--end::Title-->
					</div>
					<!--end::Page title-->
				</div>		
			</div>
			
			<!-- 로그인세션영역 -->
			<input type="hidden" value="20230108" id="empl_id"> 
			<input type="hidden" value="대리" id="positionFlag">
			
			<!-- OJS -->	
			<div class="app-container container-fulid">
				<div class="row gx-5 gx-xl-10">
				<div class="col-xxl-4 mb-5 mb-xl-10">
					<div class="card card-flush h-md-100 mb-xl-10">
						<div class="card-header pt-5">
							<h3 class="card-title text-gray-800 fw-bold">사원 검색</h3>
						</div> 
						<div class="separator separator-dashed my-3"></div>	
						<div class="card-body pt-5" style="overflow: auto;">
							<div class="app-main flex-column flex-row-fluid" id="kt_app_main">
								<div class="d-flex flex-column flex-column-fluid">
									<div id="kt_app_content" class="app-content flex-column-fluid">
										<div class="input-group">
										<input type="text" id="schName" spellcheck="false" class="form-control" placeholder="검색할 사원을 입력하세요"> 
										<span class="input-group-btn">
											<button class="btn btn-default btn-sm" type="button" id="schBtn">
												<span class="glyphicon glyphicon-search"></span>
											</button>
										</span>
										</div>
										<br>
										<div id="jstree"></div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-xxl-8 mb-5 mb-xl-10">
					<div class="card card-flush h-md-100 mb-xl-10">
						<div class="card-header pt-5">
							<h3 class="card-title text-gray-800 fw-bold">참조인 설정</h3>
						</div> 
						<div class="separator separator-dashed my-3"></div>	
						<div class="card-body pt-5">
							<div class="app-main flex-column flex-row-fluid" id="kt_app_main">
								<div class="d-flex flex-column flex-column-fluid">
									<div id="kt_app_content" class="app-content flex-column-fluid">
								
										<table class="table table-condensed" style="width: 90%; margin: 0 auto;">
											<thead>
												<tr>
													<th></th>
													<th></th>
													<th width="40%">참조자</th>
													<th width="30%">직급</th>
													<th width="30%">소속</th>
													<th style="display: none;" id="empl_id">20230108</th>
												</tr>
											</thead>
											<tbody id="tbody1">
												<tr>
													<td>
														<button class="cancelBtn btn btn-sm btn-basic" id="addRow1">➕</button>
													</td>
													<td>
														<button class="cancelBtn btn btn-sm btn-basic">➖</button>
													</td>
													<td>
														<input class="form-control form-control-solid" id="re1">
													</td>
													<td>
														<input class="form-control form-control-solid" id="rk1">
													</td>
													<td>
														<input class="form-control form-control-solid" id="dp1">
													</td>
													<td style="display: none;">
														<span id="rid1"></span>
													</td>
												</tr>
											</tbody>
										</table>
								
								
									</div>
								</div>
							</div>
						</div>
						<div class="card-header pt-5">
							<h3 class="card-title text-gray-800 fw-bold">참조부서 설정</h3>
						</div> 
						<div class="separator separator-dashed my-3"></div>	
						<div class="card-body pt-5">
							<div class="app-main flex-column flex-row-fluid" id="kt_app_main">
								<div class="d-flex flex-column flex-column-fluid">
									<div id="kt_app_content" class="app-content flex-column-fluid">
								
										<table class="table table-condensed" style="width: 90%; margin: 0 auto;">
											<thead>
												<tr>
													<th width="2%"></th>
													<th width="2%"></th>
													<th>부서명 (팀명)</th>
													<th style="display: none;" id="empl_id">20230108</th>
												</tr>
											</thead>
											<tbody id="tbody2">
												<tr>
													<td>
														<button class="cancelBtn btn btn-sm btn-basic" id="addRow2">➕</button>
													</td>
													<td>
														<button class="cancelBtn btn btn-sm btn-basic">➖</button>
													</td>
													<td>
														<input class="form-control form-control-solid" id="rd1">
													</td>
													<td style="display: none;">
														<span id="rde1"></span>
													</td>
												</tr>
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
				
			<!-- OJS  -->	
			</div>
			
<%@include file="/WEB-INF/views/menu/docSideMenu.jsp" %>		
</body>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/jstree.min.js"></script>
<script type="text/javascript" src="../js/signTree.js"></script>
<script type="text/javascript" src="../js/signRefer.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</html>