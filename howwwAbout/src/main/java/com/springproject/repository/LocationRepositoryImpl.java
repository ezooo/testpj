package com.springproject.repository;

import javax.sql.DataSource;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.springproject.domain.Location;

@Repository
public class LocationRepositoryImpl implements LocationRepository
{
	private JdbcTemplate template;
	@Autowired
	public void setJdbctemplate(DataSource dataSource)
	{
		this.template = new JdbcTemplate(dataSource);
	}
	String SQL;
	
	@Override
	public void addLocationAPI(JSONObject lt) 
	{
		System.out.println("LocationRepositoryImpl addLocationAPI in");
		SQL = "insert into location values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		template.update(SQL, lt.getString("data_title"), lt.getString("user_address"), lt.getString("lattitude"), lt.getString("logitude"), 
				lt.getString("insttnm"), lt.getString("category_name1"), lt.getString("category_name2"), lt.getString("data_content"), lt.getString("telno"), 
				lt.getString("fileurl1"), lt.getString("fileurl2"), lt.getString("fileurl3"), lt.getString("fileurl4"), lt.getString("fileurl5"));
	}

}
