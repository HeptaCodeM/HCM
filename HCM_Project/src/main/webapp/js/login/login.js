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
	//console.log("payload : ", payload);

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
		//console.log(data);
		if(data == "true"){
			swalAlert("등록된 이메일로 인증번호가 발송 되었습니다.","","","","");
			document.getElementById("send-area").style.setProperty("display", "none", "important");
			document.getElementById("auth-area").style.setProperty("display", "flex");
			document.getElementById("auth-area-time").style.setProperty("display", "flex");

			display = document.querySelector('#time');
			startCountDown(display);
			return;
		}else{
			swalAlert("사용자 정보가 없습니다.","","","","");
			return;
		}
	})
	.catch((error)=>{
		console.log("에러", error);
	})

}

//3분 카운트
function startCountDown(display) {
	let timerVar;
	var duration = 60 * 3;
    var timer = duration, minutes, seconds;
    timerVar = setInterval(function () {
        minutes = parseInt(timer / 60, 10);
        seconds = parseInt(timer % 60, 10);

        minutes = minutes < 10 ? "0" + minutes : minutes;
        seconds = seconds < 10 ? "0" + seconds : seconds;

        display.textContent = minutes + ":" + seconds;

        if (--timer < 0) {
			clearInterval(timerVar);
			swalAlert("제한시간이 만료 되었습니다.<br>다시 시도하여 주시기 바랍니다.","/login/restPwd.do","","","");
        }
    }, 1000);
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
		} else if (data == "init"){
			swalAlert("인정번호 세션이 만료 되었습니다.<br>다시 시도하여 주시기 바랍니다.","/login/restPwd.do","","","");
			return;
		} else {
			swalAlert("인증번호 정보가 다릅니다.","","","","");
			return;
		}
	})
	.catch((error)=>{
		console.log("에러", error);
	})
	
}
