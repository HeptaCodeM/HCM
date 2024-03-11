<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdn.datatables.net/1.13.7/css/jquery.dataTables.css" />	
<script src="https://cdn.datatables.net/1.13.7/js/jquery.dataTables.js"></script>
<title>Insert title here</title>
</head>
<body>

<table id="myTable" class="cell-border stripe hover">
    <thead>
        <tr style="background-color: skyblue;">
            <th>기안일자</th>
            <th>제목</th>
            <th>결재유형</th>
            <th>진행상태</th>
            <th>기안자</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="dto" items="${lists}">
            <tr>
                <td>${dto.sidb_doc_writedt}</td>
                <td>${dto.sidb_doc_title}</td>
                <td>템플릿코드조인 추가 예정</td>
                <td>진행상태 프로그래스바</td>
                <td>${dto.empl_name}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>





</body>

<script>
$(document).ready( function () {
	$('#myTable').DataTable({ 

	});
});

</script>

</html>