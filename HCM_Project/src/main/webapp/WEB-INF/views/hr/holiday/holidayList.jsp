<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<%@include file="/WEB-INF/views/menu/headerInfo.jsp" %>
	<title>HCM GroupWare</title>

	<style type="text/css">
	.table th {  vertical-align:middle; text-align:center !important; background-color:#F9F9F9; font-weight:600; }
	.holiCntInfo { text-align:center; }
	#searchHolidayList { text-align:center; }
	</style>
	<script src="/js/hr/holiday.js" type="text/javascript"></script>
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
						<h1 class="page-heading d-flex text-gray-900 fw-bold fs-3 flex-column justify-content-center my-0">인사관리 > 휴가관리 > 휴가현황</h1>
						<!--end::Title-->
					</div>
					<!--end::Page title-->
				</div>		
			</div>
			<div class="app-content flex-column-fluid">
				<!-- 내용 시작 -->
				<div class="app-content flex-column-fluid">
					<div class="app-container container-fluid">
						<div class="card card-flush h-md-50 mb-xl-10">
							<div class="card-header pt-5">
								<h3 class="card-title text-gray-800 fw-bold">휴가현황리스트</h3>
							</div>
							<div class="separator separator-dashed my-3"></div>	
							<div class="card-body pt-5">
								<div class="table-responsive">
									<table class="table table-bordered holiCntInfo">
										<tr>
											<th>휴가부여기준일</th>
											<th>총 휴가일 수</th>
											<th>휴가 사용일 수</th>
											<th>잔여 휴가일 수</th>
										</tr>
										<tr>
											<td id="standardHoliday"></td>
											<td id="totHoliday"></td>
											<td id="useHoliday"></td>
											<td id="restHoliday"></td>
										</tr>
									</table>
								</div>
								<div class="separator separator-dashed my-3"></div>	
								<div class="table-responsive">
									<form name="searchholidayDate" id="searchholidayDate" method="post">
										<table class="table table-bordered">
											<tr>
												<th>기간조회</th>
												<td>
													<div class="input-group" id="sdate" data-td-target-input="nearest" data-td-target-toggle="nearest" style="float:left;width:200px;margin-left:20px;">
													    <input id="sdate_input" type="text" name="sdate" class="form-control" data-td-target="#sdate" readonly />
													    <span class="input-group-text" data-td-target="#sdate" data-td-toggle="datetimepicker">
													    	<i class="ki-duotone ki-calendar fs-2"><span class="path1"></span><span class="path2"></span></i>
													    </span>
													</div>
													<span style="float:left;vertical-align: middle;line-height:43px;padding:0 20px;">~</span>
													<div class="input-group" id="edate" data-td-target-input="nearest" data-td-target-toggle="nearest" style="float:left;width:200px;">
													    <input id="edate_input" type="text" name="edate" class="form-control" data-td-target="#edate" readonly />
													    <span class="input-group-text" data-td-target="#edate" data-td-toggle="datetimepicker">
													        <i class="ki-duotone ki-calendar fs-2"><span class="path1"></span><span class="path2"></span></i>
													    </span>
													</div>
													<button type="button" class="btn btn-success" style="margin-left:20px;" onclick="holidaySearchList()">조회</button>
													<button type="button" class="btn btn-danger" style="margin-left:20px;" onclick="reset()">초기화</button>
												</td>
											</tr>
										</table>
									</form>
								</div>
								<div class="separator separator-dashed my-3"></div>	
								<div class="table-responsive">
									<table id="searchHolidayList" class="table table-row-bordered gy-5">
										<thead>
											<tr class="fw-semibold fs-6 text-muted">
												<th>휴가구분</th>
												<th>시작일자~종료일자</th>
												<th>차감일수</th>
												<th>승인일자</th>
											</tr>
										</thead>
									</table>
								</div>
								
							</div>
						</div>
					</div>
				</div>
				<!-- 내용 끝 -->
				
			</div>
		</div>
			
<script type="text/javascript">
$(function(){ 
	holidaySearchList()
});

var datePicker = new tempusDominus.TempusDominus(document.getElementById("sdate"), {
	display: {
		icons: {
			close: "ki-outline ki-cross fs-1",
		},
		buttons: {
			close: true,
		},
        components: {
			hours: true,
			minutes: true,
			seconds: false
		}
	},
    localization: {
		locale: "kr",
		startOfTheWeek: 1,
		format: "yyyy-MM-dd"
    }
});
var datePicker = new tempusDominus.TempusDominus(document.getElementById("edate"), {
	display: {
		icons: {
			close: "ki-outline ki-cross fs-1",
		},
		buttons: {
			close: true,
		},
        components: {
			hours: true,
			minutes: true,
			seconds: false
		}
	},
	localization: {
		locale: "kr",
		startOfTheWeek: 1,
		format: "yyyy-MM-dd"
    }
});
</script>
<%@include file="/WEB-INF/views/menu/hrSideMenu.jsp" %>		
</body>
</html>