var orgEmpl_phone = "";
var orgEmpl_tel = "";
var orgEmpl_fax = "";
var aftEmpl_phone = "";
var aftEmpl_tel = "";
var aftEmpl_fax = "";
var picCnt = 0;

window.onload = function(){

    orgEmpl_phone = document.getElementById("empl_phone").value;
    orgEmpl_tel = document.getElementById("empl_tel").value;
    orgEmpl_fax = document.getElementById("empl_fax").value;


    console.log(orgEmpl_phone);
    console.log(orgEmpl_tel);
    console.log(orgEmpl_fax);

}

document.onkeydown = function(e) {
	key = (e) ? e.keyCode : event.keyCode;
	aftEmpl_phone = document.getElementById("empl_phone").value;
	aftEmpl_tel = document.getElementById("empl_tel").value;
	aftEmpl_fax = document.getElementById("empl_fax").value;
    
    
    if(orgEmpl_phone == aftEmpl_phone ||
        	orgEmpl_tel == aftEmpl_tel ||	
        	orgEmpl_fax == aftEmpl_fax){
    		if(key==116) {
    			if(e) {
    			e.preventDefault();
    			} else {
    			event.keyCode = 0;
    			event.returnValue = false;
    			}
    		}    	
        }
}


function valChk() {
	
	aftEmpl_phone = document.getElementById("empl_phone").value;
	aftEmpl_tel = document.getElementById("empl_tel").value;
	aftEmpl_fax = document.getElementById("empl_fax").value
    var chk = document.getElementById("picChk").value;
	
    console.log(orgEmpl_phone);
    console.log(orgEmpl_tel);
    console.log(orgEmpl_fax);
    console.log(aftEmpl_phone);
    console.log(aftEmpl_tel);
    console.log(aftEmpl_fax);
    
    
    
	if(orgEmpl_phone == aftEmpl_phone &&
        	orgEmpl_tel == aftEmpl_tel &&	
        	orgEmpl_fax == aftEmpl_fax &&
        	!chk){
    		swalAlert("변경된 값이 없습니다!","","","","");
    		return false;
    } 
    
    
    var regexPhoneNum = /^\d{3}-\d{4}-\d{4}$/;
    if(!regexPhoneNum.test(aftEmpl_phone)){
    	swalAlert("올바른 휴대폰 번호를 입력하세요.","","","","");
    	return false;
    }
    
    
    if(aftEmpl_tel != ""){
	    var regexTelNum = /^\d{3}$/;
	    if(!regexTelNum.test(aftEmpl_tel)){
	    	swalAlert("내선번호는 정수로만 입력하세요.","","","","");
	    	return false;
	    }
	}
    
    if(aftEmpl_fax != ""){
	    var regexFaxNum = /^\d{2,3}-\d{3}-\d{4}$/;
	    if(!regexFaxNum.test(aftEmpl_fax)){
	    	swalAlert("올바른 팩스번호를 입력하세요.","","","","");
	    	return false;
	    }
	}
    
}
var phoneNumChk = document.querySelector("#empl_phone");
phoneNumChk.addEventListener("focusout",function(){ phoneChk(); });
var telNumChk = document.querySelector("#empl_tel");
telNumChk.addEventListener("focusout",function(){ telChk(); });
var faxNumChk = document.querySelector("#empl_fax");
faxNumChk.addEventListener("focusout",function(){ faxChk(); });

function phoneChk() {
	const chkNum = document.getElementById("empl_phone").value;
	if(orgEmpl_phone == chkNum){
		return false;
	}
	$.ajax({
	    type: "GET",
	    url: "/hr/employee/empAdminValueChk.do?empl_phone="+chkNum,	
	    success: function (data) {
	        console.log(data);
	        if(data == false){
	        	swalAlert("중복된 전화번호입니다.","","","","empl_phone");
	        }
	    },
	    error:function(err){
			console.log(err);
		}
	});
}

function telChk(){
	const chkNum = document.getElementById("empl_tel").value;
	if(orgEmpl_tel == chkNum){
		return false;
	}
	$.ajax({
	    type: "GET",
	    url: "/hr/employee/empAdminValueChk.do?empl_tel="+chkNum,	
	    success: function (data) {
	        console.log(data);
	        if(data == false){
	        	swalAlert("중복된 내선번호입니다.","","","","empl_tel");
	        }	        
	        
	    },
	    error:function(err){
			console.log(err);
		}
	});	
}

function faxChk() {
	const chkNum = document.getElementById("empl_fax").value;
	if(orgEmpl_fax == chkNum){
		return false;
	}
	$.ajax({
	    type: "GET",
	    url: "/hr/employee/empAdminValueChk.do?empl_fax="+chkNum,	
	    success: function (data) {
	        console.log(data);
	        if(data == false){
	        	swalAlert("중복된 팩스번호입니다.","","","","empl_fax");
	        }	 	        
	    },
	    error:function(err){
			console.log(err);
		}
	});
}