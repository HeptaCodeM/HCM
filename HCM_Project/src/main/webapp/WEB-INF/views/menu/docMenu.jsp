<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%
// 메뉴명
String docLeftMenu [] = {"내결재관리", "기안서작성", "임시저장", "결재설정" , "서명관리" , "관리자메뉴"};
// 메뉴링크
String docLeftMenuLink [] = {"/doc/docBox.do", "/doc/writeDoc.do", "/doc/tempDocs.do", "/doc/signFavo.do" ,"/doc/signManagement.do","/doc/templateAdmin.do"};

String docUri = request.getRequestURI();
String currentDocPageName = docUri.substring(docUri.lastIndexOf("/") + 1).replace(".jsp","");
Boolean openDocMenuFlag = true;
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
					<span class="menu-heading fw-bold text-uppercase fs-7 menu-title">전자결재</span>
					<span class="menu-arrow"></span>
			</span>
			<!-- 대분류 타이틀 영역 종료 -->


			<!-- 중분류 영역 시작 ********************************************************************************-->
			<div class="menu-sub menu-sub-accordion show">
			<%for(int i=0;i<docLeftMenu.length;i++){ %>
				<%if(docLeftMenuLink[i].contains("Admin")){%>
				<sec:authorize access="hasAnyRole('DOC_ADMIN','SYS_ADMIN')">
				<!-- 중분류 1 시작 -->
				<div class="menu-item menu-accordion">
					<a href="<%=docLeftMenuLink[i]%>">
						<!-- 중분류1 메뉴링크 시작 -->
						<span class="menu-link">
							<span class="menu-icon">
								<i class="ki-duotone ki-address-book fs-2">
									<span class="path1"></span>
									<span class="path2"></span>
									<span class="path3"></span>
								</i>
							</span>
							<span class="menu-title"><%=docLeftMenu[i]%></span>
							<span class="menu-title"></span>
						</span>
						<!-- 중분류1 메뉴링크 종료 -->
					</a>
				</div>
				<!-- 중분류 1 종료 -->
				</sec:authorize>
				<%}else{%>
				<sec:authorize access="isAuthenticated()">
				<!-- 중분류 1 시작 -->
				<div class="menu-item menu-accordion">
					<a href="<%=docLeftMenuLink[i]%>">
						<!-- 중분류1 메뉴링크 시작 -->
						<span class="menu-link">
							<span class="menu-icon">
								<i class="ki-duotone ki-address-book fs-2">
									<span class="path1"></span>
									<span class="path2"></span>
									<span class="path3"></span>
								</i>
							</span>
							<span class="menu-title"><%=docLeftMenu[i]%></span>
							<span class="menu-title"></span>
						</span>
						<!-- 중분류1 메뉴링크 종료 -->
					</a>
				</div>
				<!-- 중분류 1 종료 -->
				</sec:authorize>
				<%} %>
			<%} %>
			</div>
			<!-- 중분류 영역 종료 ********************************************************************************-->
		</div>
	</div>			
	<!-- 대분류별 영역 종료 ------------------------------------------------------------------------->