package com.springproject.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
		return "location/locationPreview";
	}
	
	@RequestMapping("/addapi")
	public String addLocationAPI()
	{
		System.out.println("LocationController /addapi 매핑");
		System.out.println("API 받아와서 로케이션 저장하기");
		
		//api 요청 할 주소
		String apiUrl = "http://apis.data.go.kr/6480000/gyeongnamtournature/gyeongnamtournaturelist?"
			+ "serviceKey=axdk7ixVxHHdRzI6x1lL6%2FCGVvu%2BsCRNby2Z9thO7g6TdPJCowoZhR0q4PDgM59dCD9YX5EcHqKp0T%2BcSJoNXw%3D%3D&numOfRows=50&pageNo=1&resultType=json"; // 호출할 API URL
			    
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
	            	
	            	//주소 가공하기
	            	String jsonaddr = location.getString("user_address");
	            	//이걸 함수에 실어보내서 api 요청함 --> 가공된 주소와 위도경도 받아오기
	            	String[] contents = locationService.getAPIContents(jsonaddr);
	            	//for 문 돌려서 꺼내기 --> 안돌려도됨..
	            	//System.out.println("주소가공한거 리턴받음 : "+contents[0]);
//	            	location.append("user_address", contents[0]);
//	            	location.append("lattitude", contents[1]);
//	            	location.append("logitude", contents[2]);
	            	//제이슨객체에 값을 할당할 때는 append 말고 put 을 쓴다고 함 (파이썬에서는 직접 값 할당)
	            	if(contents!=null && contents.length!=0)	//카카오에서 주소를 못 찾으면 리스트가 비어서 옴 : 에러남 : 유효성 검사 할 것!
	            	{
	            		location.put("user_address", contents[0]);
	            		location.put("lattitude", contents[1]);
	            		location.put("logitude", contents[2]);
	            	}
	            	
	            	//중복데이터는 받아오지 않도록 처리하기 위해 if문 돌릴건데
	            	//방금 꺼낸 데이터랑 db 저장된 데이터 비교해야하므로 db 검색 함수 추가해야 함
	            	//data_title 만 가져와서 비교하도록 설정하기 String List 받아야 함
	            	List<String> titleList = locationService.getAlltitle();
	            	List<String> addrList = locationService.getAlladdr(); 	
	            	
	            	if(titleList.contains(location.getString("data_title")))
	            	{
	            		System.out.println("addLocationAPI 같은 이름 발견..");
	            		if(addrList.contains(location.getString("user_address")))
	            		{
	            			System.out.println("addLocationAPI 주소도 같음 중복 !! ");
	            			continue;
	            		}
	            		System.out.println("주소는 다른 듯");
	            	}
	            	System.out.println("장소이름은 : "+location.getString("data_title"));
	            	System.out.println("주소는 : "+location.getString("user_address"));
	            	
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
		return "location/location";
	}

	@GetMapping("/locations")
	public String getAllLocation(Model model)
	{
		System.out.println("LocationController getAllLocation in");
		List<Location> locations = locationService.getAllLocation();
		
		if( !(locations.isEmpty()) )
		{
			model.addAttribute("locations", locations);
			
			return "location/locations";
		}
		return "location/errorLocation";
	}

	@GetMapping("/onelocation/{num}")
	public String getOneLocation(@PathVariable int num, Model model)
	{
		System.out.println("LocationController getOneLocation in");
		Location location = locationService.getOneLocation(num);
		if(location != null)
		{
			System.out.println("이름으로 장소찾기 완료 ! 뷰 페이지 반환합니다.");
			model.addAttribute("location",location);
			return "location/location";			
		}
		System.out.println("장소찾기 실패.. ");
		return "location/errorLocation";
	}

	
	@GetMapping("/category/{category}")
	public String getLocationOfCategory(@PathVariable String category, Model model)
	{
		System.out.println("LocationController getLocationCategory in");
		ArrayList<Location> locations = (ArrayList<Location>) locationService.getLocationOfCategory(category);
		if(locations != null)
		{
			System.out.println("로케이션 카테고리 찾기 성공 !");
			model.addAttribute("locations",locations);
			model.addAttribute("category", category);
			return "location/locationOfcategory";
		}
		System.out.println("로케이션 카테고리 못 찾음...");
		return "location/errorLocation";
	}

	@GetMapping("/locationCategory")
	public String getAllCategory(Model model)
	{
		System.out.println("LocationController getAllCategory in");
		ArrayList<Location> categoryList = (ArrayList<Location>) locationService.getAllCategory();
		if(categoryList != null)
		{
			System.out.println("getAllCategory 카테고리 분류 성공 !");
			model.addAttribute("categoryList",categoryList);
			return "location/locationCategory";
		}
		System.out.println("getAllCategory 카테고리 분류 fail...");
		return "location/errorLocation";
	}

	@GetMapping("/locationArea")
	public String getAllArea(Model model)
	{
		System.out.println("LocationController getAllArea in");
		ArrayList<String> areaList = (ArrayList<String>) locationService.getAllArea();
		if(areaList != null)
		{
			System.out.println("getAllArea 카테고리 분류 성공 !");
			model.addAttribute("areaList",areaList);
			return "location/locationArea";
		}
		System.out.println("getAllArea 카테고리 분류 fail...");
		return "location/errorLocation";
	}
	
	@GetMapping("/area/{area}")
	public String getLocationOfArea(@PathVariable String area, Model model)
	{
		System.out.println("LocationController getLocationOfArea in");
		List<Location> locationOfArea = locationService.getLocationOfArea(area);
		model.addAttribute("locationOfArea", locationOfArea);
		model.addAttribute(area);
		return "location/locationOfArea";
	}
	
	@GetMapping("/create")
	public String createLocation(@ModelAttribute Location location)
	{
		System.out.println("장소 추가하기 뷰 이동");
		
		return "location/createLocation";
	}
	
	@PostMapping("/create")
	public String submitCreateLocation(Location location)
	{
		System.out.println("LocationController submitCreateLocation in");
		locationService.createLocation(location);
		
		return "redirect:/location";
	}

	@GetMapping("/update")
	public String updateLocation(@RequestParam("num") int num, Model model)
	{
		System.out.println("LocationController updateLocation in : 폼으로 이동");
		//String[] find = {lat, log};
		Location location = locationService.getOneLocation(num);
		//int num = location.getNum();
		if(location != null)
		{
			model.addAttribute("location",location);
			//model.addAttribute("num", location.getNum());	//primary key 인 num값은 수정할 수 없도록 하기위해서 따로 담아서 이동
			
			return "location/updateLocation";
		}
		return "location/errorLocation";
	}
	
	@PostMapping("/update")
	public String submitUpdateLocation(@ModelAttribute Location location, Model model)
	{
		System.out.println("LocationController submitUpdateLocation in");
		//모델에 담아 둔 num 꺼내서 DTO에 담기
//		Integer num = (Integer)model.asMap().get("num");
//		System.out.println("num : "+num);
//		if(num!=null)	//받아온 num이 유효할 때
//		{
//			location.setNum(num);
//			System.out.println("dto에 num 셋 완료");
//		}
		
//		String title = location.getData_title();
//		String title2=null;
//		try 
//		{
//			title2 = URLEncoder.encode(title, "UTF-8").replace("+", "%20");
//		} 
//		catch (UnsupportedEncodingException e) 
//		{
//			e.printStackTrace();
//		}
		int num = location.getNum();
		locationService.submitUpdateLocation(location);
		System.out.println("LocationController submitUpdateLocation 수정완료");
		//return "redirect:/location/onelocation/"+title2 ;
		return "redirect:/location/onelocation/"+num ;
	}

	@GetMapping("/delete")
	public String deleteLocation(@RequestParam("lat") String lat, @RequestParam("log") String log)
	{
		System.out.println("LocationController deleteLocation in");
		locationService.deleteLocation(lat, log);
		System.out.println("로케이션 삭제 완료");
		return "redirect:/location";
	}

	@GetMapping("/findLocation")
	@ResponseBody	//json 형식으로 응답하기 위한 코드
	public List<Location> findLocations(@RequestParam String title)
	{
		System.out.println("LocationController findLocations in");
		List<Location> find = locationService.findLocationByTitle(title);
		System.out.println(find + " 찾아옴");
		return find;
	}
	
