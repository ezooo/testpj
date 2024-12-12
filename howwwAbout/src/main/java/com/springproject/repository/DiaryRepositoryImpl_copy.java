package com.springproject.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.springproject.domain.Diary;
import com.springproject.domain.DiaryImage;
import com.springproject.domain.Member;
import com.springproject.exception.DiaryIdException;

//@Repository	//이거 안하니까 객체 생성 못한다고 화냄
public class DiaryRepositoryImpl_copy implements DiaryRepository
{
	//private Map<String, Diary> diaryList;	// 목적 : 멤버 아이디 별로 각각 다이어리 만들어서 담아주기
	//private Map<String, List<Diary>> diaryIdList;	//여기는 멤버 아이디만 담고, 멤버별 다이어리는 리스트에 담음
	private List<Diary> diaryList = new ArrayList<Diary>();
	List<Diary> myDiary;	// 내 아이디에 해당하는 다이어리만 가져와서 담을 객체
	
	//db 연결을 위한 설정추가
	private JdbcTemplate template;
	@Autowired
	public void setJdbctemplate(DataSource dataSource)
	{
		this.template = new JdbcTemplate(dataSource);
	}
	//db설정end
	
	public DiaryRepositoryImpl_copy()
	{
		System.out.println("DiaryRepositoryImpl 생성자 함수 호출 - 다이어리 담을 맵 만들기");
		//diaryList = new HashMap<String, Diary>();	
		//diaryIdList = new HashMap<String, List<Diary>>();
		Diary diary1 = new Diary("11", "11", "11");
		
		diaryList.add(diary1);
	}
	
//	@Override
//	public Diary create(Diary diary) //다이어리 객체 를 받아와서
//	{
//		System.out.println("DiaryRepositoryImpl create in");
////		if(diaryIdList.keySet().contains(diary.getDiaryId()))
////		{			//리스트에 담긴 모든 키 값 가져와서 (전체 다이어리 아이디)
////								//멤버의 다이어리 아이디 있는지 확인
////			throw new IllegalArgumentException(String.format("다이어리 id 가 존재합니다.", diary.getDiaryId()));
////		}
////		//다이어리 아이디 없으면 리스트에 다이어리 추가
////		diaryIdList.put(diary.getDiaryId(), diary);
////		System.out.println("diaryList 에 새 다이어리 추가완료 "+diaryIdList);
//		return diary;
//	}

	@Override
	public void setNewDiary(Diary diary) 
	{	//다이어리 등록하기
		System.out.println("DiaryRepositoryImpl setNewDiary in");
		diaryList.add(diary);
		System.out.println("diaryList 저장완료 "+diaryList);
	}

	@Override
	public List<Diary> getAllDiary() 
	{
		System.out.println("DiaryRepositoryImpl getAllDiary in");
		
		
		return diaryList;
	}

	@Override
	public List<Diary> getMyDiary(String userId) 
	{
		System.out.println("DiaryRepositoryImpl getMyDiary in");
		// 회원 아이디에 맞는 다이어리만 가져와야 함
		// 지금 로그인 한 회원 아이디 받아오는 법 ?
		myDiary = new ArrayList<Diary>();
		for(Diary diary : diaryList)
		{
			if(diary.getUserId().equals(userId))
			{
				System.out.println("내 다이어리 입니다.");
				myDiary.add(diary);
			}
		}
		System.out.println("내 다이어리 찾기 완료");
		return myDiary;
	}

	@Override
	public Diary getDiaryById(long diaryId)
	{
		System.out.println("DiaryRepositoryImpl getDiaryById in");
		Diary diaryInfo = null;		//원하는 다이어리 찾으면 여기에 정보담기
		
		for(Diary diary : diaryList)
		{
			System.out.println("다이어리 아이디 가졍모 :"+diary.getDiaryId());
			//String di = String.valueOf(diary.getDiaryId());
			if(diary!=null && diary.getDiaryId()==diaryId)
			{
				System.out.println("다이어리 안 비었고 매칭 완료");
				diaryInfo = diary;
				break;
			}
		}
		if(diaryInfo == null)
		{
			System.out.println("다이어리 못 찾아서 정보가 비었다");
			throw new DiaryIdException();
		}
		System.out.println("getDiaryById 동작 완료");
		return diaryInfo;
	}

	@Override
	public void setUpdateDiary(Diary diary) 
	{
		System.out.println("DiaryRepositoryImpl setUpdateDiary in");
	}

	@Override
	public void deleteDiary(long diaryId) 
	{
		System.out.println("DiaryRepositoryImpl deleteDiary in");
		
	}

	@Override
	public Diary getOnediary(Long diaryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void uploadImage(DiaryImage diaryImage) {
		// TODO Auto-generated method stub
		
	}
	
	
}
