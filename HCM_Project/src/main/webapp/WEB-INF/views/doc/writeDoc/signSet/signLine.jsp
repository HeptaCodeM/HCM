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
th, td {
	text-align: center;
	height: 27px !important;
	padding: 5px;
}
td>input {
	text-align: center;
	height: 20px;
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

			<br>
			<!-- 로그인세션영역 -->
			<input type="hidden" value="${userInfoVo.empl_id}" id="empl_id"> 
			<input type="hidden" value="${userInfoVo.empl_rank_nm}" id="positionFlag">
			<!-- OJS -->	
			<div class="app-container container-fulid">
				<div class="row gx-5 gx-xl-10">
				<div class="col-xxl-5 mb-5 mb-xl-10">
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
										<input type="text" id="schName" spellcheck="false" class="form-control" placeholder="검색할 사원을 입력하세요" style="height: 36px; display: inline;"> 
										<span class="input-group-btn">
											<button class="btn btn-default btn-sm" type="button" id="schBtn" style="margin-left: 1px;">
												<span class="ki-solid ki-magnifier fs-1"></span>
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
				
				<div class="col-xxl-7 mb-5 mb-xl-10">
					<div class="card card-flush h-md-100 mb-xl-10">
						<div class="card-header pt-5" style="min-height: 40px;">
							<h3 class="card-title text-gray-800 fw-bold">결재라인</h3>
						</div> 
						<div class="separator separator-dashed my-3"></div>	
						<div class="card-body pt-5">
							<div class="app-main flex-column flex-row-fluid" id="kt_app_main">
								<div class="d-flex flex-column flex-column-fluid">
									<div id="kt_app_content" class="app-content flex-column-fluid">
								
										<table class="table-responsive" style="width: 90%; margin: 0 auto;">
											<thead>
												<tr class="fw-bold fs-6 text-gray-800">
													<th width="5%"></th>
													<th width="30%">결재자</th>
													<th width="20%">직급</th>
													<th width="20%">소속</th>
													<th width="20%">순서</th>
													<th style="display: none;" id="empl_id"></th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td></td>
													<td>
														<input class="form-control form-control-solid" value="${userInfoVo.empl_name}" readonly>
													</td>
													<td>
														<input class="form-control form-control-solid" value="${userInfoVo.empl_rank_nm}" readonly>
													</td>
													<td>
														<input class="form-control form-control-solid" value="${userInfoVo.empl_dept_nm}" readonly>
													</td>
													<td>
														<input class="form-control form-control-solid" value="본인" readonly>
													</td>
													<td style="display: none;"></td>
												</tr>
												<tr>
													<td>
														<button class="cancelBtn btn btn-sm btn-basic">➖</button>
													</td>
													<td>
														<input class="form-control form-control-solid" id="first" readonly>
													</td>
													<td>
														<input class="form-control form-control-solid" id="rk1" readonly>
													</td>
													<td>
														<input class="form-control form-control-solid" id="dp1" readonly>
													</td>
													<td>
														<input class="form-control form-control-solid" value="1" id="de1" readonly>
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
														<input class="form-control form-control-solid" id="second" readonly>
													</td>
													<td>
														<input class="form-control form-control-solid" id="rk2" readonly>
													</td>
													<td>
														<input class="form-control form-control-solid" id="dp2" readonly>
													</td>
													<td>
														<input class="form-control form-control-solid" value="2" id="de2" readonly>
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
														<input class="form-control form-control-solid" id="third" readonly>
													</td>
													<td>
														<input class="form-control form-control-solid" id="rk3" readonly>
													</td>
													<td>
														<input class="form-control form-control-solid" id="dp3" readonly>
													</td>
													<td>
														<input class="form-control form-control-solid" value="3" id="de3" readonly>
													</td>
													<td style="display: none;">
														<span id="id3"></span>
													</td>
												</tr>
											</tbody>
										</table>
										<br>
										<div style="text-align: right;">
											<input type="text" id="favoName" placeholder="별칭 입력" maxlength="20" class="form-control bg-transparent" style="width: 40%; display: inline; height: 36px;">
											<input type="button" class="btn btn-light-primary btnSm btn-color-gray-600" id="addLine" value="등록">
											<input type="button" class="btn btnSm btn-light-primary btn-color-gray-600" id="initial" value="초기화" style="margin-right: 40px;">
										</div>
								
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				
				<div class="col-xxl-6 mb-5 mb-xl-10">
					<div class="card card-flush h-md-100 mb-xl-10">
						<div class="card-header pt-5" style="min-height: 40px;">
							<h3 class="card-title text-gray-800 fw-bold">즐겨찾는 라인</h3>
						</div> 
						<div class="separator separator-dashed my-3"></div>	
						<div class="card-body pt-5">
							<div class="app-main flex-column flex-row-fluid" id="kt_app_main">
								<div class="d-flex flex-column flex-column-fluid">
									<div id="kt_app_content" class="app-content flex-column-fluid">
											<select id="apprLineList" class="form-select form-select-sm" style="height: 40px;" data-control="select2">
												<option selected>결재선을 선택해주세요</option>
											</select>
											<br><br>
											<button type="button" class="btn btn-light-primary btnSm btn-color-gray-600" id="kt_button_1" name="selectApprLine">
											    <span class="indicator-label">
											        적용
											    </span>
											    <span class="indicator-progress">
											        Please wait... <span class="spinner-border spinner-border-sm align-middle ms-2"></span>
											    </span>
											</button>
											<button type="button" class="btn btn-light-primary btnSm btn-color-gray-600" id="delLineBtn">
											    삭제
											</button>
								
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				
				<div class="col-xxl-6 mb-5 mb-xl-10">
					<div class="card card-flush h-md-100 mb-xl-10">
						<div class="card-header pt-5" style="min-height: 40px;">
							<h3 class="card-title text-gray-800 fw-bold">즐겨찾는 결재자</h3>
						</div> 
						<div class="separator separator-dashed my-3"></div>	
						<div class="card-body pt-5">
							<div class="app-main flex-column flex-row-fluid" id="kt_app_main">
								<div class="d-flex flex-column flex-column-fluid">
									<div id="kt_app_content" class="app-content flex-column-fluid">
											<div>
											<select id="apprList" class="form-select form-select-sm" style="height: 40px;" data-control="select2">
												<option selected>결재자를 선택해주세요</option>
											</select>
											</div>
											<br>
											<div style="text-align: left">
											<button type="button" class="btn btn-light-primary btnSm btn-color-gray-600" id="kt_button_1" name="insBtn">
											    <span class="indicator-label">
											        추가
											    </span>
											    <span class="indicator-progress">
											        Please wait... <span class="spinner-border spinner-border-sm align-middle ms-2"></span>
											    </span>
											</button>
											<button type="button" class="btn btn-light-primary btnSm btn-color-gray-600" id="delBtn">
											    삭제
											</button>
											</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			<div>
				<div style="text-align: center;">
					<button type="button" class="btn btn-light-info btnMd btn-color-gray-600" id="saveLineBtn">저장</button>
					<button type="button" class="btn btn-light-info btnMd btn-color-gray-600" id="cancelBtn">취소</button>
				</div>
				<br><br>
			</div>

		</div>
				
			<!-- OJS  -->	
			</div>
</body>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/jstree.min.js"></script>
<script type="text/javascript" src="/js/doc/signLine.js"></script>
</html>