$(document).ready(function() {
	var oEditors = []; // 개발되어 있는 소스에 맞추느라, 전역변수로 사용하였지만, 지역변수로 사용해도 전혀 무관 함.  

	// Editor Setting 
	nhn.husky.EZCreator.createInIFrame({
		oAppRef: oEditors, // 전역변수 명과 동일해야 함. 
		elPlaceHolder: "smartEditor", // 에디터가 그려질 textarea ID 값과 동일 해야 함. 
		sSkinURI: "/smartEditor/SmartEditor2Skin.html", // Editor HTML 
		fCreator: "createSEditor2", // SE2BasicCreator.js 메소드명이니 변경 금지 X 
		htParams: {
			// 툴바 사용 여부 (true:사용/ false:사용하지 않음) 
			bUseToolbar: true,

			// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음) 
			bUseVerticalResizer: true,

			// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음) 
			bUseModeChanger: true,
		}
	});
	
	// 전송버튼 클릭이벤트 
	$("#savebutton").click(function() {
		
		//textarea의 id를 적어줍니다.
		oEditors.getById["smartEditor"].exec("UPDATE_CONTENTS_FIELD", []);
		var gobo_writer_id = $("#gobo_writer_id").val();
		var gobo_writer = $("#gobo_writer").val();
		var gobo_title = $("#gobo_title").val();
		var gobo_content = $("#smartEditor").val();
		var gobo_bigo = $("#gobo_bigo").val();
		if (gobo_title == null || gobo_title == "") {
			alert("제목을 입력해주세요.");
			$("#gobo_title").focus();
			return;
		}
		else if (gobo_content == "" || gobo_content == null || gobo_content == '&nbsp;' ||
			gobo_content == '<br>' || gobo_content == '<br/>' || gobo_content == '<p>&nbsp;</p>' || gobo_content == '<p><br></p>') {
			alert("본문을 작성해주세요.");
			oEditors.getById["smartEditor"].exec("FOCUS"); //포커싱
			return;
		} else {//이 부분은 스마트에디터 유효성 검사 부분이니 참고하시길 바랍니다.
			var result = confirm("글을 등록 하시겠습니까?");
			if(result){
			$.ajax({
				url: "/sm/insertGobo.do",
				data: $("#insertForm").serialize(),
				type: "post",
				dataType: "json",
				success: function(msg) {
					console.log(msg);
					if (msg != true) {
						alert("insert 실패!!")
						return false;
					} else {
						$("#insertForm")[0].reset(); // 폼 초기화
						alert("성공");
						location.href='/sm/getAllGobo.do'
					}
				},
				error: function() {
					alert("오류");
				}
			});
			}
		}


	});
		//초기화버튼 클릭이벤트
	$("#resetbutton").click(function() {
		oEditors.getById["smartEditor"].exec("SET_IR", [""]);
		$('#form')[0].reset();
	});
});






