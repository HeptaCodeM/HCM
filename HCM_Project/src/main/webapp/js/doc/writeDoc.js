
// 드래그형 모달
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
// Doc_jstree
var docData;
var sica_cd;
var sidt_temp_cd;

document.getElementById('getTemplate').addEventListener('click', function(e) {
	var selNode = $('#jstree').jstree('get_selected');
	var parentNode = $('#jstree').jstree('get_parent', selNode);
	sica_cd = parentNode;
	sidt_temp_cd = selNode[0];

//	console.log("selNode: " + selNode);
	if (selNode.length == 0) {
		alert('템플릿을 선택해주세요');
		return;
	}
	if (selNode[0].startsWith("CC")) {
		alert('템플릿을 선택해주세요');
		return;
	}

	//		console.log("getTemplate: " + docData);
	$("#template_div").hide();
	$("#editor_div").show();
	document.getElementById('closeBtn').click();
	var sessionName = document.getElementById('myName');
	var sessionDept = document.getElementById('myDept');
	var sessionRank = document.getElementById('myRank');
	var insertName = docData.replace("홍길동", sessionName.value)
	var insertDept = insertName.replace("인사팀", sessionDept.value)
	var insertRank = insertDept.replace("대리", sessionRank.value)
	editor.setData(insertRank);
	//editor.setData(docData);



})
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
},1000)


$('#jstree').on('select_node.jstree', function(e, data) {
	var id = data.node.id;
	if (id.startsWith("CC")) {
		return;
	}
	fetch('./getDoc.do?sidt_temp_cd=' + id)
		.then(resp => { return resp.text() })
		.then(data => {
			docData = data;
			//			console.log(data);
			var template = document.getElementById('template')
			template.innerHTML = data;
		})
		.catch(err => { console.log(err) });

})
	
document.getElementById('currentDate').innerHTML = Date();

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
	
	// 파일
//	var file = document.getElementById('sidb_doc_content');
//	var json = new FormData();
	
//	json.append(file);

	var file = document.getElementById('sidf_file_content').files[0]; // 파일 가져오기
	var formData = new FormData();

	// 데이터 추가
//	json.append('sidb_doc_title', sidb_doc_title);
//	json.append('sidb_doc_content', sidb_doc_content);
//	json.append('empl_id', empl_id);
//	json.append('sidb_doc_expiredt', sidb_doc_expiredt);
//	json.append('sidb_doc_alflag', sidb_doc_alflag);
//	json.append('sidb_doc_be', sidb_doc_be);
//	json.append('sidb_doc_end', sidb_doc_end);
//	json.append('sica_cd', sica_cd);
//	json.append('sidt_temp_cd', sidt_temp_cd);
//	json.append('sidb_curr_id', '20230109');
//
//	// 배열 추가
//	json.append('sidb_doc_json[]', JSON.stringify({ appr_id: '20230109', appr_depth: '1' }));
//	json.append('sidb_doc_json[]', JSON.stringify({ appr_id: '20230108', appr_depth: '2' }));
//	json.append('sidb_doc_json[]', JSON.stringify({ appr_id: '20230107', appr_depth: '3' }));

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
		sidb_curr_id : '20230109',
		sidb_doc_json : 
		[
			{
				appr_id : '20230109',
				appr_depth : '1'
			},
			{
				appr_id : '20230108',
				appr_depth : '2'
			},
			{
				appr_id : '20230107',
				appr_depth : '3'
			}
		]
	}
	
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
				location.href = '/doc/template.do';
			} else {
				swalAlert('작성에 실패했습니다','','','확인','')
			}
			
		})
		.catch(err => {console.log(err)})
	
}

document.getElementById('insertDoc').addEventListener('click', insertDoc);







