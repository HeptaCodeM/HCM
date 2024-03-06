<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>템플릿 작성</title>
<link rel="stylesheet" href="../css/template.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

</head>

<body>
	<div id="container">
		<h1>템플릿 등록</h1>
		<button id="getTemplate">[TEST] 템플릿 가져오기</button>
		<form action="./insertTemplate.do" method="post">
		<div id="category">
			결재 구분 : <select id="selectCategory" name="sica_cd"></select>
		</div>
			<h3>제목 <input type="text" name="sidt_temp_name"></h3>
			<textarea id="editor" name="sidt_temp_content"></textarea>
		</form>	
		<div class="btn">
			<input type="button" onclick="writeTemplate()" value="등록하기">
			<input type="button" onclick="location.href='./template.do'" value="목록보기">
		</div>	
	</div>

</body>

<script type="text/javascript" src="../ckeditor5/build/ckeditor.js"></script>
<script type="module" src="../ckeditor5/sample/script.js"></script>
<script type="text/javascript" src="../js/template.js"></script>
</html>