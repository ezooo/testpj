package com.springproject.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springproject.domain.WeatherDay;
import com.springproject.domain.WeatherNow;
import com.springproject.domain.WeatherWeek;
import com.springproject.service.WeatherService;
@RequestMapping("/weather")
@Controller
public class WeatherController {
	@Autowired
	private WeatherService weatherService;
	@GetMapping("/jsonCall")
	public String requestWeatherCall(Model model) throws IOException {
		List<WeatherNow> weatherNowList = weatherService.getWeatherNow();
		List<WeatherDay> weatherDayList = weatherService.getWeatherDay();
		List<WeatherWeek> weatherWeekList = weatherService.getWeatherWeek();
		model.addAttribute(weatherNowList);
		model.addAttribute(weatherDayList);
		model.addAttribute(weatherWeekList);
		return "Weather";
	}
	@GetMapping("/call")
	public String requestWeatherByDate(Model model) {
		
		weatherService.getWeatherByDate();
		
		return "Weather";
	}
}
