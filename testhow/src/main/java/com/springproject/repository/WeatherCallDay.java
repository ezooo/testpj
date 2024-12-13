package com.springproject.repository;
import java.io.InputStreamReader;
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
import org.springframework.stereotype.Repository;

import com.springproject.domain.WeatherDay;
import com.springproject.domain.WeatherDayByDate;

import java.io.BufferedReader;
import java.io.IOException;
@Repository
public class WeatherCallDay {
	static WeatherDay weather;
	static List<WeatherDay>  weatherDayList= new ArrayList<>();
    public static List<WeatherDay> mainCall() throws IOException {
    	System.out.println("WeatherCallDay의 mainCall 실행");
    	StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=WrmKloGjwEzpg3VwrKAyuT8RGfFrbTdSVvzthl5e%2FWL6P5aC2uwyAIssv9jYkPGVewrBzFqweTj%2FNURFqIpr0g%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("XML", "UTF-8")); /*요청자료형식(XML/JSON) Default: XML*/
        urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode("20241210", "UTF-8")); /*‘21년 6월 28일발표*/
        urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode("0600", "UTF-8")); /*05시 발표*/
        urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode("55", "UTF-8")); /*예보지점의 X 좌표값*/
        urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode("127", "UTF-8")); /*예보지점의 Y 좌표값*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());
    
         JSONTokener tokener = new JSONTokener(sb.toString());
         JSONObject object = new JSONObject(tokener);
         System.out.println(object.toString());
         
         JSONArray arr = object.getJSONArray("item");
         for(int i=0; i<arr.length(); i++) {
         	JSONObject temp = (JSONObject)arr.get(i);
         	weather.setBase_date(temp.getString("base_date"));
         	weather.setBase_time(temp.getString("base_time"));
         	weather.setCategory(temp.getString("category"));
         	weather.setFcstDate(temp.getString("fcstDate"));
         	weather.setFcstTime(temp.getString("fcstTime"));
         	weather.setFcstValue(temp.getString("fcstValue"));
         	weather.setNx(temp.getString("nx"));
         	weather.setNy(temp.getString("ny"));
         	weatherDayList.add(weather);
         	System.out.println("weatherDay add "+i+"번 완료");
         }
         return weatherDayList;
     }
    
    public WeatherDayByDate getWeatherByDate(){
    	System.out.println("WeatherCallDay 의 getWeatherByDate 실행");
    	
    	WeatherDayByDate wd = new WeatherDayByDate();
    	
    	Map<String,Map<String,String>> weatherByDate = new HashMap<>();
    	for(int i = 0; i <weatherDayList.size(); i++) 
    	{
    		
    		WeatherDay weatherDay = weatherDayList.get(i);
    		String category = weatherDay.getCategory();	//lgt, pty ...
    		String fcstValue = weatherDay.getFcstValue();	//값
    		String fcstDate = weatherDay.getFcstDate();	//조회할 날짜
    		String fcstTime = weatherDay.getFcstTime();	//조회할 시간
    		String nx=weatherDay.getNx();	//좌표
    		String ny=weatherDay.getNy();
    		
    		wd.setFcstDate(fcstDate);
    		
    		Map<String,String> weatherCategory = new HashMap<>();
    		
    		weatherCategory.put(category, fcstValue);
    		weatherCategory.put("fcstDate",fcstDate);
    		weatherCategory.put("fcstTime",fcstTime);
    		weatherCategory.put("nx",nx);
    		weatherCategory.put("ny",ny);
    		String date = weather.getBase_date()+weather.getBase_time();
    		weatherByDate.put(date, weatherCategory);
    	}
    		
    		List<WeatherDayByDate> weatherDayByDateList = new ArrayList<>();
    		
    		Object[] mapKeys = weatherByDate.keySet().toArray(); 
    		
    		for(int i=0; i<mapKeys.length; i++) {
    		String keyDate = (String)mapKeys[i];
    		Map<String, String> innerMap = weatherByDate.get(keyDate);

    		WeatherDayByDate weatherDayByDate = new WeatherDayByDate();
    		weatherDayByDate.setDate(keyDate);
    		weatherDayByDate.setPOP(innerMap.get("POP"));
    		weatherDayByDate.setPTY(innerMap.get("PTY"));
    		weatherDayByDate.setPCP(innerMap.get("PCP"));
    		weatherDayByDate.setREH(innerMap.get("REH"));
    		weatherDayByDate.setSNO(innerMap.get("SNO"));
    		weatherDayByDate.setSKY(innerMap.get("SKY"));
    		weatherDayByDate.setTMP(innerMap.get("TMP"));
    		weatherDayByDate.setTMN(innerMap.get("TMN"));
    		weatherDayByDate.setTMX(innerMap.get("TMX"));
    		weatherDayByDate.setWSD(innerMap.get("WSD"));
    		weatherDayByDate.setFcstDate(innerMap.get("fcstDate"));
    		weatherDayByDate.setFcstTime(innerMap.get("fcstTime"));
    		weatherDayByDateList.add(weatherDayByDate);
    		}
    	return weatherDayByDateList;
    }
}