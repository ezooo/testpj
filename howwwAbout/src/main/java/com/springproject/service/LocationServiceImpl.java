package com.springproject.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springproject.domain.Location;
import com.springproject.repository.LocationRepository;

@Service
public class LocationServiceImpl implements LocationService
{
	@Autowired
	LocationRepository locationRepository;
	
	@Override
	public void addLocationAPI(JSONObject location) 
	{
		System.out.println("LocationServiceImpl addLocationAPI in");
		locationRepository.addLocationAPI(location);
		
	}

	@Override
	public List<String> getAlltitle() 
	{
		List<String> titleList;
		titleList = locationRepository.getAlltitle();// TODO Auto-generated method stub
		return titleList;
	}

	@Override
	public List<Location> getAllLocation() 
	{
		//System.out.println("LocationServiceImpl getAllLocation in");
		List<Location> locations = locationRepository.getAllLocation();
		return locations;
	}

	@Override
	public Location getOneLocation(int num) 
	{
		//System.out.println("LocationServiceImpl getOneLocation in");
		Location location = locationRepository.getOneLocation(num);
		return location;
	}

	@Override
	public List<Location> getLocationOfCategory(String category) 
	{
		System.out.println("LocationServiceImpl getLocationOfCategory in");
		List<Location> locations = locationRepository.getLocationOfCategory(category);
		return locations;
	}

	@Override
	public List<Location> getAllCategory() 
	{
		System.out.println("LocationServiceImpl getAllCategory in");
		List<Location> categoryList = locationRepository.getAllCategory();
		return categoryList;
	}

	
	@Override
	public void createLocation(Location location) 
	{
		System.out.println("LocationServiceImpl createLocation in");
		locationRepository.createLocation(location);

	}

	@Override
	public Location findLocation(String[] find) 
	{
		System.out.println("LocationServiceImpl findLocation in");
		Location location = locationRepository.findLocation(find);
		return location;
	}

	@Override
	public void submitUpdateLocation(Location location) 
	{
		System.out.println("LocationServiceImpl submitUpdateLocation in");
		locationRepository.submitUpdateLocation(location);
	}

	@Override
	public void deleteLocation(String lat, String log) 
	{
		locationRepository.deleteLocation(lat, log);
	}

	@Override
	public List<Location> findLocationByTitle(String title) 
	{
		return locationRepository.findLocationByTitle(title);
	}

	@Override
	public String[] getAPIContents(String jsonaddr) 
	{
		return locationRepository.getAPIContents(jsonaddr);
	}

	@Override
	public List<String> getAlladdr() 
	{
		return locationRepository.getAlladdr();
	}

	@Override
	public List<String> getAllArea() 
	{
		return locationRepository.getAllArea();
	}

	@Override
	public List<Location> getLocationOfArea(String area) 
	{
		return locationRepository.getLocationOfArea(area);
	}

	@Override
	public int searchOneLocationNum(String title, String address) 
	{
		return locationRepository.searchOneLocationNum(title, address);
	}

}
