<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<%@include file="/WEB-INF/views/menu/headerInfo.jsp" %>
	<title>HR메인화면</title>
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
						<h1 class="page-heading d-flex text-gray-900 fw-bold fs-3 flex-column justify-content-center my-0">제목이 여기에 들어가요!</h1>
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
								<h3 class="card-title text-gray-800 fw-bold">소제목? 들어갑니다</h3>
							</div>
							<div class="separator separator-dashed my-3"></div>	
							<div class="card-body pt-5" >
								대충 여기에 내용이 들어가요!<br>
								대충 여기에 내용이 들어가요!<br>
								대충 여기에 내용이 들어가요!<br>
								
								
								<button class="btn btn-primary" onclick="swalAlert('기본 테스트', '', '', '')">기본 alert</button><br /><br />
								<button class="btn btn-primary" onclick="swalAlert('테스트', '', 'btn-danger', '주의')">일반 alert</button><br /><br />
								<button class="btn btn-primary" onclick="swalHistoryBack('테스트', 'btn-info', '뒤로갑니다.')">alert history.back()</button><br /><br />
								<button class="btn btn-primary" onclick="sweetAlertConfirm('테스트', a, b);">confirm 테스트</button>
							
								<script>
								function a(){
									alert('a');
								}
								function b(){
									alert('b');
								}
								</script>
								
								<br>
								<hr>
								<br>
								<a href="https://preview.keenthemes.com/html/keen/docs/index" target="_blank">Template Reference Site</a>
								<br>
								<hr>
								ex) empInfoLayer(사번, this)<br>
								<br>
								<a href="javascript:void(0);" onclick="empInfoLayer('20220101', this)">테스트1</a>
								<br>
								<hr>
								<br>
								<a href="javascript:void(0);" onclick="empInfoLayer('20230102', this)">테스트2</a>
								<br>
								<hr>
								<br>
								<a href="javascript:void(0);" onclick="empInfoLayer('20230103', this)">테스트3</a>
								<br>
								<hr>
								<br>
								
							</div>
						</div>
					</div>
				</div>
				<!-- 내용 끝 -->
			</div>
		</div>
			
<%@include file="/WEB-INF/views/menu/hrSideMenu.jsp" %>		
<script src="/js/common/common.js"></script>
</body>
</html>