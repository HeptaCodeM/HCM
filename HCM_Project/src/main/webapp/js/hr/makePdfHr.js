	var makePdf = document.querySelector("#makePdf");
	makePdf.addEventListener("click",function(){
		console.log("작동");
		pdfPrint();
	});
	
	function pdfPrint() {
	
	// 현재 document.body의 html을 A4 크기에 맞춰 PDF로 변환
	html2canvas(document.querySelector("#saveZone"), {
		onrendered: function(canvas) {
			console.log("작동");
			
			// (그리거나 캡쳐한)캔버스를 지정한포멧에 따라 이미지로 변환후 데이터URL로 반환하는 함수
			var imgData = canvas.toDataURL('image/png');
	

			var imgWidth = 210; // 이미지 가로 길이(mm) A4 기준
			var imgHeight = canvas.height * imgWidth / canvas.width*1.2;//이미지의 세로길이

			var doc = new jsPDF('p', 'mm');	//PDF를 생성하고 조작하기 위한 객체 생성
			//매개변수는 PDF의 생성되는 방향과 단위를 의미한다 ,'p'는 세로'l'은 가로방향 'mm'단위로 페이지의 크기 단위
			

			// 첫 페이지 출력
			//			저장할이미지, 저장타입, 시작x좌표, 시작y좌표, PDF의 크기(가로,세로)	
			doc.addImage(imgData, 'PNG', 0, 5, imgWidth, imgHeight);


			var docTitle = document.getElementById("docTitle").value;
			// 파일 저장
			doc.save(docTitle+'.pdf');

			var emdh_type = 'D';
			var emdn_id = document.getElementById("docNumber").value;
			
			fetch('/hr/certificate/downloadDoc.do?emdh_type='+emdh_type+'&emdn_id='+emdn_id,{
			method: "GET",
			})
			.then(response =>{
				return response;
			})
			.then(returnData => {
				console.log(returnData);
			})
		    .catch(err => { 
		        console.log('에러발생', err);
		    });
			
		}

	}); 

}