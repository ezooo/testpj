package com.springproject.repository;

import java.util.List;

import org.json.JSONObject;

import com.springproject.domain.Location;

public interface LocationRepository 
{
	void addLocationAPI(JSONObject location);
	List<String> getAlltitle();
	List<Location> getAllLocation();
	Location getOneLocation(String title);
	List<Location> getLocationOfCategory(String category);
	List<Location> getAllCategory();
	Location createLocation();
}
