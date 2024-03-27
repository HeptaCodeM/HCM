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
     .no-border {
        border: none; /* 테두리를 없애는 스타일 */
        padding: 0; /* 선택 사항: 있으면 패딩을 제거합니다. */
        margin: 0; /* 선택 사항: 있으면 여백을 제거합니다. */
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
			<div id="kt_app_toolbar_container"
				class="app-container container-fluid d-flex flex-stack justify-content-between">
				<div
					class="page-title d-flex flex-column justify-content-center flex-wrap">
					<h1
						class="page-heading d-flex text-gray-900 fw-bold fs-3 flex-column justify-content-center my-0">${dto.gobo_title}</h1>
				</div>
				
				<div>
					<button type="button" class="btn btn-primary"
						onclick="location.href='/sm/updateGoboMove.do?gobo_no=${dto.gobo_no}'"
						style="margin-right: 20px">수정</button>
					<button type="button" class="btn btn-primary"
						onclick="updateGoboDelFlag(${dto.gobo_no})"
						style="margin-right: 20px">삭제</button>
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
                          <li id="firstReply">
                              <div class="d-flex align-items-center no-border" style="border: none;">
							        <img src="${sessionScope.userInfoVo.empl_picture_str}" alt="프로필 사진" width="36" height="36" class="mr-3">
                                  <div style="margin-left: 10px; position: relative; width: 100%;">
								    <strong>${comment.rebo_writer}</strong><br>
								    <div>${comment.rebo_content}</div>
								    <div>
								        <small><fmt:formatDate value="${comment.rebo_regdate}" pattern="yyyy-MM-dd HH:mm"/></small>
								        <div style="position: absolute; top: 0; right: 0; float: right">
								            <a role="button" title="더보기" class="comment_tool" href="#" id="comment_tool${comment.rebo_no}" onclick="toggleMenu(event, ${comment.rebo_no})">
								                <img alt="더보기" src="https://cdn4.iconfinder.com/data/icons/liny/24/more-menu-vertical-line-64.png" style="width: 30px;">
								            </a>
								        </div>
								    </div>
								    <a role="button" class="comment_info_button" onclick="showCommentForm(${comment.rebo_no})">답글쓰기</a>
								</div>
							</div>
                          </li>
                          <div class="separator border-2 separator-dashed my-5 hrline"></div>
                           <li  id="commentFormContainer${comment.rebo_no}" style="display: none;">
                             <div>
                                <div class="CommentWriter mb-4">
                                    <form id="ReplyTwoForm">
                                        <input type="hidden" name="rebo_step" id="rebo_step" value="${comment.rebo_step}">
                                        <input type="hidden" name="rebo_writer_id" id="rebo_writer_id" value="${sessionScope.userInfoVo.empl_id}">
                             <input type="hidden" name="rebo_modify_id" id="rebo_modify_id" value="${sessionScope.userInfoVo.empl_id}">
                             <input type="hidden" name="rebo_writer" id="rebo_writer" value="${sessionScope.userInfoVo.empl_name}">
                             <input type="hidden" name="gobo_no" id="gobo_no" value="${comment.gobo_no}">
                                        <div class="comment_inbox border border-2">
                                            <em class="comment_inbox_name">${sessionScope.userInfoVo.empl_name}</em>
                                            <textarea id="commentTextArea${comment.rebo_no}" placeholder="댓글을 남겨보세요" rows="2" class="comment_inbox_text form-control border-0" name="rebo_content"></textarea>
                                            <div class="d-flex justify-content-end align-items-end">
                                                <div class="register_box">
                                                    <button type="button" class="btn btn-primary" onclick="cancelReply(${comment.rebo_no})">취소</button>
                                                    <button type="button" class="btn btn-primary insertReplyTwoBtn" onclick="insertReplyTwo(${comment.rebo_no})">등록</button>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                             </div>
                          </li>
                           <c:forEach var="reply" items="${Dlist}" varStatus="replyStatus">
            <!-- 현재 댓글과 depth가 1이고, rebo_parent_no가 현재 댓글의 rebo_no와 같은 경우 -->
				            <c:if test="${comment.rebo_step == reply.rebo_step}">
				                <li id="secondReply" style="margin-left: 50px;">
				                    <!-- 대댓글 내용 출력 -->
				                    <div class="d-flex align-items-center no-border" style="border: none;">
				                         <c:choose>
							    <c:when test="${sessionScope.userInfoVo.empl_picture_str != null}">
							        <img src="${sessionScope.userInfoVo.empl_picture_str}" alt="프로필 사진" width="36" height="36" class="mr-3">
							    </c:when>
							    <c:otherwise>
							        <img src="https://ssl.pstatic.net/static/cafe/cafe_pc/default/cafe_profile_77.png?type=c77_77" alt="프로필 사진" width="36" height="36" class="mr-3">
							    </c:otherwise>
								</c:choose>
				                        <div style="margin-left: 10px; position: relative; width: 100%;">
				                            <strong>${reply.rebo_writer}</strong><br>
				                            <div>${reply.rebo_content}</div>
				                            <div><small><fmt:formatDate value="${reply.rebo_regdate}" pattern="yyyy-MM-dd HH:mm"/></small>
				                            <div style="position: absolute; top: 0; right: 0; float: right">
				                            	<a role="button" title="더보기" class="comment_tool" href="#" id="comment_tool${reply.rebo_no}" onclick="toggleMenu(event, ${reply.rebo_no})">
				                            	 <img alt="더보기" src="https://cdn4.iconfinder.com/data/icons/liny/24/more-menu-vertical-line-64.png" style="width: 30px;">
												</a>
				                            </div>
				                            <a role="button" class="comment_info_button" onclick="showCommentForm(${reply.rebo_no})">답글쓰기</a>
				                            </div>
				                        </div>
				                    </div>
				                </li>
				                <div class="separator border-2 separator-dashed my-5 hrline" style="margin-left: 50px;"></div>
				            <li  id="commentFormContainer${reply.rebo_no}" style="display: none;">
                             <div>
                                <div class="CommentWriter mb-4">
                                    <form id="ReplyTwoForm">
                                        <input type="hidden" name="rebo_step" id="rebo_step" value="${reply.rebo_step}">
                                        <input type="hidden" name="rebo_writer_id" id="rebo_writer_id" value="${sessionScope.userInfoVo.empl_id}">
                             <input type="hidden" name="rebo_modify_id" id="rebo_modify_id" value="${sessionScope.userInfoVo.empl_id}">
                             <input type="hidden" name="rebo_writer" id="rebo_writer" value="${sessionScope.userInfoVo.empl_name}">
                             <input type="hidden" name="gobo_no" id="gobo_no" value="${comment.gobo_no}">
                                        <div class="comment_inbox border border-2">
                                            <em class="comment_inbox_name">${sessionScope.userInfoVo.empl_name}</em>
                                            <textarea id="commentTextArea${reply.rebo_no}" placeholder="댓글을 남겨보세요" rows="2" class="comment_inbox_text form-control border-0" name="rebo_content"></textarea>
                                            <div class="d-flex justify-content-end align-items-end">
                                                <div class="register_box">
                                                    <button type="button" class="btn btn-primary" onclick="cancelReply(${reply.rebo_no})">취소</button>
                                                    <button type="button" class="btn btn-primary insertReplyTwoBtn" onclick="insertReplyTwo(${reply.rebo_no})">등록</button>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                             </div>
				            </c:if>
				        </c:forEach>
                         
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
                                 <textarea id="rebo_content" placeholder="댓글을 남겨보세요" rows="2" class="comment_inbox_text form-control border-0" oninput="checkInput()" name="rebo_content"></textarea>
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
         
         
         <div data-v-0330f652="" id="commentItem" role="menu" class="LayerMore" style="display: none;">
         <ul class="layer_list">
         	<li class="layer_item">
         		<a href="#" role="button" class="update_button" onclick="updateButton()" style="text-decoration: none;"> 수정 </a>
         		</li>
         	<li class="layer_item">
         	<a href="#" role="button" class="delete_button" onclick="deleteButoon()" style="text-decoration: none;"> 삭제 </a>
         	</li>
         	</ul>
         	</div>
         
         
         
<%@include file="/WEB-INF/views/menu/smSideMenu.jsp" %>   
<script type="text/javascript" src="/js/sm/Gobo.js"></script>  
<script type="text/javascript">
document.addEventListener('DOMContentLoaded', function () {
    // Enter 키를 눌렀을 때 이벤트 처리
    document.querySelectorAll('.comment_inbox_text').forEach(function (textarea) {
        textarea.addEventListener('keypress', function (e) {
            var key = e.which || e.keyCode;
            if (key === 13 && !e.shiftKey) { // Enter 키이면서 Shift 키가 눌리지 않은 경우
                e.preventDefault(); // 기본 동작 (엔터 키 입력) 막기
                var form = this.closest('form'); // 해당 텍스트 영역이 속한 폼 요소 선택
                form.querySelector('.insertReplyTwoBtn').click(); // 등록 버튼 클릭
            }
        });
    });
});





document.addEventListener('DOMContentLoaded', function () {
    // Enter 키를 눌렀을 때 이벤트 처리
    document.getElementById('rebo_content').addEventListener('keypress', function (e) {
        var key = e.which || e.keyCode;
        if (key === 13 && !e.shiftKey) { // Enter 키이면서 Shift 키가 눌리지 않은 경우
            e.preventDefault(); // 기본 동작 (엔터 키 입력) 막기
            document.getElementById('submitButton').click(); // 등록 버튼 클릭
        }
    });
});

//마지막 hrline 요소를 가져옵니다.
var hrlineElement = document.querySelector('#commentList .hrline:last-of-type');

// 만약 hrline 요소가 존재하면 삭제합니다.
if (hrlineElement !== null) {
    hrlineElement.parentNode.removeChild(hrlineElement);
}




function insertReplyTwo(rebo_no) {
    var container = $("#commentFormContainer" + rebo_no); // #commentFormContainer 요소를 선택합니다.
    var formData = container.find("#ReplyTwoForm").serialize(); // 해당 요소 안의 form 데이터를 가져옵니다.
    var rebo_writer = $("#ReplyTwoForm #rebo_writer").val();
    var rebo_content = $("#commentTextArea" + rebo_no).val();

    $.ajax({
        url: "/sm/insertReplyTwo.do",
        data: formData,
        type: "GET",
        dataType: "json",
        success: function () {
            var currentDate = new Date();
            var formattedDate = currentDate.getFullYear() + '-' +
                ('0' + (currentDate.getMonth() + 1)).slice(-2) + '-' +
                ('0' + currentDate.getDate()).slice(-2) + ' ' +
                ('0' + currentDate.getHours()).slice(-2) + ':' +
                ('0' + currentDate.getMinutes()).slice(-2);

            var commentHtml = ''; // commentHtml 변수 초기화
            
            if ($("#commentList > li").length > 0) {
                // 상위에 형제 노드가 있는 경우
                commentHtml += '<div class="separator border-2 separator-dashed my-5 hrline" style="margin-left: 50px;"></div>';
            }

            // secondReply가 있는 경우
            commentHtml += '<li style="margin-left: 50px;">';
            commentHtml += '<div class="d-flex align-items-center no-border" style="border: none;">';
            commentHtml += '<img src="${sessionScope.userInfoVo.empl_picture_str}" alt="프로필 사진" width="36" height="36" class="mr-3">';
            commentHtml += '<div style="margin-left: 10px;">';
            commentHtml += '<strong>' + rebo_writer + '</strong><br>';
            commentHtml += '<div>' + rebo_content + '</div>';
            commentHtml += '<div><small>' + formattedDate + '</small>';
            commentHtml += '<a role="button" class="comment_info_button" onclick="showCommentForm()">답글쓰기</a>';
            commentHtml += '</div>';
            commentHtml += '</div>';
            commentHtml += '</div>';
            commentHtml += '</li>';

            // container가 포함된 ul 또는 ol 요소를 찾아서 새로운 li를 추가
            container.closest('ul, ol').append(commentHtml);
            
            hideAllCommentForms();
        },
        error: function () {
            // 에러 처리
        }
    });
}





</script> 


</body>

</html>