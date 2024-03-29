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
    var inputContent = $(".CommentWriter #rebo_content").val().trim();
    var submitButton = document.getElementById("submitButton");

    if (inputContent.length > 0) {
        submitButton.disabled = false;
    } else {
        submitButton.disabled = true;
    }
}

// textarea의 입력 이벤트에 checkInput 함수를 연결
document.getElementById("rebo_content").addEventListener("#rebo_content", checkInput);



function updateGoboDelFlag(gobo_no){
	var result = confirm("글을 삭제 하시겠습니까?");
	if(result){
		location.href="/sm/updateGoboDelFlag.do?gobo_no="+gobo_no;
	}
}




function toggleMenu(event, rebo_no) {
    // 이벤트 전파 방지
    event.stopPropagation();
    event.preventDefault();
    
    // 클릭된 요소를 선택합니다.
    var moreButton = $(event.target);
    
    // 클릭된 요소 바로 다음에 있는 LayerMore 요소를 선택합니다.
    var layerMore = moreButton.next('.LayerMore');
    
    // LayerMore 요소가 보이는지 여부를 확인하여 토글합니다.
    if (layerMore.is(':visible')) {
        layerMore.hide();
    } else {
        // 다른 LayerMore 요소를 모두 숨깁니다.
        $('.LayerMore').hide();
        // LayerMore 요소를 생성하여 추가합니다.
        var html = '<div data-v-0330f652="" id="commentItem'+rebo_no+'" role="menu" class="menu-rounded menu-gray-500 menu-state-bg menu-state-color fw-semibold py-1 fs-6 w-50px LayerMore" style="position: absolute; background: #fff; border-radius: 8px; border: 1px solid #ccc; z-index: 9999; text-align:center; right:-11px;">';
        html += '<a href="#" role="button" class="update_button" onclick="updateButton('+rebo_no+')" style="text-decoration: none; color:black;"> 수정 </a><br>';
        html += '<a href="#" role="button" class="delete_button" onclick="deleteButton('+rebo_no+')" style="text-decoration: none; color:black;"> 삭제 </a>';
        html += '</div>';
        // 클릭된 요소 바로 다음에 새로운 HTML을 추가합니다.
        moreButton.after(html);
    }
}

function deleteButton(rebo_no){
	sweetAlertConfirm("댓글을 삭제 하시겠습니까?",deleteAjax(rebo_no) , '');
}
function deleteAjax(rebo_no){
	$.ajax({
		url: "/sm/updateReply.do",
		data:{rebo_no:rebo_no},
		type:"get",
		dataType: "json",
		success: function(){
			
		},
		error: function(){
			
		}
	})
	
}
function updateButton(rebo_no){
	sweetAlertConfirm("댓글을 수정 하시겠습니까?",updateAjax(rebo_no) , '');
}
function updateAjax(rebo_no){
	
	$.ajax({
		url: "/sm/updateReplyDelFlag.do",
		data:{rebo_no:rebo_no},
		type:"get",
		dataType: "json",
		success: function(){
		},
		error:function(){
			
		}
	})
	
}

























