<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/menu/headerInfo.jsp" %>
<title>증명서리스트</title>
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
						<h1 class="page-heading d-flex text-gray-900 fw-bold fs-3 flex-column justify-content-center my-0">결제완료된 증명서</h1>
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
								<h3 class="card-title text-gray-800 fw-bold">증명서</h3>
							</div>
							<div class="separator separator-dashed my-3"></div>	
							<div class="card-body pt-5 table-responsive">
								<c:choose>
									<c:when test="${docList eq '[]'}">
										<h1>결제완료된 증명서가 없습니다</h1>
									</c:when>
									
									<c:otherwise>
										<table class="table table-hover table-rounded table-striped border gy-7 gs-7">
											<thead>
												<tr class="fw-semibold fs-6 text-gray-800 border-bottom-2 border-gray-200">
													<td>순번</td>
													<td>제목</td>
													<td>템플릿이름</td>
													<td>결재완료일</td>
												</tr>
											</thead>
											
											<c:forEach var="doc" items="${docList}" varStatus="var"> 
												<tbody>
													<tr style="cursor: pointer;" onclick="location.href='/hr/certificate/selectOneCertificate.do?sidb_doc_num=${doc.getSidb_doc_num()}&docNum=${doc.getSidb_doc_num()}'">
														<td>${var.count}</td>
														<td>${doc.getSidb_doc_title()}</td>
														<td>${doc.getSidt_temp_name()}</td>
														<td>${doc.getSidb_doc_apprdt()}</td>
													</tr>
												</tbody>
											</c:forEach>
										</table>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
				</div>
				<!-- 내용 끝 -->
				
				<%-- <!-- 내용 시작 -->
				<div id="kt_app_content" class="app-content flex-column-fluid">
					<div class="app-container container-fluid">
						<div class="card card-flush h-md-50 mb-xl-10">
							<div class="card-header pt-5">
								<h3 class="card-title text-gray-800 fw-bold">History</h3>
							</div>
							<div class="separator separator-dashed my-3"></div>	
							<div class="card-body pt-5 table-responsive">
								<c:choose>
									<c:when test="${docDownloadList eq '[]'}">
										<h1>출력된 증명서가 없습니다</h1>
									</c:when>
									
									<c:otherwise>
										<table class="table table-hover table-rounded table-striped border gy-7 gs-7">
											<thead>
												<tr class="fw-semibold fs-6 text-gray-800 border-bottom-2 border-gray-200">
													<td>순번</td>
													<td>제목</td>
													<td>템플릿이름</td>
													<td>출력일</td>
													<td>출력타입</td>
												</tr>
											</thead>
											
											<c:forEach var="down" items="${docDownloadList}" varStatus="var"> 
												<tbody>
													<tr>
														<td>${var.count}</td>
														<td>${down.getSidb_doc_title()}</td>
														<td>${down.getSidt_temp_name()}</td>
														<td>${down.getSidt_print_date()}</td>
														<td>
															<c:choose>
																<c:when test="${down.getSidt_print_type() eq 'D'}">
																	PDF출력
																</c:when>
																<c:otherwise>
																	프린트
																</c:otherwise>
															</c:choose>
														</td>
													</tr>
												</tbody>
											</c:forEach>
										</table>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
				</div>
				<!-- 내용 끝 --> --%>				
			</div>
		</div>
			
<%@include file="/WEB-INF/views/menu/hrSideMenu.jsp" %>		
</body>
</html>