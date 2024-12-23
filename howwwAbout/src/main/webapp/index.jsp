<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false"%>
<html>
<body>
<h2>Hello World!</h2>
<a href="/howAbout/diaries">다이어리 보기</a>
<a href="/howAbout/recommend">관광지 추천 게시판</a>
<a href="/howAbout/location">장소</a>

<p>
	<button onclick="howsWeather()" id="weatherBtn">날씨 호출</button>
	현재 날씨 : <input type="text" id="weatherNow">
	기온 : <input type="text" id="tempNow">
</p>
</body>

<script>
function howsWeather()
{	// 데이터 가공 처리는 컨트롤러에서 할거임
	fetch("/howAbout/weather/call")
	.then( console.log("컨트롤러 잘 다녀옴") );
}
</script>
</html>
