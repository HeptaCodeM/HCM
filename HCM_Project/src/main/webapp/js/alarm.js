function offAlarm(icon) {
	var paDiv = icon.parentNode.parentNode.parentNode.parentNode;
	var input = paDiv.querySelector('input').value;
	fetch('/offAlarmOne.do?al_no=' + input, {
		method: 'get'
	})
		.then(resp => { return resp.text() })
		.then(data => { 
			console.log(data)
			
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
			console.log(data) 
			var timeLines = document.getElementById('timeLines');
			timeLines.classList.add('slide-out-right');
			setTimeout(function() {
				timeLines.innerHTML = '';
			}, 500);
			document.getElementById('numberFlag').style.display = 'none';
		})
		.catch(err => { console.log(err) });
}

   
