package com.springproject.domain;

public class Weather 
{	//T1H(기온), UUU(동서바람성분), VVV(남북바람성분), VEC(풍향), WSD(풍속), SKY(하늘상태), LGT(낙뢰), PTY(강수형태), RN1(1시간 강수량), REH(상대습도)
	//RN1, T1H, UUU, VVV, WSD 실수로 제공
	
	String baseDate;	// 발표일자
	String fcstDate;	// 예측일자 YYYYMMDD
	String fcstTime;	//예측시간 HH24M
	String T1H;	//기온
	String SKY;	//하늘상태
	int nx;
	int ny;
	
	public String getBaseDate() {
		return baseDate;
	}
	public void setBaseDate(String baseDate) {
		this.baseDate = baseDate;
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
	public String getT1H() {
		return T1H;
	}
	public void setT1H(String t1h) {
		T1H = t1h;
	}
	public String getSKY() {
		return SKY;
	}
	public void setSKY(String sKY) {
		SKY = sKY;
	}
	public int getNx() {
		return nx;
	}
	public void setNx(int nx) {
		this.nx = nx;
	}
	public int getNy() {
		return ny;
	}
	public void setNy(int ny) {
		this.ny = ny;
	}
	
	
}
