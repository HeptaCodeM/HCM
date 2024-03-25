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
						임시보관 문서 상세페이지</h1>
					<!--end::Title-->
				</div>
				<!--end::Page title-->
			</div>
		</div>
		<input id="tempTitle" type="hidden" value="${dto.sitb_doc_title }">
		<div id="tempContent" style="display: none;">${dto.sitb_doc_content}</div>
		<input id="tempSicaCd" type="hidden" value="${dto.sica_cd }">
		<input id="tempExpiredt" type="hidden" value="${dto.sitb_doc_expiredt }">
		<input id="tempAlflag" type="hidden" value="${dto.sitb_doc_alflag }">
		<input id="tempCd" type="hidden" value="${dto.sidt_temp_cd }">
		<input id="tempJson" type="hidden" value="${dto.sitb_doc_json }">
		<input id="tempRef" type="hidden" value="${dto.empl_ref }">
		<input id="tempDept" type="hidden" value="${dto.empl_dept_cd }">
		<input id="tempSign" type="hidden" value="${dto.emsi_seq }">
		<div class="app-content flex-column-fluid">
			<!-- 내용 시작 -->
			<div id="kt_app_content" class="app-content flex-column-fluid">

				<!-- 컨테이너 -->
				<div class="app-container container-fluid">

					<!-- row -->
					<!-- col -->
					<div class="card card-flush h-md-50 mb-xl-10">
						<div class="card-header pt-5">
							<h3 class="card-title text-gray-800 fw-bold">작성중인 문서</h3>
						</div>
						<div class="separator separator-dashed my-3"></div>
						<div class="card-body pt-5">

							<!-- ---------------------------- 내 용 입 력 -------------------------------- -->
							
							
							<!-- 기안문 작성 화면 -->
							<div id="editor_div">
							
							<input type="hidden" value="${userInfoVo.emsi_seq }" id="emsi_seq">
							<table class="table table-bordered">
								<tr class="success">
									<th>기안일</th>
<!-- 									<td><input id="currentDate" name="sidb_doc_writedt" maxlength="20"></td> -->
									<td><div id="currentDate" name="sidb_doc_writedt" maxlength="20"></div></td>
									<th>만료일</th>
									<td><input type="date" class="sitb_doc_expiredt" name="sidb_doc_expiredt" data-td-target-input="nearest"></td>
									<th>알림여부</th>
									<td><input type="checkbox" class="form-check-input" name="alflag"></td>
								</tr>
								<tr id="signLine">
									<th>결재선</th>
									<td colspan="5">
										<div id="apprName"></div>
									</td>
								</tr>
								<tr id="signRefer">
									<th>참조 및 참조부서</th>
									<td colspan="5">
										<div id="refName"></div>
										<div id="deptName"></div>
									</td>
								</tr>
								<tr id="signLine">
									<th>서명선택</th>
									<td colspan="3">
										<button id="selectSign">서명관리</button>
									</td>
									<th>기본서명사용</th>
									<td>
										<input type="checkbox" id="chk"  class="form-check-input" name="defaultSign" onclick="defaultSign()">
									</td>
								</tr>
								
								<tr>
									<th>제목</th>
									<td colspan="5"><input type="text" class="sitb_doc_title" name="sidb_doc_title" maxlength="20"></td>
									
								</tr>
							</table>
							<textarea id="editor" name="sidb_doc_content"></textarea>
							<table class="table table-bordered">
								<tr>
									<th>첨부파일</th>
									<td colspan="2"><input type="file" id="sidf_file_content" name="file" class="form-control form-control-solid"></td>
									
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


	<%@include file="/WEB-INF/views/menu/docSideMenu.jsp"%>
</body>

<script type="text/javascript">
	
</script>


<script type="text/javascript" src="/ckeditor5/build/ckeditor.js"></script>
<script type="text/javascript" src="/js/doc/getTempDoc.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/jstree.min.js"></script>
<script type="module" src="/ckeditor5/sample/script.js"></script>
<!-- <script type="text/javascript" src="../js/template.js"></script> -->
</html>
