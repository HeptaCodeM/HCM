<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/menu/headerInfo.jsp"%>
<title>HCM GROUPWARE</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/themes/default/style.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css">
<link rel="stylesheet" href="/ckeditor5/sample/template.css">

<style type="text/css">
	/* 모달창 사이즈 조절 */
	.modal { 
		--bs-modal-width: 1200px;
	}
	/* 만료일 달력 높이 조절 */
	.form-control {
		height: 30px !important;
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
	data-kt-app-toolbar-enabled="true" class="app-default modal-open" style="overflow: auto; padding-right: 0px;">
	<div class="app-wrapper flex-column flex-row-fluid">
		<div class="app-toolbar py-3 py-lg-6">
			<div id="kt_app_toolbar_container" class="app-container container-fluid d-flex flex-stack">
				<div class="page-title d-flex flex-column justify-content-center flex-wrap me-3">
					<h1 class="page-heading d-flex text-gray-900 fw-bold fs-3 flex-column justify-content-center my-0">
						기안서 작성
					</h1>
				</div>
			</div>
		</div>

		<div class="app-content flex-column-fluid">
			<div id="kt_app_content" class="app-content flex-column-fluid">
				<div class="app-container container-fluid">
					<div class="card card-flush h-md-50 mb-xl-10">
						<div class="card-header pt-5">
							<h3 class="card-title text-gray-800 fw-bold">
								<span>기안서 작성</span>
							</h3>
						</div>
						<div class="separator separator-dashed my-3"></div>
						<div class="card-body pt-5">

							<!-- ---------------------------- 내 용 입 력 -------------------------------- -->
							
							<!-- ---------- 1. 템플릿양식 선택 모달화면 버튼 ---------- -->
							<div id="template_div" style="text-align: center;">
									결재 진행을 위해 양식을 선택해주세요. <br><br>
								<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#kt_modal_3">
									기안문 템플릿 선택하기
								</button>
							</div>
							
							<!-- ------------- 2. 기안문 작성 화면 ------------ -->
							<div id="editor_div" style="display: none; max-width: 80%; margin: 0px auto;">
							<!-- 정보 설정 : userInfo. company, holiday -->
							<input type="hidden" value="${userInfoVo.emsi_seq }" id="emsi_seq">
							<input type="hidden" value="${userInfoVo.empl_birth }" id="empl_birth">
							<input type="hidden" value="${userInfoVo.empl_joindate }" id="empl_joindate">
							<input type="hidden" value="${com.comp_name }" id="comp_name">
							<input type="hidden" value="${com.comp_num }" id="comp_num">
							<input type="hidden" value="${com.comp_ceo_name }" id="comp_ceo_name">
							<input type="hidden" value="${com.comp_tel }" id="comp_tel">
							<input type="hidden" value="${com.comp_addr1 }" id="comp_addr1">
							<input type="hidden" value="${com.comp_addr2 }" id="comp_addr2">
							<input type="hidden" value="${holiMap.USE_HOLIDAY }" id="useHoli">
							<input type="hidden" value="${holiMap.REST_HOLIDAY }" id="restHoli">
							<input type="hidden" value="${holiMap.TOTAL_HOLIDAY }" id="totalHoli">
							
							
							<table class="table table-bordered">
								<tr class="success">
									<th>기안일</th>
<!-- 									<td><input id="currentDate" name="sidb_doc_writedt" maxlength="20"></td> -->
									<td style="text-align: center; vertical-align: middle;"><div id="currentDate" name="sidb_doc_writedt" maxlength="20"></div></td>
									<th style="text-align: center;">만료일</th>
									<td style="width: 28%;">
									<div class="input-group" id="kt_td_picker_localization" data-td-target-input="nearest" data-td-target-toggle="nearest">
									    <input type="text" class="form-control sitb_doc_expiredt" name="sidb_doc_expiredt" data-td-target="#kt_td_picker_localization"/>
									    <span class="input-group-text" data-td-target="#kt_td_picker_localization" data-td-toggle="datetimepicker" style="height: 30px !important;">
									        <i class="ki-duotone ki-calendar fs-2"><span class="path1"></span><span class="path2"></span></i>
									    </span>
									</div>
									</td>
<!-- 									<td><input type="date" class="sitb_doc_expiredt" name="sidb_doc_expiredt" data-td-target-input="nearest"></td> -->
									<th style="text-align: center;">결재승인 / 반려 알림</th>
									<td style="text-align: center; vertical-align: middle;">
										<input type="checkbox" class="form-check-input" name="alflag">
									</td>
								</tr>
								<tr>
									<th id="signLine" style="cursor: pointer;">결재선</th>
									<td colspan="5">
										<input type="text" class="form-control form-control-solid" id="apprName" readonly placeholder="결재선을 설정해주세요">
									</td>
								</tr>
								<tr>
									<th id="signRefer" style="cursor: pointer;">참조 및 참조부서</th>
									<td colspan="3">
										<input type="text" class="form-control form-control-solid" id="refName" readonly placeholder="참조자 없음">
									</td>
									<td colspan="2">
										<input type="text" class="form-control form-control-solid" id="deptName" readonly placeholder="참조부서 없음">
									</td>
								</tr>
								<tr id="signLine">
									<th id="selectSign" style="cursor: pointer;">서명선택</th>
									<td colspan="3">
										<input id="signMsg" class="form-control form-control-solid" readonly placeholder="서명을 선택해주세요">
									</td>
									<th style="text-align: center;">대표 등록 서명사용</th>
									<td style="text-align: center; vertical-align: middle;">
										<input type="checkbox" id="chk"  class="form-check-input" onclick="defaultSign()">
									</td>
								</tr>
								
								<tr>
									<th>제목</th>
									<td colspan="5"><input type="text" class="form-control form-solid" id="sitb_doc_title" name="sidb_doc_title" maxlength="20"></td>
									
								</tr>
							</table>
							<textarea id="editor" name="sidb_doc_content"></textarea>
							<table class="table table-bordered" style="margin: 10px 0px;">
								<tr>
									<th colspan="6">첨부파일 <span style="color: orange; margin-left: 30px;">* 10MB 미만의 이미지 / 문서 파일만 업로드 가능합니다</span></th>
								</tr>
								<tr>
									<td colspan="6"><input type="file" id="sidf_file_content" name="file" class="form-control form-control-solid" style="line-height: 12px;"></td>
								</tr>
							</table>
							
							<!-- 제출 버튼 -->				
							<div id="btn_div" style="margin: 10px auto; text-align: center;">
							<button type="button" class="btn btn-primary btnSm" id="insertTempDoc" style="height: 32px; line-height: 12px; width: 100px;">
							    임시저장
							</button>
							<button type="button" class="btn btn-primary btnSm" id="insertDoc" style="height: 32px; line-height: 12px; width: 100px;">
							    기안제출
							</button>
							</div>
							</div>

							</div>
							<!-- ---------------------------- 내 용 입 력 -------------------------------- -->

						</div>
					</div>

				</div>
			<!-- 내용 끝 --> 
		</div>
	</div>

	<div class="modal fade" tabindex="-1" id="kt_modal_3">
		<div class="modal-dialog">
			<div class="modal-content position-absolute">
				<div class="modal-header">
					<h5 class="modal-title">양식 선택</h5>

					<!--begin::Close-->
					<div class="btn btn-icon btn-sm btn-active-light-primary ms-2"
						data-bs-dismiss="modal" aria-label="Close">
						<i class="ki-duotone ki-cross fs-2x"><span class="path1"></span>
						<span class="path2"></span></i>
					</div>
					<!--end::Close-->
				</div>
     
					<!-- ---------------------------- [ 모달창 ] 템플릿 선택 ----------------------------------- -->
				<div class="modal-body">
					<div class="app-container container-fluid">
					<div class="row gx-5 gx-xl-10">
							<div class="col-xxl-4 mb-5 mb-xl-10">
								<div class="card card-flush h-xl-100">
									<div class="card-header pt-5">
										<!--begin::Title-->
										<h3 class="card-title align-items-start flex-column">
											<span class="card-label fw-bold text-gray-900">문서 선택</span> <span
												class="text-gray-500 mt-1 fw-semibold fs-6">사용하실 템플릿을 선택하세요</span>
										</h3>
										<!--end::Title-->
									</div>
									
					<!-- ----------------------- [모달창] 기안문 선택 jstree ------------------------------ -->
									<!--begin::Tab content-->
									<div class="card-body pt-6">
									<div class="tab-content">									
										<div id="jstree"></div>
									</div>
									</div>
									<!--end::Tab content-->
									
								</div>
							</div>
							
					<!-- ------------------------ [모달창] 템플릿 미리보기 -------------------------------- -->		
							<div class="col-xxl-8 mb-5 mb-xl-10">
								<div class="card card-flush overflow-hidden h-xl-100">
									<div class="card-header pt-5">
										<!--begin::Title-->
										<h3 class="card-title align-items-start flex-column">
											<span class="card-label fw-bold text-gray-900">템플릿 미리보기</span>
											<span class="text-gray-500 mt-1 fw-semibold fs-6"></span>
										</h3>
										<!--end::Title-->
									</div>
									<div id="template" style="margin: 0px auto;"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- ------------------------------ [ 모달창 ] 버튼 ---------------------------------- -->	
				<div class="modal-footer">
					<button type="button" class="btn btn-light" data-bs-dismiss="modal" id="closeBtn">닫기</button>
					<button type="button" class="btn btn-primary" id="getTemplate">적용하기</button>
				</div>
				<!-- ------------------------------ 모 달 내 용 ---------------------------------- -->
			</div>
		</div>
	</div>

	<%@include file="/WEB-INF/views/menu/docSideMenu.jsp"%>
</body>

<script type="text/javascript">
	
</script>


<script type="text/javascript" src="/ckeditor5/build/ckeditor.js"></script>
<script type="text/javascript" src="/js/doc/writeDoc.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/jstree.min.js"></script>
<script type="module" src="/ckeditor5/sample/script.js"></script>
<!-- <script type="text/javascript" src="../js/template.js"></script> -->
</html>
