package com.springproject.domain;

public class WeatherDayByDate {
	private String date; //발표일자 0000000 + 발표시각 0000
	private String POP;//강수확률
	private String PTY;//강수형태
	private String PCP;//1시간 강수량
	private String REH;//습도
	private String SNO;//1시간신적설
	private String SKY;//하늘상태
	private String TMP;//1시간기온
	private String TMN;//일최저기온
	private String TMX;//일최고기온
	private String WSD;//풍속
	private String fcstDate; //예측 일자
	private String fcstTime;//예보 시각
	public WeatherDayByDate() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WeatherDayByDate(String date, String pOP, String pTY, String pCP, String rEH, String sNO, String sKY,
			String tMP, String tMN, String tMX, String wSD, String fcstDate, String fcstTime) {
		super();
		this.date = date;
		POP = pOP;
		PTY = pTY;
		PCP = pCP;
		REH = rEH;
		SNO = sNO;
		SKY = sKY;
		TMP = tMP;
		TMN = tMN;
		TMX = tMX;
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

	public String getPOP() {
		return POP;
	}
	public void setPOP(String pOP) {
		POP = pOP;
	}
	public String getPTY() {
		return PTY;
	}
	public void setPTY(String pTY) {
		PTY = pTY;
	}
	public String getPCP() {
		return PCP;
	}
	public void setPCP(String pCP) {
		PCP = pCP;
	}
	public String getREH() {
		return REH;
	}
	public void setREH(String rEH) {
		REH = rEH;
	}
	public String getSNO() {
		return SNO;
	}
	public void setSNO(String sNO) {
		SNO = sNO;
	}
	public String getSKY() {
		return SKY;
	}
	public void setSKY(String sKY) {
		SKY = sKY;
	}
	public String getTMP() {
		return TMP;
	}
	public void setTMP(String tMP) {
		TMP = tMP;
	}
	public String getTMN() {
		return TMN;
	}
	public void setTMN(String tMN) {
		TMN = tMN;
	}
	public String getTMX() {
		return TMX;
	}
	public void setTMX(String tMX) {
		TMX = tMX;
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
