package com.springproject.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springproject.domain.WeatherDay;
import com.springproject.domain.WeatherNow;
import com.springproject.domain.WeatherWeek;
import com.springproject.repository.WeatherCallDay;
import com.springproject.repository.WeatherCallNow;
import com.springproject.repository.WeatherCallWeek;

@Service
public class WeatherService {
	@Autowired
	private WeatherCallNow weatherCallNow;
	
	@Autowired
	private WeatherCallDay weatherCallDay;
	
	@Autowired
	private WeatherCallWeek weatherCallWeek;
	
	
	public List<WeatherNow> getWeatherNow() throws IOException{
		List<WeatherNow> weatherNowList=weatherCallNow.mainCall();
		return weatherNowList;
	}
	public List<WeatherDay> getWeatherDay() throws IOException{
		List<WeatherDay> weatherDayList=weatherCallDay.mainCall();
		return weatherDayList;
	}
	public List<WeatherWeek> getWeatherWeek() throws IOException{
		List<WeatherWeek> weatherWeekList=weatherCallWeek.mainCall();
		return weatherWeekList;
	}
	
	public Map<String, Map<String,String>> getWeatherNowByDate(WeatherNow weatherNow){
		
		return null;
	}
	public Map<String, Map<String,String>> getWeatherDayByDate(WeatherDay weatherDay){
		
		return null;
	}
	public Map<String, Map<String,String>> getWeatherWeekByDate(WeatherWeek weatherWeek){
		
		return null;
	}
}
