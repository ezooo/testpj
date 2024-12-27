document.addEventListener("DOMContentLoaded", function() {
            // 여기에 실행할 함수를 호출합니다.
            midtermCall();
        });

function midtermCall()
{
	$.ajax
	(
		{
			url : "/howAbout/weather/callMidterm",
			type : "post",
			contentType : "application/json",
			success : function(data)
				{
					weatherWeek(data);
				},
			error : function(errorThrown){alert("중기 예보 호출 실패");}
		}
	)
}


var todaybox;
var todaytemp;

function weatherWeek(weather)
{
	console.log("함수호출됨");

	// 오늘 날짜 기준으로 4일 후와 10일 후의 날짜 계산
	var year = document.getElementById("spanYear").innerText;
	var month = document.getElementById("spanMonth").textContent ;
	var today = new Date();
	
	var fourthday = new Date(today);
	fourthday.setDate(today.getDate()+4);
	var fourthdd = ""+fourthday.getFullYear() + (fourthday.getMonth()+1) + fourthday.getDate();
	
	var fifthday = new Date(today);
	fifthday.setDate(today.getDate()+5);
	var fifthdd = ""+fifthday.getFullYear() + (fifthday.getMonth()+1) + fifthday.getDate();
	
	var sixthday = new Date(today);
	sixthday.setDate(today.getDate()+6);
	var sixthdd = ""+sixthday.getFullYear() + (sixthday.getMonth()+1) + sixthday.getDate();
	
	var seventhday = new Date(today);
	seventhday.setDate(today.getDate()+7);
	var seventhdd = ""+seventhday.getFullYear() + (seventhday.getMonth()+1) + seventhday.getDate();
	
	var eighthday = new Date(today);
	eighthday.setDate(today.getDate()+8);
	var eighthdd = ""+eighthday.getFullYear() + (eighthday.getMonth()+1) + eighthday.getDate();
	
	var ninthday = new Date(today);
	ninthday.setDate(today.getDate()+9);
	var ninthdd = ""+ninthday.getFullYear() + (ninthday.getMonth()+1) + ninthday.getDate();
	
	var tenthday = new Date(today);
	tenthday.setDate(today.getDate()+10);
	var tenthdd = ""+tenthday.getFullYear() + (tenthday.getMonth()+1) + tenthday.getDate();
	
	for (var i = 0; i <= 41; i++) 
	{
	    todaybox = document.getElementById("todaybox" + i);
	    todaytemp = document.getElementById("todaytemp" + i);
		var dateValue = document.getElementById("dates" + i).textContent; // 날짜 값 가져오기
		var dateformat = ""+year+month+dateValue;
		console.log("dateformat 값은 : "+dateformat);
        // 날짜가 4일 후부터 10일 후 사이인 경우
        if (dateformat === fourthdd) {
            todaybox.innerHTML = weather.wf4;
            todaytemp.innerHTML = "최저기온 : " + weather.taMin4 + " 최고기온 : " + weather.taMax4;
        } else if (dateformat === fifthdd) {
            todaybox.innerHTML = weather.wf5;
            todaytemp.innerHTML = "최저기온 : " + weather.taMin5 + " 최고기온 : " + weather.taMax5;
        } else if (dateformat === sixthdd) {
            todaybox.innerHTML = weather.wf6;
            todaytemp.innerHTML = "최저기온 : " + weather.taMin6 + " 최고기온 : " + weather.taMax6;
        } else if (dateformat === seventhdd) {
            todaybox.innerHTML = weather.wf7;
            todaytemp.innerHTML = "최저기온 : " + weather.taMin7 + " 최고기온 : " + weather.taMax7;
        } else if (dateformat === eighthdd) {
            todaybox.innerHTML = weather.wf8;
            todaytemp.innerHTML = "최저기온 : " + weather.taMin8 + " 최고기온 : " + weather.taMax8;
        } else if (dateformat === ninthdd) {
            todaybox.innerHTML = weather.wf9;
            todaytemp.innerHTML = "최저기온 : " + weather.taMin9 + " 최고기온 : " + weather.taMax9;
        } else if (dateformat === tenthdd) {
            todaybox.innerHTML = weather.wf10;
            todaytemp.innerHTML = "최저기온 : " + weather.taMin10 + " 최고기온 : " + weather.taMax10;
        }
		    
	}
	console.log("함수호출끝");
}