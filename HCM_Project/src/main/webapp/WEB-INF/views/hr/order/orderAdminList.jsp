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
	#searchOrderAdminList { text-align:center; }
	#searchOrderAdminList thead th {padding-left:0; cursor:pointer;}
	.searchLast>span, 
	.searchLast>button { float:left; margin-right:20px; }
	.searchEmpInput{ width: 300px; height: 40px; }
	.searchEmpSelect{ width: 130px; height: 40px; }
	.searchEmpDate{ width: 290px; height: 40px; }
	
	.datePic input, .datePic .input-group-text { height:30px; }
	.datePic>span { line-height:30px; }
	</style>
	<script src="/js/hr/order.js" type="text/javascript"></script>
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
						<h1 class="page-heading d-flex text-gray-900 fw-bold fs-3 flex-column justify-content-center my-0">인사관리 > 인사발령관리 > 인사발령 현황관리</h1>
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
								<h3 class="card-title text-gray-800 fw-bold">인사발령 현황리스트</h3>
							</div>
							<div class="separator separator-dashed my-3"></div>	
							<div class="card-body pt-5">
								<div class="table-responsive">
									<form name="searchOrderAdminForm" id="searchOrderAdminForm" method="post">
										<table class="table table-bordered">
											<tr>
												<th rowspan="6">검색</th>
												<th>기간조회</th>
												<td class="datePic">
													<div class="input-group" id="sdate" data-td-target-input="nearest" data-td-target-toggle="nearest" style="float:left;width:200px;">
													    <input id="sdate_input" type="text" name="sdate" class="form-control" data-td-target="#sdate" readonly />
													    <span class="input-group-text" data-td-target="#sdate" data-td-toggle="datetimepicker">
													    	<i class="ki-duotone ki-calendar fs-2"><span class="path1"></span><span class="path2"></span></i>
													    </span>
													</div>
													<span style="float:left;vertical-align: middle;padding:0 20px;">~</span>
													<div class="input-group" id="edate" data-td-target-input="nearest" data-td-target-toggle="nearest" style="float:left;width:200px;">
													    <input id="edate_input" type="text" name="edate" class="form-control" data-td-target="#edate" readonly />
													    <span class="input-group-text" data-td-target="#edate" data-td-toggle="datetimepicker">
													        <i class="ki-duotone ki-calendar fs-2"><span class="path1"></span><span class="path2"></span></i>
													    </span>
													</div>
												</td>
											</tr>
											<tr>
												<th>부서</th>
												<td>
													<c:forEach items="${deptList}" var="dept">
													<div class="form-check" style="float:left;margin-right:20px;">
													    <input class="form-check-input" type="checkbox" name="empl_dept_cd" id="empl_dept_cd" value="${dept.coco_cd}">
													    <label class="form-check-label" for="flexCheckChecked">
															${dept.coco_name}
													    </label>
													</div>
													</c:forEach>
												</td>
											</tr>
											<tr>
												<th>직위</th>
												<td>
													<c:forEach items="${rankList}" var="rank">
													<div class="form-check" style="float:left;margin-right:20px;">
													    <input class="form-check-input" type="checkbox" name="empl_rank_cd" value="${rank.coco_cd}">
													    <label class="form-check-label" for="flexCheckChecked">
															${rank.coco_name}
													    </label>
													</div>
													</c:forEach>
												</td>
											</tr>
											<tr>
												<th>직책</th>
												<td>
													<c:forEach items="${positionList}" var="position">
													<div class="form-check" style="float:left;margin-right:20px;">
													    <input class="form-check-input" type="checkbox" name="empl_position_cd" value="${position.coco_cd}">
													    <label class="form-check-label" for="flexCheckChecked">
															${position.coco_name}
													    </label>
													</div>
													</c:forEach>
												</td>
											</tr>
											<tr>
												<th>발령구분</th>
												<td>
													<c:forEach items="${orderList}" var="order">
													<div class="form-check" style="float:left;margin-right:20px;">
													    <input class="form-check-input" type="checkbox" name="emod_type" value="${order.coco_cd}">
													    <label class="form-check-label" for="flexCheckChecked">
															${order.coco_name}
													    </label>
													</div>
													</c:forEach>
												</td>
											</tr>
											<tr>
												<th>조건</th>
												<td class="searchLast">
													<span>
														<select name="empl_delflag" id="empStaCtg" class="form-select form-select-solid searchEmpSelect">
															<option value="">재직여부</option>
															<option value="N">재직</option>
															<option value="Y">퇴직</option>
														</select>
													</span>
													<span>
														<select NAME="searchType" id="searchType" class="form-select form-select-solid searchEmpSelect">
															<option value="">검색분류</option>
															<option value="EMPL_NAME">성명</option>
															<option value="EMPL_ID">사원번호</option>
														</select>
													</span>
													<span>
														<input type="text" class="form-control form-control-solid searchEmpInput" id="keyWord" name="keyWord" placeholder="검색">
													</span>
													<button type="button" class="btn btn-success" onclick="orderSearchAdminList()">조회</button>
													<button type="button" class="btn btn-danger" onclick="reset()">초기화</button>
												</td>
											</tr>
										</table>
									</form>
								</div>
								<div class="separator separator-dashed my-3"></div>	
								<div class="table-responsive">
									<table id="searchOrderAdminList" class="table table-row-bordered table-hover gy-5">
										<thead>
											<tr class="fw-semibold fs-6 text-muted">
												<th>발령번호</th>
												<th>발령일자</th>
												<th>사번</th>
												<th>성명</th>
												<th>발령구분</th>
												<th>이전부서</th>
												<th>발령부서</th>
												<th>이전직위</th>
												<th>발령직위</th>
												<th>이전직책</th>
												<th>발령직책</th>
												<th>구분</th>
											</tr>
										</thead>
									</table>
								</div>
								
							</div>
							<div class="card-footer" style="text-align:right;">
								<a href="/hr/order/registOrderAdmin.do" class="btn btn-primary">발령정보 추가</a>
						    </div>
						</div>
					</div>
				</div>
				<!-- 내용 끝 -->
				
			</div>
		</div>
			
<script type="text/javascript">
$(function(){ 
	orderSearchAdminList()

	$("#searchOrderAdminList tbody").on('click', 'tr', function () {
		var row = $("#searchOrderAdminList").DataTable().row($(this)).data();
		location.href = "/hr/order/orderAdminDetail.do?emor_id="+row.emor_id;
	});

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