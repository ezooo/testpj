<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장소 추가하기</title>
</head>
<body>
<%@ include file="main.jsp" %>
<form:form modelAttribute="location" method="post">
	<p>장소명 : <form:input path="data_title" name="data_title" /> </p>
	<p>주소 : <form:input path="user_address" name="user_address" /> </p>
	<p>위도 : <form:input path="latitude" name="latitude" /> </p>
	<p>경도 : <form:input path="longitude" name="longitude" /> </p>
	<p>담당기관 : <form:input path="insttnm" name="insttnm" /> </p>
	<p>카테고리 구분 (ex.바다) : <form:input path="category_name1" name="category_name1" /> </p>
	<p>시군명 : <form:input path="category_name2" name="category_name2" /> </p>
	<p>내용 : <form:input path="data_content" name="data_content" /> </p>
	<p>대표연락처 : <form:input path="telno" name="telno" /> </p>
	<p>이미지 주소 1 : <form:input path="fileurl1" name="fileurl1" /> </p>
	<p>이미지 주소 2 : <form:input path="fileurl2" name="fileurl2" /> </p>
	<p>이미지 주소 3 : <form:input path="fileurl3" name="fileurl3" /> </p>
	<p>이미지 주소 4 : <form:input path="fileurl4" name="fileurl4" /> </p>
	<p> <input type="submit" value="제출" /> </p>
</form:form>
</body>
</html>