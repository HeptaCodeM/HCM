onload = function() {
	
	var id = document.getElementById('empl_id').value;
	
	fetch('./signFavoList.do?empl_id=' + id)
	.then(resp => {
		return resp.json();
	}) 
	.then(data => {
		console.log(data);
		var apprList = document.getElementById('apprList');
		for(let d of data) {
			var opt = document.createElement('option');
			opt.setAttribute('value', d.siaf_appr_id);
			opt.textContent = d.empl_name;
			apprList.appendChild(opt);
		}
		
	})
	.catch(error => {
		console.log(error)
	})
	
	fetch('./signFavoLineList.do?empl_id=' + id)
	.then(resp => {
		return resp.json();
	}) 
	.then(data => {
		console.log(data);
		var apprLineList = document.getElementById('apprLineList');
		for(let d of data) {
			
			var arr = d.siaf_appr_line.split(',');
			console.log(arr);
			
			for(let i=0; i<arr.length; i++) {
				
				var opt = document.createElement('option');
				opt.setAttribute('value', arr[i].trim());
				opt.textContent = d.empl_name;
				apprLineList.appendChild(opt);
			}
		}
	})
	.catch(error => {
		console.log(error)
	})
}