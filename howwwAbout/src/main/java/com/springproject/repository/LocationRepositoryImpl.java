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
		SQL = "select * from location where category_name1=? order by data_title asc";
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
				+ " from location a group by a.category_name1"
				+ " order by a.category_name1 asc";
		List<Location> categoryList = template.query(SQL, new LocationCategoryRowMapper());
		//List<Location> categoryList = template.query
		
		return categoryList;
	}

	@Override
	public void createLocation(Location lt) 
	{
		System.out.println("LocationRepositoryImpl createLocation in");
		SQL = "insert into location values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		template.update(SQL, lt.getData_title(), lt.getUser_address(), lt.getLattitude(), lt.getLogitude(), lt.getInsttnm(), lt.getCategory_name1(), 
				lt.getCategory_name2(), lt.getData_content(), lt.getTelno(), lt.getFileurl1(), null, lt.getFileurl3(), lt.getFileurl4(), lt.getFileurl5(),
				null);
	}

	@Override
	public Location findLocation(String[] find) 
	{
		System.out.println("LocationRepositoryImpl findLocation in");
		String lat = find[0];
		System.out.println(lat);
		String log = find[1];
		
		SQL = "select * from location where lattitude=? and logitude=?";
		Location location = template.queryForObject(SQL, new LocationRowMapper(), lat, log);
		if(location != null)
		{
			System.out.println("수정할 로케이션 찾아옴");
			return location;
		}
		System.out.println("수정할 로케이션 못찾음....");
		return null;
	}

	@Override
	public void submitUpdateLocation(Location lt) 
	{
		System.out.println("LocationRepositoryImpl submitUpdateLocation in");
		SQL = "update location set data_title=?, user_address=?, lattitude=?, logitude=?, insttnm=?, category_name1=?, category_name2=?,"
				+ " data_content=?, telno=?, fileurl1=?, fileurl3=?, fileurl4=?, fileurl5=?"
				+ " where num=?";
		System.out.println("수정할 제목 : "+lt.getData_title()+", 수정할 카테고리 : "+lt.getCategory_name1()+", 수정할 넘버 : "+lt.getNum());
		template.update(SQL, lt.getData_title(), lt.getUser_address(), lt.getLattitude(), lt.getLogitude(), lt.getInsttnm(), lt.getCategory_name1(), 
				lt.getCategory_name2(), lt.getData_content(), lt.getTelno(), lt.getFileurl1(), lt.getFileurl3(), lt.getFileurl4(), lt.getFileurl5(), 
				lt.getNum());
	}

	@Override
	public void deleteLocation(String lat, String log) 
	{
		System.out.println("LocationRepositoryImpl deleteLocation in");
		
		SQL= "delete from location where lattitude=? and logitude=?";
		template.update(SQL, lat, log);
		
	}

}
