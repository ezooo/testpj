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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <style>
    	*{
    		margin : 0 auto;
    	}
        body {
            background-image: url('/howAbout/resources/images/back.png');
            background-position: center; /* 중앙에 위치 */
            background-repeat: no-repeat; /* 반복하지 않음 */
            background-size: contain; /* 이미지 크기를 화면에 맞게 조정 */
            height: 100vh; /* 전체 화면 높이 */
            margin: 0; /* 기본 마진 제거 */
            
        }
        .container {
	        max-width: 65%; /* 최대 너비 설정 */
	        margin: 0 auto; /* 중앙 정렬 */
	        padding: 0 20px; /* 양쪽 여백 설정 */
	    }
	    .diary-box {
	        position: relative;
	        border: 1px hidden;
	        border-radius: 5px; /* 둥글기 조정 */
	        overflow: hidden; /* 자식 요소가 박스를 넘지 않도록 설정 */
	        margin-bottom: 20px;
	        width: 100%; /* 박스 너비 100% */
	        aspect-ratio: 6 / 5; /* 가로:세로 비율 6:5 */
	        background-color: rgba(255, 255, 255, 0.78);
	        display: flex; /* Flexbox 사용 */
	        justify-content: center; /* 중앙 정렬 */
	        align-items: center; /* 중앙 정렬 */
	        transition: all 0.3s ease; /* 부드러운 전환 효과 */
	        background-size: cover; /* 배경 이미지 크기 조정 */
	        background-position: center; /* 배경 이미지 중앙 정렬 */
	        margin-bottom: 0px; /* 아래쪽 간격 설정 */
	        box-shadow: 0 6px 12px rgba(0, 0, 0, 0.5); /* 그림자 추가 */
	    }
	    .diary-image {
	        display: none; /* 이미지 요소는 숨김 */
	    }
	
	    /* 일반 박스의 최대 크기 설정 */
	    .regular-box {
	        max-width: 300px; /* 최대 가로 길이 300px */
	        max-height: 250px; /* 최대 세로 길이 250px */
	    }
	
	    /* 네 번째 박스의 세로 최대 길이 설정 */
	    #fourth-box {
	        max-height: 250px; /* 최대 세로 길이 250px */
	        max-width: 62s0px;
	    }
	        /* 박스 사이의 간격을 고정 */
	    .col-6, .col-md-4, .col-12, .col-md-8 {
	        padding: 10px; /* 박스 간격 설정 (좌우 간격) */
	    }
        .hover-overlay {
            position: absolute; /* 이미지 위에 띄우기 */
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            background-color: rgba(255, 255, 255, 0.75); 
            opacity: 0; /* 기본적으로 숨기기 */
            transition: opacity 0.3s ease; /* 부드러운 전환 효과 */
        }
        .diary-box:hover .hover-overlay {
            opacity: 1; /* 호버 시 나타나기 */
        }
        .diary-info {
            position: absolute; /* 이미지 위에 띄우기 */
            bottom: 6px;
            left: 10px;
            right: 0;
            margin: 0;
            opacity: 0; /* 기본적으로 숨기기 */
            transform: translateY(100%);
            transition: opacity 0.3s ease, transform 0.6s ease; /* 부드러운 전환 효과 */
        }
        .diary-box:hover .diary-info {
            opacity: 1; /* 호버 시 나타나기 */
            transform: translateY(0);
        }
        .custom-badge {
            background-color: rgba(31, 174, 193, 0.53); 
            color: grey; /* 텍스트 색상 */
            padding: 2px 4px; /* 패딩 조정 */
            font-size: 10px; /* 글꼴 크기 조정 */
        }
        .header{
        	text-align: center;
        }
         @font-face {
	        font-family: 'Paperlogy-8ExtraBold';
	        src: url('https://fastly.jsdelivr.net/gh/projectnoonnu/2408-3@1.0/Paperlogy-8ExtraBold.woff2') format('woff2');
	        font-weight: 800;
	        font-style: normal;
	    }
	    h2 {
	        font-family: 'Paperlogy-8ExtraBold', sans-serif; /* 폰트 적용 */
	        font-style: italic; /* 기울임체 적용 */
       		 /* 글씨 색상 회색 */
	    }
	    p {
	        font-family: 'Paperlogy-8ExtraBold', sans-serif; /* 폰트 적용 */
	        color: gray;
	    }
    </style>
