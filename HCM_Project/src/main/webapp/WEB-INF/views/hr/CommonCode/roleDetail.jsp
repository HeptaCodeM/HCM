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
    	alert("수정내용이 같습니다!(swal로 바꿀예정)");
    	return false;
    }else{
	    if(checkKor.test(coco_name)){
	    	console.log("성공");
	    }else{
	    	alert("한글만 입력해주세요!(swal로 바꿀예정)");
		    return false;
	    }
    }

}



function deleteRole(){
    console.log("동작");
    var coco_cd = document.getElementById("coco_cd").value;
    var role = document.getElementById("role").value;
    
    location.href="./deleteRoleOne.do?coco_cd="+coco_cd+"&role="+role;
}


function checkTest(){
	   let chk = document.getElementById("coco_name").value;
	   console.log(chk.length);
	   if(chk.length >=6){
		   
	   }
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
							<form action="./correctionRole.do" onsubmit="return checkNameValue()" method="post">
								<div class="card-body pt-5">
									${thisRole}명<input id="coco_name" name="coco_name" onkeypress="checkTest()" class="form-control form-control-solid" type="text" maxlength="6" value="${roleDto.getCoco_name()}">
										<span class="fs-6 text-muted">한글 6글자 이내로 입력해주세요!</span><br>
									${thisRole}코드<input id="coco_cd" name="coco_cd" class="form-control form-control-solid" type="text" value="${roleDto.getCoco_cd()}" readonly="readonly">
									<input type="hidden" id="role" name="role" value="${role}">
								</div>
								<div class="card-footer">
									<button class="btn btn-primary btnMd me-10" type="submit">저장</button>
									<button class="btn btn-primary btnMd me-10" type="button" data-bs-toggle="modal" data-bs-target="#deptModal">삭제</button>
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
<!-- 모달창 -->			
<div class="modal fade" tabindex="-1" id="deptModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <h3>삭제하시겠습니까?</h3>
                <h4>[이것도 swal로 바꿀예정 입니다!]</h4>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-secondary me-10" data-bs-dismiss="modal">취소</button>
                <button type="button" class="btn btn-primary me-10" onclick="deleteRole()">삭제</button>
            </div>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/views/menu/hrSideMenu.jsp" %>		
</body>
</html>