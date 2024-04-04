

// -----------------------------------> [ 모달창 ] 드래그형 모달 설정
var element = document.querySelector('#kt_modal_3');
dragElement(element);

function dragElement(elmnt) {
	var pos1 = 0, pos2 = 0, pos3 = 0, pos4 = 0;
	if (elmnt.querySelector('.modal-content')) {
		elmnt.querySelector('.modal-content').onmousedown = dragMouseDown;
	} else {
		elmnt.onmousedown = dragMouseDown;
	}

	function dragMouseDown(e) {
		e = e || window.event;
		pos3 = e.clientX;
		pos4 = e.clientY;
		document.onmouseup = closeDragElement;
		document.onmousemove = elementDrag;
	}

	function elementDrag(e) {
		e = e || window.event;
		pos1 = pos3 - e.clientX;
		pos2 = pos4 - e.clientY;
		pos3 = e.clientX;
		pos4 = e.clientY;
		elmnt.style.top = (elmnt.offsetTop - pos2) + "px";
		elmnt.style.left = (elmnt.offsetLeft - pos1) + "px";
	}

	function closeDragElement() {
		document.onmouseup = null;
		document.onmousemove = null;
	}
}

// -----------------------------------> [ 작성화면 ] 만료일 달력 설정
new tempusDominus.TempusDominus(document.getElementById("kt_td_picker_localization"), {
	localization: {
		locale: "ko",
		startOfTheWeek: 1,
		format: "yyyy-MM-dd"
	}
});


// -----------------------------------> [ 모달창 ] 템플릿 jstree 정보 가져오기
setTimeout(function() {
	$('#jstree').jstree({
		plugins: ['search', 'wholerow'],
		core: {
			data: {
				url: './getTempTree.do',
				method: 'get',
				dataType: 'json',
				success: function(data) {
					console.log(data)
				},
				error: function() {
					alert('데이터 전송 실패');
				}
			}
		}
	});
}, 1000)


// -----------------------------------> [ 모달창 ] 템플릿 미리보기
$('#jstree').on('select_node.jstree', function(e, data) {
	var id = data.node.id;
	if (id.startsWith("CC")) {
		return;
	}
	fetch('./getDoc.do?sidt_temp_cd=' + id)
		.then(resp => { return resp.text() })
		.then(data => {
			docData = data;
			var template = document.getElementById('template')
			template.innerHTML = data;
		})
		.catch(err => { console.log(err) });

})


// -----------------------------------> [ 모달창 ] 데이터 전역변수 설정
var docData;
var sica_cd;
var sidt_temp_cd;


