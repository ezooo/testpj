package com.springproject.domain;

import org.springframework.web.multipart.MultipartFile;

public class DiaryImage 
{
	private long diaryId;
	private MultipartFile image;
	private String imagename;
	
	public long getDiaryId() {
		return diaryId;
	}
	public void setDiaryId(long diaryId) {
		this.diaryId = diaryId;
	}
	public MultipartFile getImage() {
		return image;
	}
	public void setImage(MultipartFile image) {
		this.image = image;
	}
	public String getImagename() {
		return imagename;
	}
	public void setImagename(String imagename) {
		this.imagename = imagename;
	}
}
