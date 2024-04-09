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
			</div>
		</div>

		<div class="app-content flex-column-fluid">
            <!-- 내용 시작 -->
            <div id="kt_app_content" class="app-content flex-column-fluid">
                <div class="app-container container-fluid">
                    <div class="card card-flush h-md-50 mb-xl-10">
                        <div class="card-body pt-5">
                            <div class="contatiner">
					      <form id="updateForm">
					      	<input type="hidden" name="gobo_no" id="gobo_no" value="${dto.gobo_no}">
					         <div class="form-group">
					            <label for="title">제목</label>
					            <textarea class="form-control" rows="1" id="gobo_title" name="gobo_title" required="required">${dto.gobo_title}</textarea>
					         </div>
					         <div class="form-group">
					            <label for="id">내용</label>
					            <textarea class="form-control" rows="5" id="editor" name="gobo_content" required="required">${dto.gobo_content}</textarea>
					         </div>
					         <div class="form-group">
					            <label for="id">비고</label>
					            <textarea class="form-control" rows="1" id="gobo_bigo" name="gobo_bigo" required="required">${dto.gobo_bigo}</textarea>
					         </div>
					        
					      </form>
					   </div>
                     </div>
                     	<div style="display: flex; justify-content: space-between;">
						    <div>
						        <input type="button" class="btn btn-success" value="수정"  id="savebutton">
						        <input type="button" class="btn btn-info" value="이전화면" onclick="history.back(-1)">
						    </div>
						    <div>
						        <button type="button" class="btn btn-primary" onclick="location.href='/sm/getAllGobo.do'" >목록</button>                     	
						        <button type="button" class="btn btn-primary" onclick="scrollToTop()" id="scrollTopButton" title="맨 위로 이동" >TOP</button>
						    </div>
						</div>
                        </div>
                    </div>
                </div>
            </div>
            </div>
         
<%@include file="/WEB-INF/views/menu/smSideMenu.jsp" %>   

<script type="text/javascript" src="/ckeditor5/build/ckeditor.js"></script>
<script type="text/javascript" src="/js/sm/updateGobo.js"></script>
<script type="module" src="/ckeditor5/sample/script.js"></script>

</body>

</html>