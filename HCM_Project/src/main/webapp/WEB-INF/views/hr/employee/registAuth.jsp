<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
							<form name="registAuthForm" method="post" action="/hr/commonCode/registAuthOk.do">
								
									<div class="table-responsive">
										<table class="table table-bordered">
											<tr>
												<th class="required">사번</th>
												<td><input type="text" class="form-control form-control-solid" name="empl_id" id="empl_id" maxlength="8" required="required"></td>
											</tr>
											<tr>
												<th class="required">성명</th>
												<td><input type="text" class="form-control form-control-solid" name="empl_name" id="empl_name" maxlength="20" required="required"></td>
											</tr>
											<tr>
												<th class="required">부서</th>
												<td><input type="text" class="form-control form-control-solid" name="empl_name" id="empl_name" maxlength="20" required="required"></td>
											</tr>
											<tr>
												<th class="required">직위</th>
												<td><input type="text" class="form-control form-control-solid" name="empl_name" id="empl_name" maxlength="20" required="required"></td>
											</tr>
											<tr>
												<th class="required">권한</th>
												<td>
													<select name="empl_auth" required="required" class="form-select">
														<option value="">==== 권한선택 ====</option>
														<c:forEach items="${authList}" var="dept">
														<option value="${authList.auco_cd}">${authList.auco_name}</option>
														</c:forEach>
													</select>
												</td>
											</tr>
											<tr>
												<td colspan="2" style="text-align:center;">
													<button type="button" class="btn btn-primary me-10" id="kt_button_1" onclick="registEmpAuth()">
													    <span class="indicator-label">
													        등록
													    </span>
													    <span class="indicator-progress">
													        Please wait... <span class="spinner-border spinner-border-sm align-middle ms-2"></span>
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
		
		<!-- 사원검색 Layer 시작 -->
		<style type="text/css">
			#empSearch { position: absolute; background-color:#ccc; z-index:999; width:600px; left: 50%; transform: translateX(-50%); top: 25%; padding: 20px; }
		</style>
		<div id="empSearch">
			<div>
				<form name="searchEmpInfo" method="post" action="/hr/employee/getUserInfoSearch.do">
					<input type="hidden" name="layer" value="empSearch">
					<table class="table">
						<tr>
							<th>사원검색</th>
							<td>
								<select name="searchType" class="form-select">
									<option value="empl_name">성명</option>
									<option value="empl_id">사번</option>
								</select>
							</td>
							<td>
								<input type="text" class="form-control form-control-solid" name="keyWord" id="keyWord" maxlength="20" required="required">
							</td>
							<td>
								<button type="button" class="btn btn-success" id="kt_button_1" onclick="empInfoSearch()">
								    <span class="indicator-label">
								        검색
								    </span>
								    <span class="indicator-progress">
								        Please wait... <span class="spinner-border spinner-border-sm align-middle ms-2"></span>
								    </span>
								</button>
							</td>
						</tr>
					</table>
				</form>
			</div>
			<div>
			<table class="table table-bordered">
				<thead>
					<th>사번</th>
					<th>성명</th>
					<th>부서</th>
					<th>직위</th>
					<th>직책</th>
				</thead>
				<tbody id="empSearchList">
				</tbody>
			</table>
			</div>
		</div>
		<!-- 사원검색 Layer 종료 -->

<%@include file="/WEB-INF/views/menu/hrSideMenu.jsp" %>		
</body>
</html>