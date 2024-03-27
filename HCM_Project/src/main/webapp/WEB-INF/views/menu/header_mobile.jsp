<%@ page language="java" contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div id="kt_app_header" class="app-header">
			<div
				class="app-container container-fluid d-flex align-items-stretch justify-content-between"
				id="kt_app_header_container">
				<div class="d-flex align-items-center flex-grow-1 flex-lg-grow-0">
					<a href="javascript:void(0);" class="d-lg-none">
						<img alt="HCM" src="/image/logo.jpg" class="theme-light-show h-30px">
					</a>
				</div>
				<div class="app-navbar flex-shrink-0">

					<div class="app-navbar-item ms-2 ms-1 ms-lg-3" id="kt_header_user_menu_toggle">
						<div class="cursor-pointer symbol symbol-35px symbol-md-40px"
							data-kt-menu-trigger="{default: 'click', lg: 'hover'}"
							data-kt-menu-attach="parent" data-kt-menu-placement="bottom-end">
							<img src="${userInfoVo.empl_picture_str}" alt="user">
						</div>


						<!--begin::내정보 menu 시작-->
						<div class="menu menu-sub menu-sub-dropdown menu-column menu-rounded menu-gray-800 menu-state-bg menu-state-color fw-semibold py-4 fs-6 w-275px" data-kt-menu="true">
							<!--begin::Menu item-->
							<div class="menu-item px-3">
								<div class="menu-content d-flex align-items-center px-3">
									<!--begin::Avatar-->
									<div class="symbol symbol-50px me-5">
										<img alt="Logo" src="${userInfoVo.empl_picture_str}" />
									</div>
									<!--end::Avatar-->
									<!--begin::Username-->
									<div class="d-flex flex-column">
										<div class="fw-bold d-flex align-items-center fs-5">${userInfoVo.empl_name}
										<span class="badge badge-light-success fw-bold fs-8 px-2 py-1 ms-2">${userInfoVo.empl_rank_nm}</span></div>
										<div class="fw-semibold text-muted fs-7">${userInfoVo.empl_dept_nm}</div>
										<a href="mailto:${userInfoVo.empl_email}" class="fw-semibold text-muted text-hover-primary fs-7">${userInfoVo.empl_email}</a>
									</div>
									<!--end::Username-->
								</div>
							</div>
							<!--end::Menu item-->

							<!--begin::Menu separator-->
							<div class="separator my-2"></div>
							<!--end::Menu separator-->
							<!--begin::Menu item-->
							<div class="menu-item px-5">
								<a href="/logout" class="menu-link px-5">로그아웃</a>
							</div>
							<!--end::Menu item-->
						</div>
						<!--end::내정보 menu 종료-->

					</div>
				</div>
			</div>
		</div>

		<!-- ------------------------------ 알림 영역 사작 ------------------------------ -->
		<div id="kt_activities" class="bg-body drawer drawer-end drawer-end" data-kt-drawer="true" data-kt-drawer-name="activities" data-kt-drawer-activate="true" data-kt-drawer-overlay="true" data-kt-drawer-width="{default:'300px', 'lg': '720px'}" data-kt-drawer-direction="end" data-kt-drawer-toggle="#kt_activities_toggle" data-kt-drawer-close="#kt_activities_close" style="width: 600px !important;">
			<div class="card shadow-none border-0 rounded-0">
				<div class="card-header" id="kt_activities_header">
					<h3 class="card-title fw-bold text-gray-900">알림 목록</h3>
					<div class="card-toolbar">
						<div class="min-w-120px pe-2">
							<button type="button" class="btn btnSm">
								<span class="badge badge-light text-muted" onclick="offAlarmAll()">전체끄기</span>
							</button>
						</div>
						<button type="button" class="btn btn-sm btn-icon btn-active-light-primary me-n5" id="kt_activities_close">
							<i class="ki-duotone ki-cross fs-1">
								<span class="path1"></span>
								<span class="path2"></span>
							</i>
						</button>
						<c:if test="${userInfoVo ne null}">
							<input type="hidden" id="id" value="${userInfoVo.empl_id}">
							<input type="hidden" id="myName" value="${userInfoVo.empl_name}">
							<input type="hidden" id="myDept" value="${userInfoVo.empl_dept_nm}">
							<input type="hidden" id="myRank" value="${userInfoVo.empl_rank_nm}">
							<input type="hidden" id="myPic" value="${userInfoVo.empl_picture_str }">
						</c:if>
					</div>
				</div>
				<div class="card-body position-relative" id="kt_activities_body">
					<div id="kt_activities_scroll" class="position-relative scroll-y me-n5 pe-5" data-kt-scroll="true" data-kt-scroll-height="auto" data-kt-scroll-wrappers="#kt_activities_body" data-kt-scroll-dependencies="#kt_activities_header, #kt_activities_footer" data-kt-scroll-offset="5px" style="height: 1065px;">
						
						
						<div class="timeline timeline-border-dashed" id="timeLines">
					
							<!-- 알림 Ajax 삽입 영역 -->
							
