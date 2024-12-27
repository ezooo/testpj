package com.springproject.repository;

import com.springproject.domain.WeatherOfWeek;

public interface WeatherRepository
{
	public void setWeatherOfWeek(WeatherOfWeek ww);
	public WeatherOfWeek getWeatherOfWeek();
	public String getRegCode(String areaname);
}
