package com.springproject.repository;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springproject.domain.WeatherNow;

import java.io.BufferedReader;
import java.io.IOException;
@Repository
public class WeatherCallNow {
	
	static WeatherNow weather;
	
	static List<WeatherNow>  weatherNowList= new ArrayList<>();
	
    public static List<WeatherNow> mainCall() throws IOException {
    	 StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtFcst"); /*URL*/
         urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=WrmKloGjwEzpg3VwrKAyuT8RGfFrbTdSVvzthl5e%2FWL6P5aC2uwyAIssv9jYkPGVewrBzFqweTj%2FNURFqIpr0g%3D%3D"); /*Service Key*/
         urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
         urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8")); /*한 페이지 결과 수*/
         urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*요청자료형식(XML/JSON) Default: XML*/
         urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode("20241210", "UTF-8")); /*‘21년 6월 28일 발표*/
         urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode("0630", "UTF-8")); /*06시30분 발표(30분 단위)*/
         urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode("55", "UTF-8")); /*예보지점 X 좌표값*/
         urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode("127", "UTF-8")); /*예보지점 Y 좌표값*/
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
         	weatherNowList.add(weather);
         	System.out.println("weatherNow add "+i+"번 완료");
         }
         return weatherNowList;
     }
}