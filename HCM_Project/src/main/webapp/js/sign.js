
var canvas = document.getElementById("signpad");
var signature = new SignaturePad(canvas, {
	minWidth : 2,
	maxWidth : 2,
	penColor : "rgb(0, 0, 0)"
});
 
var clear = document.querySelector("#clear");
clear.addEventListener("click",function(){
	console.log("작동");
	signature.clear();
});

var save = document.querySelector("#save");
save.addEventListener("click",function(){
	console.log("작동");
	var data = signature.toDataURL("image/png");
	var emsi_title = document.getElementById("emsi_title").value;
	var empl_id = document.getElementById("empl_id").value;
	var emsi_create_id = document.getElementById("emsi_create_id").value;
	var signData = new Object();
	if(signature.isEmpty() || emsi_title == '') {
		alert("내용이 없습니다.");
	} else {
//		console.log(data);
		signData.emsi_sign_img = data;
		signData.emsi_title = emsi_title;
		signData.empl_id = empl_id;
		signData.emsi_create_id = emsi_create_id;
		console.log(signData);
		fetch('/doc/signManagement/insertSign.do',{
			method:"post",
			headers: {
            	"Content-Type": "application/json"
            },
			body:JSON.stringify(signData)
		})
		.then(response =>{
			console.log(response);
			if(!response.ok){
				throw new Error("에러! :( ");
			}else{
				return response.text();
			}
		})
		.then(data => {
			console.log(data);
			if(data === 'true') {
				swalAlert('저장되었습니다', '', '', '확인');
				setTimeout(function() {
					location.href = '/doc/signManagement.do';
				}, 1000)
			} else {
				swalAlert('저장실패', '', '', '확인');
				setTimeout(function() {
					location.href = '/doc/signManagement.do';
				}, 1000)
			}
		})
	    .catch(err => { // 오류 발생시 오류를 담아서 보여줌
	        console.log('Fetch Error', err);
	    });
	}
	
});

onload = function() {
	document.getElementById('delBtn').addEventListener('click', delSign);
	document.getElementById('defaultBtn').addEventListener('click', setDefault);
}

function delSign() {
	sweetAlertConfirm("서명을 삭제할까요?", function() {
		var seq = '';
		var id = document.getElementById('empl_id').value;
		var chkbox = document.getElementsByName("signDefault");
		for (let chk of chkbox) {
			if (chk.checked) {
				seq = chk.value;
			}
		}
		if (seq == '') {
			swalAlert('서명을 선택해주세요', '', '', '확인');
			return;
		}
		fetch('/doc/signManagement/deleteSign.do?emsi_seq=' + seq + '&empl_id=' + id)
			.then(resp => { return resp.text() })
			.then(data => {
				console.log(data);
				swalAlert('삭제되었습니다', '', '', '확인');
				setTimeout(function() {
					location.href = '/doc/signManagement.do';
				}, 1000)

			})
	.catch(err => {console.log(err)});
	}, '')
}

function setDefault() {
	var seq = '';
	var id = document.getElementById('empl_id').value;
	var chkbox = document.getElementsByName("signDefault");
	for(let chk of chkbox) {
		if(chk.checked) {
			seq = chk.value;
		}
	}
	if(seq == '') {
		swalAlert('서명을 선택해주세요', '', '', '확인');
		return;
	}
	
	
	fetch('/doc/signManagement/setDefault.do?emsi_seq=' + seq + '&empl_id=' + id)
	.then(resp => {return resp.text()})
	.then(data => {
		console.log(data);
		swalAlert('설정되었습니다', '', '', '확인');
		setTimeout(function() {
			location.href = '/doc/signManagement.do';
		}, 1000)
		
	})
	.catch(err => {console.log(err)});
}

function chkOnly(chk) {
	const checkboxes = document.getElementsByName("signDefault");
	checkboxes.forEach((cb) => {
		cb.checked = false;
	})
	chk.checked = true;

}



















