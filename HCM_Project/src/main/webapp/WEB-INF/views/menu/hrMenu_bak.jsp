<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 대분류 순서	
String hrLeftMenu [] = {"조직관리", "증명서관리", "인사발령관리", "근태관리"};
//	
String hrSubLeftMenu [][] = {
		{"부서관리","직위관리","직책관리"},
		{"증명서리스트"},
		{"인사발령조회", "인사발령관리(관리자)"},
		{"출퇴근등록", "출퇴근현황"}
};
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
					<span class="menu-heading fw-bold text-uppercase fs-7 menu-title">인사관리</span>
					<span class="menu-arrow"></span>
			</span>
			<!-- 대분류 타이틀 영역 종료 -->


			<%for(int i=0;i<hrLeftMenu.length;i++){ %>
				<%for(int j=0;j<hrSubLeftMenu[i].length;j++){ %>
					<%=hrLeftMenu[i]%>><%=hrSubLeftMenu[i][j]%><br>
				<%} %>
			<%} %>

			<!-- 중분류 영역 시작 ********************************************************************************-->
			<div class="menu-sub menu-sub-accordion" kt-hidden-height="242" style="display: none; overflow: hidden;">
				
				
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
						<span class="menu-title">조직 관리</span>
						<span class="menu-arrow"></span>
					</span>
					<!-- 중분류1 메뉴링크 종료 -->

					<!-- 소분류 영역 시작 ========================================================-->
					<div class="menu-sub menu-sub-accordion" style="display: none; overflow: hidden;">

						<!-- 소분류1 메뉴 영역 시작 -->
						<div class="menu-item">
							<!--begin:Menu link-->
							<a class="menu-link" href="./hrDept.do">
								<span class="menu-bullet">
									<span class="bullet bullet-dot"></span>
								</span>
								<span class="menu-title">부서관리</span>
							</a>
						</div>
						<!-- 소분류1 메뉴 영역 종료 -->

						<!-- 소분류2 메뉴 영역 시작 -->
						<div class="menu-item">
							<!--begin:Menu link-->
							<a class="menu-link" href="#">
								<span class="menu-bullet">
									<span class="bullet bullet-dot"></span>
								</span>
								<span class="menu-title">직위관리</span>
							</a>
							<!--end:Menu link-->
						</div>
						<!-- 소분류2 메뉴 영역 종료 -->

						<!-- 소분류3 메뉴 영역 시작 -->
						<div class="menu-item">
							<!--begin:Menu link-->
							<a class="menu-link" href="#">
								<span class="menu-bullet">
									<span class="bullet bullet-dot"></span>
								</span>
								<span class="menu-title">직책관리</span>
							</a>
							<!--end:Menu link-->
						</div>
						<!-- 소분류3 메뉴 영역 종료 -->

					</div>
					<!-- 소분류 영역 종료 ========================================================-->
			
				</div>
				<!-- 중분류 1 종료 -->
				

				<!-- 중분류 2 시작 -->
				<div  data-kt-menu-trigger="click" class="menu-item menu-accordion">
					
					<!-- 중분류2 메뉴링크 시작 -->
					<span class="menu-link">
						<span class="menu-icon">
							<i class="ki-duotone ki-address-book fs-2">
								<span class="path1"></span>
								<span class="path2"></span>
								<span class="path3"></span>
							</i>
						</span>
						<span class="menu-title">증명서 관리</span>
						<span class="menu-arrow"></span>
					</span>
					<!-- 중분류2 메뉴링크 종료 -->

					<!-- 소분류 영역 시작 ========================================================-->
					<div class="menu-sub menu-sub-accordion" style="display: none; overflow: hidden;">

						<!-- 소분류1 메뉴 영역 시작 -->
						<div class="menu-item">
							<a class="menu-link" href="./hrDept.do">
								<span class="menu-bullet">
									<span class="bullet bullet-dot"></span>
								</span>
								<span class="menu-title">증명서 리스트</span>
							</a>
						</div>
						<!-- 소분류1 메뉴 영역 종료 -->



					</div>
					<!-- 소분류 영역 종료 ========================================================-->
			
				</div>
				<!-- 중분류 2 종료 -->


				<!-- 중분류 3 시작 -->
				<div  data-kt-menu-trigger="click" class="menu-item menu-accordion">
					
					<!-- 중분류3 메뉴링크 시작 -->
					<span class="menu-link">
						<span class="menu-icon">
							<i class="ki-duotone ki-address-book fs-2">
								<span class="path1"></span>
								<span class="path2"></span>
								<span class="path3"></span>
							</i>
						</span>
						<span class="menu-title">인사발령 관리</span>
						<span class="menu-arrow"></span>
					</span>
					<!-- 중분류3 메뉴링크 종료 -->

					<!-- 소분류 영역 시작 ========================================================-->
					<div class="menu-sub menu-sub-accordion" style="display: none; overflow: hidden;">

						<!-- 소분류1 메뉴 영역 시작 -->
						<div class="menu-item">
							<a class="menu-link" href="./hrDept.do">
								<span class="menu-bullet">
									<span class="bullet bullet-dot"></span>
								</span>
								<span class="menu-title">인사발령 조회</span>
							</a>
						</div>
						<!-- 소분류1 메뉴 영역 종료 -->

						<!-- 소분류2 메뉴 영역 시작 -->
						<div class="menu-item">
							<a class="menu-link" href="./hrDept.do">
								<span class="menu-bullet">
									<span class="bullet bullet-dot"></span>
								</span>
								<span class="menu-title">인사발령 관리(관리자)</span>
							</a>
						</div>
						<!-- 소분류2 메뉴 영역 종료 -->



					</div>
					<!-- 소분류 영역 종료 ========================================================-->
			
				</div>
				<!-- 중분류 3 종료 -->


				<!-- 중분류 4 시작 -->
				<div  data-kt-menu-trigger="click" class="menu-item menu-accordion">
					
					<!-- 중분류3 메뉴링크 시작 -->
					<span class="menu-link">
						<span class="menu-icon">
							<i class="ki-duotone ki-address-book fs-2">
								<span class="path1"></span>
								<span class="path2"></span>
								<span class="path3"></span>
							</i>
						</span>
						<span class="menu-title">근태 관리</span>
						<span class="menu-arrow"></span>
					</span>
					<!-- 중분류3 메뉴링크 종료 -->

					<!-- 소분류 영역 시작 ========================================================-->
					<div class="menu-sub menu-sub-accordion" style="display: none; overflow: hidden;">

						<!-- 소분류1 메뉴 영역 시작 -->
						<div class="menu-item">
							<a class="menu-link" href="./hrDept.do">
								<span class="menu-bullet">
									<span class="bullet bullet-dot"></span>
								</span>
								<span class="menu-title">출퇴근 등록</span>
							</a>
						</div>
						<!-- 소분류1 메뉴 영역 종료 -->

						<!-- 소분류2 메뉴 영역 시작 -->
						<div class="menu-item">
							<a class="menu-link" href="./hrDept.do">
								<span class="menu-bullet">
									<span class="bullet bullet-dot"></span>
								</span>
								<span class="menu-title">출퇴근 현황</span>
							</a>
						</div>
						<!-- 소분류2 메뉴 영역 종료 -->



					</div>
					<!-- 소분류 영역 종료 ========================================================-->
			
				</div>
				<!-- 중분류 4 종료 -->
				

			</div>
			<!-- 중분류 영역 종료 ********************************************************************************-->



		</div>		
	</div>		
	<!-- 대분류별 영역 종료 ------------------------------------------------------------------------->

</html>