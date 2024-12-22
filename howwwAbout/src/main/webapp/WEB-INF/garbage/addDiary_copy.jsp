<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false"%>
<%@ page import="com.springproject.domain.Member" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>다이어리 작성 폼</title>
</head>
<body>
<%@ include file="main.jsp" %>
<br>
<p>다이어리 작성 폼 입니다.<p>

<form:form modelAttribute="diary" method="post" enctype="multipart/form-data">
	<p>방문일 : <form:input path="visit_date" name="visit_date"/> </p>
	<p>메모 : <form:textarea path="visit_diary" name="visit_diary"/> </p>
	<p>사진 : <form:input path="picture"  type="file" name="picture"/> </p>


	<p><input type="submit" value="제출"> </p>
</form:form>

</body>
</html>