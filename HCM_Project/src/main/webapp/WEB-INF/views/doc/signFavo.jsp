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
th {
	text-align: center;
}
td>input {
	text-align: center;
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
						<h1 class="page-heading d-flex text-gray-900 fw-bold fs-3 flex-column justify-content-center my-0">결재선 관리</h1>
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
							<h3 class="card-title text-gray-800 fw-bold">즐겨찾기 등록</h3>
						</div> 
						<div class="separator separator-dashed my-3"></div>	
						<div class="card-body pt-5">
							<div class="app-main flex-column flex-row-fluid" id="kt_app_main">
								<div class="d-flex flex-column flex-column-fluid">
									<div id="kt_app_content" class="app-content flex-column-fluid">
										<div>
											<select id="apprList" class="form-select" style="height: 40px;">
												<option selected>결재자를 선택해주세요</option>
											</select>
											<br>
											<button type="button" class="btn btn-primary me-10" id="kt_button_1" name="insBtn" style="height: 32px; line-height: 14px;">
											    <span class="indicator-label">
											        추가
											    </span>
											    <span class="indicator-progress">
											        Please wait... <span class="spinner-border spinner-border-sm align-middle ms-2"></span>
											    </span>
											</button>
											<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#kt_modal_3" id="delBtn" style="height: 32px; line-height: 14px;">
											    삭제
											</button>
										</div>
										<br><br>
										<div class="separator separator-dashed my-3"></div>
										<br><br>
										<div>
											<select id="apprLineList" class="form-select" style="height: 40px;">
												<option selected>결재선을 선택해주세요</option>
											</select>
											<br>
											<button type="button" class="btn btn-primary me-10" id="kt_button_1" name="selectApprLine" style="height: 32px; line-height: 14px;">
											    <span class="indicator-label">
											        적용
											    </span>
											    <span class="indicator-progress">
											        Please wait... <span class="spinner-border spinner-border-sm align-middle ms-2"></span>
											    </span>
											</button>
											<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#kt_modal_3" id="delLineBtn" style="height: 32px; line-height: 14px;">
											    삭제
											</button>
										</div>
								
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				</div>
			</div>
			
			<div class="app-container container-fulid">
					<div class="card card-flush h-md-100 mb-xl-10">
						<div class="card-header pt-5">
							<h3 class="card-title text-gray-800 fw-bold">결재라인</h3>
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
													<th>결재자</th>
													<th>직급</th>
													<th>소속</th>
													<th>순서</th>
													<th style="display: none;" id="empl_id">20230108</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td></td>
													<td>
														<input class="form-control form-control-solid" value="전민균">
													</td>
													<td>
														<input class="form-control form-control-solid" value="인턴">
													</td>
													<td>
														<input class="form-control form-control-solid" value="개발팀">
													</td>
													<td>
														<input class="form-control form-control-solid" value="기안자">
													</td>
													<td style="display: none;" id="empl_id"></td>
												</tr>
												<tr>
													<td>
														<button class="cancelBtn btn btn-sm btn-basic">➖</button>
													</td>
													<td>
														<input class="form-control form-control-solid" id="first">
													</td>
													<td>
														<input class="form-control form-control-solid" id="rk1">
													</td>
													<td>
														<input class="form-control form-control-solid" id="dp1">
													</td>
													<td>
														<input class="form-control form-control-solid" value="1" id="de1">
													</td>
													<td style="display: none;">
														<span id="id1"></span>
													</td>
												</tr>
												<tr>
													<td>
														<button class="cancelBtn btn btn-sm btn-basic">➖</button>
													</td>
													<td>
														<input class="form-control form-control-solid" id="second">
													</td>
													<td>
														<input class="form-control form-control-solid" id="rk2">
													</td>
													<td>
														<input class="form-control form-control-solid" id="dp2">
													</td>
													<td>
														<input class="form-control form-control-solid" value="2" id="de2">
													</td>
													<td style="display: none;">
														<span id="id2"></span>
													</td>
												</tr>
												<tr>
													<td>
														<button class="cancelBtn btn btn-sm btn-basic">➖</button>
													</td>
													<td>
														<input class="form-control form-control-solid" id="third">
													</td>
													<td>
														<input class="form-control form-control-solid" id="rk3">
													</td>
													<td>
														<input class="form-control form-control-solid" id="dp3">
													</td>
													<td>
														<input class="form-control form-control-solid" value="3" id="de3">
													</td>
													<td style="display: none;">
														<span id="id3"></span>
													</td>
												</tr>
											</tbody>
										</table>
										<br>
										<div style="text-align: right;">
											<input type="text" id="favoName" placeholder="별칭 입력" maxlength="20">
											<input type="button" class="btn btn-sm btn-primary" id="addLine" value="결재선 추가" style="margin-right: 50px;">
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
<script type="text/javascript" src="../js/signFavo.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</html>