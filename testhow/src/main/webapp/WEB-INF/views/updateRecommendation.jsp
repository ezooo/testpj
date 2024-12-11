<%@page import="com.springproject.domain.Recommendation"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false"%>
<%@ page import="com.springproject.domain.Member" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>여행지 추천 수정 폼</title>
</head>
<body>
<%
	Recommendation recommendation = (Recommendation)request.getAttribute("recommendation");
%>
<%@ include file="main.jsp" %>
<br>
<p>여행지 추천 수정하기<p>

<form:form modelAttribute="recommendation" method="post">
	<p>제 목 : <form:input path="recommendTitle" name="recommendTitle" value="<%= recommendation.getRecommendTitle() %>"/> </p>
	<p>내 용 : <form:input path="recommendContent" name="recommendContent" value="<%= recommendation.getRecommendContent() %>"/> </p>
	<p><input type="submit" value="수정 완료"> </p>
</form:form>

</body>
</html>