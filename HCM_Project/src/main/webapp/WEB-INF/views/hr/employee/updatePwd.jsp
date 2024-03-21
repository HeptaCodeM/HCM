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
	<script type="text/javascript" src="/js/hr/employee.js"></script>
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
						<h1 class="page-heading d-flex text-gray-900 fw-bold fs-3 flex-column justify-content-center my-0">인사관리 > 조직관리 > 비밀번호 변경</h1>
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
								<h3 class="card-title text-gray-800 fw-bold">비밀번호 변경</h3>
							</div>
							<div class="separator separator-dashed my-3"></div>	
							<div class="card-body pt-5">

								<form name="updatePwdForm" method="post" action="/hr/employee/updatePwdOk.do">
									<table class="table table-bordered">
										<tr>
											<th class="required">현재 비밀번호</th>
											<td colspan="2"><input type="password" class="form-control form-control-solid" name="empl_pwd" maxlength="12" value="${empInfo.empl_tel}" required="required"></td>
										</tr>
										<tr>
											<th class="required">변경 비밀번호</th>
											<td colspan="2"><input type="password" class="form-control form-control-solid" name="empl_new_pwd" maxlength="12" value="${empInfo.empl_tel}" required="required"></td>
										</tr>
										<tr>
											<th class="required">변경 비밀번호 확인</th>
											<td colspan="2"><input type="password" class="form-control form-control-solid" name="empl_new_pwd2" maxlength="12" value="${empInfo.empl_tel}" required="required"></td>
										</tr>
										<tr>
											<th colspan="2">
											<span class="required"></span>
											<span>비밀번호 규칙</span><br />
											- 최소한 하나의 영문자가 포함되어야 합니다.<br />
											- 최소한 하나의 숫자가 포함되어야 합니다.<br />
											- 최소한 하나의 특수문자가 포함되어야 합니다.<br />
											- 영문자, 숫자, 특수문자 중에서 8~12자 길이로 이루어져야 합니다.<br />
											</th>
										</tr>
										<tr>
											<td colspan="3" style="text-align:center;">
												<button type="button" class="btn btn-primary me-10" id="kt_button_1" onclick="updatePwd()">
												    <span class="indicator-label">
												        비밀번호 변경
												    </span>
												    <span class="indicator-progress">
												        Please wait... <span class="spinner-border spinner-border-sm align-middle ms-2"></span>
												    </span>
												</button>
												<button type="button" class="btn btn-success me-10" id="kt_button_1" onclick="history.back();">
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