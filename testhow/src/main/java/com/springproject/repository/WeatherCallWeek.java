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
import org.springframework.stereotype.Repository;

import com.springproject.domain.WeatherDay;
import com.springproject.domain.WeatherWeek;

import java.io.BufferedReader;
import java.io.IOException;
@Repository
public class WeatherCallWeek {
	static WeatherWeek weather;
	static List<WeatherWeek>  weatherWeekList= new ArrayList<>();
    public static List<WeatherWeek> mainCall() throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/MidFcstInfoService/getMidLandFcst"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=WrmKloGjwEzpg3VwrKAyuT8RGfFrbTdSVvzthl5e%2FWL6P5aC2uwyAIssv9jYkPGVewrBzFqweTj%2FNURFqIpr0g%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*요청자료형식(XML/JSON)Default: XML*/
        urlBuilder.append("&" + URLEncoder.encode("regId","UTF-8") + "=" + URLEncoder.encode("11B00000", "UTF-8")); /*11B0000 서울, 인천, 경기도 11D10000 등 (활용가이드 하단 참고자료 참조)*/
        urlBuilder.append("&" + URLEncoder.encode("tmFc","UTF-8") + "=" + URLEncoder.encode("202412100600", "UTF-8")); /*-일 2회(06:00,18:00)회 생성 되며 발표시각을 입력 YYYYMMDD0600(1800)-최근 24시간 자료만 제공*/
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
         	weather.setTmFc(temp.getString("tmFc"));
         	weather.setRegId(temp.getString("regId"));
         	weather.setRnSt4Am(temp.getString("rnSt4Am"));
         	weather.setRnSt4Pm(temp.getString("rnSt4Pm"));
         	weather.setRnSt5Am(temp.getString("rnSt5Am"));
         	weather.setRnSt5Pm(temp.getString("rnSt5Pm"));
         	weather.setRnSt6Am(temp.getString("rnSt6Am"));
         	weather.setRnSt6Pm(temp.getString("rnSt6Pm"));
         	weather.setRnSt7Am(temp.getString("rnSt7Am"));
         	weather.setRnSt7Pm(temp.getString("rnSt7Pm"));
         	weather.setRnSt8(temp.getString("rnSt8"));
         	weather.setRnSt9(temp.getString("rnSt9"));
         	weather.setRnSt10(temp.getString("rnSt10"));
         	weather.setWf4Am(temp.getString("wf4Am"));
         	weather.setWf4Pm(temp.getString("wf4Pm"));
         	weather.setWf5Am(temp.getString("wf5Am"));
         	weather.setWf5Pm(temp.getString("wf5Pm"));
         	weather.setWf6Am(temp.getString("wf6Am"));
         	weather.setWf6Pm(temp.getString("wf6Pm"));
         	weather.setWf7Am(temp.getString("wf7Am"));
         	weather.setWf7Pm(temp.getString("wf7Pm"));
         	weather.setWf8(temp.getString("wf8"));
         	weather.setWf9(temp.getString("wf9"));
         	weather.setWf10(temp.getString("wf10"));
         	weather.setTaMin4(temp.getString("taMin4"));
         	weather.setTaMax4(temp.getString("taMax4"));
         	weather.setTaMin5(temp.getString("taMin5"));
         	weather.setTaMax5(temp.getString("taMax5"));
         	weather.setTaMin6(temp.getString("taMin6"));
         	weather.setTaMax6(temp.getString("taMax6"));
         	weather.setTaMin7(temp.getString("taMin7"));
         	weather.setTaMax7(temp.getString("taMax7"));
         	weather.setTaMin8(temp.getString("taMin8"));
         	weather.setTaMax8(temp.getString("taMax8"));
         	weather.setTaMin9(temp.getString("taMin9"));
         	weather.setTaMax9(temp.getString("taMax9"));
         	weather.setTaMin10(temp.getString("taMin10"));
         	weather.setTaMax10(temp.getString("taMax10"));

         	weatherWeekList.add(weather);
         	System.out.println("weatherWeek add "+i+"번 완료");
        }
        return weatherWeekList;
    }
}