onload = function() {
	
	// 동적 참조자 행 추가
	document.getElementById('tbody1').addEventListener('click', function(e) {
		var addRow = e.target.id;
		if(addRow.startsWith('addRow1')){
			var rowCount = document.querySelectorAll('#tbody1 tr').length + 1;
			console.log(rowCount);
			var newRow = '<tr>                                                                                                    '
									+	'		<td>                                                                          '
									+	'			<button class="cancelBtn btn btn-sm btn-basic" id="addRow1">➕</button>    '
									+	'		</td>                                                                         '
									+	'		<td>                                                                          '
									+	'			<button class="cancelBtn btn btn-sm btn-basic">➖</button>                '
									+	'		</td>                                                                         '
									+	'		<td>                                                                          '
									+	'			<input class="form-control form-control-solid" id="re'+rowCount+'">                  '
									+	'		</td>                                                                         '
									+	'		<td>                                                                          '
									+	'			<input class="form-control form-control-solid" id="rk'+rowCount+'">                  '
									+	'		</td>                                                                         '
									+	'		<td>                                                                          '
									+	'			<input class="form-control form-control-solid" id="dp'+rowCount+'">                  '
									+	'		</td>                                                                         '
									+	'		<td style="display: none;">                                                   '
									+	'			<span id="rid'+rowCount+'"></span>                                                   '
									+	'		</td>                                                                         '
									+	'	</tr>'                                                                            
			var tbody1 = document.querySelector('#tbody1');
			document.querySelectorAll('#tbody1 td>button')[rowCount * 2 - 4].removeAttribute('id')
			document.querySelectorAll('#tbody1 td>button')[rowCount * 2 - 4].textContent = '';
			tbody1.insertAdjacentHTML('beforeend', newRow);
		}
	});
	
	// 동적 참조부서 행 추가
	document.getElementById('tbody2').addEventListener('click', function(e) {
		var addRow = e.target.id;
		if(addRow.startsWith('addRow2')){
			var rowCount = document.querySelectorAll('#tbody2 tr').length + 1;
			console.log(rowCount);
			var newRow = '<tr>'
								+'					<td>                                                                         '
								+'						<button class="cancelBtn btn btn-sm btn-basic" id="addRow2">➕</button>  '
								+'					</td>                                                                        '
								+'					<td>                                                                         '
								+'						<button class="cancelBtn btn btn-sm btn-basic">➖</button>               '
								+'					</td>                                                                        '
								+'					<td>                                                                         '
								+'						<input class="form-control form-control-solid" id="rd'+rowCount+'">'
								+'					</td>'
								+'					<td style="display: none;"'
								+'						<span id="rde'+rowCount+'"></span>'
								+'					</td>'
								+'				</tr>';                              
			var tbody1 = document.querySelector('#tbody2');
			document.querySelectorAll('#tbody2 td>button')[rowCount * 2 - 4].removeAttribute('id')
			document.querySelectorAll('#tbody2 td>button')[rowCount * 2 - 4].textContent = '';
			tbody1.insertAdjacentHTML('beforeend', newRow);
		}
	});
	
	
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

// onload 함수
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
	$('#addLine').on('click', function(e) {
		e.preventDefault();
		let approverData;
		let favoName = document.getElementById('favoName').value;
		let empl_id = document.getElementById('empl_id').value;
		let appr1 = document.getElementById('id1').textContent;
		let appr2 = document.getElementById('id2').textContent;
		let appr3 = document.getElementById('id3').textContent;
		if(appr3.length > 0) {
			approverData = [
								{
									empl_id : empl_id,
									siaf_favo_name : favoName
								},
								{
									firstApprover: appr1,
									appr_depth : "1"
								},
								{
									secondApprover : appr2,
									appr_depth : "2"
								},
								{
									thirdApprover : appr3,
									appr_depth : "3"
								}
							];
		}
		if(appr1.length > 0 && appr2.length > 0 && appr3.length == 0) {
			approverData = [
								{
									empl_id : empl_id,
									siaf_favo_name : favoName
								},
								{
									firstApprover: appr1,
									appr_depth : "1"
								},
								{
									secondApprover : appr2,
									appr_depth : "2"
								}
							];
		}
		if(appr3.length == 0 && appr2.length == 0 && appr1.length > 0) {
			approverData = [
								{
									empl_id : empl_id,
									siaf_favo_name : favoName
								},
								{
									firstApprover: appr1,
									appr_depth : "1"
								}
							];
		}
		
		console.log(approverData);
		if(approverData != null) {
			fetch('./insertFavoList.do', {
				method: 'post',
				headers: {'Content-Type': 'application/json'},
				body: JSON.stringify(approverData)
			})
				.then(resp => {
					return resp.text();
				})
				.then(data => {
					alert(data);
					approverData = null;
					document.getElementById('favoName').value = '';
				})
				.catch(err => {
					console.log(err);
				});
		} else {
			console.log(appr1, appr2, appr3)
			alert('결재자를 선택해주세요');
		}
	})
	
	
});
function signFavo() {
	location.href='./signFavo.do';
}

var fFlag;
var sFlag;
var tFlag;

// + 버튼으로 라인 보내기
function pick() {
	setTimeout(function() {
		var id = $('#jstree').jstree('get_selected')[0];
		fetch('./userInfo.do?empl_id=' + id)
		.then(resp => {return resp.json();})
		.then(data => {
			console.log(data);
			var f = data[0];
			var first = $('#first').val();
			var second = $('#second').val();
			var third = $('#third').val();
			var posFlag = f.coco_name_rnm;
			var resFlag;
			switch (posFlag) {
				case '사원': resFlag = 1; break;
				case '대리': resFlag = 2; break;
				case '과장': resFlag = 3; break;
				case '차장': resFlag = 4; break;
				case '부장': resFlag = 5; break;
				case '상무': resFlag = 6; break;
				case '전무': resFlag = 7; break;
				case '부사장': resFlag = 8; break;
				case '사장': resFlag = 9; break;
				default: resFlag = 0;
			}
			
			if(first.length == 0) {
				fFlag = resFlag;
			} else if(first.length > 0 && second.length == 0) {
				sFlag = resFlag;
			} else if (first.length > 0 && second.length > 0 && third.length == 0) {
				tFlag = resFlag;
			}
			
			if(fFlag > sFlag || fFlag > tFlag || sFlag > tFlag) {
				alert('더 높은 직급의 결재자를 선택해주세요');
				return;
			}
			console.log(fFlag, sFlag, tFlag)
			if(first.length == 0) {
				$('#first').val(f.empl_name);
				$('#rk1').val(f.coco_name_rnm);
				$('#dp1').val(f.coco_name_dnm);
				$('#id1').text(f.empl_id);
				$('#de1').val(1);
			} else if(first.length > 0 && second.length == 0) {
				$('#second').val(f.empl_name);
				$('#rk2').val(f.coco_name_rnm);
				$('#dp2').val(f.coco_name_dnm);
				$('#id2').text(f.empl_id);
				$('#de2').val(2);
			} else if(first.length > 0 && second.length > 0 && third.length == 0) {
				$('#third').val(f.empl_name);
				$('#rk3').val(f.coco_name_rnm);
				$('#dp3').val(f.coco_name_dnm);
				$('#id3').text(f.empl_id);
				$('#de3').val(3);
			}
			var selNode = $('#jstree').jstree('get_selected');
			$('#jstree').jstree('hide_node', selNode);
		})
		.catch(err => {console.log(err)});
		
	}, 100);
}

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             