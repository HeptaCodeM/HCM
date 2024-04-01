
//윈도우 오픈시 생성시킬 html 코드들 
function openPreview() {
		 const width = 1200;
 	 const height = 800;
	     const left =  Math.ceil((window.screen.width - width)/2);
	     const top = Math.ceil((window.screen.height - height)/2);
	     const options = 'width=' + width + ', height=' + height + ', top=' + top + ', left=' + left + ', scrollbars=yes';
         
         var previewContent = document.getElementById("savePdfZone").innerHTML;
         var previewWindow = window.open('', '_blank', options);
         previewWindow.document.write('<html><style type="text/css"> #hiidenSealImg{ display: inline-block !important; vertical-align: middle; } </style><head><title>미리보기</title></head><body><div id="saveZone">' + previewContent + '</div></body>'
         							+  '<button class="btn btn-primary btnMd" style="font-size: small;" id="makePdf">PDF 저장</button>'+
         							'</html>');
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
 script5.src = "/js/hr/makePdfHr.js";
 previewWindow.document.body.appendChild(script5);
 
 
         
}


window.onload = function(){
	var saveBtn = document.querySelector("#savePdf");
	saveBtn.addEventListener("click",function(){
		console.log("작동");
		openPreview();
	});
	
	
	var printBtn = document.querySelector("#printPdf");
	printBtn.addEventListener("click",function(){
		console.log("작동");
		
		var orgBody = document.body.innerHTML;
		var printBody = document.getElementById("savePdfZone").innerHTML;
		
		var emdh_type = 'P';
		var emdn_id = document.getElementById("docNumber").value;
		
		fetch('/hr/certificate/downloadDoc.do?emdh_type='+emdh_type+'&emdn_id='+emdn_id,{
		method: "GET",
		})
		.then(response =>{
			return response;
			console.log(response);
		})
		.then(returnData => {
			console.log(returnData);
			document.body.innerHTML = printBody;
			document.getElementById("hiidenSealImg").style.display="inline-block";
			window.print();
			document.body.innerHTML = orgBody;
		})
	    .catch(err => { 
	        console.log('에러발생', err);
	    });
		
		
	});
	
	
}