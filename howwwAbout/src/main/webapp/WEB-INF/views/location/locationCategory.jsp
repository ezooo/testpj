<%@page import="java.net.URLEncoder"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.springproject.domain.Location"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로케이션 보여주기</title>
</head>
<body>
<%@ include file="main.jsp" %>
<%
	ArrayList<Location> categoryList = (ArrayList<Location>)request.getAttribute("categoryList");
%>
카테고리별 여행지 
<span>&nbsp;&nbsp;<a href="/howAbout/location/locationArea">&raquo;지역별 여행지 보기</a></span>
<hr><hr>
<div class="container">
<%
	for(Location location : categoryList)
	{
%>
<div>
<img alt="사진없당" src="<%= location.getFileurl1() %>" style="width:25%" />
<p><a href="/howAbout/location/category/<%= location.getCategory_name1() %>"> <%= location.getCategory_name1() %> </a></p>
</div>
<%
	}
%>
</div>
</body>
</html>