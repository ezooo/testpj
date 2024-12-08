package com.spring.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.domain.Location;
import com.spring.repository.LocationRepository;


@Controller
@RequestMapping("/")
public class controller 
{
	@Autowired
	LocationRepository lr;
	List<Location> list;
	
	@GetMapping("/case1")
	public String index()
	{
		return "index";
	}
	
	@GetMapping
	public void test(Model model)
	{
		System.out.println("테스트 인");
		//api 요청 할 주소
		String apiUrl = "http://apis.data.go.kr/6480000/gyeongnamtournature/gyeongnamtournaturelist?"
				+ "serviceKey=axdk7ixVxHHdRzI6x1lL6%2FCGVvu%2BsCRNby2Z9thO7g6TdPJCowoZhR0q4PDgM59dCD9YX5EcHqKp0T%2BcSJoNXw%3D%3D&numOfRows=10&pageNo=1&resultType=json"; // 호출할 API URL
	    
		try 
	    {
			System.out.println("서버와 연결 try in");
	        // URL 객체 생성
	        URL url = new URL(apiUrl);
	        // HttpURLConnection 객체 생성 : 주소를 URL 클래스에 담아서 openConnection() 통해 서버와 연결 시도함
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setRequestMethod("GET"); // 요청 방식 설정
	        BufferedReader br;	//input을 읽어오기 위한 클래스
	
	        // 응답 코드 확인
	        int responseCode = connection.getResponseCode();
	        if (responseCode == 200) //HttpURLConnection.HTTP_OK 써도 됨
	        {
	        	System.out.println("정상적인 응답코드 200 확인 완료. 데이터를 읽어오자");
	            // 응답 데이터 읽기
	            br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
	            
	            String inputLine;
	            //StringBuilder response = new StringBuilder(); 뤼튼의 추천
	            StringBuffer response = new StringBuffer();	//json
	            
	            while((inputLine = br.readLine()) != null) 
	            {	//한 줄씩 읽어와서 스트링에 담고 그걸 또 응답 클래스안에 한 줄씩 담음
	            	response.append(inputLine);
	            }
	            br.close();
	            System.out.println("작성완료");
	            
	            // 응답 출력
	            //System.out.println("Response: " + response.toString());
	            
	            //꺼내보기
	            JSONTokener tokener = new JSONTokener(response.toString());	//json
//	            JSONObject gyeongnam = new JSONObject(tokener);
//	            System.out.println(gyeongnam.toString());
//	            JSONObject body = gyeongnam.getJSONObject("body");	//바디 객체 꺼내기
//	            System.out.println(body.toString());
	            JSONObject json = new JSONObject(tokener);	//이게 최상위임 : json 객체
	           
	            JSONObject gyeongnam = json.getJSONObject("gyeongnamtournaturelist");	// json 객체에서 키로 경남꺼내기
	            JSONObject body = gyeongnam.getJSONObject("body");	//바디 객체 꺼내기
	            System.out.println("바디꺼냄 : "+body.toString());
	            JSONObject items = body.getJSONObject("items");
	            JSONArray item = items.getJSONArray("item");
	            JSONObject location = item.getJSONObject(0);
	            System.out.println("location 0번째 꺼냄 : "+location);
	            
	            //model.addAttribute("location", location);
	            lr.setting(location);
	        } 
	        else 
	        {
	            System.out.println("GET 요청 실패: " + responseCode);
	            //br = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
	            //요청이 실패했으면 에러코드를 버퍼에 담음
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	@GetMapping("/testtt")
	public void testtt(Model model)
	{
		System.out.println("테스트 테스트 인");
		String client_id = "1qjymgijxj";
		String client_secret = "aNlBGa2TOJhxl88aLT9g1sT0eDLHYvGuLci7Rmmg";
		//네이버 지도 호출을 위한 키
		//키보드를 통해 인풋 입력받고 라인 단위로 읽기 위해 버퍼 리더 연결
		BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
		Boolean bb = true;
		while(bb)
		{
			try 
			{
				System.out.println("검색할 주소를 입력하세요.");
				String address = io.readLine();	//한줄씩 읽기
				String addr = URLEncoder.encode(address, "UTF-8");
				System.out.println("addr"+addr);
				String requrl = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query="+addr;
				//주소를 url클래스에 담아 --> 커넥션함수 통해 네이버 서버와 연결하기
				URL url = new URL(requrl);
				HttpURLConnection con = (HttpURLConnection)url.openConnection();
				con.setRequestMethod("GET");
				//필요한 아이디와 비밀번호를 헤더에 파라미터로 삽입
				con.setRequestProperty("x-ncp-apigw-api-key-id", client_id);
				con.setRequestProperty("x-ncp-apigw-api-key", client_secret);
				
				BufferedReader br;
				int responseCode = con.getResponseCode();	//응답코드가 200 이면 정상실행
				if(responseCode==200)
				{
					System.out.println("테스트 테스트 정상적인 응답코드");
					//문자단위로 읽어서 라인 단위로 변환.
					br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
				}
				else
				{
					System.out.println("테스트 테스트 응답실패");
					br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
				}
				String line;
				StringBuffer response = new StringBuffer();
				
				while( (line=br.readLine())!=null )
				{
					response.append(line);
				}
				br.close();
				System.out.println(response);
				
				JSONTokener tokener = new JSONTokener(response.toString());
				JSONObject object = new JSONObject(tokener);
				System.out.println(object.toString());
				
				JSONArray arr = object.getJSONArray("addresses");
				System.out.println(arr);
			}
			catch (IOException e) 
			{
				System.out.println("testtt 에러에러");
				e.printStackTrace();
			} 
		}
	}
	
	
	@GetMapping("/tesss")
	public void tesss(Model model)
	{
		System.out.println("테스트 테스트 인");
		String client_id = "1qjymgijxj";
		String client_secret = "aNlBGa2TOJhxl88aLT9g1sT0eDLHYvGuLci7Rmmg";
		//네이버 지도 호출을 위한 키
		//키보드를 통해 인풋 입력받고 라인 단위로 읽기 위해 버퍼 리더 연결
		BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
		Boolean bb = true;
		while(bb)
		{
			try 
			{
				System.out.println("검색할 주소를 입력하세요.");
				String address = io.readLine();	//한줄씩 읽기
				String addr = URLEncoder.encode(address, "UTF-8");
				System.out.println("addr"+addr);
				String requrl = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query="+addr;
				//주소를 url클래스에 담아 --> 커넥션함수 통해 네이버 서버와 연결하기
				URL url = new URL(requrl);
				HttpURLConnection con = (HttpURLConnection)url.openConnection();
				con.setRequestMethod("GET");
				//필요한 아이디와 비밀번호를 헤더에 파라미터로 삽입
				con.setRequestProperty("x-ncp-apigw-api-key-id", client_id);
				con.setRequestProperty("x-ncp-apigw-api-key", client_secret);
				
				BufferedReader br;
				int responseCode = con.getResponseCode();	//응답코드가 200 이면 정상실행
				if(responseCode==200)
				{
					System.out.println("테스트 테스트 정상적인 응답코드");
					//문자단위로 읽어서 라인 단위로 변환.
					br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
				}
				else
				{
					System.out.println("테스트 테스트 응답실패");
					br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
				}
				String line;
				StringBuffer response = new StringBuffer();
				
				while( (line=br.readLine())!=null )
				{
					response.append(line);
				}
				br.close();
				System.out.println(response);
				
				JSONTokener tokener = new JSONTokener(response.toString());
				JSONObject object = new JSONObject(tokener);
				System.out.println(object.toString());
				
				JSONArray arr = object.getJSONArray("addresses");
				System.out.println(arr);
			}
			catch (IOException e) 
			{
				System.out.println("testtt 에러에러");
				e.printStackTrace();
			} 
		}
	}
}
