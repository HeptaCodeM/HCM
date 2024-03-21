<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/menu/headerInfo.jsp" %>
<script type="text/javascript">
	
		var orgComp_name = "";
		var orgComp_num = "";
		var orgComp_ceo_name = "";
		var orgComp_tel = "";
		var orgComp_email = "";
		var orgComp_fax = "";
		var orgComp_addr1 = "";
		var orgComp_addr2 = "";
		var orgComp_post = "";		
		
	window.onload = function(){
		orgComp_name = document.getElementById("comp_name").value;
		orgComp_num = document.getElementById("comp_num").value;
		orgComp_ceo_name = document.getElementById("comp_ceo_name").value;
		orgComp_tel = document.getElementById("comp_tel").value;
		orgComp_email = document.getElementById("comp_email").value;
		orgComp_fax = document.getElementById("comp_fax").value;
		orgComp_addr1 = document.getElementById("comp_addr1").value;
		orgComp_addr2 = document.getElementById("comp_addr2").value;
		orgComp_post = document.getElementById("comp_post").value;	
	}
	
document.onkeydown = function(e) {
	key = (e) ? e.keyCode : event.keyCode;
	console.log("동작합니다");
	const chComp_name = document.getElementById("comp_name").value;
	const chComp_num = document.getElementById("comp_num").value;
	const chComp_ceo_name = document.getElementById("comp_ceo_name").value;
	const chComp_tel = document.getElementById("comp_tel").value;
	const chComp_email = document.getElementById("comp_email").value;
	const chComp_fax = document.getElementById("comp_fax").value;
	const chComp_addr1 = document.getElementById("comp_addr1").value;
	const chComp_addr2 = document.getElementById("comp_addr2").value;
	const chComp_post = document.getElementById("comp_post").value;	

	if(orgComp_name != chComp_name ||
		      orgComp_num != chComp_num ||
		      orgComp_ceo_name != chComp_ceo_name ||
		      orgComp_tel != chComp_tel ||
		      orgComp_email != chComp_email ||
		      orgComp_fax != chComp_fax ||
		      orgComp_addr1 != chComp_addr1 ||
		      orgComp_addr2 != chComp_addr2 ||
		      orgComp_post != chComp_post){
			if(key==116) {
				if(e) {
				e.preventDefault();
				} else {
				event.keyCode = 0;
				event.returnValue = false;
				}
			}
		}
	
}
	
	
	function valChk() {
		
		
		var reComp_name = document.getElementById("comp_name").value;
		var reComp_num = document.getElementById("comp_num").value;
		var reComp_ceo_name = document.getElementById("comp_ceo_name").value;
		var reComp_tel = document.getElementById("comp_tel").value;
		var reComp_email = document.getElementById("comp_email").value;
		var reComp_fax = document.getElementById("comp_fax").value;
		var reComp_addr1 = document.getElementById("comp_addr1").value;
		var reComp_addr2 = document.getElementById("comp_addr2").value;
		var reComp_post = document.getElementById("comp_post").value;
		
		console.log("=============================");
		console.log(orgComp_name);
		console.log(orgComp_num);
		console.log(orgComp_ceo_name);
		console.log(orgComp_tel);
		console.log(orgComp_email);
		console.log(orgComp_fax);
		console.log(orgComp_addr1);
		console.log(orgComp_addr2);
		console.log(orgComp_post);
		console.log("=============================");
		console.log(reComp_name);
		console.log(reComp_num);
		console.log(reComp_ceo_name);
		console.log(reComp_tel);
		console.log(reComp_email);
		console.log(reComp_fax);
		console.log(reComp_addr1);
		console.log(reComp_addr2);
		console.log(reComp_post);
		console.log("=============================");
		
		if(orgComp_name == reComp_name &&
				orgComp_num == reComp_num &&
				orgComp_ceo_name == reComp_ceo_name &&
				orgComp_tel == reComp_tel &&
				orgComp_email == reComp_email &&
				orgComp_fax == reComp_fax &&
				orgComp_addr1 == reComp_addr1 &&
				orgComp_addr2 == reComp_addr2 &&
				orgComp_post == reComp_post){
			swalHistoryBack("변경된 값이 없습니다!","","");
			return false;
		}
		
		
		console.log(reComp_num.replace(/-/g,''));
		if(!only_Num3(reComp_num.replace(/-/g,''))){
			swalAlert("숫자만 입력하세요","","","","comp_num");
		}
		
		console.log(reComp_tel.replace(/-/g,''));
		if(!only_Num3(reComp_tel.replace(/-/g,''))){
			swalAlert("숫자만 입력하세요","","","","comp_tel");
		}
		
		if(!checkEmail(reComp_email)){
			swalAlert("이메일을 확인해주세요","","","","comp_email");
		}
		
		console.log(reComp_fax.replace(/-/g,''));
		if(!only_Num3(reComp_fax.replace(/-/g,''))){
			swalAlert("숫자만 입력하세요","","","","comp_fax");
		}
		
		console.log(reComp_post);
		if(!only_Num3(reComp_post)){
			swalAlert("숫자만 입력하세요","","","","comp_post");
		}
		
		
		
		return false;
	}
	
