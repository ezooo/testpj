<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false"%>
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
	<p>방문일 : <input type="text" name="visit_date" required /> </p>
	<p>메모 : <input type="text" name="visit_diary"/> </p>

	<p>사진 : <input type="file" name="uploadFiles" multiple /></p>
	<p><input type="submit" value="제출"> </p>
</form>

</body>

</html>