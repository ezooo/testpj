package com.springproject.domain;

public class WeatherWeekByDate {
	private String date; //발표일자 0000000 + 발표시각 0000
	
	private String regId; //지역코드
	private String rnStAm;//오전 강수 확률
	private String rnStPm;//오후 강수 확률

    private String wfAm;//오전 날씨
    private String wfPm;//오후 날씨

    private String taMin;//예상최저기온(℃)
    private String taMax;//예상최고기온(℃)
	public WeatherWeekByDate() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public WeatherWeekByDate(String date, String regId, String rnStAm, String rnStPm, String wfAm, String wfPm,
			String taMin, String taMax) {
		super();
		this.date = date;
		this.regId = regId;
		this.rnStAm = rnStAm;
		this.rnStPm = rnStPm;
		this.wfAm = wfAm;
		this.wfPm = wfPm;
		this.taMin = taMin;
		this.taMax = taMax;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public String getRnStAm() {
		return rnStAm;
	}
	public void setRnStAm(String rnStAm) {
		this.rnStAm = rnStAm;
	}
	public String getRnStPm() {
		return rnStPm;
	}
	public void setRnStPm(String rnStPm) {
		this.rnStPm = rnStPm;
	}
	public String getWfAm() {
		return wfAm;
	}
	public void setWfAm(String wfAm) {
		this.wfAm = wfAm;
	}
	public String getWfPm() {
		return wfPm;
	}
	public void setWfPm(String wfPm) {
		this.wfPm = wfPm;
	}
	public String getTaMin() {
		return taMin;
	}
	public void setTaMin(String taMin) {
		this.taMin = taMin;
	}
	public String getTaMax() {
		return taMax;
	}
	public void setTaMax(String taMax) {
		this.taMax = taMax;
	}

	
}