</script>
<title>회사정보수정화면</title>
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
						<h1 class="page-heading d-flex text-gray-900 fw-bold fs-3 flex-column justify-content-center my-0">HCM Company!</h1>
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
								<h3 class="card-title text-gray-800 fw-bold">회사정보</h3>
							</div>
							<div class="separator separator-dashed my-3"></div>	
							<div class="card-body pt-5" >
								<form action="/hr/company/correctionCompanyInfo.do" onsubmit="return valChk()" method="post">
									<div class="table-responsive">
										<table class="table table-hover table-rounded table-striped border gy-7 gs-7">
											<tbody class="fw-semibold fs-6 text-gray-800 border-bottom-2 border-gray-200">
												<tr>
													<td>회사명</td>
													<td colspan="2"><input name="comp_name" id="comp_name" class="form-control form-control-solid" type="text" value="${companyDto.getComp_name()}"></td>
												</tr>
			
												<tr>
													<td>사업자 등록번호</td>
													<td colspan="2"><input name="comp_num" id="comp_num" class="form-control form-control-solid" type="text" value="${companyDto.getComp_num()}"></td>
												</tr>
												
												<tr>
													<td>대표자명</td>
													<td colspan="2"><input name="comp_ceo_name" id="comp_ceo_name" class="form-control form-control-solid" type="text" value="${companyDto.getComp_ceo_name()}"></td>
												</tr>
												
												<tr>
													<td>대표전화번호</td>
													<td colspan="2"><input name="comp_tel" id="comp_tel" class="form-control form-control-solid" type="text" value="${companyDto.getComp_tel()}"></td>
												</tr>
												
												<tr>
													<td>회사메일</td>
													<td colspan="2"><input name="comp_email" id="comp_email" class="form-control form-control-solid" type="text" value="${companyDto.getComp_email()}"></td>
												</tr>
												
												<tr>
													<td>팩스번호</td>
													<td colspan="2"><input name="comp_fax" id="comp_fax" class="form-control form-control-solid" type="text" value="${companyDto.getComp_fax()}"></td>
												</tr>
												
												<tr>
													<td>주소</td>
													<td><input name="comp_addr1" id="comp_addr1" class="form-control form-control-solid" type="text" value="${companyDto.getComp_addr1()}"></td>
													<td><input name="comp_addr2" id="comp_addr2" class="form-control form-control-solid" type="text" value="${companyDto.getComp_addr2()}"></td>
												</tr>
												
												<tr>
													<td>우편번호</td>
													<td colspan="2"><input name="comp_post" id="comp_post" class="form-control form-control-solid" type="text" value="${companyDto.getComp_post()}"></td>
												</tr>
											</tbody>
											<tfoot>
												<tr>
													<td colspan="3">
														<button class="btn btn-primary btnLg me-10" type="submit">저장</button>
														<button class="btn btn-primary btnLg me-10" type="reset">초기화</button>
														<a onclick="javascript:window.history.back(-1)" class="btn btn-primary btnLg me-10">취소</a>
														<input name="comp_id" id="comp_id" type="hidden" value="${companyDto.getComp_id()}">
													</td>	
												</tr>
											</tfoot>
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