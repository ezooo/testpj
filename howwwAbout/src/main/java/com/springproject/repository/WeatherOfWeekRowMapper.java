package com.springproject.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.springproject.domain.WeatherOfWeek;

public class WeatherOfWeekRowMapper implements RowMapper<WeatherOfWeek>
{

	@Override
	public WeatherOfWeek mapRow(ResultSet rs, int rowNum) throws SQLException 
	{
		WeatherOfWeek wow = new WeatherOfWeek();
		wow.setBasedate(rs.getString(1));
		wow.setWf4Am(rs.getString(2));
		wow.setWf5Am(rs.getString(3));
		wow.setWf6Am(rs.getString(4));
		wow.setWf7Am(rs.getString(5));
		wow.setWf8(rs.getString(6));
		wow.setWf9(rs.getString(7));
		wow.setWf10(rs.getString(8));
		
		return wow;
	}


}
