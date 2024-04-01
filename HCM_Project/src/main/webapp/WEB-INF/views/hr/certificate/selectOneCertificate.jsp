<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/menu/headerInfo.jsp" %>
<script src="https://cdnjs.cloudflare.com/ajax/libs/es6-promise/4.1.1/es6-promise.auto.js"></script>
<script src="https://html2canvas.hertzen.com/dist/html2canvas.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.4/jspdf.min.js"></script>
<script type="text/javascript" src="/js/hr/printPdfhr.js"></script>
<style type="text/css">
	.saveZomeCss{
		padding: 20px;	
	}
	
	@media savePdfZone {
	  @page { margin: 0; }
	  body { margin: 1.6cm; }
	}
	
	#hiidenSealImg{
		display: inline-block !important;
	}
	
</style>
<title>증명서출력화면</title>
</head>
<%@include file="/WEB-INF/views/menu/header.jsp" %>
<body id="kt_app_body" data-kt-app-layout="dark-sidebar"
		data-kt-app-header-fixed="true" data-kt-app-sidebar-enabled="true"
		data-kt-app-sidebar-fixed="true" data-kt-app-sidebar-hoverable="true"
		data-kt-app-sidebar-push-header="true"
		data-kt-app-sidebar-push-toolbar="true"
		data-kt-app-sidebar-push-footer="true"
		data-kt-app-toolbar-enabled="true" class="app-default">
		<c:set var="docDto1" value="${docDto[0]}" />
		<div class="app-wrapper flex-column flex-row-fluid">
			<div class="app-toolbar py-3 py-lg-6">
				<div id="kt_app_toolbar_container" class="app-container container-fluid d-flex flex-stack">
					<!--begin::Page title-->
					<div class="page-title d-flex flex-column justify-content-center flex-wrap me-3">
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
								<h3 class="card-title text-gray-800 fw-bold">[재직증명서] ${docDto1.sidb_doc_title}</h3>
							</div>
							<div class="separator separator-dashed my-3"></div>	
							<div class="card-body pt-5" >
								<div class="saveZomeCss">
									<div id="savePdfZone" class="content">
										<input id="docTitle" type="hidden" value="${docDto1.sidb_doc_title}">
										<input id="docNumber" type="hidden" value="${boxDto.getSidb_doc_num()}">
										${docDto1.sidb_doc_content}
									</div>
								</div>
								<div>
									<button id="savePdf" class="btn btn-primary btnLg me-10">PDF저장하기</button>
									<button id="printPdf" class="btn btn-primary btnLg me-10">프린트하기</button>
									<button id="toDocList" onclick="javascript:history.back(-1)" class="btn btn-primary btnLg me-10">목록으로</button>
								</div>
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