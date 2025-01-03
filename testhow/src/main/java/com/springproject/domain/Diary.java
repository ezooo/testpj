package com.springproject.domain;

import org.springframework.web.multipart.MultipartFile;

public class Diary 
{
	private long diaryId;
	private String userId;	//이 아이디 가진 다이어리 찾아줘야 함
	private String visit_date;
	private String visit_diary;
	private MultipartFile picture;
	private String filename;

	public Diary(String userId, String visit_date, String visit_diary) 
	{
		super();
		System.out.println("다이어리 생성자");
		this.userId = userId;
		this.visit_date = visit_date;
		
		System.out.println("다이어리 생성 완료 ! 다이어리 아이디는 : "+diaryId);
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
	
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public long getDiaryId() {
		return diaryId;
	}

	public void setDiaryId(long diaryId) {
		this.diaryId = diaryId;
	}
	
}
