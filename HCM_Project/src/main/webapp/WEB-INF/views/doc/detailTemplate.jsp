<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>템플릿 상세조회</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	
	<div class="container" style="margin: 200px auto;">
	<h2 style="text-align: center;">${temDto.sidt_temp_name}</h2>
	${temDto.sidt_temp_content}
	
	<div style="text-align: center;">
		<button class="modifyTemplate">수정하기</button>
		<button class="deleteTemplate">삭제하기</button>
	</div>
	</div>
	
<script type="text/javascript">
	/* table 위치 설정 */
	var table = document.querySelectorAll('table');
	for(let t of table) {
		t.setAttribute('style', 'margin:20px auto;')
	}

	/* 수정하기 */
	var modifybtn = document.querySelector('.modifyTemplate');
	modifybtn.addEventListener("click", function(){
		window.location.href = "./modifyTemplate.do?sidt_temp_cd=${temDto.sidt_temp_cd}";
	});
	
	/* 삭제하기 */
	var deletebtn = document.querySelector('.deleteTemplate');
	deletebtn.addEventListener("click", function(){
		var confirmation = confirm("해당 템플릿을 삭제하시겠습니까?");
		if(confirmation){
			window.location.href = "./deleteTemplate.do?sidt_temp_cd=${temDto.sidt_temp_cd}";
			alert("삭제되었습니다");
		} else {
			return;
		}		
	});
</script>
</body>
</html>