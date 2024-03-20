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
		swalAlert("올바르지 않은 비밀번호 확인 입니다.","","","","");
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
function empInfoSearch(){
	var f = document.searchEmpInfo;
	
	/*if(!f.keyWord.value){
		swalAlert("검색어를 입력하여 주세요.","","","","keyWord");
		return;
	}*/

	const formData = new FormData(f);
    const payload = new URLSearchParams(formData);
	console.log("payload : ", payload);
	
	fetch('/hr/employee/getUserInfoSearch.do', {
		method: 'post',
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        },
		body: payload
	})
	.then(resp => resp.json())
	.then(data => {
		console.log("data", data, data.length);
		listHtml = "";
		for(let i=0;i<data.length;i++){
			listHtml += "<tr>";
			listHtml += "<td>"+data[i].empl_id+"</td>";
			listHtml += "<td>"+data[i].empl_name+"</td>";
			listHtml += "<td>"+data[i].empl_dept_nm+"</td>";
			listHtml += "<td>"+data[i].empl_rank_nm+"</td>";
			listHtml += "<td>"+data[i].empl_position_nm+"</td>";
			listHtml += "</tr>";
		}
		document.getElementById("empSearchList").innerHTML = listHtml;
	})
}
