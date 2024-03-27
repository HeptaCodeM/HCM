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

function toggleMenu(event, reboNo) {
    event.preventDefault(); // 기본 이벤트 동작 방지
    // 버튼에 연결된 commentItem 요소를 찾아서 표시 여부를 토글합니다.
    var menu = document.getElementById("comment_tool"+reboNo);
		menu.style.display='block';
		menu.style.top = (event.offsetTop+menu.offsetHeight-20)+"px";
		menu.style.left = (event.offsetLeft+(event.offsetWidth*1.5)+menu.offsetWidth+15)+"px";
		
		
}


function insertReply(data){
	 // 직렬화된 데이터를 가져옵니다.
		var form = $("#ReplyForm").serialize();
		var additionalData = "gobo_no=" + data;
		form += "&" + additionalData;
		
		// jQuery를 사용하여 폼 필드의 값을 가져옵니다.
		var rebo_writer = $("#ReplyForm #rebo_writer").val();
		var rebo_content = $("#ReplyForm #rebo_content").val();
		
		$.ajax({
				url: "/sm/insertReply.do",
				data: form,
				type: "get",
				dataType: "json",
				success: function() {
					$("#ReplyForm")[0].reset();
					var currentDate = new Date();
					var formattedDate = currentDate.getFullYear() + '-' + 
					                    ('0' + (currentDate.getMonth() + 1)).slice(-2) + '-' + 
					                    ('0' + currentDate.getDate()).slice(-2) + ' ' + 
					                    ('0' + currentDate.getHours()).slice(-2) + ':' + 
					                    ('0' + currentDate.getMinutes()).slice(-2);
					var commentHtml = '';
					if ($("#commentList > li").length > 0) {
					    // 상위에 형제 노드가 있는 경우
					    commentHtml += '<div class="separator border-2 separator-dashed my-5 hrline" style="margin-left: 50px;"></div>';
					}
					commentHtml += '<li>';
					commentHtml += '<div class="d-flex align-items-center no-border" style="border: none;">';

					commentHtml += '<img src="${sessionScope.userInfoVo.empl_picture_str}" alt="프로필 사진" width="36" height="36" class="mr-3">';
					
					commentHtml += '<div style="margin-left: 10px;">';
					commentHtml += '<strong>'+rebo_writer+'</strong><br>';
					commentHtml += '<div>'+rebo_content+'</div>';
					commentHtml += '<div><small>'+formattedDate+'</small>';
					commentHtml += '<a role="button" class="comment_info_button" onclick="showCommentForm()">답글쓰기</a>';
					commentHtml += '</div>';
					commentHtml += '</div>';
					commentHtml += '</div>';
					commentHtml += '</li>';
					
					
					$("#commentList").append(commentHtml);
				},
				error: function() {
					
				}
			});
			
			
	}








// 새로운 li 요소 생성
var liElement = document.createElement('li');
liElement.id = 'firstReply';

// 새로운 div 요소 생성
var divElement = document.createElement('div');
divElement.classList.add('d-flex', 'align-items-center', 'no-border');
divElement.style.border = 'none';

// 새로운 이미지 요소 생성
var imgElement = document.createElement('img');
imgElement.src = sessionScope.userInfoVo.empl_picture_str;
imgElement.alt = '프로필 사진';
imgElement.width = '36';
imgElement.height = '36';
imgElement.classList.add('mr-3');

// 새로운 div 요소 생성
var innerDivElement = document.createElement('div');
innerDivElement.style.marginLeft = '10px';
innerDivElement.style.position = 'relative';
innerDivElement.style.width = '100%';

// 새로운 strong 요소 생성
var strongElement = document.createElement('strong');
strongElement.textContent = comment.rebo_writer;

// 새로운 div 요소 생성
var contentDivElement = document.createElement('div');
contentDivElement.textContent = comment.rebo_content;

// 새로운 small 요소 생성
var smallElement = document.createElement('small');
smallElement.textContent = new Intl.DateTimeFormat('en-US', { 
  year: 'numeric', 
  month: '2-digit', 
  day: '2-digit', 
  hour: '2-digit', 
  minute: '2-digit' 
}).format(comment.rebo_regdate);

// 새로운 div 요소 생성
var actionDivElement = document.createElement('div');
actionDivElement.style.position = 'absolute';
actionDivElement.style.top = '0';
actionDivElement.style.right = '0';
actionDivElement.style.float = 'right';

// 새로운 a 요소 생성
var aElement = document.createElement('a');
aElement.role = 'button';
aElement.title = '더보기';
aElement.classList.add('comment_tool');
aElement.href = '#';
aElement.id = 'comment_tool' + comment.rebo_no;
aElement.onclick = function(event) {
  toggleMenu(event, comment.rebo_no);
};

// 새로운 이미지 요소 생성
var imgElementInsideA = document.createElement('img');
imgElementInsideA.alt = '더보기';
imgElementInsideA.src = 'https://cdn4.iconfinder.com/data/icons/liny/24/more-menu-vertical-line-64.png';
imgElementInsideA.style.width = '30px';

// 새로운 a 요소 생성
var replyButton = document.createElement('a');
replyButton.role = 'button';
replyButton.classList.add('comment_info_button');
replyButton.onclick = function() {
  showCommentForm(comment.rebo_no);
};
replyButton.textContent = '답글쓰기';

// DOM 트리에 요소들을 추가
aElement.appendChild(imgElementInsideA);
actionDivElement.appendChild(aElement);
innerDivElement.appendChild(strongElement);
innerDivElement.appendChild(document.createElement('br'));
innerDivElement.appendChild(contentDivElement);
innerDivElement.appendChild(document.createElement('br'));
innerDivElement.appendChild(smallElement);
innerDivElement.appendChild(actionDivElement);
innerDivElement.appendChild(replyButton);
divElement.appendChild(imgElement);
divElement.appendChild(innerDivElement);
liElement.appendChild(divElement);

// 원하는 부모 요소에 새로 생성한 요소를 추가
var parentElement = document.getElementById('commentList'); // 부모 요소를 지정해주세요.
parentElement.appendChild(liElement);

















