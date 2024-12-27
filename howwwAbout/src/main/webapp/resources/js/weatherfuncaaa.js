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

    var today = new Date();
    var year = parseInt(document.getElementById("spanYear").innerText); // 현재 년도
    var month = parseInt(document.getElementById("spanMonth").textContent)-1; // 현재 월 (0~11)
	console.log("현재 년월 : "+year+month);
    // 현재 달의 마지막 날 계산
    var lastDayCurrentMonth = new Date(year, month, 0).getDate(); // 현재 달의 마지막 날

    var currentDay = today.getDate(); // 오늘 날짜
    var startDay = currentDay + 4; // 오늘로부터 4일 후
    var endDay = currentDay + 10; // 오늘로부터 10일 후
	console.log("오늘날짜, 4일후, 10일 후 : "+currentDay+startDay+endDay);
	
    // 날짜 계산을 위한 변수 초기화
    var startMonth = month; // 시작 월
    var endMonth = month; // 종료 월

    // startDay가 현재 달의 마지막 날을 초과할 경우
    if (startDay > lastDayCurrentMonth) {
		console.log(startDay+">"+lastDayCurrentMonth);
        startDay = startDay - lastDayCurrentMonth; // 다음 달의 날짜로 조정
        startMonth++; // 다음 달로 이동
    }

    // endDay가 현재 달의 마지막 날을 초과할 경우
    if (endDay > lastDayCurrentMonth) {
        endDay = endDay - lastDayCurrentMonth; // 다음 달의 날짜로 조정
        endMonth++; // 다음 달로 이동
    }

    // 월이 12를 초과할 경우 연도를 증가시킴
    if (startMonth > 11) {
        startMonth = 0; // 1월로 돌아감
		endMonth = 0; // 1월로 돌아감
        year++; // 년도 증가
    }
	else
	{
		if (endMonth > 11) {
        endMonth = 0; // 1월로 돌아감
        year++; // 년도 증가
    	}
	}
   

    // 다음 달의 마지막 날 계산
    var nextMonth = month + 1; // 다음 달
    var nextYear = year;

    // 다음 달의 마지막 날 계산
    if (nextMonth > 11) {
        nextMonth = 0; // 1월로 넘어가는 경우
        nextYear = year + 1; // 년도 증가
    }

    var lastDayNextMonth = new Date(nextYear, nextMonth, 0).getDate(); // 다음 달의 마지막 날

    // 날씨 정보 출력
    for (var i = 0; i <= 41; i++) {
        var todaybox = document.getElementById("todaybox" + i);
        var todaytemp = document.getElementById("todaytemp" + i);
        var dateValue = parseInt(document.getElementById("dates" + i).textContent); // 날짜 값 가져오기

        // 현재 달의 날짜 처리
		if(startMonth === month)
		{
	        if (dateValue <= lastDayCurrentMonth) {
	            // 날짜가 현재 달에 속하는 경우
	            if (dateValue >= startDay && dateValue <= endDay) {
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
		}
         else if (dateValue > lastDayCurrentMonth && dateValue <= lastDayCurrentMonth + lastDayNextMonth) {
            // 다음 달의 날짜 처리
            var nextDateValue = dateValue - lastDayCurrentMonth; // 다음 달의 날짜
            if (nextDateValue >= 1 && nextDateValue <= 7) {
                // 1일부터 7일까지
                if (nextDateValue === 1) {
                    todaybox.innerHTML = weather.wf1; // 다음 달의 1일 날씨
                    todaytemp.innerHTML = "최저기온 : " + weather.taMin1 + " 최고기온 : " + weather.taMax1;
                } else if (nextDateValue === 2) {
                    todaybox.innerHTML = weather.wf2; // 다음 달의 2일 날씨
                    todaytemp.innerHTML = "최저기온 : " + weather.taMin2 + " 최고기온 : " + weather.taMax2;
                } else if (nextDateValue === 3) {
                    todaybox.innerHTML = weather.wf3; // 다음 달의 3일 날씨
                    todaytemp.innerHTML = "최저기온 : " + weather.taMin3 + " 최고기온 : " + weather.taMax3;
                } else if (nextDateValue === 4) {
                    todaybox.innerHTML = weather.wf4; // 다음 달의 4일 날씨
                    todaytemp.innerHTML = "최저기온 : " + weather.taMin4 + " 최고기온 : " + weather.taMax4;
                } else if (nextDateValue === 5) {
                    todaybox.innerHTML = weather.wf5; // 다음 달의 5일 날씨
                    todaytemp.innerHTML = "최저기온 : " + weather.taMin5 + " 최고기온 : " + weather.taMax5;
                } else if (nextDateValue === 6) {
                    todaybox.innerHTML = weather.wf6; // 다음 달의 6일 날씨
                    todaytemp.innerHTML = "최저기온 : " + weather.taMin6 + " 최고기온 : " + weather.taMax6;
                } else if (nextDateValue === 7) {
                    todaybox.innerHTML = weather.wf7; // 다음 달의 7일 날씨
                    todaytemp.innerHTML = "최저기온 : " + weather.taMin7 + " 최고기온 : " + weather.taMax7;
                }
            }
        }
    }
    console.log("함수호출끝");
}