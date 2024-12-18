<%@page import="com.springproject.domain.Member"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.springproject.domain.Diary"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>다이어리 확인하기</title>
</head>
<body>
<%
	Diary diary = (Diary)request.getAttribute("diary");
	//ArrayList<DiaryImage> diaryImages = (ArrayList<DiaryImage>)request.getAttribute("diaryImages");
	HttpSession ssn = request.getSession(false);
%>
<%@ include file="main.jsp" %>

<p>방문일 : <%= diary.getVisit_date() %> </p>
<p>방문장소 : <%= diary.getVisit_location() %> </p>
<p> <%= diary.getAddress() %> </p>
<p>메모 : <%= diary.getVisit_diary() %> </p>
<%
String[] filenames = {diary.getFilename0(), diary.getFilename1(), diary.getFilename2(), diary.getFilename3()};

for(int i=0; i<filenames.length; i++)
{
	if(filenames[i] != null)
	{
%>
	<img src="/howAbout/resources/images/<%=filenames[i]%>" style="width: 40%" /> 
<%
	}
}
%>
<p>
	<% if(diary.getIsopen().equals("true")){ %> 공개 <% } else { %> 비공개 <% } %>
</p>
<%
if(ssn != null)
{
	Member mb = (Member)ssn.getAttribute("member");
	if(mb!=null )
	{
		if(mb.getUserId().equals(diary.getUserId()))
		{
%>
			<p> <a href="/howAbout/diaries/updateDiary?id=<%=diary.getDiaryId()%>">수정하기</a>&nbsp;
			|&nbsp;<a href="/howAbout/diaries/deleteDiary?id=<%=diary.getDiaryId()%>">삭제하기</a> </p>
<%
		}
	}
}
%>
</body>
</html>