<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/menu/headerInfo.jsp" %>
<title>조직관리</title>

<style type="text/css">
.table th { vertical-align:middle; }
.form-check-input.radio {margin-right:10px;}
.form-check-input.radio.last {margin-left:30px;}
</style>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
    function searchAddr() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var roadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 참고 항목 변수

                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('zipcode').value = data.zonecode;
                document.getElementById("addr1").value = roadAddr;
                
            }
        }).open();
    }
</script>


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
						<h1 class="page-heading d-flex text-gray-900 fw-bold fs-3 flex-column justify-content-center my-0">사원등록</h1>
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
								<h3 class="card-title text-gray-800 fw-bold">사원등록</h3>
							</div>
							<div class="separator separator-dashed my-3"></div>	
							<div class="card-body pt-5">

								<form name="form1" method="post" action="/hr/employee/registAdmin.do" enctype="multipart/form-data">
									<div class="table-responsive">
										<table class="table ">
											<tr>
												<th>성명</th>
												<td><input type="text" class="form-control form-control-solid" name="empl_name" maxlength="20" required="required"></td>
											</tr>
											<tr>
												<th>생년월일</th>
												<td><input type="date" class="form-control form-control-solid" name="empl_birth" required="required"></td>
											</tr>
											<tr>
												<th>성별</th>
												<td>
													<input type="radio" class="form-check-input radio" name=empl_gender value="M" checked>남성
													<input type="radio" class="form-check-input radio last" name=empl_gender value="F">여성
												</td>
											</tr>
											<tr>
												<th>이메일</th>
												<td><input type="text" class="form-control form-control-solid" name="empl_email" maxlength="50" required="required"></td>
											</tr>
											<tr>
												<th>전화번호</th>
												<td><input type="text" class="form-control form-control-solid" name="empl_phone" maxlength="15" required="required"></td>
											</tr>
											<tr>
												<th>내선번호</th>
												<td><input type="text" class="form-control form-control-solid" name="empl_tel" maxlength="3"></td>
											</tr>
											<tr>
												<th>팩스번호</th>
												<td><input type="text" class="form-control form-control-solid" name="empl_fax" maxlength="15"></td>
											</tr>
											<tr>
												<th>입사년도</th>
												<td><input type="date" class="form-control form-control-solid" name="empl_joindate" required="required"></td>
											</tr>
											<!-- 
											<tr>
												<th>우편번호</th>
												<td><input type="text" name="zipcode" id="zipcode" readonly required="required"> <button type="button" class="btn btn-info" onclick="searchAddr()">주소찾기</button></td>
											</tr>
											<tr>
												<th>주소</th>
												<td><input type="text" name="addr1" id="addr1" readonly required="required"></td>
											</tr>
											<tr>
												<th>상세주소</th>
												<td><input type="text" name="addr2" id="addr2" maxlength="100" required="required"></td>
											</tr>
											 -->
											<tr>
												<th>부서</th>
												<td>
													<select name="empl_dept_cd" required="required" class="form-select">
														<option value="">==== 부서선택 ====</option>
														<c:forEach items="${deptList}" var="dept">
														<option value="${dept.coco_cd}">${dept.coco_name}</option>
														</c:forEach>
													</select>
												</td>
											</tr>
											<tr>
												<th>직위</th>
												<td>
													<select name="empl_rank_cd" required="required" class="form-select">
														<option value="">==== 직위선택 ====</option>
														<c:forEach items="${rankList}" var="rank">
														<option value="${rank.coco_cd}">${rank.coco_name}</option>
														</c:forEach>
													</select>
												</td>
											</tr>
											<tr>
												<th>직책</th>
												<td>
													<select name="empl_position_cd" class="form-select">
														<option value="">==== 직책선택 ====</option>
														<c:forEach items="${positionList}" var="position">
														<option value="${position.coco_cd}">${position.coco_name}</option>
														</c:forEach>
													</select>
												</td>
											</tr>
											<tr>
												<th>증명사진</th>
												<td><input type="file" class="form-control form-control-solid" name="empl_picture"></td>
											</tr>
											<tr>
												<td colspan="2">
													<button class="btn btn-primary me-10" id="kt_button_1">
													    <span class="indicator-label">
													        Submit
													    </span>
													    <span class="indicator-progress">
													        Please wait... <span class="spinner-border spinner-border-sm align-middle ms-2"></span>
													    </span>
													</button>
												</td>
											</tr>
										</table>
									</div>
								</form>

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