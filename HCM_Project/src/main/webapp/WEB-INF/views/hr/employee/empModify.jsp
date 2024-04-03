<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<%@include file="/WEB-INF/views/menu/headerInfo.jsp" %>
	<title>HCM GroupWare</title>
	
	<style type="text/css">
	.table th {  width:170px;vertical-align:middle; }
	.form-check-input.radio {margin-right:10px;}
	.form-check-input.radio.last {margin-left:30px;}
	</style>
	<script src="/js/hr/employee.js" type="text/javascript"></script>
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
						<h1 class="page-heading d-flex text-gray-900 fw-bold fs-3 flex-column justify-content-center my-0">인사관리 > 조직관리 > 사원수정</h1>
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
								<h3 class="card-title text-gray-800 fw-bold">사원수정</h3>
							</div>
							<div class="separator separator-dashed my-3"></div>	
							<div class="card-body pt-5">

								<form name="modifyEmpForm" method="post" action="/hr/employee/empModifyOk.do" enctype="multipart/form-data">
									<table class="table table-bordered">
										<tr class="success">
											<th>사번</th>
											<td>
												${empInfo.empl_id}
												<input type="hidden" name="empl_id" maxlength="20" value="${empInfo.empl_id}">
											</td>
											<td style="width:150px;" rowspan="4">
												<img src="${empInfo.empl_picture_str}" style="width:100%;height:100%;" >
											</td>
										</tr>
										<tr>
											<th>성명</th>
											<td>${empInfo.empl_name}</td>
										</tr>
										<tr>
											<th>생년월일</th>
											<td>
												<fmt:parseDate var="parseDate" value="${empInfo.empl_birth}" pattern="yyyyMMdd" />
												<fmt:formatDate value="${parseDate}" pattern="yyyy-MM-dd" />
											</td>
										</tr>
										<tr>
											<th>성별</th>
											<td>
												<c:if test="${empInfo.empl_gender eq 'M'}">
												남성
												</c:if>
												<c:if test="${empInfo.empl_gender eq 'F'}">
												여성
												</c:if>
											</td>
										</tr>
										<tr>
											<th>이메일</th>
											<td colspan="2">${empInfo.empl_email}</td>
										</tr>

										<tr>
											<th class="required">휴대폰</th>
											<td colspan="2"><input type="text" class="form-control form-control-solid" name="empl_phone" id="empl_phone" maxlength="15" value="${empInfo.empl_phone}"></td>
										</tr>
										<tr>
											<th>내선번호</th>
											<td colspan="2"><input type="text" class="form-control form-control-solid" name="empl_tel" id="empl_tel" maxlength="3" value="${empInfo.empl_tel}"></td>
										</tr>
										<tr>
											<th>팩스번호</th>
											<td colspan="2"><input type="text" class="form-control form-control-solid" name="empl_fax" id="empl_fax" maxlength="15" value="${empInfo.empl_fax}"></td>
										</tr>
										<tr>
											<th>입사년도</th>
											<td colspan="2">${empInfo.empl_joindate}</td>
										</tr>
										<tr>
											<th>부서</th>
											<td colspan="2">
												${empInfo.coco_name_dnm}
											</td>
										</tr>
										<tr>
											<th>직위</th>
											<td colspan="2">
												${empInfo.coco_name_rnm}
											</td>
										</tr>
										<tr>
											<th>직책</th>
											<td colspan="2">
												${empInfo.coco_name_pnm}
											</td>
										</tr>
										<tr>
											<th>증명사진</th>
											<td colspan="2"><input type="file" class="form-control form-control-solid" name="empl_picture"></td>
										</tr>
										<tr>
											<td colspan="3" style="text-align:center;">
												<button type="button" class="btn btn-primary me-10" id="kt_button_1" onclick="modifyEmployee()">
												    <span class="indicator-label">
												        수정
												    </span>
												    <span class="indicator-progress">
												        Please wait... <span class="spinner-border spinner-border-sm align-middle ms-2"></span>
												    </span>
												</button>
												<button type="button" class="btn btn-success me-10" id="kt_button_1" onclick="location.href='/hr/employee/empModify.do';">
												    <span class="indicator-label">
												        취소
												    </span>
												</button>
											</td>
										</tr>
									</table>
								</form>

							</div>
						</div>
					</div>
				</div>
				<!-- 내용 끝 -->
			</div>
		</div>
			
<%@include file="/WEB-INF/views/menu/hrSideMenu.jsp" %>		
</body>
</html>