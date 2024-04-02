<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/menu/headerInfo.jsp" %>
<title>HCM GroupWare</title>
	<style type="text/css">
		.searchEmpInput{
			width: 300px;
			height: 40px;
		}
		
		.searchEmpSelect{
			width: 130px;
			height: 40px;
		}
		.searchEmpDate{
			width: 290px;
			height: 40px;
		}
		.cardFlexSearch{
			display: flex;
			justify-content: center;
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
						<h1 class="page-heading d-flex text-gray-900 fw-bold fs-3 flex-column justify-content-center my-0">인사관리 > 조직관리 > 임직원 정보</h1>
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
							<div class="card-body pt-5">
								<div class="cardFlexSearch">
									<span>
										<select id="empShCtg" class="form-select form-select-solid searchEmpSelect">
											<option value="empl_name">검색분류</option>
											<option value="empl_name">성명</option>
											<option value="empl_id">사원번호</option>
											<option value="empl_phone">전화번호</option>
										</select>
									</span>&nbsp;&nbsp;
									
									<span>
										<select id="empStaCtg" class="form-select form-select-solid searchEmpSelect">
											<option value="">재직여부</option>
											<option value="N">재직</option>
											<option value="Y">퇴직</option>
										</select>
									</span>&nbsp;&nbsp;
									
									<span>
    									<input class="form-control form-control-solid searchEmpDate" placeholder="재직기간" id="empDatepicker" />
									</span>&nbsp;&nbsp;
									
									
									
									<span>
										<input type="text" class="form-control form-control-solid searchEmpInput" id="empSearchValue" name="empSearchValue" placeholder="검색">
									</span>&nbsp;&nbsp;
								
									<button class="btn btn-primary btnLg me-10" id="empSearchBtn">검색</button>
								</div>
								<div class="separator border-2 separator-dashed my-5"></div>
								<div>
									<div class="form-check form-check-custom form-check-solid">
										부서&nbsp;&nbsp;
										<span>  
											<c:forEach var="dt" items="${deptList}">
												<input name="dtChkVal" class="form-check-input" type="checkbox" value="${dt.getCoco_cd()}"/>&nbsp;
												${dt.getCoco_name()}
												<c:if test="${dt.getCoco_delflag() eq 'Y'}">
													<span class="text-danger">[X]</span>
												</c:if>
											</c:forEach>
										</span>&nbsp;
									</div>
									<div class="separator border-2 separator-dashed my-5"></div>
									<div class="form-check form-check-custom form-check-solid">
										직위&nbsp;&nbsp;
										<span>
											<c:forEach var="rk" items="${rankList}">
												<input name="rkChkVal" class="form-check-input" type="checkbox" value="${rk.getCoco_cd()}"/>&nbsp;
												${rk.getCoco_name()}
												<c:if test="${rk.getCoco_delflag() eq 'Y'}">
													<span class="text-danger">[X]</span>
												</c:if>
											</c:forEach>
										</span>&nbsp;
									</div>
									<div class="separator border-2 separator-dashed my-5"></div>
									<div class="form-check form-check-custom form-check-solid">
										직책&nbsp;&nbsp;
										<span>
											<c:forEach var="pn" items="${positionList}">
												<input name="pnChkVal" class="form-check-input" type="checkbox" value="${pn.getCoco_cd()}"/>&nbsp;
												${pn.getCoco_name()}
												<c:if test="${pn.getCoco_delflag() eq 'Y'}">
													<span class="text-danger">[X]</span>
												</c:if>
											</c:forEach>
										</span>&nbsp;
									</div>
								</div>
							</div>
						</div>
						
						<div class="card card-flush h-md-50 mb-xl-10">
							<div class="card-body pt-5 table-responsive">
								<table id="emplListTable" class="table table-row-bordered gy-5">
									<thead>
										<tr class="fw-semibold fs-6 text-muted">
											<th>사번</th>
											<th>성명</th>
											<th>부서명</th>
											<th>직위</th>
											<th>직책</th>
											<th>재직여부</th>
										</tr>
									</thead>
									<tbody>
									<c:forEach items="${lists}" var="emp">
										<tr style="cursor: pointer;" onclick="location.href='/hr/employee/modifyAdmin.do?empl_id=${emp.empl_id}'">
											<td>${emp.empl_id}</td>
											<td>${emp.empl_name}</td>
											<td>${emp.coco_name_dnm}</td>
											<td>${emp.coco_name_rnm}</td>
											<td>${emp.coco_name_pnm}</td>
											<td>
												<c:choose>
													<c:when test="${emp.empl_delflag eq 'Y'}">
														퇴사자
													</c:when>
													<c:otherwise>
														재직자
													</c:otherwise>
												</c:choose>
											</td>
										</tr>
										<input type="hidden" id="empl_id" value="${emp.empl_id}">
									</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
						
						
					</div>
				</div>
				<!-- 내용 끝 -->
			</div>
		</div>
			
<%@include file="/WEB-INF/views/menu/hrSideMenu.jsp" %>		
</body>
<script type="text/javascript" src="/js/hr/empAdminList.js"></script>
</html>