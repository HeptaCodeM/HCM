function approve() {
	alert("승인처리 되었습니다");
	document.getElementById('reply').submit();
}


function deny() {
	alert("반려처리 되었습니다");
	document.getElementById('denyReply').submit();
}

function gianCancel() {
	 var really = confirm("정말 상신 취소하시겠습니까?");
    if (really) {
        alert("상신취소 되었습니다");
        document.getElementById('gianCancel').submit();
    }
}

//승인 모달창
var element = document.querySelector('#kt_modal_3');
dragElement(element);

function dragElement(elmnt) {
	var pos1 = 0, pos2 = 0, pos3 = 0, pos4 = 0;
	if (elmnt.querySelector('.modal-content')) {
		// if present, the header is where you move the DIV from:
		elmnt.querySelector('.modal-content').onmousedown = dragMouseDown;
	} else {
		// otherwise, move the DIV from anywhere inside the DIV:
		elmnt.onmousedown = dragMouseDown;
	}

	function dragMouseDown(e) {
		e = e || window.event;
		// get the mouse cursor position at startup:
		pos3 = e.clientX;
		pos4 = e.clientY;
		document.onmouseup = closeDragElement;
		// call a function whenever the cursor moves:
		document.onmousemove = elementDrag;
	}

	function elementDrag(e) {
		e = e || window.event;
		// calculate the new cursor position:
		pos1 = pos3 - e.clientX;
		pos2 = pos4 - e.clientY;
		pos3 = e.clientX;
		pos4 = e.clientY;
		// set the element's new position:
		elmnt.style.top = (elmnt.offsetTop - pos2) + "px";
		elmnt.style.left = (elmnt.offsetLeft - pos1) + "px";
	}

	function closeDragElement() {
		// stop moving when mouse button is released:
		document.onmouseup = null;
		document.onmousemove = null;
	}
}



//반려 모달창
var element = document.querySelector('#kt_modal_3');
dragElement(element);

var denyModal = document.querySelector('#kt_deny');
dragElement(denyModal);

function dragElement(elmnt) {
	var pos1 = 0, pos2 = 0, pos3 = 0, pos4 = 0;
	if (elmnt.querySelector('.modal-content')) {
		// if present, the header is where you move the DIV from:
		elmnt.querySelector('.modal-content').onmousedown = dragMouseDown;
	} else {
		// otherwise, move the DIV from anywhere inside the DIV:
		elmnt.onmousedown = dragMouseDown;
	}

	function dragMouseDown(e) {
		e = e || window.event;
		// get the mouse cursor position at startup:
		pos3 = e.clientX;
		pos4 = e.clientY;
		document.onmouseup = closeDragElement;
		// call a function whenever the cursor moves:
		document.onmousemove = elementDrag;
	}

	function elementDrag(e) {
		e = e || window.event;
		// calculate the new cursor position:
		pos1 = pos3 - e.clientX;
		pos2 = pos4 - e.clientY;
		pos3 = e.clientX;
		pos4 = e.clientY;
		// set the element's new position:
		elmnt.style.top = (elmnt.offsetTop - pos2) + "px";
		elmnt.style.left = (elmnt.offsetLeft - pos1) + "px";
	}

	function closeDragElement() {
		// stop moving when mouse button is released:
		document.onmouseup = null;
		document.onmousemove = null;
	}
}

//파일 다운로드
var docNum = "";
onload = function() {
	docNum = document.getElementById('docNum').value;
	console.log(docNum)
	getFile();
	document.getElementById('downBtn').addEventListener('click', function() {
		var sel = document.getElementById('selectFile');
    location.href = './fileDown.do?sidf_file_num=' + sel.value;

	});
}


function getFile() {
	console.log(docNum)
	fetch('./getDocsFileList.do?docNum=' + docNum)
		.then(resp => { return resp.json() })
		.then(data => {

			var sel = document.getElementById('selectFile');
			console.log(data);
			console.log("데이터들고왔니?"+data.length);
			 if (data.length === 0) {
				var downBtn = document.getElementById('downBtn');
   				 downBtn.disabled = true;
                var opt = document.createElement('span');
                opt.textContent = " 첨부된 파일이 없습니다.";
                sel.appendChild(opt);
            } else {
			for (let d of data) {
				var opt = document.createElement('span');
				opt.setAttribute('value', d.sidf_file_num);
				opt.textContent = d.sidf_file_origin;
				sel.appendChild(opt);
			}
			}
		})
		.catch(err => { console.log(err) });
}



