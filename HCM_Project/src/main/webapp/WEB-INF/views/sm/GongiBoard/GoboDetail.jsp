<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 상세 화면</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>






  
 <div class="container">
    <h2>공지사항 게시판</h2>
    <form id="updateForm">
        <table class="table">
            <input type="hidden" name="gobo_modify_id" value="${dto.gobo_writer_id}">
            <input id="gobo_no" type="hidden" name="gobo_no" value="${dto.gobo_no}">
            <thead>
                <tr>
                    <th>번호</th>
                    <th>조회수</th>
                    <th>제목</th>
                    <th>내용</th>
                    <th>작성일</th>
                    <th>비고</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>${dto.gobo_no}</td>
                    <td>${dto.gobo_view}</td>
                    <td><input type="text" name="gobo_title" value="${dto.gobo_title}"></td>
                    <td><input type="text" name="gobo_content" value="${dto.gobo_content}"></td>
                    <td>${dto.gobo_regdate}</td>
                    <td><input type="text" name="gobo_bigo" value="${dto.gobo_bigo}"></td>
                </tr>
                
            </tbody>
        </table>
    </form>
        <button class="btn btn-info" onclick="location.href='/sm/getAllGobo.do'">목록</button>
        <button class="btn btn-success" type="button" onclick="updateGobo()">수정</button>
        <button class="btn btn-danger" onclick="updateGoboDelFlag(${dto.gobo_no})">삭제</button>
</div>
    
  
  
</body>

<script type="text/javascript">
function updateGobo() {
    $.ajax({
        url: "/sm/updateGobo.do",
        type: "POST",
        data: $("#updateForm").serialize(),
        success: function(response) {
        	alert("수정완료")
        	
        },
        error: function(error) {
            alert("오류")
        }
    });
}

function updateGoboDelFlag(e) {
    var result = confirm("정말 삭제 하시겠습니까?");
    console.log(e);
    if (result) {
        // 확인 버튼을 눌렀을 때의 동작
        alert("삭제 되었습니다.")
    	location.href='/sm/updateGoboDelFlag.do?gobo_no='+e;
    } else {
        // 취소 버튼을 눌렀을 때의 동작
        // 아무 동작 없음
    }
}



</script>
</html>