// -----------------------------------> [ 모달창 ] jstree를 통해 템플릿 불러오기
document.getElementById('getTemplate').addEventListener('click', function(e) {
	var selNode = $('#jstree').jstree('get_selected');
	var parentNode = $('#jstree').jstree('get_parent', selNode);
	sica_cd = parentNode;
	sidt_temp_cd = selNode[0];
	// 템플릿 선택 유효성 검사
	if (selNode.length == 0) {
		swalAlert('템플릿을 선택해주세요', '', '', '확인');
		return;
	}
	if (selNode[0].startsWith("CC")) {
		swalAlert('템플릿을 선택해주세요', '', '', '확인');
		return;
	}

	// -----------------------------------> [ 모달창 ] 로그인 정보를 포함한 에디터 화면으로 변경
	// 화면 전환 (템플릿 선택 -> 템플릿 작성)
	$("#template_div").hide();
	$("#editor_div").show();
	document.getElementById('closeBtn').click();
	// 화면으로 전달할 session 정보 설정
	var sessionName = document.getElementById('myName');
	var sessionDept = document.getElementById('myDept');
	var sessionRank = document.getElementById('myRank');
	var sessionBirth = document.getElementById('empl_birth');
	var sessionJoinDate = document.getElementById('empl_joindate').value;
	var sessioncompName = document.getElementById('comp_name').value;
	var sessioncompNum = document.getElementById('comp_num').value;
	var sessionCEOName = document.getElementById('comp_ceo_name').value;
	var sessioncompTel = document.getElementById('comp_tel').value;
	var sessioncompAddr1 = document.getElementById('comp_addr1').value;
	var sessioncompAddr2 = document.getElementById('comp_addr2').value;
	// 템플릿에 로그인한 유저 정보 넣어주기
	var insertName = docData.replace("홍길동", sessionName.value)
	var insertDept = insertName.replace("인사팀", sessionDept.value)
	var insertRank = insertDept.replace("대리", sessionRank.value)

	// 재직증명서 값 모두 넣어주기
	var insertBirth = insertRank.replace("900101", sessionBirth.value)
	var insertcompName = insertBirth.replace("　", sessioncompName)

	var date = new Date();
	var formatDate = moment(date).format('YYYY년 MM월 DD일');
	var formatJoinDate = moment(sessionJoinDate).format('YYYY년 MM월 DD일');

	var insertJoinDate = insertcompName.replace('2024년 01월 01일 ~ 2024년 12월 31일', formatJoinDate + ' ~ ' + formatDate);
	var insertCompNum = insertJoinDate.replace('　　', sessioncompNum);
	var insertCEOName = insertCompNum.replace('　　　', sessionCEOName);
	var insertCompTel = insertCEOName.replace('　　　　', sessioncompTel);
	var insertCompAddr = insertCompTel.replace('　　　　　', sessioncompAddr1 + sessioncompAddr2);
	var insertToday = insertCompAddr.replace('2024년 01월 01일', formatDate)
	editor.setData(insertToday);
	
	// -----------------------------------> [ 작성화면 ]이벤트 날짜 유효성검사
	
	var restHoli = document.getElementById('restHoli').value;
	console.log('restHoli: ',restHoli);
	
	
	



	// -----------------------------------> [ 작성화면 ] 사원정보 유효성검사
	setTimeout(function() {
		var noTouch = document.getElementById('noTouch');
		noTouch.addEventListener('click', function() {
			swalAlert('편집불가한 영역입니다', '', '', '확인');
			document.getElementById('sitb_doc_title').focus();
		})
	}, 1500)
	
	// -----------------------------------> [ 작성화면 ] 휴가/연차 일자 표시
	if (sidt_temp_cd == 'TC000001' || sidt_temp_cd == 'TC000002') {
		var span1 = document.createElement('span');
		var span2 = document.createElement('span');
		var span3 = document.createElement('span');
		var div = document.createElement('div')
		
		span1.setAttribute('style', 'font-size: 13px; font-weight: bold; color: orange;');
		span2.setAttribute('style', 'font-size: 13px; margin-left: 20px; font-weight: bold; color: orange;');
		span3.setAttribute('style', 'font-size: 13px; margin-left: 20px; font-weight: bold; color: orange;');
		div.setAttribute('style', 'text-align: right;')
		
		span1.textContent = '* 총 휴가일수 : ' + document.getElementById('totalHoli').value; 
		span2.textContent = '사용한 휴가일수 : ' + document.getElementById('useHoli').value;
		span3.textContent = '잔여 휴가일수 : ' + document.getElementById('restHoli').value;
		
		div.append(span1);
		div.append(span2);
		div.append(span3);
		
		document.getElementById('editor_div').prepend(div);		
			
	}

})

	
	

// -----------------------------------> [ 작성화면 ] 현재 작성일 설정   
var currentDate = new Date();
//var formatDate = currentDate.getFullYear() + '-' + ('0' + (currentDate.getMonth() + 1)).slice(-2) + '-' + ('0' + currentDate.getDate()).slice(-2);
var formatDate = moment(currentDate).format('YYYY-MM-DD');
document.getElementById('currentDate').innerHTML = formatDate;


// -----------------------------------> [ 작성화면 ] 기안문 임시저장
function insertTempDoc() {
	// 로그인 정보
	var empl_id = document.getElementById('id').value;
	// 기안서 제목
	var sitb_doc_title = document.getElementById('sitb_doc_title').value;	
	if(sitb_doc_title.length == 0) {
		swalAlert('제목을 입력해주세요','','','확인');
		return;
	}
	// 기안서 내용
	var sitb_doc_content = editor.getData();
	// 기안 만료일
	var sitb_doc_expiredt = document.getElementsByClassName('sitb_doc_expiredt')[0].value;
	// 알림 여부 설정
	var checkbox = document.getElementsByName('alflag')[0];
	var sitb_doc_alflag;
	if (checkbox.checked) {
		sitb_doc_alflag = 'Y'
	} else {
		sitb_doc_alflag = 'N'
	}


	// 특정 템플릿 외 이벤트 날짜 검사 안하도록 수정
	var sitb_doc_be = null;
	var sitb_doc_end = null;
	if (sidt_temp_cd == 'TC000001' || sidt_temp_cd == 'TC000002' || sidt_temp_cd == 'TC000006') {
		var sidt_doc_event = document.getElementById('sidb_doc_event').textContent;
		var eventArr = sidt_doc_event.split('~');
		sitb_doc_be = eventArr[0].replace(/년|월|\s+/g, '-').replace(/일/g, '').replace(/-+/g, '-').replace(/^-|-$/g, '');
		sitb_doc_end = eventArr[1].replace(/년|월|\s+/g, '-').replace(/일/g, '').replace(/-+/g, '-').replace(/^-|-$/g, '');
		console.log(sitb_doc_be)
		console.log(sitb_doc_end)
	}
	
	// 기안문 임시저장 정보
	var docData = {
		sitb_doc_title: sitb_doc_title,
		sitb_doc_content: sitb_doc_content,
		empl_id: empl_id,
		sitb_doc_expiredt: sitb_doc_expiredt,
		sitb_doc_alflag: sitb_doc_alflag,
		sitb_doc_be: sitb_doc_be,
		sitb_doc_end: sitb_doc_end,
		sica_cd: sica_cd,
		sidt_temp_cd: sidt_temp_cd
	}
	console.log("기안문 임시저장 정보:", docData);
	var formData = new FormData();
	formData.append('dto', new Blob([JSON.stringify(docData)], { type: 'application/json' }));

	fetch('/doc/insertTempDoc.do', {
		method: 'post',
		body: formData
	})
		.then(resp => {
			if(!resp.ok) {
				throw new Error();
			} else {
				return resp.text();
			}
		})
		.then(data => {
			console.log(data);
			if (data == "성공") {
				location.href = '/doc/tempDocs.do';
			} else {
				swalAlert('작성에 실패했습니다', '', '', '확인', '')
			}

		})
		.catch(err => { console.log(err) })

}


