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
						결재문서함</h1>
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
<button class="btn btn-primary" onclick="gianBox()">기안</button>
<button class="btn btn-primary" onclick="ingBox()">진행</button>
<button class="btn btn-primary" onclick="approveBox()">완료</button>
<button class="btn btn-primary" onclick="denyBox()">반려</button>
<span style="display:inline-block; width:400px;"> &nbsp;</span>
<button class="btn btn-success" onclick="chamjoBox()">참조</button>
<button class="btn btn-success" onclick="myTurnBox()">요청</button>
<button class="btn btn-success" onclick="iDidBox()">결재</button>
	<div id="tableOuter">
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
								</i> <br> ${dto.sidt_temp_name}
							</c:when>
							<c:when test="${dto.sidt_temp_cd eq 'TC000013'}">
								<i class="ki-duotone ki-briefcase fs-3x text-gray-900">
 							<span class="path1"></span><span class="path2"></span>
								</i> <br> ${dto.sidt_temp_name}
							</c:when>
							<c:when test="${dto.sidt_temp_cd eq 'TC000002'}">
								<i class="ki-duotone ki-home fs-3x text-muted">
								</i> <br> ${dto.sidt_temp_name}
							</c:when>
							<c:otherwise>
								<i class="ki-duotone ki-bill fs-3x text-success"> 
								<span class="path1"></span> <span class="path2"></span> 
								<span class="path3"></span> <span class="path4"></span> 
								<span class="path5"></span> <span class="path6"></span>
								</i> <br> ${dto.sidt_temp_name}
							</c:otherwise>
						</c:choose>
					</td>
						<td><a href="#" onclick="detailBoard(${dto.sidb_doc_num})">${dto.sidb_doc_title}</a> <br> <br>
						기안일자 |<fmt:parseDate var="patternDate"	value="${dto.sidb_doc_writedt}"	pattern="yyyy-MM-dd HH:mm:ss" /> 
								<fmt:formatDate value="${patternDate}" pattern="yyyy년 MM월 dd일" /></td>
						<td><img style="width:50px; height:50px; border-radius: 22px;"  src="data:image/png;base64,${dto.empl_pictureStr}" /> ${dto.empl_name}</td>
						
						<td style="text-align:center;">
						
							<c:if test="${dto.sidb_doc_stat == 1  and empty dto.appr_name1 and dto.appr_flag0 == 0}">
							<img style="width:80%;" src="/image/doc/docBox/s1a1d1.png">
							<br> &nbsp;&nbsp;기안  <span style="display:inline-block; width:50%;"> </span><span style="color: DodgerBlue; font-size: large;">${dto.appr_name0} ></span> &nbsp;&nbsp;
							</c:if>
							
							<c:if test="${dto.sidb_doc_stat == 1  and empty dto.appr_name2 and dto.appr_flag0 == 0}">
							<img style="width:80%;" src="/image/doc/docBox/s1a2d1.png">
							<br> 기안 <span style="display:inline-block; width:25%;"> </span><span style="color: DodgerBlue;  font-size: large;">
							${dto.appr_name0} ></span> <span style="display:inline-block; width:22%;"></span>${dto.appr_name1}
							</c:if>
							
							<c:if test="${dto.sidb_doc_stat == 2  and empty dto.appr_name2 and dto.appr_flag0 == 1 and dto.appr_flag1 == 0}">
							<img style="width:80%;" src="/image/doc/docBox/s2a2d2.png">
							<br>  &nbsp;&nbsp; &nbsp; &nbsp; &nbsp;기안 &nbsp;  &nbsp;  <span style="display:inline-block; width:20%;"> </span>&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;<span style="color: MediumSeaGreen; ">
							${dto.appr_name0} > </span> <span style="display:inline-block; width:24%;"></span><span style="color: DodgerBlue; font-size: large; ">${dto.appr_name1} &nbsp; &nbsp;</span> 
							</c:if>
							
							<c:if test="${dto.sidb_doc_stat == 2  and !empty dto.appr_name2 and dto.appr_flag0 == 1 and dto.appr_flag1 == 0}">
							<img style="width:80%;" src="/image/doc/docBox/s2a3d2.png">
							<br>   &nbsp;&nbsp;기안 <span style="display:inline-block; width:13%;"></span><span style="color: MediumSeaGreen;">${dto.appr_name0} ></span> 
							<span style="display:inline-block; width:11%;"></span>&nbsp;&nbsp;&nbsp; &nbsp;<span style="color: DodgerBlue; font-size: large;">${dto.appr_name1} &nbsp;></span>
							&nbsp;&nbsp; <span style="display:inline-block; width:9%;"></span>${dto.appr_name2}&nbsp; &nbsp;
							</c:if>
							
							<c:if test="${dto.sidb_doc_stat == 2  and !empty dto.appr_name2 and dto.appr_flag1 == 1 and dto.appr_flag2 == 0}">
							<img style="width:80%;" src="/image/doc/docBox/s2a3d3.png">
							<br> 기안 &nbsp;&nbsp; <span style="color: MediumSeaGreen;">${dto.appr_name0} ></span> &nbsp;&nbsp;&nbsp; &nbsp;<span style="color: blue; font-size: larger;">${dto.appr_name1} &nbsp;></span>&nbsp;&nbsp; ${dto.appr_name2}&nbsp; &nbsp;
							</c:if>
							
							<c:if test="${dto.sidb_doc_stat == 1  and !empty dto.appr_name2 and dto.appr_flag0 == 0 }">
							<img style="width:80%;" src="/image/doc/docBox/s1a3d1.png">
							<br> &nbsp;&nbsp;기안 <span style="display:inline-block; width:12%;"></span><span style="color: DodgerBlue; font-size: large;">${dto.appr_name0} ></span> 
							<span style="display:inline-block; width:10%;"></span>&nbsp;&nbsp;&nbsp; &nbsp;${dto.appr_name1} &nbsp;>
							&nbsp;&nbsp; <span style="display:inline-block; width:10%;"></span>${dto.appr_name2}&nbsp; &nbsp;
							</c:if>
							
							<c:if test="${dto.sidb_doc_stat == 3  and empty dto.appr_name1 and dto.appr_flag0 == 1 }">
							<img style="width:80%;" src="/image/doc/docBox/s3a1d1.png">
							<br> &nbsp;&nbsp;기안  <span style="display:inline-block; width:50%;"> </span><span style="color: MediumSeaGreen; font-size: large;">${dto.appr_name0} ></span> &nbsp;&nbsp;
							</c:if>
							
							<c:if test="${dto.sidb_doc_stat == 3  and empty dto.appr_name2 and dto.appr_flag1 == 1 }">
							<img style="width:80%;" src="/image/doc/docBox/s3a2d2.png">
							<br>   &nbsp;&nbsp; &nbsp; &nbsp; &nbsp;기안 &nbsp;  &nbsp;  <span style="display:inline-block; width:20%;"> </span>&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;<span style="color: MediumSeaGreen; ">
							${dto.appr_name0} > </span> <span style="display:inline-block; width:24%;"></span><span style="color: MediumSeaGreen; font-size: large; ">${dto.appr_name1} &nbsp; &nbsp;</span> 
							</c:if>
							
							<c:if test="${dto.sidb_doc_stat == 3  and !empty dto.appr_name2 and dto.appr_flag2 == 1 }">
							<img style="width:80%;" src="/image/doc/docBox/s3a3d3.png">
							<br>  &nbsp; &nbsp; &nbsp;기안 <span style="display:inline-block; width:13%;"></span><span style="color: MediumSeaGreen;">${dto.appr_name0} ></span> 
							<span style="display:inline-block; width:12%;"></span>&nbsp;&nbsp;&nbsp; &nbsp;<span style="color: MediumSeaGreen;">${dto.appr_name1} &nbsp;></span>
							&nbsp;&nbsp; <span style="display:inline-block; width:10%;"></span><span style="color: MediumSeaGreen; font-size: large;">${dto.appr_name2}</span>&nbsp; &nbsp;
							</c:if>
				
							
							<c:if test="${dto.sidb_doc_stat == 4  and empty dto.appr_name1 and dto.appr_flag0 == 2 }">
							<img style="width:80%;" src="/image/doc/docBox/s4a1d1.png">
							<br> &nbsp;&nbsp;기안  <span style="display:inline-block; width:50%;"> </span><span style="color: red; font-size: large;">${dto.appr_name0} ></span> &nbsp;&nbsp;
							</c:if>
							
							<c:if test="${dto.sidb_doc_stat == 4  and empty dto.appr_name2 and dto.appr_flag0 == 2 }">
							<img style="width:80%;" src="/image/doc/docBox/s4a2d1.png">
							<br> 기안 <span style="display:inline-block; width:25%;"> </span><span style="color: red;  font-size: large;">
							${dto.appr_name0} > </span> <span style="display:inline-block; width:22%;"></span>${dto.appr_name1}
							</c:if>
							
							<c:if test="${dto.sidb_doc_stat == 4  and empty dto.appr_name2 and dto.appr_flag1 == 2 }">
							<img style="width:80%;" src="/image/doc/docBox/s4a2d2.png">
							<br> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;기안 &nbsp;  &nbsp;  <span style="display:inline-block; width:21%;"> </span>&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;<span style="color: MediumSeaGreen; ">
							${dto.appr_name0} > </span> <span style="display:inline-block; width:24%;"></span><span style="color: red; font-size: large; ">${dto.appr_name1} &nbsp; &nbsp;</span> 
							</c:if>
							
							<c:if test="${dto.sidb_doc_stat == 4  and !empty dto.appr_name2 and dto.appr_flag0 == 2 }">
							<img style="width:80%;" src="/image/doc/docBox/s4a3d1.png">
							<br> &nbsp;&nbsp;기안 <span style="display:inline-block; width:12%;"></span><span style="color: red; font-size: large;">${dto.appr_name0} ></span> 
							<span style="display:inline-block; width:10%;"></span>&nbsp;&nbsp;&nbsp; &nbsp;${dto.appr_name1} &nbsp;>
							&nbsp;&nbsp; <span style="display:inline-block; width:10%;"></span>${dto.appr_name2}&nbsp; &nbsp;
							</c:if>
							
							<c:if test="${dto.sidb_doc_stat == 4  and !empty dto.appr_name2 and dto.appr_flag1 == 2 }">
							<img style="width:80%;" src="/image/doc/docBox/s4a3d2.png">
							<br>   &nbsp;&nbsp;기안 <span style="display:inline-block; width:13%;"></span><span style="color: MediumSeaGreen;">${dto.appr_name0} ></span> 
							<span style="display:inline-block; width:11%;"></span>&nbsp;&nbsp;&nbsp; &nbsp;<span style="color: red; font-size: large;">${dto.appr_name1} &nbsp;></span>
							&nbsp;&nbsp; <span style="display:inline-block; width:9%;"></span>${dto.appr_name2}&nbsp; &nbsp;
							</c:if>
							
							<c:if test="${dto.sidb_doc_stat == 4  and !empty dto.appr_name2 and dto.appr_flag2 == 2 }">
							<img style="width:80%;" src="/image/doc/docBox/s4a3d3.png">
							<br>  &nbsp; &nbsp; &nbsp;기안 <span style="display:inline-block; width:13%;"></span><span style="color: MediumSeaGreen;">${dto.appr_name0} ></span> 
							<span style="display:inline-block; width:12%;"></span>&nbsp;&nbsp;&nbsp; &nbsp;<span style="color: MediumSeaGreen;">${dto.appr_name1} &nbsp;></span>
							&nbsp;&nbsp; <span style="display:inline-block; width:10%;"></span><span style="color: red; font-size: large;">${dto.appr_name2}</span>&nbsp; &nbsp;
							</c:if>
						</td>
						<td>
						<c:if test="${dto.sidb_doc_stat == 1}">
						<img style="width:80%;" src="/image/doc/docBox/s1.png">
						</c:if>
						<c:if test="${dto.sidb_doc_stat == 2}">
						<img style="width:80%;" src="/image/doc/docBox/s2.png">
						</c:if>
						<c:if test="${dto.sidb_doc_stat == 3}">
						<img style="width:80%;" src="/image/doc/docBox/s3.png">
						</c:if>
							<c:if test="${dto.sidb_doc_stat == 4}">
						<img style="width:80%;" src="/image/doc/docBox/s4.png">
						</c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</div>
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
<script type="text/javascript" src="../js/doc/docBox.js"></script>


</html>