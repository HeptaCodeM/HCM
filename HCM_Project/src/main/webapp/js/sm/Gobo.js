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




function insertReply(gobo_no){
	var data = $("#ReplyForm").serialize()
	
	$.ajax({
			url: "./insert.do",
			data: data,
			type: "post",
			dataType: "json",
			success: function() {
				
			},
			error: function() {
				alert("jdbc로 넘기지도 못함");
			}
		});
}