package com.springproject.domain;

public class WeatherDay {
	private String base_date; //발표일자 00000000
	private String base_time; //발표시각 0000
	private String category; //자료구분문자
	private String fcstDate; //예측 일자
	private String fcstTime;//예보 시각
	private String fcstValue;//예보 값
	private String nx; //예보지점 X좌표
	private String ny; //예보지점 Y좌표
	public WeatherDay(String base_date, String base_time, String category, String fcstDate, String fcstTime,
			String fcstValue, String nx, String ny) {
		super();
		this.base_date = base_date;
		this.base_time = base_time;
		this.category = category;
		this.fcstDate = fcstDate;
		this.fcstTime = fcstTime;
		this.fcstValue = fcstValue;
		this.nx = nx;
		this.ny = ny;
	}
	public WeatherDay() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getBase_date() {
		return base_date;
	}
	public void setBase_date(String base_date) {
		this.base_date = base_date;
	}
	public String getBase_time() {
		return base_time;
	}
	public void setBase_time(String base_time) {
		this.base_time = base_time;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getFcstDate() {
		return fcstDate;
	}
	public void setFcstDate(String fcstDate) {
		this.fcstDate = fcstDate;
	}
	public String getFcstTime() {
		return fcstTime;
	}
	public void setFcstTime(String fcstTime) {
		this.fcstTime = fcstTime;
	}
	public String getFcstValue() {
		return fcstValue;
	}
	public void setFcstValue(String fcstValue) {
		this.fcstValue = fcstValue;
	}
	public String getNx() {
		return nx;
	}
	public void setNx(String nx) {
		this.nx = nx;
	}
	public String getNy() {
		return ny;
	}
	public void setNy(String ny) {
		this.ny = ny;
	}
	
}
