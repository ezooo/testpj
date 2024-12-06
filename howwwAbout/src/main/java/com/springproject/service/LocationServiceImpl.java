package com.springproject.service;

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
	public Location getOneLocation(String title) 
	{
		//System.out.println("LocationServiceImpl getOneLocation in");
		Location location = locationRepository.getOneLocation(title);
		return location;
	}

	@Override
	public List<Location> getLocationOfCategory(String category) 
	{
		System.out.println("LocationServiceImpl getLocationOfCategory in");
		List<Location> locations = locationRepository.getLocationOfCategory(category);
		return locations;
	}

}
