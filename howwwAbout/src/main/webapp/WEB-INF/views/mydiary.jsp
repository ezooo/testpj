<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
다이어리 입니ㅏㄷ
<c:forEach items="${diaryList }" var="diary">
	<p>========================================= </p>
	<p>userId : ${diary.userId} </p>
	<p>방문일 : ${ diary.visit_date} </p>
	<p>메모 : ${diary.visit_diary} </p>
	<p>사진 </p>

</c:forEach>
	
	
<a href="/howAbout/diary/addMyDiary">글쓰기</a>
</body>
</html>