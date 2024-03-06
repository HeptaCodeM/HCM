onload = function() {
	
	// 즐겨찾기 불러오기
	var id = document.getElementById('empl_id').value;
	document.getElementById('apprList').addEventListener('focus', function() {
		
		var apprList = document.getElementById('apprList');
		apprList.innerHTML = '';
		var opt = document.createElement('option');
		opt.textContent = '결재자를 선택해주세요';
		apprList.appendChild(opt);
		fetch('./signFavoList.do?empl_id=' + id)
			.then(resp => {
				return resp.json();
			})
			.then(data => {
				console.log(data);
				
				for (let d of data) {
					var opt = document.createElement('option');
					opt.setAttribute('value', d.siaf_favo_cd);
					opt.textContent = d.employee.empl_name;
					apprList.appendChild(opt);
				}

			})
			.catch(error => {
				console.log(error)
			});
	});
	
	// 즐겨찾기 라인 불러오기
	document.getElementById('apprLineList').addEventListener('focus', function() {
		
		var apprLineList = document.getElementById('apprLineList');	
		apprLineList.innerHTML = '';
		var opt = document.createElement('option');
		opt.textContent = '결재선을 선택해주세요';
		apprLineList.appendChild(opt)
		
		fetch('./signFavoLineList.do?empl_id=' + id)
			.then(resp => {
				return resp.json();
			})
			.then(data => {
				console.log(data);
				var lineList = data.lineList;
				var empList = data.resultList[0];
				console.log(empList);
				
				for (let i = 0; i < lineList.length; i++) {
					console.log(lineList[i].siaf_favo_cd, lineList[i].siaf_favo_name);
					var opt = document.createElement('option');
					opt.setAttribute('value', lineList[i].siaf_favo_cd);
					opt.textContent = lineList[i].siaf_favo_name + ' ➡️ (';
					
					for (let j = 0; j < empList[i].length; j++) {
						console.log(empList[i][j].empl_id, empList[i][j].empl_name);
						if(j == empList[i].length - 1) {
							opt.textContent += empList[i][j].empl_name
						} else {
							opt.textContent += empList[i][j].empl_name + ' - '
						}
					}
					opt.textContent += ')';
					apprLineList.appendChild(opt);
				}
			})
			.catch(error => {
				console.log(error);
			});
	});
	
	// 즐겨찾기 삭제
	document.getElementById('delBtn').addEventListener('click', function() {
		var selectId = document.querySelectorAll('select')[0].value;
	 	fetch('./deleteFav.do?siaf_favo_cd=' + selectId)
			.then(resp => {
				return resp.text();
			}).then(data => {
				alert(data);
				document.getElementById('apprList').innerHTML = '';
				var sel = document.getElementById('apprList');
				var opt = document.createElement('option');
				opt.textContent = '결재자를 선택해주세요';
				sel.appendChild(opt);
			}).catch(err => {
				console.log(err);
			});
		})
		
	// 즐겨찾기 결재자 결재선으로 보내기
	document.getElementById('insBtn').addEventListener('click', function() {
		let first = document.getElementById('first');
		let second = document.getElementById('second');
		let third = document.getElementById('third');
		var select = document.querySelector('select#apprList');
		var selValue = select.options[select.selectedIndex].value;
		var selContent = select.options[select.selectedIndex].textContent;
		
	})
	
	// 즐겨찾기 라인 결재선으로 보내기
	document.getElementById('selectApprLine').addEventListener('click', function() {
		var select = document.querySelector('select#apprLineList');
		var selList = select.options[select.selectedIndex].value;
		console.log(selList);
	})
	
	// 즐겨찾기 라인 삭제
	document.getElementById('delLineBtn').addEventListener('click', function() {
		var select = document.querySelector('select#apprLineList');
		var selList = select.options[select.selectedIndex].value;
		console.log(selList);
		fetch('./deleteFav.do?siaf_favo_cd=' + selList)
			.then(resp => {
				return resp.text();
			}).then(data => {
				alert(data);
				document.getElementById('apprLineList').innerHTML = '';
				var sel = document.getElementById('apprLineList');
				var opt = document.createElement('option');
				opt.textContent = '결재선을 선택해주세요';
				sel.appendChild(opt);
			}).catch(err => {
				console.log(err);
			});
	})
}

