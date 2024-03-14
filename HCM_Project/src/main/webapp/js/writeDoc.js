
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


onload = function() {
	document.getElementById('tree').addEventListener('click', getTree)
}

/*$().ready(function() {
	$('#jstree').jstree({
		plugins: ['search','wholrow'],
		core : {
			data : {
				url: './getTempTree.do',
				method: 'get',
				dataType: 'json',
				seccess: function(data){
					console.log(data);
				}
			}
		}
	})
})*/


function getTree() {
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
}













