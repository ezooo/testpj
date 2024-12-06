package com.springproject.service;

import java.util.List;

import org.json.JSONObject;

import com.springproject.domain.Location;

public interface LocationService 
{
	void addLocationAPI(JSONObject location);
	List<String> getAlltitle();
	List<Location> getAllLocation();
	Location getOneLocation(String title);
	List<Location> getLocationOfCategory(String category);
}