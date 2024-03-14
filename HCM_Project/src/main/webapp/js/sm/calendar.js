document.addEventListener('DOMContentLoaded', listAjax());

function listAjax() {
	$.ajax({
		type: "get",
		url: "/sm/getAllCalendar.do",
		dataType: "json",
		success: function(data) {
			var calendarEl = document.getElementById('calendar');

			var calendar = new FullCalendar.Calendar(calendarEl, {
				googleCalendarApiKey: 'AIzaSyBBTfPVr0UjVIt-H0VGRuLFRg-ltL-YQDk',  // 구글 api 키
				initialView: 'dayGridMonth',   //월 달력 표시
				

				headerToolbar: {
					left: 'addEventButton',  // 왼쪽에 이벤트 버튼 추가
					center: 'title',		//center 버튼 추가 dayGridMonth(월간),timeGridWeek(주간),timeGridDay(시간대),listWeek(리스트형태)
				},
				titleFormat: function(date) {
					return date.date.year + '년 ' + (parseInt(date.date.month) + 1) + '월';   //달력표시 한글로 변경
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
				customButtons: { //버튼 추가
					addEventButton: { // 추가한 버튼 설정
						text: "일정 추가",  // 버튼 내용
						click: function() { // 버튼 클릭 시 이벤트 추가
								insert();
						}
					}
				},dateClick: function() {   //일자 클릭시 이벤트
				    	insert();
				 },
				  eventClick: function(info) {  // 일정명 클릭시 이벤트
					var seq = info.event.extendedProps.seq;
					
					detail(seq);
					
				},
				editable: false,// 수정할려면 true로 바꾸면 됨
				droppable: false, //드래그 안되게 막음
				events: data, // 뿌려주는 값 ,
				displayEventTime: false,//이벤트에 시간 표시여부
				dayMaxEvents: true,// 일정 표시개수 4개 제한 

        		
	

			});
			// 달력 초기화시 필수
			calendar.render();
		},
		error: function() {
		}
	});
}

function detail(seq){
	console.log(seq);
	$.ajax({
        url: "./detail.do",
        method: "POST",
        data: { seq: seq }, 
        dataType: "json", // seq를 key로 하는 객체를 전송
        success: function(data) {
            $("#groupid1").text(data.groupid);
            $("#title1").text(data.title);
            $("#content1").text(data.content);
            $("#start1").text(data.start);
            $("#end1").text(data.end);
            // 모달 창 표시
            $("#kt_modal_scrollable_2").modal("show");	
        },
        error: function() {
            console.log("오류임");
        }
    });
}

function insert(){
		$("#kt_modal_scrollable_2").modal("show"); // modal 나타내기

		$("#addCalendar").on("click", function() {  // modal의 추가 버튼 클릭 시
			var groupid = $("#groupid").val();
			var title = $("#title").val();
			var content = $("#content").val();
			var start = $("#start").val();
			var end = $("#end").val();

			//내용 입력 여부 확인
			if (groupid == null || groupid == "") {
				alert("구분을 입력하세요.");
			} else if (title == null || title == "") {
				alert("제목을 입력하세요.");
			} else if (content == null || content == "") {
				alert("내용을 입력하세요.");
			} else if (start == "" || end == "") {
				alert("날짜를 입력하세요.");
			} else if (new Date(end) - new Date(start) < 0) { // date 타입으로 변경 후 확인
				alert("종료일이 시작일보다 먼저입니다.");
			} else { // 정상적인 입력 시
				var data = $("#form").serialize()

				$.ajax({
					url: "./insert.do",
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
							$("#kt_modal_scrollable_2").modal("hide");
							listAjax();

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
	 $("#start, #end").datetimepicker({//input 태그 클릭시 이벤트 처리
        disabledWeekDays: [0, 6], // 0: 일요일, 6: 토요일 // 주말 선택 불가
        minDate: 0, // 오늘 이후 날짜만 선택 가능
        allowTimes: ['09:00','09:30', '10:00','10:30', '11:00','11:30','12:00','12:30',
         '13:00','13:30','14:00','14:30','15:00','15:30','16:00','16:30','17:00','17:30',
         '18:00','18:30','19:00','19:30','20:00','20:30','21:00'] // 선택 가능한 시간
    });

// datetimepicker 생성
$("#start").datetimepicker({
	
});

$("#end").datetimepicker({
	
});


$("#imagebutton").click(function(){
	$("#start").datetimepicker('show');
});

$("#imagebutton2").click(function(){
	$("#end").datetimepicker('show');
});
})


$("button.fc-prev-button").click(function() {            
var date = jQuery("#calendar").fullCalendar("getDate");            
convertDate(date);        
});        // 오른쪽 버튼을 클릭하였을 경우        
$("button.fc-next-button").click(function() {            
var date = jQuery("#calendar").fullCalendar("getDate");            
convertDate(date);        
});

function convertDate(date) {        
var date = new Date(date);        
alert(date.yyyymm());    }

 Date.prototype.yyyymm = function() {        
 var yyyy = this.getFullYear().toString();        
 var mm = (this.getMonth() + 1).toString();        
 var dd = this.getDate().toString();        
 return yyyy + "-" + mm[1] ? mm : "0" + mm[0];    
 }
