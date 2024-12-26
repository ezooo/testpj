//var todaybox = document.getElementById("todaybox");
var todaybox;
var todaytemp;
var spanYear = document.getElementById("spanYear").innerText;
var spanMonth = document.getElementById("spanMonth").textContent;
//var dates = document.getElementsByClassName("dates").textContent;
var dates = [];
for(var i=0; i<=41; i++)
	{
		console.log(i+"번째 date 저장");
		//dates.put(document.getElementsById("dates"+i).textContent);
		dates.push(document.getElementById("dates"+i).textContent);
		console.log(dates);
	}

var afterday = new Date();
afterday.setDate(afterday.getDate() +4);
var year = afterday.getFullYear();
var month = (afterday.getMonth() + 1);
var day = (afterday.getDate());

document.addEventListener("DOMContentLoaded", function() {
            // 여기에 실행할 함수를 호출합니다.
            weatherWeek();
        });
//todaybox.addEventListener('load', weatherWeek());

function weatherWeek()
{
	console.log("함수호출됨");
	console.log("Year : "+year + "spanYear : "+spanYear);
	//console.log("day : "+day + "dates : "+dates);
	console.log("month : "+month + "spanMonth : "+spanMonth);

	//if(spanYear==year && spanMonth==month)
	//{
		for(var i=0; i<=41; i++)
		{
			if(spanYear==year && spanMonth==month)
				{
			todaybox = document.getElementById("todaybox"+i);
			todaytemp = document.getElementById("todaytemp"+i);
			if( dates[i]==day )
			{
				console.log(weatherJson.wf4Am);
				todaybox.innerHTML=weatherJson.wf4Am ;
				todaytemp.innerHTML="최저기온 : " + "최고기온 : " ;
			}
			else if( dates[i]==(day)+1 )
			{
				todaybox.innerHTML=weatherJson.wf5Am ;	
			}
			else if( dates[i]==(day)+2 )
			{
				todaybox.innerHTML=weatherJson.wf6Am ;	
			}
				
		}
	}
	console.log("함수호출끝");
}