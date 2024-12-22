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
<title>지역 분류 보여주기</title>
</head>
<body>
<%@ include file="main.jsp" %>
<%
	ArrayList<String> areaList = (ArrayList<String>)request.getAttribute("areaList");
%>
지역별 여행지 
<span>&nbsp;&nbsp;<a href="/howAbout/location/locationCategory">&raquo;카테고리별 여행지 보기</a></span>
<hr><hr>
<div class="container">
<%
if(areaList != null)
{
	for(String areaname : areaList)
	{
		String area = URLEncoder.encode(areaname, "UTF-8");
%>
<div>
<p><a href="/howAbout/location/area/<%= area %>"> <%= areaname %> </a></p>
</div>
<%
	}
}
%>
</div>
</body>
</html>