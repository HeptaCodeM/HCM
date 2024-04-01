var ws = null;
var sender = null;
var target = null;
var message = null;

onload = function() {
	getAlarmList();
	
	setInterval(function() {
	if (sender != null && target != null) {
		ws.send(sender + ',' + target + ',접속여부판단');
	}
}, 1000);
	
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
	})
	
	ws.onmessage = function(e) {
//		console.log('웹소켓 서버 수신')
		var myId = document.getElementById('id').value;
//		console.log('웹소켓 수신', e.data);
		if(e.data === '새로운 공지사항이 등록되었습니다') {
			notify(e.data);
			
			return;
		}
		if(e.data === '접속여부판단:온라인') {
//			console.log(e.data);
			var parent = document.getElementById('target').parentNode;
			var dong = parent.querySelector('.badge-circle');
			var text = parent.querySelector('.text-muted');
			dong.removeAttribute('class');
			dong.setAttribute('class', 'badge badge-success badge-circle w-10px h-10px me-1');
			text.textContent = '접속중';
			return;
		} else if (e.data === '접속여부판단:오프라인') {
			var parent = document.getElementById('target').parentNode;
			var dong = parent.querySelector('.badge-circle');
			var text = parent.querySelector('.text-muted');
			dong.removeAttribute('class');
			dong.setAttribute('class', 'badge badge-danger badge-circle w-10px h-10px me-1');
			text.textContent = '오프라인';
			return;
		}
		
		// 유저객체 전달 경우
		if (!e.data.includes('님으로 부터 메세지 도착') && !e.data.includes('접속 해제')) {
			var json = JSON.parse(e.data);
			var header = document.getElementById('chatHeaderDiv');
			var headerName = header.querySelector('a');
			
			// 수신자가 현재대화를 보고있는경우
			if(headerName.textContent === json.sender_name) {
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
				if(json.sender_pic != undefined) {
					reImg.setAttribute('src', 'data:image/png;base64,' + json.sender_pic);
				} else {
					reImg.setAttribute('src', 'https://ssl.pstatic.net/static/cafe/cafe_pc/default/cafe_profile_77.png?type=c77_77');
				}
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
				
				fetch('/setReadMessage.do?ch_sender=' + json.ch_sender + '&ch_target=' + myId).then().then().catch();
				chatUserList();
				// 현재 대화를 안보고있는 경우
			} else {
				var symbol = document.getElementById(json.ch_sender);
				fetch('/chatCount.do?ch_sender=' + json.ch_sender + '&ch_target=' + myId)
					.then(resp => { return resp.json() })
					.then(data => {
						if (data != 0) {
							symbol.style.background = '#f8285a';
							symbol.textContent = data;
						}
					})
					.catch(err => { console.log(err) });
				notify(json.sender_name + '님으로 부터 메세지 도착');
				chatUserList();
			}
			
		} else {
			// 노티피케이션 처리 영역
//			notify(e.data);
		}
		
	}
	
	// 웹소켓 연결해제
	ws.onclose = function() {
		console.log('웹소켓 연결 해제');
		var parent = document.getElementById('target').parentNode;
		var dong = parent.querySelector('.badge-success');
		var text = parent.querySelector('.text-muted');
		dong.removeAttribute('class');
		dong.setAttribute('class', 'badge badge-danger badge-circle w-10px h-10px me-1');
		text.textContent = '오프라인';
	}

	document.getElementById('send').addEventListener('click', sendMessage);
	

}

// 메세지 보내기
function sendMessage() {
//	console.log(ws);
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
//	console.log(sender, target, message);
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
//			console.log(data);
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
			
			var pic = document.getElementById('myPic').value;
			if (pic != '/images/blank.png') {
				seImg.setAttribute('src', pic);
			} else {
				seImg.setAttribute('src', 'https://ssl.pstatic.net/static/cafe/cafe_pc/default/cafe_profile_77.png?type=c77_77');
			}

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
		chatUserList();
}

