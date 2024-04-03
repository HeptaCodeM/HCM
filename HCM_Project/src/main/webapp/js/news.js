
	
	var sendNews = document.querySelector("#newsSearchBtn");
	sendNews.addEventListener("click",function(){
		var newsSearch = document.getElementById("newsSearch").value;
		console.log(newsSearch);
		
		fetch('searchNews.do?newsSearch='+newsSearch,{
			method: "GET",
			header:{
				"Content-Type":"application/json; charset:UTF-8"
			}
		})
		.then(response =>{
			console.log(response);
			return response.text();
		})
		.then(data => {
			console.log(data);
			document.getElementById("resultZone").innerHTML = data;
	
		})
		.catch(err => { 
		    console.log('에러발생', err);
		});
	})