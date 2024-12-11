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
%>
<%@ include file="main.jsp" %>

<p>방문일 : <%= diary.getVisit_date() %> </p>
<p>방문장소 :  </p>
<p>메모 : <%= diary.getVisit_diary() %> </p>
<%
	if(diary.getFilename() != null)
	{
		System.out.println("diary에 보여줄 이미지가 있습니다.");
%>
<img src="/howAbout/resources/images/<%=diary.getFilename()%>" style="width: 20%" /> 
<%
	}
%>	
<p> <a href="/howAbout/diaries/updateDiary?id=<%=diary.getDiaryId()%>">수정하기</a>&nbsp;|&nbsp;<a href="/howAbout/diaries/deleteDiary?id=<%=diary.getDiaryId()%>">삭제하기</a> </p>

</body>
</html>