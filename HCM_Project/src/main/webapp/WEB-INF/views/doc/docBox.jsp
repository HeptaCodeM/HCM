<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/menu/headerInfo.jsp"%>
<link rel="stylesheet" href="https://cdn.datatables.net/1.13.7/css/jquery.dataTables.css" />	
<script src="https://cdn.datatables.net/1.13.7/js/jquery.dataTables.js"></script>
<title>결재문서함메인화면</title>
</head>

<%@include file="/WEB-INF/views/menu/header.jsp"%>
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
				class="app-container container-fluid d-flex flex-stack">
				<!--begin::Page title-->
				<div
					class="page-title d-flex flex-column justify-content-center flex-wrap me-3">
					<!--begin::Title-->
					<h1
						class="page-heading d-flex text-gray-900 fw-bold fs-3 flex-column justify-content-center my-0">
						결재문서함 메인 페이지</h1>
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
						<div class="card-header pt-5">
							<h3 class="card-title text-gray-800 fw-bold">결재문서</h3>
						</div>
						<div class="separator separator-dashed my-3"></div>
						<div class="card-body pt-5" style="height: 80%;">


								<table id="myTable" class="cell-border stripe hover">
									<thead>
										<tr style="">
											<th>기안일자</th>
											<th>제목</th>
											<th>결재유형</th>
											<th>진행상태</th>
											<th>기안자</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="dto" items="${lists}">
											<tr>
												<td>
												<fmt:parseDate var="patternDate"
                              value="${dto.sidb_doc_writedt}"
                              pattern="yyyy-MM-dd HH:mm:ss" />
                           <fmt:formatDate value="${patternDate}" pattern="yyyy년 MM월 dd일" />
										 </td>
												<td><a href="#" onclick="detailBoard('${dto.sidb_doc_num}')">${dto.sidb_doc_title}</a></td>
												<td>결재템플릿이 될거야</td>
												<td>나는 커서 프로그래스바가 될거야</td>
												<td>${dto.empl_name}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>


						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<%@include file="/WEB-INF/views/menu/docSideMenu.jsp"%>




</body>
<script type="text/javascript">

function detailBoard(docNum) {
    // Constructing the URL with the parameter
    var url = '/doc/getDetail.do?docNum=' + encodeURIComponent(docNum);
    // Redirecting to the URL
    window.location.href = url;
}

$(document).ready( function () {
	$('#myTable').DataTable({ 

    	"language": { 
            "emptyTable": "열람 가능한 결재문서가 없습니다.",
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
        lengthChange: false, //  좌상단 몇 건씩 볼지 정하는 기능
   //   order: [ [ 3, "desc" ], [ 1, "asc"] ], //첫 화면 출력시 정렬 기준  + 안할시 첫 열을 기준으로 정렬하는듯
   	//    autoWidth: true,    //자동 열 조정
   //  	columnDefs: [{ targets: 3, width: 10 }], //열의 너비 조절 0,1,2,3 순임
//      displayLength: 20, //처음에 몇 건을 볼지 
		lengthMenu: [ 5, 10, 15 ], //몇개씩 볼지(기본값 10, 25, 50, 100)
        pagingType: "simple_numbers" 
        // 페이징 타입 설정 : simple =이전, 다음 /simple_numbers 숫자페이징+이전 다음 , /full_numbers = 처음, 마지막 추가

	});
});

</script>

</html>