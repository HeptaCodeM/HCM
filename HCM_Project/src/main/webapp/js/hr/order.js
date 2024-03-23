/* 휴가현황 검색(임직원) */
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
				}
			});

		},
		error:function(request, error){
			console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});

}


/* 휴가현황 검색(관리자) */
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
				}
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
	
	if(flag=="F"){
		if(!f.keyWord.value){
			swalAlert("검색어를 입력하여 주세요.","","","","keyWord");
			return;
		}
	}

	$("#searchEmployeeList").DataTable().destroy();

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
					{ data: 'empl_position_nm' }
				],
				displayLength: 5,
				lengthChange: false,
				info: false,
				language: {
					emptyTable: "조회된 정보가 없습니다."
				}
			});
		},
		error:function(request, error){
			console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});

}

function openEmpInfoSearch(){
	$("#searchEmployeeList").DataTable().destroy();
	empInfoSearch('');
	$('#empSearch').show();
}
function closeEmpInfoSearch(){
	$('#empSearch').hide();
}