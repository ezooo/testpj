package com.springproject.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springproject.domain.Location;
import com.springproject.service.LocationService;

@Controller
@RequestMapping("/location")
public class LocationController 
{
	@Autowired
	LocationService locationService;
	
	@RequestMapping
	public String locationin()
	{
		return "locationPreview";
	}
	
	@RequestMapping("/addapi")
	public String addLocationAPI()
	{
		System.out.println("LocationController /addapi 매핑");
		System.out.println("API 받아와서 로케이션 저장하기");
		
		//api 요청 할 주소
		String apiUrl = "http://apis.data.go.kr/6480000/gyeongnamtournature/gyeongnamtournaturelist?"
			+ "serviceKey=axdk7ixVxHHdRzI6x1lL6%2FCGVvu%2BsCRNby2Z9thO7g6TdPJCowoZhR0q4PDgM59dCD9YX5EcHqKp0T%2BcSJoNXw%3D%3D&numOfRows=30&pageNo=2&resultType=json"; // 호출할 API URL
			    
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
	            JSONObject json = new JSONObject(tokener);	//이게 최상위임 : json 객체
	            JSONObject gyeongnam = json.getJSONObject("gyeongnamtournaturelist");	// json 객체에서 키로 경남꺼내기
	            System.out.println(gyeongnam);
	            JSONObject body = gyeongnam.getJSONObject("body");	//바디 객체 꺼내기
	            System.out.println("바디꺼냄 : "+body.toString());
	            JSONObject items = body.getJSONObject("items");
	            JSONArray item = items.getJSONArray("item");
	            
	            for(int i=0; i<item.length(); i++)
	            {
	            	JSONObject location = item.getJSONObject(i);
	            	System.out.println("location "+i+"번째 꺼냄 : "+location);
	            	
	            	//중복데이터는 받아오지 않도록 처리하기 위해 if문 돌릴건데
	            	//방금 꺼낸 데이터랑 db 저장된 데이터 비교해야하므로 db 검색 함수 추가해야 함
	            	//data_title 만 가져와서 비교하도록 설정하기 String List 받아야 함
	            	List<String> titleList = locationService.getAlltitle();
	            	if(titleList.contains(location.getString("data_title")))
	            	{
	            		System.out.println("addLocationAPI 중복된 내용 발견 : continue");
	            		continue;
	            	}
	            	
	            	locationService.addLocationAPI(location);
	            }
	            System.out.println("로케이션 꺼내기 완료 !");
	        } 
	        else 
	        {
	            System.out.println("GET 요청 실패: " + responseCode);
	            //br = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
	            //요청이 실패했으면 에러코드를 버퍼에 담음
	        }
	    } 
		catch (Exception e) 
		{
	        e.printStackTrace();
	    }
		
		System.out.println("로케이션 저장완료");
		return "redirect:/";
	}
	
	
	public String addL()
	{
		System.out.println("LocationController /location 매핑");
		System.out.println("API 받아와서 로케이션 저장하기");
		
		//api 요청 할 주소
		String apiUrl = "http://apis.data.go.kr/6480000/gyeongnamtournature/gyeongnamtournaturelist?"
			+ "serviceKey=axdk7ixVxHHdRzI6x1lL6%2FCGVvu%2BsCRNby2Z9thO7g6TdPJCowoZhR0q4PDgM59dCD9YX5EcHqKp0T%2BcSJoNXw%3D%3D&numOfRows=20&pageNo=1&resultType=json"; // 호출할 API URL
			    
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
			    
	            JSONObject json = new JSONObject(tokener);	//이게 최상위임 : json 객체
	           
	            JSONObject gyeongnam = json.getJSONObject("gyeongnamtournaturelist");	// json 객체에서 키로 경남꺼내기
	            System.out.println(gyeongnam);
	            JSONObject body = gyeongnam.getJSONObject("body");	//바디 객체 꺼내기
	            System.out.println("바디꺼냄 : "+body.toString());
	            JSONObject items = body.getJSONObject("items");
	            JSONArray item = items.getJSONArray("item");
	            JSONObject location = item.getJSONObject(0);
	            System.out.println("location 0번째 꺼냄 : "+location);
	            
	            //model.addAttribute("location", location);
	            locationService.addLocationAPI(location);
	        } 
	        else 
	        {
	            System.out.println("GET 요청 실패: " + responseCode);
	            //br = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
	            //요청이 실패했으면 에러코드를 버퍼에 담음
	        }
	    } 
		catch (Exception e) 
		{
	        e.printStackTrace();
	    }
		
		
		System.out.println("로케이션 저장완료");
		return "location";
	}

	@GetMapping("/locations")
	public String getAllLocation(Model model)
	{
		System.out.println("LocationController getAllLocation in");
		List<Location> locations = locationService.getAllLocation();
		
		if( !(locations.isEmpty()) )
		{
			model.addAttribute("locations", locations);
			
			return "locations";
		}
		return "errorLocation";
	}

	@GetMapping("/onelocation/{title}")
	public String getOneLocation(@PathVariable String title, Model model)
	{
		System.out.println("LocationController getOneLocation in");
		System.out.println("뷰에서 인코딩해서 받아온 타이틀 : "+title);
		Location location = locationService.getOneLocation(title);
		if(location != null)
		{
			System.out.println("이름으로 장소찾기 완료 ! 뷰 페이지 반환합니다.");
			model.addAttribute("location",location);
			return "location";			
		}
		System.out.println("장소찾기 실패.. ");
		return "errorLocation";
	}

//	@GetMapping("/category/{category}")
//	public String getLocationOfCategory(@PathVariable String category, @ModelAttribute ArrayList<Location> locations)
//	{
//		System.out.println("LocationController getLocationCategory in");
//		locations = (ArrayList<Location>) locationService.getLocationOfCategory(category);
//		if(locations != null)
//		{
//			System.out.println("로케이션 카테고리 찾기 성공 !");
//			return "locationOfcategory";
//		}
//		System.out.println("로케이션 카테고리 못 찾음...");
//		return "errorLocation";
//	}
	
	@GetMapping("/category/{category}")
	public String getLocationOfCategory(@PathVariable String category, Model model)
	{
		System.out.println("LocationController getLocationCategory in");
		ArrayList<Location> locations = (ArrayList<Location>) locationService.getLocationOfCategory(category);
		if(locations != null)
		{
			System.out.println("로케이션 카테고리 찾기 성공 !");
			model.addAttribute("locations",locations);
			return "locationOfcategory";
		}
		System.out.println("로케이션 카테고리 못 찾음...");
		return "errorLocation";
	}
	
}
