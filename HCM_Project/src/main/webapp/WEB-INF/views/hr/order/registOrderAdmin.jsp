<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<%@include file="/WEB-INF/views/menu/headerInfo.jsp" %>
	<title>HCM GroupWare</title>
	<style type="text/css">
	.table.regiform th {  vertical-align:middle; text-align:center !important; background-color:#F9F9F9; font-weight:600; }
	.table.regiform th:nth-child(1) { width:120px !important; }
	.table.regiform th:nth-child(2) { width:100px; }
	.table.regiform th:nth-child(3) { width:130px; }
	.table.regiform th:nth-child(11) { width:60px; }
	.table.regiform th:nth-child(4),
	.table.regiform th:nth-child(6),
	.table.regiform th:nth-child(8),
	.table.regiform th:nth-child(10) { width:131px; }
	.datepicker { height: 43px; }
	.datepicker-days { background-color: #fff; border: 1px solid #ccc; }
	.datepicker-title { border-bottom: 1px dotted #ccc !important; padding: 10px 0 !important; }
	</style>
	<script type="text/javascript" src="/js/hr/order.js"></script>
	<!-- Bootstrap Datepicker CSS -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css">
	<!-- Bootstrap Datepicker JS -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
	<!-- Bootstrap-datepicker 한글 번역 파일 CDN -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/locales/bootstrap-datepicker.kr.min.js"></script>
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
						<div class="card card-flush h-md-50 mb-xl-10" id="kt_docs_repeater_basic">
							<div class="card-header pt-5">
								<h3 class="card-title text-gray-800 fw-bold">인사관리 > 인사발령관리 > 발령등록</h3>
							</div>
							<div class="separator separator-dashed my-3"></div>
							<form name="registOrderForm" id="registOrderForm" method="post" action="/hr/order/registOrderAdminOk.do" >
								<div class="table-responsive">
									<table class="table table-bordered regiform">
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
											<th>삭제</th>
										</tr>
										</thead>
										<tbody id="repeater">
										<tr class="item">
											<td>
						                        <input type="text" class="form-control form-control-solid datepicker" name="emod_order_dt">
											</td>
											<td>
												<input type="text" class="form-control form-control-solid" name="empl_id" maxlength="20" readonly required="required" onclick="openEmpInfoSearch()">
											</td>
											<td>
												<input type="text" class="form-control form-control-solid" name="empl_name" maxlength="20" readonly>
											</td>
											<td>
												<select NAME="emod_type" class="form-select form-select-solid searchEmpSelect">
													<option value="">발령구분</option>
													<c:forEach items="${orderList}" var="order">
													<option value="${order.coco_cd}">${order.coco_name}</option>
													</c:forEach>
												</select>
											</td>
											<td>
												<input type="text" class="form-control form-control-solid" name="emod_prev_dept" maxlength="20" readonly>
											</td>
											<td>
												<select NAME="emod_order_dept" id="emod_order_dept1" class="form-select form-select-solid searchEmpSelect" disabled>
													<option value="">부서선택</option>
													<c:forEach items="${deptList}" var="dept">
													<option value="${dept.coco_cd}">${dept.coco_name}</option>
													</c:forEach>
												</select>
											</td>
											<td>
												<input type="text" class="form-control form-control-solid" name="emod_prev_rank" maxlength="20" readonly>
											</td>
											<td>
												<select NAME="emod_order_rank" id="emod_order_rank1" class="form-select form-select-solid searchEmpSelect" disabled>
													<option value="">직위선택</option>
													<c:forEach items="${rankList}" var="rank">
													<option value="${rank.coco_cd}">${rank.coco_name}</option>
													</c:forEach>
												</select>
											</td>
											<td>
												<input type="text" class="form-control form-control-solid" name="emod_prev_position" maxlength="20" readonly>
											</td>
											<td>
												<select NAME="emod_order_position" class="form-select form-select-solid searchEmpSelect" disabled>
													<option value="">직책선택</option>
													<c:forEach items="${positionList}" var="position">
													<option value="${position.coco_cd}">${position.coco_name}</option>
													</c:forEach>
												</select>
											</td>
											<td style="text-align: center; vertical-align: middle;">
												-
											</td>
										</tr>
										</tbody>
									</table>
								</div>

								<div style="text-align: right;margin-bottom:20px;">
									<button type="button" class="btn btn-primary me-4" id="kt_button_1" onclick="registEmpAuth()">
									    <span class="indicator-label">
									        등록
									    </span>
									    <span class="indicator-progress">
									        Please wait... <span class="spinner-border spinner-border-sm align-middle ms-2"></span>
									    </span>
									</button>
									<button type="button" class="btn btn-success me-4" id="kt_button_1" onclick="history.back();">
									    <span class="indicator-label">
									        취소
									    </span>
									</button>
								</div>
							</form>
						    <div class="form-group mt-5">
						        <a href="javascript:;" id="add" class="btn btn-light-primary">
						            <i class="ki-duotone ki-plus fs-3"></i>
						            Add
						        </a>
						    </div>							
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
// Add Row HandleBarJS
var repeatHtml = "";
repeatHtml+="<tr class='item'>"
repeatHtml+="<td>"
repeatHtml+="	<input type='text' class='form-control form-control-solid datepicker' name='emod_order_dt'>";
repeatHtml+="</td>";
repeatHtml+="<td>";
repeatHtml+="	<input type='text' class='form-control form-control-solid' name='empl_id' maxlength='20' readonly required='required' onclick='openEmpInfoSearch(this)'>";
repeatHtml+="</td>";
repeatHtml+="<td>";
repeatHtml+="	<input type='text' class='form-control form-control-solid' name='empl_name' maxlength='20' readonly>";
repeatHtml+="</td>";
repeatHtml+="<td>";
repeatHtml+="	<select NAME='emod_type' class='form-select form-select-solid searchEmpSelect'>";
repeatHtml+="		<option value=''>발령구분</option>";
<c:forEach items="${orderList}" var="order">
repeatHtml+="		<option value='${order.coco_cd}'>${order.coco_name}</option>";
</c:forEach>
repeatHtml+="	</select>";
repeatHtml+="</td>";
repeatHtml+="<td>";
repeatHtml+="	<input type='text' class='form-control form-control-solid' name='emod_prev_dept' maxlength='20' readonly>";
repeatHtml+="</td>";
repeatHtml+="<td>";
repeatHtml+="	<select NAME='emod_order_dept' class='form-select form-select-solid searchEmpSelect' disabled>";
repeatHtml+="		<option value=''>부서선택</option>";
<c:forEach items="${deptList}" var="dept">
repeatHtml+="		<option value='${dept.coco_cd}'>${dept.coco_name}</option>";
</c:forEach>
repeatHtml+="	</select>";
repeatHtml+="</td>";
repeatHtml+="<td>";
repeatHtml+="	<input type='text' class='form-control form-control-solid' name='emod_prev_rank' maxlength='20' readonly>";
repeatHtml+="</td>";
repeatHtml+="<td>";
repeatHtml+="	<select NAME='emod_order_rank' class='form-select form-select-solid searchEmpSelect' disabled>";
repeatHtml+="		<option value=''>직위선택</option>";
<c:forEach items="${rankList}" var="rank">
repeatHtml+="		<option value='${rank.coco_cd}'>${rank.coco_name}</option>";
</c:forEach>
repeatHtml+="	</select>";
repeatHtml+="</td>";
repeatHtml+="<td>";
repeatHtml+="	<input type='text' class='form-control form-control-solid' name='emod_prev_position' maxlength='20' readonly>";
repeatHtml+="</td>";
repeatHtml+="<td>";
repeatHtml+="	<select NAME='emod_order_position' id='emod_order_position1' class='form-select form-select-solid searchEmpSelect' disabled>";
repeatHtml+="		<option value=''>직책선택</option>";
<c:forEach items="${positionList}" var="position">
repeatHtml+="		<option value='${position.coco_cd}'>${position.coco_name}</option>";
</c:forEach>
repeatHtml+="	</select>";
repeatHtml+="</td>";
repeatHtml+="<td style='text-align: center; vertical-align: middle;'>";
repeatHtml+="	<a href='javascript:void(0)' class='remove'>";
repeatHtml+="		<i class='ki-duotone ki-minus-square fs-2'>";
repeatHtml+="			<span class='path1'></span>";
repeatHtml+="			<span class='path2'></span>";
repeatHtml+="		</i>";
repeatHtml+="	</a>";
repeatHtml+="</td>";
repeatHtml+="</tr>";


$(function(){ 
	$("#searchEmployeeList").DataTable({
		displayLength: 10,
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

    // datepicker 초기화 함수
    function initializeDatepicker() {
        $('.datepicker').datepicker({
        	title: "발령일자 선택",	//캘린더 상단에 보여주는 타이틀
        	format: 'yyyy-mm-dd',
            autoclose: true,
            orientation: 'bottom',
            daysOfWeekHighlighted : [0,6], //강조 되어야 하는 요일 설정
            todayHighlight : true,
            viewMode: "months", 
            language: 'kr' // 한국어 설정
        });

    }
    
	// 초기화
	initializeDatepicker();
	
	
	// 추가 버튼 클릭 이벤트
	$('#add').click(function () {
		$('#repeater').append(repeatHtml);
		// 새로운 datepicker 초기화
		initializeDatepicker();
	});

	// 삭제 버튼 클릭 이벤트
	$('#repeater').on('click', '.remove', function () {
		$(this).closest('.item').remove();
	});
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

</script>
<%@include file="/WEB-INF/views/menu/hrSideMenu.jsp" %>		
</body>
</html>