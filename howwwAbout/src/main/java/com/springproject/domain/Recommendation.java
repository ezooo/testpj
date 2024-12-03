package com.springproject.domain;

import java.util.Date;

public class Recommendation 
{
	String recommend_title;
	String recommend_content;
	Date recommend_date;
	
	public String getRecommend_title() {
		return recommend_title;
	}
	public void setRecommend_title(String recommend_title) {
		this.recommend_title = recommend_title;
	}
	public String getRecommend_content() {
		return recommend_content;
	}
	public void setRecommend_content(String recommend_content) {
		this.recommend_content = recommend_content;
	}
	public Date getRecommend_date() {
		return recommend_date;
	}
	public void setRecommend_date(Date recommend_date) {
		this.recommend_date = recommend_date;
	}
}
