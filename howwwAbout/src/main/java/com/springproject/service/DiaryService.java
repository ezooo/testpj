package com.springproject.service;

import java.util.List;

import com.springproject.domain.Diary;

public interface DiaryService 
{
	void setNewDiary(Diary diary);
	List<Diary> getAllDiary();
	List<Diary> getMyDiary(String userId);
	Diary getDiaryById(long diaryId);
	void setUpdateDiary(Diary diary);
	void deleteDiary(long diaryId);
	Diary getOnediary(Long diaryId);
	
}
