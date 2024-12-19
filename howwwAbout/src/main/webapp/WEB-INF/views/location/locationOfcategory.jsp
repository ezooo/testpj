<%@page import="java.util.ArrayList"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="com.springproject.domain.Location"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로케이션 카테고리</title>
</head>
<body>
<%@ include file="main.jsp" %>
<%
	ArrayList<Location> locations = (ArrayList<Location>)request.getAttribute("locations");
	String category = (String)request.getAttribute("category");
%>
	카테고리 :  <%=category %> &nbsp;&nbsp;<span><a href="/howAbout/location/locationCategory">&raquo;전체 카테고리 보기</a></span>
<hr>
<%
	for(Location lt : locations)
	{
%>
<p><a href="/howAbout/location/onelocation/<%= lt.getNum() %>"> <%= lt.getData_title() %> </a></p>
<img alt="사진없당" src="<%= lt.getFileurl1() %>" />
<hr>
<%
	}
%>
</body>
</html>