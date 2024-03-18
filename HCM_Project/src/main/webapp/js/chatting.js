var ws = null;
var sender = null;
var target = null;
var message = null;

onload = function() {
	if (ws === null) {
		ws = new WebSocket('ws://localhost:8080/hcmWs.do');
		ws.onopen = function() {
			console.log('WebSocket 연결 성공');
		}
	}
	document.addEventListener('keydown', function(e) {
		if (e.keyCode == '13') {
			sendMessage();
		}
	})
	
	document.getElementById('chatMain').addEventListener('click', function() {
		chatUserList();
		loadMessage();
	})
	
	ws.onmessage = function(e) {
		
		console.log(e);

		if (!e.data.includes('님으로 부터 메세지 도착')) {
			console.log('유저객체');
			var json = JSON.parse(e.data);
			var reDiv1 = document.createElement('div') // 아우터
			var reDiv2 = document.createElement('div') // 이너
			var reDiv3 = document.createElement('div') // 헤더아우터
			var reDiv4 = document.createElement('div') // 헤더이미지영역
			var reDiv5 = document.createElement('div') // 헤더텍스트영역
			var reDiv6 = document.createElement('div') // 바디(내용)
			var reImg = document.createElement('img'); // 이미지
			var reA = document.createElement('a'); // 헤더이름
			var reSpan = document.createElement('span'); // 헤더시간

			reDiv1.setAttribute('class', 'd-flex justify-content-start mb-10')
			reDiv2.setAttribute('class', 'd-flex flex-column align-items-start')
			reDiv3.setAttribute('class', 'd-flex align-items-center mb-2')
			reDiv4.setAttribute('class', 'symbol symbol-35px symbol-circle')
			reDiv5.setAttribute('class', 'ms-3')
			reDiv6.setAttribute('class', 'p-5 rounded bg-light-info text-gray-900 fw-semibold mw-lg-400px text-start')
//			reImg.setAttribute('src', '')
			reA.setAttribute('class', 'fs-5 fw-bold text-gray-900 text-hover-primary me-1')
			reSpan.setAttribute('class', 'text-muted fs-7 mb-1')
			
			reSpan.textContent = json.ch_date;
			reA.textContent = json.sender_name;
			reDiv6.textContent = json.ch_message;

			reDiv4.append(reImg);
			reDiv5.append(reA);
			reDiv5.append(reSpan);
			reDiv3.append(reDiv4);
			reDiv3.append(reDiv5);
			reDiv2.append(reDiv3);
			reDiv2.append(reDiv6);
			reDiv1.append(reDiv2);
			document.getElementById('mainDiv').append(reDiv1);
			// 채팅창 스크롤 맨아래로 내리기
			var chatContent = document.querySelector('#mainDiv');
			var chatHeigth = chatContent.scrollHeight;
			chatContent.scrollTop = chatHeigth;

		} else {
			// 노티피케이션 처리 영역
		}
	}

	document.getElementById('send').addEventListener('click', sendMessage);
	ws.onclose = function() {
		
		console.log('웹소켓 연결 해제')
	}

}

function sendMessage() {
	
	if(ws === null) {
		ws = new WebSocket('ws://localhost:8080/hcmWs.do');
		ws.onopen = function() {
			console.log('WebSocket 연결 성공');
		}
	}
	if(document.getElementById('mainDiv').textContent == '대화 기록이 없습니다') {
		document.getElementById('mainDiv').textContent = '';
	}

	var name = document.getElementById('myName').value;

	sender = document.getElementById('id').value;
	target = document.getElementById('target').value;
	message = document.getElementById('message').value;
	console.log(sender, target, message);
	var obj = {
				ch_sender : sender,
				ch_target : target,
				ch_message : message
			}
	
	fetch('/sendMessage.do', {
		method : 'post',
		headers : {
			'Content-Type' : 'application/json'
		},
		body : JSON.stringify(obj)
	})
		.then(resp => {
			return resp.text();
		})
		.then(data => {
			console.log(data);
			var seDiv1 = document.createElement('div');
			var seDiv2 = document.createElement('div');
			var seDiv3 = document.createElement('div');
			var seDiv4 = document.createElement('div');
			var seDiv5 = document.createElement('div');
			var seDiv6 = document.createElement('div');
			var seImg = document.createElement('img');
			var seA = document.createElement('a');
			var seSpan = document.createElement('span');
			seDiv1.setAttribute('class', 'd-flex justify-content-end mb-10')
			seDiv2.setAttribute('class', 'd-flex flex-column align-items-end')
			seDiv3.setAttribute('class', 'd-flex align-items-center mb-2')
			seDiv4.setAttribute('class', 'me-3')
			seDiv5.setAttribute('class', 'symbol symbol-35px symbol-circle')
			seDiv6.setAttribute('class', 'p-5 rounded bg-light-primary text-gray-900 fw-semibold mw-lg-400px text-end')
			seSpan.setAttribute('class', 'text-muted fs-7 mb-1')
			seA.setAttribute('class', 'fs-5 fw-bold text-gray-900 text-hover-primary ms-1')
//			seImg.setAttribute('src', '');

			var date = new Date();
			var formatDate = moment(date).format('YYYY-MM-DD HH:mm:ss');
			seSpan.textContent = formatDate;
			seA.textContent = name;
			seDiv6.textContent = message;

			seDiv4.append(seSpan);
			seDiv4.append(seA);
			seDiv5.append(seImg);
			seDiv3.append(seDiv4);
			seDiv3.append(seDiv5);
			seDiv2.append(seDiv3);
			seDiv2.append(seDiv6);
			seDiv1.append(seDiv2);
			document.getElementById('mainDiv').append(seDiv1);
			document.getElementById('message').value = '';
			document.getElementById('message').focus();
			// 채팅창 스크롤 맨아래로 내리기
			var chatContent = document.querySelector('#mainDiv');
			var chatHeigth = chatContent.scrollHeight;
			chatContent.scrollTop = chatHeigth;
		})
		.catch(err => {
			console.log('전송 실패', err)
		});
		ws.send(sender + ',' + target + ',' + message);
}

