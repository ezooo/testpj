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
import com.springproject.domain.Member;
import com.springproject.exception.DiaryIdException;

@Repository	
public class DiaryRepositoryImpl implements DiaryRepository
{
	private List<Diary> diaryList = new ArrayList<Diary>();
	List<Diary> myDiary;	// 내 아이디에 해당하는 다이어리만 가져와서 담을 객체
	
	//db 연결을 위한 설정추가
	private JdbcTemplate template;
	@Autowired
	public void setJdbctemplate(DataSource dataSource)
	{
		this.template = new JdbcTemplate(dataSource);
	}
	String SQL;
	//db설정end
	
	public DiaryRepositoryImpl()
	{
		System.out.println("DiaryRepositoryImpl 생성자 함수 호출");
	}
	
	@Override
	public void setNewDiary(Diary diary) 
	{	//다이어리 등록하기
		System.out.println("DiaryRepositoryImpl setNewDiary in");
		
		String SQL = "insert into diary(diaryId,userId,visit_date,visit_diary,filename) values(?,?,?,?,?)";
		template.update(SQL, diary.getDiaryId(), diary.getUserId(), diary.getVisit_date(), diary.getVisit_diary(), diary.getFilename());
		System.out.println("setNewDiary 쿼리 업데이트 완료");
	}

	@Override
	public List<Diary> getAllDiary() 
	{
		System.out.println("DiaryRepositoryImpl getAllDiary in");
		
		SQL = "select * from diary";
		List<Diary> diaries = template.query(SQL, new DiaryRowMapper());	//sql을 
		
		return diaries;
	}

	@Override
	public List<Diary> getMyDiary(String userId) 
	{
		System.out.println("DiaryRepositoryImpl getMyDiary in");
		myDiary = new ArrayList<Diary>();
		diaryList = getAllDiary();
		
		System.out.println("getMyDiary 받아온 유저 아이디 : "+userId);
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
		
		String SQL = "select count(*) from diary where diaryId=?";
		int rowCount = template.queryForObject(SQL, Integer.class, diaryId);	//레코드 갯수가 한 개 이상일 때
		if(rowCount != 0)
		{
			System.out.println("DiaryRepositoryImpl getDiaryById rowCount != 0");
			SQL = "select * from diary where diaryId=?";
			diaryInfo = template.queryForObject(SQL, new DiaryRowMapper(), new Object[] {diaryId});
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
		SQL = "select count(*) from diary where diaryId=?";
		int row = template.queryForObject(SQL, Integer.class, diary.getDiaryId());
		if(row != 0)
		{
			System.out.println("발견 !");
		}
		
		if(diary.getFilename()!=null)
		{
			System.out.println("수정할 다이어리 아이디 : "+diary.getDiaryId());
			System.out.println("setUpdateDiary 파일 이미지도 수정");
			SQL = "update diary set visit_date=?, visit_diary=?, filename=? where diaryId=?";
			System.out.println("수정할 방문일: "+diary.getVisit_date());
			template.update(SQL, diary.getVisit_date(), diary.getVisit_diary(), diary.getFilename(), diary.getDiaryId());
		}
		else
		{
			System.out.println("수정할 다이어리 아이디 : "+diary.getDiaryId());
			System.out.println("setUpdateDiary 파일 이미지 수정 안함");
			SQL = "update diary set visit_date=?, visit_diary=? where diaryId=?";
			System.out.println("수정할 방문일: "+diary.getVisit_date());
			template.update(SQL, diary.getVisit_date(), diary.getVisit_diary(), diary.getDiaryId());	
		}
	}

	@Override
	public void deleteDiary(long diaryId) 
	{
		System.out.println("DiaryRepositoryImpl deleteDiary in");
		SQL = "delete from diary where diaryId=?";
		template.update(SQL,diaryId);
	}

	@Override
	public Diary getOnediary(Long diaryId) 
	{
		System.out.println("DiaryRepositoryImpl getOnediary in");
		SQL = "select * from diary where diaryId=?";
		Diary diary = template.queryForObject(SQL, new DiaryRowMapper(), diaryId);
		return diary;
	}
}
