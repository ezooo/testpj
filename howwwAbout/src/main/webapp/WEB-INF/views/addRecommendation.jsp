<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false"%>
<%@ page import="com.springproject.domain.Member" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>여행지 추천 작성 폼</title>
</head>
<body>
<%@ include file="main.jsp" %>
<br>
<p>여행지 추천 작성 폼 입니다.<p>

<form:form modelAttribute="recommendation" method="post">
	<p>제 목 : <form:input path="recommendTitle" name="recommendTitle"/> </p>
	<p>내 용 : <form:input path="recommendContent" name="recommendContent"/> </p>
	<p><input type="submit" value="제출"> </p>
</form:form>

</body>
</html>