function loadMessage(empl_id) {
	document.getElementById('mainDiv').textContent = '';
	sender = document.getElementById('id').value;
	target = empl_id;
	console.log(sender, target, message);
	fetch('/loadMessage.do?ch_sender=' + sender + '&ch_target=' + target)
		.then(resp => {
			return resp.json();
		})
		.then(data => {
			console.log(data);
			if(data.length == 0) {
				document.getElementById('mainDiv').textContent = '대화 기록이 없습니다';
			}
			var headerDiv = document.getElementById('chatHeaderDiv');
			headerDiv.innerHTML = '	<div class="card-title">                                                           '
				+'		<div class="d-flex justify-content-center flex-column me-3">                                   '
				+'			<a class="fs-4 fw-bold text-gray-900 text-hover-primary me-1 mb-2 lh-1">' + 'a' + '</a>'
				+'			<input type="hidden" value="' + empl_id + '" id="target">                           '
				+'			<div class="mb-0 lh-1">                                                                    '
				+'				<span class="badge badge-success badge-circle w-10px h-10px me-1"></span>              '
				+'				<span class="fs-7 fw-semibold text-muted">접속중</span>                                '
				+'			</div>                                                                                     '
				+'		</div>                                                                                         '
				+'	</div>                                                                                             '
				+'	<div class="card-toolbar">                                                                         '
				+'		<div class="btn btn-sm btn-icon btn-active-color-primary" id="kt_drawer_chat_close">           '
				+'			<i class="ki-duotone ki-cross-square fs-2">                                                '
				+'				<span class="path1"></span>                                                            '
				+'				<span class="path2"></span>                                                            '
				+'			</i>                                                                                       '
				+'		</div>                                                                                         '
				+'	</div>';

			data.forEach(function(d, idx) {
				if (d.ch_sender == sender) {
					console.log('보낸데이터 : ', d)
					var seDiv1 = document.createElement('div');
					var seDiv2 = document.createElement('div');
					var seDiv3 = document.createElement('div');
					var seDiv4 = document.createElement('div');
					var seDiv5 = document.createElement('div');
					var seDiv6 = document.createElement('div');
					var seImg = document.createElement('img');
					var seA = document.createElement('a');
					var seSpan = document.createElement('span');
					seDiv1.setAttribute('class', 'd-flex justify-content-end mb-10')
					seDiv2.setAttribute('class', 'd-flex flex-column align-items-end')
					seDiv3.setAttribute('class', 'd-flex align-items-center mb-2')
					seDiv4.setAttribute('class', 'me-3')
					seDiv5.setAttribute('class', 'symbol symbol-35px symbol-circle')
					seDiv6.setAttribute('class', 'p-5 rounded bg-light-primary text-gray-900 fw-semibold mw-lg-400px text-end')
					seSpan.setAttribute('class', 'text-muted fs-7 mb-1')
					seA.setAttribute('class', 'fs-5 fw-bold text-gray-900 text-hover-primary ms-1')
//					seImg.setAttribute('src', '');
					seSpan.textContent = d.ch_date;
					seA.textContent = d.sender_name;
					seDiv6.textContent = d.ch_message;
					seDiv4.append(seSpan);
					seDiv4.append(seA);
					seDiv5.append(seImg);
					seDiv3.append(seDiv4);
					seDiv3.append(seDiv5);
					seDiv2.append(seDiv3);
					seDiv2.append(seDiv6);
					seDiv1.append(seDiv2);
					document.getElementById('mainDiv').append(seDiv1);
				} else {
					console.log('받은데이터 : ', d)
					var reDiv1 = document.createElement('div') // 아우터
					var reDiv2 = document.createElement('div') // 이너
					var reDiv3 = document.createElement('div') // 헤더아우터
					var reDiv4 = document.createElement('div') // 헤더이미지영역
					var reDiv5 = document.createElement('div') // 헤더텍스트영역
					var reDiv6 = document.createElement('div') // 바디(내용)
					var reImg = document.createElement('img'); // 이미지
					var reA = document.createElement('a'); // 헤더이름
					var reSpan = document.createElement('span'); // 헤더시간

					reDiv1.setAttribute('class', 'd-flex justify-content-start mb-10')
					reDiv2.setAttribute('class', 'd-flex flex-column align-items-start')
					reDiv3.setAttribute('class', 'd-flex align-items-center mb-2')
					reDiv4.setAttribute('class', 'symbol symbol-35px symbol-circle')
					reDiv5.setAttribute('class', 'ms-3')
					reDiv6.setAttribute('class', 'p-5 rounded bg-light-info text-gray-900 fw-semibold mw-lg-400px text-start')
//					reImg.setAttribute('src', '')
					reA.setAttribute('class', 'fs-5 fw-bold text-gray-900 text-hover-primary me-1')
					reSpan.setAttribute('class', 'text-muted fs-7 mb-1')

					reSpan.textContent = d.ch_date;
					reA.textContent = d.sender_name;
					reDiv6.textContent = d.ch_message;

					reDiv4.append(reImg);
					reDiv5.append(reA);
					reDiv5.append(reSpan);
					reDiv3.append(reDiv4);
					reDiv3.append(reDiv5);
					reDiv2.append(reDiv3);
					reDiv2.append(reDiv6);
					reDiv1.append(reDiv2);
					document.getElementById('mainDiv').append(reDiv1);
				}
			})

			// 채팅창 스크롤 맨아래로 내리기
			var chatContent = document.querySelector('#mainDiv');
			var chatHeigth = chatContent.scrollHeight;
			chatContent.scrollTop = chatHeigth;
		})
		.catch(err => {
			console.log('전송 실패', err)
		});
}

