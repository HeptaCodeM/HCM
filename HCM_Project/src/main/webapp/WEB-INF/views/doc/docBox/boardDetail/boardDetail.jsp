<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/menu/headerInfo.jsp"%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/es6-promise/4.1.1/es6-promise.auto.js"></script>
<script src="https://html2canvas.hertzen.com/dist/html2canvas.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.4/jspdf.min.js"></script>
<title>결재문서 상세 페이지</title>
</head>
<%@include file="/WEB-INF/views/menu/header.jsp"%>
<style>
.card .card-header {
justify-content: center;
 align-items: center;
flex-direction:column;
}
.container {
	width: 80%;
	margin: 0 auto;
}

.footer {
	margin-top: 30px;
}

table {
	width: 100%;
	border-collapse: collapse;
}

th, td {
	padding: 5px;
	text-align: left;
}
.top-table, .top-table th, .top-table td, .top-table tr{
 border: solid 1px;
 text-align: center;
 vertical-align: middle;
}

.header {
	float: right;
	top: 10px; /* 원하는 위치 조정 */
	right: 10px; /* 원하는 위치 조정 */
	width: 30%; /* 크기 조정 */
	height: 30%;
}

.content {
	margin-top: 15%;
	width: 100%;
}
.transparent-table, .transparent-table td, .transparent-table tr {
    border: none;
}
.transparent-table {
    margin-left : 7%;
}
.reply-table, .reply-table td, .reply-table tr, .reply-table th{
 text-align: center;
 vertical-align: middle;
}
</style>
<body id="kt_app_body" data-kt-app-layout="dark-sidebar"
	data-kt-app-header-fixed="true" data-kt-app-sidebar-enabled="true"
	data-kt-app-sidebar-fixed="true" data-kt-app-sidebar-hoverable="true"
	data-kt-app-sidebar-push-header="true"
	data-kt-app-sidebar-push-toolbar="true"
	data-kt-app-sidebar-push-footer="true"
	data-kt-app-toolbar-enabled="true" class="app-default">

	<div class="app-wrapper flex-column flex-row-fluid">
		<div class="app-toolbar py-3 py-lg-6">
			<div id="kt_app_toolbar_container"
				class="app-container container-fluid d-flex flex-stack">
				<!--begin::Page title-->
				<div
					class="page-title d-flex flex-column justify-content-center flex-wrap me-3">
					<!--begin::Title-->
					<h1
						class="page-heading d-flex text-gray-900 fw-bold fs-3 flex-column justify-content-center my-0">결재문서
						상세 페이지</h1>
					<!--end::Title-->
				</div>
				<!--end::Page title-->
			</div>
		</div>
		<div class="app-content flex-column-fluid">
		<c:set var="docDto1" value="${docDto[1]}" />
			<!-- 내용 시작 -->
			<div id="kt_app_content" class="app-content flex-column-fluid">
				<div class="app-container container-fluid">
					<div class="card card-flush h-md-50 mb-xl-10">
						<div style="text-align:center;"  class="card-header pt-5">
							<h1  style="text-align:center;" class="card-title text-gray-800 fw-bold">${docDto1.empl_name} ${docDto1.empl_rank}님이 기안한 ${docDto1.sidt_temp_name}입니다.</h1>
							 <br>
							 <div>
							<table class="transparent-table">
								<tr>
									<td style="min-width:70px;">문서번호 :</td>
									<td style="width:300px;">${docDto1.sidb_doc_num}</td>
									<td style="min-width:70px;">기안자 :</td>
									<td style="width:300px;">${docDto1.empl_name}</td>
									<td style="min-width:70px;">보존년한 :</td>
									<td style="width:300px;">
									<fmt:parseDate var="patternDate"
													value="${docDto1.sidb_doc_expiredt}"
													pattern="yyyy-MM-dd HH:mm:ss" /> <fmt:formatDate
													value="${patternDate}" pattern="yyyy년 MM월 dd일" />
									</td>
								</tr>
								<tr>
									<td>문서분류 :</td>
									<td>${docDto1.sidt_temp_name}</td>
									<td>기안부서 :</td>
									<td>${docDto1.writer_dt}</td>
									<td>기안일자 :</td>
									<td><fmt:parseDate var="patternDate"
													value="${docDto1.sidb_doc_writedt}"
													pattern="yyyy-MM-dd HH:mm:ss" /> <fmt:formatDate
													value="${patternDate}" pattern="yyyy년 MM월 dd일" />
									</td>
								</tr>
							</table>
							참조자 : ${docDto1.empl_ref} <br>
							참조부서 :  ${docDto1.empl_dept_cd} 
							</div>
						</div>
						<div class="separator separator-dashed my-3"></div>
						<div class="card-body pt-5">

							<div  class="container">
							<div id="pdfZone">
								<div class="header">
									<div></div>
									<table class="top-table">
										<tr>
											<th rowspan="4">결<br>재
											</th>
										<tr style="height:30px;">
											<c:forEach items="${docDto}" var="dt" varStatus="i">
												<td>${dt.appr_name} ${dt.appr_rank}</td>
											</c:forEach>
										</tr>
										<tr style="height:80px;">
											<c:forEach items="${docDto}" var="dt" varStatus="i">
												<td><c:choose>
													<%-- 	<c:when test="${empty dt.appr_sign}"> --%>
														<c:when test="${dt.appr_sign == null || dt.appr_sign ==' '|| dt.appr_sign ==''}">
            											    &nbsp;
         											   </c:when>
														<c:otherwise>
															<img style="width: 80px; height: 70px; border: none;"
																src="${dt.appr_sign}" />
														</c:otherwise>
													</c:choose></td>
											</c:forEach>
										</tr>	
										<tr style="height:30px;">
											<c:forEach items="${docDto}" var="dt" varStatus="i">
												<td>${dt.appr_dt}</td>
											</c:forEach>
										</tr>
									</table>
								</div>
								<div style="height: 30px;">
								</div>
								<div class="content">
										<input id="docTitle" type="hidden" value="${docDto1.sidb_doc_title}">
									${docDto1.sidb_doc_content}
								</div>
								</div>	
								
									<div class="separator separator-dashed my-3"></div>
									
									<div style="margin-top:50px; text-align:right;">
									첨부파일:<select id="selectFile" style="min-width:100px; margin-right:1.5%;"></select> <button class="btn btn-primary btnMd" id="downBtn" style="font-size: small;">다운로드</button>
									<button class="btn btn-primary btnMd" id="savePdf" style="font-size: small;">PDF 저장</button>
									</div>
								<div class="footer">
									<div style="text-align:center; font-size:large; margin-bottom:20px;">첨언내역</div>
									<table  class="table table-bordered reply-table" >
										<tr >
											<th>작성자</th>
											<th>결재결과</th>
											<th style="margin-right: 10%;">내용</th>
										</tr>

										<c:forEach items="${docDto}" var="dt">

											<tr style="height:50px;">
												<td style="width:100px;">${dt.appr_name} ${dt.appr_rank}
												</td>
												<td style="width:100px;">
												 <c:choose>
														<c:when test="${dt.appr_flag eq 1}">
															승인
														</c:when>
														<c:when test="${dt.appr_flag eq 2}">
															반려
														</c:when>
												 </c:choose>
												</td>
												<td>${dt.appr_reply}<br></td>
											</tr>
										</c:forEach>
										<%-- 	<tr>
											<td>${docDto1.appr_name} <br> <br></td>
											<td>${docDto1.appr_reply}<br> <br></td>
										</tr> --%>
									</table>
								</div>
								
		<div style="text-align: center; margin-top: 30px;">
	<c:if test="${sessionScope.userInfoVo.empl_id eq docDto1.sidb_curr_id}">
    <button type="button" class="btn btn-primary btnLg" data-bs-toggle="modal" data-bs-target="#kt_modal_3">승인</button>
    <span style="min-width:100px;">&nbsp;</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <button type="button" class="btn btn-danger btnLg" data-bs-toggle="modal" data-bs-target="#kt_deny">반려</button>
	</c:if>

	<c:if test="${sessionScope.userInfoVo.empl_id eq docDto1.empl_id and docDto1.sidb_doc_stat == 1}">
  <!--   <button type="button" class="btn btn-primary" onclick="docEdit()">품의수정</button> -->
    <button type="button" class="btn btn-danger" onclick="gianCancel()">상신취소</button>
	<form id="gianCancel" action="./gianCancel.do" method="post">
						<input type="hidden" value="${docDto1.sidb_doc_num}" name="docNum"/>
					</form>
	</c:if>
	
	<%-- <c:if test="${sessionScope.userInfoVo.empl_id eq docDto1.empl_id and docDto1.sidb_doc_stat == 2}">
    <button type="button" class="btn btn-danger" onclick="denyPlease()">반려요청</button>
	</c:if> --%>
		</div>
								
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<%@include file="/WEB-INF/views/menu/docSideMenu.jsp"%>


	<!-- 승인 모달창 -->
	<div class="modal fade" tabindex="-1" id="kt_modal_3">
		<div class="modal-dialog">
			<div class="modal-content position-absolute">
				<div class="modal-header">
					<h5 class="modal-title">승인</h5>

					<!--begin::Close-->
					<div class="btn btn-icon btn-sm btn-active-light-primary ms-2"
						data-bs-dismiss="modal" aria-label="Close">
						<i class="ki-duotone ki-cross fs-2x"><span class="path1"></span><span
							class="path2"></span></i>
					</div>
					<!--end::Close-->
				</div>

				<!-- 승인 첨언 입력 form -->
				<div class="modal-body">
					<p>첨언</p>
					<form id="reply" action="./approve.do" method="post">
					<input type="hidden" value="${docDto1.sidb_doc_num}" id="docNum" name="docNum"/>
						<textarea name="reply" style="width: 100%;"></textarea>
					</form>
				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-primary me-10"
						onclick="approve()">승인</button>
						
				</div>
			</div>
		</div>
	</div>

	<!-- 반려 모달창 -->
	<div class="modal fade" tabindex="-1" id="kt_deny">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h3 class="modal-title">반려</h3>

					<!--begin::Close-->
					<div class="btn btn-icon btn-sm btn-active-light-primary ms-2"
						data-bs-dismiss="modal" aria-label="Close">
						<i class="ki-duotone ki-cross fs-1"><span class="path1"></span><span
							class="path2"></span></i>
					</div>
					<!--end::Close-->
				</div>

				<!-- 반려사유 입력 form -->
				<div class="modal-body">
					<p>반려사유</p>
					<form id="denyReply" action="./deny.do" method="post">
						<textarea name="reply" style="width: 100%;"></textarea>
						<input type="hidden" value="${docDto1.sidb_doc_num}" name="docNum"/>
					</form>
				</div>

				<div class="modal-footer">
					<button type="submit" class="btn btn-danger me-10" onclick="deny()">반려</button>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="/js/doc/boardDetail.js"></script>


</html>