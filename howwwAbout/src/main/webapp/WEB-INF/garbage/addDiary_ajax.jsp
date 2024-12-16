<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false"%>
<%@ page import="com.springproject.domain.Member" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>다이어리 작성 폼</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
<%@ include file="main.jsp" %>
<br>
<p>다이어리 작성 폼 입니다.</p>

<form:form modelAttribute="diary" method="post" enctype="multipart/form-data" id="diaryForm" >
	<p>방문일 : <form:input path="visit_date" name="visit_date"/> </p>
	<p>메모 : <form:textarea path="visit_diary" name="visit_diary"/> </p>
	
	<div>
	사진 : <input type="file" name="picture" multiple />

	<button id="upload" onclick="uploadImages()" type="button">upload</button>
	</div>

	<p><input type="submit" value="제출"> </p>
</form:form>

</body>

<script>

$("#diaryForm").submit(function(event) 
	{
	    event.preventDefault(); // 기본 폼 제출 방지
	    // 다이어리 폼 데이터 전송
	    $.ajax({
	        url: '/howAbout/diaries/addDiary',
	        type: 'POST',
	        data: $(this).serialize(), // 폼 데이터 직렬화
	        success: function(diaryId) 
	        {
	            // 다이어리 아이디를 받아온 후 이미지 업로드 시작
	            uploadImages(diaryId);
	        },
	        error: function() { alert("다이어리 추가 실패"); }
    	});
	}
);

function uploadImages(diaryId) {
    var formData = new FormData();
    var inputFile = $("input[name='picture']");
    var files = inputFile[0].files;

    // 다이어리 ID를 추가
    formData.append("diaryId", diaryId);

    for (var i = 0; i < files.length; i++) {
        formData.append("uploadFile", files[i]);
    }

    $.ajax({
        url: '/howAbout/diaries/uploadfile',
        processData: false,
        contentType: false,
        data: formData,
        type: 'POST',
        success: function(result) {
            alert("Upload 완료");
        },
        error: function() {
            alert("파일 업로드 실패");
        }
    });
}
/*
function test()
{	
	var formData = new FormData();
	var inputFile = $("input[name='picture']");
	var files = inputFile[0].files;
	console.log(files);
	
	for(var i = 0; i < files.length; i++)
	{
		formData.append("uploadFile", files[i]);
	}
	
	$.ajax({
		url: '/howAbout/diaries/uploadfile',
		processData: false,
		contentType: false,
		data: formData,
		type: 'POST',
		success: function(result){
			alert("Upload");
		}
	})
}*/
</script>
</html>