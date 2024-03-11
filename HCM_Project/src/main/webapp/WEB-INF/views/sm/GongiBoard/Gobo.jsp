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
 <link rel="stylesheet" href="https://cdn.datatables.net/1.13.7/css/jquery.dataTables.css" />
<script src="https://cdn.datatables.net/1.13.7/js/jquery.dataTables.js"></script>
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
						<h1 class="page-heading d-flex text-gray-900 fw-bold fs-3 flex-column justify-content-center my-0">공지사항</h1>
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
		 <button btn btn-info onclick="location.href='/sm/insertGoboForm.do'">글작성</button>
		 	<table id="myTable" class="cell-border stripe hover" >
		 		<thead>
		 			<tr>
		 				<th>번호</th>
		 				<th>제목</th>
		 				<th>내용</th>
		 				<th>작성일</th>
		 				<th>조회수</th>
		 			</tr>
		 		</thead>
		 		<tbody>
		 			<c:forEach var="vo" items="${lists}" varStatus="vs">
		 				<c:if test="${vo.gobo_delflag == 'N'}">
		 				<tr>
		 					<td>${vs.count}</td>
		 					<td><a href="/sm/getDetailGobo.do?gobo_no=${vo.gobo_no}">${vo.gobo_title}</a></td>
		 					<td>${vo.gobo_content}</td>
		 					<td>${vo.gobo_regdate}</td>
		 					<td>${vo.gobo_view}</td>
		 				</tr>
		 				</c:if>
		 			</c:forEach>
		 		</tbody>
		 	</table>

							</div>
						</div>
					</div>
				</div>
				<!-- 내용 끝 -->
			</div>
		</div>
		
<script type="text/javascript">

$.fn.dataTable.ext.classes.sPageButton = 'btn btn-danger';

	$(document).ready( function () {
    	$('#myTable').DataTable({

    	"language": { 
            "emptyTable": "열람 가능한 공지사항이 없습니다.",
            "lengthMenu": " _MENU_ 개씩 보기",
            "info": "현재 _START_ - _END_ / _TOTAL_건",
            "search": "검색: ",
            "paginate": {
                "next": "다음",
                "previous": "이전",
                "first": "처음",
                "last" : "마지막"
            },
          //https://datatables.net/reference/option/language   위를 더 디테일하게 수정하고싶으면 여기로~
        },
        
        info: true, // 좌측하단 정보 표시 
        searching: true, // 검색 기능 
        ordering: true, // 정렬 기능
        paging:true, // 페이징 기능 
        lengthChange: true, //  좌상단 몇 건씩 볼지 정하는 기능
        order: [ [ 3, "desc" ], [ 1, "asc"] ], //첫 화면 출력시 정렬 기준  + 안할시 첫 열을 기준으로 정렬하는듯
    	 autoWidth: true,    //자동 열 조정
//      	columnDefs: [{ targets: 3, width: 100 }], //열의 너비 조절 0,1,2,3 순임
//      displayLength: 20, //처음에 몇 건을 볼지 
		lengthMenu: [ 5, 10, 15 ], //몇개씩 볼지(기본값 10, 25, 50, 100)
        pagingType: "simple_numbers" // 페이징 타입 설정 : simple =이전, 다음 /simple_numbers 숫자페이징+이전 다음 , /full_numbers = 처음, 마지막 추가
     
    });
});






</script>		
			
<%@include file="/WEB-INF/views/menu/smSideMenu.jsp" %>		
</body>
</html>