var startDate = "";
var endDate = "";

$(document).ready(function(){
	$('#emplListTable').DataTable({
        lengthChange: false,
        info: false,
        order: [ [ 0, "desc"] ] 
	});
	
	$("#emplListTable tbody").on('click', 'tr', function () {
		console.log("작동");
		var row = $("#emplListTable").DataTable().row($(this)).data();
		console.log(row.empl_id);
		if(row.empl_id == null){
			return false;
		}else{
			location.href="/hr/employee/modifyAdmin.do?empl_id="+row.empl_id;
		} 
	});

});

	
$(function() {

  $('#empDatepicker').daterangepicker({
      autoUpdateInput: false,
      locale: {
          cancelLabel: '초기화',
          applyLabel: '설정',
          language: {
					emptyTable: "조회된 정보가 없습니다."
				}
      }
  });
	
  $('#empDatepicker').on('apply.daterangepicker', function(ev, picker) {
     	$(this).val(picker.startDate.format('YYYY년MM월DD일') + ' ~ ' + picker.endDate.format('YYYY년MM월DD일'));
     	startDate = picker.startDate.format('YYYY-MM-DD');
     	endDate = picker.endDate.format('YYYY-MM-DD');
  });
	
  $('#empDatepicker').on('cancel.daterangepicker', function(ev, picker) {
      	$(this).val('');
  });
	
});
	
var empSearchBtn = document.getElementById("empSearchBtn");
empSearchBtn.addEventListener('click',function(){
	console.log("작동");
	
	var empShCtgVal = document.getElementById('empShCtg').value;
	console.log(empShCtgVal);
	
	var empStaCtg = document.getElementById('empStaCtg').value;
	console.log(empStaCtg);
	
	var empSearchValue = document.getElementById('empSearchValue').value;
	console.log(empSearchValue);

   
	var dtChkVal = document.getElementsByName('dtChkVal');
	let dtArr = new Array();	
		for(let i = 0; i < dtChkVal.length; i++){
			if(dtChkVal[i].checked){
				dtArr.push(dtChkVal[i].value);
			}
		}
	console.log(dtArr);
		
	var rkChkVal = document.getElementsByName('rkChkVal');
	let rkArr = new Array();
		for(let j = 0; j < rkChkVal.length; j++){
			if(rkChkVal[j].checked){
				rkArr.push(rkChkVal[j].value);
			}
		}
	console.log(rkArr);
		
	var pnChkVal = document.getElementsByName('pnChkVal');
	let pnArr = new Array();
	
		for(let k = 0; k < pnChkVal.length; k++){
			if(pnChkVal[k].checked){
				pnArr.push(pnChkVal[k].value);
			}
		}
	console.log(pnArr);	
	
	
	var valueChk = new URLSearchParams();
	valueChk.append('empShCtgVal', empShCtgVal);
	valueChk.append('empStaCtg', empStaCtg);
	valueChk.append('startDate', startDate);
	valueChk.append('endDate', endDate);
	valueChk.append('empSearchValue', empSearchValue);
	valueChk.append('dtArr', dtArr);
	valueChk.append('rkArr', rkArr);
	valueChk.append('pnArr', pnArr);
	
	$("#emplListTable").DataTable().destroy(); 
	$.ajax({
	    type: "POST",
	    url: "/hr/employee/empSearching.do?"+valueChk,	
	    success: function (data) {
	        console.log(data);
	        $('#emplListTable').DataTable({
	        	data:data,
	        	lengthChange: false,
		        info: false,
	        	columns: [ { data: "empl_id" }, 
				       { data: "empl_name" }, 
				       { data: "coco_name_dnm" }, 
				       { data: "coco_name_rnm" }, 
				       { data: "coco_name_pnm" }, 
				       { data: "empl_delflag",
				        render: function(data, type, row) {
                           return data === "Y" ? "퇴직자" : "재직자";
				       }
				       }],
				       language: {
					emptyTable: "조회된 정보가 없습니다."
				},
				order : [[0,"desc"]]
	        });
	        empSearchValue = "";
	        startDate = "";
	        endDate = "";
	    },
	    error:function(err){
			console.log(err);
		}
	});
	
});