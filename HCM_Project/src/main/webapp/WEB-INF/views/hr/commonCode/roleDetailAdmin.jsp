<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/menu/headerInfo.jsp" %>
<script type="text/javascript">
window.onload = function(){
	orgCoco_name = document.getElementById("coco_name").value;
	console.log(orgCoco_name+"!!");
}

function checkNameValue(){
    
    var coco_name = document.getElementById("coco_name").value;
    console.log(orgCoco_name);
    console.log(coco_name);
    
    var checkKor = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;

    if(orgCoco_name == coco_name){
    	console.log("값이 같음");
    	swalAlert("수정내용이 없습니다!", "", "", "", "");
    	return false;
    }else{
	    if(checkKor.test(coco_name)){
	    	console.log("성공");
	    }else{
	    	swalAlert("한글만 입력해주세요!", "", "", "", "");
		    return false;
	    }
    }

}

function deleteRole(){
    sweetAlertConfirm("삭제하시겠습니까?",delOk,returnFlase);
}

function delOk(){
    var coco_cd = document.getElementById("coco_cd").value;
    var role = document.getElementById("role").value;
    location.href="/hr/commonCode/deleteRoleOneAdmin.do?coco_cd="+coco_cd+"&role="+role;
}
function returnFlase(){
	return false;
}

</script>
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
<title>${thisRole}정보 상세보기</title>
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
								<h3 class="card-title text-gray-800 fw-bold">${roleDto.getCoco_name()} 정보수정</h3>
							</div>
							<div class="separator separator-dashed my-3"></div>	
							<form action="/hr/commonCode/correctionRoleAdmin.do" onsubmit="return checkNameValue()" method="post">
								<div class="card-body pt-5">
									${thisRole}명<input id="coco_name" name="coco_name" class="form-control form-control-solid" type="text" maxlength="6" value="${roleDto.getCoco_name()}">
										<span id="nameSpan" class="fs-6 text-muted">한글 6글자 이내로 입력해주세요!</span><br>
									${thisRole}코드<input id="coco_cd" name="coco_cd" class="form-control form-control-solid" type="text" value="${roleDto.getCoco_cd()}" readonly="readonly">
									<input type="hidden" id="role" name="role" value="${role}">
								</div>
								<div class="card-footer">
									<button id="submitBtn" disabled="disabled" class="btn btn-primary btnMd me-10" type="submit">저장</button>
									<c:choose>
										<c:when test="${delFLAG eq 'N'}">
											<button disabled="disabled" class="btn btn-primary btnMd me-10">삭제</button>
										</c:when>
										<c:otherwise>
											<button class="btn btn-primary btnMd me-10" type="button" onclick="deleteRole()">삭제</button>
										</c:otherwise>
									</c:choose>
									<button class="btn btn-primary btnMd me-10" type="reset">초기화</button>
									<a onclick="javascript:window.history.back(-1)" class="btn btn-primary btnMd me-10">취소</a>
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
<script type="text/javascript">
$('#coco_name').maxlength({
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


var nameChk = document.querySelector("#coco_name");
nameChk.addEventListener("focusout",function(){ valueChk(); });
function valueChk(){
	console.log("작동");
		var coco_name = document.getElementById('coco_name').value;
		var role = document.getElementById('role').value;
	var valueChk = new URLSearchParams();
	valueChk.append('coco_name', coco_name);
	valueChk.append('coco_cd', coco_cd);
	valueChk.append('role', role);
	
	if(coco_name != orgCoco_name){
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
			console.log('NAME중복 : ',data.nameFlag);
			var nameSpan = document.getElementById("nameSpan");
			let coco_cdInput = document.getElementById("coco_cd");
			let coco_nameInput = document.getElementById("coco_name");
			let submitBtn = document.getElementById("submitBtn");
			
			if(data.nameFlag == "false" || coco_cdInput.value == ""){
				nameSpan.innerHTML = "중복된 이름입니다! 다시 입력하세요";
				submitBtn.disabled = true;
			}else{
				if(checkNameValue()){
					nameSpan.innerHTML = "사용가능합니다";
					submitBtn.disabled = false;
				}else{
					nameSpan.innerHTML = "한글만 입력하세요"
					submitBtn.disabled = true;
				}
			}
			
		})
	    .catch(err => { 
	        console.log('에러발생', err);
	    });
	}
	
}


</script>

</html>