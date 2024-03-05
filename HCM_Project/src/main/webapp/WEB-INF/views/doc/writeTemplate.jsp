<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>템플릿 작성</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>

<body>
	<div id="container">
		<h2>템플릿 등록 페이지</h2>
		<button id="getTemplate">템플릿 가져오기</button>
		<form action="./insertTemp.do" method="post">
			<h3>
				제목 <input type="text" name="title">
			</h3>
			<textarea id="editor" name="content"></textarea>
		</form>
	</div>
	<div class="btn">
		<input type="button" onclick="writeBoard()" value="글쓰기">
	</div>
	<div class="btn">
		<input type="button" onclick="location.href='./boardList.do'" value="목록보기">
	</div>
</body>

<script type="text/javascript" src="../ckeditor5/build/ckeditor.js"></script>
<script type="module" src="../ckeditor5/sample/script.js"></script>
</html>