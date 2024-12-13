package com.springproject.domain;

public class WeatherNowByDate {
	private String date; //발표일자 0000000 + 발표시각 0000
	private String PTY;//강수형태
	private String RN1;//1시간 강수량
	private String REH;//습도
	private String SKY;//하늘상태
	private String T1H;//기온
	private String WSD;//풍속
	private String fcstDate; //예측 일자
	private String fcstTime;//예보 시각
	public WeatherNowByDate() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public WeatherNowByDate(String date, String pTY, String rN1, String rEH, String sKY, String t1h, String wSD,
			String fcstDate, String fcstTime) {
		super();
		this.date = date;
		PTY = pTY;
		RN1 = rN1;
		REH = rEH;
		SKY = sKY;
		T1H = t1h;
		WSD = wSD;
		this.fcstDate = fcstDate;
		this.fcstTime = fcstTime;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getPTY() {
		return PTY;
	}
	public void setPTY(String pTY) {
		PTY = pTY;
	}
	public String getRN1() {
		return RN1;
	}
	public void setRN1(String rN1) {
		RN1 = rN1;
	}
	public String getREH() {
		return REH;
	}
	public void setREH(String rEH) {
		REH = rEH;
	}
	public String getSKY() {
		return SKY;
	}
	public void setSKY(String sKY) {
		SKY = sKY;
	}
	public String getT1H() {
		return T1H;
	}
	public void setT1H(String t1h) {
		T1H = t1h;
	}
	public String getWSD() {
		return WSD;
	}
	public void setWSD(String wSD) {
		WSD = wSD;
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
	
}
