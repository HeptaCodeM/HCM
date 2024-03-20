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
<style type="text/css">
 /* 화면늘리는 버튼 숨기기 */
    textarea.comment_inbox_text {
        resize: none; /* 사용자가 텍스트 영역 크기를 조절할 수 없도록 함 */
        min-height: 38px; /* 최소 높이 지정 */
    }
   .comment_info_button{
   text-decoration: none;
   color: gray;
   }
   ul {
    list-style-type: none; /* 리스트 스타일을 없앰 */
}


  /* 화면늘리는 버튼 숨기기 */
    textarea.comment_inbox_text {
        resize: none; /* 사용자가 텍스트 영역 크기를 조절할 수 없도록 함 */
        min-height: 38px; /* 최소 높이 지정 */
    }
    /* 댓글 사이에 수평선 추가 */
    .list-group-item + .list-group-item {
        border-top: 1px solid #dee2e6; /* 수평선 스타일 지정 */
        padding-top: 10px; /* 상단 패딩 추가 */
    }
</style>
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
                <div class="page-title d-flex flex-column justify-content-center flex-wrap me-3">
                    <h1 class="page-heading d-flex text-gray-900 fw-bold fs-3 flex-column justify-content-center my-0">${dto.gobo_title}</h1>
                </div>
            </div>      
        </div>
        <div class="app-content flex-column-fluid">
            <!-- 내용 시작 -->
            <div id="kt_app_content" class="app-content flex-column-fluid">
                <div class="app-container container-fluid">
                    <div class="card card-flush h-md-50 mb-xl-10">
                        <div class="card-body pt-5">
                            <!-- 공지사항 상세 내용 표시 -->
                            <p>작성자: ${dto.gobo_writer}</p>
                            <p>작성일: <fmt:formatDate value="${dto.gobo_regdate}" pattern="yyyy-MM-dd HH:mm"/></p>
                            <p  style="font-size: 20px;">${dto.gobo_content}</p>
                            <!-- 댓글 표시 -->
                            <h3>댓글</h3>
                          <ul class="list-group" id="commentList">
                      <c:forEach var="comment" items="${Rlist}">
                      	  <c:if test="${comment.rebo_depth == 0}">
                          <li class="list-group-item">
                              <div class="d-flex align-items-center">
                                  <img src="https://ssl.pstatic.net/static/cafe/cafe_pc/default/cafe_profile_77.png?type=c77_77" alt="프로필 사진" width="36" height="36" class="mr-3">
                                  <div>
                                      <strong>${comment.rebo_writer}</strong><br>
                                      <div>${comment.rebo_content}</div>
                                      <div><small><fmt:formatDate value="${comment.rebo_regdate}" pattern="yyyy-MM-dd HH:mm"/></small>
                                      <a role="button" class="comment_info_button" onclick="showCommentForm(${comment.rebo_no})">답글쓰기</a>
                                      </div>
                                  </div>
                              </div>
                          </li>
                           <c:forEach var="reply" items="${Rlist}" varStatus="replyStatus">
            <!-- 현재 댓글과 depth가 1이고, rebo_parent_no가 현재 댓글의 rebo_no와 같은 경우 -->
				            <c:if test="${reply.rebo_depth == 1 && reply.rebo_step == comment.rebo_step}">
				                <li class="list-group-item" style="margin-left: 10px;">
				                    <!-- 대댓글 내용 출력 -->
				                    <div class="d-flex align-items-center">
				                        <img src="https://ssl.pstatic.net/static/cafe/cafe_pc/default/cafe_profile_77.png?type=c77_77" alt="프로필 사진" width="36" height="36" class="mr-3">
				                        <div>
				                            <strong>${reply.rebo_writer}</strong><br>
				                            <div>${reply.rebo_content}</div>
				                            <div><small><fmt:formatDate value="${reply.rebo_regdate}" pattern="yyyy-MM-dd HH:mm"/></small>
				                            <a role="button" class="comment_info_button" onclick="showCommentForm(${comment.rebo_no})">답글쓰기</a>
				                            </div>
				                        </div>
				                    </div>
				                </li>
				            </c:if>
				        </c:forEach>
                          <li  id="commentFormContainer${comment.rebo_no}" style="display: none;">
                             <div>
                                <div class="CommentWriter mb-4">
                                    <form id="ReplyTwoForm">
                                        <input type="hidden" name="rebo_step" id="rebo_step" value="${comment.rebo_step}">
                                        <input type="hidden" name="rebo_writer_id" id="rebo_writer_id" value="${sessionScope.userInfoVo.empl_id}">
                             <input type="hidden" name="rebo_modify_id" id="rebo_modify_id" value="${sessionScope.userInfoVo.empl_id}">
                             <input type="hidden" name="rebo_writer" id="rebo_writer" value="${sessionScope.userInfoVo.empl_name}">
                                        <div class="comment_inbox border border-2">
                                            <em class="comment_inbox_name">${sessionScope.userInfoVo.empl_name}</em>
                                            <textarea id="commentTextArea" placeholder="댓글을 남겨보세요" rows="2" class="comment_inbox_text form-control border-0" name="rebo_content"></textarea>
                                            <div class="d-flex justify-content-end align-items-end">
                                                <div class="register_box">
                                                    <button type="button" class="btn btn-primary" onclick="cancelReply(${comment.rebo_no})">취소</button>
                                                    <button type="button" class="btn btn-primary" onclick="insertReplyTwo(${comment.gobo_no})">등록</button>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                             </div>
                          </li>
                          </c:if>
                      </c:forEach>
                  </ul>
                  
                  
                            <!-- 댓글 작성 폼 -->
                       <div class="CommentWriter mb-4" style="margin-top: 50px ">
                             <form id="ReplyForm">
                             <div class="comment_inbox border border-2">
                             <input type="hidden" name="rebo_writer_id" id="rebo_writer_id" value="${sessionScope.userInfoVo.empl_id}">
                             <input type="hidden" name="rebo_modify_id" id="rebo_modify_id" value="${sessionScope.userInfoVo.empl_id}">
                             <input type="hidden" name="rebo_writer" id="rebo_writer" value="${sessionScope.userInfoVo.empl_name}">
                                 <em class="comment_inbox_name">${sessionScope.userInfoVo.empl_name}</em>
                                 <textarea id="commentTextArea" placeholder="댓글을 남겨보세요" rows="2" class="comment_inbox_text form-control border-0" oninput="checkInput()" name="rebo_content"></textarea>
                            <div class="d-flex justify-content-end align-items-end">
                             <div class="register_box">
                                 <button id="submitButton" type="button" class="btn btn-primary" onclick="insertReply(${dto.gobo_no})" disabled>등록</button>

                             </div>
                             </div>
                             </div>
                             </form>
                       </div>
                     </div>
                     	<div style="display: flex; justify-content: flex-end;">
						<button type="button" class="btn btn-primary" onclick="location.href='/sm/getAllGobo.do'" style="margin-right: 20px">목록</button>                     	
                     	<button type="button" class="btn btn-primary" onclick="scrollToTop()" id="scrollTopButton" title="맨 위로 이동" style="margin-right: 10px">TOP</button>
                     	</div>
                        </div>
                    </div>
                </div>
            </div>
         
<%@include file="/WEB-INF/views/menu/smSideMenu.jsp" %>   
<script type="text/javascript" src="/js/sm/Gobo.js"></script>   
</body>

</html>