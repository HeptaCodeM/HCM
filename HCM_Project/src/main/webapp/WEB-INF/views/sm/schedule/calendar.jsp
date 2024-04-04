<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/menu/headerInfo.jsp" %>
<title>HCM GroupWare</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-datetimepicker/2.5.20/jquery.datetimepicker.full.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-datetimepicker/2.5.20/jquery.datetimepicker.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/fullcalendar@5.7.0/main.min.css">
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/fullcalendar@5.7.0/main.min.js"></script>
<script type="text/javascript" src="/js/sm/calendar.js"></script>
<style type="text/css">

	#calendar {
      max-width: 1000px;
      max-height:800px;
      margin:  auto;
    }
    .koHol{
    	pointer-events: none;
    }
    a{
    color : black;
    }
 
	.calendarwidth{
		width: 1500px;
		height: 1200px;
	}
	.fc-prev-button {
    display: inline-block;
	}
	.fc-next-button {
	}
	.fc-toolbar-title{
	display: inline-block;
	}
	.addEventButton{
	margin-right: 20px;
	}
	.input-group-addon {
    display: flex;
    align-items: center; /* 수직 가운데 정렬 */
	}

	.input-group-addon i {
     max-width: 100%; /* 이미지의 최대 너비를 부모 요소에 맞게 조정 */
    max-height: 100%; /
	}
	
   .fc-scrollgrid-sync-table tr td:first-child .fc-daygrid-day-number{
   color:red;
   }
   .fc-scrollgrid-sync-table tr td:last-child .fc-daygrid-day-number{
   color:blue;
   }
   #filterLayer { position: absolute; background-color:#fff; 
	z-index:999; width:140px; height:300px; left: 32%; transform: translateX(-50%); 
	top: 20%; padding: 20px; border:1px solid #ccc; 
	border-radius: 20px; display: none; 
	}
	
    table {
        border-collapse: collapse;
        border-spacing: 0;
    }

    th, td {
        padding: 5px; /* 적절한 값으로 조절 */
        margin: 0;
    }
    .category-100 >*{
      background-color: #39CEFA;
      color:white;
    }
    .category-200 >*{
      background-color: orange;
      color:white;
    }
    .category-TC000001 >*{
    background-color: #70DB8A;
    color:white;
    }
    .category-TC000002 >*{
     background-color: #9368DC;
     color:white;
    }
</style>
	

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
			</div>
			<div class="app-content flex-column-fluid">
				<!-- 내용 시작 -->
				<div id="kt_app_content" class="app-content flex-column-fluid">
					<div class="app-container container-fluid">
						<div class="card card-flush h-md-50 mb-xl-10">
							<div class="card-body pt-5 calendarwidth" id="calendar">
							</div>
							
							
						</div>
					</div>
				</div>
				<!-- 내용 끝 -->
			</div>
		</div>
		
		
		
		

<div class="modal fade" tabindex="-1" id="insertModal">
    <div class="modal-dialog modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">일정 등록</h5>

                <!--begin::Close-->
                <div class="btn btn-icon btn-sm btn-active-light-primary ms-2" data-bs-dismiss="modal" aria-label="Close">
                    <i class="ki-duotone ki-cross fs-2x"><span class="path1"></span><span class="path2"></span></i>
                </div>
                <!--end::Close-->
            </div>

             <div class="modal-body">
               <div class="form-group">
                     <form id="form">
                     <div class="form-group">
                       <label for="sel1" class="col-form-label">구분</label>
                       <select class="form-select" id="scbo_cgory_no" name="scbo_cgory_no" required="required">
                         <option value="100">개인일정</option>
                         <option value="200">외근</option>
                       </select>
                     <label for="title" class="col-form-label">일정 제목</label>
                     <input type="text" class="form-control" id="scbo_title" name="scbo_title">
                     </div>
                       <label for="content" class="col-form-label">일정 내용</label>
                       <textarea class="form-control" rows="5" id="scbo_content" name="scbo_content"></textarea>
                       
                  <label for="startDate" class="col-form-label">시작 날짜</label>
                     <div class="input-group">
                        <input type="text" class="form-control" name="scbo_start" id="scbo_start" readonly="readonly" ondblclick="return false" id="startDate">
                        <span class="input-group-text  imagebutton" data-td-target="#scbo_start" data-td-toggle="datetimepicker">
				       	 	<i class="ki-duotone ki-calendar fs-2"><span class="path1"></span><span class="path2"></span></i>
					    </span>
                       </div>
                     <label for="endDate" class="col-form-label">종료 날짜</label>
                     <div class="input-group">
                        <input type="text" class="form-control" name="scbo_end" id="scbo_end" readonly="readonly">
                        <span class="input-group-text  imagebutton2" data-td-target="#scbo_end" data-td-toggle="datetimepicker">
					        <i class="ki-duotone ki-calendar fs-3"><span class="path1"></span><span class="path2"></span></i>
					    </span>
                     </div>
                        <div>
                      <label for="title" class="col-form-label">비고</label>
                     <input type="text" class="form-control" id="scbo_bigo" name="scbo_bigo">
                     </div>
                    </form> 
               </div>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-light" data-bs-dismiss="modal">종료</button>
                <button type="button" class="btn btn-primary" id="addCalendar">저장</button>
            </div>
        </div>
    </div>
