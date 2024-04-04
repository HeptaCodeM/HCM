
	
	$("#savebutton").click(function() {
		var content = 	editor.getData();
		
		console.log(content)
		$("#editor").val(content)
		var gobo_content = $("#editor").val();
		console.log("2222222222",gobo_content);
		//textarea의 id를 적어줍니다.
		var gobo_writer_id = $("#gobo_writer_id").val();
		var gobo_writer = $("#gobo_writer").val();
		var gobo_title = $("#gobo_title").val();
		var gobo_bigo = $("#gobo_bigo").val();
		if (gobo_title == null || gobo_title == "") {
			alert("제목을 입력해주세요.");
			$("#gobo_title").focus();
			return;
		}
		else if (gobo_content == "" || gobo_content == null || gobo_content == '&nbsp;' ||
			gobo_content == '<br>' || gobo_content == '<br/>' || gobo_content == '<p>&nbsp;</p>' || gobo_content == '<p><br></p>') {
			alert("본문을 작성해주세요.");
			editor.focus();
			return;
		} else {//이 부분은 스마트에디터 유효성 검사 부분이니 참고하시길 바랍니다.
			sweetAlertConfirm("등록 하시겠습니까?",function(){
			$.ajax({
				url: "/sm/insertGoboAdmin.do",
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
			},'')
			
		}


	});
		//초기화버튼 클릭이벤트
	$("#resetbutton").click(function() {
		editor.setData("");
		$('#insertForm')[0].reset();
	});







