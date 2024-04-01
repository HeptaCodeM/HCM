function offAlarm(icon) {
	var paDiv = icon.parentNode.parentNode.parentNode.parentNode;
	var input = paDiv.querySelector('input').value;
	fetch('/offAlarmOne.do?al_no=' + input, {
		method: 'get'
	})
		.then(resp => { return resp.text() })
		.then(data => { 
//			console.log(data)
			
			paDiv.classList.add('slide-out-right');
			setTimeout(function() {
				paDiv.innerHTML = '';
			}, 500);
			var numberFlag = document.getElementById('numberFlag');
			var count = parseInt(numberFlag.textContent, 10) - 1;
			if (numberFlag && !isNaN(parseInt(numberFlag.textContent))) {
				numberFlag.textContent = count;
				if(numberFlag.textContent == 0) {
					numberFlag.style.display = 'none';
				}
			}
		})
		.catch(err => { console.log(err) });
		
}


function offAlarmAll() {
	var alList = document.getElementsByName('alList');
	var alarmList = Array.from(alList).map(function(al) {
		return al.value;
	});
	fetch('/offAlarm.do', {
		method: 'post',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(alarmList)
	})
		.then(resp => { return resp.text() })
		.then(data => { 
//			console.log(data) 
			var timeLines = document.getElementsByClassName('timeline-item');
			timeLines.forEach(function(time) {
				time.classList.add('slide-out-right');
				setTimeout(function() {
					time.innerHTML = '';
				}, 500);
			})
			
			document.getElementById('numberFlag').style.display = 'none';
		})
		.catch(err => { console.log(err) });
		
}

