
/* 템플릿 카테고리 선택 */
onload = function() {
	var select = document.getElementById('selectCategory');	
	// 서버에서 데이터를 가져와서 select 옵션 추가
	fetch('./selectCategory.do',{})
	.then(resp => {
		return resp.json();
	})
	.then(data => {
		console.log(data);
		for(let d of data) {
			var opt = document.createElement('option');
			console.log(d.sica_cd);
			opt.setAttribute('value', d.sica_cd);
			opt.textContent = d.sica_name;
			select.appendChild(opt);
		}
	}) 
	.catch(error => {
		console.log(error);
	})
}

/* 템플릿 가져오기 */
onload = function(){
	document.getElementById('getTemplate').addEventListener('click', function(){
		fetch('./getTemplate.do',{
			method : 'post'
		}).then(resp => {
			return resp.text();
		}).then(data => {
			editor.setData(data)
		}).catch(error => {
			console.log(error);
		});
	});
}

// 템플릿 게시하기
/*function writeTemplate(){
	const data = editor.getData();
	
	var opt = $('#selectCategory option:selected').val();
	var title = $('input[name=sidt_temp_name]').val();
	var content = $("#editor").text(data);
	
	$.ajax({
		url : './insertTemplate.do',
		method : 'post',
		data : {"sica_cd":opt, "sidt_temp_name":title, "sidt_temp_content":content},
		dataType : 'json',
		
	})
	$("form").eq(0).submit();
	
}*/

// 템플릿 수정하기
/*var updateBtn = document.querySelector('.updateTemplate');
updateBtn.addEventListener("click", function(){
	window.location.href = "./updateTemplate.do"
});*/
//function updateTemplate(){
//	const data = editor.getData();
//	
//	var opt = $('#selectCategory option:selected').val();
//	var title = $('input[name=sidt_temp_name]').val();
//	var content = $("#editor").text(data);
//	
//	$.ajax({
//		url : './updateTemplate.do',
//		method : 'post',
//		data : {"sica_cd":opt, "sidt_temp_name":title, "sidt_temp_content":content},
//		dataType : 'json',
//		success : alert('수정 완료'),
//		error : alert('수정 실패')
//	})
//	$("form").eq(0).submit();
//}

