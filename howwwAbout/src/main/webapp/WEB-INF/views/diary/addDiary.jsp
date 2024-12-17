<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>다이어리 작성 폼</title>
</head>
<body>
<%@ include file="main.jsp" %>
<br>
<p>다이어리 작성 폼 입니다.</p>

<form action="/howAbout/diaries/addDiary" method="post" enctype="multipart/form-data">
	<p>방 문 일 : <input type="date" name="visit_date" required /> </p>
	<p>방문 장소 : <input type="text" name="visit_location" id="visit_location" required /> 
					<button id="find" onclick="findLocation()" type="button">장소검색</button> </p>
	<div id="locationResults" style="display: none;"></div>
	<p>주   소 : <input type="text" name="address" id="address"/> </p>				
	<p>메   모 : <input type="text" name="visit_diary"/> </p>

	<p> 내 여행 사진을 업로드 하세요. (최대 4장) </p>
	<p> <input type="file" name="uploadFile"/> </p>
	<p> <input type="file" name="uploadFile"/> </p>
	<p> <input type="file" name="uploadFile"/> </p>
	<p> <input type="file" name="uploadFile"/> </p>
	
	<p> 
	<select name="isopen" required>
		<option value="true">공개</option>
		<option value="false">비공개</option>	
	</select>
	</p>
	
	<p><input type="submit" value="제출"> </p>
</form>

</body>
<script>
function findLocation()
{
	const visit_location = document.getElementById("visit_location").value;
	
	if (!visit_location) 
	{
        alert("장소명을 입력하세요.");
        return; // 장소명이 비어있으면 함수 종료
    }
	const encodedLocationName = encodeURIComponent(visit_location);
	
	fetch("/howAbout/location/findLocation?title="+encodedLocationName)
		.then
		(
			response => 
			{
				if(!response.ok){ throw new Error("network response was not ok"); }
				return response.json();
			}	
		)
		.then
		(
			data =>	{ displayLocationResults(data); }	//검색 결과 표시
		)
		.catch
		(
			error => { console.error("Fetch error : "+error.message); }	
		);
}

function displayLocationResults(locations) //locations : 반환된 장소 리스트 담음
{
    const resultsContainer = document.getElementById("locationResults");
    resultsContainer.innerHTML = ""; // 이전 결과 초기화
    resultsContainer.style.display = "none"; // 기본적으로 숨김

    if (locations.length > 0) 
    {
        locations.forEach
        (
        	location => 
        	{
	            const resultItem = document.createElement("div");
	            resultItem.textContent = location.data_title +" - "+ location.user_address;
	            resultItem.style.cursor = "pointer"; // 클릭 가능 표시
	            resultItem.onclick = () => selectLocation(location.data_title, location.user_address); // 클릭 시 선택
	            resultsContainer.appendChild(resultItem);
        	}
        );
        resultsContainer.style.display = "block"; // 결과 표시
    } 
    else 
    {
        resultsContainer.innerHTML = "<div>해당 장소를 찾을 수 없습니다. 원하는 장소가 없다면 직접 입력하세요.</div>";
        resultsContainer.style.display = "block"; // 결과 표시
    }
}

function selectLocation(data_title, user_address) 
{
    document.getElementById("visit_location").value = data_title; // 장소명 입력
    document.getElementById("address").value = user_address; // 주소 입력
    document.getElementById("location-results").style.display = "none"; // 결과 숨김
}
</script>

</html>