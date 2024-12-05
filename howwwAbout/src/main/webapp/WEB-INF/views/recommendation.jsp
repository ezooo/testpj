<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@page import="com.springproject.domain.Recommendation"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>여행지 추천 글 확인하기</title>
</head>

<body>
<%
	Recommendation recommendation = (Recommendation)request.getAttribute("recommendation");
%>
<%@ include file="main.jsp" %>
<br>
<p>여행지를 추천합니다 !</p>
<p> ===================================== </p>

<p> 제   목 : <%= recommendation.getRecommendTitle() %> </p>
<p> 작성자 ID : <%= recommendation.getUserId() %> </p>
<p> 작 성 일 : <%= recommendation.getRecommendDate() %> </p>
<br>
<p> 추천내용 : <%= recommendation.getRecommendContent() %> </p>
<p> </p>
<p> <a href="update/<%=recommendation.getRecommendId()%>">수정</a> | <a href="delete/<%=recommendation.getRecommendId()%>">삭제</a></p>
</body>
</html>