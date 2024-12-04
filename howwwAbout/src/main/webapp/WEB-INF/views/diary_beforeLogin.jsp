<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 안한 상태 다이어리</title>
</head>
<body>
<%@ include file="main.jsp" %>
<br>
<p> 로그인 후 아래와 같은 다이어리를 작성해보세요 </p>
<a href="login">로그인하고 다이어리 작성하기</a> <br>

	<p>========================================= </p>
	<p>userId : 홍길동 </p>
	<p>diaryId : 00000 </p>
	<p>방문일 : 24.09.09 </p>
	<p>메모 : 경치가 좋았다 </p>
	<p>사진 : </p><img src="/howAbout/resources/images/dog.png" style="width: 20%" /> 
	<br>
</body>
</html>