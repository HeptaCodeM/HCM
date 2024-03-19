/* 인증번호 발송 */
function initPwdAuthNumSend(){
	var f = document.initPwdForm;
	
	if(!f.empl_id.value){
		swalAlert("사번을 입력하세요.","","","","empl_id");
		return;
	}
	if(!f.empl_name.value){
		swalAlert("성명을 입력하세요.","","","","empl_id");
		return;
	}
	if(!f.empl_email.value){
		swalAlert("이메일을 입력하세요.","","","","empl_id");
		return;
	}

	const formData = new FormData(f);
    const payload = new URLSearchParams(formData);
	console.log("payload : ", payload);

	fetch('/login/initPwdAuthNumSend.do', {
		method: 'post',
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        },
		body: payload
	})
	.then(resp=>{
		if (!resp.ok){
		      throw new Error(resp)
		}
		return resp.text()
	})
	.then(data => {
		console.log(data);
		if(data == "true"){
			swalAlert("등록된 이메일로 인증번호가 발송 되었습니다.","","","","");
			document.getElementById("send-area").style.setProperty("display", "none", "important");
			document.getElementById("auth-area").style.setProperty("display", "flex");
			return;
		}else{
			swalAlert("인증번호 발송에 실패하였습니다.","","","","");
			return;
		}
	})
	.catch((error)=>{
		console.log("에러", error);
	})

}


function initPwdAuthNumCheck(){
	var f = document.initPwdForm;

	if(f.authnum.value.length < 4){
		swalAlert("올바른 인증번호를 입력하세요.","","","","authnum");
		return;
	}

	const formData = new FormData(f);
    const payload = new URLSearchParams(formData);
	console.log("payload : ", payload);

	fetch('/login/initPwdAuthNumCheck.do', {
		method: 'post',
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        },
		body: payload
	})
	.then(resp=>{
		if (!resp.ok){
		      throw new Error(resp)
		}
		return resp.text()
	})
	.then(data => {
		console.log(data);
		if(data == "true"){
			swalAlert("비밀번호가 초기화 되었습니다.<br>초기화 비밀번호는 등록된 이메일로 발송 되었습니다.","/login/login.do","","","");
			return;
		}else{
			swalAlert("인증번호 정보가 다릅니다.","","","","");
			return;
		}
	})
	.catch((error)=>{
		console.log("에러", error);
	})
	
}
