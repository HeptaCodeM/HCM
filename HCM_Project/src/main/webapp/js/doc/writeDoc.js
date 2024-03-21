
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

onload = function() {
	document.getElementById('getTemplate').addEventListener('click', function(e) {
		var selNode = $('#jstree').jstree('get_selected')
		var editorVal = document.querySelector("#editor")

		console.log("selNode: "+selNode);
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
	
	$('#jstree').jstree({
		plugins: ['search', 'wholerow'],
		core : {
			data : {
				url : './getTempTree.do',
				method : 'get',
				dataType : 'json',
				success : function(data) {
					console.log(data)
				},
				error: function(){
					alert('데이터 전송 실패');
				}
			}
		}
	});
	
	$('#jstree').on('select_node.jstree', function(e, data) {
		var id = data.node.id;
		if(id.startsWith("CC")){
			return;
		}
		fetch('./getDoc.do?sidt_temp_cd=' + id)
		.then(resp => {return resp.text()})
		.then(data => {
			docData = data;
//			console.log(data);
			var template = document.getElementById('template')
			template.innerHTML = data;
		})
		.catch(err => {console.log(err)});
		
	})
	
}


$(document).ready(function(){
	
});










