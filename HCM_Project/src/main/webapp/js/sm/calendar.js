var daygridmonth = $(".fc-toolbar-title").text();
 var calendar = "";

document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
    var currentMonth = null; // 현재 월을 저장할 변수

     	calendar = new FullCalendar.Calendar(calendarEl, {
        googleCalendarApiKey:  'AIzaSyBBTfPVr0UjVIt-H0VGRuLFRg-ltL-YQDk',
        initialView: 'dayGridMonth',
        headerToolbar: {
            left: 'addEventButton,fileterButton',
            center: 'customPrevButton,title,customNextButton',
            right: 'today'
        },
        eventSources: [
            {
                // 구글 캘린더에서 가져올 이벤트의 ID 특일정보 아이디
                googleCalendarId: "ko.south_korea#holiday@group.v.calendar.google.com"
                // 클릭 이벤트를 제거하기 위해 넣은 클래스
                , className: "koHol"
                //이벤트의 색
                , color: "red"
                //이벤트의 텍스트 컬러
                , textColor: "white"
            }
        ],

        titleFormat: function(date) {
            // 현재 월의 값을 표시
            return date.date.year + '년 ' + (parseInt(date.date.month) + 1) + '월';
        },
        customButtons: {
            customPrevButton: {
                text: '<',
                click: function() {
                    calendar.prev(); // 이전 달로 이동
                    currentMonth = calendar.view.title; // 현재 월 값 업데이트
                    listAjax(currentMonth,); // AJAX 호출
                }
            },
            customNextButton: {
                text: '>',
                click: function() {
                    calendar.next(); // 다음 달로 이동
                    currentMonth = calendar.view.title; // 현재 월 값 업데이트
                    listAjax(currentMonth); // AJAX 호출
                }
            },
            addEventButton: { // 추가한 버튼 설정
            	id: 'addEventButton',
                text: "일정 추가",  // 버튼 내용
                click: function() { // 버튼 클릭 시 이벤트 추가
                    insert();
                    
                }
            },
            fileterButton:{
	 			id: 'fileterButton',
                text: "라벨",
                click: function(){
                    filterLayer();
                    
                }
            }
        },
        dateClick: function() {   //일자 클릭시 이벤트
            insert();
        },
        eventClick: function(info) {  // 일정명 클릭시 이벤트
            var scbo_no = info.event.extendedProps.scbo_no;
            detail(scbo_no);
        },
        
        editable: false,// 수정할려면 true로 바꾸면 됨
        droppable: false, //드래그 안되게 막음
        displayEventTime: false,//이벤트에 시간 표시여부
        dayMaxEvents: true,// 일정 표시개수 4개 제한
       	eventOverlap: function(stillEvent, movingEvent) {
    return stillEvent.allDay && movingEvent.allDay;
  }
    });

    // 초기 렌더링 시에 AJAX 호출
    currentMonth = calendar.view.title; // 현재 월 값 업데이트
    listAjax(currentMonth)
    calendar.render();

});

function listAjax(daygridmonth) {
	
	    // 기존에 동일한 ID를 가진 이벤트 소스가 있는지 확인하고 있다면 삭제합니다.
    var existingEventSource = calendar.getEventSourceById("calendarData");
    if (existingEventSource) {
        existingEventSource.remove();
    }
	
//			calendar.getEventSources().forEach(function(source) {
			    // 이벤트 소스의 클래스 이름이 "koHol"을 포함하는지 확인합니다.
//			    var google = source.context.eventSources
//			    console.log(google);
//			    console.log(source);
//			    source.remove();
			   
//			    calendar.addEventSource(google);
//			    if(source.context.eventSources){
//					google += source;
//				}else{}
			    // "koHol" 클래스를 가지지 않은 이벤트 소스를 제거합니다.
//});
	
    var formData = $("#filter").serializeArray();
    var selectedValues = [];
    // 수집한 값을 배열에 넣습니다.
    $.each(formData, function(index, element){
        selectedValues.push(element.value);
    });
    // 선택된 값들을 콤마로 구분하여 문자열로 변환합니다.
    var selectedString = selectedValues.join(',');
    // AJAX 요청을 보냅니다.
    if(selectedString == null){
        selectedString = "1,2,3,4"
    }
    
    // AJAX 호출 및 데이터 처리
      $.ajax({
        type: "get",
        url: "/sm/getAllCalendar.do",
        data: { daygridmonth: daygridmonth, type: selectedString},
        dataType: "json",
        success: function(data) {
		// 받아온 데이터를 FullCalendar의 이벤트 형식으로 가공합니다.
            var events = data.map(function(item) {
                return {
                    title: item.title, // 이벤트 제목
                    start: item.start, // 이벤트 시작일
                    end: item.end,     // 이벤트 종료일
                    scbo_no: item.scbo_no,
                    scbo_empno: item.scbo_empno,
                    scbo_cgory_no: item.scbo_cgory_no,
                };
            });

            // FullCalendar에 새로운 이벤트 소스를 추가합니다.
            calendar.addEventSource({
                id: "calendarData", // 이벤트 소스에 대한 ID
                events: events // 새로운 이벤트 데이터
            });

            // FullCalendar에 추가된 이벤트를 다시 그립니다.
            calendar.refetchEvents();
        },
        error: function() {
            // 오류 시 처리할 코드
        }
    });
}

