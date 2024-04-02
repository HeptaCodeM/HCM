		var orgComp_name = "";
		var orgComp_num = "";
		var orgComp_ceo_name = "";
		var orgComp_tel = "";
		var orgComp_email = "";
		var orgComp_fax = "";
		var orgComp_addr1 = "";
		var orgComp_addr2 = "";
		var orgComp_post = "";		
		
	window.onload = function(){
		orgComp_name = document.getElementById("comp_name").value;
		orgComp_num = document.getElementById("comp_num").value;
		orgComp_ceo_name = document.getElementById("comp_ceo_name").value;
		orgComp_tel = document.getElementById("comp_tel").value;
		orgComp_email = document.getElementById("comp_email").value;
		orgComp_fax = document.getElementById("comp_fax").value;
		orgComp_addr1 = document.getElementById("comp_addr1").value;
		orgComp_addr2 = document.getElementById("comp_addr2").value;
		orgComp_post = document.getElementById("comp_post").value;	
	}
	
document.onkeydown = function(e) {
	key = (e) ? e.keyCode : event.keyCode;
	console.log("동작합니다");
	const chComp_name = document.getElementById("comp_name").value;
	const chComp_num = document.getElementById("comp_num").value;
	const chComp_ceo_name = document.getElementById("comp_ceo_name").value;
	const chComp_tel = document.getElementById("comp_tel").value;
	const chComp_email = document.getElementById("comp_email").value;
	const chComp_fax = document.getElementById("comp_fax").value;
	const chComp_addr1 = document.getElementById("comp_addr1").value;
	const chComp_addr2 = document.getElementById("comp_addr2").value;
	const chComp_post = document.getElementById("comp_post").value;	

	if(orgComp_name != chComp_name ||
		      orgComp_num != chComp_num ||
		      orgComp_ceo_name != chComp_ceo_name ||
		      orgComp_tel != chComp_tel ||
		      orgComp_email != chComp_email ||
		      orgComp_fax != chComp_fax ||
		      orgComp_addr1 != chComp_addr1 ||
		      orgComp_addr2 != chComp_addr2 ||
		      orgComp_post != chComp_post){
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
		
		
		var reComp_name = document.getElementById("comp_name").value;
		var reComp_num = document.getElementById("comp_num").value;
		var reComp_ceo_name = document.getElementById("comp_ceo_name").value;
		var reComp_tel = document.getElementById("comp_tel").value;
		var reComp_email = document.getElementById("comp_email").value;
		var reComp_fax = document.getElementById("comp_fax").value;
		var reComp_addr1 = document.getElementById("comp_addr1").value;
		var reComp_addr2 = document.getElementById("comp_addr2").value;
		var reComp_post = document.getElementById("comp_post").value;
		var reComp_seal = document.getElementById("comp_seal").value;
		
		console.log("=============================");
		console.log(orgComp_name);
		console.log(orgComp_num);
		console.log(orgComp_ceo_name);
		console.log(orgComp_tel);
		console.log(orgComp_email);
		console.log(orgComp_fax);
		console.log(orgComp_addr1);
		console.log(orgComp_addr2);
		console.log(orgComp_post);
		console.log("=============================");
		console.log(reComp_name);
		console.log(reComp_num);
		console.log(reComp_ceo_name);
		console.log(reComp_tel);
		console.log(reComp_email);
		console.log(reComp_fax);
		console.log(reComp_addr1);
		console.log(reComp_addr2);
		console.log(reComp_post);
		console.log("=============================");
		
		if(orgComp_name == reComp_name &&
				orgComp_num == reComp_num &&
				orgComp_ceo_name == reComp_ceo_name &&
				orgComp_tel == reComp_tel &&
				orgComp_email == reComp_email &&
				orgComp_fax == reComp_fax &&
				orgComp_addr1 == reComp_addr1 &&
				orgComp_addr2 == reComp_addr2 &&
				orgComp_post == reComp_post &&
				!reComp_seal){
			swalHistoryBack("변경된 값이 없습니다!","","");
			return false;
		}
		
		
		console.log(reComp_num.replace(/-/g,''));
		if(!only_Num3(reComp_num.replace(/-/g,''))){
			swalAlert("숫자만 입력하세요","","","","comp_num");
			return false;
		}
		
		console.log(reComp_tel.replace(/-/g,''));
		if(!only_Num3(reComp_tel.replace(/-/g,''))){
			swalAlert("숫자만 입력하세요","","","","comp_tel");
			return false;
		}
		
		if(!checkEmail(reComp_email)){
			swalAlert("이메일을 확인해주세요","","","","comp_email");
			return false;
		}
		
		console.log(reComp_fax.replace(/-/g,''));
		if(!only_Num3(reComp_fax.replace(/-/g,''))){
			swalAlert("숫자만 입력하세요","","","","comp_fax");
			return false;
		}
		
		console.log(reComp_post);
		if(!only_Num3(reComp_post)){
			swalAlert("숫자만 입력하세요","","","","comp_post");
			return false;
		}
		
	}

	
	