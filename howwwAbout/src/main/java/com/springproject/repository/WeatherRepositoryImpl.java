package com.springproject.repository;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.springproject.domain.WeatherOfWeek;

@Repository
public class WeatherRepositoryImpl implements WeatherRepository
{
	private JdbcTemplate template;
	@Autowired
	public void setJdbcTemplate(DataSource dataSource)
	{
		this.template = new JdbcTemplate(dataSource);
	}
	String sql;


	@Override
	public void setWeatherOfWeek(WeatherOfWeek ww) 
	{
		System.out.println("WeatherRepositoryImpl setWeatherOfWeek in");
		
		sql = "insert into WeatherOfWeek values(?,?,?,?,?,?,?,?)";
		template.update(sql, ww.getBasedate(), ww.getWf4Am(), ww.getWf5Am(), ww.getWf6Am(), ww.getWf7Am(), ww.getWf8(), ww.getWf9(), ww.getWf10());
	}


	@Override
	public WeatherOfWeek getWeatherOfWeek() 
	{
		System.out.println("WeatherRepositoryImpl getWeatherOfWeek in");
		sql = "select * from WeatherOfWeek";
		WeatherOfWeek weatherOfWeek = template.queryForObject(sql, new WeatherOfWeekRowMapper());
		return weatherOfWeek;
	}
	
	
	

}
