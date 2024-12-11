package com.springproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springproject.domain.Diary;
import com.springproject.repository.DiaryRepository;

@Service	//저는 서비스에요
public class DiaryServiceImpl implements DiaryService
{
	@Autowired
	private DiaryRepository diaryRepository;

	@Override
	public void setNewDiary(Diary diary) 
	{
		System.out.println("DiaryServiceImpl - setNewDiary in - 레파지토리 함수 호출");
		diaryRepository.setNewDiary(diary);
	}

	@Override
	public List<Diary> getAllDiary() 
	{
		System.out.println("DiaryServiceImpl - getAllDiary in - 레파지토리 함수 호출");
		return diaryRepository.getAllDiary();
	}

	@Override
	public List<Diary> getMyDiary(String userId) 
	{
		System.out.println("DiaryServiceImpl - getMyDiary in - 레파지토리 함수 호출");
		return diaryRepository.getMyDiary(userId);
	}

	@Override
	public Diary getDiaryById(long diaryId) 
	{
		System.out.println("DiaryServiceImpl - getDiaryById in - 레파지토리 함수 호출");
		return diaryRepository.getDiaryById(diaryId);
	}

	@Override
	public void setUpdateDiary(Diary diary) 
	{
		System.out.println("DiaryServiceImpl - setUpdateDiary in - 레파지토리 함수 호출");
		diaryRepository.setUpdateDiary(diary);
	}

	@Override
	public void deleteDiary(long diaryId) 
	{
		diaryRepository.deleteDiary(diaryId);
	}

	@Override
	public Diary getOnediary(Long diaryId) 
	{
		return diaryRepository.getOnediary(diaryId);
	}
}
