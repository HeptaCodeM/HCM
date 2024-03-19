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
<title>DOC메인화면</title>
</head>
<%@include file="/WEB-INF/views/menu/header.jsp"%>
<style>
.container {
	width: 80%;
	margin: 0 auto;
}

.footer {
	border: 1px solid #000;
	margin-top: 20%;
	padding: 10px;
}

table {
	width: 100%;
	border-collapse: collapse;
}

th, td {
	border: 1px solid #000;
	padding: 5px;
	text-align: left;
}

.header {
	float: right;
	top: 10px; /* 원하는 위치 조정 */
	right: 10px; /* 원하는 위치 조정 */
	width: 30%; /* 크기 조정 */
	height: 30%;
}

.content {
	margin-top: 15%;
	width: 100%;
}
</style>
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
						class="page-heading d-flex text-gray-900 fw-bold fs-3 flex-column justify-content-center my-0">결재문서
						상세 페이지</h1>
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
						<div class="card-body pt-5">

							<div class="container">
								<div class="header">
									<div></div>
									<table>
										<tr>
											<c:set var="docDto1" value="${docDto[1]}" />
											<th rowspan="3">결<br>재
											</th>

											<c:forEach items="${docDto}" var="dt" varStatus="i">
												<td><c:choose>
													<%-- 	<c:when test="${empty dt.appr_sign}"> --%>
														<c:when test="${dt.appr_sign == null || dt.appr_sign ==' '|| dt.appr_sign ==''}">
            											    &nbsp;
         											   </c:when>
														<c:otherwise>
															<img style="width: 100px; height: 70px; border: none;"
																src="${dt.appr_sign}" />
														</c:otherwise>
													</c:choose></td>
											</c:forEach>

											<!-- 	<td>ㅇ</td>
											<td>ㅇ</td>
											<td>ㅇ</td> -->
										</tr>
										<tr>
											<c:forEach items="${docDto}" var="dt" varStatus="i">
												<td>${dt.appr_dt}</td>
											</c:forEach>
										</tr>
									</table>
								</div>
								 문서 번호: ${docDto1.sidb_doc_num}
								<p>
									결재문서기한:
									<fmt:parseDate var="patternDate"
										value="${docDto1.sidb_doc_expiredt}"
										pattern="yyyy-MM-dd HH:mm:ss" />
									<fmt:formatDate value="${patternDate}" pattern="yyyy년 MM월 dd일" />

								</p>
								<div class="content">
									<table>
										<tr>
											<th>문서제목 ${docDto1.sidb_doc_title}</th>
											<td>부서 ${docDto[1].writer_dt}</td>
										</tr>
										<tr>
											<th>기안자 ${docDto1.empl_name}</th>
											<td>작성일 <fmt:parseDate var="patternDate"
													value=" ${docDto1.sidb_doc_writedt}"
													pattern="yyyy-MM-dd HH:mm:ss" /> <fmt:formatDate
													value="${patternDate}" pattern="yyyy년 MM월 dd일" />
											</td>
										</tr>
										<tr>
											<td colspan="2">${docDto1.sidb_doc_content}<br> <br>
												<br> <br> <br> <br> <br> <br>
												<br> <br> <br> <br> <br>
											</td>
										</tr>
									</table>
								</div>
								<div class="footer">
									<div>첨언내역</div>
									<table>
										<tr>
											<th>작성자</th>
											<th>결재결과</th>
											<th>내용</th>
										</tr>

										<c:forEach items="${docDto}" var="dt">

											<tr>
												<td>${dt.appr_name}<br>
												</td>
												<td><c:choose>
														<c:when test="${dt.appr_flag eq 1}">
															<p>승인</p>
														</c:when>
														<c:when test="${dt.appr_flag eq 2}">
															<p>반려</p>
														</c:when>
													</c:choose></td>
												<td>${dt.appr_reply}<br></td>
											</tr>
										</c:forEach>
										<%-- 	<tr>
											<td>${docDto1.appr_name} <br> <br></td>
											<td>${docDto1.appr_reply}<br> <br></td>
										</tr> --%>
									</table>
								</div>
								<div style="text-align: center;">
								
	<c:if test="${sessionScope.userInfoVo.empl_id eq docDto1.sidb_curr_id}">
    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#kt_modal_3">승인</button>
    <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#kt_modal_1">반려</button>
	</c:if>

	<c:if test="${sessionScope.userInfoVo.empl_id eq docDto1.empl_id and docDto1.sidb_doc_stat == 1}">
    <button type="button" class="btn btn-primary" onclick="docEdit()">품의수정</button>
    <button type="button" class="btn btn-danger" onclick="docCancel()">상신취소</button>
	</c:if>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<%@include file="/WEB-INF/views/menu/docSideMenu.jsp"%>


	<!-- 승인 모달창 -->
	<div class="modal fade" tabindex="-1" id="kt_modal_3">
		<div class="modal-dialog">
			<div class="modal-content position-absolute">
				<div class="modal-header">
					<h5 class="modal-title">승인</h5>

					<!--begin::Close-->
					<div class="btn btn-icon btn-sm btn-active-light-primary ms-2"
						data-bs-dismiss="modal" aria-label="Close">
						<i class="ki-duotone ki-cross fs-2x"><span class="path1"></span><span
							class="path2"></span></i>
					</div>
					<!--end::Close-->
				</div>

				<!-- 승인 첨언 입력 form -->
				<div class="modal-body">
					<p>첨언</p>
					<form id="reply" action="./approve.do" method="post">
					<input type="hidden" value="${docDto1.sidb_doc_num}" name="docNum"/>
						<textarea name="reply" style="width: 100%;"></textarea>
					</form>
				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-primary me-10"
						onclick="approve()">승인</button>
						
				</div>
			</div>
		</div>
	</div>

	<!-- 반려 모달창 -->
	<div class="modal fade" tabindex="-1" id="kt_modal_1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h3 class="modal-title">반려</h3>

					<!--begin::Close-->
					<div class="btn btn-icon btn-sm btn-active-light-primary ms-2"
						data-bs-dismiss="modal" aria-label="Close">
						<i class="ki-duotone ki-cross fs-1"><span class="path1"></span><span
							class="path2"></span></i>
					</div>
					<!--end::Close-->
				</div>

				<!-- 반려사유 입력 form -->
				<div class="modal-body">
					<p>반려사유</p>
					<form id="denyReply" action="./deny.do" method="post">
						<textarea name="reply" style="width: 100%;"></textarea>
						<input type="hidden" value="${docDto1.sidb_doc_num}" name="docNum"/>
					</form>
				</div>

				<div class="modal-footer">
					<button type="submit" class="btn btn-danger me-10" onclick="deny()">반려</button>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	function approve() {
	        alert("승인처리 되었습니다");
	        document.getElementById('reply').submit();
	    }
	

	function deny() {
		alert("반려처리 되었습니다");
		document.getElementById('denyReply').submit();
	}

	var element = document.querySelector('#kt_modal_3');
	dragElement(element);

	function dragElement(elmnt) {
		var pos1 = 0, pos2 = 0, pos3 = 0, pos4 = 0;
		if (elmnt.querySelector('.modal-content')) {
			// if present, the header is where you move the DIV from:
			elmnt.querySelector('.modal-content').onmousedown = dragMouseDown;
		} else {
			// otherwise, move the DIV from anywhere inside the DIV:
			elmnt.onmousedown = dragMouseDown;
		}

		function dragMouseDown(e) {
			e = e || window.event;
			// get the mouse cursor position at startup:
			pos3 = e.clientX;
			pos4 = e.clientY;
			document.onmouseup = closeDragElement;
			// call a function whenever the cursor moves:
			document.onmousemove = elementDrag;
		}

		function elementDrag(e) {
			e = e || window.event;
			// calculate the new cursor position:
			pos1 = pos3 - e.clientX;
			pos2 = pos4 - e.clientY;
			pos3 = e.clientX;
			pos4 = e.clientY;
			// set the element's new position:
			elmnt.style.top = (elmnt.offsetTop - pos2) + "px";
			elmnt.style.left = (elmnt.offsetLeft - pos1) + "px";
		}

		function closeDragElement() {
			// stop moving when mouse button is released:
			document.onmouseup = null;
			document.onmousemove = null;
		}
	}
</script>

</html>