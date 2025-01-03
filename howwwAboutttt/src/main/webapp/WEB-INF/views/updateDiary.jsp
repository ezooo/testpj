<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false"%>
<%@ page import="com.springproject.domain.Diary" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>다이어리 수정하기</title>
</head>
<body>
<%
	Diary diary = (Diary)request.getAttribute("diaryById");
%>
<%@ include file="main.jsp" %>
<br>
<form:form modelAttribute="diary" method="post" enctype="multipart/form-data">
	<form:input path="diaryId" value="<%=diary.getDiaryId() %>" type="hidden" />	
	<p>방문일 : <form:input path="visit_date" name="visit_date" value="<%=diary.getVisit_date() %>" /> </p>
	<p>메모 : <form:input path="visit_diary" name="visit_diary" value="<%=diary.getVisit_diary() %>" /> </p>
	<p>사진 : <form:input path="picture"  type="file" name="picture"/> </p>
	<p><input type="submit" value="제출"> </p>
</form:form>

</body>
</html>
