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

<button class="btn btn-primary" onclick="allDocs()">전체</button>
<button class="btn btn-primary" onclick="gianBox()">기안중</button>
<button class="btn btn-primary" onclick="Ajax()">진행중</button>
<button class="btn btn-primary" onclick="Ajax()">완료</button>
<button class="btn btn-primary" onclick="Ajax()">반려</button>
<span style="display:inline-block; width:200px;"> &nbsp;</span>
<button class="btn btn-success" onclick="Ajax()">참조</button>
<button class="btn btn-success" onclick="Ajax()">요청</button>
								<table id="myTable" class="stripe hover">
									<thead>
										<tr style="">
											<th></th>
											<th></th>
											<th></th>
											<th></th>
											<th></th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="dto" items="${lists}">
											<tr style="min-height:200px; text-align:center; ">
												<td>
											<c:choose>
													<c:when test="${dto.sidt_temp_cd eq 'TC000014'}">
														<i class="ki-duotone ki-document fs-3x text-primary">
															<span class="path1"></span> <span class="path2"></span>
														</i> <br>
														${dto.sidt_temp_name}
													</c:when>
													<c:when test="${dto.sidt_temp_cd eq 'TC000002'}">
														<i class="ki-duotone ki-bill fs-3x text-success"> <span
															class="path1"></span> <span class="path2"></span> <span
															class="path3"></span> <span class="path4"></span> <span
															class="path5"></span> <span class="path6"></span>
														</i> <br>
														${dto.sidt_temp_name}
													</c:when>
												</c:choose>
											</td>
												<td><a href="#" onclick="detailBoard('${dto.sidb_doc_num}')">${dto.sidb_doc_title}</a> <br> <br>
												기안일자 |<fmt:parseDate var="patternDate"
													value="${dto.sidb_doc_writedt}"
													pattern="yyyy-MM-dd HH:mm:ss" /> <fmt:formatDate
													value="${patternDate}" pattern="yyyy년 MM월 dd일" /></td>
												<td><img style="width:50px; height:50px; border-radius: 22px;" src="data:image/jpeg;base64,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"/> ${dto.empl_name}</td>
												<td style="text-align:center;">
													<c:if test="${dto.sidb_doc_stat == 2  and empty dto.appr_name2 and dto.appr_flag0 == 1 and dto.appr_flag1 == 0}">
    												<img style="width:50%;" src="/image/s2a2d2.png">
    												<br> 기안 &nbsp;&nbsp; <span style="color: green;">${dto.appr_name0} ></span> &nbsp;&nbsp;&nbsp; &nbsp;<span style="color: blue; font-size: larger;">${dto.appr_name1} &nbsp;></span>&nbsp;&nbsp; ${dto.appr_name2}&nbsp; &nbsp;결재완료
													</c:if>
													
													<c:if test="${dto.sidb_doc_stat == 2  and !empty dto.appr_name2 and dto.appr_flag0 == 1 and dto.appr_flag1 == 0}">
    												<img style="width:50%;" src="/image/s2a3d2.png">
    												<br> 기안 &nbsp;&nbsp; <span style="color: green;">${dto.appr_name0} ></span> &nbsp;&nbsp;&nbsp; &nbsp;<span style="color: blue; font-size: larger;">${dto.appr_name1} &nbsp;></span>&nbsp;&nbsp; ${dto.appr_name2}&nbsp; &nbsp;결재완료
    												
													</c:if>
													<c:if test="${dto.sidb_doc_stat == 1  and !empty dto.appr_name2 and dto.appr_flag0 == 0 and dto.appr_flag1 == 0}">
    												<img style="width:50%;" src="/image/s1a3d1.png">
													</c:if>
													<br> 기안 &nbsp;&nbsp; ${dto.appr_name0} &nbsp; &nbsp;${dto.appr_name1}&nbsp;&nbsp; ${dto.appr_name2}&nbsp; &nbsp;결재완료
												</td>
												<td>
												<c:if test="${dto.sidb_doc_stat == 1}">
												<img style="width:80%;" src="/image/s1.png">
												</c:if>
												<c:if test="${dto.sidb_doc_stat == 2}">
												<img style="width:80%;" src="/image/s2.png">
												</c:if>
												<c:if test="${dto.sidb_doc_stat == 3}">
												<img style="width:80%;" src="/image/s3.png">
												</c:if>
													<c:if test="${dto.sidb_doc_stat == 4}">
												<img style="width:80%;" src="/image/s4.png">
												</c:if>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
<div class="d-flex flex-stack">
															

						</div>
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
    var url = '/doc/docBox/getDetail.do?docNum=' + encodeURIComponent(docNum);
    // Redirecting to the URL
    window.location.href = url;
}
function gianBox() {
    var url = '/doc/gianBox.do';
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
   	    ordering: false, // 정렬 기능
        paging:true, // 페이징 기능 
        lengthChange: false, //  좌상단 몇 건씩 볼지 정하는 기능
   //   order: [ [ 3, "desc" ], [ 1, "asc"] ], //첫 화면 출력시 정렬 기준  + 안할시 첫 열을 기준으로 정렬하는듯
   	    autoWidth: true,    //자동 열 조정
     	columnDefs: [{ targets: 0, width: 30 },{ targets: 1, width: 100 },{ targets: 2, width: 5 },{ targets: 3, width: 200 },{ targets: 4, width: 20 }], //열의 너비 조절 0,1,2,3 순임
//      displayLength: 20, //처음에 몇 건을 볼지 
		lengthMenu: [ 5, 10, 15 ], //몇개씩 볼지(기본값 10, 25, 50, 100)
        pagingType: "simple_numbers" 
        // 페이징 타입 설정 : simple =이전, 다음 /simple_numbers 숫자페이징+이전 다음 , /full_numbers = 처음, 마지막 추가

	});
});
</script>

</html>