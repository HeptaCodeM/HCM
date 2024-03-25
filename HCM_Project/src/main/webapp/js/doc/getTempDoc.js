
// -----------------------------------> [ 작성화면 ] 현재 작성일 설정	
var currentDate = new Date();
var formatDate =currentDate.getFullYear()+'-'+ ('0' + (currentDate.getMonth() + 1)).slice(-2) + '-' + ('0' + currentDate.getDate()).slice(-2);
document.getElementById('currentDate').innerHTML = formatDate;


// -----------------------------------> [ 작성화면 ] 기안문 임시저장 정보
function insertTempDoc() {
	// 로그인 정보
	var empl_id = document.getElementById('id').value;
	// 기안서 제목
	var sitb_doc_title = document.getElementsByClassName('sitb_doc_title')[0].value;
	// 기안서 내용
	var sitb_doc_content = editor.getData();
	// 기안 만료일
	var sitb_doc_expiredt = document.getElementsByClassName('sitb_doc_expiredt')[0].value;
	// 알림 여부 설정
	var checkbox = document.getElementsByName('alflag')[0];
	var sitb_doc_alflag;
	if(checkbox.checked) {
		sitb_doc_alflag  = 'Y'
	} else {
		sitb_doc_alflag  = 'N'
	}
	/*if(sitb_doc_alflag  = 'Y') {
		checkbox.checked
	}*/
	// 이벤트 날짜
	var sidt_doc_event = document.getElementById('sidb_doc_event').textContent;
	var eventArr = sidt_doc_event.split('~');	
	var sitb_doc_be = eventArr[0].replace(/년|월|\s+/g, '-').replace(/일/g, '').replace(/-+/g, '-').replace(/^-|-$/g, '');
	var sitb_doc_end = eventArr[1].replace(/년|월|\s+/g, '-').replace(/일/g, '').replace(/-+/g, '-').replace(/^-|-$/g, '');
//	var empl_ref (참조)
//	var empl_dept_cd
	
	var docData = {
		sitb_doc_title : sitb_doc_title,
		sitb_doc_content : sitb_doc_content,
		empl_id : empl_id,
		sitb_doc_expiredt : sitb_doc_expiredt,
		sitb_doc_alflag : sitb_doc_alflag,
		sitb_doc_be : sitb_doc_be,
		sitb_doc_end : sitb_doc_end,
		sica_cd : sica_cd,
		sidt_temp_cd : sidt_temp_cd,
		sitb_curr_id : json[0].appr_id,
		empl_ref : ref.empl_ref,
		empl_dept_cd : dept.empl_dept_cd,
		emsi_seq : sign,
		sitb_doc_json : json
	}	
		console.log("기안문 임시저장 정보:",docData);
		var formData = new FormData();
		formData.append('dto', new Blob([JSON.stringify(docData)], {type : 'application/json'}));
		
		fetch('/doc/insertTempDoc.do', {
		method : 'post',
		body : formData
	})
		.then(resp => {
			return resp.text()
		})
		.then(data => {
			console.log(data);
			if(data == "성공") {
				location.href = '/doc/tempDocs.do';
			} else {
				swalAlert('작성에 실패했습니다','','','확인','')
			}
			
		})
		.catch(err => {console.log(err)})
	
}

var sica_cd;
var sidt_temp_cd;
// -----------------------------------> [ 작성화면 ] 기안문 제출하기 정보
function insertDoc() {
	var sidb_doc_expiredt = document.getElementsByName('sidb_doc_expiredt')[0].value;
	var sidb_doc_title = document.getElementsByName('sidb_doc_title')[0].value;
	var sidb_doc_content = editor.getData();
	var empl_id = document.getElementById('id').value;
	var checkbox = document.getElementsByName('alflag')[0];
	if(checkbox.checked) {
		sidb_doc_alflag = 'Y'
	} else {
		sidb_doc_alflag = 'N'
	}
	var sidb_doc_event = document.getElementById('sidb_doc_event').textContent;
	
	var eventArr = sidb_doc_event.split('~');
	const bebe = eventArr[0].replace(/\s+/g, '');
	const enen = eventArr[1].replace(/\s+/g, '');
	
	result1 = bebe.substring(0, bebe.length-1);
	result2 = enen.substring(0, enen.length-1);
	var be = result1.replace('년','-');
	var end = result2.replace('년','-');
	var sidb_doc_be = be.replace('월','-')
	var sidb_doc_end = end.replace('월','-')
	
	var file = document.getElementById('sidf_file_content').files[0]; // 파일 가져오기
	var formData = new FormData();

	var docData = {
		sidb_doc_title : sidb_doc_title,
		sidb_doc_content : sidb_doc_content,
		empl_id : empl_id,
		sidb_doc_expiredt : sidb_doc_expiredt,
		sidb_doc_alflag : sidb_doc_alflag,
		sidb_doc_be : sidb_doc_be,
		sidb_doc_end : sidb_doc_end,
		sica_cd : sica_cd,
		sidt_temp_cd : sidt_temp_cd,
		sidb_curr_id : json[0].appr_id,
		empl_ref : ref.empl_ref,
		empl_dept_cd : dept.empl_dept_cd,
		emsi_seq : sign,
		sidb_doc_json : json
	}
	if(sign == undefined) {
		docData.emsi_seq = document.getElementById('emsi_seq').value;
	}
	console.log(docData);
	
	// 파일 추가
	formData.append('file', file);
	formData.append('dto', new Blob([JSON.stringify(docData)], {type : 'application/json'}));
	
	fetch('/doc/insertDoc.do', {
		method : 'post',
		body : formData
	})
		.then(resp => {
			return resp.text()
		})
		.then(data => {
			console.log(data);
			if(data == "성공") {
				location.href = '/doc/docBox.do';
			} else {
				swalAlert('작성에 실패했습니다','','','확인','')
			}
			
		})
		.catch(err => {console.log(err)})
	
}

