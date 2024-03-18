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