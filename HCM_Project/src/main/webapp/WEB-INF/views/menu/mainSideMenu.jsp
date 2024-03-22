<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<!--begin::Wrapper-->
				<div>
					<!--begin::Sidebar-->
					<div id="kt_app_sidebar" class="app-sidebar flex-column" data-kt-drawer="true" data-kt-drawer-name="app-sidebar" data-kt-drawer-activate="{default: true, lg: false}" data-kt-drawer-overlay="true" data-kt-drawer-width="225px" data-kt-drawer-direction="start" data-kt-drawer-toggle="#kt_app_sidebar_mobile_toggle">
						<!--begin::Logo-->
						<div class="app-sidebar-logo px-6" id="kt_app_sidebar_logo">
							<!--begin::Logo image-->
							<a href="/mainTmp.do">
								<img alt="HCM" src="/image/logo.jpg" class="h-30px app-sidebar-logo-default">
							</a>
							<!--end::Logo image-->
						</div>
						<!--end::Logo-->
						<!--begin::sidebar menu-->
							<!--begin::Menu wrapper-->
							<div class="app-sidebar-wrapper">
									<!--begin::Scroll wrapper-->
									<div class="hover-scroll-y my-5 mx-3" data-kt-scroll="true" data-kt-scroll-activate="true" data-kt-scroll-height="auto" data-kt-scroll-dependencies="#kt_app_sidebar_logo, #kt_app_sidebar_footer" data-kt-scroll-wrappers="#kt_app_sidebar_menu" data-kt-scroll-offset="5px" data-kt-scroll-save-state="true" style="height: 1097px;">
									
									<div class="card mb-5 mb-xl-8">
										<!--begin::Body-->
										<div class="card-body pt-15 px-0">
											<!--begin::Member-->
											<div class="d-flex flex-column text-center mb-9 px-9">
												<!--begin::Photo-->
												<div class="symbol symbol-80px symbol-lg-150px mb-4">
													<img src="${userInfoVo.empl_picture_str}" alt="사진">
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
											<!--begin::Row-->
											<div class="row px-9 mb-4">
												<!--begin::Col-->
												<div class="col-md-4 text-center">
													<div class="text-gray-800 fw-bold fs-3">
														<span class="m-0 counted" data-kt-countup="true"
															data-kt-countup-value="642" data-kt-initialized="1">642</span>
													</div>
													<span class="text-gray-500 fs-8 d-block fw-bold">POSTS</span>
												</div>
												<!--end::Col-->
												<!--begin::Col-->
												<div class="col-md-4 text-center">
													<div class="text-gray-800 fw-bold fs-3">
														<span class="m-0 counted" data-kt-countup="true"
															data-kt-countup-value="24" data-kt-initialized="1">24</span>K
													</div>
													<span class="text-gray-500 fs-8 d-block fw-bold">FOLLOWERS</span>
												</div>
												<!--end::Col-->
												<!--begin::Col-->
												<div class="col-md-4 text-center">
													<div class="text-gray-800 fw-bold fs-3">
														<span class="m-0 counted" data-kt-countup="true"
															data-kt-countup-value="12" data-kt-initialized="1">12</span>K
													</div>
													<span class="text-gray-500 fs-8 d-block fw-bold">FOLLOWING</span>
												</div>
												<!--end::Col-->
											</div>
											<!--end::Row-->
											<!--begin::Navbar-->
											<div class="m-0">
												<!--begin::Navs-->
												<ul class="nav nav-pills nav-pills-custom flex-column border-transparent fs-5 fw-bold">
													<!--begin::Nav item-->
													<li class="nav-item mt-5"><a
														class="nav-link text-muted text-active-primary ms-0 py-0 me-10 ps-9 border-0"
														href="pages/social/feeds.html"> <i
															class="ki-duotone ki-row-horizontal fs-3 text-muted me-3"> <span
																class="path1"></span> <span class="path2"></span>
														</i>Feeds <!--begin::Bullet--> <span
															class="bullet-custom position-absolute start-0 top-0 w-3px h-100 bg-primary rounded-end"></span>
															<!--end::Bullet--></a></li>
													<!--end::Nav item-->
													<!--begin::Nav item-->
													<li class="nav-item mt-5"><a
														class="nav-link text-muted text-active-primary ms-0 py-0 me-10 ps-9 border-0"
														href="pages/social/activity.html"> <i
															class="ki-duotone ki-chart-simple-2 fs-3 text-muted me-3"> <span
																class="path1"></span> <span class="path2"></span> <span
																class="path3"></span> <span class="path4"></span>
														</i>Activity <!--begin::Bullet--> <span
															class="bullet-custom position-absolute start-0 top-0 w-3px h-100 bg-primary rounded-end"></span>
															<!--end::Bullet--></a></li>
													<!--end::Nav item-->
													<!--begin::Nav item-->
													<li class="nav-item mt-5"><a
														class="nav-link text-muted text-active-primary ms-0 py-0 me-10 ps-9 border-0"
														href="pages/social/followers.html"> <i
															class="ki-duotone ki-profile-circle fs-3 text-muted me-3"> <span
																class="path1"></span> <span class="path2"></span> <span
																class="path3"></span>
														</i>Followers <!--begin::Bullet--> <span
															class="bullet-custom position-absolute start-0 top-0 w-3px h-100 bg-primary rounded-end"></span>
															<!--end::Bullet--></a></li>
													<!--end::Nav item-->
												</ul>
												<!--begin::Navs-->
											</div>
											<!--end::Navbar-->
										</div>
										<!--end::Body-->
									</div>
									
									<div class="card shadow-sm">
									    <div class="card-body">
									    	<a href="/hr/commute/registCommuteOk.do" class="btn btn-primary fs-3x">
												<i class="ki-duotone ki-exit-right fs-5x">
													<span class="path1"></span>
													<span class="path2"></span>
													<span class="path3"></span>
													<span class="path4"></span>
												</i>
												퇴근등록
											</a>
									    </div>
									</div>
								</div>
								<!--end::Scroll wrapper-->
							</div>
							<!--end::Menu wrapper-->
							</div>
							</div>
</html>