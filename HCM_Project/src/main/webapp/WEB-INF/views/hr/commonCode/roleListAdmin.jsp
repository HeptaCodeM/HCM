<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/menu/headerInfo.jsp" %>
<c:choose>
	<c:when test="${role eq 'DT'}">
		<c:set var="thisRole" value="부서"/>
	</c:when>
	
	<c:when test="${role eq 'RK'}">
		<c:set var="thisRole" value="직위"/>
	</c:when>
	
	<c:when test="${role eq 'PN'}">
		<c:set var="thisRole" value="직책"/>
	</c:when>
</c:choose>
<title>${thisRole}관리</title>
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
						<h1 class="page-heading d-flex text-gray-900 fw-bold fs-3 flex-column justify-content-center my-0">부서관리</h1>
						<!--end::Title-->
					</div>
					<!--end::Page title-->
				</div>		
			</div>
			<div class="app-content flex-column-fluid">
				<!-- 내용 시작 -->
				<div class="app-content flex-column-fluid">
					<div class="app-container container-fluid">
						<div class="card card-flush h-md-50 mb-xl-10">
							<div class="card-header pt-5">
								<h3 class="card-title text-gray-800 fw-bold">${thisRole}</h3>
							</div>
							<div class="separator separator-dashed my-3"></div>	
							<div class="card-body pt-5">
								<div class="table-responsive">
									<table class="table table-hover table-rounded table-striped border gy-7 gs-7">
										<thead>
											<tr class="fw-semibold fs-6 text-gray-800 border-bottom-2 border-gray-200">
												<th>순번</th>
												<th>${thisRole}코드</th>
												<th>${thisRole}명</th>
												<th>생성자ID</th>
												<th>생성일자</th>
												<th>비고</th>
											</tr>
										</thead>	
										<tbody>
											<c:forEach var="codeList" items="${codeList}" varStatus="var">
												<c:if test="${codeList.getCoco_delflag() eq 'N'}">
												<tr style="cursor: pointer;" onclick="location.href='/hr/commonCode/roleDetailAdmin.do?coco_cd=${codeList.getCoco_cd()}&role=${role}'" class="py-5 fw-semibold  border-bottom border-gray-300 fs-6">
												</c:if>
												<c:if test="${codeList.getCoco_delflag() eq 'Y'}">
												<tr class="py-5 fw-semibold  border-bottom border-gray-300 fs-6">
												</c:if>
													<td>NO.${var.count}</td>
													<td>${codeList.getCoco_cd()}</td>
													<td>${codeList.getCoco_name()}</td>
													<td>${codeList.getCoco_create_id()}</td>
													<td>${codeList.getCoco_create_dt()}</td>
													<td>
														<c:choose>
															<c:when test="${codeList.getCoco_delflag() eq 'Y'}">
																미사용
															</c:when>
															<c:when test="${codeList.getCoco_delflag() eq 'N'}">
																사용
															</c:when>
														</c:choose>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
								
							</div>
							<div class="card-footer">
								<!-- <a href="#" class="btn btn-primary me-10">삭제</a> -->
								<a href="/hr/commonCode/insertRoleAdmin.do?role=${role}" class="btn btn-primary me-10">추가</a>
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