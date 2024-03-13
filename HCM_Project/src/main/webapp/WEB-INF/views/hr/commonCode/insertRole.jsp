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
							<form action="/hr/commonCode/insertRoleOne.do" onsubmit="return checkNameValue()" method="post">
								<div class="card-body pt-5">
									${thisRole}명<input id="coco_name" name="coco_name" class="form-control form-control-solid" type="text" maxlength="6">
										<span class="fs-6 text-muted">한글 6글자 이내로 입력해주세요!</span><br>
									${thisRole}코드<input id="coco_cd" name="coco_cd" class="form-control form-control-solid" onchange="" type="text" maxlength="8">
										<span class="fs-6 text-muted">EX)"${role}000001" 형식으로 입력해주세요</span><br>
										<!-- Ajax로 중복검사 필요 -->
										<!-- 유효값 검사도 필요 -->
										<input type="hidden" id="role" name="role" value="${role}">
								</div>
								<div class="card-footer">
									<button class="btn btn-primary btnLg me-10" type="submit">저장</button>
									<button class="btn btn-primary btnLg me-10" type="reset">초기화</button>
									<a onclick="javascript:window.history.back(-1)" class="btn btn-primary me-10">취소</a>
							    </div>
							</form>
						</div>
					</div>
				</div>
				<!-- 내용 끝 -->
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
					    
					    var coco_name = document.getElementById("coco_name").value;
					    console.log(coco_name);
					    
					    var checkKor = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;

						if(checkKor.test(coco_name)){
						 	console.log("성공");
						}else{
							alert("한글만 입력해주세요!(swal로 바꿀예정)");
							return false;
						}
					}
					
					var nameChk = document.querySelector("#coco_name");
					
					nameChk.addEventListener("focusout",function(){
						console.log("작동");
                  		var coco_name = document.getElementById('coco_name').value;
						var nameChk = new URLSearchParams();
						nameChk.append('coco_name', coco_name);
						fetch('/hr/commonCode/roleNameDuplicateChk.do',{
							method: "POST",
							headers: {
							    'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
							},
							body: nameChk
						})
						.then(response =>{
							console.log(response);
							return response.json()	;
						})
						.then(data => {
							console.log(data);
						})
					    .catch(err => { 
					        console.log('에러발생', err);
					    });
					});
				</script>
			</div>
		</div>
			
<%@include file="/WEB-INF/views/menu/hrSideMenu.jsp" %>		
</body>
</html>