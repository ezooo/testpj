<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로케이션... 임시페이지</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
<%@ include file="main.jsp" %>
로케이션 임시페이지 입니다.
<a href="/howAbout/location/locations">전체 로케이션 보기</a>
<a href="/howAbout/location/locationCategory">카테고리 분류 보기</a>
<a href="/howAbout/location/locationArea">지역 분류 보기</a>
<a href="/howAbout/location/create">장소 추가</a>
<br><br>
 
	<button type="button" onclick="searchLocation()">클릭하고 여행지 찾기</button>
	<input type="text" id="searchLocation" placeholder="여행지 입력" style="display:none;"/>
<div id="results" ></div>
</body>

<script src="/howAbout/resources/js/locationAjax.js"></script>
</html>