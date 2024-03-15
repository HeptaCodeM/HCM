onload = function() {
	getFile();
	var fileView = document.getElementById('fileView');
	document.getElementById('getFile').addEventListener('click', function() {
		var sel = document.getElementById('selectFile');
		
		fetch('./getFileView.do?sidf_file_num=' + sel.value)
			.then(resp => { return resp.text() })
			.then(data => {
				console.log(data);
				fileView.innerHTML = "<img src='data:image/png;base64," + data + "'>";
				
			})
			.catch(err => { console.log(err) });
		
		
		
	}); 
	
	document.getElementById('downBtn').addEventListener('click', function() {
		var sel = document.getElementById('selectFile');
		location.href = './fileDown.do?sidf_file_num=' + sel.value
	}); 
}

function getFile() {
	fetch('./getFileList.do')
		.then(resp => { return resp.json() })
		.then(data => {
			console.log(data);
			var sel = document.getElementById('selectFile');

			for (let d of data) {
				var opt = document.createElement('option');
				opt.setAttribute('value', d.sidf_file_num);
				opt.textContent = d.sidf_file_origin;
				sel.appendChild(opt);
			}
		})
		.catch(err => { console.log(err) });
}