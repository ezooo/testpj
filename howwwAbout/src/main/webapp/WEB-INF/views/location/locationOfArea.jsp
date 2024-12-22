<%@page import="java.util.ArrayList"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="com.springproject.domain.Location"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로케이션 지역</title>
</head>
<body>
<%@ include file="main.jsp" %>
<%
	ArrayList<Location> locationOfArea = (ArrayList<Location>)request.getAttribute("locationOfArea");
	String area = (String)request.getAttribute("area");
%>
	지역 :  <%=area %> &nbsp;&nbsp;<span><a href="/howAbout/location/locationArea">&raquo;전체 지역 보기</a></span>
<hr>
<%
	for(Location lt : locationOfArea)
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