// -----------------------------------> [ 작성화면 ] 임시저장 insertTempDoc button
document.getElementById('insertTempDoc').addEventListener('click', insertTempDoc)
// -----------------------------------> [ 작성화면 ] 제출하기 insertDoc button
document.getElementById('insertDoc').addEventListener('click', insertDoc);



// 참조 팝업창
document.getElementById('signRefer').addEventListener('click', function() {
	open('/doc/writeDoc/signRefer.do', '', 'width=720px height=900px left=400');
});
// 결재선 팝업
document.getElementById('signLine').addEventListener('click', function() {
	open('/doc/writeDoc/signLine.do', '', 'width=1600px height=900px left=400');
});
// 서명 팝업
document.getElementById('selectSign').addEventListener('click', function() {
	open('/doc/writeDoc/selectSign.do', '', 'width=1200px height=720px left=400');
});

var ref;
var dept;
var json;
var sign;

window.addEventListener('message', function(e) {
	var data = e.data;
	if(data.hasOwnProperty('empl_ref')) {
		ref = data;
		document.getElementById('refName').textContent = ref.empl_ref_name;
		console.log("값: ", ref.empl_ref_name);
		
	} else if(data.hasOwnProperty('empl_dept_cd')) {
		dept = data;
		document.getElementById('deptName').textContent = dept.empl_dept_name;
	} else if (typeof data == "string") {
		sign = data;
	} else {
		json = data;
//		document.getElementById('json').textContent = JSON.stringify(data);
		if (data.length == 3) {
            document.getElementById('apprName1').textContent = data[0].appr_name + "(" + data[0].appr_position +")" + "," + data[1].appr_name + "," + data[2].appr_name;
        } else if (data.length == 2) {
			document.getElementById('apprName1').textContent = data[0].appr_name + "(" + data[0].appr_position +")"+ "," + data[1].appr_name ;
		} else {
			document.getElementById('apprName1').textContent = data[0].appr_name + "(" + data[0].appr_position +")";
		}
	
	}
	console.log('ref : ' , ref);
	console.log('참조자: ', data.empl_ref_name);
	console.log('dept : ', dept);
	console.log('json : ', json);
	console.log('sign : ', sign);
	
});

function defaultSign() {
	var chk = document.getElementById('chk');
	var btn = document.getElementById('selectSign');
	if (chk.checked) {
		console.log('체크')
		sign = 'Y';
		btn.setAttribute('disabled', '');
	} else {
		console.log('해제');
		sign = 'N';
		btn.removeAttribute('disabled');
	}
}

setTimeout(function() {
	var tempContent = document.getElementById('tempContent').innerHTML;
	console.log(tempContent)
	editor.setData(tempContent);
	var tempTitle = document.getElementById('tempTitle').value;
	sica_cd = document.getElementById('tempSicaCd').value;
	var tempExpiredt = document.getElementById('tempExpiredt').value;
	var tempAlflag = document.getElementById('tempAlflag').value;
	sidt_temp_cd = document.getElementById('tempCd').value;
	ref = document.getElementById('tempRef').value;
	dept = document.getElementById('tempDept').value;
	json = document.getElementById('tempJson').value;
	sign = document.getElementById('tempSign').value;
	
	document.getElementsByName('sidb_doc_title')[0].value = tempTitle;
	document.getElementsByClassName('sitb_doc_expiredt')[0].value = tempExpiredt;
	if(tempAlflag == 'N') {
//		document.getElementsByName('alflag').checked;
		document.querySelector('input[name="alflag"]').checked = true;
	}
	console.log(sign)
	if(sign == 'Y') {
		document.querySelector('input[name="defaultSign"]').checked = true;
	}
	
},1000);



