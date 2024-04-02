/* 임직원등록 */
function registEmployee(){
	var f = document.registEmpForm;

	if(f.empl_name.value < 2){
		swalAlert("올바른 성명을 입력하세요.","","","","empl_name");
		return;
	}

	if(f.empl_birth.value < 10){
		swalAlert("올바른 생년월일을 입력하세요.","","","","empl_birth");
		return;
	}

	if(!checkDate(f.empl_birth.value)){
		swalAlert("날짜 형식에 맞지 않습니다.<br>ex)1900-01-01","","","","empl_birth");
		return;
	}

	if(!f.empl_email.value){
		swalAlert("올바른 이메일을 입력하세요.","","","","empl_email");
		return;
	}

	if(!checkEmail(f.empl_email.value)){
		swalAlert("이메일 형식에 맞지 않습니다.","","","","empl_email");
		return;
	}

	if(!f.empl_phone.value){
		swalAlert("올바른 휴대폰 번호를 입력하세요.","","","","empl_phone");
		return;
	}

	if(!checkPhone(f.empl_phone.value)){
		swalAlert("휴대폰번호 형식에 맞지 않습니다.","","","","empl_phone");
		return;
	}
	
	if(f.empl_tel.value.length>0){
		if(!only_Num(f.empl_tel.value)){
			swalAlert("내선번호는 정수로만 입력하세요.","","","","empl_tel");
			return;
		}
	}
	
	if(f.empl_joindate.value < 10){
		swalAlert("올바른 입사일자을 입력하세요.","","","","empl_birth");
		return;
	}

	if(!checkDate(f.empl_joindate.value)){
		swalAlert("날짜 형식에 맞지 않습니다.<br>ex)1900-01-01","","","","empl_joindate");
		return;
	}
	
	if(!f.empl_dept_cd.value){
		swalAlert("올바른 부서를 선택하세요.","","","","empl_dept_cd");
		return;
	}

	if(!f.empl_rank_cd.value){
		swalAlert("올바른 직위를 선택하세요.","","","","empl_rank_cd");
		return;
	}

	sweetAlertConfirm("현재 입력하신 정보로 등록 하시겠습니까?", registEmployeeOk, "");
	/*
	if(confirm("현재 입력하신 정보로 등록 하시겠습니까?")){
		f.submit();
	}
	*/
}
function registEmployeeOk(){
	var f = document.registEmpForm;
	f.submit();
}

/* 임직원수정 */
function modifyEmployee(){
	var f = document.modifyEmpForm;

	if(!f.empl_phone.value){
		swalAlert("올바른 휴대폰 번호를 입력하세요.","","","","empl_phone");
		return;
	}

	if(!checkPhone(f.empl_phone.value)){
		swalAlert("휴대폰번호 형식에 맞지 않습니다.","","","","empl_phone");
		return;
	}

	if(f.empl_tel.value.length>0){
		if(!only_Num(f.empl_tel.value)){
			swalAlert("내선번호는 정수로만 입력하세요.","","","","empl_tel");
			return;
		}
	}

	sweetAlertConfirm("현재 입력하신 정보로 수정 하시겠습니까?", modifyEmployeeSubmit, "");
	/*
	if(confirm("현재 입력하신 정보로 수정 하시겠습니까?")){
		f.submit();
	}
	*/
}
function modifyEmployeeSubmit(){
	var f = document.modifyEmpForm;
	f.submit();
}

/* 비밀번호 변경 */
function updatePwd(){
	var f = document.updatePwdForm;
	
	if(f.empl_pwd.value.length < 8 || f.empl_pwd.value.length > 12){
		swalAlert("비밀번호는 8~12자리 이어야 합니다.","","","","");
		return;
	}
	
	if(f.empl_new_pwd.value.length < 8 || f.empl_new_pwd.value.length > 12){
		swalAlert("변경하실 비밀번호는 8~12자리 이어야 합니다.","","","","");
		return;
	}

	if(!checkPw(f.empl_new_pwd.value)){
		swalAlert("규칙에 맞는 비밀번호를 입력하여 주세요.","","","","");
		return;
	}

	if(f.empl_new_pwd.value != f.empl_new_pwd2.value){
		swalAlert("변경하실 비밀번호와 변경 비밀번호 확인 정보가 다릅니다.","","","","");
		return;
	}

	sweetAlertConfirm("현재 입력하신 정보로 수정 하시겠습니까?", updatePwdOk, "");
	/*
	if(confirm("현재 입력하신 정보로 비밀번호를 변경하시겠습니까?")){
		f.submit();
	}
	*/

}
function updatePwdOk(){
	var f = document.updatePwdForm;
	f.submit();
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
	$(".overlay").show();
	
}
function closeEmpInfoSearch(){
	$('#empSearch').hide();
	$(".overlay").hide();
}

function registEmpAuth(){
	var f = document.registAuthForm;
	if(!f.empl_id.value || !f.empl_name.value || !f.empl_dept_nm.value || !f.empl_rank_nm.value){
		swalAlert("사원정보가 없습니다.<br>사원을 조회하여 주세요.","","","","");
		return;
	}
	if(!f.empl_auth.value){
		swalAlert("권한을 선택하여 주세요.","","","","empl_auth");
		return;
	}
	
	f.submit();
}

function modifyEmpAuth(){
	var f = document.modifyAuthForm;
	
	f.type.value = "U";
	
	if(!f.empl_id.value || !f.empl_name.value || !f.empl_dept_nm.value || !f.empl_rank_nm.value){
		swalAlert("사원정보가 없습니다.<br>사원을 조회하여 주세요.","","","","");
		return;
	}
	if(!f.empl_auth.value){
		swalAlert("권한을 선택하여 주세요.","","","","empl_auth");
		return;
	}

	if(f.empl_auth.value == f.empl_auth1.value){
		swalAlert("현재권한과 수정하려는 권한이 동일합니다.","","","","empl_auth");
		return;
	}
	
	sweetAlertConfirm("현재 권한정보를 수정 하시겠습니까?", modifyEmpAuthOk, "");
}
function modifyEmpAuthOk(){
	var f = document.modifyAuthForm;

	f.submit();
}

function deleteEmpAuth(){
	var f = document.modifyAuthForm;
	
	f.type.value = "D";
	
	if(!f.empl_id.value || !f.empl_name.value || !f.empl_dept_nm.value || !f.empl_rank_nm.value){
		swalAlert("사원정보가 없습니다.<br>사원을 조회하여 주세요.","","","","");
		return;
	}

	sweetAlertConfirm("현재 권한정보를 삭제 하시겠습니까?", deleteEmpAuthOk, "");
}
function deleteEmpAuthOk(){
	var f = document.modifyAuthForm;

	f.submit();
}

