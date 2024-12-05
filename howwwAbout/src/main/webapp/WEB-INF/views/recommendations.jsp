<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@page import="com.springproject.domain.Recommendation"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>여행지 추천 게시판</title>
</head>

<body>
<%
	ArrayList<Recommendation> recommendationList = (ArrayList<Recommendation>)request.getAttribute("recommendationList");
%>
<%@ include file="main.jsp" %>

<p>새로운 여행지를 추천해주세요 !</p>
<hr><hr>
<%
	for(Recommendation rd : recommendationList)
	{
%>
<p><a href="recommend/recommendation/<%= rd.getRecommendId() %>"> <%= rd.getRecommendTitle() %> | <%= rd.getRecommendDate() %> </a></p>
<p><%= rd.getRecommendId() %> </p>
<hr>

<%
	}
%>
</body>
</html>