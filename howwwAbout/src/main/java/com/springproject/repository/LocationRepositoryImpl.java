package com.springproject.repository;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.springproject.domain.Diary;
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

	@Override
	public List<String> getAlltitle() 
	{
		System.out.println("LocationRepositoryImpl getAlltitle in");
		
		SQL = "select data_title from location";
		List<String> titleList= template.query(SQL, new LocationTitleRowMapper());
		
		return titleList;
	}

	@Override
	public List<Location> getAllLocation() 
	{
		System.out.println("LocationRepositoryImpl getAllLocation in");
		SQL = "select * from location";
		List<Location> locations = template.query(SQL, new LocationRowMapper());
		
		return locations;
	}

	
	@Override
	public Location getOneLocation(String title) 
	{
		System.out.println("LocationRepositoryImpl getOneLocation in");
		SQL = "select * from location where data_title=?";
		Location location = template.queryForObject(SQL, new LocationRowMapper(), new Object[] {title});
		return location;
	}

	
	@Override
	public List<Location> getLocationOfCategory(String category) 
	{
		System.out.println("LocationRepositoryImpl getLocationOfCategory in");
		SQL = "select * from location where category_name1=?";
		List<Location> locations = template.query(SQL, new LocationRowMapper(), new Object[] {category});
		
		return locations;
	}

	@Override
	public List<Location> getAllCategory() 
	{
		System.out.println("LocationRepositoryImpl getAllCategory in");
		//SQL = "select distinct category_name1 from location";
		//List<String> categoryList = template.query(SQL, new LocationTitleRowMapper());
		
		SQL = "select a.category_name1, "
				+ "(select b.fileurl1 from location b where b.category_name1 = a.category_name1 LIMIT 1) as fileurl1"
				+ " from location a group by a.category_name1";
		List<Location> categoryList = template.query(SQL, new LocationCategoryRowMapper());
		//List<Location> categoryList = template.query
		
		return categoryList;
	}

	@Override
	public Location createLocation() 
	{
		// TODO Auto-generated method stub
		return null;
	}

}
