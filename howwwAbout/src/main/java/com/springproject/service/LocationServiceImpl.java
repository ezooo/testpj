package com.springproject.service;

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

}
