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
	Member mb = (Member)ssn.getAttribute("member");
%>
<%@ include file="main.jsp" %>

<%
if(mb != null)
{
%>	
	다이어리 입니다
	<hr><hr>
	<div>
<%
	for(Diary diary : diaryList)
	{
%>
	<div>
		<a href="diaries/diary/<%= diary.getDiaryId()%>">
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
	<p> 로그인 후 아래와 같은 다이어리를 작성해보세요 </p>
	<a href="login">로그인하고 다이어리 작성하기</a> <br>
	<hr><hr>
	<p>userId : 홍길동 </p>
	<p>diaryId : 00000 </p>
	<p>방문일 : 24.09.09 </p>
	<p>메모 : 경치가 좋았다 </p>
	<p>사진 : </p><img src="/howAbout/resources/images/dog.png" style="width: 20%" /> 
	<br>
<%
}
%>
	
</body>
</html>