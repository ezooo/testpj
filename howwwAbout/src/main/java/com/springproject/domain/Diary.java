package com.springproject.domain;

import org.springframework.web.multipart.MultipartFile;

public class Diary 
{
	//String diaryId;	//이 아이디 가지고 멤버의 다이어리 찾아줘야 함
	private String userId;	//이 아이디 가진 다이어리 찾아줘야 함
	private String visit_date;
	private String visit_diary;
	private MultipartFile picture;
	
	public Diary(String userId, String visit_date, String visit_diary) 
	{
		super();
		System.out.println("다이어리 생성자");
		this.userId = userId;
		this.visit_date = visit_date;
		this.visit_diary = visit_diary;
	}
	
	public Diary() 
	{
		super();
		System.out.println("다이어리 기본생성자");
	}



	public String getVisit_date() {
		return visit_date;
	}
	public void setVisit_date(String visit_date) {
		this.visit_date = visit_date;
	}
	public String getVisit_diary() {
		return visit_diary;
	}
	public void setVisit_diary(String visit_diary) {
		this.visit_diary = visit_diary;
	}
	public MultipartFile getPicture() {
		return picture;
	}
	public void setPicture(MultipartFile picture) {
		this.picture = picture;
	}
	public String getuserId() {
		return userId;
	}
	public void setuserId(String userId) {
		this.userId = userId;
	}
}
