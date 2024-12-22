package com.spring.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import org.json.HTTP;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

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
	
	@GetMapping("/case2")
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

	@GetMapping("/tt")
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
	
	private final RestTemplate restTemplate = new RestTemplate();


	
	@GetMapping("/sss")
	public void tessss(Model model)
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
				String requrl = "https://openapi.naver.com/v1/map/geocode?query=" + addr;

				HttpHeaders headers = new HttpHeaders();
		        headers.set("X-Naver-Client-Id", client_id);
		        headers.set("X-Naver-Client-Secret", client_secret);
		        HttpEntity<String> entity = new HttpEntity<>(headers);

		        ResponseEntity<String> response = restTemplate.exchange(requrl, HttpMethod.GET, entity, String.class);
		        
		        // 응답 처리
		        System.out.println(response.getBody());
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

	@GetMapping("/naver")
	public void naver(Model model)
	{
		System.out.println("네이버 테스트 인");
		String client_id = "1qjymgijxj";
		String client_secret = "aNlBGa2TOJhxl88aLT9g1sT0eDLHYvGuLci7Rmmg";
		BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader ioo = new BufferedReader(new InputStreamReader(System.in));
		//주소를 입력받아야 해요
		while(true)
		{
			try 
			{
				//String address = io.readLine();
				//String addr = URLEncoder.encode(address, "UTF-8");
				//주소 입력받은거 인코딩 함 --> 할필요없음 위도경도는 숫자만 들어가니까
/*				System.out.println("위도를 입력하세요.");
				String lattitude = io.readLine();
				System.out.println("경도를 입력하세요.");
				String logitude = ioo.readLine();
				String requrl = "https://naveropenapi.apigw.ntruss.com/map-reversegeocode/v2/gc"
						+ "?coords="+lattitude+"%2C"+logitude+"&output=json&orders=legalcode%2Cadmcode%2Caddr%2Croadaddr";*/
				System.out.println("위도,경도를 입력하세요.");
				String lalo = io.readLine();
				System.out.println("입력받음");
				String requrl = "https://naveropenapi.apigw.ntruss.com/map-reversegeocode/v2/gc"
						+ "?coords="+lalo+"&output=json&orders=legalcode%2Cadmcode%2Caddr%2Croadaddr";
				System.out.println("주소는 : "+requrl);
				//요청주소 확보했으면 네이버 서버와 연결해야 함 : 커넥션 객체 이용해야함
				//그 전에 주소를 url 클래스에 담는다 : 
				URL url = new URL(requrl);
				HttpURLConnection conn = (HttpURLConnection)url.openConnection();
				conn.setRequestMethod("GET");
				//필요한 아이디와 비밀번호를 헤더에 파라미터로 삽입
				conn.setRequestProperty("x-ncp-apigw-api-key-id", client_id);
				conn.setRequestProperty("x-ncp-apigw-api-key", client_secret);
				
				//이걸 또 버퍼에 넣어서 읽어와
				BufferedReader br;
				//응답코드 확인
				int responseCode = conn.getResponseCode();
				if(responseCode==200)
				{
					System.out.println("응답코드 정상");
					br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
				}
				else
				{
					System.out.println("응답코드 error");
					br = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
				}
				//한줄씩 읽어와서 내용이 있으면 스트링버퍼에 append 할 것 : 필요한거 : 한줄씩 읽기위한 스트링과 내용담을 스트링 버퍼
				String line;
				StringBuffer sb = new StringBuffer();
				while( (line = br.readLine())!= null )
				{
					sb.append(line);
				}
				br.close();
				
				JSONTokener tokener = new JSONTokener(sb.toString());
				JSONObject object = new JSONObject(tokener);
				System.out.println(object);
				
				JSONArray arr = object.getJSONArray("results");
				JSONObject ob = arr.getJSONObject(0);
				JSONObject region = ob.getJSONObject("region");
				
				int i = 1;
				String area = "area"+i;
				StringBuffer newaddr = new StringBuffer();
				
				while( region.has(area) && region.getJSONObject(area)!=null )
				{
					System.out.println(area + "존재함");
					String name = region.getJSONObject(area).getString("name");
					System.out.println(area + name);
					newaddr.append(name+" ");
					System.out.println("가공한 주소 : "+newaddr);
					i++;
					area = "area"+i;
				}
			} 
			catch (IOException e) 
			{
				System.out.println("위도경도 네이버연결 에러에러");
				e.printStackTrace();
			}
		}
	}

	@GetMapping("/kko")
	public void kko(Model model)
	{
		System.out.println("카카오 테스트 인");
		String REST_API_KEY = "d1b75ca528c7355eb5a8b379d289c649";

		BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader ioo;
		//주소를 입력받아야 해요
		//while(true)
		//{
			try 
			{
				//System.out.println("위도를 입력하세요.");
				//String lattitude = io.readLine();
				//System.out.println("경도를 입력하세요.");
				//ioo = new BufferedReader(new InputStreamReader(System.in));
				//String logitude = ioo.readLine();

				System.out.println("입력받음");
				String requrl = "https://dapi.kakao.com/v2/local/geo/coord2address.json?x="+128.042147796143+"&y="+34.9395059326023+"&input_coord=WGS84";
				System.out.println("주소는 : "+requrl);
				//요청주소 확보했으면 네이버 서버와 연결해야 함 : 커넥션 객체 이용해야함
				//그 전에 주소를 url 클래스에 담는다 : 
				URL url = new URL(requrl);
				HttpURLConnection conn = (HttpURLConnection)url.openConnection();
				conn.setRequestMethod("GET");
				//필요한 아이디와 비밀번호를 헤더에 파라미터로 삽입
				conn.setRequestProperty("Authorization", "KakaoAK " + REST_API_KEY);
				
				//이걸 또 버퍼에 넣어서 읽어와
				BufferedReader br;
				//응답코드 확인
				int responseCode = conn.getResponseCode();
				if(responseCode==200)
				{
					System.out.println("응답코드 정상");
					br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
				}
				else
				{
					System.out.println("응답코드 error");
					br = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
				}
				//한줄씩 읽어와서 내용이 있으면 스트링버퍼에 append 할 것 : 필요한거 : 한줄씩 읽기위한 스트링과 내용담을 스트링 버퍼
				String line;
				StringBuffer sb = new StringBuffer();
				while( (line = br.readLine())!= null )
				{
					sb.append(line);
				}
				br.close();
				
				JSONTokener tokener = new JSONTokener(sb.toString());
				JSONObject object = new JSONObject(tokener);
				System.out.println(object);
				
//				JSONArray arr = object.getJSONArray("results");
//				JSONObject ob = arr.getJSONObject(0);
//				JSONObject region = ob.getJSONObject("region");
//				
//				int i = 1;
//				String area = "area"+i;
//				StringBuffer newaddr = new StringBuffer();
//				
//				while( region.has(area) && region.getJSONObject(area)!=null )
//				{
//					System.out.println(area + "존재함");
//					String name = region.getJSONObject(area).getString("name");
//					System.out.println(area + name);
//					newaddr.append(name+" ");
//					System.out.println("가공한 주소 : "+newaddr);
//					i++;
//					area = "area"+i;
//				}
			} 
			catch (IOException e) 
			{
				System.out.println("위도경도 연결 에러에러");
				e.printStackTrace();
			}
		//}
	}
	
	@GetMapping("/kkoo")
	public void kkoo(Model model)
	{
		System.out.println("카카오 주소 테스트 인");
		String REST_API_KEY = "d1b75ca528c7355eb5a8b379d289c649";

		BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader ioo;
		//주소를 입력받아야 해요
		while(true)
		{
			try 
			{
				System.out.println("주소를 입력하세요.");
				String address = io.readLine();

				System.out.println("입력받음");
				String addr = URLEncoder.encode(address, "UTF-8");
				String requrl = "https://dapi.kakao.com/v2/local/search/address.json?query="+addr;
				System.out.println("주소는 : "+requrl);
				//요청주소 확보했으면 네이버 서버와 연결해야 함 : 커넥션 객체 이용해야함
				//그 전에 주소를 url 클래스에 담는다 : 
				URL url = new URL(requrl);
				HttpURLConnection conn = (HttpURLConnection)url.openConnection();
				conn.setRequestMethod("GET");
				//필요한 아이디와 비밀번호를 헤더에 파라미터로 삽입
				conn.setRequestProperty("Authorization", "KakaoAK " + REST_API_KEY);
				conn.setRequestProperty("Content-Type", "application/json");
				
				//이걸 또 버퍼에 넣어서 읽어와
				BufferedReader br;
				//응답코드 확인
				int responseCode = conn.getResponseCode();
				if(responseCode==200)
				{
					System.out.println("응답코드 정상");
					br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
				}
				else
				{
					System.out.println("응답코드 error");
					br = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
				}
				//한줄씩 읽어와서 내용이 있으면 스트링버퍼에 append 할 것 : 필요한거 : 한줄씩 읽기위한 스트링과 내용담을 스트링 버퍼
				String line;
				StringBuffer sb = new StringBuffer();
				while( (line = br.readLine())!= null )
				{
					sb.append(line);
				}
				br.close();
				
				JSONTokener tokener = new JSONTokener(sb.toString());
				JSONObject object = new JSONObject(tokener);
				System.out.println(object);
				//가공하기
				//가져다 쓸 것 : 주소, 위도, 경도
				JSONArray documents = object.getJSONArray("documents");
				JSONObject ob = documents.getJSONObject(0);
				String user_address = ob.getString("address_name");
				String lattitude = ob.getString("y");
				String logitude = ob.getString("x");
				System.out.println("주소 : "+user_address);
				System.out.println("위도 : "+lattitude);
				System.out.println("경도 : "+logitude);
			} 
			catch (IOException e) 
			{
				System.out.println("위도경도 연결 에러에러");
				e.printStackTrace();
			}
		}
	}
}
