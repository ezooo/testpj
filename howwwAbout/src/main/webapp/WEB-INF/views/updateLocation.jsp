<%@page import="com.springproject.domain.Location"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장소 수정하기</title>
</head>
<body>
<%
	Location lt = (Location)request.getAttribute("location");
	//int num = lt.getNum();
	//out.print("뷰ㅔㅇ서 num 꺼냈나요 :"+num);
	//request.setAttribute("num", num);
%>
<%@ include file="main.jsp" %>
<form:form modelAttribute="location" method="post">
	<form:hidden path="num" name="num" value="<%= lt.getNum() %>"/> 
	<p>장소명 : <form:input path="data_title" name="data_title" value="<%= lt.getData_title() %>"/> </p>
	<p>주소 : <form:input path="user_address" name="user_address" value="<%= lt.getUser_address() %>" /> </p>
	<p>위도 : <form:input path="lattitude" name="lattitude" value="<%= lt.getLattitude() %>" /> </p>
	<p>경도 : <form:input path="logitude" name="logitude" value="<%= lt.getLogitude() %>" /> </p>
	<p>담당기관 : <form:input path="insttnm" name="insttnm" value="<%= lt.getInsttnm() %>" /> </p>
	<p>카테고리 구분 (ex.바다) : <form:input path="category_name1" name="category_name1" value="<%= lt.getCategory_name1() %>" /> </p>
	<p>시군명 : <form:input path="category_name2" name="category_name2" value="<%= lt.getCategory_name2() %>" /> </p>
	<p>내용 : <form:input path="data_content" name="data_content" value="<%= lt.getData_content() %>" /> </p>
	<p>대표연락처 : <form:input path="telno" name="telno" value="<%= lt.getTelno() %>" /> </p>
	<p>이미지 주소 1 : <form:input path="fileurl1" name="fileurl1" value="<%= lt.getFileurl1() %>" /> </p>
	<p>이미지 주소 2 : <form:input path="fileurl2" name="fileurl2" value="<%= lt.getFileurl2() %>" /> </p>
	<p>이미지 주소 3 : <form:input path="fileurl3" name="fileurl3" value="<%= lt.getFileurl3() %>" /> </p>
	<p>이미지 주소 4 : <form:input path="fileurl4" name="fileurl4" value="<%= lt.getFileurl4() %>" /> </p>
	<p> <input type="submit" value="수정하기" /> </p>
</form:form>
</body>
</html>