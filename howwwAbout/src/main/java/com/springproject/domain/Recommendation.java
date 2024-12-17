package com.springproject.domain;

public class Recommendation 
{
	private long recommendId;	//기본키
	private String userId;	//작성자 아이디
	private String recommendTitle;	//제목
	private String recommendContent;	//내용
	private String recommendDate;	//작성일자
	
	// 1. 생성자 만들기
	public Recommendation() 
	{
		super();
		System.out.println("Recommendation 기본생성자");
	}

	public long getRecommendId() {
		return recommendId;
	}
	public void setRecommendId(long recommendId) {
		this.recommendId = recommendId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRecommendTitle() {
		return recommendTitle;
	}
	public void setRecommendTitle(String recommendTitle) {
		this.recommendTitle = recommendTitle;
	}
	public String getRecommendContent() {
		return recommendContent;
	}
	public void setRecommendContent(String recommendContent) {
		this.recommendContent = recommendContent;
	}
	public String getRecommendDate() {
		return recommendDate;
	}
	public void setRecommendDate(String string) {
		this.recommendDate = string;
	}
	

}
