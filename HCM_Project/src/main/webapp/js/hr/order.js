/* 발령현황 검색(임직원) */
function orderSearchList(){
	var f = $("#searchOrderDate");
	/*
	if($("#sdate_input").val() && $("#edate_input").val()){
		if($("#sdate_input").val() > $("#edate_input").val()){
			swalAlert("기간 종료일보다 시작일이 더 클 수 없습니다.","","","","sdate_input");
			$("#sdate_input").val('');
			$("#edate_input").val('');
			return;
		}
	}
	*/
	$("#searchOrderList").DataTable().destroy();
	
	$.ajax({
		url:"/hr/order/orderSearchList.do",
		type:"POST",
		data : f.serialize(),
		success:function(data){
			/*
			console.log(data)
			console.log(data.orderLists);
			*/

	 		$("#searchOrderList").dataTable({
	 			data: data,
				columns: [
					{ data: 'emor_id' },
					{ data: 'emod_order_dt' },
					{ data: 'emod_type_nm' },
					{ data: 'emod_prev_dept_nm' },
					{ data: 'emod_order_dept_nm' },
					{ data: 'emod_prev_rank_nm' },
					{ data: 'emod_order_rank_nm' },
					{ data: 'emod_prev_position_nm' },
					{ data: 'emod_order_position_nm' }
				],
				displayLength: 10,
				lengthChange: false,
				info: false,
				language: {
					emptyTable: "조회된 정보가 없습니다."
				},
				order : [[0,"desc"]]
			});

		},
		error:function(request, error){
			console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});

}


/* 발령현황 검색(관리자) */
function orderSearchAdminList(){
	var f = $("#searchOrderAdminForm");
	
	if($("#sdate_input").val() && $("#edate_input").val()){
		if($("#sdate_input").val() > $("#edate_input").val()){
			swalAlert("기간 종료일보다 시작일이 더 클 수 없습니다.","","","","sdate_input");
			$("#sdate_input").val('');
			$("#edate_input").val('');
			return;
		}
	}
	//console.log((($("#searchType").val() && $("#keyWord").val()) || (!$("#searchType").val() && !$("#keyWord").val())));
	if(!(($("#searchType").val() && $("#keyWord").val()) || (!$("#searchType").val() && !$("#keyWord").val()))){
		swalAlert("검색조건 선택 또는 검색어를 입력하세요.","","","","");
		return;
	}
	
	$("#searchOrderAdminList").DataTable().destroy();

	$.ajax({
		url:"/hr/order/orderSearchAdminList.do",
		type:"POST",
		data : f.serialize(),
		success:function(data){
			/*
			console.log(data)
			console.log(data.holidayLists);

			console.log(data.holidayTotalMap.TOTAL_HOLIDAY);
			console.log(data.holidayTotalMap.USE_HOLIDAY);
			console.log(data.holidayTotalMap.REST_HOLIDAY);
			*/
	 		$("#searchOrderAdminList").dataTable({
	 			data: data,
				columns: [
					{ data: 'emor_id' },
					{ data: 'emod_order_dt' },
					{ data: 'empl_id' },
					{ data: 'empl_name' },
					{ data: 'emod_type_nm' },
					{ data: 'emod_prev_dept_nm' },
					{ data: 'emod_order_dept_nm' },
					{ data: 'emod_prev_rank_nm' },
					{ data: 'emod_order_rank_nm' },
					{ data: 'emod_prev_position_nm' },
					{ data: 'emod_order_position_nm' },
					{ data: 'emor_status' }
				],
			    columnDefs: [
			        {
			            targets: [11], // emor_status 열
			            render: function(data, type, row) {
			                if (data === 'Y') {
			                    return '<span style="color: #1B84FF;">발령확정</span>';
			                } else {
			                    return '<span style="color: #B2A1B7;">발령전</span>';
			                }
			            }
			        }
			    ],
				displayLength: 10,
				lengthChange: false,
				info: false,
				language: {
					emptyTable: "조회된 정보가 없습니다."
				},
				order : [[0,"desc"]]
			});

		},
		error:function(request, error){
			console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
}


/* 임직원 검색 */
function empInfoSearch(flag){
	var f = $("#searchEmpInfo");
	
	/*
	if(flag=="F"){
		if(!f.keyWord.value){
			swalAlert("검색어를 입력하여 주세요.","","","","keyWord");
			return;
		}
	}
	*/
	
	console.log("empInfoSearch()");
	
    // DataTable 초기화 코드 추가
    $('#searchEmployeeList').DataTable().destroy();

	$.ajax({
		url:"/hr/employee/getUserInfoSearch.do",
		type:"POST",
		data : f.serialize(),
		success:function(data){
			console.log(data);

	 		$("#searchEmployeeList").dataTable({
	 			data: data,
				columns: [
					{ data: 'empl_id' },
					{ data: 'empl_name' },
					{ data: 'empl_dept_nm' },
					{ data: 'empl_rank_nm' },
					{ data: 'empl_position_nm' },
					{ data: 'empl_dept_cd' },
					{ data: 'empl_rank_cd' },
					{ data: 'empl_position_cd' }
				],
				columnDefs: [
	                {
	                    "targets": [5,6,7],
	                    "visible": false
	                }
				],
				displayLength: 5,
				lengthChange: false,
				info: false,
				language: {
					emptyTable: "조회된 정보가 없습니다."
				},
				order : [[1,"desc"]]
			});
		},
		error:function(request, error){
			console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});

}

function openEmpInfoSearch(){
	console.log("openEmpInfoSearch()");
	empInfoSearch('');
	$('#empSearch').show();
	$(".overlay").show();
}
function closeEmpInfoSearch(){
	$('#empSearch').hide();
	$(".overlay").hide();	
}

/* 발령정보 등록 */
function registOrderAdmin(){
	var rows = document.getElementsByClassName('item');

    for (var i = 0; i < rows.length; i++) {
		var emod_order_dt = rows[i].querySelector('input[name="emod_order_dt"]').value.trim();
		var empl_id = rows[i].querySelector('input[name="empl_id"]').value.trim();
		var empl_name = rows[i].querySelector('input[name="empl_name"]').value.trim();
		var emod_type = rows[i].querySelector('select[name="emod_type"]').value.trim();

		var emod_prev_dept = rows[i].querySelector('input[name="emod_prev_dept"]').value.trim();
		var emod_prev_dept_nm = rows[i].querySelector('input[name="emod_prev_dept_nm"]').value.trim();
		var emod_order_dept = rows[i].querySelector('select[name="emod_order_dept"]').value.trim();

		var emod_prev_rank = rows[i].querySelector('input[name="emod_prev_rank"]').value.trim();
		var emod_prev_rank_nm = rows[i].querySelector('input[name="emod_prev_rank_nm"]').value.trim();
		var emod_order_rank = rows[i].querySelector('select[name="emod_order_rank"]').value.trim();

		var emod_prev_position = rows[i].querySelector('input[name="emod_prev_position"]').value.trim();
		var emod_prev_position_nm = rows[i].querySelector('input[name="emod_prev_position_nm"]').value.trim();
		var emod_order_position = rows[i].querySelector('select[name="emod_order_position"]').value.trim();
		
		console.log(i+"번째 발령정보", emod_order_dt, empl_id, empl_name, emod_type);
		console.log(i+"번째 인사기본정보", emod_prev_dept, emod_prev_dept_nm, emod_prev_rank, emod_prev_rank_nm, emod_prev_position, emod_prev_position_nm);
		console.log(i+"번째 발령정보", emod_order_dept, emod_order_rank, emod_order_position);
		
		// 0번째 index 필수처리		
		var firstFlag = true;
		if(i===0){
			if(emod_order_dt && empl_id && empl_name && emod_type && emod_prev_dept && emod_prev_dept_nm && emod_prev_rank && emod_prev_rank_nm){
		        if(emod_type==='OR000002'){
			        if (emod_order_dept === '') {
			        	firstFlag = false;
			        }
		        }else if(emod_type==='OR000003'){
			        if (emod_order_rank === '') {
			        	firstFlag = false;
			        }
		        }else if(emod_type==='OR000004'){
			        if (emod_order_position === '') {
			        	firstFlag = false;
			        }
				}
			}else{
				firstFlag = false;
			}
			
			if(!firstFlag){
				swalAlert("첫번째 줄은 발령정보 값이 필수로 등록되어야 합니다.","","","","");
				return;
			}
		}else{
			if(!emod_order_dt || !empl_id || !empl_name || !emod_type || !emod_prev_dept || !emod_prev_dept_nm || !emod_prev_rank || !emod_prev_rank_nm){
		        
		        if (emod_order_dt === '') {
					swalAlert((i+1)+"번째 줄 발령일자가 없습니다.","","","","");
					return;
		        }
		        if (empl_id === '') {
					swalAlert((i+1)+"번째 줄 사번이 없습니다.","","","","");
					return;
		        }
		        if (empl_name === '') {
					swalAlert((i+1)+"번째 줄 성명이 없습니다.","","","","");
					return;
		        }
		        if (emod_type === '') {
					swalAlert((i+1)+"번째 줄 발령구분이 없습니다.","","","","");
					return;
		        }
		        if (emod_prev_dept === '') {
					swalAlert((i+1)+"번째 줄 이전 부서코드가 없습니다.","","","","");
					return;
		        }
		        if (emod_prev_dept_nm === '') {
					swalAlert((i+1)+"번째 줄 이전 부서명이 없습니다.","","","","");
					return;
		        }
		        if (emod_prev_rank === '') {
					swalAlert((i+1)+"번째 줄 이전 직위코드가 없습니다.","","","","");
					return;
		        }
		        if (emod_prev_dept_nm === '') {
					swalAlert((i+1)+"번째 줄 이전 직위명이 없습니다.","","","","");
					return;
		        }
			}else{
		        if(emod_type==='OR000002'){
			        if (emod_order_dept === '') {
						swalAlert((i+1)+"번째 줄 발령 부서정보가 없습니다.","","","","");
						return;
			        }
		        }else if(emod_type==='OR000003'){
			        if (emod_order_rank === '') {
						swalAlert((i+1)+"번째 줄 발령 직위정보가 없습니다.","","","","");
						return;
			        }
		        }else if(emod_type==='OR000004'){
			        if (emod_order_position === '') {
						swalAlert((i+1)+"번째 줄 발령 직책정보가 없습니다.","","","","");
						return;
			        }
				}
				
			}
		}
		
    }
    
    $("#orderRows").val(rows.length);

	$("#registOrderForm").submit();
}

var del_emor_id="";
function delelteOrderAdmin(emor_id){
	del_emor_id = emor_id;
	
	sweetAlertConfirm("현재 발령정보를 모두 삭제 하시겠습니까?", delelteOrderAdminOk,"");
}
function delelteOrderAdminOk(){
	console.log("del_emor_id : ", del_emor_id);
	if(del_emor_id != ""){
		location.href = "/hr/order/deleteOrderAdminOk.do?emor_id="+del_emor_id;
	}else{
		swalAlert("발령 삭제정보가 없습니다.","","","","");
	}
}

var confirm_emor_id="";
function confirmOrderAdmin(emor_id){
	var f = document.modifyOrderForm;
	f.reset();
	confirm_emor_id = emor_id;
	
	var rows = document.getElementsByClassName('item');

	// 현재 날짜 객체 생성
	var today = new Date();
	// 년, 월, 일 추출
	var year = today.getFullYear();
	var month = String(today.getMonth() + 1).padStart(2, '0'); // 월은 0부터 시작하므로 +1을 해줘야 함
	var day = String(today.getDate()).padStart(2, '0');
	// yyyy-mm-dd 형식으로 조합
	var currentDate = year + '-' + month + '-' + day;

    for (var i = 0; i < rows.length; i++) {
		var emod_order_dt = rows[i].querySelector('input[name="emod_order_dt"]').value.trim();
		if(emod_order_dt=="" || emod_order_dt <= currentDate){
			swalAlert((i+1)+"번째 줄 발령일자는 금일 이후의 일자여야 합니다.","","","","");
			return;
		}
	}
	
	sweetAlertConfirm("현재 발령정보를 확정 하시겠습니까?", confirmOrderAdminOk,"");
}
function confirmOrderAdminOk(){
	console.log("confirm_emor_id : ", confirm_emor_id);

	if(confirm_emor_id != ""){
		location.href = "/hr/order/confirmOrderAdminOk.do?emor_id="+confirm_emor_id;
	}else{
		swalAlert("발령 확정정보가 없습니다.","","","","");
	}
}

// 확정 시 모든 input, select disabled 처리
function disableFormInputs(formId) {
    // 폼 내의 모든 input 요소 선택
    var inputs = document.getElementById(formId).getElementsByTagName('input');
    var selects = document.getElementById(formId).getElementsByTagName('select');
    
    // 모든 input 요소에 대해 반복하여 disabled 속성 설정
    for (var i = 0; i < inputs.length; i++) {
        inputs[i].disabled = true;
    }
    // 모든 select 요소에 대해 반복하여 disabled 속성 설정
    for (var i = 0; i < selects.length; i++) {
    	selects[i].disabled = true;
    }
}


/* 발령정보 등록 */
function modifyOrderAdmin(){
	var rows = document.getElementsByClassName('item');

    for (var i = 0; i < rows.length; i++) {
		var emod_seq = rows[i].querySelector('input[name="emod_seq"]').value.trim();
		var emod_order_dt = rows[i].querySelector('input[name="emod_order_dt"]').value.trim();
		var empl_id = rows[i].querySelector('input[name="empl_id"]').value.trim();
		var empl_name = rows[i].querySelector('input[name="empl_name"]').value.trim();
		var emod_type = rows[i].querySelector('select[name="emod_type"]').value.trim();

		var emod_prev_dept = rows[i].querySelector('input[name="emod_prev_dept"]').value.trim();
		var emod_prev_dept_nm = rows[i].querySelector('input[name="emod_prev_dept_nm"]').value.trim();
		var emod_order_dept = rows[i].querySelector('select[name="emod_order_dept"]').value.trim();

		var emod_prev_rank = rows[i].querySelector('input[name="emod_prev_rank"]').value.trim();
		var emod_prev_rank_nm = rows[i].querySelector('input[name="emod_prev_rank_nm"]').value.trim();
		var emod_order_rank = rows[i].querySelector('select[name="emod_order_rank"]').value.trim();

		var emod_prev_position = rows[i].querySelector('input[name="emod_prev_position"]').value.trim();
		var emod_prev_position_nm = rows[i].querySelector('input[name="emod_prev_position_nm"]').value.trim();
		var emod_order_position = rows[i].querySelector('select[name="emod_order_position"]').value.trim();
		
		console.log(i+"번째 발령정보", emod_seq, emod_order_dt, empl_id, empl_name, emod_type);
		console.log(i+"번째 인사기본정보", emod_prev_dept, emod_prev_dept_nm, emod_prev_rank, emod_prev_rank_nm, emod_prev_position, emod_prev_position_nm);
		console.log(i+"번째 발령정보", emod_order_dept, emod_order_rank, emod_order_position);
		
		// 0번째 index 필수처리		
		var firstFlag = true;
		if(i===0){
			if(emod_seq && emod_order_dt && empl_id && empl_name && emod_type && emod_prev_dept && emod_prev_dept_nm && emod_prev_rank && emod_prev_rank_nm){
		        if(emod_type==='OR000002'){
			        if (emod_order_dept === '') {
			        	firstFlag = false;
			        }
		        }else if(emod_type==='OR000003'){
			        if (emod_order_rank === '') {
			        	firstFlag = false;
			        }
		        }else if(emod_type==='OR000004'){
			        if (emod_order_position === '') {
			        	firstFlag = false;
			        }
				}
			}else{
				firstFlag = false;
			}
			
			if(!firstFlag){
				swalAlert("첫번째 줄은 발령정보 값이 필수로 등록되어야 합니다.","","","","");
				return;
			}
		}else{
			if(!emod_seq || !emod_order_dt || !empl_id || !empl_name || !emod_type || !emod_prev_dept || !emod_prev_dept_nm || !emod_prev_rank || !emod_prev_rank_nm){
		        if (emod_seq === '') {
					swalAlert((i+1)+"번째 줄 시퀀스 번호가 없습니다.","","","","");
					return;
		        }
		        if (emod_order_dt === '') {
					swalAlert((i+1)+"번째 줄 발령일자가 없습니다.","","","","");
					return;
		        }
		        if (empl_id === '') {
					swalAlert((i+1)+"번째 줄 사번이 없습니다.","","","","");
					return;
		        }
		        if (empl_name === '') {
					swalAlert((i+1)+"번째 줄 성명이 없습니다.","","","","");
					return;
		        }
		        if (emod_type === '') {
					swalAlert((i+1)+"번째 줄 발령구분이 없습니다.","","","","");
					return;
		        }
		        if (emod_prev_dept === '') {
					swalAlert((i+1)+"번째 줄 이전 부서코드가 없습니다.","","","","");
					return;
		        }
		        if (emod_prev_dept_nm === '') {
					swalAlert((i+1)+"번째 줄 이전 부서명이 없습니다.","","","","");
					return;
		        }
		        if (emod_prev_rank === '') {
					swalAlert((i+1)+"번째 줄 이전 직위코드가 없습니다.","","","","");
					return;
		        }
		        if (emod_prev_dept_nm === '') {
					swalAlert((i+1)+"번째 줄 이전 직위명이 없습니다.","","","","");
					return;
		        }
			}else{
		        if(emod_type==='OR000002'){
			        if (emod_order_dept === '') {
						swalAlert((i+1)+"번째 줄 발령 부서정보가 없습니다.","","","","");
						return;
			        }
		        }else if(emod_type==='OR000003'){
			        if (emod_order_rank === '') {
						swalAlert((i+1)+"번째 줄 발령 직위정보가 없습니다.","","","","");
						return;
			        }
		        }else if(emod_type==='OR000004'){
			        if (emod_order_position === '') {
						swalAlert((i+1)+"번째 줄 발령 직책정보가 없습니다.","","","","");
						return;
			        }
				}
				
			}
		}
		
    }
    
    $("#orderRows").val(rows.length);

	$("#modifyOrderForm").submit();
}

