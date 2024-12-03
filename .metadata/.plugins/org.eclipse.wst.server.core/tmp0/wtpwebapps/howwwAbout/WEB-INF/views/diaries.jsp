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
다이어리 입니ㅏㄷ
<%
	List<Diary> diaryList = (List<Diary>)request.getAttribute("diaryList");
	HttpSession ssn = request.getSession(false);
	Member mb = (Member)ssn.getAttribute("member");
%>

<%
if(mb != null)
{
	for(Diary diary : diaryList)
	{
%>
	<p>========================================= </p>
	<p>userId : <%= mb.getUserId() %> </p>
	<p>diaryId : <%= diary.getDiaryId() %> </p>
	<p>방문일 : <%= diary.getVisit_date() %> </p>
	<p>메모 : <%= diary.getVisit_diary() %> </p>
	<%
		if(diary.getPicture() != null)
		{
			System.out.println("diary에 보여줄 이미지가 있습니다.");
	%>
	<p>사진 : </p><img src="/howAbout/resources/images/<%=diary.getPicture().getOriginalFilename()%>" style="width: 20%" /> 
	<p> <a href="/howAbout/diaries/updateDiary?id=<%=diary.getDiaryId()%>">수정하기</a> | <a href="/howAbout/diaries/deleteDiary?id=<%=diary.getDiaryId()%>">삭제하기</a> </p>
	<br>
<%
		}
	}
}
else
{
%>
	<p>========================================= </p>
	<p> 로그인 후 아래와 같은 다이어리를 작성해보세요 </p>
	<p>userId : 홍길동 </p>
	<p>diaryId : 00000 </p>
	<p>방문일 : 24.09.09 </p>
	<p>메모 : 경치가 좋았다 </p>
	<p>사진 : </p><img src="/howAbout/resources/images/dog.png" style="width: 20%" /> 
	<br>
<%
}
%>
<a href="/howAbout/diaries/addDiary">글쓰기</a>
</body>
</html>