var newsSearch = document.querySelector("#newsSearchBtn");
newsSearch.addEventListener("click",function(){
	console.log("뉴스검색");
	
	var newsSearch = document.getElementById("newsSearch").value;
	console.log(newsSearch);

	
/*	fetch('https://openapi.naver.com/v1/search/news.json?query='+newsSearch+'&display=5&start=1&sort=sim',{
		method: "GET",
	    mode: 'no-cors',
		headers: {
			'X-Naver-Client-Id':'MflzXYZnwONG5QBgICK2', 
			'X-Naver-Client-Secret': 'lhnOaKuzFv'
		}
	})
	.then(data => {
		console.log(data);
	})
	.then(response =>{
		console.log(response);
	})
	.catch(err => { 
	    console.log('에러발생', err);
	});*/
	fetch('searchNews.do?newsSearch='+newsSearch,{
		method: "GET"
	})
	.then(response =>{
		console.log(response);
		return response;
	})
	.then(data => {
		console.log(data);
		document.getElementById("resultZone").innerHTML = data;
		
	})
	.catch(err => { 
	    console.log('에러발생', err);
	});
	
})