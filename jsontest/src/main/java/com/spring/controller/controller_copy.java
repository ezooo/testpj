package com.spring.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


//@Controller
//@RequestMapping("/test")
public class controller_copy 
{
	@GetMapping("/case1")
	public String index()
	{
		return "index";
	}
	
	@GetMapping("/test")
	public void test()
	{
		System.out.println("테스트 인");
		 String apiUrl = "http://apis.data.go.kr/6480000/gyeongnamtournature/gyeongnamtournaturelist?serviceKey=axdk7ixVxHHdRzI6x1lL6%2FCGVvu%2BsCRNby2Z9thO7g6TdPJCowoZhR0q4PDgM59dCD9YX5EcHqKp0T%2BcSJoNXw%3D%3D&numOfRows=10&pageNo=1&resultType=json"; // 호출할 API URL
	      try {
	            // URL 객체 생성
	            URL url = new URL(apiUrl);
	            // HttpURLConnection 객체 생성
	            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	            connection.setRequestMethod("GET"); // 요청 방식 설정

	            // 응답 코드 확인
	            int responseCode = connection.getResponseCode();
	            if (responseCode == HttpURLConnection.HTTP_OK) { // 200 OK
	                // 응답 데이터 읽기
	                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	                String inputLine;
	                StringBuilder response = new StringBuilder();

	                while ((inputLine = in.readLine()) != null) {
	                    response.append(inputLine);
	                }
	                in.close();

	                // 응답 출력
	                System.out.println("Response: " + response.toString());
	                
	                
	                //꺼내보기
	                
	                
	                
	                
	                
	                
	                
	            } else {
	                System.out.println("GET 요청 실패: " + responseCode);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		
	}
}
