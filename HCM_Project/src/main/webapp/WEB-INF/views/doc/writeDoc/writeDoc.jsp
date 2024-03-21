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
<link rel="stylesheet" href="/ckeditor5/sample/template.css">

<style type="text/css">
	/* 모달창 사이즈 조절 */
	.modal { 
		--bs-modal-width: 1200px;
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
	<!-- **전체페이지 감싸기** -->
	<div class="app-wrapper flex-column flex-row-fluid">
		<!-- 메인페이지 -->
		<div class="app-toolbar py-3 py-lg-6">
			<div id="kt_app_toolbar_container"
				class="app-container container-fluid d-flex flex-stack">
				<!--begin::Page title-->
				<div
					class="page-title d-flex flex-column justify-content-center flex-wrap me-3">
					<!--begin::Title-->
					<h1 class="page-heading d-flex text-gray-900 fw-bold fs-3 flex-column justify-content-center my-0">
						기안서 작성</h1>
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
							<!-- -----기안양식 선택화면 버튼 ------------------------------------------------ -->
							<div id="template_div" style="text-align: center;">
								결재 진행을 위해 양식을 선택해주세요. <br/>
								<button type="button" class="btn btn-primary"
									data-bs-toggle="modal" data-bs-target="#kt_modal_3">
									기안문 템플릿 선택하기</button>
							</div>
							
							<!-- 기안문 작성 화면 -->
							<div id="editor_div" style="display: none;">
							<%-- <form>
							${loginInfo }
							<div class="row gx-5 gx-xl-10">
							<div class="col-xxl-5 mb-5 mb-xl-10">
							<div class="input-group input-group-sm mb-5">
							    <span class="input-group-text" id="inputGroup-sizing-sm">기안일</span>
							    <input type="date" class="form-control" name="sidb_doc_be" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm"/>
							</div>
							</div>
							<div class="col-xxl-5 mb-5 mb-xl-10">
							<div class="input-group input-group-sm mb-5">
							    <span class="input-group-text" id="inputGroup-sizing-sm">만료일</span>
							    <input type="date" class="form-control" name="sidb_doc_end" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm"/>
							</div>
							</div>
							</div>
							
							<div class="input-group input-group-sm mb-5">
							    <span class="input-group-text" id="inputGroup-sizing-sm">만료일</span>
							    <input type="date" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm"/>
							</div>
							<div class="input-group input-group-sm mb-5">
							    <span class="input-group-text" id="inputGroup-sizing-sm">결재선</span>
							    <input class="form-control" name="" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm"/>
							</div>
							<div class="input-group input-group-sm mb-5">
							    <span class="input-group-text" id="inputGroup-sizing-sm">참조</span>
							    <input class="form-control" name="" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm"/>
							</div>
							<div class="input-group input-group-sm mb-5">
							    <span class="input-group-text" id="inputGroup-sizing-sm">제목</span>
							    <input type="text" class="form-control" name="sidb_doc_title" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm"/>
							</div>
							<textarea id="editor" name="sidt_temp_content"></textarea>
							알림여부 체크박스	
							첨부파일 --%>
							
							<table class="table table-bordered">
								<tr class="success">
									<th>기안일</th>
									<td><input type="date" id="currentDate" name="sidb_doc_writedt" maxlength="20"></td>
									<th>만료일</th>
									<td><input type="date" name="sidb_doc_expiredt" maxlength="20"></td>
									<th>알림여부</th>
									<td><input type="checkbox" name="alflag"></td>
								</tr>
								<tr>
									<th>결재선</th>
									<td colspan="5">
										<div></div>
									</td>
								</tr>
								<tr>
									<th>참조</th>
									<td colspan="5">
										<div></div>
									</td>
								</tr>
								<tr>
									<th>제목</th>
									<td colspan="5"><input type="text" name="sidb_doc_title" maxlength="20"></td>
								</tr>
							</table>
							<textarea id="editor" name="sidb_doc_content"></textarea>
							<table class="table table-bordered">
								<tr>
									<th>첨부파일</th>
									<td colspan="2"><input type="file" id="sidf_file_content" class="form-control form-control-solid"></td>
									
								</tr>
							</table>
							
							<!-- 제출 버튼 -->				
							<div id="btn_div" style="margin: 10px auto; text-align: center;">
							<button type="button" class="btn btn-primary btnSm" id="insertTempDoc" style="height: 32px; line-height: 14px; width: 100px;">
							    임시저장
							</button>
							<button type="button" class="btn btn-primary btnSm" id="insertDoc" style="height: 32px; line-height: 14px; width: 100px;">
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
     
				<div class="modal-body">
					<!-- ---------------------------- [ 모달창 ] 템플릿 선택 ----------------------------------- -->
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
									<div id="template"></div>
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