</div>
		
		
		
		
		

<div class="modal fade" tabindex="-1" id="detailModal">
    <div class="modal-dialog modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">일정 조회</h5>

                <!--begin::Close-->
                <div class="btn btn-icon btn-sm btn-active-light-primary ms-2" data-bs-dismiss="modal" aria-label="Close">
                    <i class="ki-duotone ki-cross fs-2x"><span class="path1"></span><span class="path2"></span></i>
                </div>
                <!--end::Close-->
            </div>

             <div class="modal-body">
               <div class="form-group">
                     <form id="updateform">
                      <input type="hidden" name="scbo_no" id="scbo_no1">
                     <div class="form-group">
                       <label for="sel1" class="col-form-label">구분</label>
                       <select class="form-select" id="scbo_cgory_no_update" name="scbo_cgory_no" required="required">
                         <option value="100">개인일정</option>
                         <option value="200">외근일정</option>
                         <option value="TC000002">휴가</option>
                         <option value="TC000001">연차</option>
                       </select>
                       <div id="schtitle">
                     <label for="title" class="col-form-label">일정 제목</label>
                     <input type="text" class="form-control" id="scbo_title1" name="scbo_title">
                     </div>
                       <div id="favo">
                     <label for="title" class="col-form-label">기안자</label>
                     <input type="text" class="form-control" id="scbo_writer" name="scbo_writer">
                     </div>
                     </div>
                       <label for="content" class="col-form-label">일정 내용</label>
                       <textarea class="form-control" rows="5" id="scbo_content1" name="scbo_content"></textarea>
                       
                  <label for="startDate" class="col-form-label">시작 날짜</label>
                     <div class="input-group">
                        <input type="text" class="form-control" name="scbo_start" id="scbo_start1" readonly="readonly" ondblclick="return false" id="startDate">
                        <span class="input-group-text imagebutton3" data-td-target="#scbo_start" data-td-toggle="datetimepicker">
				       	 	<i class="ki-duotone ki-calendar fs-2"><span class="path1"></span><span class="path2"></span></i>
					    </span>
                       </div>
                     <label for="endDate" class="col-form-label">종료 날짜</label>
                     <div class="input-group">
                        <input type="text" class="form-control" name="scbo_end" id="scbo_end1" readonly="readonly">
                        <span class="input-group-text imagebutton4" data-td-target="#scbo_start" data-td-toggle="datetimepicker">
				       	 	<i class="ki-duotone ki-calendar fs-2"><span class="path1"></span><span class="path2"></span></i>
					    </span>
                     </div>
                        <div>
                      <label for="title" class="col-form-label">비고</label>
                     <input type="text" class="form-control" id="scbo_bigo1" name="scbo_bigo">
                     </div>
                    </form> 
               </div>
            </div>
            <div class="modal-footer">
            	<button type="button" class="btn btn-primary" id="deleteButton" onclick="deleteCalendar()">삭제</button>
            	<button type="button" class="btn btn-primary" id="updateButton" onclick="updateCalendar()">수정</button>
            </div>
        </div>
    </div>
</div>
				
				
				
<div id="filterLayer">
    <div style="text-align: right;">
        <a href="javascript:void(0);" onclick="document.getElementById('filterLayer').style.display='none';">
            <i class="ki-duotone ki-cross-square fs-2x">
                <span class="path1"></span><span class="path2"></span>
            </i>
        </a>
        <br>
    </div>
    <div class="menu-item px-3" style="overflow: hidden;">
        <form name="filter" id="filter" method="post" onsubmit="return false;">
            <table class="table">
                <tbody>
                    <tr>
					    <th style="color: #39CEFA; ">&nbsp;&nbsp;개인 &nbsp;&nbsp;&nbsp;
					    <input class="form-check-input" type="checkbox" name="type" value="3">
					    </th>
					</tr>
					
                    <tr>
                        <th  style="color: orange;">&nbsp;&nbsp;외근 &nbsp;&nbsp;&nbsp;
                        <input class="form-check-input" type="checkbox" name="type" value="4">
                        </th>
                    </tr>
                       
                    <tr >
                        <th style="color:#70DB8A;">&nbsp;&nbsp;연차 &nbsp;&nbsp;&nbsp;
                        <input class="form-check-input" type="checkbox" name="type" value="2">
                        </th>
                    </tr> 
                      
                    <tr >
                        <th style="color:#9368DC;">&nbsp;&nbsp;휴가 &nbsp;&nbsp;&nbsp;
                        <input class="form-check-input" type="checkbox" name="type" value="1">
                        </th>
                    </tr>
                       
                </tbody>
                <tfoot>
                    <tr>
                        <th>
                            <input type="button" class="btn btn-success" value="조회"  id="searchCalendar" onclick="searchLabel()" style="margin-left: 7px;">
                        </th>
                    </tr>
                </tfoot>
            </table>
        </form>
    </div>
</div>
<%@include file="/WEB-INF/views/menu/smSideMenu.jsp" %>		
</body>
<script type="text/javascript">
$(document).ready(function(){
    $('#kt_modal_scrollable_2').on('hidden.bs.modal', function (e) {
        // 모달이 닫힐 때 form 요소 초기화
        $('#form')[0].reset();
    });
});




</script>
</html>