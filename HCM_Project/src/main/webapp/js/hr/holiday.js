/* 휴가현황 검색 */
function holidaySearchList(){
	var f = $("#searchholidayDate");
	if(!$("#sdate_input").val() && !$("#edate_input").val()){
		if($("#sdate_input").val() > $("#edate_input").val()){
			swalAlert("기간 종료일보다 시작일이 더 클 수 없습니다.","","","","sdate_input");
			$("#sdate_input").val('');
			$("#edate_input").val('');
			return;
		}
	}

	$("#searchHolidayList").DataTable().destroy();

	$.ajax({
		url:"/hr/holiday/holidaySearchList.do",
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
			$("#totHoliday").html(data.holidayTotalMap.TOTAL_HOLIDAY);
			$("#useHoliday").html(data.holidayTotalMap.USE_HOLIDAY);
			$("#restHoliday").html(data.holidayTotalMap.REST_HOLIDAY);
			
	 		$("#searchHolidayList").dataTable({
	 			data: data.holidayLists,
				columns: [
					{ data: 'sidt_temp_name' },
					{ data: 'sidb_doc_date' },
					{ data: 'holiday' },
					{ data: 'sidb_doc_apprdt' }
				],
				displayLength: 5,
				lengthChange: false,
				info: false
			});

		},
		error:function(request, error){
			console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});

}