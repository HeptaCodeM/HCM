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
<style type="text/css">
	#container{
		width: 1000px;
		margin: 50px auto;
	}
	#container>h1, #container>h3{
		color: gray;
		margin: 0px;
		padding: 20px 0px;
	}
	h3>input {
		width: 100%;
		padding: 5px;
		border: 1px solid gray;
	}
	
	.ck-content {
		height: 600px;
	}
	
	.ck-editor__editable {
	    max-height: 1200px;
	}
	
	.ck-editor p {
	  margin-block-start: 0.5em;
	  margin-block-end: 0.5em;
	}
	
	.ck-content p {
	  margin-block-start: 0.5em;
	  margin-block-end: 0.5em;
	}
</style>

</head>

<body>
	<div id="container">
		<h1>템플릿 등록 페이지</h1>
		<button id="getTemplate">템플릿 가져오기</button>
		<form action="./insertTemp.do" method="post">
			<h3>
				제목 <input type="text" name="title">
			</h3>
			<textarea id="editor" name="content"></textarea>
		</form>	
		<div class="btn">
			<input type="button" onclick="writeBoard()" value="게시하기">
			<input type="button" onclick="location.href='./boardList.do'" value="목록보기">
		</div>	
	</div>

</body>

<script type="text/javascript" src="../ckeditor5/build/ckeditor.js"></script>
<script type="module" src="../ckeditor5/sample/script.js"></script>
</html>