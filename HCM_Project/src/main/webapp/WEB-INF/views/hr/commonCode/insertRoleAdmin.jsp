<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/menu/headerInfo.jsp" %>
<c:choose>
	<c:when test="${role eq 'DT'}">
		<c:set var="thisRole" value="부서"/>
	</c:when>
	
	<c:when test="${role eq 'RK'}">
		<c:set var="thisRole" value="직위"/>
	</c:when>
	
	<c:when test="${role eq 'PN'}">
		<c:set var="thisRole" value="직책"/>
	</c:when>
</c:choose>
<title>${thisRole}정보 입력하기</title>
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
			<br>
			<div class="app-content flex-column-fluid">
				<!-- 내용 시작 -->
				<div id="kt_app_content" class="app-content flex-column-fluid">
					<div class="app-container container-fluid">
						<div class="card card-flush h-md-50 mb-xl-10">
							<div class="card-header pt-5">
								<h3 class="card-title text-gray-800 fw-bold">${thisRole}입력</h3>
							</div>
							<div class="separator separator-dashed my-3"></div>	
							<form action="/hr/commonCode/insertRoleOneAdmin.do" method="post">
								<div class="card-body pt-5">
									${thisRole}명<input id="coco_name" name="coco_name" class="form-control form-control-solid" type="text" maxlength="6">
										<span id="nameSpan" class="fs-6 text-muted">한글 6글자 이내로 입력해주세요!</span><br>
									${thisRole}코드<input id="coco_cd" name="coco_cd" class="form-control form-control-solid" type="text" maxlength="8">
										<span id="codeSpan" class="fs-6 text-muted">EX)"${role}000001" 형식으로 입력해주세요</span><br>
										<!-- 유효값 검사도 필요 -->
										<input type="hidden" id="role" name="role" value="${role}">
										<br>
								</div>
								<div class="card-footer">
									<button id="submitBtn" disabled="disabled" class="btn btn-primary btnLg me-10" type="submit">저장</button>
									<button class="btn btn-primary btnLg me-10" type="reset">초기화</button>
									<a onclick="javascript:window.history.back(-1)" class="btn btn-primary me-10">취소</a>
							    </div>
							</form>
						</div>
					</div>
				</div>
				<!-- 내용 끝 -->
			</div>
		</div>
			
<%@include file="/WEB-INF/views/menu/hrSideMenu.jsp" %>		
</body>
				<!-- 헤더 위로가면 작동(X) -->
				<script type="text/javascript">
					$('#coco_name').maxlength({
					    warningClass: "badge badge-warning",
					    limitReachedClass: "badge badge-success"
					});
					
					$('#coco_cd').maxlength({
					    warningClass: "badge badge-warning",
					    limitReachedClass: "badge badge-success"
					});
					
					function checkNameValue(){
					    
					    let coco_name = document.getElementById("coco_name").value;
					    console.log(coco_name);
					    
					    var checkKor = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;

						if(checkKor.test(coco_name)){
							return true;
						}else{
							return false;
						}
					}
					
					function checkCodeValue(){
					    
					    let coco_cd = document.getElementById("coco_cd").value;
					    console.log(coco_cd);
					    
					    var checkCode = /^[A-Z]{2}\d{6}$/;
					    
						if(checkCode.test(coco_cd)){
							return true;
						}else{
							return false;
						}
					}
					
					
					
					var nameChk = document.querySelector("#coco_name");
					var codeChk = document.querySelector("#coco_cd");
					nameChk.addEventListener("focusout",function(){ valueChk(); });
					codeChk.addEventListener("focusout",function(){ valueChk(); });
					function valueChk(){
						console.log("작동");
                  		var coco_name = document.getElementById('coco_name').value;
                  		var coco_cd = document.getElementById('coco_cd').value;
                  		var role = document.getElementById('role').value;
						var valueChk = new URLSearchParams();
						valueChk.append('coco_name', coco_name);
						valueChk.append('coco_cd', coco_cd);
						valueChk.append('role', role);
						
						
						
						if(coco_name == "" || coco_cd == ""){
							document.getElementById("nameSpan").innerHTML = "${thisRole}명을 입력하세요";
							document.getElementById("codeSpan").innerHTML = "${thisRole}코드를 입력하세요";
							return false;
						}
						
						fetch('/hr/commonCode/roleNameDuplicateChkAdmin.do',{
							method: "POST",
							headers: {
							    'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
							},
							body: valueChk
						})
						.then(response =>{
							return response.json();
							console.log(response);
						})
						.then(data => {
							console.log('CODE중복 : ',data.codeFlag);
							console.log('NAME중복 : ',data.nameFlag);
							var codeSpan = document.getElementById("codeSpan");
							var nameSpan = document.getElementById("nameSpan");
							let coco_cdInput = document.getElementById("coco_cd");
							let coco_nameInput = document.getElementById("coco_name");
							let submitBtn = document.getElementById("submitBtn");
							
							if(data.nameFlag == "false"){
								nameSpan.innerHTML = "중복된 이름입니다! 다시 입력하세요";
								coco_cdInput.disabled = true;
								return false;
							}else{
								if(checkNameValue()){
									nameSpan.innerHTML = "사용가능합니다";
									coco_cdInput.disabled = false;
								}else{
									nameSpan.innerHTML = "한글만 입력 가능합니다"
									coco_cdInput.disabled = true;
								}
							}
							
							if(coco_cdInput.value == "${role}000001"){
								codeSpan.innerHTML = "EX)\"${role}000001\" 번은 사용할수 없습니다";
								return false;
							}
							
							if(data.codeFlag == "false" || coco_cdInput.value == ""){
								if(data.codeFlag == "false"){
									codeSpan.innerHTML = "중복된 코드입니다! 다시 입력하세요";
								}else{
									codeSpan.innerHTML = "EX)\"${role}000008\" 형식으로 입력해주세요";
								}
								submitBtn.disabled = true;
								return false;
							}else{
								if(checkCodeValue()){
									codeSpan.innerHTML = "사용가능합니다";
									submitBtn.disabled = false;
								}else{
									codeSpan.innerHTML = "코드형식을 확인해주세요! EX)\"${role}000008\""
									submitBtn.disabled = true;
									return false;
								}
							}
							
							
						})
					    .catch(err => { 
					        console.log('에러발생', err);
					    });
					}
				</script>
</html>