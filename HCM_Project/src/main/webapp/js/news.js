var sendNews = document.querySelector("#newsSearchBtn");
sendNews.addEventListener("click",function(){ searchNews(); });


function searchNews(){
	var newsSearch = document.getElementById("newsSearch").value;
	var newsSearchPick = document.getElementById("newsSearchPick").value;
	console.log(newsSearch);
	console.log(newsSearchPick);
	var text = "";

	if(newsSearchPick == "HCM" && newsSearch == ""){
		text = newsSearchPick;
	}else if(newsSearch != ""){
		text = newsSearch;
	}else {
		text = newsSearchPick;
	}

	
	fetch('searchNews.do?newsSearch='+text,{
		method: "GET",
		header:{
			"Content-Type":"application/json; charset:UTF-8"
		}
	})
	.then(response =>{
		console.log(response);
		return response.json();
	})
	.then(data => {
//		console.log(data);
	
		var Obj = JSON.parse(JSON.stringify(data));
		console.log(Obj.items.length);
		
		var newsResult = "";
		
		for(let i = 0; i < Obj.items.length; i++){
//			console.log(Obj.items[i].title);
//			console.log(Obj.items[i].description);
//			console.log(Obj.items[i].pubDate);
//			console.log(Obj.items[i].link);
			var emptyTr = "<input type='hidden' value='"+Obj.items[i].link+"' name='thisPage'><tr style='cursor: pointer;' onclick='moveNewsPage("+i+")'><td colspan='2'>"+Obj.items[i].title+"</td><td rowspan='2' class='tdWidth'>발행일<br><br><br>"+replaceDate(Obj.items[i].pubDate)+"</td></tr><tr><td colspan='2'>"+Obj.items[i].description+"</td></tr>";
			newsResult += emptyTr;
		}
		
		document.getElementById("resultZone").innerHTML = newsResult;
	
	
	})
	.catch(err => { 
	    console.log('에러발생', err);
	});
}

searchNews();

function moveNewsPage(num){
	var page = document.getElementsByName("thisPage")[num].value;
	console.log(page);
	window.open(page);
}

function replaceDate(arr){
//	console.log(arr.split(" "));
	
	var repArr = arr.split(" ");
	
	var week = "";
	switch((repArr[0]).split(",")[0]){
		case 'Mon': week="(월)"; break;
		case 'Tue': week="(화)"; break;
		case 'Wed': week="(수)"; break;
		case 'Thu': week="(목)"; break;
		case 'Fri': week="(금)"; break;
		case 'Sat': week="(토)"; break;
		case 'Sun': week="(일)"; break;
	}
//	console.log(week);
	
	var day = repArr[1];
//	console.log(day);
	
	var mon = "";
	switch(repArr[2]){
		case 'Jan': mon="01"; break;
		case 'Feb': mon="02"; break;
		case 'Mar': mon="03"; break;
		case 'Apr': mon="04"; break;
		case 'May': mon="05"; break;
		case 'Jun': mon="06"; break;
		case 'Jul': mon="07"; break;
		case 'Aug': mon="08"; break;
		case 'Sep': mon="09"; break;
		case 'Oct': mon="10"; break;
		case 'Nov': mon="11"; break;
		case 'Dec': mon="12"; break;
	}
//	console.log(mon);
	
	return repArr[3]+"년 "+mon+"월 "+day+"일 "+week 
}

