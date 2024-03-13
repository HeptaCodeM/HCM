onload = function() {
	
	// 닫기버튼
	document.getElementById('closeRefer').addEventListener('click', closeRefer);
	
	// 저장버튼
	document.getElementById('saveRefer').addEventListener('click', saveRefer);
	
}

// jsTree 검색창의 엔터키로 검색 가능하게 하는 함수
$('#schName').on('keydown', function(e) {
	if (e.keyCode == 13) {
		$('#schBtn').click();
	}
});

// jstree 출력
$().ready(function() {
	$('#jstree').jstree({
		// 검색기능 , 우클릭메뉴, 라벨 효과
		plugins: ['search', 'wholerow'],
		// 우클릭메뉴 정의
		core: {
			data : {
				url : './signTree.do',
				method : 'get',
				dataType : 'json',
				success : function(data) {
					data.forEach(function(node) {
						if(node.pos_na != undefined) {
							node.text = node.text + ' (' + node.pos_na + ')&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button onclick="pick()" class="btn btn-basic btn-sm addd" style="padding: 0.2px;">➕</button><span class="positionFlag" style="display: none;">' + node.pos_flag + '</span>';
						} else {
							node.text = node.text + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button onclick="pick()" class="btn btn-basic btn-sm addd" style="padding: 0.2px;">➕</button>'
						}
					});
				},
				error : function() {
					alert('데이터 전송 실패');
				}
			}
		}
	});

})

$().ready(function() {
	$('#schName').focus();
	
	// 검색기능
	$('#schBtn').on('click', function() {
		$('#jstree').jstree().search($('#schName').val());
		$('#jstree').jstree('deselect_all');
	});

	// 결재자 취소 로직
	$('.cancelBtn').on('click', function(e) {
		// form태그의 기본동작 막기
		e.preventDefault();
		
		var row = $(this).closest('tr');
		
		// json형태로 모든 노드를 불러오기..
		var allNode = $('#jstree').jstree('get_json', '#', { flat: true });
		var selectNode = allNode.find(function(node) {
			return node.id == row.find('td span').text();
		});
		console.log(selectNode);
		// 숨겼던 노드를 다시 보여줌
		$('#jstree').jstree('show_node', selectNode);
		// input창 비움
		row.find('td input').val('');
		row.find('td span').text('');
		$('#de1').val(1);
		$('#de2').val(2);
		$('#de3').val(3);

	});
	
	// 결재자 리스트를 json형태로 저장
	
	
});
function signFavo() {
	location.href='./signFavo.do';
}


// + 버튼
function pick() {
	setTimeout(function() {
		var id = $('#jstree').jstree('get_selected')[0];
		if(id.includes('DT')) {
			// 참조부서 추가
			var dname = $('#jstree').jstree('get_text', id);
			dname = dname.substring(0, dname.indexOf('&'));
			var dept = document.getElementById('ref-dept');
			var input = document.createElement('input');
			var hiddenInput = document.createElement('input');
			var span = document.createElement('span');
			var inSpan = document.createElement('span');
			var btn = document.createElement('button');
			span.setAttribute('class', 'input-group-btn');
			inSpan.setAttribute('class', 'ki-solid ki-cross-square fs-1');
			inSpan.setAttribute('style', 'margin-left: -10px;');
			btn.setAttribute('class', 'btn btn-default');
			input.setAttribute('value', dname);
			input.setAttribute('class', 'form-control');
			input.setAttribute('readonly', 'readonly');
			hiddenInput.setAttribute('type', 'hidden');
			hiddenInput.setAttribute('value', id);
			btn.append(inSpan);
			span.append(btn);
			dept.append(input);
			dept.append(hiddenInput);
			dept.append(span);
		} else {
			// 참조자 추가
			var emp = document.getElementById('ref-emp');
			fetch('./userInfo.do?empl_id=' + id)
				.then(resp => { return resp.json(); })
				.then(data => {
					console.log(data);
					var d = data[0];
					var input = document.createElement('input');
					var hiddenInput = document.createElement('input');
					var span = document.createElement('span');
					var inSpan = document.createElement('span');
					var btn = document.createElement('button');
					span.setAttribute('class', 'input-group-btn');
					inSpan.setAttribute('class', 'ki-solid ki-cross-square fs-1');
					inSpan.setAttribute('style', 'margin-left: -10px;');
					btn.setAttribute('class', 'btn btn-default');
					input.setAttribute('value', d.empl_name);
					input.setAttribute('class', 'form-control');
					input.setAttribute('readonly', 'readonly');
					hiddenInput.setAttribute('type', 'hidden');
					hiddenInput.setAttribute('value', d.empl_id);
					btn.append(inSpan);
					span.append(btn);
					emp.append(input);
					emp.append(hiddenInput);
					emp.append(span);
					
					var selNode = $('#jstree').jstree('get_selected');
					$('#jstree').jstree('hide_node', selNode);
				})
				.catch(err => { console.log(err) });
		}
	}, 100);
}

function closeRefer() {
	window.close();
}      

function saveRefer() {
	
}









                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     