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
<link rel="stylesheet" href="https://cdn.datatables.net/1.13.7/css/jquery.dataTables.css" />	
<script src="https://cdn.datatables.net/1.13.7/js/jquery.dataTables.js"></script>
<title>임시보관함</title>
<style type="text/css">
table.dataTable thead th, table.dataTable thead td, table.dataTable tfoot th, table.dataTable tfoot td {
    text-align: center;
}

.btnSm{
width:70px;
}
.picture{
 display: inline-block; 
 vertical-align: middle;
 width: 50px; 
 height: 50px; 
 border-radius: 22px;
 background-repeat: no-repeat; 
 background-position: center; 
 background-size: cover;
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

	<div class="app-wrapper flex-column flex-row-fluid">
		<div class="app-toolbar py-3 py-lg-6">
			<div id="kt_app_toolbar_container"
				class="app-container container-fluid d-flex flex-stack">
				<!--begin::Page title-->
				<div
					class="page-title d-flex flex-column justify-content-center flex-wrap me-3">
					<!--begin::Title-->
					<h1
						class="page-heading d-flex text-gray-900 fw-bold fs-3 flex-column justify-content-center my-0">
						결재문서함</h1>
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
							<h3 class="card-title text-gray-800 fw-bold">결재문서 임시 보관함</h3>
						</div>
						<div class="separator separator-dashed my-3"></div>
						<div class="card-body pt-5" style="height: 80%;">


	<div id="tableOuter">
		<table id="tempBox" class="stripe hover" style="text-align:center;">
			<thead>
				<tr style="text-align:center;">
					<th>문서구분</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="dto" items="${lists}">
					<tr style="min-height:200px; text-align:center; ">
					
						<td>
						<input type="hidden" value = "${dto.sidb_doc_title}" class="docName">
							 ${dto.sidt_temp_name}
						</td>
						<td><a href="/doc/getTempDoc.do?sitb_doc_num=${dto.sidb_doc_num}">${dto.sidb_doc_title}</a></td>
						
						<td><div class="picture" style="background-image: url('${dto.empl_pictureStr}');"></div>
    						<span style="vertical-align: middle;">${dto.empl_name}</span></td>						
						<td style="text-align:center;">
						<fmt:parseDate var="patternDate"	value="${dto.sidb_doc_writedt}"	pattern="yyyy-MM-dd HH:mm:ss" /> 
								<fmt:formatDate value="${patternDate}" pattern="yyyy년 MM월 dd일" /></td>
							
						<td>
							<button type="submit" class="btn btn-light-danger btnSm" onclick="deleteAjax(${dto.sidb_doc_num}, '${dto.sidb_doc_title}')">삭제</button>						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</div>
<div class="d-flex flex-stack">
															

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
	<%@include file="/WEB-INF/views/menu/docSideMenu.jsp"%>




</body>
<script type="text/javascript" src="../js/doc/docBox.js"></script>


</html>