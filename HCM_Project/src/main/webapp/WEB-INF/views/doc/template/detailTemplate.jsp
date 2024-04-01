<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/menu/headerInfo.jsp"%>
<title>DOC메인화면</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/themes/default/style.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css">
<link rel="stylesheet" href="/ckeditor5/sample/template.css">	
<style type="text/css">
	#sealImg {
		display : inline-block !important;
	}
	
	.btnMd {
		height: 38px;
	}
</style>
</head>
<%@include file="/WEB-INF/views/menu/header.jsp"%>
<body id="kt_app_body" data-kt-app-layout="dark-sidebar"
	data-kt-app-header-fixed="true" data-kt-app-sidebar-enabled="true"
	data-kt-app-sidebar-fixed="true" data-kt-app-sidebar-hoverable="true"
	data-kt-app-sidebar-push-header="true"
	data-kt-app-sidebar-push-toolbar="true"
	data-kt-app-sidebar-push-footer="true"
	data-kt-app-toolbar-enabled="true" class="app-default">

	<div class="app-wrapper flex-column flex-row-fluid"> <!-- 전체페이지 감싸기 -->
		<div class="app-toolbar py-3 py-lg-6">
			<div id="kt_app_toolbar_container"
				class="app-container container-fluid d-flex flex-stack">
				<!-- Page Title -->
				<div class="page-title d-flex flex-column justify-content-center flex-wrap me-3">
					<h1 class="page-heading d-flex text-gray-900 fw-bold fs-3 flex-column justify-content-center my-0">
						템플릿 상세보기
					</h1>
				</div>
				<!-- Page Title -->
			</div>
		</div>
			<div class="app-content flex-column-fluid">
				<!-- 내용 시작 -->
				<div id="kt_app_content" class="app-content flex-column-fluid">
				
				<!-- 컨테이너 -->
					<div class="app-container container-fluid">
					
					<!-- row -->
					<!-- col -->
						<div class="card card-flush h-md-50 mb-xl-10">
							<div class="card-header pt-5">
								<h3 class="card-title text-gray-800 fw-bold">
								${temDto.sidt_temp_name}
								</h3>
							</div>
							<div class="separator separator-dashed my-3"></div>	
							<div class="card-body pt-5">
					<!-- ---------------------------- 내 용 입 력 -------------------------------- -->		
								
<%-- 							<h2 style="text-align: center;">${temDto.sidt_temp_name}</h2> --%>
							<div class="ck_content_detail">${temDto.sidt_temp_content}</div>
							
							<div style="text-align: center;">
								<button class="modifyTemplate btn btn-light-primary btnMd btn-color-gray-600">수정하기</button>
								<button class="deleteTemplate btn btn-light-primary btnMd btn-color-gray-600">삭제하기</button>
<!-- 								<button class="returnTemplate btn btn-light-primary btnMd btn-color-gray-600">복구하기</button> -->
							</div>
			

					<!-- ---------------------------- 내 용 입 력 -------------------------------- -->

						</div>
						</div>
						
					</div>
				</div>
				<!-- 내용 끝 -->
			</div>
		</div>

	<%@include file="/WEB-INF/views/menu/docSideMenu.jsp"%>
</body>
<script type="text/javascript">
	/* table 위치 설정 */
	var table = document.querySelectorAll('table');
	for(let t of table) {
		t.setAttribute('style', 'margin:20px auto;')
	}

	/* 수정하기 */
	var modifybtn = document.querySelector('.modifyTemplate');
	modifybtn.addEventListener("click", function(){
		window.location.href = "/doc/modifyTemplateAdmin.do?sidt_temp_cd=${temDto.sidt_temp_cd}";
	});
	
	/* 삭제하기 */
	var deletebtn = document.querySelector('.deleteTemplate');
	deletebtn.addEventListener("click", function(){
		var confirmation = sweetAlertConfirm("해당 템플릿을 삭제하시겠습니까?", function(){
			if(confirmation){
				window.location.href = "/doc/deleteTemplateAdmin.do?sidt_temp_cd=${temDto.sidt_temp_cd}";
			} else {
				self.close();
			}					
		}, '');
	});
	
	
</script>
</html>