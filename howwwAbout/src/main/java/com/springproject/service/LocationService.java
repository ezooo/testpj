package com.springproject.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.springproject.domain.Location;

public interface LocationService 
{
	void addLocationAPI(JSONObject location);
	List<String> getAlltitle();
	List<Location> getAllLocation();
	Location getOneLocation(int num);
	List<Location> getLocationOfCategory(String category);
	List<Location> getAllCategory();
	void createLocation(Location location);
	Location findLocation(String[] find);
	void submitUpdateLocation(Location location);
	void deleteLocation(String lat, String log);
	List<Location> findLocationByTitle(String title);
	String[] getAPIContents(String jsonaddr);
	List<String> getAlladdr();
	List<String> getAllArea();
	List<Location> getLocationOfArea(String area);
//	Location searchOneLocation(String title, String address);
	int searchOneLocationNum(String title, String address);
}
