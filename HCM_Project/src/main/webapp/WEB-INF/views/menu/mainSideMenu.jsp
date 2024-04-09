<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<!--begin::Wrapper-->
				<div>
					<!--begin::Sidebar-->
					<div id="kt_app_sidebar" class="app-sidebar flex-column" data-kt-drawer="true" data-kt-drawer-name="app-sidebar" data-kt-drawer-activate="{default: true, lg: false}" data-kt-drawer-overlay="true" data-kt-drawer-width="225px" data-kt-drawer-direction="start" data-kt-drawer-toggle="#kt_app_sidebar_mobile_toggle">
						<!--begin::Logo-->
						<div class="app-sidebar-logo px-6" style="justify-content: center;" id="kt_app_sidebar_logo">
							<!--begin::Logo image-->
							<a href="/">
								<img class="mainMenuLog" alt="HCM" src="/image/logo-removebg-preview.png">
							</a>
							<!--end::Logo image-->
						</div>
						<!--end::Logo-->
						<!--begin::sidebar menu-->
							<!--begin::Menu wrapper-->
							<div class="app-sidebar-wrapper">
									<!--begin::Scroll wrapper-->
									<div class="hover-scroll-y my-5 mx-3" data-kt-scroll="true" data-kt-scroll-activate="true" data-kt-scroll-height="auto" data-kt-scroll-dependencies="#kt_app_sidebar_logo, #kt_app_sidebar_footer" data-kt-scroll-wrappers="#kt_app_sidebar_menu" data-kt-scroll-offset="5px" data-kt-scroll-save-state="true" style="height: 1097px;">
									
									<div class="card mb-5 mb-xl-8 sideWidth">
										<!--begin::Body-->
										<div class="card-body pt-15 px-0">
											<!--begin::Member-->
											<div class="d-flex flex-column text-center mb-9 px-9">
												<!--begin::Photo-->
												<div class="symbol symbol-80px symbol-lg-150px mb-4" style="width:150px; height:180px;" alt="사진">
													<div class="emp-pic" style="background-image:url('${userInfoVo.empl_picture_str}')"></div>
												</div>
												<!--end::Photo-->
												<!--begin::Info-->
												<div class="text-center">
													<!--begin::Name-->
													<a class="text-gray-800 fw-bold text-hover-primary fs-4">
														${userInfoVo.empl_rank_nm}&nbsp;${userInfoVo.empl_name}
													</a>
													<!--end::Name-->
													<!--begin::Position-->
													<span class="text-muted d-block fw-semibold">
														${userInfoVo.empl_dept_nm}&nbsp;${userInfoVo.empl_position_nm}
													</span>
													<!--end::Position-->
												</div>
												<!--end::Info-->
											</div>
											<!--end::Member-->
											
											<!--begin::Navbar-->
											<div class="m-0">
												<!--begin::Navs-->
												<ul class="nav nav-pills nav-pills-custom flex-column border-transparent fs-5 fw-bold">
												
													<!--begin::Nav item-->
													<li class="nav-item mt-5">
														<a class="nav-link text-muted text-active-primary ms-0 py-0 border-0"> 
															남은연차 : ${rest_holiday}
														</a>
													</li>
													<!--end::Nav item-->
													
													<!--begin::Nav item-->
													<li class="nav-item mt-5">
														<a class="nav-link text-muted text-active-primary ms-0 py-0 border-0"> 
															진행중인 결재건수 : ${ingDoc}
														</a>
													</li>
													<!--end::Nav item-->
													
												</ul>
												<!--begin::Navs-->
											</div>
											<!--end::Navbar-->
										</div>
										<!--end::Body-->
									</div>
									
									<div class="card mb-5 mb-xl-8 sideWidth">
										<div id="nowTime" class="text-gray-700" style="text-align: center;">
											시간
										</div>
									</div>
									
									<div class="card shadow-sm sideWidth">
										<div style="padding: 10px;">
											<c:if test="${commuteInTime eq null || commuteInTime eq ''}">
												<a href="/hr/home/registCommuteOkMain.do" class="btn btn-primary exitBtn">
													<i class="ki-duotone ki-entrance-left fs-2x">
														<span class="path1"></span>
														<span class="path2"></span>
														<span class="path3"></span>
														<span class="path4"></span>
													</i>
													출근등록
												</a>
											</c:if>
											<c:if test="${commuteInTime ne null && commuteInTime ne ''}">
												<a href="/hr/home/registCommuteOkMain.do" class="btn btn-primary exitBtn">
													<i class="ki-duotone ki-exit-right fs-2x">
														<span class="path1"></span>
														<span class="path2"></span>
														<span class="path3"></span>
														<span class="path4"></span>
													</i>
													퇴근등록
												</a>
											</c:if>
										</div>
									</div>
								</div>
								<!--end::Scroll wrapper-->
							</div>
							<!--end::Menu wrapper-->
							</div>
							</div>
</html>