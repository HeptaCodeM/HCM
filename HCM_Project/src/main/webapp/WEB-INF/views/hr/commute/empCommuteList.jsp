<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/menu/headerInfo.jsp"%>
<title>HCM GroupWare</title>

<style type="text/css">
.table th {vertical-align: middle; text-align:center;}
.form-check-input.radio {
	margin-right: 10px;
}
.form-check-input.radio.last {
	margin-left: 30px;
}
.top-ym {text-align:center; margin-bottom:30px;}
.top-ym>div {display: inline-block; vertical-align: middle;}
.top-ym .cur-month {font-size: 32px; vertical-align: middle; font-weight: 600; margin:0 50px;}
</style>

</head>
<%@include file="/WEB-INF/views/menu/header.jsp"%>
<body id="kt_app_body" data-kt-app-layout="dark-sidebar" data-kt-app-header-fixed="true"
	data-kt-app-sidebar-enabled="true" data-kt-app-sidebar-fixed="true" data-kt-app-sidebar-hoverable="true"
	data-kt-app-sidebar-push-header="true" data-kt-app-sidebar-push-toolbar="true" data-kt-app-sidebar-push-footer="true"
	data-kt-app-toolbar-enabled="true" class="app-default">

	<div class="app-wrapper flex-column flex-row-fluid">
		<div class="app-toolbar py-3 py-lg-6">
			<div id="kt_app_toolbar_container" class="app-container container-fluid d-flex flex-stack">
				<!--begin::Page title-->
				<div class="page-title d-flex flex-column justify-content-center flex-wrap me-3">
					<!--begin::Title-->
					<h1 class="page-heading d-flex text-gray-900 fw-bold fs-3 flex-column justify-content-center my-0">인사관리 > 근태관리
						> 출퇴근 현황</h1>
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
							<div class="card-body pt-5 table-responsive">
								<div class="top-ym">
									<div>
										<a href="/hr/commute/empCommuteList.do?getYM=${preYearMonth}" class="btn btn-icon btn-primary">
											<i class="ki-duotone ki-to-left fs-3x">
												<span class="path1"></span>
												<span class="path2"></span>
												<span class="path3"></span>
												<span class="path4"></span>
											</i>
										</a>
									</div>
									<div class="cur-month">
										${fn:substring(currentYearMonth, 0, 4)}년 ${fn:substring(currentYearMonth, 4, 6)}월
									</div>
									<div>
										<a href="/hr/commute/empCommuteList.do?getYM=${nextYearMonth}" class="btn btn-icon btn-primary">
											<i class="ki-duotone ki-to-right fs-3x">
												<span class="path1"></span>
												<span class="path2"></span>
												<span class="path3"></span>
												<span class="path4"></span>
											</i>
										</a>
									</div>
								</div>
								
								<table id="emplListTable" class="table table-hover table-rounded table-flush">
									<thead>
										<tr class="fw-semibold fs-7 border-bottom border-gray-200 py-4">
											<th class="center">일자</th>
											<th class="center">출근시간</th>
											<th class="center">퇴근시간</th>
											<th class="center">결근/조퇴</th>
											<th class="center">비고</th>
										</tr>
									</thead>
									<tbody>
									<colgroup>
										<col width="200px">
										<col width="150px">
										<col width="150px">
										<col width="80px">
										<col width="*">
									</colgroup>
									<c:forEach items="${lists}" var="list">
											<fmt:parseDate var="date" value="${list.yyyymmdd}" pattern="yyyy-MM-dd" />
											<fmt:formatDate var="preWeekName" value="${date}" pattern="EEEE" />
											<c:set var="weekName" value="${preWeekName}" />
										<tr class="py-5 fw-semibold  border-bottom border-gray-300 fs-6" style="cursor: pointer;
										<c:if test="${weekName eq '토요일'}">color:blue;</c:if>
										<c:if test="${weekName eq '일요일'}">color:red;</c:if>">
											<td class="center">
												${list.yyyymmdd}
												(${weekName})
											</td>
											<td class="center">
												<fmt:formatDate value="${list.emco_in_dt}" pattern="HH:mm:ss" />
											</td>
											<td class="center">
												<fmt:formatDate value="${list.emco_out_dt}" pattern="HH:mm:ss" />
											</td>
											<td class="center">
												<c:if test="${list.duration_hour < 9}">
													<c:if test="${list.duration_hour == 0}">
														<span style="color:#B40404;">결근</span>
													</c:if>
													<c:if test="${list.duration_hour > 0}">
														<span style="color:#04B404;">조퇴</span>
													</c:if>
												</c:if>
												<c:if test="${list.duration_hour >= 9}">
													<span style="color:#08088A;">조퇴</span>
												</c:if>
											</td>
											<td class="left">
											</td>
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

	<%@include file="/WEB-INF/views/menu/hrSideMenu.jsp"%>
</body>
</html>