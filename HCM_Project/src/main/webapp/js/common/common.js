//Swal Alert
function swalAlert(msg, movePage, className, btnName, focusid) {
	//console.log(msg, className, btnName);
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

//Swal Alert Close(Popup)
function swalClose(msg, movePage, className, btnName) {
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
			if (movePage != "" && movePage != undefined) {
				opener.location.href = movePage;
			}
			self.close();
		}
	})
}

//Swal Alert Confirm
async function sweetAlertConfirm(msg, tFunc, fFunc){
	var cFlag = await sc(msg);
	//console.log(cFlag);
	if(cFlag){
		tFunc();
	}else{
		if(fFunc != "" && fFunc != "undefined"){
			fFunc();
		}
	}
}

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

/* 날짜형식 체크(0000-00-00) */
function checkDate(val) {
	var Regex = /^(?!0000)(?:(?:19|20)\d{2})-(?:(?:0[1-9]|1[0-2]))-(?:(?:0[1-9]|[12][0-9]|3[01]))$/;
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


// 날짜 값을 yyyy-mm-dd 형식으로 변환하는 함수
function formatDate(date) {
    var year = date.getFullYear();
    var month = String(date.getMonth() + 1).padStart(2, '0'); // 월은 0부터 시작하므로 1을 더하고 2자리로 맞춤
    var day = String(date.getDate()).padStart(2, '0'); // 일을 2자리로 맞춤
    return year + '-' + month + '-' + day;
}

//레이어창 뒤 DIMED 클릭 시 처리
document.onload = function() {
	document.getElementById("overlay").addEventListener("click", function() {
		const layer = this.nextElementSibling;
		this.style.display = "none";
		if(layer != '' && layer != 'undefined'){
	        layer.style.display = "none";
		}
	});
}

/* 임직원 정보 확인 Layer */
function empInfoLayer(empl_id, element){
	/*
	if(typeof xVal == 'undefined'){
		xVal = 0;
	}
	if(typeof yVal == 'undefined'){
		yVal = 0;
	}
	*/
	
	fetch('/hr/employee/selectEmployeeOne.do?empl_id='+empl_id, {
		method: 'GET',
	})
	.then(resp=>{
		if (!resp.ok){
		      throw new Error(resp)
		}
		return resp.json();
	})
	.then(data => {
		var htmlBody = "";	
		htmlBody += "<div class='symbol symbol-50px me-5'>";
		htmlBody += "	<img alt='Logo' src='"+ data.empl_picture_str +"' />";
		htmlBody += "</div>";
		htmlBody += "<div class='d-flex flex-column'>";
		htmlBody += "	<div class='fw-bold d-flex align-items-center fs-5'>"+data.empl_name;
		htmlBody += "		<span class='badge badge-light-success fw-bold fs-8 px-2 py-1 ms-2'>"+data.coco_name_rnm+"</span>";
		htmlBody += "	</div>";
		htmlBody += "	<div class='fw-semibold text-muted fs-7'>"+data.coco_name_dnm+"</div>";
		htmlBody += "	<a href='mailto:${userInfoVo.empl_email}' class='fw-semibold text-muted text-hover-primary fs-7'>"+data.empl_email+"</a>";
		htmlBody += "</div>";

		var empInfoId = document.querySelector('#empInfoLayer');
		var empInfoElement = document.querySelector('.emp-info-layer');
		// 선택한 요소의 innerHTML 속성을 설정하여 내용 추가
		empInfoElement.innerHTML = htmlBody;
		empInfoId.style.display='block';
		
		console.log($("#jstree").clientTop);
		var elementPos = element.getBoundingClientRect();
		var elementPosTop = elementPos.y;
		var elementPosLeft = elementPos.x;
		var elementPosWidth = elementPos.width;
		var elementPosHeight = elementPos.height;

		console.log(element.getBoundingClientRect());
		console.log(elementPosTop, elementPosLeft, elementPosWidth, elementPosHeight)
		
		empInfoId.style.top = (elementPosTop+elementPosHeight-45)+"px";
		empInfoId.style.left = (elementPosLeft+elementPosWidth+20)+"px";
	})
	.catch((error)=>{
		console.log("에러", error);
	})
}
