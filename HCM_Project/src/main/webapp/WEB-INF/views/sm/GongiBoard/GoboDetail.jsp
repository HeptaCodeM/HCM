<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/menu/headerInfo.jsp" %>
<title>SM메인화면</title>
</head>
<%@include file="/WEB-INF/views/menu/header.jsp" %>
<body id="kt_app_body" data-kt-app-layout="dark-sidebar"
		data-kt-app-header-fixed="true" data-kt-app-sidebar-enabled="true"
		data-kt-app-sidebar-fixed="true" data-kt-app-sidebar-hoverable="true"
		data-kt-app-sidebar-push-header="true"
		data-kt-app-sidebar-push-toolbar="true"
		data-kt-app-sidebar-push-footer="true"
		data-kt-app-toolbar-enabled="true" class="app-default">

	    <div class="app-wrapper flex-column flex-row-fluid">
        <div class="app-toolbar py-3 py-lg-6">
            <div id="kt_app_toolbar_container" class="app-container container-fluid d-flex flex-stack">
                <!--begin::Page title-->
                <div class="page-title d-flex flex-column justify-content-center flex-wrap me-3">
                    <!--begin::Title-->
                    <h1 class="page-heading d-flex text-gray-900 fw-bold fs-3 flex-column justify-content-center my-0">공지사항 상세보기</h1>
                    <!--end::Title-->
                </div>
                <!--end::Page title-->
            </div>      
        </div>
        <div class="app-content flex-column-fluid">
            <!-- 내용 시작 -->
            <div id="kt_app_content" class="app-content flex-column-fluid">
                <div class="app-container container-fluid">
                    <div class="card card-flush h-md-50 mb-xl-10">
                        <div class="card-body pt-5">
                            <!-- 공지사항 상세 내용 표시 -->
                            <h2>${dto.gobo_title}</h2>
                            <p>작성자: ${dto.gobo_writer}</p>
                            <p>작성일: <fmt:formatDate value="${dto.gobo_regdate}" pattern="yyyy-MM-dd"/></p>
                            <p  style="font-size: 30px;">${dto.gobo_content}</p>

                            <!-- 댓글 표시 -->
                            <h3>댓글</h3>
                          <ul class="list-group" id="commentList">
						    <c:forEach var="comment" items="${Rlist}">
						        <li class="list-group-item">
						            <div class="d-flex align-items-center">
						                <img src="https://ssl.pstatic.net/static/cafe/cafe_pc/default/cafe_profile_77.png?type=c77_77" alt="프로필 사진" width="36" height="36" class="mr-3">
						                <div>
						                    <strong>${comment.rebo_writer}</strong><br>
						                    <div>${comment.rebo_content}</div>
						                    <div><small><fmt:formatDate value="${comment.rebo_regdate}" pattern="yyyy-MM-dd"/></small></div>
						                </div>
						            </div>
						        </li>
						    </c:forEach>
						</ul>
                            <!-- 댓글 작성 폼 -->
                           <form id="commentForm" method="post">
						    <div class="CommentWriter">
						        <div class="comment_inbox">
						            <textarea placeholder="댓글을 남겨보세요" rows="3" class="comment_inbox_text form-control" style="overflow: hidden; overflow-wrap: break-word;"></textarea>
						        </div>
						        <div class="comment_attach">
						            <div class="register_box">
						                <button type="button" class="btn btn-primary" onclick="insertComment(${dto.gobo_no})">등록</button>
						            </div>
						        </div>
						    </div>
						</form>
                        </div>
                    </div>
                </div>
            </div>
			
<%@include file="/WEB-INF/views/menu/smSideMenu.jsp" %>		
</body>
</html>