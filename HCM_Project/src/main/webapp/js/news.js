var newsSearch = document.querySelector("#newsSearchBtn");
newsSearch.addEventListener("click",function(){
	console.log("뉴스검색");
	
	var newsSearch = document.getElementById("newsSearch").value;
	console.log(newsSearch);

	
	fetch('https://openapi.naver.com/v1/search/news.json?query='+encodeURIComponent(newsSearch)+'&display=5&start=1&sort=sim',{
		method: "GET",
	    mode: 'no-cors',
		headers: {
			'X-Naver-Client-Id':'GOoppOh7AqFHnC2N6Cce', 
			'X-Naver-Client-Secret': 'cmbVwmqkt5',
	        'Content-Type': 'application/x-www-form-urlencoded'
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
	});
	
})