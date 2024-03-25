/* 임직원 검색 (xVal:x좌표 이동값,yVal:y좌표 이동값) */
function empInfoLayer(empl_id, element, xVal, yVal){
	if(typeof xVal == 'undefined'){
		xVal = 0;
	}
	if(typeof yVal == 'undefined'){
		yVal = 0;
	}
	
	fetch('/hr/employee/selectEmployeeOne.do?empl_id='+empl_id, {
		method: 'GET',
	})
	.then(resp=>{
		if (!resp.ok){
		      throw new Error(resp)
		}
		return resp.json();
	})
	.then(data => {
		var htmlBody = "";	
		htmlBody += "<div class='symbol symbol-50px me-5'>";
		htmlBody += "	<img alt='Logo' src='"+ data.empl_picture_str +"' />";
		htmlBody += "</div>";
		htmlBody += "<div class='d-flex flex-column'>";
		htmlBody += "	<div class='fw-bold d-flex align-items-center fs-5'>"+data.empl_name;
		htmlBody += "		<span class='badge badge-light-success fw-bold fs-8 px-2 py-1 ms-2'>"+data.empl_rank_nm+"</span>";
		htmlBody += "	</div>";
		htmlBody += "	<div class='fw-semibold text-muted fs-7'>"+data.empl_dept_nm+"</div>";
		htmlBody += "	<a href='mailto:${userInfoVo.empl_email}' class='fw-semibold text-muted text-hover-primary fs-7'>"+data.empl_email+"</a>";
		htmlBody += "</div>";

		var empInfoId = document.querySelector('#empInfoLayer');
		var empInfoElement = document.querySelector('.emp-info-layer');
		// 선택한 요소의 innerHTML 속성을 설정하여 내용 추가
		empInfoElement.innerHTML = htmlBody;
		empInfoId.style.top = (element.offsetTop+90)+"px";
		empInfoId.style.left = (element.offsetLeft+350+xVal)+"px";
		empInfoId.style.display='block';
	})
	.catch((error)=>{
		console.log("에러", error);
	})
}

