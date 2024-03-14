//Swal Alert
function swalAlert(msg, movePage, className, btnName, focusid) {
	if(btnName == "" || className == "undefined"){
		btnName = "확인";
	}
	if(className == "" || className == "undefined"){
		className = "btn-primary";
	}
	Swal.fire({
		width: 500,
		html: msg,
		allowOutsideClick: false,	// 팝업 외부 클릭하여 창닫기 금지
		returnFocus: false,			// 팝업을 호출한 곳으로 focus 반환하지 않음
        confirmButtonText: btnName,
		showClass: {
			popup: `
		      animate__animated
		      animate__fadeInUp
		      animate__faster
			`
		},
		hideClass: {
			popup: `
		      animate__animated
		      animate__fadeOutDown
		      animate__faster
		    `
		},
        customClass: {
            confirmButton: "btn "+className
        }
	})
		//document.getElementById("main").focus()	//input으로   focus 해줘야 버튼포커스 빠져나감
		.then((result) => {
			//console.log(result);
			if (result.isConfirmed) {
				if (movePage != "" && movePage != undefined) {
					location.href = movePage;
				} else if (focusid != "" && focusid != undefined) {
					$("#" + focusid).focus();
				}
			}
		})
}

//Swal Alert History Back
function swalHistoryBack(msg, className, btnName) {
	if(btnName == "" || btnName == "undefined"){
		btnName = "확인";
	}
	if(className == "" || className == "undefined"){
		className = "btn-primary";
	}
	Swal.fire({
		width: 500,
		html: msg,
		allowOutsideClick: false,	// 팝업 외부 클릭하여 창닫기 금지
		returnFocus: false,			// 팝업을 호출한 곳으로 focus 반환하지 않음
        confirmButtonText: btnName,
		showClass: {
			popup: `
		      animate__animated
		      animate__fadeInUp
		      animate__faster
			`
		},
		hideClass: {
			popup: `
		      animate__animated
		      animate__fadeOutDown
		      animate__faster
		    `
		},
        customClass: {
            confirmButton: "btn "+className
        }
	})
		.then((result) => {
			if (result.isConfirmed) {
				history.back();
			}
		})
}

//Swal Alert Confirm
//async function sweetAlertConfirm(msg, tFunc, fFunc){
//	var cFlag = await sc(msg);
//	console.log(cFlag);
//	if(cFlag){
//		tFunc;
//	}else{
//		fFunc;
//	}
//}

var sc = function(msg) {
	return new Promise((resolve, reject) => {
		Swal.fire({
			html: msg,
			showCancelButton: true,
			confirmButtonColor: "#3085d6",
			cancelButtonColor: "#d33",
			confirmButtonText: "확인",
			cancelButtonText: "취소",
			showClass: {
				popup: `
			      animate__animated
			      animate__fadeInUp
			      animate__faster
				`
			},
			hideClass: {
				popup: `
			      animate__animated
			      animate__fadeOutDown
			      animate__faster
			    `
			}		
		}).then((result) => {
			if (result.isConfirmed) {
				resolve(true);
			}else{
				resolve(false);
			}
		}).catch(error => reject(error));
    });
}

/* check 시작 */
/*숫자만*/
function only_Num(val) {
	var regAlphaNum = /^[0-9]+$/;
	if (!regAlphaNum.test(val)) {
		return false;
	}
	else {
		return true;
	}
}

/*실수 숫자 체크*/
function only_Num2(val) {
	var regAlphaNum = /^[0-9.]+$/;
	if (!regAlphaNum.test(val)) {
		return false;
	}
	else {
		return true;
	}
}

/*전화번호 숫자 체크*/
function only_Num3(val) {
	var regAlphaNum = /^[0-9-]+$/;
	if (!regAlphaNum.test(val)) {
		return false;
	}
	else {
		return true;
	}
}

/*영문대/소,숫자만*/
function only_AlphaNum(val) {
	var regAlphaNum = /^[A-Za-z0-9]+$/;
	if (!regAlphaNum.test(val)) {
		return false;
	}
	else {
		return true;
	}
}

/*연락처 숫자 체크*/
function checkTel(val) {
	var regEx = /^\d{2,3}-\d{3,4}-\d{4}/;
	return regEx.test(val);
}

/*휴대폰 숫자 체크*/
function checkPhone(val) {
	var regEx = /^01[01679]-\d{3,4}-\d{4}$/;
	return regEx.test(val);
}

/* 이메일 체크 */
function checkEmail(val) {
	var Regex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
	return Regex.test(val);
}

function checkId(val) {
	var regEx = /^[a-zA-Z0-9_]{8,12}$/;
	return regEx.test(val);
}

/*
- `(?=.*[A-Za-z])`: 최소한 하나의 영문자가 포함되어야 합니다.
- `(?=.*\d)`: 최소한 하나의 숫자가 포함되어야 합니다.
- `(?=.*[!@#$%^&*])`: 최소한 하나의 특수문자가 포함되어야 합니다.
- `[A-Za-z\d!@#$%^&*]{8,12}`: 영문자, 숫자, 특수문자 중에서 8~12자 길이로 이루어져야 합니다.
*/
function checkPw(val) {
	var regEx = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{8,12}$/;
	return regEx.test(val);
}
/* check 종료 */