function detail(scbo_no){
	$.ajax({
        url: "/sm/detailScbo.do",
        method: "POST",
        data: { scbo_no: scbo_no }, 
        dataType: "json", // seq를 key로 하는 객체를 전송
        success: function(data) {
			if(data.scbo_cgory_no == 100){
            $("#scbo_cgory_no1").val("개인일정");
			}else if(data.scbo_cgory_no == 200){
				$("#scbo_cgory_no1").val("외근일정");
			}else if(data.scbo_cgory_no == "TC000002"){
				$("#scbo_cgory_no1").val("휴가");
			}else if(data.scbo_cgory_no == "TC000001"){
				$("#scbo_cgory_no1").val("연차");
			}
			
			var start = new Date(data.scbo_start);
			var end = new Date(data.scbo_end);
			var formatstart = start.getFullYear() + '/' + (start.getMonth() + 1).toString().padStart(2, '0') + '/' + start.getDate().toString().padStart(2, '0') + ' ' + start.getHours().toString().padStart(2, '0') + ':' + start.getMinutes().toString().padStart(2, '0');
			var formatend = end.getFullYear() + '/' + (end.getMonth() + 1).toString().padStart(2, '0') + '/' + end.getDate().toString().padStart(2, '0') + ' ' + end.getHours().toString().padStart(2, '0') + ':' + end.getMinutes().toString().padStart(2, '0');
            $("#scbo_no1").val(data.scbo_no);
            $("#scbo_title1").val(data.scbo_title);
            $("#scbo_title1").val(data.scbo_title);
            $("#scbo_content1").val(data.scbo_content);
            $("#scbo_start1").val(formatstart);
            $("#scbo_end1").val(formatend);
            $("#scbo_bigo1").val(data.scbo_bigo);
            $("#scbo_no").val(data.scbo_no);
            // 모달 창 표시
            $("#detailModal").modal("show");
        },
        error: function() {
            console.log("오류임");
        }
    });
}

function insert() {
    var daygridmonth = $(".fc-toolbar-title").text();
    $("#insertModal").modal("show"); // modal 나타내기

    // 기존의 이벤트 핸들러를 제거합니다.
    $("#addCalendar").off("click");

    // 추가 버튼 클릭 시 이벤트 핸들러를 설정합니다.
    $("#addCalendar").on("click", function() {
        var scbo_cgory_no = $("#scbo_cgory_no").val();
        var scbo_title = $("#scbo_title").val();
        var scbo_content = $("#scbo_content").val();
        var scbo_start = $("#scbo_start").val();
        var scbo_end = $("#scbo_end").val();

        //내용 입력 여부 확인
        if (scbo_title == null || scbo_title == "") {
            alert("제목을 입력하세요.");
        } else if (scbo_content == null || scbo_content == "") {
            alert("내용을 입력하세요.");
        } else if (scbo_start == "" || scbo_end == "") {
            alert("날짜를 입력하세요.");
        } else if (new Date(scbo_end) - new Date(scbo_start) < 0) { // date 타입으로 변경 후 확인
            alert("종료일이 시작일보다 먼저입니다.");
        } else { // 정상적인 입력 시
            var data = $("#form").serialize()

            $.ajax({
                url: "/sm/insertScbo.do",
                data: data,
                type: "post",
                dataType: "json",
                success: function(msg) {
                    console.log(msg);
                    if (msg != true) {
                        alert("insert 실패!!")
                        return false;
                    } else {
                        $("#form")[0].reset(); // 폼 초기화
                        $("#insertModal").modal("hide");
                        listAjax(daygridmonth);
                    }
                },
                error: function() {
                    alert("jdbc로 넘기지도 못함");
                }
            });
        }
    });
}






