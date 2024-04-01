var canvas = document.getElementById("signpad");
var signature = new SignaturePad(canvas, {
	minWidth : 2,
	maxWidth : 2,
	penColor : "rgb(0, 0, 0)"
});
 
var clear = document.querySelector("#clear");
clear.addEventListener("click",function(){
//	console.log("작동");
	signature.clear();
});

var save = document.querySelector("#save");
save.addEventListener("click",function(){
//	console.log("작동");
	var data = signature.toDataURL("image/png");
	var emsi_title = document.getElementById("emsi_title").value;
	var empl_id = document.getElementById("empl_id").value;
	var emsi_create_id = document.getElementById("emsi_create_id").value;
	var signData = new Object();
	if(signature.isEmpty() || emsi_title == '') {
		swalAlert('서명 타이틀을 지정해주세요', '', '', '확인');
	} else {
//		console.log(data);
		signData.emsi_sign_img = data;
		signData.emsi_title = emsi_title;
		signData.empl_id = empl_id;
		signData.emsi_create_id = emsi_create_id;
//		console.log(signData);
		fetch('/doc/signManagement/insertSign.do',{
			method:"post",
			headers: {
            	"Content-Type": "application/json"
            },
			body:JSON.stringify(signData)
		})
		.then(response =>{
//			console.log(response);
			if(!response.ok){
				throw new Error("에러! :( ");
			}else{
				return response.text();
			}
		})
		.then(data => {
//			console.log(data);
			if(data === 'true') {
				swalAlert('저장되었습니다', '', '', '확인');
				setTimeout(function() {
					location.href = '/doc/writeDoc/selectSign.do';
				}, 1000)
			} else {
				swalAlert('저장실패', '', '', '확인');
				setTimeout(function() {
					location.href = '/doc/writeDoc/selectSign.do';
				}, 1000)
			}
		})
	    .catch(err => { // 오류 발생시 오류를 담아서 보여줌
	        console.log('Fetch Error', err);
	    });
	}
	
});

document.getElementById('saveSign').addEventListener('click', saveSign);
document.getElementById('closeBtn').addEventListener('click', function() {
	self.close();
});

function saveSign() {
	var selCheck = document.querySelectorAll('input[type=checkbox]:checked');
	if(selCheck.length == 0) {
		swalAlert('서명을 선택해주세요', '', '', '확인');
	} else {
		sweetAlertConfirm("선택한 서명을 저장할까요?", function() {
			var sign = selCheck[0].value;
			opener.postMessage(sign, location.origin);
			self.close();
		}, '');
	}
}

function chkOnly(chk) {
	const checkboxes = document.getElementsByName("signDefault");
	checkboxes.forEach((cb) => {
		cb.checked = false;
	})
	chk.checked = true;

}



















