<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>템플릿 전체 리스트 조회</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<!-- SIDT_TEMP_CD, SICA_CD, SIDT_TEMP_NAME, SIDT_TEMP_CREATEDT, SIDT_TEMP_MODIFYDT, SIDT_TEMP_FLAG -->

	<div class="container"  style="margin: 200px auto; width: 800px;">
	<div>
  		<button class="writeTemplate">템플릿 등록</button>
  	</div>
	<table class="table table-hover">
	    <thead>
	      <tr>
	        <th>템플릿코드</th>
	        <th>템플릿명</th>
	        <th>활성여부</th>
	      </tr>
	    </thead>
	    <tbody>
	    <c:forEach var="lst" items="${lst}" varStatus="vs">
	      <tr>
	        <td>${lst.sidt_temp_cd}</td>
	        <td><a href="./detailTemplate.do?sidt_temp_cd=${lst.sidt_temp_cd}">${lst.sidt_temp_name}</a></td>
	        <td>${lst.sidt_temp_flag}</td>
	      </tr>
	     </c:forEach>
  	</table>
  	</div>
<script type="text/javascript">
	var writebtn = document.querySelector('.writeTemplate');
	writebtn.addEventListener("click", function(){
		window.location.href = "./writeTemplate.do";
	});
</script>
</body>
</html>