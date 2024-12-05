package com.spring.repository;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import com.spring.domain.Location;

@Repository
public class LocationRepository 
{
	List<Location> list = new ArrayList<Location>();
	
	public void setting(JSONObject location)
	{
		System.out.println("LocationRepository setting in");
		System.out.println("location : "+location.toString());
		Location lt = new Location();
		lt.setData_title(location.getString("data_title"));
		lt.setUser_address(location.getString("user_address"));
		list.add(lt);
		System.out.println("data_title"+lt.getData_title());
	}
}
