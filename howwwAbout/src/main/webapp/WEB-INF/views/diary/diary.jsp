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
<style>
    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box; /* 박스 모델을 border-box로 설정하여 패딩과 테두리가 너비에 포함되도록 */
		text-decoration: none;
    }
    body {
    
        padding: 20px;
        width: 100%;
    }
    .container {
    	margin: 0 auto; /* 중앙 정렬 */
        display: flex;
        width: 70%; /* 원하는 너비로 설정 */
        justify-content: center;	 /* 중앙 정렬 */
        max-width : 1200px;
    }
    .clickBox {
    	margin: 0 auto; /* 중앙 정렬 */
		padding : 10px 0;
        width: 70%; /* 원하는 너비로 설정 */
        justify-content: center;	 /* 중앙 정렬 */
        max-width : 1200px;
    }
    
    .clickBox a{
    	padding : 5px 15px;
    	font-size: 12px;
    	margin : 2px;
    	border : 1px hidden;
    	border-radius: 7px;
    	background-color: rgba(31, 174, 193, 0.53); 
    	color : black;
    }
    .clickBox a:hover {
        background-color: rgba(39, 118, 221, 0.5);
        color : white;
        transition: 0.5s ease;
    }
    
    .info {
        flex: 0 0 40%; /* 텍스트 영역 40% */
        margin: 10px;
        border : 1px hidden;
        box-shadow: 0 2px 10px rgba(0, 0, 0, 0.3); /* 그림자 추가 */
        padding : 10px;
    }
    .album {
        flex: 0 0 60%; /* 앨범 영역 60% */
        display: flex;
        flex-direction: column; /* 세로 방향으로 정렬 */
        align-items: center; /* 가운데 정렬 */
        margin: 10px;
    }
    .main-image {
        width: 100%; /* 대표 이미지 영역 */
        height: auto; /* 자동 높이 */
        margin-bottom: 10px; /* 아래 여백 */
		border : 1px hidden;
        box-shadow: 0 5px 12px rgba(0, 0, 0, 0.5);
    }
    .thumbnails {
        display: grid;
        grid-template-columns: repeat(4, 1fr); /* 4개의 열로 구성 */
        gap: 10px;
        width: 100%; /* 전체 너비 */
    }
    .thumbnail {
	    width: 100%; /* 부모의 너비를 100%로 설정 */
	    padding-top: 100%; /* 높이를 너비와 동일하게 만들기 위해 패딩 사용 */
	    position: relative; /* 자식 요소에 대한 상대적 위치 설정 */
	    overflow: hidden; /* 내용이 영역을 벗어나지 않도록 설정 */
	    cursor: pointer; /* 클릭 가능 커서 */
	}
	.thumbnail img {
	    position: absolute; /* 절대 위치 지정 */
	    top: 0; /* 상단 정렬 */
	    left: 0; /* 좌측 정렬 */
	    width: 100%; /* 부모 div의 너비에 맞춤 */
	    height: 100%; /* 부모 div의 높이에 맞춤 */
	    object-fit: cover; /* 이미지 비율 유지하며 크기 맞춤 */
	    transition: transform 1s; /* 부드러운 변환 효과 */
	}
    .thumbnail img:hover {
        transform: scale(1.5); /* 마우스 오버 시 확대 */
    }
</style>
</head>
<body>
<%
    Diary diary = (Diary)request.getAttribute("diary");
    HttpSession ssn = request.getSession(false);
    String[] filenames = {diary.getFilename0(), diary.getFilename1(), diary.getFilename2(), diary.getFilename3()};
    String mainImage = filenames[0]; // 기본적으로 첫 번째 이미지를 대표 이미지로 설정
%>
<%@ include file="main.jsp" %>
<div class="container">
    <div class="info">
        <p>방문일 : <%= diary.getVisit_date() %></p>
        <p>방문장소 : <%= diary.getVisit_location() %></p>
        <p><%= diary.getAddress() %></p>
        <p>메모 : <%= diary.getVisit_diary() %></p>
        <p>
            <% if(diary.getIsopen().equals("true")) { %> 공개 <% } else { %> 비공개 <% } %>
        </p>
    </div>

    <div class="album">
        <img src="/howAbout/resources/images/<%=mainImage%>" alt="대표 이미지" class="main-image" id="mainImage"/>
        
        <div class="thumbnails">
            <%
            for(int i = 0; i < filenames.length; i++) {
                if(filenames[i] != null) {
            %>
                <div class="thumbnail" onclick="changeImage('<%=filenames[i]%>')">
                    <img src="/howAbout/resources/images/<%=filenames[i]%>" alt="썸네일 <%=i+1%>"/>
                </div>
            <%
                }
            }
            %>
        </div>
    </div>
</div>
<div class="clickBox">
	<%
	if(ssn != null) {
	    Member mb = (Member)ssn.getAttribute("member");
	    if(mb != null && mb.getUserId().equals(diary.getUserId())) {
	%>
	    <p>
	        <a href="/howAbout/diaries/updateDiary?id=<%=diary.getDiaryId()%>">수정</a>
	        <a href="/howAbout/diaries/deleteDiary?id=<%=diary.getDiaryId()%>">삭제</a>
	    </p>
	<%
	    }
	}
	%>
</div>

<script>
    function changeImage(filename) {
        document.getElementById('mainImage').src = '/howAbout/resources/images/' + filename;
    }
</script>
</body>
</html>