//html to pdf

function pdfPrint() {

	// 현재 document.body의 html을 A4 크기에 맞춰 PDF로 변환
	html2canvas(document.getElementById("previewContent"), {
		onrendered: function(canvas) {
			console.log("작동");

			// (그리거나 캡쳐한)캔버스를 지정한포멧에 따라 이미지로 변환후 데이터URL로 반환하는 함수
			var imgData = canvas.toDataURL('image/jpg');


			var imgWidth = 210; // 이미지 가로 길이(mm) A4 기준
			var pageHeight = imgWidth *3;  // 출력 페이지 세로 길이 계산 A4 기준
			var imgHeight = canvas.height * imgWidth / canvas.width;//이미지의 세로길이
			var heightLeft = imgHeight; //출력해야할 이미지의 높이

			var doc = new jsPDF('p', 'mm');	//PDF를 생성하고 조작하기 위한 객체 생성
			//매개변수는 PDF의 생성되는 방향과 단위를 의미한다 ,'p'는 세로'l'은 가로방향 'mm'단위로 페이지의 크기 단위

			var position = 0;

			// 첫 페이지 출력
			//			저장할이미지, 저장타입, 시작x좌표, 시작y좌표, PDF의 크기(가로,세로)	
			doc.addImage(imgData, 'PNG', 0, position, imgWidth, imgHeight);
			heightLeft -= pageHeight;
			//전체 이미지의 높이에서 PDF 한페이지의 높이를 -

			// 한 페이지 이상일 경우 루프 돌면서 출력
			while (heightLeft >= 20) {
				position = heightLeft - imgHeight;
				doc.addPage();
				doc.addImage(imgData, 'PNG', 0, position, imgWidth, imgHeight);
				heightLeft -= pageHeight;
			}

			var docTitle = document.getElementById("docTitle").value;
			// 파일 저장
			doc.save(docTitle+'.pdf');

		}
	});

}


var saveBtn = document.querySelector("#openWindow");
	saveBtn.addEventListener("click", function() {
		console.log("작동");
		pdfPrint();
	});
	
//모달버튼클릭시 내용 복제
function preview(){
	var pdfZoneContent = document.getElementById('pdfZone').innerHTML;
    document.getElementById('previewContent').innerHTML = pdfZoneContent;
}
	
	

//윈도우 오픈시 생성시킬 html 코드들 
   function openPreview() {
		const options = 'width=1100, height=800, top=50, left=50, right=100, scrollbars=yes'
            var previewContent = document.getElementById('pdfZone').innerHTML;
            var previewWindow = window.open('', '_blank', options);
            previewWindow.document.write('<html><head><title>미리보기</title></head><body>' + previewContent 
            							+  '<button id="modalPdf">PDF로 변환</button>'+
            							'</body></html>');
            previewWindow.document.close();
            
    var cssLink = previewWindow.document.createElement("link");
		cssLink.href = "/css/doc/preview.css";
		cssLink.rel = "stylesheet";
		cssLink.type = "text/css";
		previewWindow.document.head.appendChild(cssLink);        
            
    var script1 = previewWindow.document.createElement('script');
    script1.src = "https://cdnjs.cloudflare.com/ajax/libs/es6-promise/4.1.1/es6-promise.auto.js";
    previewWindow.document.head.appendChild(script1);

    var script2 = previewWindow.document.createElement('script');
    script2.src = "https://html2canvas.hertzen.com/dist/html2canvas.min.js";
    previewWindow.document.head.appendChild(script2);

    var script3 = previewWindow.document.createElement('script');
    script3.src = "https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.4/jspdf.min.js";
    previewWindow.document.head.appendChild(script3);

    var script4 = previewWindow.document.createElement('script');
    script4.src = "https://cdnjs.cloudflare.com/ajax/libs/html2pdf.js/0.10.1/html2pdf.bundle.min.js";
    previewWindow.document.head.appendChild(script4);

    var script5 = previewWindow.document.createElement('script');
    script5.src = "/js/doc/previewPdf.js";
    previewWindow.document.body.appendChild(script5);
    
    
            
        }

    // 미리보기 버튼을 클릭하면 미리보기 창열림.
    document.addEventListener('DOMContentLoaded', function() {
        document.getElementById('openWindow').addEventListener('click', function() {
           console.log("누름")
            openPreview();
        });
    });