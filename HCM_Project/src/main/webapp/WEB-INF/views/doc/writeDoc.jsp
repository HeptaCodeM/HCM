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
<link rel="stylesheet" href="../ckeditor5/sample/template.css">

<style type="text/css">
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
	data-kt-app-toolbar-enabled="true" class="app-default">
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
					<h1
						class="page-heading d-flex text-gray-900 fw-bold fs-3 flex-column justify-content-center my-0">기안서
						작성</h1>
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
							<!-- 화면 버튼 -->
							<div id="template_div" style="text-align: center;">
								결재 진행을 위해 양식을 선택해주세요. <br/>
								<button type="button" class="btn btn-primary"
									data-bs-toggle="modal" data-bs-target="#kt_modal_3">
									기안문 템플릿 선택하기</button>
							</div>
							<!-- 				<input type="button" id="tree" value="JSTREE나와라"> -->

							<!-- 에디터 화면 -->
							<div id="editor_div" style="display: none;">
							<div class="input-group input-group-sm mb-5">
							    <span class="input-group-text" id="inputGroup-sizing-sm">제목</span>
							    <input type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm"/>
							</div>
							<textarea id="editor" name="sidt_temp_content"></textarea>							
							<div id="btn_div" style="margin: 10px auto; text-align: center;">
							<button type="button" class="btn btn-primary btnSm" id="delBtn" style="height: 32px; line-height: 14px; width: 100px;">
							    임시저장
							</button>
							<button type="button" class="btn btn-primary btnSm" id="delBtn" style="height: 32px; line-height: 14px; width: 100px;">
							    기안제출
							</button>
							</div>
							</div>

							<!-- ---------------------------- 내 용 입 력 -------------------------------- -->

						</div>
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
					<!-- ---------------------------- 모 달 내 용 -------------------------------- -->
					<div class="app-container container-fluid">
						<div class="row gx-5 gx-xl-10">
							<!--begin::Col-->
							<div class="col-xxl-4 mb-5 mb-xl-10">
								<!--begin::Chart widget 8-->
								<div class="card card-flush h-xl-100">
									<!--begin::Header-->
									<div class="card-header pt-5">
										<!--begin::Title-->
										<h3 class="card-title align-items-start flex-column">
											<span class="card-label fw-bold text-gray-900">문서 선택</span> <span
												class="text-gray-500 mt-1 fw-semibold fs-6">사용하실 템플릿을 선택하세요</span>
										</h3>
										<!--end::Title-->
									</div>
									<!--end::Header-->
									<!--begin::Body-->
									<div class="card-body pt-6">
										<!--begin::Tab content-->
										<div class="tab-content">
											<div id="jstree"></div>
										</div>
										<!--end::Tab content-->
									</div>
									<!--end::Body-->
								</div>
								<!--end::Chart widget 8-->
							</div>
							<!--end::Col-->
							<!--begin::Col-->
							<div class="col-xxl-8 mb-5 mb-xl-10">
								<!--begin::Chart widget 36-->
								<div class="card card-flush overflow-hidden h-xl-100">
									<!--begin::Header-->
									<div class="card-header pt-5">
										<!--begin::Title-->
										<h3 class="card-title align-items-start flex-column">
											<span class="card-label fw-bold text-gray-900">템플릿 미리보기</span>
											<span class="text-gray-500 mt-1 fw-semibold fs-6"></span>
										</h3>
										<!--end::Title-->
									</div>
									<!--end::Header-->
									<!--begin::Card body-->
									<div id="template"></div>
									<!--end::Card body-->
								</div>
								<!--end::Chart widget 36-->
							</div>
							<!--end::Col-->
						</div>
					</div>
					<!-- ---------------------------- 모 달 내 용 -------------------------------- -->
				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-light" data-bs-dismiss="modal" id="closeBtn">닫기</button>
					<button type="button" class="btn btn-primary" id="getTemplate">적용하기</button>
				</div>
			</div>
		</div>
	</div>

<script type="text/javascript">


</script>
	<%@include file="/WEB-INF/views/menu/docSideMenu.jsp"%>
</body>


<script type="text/javascript" src="/ckeditor5/build/ckeditor.js"></script>
<script type="text/javascript" src="/js/writeDoc.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/jstree.min.js"></script>
<script type="module" src="../ckeditor5/sample/script.js"></script>
<!-- <script type="text/javascript" src="../js/template.js"></script> -->
</html>
