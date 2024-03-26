<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/menu/headerInfo.jsp" %>
<title>HCM메인화면</title>
<style type="text/css">
	
	.exitBtn{
		width: 100%;
	}
	
	.sideWidth{
		padding: 10px;
	}
	
	.fixheightCard{
		height: 480px;
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
			<!--begin::Page title-->
			<div class="page-title d-flex flex-column justify-content-center flex-wrap me-3">
				<!--begin::Title-->
				<h1 class="page-heading d-flex text-gray-900 fw-bold fs-3 flex-column justify-content-center my-0">제목이 여기에 들어가요!</h1>
				<!--end::Title-->
			</div>
			<!--end::Page title-->
		</div>		
	</div>

	<div class="app-content flex-column-fluid">
		<div class="app-container container-fluid">
			<div class="row gx-5 gx-xl-10">
			
				<!--begin::Col-->
				<div class="col-xxl-6 mb-5 mb-xl-10">
					<div class="card shadow-sm fixheightCard">
					    <div class="card-header">
					        <h3 class="card-title">공지사항</h3>
					    </div>
					    <div class="card-body table-responsive">
					    <table style="text-align: center;" class="table table-hover gs-7 gy-7 gx-7">
					    	<thead>
					    		<tr class="fw-semibold fs-6 text-gray-800 border-bottom border-gray-200">
					    			<th>순번</th>
					    			<th>제목</th>
					    			<th>작성일</th>
					    		</tr>
					    	</thead>
					    	<tbody>
					    		<c:choose>
					    			<c:when test="${gbListsSize eq '0'}">
					    				<tr style="height: 195.5px;">
					    					<td colspan="3"  style="align-content: center; align-items: center;">
					    						공지사항이 없습니다
					    					</td>
					    				</tr>
					    			</c:when>
					    			<c:when test="${gbListsSize > 2}">
									    <c:forEach var="index" begin="0" end="2">
							    		<tr style="cursor: pointer;" onclick="location.href='sm/getDetailGobo.do?gobo_no=${gbLists.get(index).getGobo_no()}'">
							    			<td>${index+1}</td>
							    			<td>${gbLists.get(index).getGobo_title()}</td>
							    			<td>
							    				<fmt:formatDate value="${gbLists.get(index).getGobo_regdate()}" pattern="yyyy년 MM월 dd일"/>
							    			</td>
							    		</tr>
									    </c:forEach>
					    			</c:when>
					    			<c:otherwise>
									    <c:forEach var="index" begin="0" end="${gbListsSize}">
							    		<tr style="cursor: pointer;" onclick="location.href='sm/getDetailGobo.do?gobo_no=${gbLists.get(index).getGobo_no()}'">
							    			<td>${index+1}</td>
							    			<td>${gbLists.get(index).getGobo_title()}</td>
							    			<td>
							    				<fmt:formatDate value="${gbLists.get(index).getGobo_regdate()}" pattern="yyyy년 MM월 dd일"/>
							    			</td>
							    		</tr>
									    </c:forEach>
					    			</c:otherwise>
					    		</c:choose>
					    	</tbody>
					    	<tfoot>
								<tr>
									<td colspan="3" style="text-align: center;">
										<button onclick="location.href='sm/getAllGobo.do'" class="btn btn-primary btnMd">더보기</button>
									</td>
								</tr>					    	
					    	</tfoot>
					    </table>
					    </div>
					</div>
				</div>
				<!--end::Col-->
				
				<!--begin::Col-->
				<div class="col-xxl-6 mb-5 mb-xl-10">
					<div class="card shadow-sm fixheightCard">
					    <div class="card-header">
					        <h3 class="card-title">최근결재문서</h3>
					    </div>
					    <div class="card-body table-responsive">
					    <table style="text-align: center;" class="table table-hover gs-7 gy-7 gx-7">
					    	<thead>
					    		<tr class="fw-semibold fs-6 text-gray-800 border-bottom border-gray-200">
					    			<th>순번</th>
					    			<th>제목</th>
					    			<th>작성일</th>
					    		</tr>
					    	</thead>
					    	<tbody>
					    		<c:choose>
					    			<c:when test="${docSize < 0}">
					    				<tr style="height: 195.5px;">
					    					<td colspan="3" style="align-content: center; align-items: center;">
					    						최근결재문서가 없습니다
					    					</td>
					    				</tr>
					    			</c:when>
					    			<c:when test="${docSize > 2}">
									    <c:forEach var="idx" begin="0" end="2">
							    		<tr style="cursor: pointer;" onclick="location.href='/doc/docBox/getDetail.do?docNum=${allDoc.get(idx).getSidb_doc_num()}'">
							    			<td>${idx+1}</td>
							    			<td>${allDoc.get(idx).getSidb_doc_title()}</td>
							    			<td>
							    				${allDoc.get(idx).getSidb_doc_writedt()}
							    			</td>
							    		</tr>
									    </c:forEach>
					    			</c:when>
					    			<c:otherwise>
									    <c:forEach var="idx" begin="0" end="${docSize}">
							    		<tr style="cursor: pointer;" onclick="location.href='/doc/docBox/getDetail.do?docNum=${allDoc.get(idx).getSidb_doc_num()}'">
							    			<td>${idx+1}</td>
							    			<td>${allDoc.get(idx).getSidb_doc_title()}</td>
							    			<td>
							    				${allDoc.get(idx).getSidb_doc_writedt()}
							    			</td>
							    		</tr>
									    </c:forEach>
					    			</c:otherwise>
					    		</c:choose>
					    	</tbody>
					    	<tfoot>
								<tr>
									<td colspan="3" style="text-align: center;">
										<button onclick="location.href='/doc/docBox.do'" class="btn btn-primary btnMd">더보기</button>
									</td>
								</tr>					    	
					    	</tfoot>
					    </table>
					    </div>
					</div>
				</div>
				<!--end::Col-->
				
			</div>	
			
				<div class="row gx-5 gx-xl-10">
			
				<!--begin::Col-->
				<div class="col-xxl-6 mb-5 mb-xl-10">
					<div class="card shadow-sm fixheightCard">
					    <div class="card-header">
					        <h3 class="card-title">캘린더</h3>
					    </div>
					    <div class="card-body">
					    	공사중입니다!
					    </div>
					</div>
				</div>
				<!--end::Col-->
				
				<!--begin::Col-->
				<div class="col-xxl-6 mb-5 mb-xl-10">
					<div class="card shadow-sm fixheightCard">
					    <div class="card-header">
					        <h3 class="card-title">뉴스?</h3>
					    </div>
					    <div class="card-body">
					    	공사중입니다!
					    </div>
					</div>
				</div>
				<!--end::Col-->
				
			</div>
			
		</div>
	</div>
	
	
	
</div>		
		
		
<%@include file="/WEB-INF/views/menu/mainSideMenu.jsp"%>	
</body>
<script type="text/javascript">

var clockTarget = document.getElementById("nowTime");


function clock() {
    var date = new Date();
    var month = date.getMonth();
    var clockDate = date.getDate();
    var day = date.getDay();
    var week = ['일', '월', '화', '수', '목', '금', '토'];
    var hours = date.getHours();
    const formattedHours = hours < 10 ? '0' + hours : hours;
    
    var minutes = date.getMinutes();
    const formattedMinutes = minutes < 10 ? '0' + minutes : minutes;
    
    var seconds = date.getSeconds();
    const formattedSeconds = seconds < 10 ? '0' + seconds : seconds;
    
    clockTarget.innerText = month+1+"월 "+clockDate+"일 "+week[day]+"요일 "+hours+"시 "+formattedMinutes+"분 "+formattedSeconds+"초"
}



function init() {
	clock();
	setInterval(clock, 1000);
}

	init();

</script>
</html>