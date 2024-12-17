<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ page import="com.springproject.domain.Diary" %>
<%@ page import="com.springproject.domain.Member" %>
<%@page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전체 다이어리 보기</title>
</head>
<body>
<%
	List<Diary> diaryList = (List<Diary>)request.getAttribute("diaryList");
	HttpSession ssn = request.getSession(false);

%>
<%@ include file="main.jsp" %>

<%
if(ssn != null)
{
	Member mb = (Member)ssn.getAttribute("member");
	if(mb != null)
	{
%>	
	<a href="/howAbout/diaries/my">내 다이어리 가기</a> <br>
<%
	}
	else
	{
%>
		<p> 로그인 후 아래와 같은 다이어리를 작성해보세요 </p>
		<a href="login">로그인하고 다이어리 작성하기</a> <br>
<%
	}
}
else
{
%>
	<p> 로그인 후 아래와 같은 다이어리를 작성해보세요 </p>
	<a href="login">로그인하고 다이어리 작성하기</a> <br>
<%
}
%>	
<hr><hr>
	<div>
<%
	for(Diary diary : diaryList)
	{
%>
	<div>
		<p> <%= diary.getUserId() %>  &nbsp;님의 다이어리 </p>
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
	</div>
<%
	}
%>
	</div>
	
</body>
</html>