// 대화내용 불러오기
function loadMessage(event, empl_id) {
	sender = document.getElementById('id').value;
	target = empl_id;
	var ele = event.target.text;
	document.getElementById('mainDiv').textContent = '';
//	console.log(sender, target, message);
	fetch('/loadMessage.do?ch_sender=' + sender + '&ch_target=' + target)
		.then(resp => {
			return resp.json();
		})
		.then(data => {
//			console.log(data);
			if(data.length == 0) { 
				document.getElementById('mainDiv').textContent = '대화 기록이 없습니다';
			}
			var headerDiv = document.getElementById('chatHeaderDiv');
			headerDiv.innerHTML = '	<div class="card-title">                                                           '
				+'		<div class="d-flex justify-content-center flex-column me-3">                                   '
				+'			<a class="fs-4 fw-bold text-gray-900 text-hover-primary me-1 mb-2 lh-1">' + ele + '</a>'
				+'			<input type="hidden" value="' + target + '" id="target">                           '
				+'			<div class="mb-0 lh-1">                                                                    '
				+'				<span class="badge badge-circle w-10px h-10px me-1"></span>              '
				+'				<span class="fs-7 fw-semibold text-muted"></span>                                '
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
//					console.log('보낸데이터 : ', d)
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
					seA.setAttribute('class', 'fs-5 fw-bold text-gray-900 text-hover-primary ms-1');
					var pic = document.getElementById('myPic').value;
					if (pic != '/images/blank.png') {
						seImg.setAttribute('src', pic);
					} else {
						seImg.setAttribute('src', 'https://ssl.pstatic.net/static/cafe/cafe_pc/default/cafe_profile_77.png?type=c77_77');
					}
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
//					console.log('받은데이터 : ', d)
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
					
					if (d.sender_pic_str != undefined) {
						reImg.setAttribute('src', 'data:image/png;base64,' + d.sender_pic_str);
					} else {
						reImg.setAttribute('src', 'https://ssl.pstatic.net/static/cafe/cafe_pc/default/cafe_profile_77.png?type=c77_77');
					}
					
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
		
		fetch('/setReadMessage.do?ch_sender=' + target + '&ch_target=' + sender).then().then().catch();
		var symbol = event.target.parentNode.querySelector('.symbol-circle');
		if(symbol != null) {
			symbol.style.background = 'white';
		}
		
		setTimeout(function() {
			ws.send(sender + ',' + target + ',접속여부판단');
		}, 100);
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

// 대화상대 목록
function chatUserList() {
	var myId = document.getElementById('id').value;
	fetch('/chatUserList.do?ch_target=' + myId)
	.then(resp => {return resp.json()})
	.then(data => {
		document.getElementById('searchMainDiv').textContent = '';
//		console.log(data)
		data.forEach(function(d, idx) {
			if(d.ch_sender != myId) {
			
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
				div1.setAttribute('class', 'rounded d-flex flex-stack bg-active-lighten p-4');
				div1.setAttribute('data-user-id', idx);
				div2.setAttribute('class', 'd-flex align-items-center');
				div3.setAttribute('class', 'symbol symbol-35px symbol-circle');
				div4.setAttribute('class', 'ms-5');
				div5.setAttribute('class', 'fw-semibold text-muted');
				div6.setAttribute('class', 'ms-2 w-100px');
				div6.setAttribute('style', 'text-align: right;');
				div7.setAttribute('class', 'border-bottom border-gray-300 border-bottom-dashed');
				if(d.sender_pic_str != undefined) {
					img.setAttribute('src', 'data:image/png;base64,' + d.sender_pic_str);
				} else {
					img.setAttribute('src', 'https://ssl.pstatic.net/static/cafe/cafe_pc/default/cafe_profile_77.png?type=c77_77');
				}
				a.setAttribute('class', 'fs-5 fw-bold text-gray-900 text-hover-primary mb-2');
				a.setAttribute('onclick', 'loadMessage(event,' + d.ch_sender + ')');
				a.setAttribute('style', 'cursor: pointer;');
				span.setAttribute('class', 'badge badge-light');

				a.textContent = d.empl_name;
				div5.textContent = d.empl_email;
				span.textContent = d.dept_name;
				
				var number = document.createElement('div');
				fetch('/chatCount.do?ch_sender=' + d.ch_sender + '&ch_target=' + myId)
				.then(resp => {return resp.json()})
				.then(cnt => {
					if(cnt != 0) {
						number.setAttribute('class', 'symbol symbol-circle symbol-25px');
						number.setAttribute('id', d.ch_sender);
						number.setAttribute('style', 'background-color: #f8285a; width: 18px; height: 12px; color: white; font-size: 10px;'
							+ 'text-align: center; line-height: 12px; margin-left: 10px;');
						number.textContent = cnt;
					} else {
						number.setAttribute('class', 'symbol symbol-circle symbol-25px');
						number.setAttribute('id', d.ch_sender);
						number.setAttribute('style', 'background-color: white; width: 18px; height: 12px; color: white; font-size: 10px;'
							+ 'text-align: center; line-height: 12px; margin-left: 10px;');
						number.textContent = cnt;
					}
					
				})
				.catch(err => {console.log(err)});

				div3.append(img);
				div4.append(a);
				div4.append(number);
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
		// 대화순으로 정렬...
		
	})
	.catch(err => {console.log(err)})
	
}









