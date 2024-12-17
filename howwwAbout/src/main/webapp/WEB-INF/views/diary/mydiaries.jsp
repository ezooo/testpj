<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ page import="com.springproject.domain.Diary" %>
<%@ page import="com.springproject.domain.Member" %>
<%@page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 다이어리</title>
</head>
<body>
<%
	List<Diary> diaryList = (List<Diary>)request.getAttribute("diaryList");
	HttpSession ssn = request.getSession(false);
	Member mb = (Member)ssn.getAttribute("member");
%>
<%@ include file="main.jsp" %>

<%
if(mb != null)
{
%>	
	내 다이어리
	<hr><hr>
	<div>
<%
	for(Diary diary : diaryList)
	{
%>
	<div>
		<a href="/howAbout/diaries/diary/<%= diary.getDiaryId()%>">
		<p><%= diary.getVisit_date() %> &nbsp;&raquo; <%= diary.getVisit_location()%> </p>
		<%
		if(diary.getFilename0() != null)
		{
		%>
		<div style="width: 20%"> <img src="/howAbout/resources/images/<%=diary.getFilename0()%>" style="width: 100%" /> </div>
		<%
		}
		%>	
		</a>
		<p>	<% if(diary.getIsopen().equals("true")){ %> 공개글 <% } else { %> 비공개 <% } %> </p>
	</div>
<%
	}
%>
	</div>
<%
}
else
{
%>
	<p> 로그인하고 내 다이어리를 작성해보세요</p>
	<a href="login">로그인</a> <br>
<%
}
%>
	
</body>
</html>