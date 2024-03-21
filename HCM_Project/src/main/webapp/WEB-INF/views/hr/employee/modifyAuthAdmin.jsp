<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<%@include file="/WEB-INF/views/menu/headerInfo.jsp" %>
	<title>HCM GroupWare</title>
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
			<br>
			<div class="app-content flex-column-fluid">
				<!-- 내용 시작 -->
				<div id="kt_app_content" class="app-content flex-column-fluid">
					<div class="app-container container-fluid">
						<div class="card card-flush h-md-50 mb-xl-10">
							<div class="card-header pt-5">
								<h3 class="card-title text-gray-800 fw-bold">인사관리 > 조직관리 > 권한관리</h3>
							</div>
							<div class="separator separator-dashed my-3"></div>
							<form name="modifyAuthForm" id="modifyAuthForm" method="post" action="/hr/employee/updateAuthAdminOk.do">
								<input type="hidden" name="type" value="U">
								<div class="table-responsive">
									<table class="table table-bordered">
										<tr>
											<th class="required">사번</th>
											<td>
												<input type="text" class="form-control form-control-solid" name="empl_id" id="empl_id" maxlength="8" value="${authInfo.empl_id}" readonly required="required">
											</td>
										</tr>
										<tr>
											<th class="required">성명</th>
											<td><input type="text" class="form-control form-control-solid" name="empl_name" id="empl_name" maxlength="20" value="${authInfo.empl_name}" readonly required="required"></td>
										</tr>
										<tr>
											<th class="required">부서</th>
											<td><input type="text" class="form-control form-control-solid" name="empl_dept_nm" id="empl_dept_nm" maxlength="20" value="${authInfo.empl_dept_nm}" readonly required="required"></td>
										</tr>
										<tr>
											<th class="required">직위</th>
											<td><input type="text" class="form-control form-control-solid" name="empl_rank_nm" id="empl_rank_nm" maxlength="20" value="${authInfo.empl_rank_nm}" readonly required="required"></td>
										</tr>
										<tr>
											<th class="required">직책</th>
											<td><input type="text" class="form-control form-control-solid" name="empl_position_nm" id="empl_position_nm" maxlength="20" value="${authInfo.empl_position_nm}" readonly required="required"></td>
										</tr>
										<tr>
											<th class="required">권한</th>
											<td>
												<select name="empl_auth" id="empl_auth" required="required" class="form-select">
													<option value="">==== 권한선택 ====</option>
													<c:forEach items="${authLists}" var="alist">
														<c:if test="${alist.auco_cd ne 'ROLE_USER'}">
													<option value="${alist.auco_cd}" <c:if test="${authInfo.empl_auth eq alist.auco_cd}">selected disabled="disabled" style="background-color:#D7D9D8;"</c:if>>${alist.auco_name}</option>
														</c:if>
													</c:forEach>
												</select>
												<input type="hidden" name="empl_auth1" value="${authInfo.empl_auth}">
											</td>
										</tr>
										<tr>
											<td colspan="2" style="text-align:center;">
												<button type="button" class="btn btn-primary me-10" id="kt_button_1" onclick="modifyEmpAuth()">
												    <span class="indicator-label">
												        수정
												    </span>
												    <span class="indicator-progress">
												        Please wait... <span class="spinner-border spinner-border-sm align-middle ms-2"></span>
												    </span>
												</button>
												<button type="button" class="btn btn-danger me-10" id="kt_button_1" onclick="deleteEmpAuth()">
												    <span class="indicator-label">
												        삭제
												    </span>
												    <span class="indicator-progress">
												        Please wait... <span class="spinner-border spinner-border-sm align-middle ms-2"></span>
												    </span>
												</button>
												<button type="button" class="btn btn-success me-10" id="kt_button_1" onclick="location.href='/hr/employee/authAdminList.do'">
												    <span class="indicator-label">
												        취소
												    </span>
												</button>
											</td>
										</tr>
									</table>
								</div>

							</form>
						</div>
					</div>
				</div>
				<!-- 내용 끝 -->
			</div>
		</div>
		
<%@include file="/WEB-INF/views/menu/hrSideMenu.jsp" %>		
</body>
</html>