// jsTree 검색창의 엔터키로 검색 가능하게 하는 함수
$('#schName').on('keydown', function(e) {
	if (e.keyCode == 13) {
		$('#schBtn').click();
	}
});

// 로그인한 사용자의 직급을 숫자처리
//var myPositionFlagText = document.getElementById('positionFlag').value;
//var myPositionFlag;
//switch (myPositionFlagText) {
//	case '사원': myPositionFlag = 1; break;
//	case '대리': myPositionFlag = 2; break;
//	case '과장': myPositionFlag = 3; break;
//	case '차장': myPositionFlag = 4; break;
//	case '부장': myPositionFlag = 5; break;
//	default: myPositionFlag = 0;
//}

// 결재자 직급에 따른 로직을 위한 변수 선언
var first;
var second;
var third;

// 결재자 최종 저장을 위한 변수 선언
var firstApprover;
var secondApprover;
var thirdApprover;

// jstree 출력
$().ready(function() {
	$('#jstree').jstree({
		// 검색기능 , 우클릭메뉴, 라벨 효과
		plugins: ['search', 'contextmenu', 'wholerow'],
		// 우클릭메뉴 정의
		contextmenu: {
			items: function(node) {
				if (node.children.length == 0 && node.state.disabled != true) {
					console.log(node.state.disabled);
					return {
						m1: {
							label: '결재자 지정',
							icon: 'fas fa-user',
							// 결재자 지정 로직
							action: function() {
								var sel = $("#jstree").jstree('get_selected'); // 선택된 노드
								var text = $("#jstree").jstree('get_text', sel); // 선택된 노드에서 텍스트 추출

								var team = $("#jstree").jstree('get_parent', sel); // 선택된 노드의 부모 노드
								var teamTxt = $('#jstree').jstree('get_text', team); // 부모 노드에서 텍스트 추출

								var group = $('#jstree').jstree('get_parent', team); // 부모의 부모 노드
								var groupTxt = $('#jstree').jstree('get_text', group); // 텍스트 추출

								var result = groupTxt + ' > ' + teamTxt + ' > ' + text.substring(0, text.lastIndexOf(')') + 1);

								var val1 = $('#first').val();
								var val2 = $('#second').val();
								
								if (val1.length > 0 && val2.length > 0) {
									third = result.substring(result.lastIndexOf('(') + 1, result.lastIndexOf(')'));
								} else if (val1.length > 0 && val2.length == 0) {
									second = result.substring(result.lastIndexOf('(') + 1, result.lastIndexOf(')'));
								} else {
									first = result.substring(result.lastIndexOf('(') + 1, result.lastIndexOf(')'));
								}
								
								switch(first) {
									case '사원' : first = 1; break;
									case '대리' : first = 2; break;
									case '과장' : first = 3; break;
									case '차장' : first = 4; break;
									case '부장' : first = 5; break;
									default : 0;
								}
								switch(second) {
									case '사원' : second = 1; break;
									case '대리' : second = 2; break;
									case '과장' : second = 3; break;
									case '차장' : second = 4; break;
									case '부장' : second = 5; break;
									default : 0;
								}
								switch(third) {
									case '사원' : third = 1; break;
									case '대리' : third = 2; break;
									case '과장' : third = 3; break;
									case '차장' : third = 4; break;
									case '부장' : third = 5; break;
									default : 0;
								}
								console.log(first, second, third);
								
								// 결재자를 순서대로 화면에 뿌려줌
								if (val1.length > 0 && val2.length > 0) {
									if(second > third) {
										alert('낮은 직급의 결재자를 지정할 수 없습니다');
										return;
									}
									$('#third').val(result); // 3차결재자
									var id = $('#jstree').jstree('get_selected')[0];
									$('#jstree').jstree('hide_node', id);
									$('#jstree').jstree('deselect_all');
									thirdApprover = id;
								} else if (val1.length > 0 && val2.length == 0) {
									if(first > second) {
										alert('낮은 직급의 결재자를 지정할 수 없습니다');
										return;
									}
									$('#second').val(result); // 2차결재자
									var id = $('#jstree').jstree('get_selected')[0];
									$('#jstree').jstree('hide_node', id);
									$('#jstree').jstree('deselect_all');
									secondApprover = id;
								} else {
									if(first > third || first > second) {
										alert('높은 직급의 결재자를 지정할 수 없습니다');
										return;
									}
									$('#first').val(result); // 1차결재자
									var id = $('#jstree').jstree('get_selected')[0];
									$('#jstree').jstree('hide_node', id);
									$('#jstree').jstree('deselect_all');
									firstApprover = id;
								}
							}
						},
						m2: {
							label: '즐겨찾기 추가',
							icon: 'fas fa-star',
							// 즐겨찾기 등록 
							action: function(e) {
								console.log(e);
								var id = document.getElementById('empl_id').value
								var siaf_appr_id = $('#jstree').jstree('get_selected')[0];
								console.log(siaf_appr_id, id)
								fetch('./insertFavo.do?siaf_appr_id=' + siaf_appr_id + '&empl_id=' + id, {
									headers : {'Content-Type' : 'x-www-form-urlencoded'},
								})
									.then(resp => {
										return resp.text();
									})
									.then(data => {
										alert(data);
									})
									.catch(error => {
										console.log(error);
									})
								
							}
						}
					}
				}
			}
		},
		core: {
			data : {
				url : './signTree.do',
				method : 'get',
				dataType : 'json',
				success : function(data) {
					data.forEach(function(node) {
						if(node.pos_na != undefined) {
							node.text = node.text + ' (' + node.pos_na + ')<span class="positionFlag" style="display: none;">' + node.pos_flag + '</span>&nbsp;&nbsp;<button class="btn btn-primary btn-sm" style="padding: 0.2px;">➡️</button>';
						}
						
					});
					
					data.forEach(function(node) {
//						if(myPositionFlag > parseInt(node.position_flag) && node.position != null) {
//							node.state = {disabled : true};
//						}
					});
//					console.log(myPositionFlagText, myPositionFlag)
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
		// input의 value 가져와서 > 기준으로 날리고 이름만 남겨서 저장
		var text = $(this).parent().siblings('input').val();
		var name = text.substring(text.lastIndexOf('>') + 1, text.lastIndexOf(')') + 1).trim();
		console.log(name);
		// json형태로 모든 노드를 불러오기..
		var allNode = $('#jstree').jstree('get_json', '#', { flat: true });
		// 모든 노드에서 이름으로 노드 찾기
		var selectNode = allNode.find(function(node) {
			return node.text.substring(0, node.text.lastIndexOf(')') + 1) == name;
		});
		console.log(selectNode);
		// 숨겼던 노드를 다시 보여줌
		$('#jstree').jstree('show_node', selectNode);
		// input창 비움
		$(this).parent().siblings('input').val('');

	});
//	var id = document.getElementById('id').value;
//	var appr_num = document.getElementById('appr_num').value;
	// 결재자 리스트를 json형태로 저장해 서버에 떤져줌
	$('#accept').on('click', function(e) {
		e.preventDefault();
		var approverData = {
			firstApprover: firstApprover,
			secondApprover: secondApprover,
			thirdApprover: thirdApprover,
			id : id,
			appr_num : appr_num
		};
		
		console.log(approverData);
		if(approverData.firstApprover != null) {
			
			fetch('./approve.do', {
				method: 'post',
				headers: { 'Content-Type': 'application/json' },
				body: JSON.stringify(approverData)
			})
				.then(resp => {
					return resp.text();
				})
				.then(data => {
					console.log(data);
				})
				.catch(err => {
					alert(err);
				});
		} else {
			alert('결재자를 선택해주세요');
		}
	})
});
function signFavo() {
	location.href='./signFavo.do';
}


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             