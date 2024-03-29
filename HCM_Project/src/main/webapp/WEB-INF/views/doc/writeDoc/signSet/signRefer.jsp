<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/menu/headerInfo.jsp" %>
<title>참조자 등록 페이지</title>
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
<body id="kt_app_body" data-kt-app-layout="dark-sidebar"
		data-kt-app-header-fixed="true" data-kt-app-sidebar-enabled="true"
		data-kt-app-sidebar-fixed="true" data-kt-app-sidebar-hoverable="true"
		data-kt-app-sidebar-push-header="true"
		data-kt-app-sidebar-push-toolbar="true"
		data-kt-app-sidebar-push-footer="true"
		data-kt-app-toolbar-enabled="true" class="app-default">
		
		<input type="hidden" value="${userInfoVo.empl_id}" id="empl_id"> 
		<input type="hidden" value="${userInfoVo.empl_rank_nm}" id="positionFlag">

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
			
			<!-- OJS -->	
			<div class="app-container container-fulid">
				<div class="row gx-5 gx-xl-10">
				<div class="col-xxl-4 mb-5 mb-xl-10">
					<div class="card card-flush h-md-100 mb-xl-10">
						<div class="card-header pt-5" style="min-height: 40px;">
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
												<span class="ki-solid ki-magnifier fs-2qx"></span>
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
						<div class="card-header pt-5" style="min-height: 40px;">
							<h3 class="card-title text-gray-800 fw-bold">참조인 설정</h3>
						</div> 
						<div class="separator separator-dashed my-3"></div>	
						<div class="card-body pt-5">
							<div class="app-main flex-column flex-row-fluid" id="kt_app_main">
								<div class="d-flex flex-column flex-column-fluid">
									<div id="kt_app_content" class="app-content flex-column-fluid">
								
										<div id="ref-emp" class="input-group input-group-solid mb-5"></div>
								
									</div>
								</div>
							</div>
						</div>
						<div class="card-header pt-5" style="min-height: 40px;">
							<h3 class="card-title text-gray-800 fw-bold">참조부서 설정</h3>
						</div> 
						<div class="separator separator-dashed my-3"></div>	
						<div class="card-body pt-5">
							<div class="app-main flex-column flex-row-fluid" id="kt_app_main">
								<div class="d-flex flex-column flex-column-fluid">
									<div id="kt_app_content" class="app-content flex-column-fluid">
									
										<div id="ref-dept" class="input-group input-group-solid mb-5"></div>
									
										<br><br>
										<div style="text-align: center;">
											<button class="btn btnMd btn-light-primary btn-color-gray-600" id="saveRefer">저장</button>
											<button class="btn btnMd btn-light-primary btn-color-gray-600" id="closeRefer">닫기</button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					
				</div>
				</div>
			</div>
				
			<!-- OJS  -->	
</body>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/jstree.min.js"></script>
<script type="text/javascript" src="/js/doc/signRefer.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</html>