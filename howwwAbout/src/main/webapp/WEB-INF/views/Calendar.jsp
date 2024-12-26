<%@page import="com.google.gson.Gson"%>
<%@page import="com.springproject.domain.WeatherOfWeek"%>
<%@page import="java.time.LocalDate"%>
<%@page import="org.springframework.ui.Model"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Calendar</title>
<style>
body {
        font-family: Arial, sans-serif;
        background-color: #f9f9f9;
        margin: 0;
        padding: 20px;
    }

    div {
        margin: 10px 0;
    }

    .calendar {
        display: grid;
        grid-template-columns: repeat(7, 1fr); /* 7개의 열 */
        gap: 10px; /* 열 사이의 간격 */
        padding: 10px;
        background-color: #fff;
        border: 1px solid #ddd;
        border-radius: 5px;
    }

    .header {
        background-color: #007bff; /* 헤더 색상 */
        color: white;
        padding: 10px;
        text-align: center;
        font-weight: bold;
    }

    .dates {
        padding: 15px;
        text-align: center;
        border: 1px solid #ddd; /* 날짜 셀 경계선 */
        border-radius: 5px;
        background-color: #f0f0f0; /* 날짜 배경 색상 */
        transition: background-color 0.3s; /* 호버 효과 */
    }

    .dates:hover {
        background-color: #e0e0e0; /* 호버 시 색상 변화 */
    }

    button {
        padding: 10px 15px;
        font-size: 16px;
        background-color: #007bff; /* 버튼 색상 */
        color: white;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        transition: background-color 0.3s; /* 호버 효과 */
    }

    button:hover {
        background-color: #0056b3; /* 버튼 호버 시 색상 변화 */
    }

    .calendar-navigation {
        display: flex;
        justify-content: space-between; /* 이전/다음 버튼 사이의 공간 조정 */
        align-items: center;
    }
</style>
</head>
<body>
<%
	WeatherOfWeek weatherOfWeek = (WeatherOfWeek)request.getAttribute("weatherOfWeek");
	Gson gson = new Gson();
	String weatherJson = gson.toJson(weatherOfWeek);
%>
	<div>
		<div><a href="${pageContext.request.contextPath}/calendar?year=${preYear}&month=${preMonth}"><button>이전 달</button></a></div>
		<div id="monthDiv" > <span id="spanYear">${year}</span>년 <span id="spanMonth">${month}</span>월</div>
		<div><a href="${pageContext.request.contextPath}/calendar?year=${nextYear}&month=${nextMonth}"><button>다음 달</button></a></div>
	</div>
	<div class="calendar">
		<div class="header">일</div>
		<div class="header">월</div>
		<div class="header">화</div>
		<div class="header">수</div>
		<div class="header">목</div>
		<div class="header">금</div>
		<div class="header">토</div>
			<c:forEach var="i" begin="0" end="41">
				<div class="dates" >	
					<span id="dates${i}">${dates[i]}</span>
					<p id="todaybox${i}"></p>
					<p id="todaytemp${i}"></p>
				</div>
			</c:forEach>
	</div>
</body>
<script type="text/javascript">
	var weatherJson = JSON.parse('<%= weatherJson %>');	//
	console.log(weatherJson);
</script>

<script src="/howAbout/resources/js/weatherfunc.js"></script>

</html>