package com.springproject.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/weather")
public class WeatherController 
{
	@GetMapping("/call")
	public void apicall()
	{
		System.out.println("WeatherController apicall in");
		String clientKey = "axdk7ixVxHHdRzI6x1lL6%2FCGVvu%2BsCRNby2Z9thO7g6TdPJCowoZhR0q4PDgM59dCD9YX5EcHqKp0T%2BcSJoNXw%3D%3D";
		// api call
		//T1H(기온), UUU(동서바람성분), VVV(남북바람성분), VEC(풍향), WSD(풍속), SKY(하늘상태), LGT(낙뢰), PTY(강수형태), RN1(1시간 강수량), REH(상대습도)
		try 
		{
			StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtFcst"); /*URL*/
			urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + clientKey);	/*Service Key*/
	        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
	        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8")); /*한 페이지 결과 수*/
	        urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*요청자료형식(XML/JSON) Default: XML*/
	        urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode("20241223", "UTF-8")); /*‘21년 6월 28일발표*/
	        urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode("0630", "UTF-8")); /*06시30분 발표(30분 단위)*/
	        urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode("55", "UTF-8")); /*예보지점의 X 좌표값*/
	        urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode("127", "UTF-8")); /*예보지점의 Y 좌표값*/
	        System.out.println("urlBuilder : "+urlBuilder);
	        
	        URL url = new URL(urlBuilder.toString());
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Content-type", "application/json");
	        System.out.println("Response code: " + conn.getResponseCode());
	        BufferedReader rd;
	        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) 
	        {
	        	System.out.println("apicall 응답코드 정상");
	            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        } 
	        else 
	        {
	        	System.out.println("apicall 응답코드 에러에러");
	            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	        }
	        StringBuilder sb = new StringBuilder();
	        String line;
	        while ((line = rd.readLine()) != null) 
	        {
	            sb.append(line);
	        }
	        System.out.println("apicall 파일 작성 완료");
	        rd.close();
	        conn.disconnect();
	        System.out.println("sb : "+sb.toString());
	        
	        //제이슨 꺼내기
	        JSONTokener tokener = new JSONTokener(sb.toString());
	        JSONObject json = new JSONObject(tokener);
	        System.out.println(json);
	        JSONObject response = json.getJSONObject("response");
	        JSONObject body = response.getJSONObject("body");
	        JSONObject items = body.getJSONObject("items");
	        JSONArray item = items.getJSONArray("item");
	        
//	        int i = 0;
//	        for(Object ob : item)
//	        {
//	        	System.out.println( (++i)+"번째 반복 중");
//	        	ob.
//	        }
	        //item.getJSONObject(0).getString("");
	        
	        for(int i=0; i<item.length(); i++)
	        {
	        	System.out.println(item.getJSONObject(i).getString("category"));
	        }
	        
	        
		} 
		catch (Exception e) 
		{
			System.out.println("apicall 트라이캐치 에러에러");
			e.printStackTrace();
		}
    }
	
}
