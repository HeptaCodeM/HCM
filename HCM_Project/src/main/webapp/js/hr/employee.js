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


	if(confirm("현재 입력하신 정보로 등록 하시겠습니까?")){
		f.submit();
	}

}


/* 임직원등록 */
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

	if(confirm("현재 입력하신 정보로 수정 하시겠습니까?")){
		f.submit();
	}

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

	if(confirm("현재 입력하신 정보로 비밀번호를 변경하시겠습니까?")){
		f.submit();
	}

}