<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    
<%@ taglib  uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<title>Insert title here</title>
<style>
  .container {
    width: 80%;
    margin: 0 auto;
  }
   .footer {
    border: 1px solid #000;
    margin-top: 20px;
    padding: 10px;
  }
  table {
    width: 100%;
    border-collapse: collapse;
  }
  th, td {
    border: 1px solid #000;
    padding: 5px;
    text-align: left;
  }
  
  .header {
 float: right;
  top: 10px; /* 원하는 위치 조정 */
  right: 10px; /* 원하는 위치 조정 */
  width: 30%; /* 크기 조정 */
}
  
  .content{
  width: 100%;
  }
</style>


</head>
<body>
<div class="container">
  <div class="header">
    <div ></div>
   <table>
    <tr>
      <th rowspan="3">결재</th>
      <th>기안자</th>
      <th>팀장</th>
      <th>대리</th>
    </tr>
    <tr>
      <td>ㅇ</td>
      <td>ㅇ</td>
      <td>ㅇ</td>
    </tr>
    <tr>
      <td>2024/03/04</td>
      <td>ㅇ</td>
      <td>ㅇ</td>
    </tr>
  </table>
  </div>
  </div>
<div class="container">
    <button>pdf로 보기</button>
  ${boardDto.sidb_doc_num}
    <p>결재문서기한: ${boardDto.sidb_doc_expiredt}</p>
  <div class="content">
    <textarea class="content">내용   ${boardDto.sidb_doc_content}</textarea>
  </div>
  <div class="footer">
    <div> 첨언내역</div>
    <table>
      <tr>
        <th>작성자</th>
        <td>내용</td>
      </tr>
      <tr>
        <td></td>
        <td>${boardDto.sidb_doc_json}</td>
      </tr>
    </table>
  </div>
    <button>승인</button>
    <button>반려</button>
</div>
</body>

</html>