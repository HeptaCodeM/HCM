
$('#schName').focus();

// 검색기능
$('#schBtn').on('click', function() {
	$('#jstree').jstree().search($('#schName').val());
	$('#jstree').jstree('deselect_all');
});

// jstree
$('#jstree').jstree({
	// 검색기능 , 우클릭메뉴, 라벨 효과
	plugins: ['search', 'contextmenu', 'wholerow'],
	// 우클릭메뉴 정의
	core: {
		data: {
			url: '/doc/signTree.do',
			method: 'get',
			dataType: 'json',
			success: function(data) {
				data.forEach(function(node) {
					if (node.pos_na != undefined) {
						node.text = node.text + ' (' + node.pos_na + ')&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button onclick="pick()" class="btn btn-basic btn-sm addd" style="padding: 0.2px;">➕</button><span class="positionFlag" style="display: none;">' + node.pos_flag + '</span>';
					} else {
						node.text = node.text + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button onclick="pick()" class="btn btn-basic btn-sm addd" style="padding: 0.2px;">➕</button>'
					}
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

// 닫기버튼
document.getElementById('closeRefer').addEventListener('click', closeRefer);

// 저장버튼
document.getElementById('saveRefer').addEventListener('click', saveRefer);

var refer = '';
var referDept = '';

var idx1 = 1;
var idx2 = 1;

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
			btn.setAttribute('onclick', 'removeRefer(this)');
			input.setAttribute('value', dname);
			input.setAttribute('class', 'form-control');
			input.setAttribute('readonly', 'readonly');
			input.setAttribute('id', 'refDept' + idx1);
			hiddenInput.setAttribute('type', 'hidden');
			hiddenInput.setAttribute('value', id);
			btn.append(inSpan);
			span.append(btn);
			dept.append(input);
			dept.append(hiddenInput);
			dept.append(span);
			referDept += id + ','
			idx1++;
			
		} else {
			// 참조자 추가
			var emp = document.getElementById('ref-emp');
			fetch('/doc/userInfo.do?empl_id=' + id)
				.then(resp => { return resp.json() })
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
					btn.setAttribute('onclick', 'removeRefer(this)');
					input.setAttribute('value', d.empl_name);
					input.setAttribute('class', 'form-control');
					input.setAttribute('readonly', 'readonly');
					input.setAttribute('id', 'ref' + idx2);
					hiddenInput.setAttribute('type', 'hidden');
					hiddenInput.setAttribute('value', d.empl_id);
					btn.append(inSpan);
					span.append(btn);
					emp.append(hiddenInput);
					emp.append(input);
					emp.append(span);
					
					refer += d.empl_id + ','
					idx2++;
					
					var selNode = $('#jstree').jstree('get_selected');
					$('#jstree').jstree('hide_node', selNode);
				})
				.catch(err => { 
					console.log(err);
				});
		}
	}, 100);
}

function closeRefer() {
	window.close();
}      

function saveRefer() {
	
	var refName = '';
	var refDeptName = '';
	for(let i=0; i<idx1-1; i++) {
		var num1 = document.getElementById('refDept' + (i+1));
		refDeptName += num1.value + ',';
	}
	for(let i=0; i<idx2-1; i++) {
		var num2 = document.getElementById('ref' + (i+1));
		refName += num2.value + ',';
	}
	refName = refName.substring(0, refName.lastIndexOf(','));
	refDeptName = refDeptName.substring(0, refDeptName.lastIndexOf(','));
	console.log(refName);
	console.log(refDeptName);
	
	refer = refer.substring(0, refer.lastIndexOf(','));
	referDept = referDept.substring(0, referDept.lastIndexOf(','));
	
	var ref = {
		empl_ref : refer,
		empl_ref_name : refName
	}
	var refDept = {
		empl_dept_cd : referDept,
		empl_dept_name : refDeptName
	}
	
	if(refer.length == 0 && referDept.length == 0) {
		sweetAlertConfirm('참조자가 없습니다 저장할까요?',function() {
			self.close();
		})
	} else if(refer.length == 0 && referDept.length > 0) {
		sweetAlertConfirm('참조자를 저장할까요?',function() {
			opener.postMessage(refDept, '*');
			self.close();
		})
	} else if(refer.length > 0 && referDept.length == 0) {
		sweetAlertConfirm('참조자를 저장할까요?',function() {
			opener.postMessage(ref, '*');
			self.close();
		})
	} else {
		sweetAlertConfirm('참조자를 저장할까요?',function() {
			opener.postMessage(ref, '*');
			opener.postMessage(refDept, '*');
			self.close();
		})
	}
	
}








                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     