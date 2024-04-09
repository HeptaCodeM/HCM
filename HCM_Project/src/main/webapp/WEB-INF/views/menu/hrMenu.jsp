<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%
//중분류 순서
String hrLeftMenu [] = {"조직관리", "증명서관리", "근태관리", "인사발령관리" , "휴가관리"};
//중분류 메뉴주소
String hrLeftMenuLinkNm [][] = {
		{"employee", "commonCode", "company"}, 
		{"certificate"}, 
		{"commute"},
		{"order"}, 
		{"holiday"}
};

// 소분류 메뉴명
String hrSubLeftMenu [][] = {
		{"정보수정", "비밀번호변경", "간편로그인관리", "사원등록", 
		"사원조회", "부서관리","직위관리", "직책관리", 
		"권한관리", "회사정보관리"},			//hrLeftMenu[0]
		{"증명서리스트"},					//hrLeftMenu[1]
		{"출퇴근등록", "출퇴근현황"},			//hrLeftMenu[2]
		{"인사발령조회", "인사발령관리(관리자)"},	//hrLeftMenu[3]
		{"휴가현황", "휴가현황관리"}			//hrLeftMenu[4]
};
//소분류 링크
String hrSubLinkLeftMenu [][] = {
		{"/hr/employee/empModify.do", "/hr/employee/updatePwd.do", "/hr/employee/naverSns.do", "/hr/employee/registEmpAdmin.do", 
		"/hr/employee/listAdmin.do", "/hr/commonCode/roleListAdmin.do?role=DT","/hr/commonCode/roleListAdmin.do?role=RK", "/hr/commonCode/roleListAdmin.do?role=PN", 
		"/hr/employee/authAdminList.do", "/hr/company/companyInfoAdmin.do"},		//hrLeftMenu[0]
		{"/hr/certificate/certificate.do"},									//hrLeftMenu[1]
		{"/hr/commute/registCommute.do", "/hr/commute/empCommuteList.do"},	//hrLeftMenu[2]
		{"/hr/order/orderList.do", "/hr/order/orderAdminList.do"},			//hrLeftMenu[3]
		{"/hr/holiday/holidayList.do","/hr/holiday/holidayAdminList.do"}	//hrLeftMenu[4]
};

String uri = request.getRequestURI();
String currentPageName = uri.substring(uri.lastIndexOf("/") + 1).replace(".jsp","");
String currentRootName = uri.replace("/WEB-INF/views/hr/","");
currentRootName = currentRootName.substring(0, currentRootName.indexOf("/"));

Boolean openHrMenuFlag1 = false;
Boolean openHrMenuFlag2 = false;
%>
	<!-- 대분류별 영역 시작 -->
	<div class="menu menu-column menu-rounded menu-sub-indention fw-semibold" id="#kt_app_sidebar_menu" data-kt-menu="true" data-kt-menu-expand="false">
		
		<div data-kt-menu-trigger="click" class="menu-item pt-5 menu-item menu-accordion hover show">

			<!-- 대분류 타이틀 영역 시작 ------------------------------------------------------------------------->
			<span class="menu-link">
				<span class="menu-icon">
						<i class="ki-duotone ki-user fs-2">
							<span class="path1"></span>
							<span class="path2"></span>
							<span class="path3"></span>
						</i>
					</span>
					<span class="menu-heading fw-bold text-uppercase fs-7 menu-title">인사관리</span>
					<span class="menu-arrow"></span>
			</span>
			<!-- 대분류 타이틀 영역 종료 -->


			<!-- 중분류 영역 시작 ********************************************************************************-->
			<div class="menu-sub menu-sub-accordion">
			<%for(int i=0;i<hrLeftMenu.length;i++){ %>
				<%
				
				openHrMenuFlag1 = false;
				for(int j=0;j<hrLeftMenuLinkNm[i].length;j++){
					if(hrLeftMenuLinkNm[i][j].equals(currentRootName)){
						openHrMenuFlag1 = true;
						break;
					}
				}
				%>
				<!-- 중분류 1 시작 -->
				<div  data-kt-menu-trigger="click" class="menu-item menu-accordion <%if(openHrMenuFlag1){%>hover show<%}%>">

					<!-- 중분류1 메뉴링크 시작 -->
					<span class="menu-link">
						<span class="menu-icon">
							<i class="ki-duotone ki-address-book fs-2">
								<span class="path1"></span>
								<span class="path2"></span>
								<span class="path3"></span>
							</i>
						</span>
						<span class="menu-title"><%=hrLeftMenu[i]%></span>
						<span class="menu-arrow"></span>
					</span>
					<!-- 중분류1 메뉴링크 종료 -->

					<!-- 소분류 영역 시작 ========================================================-->
					
				<%
				openHrMenuFlag2 = false;
				for(int j=0;j<hrSubLeftMenu[i].length;j++){ 
					if(hrSubLinkLeftMenu[i][j].contains(currentPageName)){
						openHrMenuFlag2 = true;
					}
				}

				if(openHrMenuFlag2 == true){
				%>
					<div class="menu-sub menu-sub-accordion show" style="">
				<%
				}else{
				%>
					<div class="menu-sub menu-sub-accordion" style="display: none; overflow: hidden;">
				<%
				}
				%>
				<%for(int j=0;j<hrSubLeftMenu[i].length;j++){ %>
					<%if(hrSubLinkLeftMenu[i][j].contains("Admin")){%>
					<sec:authorize access="hasAnyRole('HR_ADMIN','SYS_ADMIN')">
					<!-- 소분류1 메뉴 영역 시작 -->
					<div class="menu-item <%if(currentPageName.contains(hrSubLinkLeftMenu[i][j])){%>hover<%}%>">
						<!--begin:Menu link-->
						<a class="menu-link" href="<%=hrSubLinkLeftMenu[i][j]%>">
							<span class="menu-bullet">
								<span class="bullet bullet-dot"></span>
							</span>
							<span class="menu-title"><%=hrSubLeftMenu[i][j]%></span>
						</a>
					</div>
					<!-- 소분류1 메뉴 영역 종료 -->
					</sec:authorize>
					<%}else{%>
					<sec:authorize access="isAuthenticated()">
					<!-- 소분류1 메뉴 영역 시작 -->
					<div class="menu-item <%if(hrSubLinkLeftMenu[i][j].contains(currentPageName)){%>hover<%}%>">
						<!--begin:Menu link-->
						<a class="menu-link" href="<%=hrSubLinkLeftMenu[i][j]%>">
							<span class="menu-bullet">
								<span class="bullet bullet-dot"></span>
							</span>
							<span class="menu-title"><%=hrSubLeftMenu[i][j]%></span>
						</a>
					</div>
					<!-- 소분류1 메뉴 영역 종료 -->
					</sec:authorize>
					<%}%>
				<%} %>
					</div>
					<!-- 소분류 영역 종료 ========================================================-->
				</div>
				<!-- 중분류 1 종료 -->
			<%} %>
			</div>
			<!-- 중분류 영역 종료 ********************************************************************************-->
		</div>		
	</div>		
	<!-- 대분류별 영역 종료 ------------------------------------------------------------------------->