</head>
<body>
<%
    List<Diary> diaryList = (List<Diary>)request.getAttribute("diaryList");
%>
<%@ include file="main.jsp" %>
<div class="header">
	<h2> 저기 어때 ?</h2>
	<p> 다른 사람들은 이런 다이어리를 작성했어요 ! </p>
</div>

<div class="container">
<div class="row">
<%
    int index = 0; // 인덱스 변수 추가
    for(Diary diary : diaryList) {
        // 첫 번째 줄에 3개의 박스까지
        if (index < 3) {
%>
            <div class="col-6 col-md-4"> <!-- 일반 박스: 반응형 -->
                <div class="diary-box regular-box" style="background-image: url('/howAbout/resources/images/<%=diary.getFilename0()%>');">
                    <a href="/howAbout/diaries/diary/<%= diary.getDiaryId()%>" style="display: block; height: 100%;">
                        <div class="hover-overlay"></div> <!-- 호버 오버레이 추가 -->
                        <div class="diary-info">
                            <span class="badge custom-badge" style="margin-bottom: 5px;"> <%= diary.getUserId() %> &nbsp;님의 다이어리 </span>
                            <p style="font-size: 15px; margin: 0;"> 
                                <span style="font-size: 12px;"><%= diary.getVisit_date() %>&nbsp;</span> &raquo; <%= diary.getVisit_location()%> 
                            </p>
                        </div>
                    </a>
                </div>
            </div>
<%
        } else if (index == 3) { // 네 번째 박스
%>
            <div class="col-12 col-md-8"> <!-- 네 번째 박스: 일반 박스의 두 배, 반응형 -->
                <div class="diary-box" id="fourth-box" style="background-image: url('/howAbout/resources/images/<%=diary.getFilename0()%>');">
                    <a href="/howAbout/diaries/diary/<%= diary.getDiaryId()%>" style="display: block; height: 100%;">
                        <div class="hover-overlay"></div> <!-- 호버 오버레이 추가 -->
                        <div class="diary-info">
                            <span class="badge custom-badge" style="margin-bottom: 5px;"> <%= diary.getUserId() %> &nbsp;님의 다이어리 </span>
                            <p style="font-size: 15px; margin: 0;"> 
                                <span style="font-size: 12px;"><%= diary.getVisit_date() %>&nbsp;</span> &raquo; <%= diary.getVisit_location()%> 
                            </p>
                        </div>
                    </a>
                </div>
            </div>
<%
        } else { // 다섯 번째 박스
%>
            <div class="col-6 col-md-4"> <!-- 다섯 번째 박스: 일반 크기 -->
                <div class="diary-box regular-box" style="background-image: url('/howAbout/resources/images/<%=diary.getFilename0()%>');">
                    <a href="/howAbout/diaries/diary/<%= diary.getDiaryId()%>" style="display: block; height: 100%;">
                        <div class="hover-overlay"></div> <!-- 호버 오버레이 추가 -->
                        <div class="diary-info">
                            <span class="badge custom-badge" style="margin-bottom: 5px;"> <%= diary.getUserId() %> &nbsp;님의 다이어리 </span>
                            <p style="font-size: 15px; margin: 0;"> 
                                <span style="font-size: 12px;"><%= diary.getVisit_date() %>&nbsp;</span> &raquo; <%= diary.getVisit_location()%> 
                            </p>
                        </div>
                    </a>
                </div>
            </div>
<%
        }
        index++; // 인덱스 증가
    }
%>
</div>
</div>

<script>
    document.addEventListener("DOMContentLoaded", function() {
        // 모든 일반 박스의 높이를 가져옵니다.
        const boxes = document.querySelectorAll('.diary-box');
        let maxHeight = 0;

        boxes.forEach(box => {
            const boxHeight = box.offsetHeight; // 각 박스의 높이
            if (boxHeight > maxHeight) {
                maxHeight = boxHeight; // 최대 높이 업데이트
            }
        });

        // 네 번째 박스의 최대 높이를 설정합니다.
        const fourthBox = document.getElementById('fourth-box');
        if (fourthBox) {
            fourthBox.style.maxHeight = maxHeight + 'px'; // 최대 높이 설정
        }
    });
</script>
</body>


</html>