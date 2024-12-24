package com.springproject.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;
import org.json.JSONTokener;

public class APIFileWriting 
{
	public JSONObject returnJson(URL url) throws Exception
	{
		System.out.println("모듈화작업중.. 들어옴");

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
        
		return json;
	}
}
