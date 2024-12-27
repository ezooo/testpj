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
	var year = parseInt(document.getElementById("spanYear").innerText);
	var month = parseInt(document.getElementById("spanMonth").textContent)-1 ;
	var today = new Date();
	
	var currentDay = today.getDate(); // 오늘 날짜
	var startDay = currentDay + 4; // 오늘로부터 4일 후
	var endDay = currentDay + 10; // 오늘로부터 10일 후
	
	
	// 현재 달의 마지막 날 계산
	var lastDayCurrentMonth = new Date(year, month, 0).getDate(); // 현재 달의 마지막 날
	console.log("저장된 달 : "+month);
	console.log("현재달 마지막 날 : "+lastDayCurrentMonth);
	
	// startDay와 endDay가 현재 달의 마지막 날을 초과할 경우 처리
	if (startDay > lastDayCurrentMonth) 
	{
	    startDay = startDay - lastDayCurrentMonth; // 다음 달로 넘어가는 경우
		console.log("달 바뀐 4일 후 날짜 : "+startDay);
		endDay = endDay - lastDayCurrentMonth; // 다음 달로 넘어가는 경우
		console.log("10일 후 날짜 : "+endDay);
	    month++; // 다음 달로 이동
		console.log("4일 후 달 : "+month);
	}
	else
	{
		console.log("달 안바뀐 4일 후 날짜 : "+startDay);
		if (endDay > lastDayCurrentMonth) 
		{
		    endDay = endDay - lastDayCurrentMonth; // 다음 달로 넘어가는 경우
		    console.log("달 바뀐 10일 후 날짜 : "+endDay);
			month++; // 다음 달로 이동
		}
	}

	// month가 12를 초과하면 연도를 증가시킴
	if (month > 12) {
	    month = 0; // 1월로 돌아감
	    year++; // 년도 증가
	};
	console.log("저장된 달 : "+month);
	
	
	var nextMonth = month + 1; // 다음 달
	var nextYear = year;

	// 다음 달의 첫 날과 마지막 날 계산
	if (nextMonth > 12) 
	{
	    nextMonth = 0; // 1월로 넘어가는 경우
	    nextYear = parseInt(spanYear) + 1; // 년도 증가
	}

	var lastDayNextMonth = new Date(nextYear, nextMonth, 0).getDate(); // 다음 달의 마지막 날

	for (var i = 0; i <= 41; i++) 
	{
	    todaybox = document.getElementById("todaybox" + i);
	    todaytemp = document.getElementById("todaytemp" + i);
		var dateValue = parseInt(document.getElementById("dates" + i).textContent); // 날짜 값 가져오기

		// 현재 달의 날짜 처리
		if (dateValue <= lastDayCurrentMonth) 
		{
		    // 날짜가 현재 달에 속하는 경우
		    if (dateValue >= startDay && dateValue <= endDay) 
			{
		        // 날짜가 4일 후부터 10일 후 사이인 경우
		        if (dateValue === startDay) {
		            todaybox.innerHTML = weather.wf4;
		            todaytemp.innerHTML = "최저기온 : " + weather.taMin4 + " 최고기온 : " + weather.taMax4;
		        } else if (dateValue === startDay + 1) {
		            todaybox.innerHTML = weather.wf5;
		            todaytemp.innerHTML = "최저기온 : " + weather.taMin5 + " 최고기온 : " + weather.taMax5;
		        } else if (dateValue === startDay + 2) {
		            todaybox.innerHTML = weather.wf6;
		            todaytemp.innerHTML = "최저기온 : " + weather.taMin6 + " 최고기온 : " + weather.taMax6;
		        } else if (dateValue === startDay + 3) {
		            todaybox.innerHTML = weather.wf7;
		            todaytemp.innerHTML = "최저기온 : " + weather.taMin7 + " 최고기온 : " + weather.taMax7;
		        } else if (dateValue === startDay + 4) {
		            todaybox.innerHTML = weather.wf8;
		            todaytemp.innerHTML = "최저기온 : " + weather.taMin8 + " 최고기온 : " + weather.taMax8;
		        } else if (dateValue === startDay + 5) {
		            todaybox.innerHTML = weather.wf9;
		            todaytemp.innerHTML = "최저기온 : " + weather.taMin9 + " 최고기온 : " + weather.taMax9;
		        } else if (dateValue === startDay + 6) {
		            todaybox.innerHTML = weather.wf10;
		            todaytemp.innerHTML = "최저기온 : " + weather.taMin10 + " 최고기온 : " + weather.taMax10;
		        }
		    }
		} 
		else if (dateValue > lastDayCurrentMonth && dateValue <= lastDayCurrentMonth + lastDayNextMonth) 
		{
		    // 다음 달의 날짜 처리
		    var nextDateValue = dateValue - lastDayCurrentMonth; // 다음 달의 날짜
		    if (nextDateValue >= 1 && nextDateValue <= 7) 
			{
		        // 1일부터 7일까지
		        if (nextDateValue === 1) {
		            todaybox.innerHTML = weather.wf4;
		            todaytemp.innerHTML = "최저기온 : " + weather.taMin4 + " 최고기온 : " + weather.taMax4;
		        } else if (nextDateValue === 2) {
		            todaybox.innerHTML = weather.wf5;
		            todaytemp.innerHTML = "최저기온 : " + weather.taMin5 + " 최고기온 : " + weather.taMax5;
		        } else if (nextDateValue === 3) {
		            todaybox.innerHTML = weather.wf6;
		            todaytemp.innerHTML = "최저기온 : " + weather.taMin6 + " 최고기온 : " + weather.taMax6;
		        } else if (nextDateValue === 4) {
		            todaybox.innerHTML = weather.wf7;
		            todaytemp.innerHTML = "최저기온 : " + weather.taMin7 + " 최고기온 : " + weather.taMax7;
		        } else if (nextDateValue === 5) {
		            todaybox.innerHTML = weather.wf8;
		            todaytemp.innerHTML = "최저기온 : " + weather.taMin8 + " 최고기온 : " + weather.taMax8;
		        } else if (nextDateValue === 6) {
		            todaybox.innerHTML = weather.wf9;
		            todaytemp.innerHTML = "최저기온 : " + weather.taMin9 + " 최고기온 : " + weather.taMax9;
		        } else if (nextDateValue === 7) {
		            todaybox.innerHTML = weather.wf10;
		            todaytemp.innerHTML = "최저기온 : " + weather.taMin10 + " 최고기온 : " + weather.taMax10;
		        }
		    }
		}
	}
	console.log("함수호출끝");
}