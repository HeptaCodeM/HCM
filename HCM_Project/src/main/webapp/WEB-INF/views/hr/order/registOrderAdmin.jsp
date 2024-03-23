<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<%@include file="/WEB-INF/views/menu/headerInfo.jsp" %>
	<title>HCM GroupWare</title>
	<style type="text/css">
	.table th {  vertical-align:middle; text-align:center !important; background-color:#F9F9F9; font-weight:600; }
	</style>
	<script type="text/javascript" src="/js/hr/order.js"></script>
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
			<br>
			<div class="app-content flex-column-fluid">
				<!-- 내용 시작 -->
				<div id="kt_app_content" class="app-content flex-column-fluid">
					<div class="app-container container-fluid">
						<div class="card card-flush h-md-50 mb-xl-10">
							<div class="card-header pt-5">
								<h3 class="card-title text-gray-800 fw-bold">인사관리 > 인사발령관리 > 발령등록</h3>
							</div>
							<div class="separator separator-dashed my-3"></div>
							<form name="registOrderForm" id="registOrderForm" method="post" action="/hr/order/registOrderAdminOk.do">
								<div class="table-responsive">
									<table class="table table-bordered">
										<thead>
										<tr>
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
										</tr>
										</thead>
										<tbody>
										<tr>
											<td>
												<div class="input-group" id="emod_order_dt1" data-td-target-input="nearest" data-td-target-toggle="nearest" style="float:left;width:200px;">
												    <input id="emod_order_dt_input1" type="text" name="emod_order_dt" class="form-control" data-td-target="#emod_order_dt1" readonly />
												    <span class="input-group-text picker" data-td-target="#emod_order_dt1" data-td-toggle="datetimepicker">
												    	<i class="ki-duotone ki-calendar fs-2"><span class="path1"></span><span class="path2"></span></i>
												    </span>
												</div>
											</td>
											<td>
												<input type="text" class="form-control form-control-solid" name="empl_id" id="empl_id1" maxlength="20"  readonly required="required">
											</td>
											<td>
												<input type="text" class="form-control form-control-solid" name="empl_name" id="empl_name1" maxlength="20"  readonly>
											</td>
											<td>
												<input type="text" class="form-control form-control-solid" name="emod_type" id="emod_type1" maxlength="20"  readonly required="required">
											</td>
											<td>
												<input type="text" class="form-control form-control-solid" name="emod_prev_dept" id="emod_prev_dept1" maxlength="20"  readonly>
											</td>
											<td>
												<select NAME="emod_order_dept" id="emod_order_dept1" class="form-select form-select-solid searchEmpSelect">
													<option value="">부서선택</option>
													<c:forEach items="${deptList}" var="dept">
													<option value="${dept.coco_cd}">${dept.coco_name}</option>
													</c:forEach>
												</select>
											</td>
											<td>
												<input type="text" class="form-control form-control-solid" name="emod_prev_rank" id="emod_prev_rank1" maxlength="20"  readonly>
											</td>
											<td>
												<input type="text" class="form-control form-control-solid" name="emod_order_rank" id="emod_order_rank1" maxlength="20"  readonly>
											</td>
											<td>
												<input type="text" class="form-control form-control-solid" name="emod_prev_position" id="emod_prev_position1" maxlength="20"  readonly>
											</td>
											<td>
												<input type="text" class="form-control form-control-solid" name="emod_order_position" id="emod_order_position1" maxlength="20"  readonly>
											</td>
										</tr>
										</tbody>
									</table>
								</div>						
								<div style="text-align: right;margin-bottom:20px;">
									<button type="button" class="btn btn-primary me-10" id="kt_button_1" onclick="registEmpAuth()">
									    <span class="indicator-label">
									        등록
									    </span>
									    <span class="indicator-progress">
									        Please wait... <span class="spinner-border spinner-border-sm align-middle ms-2"></span>
									    </span>
									</button>
									<button type="button" class="btn btn-success me-10" id="kt_button_1" onclick="history.back();">
									    <span class="indicator-label">
									        취소
									    </span>
									</button>
								</div>
							</form>
						</div>
					</div>
				</div>
				<!-- 내용 끝 -->
			</div>
		</div>
		
		<!-- 사원검색 Layer 시작 -->
		<div id="empSearch">
			<div style="text-align:right;" >
				<a href="javascript:void(0);" onclick="closeEmpInfoSearch();">
					<i class="ki-duotone ki-cross-square fs-2x">
					<span class="path1"></span><span class="path2"></span>
					</i>
				</a>
			</div>
			<div>
				<form name="searchEmpInfo" id="searchEmpInfo" method="post" action="/hr/employee/getUserInfoSearch.do">
					<table class="table">
						<tr>
							<th>사원검색</th>
							<td>
								<select name="searchType" class="form-select">
									<option value="empl_name">성명</option>
									<option value="empl_id">사번</option>
								</select>
							</td>
							<td>
								<input type="text" class="form-control form-control-solid" name="keyWord" id="keyWord" maxlength="20" required="required">
							</td>
							<td>
								<button type="button" class="btn btn-success" id="kt_button_1" onclick="empInfoSearch()">
								    <span class="indicator-label">
								        검색
								    </span>
								    <span class="indicator-progress">
								        Please wait... <span class="spinner-border spinner-border-sm align-middle ms-2"></span>
								    </span>
								</button>
							</td>
						</tr>
					</table>
				</form>
			</div>
			<div class="table-responsive">
			<table id="searchEmployeeList" class="table table-row-bordered gy-5">
				<thead>
					<tr class="fw-semibold fs-6 text-muted">
						<th>사번</th>
						<th>성명</th>
						<th>부서</th>
						<th>직위</th>
						<th>직책</th>
					</tr>
				</thead>
			</table>
			</div>
		</div>
		<!-- 사원검색 Layer 종료 -->
<script type="text/javascript">
$(function(){ 
	$("#searchEmployeeList").DataTable({
		displayLength: 5,
		lengthChange: false,
		info: false
	});

	$("#searchEmployeeList tbody").on('click', 'tr', function () {
		var row = $("#searchEmployeeList").DataTable().row($(this)).data();
		$("#empl_id").val(row.empl_id);
		$("#empl_name").val(row.empl_name);
		$("#empl_dept_nm").val(row.empl_dept_nm);
		$("#empl_rank_nm").val(row.empl_rank_nm);
		$("#empl_position_nm").val(row.empl_position_nm);
		
		closeEmpInfoSearch();
	});
});

$(".input-group-text.picker").on('click', function(){
	console.log($(this).data("td-target").replace('#',''));
	var idVal = $(this).data("td-target").replace('#','');
	var datePicker = new tempusDominus.TempusDominus(document.getElementById(""+idVal+""), {
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

});
/*
var datePicker = new tempusDominus.TempusDominus(document.getElementById("emod_order_dt1"), {
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
*/
</script>
<%@include file="/WEB-INF/views/menu/hrSideMenu.jsp" %>		
</body>
</html>