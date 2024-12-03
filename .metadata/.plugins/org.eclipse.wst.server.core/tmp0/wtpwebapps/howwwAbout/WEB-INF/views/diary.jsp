<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.springproject.domain.Diary" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
다이어리 입니ㅏㄷ
<%
	List<Diary> diaryList = (List<Diary>)request.getAttribute("diaryList");

%>

<%
	for(Diary diary : diaryList)
	{
%>
	<p>========================================= </p>
	<p>userId : <%= diary.getuserId() %> </p>
	<p>방문일 : <%= diary.getVisit_date() %> </p>
	<p>메모 : <%= diary.getVisit_diary() %> </p>
	<%
		if(diary.getPicture() != null)
		{
			System.out.println("diary에 보여줄 이미지가 있습니다.");
	%>
	<p>사진 : </p><img src="/howAbout/resources/images/<%=diary.getPicture().getOriginalFilename()%>" style="width: 20%" /> 
	<br>
<%
		}
	}
%>

	
	
<a href="/howAbout/diary/addDiary">글쓰기</a>
</body>
</html>