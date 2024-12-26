package com.springproject.domain;



import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherOfWeek 
{
	private String basedate;
	
	@JsonProperty("wf4Am")
	private String wf4Am;
	
	@JsonProperty("wf5Am")
	private String wf5Am;
	
	@JsonProperty("wf6Am")
	private String wf6Am;
	
	@JsonProperty("wf7Am")
	private String wf7Am;
	
	@JsonProperty("wf8")
	private String wf8;
	
	@JsonProperty("wf9")
	private String wf9;
	
	@JsonProperty("wf10")
	private String wf10;


	public String getWf4Am() {
		return wf4Am;
	}

	public void setWf4Am(String wf4Am) {
		this.wf4Am = wf4Am;
	}

	public String getWf5Am() {
		return wf5Am;
	}

	public void setWf5Am(String wf5Am) {
		this.wf5Am = wf5Am;
	}

	public String getWf6Am() {
		return wf6Am;
	}

	public void setWf6Am(String wf6Am) {
		this.wf6Am = wf6Am;
	}

	public String getWf7Am() {
		return wf7Am;
	}

	public void setWf7Am(String wf7Am) {
		this.wf7Am = wf7Am;
	}

	public String getWf8() {
		return wf8;
	}

	public void setWf8(String wf8) {
		this.wf8 = wf8;
	}

	public String getWf9() {
		return wf9;
	}

	public void setWf9(String wf9) {
		this.wf9 = wf9;
	}

	public String getWf10() {
		return wf10;
	}

	public void setWf10(String wf10) {
		this.wf10 = wf10;
	}

	public String getBasedate() {
		return basedate;
	}

	public void setBasedate(String basedate) {
		this.basedate = basedate;
	}
	
//	@JsonProperty("taMin4")
//	private int taMin4;
//	@JsonProperty("taMax4")
//	private int taMax4;
//	
//	@JsonProperty("taMin5")
//	private int taMin5;
//	@JsonProperty("taMax5")
//	private int taMax5;
//	
//	@JsonProperty("taMin6")
//	private int taMin6;
//	@JsonProperty("taMax6")
//	private int taMax6;
//	
//	@JsonProperty("taMin7")
//	private int taMin7;
//	@JsonProperty("taMax7")
//	private int taMax7;
//	
//	@JsonProperty("taMin8")
//	private int taMin8;
//	@JsonProperty("taMax8")
//	private int taMax8;
//	
//	@JsonProperty("taMin9")
//	private int taMin9;
//	@JsonProperty("taMax9")
//	private int taMax9;
//	
//	@JsonProperty("taMin10")
//	private int taMin10;
//	@JsonProperty("taMax10")
//	private int taMax10;
	
	public String toJson()
	{
		Gson gson = new Gson();
		return gson.toJson(this);
	}
	
}
