package com.springproject.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.springproject.domain.Location;

public class LocationTitleRowMapper implements RowMapper<String>
{
	@Override
	public String mapRow(ResultSet rs, int rowNum) throws SQLException 
	{
		
		return rs.getString(1);
	}
}
