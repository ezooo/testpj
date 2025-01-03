<%@page import="com.springproject.domain.Location"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로케이션</title>
</head>
<body>
<%@ include file="main.jsp" %>
<%
	Location location = (Location)request.getAttribute("location");
%>
<%= location.getData_title() %> 안내 페이지입니다.
<p> 카테고리 : <%= location.getCategory_name1() %> &raquo; <a href="/howAbout/location/category/<%= location.getCategory_name1() %>">해당 카테고리 더보기</a> </p>
<hr>
<p> 지역분류 : <%= location.getInsttnm().substring(0, 2) %> </p>
<p> 주  소 : <%= location.getUser_address() %> </p>
<p> 문  의 : <%= location.getTelno() %> </p>
<p> 설  명 : <%= location.getData_content() %> </p>
<div class="container">
	<img alt="image" src="<%= location.getFileurl1() %>" style="width:25%" />
	<img alt="image" src="<%= location.getFileurl3() %>" style="width:25%" />
	<img alt="image" src="<%= location.getFileurl4() %>" style="width:25%" />
	<img alt="image" src="<% if(location.getFileurl5() != null && location.getCategory_name1() !=""){location.getCategory_name1();}  %>" style="width:25%" />
</div>
<p> <a href="/howAbout/location/update?lat=<%=location.getLattitude()%>&log=<%=location.getLogitude()%>">수정</a>
	 | <a href="/howAbout/location/delete?lat=<%=location.getLattitude()%>&log=<%=location.getLogitude()%>">삭제</a></p>
<p> <a href="/howAbout/location">location 홈으로</a> </p>
</body>
</html>