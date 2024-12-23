// 다이어리 함수
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
	            resultItem.textContent = location.data_title +" >> "+ location.user_address;
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


// 장소검색 함수
var searchLocationResults = document.getElementById("results");

function searchLocation()
{
	$.ajax
	(
		{
			url : "/howAbout/location/locationTitles",
			type : "post",
			contentType : "application/json",
			success : function(data)
				{
					//alert("성공"); 
					document.getElementById("searchLocation").style.display="block";
					displayResults(data);
				},
			error : function(errorThrown){ alert("실패"); }
		}		
	)
}

function displayResults(locations)
{
	//const results = document.getElementById("results");
	searchLocationResults.innerHTML = ""; // 이전 결과 초기화
	//results.style.display="none";
	
	if(locations.length > 0)
	{
		locations.forEach
		(
			function(location)
			{
				const resultItem = document.createElement("div");
				resultItem.textContent = location.data_title + " >> " + location.user_address;
				resultItem.style.display="none";
				searchLocationResults.appendChild(resultItem);
			}
		)
	}
	console.log("장소 가져오기 성공");
	//results.style.display="block";
}

var search = document.getElementById("searchLocation")
search.addEventListener('input', lookup);
//search.addEventListener('keyup', lookup);
function lookup()
{
	//var resultbox = ;
	
	var text = search.value.toLowerCase(); // 소문자로 변환하여 대소문자 구분 없이 비교
	console.log(text);
	if(text===null || text.trim()==="")
	{
		console.log("보여줄게없어요");
		searchLocationResults.style.display="none";
	}
	else
	{
		var textboxes = [];
		for(var i=0; i<searchLocationResults.childElementCount; i++)
		{
			var resultItem = searchLocationResults.children[i];
			console.log(resultItem);
			//results.style.display="none";
			if(resultItem.textContent.toLowerCase().includes(text))
			{
				//console.log("검색되는중");
				searchLocationResults.style.display="block";
				resultItem.style.display="block";
				console.log("이제 결과아이템 커서 바꾸기");
				resultItem.style.cursor = "pointer"; // 클릭 가능 표시
				var textbox = resultItem.textContent.split(" >> ");
				//console.log("textbox : "+textbox);

				// 클로저 문제를 피하기 위해 IIFE를 사용
				(function(textbox) 
					{
				       resultItem.onclick = () => searchOneLocation(textbox[0], textbox[1]); // 클릭 시 선택
				 	})(textbox);
				
			}
			else
			{
				resultItem.style.display="none";
			}
		}
	}
}

// 장소 선택하면 컨트롤러로 보내서 해당 장소 보여주기
function searchOneLocation(title, address)
{
	console.log("searchOneLocation 들어옴 title : "+title+" / address : "+address);
	$.ajax
	(
		{
			url : "/howAbout/location/searchOneLocation/"+title+"/"+address,
			type : "POST",
			dataType : "json",
			contentType : "application/json",
			success : function(data)
				{
					window.location.href = "/howAbout/location/onelocation/"+data;
				},
			error : function(errorThrown){ alert("이동 실패"); }
		}
	);
}