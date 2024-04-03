<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
// 메뉴명
String smLeftMenu [] = {"캘린더","공지사항"};
// 메뉴링크
String smLeftMenuLink [] = {"/sm/calendar.do", "/sm/getAllGobo.do"};

String smUri = request.getRequestURI();
String currentSmPageName = smUri.substring(smUri.lastIndexOf("/") + 1).replace(".jsp","");
Boolean openSmMenuFlag = false;
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
					<span class="menu-heading fw-bold text-uppercase fs-7 menu-title">일정관리</span>
					<span class="menu-arrow"></span>
			</span>
			<!-- 대분류 타이틀 영역 종료 -->


			<!-- 중분류 영역 시작 ********************************************************************************-->
			<div class="menu-sub menu-sub-accordion  show">
			<%for(int i=0;i<smLeftMenu.length;i++){ %>
				<!-- 중분류 1 시작 -->
				<div class="menu-item menu-accordion">
					<a href="<%=smLeftMenuLink[i]%>">
						<!-- 중분류1 메뉴링크 시작 -->
						<span class="menu-link">
							<span class="menu-icon">
								<i class="ki-duotone ki-address-book fs-2">
									<span class="path1"></span>
									<span class="path2"></span>
									<span class="path3"></span>
								</i>
							</span>
							<span class="menu-title"><%=smLeftMenu[i]%></span>
							<span class="menu-title"></span>
						</span>
						<!-- 중분류1 메뉴링크 종료 -->
					</a>
				</div>
				<!-- 중분류 1 종료 -->
			<%} %>
			</div>
			<!-- 중분류 영역 종료 ********************************************************************************-->
		</div>		
	</div>		
	<!-- 대분류별 영역 종료 ------------------------------------------------------------------------->