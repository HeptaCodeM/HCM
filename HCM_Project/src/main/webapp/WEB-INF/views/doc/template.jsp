<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/menu/headerInfo.jsp" %>
<title>DOC메인화면</title>
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
						<h1 class="page-heading d-flex text-gray-900 fw-bold fs-3 flex-column justify-content-center my-0">관리자 메뉴</h1>
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
								<h3 class="card-title text-gray-800 fw-bold">템플릿 리스트</h3>
							</div>
							<div class="separator separator-dashed my-3"></div>	
							<div class="card-body pt-5">
<!-- 											SIDT_TEMP_CD, SICA_CD, SIDT_TEMP_NAME, SIDT_TEMP_CREATEDT, SIDT_TEMP_MODIFYDT, SIDT_TEMP_FLAG -->

												<div>
													<button class="writeTemplate">템플릿 등록</button>
												</div>
												<table class="table table-hover">
													<thead>
														<tr>
															<th>템플릿코드</th>
															<th>템플릿명</th>
															<th>활성여부</th>
														</tr>
													</thead>
													<tbody>
														<c:forEach var="lst" items="${lst}" varStatus="vs">
															<tr>
																<td>${lst.sidt_temp_cd}</td>
																<td><a
																	href="./detailTemplate.do?sidt_temp_cd=${lst.sidt_temp_cd}">${lst.sidt_temp_name}</a></td>
																<td>${lst.sidt_temp_flag}</td>
															</tr>
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
			
<%@include file="/WEB-INF/views/menu/docSideMenu.jsp" %>
</body>
<script type="text/javascript">
	var writebtn = document.querySelector('.writeTemplate');
	writebtn.addEventListener("click", function(){
		window.location.href = "./writeTemplate.do";
	});
</script>
</html>