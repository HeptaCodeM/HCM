<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/menu/headerInfo.jsp" %>
<style type="text/css">
	.sealAlign{
		text-align: center;
		font-size: 10px !important;
	}
	.sealImg{
		width: auto;
		height: 35px;
	}
	
	
</style>
<title>회사정보화면</title>
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
						<h1 class="page-heading d-flex text-gray-900 fw-bold fs-3 flex-column justify-content-center my-0">HCM Company</h1>
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
							<div class="card-body pt-5">
								<div class="table-responsive">
									<table class="table table-hover table-rounded table-striped border gy-7 gs-7">
										<tbody class="fw-semibold fs-6 text-gray-800 border-bottom-2 border-gray-200">
											<tr>
												<td>회사명</td>
												<td colspan="2">${companyDto.getComp_name()}</td>
											</tr>
		
											<tr>
												<td>사업자 등록번호</td>
												<td colspan="2">${companyDto.getComp_num()}</td>
											</tr>
											
											<tr>
												<td>대표자명</td>
												<td colspan="2">${companyDto.getComp_ceo_name()}</td>
											</tr>
											
											<tr>
												<td>대표전화번호</td>
												<td colspan="2">${companyDto.getComp_tel()}</td>
											</tr>
											
											<tr>
												<td>회사메일</td>
												<td colspan="2">${companyDto.getComp_email()}</td>
											</tr>
											
											<tr>
												<td>팩스번호</td>
												<td colspan="2">${companyDto.getComp_fax()}</td>
											</tr>
											
											<tr>
												<td>주소</td>
												<td>${companyDto.getComp_addr1()}</td>
												<td>(${companyDto.getComp_addr2()})</td>
											</tr>
											
											<tr>
												<td>우편번호</td>
												<td colspan="2">${companyDto.getComp_post()}</td>
											</tr>
											
											<tr>
												<td>직인</td>
												<td colspan="2">
													<img class="sealImg" src="data:image/png;base64,${sealImg}">
												</td>
											</tr>
										</tbody>
									</table>
								</div>
								<input type="hidden" value="작성자 : ${companyDto.getComp_create_id()}">
								<input type="hidden" value="작성일자 : ${companyDto.getComp_create_dt()}">
								<c:choose>
									<c:when test="${companyDto.getComp_modify_id() eq null}">
										<input type="hidden" value="최초입력">
									</c:when>
									
									<c:otherwise>
										<input type="hidden" value="수정">
										<input type="hidden" value="수정자 : ${companyDto.getComp_modify_id()}">
										<input type="hidden" value="수정일자 : ${companyDto.getComp_modify_dt()}">
									</c:otherwise>
								</c:choose>
							</div>
							<div class="card-footer">
								<button class="btn btn-primary btnLg me-10" type="button" onclick="location.href='/hr/company/companyInfoCorrectionAdmin.do'">수정</button>
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