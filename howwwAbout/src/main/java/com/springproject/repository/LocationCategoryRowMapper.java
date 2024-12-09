package com.springproject.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.springproject.domain.Location;

public class LocationCategoryRowMapper implements RowMapper<Location>
{
	@Override
	public Location mapRow(ResultSet rs, int rowNum) throws SQLException 
	{
		Location lt = new Location();
		lt.setCategory_name1(rs.getString(1));
		lt.setFileurl1(rs.getString(2));
		return lt;
	}
}
