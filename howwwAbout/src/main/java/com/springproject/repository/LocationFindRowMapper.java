package com.springproject.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.springproject.domain.Location;

public class LocationFindRowMapper implements RowMapper<Location>
{
	@Override
	public Location mapRow(ResultSet rs, int rowNum) throws SQLException 
	{
		Location lt = new Location();
		lt.setData_title(rs.getString(1));
		lt.setUser_address(rs.getString(2));
		
		return lt;
	}
}
