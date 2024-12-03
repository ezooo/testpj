package com.springproject.repository;

import java.util.List;

import com.springproject.domain.Diary;

public interface DiaryRepository 
{
	Diary create(Diary diary);
	Diary read(String diaryId);
	void setNewDiary(Diary diary);
	List<Diary> getAllDiary();
	List<Diary> getMyDiary(String userId);
	Diary getDiaryById(long diaryId);
	void setUpdateDiary(Diary diary);
}