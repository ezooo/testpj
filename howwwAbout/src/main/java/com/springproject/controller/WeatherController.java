package com.springproject.controller;

import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.Calendar;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springproject.domain.WeatherOfWeek;
import com.springproject.service.WeatherService;

@Controller
@RequestMapping("/weather")
public class WeatherController 
{
	@Autowired
	WeatherService weatherService;
	
	@GetMapping("/callWeek")
	public WeatherOfWeek weatherCallOfWeek()
	{
		System.out.println("WeatherController callWeek in");
		APIFileWriting afile = new APIFileWriting();
		
		String clientKey = "axdk7ixVxHHdRzI6x1lL6%2FCGVvu%2BsCRNby2Z9thO7g6TdPJCowoZhR0q4PDgM59dCD9YX5EcHqKp0T%2BcSJoNXw%3D%3D";
		// api call
		// 예보구역코드	regid , 3일 후 오전 강수 확률	 rnSt3Am, 3일 후 오전 날씨예보 wf3Am, 8일 후 날씨예보	wf8
		
		WeatherOfWeek weather = new WeatherOfWeek();
		//String regId = "11H20000";	//부산, 울산, 경상남도	//고정이라서 변수 쓸 필요 X
		String tt = ""+LocalDate.now()+"";
		
		String today = tt.replaceAll("-", "");
		System.out.println("today : "+today);
		String baseDate = today+"0600";
		System.out.println("기준일 : "+baseDate);
		
		try 
		{
			// 중기 육상 예보 받아오기
			 StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/MidFcstInfoService/getMidLandFcst"); /*URL*/
		     urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "="+clientKey); /*Service Key*/
		     urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
		     urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
		     urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*요청자료형식(XML/JSON)Default: XML*/
		     urlBuilder.append("&" + URLEncoder.encode("regId","UTF-8") + "=" + URLEncoder.encode("11H20000", "UTF-8")); //부산, 울산, 경상남도
		     urlBuilder.append("&" + URLEncoder.encode("tmFc","UTF-8") + "=" + URLEncoder.encode(baseDate, "UTF-8")); 
		     /*-일 2회(06:00,18:00)회 생성 되며 발표시각을 입력 YYYYMMDD0600(1800)-최근 24시간 자료만 제공*/
	        System.out.println("urlBuilder : "+urlBuilder);
	        
	        URL url = new URL(urlBuilder.toString());
	        
	        JSONObject json = afile.returnJson(url);
	        
	        System.out.println(json);
	        JSONObject response = json.getJSONObject("response");
	        JSONObject body = response.getJSONObject("body");
	        JSONObject items = body.getJSONObject("items");
	        JSONArray item = items.getJSONArray("item");
	        
	        //jackson 쓰는법
	        ObjectMapper om = new ObjectMapper();
	        String itemmm = item.getJSONObject(0).toString();
	        WeatherOfWeek ww = new WeatherOfWeek();
	        ww = om.readValue(itemmm, WeatherOfWeek.class);
	        
	        //weather 에 저장해서 database 보내기
	        weatherService.setWeatherOfWeek(ww);
	        System.out.println("중기예보 set 완료");
		} 
		catch (Exception e) 
		{
			System.out.println("apicall 트라이캐치 에러에러");
			e.printStackTrace();
		}
		
		//기온 불러오기
		System.out.println("이제 기온 불러오기");
		String regId = "11H20301";
		try 
		{
			StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/MidFcstInfoService/getMidTa"); /*URL*/
	        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "="+clientKey); /*Service Key*/
	        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
	        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
	        urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*요청자료형식(XML/JSON)Default: XML*/
	        urlBuilder.append("&" + URLEncoder.encode("regId","UTF-8") + "=" + URLEncoder.encode(regId, "UTF-8")); /*11B10101 서울, 11B20201 인천 등 ( 별첨엑셀자료 참고)*/
	        urlBuilder.append("&" + URLEncoder.encode("tmFc","UTF-8") + "=" + URLEncoder.encode(baseDate, "UTF-8")); /*-일 2회(06:00,18:00)회 생성 되며 발표시각을 입력- YYYYMMDD0600(1800) 최근 24시간 자료만 제공*/
	        URL url = new URL(urlBuilder.toString());
	        
	        JSONObject json = afile.returnJson(url);
	        System.out.println(json);
	        JSONObject response = json.getJSONObject("response");
	        JSONObject body = response.getJSONObject("body");
	        JSONObject items = body.getJSONObject("items");
	        JSONArray item = items.getJSONArray("item");
	        // 3일 후 예상최저기온(℃) taMin3, 3일 후 예상최고기온(℃) taMax3 이라고 해놓고 키는 또 4부터 시작함
	        System.out.println("3일 후 예상 최고 기온 : "+item.getJSONObject(0).getInt("taMax4"));
	        
	        //jackson 쓰는법
//	        ObjectMapper om = new ObjectMapper();
//	        for()
//	        {	
//	        	String aaa = item.getJSONObject(i).toString();
//	        	WeatherWeek ww = new WeatherWeek();
//	        	ww = om.readValue(aaa, WeatherWeek.class);
//	        }
		} 
		catch (Exception e) 
		{
			System.out.println("apicall 기온 불러오기 트라이캐치 에러에러");
			e.printStackTrace();
		}
    
		
		return weather;
    }

	@GetMapping("/ww")
	public void ww()
	{
		//날짜 가공
		Calendar cal = Calendar.getInstance();
//		int todayyy = cal.DATE;
		
		//LocalDate ld = new Local
		LocalDate todayyy = LocalDate.now();
		System.out.println("todayyy : "+todayyy);
	}

}
