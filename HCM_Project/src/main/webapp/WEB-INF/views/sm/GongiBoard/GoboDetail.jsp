<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/menu/headerInfo.jsp" %>

<title>HCM GroupWare</title>
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
				<c:if test="${sessionScope.userInfoVo.empl_auth == 'ROLE_SM_ADMIN' || sessionScope.userInfoVo.empl_auth == 'ROLE_SYS_ADMIN'}">
				<div>
					<button type="button" class="btn btn-primary"
						onclick="location.href='/sm/updateGoboMoveAdmin.do?gobo_no=${dto.gobo_no}'"
						style="margin-right: 20px">수정</button>
					<button type="button" class="btn btn-primary"
						onclick="updateGoboDelFlag(${dto.gobo_no})"
						style="margin-right: 20px">삭제</button>
				</div>
				</c:if>
			</div>
		</div>

		<div class="app-content flex-column-fluid">
            <!-- 내용 시작 -->
            <div id="kt_app_content" class="app-content flex-column-fluid">
                <div class="app-container container-fluid">
                    <div class="card card-flush h-md-50 mb-xl-10">
                        <div class="card-body pt-5">
                            <!-- 공지사항 상세 내용 표시 -->
                            <div style="display: flex; justify-content: space-between;"><p><strong>작성자: 관리자</strong></p><p>조회수:${dto.gobo_view }</p></div>
                            <p><fmt:formatDate value="${dto.gobo_regdate}" pattern="yyyy-MM-dd HH:mm"/></p>
                            <hr style="margin-bottom: 100px;">
                            <p  style="font-size: 20px;">${dto.gobo_content}</p>
                            <!-- 댓글 표시 -->  
                            <h3 style="margin-top: 100px">댓글</h3>
                          <ul class="list-group" id="commentList">
                      <c:forEach var="comment" items="${list}" varStatus="index">
                          <li id="firstReply${comment.rebo_no}">
                          <input type="hidden" name="rebo_no" id="rebo_no" value="${comment.rebo_no}">
                          <input type="hidden" name="rebo_no" id="rebo_no" value="${comment.rebo_no}">
                              <div class="d-flex align-items-center no-border replyName${comment.rebo_no}" style="border: none;">
							        <img src="data:image/png;base64,${comment.empl_picture_str}" alt="프로필 사진" width="36" height="36" class="mr-3">
                                  <div style="margin-left: 10px; position: relative; width: 100%;">
								    <strong id="rebo_writer${comment.rebo_no}">${comment.rebo_writer}</strong><br>
								    <div id="replycontent${comment.rebo_no}">${comment.rebo_content}</div>
								    <c:if test="${sessionScope.userInfoVo.empl_name == comment.rebo_writer}">
								    <div>
								        <small><fmt:formatDate value="${comment.rebo_regdate}" pattern="yyyy-MM-dd HH:mm"/></small>
								        <div style="position: absolute; top: -12px; right: 0; float: right">
								           	<div style="position: relative;">
				                            	<a role="button" title="더보기" class="comment_tool" href="#" id="comment_tool${comment.rebo_no}" onclick="toggleMenu(event, ${comment.rebo_no})">
				                            	 <img alt="더보기" src="https://cdn4.iconfinder.com/data/icons/liny/24/more-menu-vertical-line-64.png" style="height: 30px;">
												</a>
												</div>
								        </div>
								    </div>
								    </c:if>
								</div>
							</div>
                          </li>
                         <c:if test="${!index.last}">
					        <div class="separator border-2 separator-dashed my-5 hrline"></div>
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



function insertReply(gobo_no){
	 // 직렬화된 데이터를 가져옵니다.
		var form = $("#ReplyForm").serialize();
		var additionalData = "gobo_no=" + gobo_no;
		form += "&" + additionalData;
	
		// jQuery를 사용하여 폼 필드의 값을 가져옵니다.
		var rebo_writer = $("#ReplyForm #rebo_writer").val();
		var rebo_content = $("#ReplyForm #rebo_content").val();
		
		$.ajax({
				url: "/sm/insertReply.do",
				data: form,
				type: "get",
				dataType: "json",
				success: function(data) {
					console.log(data);
					 $("#ReplyForm")[0].reset();
					    var currentDate = new Date();
					    var formattedDate = currentDate.getFullYear() + '-' + 
					                        ('0' + (currentDate.getMonth() + 1)).slice(-2) + '-' + 
					                        ('0' + currentDate.getDate()).slice(-2) + ' ' + 
					                        ('0' + currentDate.getHours()).slice(-2) + ':' + 
					                        ('0' + currentDate.getMinutes()).slice(-2);

					   
					   	var	commentHtml = '';
                       if ($("#commentList li").length > 0) {
   					    commentHtml +=('<div class="separator border-2 separator-dashed my-5 hrline"></div>');
   				        }                    
					                        
					    commentHtml += '<li id="firstReply'+data.rebo_no+'">';
					    commentHtml += '<div class="d-flex align-items-center no-border" style="border: none;">';
					    commentHtml += '<img src="${sessionScope.userInfoVo.empl_picture_str}" alt="프로필 사진" width="36" height="36" class="mr-3">';
					    commentHtml += '<div style="margin-left: 10px; position: relative; width: 100%;">';
					    commentHtml += '<strong id="rebo_writer'+data.rebo_no+'">'+rebo_writer+'</strong><br>';
					    commentHtml += '<div id="replycontent'+data.rebo_no+'">'+rebo_content+'</div>';
					    commentHtml += '<div>';
					    commentHtml += '<small>'+formattedDate+'</small>';
					    commentHtml += '<div style="position: absolute; top: 0; right: 0; float: right">';
					    commentHtml += '<div style="position: relative;">';
					    commentHtml += '<a role="button" title="더보기" class="comment_tool" href="#" id="comment_tool'+data.rebo_no+'" onclick="toggleMenu(event,'+data.rebo_no+')">';
					    commentHtml += '<img alt="더보기" src="https://cdn4.iconfinder.com/data/icons/liny/24/more-menu-vertical-line-64.png" style="height: 30px;">';
					    commentHtml += '</a>';
					    commentHtml += '</div>';
					    commentHtml += '</div>';
					    commentHtml += '</div>';
					    commentHtml += '</div>';
					    commentHtml += '</div>';
					    commentHtml += '</li>';
					   
					    
					    
					    $("#commentList").append(commentHtml);
				},
				error: function() {
					
				}
			});
			
			
	}





</script> 


</body>

</html>