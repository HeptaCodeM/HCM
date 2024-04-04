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
	sweetAlertConfirm("글을 삭제 하시겠습니까?",function(){
	
		location.href="/sm/updateGoboDelFlagAdmin.do?gobo_no="+gobo_no;
	
	},'')
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
		Swal.fire({
        html: `정말 삭제하시겠습니까?`,
   //     icon: "question",
        buttonsStyling: false,
        showCancelButton: true,
        confirmButtonText: " &nbsp;&nbsp; 예  &nbsp;&nbsp; ",
        cancelButtonText: '아니요',
        customClass: {
            confirmButton: "btn btn-light-primary",
            cancelButton: 'btn btn-light-danger'
        }
    }).then((result) => { 
        if (result.isConfirmed) {
            // 사용자가 상신 취소를 확인한 경우
            // 여기서 상신 취소 처리 로직을 실행하면 됩니다.
            Swal.fire({
                text: '삭제되었습니다.',
             //   icon: 'success',
                confirmButtonText: '확인',
                customClass: {
                    confirmButton: 'btn btn-light-primary' 
                }
            })
            .then(() => {
             deleteAjax(rebo_no);
             });
        }
    });
}




function deleteAjax(rebo_no){
	$.ajax({
		url: "/sm/updateReplyDelFlagAdmin.do",
		data:{rebo_no:rebo_no},
		type:"get",
		dataType: "json",
		success: function(){
			var liElement = $('#commentItem' + rebo_no).closest('li');
            // 해당 li 요소의 바로 위에 있는 hrline 요소를 선택합니다.
            var hrLineAbove = liElement.prev('.hrline');
            // 해당 li 요소의 바로 아래에 있는 hrline 요소를 선택합니다.
            var hrLineBelow = liElement.next('.hrline');
            // hrline 요소가 있으면 삭제합니다.
            if (hrLineAbove.length > 0) {
                hrLineAbove.remove();
            } else if (hrLineBelow.length > 0) {
                hrLineBelow.remove();
            }
            // 선택한 li 요소를 삭제합니다.
            liElement.remove();
		},
		error: function(){
			
		}
	})
	
}




function updateButton(rebo_no) {
    var liElement = $('#firstReply' + rebo_no);

    // 이전 내용을 가져옵니다.
    var Content = $('#replycontent' + rebo_no).text().trim();
    var writer = $('#rebo_writer' + rebo_no).text().trim();

    // li 태그 내의 내용을 숨깁니다.
    liElement.hide();

    // 새로운 내용을 생성하여 추가합니다.
    var newContent = '<div id="updatedContent' + rebo_no + '" class="CommentWriter mb-4" style="margin-top: 50px">';
    newContent += '<form id="ReplyForm'+rebo_no+'">';
    newContent += '<div class="comment_inbox border border-2">';
    newContent += '<input type="hidden" name="rebo_writer_id" id="rebo_writer_id" value="${sessionScope.userInfoVo.empl_id}">';
    newContent += '<input type="hidden" name="rebo_modify_id" id="rebo_modify_id" value="${sessionScope.userInfoVo.empl_id}">';
    newContent += '<input type="hidden" name="rebo_writer" id="rebo_writer" value="${sessionScope.userInfoVo.empl_name}">';
    newContent += '<em class="comment_inbox_name">' + writer + '</em>';
    newContent += '<textarea id="rebo_content'+rebo_no+'" placeholder="댓글을 남겨보세요" rows="2" class="comment_inbox_text form-control border-0" oninput="checkInput()" name="rebo_content">'+Content+'</textarea>';
    newContent += '<div class="d-flex justify-content-end align-items-end">';
    newContent += '<div class="register_box">';
    newContent += '<button id="cancelButton" type="button" class="btn btn-danger ml-2" onclick="cancelUpdate(' + rebo_no + ')">취소</button>';
    newContent += '<button id="updateButton" type="button" class="btn btn-primary" onclick="updateAjax(' + rebo_no + ')">등록</button>';
    newContent += '</div>';
    newContent += '</div>';
    newContent += '</div>';
    newContent += '</form>';
    newContent += '</div>';

    // 새로운 내용을 li 태그 이전에 추가합니다.
    liElement.before(newContent);
    
}



function updateAjax(rebo_no){
	var rebo_content = $('#rebo_content' + rebo_no).val();
	console.log(rebo_content);
	$.ajax({
		url: "/sm/updateReply.do",
		data: {rebo_no:rebo_no,rebo_content:rebo_content},
		type:"get",
		dataType: "json",
		success: function(isc){
			if(isc>0){
			 var updatedContent = $('#updatedContent' + rebo_no);
            var newContent = $('#rebo_content' + rebo_no).val();

            // 기존의 내용을 갱신합니다.
            $('#replycontent' + rebo_no).text(newContent);

            // 기존 내용을 갱신한 후 새로운 내용을 보여줍니다.
            $('#firstReply' + rebo_no).show();
            updatedContent.remove();
            }
		},
		error:function(){
			
		}
	})
	
}




function cancelUpdate(rebo_no) {
    // 업데이트된 새로운 내용을 삭제하고 li 태그를 보여줍니다.
    $('#updatedContent' + rebo_no).remove();
    $('#firstReply' + rebo_no).show();
}




