// 알림목록 불러오기
function getAlarmList() {
	var id = document.getElementById('id');
	if(id == null) {
		return;
	} 
	var timeLines = document.getElementsByClassName('timeline-item');
	timeLines.forEach(function(time) {
		time.textContent = '';
	})
	fetch('/getAlarmList.do?al_target=' + id.value)
	.then(resp => {
		return resp.json();
	})
	.then(data => {
		var sepa = document.createElement('div');
		sepa.setAttribute('class', 'separator separator-dashed my-3');
		sepa.setAttribute('style', 'width: 700px !important;');
		if(data.length == 0) {
			document.getElementById('timeLines').textContent = '새로운 알림이 없습니다'
			document.getElementById('numberFlag').style.display = 'none';
			document.getElementById('timeLines').append(sepa);
			return;
		}
		document.getElementById('numberFlag').textContent = data.length;
		document.getElementById('numberFlag').style.display = 'block';
		
		for(let d of data) {
			
			var alDiv1 = document.createElement('div'); // 루트 레벨
			var alInput1 = document.createElement('input'); // 1 레벨
			var alDiv2 = document.createElement('div'); // 1 레벨
			var alDiv3 = document.createElement('div'); // 1 레벨
			var alDiv4 = document.createElement('div'); // 1 레벨
			var alI1 = document.createElement('i'); // 3레벨
			var alSpan1 = document.createElement('span'); // 3-1레벨
			var alSpan2 = document.createElement('span'); // 3-1레벨
			var alSpan3 = document.createElement('span'); // 3-1레벨

			var alDiv5 = document.createElement('div'); // 4 레벨
			var alDiv6 = document.createElement('div'); // 4 레벨
			var alDiv7 = document.createElement('div'); // 5 레벨
			var alDiv8 = document.createElement('div'); // 5 레벨
			var alDiv10 = document.createElement('div'); // 6 레벨

			var alA1 = document.createElement('a') // 7-1레벨
			var alI2 = document.createElement('i'); // 7-1레벨
			var alSpan4 = document.createElement('span'); // 7-2레벨
			var alSpan5 = document.createElement('span'); // 7-2레벨
			var alSpan6 = document.createElement('span'); // 7-2레벨
			var alSpan7 = document.createElement('span'); // 7-2레벨

			var alDiv9 = document.createElement('div'); // 8 레벨
			var alDiv11 = document.createElement('div'); // 10 레벨
			var alDiv12 = document.createElement('div'); // 10 레벨
			var alDiv13 = document.createElement('div'); // 12 레벨
			var alDiv14 = document.createElement('div'); // 12 레벨
			var alImg = document.createElement('img'); // 13레벨

			alDiv1.setAttribute('class', 'timeline-item');
			alInput1.setAttribute('type', 'hidden');
			alInput1.setAttribute('name', 'alList');
			alInput1.value = d.al_no;
			alDiv2.setAttribute('class', 'timeline-line');
			alDiv3.setAttribute('class', 'timeline-icon');
			alDiv4.setAttribute('class', 'timeline-content mb-10 mt-n1');
			if (d.al_flag == 1) {
				alI1.setAttribute('class', 'ki-duotone ki-message-text-2 fs-2 text-gray-500');
			} else if (d.al_flag == 2) {
				alI1.setAttribute('class', 'ki-duotone ki-calendar fs-2 text-gray-500');
			} else if (d.al_flag == 3) {
				alI1.setAttribute('class', 'ki-duotone ki-file-added fs-2 text-gray-500');
			} else if (d.al_flag == 4) {
				alI1.setAttribute('class', 'ki-duotone ki-file-deleted fs-2 text-gray-500');
			} else if (d.al_flag == 5) {
				alI1.setAttribute('class', 'ki-duotone ki-pencil fs-2 text-gray-500');
			}
			alDiv1.append(alInput1);
			alDiv1.append(alDiv2);
			alDiv1.append(alDiv3);
			alDiv1.append(alDiv4);
			alDiv3.append(alI1);
			alSpan1.setAttribute('class', 'path1');
			alSpan2.setAttribute('class', 'path2');
			alSpan3.setAttribute('class', 'path3');
			alI1.append(alSpan1);
			alI1.append(alSpan2);
			alI1.append(alSpan3);
			alDiv4.append(alDiv5);
			alDiv4.append(alDiv6);
			alDiv5.setAttribute('class', 'pe-3 mb-5');
			alDiv6.setAttribute('class', 'overflow-auto pb-5');
			alDiv5.append(alDiv7);
			alDiv5.append(alDiv8);
			if (d.al_flag == 1) {
				alDiv7.textContent = '공지사항이 등록되었습니다'; // 타이틀
			} else if (d.al_flag == 2) {
				alDiv7.textContent = '일정 알림'; // 타이틀
			} else if (d.al_flag == 3) {
				alDiv7.textContent = '결재 승인 알림'; // 타이틀
			} else if (d.al_flag == 4) {
				alDiv7.textContent = '결재 반려 알림'; // 타이틀
			} else if (d.al_flag == 5) {
				alDiv7.textContent = '결재 요청 알림'; // 타이틀
			}
			alDiv8.setAttribute('class', 'd-flex align-items-center mt-1 fs-6');
			alDiv8.append(alDiv9);
			alDiv9.setAttribute('class', 'text-muted me-2 fs-7') // 시간
			alDiv9.textContent = d.al_date;
			alDiv6.append(alDiv10);
			alDiv10.setAttribute('class', 'd-flex align-items-center border border-dashed border-gray-300 rounded min-w-600px px-7 py-3 mb-5');
			alDiv10.append(alDiv11);
			alDiv10.append(alA1);
			alDiv10.append(alDiv12);

			if (d.al_flag == 3) {
				alDiv10.append(alDiv14);
				alDiv14.setAttribute('class', 'min-w-80px pe-2')
				alSpan7.setAttribute('class', 'badge badge-light-success');
				alSpan7.textContent = '승인'
				alDiv14.append(alSpan7);
			} else if (d.al_flag == 4) {
				alDiv10.append(alDiv14);
				alDiv14.setAttribute('class', 'min-w-80px pe-2')
				alSpan7.setAttribute('class', 'badge badge-light-danger');
				alSpan7.textContent = '반려'
				alDiv14.append(alSpan7);
			} else if (d.al_flag == 5) {
				alDiv10.append(alDiv14);
				alDiv14.setAttribute('class', 'min-w-80px pe-2')
				alSpan7.setAttribute('class', 'badge badge-light-primary');
				alSpan7.textContent = '결재요청'
				alDiv14.append(alSpan7);
			}
			alDiv10.append(alI2);
			alDiv11.setAttribute('class', 'min-w-120px pe-2');
			alDiv11.setAttribute('style', 'width: 70px;');
			alA1.setAttribute('class', 'fs-5 text-gray-900 text-hover-primary fw-semibold w-300px min-w-200px'); // 제목
			if (d.al_flag == 1) {
				alA1.textContent = d.al_title;
				alA1.setAttribute('href', '/sm/getDetailGobo.do?gobo_no=' + d.al_key); // 공지링크
			} else if (d.al_flag == 2) {
				alA1.textContent = d.al_title;
				alA1.setAttribute('href', '/sm/getDetailGobo.do?gobo_no=' + d.al_key); // 일정링크
			} else if (d.al_flag == 3 || d.al_flag == 4 || d.al_flag == 5) {
				alA1.textContent = d.al_title;
				alA1.setAttribute('href', '/doc/docBox/getDetail.do?docNum=' + d.al_key); // 결재링크
			}
			alDiv12.setAttribute('class', 'symbol-group symbol-hover flex-nowrap flex-grow-1 min-w-60px pe-2');
			alI2.setAttribute('class', 'ki-duotone ki-cross-circle fs-1');
			alI2.setAttribute('style', 'cursor: pointer;');
			alI2.setAttribute('onclick', 'offAlarm(this)');
			alDiv11.append(alSpan4);
			alSpan4.setAttribute('class', 'badge badge-light text-muted') // 카테고리
			if (d.al_flag == 1) {
				alSpan4.textContent = '전사공지';
			} else if (d.al_flag == 2) {
				alSpan4.textContent = '일정';
			} else if (d.al_flag == 3 || d.al_flag == 4 || d.al_flag == 5) {
				alSpan4.textContent = d.template;
			}
			alDiv12.append(alDiv13);
			alDiv13.setAttribute('class', 'symbol symbol-circle symbol-25px')
			alDiv13.textContent = d.producer_name; // 제공자 이름
			alDiv13.prepend(alImg);
			alImg.setAttribute('src','https://ssl.pstatic.net/static/cafe/cafe_pc/default/cafe_profile_77.png?type=c77_77') // 사진
			alI2.append(alSpan5);
			alI2.append(alSpan6);
			alSpan5.setAttribute('class', 'path1');
			alSpan6.setAttribute('class', 'path2');
			
			document.getElementById('timeLines').append(alDiv1);
		}
		
		document.getElementById('timeLines').append(sepa);
		
	})
	.catch(err => {
		console.log(err);
	});
}  
