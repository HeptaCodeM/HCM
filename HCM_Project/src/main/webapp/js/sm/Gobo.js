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
    // 모든 댓글 작성 폼을 숨깁니다.
    hideAllCommentForms();

    // commentFormContainer 요소 찾기
    var commentFormContainer = document.getElementById("commentFormContainer" + commentId);
    // commentFormContainer가 존재하면 보이게 함
    if (commentFormContainer) {
        commentFormContainer.style.display = 'block';
    }
}

function hideAllCommentForms() {
    // 모든 댓글 작성 폼을 숨깁니다.
    var commentFormContainers = document.querySelectorAll("[id^='commentFormContainer']");
    commentFormContainers.forEach(function(container) {
        container.style.display = "none";
    });
}
function cancelReply(commentId) {
     // commentFormContainer 요소 찾기
    var commentFormContainer = document.getElementById("commentFormContainer" + commentId);
    // commentFormContainer가 존재하면 보이게 함
    if (commentFormContainer) {
        commentFormContainer.style.display = 'none';
    }
}



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
    var inputContent = $(".CommentWriter #commentTextArea").val().trim();
    var submitButton = document.getElementById("submitButton");

    if (inputContent.length > 0) {
        submitButton.disabled = false;
    } else {
        submitButton.disabled = true;
    }
}

// textarea의 입력 이벤트에 checkInput 함수를 연결
document.getElementById("commentTextArea").addEventListener("#commentTextArea", checkInput);






$(document).ready(function() {
    // 등록 버튼 클릭 시 AJAX 요청을 보냅니다.
    $(".insertReplyTwoBtn").click(function() {
        var container = $(this).closest("li"); // 클릭한 버튼이 속한 li 요소를 찾습니다.
        var formData = container.find("#ReplyTwoForm").serialize(); // 해당 li 요소 안의 form 데이터를 가져옵니다.
		var gobo_no = $("#gobo_no").val();
        // AJAX 요청을 보냅니다.
        $.ajax({
            url: "/sm/insertReplyTwo.do",
            data: formData,
            type: "GET",
            dataType: "json",
            success: function() {
                location.href='/sm/getDetailGobo.do?gobo_no='+gobo_no;
            },
            error: function() {
                // 에러 처리
            }
        });
    });
});



function updateGoboDelFlag(gobo_no){
	var result = confirm("글을 삭제 하시겠습니까?");
	if(result){
		location.href="/sm/updateGoboDelFlag.do?gobo_no="+gobo_no;
	}
}





