// -----------------------------------> [ 작성화면 ] 기안문 제출하기
function insertDoc() {
	var sidb_doc_expiredt = document.getElementsByName('sidb_doc_expiredt')[0].value;
	var sidb_doc_title = document.getElementsByName('sidb_doc_title')[0].value;
	var sidb_doc_content = editor.getData();
	var empl_id = document.getElementById('id').value;
	var checkbox = document.getElementsByName('alflag')[0];
	var sidb_doc_alflag;
	if (checkbox.checked) {
		sidb_doc_alflag = 'Y'
	} else {
		sidb_doc_alflag = 'N'
	}

	var sidb_doc_be = null;
	var sidb_doc_end = null;

	if (sidt_temp_cd == 'TC000001' || sidt_temp_cd == 'TC000002' || sidt_temp_cd == 'TC000006') {
		var sidb_doc_event = document.getElementById('sidb_doc_event').textContent;
		var eventArr = sidb_doc_event.split('~');
		const bebe = eventArr[0].replace(/\s+/g, '');
		const enen = eventArr[1].replace(/\s+/g, '');

		result1 = bebe.substring(0, bebe.length - 1);
		result2 = enen.substring(0, enen.length - 1);
		var be = result1.replace('년', '-');
		var end = result2.replace('년', '-');
		sidb_doc_be = be.replace('월', '-')
		sidb_doc_end = end.replace('월', '-')
		
		var currentDate = moment(new Date()).format('YYYY-MM-DD');
		var calEndDate = moment(new Date(sidb_doc_end)).format('YYYY-MM-DD');
		var calBeDate = moment(new Date(sidb_doc_be)).format('YYYY-MM-DD');
		console.log(currentDate);
		console.log(calBeDate)
		console.log(calEndDate)
		if(calBeDate > calEndDate) {
			swalAlert('기간을 다시 확인해주세요','','','확인');
			return;
		}
		
		if(currentDate > calBeDate || currentDate > calEndDate) {
			swalAlert('시작일과 종료일을 확인해주세요','','','확인');
			return;
		} 
	}
	
	// -----------------------------------> [ 작성화면 ] 휴가/연차 날짜 유효성 검사
	if (sidt_temp_cd == 'TC000001' || sidt_temp_cd == 'TC000002') {
		
		var rest = document.getElementById('restHoli').value
		var calEndDate = new Date(sidb_doc_end);
		var calBeDate = new Date(sidb_doc_be);
		var calResult = (calEndDate - calBeDate) / 1000 / 60 / 60 / 24;
		if(calResult > rest) {
			swalAlert('휴가 신청 기간이 잔여 휴가일보다 많습니다','','','확인');
			return;
		}
	}
	
	// -----------------------------------> [ 작성화면 ] 유효성 검사
	var file = document.getElementById('sidf_file_content').files[0]; 
	var formData = new FormData();

	if (ref == undefined) {
		ref = { empl_ref: '' }
	}
	if (dept == undefined) {
		dept = { empl_dept_cd: '' }
	}

	if (sidb_doc_be == '2024-00-00' || sidb_doc_be == '--' || sidb_doc_be == '') {
		sidb_doc_be = '2024-01-01';
	}
	if (sidb_doc_end == '2024-00-00' || sidb_doc_end == '--' || sidb_doc_end == '') {
		sidb_doc_end = '2024-01-01';
	}

	if (sidb_doc_title.length == 0) {
		swalAlert('제목을 입력해주세요', '', '', '확인');
		return;
	} else if (sidb_doc_title.length < 6) {
		swalAlert('제목을 6글자 이상 입력해주세요', '', '', '확인');
		return;
	}

	if (!sidb_doc_expiredt) {
		swalAlert('만료일을 선택해주세요', '', '', '확인');
		return;
	}

	var signMsg = document.getElementById('signMsg');
	if (signMsg.value == "" || signMsg.value == "서명을 선택해주세요") {
		swalAlert('서명 등록을 하지 않으셨습니다', '', '', '확인');
		return;
	}

	if (json == undefined) {
		swalAlert('결재선을 지정하지 않으셨습니다', '', '', '확인');
		return;
	}


	var docData = {
		sidb_doc_title: sidb_doc_title,
		sidb_doc_content: sidb_doc_content,
		empl_id: empl_id,
		sidb_doc_expiredt: sidb_doc_expiredt,
		sidb_doc_alflag: sidb_doc_alflag,
		sidb_doc_be: sidb_doc_be,
		sidb_doc_end: sidb_doc_end,
		sica_cd: sica_cd,
		sidt_temp_cd: sidt_temp_cd,
		sidb_curr_id: json[0].appr_id,
		empl_ref: ref.empl_ref,
		empl_dept_cd: dept.empl_dept_cd,
		emsi_seq: sign,
		sidb_doc_json: json
	}


	console.log(docData);

	// 파일 추가
	formData.append('file', file);
	formData.append('dto', new Blob([JSON.stringify(docData)], { type: 'application/json' }));


	fetch('/doc/insertDoc.do', {
		method: 'post',
		body: formData
	})
		.then(resp => {
			return resp.text()
		})
		.then(data => {
			console.log(data);
			if (data == "성공") {
				location.href = '/doc/docBox.do';
			} else if (data == "중복된 날짜") {
				swalAlert('이미 해당 날짜에 일정이 있습니다', '', '', '확인', '')
			} else {
				swalAlert('작성에 실패했습니다', '', '', '확인', '')
			}

		})
		.catch(err => { console.log(err) })

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
	open('/doc/writeDoc/signLine.do', '', 'width=1600px height=900px left=100');
});
// 서명 팝업
document.getElementById('selectSign').addEventListener('click', function() {
	open('/doc/writeDoc/selectSign.do', '', 'width=1200px height=720px left=400');
});

