// 로그인한 사용자의 직급을 숫자처리
var myPositionFlagText = document.getElementById('positionFlag').value;
var myPositionFlag;
switch (myPositionFlagText) {
	case '사원': myPositionFlag = 1; break;
	case '대리': myPositionFlag = 2; break;
	case '과장': myPositionFlag = 3; break;
	case '차장': myPositionFlag = 4; break;
	case '부장': myPositionFlag = 5; break;
	case '상무': myPositionFlag = 6; break;
	case '전무': myPositionFlag = 7; break;
	case '부사장': myPositionFlag = 8; break;
	case '사장': myPositionFlag = 9; break;
	default: myPositionFlag = 0;
}

// 결재순번 로직을 위한 변수 선언
var first;
var second;
var third;

// 결재자 최종 저장을 위한 변수 선언
var firstApprover;
var secondApprover;
var thirdApprover;

var fFlag;
var sFlag;
var tFlag;

loadFavoLineList();
loadFavoList();

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
//		console.log(selectNode);
		// 숨겼던 노드를 다시 보여줌
		$('#jstree').jstree('show_node', selectNode);
		// input창 비움
		row.find('td input').val('');
		row.find('td span').text('');
		$('#de1').val(1);
		$('#de2').val(2);
		$('#de3').val(3);

	});
	
	// 로그인한 본인 아이디 숨김
	setTimeout(function () {
		var empl_id = document.getElementById('empl_id').value;
		var allNode = $('#jstree').jstree('get_json', '#', { flat: true });
		var sel = allNode.find(function(node) {
			return node.id == empl_id;
		})
		$('#jstree').jstree('hide_node', sel);
	}, 2000)
	
	
	// 결재자 리스트를 json형태로 저장 (즐겨찾기 라인추가)
	$('#addLine').on('click', function(e) {
		e.preventDefault();
		let approverData;
		let favoName = document.getElementById('favoName').value;
		let empl_id = document.getElementById('empl_id').value;
		let appr1 = document.getElementById('id1').textContent;
		let appr2 = document.getElementById('id2').textContent;
		let appr3 = document.getElementById('id3').textContent;
		if(appr1.length > 0 && appr2.length > 0 && appr3.length > 0) {
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
		if(appr1.length > 0 && appr2.length == 0 && appr3.length == 0) {
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
		
//		console.log(approverData);
		if(approverData != null) {
			fetch('/doc/insertFavoList.do', {
				method: 'post',
				headers: {'Content-Type': 'application/json'},
				body: JSON.stringify(approverData)
			})
				.then(resp => {
					return resp.text();
				})
				.then(data => {
					loadFavoLineList();
					loadFavoList();
					swalAlert(data,'','','확인');
					approverData = null;
					document.getElementById('favoName').value = '';
				})
				.catch(err => {
					console.log(err);
				});
				
		} else {
//			console.log(appr1, appr2, appr3)
			swalAlert('결재자를 선택해주세요','','','확인');
		}
	})
	
	document.getElementById('saveLineBtn').addEventListener('click', saveLine);
	document.getElementById('cancelBtn').addEventListener('click', function() {
		self.close();
	});
	
	function saveLine() {
		let sidb_doc_json;
		let appr1 = document.getElementById('id1').textContent;
		let appr2 = document.getElementById('id2').textContent;
		let appr3 = document.getElementById('id3').textContent;
		if(appr1.length > 0 && appr2.length > 0 && appr3.length > 0) {
			sidb_doc_json = [
								{
									appr_id : appr1,
									appr_depth : "1",
									appr_name : document.getElementById('first').value,
									appr_position : document.getElementById('rk1').value
								},
								{
									appr_id : appr2,
									appr_depth : "2",
									appr_name : document.getElementById('second').value,
									appr_position : document.getElementById('rk2').value
								},
								{
									appr_id : appr3,
									appr_depth : "3",
									appr_name : document.getElementById('third').value,
									appr_position : document.getElementById('rk3').value
								}
							];
		}
		if(appr1.length > 0 && appr2.length > 0 && appr3.length == 0) {
			sidb_doc_json = [
								{
									appr_id : appr1,
									appr_depth : "1",
									appr_name : document.getElementById('first').value,
									appr_position : document.getElementById('rk1').value
								},
								{
									appr_id : appr2,
									appr_depth : "2",
									appr_name : document.getElementById('second').value,
									appr_position : document.getElementById('rk2').value
								}
							];
		}
		if(appr1.length > 0 && appr2.length == 0 && appr3.length == 0) {
			sidb_doc_json = [
								{
									appr_id : appr1,
									appr_depth : "1",
									appr_name : document.getElementById('first').value,
									appr_position : document.getElementById('rk1').value
								}
							];
		}
		
		opener.postMessage(sidb_doc_json, location.origin);
		sweetAlertConfirm('결재선을 저장할까요?',function() {
			if(sidb_doc_json == undefined) {
				swalAlert('결재자를 지정해주세요','','','확인');
			} else {
				self.close();
			}
		},'');
		
	}
	
// 초기화 버튼
document.getElementById('initial').addEventListener('click', function() {

	var input = document.querySelectorAll('tr:not(:first-child):not(:nth-child(1)) input');
	for (i of input) {
		i.value = '';
	}
	var span = document.querySelectorAll('tr:not(:first-child):not(:nth-child(1)) span');
	for (s of span) {
		s.textContent = '';
	}
	var allNode = $('#jstree').jstree('get_json', '#', { flat: true });
	allNode.forEach(function(node) {
		$('#jstree').jstree('show_node', node);
	});
		
});
	
	// 즐겨찾기 삭제
	document.getElementById('delBtn').addEventListener('click', function() {
		var select = document.getElementById('apprList').value;
		if(select != '결재자를 선택해주세요') {
			sweetAlertConfirm("즐겨찾기를 삭제할까요?", delFavo, '');
		} else {
			swalAlert('결재자를 선택해주세요', '', '', '확인');
		}
		
	});
	
	// 즐겨찾기 라인 삭제
	document.getElementById('delLineBtn').addEventListener('click', function() {
		var select = document.getElementById('apprLineList').value;
		if(select != '결재선을 선택해주세요') {
			sweetAlertConfirm("즐겨찾기를 삭제할까요?", delLine, '')
		} else {
			swalAlert('라인을 선택해주세요', '', '', '확인');
		}
	});	
		
		
	// 즐겨찾기 결재자 결재선으로 보내기
	document.getElementsByName('insBtn')[0].addEventListener('click', function() {
		
		var select = document.querySelector('select#apprList');
		var selValue = select.options[select.selectedIndex].value;
		if(selValue == '결재자를 선택해주세요') {
			swalAlert('결재자를 선택해주세요', '', '', '확인');
			return;
		}
		fetch('/doc/getFavo.do?siaf_favo_cd=' + selValue)
		.then(resp => {return resp.json();})
		.then(data => {
//			console.log(data);
			var f = data[0];
			var first = $('#first').val();
			var second = $('#second').val();
			var third = $('#third').val();
			var posFlag = f.coco_name_rnm;
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
				swalAlert('높은 직급의 결재자를 선택해주세요','','','확인');
				return;
			}
//			console.log(fFlag, sFlag, tFlag)
			if(f.empl_name == $('#first').val() || f.empl_name == $('#second').val() || f.empl_name == $('#third').val()) {
				swalAlert('중복된 결재자 입니다','','','확인');
				return;
			}
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
			
			var allNode = $('#jstree').jstree('get_json', '#', { flat: true });
			var selectNode = allNode.find(function(node) {
				return node.id == f.empl_id;
			});
//			console.log(selectNode);
			$('#jstree').jstree('hide_node', selectNode);
			
		})
		.catch(err => {console.log(err);});
	})
	
	// 즐겨찾기 라인 결재선으로 보내기
	document.getElementsByName('selectApprLine')[0].addEventListener('click', function() {
		var select = document.querySelector('select#apprLineList');
		var selList = select.options[select.selectedIndex].value;
		if(selList == '결재선을 선택해주세요') {
			swalAlert('라인을 선택해주세요', '', '', '확인');
			return;
		}
//		console.log(selList);
		fetch('/doc/favoInfo.do?siaf_favo_cd=' + selList)
		.then(resp => {return resp.json()})
		.then(data => {
//			console.log(data);
			if(data.length == 1) {
				let f = data[0];
				$('#first').val(f.empl_name);
				$('#rk1').val(f.coco_name_rnm);
				$('#dp1').val(f.coco_name_dnm);
				$('#id1').text(f.empl_id);
				$('#de1').val(1);
			}
			if(data.length == 2) {
				let f = data[0];
				let s = data[1];
				$('#first').val(f.empl_name);
				$('#rk1').val(f.coco_name_rnm);
				$('#dp1').val(f.coco_name_dnm);
				$('#id1').text(f.empl_id);
				$('#de1').val(1);
				$('#second').val(s.empl_name);
				$('#rk2').val(s.coco_name_rnm);
				$('#dp2').val(s.coco_name_dnm);
				$('#id2').text(s.empl_id);
				$('#de2').val(2);
			}
			if(data.length == 3) {
				let f = data[0];
				let s = data[1];
				let t = data[2];
				$('#first').val(f.empl_name);
				$('#rk1').val(f.coco_name_rnm);
				$('#dp1').val(f.coco_name_dnm);
				$('#id1').text(f.empl_id);
				$('#de1').val(1);
				$('#second').val(s.empl_name);
				$('#rk2').val(s.coco_name_rnm);
				$('#dp2').val(s.coco_name_dnm);
				$('#id2').text(s.empl_id);
				$('#de2').val(2);
				$('#third').val(t.empl_name);
				$('#rk3').val(t.coco_name_rnm);
				$('#dp3').val(t.coco_name_dnm);
				$('#id3').text(t.empl_id);
				$('#de3').val(3);
			}
		})
		.catch(err => {
			console.log(err);
		});
	})
	
	
	
	$('#jstree').jstree({
		// 검색기능 , 우클릭메뉴, 라벨 효과
		plugins: ['search', 'contextmenu', 'wholerow'],
		// 우클릭메뉴 정의
		contextmenu: {
			items: function(node) {
				if (node.children.length == 0 && node.state.disabled != true) {
//					console.log(node.state.disabled);
					return {
						m1: {
							label: '결재자 지정',
							icon: 'ki-solid ki-notepad-edit',
							// 결재자 지정 로직
							action: function() {

								var sel = $("#jstree").jstree('get_selected'); // 선택된 노드
								fetch('/doc/userInfo.do?empl_id=' + sel[0])
									.then(resp => { return resp.json() })
									.then(data => {

//										console.log(data);
										var val1 = $('#first').val();
										var val2 = $('#second').val();

										if (val1.length > 0 && val2.length > 0) {
											third = data[0].coco_name_rnm;
										} else if (val1.length > 0 && val2.length == 0) {
											second = data[0].coco_name_rnm;
										} else {
											first = data[0].coco_name_rnm;
										}

										switch (first) {
											case '사원': first = 1; break;
											case '대리': first = 2; break;
											case '과장': first = 3; break;
											case '차장': first = 4; break;
											case '부장': first = 5; break;
											case '상무': first = 6; break;
											case '전무': first = 7; break;
											case '부사장': first = 8; break;
											case '사장': first = 9; break;
											default: 0;
										}
										switch (second) {
											case '사원': second = 1; break;
											case '대리': second = 2; break;
											case '과장': second = 3; break;
											case '차장': second = 4; break;
											case '부장': second = 5; break;
											case '상무': second = 6; break;
											case '전무': second = 7; break;
											case '부사장': second = 8; break;
											case '사장': second = 9; break;
											default: 0;
										}
										switch (third) {
											case '사원': third = 1; break;
											case '대리': third = 2; break;
											case '과장': third = 3; break;
											case '차장': third = 4; break;
											case '부장': third = 5; break;
											case '상무': third = 6; break;
											case '전무': third = 7; break;
											case '부사장': third = 8; break;
											case '사장': third = 9; break;
											default: 0;
										}

//										console.log(first, second, third);

										// 결재자를 순서대로 화면에 뿌려줌
										if (val1.length > 0 && val2.length > 0) {
											if (second > third) {
												swalAlert('높은 직급의 결재자를 선택해주세요','','','확인');
												return;
											}
											$('#third').val(data[0].empl_name); // 3차결재자
											$('#rk3').val(data[0].coco_name_rnm);
											$('#dp3').val(data[0].coco_name_dnm);
											$('#id3').text(data[0].empl_id);
											$('#de3').val(3);
											var id = $('#jstree').jstree('get_selected')[0];
											$('#jstree').jstree('hide_node', id);
											$('#jstree').jstree('deselect_all');
										} else if (val1.length > 0 && val2.length == 0) {
											if (first > second) {
												swalAlert('높은 직급의 결재자를 선택해주세요','','','확인');
												return;
											}
											$('#second').val(data[0].empl_name); // 2차결재자
											$('#rk2').val(data[0].coco_name_rnm);
											$('#dp2').val(data[0].coco_name_dnm);
											$('#id2').text(data[0].empl_id);
											$('#de2').val(2);
											var id = $('#jstree').jstree('get_selected')[0];
											$('#jstree').jstree('hide_node', id);
											$('#jstree').jstree('deselect_all');
										} else {
											if (first > third || first > second) {
												swalAlert('높은 직급의 결재자를 선택해주세요','','','확인');
												return;
											}
											$('#first').val(data[0].empl_name); // 1차결재자
											$('#rk1').val(data[0].coco_name_rnm);
											$('#dp1').val(data[0].coco_name_dnm);
											$('#id1').text(data[0].empl_id);
											$('#de1').val(1);
											var id = $('#jstree').jstree('get_selected')[0];
											$('#jstree').jstree('hide_node', id);
											$('#jstree').jstree('deselect_all');
										}
									})
									.catch(err => {
										console.log(err);
									});

							}
						},
						m2: {
							label: '즐겨찾기 추가',
							icon: 'ki-duotone ki-star',
							// 즐겨찾기 등록 
							action: function(e) {
//								console.log(e);
								var id = document.getElementById('empl_id').value
								var siaf_appr_id = $('#jstree').jstree('get_selected')[0];
								let addData = {
									empl_id: id,
									siaf_appr_id: siaf_appr_id
								}
//								console.log(siaf_appr_id, id)
								fetch('/doc/insertFavo.do', {
									headers: { 'Content-Type': 'application/json' },
									method: 'post',
									body: JSON.stringify(addData)
								})
									.then(resp => {
										return resp.text();
									})
									.then(data => {
										loadFavoLineList();
										loadFavoList();
										swalAlert(data, '', '', '확인');
									})
									.catch(error => {
										console.log(error);
									});

							}
						}
					}
				}
			}
		},
		core: {
			data: {
				url: '/doc/signTree.do',
				method: 'get',
				dataType: 'json',
				success: function(data) {
					data.forEach(function(node, idx) {
//						console.log(myPositionFlag);
						if (node.pos_na != undefined) {
							if (myPositionFlag > node.pos_flag) {
								node.text = '<span onclick="empInfoLayer(' + node.id + ', this)">' + node.text + ' (' + node.pos_na + ')&nbsp;&nbsp;<span class="positionFlag" style="display: none;">' + node.pos_flag + '</span></span>';
							} else {
								node.text = '<span onclick="empInfoLayer(' + node.id + ', this)">' + node.text + ' (' + node.pos_na + ')&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button onclick="pick()" class="btn btn-basic btn-sm addd" style="padding: 0.2px;">➕</button><span class="positionFlag" style="display: none;">' + node.pos_flag + '</span></span>';
							}
						}

//						if (myPositionFlag > parseInt(node.pos_flag) && node.pos_na != null) {
//							node.state = { disabled: true };
//						}

					});


				},
				error: function() {
					alert('데이터 전송 실패');
				}
			}
		}
	});
	
	// jsTree 검색창의 엔터키로 검색 가능하게 하는 함수
	$('#schName').on('keydown', function(e) {
		if (e.keyCode == 13) {
			$('#schBtn').click();
		}
	});
	
	



// + 버튼으로 라인 보내기
function pick() {
	setTimeout(function() {
		var id = $('#jstree').jstree('get_selected')[0];
		fetch('/doc/userInfo.do?empl_id=' + id)
		.then(resp => {return resp.json();})
		.then(data => {
//			console.log(data);
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
				swalAlert('높은 직급의 결재자를 선택해주세요','','','확인');
				return;
			}
//			console.log(fFlag, sFlag, tFlag)
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

// 즐겨찾기 결재자 삭제
function delFavo() {
	var selectId = document.querySelector('#apprList').value;
	fetch('/doc/deleteFav.do?siaf_favo_cd=' + selectId)
		.then(resp => {
			return resp.text();
		}).then(data => {
			loadFavoLineList();
			loadFavoList();
			swalAlert(data,'','','확인');
			document.getElementById('apprList').innerHTML = '';
			var sel = document.getElementById('apprList');
			var opt = document.createElement('option');
			opt.textContent = '결재자를 선택해주세요';
			sel.appendChild(opt);
		}).catch(err => {
			console.log(err);
		});
	
	
}

// 즐겨찾기 라인 삭제
function delLine() {
	var select = document.querySelector('select#apprLineList');
	var selList = select.options[select.selectedIndex].value;
//	console.log(selList);
	fetch('/doc/deleteFav.do?siaf_favo_cd=' + selList)
		.then(resp => {
			return resp.text();
		}).then(data => {
			loadFavoLineList();
			loadFavoList();
			swalAlert(data,'','','확인');
			document.getElementById('apprLineList').innerHTML = '';
			var sel = document.getElementById('apprLineList');
			var opt = document.createElement('option');
			opt.textContent = '결재선을 선택해주세요';
			sel.appendChild(opt);
		}).catch(err => {
			console.log(err);
		});
		
}

// 즐겨찾기 라인불러오기
function loadFavoLineList() {
	
	var id = document.getElementById('empl_id').value;
	var apprLineList = document.getElementById('apprLineList');
	apprLineList.innerHTML = '';
	var opt = document.createElement('option');
	opt.textContent = '결재선을 선택해주세요';
	apprLineList.appendChild(opt)

	fetch('/doc/signFavoLineList.do?empl_id=' + id)
		.then(resp => {
			return resp.json();
		})
		.then(data => {
//			console.log(data);
			var lineList = data.lineList;
			var empList = data.resultList[0];
//			console.log(empList);

			for (let i = 0; i < lineList.length; i++) {
//				console.log(lineList[i].siaf_favo_cd, lineList[i].siaf_favo_name);
				var opt = document.createElement('option');
				opt.setAttribute('value', lineList[i].siaf_favo_cd);
				opt.textContent = lineList[i].siaf_favo_name + ' ➡️ (';

				for (let j = 0; j < empList[i].length; j++) {
//					console.log(empList[i][j].empl_id, empList[i][j].empl_name);
					if (j == empList[i].length - 1) {
						opt.textContent += empList[i][j].empl_name;
					} else {
						opt.textContent += empList[i][j].empl_name + ' - ';
					}
				}
				opt.textContent += ')';
				apprLineList.appendChild(opt);
			}
		})
		.catch(error => {
			console.log(error);
		});
}

// 즐겨찾기 불러오기
function loadFavoList() {
	var id = document.getElementById('empl_id').value;
	var apprList = document.getElementById('apprList');
	apprList.innerHTML = '';
	var opt = document.createElement('option');
	opt.textContent = '결재자를 선택해주세요';
	apprList.appendChild(opt);
	fetch('/doc/signFavoList.do?empl_id=' + id)
		.then(resp => {
			return resp.json();
		})
		.then(data => {
//			console.log(data);

			for (let d of data) {
				var opt = document.createElement('option');
				opt.setAttribute('value', d.siaf_favo_cd);
				opt.textContent = d.employee.empl_name + ' (' + d.employee.coco_name_pnm + ')';
				apprList.appendChild(opt);
			}

		})
		.catch(error => {
			console.log(error);
		});
}
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             