$(document).ready(function(){
	// 언어를 한국어로 변경
	$.datetimepicker.setLocale('ko');

	// start day와 end day datetimepicker 설정
	$("#scbo_start, #scbo_end,scbo_start1, #scbo_end1").datetimepicker({
		disabledWeekDays: [0, 6], // 0: 일요일, 6: 토요일 // 주말 선택 불가
		allowTimes: ['09:00','09:30', '10:00','10:30', '11:00','11:30','12:00','12:30',
		 '13:00','13:30','14:00','14:30','15:00','15:30','16:00','16:30','17:00','17:30',
		 '18:00','18:30','19:00','19:30','20:00','20:30','21:00'], // 선택 가능한 시간
	});
// datetimepicker 생성
$("#scbo_start").datetimepicker({
	
});

$("#scbo_end").datetimepicker({
	
});


$(".imagebutton").click(function(){
	$("#scbo_start").datetimepicker('show');
});

$(".imagebutton2").click(function(){
	$("#scbo_end").datetimepicker('show');
});
$("#scbo_start1").datetimepicker({
	
});

$("#scbo_end1").datetimepicker({
});


$(".imagebutton3").click(function(){
	$("#scbo_start1").datetimepicker('show');
});

$(".imagebutton4").click(function(){
	$("#scbo_end1").datetimepicker('show');
});
})

function updateCalendar(){
			var daygridmonth = $(".fc-toolbar-title").text();
			var scbo_title = $("#scbo_title1").val();
			var scbo_content = $("#scbo_content1").val();
			var scbo_start = $("#scbo_start1").val();
			var scbo_end = $("#scbo_end1").val();
			//내용 입력 여부 확인
			if (scbo_title == null || scbo_title == "") {
				alert("제목을 입력하세요.");
			} else if (scbo_content == null || scbo_content == "") {
				alert("내용을 입력하세요.");
			} else if (scbo_start == "" || scbo_end == "") {
				alert("날짜를 입력하세요.");
			} else if (new Date(scbo_end) - new Date(scbo_start) < 0) { // date 타입으로 변경 후 확인
				alert("종료일이 시작일보다 먼저입니다.");
			} else { // 정상적인 입력 시
				var data = $("#updateform").serialize()

				$.ajax({
					url: "/sm/updateScbo.do",
					data: data,
					type: "post",
					dataType: "json",
					success: function(msg) {
						console.log(msg);
						if (msg != true) {
							alert("insert 실패!!")
							return false;
						} else {
							$("#form")[0].reset(); // 폼 초기화
							$("#detailModal").modal("hide");
							listAjax(daygridmonth);
						}
					},
					error: function() {
						alert("jdbc로 넘기지도 못함");
					}
				});
			}
}

function deleteCalendar(){
	var daygridmonth = $(".fc-toolbar-title").text();
	var result = confirm("글을 삭제 하시겠습니까?");
	if(result){
	var scbo_no = $("#scbo_no1").val();
	$.ajax({
			url: "/sm/updateScboDelFlag.do",
			data: {scbo_no:scbo_no},
			type: "GET",
			dataType: "json",
			success: function(isc) {
					if(isc){
					listAjax(daygridmonth);
					}
					$("#detailModal").modal("hide");
			},
			error: function() {
				alert("jdbc로 넘기지도 못함");
			}
		});
		
	}
}

function filterLayer(){
    // 레이어를 표시합니다.
    $("#filterLayer").show();
}


function searchLabel(){
	var daygridmonth = $(".fc-toolbar-title").text();
	listAjax(daygridmonth);
}


