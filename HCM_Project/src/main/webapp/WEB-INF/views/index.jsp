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
		로그인 아이디 : ${getId} (/index.do로 접근)<br>
		Session : ${sessionScope.userInfoVo}
	</div>
	<div>

		<a href="./mainTmp.do">메인템플릿</a><br>
		<a href="./main.do">main1</a><br>
		<a href="./error403.do">403ERROR</a><br>
		<a href="./error404.do">404ERROR</a><br>
		<a href="./error500.do">500ERROR</a><br>
		<a href="./login.do">login</a><br>
		<a href="/sm/getAllGobo.do">SM넘어가기</a>
		<hr>
		<a href="/doc/getDetail.do">결재문서 상세페이지</a>
		<hr>
		<a href="/sendMailTest.do">메일발송 테스트</a>
	</div>
</body>
</html>