<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
// 중분류 순서
String smLeftMenu [] = {"대분류1", "대분류2"};
// 소분류 메뉴명
String smSubLeftMenu [][] = {
		{"메뉴1","메뉴2"},		//smLeftMenu[0]
		{"메뉴1","메뉴2"}		//smLeftMenu[1]
};
//소분류 링크
String smSubLinkLeftMenu [][] = {
		{"#","#"},					//smLeftMenu[0]
		{"#","#"}					//smLeftMenu[1]
};

String smUri = request.getRequestURI();
String currentSmPageName = smUri.substring(smUri.lastIndexOf("/") + 1).replace(".jsp","");
Boolean openSmMenuFlag = false;
%>
<!DOCTYPE html>
<html>
	<!-- 대분류별 영역 시작 -->
	<div class="menu menu-column menu-rounded menu-sub-indention fw-semibold" id="#kt_app_sidebar_menu" data-kt-menu="true" data-kt-menu-expand="false">
		
		<div data-kt-menu-trigger="click" class="menu-item pt-5 menu-item menu-accordion">

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
			<div class="menu-sub menu-sub-accordion  show" style="display: none; overflow: hidden;">
			<%for(int i=0;i<smLeftMenu.length;i++){ %>
				<!-- 중분류 1 시작 -->
				<div  data-kt-menu-trigger="click" class="menu-item menu-accordion">

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
						<span class="menu-arrow"></span>
					</span>
					<!-- 중분류1 메뉴링크 종료 -->

					<!-- 소분류 영역 시작 ========================================================-->
					
				<%
				openSmMenuFlag = false;
				for(int j=0;j<smSubLeftMenu[i].length;j++){ 
					if(smSubLinkLeftMenu[i][j].indexOf(currentSmPageName)>=0){
						openSmMenuFlag = true;
					}
				}

				if(openSmMenuFlag == true){
				%>
					<div class="menu-sub menu-sub-accordion show" style="">
				<%
				}else{
				%>
					<div class="menu-sub menu-sub-accordion" style="display: none; overflow: hidden;">
				<%
				}
				%>
				<%for(int j=0;j<smSubLeftMenu[i].length;j++){ %>
						<!-- 소분류1 메뉴 영역 시작 -->
						<div class="menu-item">
							<!--begin:Menu link-->
							<a class="menu-link" href="<%=smSubLinkLeftMenu[i][j]%>">
								<span class="menu-bullet">
									<span class="bullet bullet-dot"></span>
								</span>
								<span class="menu-title"><%=smSubLeftMenu[i][j]%></span>
							</a>
						</div>
						<!-- 소분류1 메뉴 영역 종료 -->
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
</html>