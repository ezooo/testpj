<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>다이어리 작성 폼</title>
</head>
<body>
<%@ include file="main.jsp" %>
<br>
<p>다이어리 작성 폼 입니다.</p>

<form action="/howAbout/diaries/addDiary" method="post" enctype="multipart/form-data">
	<p>방 문 일 : <input type="date" name="visit_date" required /> </p>
	<p>방문 장소 : <input type="text" name="visit_location" required /> </p>
	<p>메   모 : <input type="text" name="visit_diary"/> </p>

	<p> 내 여행 사진을 업로드 하세요. (최대 4장) </p>
	<p> <input type="file" name="uploadFile"/> </p>
	<p> <input type="file" name="uploadFile"/> </p>
	<p> <input type="file" name="uploadFile"/> </p>
	<p> <input type="file" name="uploadFile"/> </p>
	
	<p> 
	<select name="isopen" required>
		<option value="true">공개</option>
		<option value="false">비공개</option>	
	</select>
	</p>
	
	<p><input type="submit" value="제출"> </p>
</form>

</body>

</html>