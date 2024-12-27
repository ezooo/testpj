package com.springproject.service;

import com.springproject.domain.WeatherOfWeek;

public interface WeatherService 
{
	public void setWeatherOfWeek(WeatherOfWeek ww);

	public WeatherOfWeek getWeatherOfWeek();

	public String getRegCode(String areaname);
}
