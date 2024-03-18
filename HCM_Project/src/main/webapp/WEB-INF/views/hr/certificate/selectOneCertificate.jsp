<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/menu/headerInfo.jsp" %>
<script src="https://cdnjs.cloudflare.com/ajax/libs/es6-promise/4.1.1/es6-promise.auto.js"></script>
<script src="https://html2canvas.hertzen.com/dist/html2canvas.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.4/jspdf.min.js"></script>
<script type="text/javascript">

function pdfPrint() {

	// 현재 document.body의 html을 A4 크기에 맞춰 PDF로 변환
	html2canvas(document.getElementById("savePdfZone"), {
		onrendered: function(canvas) {
			console.log("작동");
			
			// (그리거나 캡쳐한)캔버스를 지정한포멧에 따라 이미지로 변환후 데이터URL로 반환하는 함수
			var imgData = canvas.toDataURL('image/png');
	

			var imgWidth = 210; // 이미지 가로 길이(mm) A4 기준
			var pageHeight = imgWidth * 1.414;  // 출력 페이지 세로 길이 계산 A4 기준
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


//			이미지로 표현
//			document.write('<img src="'+imgData+'" />');
		}

	});

}



window.onload = function(){
	var saveBtn = document.querySelector("#savePdf");
	saveBtn.addEventListener("click",function(){
		console.log("작동");
		pdfPrint();
	});
	
	var printBtn = document.querySelector("#printPdf");
	printBtn.addEventListener("click",function(){
		console.log("작동");
		
		var orgBody = document.body.innerHTML;
		var printBody = document.getElementById("savePdfZone").innerHTML;
		
		document.body.innerHTML = printBody;
		window.print();
		document.body.innerHTML = orgBody;
	});
	
	
}
</script>
<style type="text/css">
	.saveZomeCss{
		padding: 20px;	
	}
	
	@media savePdfZone {
	  @page { margin: 0; }
	  body { margin: 1.6cm; }
	}
	
</style>
<title>증명서출력화면</title>
</head>
<%@include file="/WEB-INF/views/menu/header.jsp" %>
<body id="kt_app_body" data-kt-app-layout="dark-sidebar"
		data-kt-app-header-fixed="true" data-kt-app-sidebar-enabled="true"
		data-kt-app-sidebar-fixed="true" data-kt-app-sidebar-hoverable="true"
		data-kt-app-sidebar-push-header="true"
		data-kt-app-sidebar-push-toolbar="true"
		data-kt-app-sidebar-push-footer="true"
		data-kt-app-toolbar-enabled="true" class="app-default">

		<div class="app-wrapper flex-column flex-row-fluid">
			<div class="app-toolbar py-3 py-lg-6">
				<div id="kt_app_toolbar_container" class="app-container container-fluid d-flex flex-stack">
					<!--begin::Page title-->
					<div class="page-title d-flex flex-column justify-content-center flex-wrap me-3">
						<!--begin::Title-->
						<h1 class="page-heading d-flex text-gray-900 fw-bold fs-3 flex-column justify-content-center my-0">제목이 여기에 들어가요!</h1>
						<!--end::Title-->
					</div>
					<!--end::Page title-->
				</div>		
			</div>
			<div class="app-content flex-column-fluid">
				<!-- 내용 시작 -->
				<div id="kt_app_content" class="app-content flex-column-fluid">
					<div class="app-container container-fluid">
						<div class="card card-flush h-md-50 mb-xl-10">
							<div class="card-header pt-5">
								<h3 class="card-title text-gray-800 fw-bold">소제목? 들어갑니다</h3>
							</div>
							<div class="separator separator-dashed my-3"></div>	
							<div class="card-body pt-5" >
								<input id="docTitle" type="hidden" value="${boxDto.getSidb_doc_title()}">
								<div id="savePdfZone" class="saveZomeCss">
									${boxDto}<br>
									${boxDto.getSidb_doc_num()}<br>
									${boxDto.getSidb_doc_title()}<br>
									${boxDto.getSidt_temp_name()}<br>
									${boxDto.getSidb_doc_apprdt()}<br>
								</div>
								<div>
									<button id="savePdf" class="btn btn-primary btnLg me-10">PDF저장하기</button>
									<button id="printPdf" class="btn btn-primary btnLg me-10">프린트하기</button>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- 내용 끝 -->
			</div>
		</div>
			
<%@include file="/WEB-INF/views/menu/hrSideMenu.jsp" %>		
</body>
</html>