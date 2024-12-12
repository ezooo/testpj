<%@page import="java.util.ArrayList"%>
<%@page import="com.springproject.domain.DiaryImage"%>
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
	ArrayList<DiaryImage> diaryImages = (ArrayList<DiaryImage>)request.getAttribute("diaryImages");
%>
<%@ include file="main.jsp" %>

<p>방문일 : <%= diary.getVisit_date() %> </p>
<p>방문장소 :  </p>
<p>메모 : <%= diary.getVisit_diary() %> </p>
<%
if(diaryImages != null)
{
	for(DiaryImage di : diaryImages)
	{
		if(di.getFilename() != null)
		{
%>
	<img src="/howAbout/resources/images/<%=di.getFilename()%>" style="width: 40%" /> 
<%
		}
	}
}
%>	
<p> <a href="/howAbout/diaries/updateDiary?id=<%=diary.getDiaryId()%>">수정하기</a>&nbsp;|&nbsp;<a href="/howAbout/diaries/deleteDiary?id=<%=diary.getDiaryId()%>">삭제하기</a> </p>

</body>
</html>