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
<p>다이어리 수정 폼 입니다.</p>
<form action="/howAbout/diaries/updateDiary" method="post" enctype="multipart/form-data">
	<input name="diaryId" value="<%=diary.getDiaryId() %>" type="hidden" />
	<p>방 문 일 : <input type="date" name="visit_date" value="<%=diary.getVisit_date() %>" required /> </p>
	<p>방문 장소 : <input type="text" name="visit_location" id="visit_location" value="<%=diary.getVisit_location() %>" required /> 
					<button id="find" onclick="findLocation()" type="button">장소검색</button> </p>
	<div id="locationResults" style="display: none;"></div>
	<p>주   소 : <input type="text" name="address" id="address" value="<%=diary.getAddress() %>"/> </p>
	<p>메   모 : <input type="text" name="visit_diary" value="<%=diary.getVisit_diary() %>"/> </p>

	<p> 사진 수정을 원하시면 새 파일을 업로드 하세요. </p>
	<p> <input type="file" name="uploadFile"/> </p>
	<p> <input type="file" name="uploadFile"/> </p>
	<p> <input type="file" name="uploadFile"/> </p>
	<p> <input type="file" name="uploadFile"/> </p>
	
	<select name="isopen" required>
		<% String status = diary.getIsopen(); %>
		<option value="true" <% if(status != null){if(status.equals("true")){out.print("selected");}} %> >공개</option>
		<option value="false" <% if(status != null){if(status.equals("false")){out.print("selected");}} %> >비공개</option>	
	</select>
	<p><input type="submit" value="제출"> </p>
</form>
</body>

<script src="/howAbout/resources/js/locationAjax.js"></script>

</html>
