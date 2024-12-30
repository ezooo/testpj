<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ page import="com.springproject.domain.Diary" %>
<%@ page import="com.springproject.domain.Member" %>
<%@page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 다이어리</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
<style>
    body {
        background-image: url('/howAbout/resources/images/back.png');
        background-position: center; /* 중앙에 위치 */
        background-repeat: no-repeat; /* 반복하지 않음 */
        background-size: contain; /* 이미지 크기를 화면에 맞게 조정 */
        height: 100vh; /* 전체 화면 높이 */
        margin: 0; /* 기본 마진 제거 */
    }
    .diary-box {
        position: relative;
        border: 1px hidden;
        border-radius: 4px; /* 둥글기 조정 */
        overflow: hidden;
        margin-bottom: 20px;
        height: 250px; /* 박스 높이 고정 */
        background-color: rgba(255, 255, 255, 0.78); 
        display: flex; /* Flexbox 사용 */
        justify-content: center; /* 중앙 정렬 */
        align-items: center; /* 중앙 정렬 */
        transition: all 0.3s ease; /* 부드러운 전환 효과 */
    }
    .diary-image {
        width: 100%;
        height: 100%; /* 높이 100% */
        object-fit: contain; /* 이미지 비율 유지 */
        transition: transform 1s ease; /* 이미지 전환 효과 */
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
</style>
</head>
<body>
<%
    List<Diary> diaryList = (List<Diary>)request.getAttribute("diaryList");
    HttpSession ssn = request.getSession(false);
    Member mb = (Member)ssn.getAttribute("member");
%>
<%@ include file="main.jsp" %>

<%
if(mb != null) {
%>  
    <h2>내 다이어리</h2>
    <hr><hr>
    <div class="container">
        <div class="row">
<%
        for(Diary diary : diaryList) {
%>
            <div class="col-6 col-md-4 col-lg-3">
                <div class="diary-box">
                    <a href="/howAbout/diaries/diary/<%= diary.getDiaryId()%>" style="display: block; height: 100%;">
                        <img src="/howAbout/resources/images/<%=diary.getFilename0()%>" class="diary-image" />
                        <div class="hover-overlay"></div>
                        <div class="diary-info">
                            <span class="badge custom-badge"> <%= diary.getUserId() %> &nbsp;님의 다이어리 </span>
                            <p style="font-size: 15px; margin: 0;">
                                <span style="font-size: 12px;"><%= diary.getVisit_date() %>&nbsp;</span> &raquo; <%= diary.getVisit_location()%> 
                            </p>
                        </div>
                    </a>
                </div>
            </div>
<%
        }
%>
        </div>
    </div>
<%
} else {
%>
    <p> 로그인하고 내 다이어리를 작성해보세요</p>
    <a href="login">로그인</a> <br>
<%
}
%>
</body>
</html>