<!-- 							<div class="timeline-item"> -->
<!-- 								<div class="timeline-line"></div> -->
<!-- 								<div class="timeline-icon"> -->
<!-- 									<i class="ki-duotone ki-calendar fs-2 text-gray-500"> -->
<!-- 										<span class="path1"></span> -->
<!-- 										<span class="path2"></span> -->
<!-- 									</i> -->
<!-- 								</div> -->
<!-- 								<div class="timeline-content mb-10 mt-n1"> -->
<!-- 									<div class="pe-3 mb-5"> -->
<!-- 										<div class="fs-5 fw-semibold mb-2">일정알림</div> -->
<!-- 										<div class="d-flex align-items-center mt-1 fs-6"> -->
<!-- 											<div class="text-muted me-2 fs-7">2024-03-15 16:23:07</div> -->
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 									<div class="overflow-auto pb-5"> -->
<!-- 										<div class="d-flex align-items-center border border-dashed border-gray-300 rounded min-w-600px p-7"> -->
										
<!-- 											<a href="apps/projects/project.html" class="fs-5 text-gray-900 text-hover-primary fw-semibold w-300px min-w-200px"> -->
<!-- 											AM 10:00 - 프로젝트 일정 중간 회의 -->
<!-- 											</a> -->
										
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 							</div> -->
						</div>
					</div>
				</div>
			</div>
		</div>
<!--    ------------------------------ 알림영역 끝 ------------------------------ -->

		<!-- ------------------------------ 채팅 영역 사작 ------------------------------ -->
		<div id="kt_drawer_chat" class="bg-body drawer drawer-end" data-kt-drawer="true" data-kt-drawer-name="chat" data-kt-drawer-activate="true" data-kt-drawer-overlay="true" data-kt-drawer-width="{default:'400px', 'md': '800px'}" data-kt-drawer-direction="end" data-kt-drawer-toggle="#kt_drawer_chat_toggle" data-kt-drawer-close="#kt_drawer_chat_close" style="width: 800px !important; flex-direction: row !important;">
		<div style="display: flex; flex-direction: row">
			<div class="card w-100 border-0 rounded-0" id="kt_drawer_chat_messenger" style="width: 500px !important;">
				<div class="card-header pe-5" id="kt_drawer_chat_messenger_header">
					<div class="card-title">
						<div class="d-flex justify-content-center flex-column me-3">
							<a class="fs-4 fw-bold text-gray-900 text-hover-primary me-1 mb-2 lh-1">대화 목록</a>
						</div>
					</div>
				</div>
				<div id="kt_modal_users_search_handler" data-kt-search-keypress="true" data-kt-search-min-length="2" data-kt-search-enter="enter" data-kt-search-layout="inline" data-kt-search="true">
							<form data-kt-search-element="form" class="w-100 position-relative mb-5" autocomplete="off">
								<input type="hidden">
								<i class="ki-duotone ki-magnifier fs-2 fs-lg-1 text-gray-500 position-absolute top-50 ms-5 translate-middle-y">
									<span class="path1"></span>
									<span class="path2"></span>
								</i>
								<input type="text" class="form-control form-control-lg form-control-solid px-15" name="search" value="" placeholder="이름이나 이메일로 검색하세요" data-kt-search-element="input">
								<span class="position-absolute top-50 end-0 translate-middle-y lh-0 d-none me-5" data-kt-search-element="spinner">
									<span class="spinner-border h-15px w-15px align-middle text-muted"></span>
								</span>
								<span class="btn btn-flush btn-active-color-primary position-absolute top-50 end-0 translate-middle-y lh-0 me-5 d-none" data-kt-search-element="clear">
									<i class="ki-duotone ki-cross fs-2 fs-lg-1 me-0">
										<span class="path1"></span>
										<span class="path2"></span>
									</i>
								</span>
							</form>
							<div class="py-5" style="height: 760px; overflow: auto;" id="searchMainDiv">
									
									<!-- ------------------------------ 유저목록 Ajax 삽입 영역 ------------------------------ -->	
									
								<div class="border-bottom border-gray-300 border-bottom-dashed"></div>
							</div>
						</div>
			</div>
			<div class="card w-100 border-0 rounded-0" id="kt_drawer_chat_messenger">
				<div class="card-header pe-5" id="chatHeaderDiv">
					<div class="card-title">
						<div class="d-flex justify-content-center flex-column me-3">
							<a class="fs-4 fw-bold text-gray-900 text-hover-primary me-1 mb-2 lh-1">대화상대를 선택해주세요</a>
