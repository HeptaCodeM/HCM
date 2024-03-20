

onload = function() {
	
}

onload = function(){
	/* 템플릿 카테고리 선택 */	
	var select = document.getElementById('selectCategory');	
	// 서버에서 데이터를 가져와서 select 옵션 추가
	fetch('./selectCategory.do')
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
	
	/* 템플릿 가져오기 */
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


$(document).ready(function(){

	const target = document.getElementsByClassName("ck-content")[0]

	const callback = (mutationList, observer) => {
		const node = mutationList[0].removedNodes[0].childNodes[0]
	  	if(node.localName == "img"){
		fetch('./removeImage.do', {
		  method: 'POST',
		  headers: {
		    'Content-Type': 'application/x-www-form-urlencoded',
		  },
		  body: 'saveName=' + node.currentSrc.substring(node.currentSrc.lastIndexOf('/') + 1),
		})
		  .then(response => {
		    if (!response.ok) {
		      throw new Error('getContent.do 잘못된 요청입니다.');
		    }
		  })
		  .catch(error => {
		    alert(error.message);
  		});

		}
	};

	observer = new MutationObserver(callback);

	const config = { 
	    childList: true,
	};

	observer.observe(target, config);
	
});


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

