<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/menu/headerInfo.jsp"%>
<title>HCM GROUPWARE</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/themes/default/style.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css">
<link rel="stylesheet" href="../ckeditor5/sample/template.css">
<style type="text/css">
	.btnMd{
		height: 38px;
	}
	
	.btn {
		display: flex;
    	justify-content: center;
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
			<div id="kt_app_toolbar_container" class="app-container container-fluid d-flex flex-stack">
				<div class="page-title d-flex flex-column justify-content-center flex-wrap me-3">
					<h1 class="page-heading d-flex text-gray-900 fw-bold fs-3 flex-column justify-content-center my-0">템플릿 등록</h1>
				</div>
			</div>
		</div>
		<div class="app-content flex-column-fluid">
			<!-- 내용 시작 -->
			<div id="kt_app_content" class="app-content flex-column-fluid">
				<div class="app-container container-fluid">
					<div class="card card-flush h-md-50 mb-xl-10">
						<div class="card-header pt-5">
							<h3 class="card-title text-gray-800 fw-bold">템플릿 등록</h3>
						</div>
						<div class="separator separator-dashed my-3"></div>
						<div class="card-body pt-5">

							<!-- ---------------------------- 내 용 입 력 -------------------------------- -->

							<!-- 						<button id="getTemplate">[TEST] 템플릿 가져오기</button> -->
							<div class="table-responsive" style="max-width: 80%; margin: 0px auto;">
							<form action="/doc/insertTemplateAdmin.do" method="post">
							<table class="table table-bordered">
								<tr>
									<th>카테고리</th>
									<td id="category">
										<select id="selectCategory" class="form-select form-select-solid" name="sica_cd"></select>
									</td>
									<th>템플릿</th>
									<td>
										<input type="text" class="form-control form-control-solid" name="sidt_temp_name" value="${temDto.sidt_temp_name }">
									</td>
								</tr>
							</table>

								<!-- <div id="category">
								결재 구분 : <select id="selectCategory" name="sica_cd"></select>
							</div>
							<h3>
								제목 <input type="text" name="sidt_temp_name">
							</h3> -->
								<textarea id="editor" name="sidt_temp_content"></textarea>
								<div class="btn">
									<input type="submit" class="btn btn-light-primary btnMd btn-color-gray-600" value="등록하기" style="margin-right: 5px;"> 
									<input type="button" class="btn btn-light-primary btnMd btn-color-gray-600" onclick="location.href='/doc/templateAdmin.do'" value="목록보기">
								</div>
							</form>
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

<script type="text/javascript" src="../ckeditor5/build/ckeditor.js"></script>
<script type="module" src="../ckeditor5/sample/script.js"></script>
<script type="text/javascript" src="/js/doc/template.js"></script>
<script type="text/javascript">
/* 템플릿 가져오기 */
document.getElementById('getTemplate').addEventListener('click', function() {
	fetch('./getTemplateAdmin.do', {
		method: 'post'
	}).then(resp => {
		return resp.text();
	}).then(data => {
		editor.setData(data)
	}).catch(error => {
		console.log(error);
	});
});

</script>
</html>