//	@PostMapping("/locationTitles")
//	@ResponseBody	//json 형식으로 응답하기 위한 코드
//	public Map<String, List<String>> getLocationTitles()
//	{
//		System.out.println("LocationController getLocationTitles in");
//		List<String> titles = locationService.getAlltitle();
//		Map<String, List<String>> locationTitles = new HashMap<String, List<String>>();
//		locationTitles.put("titles", titles);
//		return locationTitles;
//	}
	
	@PostMapping("/locationTitles")
	@ResponseBody	//json 형식으로 응답하기 위한 코드
	public List<Location> getLocationTitles()
	{
		System.out.println("LocationController getLocationTitles in");
		List<Location> locationTitles = locationService.getAllLocation();

		return locationTitles;
	}
	
	@PostMapping("/searchOneLocation/{title}/{address}")
	@ResponseBody
	public Integer searchOneLocation(@PathVariable("title") String title, @PathVariable("address") String address)
	{
		System.out.println("LocationController searchOneLocation in");
		System.out.println("받아온 파라미터 title : " + title);
		System.out.println("받아온 파라미터 address : " + address);
		//이 제목과 주소 가지고 장소 하나 찾아와야 함
//		Location lt = locationService.searchOneLocation(title, address);
//		int num = lt.getNum();
		int num = locationService.searchOneLocationNum(title, address);
		//return "redirect:/location/onelocation/"+num;
		return num;
		//java.lang.IllegalArgumentException: Unknown return value type: java.lang.Integer
	}
}
