<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style type="text/css">
 a{
 text-decoration: none;
 }
</style>
</head>
<body>

		<div class="container">
		 <h2>공지사항 게시판</h2>
		 <button btn btn-info onclick="location.href='/sm/insertGoboForm.do'">글작성</button>
		 	<table class="table">
		 		<thead>
		 			<tr>
		 				<th>번호</th>
		 				<th>제목</th>
		 				<th>내용</th>
		 				<th>작성일</th>
		 			</tr>
		 		</thead>
		 		<tbody>
		 			<c:forEach var="vo" items="${lists}" varStatus="vs">
		 				<c:if test="${vo.gobo_delflag == 'N'}">
		 				<tr>
		 					<td>${vs.count}</td>
		 					<td><a href="/sm/getDetailGobo.do?gobo_no=${vo.gobo_no}">${vo.gobo_title}</a></td>
		 					<td>${vo.gobo_content}</td>
		 					<td>${vo.gobo_regdate}</td>
		 				</tr>
		 				</c:if>
		 			</c:forEach>
		 		</tbody>
		 	</table>
		<div>




	
</body>
</html>