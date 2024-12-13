package com.springproject.domain;

public class WeatherWeek {
	private String base_date; //발표일자 00000000
	private String base_time; //발표시각 0000
	private String tmFc; //발표시각(날짜+시각) 000000000000
	
	private String regId; //지역코드
	private String rnSt4Am;//4일 후 오전 강수 확률
	private String rnSt4Pm;//4일 후 오후 강수 확률
	private String rnSt5Am;//5일 후 오전 강수 확률
	private String rnSt5Pm;//5일 후 오후 강수 확률
	private String rnSt6Am;//6일 후 오전 강수 확률
    private String rnSt6Pm;//6일 후 오후 강수 확률
    private String rnSt7Am;//7일 후 오전 강수 확률
    private String rnSt7Pm;//7일 후 오후 강수 확률
    private String rnSt8;//8일 후 강수 확률
    private String rnSt9;//9일 후 강수 확률
    private String rnSt10;//10일 후 강수 확률
    private String wf4Am;//4일 후 오전 날씨
    private String wf4Pm;//4일 후 오후 날씨
    private String wf5Am;//5일 후 오전 날씨
    private String wf5Pm;//5일 후 오후 날씨
    private String wf6Am;//6일 후 오전 날씨
    private String wf6Pm;//6일 후 오후 날씨
    private String wf7Am;//7일 후 오전 날씨
    private String wf7Pm;//7일 후 오후 날씨
    private String wf8;//8일 후 날씨
    private String wf9;//9일 후 날씨
    private String wf10;//10일 후 날씨
    private String taMin4;//4일 후 예상최저기온(℃)
    private String taMax4;//4일 후 예상최고기온(℃)
    private String taMin5;//5일 후 예상최저기온(℃)
    private String taMax5;//5일 후 예상최고기온(℃)
    private String taMin6;//6일 후 예상최저기온(℃)
    private String taMax6;//6일 후 예상최고기온(℃)
    private String taMin7;//7일 후 예상최저기온(℃)
    private String taMax7;//7일 후 예상최고기온(℃)
    private String taMin8;//8일 후 예상최저기온(℃)
    private String taMax8;//8일 후 예상최고기온(℃)
    private String taMin9;//9일 후 예상최저기온(℃)
    private String taMax9;//9일 후 예상최고기온(℃)
    private String taMin10;//10일 후 예상최저기온(℃)
    private String taMax10;//10일 후 예상최고기온(℃)
	public WeatherWeek(String base_date, String base_time, String tmFc, String regId, String rnSt4Am, String rnSt4Pm,
			String rnSt5Am, String rnSt5Pm, String rnSt6Am, String rnSt6Pm, String rnSt7Am, String rnSt7Pm,
			String rnSt8, String rnSt9, String rnSt10, String wf4Am, String wf4Pm, String wf5Am, String wf5Pm,
			String wf6Am, String wf6Pm, String wf7Am, String wf7Pm, String wf8, String wf9, String wf10, String taMin4,
			String taMax4, String taMin5, String taMax5, String taMin6, String taMax6, String taMin7, String taMax7,
			String taMin8, String taMax8, String taMin9, String taMax9, String taMin10, String taMax10) {
		super();
		this.base_date = base_date;
		this.base_time = base_time;
		this.tmFc = tmFc;
		this.regId = regId;
		this.rnSt4Am = rnSt4Am;
		this.rnSt4Pm = rnSt4Pm;
		this.rnSt5Am = rnSt5Am;
		this.rnSt5Pm = rnSt5Pm;
		this.rnSt6Am = rnSt6Am;
		this.rnSt6Pm = rnSt6Pm;
		this.rnSt7Am = rnSt7Am;
		this.rnSt7Pm = rnSt7Pm;
		this.rnSt8 = rnSt8;
		this.rnSt9 = rnSt9;
		this.rnSt10 = rnSt10;
		this.wf4Am = wf4Am;
		this.wf4Pm = wf4Pm;
		this.wf5Am = wf5Am;
		this.wf5Pm = wf5Pm;
		this.wf6Am = wf6Am;
		this.wf6Pm = wf6Pm;
		this.wf7Am = wf7Am;
		this.wf7Pm = wf7Pm;
		this.wf8 = wf8;
		this.wf9 = wf9;
		this.wf10 = wf10;
		this.taMin4 = taMin4;
		this.taMax4 = taMax4;
		this.taMin5 = taMin5;
		this.taMax5 = taMax5;
		this.taMin6 = taMin6;
		this.taMax6 = taMax6;
		this.taMin7 = taMin7;
		this.taMax7 = taMax7;
		this.taMin8 = taMin8;
		this.taMax8 = taMax8;
		this.taMin9 = taMin9;
		this.taMax9 = taMax9;
		this.taMin10 = taMin10;
		this.taMax10 = taMax10;
	}
	public WeatherWeek() {
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
	public String getTmFc() {
		return tmFc;
	}
	public void setTmFc(String tmFc) {
		this.tmFc = tmFc;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public String getRnSt4Am() {
		return rnSt4Am;
	}
	public void setRnSt4Am(String rnSt4Am) {
		this.rnSt4Am = rnSt4Am;
	}
	public String getRnSt4Pm() {
		return rnSt4Pm;
	}
	public void setRnSt4Pm(String rnSt4Pm) {
		this.rnSt4Pm = rnSt4Pm;
	}
	public String getRnSt5Am() {
		return rnSt5Am;
	}
	public void setRnSt5Am(String rnSt5Am) {
		this.rnSt5Am = rnSt5Am;
	}
	public String getRnSt5Pm() {
		return rnSt5Pm;
	}
	public void setRnSt5Pm(String rnSt5Pm) {
		this.rnSt5Pm = rnSt5Pm;
	}
	public String getRnSt6Am() {
		return rnSt6Am;
	}
	public void setRnSt6Am(String rnSt6Am) {
		this.rnSt6Am = rnSt6Am;
	}
	public String getRnSt6Pm() {
		return rnSt6Pm;
	}
	public void setRnSt6Pm(String rnSt6Pm) {
		this.rnSt6Pm = rnSt6Pm;
	}
	public String getRnSt7Am() {
		return rnSt7Am;
	}
	public void setRnSt7Am(String rnSt7Am) {
		this.rnSt7Am = rnSt7Am;
	}
	public String getRnSt7Pm() {
		return rnSt7Pm;
	}
	public void setRnSt7Pm(String rnSt7Pm) {
		this.rnSt7Pm = rnSt7Pm;
	}
	public String getRnSt8() {
		return rnSt8;
	}
	public void setRnSt8(String rnSt8) {
		this.rnSt8 = rnSt8;
	}
	public String getRnSt9() {
		return rnSt9;
	}
	public void setRnSt9(String rnSt9) {
		this.rnSt9 = rnSt9;
	}
	public String getRnSt10() {
		return rnSt10;
	}
	public void setRnSt10(String rnSt10) {
		this.rnSt10 = rnSt10;
	}
	public String getWf4Am() {
		return wf4Am;
	}
	public void setWf4Am(String wf4Am) {
		this.wf4Am = wf4Am;
	}
	public String getWf4Pm() {
		return wf4Pm;
	}
	public void setWf4Pm(String wf4Pm) {
		this.wf4Pm = wf4Pm;
	}
	public String getWf5Am() {
		return wf5Am;
	}
	public void setWf5Am(String wf5Am) {
		this.wf5Am = wf5Am;
	}
	public String getWf5Pm() {
		return wf5Pm;
	}
	public void setWf5Pm(String wf5Pm) {
		this.wf5Pm = wf5Pm;
	}
	public String getWf6Am() {
		return wf6Am;
	}
	public void setWf6Am(String wf6Am) {
		this.wf6Am = wf6Am;
	}
	public String getWf6Pm() {
		return wf6Pm;
	}
	public void setWf6Pm(String wf6Pm) {
		this.wf6Pm = wf6Pm;
	}
	public String getWf7Am() {
		return wf7Am;
	}
	public void setWf7Am(String wf7Am) {
		this.wf7Am = wf7Am;
	}
	public String getWf7Pm() {
		return wf7Pm;
	}
	public void setWf7Pm(String wf7Pm) {
		this.wf7Pm = wf7Pm;
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
	public String getTaMin4() {
		return taMin4;
	}
	public void setTaMin4(String taMin4) {
		this.taMin4 = taMin4;
	}
	public String getTaMax4() {
		return taMax4;
	}
	public void setTaMax4(String taMax4) {
		this.taMax4 = taMax4;
	}
	public String getTaMin5() {
		return taMin5;
	}
	public void setTaMin5(String taMin5) {
		this.taMin5 = taMin5;
	}
	public String getTaMax5() {
		return taMax5;
	}
	public void setTaMax5(String taMax5) {
		this.taMax5 = taMax5;
	}
	public String getTaMin6() {
		return taMin6;
	}
	public void setTaMin6(String taMin6) {
		this.taMin6 = taMin6;
	}
	public String getTaMax6() {
		return taMax6;
	}
	public void setTaMax6(String taMax6) {
		this.taMax6 = taMax6;
	}
	public String getTaMin7() {
		return taMin7;
	}
	public void setTaMin7(String taMin7) {
		this.taMin7 = taMin7;
	}
	public String getTaMax7() {
		return taMax7;
	}
	public void setTaMax7(String taMax7) {
		this.taMax7 = taMax7;
	}
	public String getTaMin8() {
		return taMin8;
	}
	public void setTaMin8(String taMin8) {
		this.taMin8 = taMin8;
	}
	public String getTaMax8() {
		return taMax8;
	}
	public void setTaMax8(String taMax8) {
		this.taMax8 = taMax8;
	}
	public String getTaMin9() {
		return taMin9;
	}
	public void setTaMin9(String taMin9) {
		this.taMin9 = taMin9;
	}
	public String getTaMax9() {
		return taMax9;
	}
	public void setTaMax9(String taMax9) {
		this.taMax9 = taMax9;
	}
	public String getTaMin10() {
		return taMin10;
	}
	public void setTaMin10(String taMin10) {
		this.taMin10 = taMin10;
	}
	public String getTaMax10() {
		return taMax10;
	}
	public void setTaMax10(String taMax10) {
		this.taMax10 = taMax10;
	}

}