// 전역변수 설정
var ref;
var dept;
var json;
var sign;


// -----------------------------------> [ 작성화면 ] 기안문 기본 정보 load
window.addEventListener('message', function(e) {
	var data = e.data;
	if (data.hasOwnProperty('empl_ref')) {
		ref = data;
		document.getElementById('refName').value = ref.empl_ref_name;
	} else if (data.hasOwnProperty('empl_dept_cd')) {
		dept = data;
		document.getElementById('deptName').value = dept.empl_dept_name;
	} else if (typeof data == "string") {
		sign = data;
		document.getElementById('signMsg').value = "서명이 선택되었습니다"
	} else {
		json = data;
		//      document.getElementById('json').textContent = JSON.stringify(data);
		if (data.length == 3) {
			document.getElementById('apprName').value = data[0].appr_name + " (" + data[0].appr_position + ")" + " ➡️ " + data[1].appr_name + " (" + data[1].appr_position + ")" + " ➡️ " + data[2].appr_name + " (" + data[2].appr_position + ")";
		} else if (data.length == 2) {
			document.getElementById('apprName').value = data[0].appr_name + " (" + data[0].appr_position + ")" + " ➡️ " + data[1].appr_name + " (" + data[1].appr_position + ")";
		} else if (data.length == 1) {
			document.getElementById('apprName').value = data[0].appr_name + " (" + data[0].appr_position + ")";
		}
	}
	console.log('ref : ', ref);
	console.log('dept : ', dept);
	console.log('json : ', json);
	console.log('sign : ', sign);

});

// -----------------------------------> [ 작성화면 ] 기본서명 check 설정
function defaultSign() {
	var chk = document.getElementById('chk');
	var btn = document.getElementById('selectSign');
	if (chk.checked) {
		console.log('체크')
		btn.setAttribute('disabled', '');
		document.getElementById('signMsg').value = "기본서명이 선택되었습니다"
		sign = document.getElementById('emsi_seq').value;
		console.log(sign)

	} else {
		console.log('해제')
		btn.removeAttribute('disabled');
		document.getElementById('signMsg').value = "서명을 선택해주세요"
		sign = undefined;
		console.log(sign)
	}
}

//if (typeof sign != undefined) {
//	console.log('sign: ', sign)
//	document.getElementById('signMsg').setAttribute('type', 'text');
//} else {
//	console.log('sign: ', sign)
//	document.getElementById('signMsg').setAttribute('type', 'hidden');
//}











