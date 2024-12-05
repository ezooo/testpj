package com.springproject.repository;

import org.json.JSONObject;

import com.springproject.domain.Location;

public interface LocationRepository 
{
	void addLocationAPI(JSONObject location);
}