function notify(msg) {
	if (Notification.permission !== 'granted') {
		alert('알림 권한을 허용해주세요');
		Notification.requestPermission();
	} else {
		var notification = new Notification('HCM Company', {
			icon: 'http://cdn.sstatic.net/stackexchange/img/logos/so/so-icon.png',
			body: msg,
		});

		notification.onclick = function() {
			window.open('http://google.com');
		};
	}
}

function isJson(msg) {
	try {
		JSON.parse(msg);
	} catch (e) {
		return false;
	}
	return true;
}

// 상대방 목록
function chatUserList() {
	var myId = document.getElementById('id').value;
	fetch('/chatUserList.do')
	.then(resp => {return resp.json()})
	.then(data => {
		document.getElementById('searchMainDiv').textContent = '';
		console.log(data)
		data.forEach(function(d, idx) {
			if(d.empl_id != myId) {
			
				var div1 = document.createElement('div');
				var div2 = document.createElement('div');
				var div3 = document.createElement('div');
				var div4 = document.createElement('div');
				var div5 = document.createElement('div');
				var div6 = document.createElement('div');
				var div7 = document.createElement('div');
				var img = document.createElement('img');
				var a = document.createElement('a');
				var span = document.createElement('span');

				div1.setAttribute('class', 'rounded d-flex flex-stack bg-active-lighten p-4')
				div1.setAttribute('onclick', 'loadMessage(' + d.empl_id + ')')
				div1.setAttribute('style', 'cursor:pointer;')
				div2.setAttribute('class', 'd-flex align-items-center')
				div3.setAttribute('class', 'symbol symbol-35px symbol-circle')
				div4.setAttribute('class', 'ms-5')
				div5.setAttribute('class', 'fw-semibold text-muted')
				div6.setAttribute('class', 'ms-2 w-100px')
				div6.setAttribute('style', 'text-align: right;')
				div7.setAttribute('class', 'border-bottom border-gray-300 border-bottom-dashed')
//				img.setAttribute('src', '/doc/')
				a.setAttribute('class', 'fs-5 fw-bold text-gray-900 text-hover-primary mb-2')
				span.setAttribute('class', 'badge badge-light')

				a.textContent = d.empl_name;
				div5.textContent = d.empl_email;
				span.textContent = d.coco_name_dnm;

				div3.append(img);
				div4.append(a);
				div4.append(div5);
				div6.append(span);
				div2.append(div3);
				div2.append(div4);
				div1.append(div2);
				div1.append(div6);

				document.getElementById('searchMainDiv').append(div1);
				document.getElementById('searchMainDiv').append(div7);
			}
		});
	})
	.catch(err => {console.log(err)})
	
}




















