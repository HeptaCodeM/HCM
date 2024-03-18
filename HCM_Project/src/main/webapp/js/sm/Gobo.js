document.addEventListener('DOMContentLoaded', function() {
    document.querySelectorAll('.CommentWriter').forEach(function(commentWriter) {
        commentWriter.addEventListener('click', function() {
            var textarea = this.querySelector('.comment_inbox_text');
            if (textarea) {
                textarea.focus();
            }
        });
    });
});



function showCommentForm(commentId) {
    // commentFormContainer 요소 찾기
    var commentFormContainer = document.getElementById("commentFormContainer" + commentId);
    // commentFormContainer가 존재하면 보이게 함
    if (commentFormContainer) {
        commentFormContainer.style.display = 'block';
    }
}
function cancelReply(commentId) {
     // commentFormContainer 요소 찾기
    var commentFormContainer = document.getElementById("commentFormContainer" + commentId);
    // commentFormContainer가 존재하면 보이게 함
    if (commentFormContainer) {
        commentFormContainer.style.display = 'none';
    }
}

document.addEventListener("click", function(event) {
    var commentFormContainers = document.querySelectorAll("[id^='commentFormContainer']");
    var isCommentButtonClicked = false;

    // 클릭된 요소가 답글쓰기 버튼인지 확인합니다.
    if (event.target.classList.contains("comment_info_button")) {
        isCommentButtonClicked = true;
    }

    // 클릭된 요소가 답글쓰기 버튼이 아닌 경우 모든 댓글 작성 폼을 숨깁니다.
    if (!isCommentButtonClicked) {
        commentFormContainers.forEach(function(container) {
            container.style.display = "none";
        });
    }
});


//맨 위로 스크롤하는 함수
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

function checkInput() {
    var inputContent = document.getElementById("commentTextArea").value.trim();
    var submitButton = document.getElementById("submitButton");

    if (inputContent.length > 0) {
        submitButton.disabled = false;
    } else {
        submitButton.disabled = true;
    }
}



function insertReply(data){
 // 직렬화된 데이터를 가져옵니다.
    var form = $("#ReplyForm").serialize();

    // 추가하려는 데이터를 직렬화된 데이터에 추가합니다.
    var additionalData = "gobo_no=" + data;

    // 추가 데이터를 직렬화된 데이터에 추가합니다.
    form += "&" + additionalData;

	$.ajax({
			url: "/sm/insertReply.do",
			data: form,
			type: "post",
			dataType: "json",
			success: function() {
				location.href='/sm/getDetailGobo.do?gobo_no='+data;
				console.log("댓글 등록 성공");
			},
			error: function() {
				
			}
		});
		
		
}































