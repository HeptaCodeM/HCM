document.addEventListener('DOMContentLoaded', function() {
    var daygridmonth = $(".fc-toolbar-title").text();
    listAjax(daygridmonth);
});

function listAjax(daygridmonth) {
	$.ajax({
		type: "get",
		url: "/sm/getAllCalendar.do",
		data: { daygridmonth: daygridmonth },
		dataType: "json",
		success: function(data) {
			var calendarEl = document.getElementById('calendar');
			var calendar = new FullCalendar.Calendar(calendarEl, {
				googleCalendarApiKey: 'AIzaSyBBTfPVr0UjVIt-H0VGRuLFRg-ltL-YQDk',  // 구글 api 키
				initialView: 'dayGridMonth',   //월 달력 표시
				headerToolbar: {
					left: 'addEventButton,fileterButton',  // 왼쪽에 이벤트 버튼 추가
					center: 'customPrevButton,title,customNextButton',	//center 버튼 추가 dayGridMonth(월간),timeGridWeek(주간),timeGridDay(시간대),listWeek(리스트형태)
					right:'today',
				},
				titleFormat: function(date) {
					1
					return date.date.year + '년 ' + (parseInt(date.date.month) + 1) + '월';   //달력표시 한글로 변경
				},
				 viewDidMount: function(view) {
		         	var daygridmonth = $(".fc-toolbar-title").text();
		            listAjax(daygridmonth);
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
						},
					fileterButton:{
						text: "카테고리",
						click: function(){
							filtermodal();
						}
						},
					customPrevButton:{
						text: "<",
						click: function(){
							var currentDate = calendar.getDate(); // 현재 날짜 가져오기
							var newDate = calendar.addMonths(currentDate, -1); // 현재 날짜에서 1달을 뺀 새로운 날짜
							calendar.gotoDate(newDate); // 새로운 날짜로 이동
							var daygridmonth = $(".fc-toolbar-title").text();
							console.log(daygridmonth);
                    			listAjax(daygridmonth);
						}
						},
					customNextButton:{
						text: ">",
						click: function(){
							   var currentDate = calendar.getDate(); // 현재 날짜 가져오기
						        var newDate = calendar.addMonths(currentDate, 1, 'month'); // 현재 날짜에서 1달을 더한 새로운 날짜
						        calendar.gotoDate(newDate); // 새로운 날짜로 이동
							  var daygridmonth = $(".fc-toolbar-title").text();
							  console.log(daygridmonth);
                    			listAjax(daygridmonth);
						}
						},
						
					
				},dateClick: function() {   //일자 클릭시 이벤트
				    	insert();
				 },
				  eventClick: function(info) {  // 일정명 클릭시 이벤트
					var scbo_no = info.event.extendedProps.scbo_no;
					detail(scbo_no);
					
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

function detail(scbo_no){
	console.log(scbo_no);
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
            $("#scbo_title1").val(data.scbo_title);
            $("#scbo_content1").val(data.scbo_content);
            $("#scbo_start1").val(formatstart);
            $("#scbo_end1").val(formatend);
            $("#scbo_bigo1").val(data.scbo_bigo);
            $("#scbo_no").val(data.scbo_no);
            // 모달 창 표시
            if(data.scbo_cgory_no == 100 || data.scbo_cgory_no == 200){
			$("#updateButton").show();
			$("#scbo_cgory_no_update").show();
			$("#scbo_cgory_no1").hide();
            $("#detailModal").modal("show");
            	
			}else{
			$("#detailModal").modal("show");
			$("#scbo_cgory_no_update").hide();	
			$("#updateButton").hide();
			}
        },
        error: function() {
            console.log("오류임");
        }
    });
}

function insert(){
	var daygridmonth = $(".fc-toolbar-title").text()
	console.log(daygridmonth);
		$("#insertModal").modal("show"); // modal 나타내기

		$("#addCalendar").on("click", function() {  // modal의 추가 버튼 클릭 시
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
	 $("#scbo_start, #scbo_end,#scbo_start1, #scbo_end1").datetimepicker({//input 태그 클릭시 이벤트 처리
        disabledWeekDays: [0, 6], // 0: 일요일, 6: 토요일 // 주말 선택 불가
        minDate: 0, // 오늘 이후 날짜만 선택 가능
        allowTimes: ['09:00','09:30', '10:00','10:30', '11:00','11:30','12:00','12:30',
         '13:00','13:30','14:00','14:30','15:00','15:30','16:00','16:30','17:00','17:30',
         '18:00','18:30','19:00','19:30','20:00','20:30','21:00'] // 선택 가능한 시간
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
							listAjax();

						}
					},
					error: function() {
						alert("jdbc로 넘기지도 못함");
					}
				});
			}
		
}




