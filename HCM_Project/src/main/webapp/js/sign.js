
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

var back = document.querySelector("#back");
back.addEventListener("click",function(){
	console.log("작동");
	history.back(-1);
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
		fetch('/hr/sign/insertSign.do',{
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
				location.href="/hr/sign/signListForm.do"
			}
		})
		.then(data => {
			console.log(data);
		})
	    .catch(err => { // 오류 발생시 오류를 담아서 보여줌
	        console.log('Fetch Error', err);
	    });
	}
	
});


