package com.springproject.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.springproject.domain.Location;

public class LocationRowMapper implements RowMapper<Location>
{
	@Override
	public Location mapRow(ResultSet rs, int rowNum) throws SQLException 
	{
		Location lt = new Location();
		lt.setData_title(rs.getString(1));
		lt.setUser_address(rs.getString(2));
		lt.setLatitude(rs.getString(3));
		lt.setLongitude(rs.getString(4));
		lt.setInsttnm(rs.getString(5));
		lt.setCategory_name1(rs.getString(6));
		lt.setCategory_name2(rs.getString(7));
		lt.setData_content(rs.getString(8));
		lt.setTelno(rs.getString(9));
		lt.setFileurl1(rs.getString(10));
		lt.setFileurl2(rs.getString(11));
		lt.setFileurl3(rs.getString(12));
		lt.setFileurl4(rs.getString(13));
		lt.setNum(rs.getInt(14));
		
		return lt;
	}

}
