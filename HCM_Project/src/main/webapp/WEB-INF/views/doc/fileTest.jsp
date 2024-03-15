<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/menu/headerInfo.jsp" %>
<title>DOC메인화면</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/themes/default/style.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css">
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
						<h1 class="page-heading d-flex text-gray-900 fw-bold fs-3 flex-column justify-content-center my-0">참조 등록</h1>
						<!--end::Title-->
					</div>
					<!--end::Page title-->
				</div>		
			</div>
			
			<!-- OJS -->	
			<div class="app-container container-fulid">
				<div class="col-xxl-12 mb-5 mb-xl-10">
					<div class="card card-flush h-md-100 mb-xl-10">
						<div class="card-header pt-5">
							<h3 class="card-title text-gray-800 fw-bold">파일테스트중</h3>
						</div> 
						<div class="separator separator-dashed my-3"></div>	
						<div class="card-body pt-5	" style="overflow: auto;">
							<div class="app-main flex-column flex-row-fluid" id="kt_app_main">
								<div class="d-flex flex-column flex-column-fluid">
									<div id="kt_app_content" class="app-content flex-column-fluid">
											
										<form action="./fileUpTest.do" method="post" enctype="multipart/form-data">
											<input name="sidb_doc_num" value="24000003" readonly="readonly">
											<input type="file" name="file"><br>
											<input type="submit">
										</form>
											
										<br><br>
										<select id="selectFile"></select>
										<input type="button" value="파일 미리보기" id="getFile">
										<input type="button" value="다운로드" id="downBtn">
										<div id="fileView" ></div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
				
			<!-- OJS  -->	
		</div>
	<%@include file="/WEB-INF/views/menu/docSideMenu.jsp" %>		
</body>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/jstree.min.js"></script>
<script type="text/javascript" src="/js/file.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</html>