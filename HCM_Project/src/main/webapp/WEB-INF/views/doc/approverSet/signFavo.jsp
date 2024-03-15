<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/themes/default/style.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css">
<%@include file="/WEB-INF/views/menu/headerInfo.jsp" %>
<title>DOC메인화면</title>
<style type="text/css">
th,td {
	text-align: center;
}
td>input {
	text-align: center;
	height: 28px;
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
						<h1 class="page-heading d-flex text-gray-900 fw-bold fs-3 flex-column justify-content-center my-0">결재선 관리</h1>
						<button type="button" id="signRefer">참조</button>
						<button type="button" id="fileTest">파일테스트</button>
						<!--end::Title-->
					</div>
					<!--end::Page title-->
				</div>		
			</div>
			
			<!-- 로그인세션영역 -->
			<input type="hidden" value="${userInfoVo.empl_id}" id="empl_id"> 
			<input type="hidden" value="${userInfoVo.coco_name_rnm}" id="positionFlag">
			<!-- OJS -->	
			<div class="app-container container-fulid">
				<div class="row gx-5 gx-xl-10">
				<div class="col-xxl-3 mb-5 mb-xl-10">
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
											<button class="btn btn-default btn-sm" type="button" id="schBtn" style="margin-left: 1px;">
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
				
				<div class="col-xxl-5 mb-5 mb-xl-10">
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
													<th width="5%"></th>
													<th width="30%">결재자</th>
													<th width="20%">직급</th>
													<th width="20%">소속</th>
													<th width="20%">순서</th>
													<th style="display: none;" id="empl_id">20230108</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td></td>
													<td>
														<input class="form-control form-control-solid" value="${userInfoVo.empl_name}">
													</td>
													<td>
														<input class="form-control form-control-solid" value="${userInfoVo.empl_rank_nm}">
													</td>
													<td>
														<input class="form-control form-control-solid" value="${userInfoVo.empl_dept_nm}">
													</td>
													<td>
														<input class="form-control form-control-solid" value="본인">
													</td>
													<td style="display: none;"></td>
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
											<input type="text" id="favoName" placeholder="별칭 입력" maxlength="20" class="col-xs-3" style="float: none;">
											<input type="button" class="btn btn-primary btnMd" id="addLine" value="등록">
											<input type="button" class="btn btnMd btn-primary" id="initial" value="초기화" style="margin-right: 40px;">
										</div>
								
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				
				<div class="col-xxl-4 mb-5 mb-xl-10">
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
											<div>
											<select id="apprList" class="form-select form-select-sm" style="height: 40px;" data-control="select2">
												<option selected>결재자를 선택해주세요</option>
											</select>
											</div>
											<br>
											<div style="text-align: left">
											<button type="button" class="btn btn-primary btnSm" id="kt_button_1" name="insBtn" style="height: 32px; line-height: 14px; width: 100px;">
											    <span class="indicator-label">
											        추가
											    </span>
											    <span class="indicator-progress">
											        Please wait... <span class="spinner-border spinner-border-sm align-middle ms-2"></span>
											    </span>
											</button>
											<button type="button" class="btn btn-primary btnSm" id="delBtn" style="height: 32px; line-height: 14px; width: 100px;">
											    삭제
											</button>
											</div>
										</div>
										<br><br>
										<div class="separator separator-dashed my-3"></div>
										<br><br>
										<div>
											<select id="apprLineList" class="form-select form-select-sm" style="height: 40px;" data-control="select2">
												<option selected>결재선을 선택해주세요</option>
											</select>
											<br><br>
											<button type="button" class="btn btn-primary btnMd" id="kt_button_1" name="selectApprLine" style="height: 32px; line-height: 14px; width: 100px;">
											    <span class="indicator-label">
											        적용
											    </span>
											    <span class="indicator-progress">
											        Please wait... <span class="spinner-border spinner-border-sm align-middle ms-2"></span>
											    </span>
											</button>
											<button type="button" class="btn btn-primary btnMd" id="delLineBtn" style="height: 32px; line-height: 14px; width: 100px;">
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
				
				
				
			<!-- OJS  -->	
			</div>
		</div>

	<%@include file="/WEB-INF/views/menu/docSideMenu.jsp" %>
	<div class="modal fade" tabindex="-1" id="kt_modal_3">
		<div class="modal-dialog">
			<div class="modal-content position-absolute">
				<div class="modal-header">
					<h5 class="modal-title">즐겨찾기 결재자 삭제</h5>

					<!--begin::Close-->
					<div class="btn btn-icon btn-sm btn-active-light-primary ms-2"
						data-bs-dismiss="modal" aria-label="Close">
						<i class="ki-duotone ki-cross fs-2x"><span class="path1"></span><span
							class="path2"></span></i>
					</div>
					<!--end::Close-->
				</div>

				<div class="modal-body">
					<p>선택한 항목을 삭제할까요?</p>
				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-secondary me-10" data-bs-dismiss="modal" id="can">취소</button>
					<button type="button" class="btn btn-primary me-10" data-bs-dismiss="modal" id="con">확인</button>
				</div>
			</div>
		</div>
	</div>		
</body>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/jstree.min.js"></script>
<script type="text/javascript" src="/js/doc/signFavo.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</html>