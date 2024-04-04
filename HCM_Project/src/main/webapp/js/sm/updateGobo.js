	
	// 전송버튼 클릭이벤트 
	$("#savebutton").click(function() {
		var content = 	editor.getData();
		$("#editor").val(content)
		var gobo_content = $("#editor").val();
		//textarea의 id를 적어줍니다.
		var gobo_no = $("#gobo_no").val();
		if (gobo_title == null || gobo_title == "") {
			alert("제목을 입력해주세요.");
			$("#gobo_title").focus();
			return;
		}
		else if (gobo_content == "" || gobo_content == null || gobo_content == '&nbsp;' ||
			gobo_content == '<br>' || gobo_content == '<br/>' || gobo_content == '<p>&nbsp;</p>' || gobo_content == '<p><br></p>') {
			alert("본문을 작성해주세요.");
			editor.focus(); //포커싱
			return;
		} else {//이 부분은 스마트에디터 유효성 검사 부분이니 참고하시길 바랍니다.
			sweetAlertConfirm("수정 하시겠습니까?",function(){
			$.ajax({
				url: "/sm/updateGoboAdmin.do",
				data: $("#updateForm").serialize(),
				type: "post",
				dataType: "json",
				success: function(msg) {
					console.log(msg);
					if (msg != true) {
						alert("insert 실패!!")
						return false;
					} else {
						$("#updateForm")[0].reset(); // 폼 초기화
						location.href='/sm/getDetailGobo.do?gobo_no='+gobo_no;
					}
				},
				error: function() {
					alert("오류");
				}
			});
			},'')
			
			
		}


	});


function scrollToTop() {
    document.body.scrollTop = 0; // For Safari
    document.documentElement.scrollTop = 0; // For Chrome, Firefox, IE and Opera
}

// 스크롤 위치에 따라 버튼 표시 여부 조절
window.onscroll = function() {scrollFunction()};

function scrollFunction() {
    if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
        document.getElementById("scrollTopButton").style.display = "block";
    } else {
        document.getElementById("scrollTopButton").style.display = "none";
    }
}




