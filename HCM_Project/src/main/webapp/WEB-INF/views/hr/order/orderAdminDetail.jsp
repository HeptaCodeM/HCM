<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<%@include file="/WEB-INF/views/menu/headerInfo.jsp" %>
	<title>HCM GroupWare</title>
	<style type="text/css">
	.table.regiform th {  vertical-align:middle; text-align:center !important; background-color:#F9F9F9; font-weight:600; }
	.table.regiform th:nth-child(1) { width:120px !important; }
	.table.regiform th:nth-child(2) { width:120px; }
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
								<h3 class="card-title text-gray-800 fw-bold">인사관리 > 인사발령관리 > 발령수정</h3>
							</div>
							<div class="separator separator-dashed my-3"></div>
							<form name="modifyOrderForm" id="modifyOrderForm" method="post" action="/hr/order/modifyOrderAdminOk.do" >
								<input type="hidden" name="orderRows" id="orderRows" value="0">
								<input type="hidden" name="emor_id" id="emor_id" value="${emor_id}">
								<c:set value="${detailLists[0].emor_status}" var="emor_status" />
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
										<c:forEach items="${detailLists}" var="detail" varStatus="vs">
										<tr class="item" id="item${vs.index+1}" data-idnum="${vs.index+1}">
											<td>
						                        <input type="text" class="form-control form-control-solid datepicker" name="emod_order_dt" value="${detail.emod_order_dt}" readonly>
												<input type="hidden" name="emod_seq" value="${detail.emod_seq}">
											</td>
											<td>
												<span class="form-control form-control-solid">${detail.empl_id}</span>
												<input type="hidden" name="empl_id" required="required" value="${detail.empl_id}">
											</td>
											<td>
												<input type="text" class="form-control form-control-solid" name="empl_name" maxlength="20" readonly value="${detail.empl_name}">
											</td>
											<td>
												<select NAME="emod_type" class="form-select form-select-solid searchEmpSelect">
													<option value="">발령구분</option>
													<c:forEach items="${orderList}" var="order">
													<option value="${order.coco_cd}" <c:if test="${detail.emod_type eq order.coco_cd}">selected</c:if>>${order.coco_name}</option>
													</c:forEach>
												</select>
											</td>
											<td>
												<input type="text" class="form-control form-control-solid" name="emod_prev_dept_nm" maxlength="20" readonly value="${detail.emod_prev_dept_nm}">
												<input type="hidden" name="emod_prev_dept" value="${detail.emod_prev_dept}">
											</td>
											<td>
												<select NAME="emod_order_dept" class="form-select form-select-solid searchEmpSelect" <c:if test="${detail.emod_order_dept eq null or detail.emod_order_dept eq ''}">style="display:none;"</c:if>>
													<option value="">부서선택</option>
													<c:forEach items="${deptList}" var="dept">
													<option value="${dept.coco_cd}" <c:if test="${detail.emod_order_dept eq dept.coco_cd}">selected</c:if>>${dept.coco_name}</option>
													</c:forEach>
												</select>
											</td>
											<td>
												<input type="text" class="form-control form-control-solid" name="emod_prev_rank_nm" maxlength="20" readonly value="${detail.emod_prev_rank_nm}">
												<input type="hidden" name="emod_prev_rank" value="${detail.emod_prev_rank}">
											</td>
											<td>
												<select NAME="emod_order_rank" class="form-select form-select-solid searchEmpSelect" <c:if test="${detail.emod_order_rank eq null or detail.emod_order_rank eq ''}">style="display:none;"</c:if>>
													<option value="">직위선택</option>
													<c:forEach items="${rankList}" var="rank">
													<option value="${rank.coco_cd}" <c:if test="${detail.emod_order_rank eq rank.coco_cd}">selected</c:if>>${rank.coco_name}</option>
													</c:forEach>
												</select>
											</td>
											<td>
												<input type="text" class="form-control form-control-solid" name="emod_prev_position_nm" maxlength="20" readonly value="${detail.emod_prev_position_nm}">
												<input type="hidden" name="emod_prev_position" value="${detail.emod_prev_position}">
											</td>
											<td>
												<select NAME="emod_order_position" class="form-select form-select-solid searchEmpSelect" <c:if test="${detail.emod_order_position eq null or detail.emod_order_position eq ''}">style="display:none;"</c:if>>
													<option value="">직책선택</option>
													<c:forEach items="${positionList}" var="position">
													<option value="${position.coco_cd}" <c:if test="${detail.emod_order_position eq position.coco_cd}">selected</c:if>>${position.coco_name}</option>
													</c:forEach>
												</select>
											</td>
											<td style="text-align: center; vertical-align: middle;">
												<c:if test="${emor_status ne 'Y'}">
													<c:if test="${vs.index eq 0}">
													-
													</c:if>
													<c:if test="${vs.index ne 0}">
												<a href='javascript:void(0)' data-emor_id="${emor_id}" data-emod_seq="${detail.emod_seq}" class='remove'>
													<i class='ki-duotone ki-minus-square fs-2'>
														<span class='path1'></span>
														<span class='path2'></span>
													</i>
												</a>
													</c:if>
												</c:if>
												<c:if test="${emor_status eq 'Y'}">
												-
												</c:if>
											</td>
										</tr>
										</c:forEach>
										</tbody>
									</table>
								</div>
								<c:if test="${emor_status ne 'Y'}">
							    <div class="form-group mb-5 me-4" style="text-align: right;">
							        <a href="javascript:;" id="add" class="btn btn-light-primary">
							            <i class="ki-duotone ki-plus fs-3"></i>
							            Add
							        </a>
							    </div>
							    </c:if>

								<div style="text-align: right;margin-bottom:20px;">
									<c:if test="${emor_status ne 'Y'}">
									<button type="button" class="btn btn-primary me-4" id="kt_button_1" onclick="modifyOrderAdmin()">
									    <span class="indicator-label">
									        수정
									    </span>
									    <span class="indicator-progress">
									        Please wait... <span class="spinner-border spinner-border-sm align-middle ms-2"></span>
									    </span>
									</button>
									<button type="button" class="btn btn-danger me-4" id="kt_button_1" onclick="delelteOrderAdmin('${emor_id}');">
									    <span class="indicator-label">
									        삭제
									    </span>
									</button>
									<button type="button" class="btn btn-info me-4" id="kt_button_1" onclick="confirmOrderAdmin('${emor_id}');">
									    <span class="indicator-label">
									        확정
									    </span>
									</button>
									</c:if>
									<button type="button" class="btn btn-success me-4" id="kt_button_1" onclick="location.href='/hr/order/orderAdminList.do';">
									    <span class="indicator-label">
									        리스트
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
		<div id="overlay" class="overlay"></div>
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
						<th>부서코드</th>
						<th>직위코드</th>
						<th>직책코드</th>
					</tr>
				</thead>
			</table>
			</div>
		</div>
		<!-- 사원검색 Layer 종료 -->
<script type="text/javascript">
var thisRow = "";
var idNum = ${fn:length(detailLists)};

// Add Row HandleBarJS
var repeatHtml = "";
repeatHtml+="<tr class='item' id='item_idNum' data-idnum='_idNum'>";
repeatHtml+="<td>";
repeatHtml+="	<input type='text' class='form-control form-control-solid datepicker' name='emod_order_dt'>";
repeatHtml+="	<input type='hidden' name='emod_seq' value='0'>";
repeatHtml+="</td>";
repeatHtml+="<td>";
repeatHtml+="	<input type='text' class='form-control form-control-solid' name='empl_id' maxlength='20' readonly required='required'>";
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
repeatHtml+="	<input type='text' class='form-control form-control-solid' name='emod_prev_dept_nm' maxlength='20' readonly>";
repeatHtml+="	<input type='hidden' name='emod_prev_dept'>";
repeatHtml+="</td>";
repeatHtml+="<td>";
repeatHtml+="	<select NAME='emod_order_dept' class='form-select form-select-solid searchEmpSelect' style='display:none;'>";
repeatHtml+="		<option value=''>부서선택</option>";
<c:forEach items="${deptList}" var="dept">
repeatHtml+="		<option value='${dept.coco_cd}'>${dept.coco_name}</option>";
</c:forEach>
repeatHtml+="	</select>";
repeatHtml+="</td>";
repeatHtml+="<td>";
repeatHtml+="	<input type='text' class='form-control form-control-solid' name='emod_prev_rank_nm' maxlength='20' readonly>";
repeatHtml+="	<input type='hidden' name='emod_prev_rank'>";
repeatHtml+="</td>";
repeatHtml+="<td>";
repeatHtml+="	<select NAME='emod_order_rank' class='form-select form-select-solid searchEmpSelect' style='display:none;'>";
repeatHtml+="		<option value=''>직위선택</option>";
<c:forEach items="${rankList}" var="rank">
repeatHtml+="		<option value='${rank.coco_cd}'>${rank.coco_name}</option>";
</c:forEach>
repeatHtml+="	</select>";
repeatHtml+="</td>";
repeatHtml+="<td>";
repeatHtml+="	<input type='text' class='form-control form-control-solid' name='emod_prev_position_nm' maxlength='20' readonly>";
repeatHtml+="	<input type='hidden' name='emod_prev_position'>";
repeatHtml+="</td>";
repeatHtml+="<td>";
repeatHtml+="	<select NAME='emod_order_position' id='emod_order_position1' class='form-select form-select-solid searchEmpSelect' style='display:none;'>";
repeatHtml+="		<option value=''>직책선택</option>";
<c:forEach items="${positionList}" var="position">
repeatHtml+="		<option value='${position.coco_cd}'>${position.coco_name}</option>";
</c:forEach>
repeatHtml+="	</select>";
repeatHtml+="</td>";
repeatHtml+="<td style='text-align: center; vertical-align: middle;'>";
repeatHtml+="	<a href='javascript:void(0)' data-emor_id='' data-emod_seq='' class='remove'>";
repeatHtml+="		<i class='ki-duotone ki-minus-square fs-2'>";
repeatHtml+="			<span class='path1'></span>";
repeatHtml+="			<span class='path2'></span>";
repeatHtml+="		</i>";
repeatHtml+="	</a>";
repeatHtml+="</td>";
repeatHtml+="</tr>";


$(function(){ 
	//Employee Info Search
	$(document).on('click', 'input[name="empl_id"]', function() {
		console.log('Input 클릭됨');
	    thisRow = $(this).parent().parent().data("idnum");
	    console.log($(this).parent().parent().data("idnum"));
	    openEmpInfoSearch();
	});

	//Employee Info Apply
	$(document).on('click', '#searchEmployeeList tbody tr', function() {
		var row = $("#searchEmployeeList").DataTable().row($(this)).data();
		console.log("row: ", row);
		console.log("row.empl_id : ", row.empl_id);
		console.log("this : ", $("#item"+thisRow));
		$("#item"+thisRow).find('input[name="empl_id"]').val(row.empl_id);
		$("#item"+thisRow).find('input[name="empl_name"]').val(row.empl_name);
		$("#item"+thisRow).find('input[name="emod_prev_dept"]').val(row.empl_dept_cd);
		$("#item"+thisRow).find('input[name="emod_prev_dept_nm"]').val(row.empl_dept_nm);
		$("#item"+thisRow).find('input[name="emod_prev_rank"]').val(row.empl_rank_cd);
		$("#item"+thisRow).find('input[name="emod_prev_rank_nm"]').val(row.empl_rank_nm);
		$("#item"+thisRow).find('input[name="emod_prev_position"]').val(row.empl_position_cd);
		$("#item"+thisRow).find('input[name="emod_prev_position_nm"]').val(row.empl_position_nm);

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
	
	//추가 버튼 클릭 이벤트
	$('#add').click(function () {
		if(idNum == 0){
			idNum = 1;
		}else{
			idNum = idNum + 1;
		}
		var replaceRepeatHtml = repeatHtml.replace(/_idNum/g,idNum);
		$('#repeater').append(replaceRepeatHtml);
		//console.log(idNum);
		//console.log(replaceRepeatHtml);
		// 새로운 datepicker 초기화
		initializeDatepicker();
	});
	
	// 삭제 버튼 클릭 이벤트
	$('#repeater').on('click', '.remove', function () {
		console.log("emor_id, emod_seq : ", $(this).data("emor_id"), $(this).data("emod_seq"));
		var emor_id = $(this).data("emor_id");
		var emod_seq = $(this).data("emod_seq");
		console.log("emor_id, emod_seq : ", emor_id, emod_seq);
		if(emor_id != '' && emod_seq != ''){
			if(!confirm("현재 저장된 정보를 삭제하시겠습니까?")){
				return;
			}
			//삭제처리
			fetch('/hr/order/deleteOrderAdminDetail.do', {
				method: 'post',
		        headers: {
		          'Content-Type': 'application/x-www-form-urlencoded'
		        },
				body: "emor_id="+emor_id+"&emod_seq="+emod_seq
			})
			.then(resp=>{
				if (!resp.ok){
				      throw new Error(resp)
				}
				return resp.text()
			})
			.then(result => {
				console.log("result : ", result);
				if(result == "false"){
					swalAlert("삭제에 실패 하였습니다.","","","","");
				}else{
					$(this).closest('.item').remove();
				}
			})
			.catch((error)=>{
				console.log("에러", error);
			})
		}else{
			$(this).closest('.item').remove();
		}
	});

	
	// 발령구분 선택 시 처리
	$(document).on('change', 'select[name="emod_type"]', function() {
		console.log("this : ", $(this).val());
		if($(this).val()=="OR000002"){
			$(this).closest('tr').find('select[name="emod_order_dept"]').show();
			$(this).closest('tr').find('select[name="emod_order_rank"]').hide();
			$(this).closest('tr').find('select[name="emod_order_position"]').hide();
		}else if($(this).val()=="OR000003"){
			$(this).closest('tr').find('select[name="emod_order_dept"]').hide();
			$(this).closest('tr').find('select[name="emod_order_rank"]').show();
			$(this).closest('tr').find('select[name="emod_order_position"]').hide();
		}else if($(this).val()=="OR000004"){
			$(this).closest('tr').find('select[name="emod_order_dept"]').hide();
			$(this).closest('tr').find('select[name="emod_order_rank"]').hide();
			$(this).closest('tr').find('select[name="emod_order_position"]').show();
		}else{
			$(this).closest('tr').find('select[name="emod_order_dept"]').hide();
			$(this).closest('tr').find('select[name="emod_order_rank"]').hide();
			$(this).closest('tr').find('select[name="emod_order_position"]').hide();
		}
		$(this).closest('tr').find('select[name="emod_order_dept"]').val('');
		$(this).closest('tr').find('select[name="emod_order_rank"]').val('');
		$(this).closest('tr').find('select[name="emod_order_position"]').val('');
	});

	// 확정이라면 모든 form내 input,select element disabled
	if("${emor_status}"=="Y"){
		disableFormInputs('modifyOrderForm');
	}
});

</script>
<%@include file="/WEB-INF/views/menu/hrSideMenu.jsp" %>		
</body>
</html>