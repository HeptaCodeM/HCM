<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/menu/headerInfo.jsp" %>
<title>회사정보수정화면</title>
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
						<h1 class="page-heading d-flex text-gray-900 fw-bold fs-3 flex-column justify-content-center my-0">HCM Company!</h1>
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
								<h3 class="card-title text-gray-800 fw-bold">회사정보</h3>
							</div>
							<div class="separator separator-dashed my-3"></div>	
							<div class="card-body pt-5" >
								<form action="/hr/company/correctionCompanyInfoAdmin.do" onsubmit="return valChk()" method="post" enctype="multipart/form-data">
									<div class="table-responsive">
										<table class="table table-hover table-rounded table-striped border gy-7 gs-7">
											<tbody class="fw-semibold fs-6 text-gray-800 border-bottom-2 border-gray-200">
												<tr>
													<td>회사명</td>
													<td colspan="2"><input name="comp_name" id="comp_name" class="form-control form-control-solid" type="text" value="${companyDto.getComp_name()}" maxlength="20"></td>
												</tr>
			
												<tr>
													<td>사업자 등록번호</td>
													<td colspan="2"><input name="comp_num" id="comp_num" class="form-control form-control-solid" type="text" value="${companyDto.getComp_num()}" maxlength="12"></td>
												</tr>
												
												<tr>
													<td>대표자명</td>
													<td colspan="2"><input name="comp_ceo_name" id="comp_ceo_name" class="form-control form-control-solid" type="text" value="${companyDto.getComp_ceo_name()}" maxlength="6"></td>
												</tr>
												
												<tr>
													<td>대표전화번호</td>
													<td colspan="2"><input name="comp_tel" id="comp_tel" class="form-control form-control-solid" type="text" value="${companyDto.getComp_tel()}" maxlength="15"></td>
												</tr>
												
												<tr>
													<td>회사메일</td>
													<td colspan="2"><input name="comp_email" id="comp_email" class="form-control form-control-solid" type="text" value="${companyDto.getComp_email()}" maxlength="40"></td>
												</tr>
												
												<tr>
													<td>팩스번호</td>
													<td colspan="2"><input name="comp_fax" id="comp_fax" class="form-control form-control-solid" type="text" value="${companyDto.getComp_fax()}" maxlength="15"></td>
												</tr>
												
												<tr>
													<td>주소</td>
													<td><input name="comp_addr1" id="comp_addr1" class="form-control form-control-solid" type="text" value="${companyDto.getComp_addr1()}" maxlength="40"></td>
													<td><input name="comp_addr2" id="comp_addr2" class="form-control form-control-solid" type="text" value="${companyDto.getComp_addr2()}" maxlength="10"></td>
												</tr>
												
												<tr>
													<td>우편번호</td>
													<td colspan="2"><input name="comp_post" id="comp_post" class="form-control form-control-solid" type="text" value="${companyDto.getComp_post()}" maxlength="5"></td>
												</tr>
												
												<tr>
													<td>회사직인</td>
													<td colspan="2">
														<input type="file" class="form-control form-control-solid" id="comp_seal" name="comp_seal">
													</td>
												</tr>
											</tbody>
											<tfoot>
												<tr>
													<td colspan="3">
														<button class="btn btn-primary btnLg me-10" type="submit">저장</button>
														<button class="btn btn-primary btnLg me-10" type="reset">초기화</button>
														<a onclick="javascript:window.history.back(-1)" class="btn btn-primary btnLg me-10">취소</a>
														<input name="comp_id" id="comp_id" type="hidden" value="${companyDto.getComp_id()}">
													</td>	
												</tr>
											</tfoot>
										</table>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
				<!-- 내용 끝 -->
			</div>
		</div>
			
<%@include file="/WEB-INF/views/menu/hrSideMenu.jsp" %>		
</body>
<script type="text/javascript" src="/js/hr/company.js"></script>
</html>