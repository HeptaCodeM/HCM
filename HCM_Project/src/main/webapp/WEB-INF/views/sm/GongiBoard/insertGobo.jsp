<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/menu/headerInfo.jsp" %>
<title>SM메인화면</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  
<script type="text/javascript" src="/smartEditor/js/service/HuskyEZCreator.js" charset="UTF-8"></script>
<script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js"></script>
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
						<h1 class="page-heading d-flex text-gray-900 fw-bold fs-3 flex-column justify-content-center my-0">공지사항 글 작성</h1>
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
										   <div class="contatiner">
					      <form id="insertForm" method="post">
					      <input type="hidden" name="gobo_writer_id" id="gobo_writer_id" value="${dto.gobo_writer_id}">
					         <div class="form-group">
					            <label for="id">작성자</label>
					            <input class="form-control" id="gobo_writer" name="gobo_writer" value="${dto.gobo_writer}" readonly="readonly">
					         </div>
					         <div class="form-group">
					            <label for="title">제목</label>
					            <input class="form-control" id="gobo_title"  name="gobo_title">
					         </div>
					         <div class="form-group">
					            <label for="id">내용</label>
					            <textarea class="form-control" rows="5" id="smartEditor" name="gobo_content" required="required"></textarea>
					         </div>
					         <div class="form-group">
					            <label for="id">비고</label>
					            <textarea class="form-control" rows="1" id="gobo_bigo" name="gobo_bigo" required="required"></textarea>
					         </div>
					         <div>
					         </div>
					      </form>
					            <input type="button" class="btn btn-success" value="글입력" onclick="insertGobo()">
					            <input type="reset" class="btn btn-danger" value="글초기화">
					            <input type="button" class="btn btn-info" value="뒤로가기" onclick="history.back(-1)">
					   </div>
							</div>
						</div>
					</div>
				</div>
				<!-- 내용 끝 -->
			</div>
		</div>
			
<%@include file="/WEB-INF/views/menu/smSideMenu.jsp" %>	
<script type="text/javascript" src="/smartEditor/js/smart.js"></script>	
</body>

<script type="text/javascript">
function insertGobo(){
	var gobo_writer_id = $("#gobo_writer_id").val("1111");
	var gobo_writer = $("#gobo_writer").val("서종우");
	var gobo_title = $("#gobo_title").val();
	var gobo_content = $("#smartEditor").val();
	var gobo_bigo = $("#gobo_bigo").val();
	if(gobo_writer_id == null || gobo_writer_id == ""){
		alert("아이디가 없습니다.");
	}else if(gobo_writer ==null || gobo_writer == ""){
		alert("작성자 이름이 없습니다.");
	}else if(gobo_title == null || gobo_title == ""){
		alert("제목을 입력하세요.");
	}else if(gobo_content == null || gobo_content == ""){
		alert("내용을 입력하세요.");
	}else{
		 var result = confirm("글을 등록 하시겠습니까?");
		    if (result) {
		    	$.ajax({
		            url: "/sm/insertGobo.do",
		            type: "POST",
		            data: $("#insertForm").serialize(),
		            success: function(e) {
		            	if(e){
		            	alert("글 작성 완료")
		            	location.href='/sm/getAllGobo.do';
		            	}
		            },
		            error: function() {
		                alert("오류")
		            }
		        });
	    } else {
	        // 취소 버튼을 눌렀을 때의 동작
	        // 아무 동작 없음
	    }
		    
		}
	    	
}
</script>
</html>