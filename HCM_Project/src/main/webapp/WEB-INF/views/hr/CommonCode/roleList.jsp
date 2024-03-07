<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/menu/headerInfo.jsp" %>
<c:choose>
	<c:when test="${role eq 'DT'}">
		<c:set var="thisRole" value="부서"/>
	</c:when>
	
	<c:when test="${role eq 'RK'}">
		<c:set var="thisRole" value="직위"/>
	</c:when>
	
	<c:when test="${role eq 'PN'}">
		<c:set var="thisRole" value="직책"/>
	</c:when>
</c:choose>
<title>${thisRole}관리</title>
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
						<h1 class="page-heading d-flex text-gray-900 fw-bold fs-3 flex-column justify-content-center my-0">부서관리</h1>
						<!--end::Title-->
					</div>
					<!--end::Page title-->
				</div>		
			</div>
			<div class="app-content flex-column-fluid">
				<!-- 내용 시작 -->
				<div class="app-content flex-column-fluid">
					<div class="app-container container-fluid">
						<div class="card card-flush h-md-50 mb-xl-10">
							<div class="card-header pt-5">
								<h3 class="card-title text-gray-800 fw-bold">${thisRole}</h3>
							</div>
							<div class="separator separator-dashed my-3"></div>	
							<div class="card-body pt-5">
								<c:forEach var="codeList" items="${codeList}" varStatus="var">
									<a href="./roleDetail.do?coco_cd=${codeList.getCoco_cd()}&role=${role}">${codeList.getCoco_cd()} // ${codeList.getCoco_name()}</a><br>
								</c:forEach>
								<select id="deptSelectGroup_1" multiple="multiple">
								<c:forEach var="codeList" items="${codeList}" varStatus="var">
								    <optgroup label="${codeList.getCoco_name()}">
								        <option>
											${codeList.getCoco_cd()} // ${codeList.getCoco_name()}
								        </option>
								    </optgroup>
								</c:forEach>
								</select>
								<script type="text/javascript">
								$("#deptSelectGroup_1").multiselectsplitter();
								</script>
								
							</div>
							<div class="card-footer">
								<!-- <a href="#" class="btn btn-primary me-10">삭제</a> -->
								<a href="./insertDept.do" class="btn btn-primary me-10">추가</a>
						    </div>
						</div>
					</div>
				</div>
				<!-- 내용 끝 -->
				
			</div>
		</div>
			
<%@include file="/WEB-INF/views/menu/hrSideMenu.jsp" %>		
</body>
</html>