<!-- 							<div class="mb-0 lh-1"> -->
<!-- 								<span class="badge badge-success badge-circle w-10px h-10px me-1"></span> -->
<!-- 								<span class="fs-7 fw-semibold text-muted">접속중</span> -->
<!-- 							</div> -->
						</div>
					</div>
					<div class="card-toolbar">
						<div class="btn btn-sm btn-icon btn-active-color-primary" id="kt_drawer_chat_close">
							<i class="ki-duotone ki-cross-square fs-2">
								<span class="path1"></span>
								<span class="path2"></span>
							</i>
						</div>
					</div>
				</div>
				<div class="card-body" id="kt_drawer_chat_messenger_body">
					<div id="mainDiv" class="scroll-y me-n5 pe-5" data-kt-element="messages" data-kt-scroll="true" data-kt-scroll-activate="true" data-kt-scroll-height="auto" data-kt-scroll-dependencies="#kt_drawer_chat_messenger_header, #kt_drawer_chat_messenger_footer" data-kt-scroll-wrappers="#kt_drawer_chat_messenger_body" data-kt-scroll-offset="0px" style="height: 1009px;">
					
					<!-- ------------------------------ 대화 Ajax 삽입 영역 ------------------------------ -->
					
					</div>
				</div>
				<div class="card-footer pt-4" id="kt_drawer_chat_messenger_footer">
					<textarea class="form-control form-control-flush mb-3" rows="1" data-kt-element="input" id="message"
					placeholder="내용을 입력하세요"></textarea>
					<div class="" style="text-align: right;">
						<input type="button" value="전송" id="send" class="btn btnSm btn-primary">
					</div>
				</div>
			</div>
			</div>
		</div>
		<!-- ------------------------------ 채팅 영역 끝 ------------------------------ -->

<div class="modal fade" id="kt_modal_users_search" tabindex="-1" style="display: none;" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered mw-650px">
				<div class="modal-content">
					<div class="modal-header pb-0 border-0 justify-content-end">
						<div class="btn btn-sm btn-icon btn-active-color-primary" data-bs-dismiss="modal">
							<i class="ki-duotone ki-cross fs-1">
								<span class="path1"></span>
								<span class="path2"></span>
							</i>
						</div>
					</div>
					<div class="modal-body scroll-y mx-5 mx-xl-18 pt-0 pb-15">
						<div class="text-center mb-13">
							<h1 class="mb-3">대화상대 검색</h1>
						</div>
						
							<div class="py-5">
								<div data-kt-search-element="suggestions">
									<h3 class="fw-semibold mb-5">자주찾는 상대</h3>
									<div class="mh-375px scroll-y me-n7 pe-7">
										<div class="d-flex align-items-center p-3 rounded bg-state-light bg-state-opacity-50 mb-1">
											<div class="symbol symbol-35px symbol-circle me-5">
												<img alt="Pic" src="/assets/media/avatars/300-6.jpg">
											</div>
											<div class="fw-semibold">
												<span class="fs-6 text-gray-800 me-2">이름</span>
												<span class="badge badge-light">부서</span>
											</div>
										</div>
										<a href="#" class="d-flex align-items-center p-3 rounded bg-state-light bg-state-opacity-50 mb-1">
										</a>
									</div>
								</div>
								
<!-- 								검색결과영역 -->
								<div data-kt-search-element="results" class="d-none">
									<div class="mh-375px scroll-y me-n7 pe-7">
<!-- 									인덱스 -->
										<div class="rounded d-flex flex-stack bg-active-lighten p-4" data-user-id="0">
											<div class="d-flex align-items-center">
												<div class="symbol symbol-35px symbol-circle">
<!-- 												프로필사진 -->
													<img alt="Pic" src="/assets/media/avatars/300-6.jpg">
												</div>
												<div class="ms-5">
<!-- 												이름 -->
													<a class="fs-5 fw-bold text-gray-900 text-hover-primary mb-2">Emma Smith</a>
<!-- 													이메일 -->
													<div class="fw-semibold text-muted">smith@kpmg.com</div>
												</div>
											</div>
											<div class="ms-2 w-100px">
												<span class="badge badge-light">부서</span>
											</div>
										</div>
										<div class="border-bottom border-gray-300 border-bottom-dashed"></div>
										
									</div>
								</div>
<!-- 								검색결과 없을때 -->
								<div data-kt-search-element="empty" class="text-center d-none">
									<div class="fw-semibold py-10">
										<div class="text-gray-600 fs-3 mb-2">검색된 결과가 없습니다</div>
										<div class="text-muted fs-6">이름이나 이메일 정확한지 확인해보세요</div>
									</div>
									<div class="text-center px-5">
										<img src="/assets/media/illustrations/sketchy-1/1.png" alt="" class="w-100 h-200px h-sm-325px">
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>