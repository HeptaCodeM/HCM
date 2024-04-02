<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hello World!!!</title>
</head>
<body>
	<div>
		Hello World!!!<br>
		로그인 아이디(security Id) : ${getId} (/index.do로 접근)<br><br>
		Session : ${sessionScope.userInfoVo}<br><hr><br>
		getUserInfo(model session) : ${empl_info}<hr><br>
	</div>
	<div>

		<a href="/mainTmp.do">메인템플릿</a><br>
		<hr>
		<a href="/login/login.do">login</a><br>
		<hr>
		<a href="/sm/getAllGobo.do">SM넘어가기</a>
		<hr>
		<a href="/doc/getDetail.do">결재문서 상세페이지</a>
		<hr>
		<a href="/sendMailTest.do">메일발송 테스트</a>
		<hr>
		<a href="/holidayTest.do">휴가정보 조회 테스트</a>
		<hr>
		<a href="/notification.do">Notification 테스트</a>
		<hr>
		<a href="/doc/noticeAlarmTest.do">Alarm 테스트</a>
		<hr>
		<a href="/hr/employee/selectEmployeeOne.do?empl_id=20220101">사원정보 요청</a>
		<br>
		<link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&family=Jua&family=Nanum+Pen+Script&display=swap" rel="stylesheet">
		<img src="/image/logo-removebg-preview.png" style="height:150px">
		<div style="color:#F5A315;color: #F5A315; font-size: 50px; font-weight: 400; letter-spacing: 4px; font-family: 'Jua','Google Sans','Roboto'">HCM</div>
	</div>
</body>
</html>