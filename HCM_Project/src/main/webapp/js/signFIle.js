onload = function() {
	document.getElementById('getFile').addEventListener('click', getFile)
}

function getFile() {
	fetch('./getFileList.do')
	.then(resp => {return resp.json()})
	.then(data => {
		console.log(data);
	})
	.catch(err => {console.log(err)});
}