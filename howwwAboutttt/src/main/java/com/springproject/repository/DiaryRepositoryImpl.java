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

@Repository	//이거 안하니까 객체 생성 못한다고 화냄
public class DiaryRepositoryImpl implements DiaryRepository
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
	String SQL;
	//db설정end
	
	public DiaryRepositoryImpl()
	{
		System.out.println("DiaryRepositoryImpl 생성자 함수 호출 - 다이어리 담을 맵 만들기");
		//diaryList = new HashMap<String, Diary>();	
		//diaryIdList = new HashMap<String, List<Diary>>();
		Diary diary1 = new Diary("11", "11", "11");
		
		diaryList.add(diary1);
	}
	
	@Override
	public void setNewDiary(Diary diary) 
	{	//다이어리 등록하기
		System.out.println("DiaryRepositoryImpl setNewDiary in");
		
		//String SQL = "insert into diary(diaryId,userId,visit_date,visit_diary,filename) values(?,?,?,?,?)";
		//template.update(SQL, diary.getDiaryId(), diary.getUserId(), diary.getVisit_date(), diary.getVisit_diary(), diary.getFilename());
		
		String SQL = "insert into diary(diaryId,userId,visit_date,visit_diary) values(?,?,?,?)";
		template.update(SQL, diary.getDiaryId(), diary.getUserId(), diary.getVisit_date(), diary.getVisit_diary());
		
		System.out.println("setNewDiary 쿼리 업데이트 완료");
		//diaryList.add(diary);
		//System.out.println("diaryList 저장완료 "+diaryList);
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
		// 회원 아이디에 맞는 다이어리만 가져와야 함
		// 지금 로그인 한 회원 아이디 받아오는 법 ?
		myDiary = new ArrayList<Diary>();
		
		diaryList = getAllDiary();	//이걸 해야 데이터베이스에 저장된 다이어리를 가져오지..
		
		System.out.println("getMyDiary 받아온 유저 아이디 : "+userId);
		for(Diary diary : diaryList)
		{
			//System.out.println("다이어리 for 문 "+i+"번째 다이어리 userId : "+ diary.getUserId());
			//내 다이어리 아니면 userid
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

	@Override
	public void uploadImage(DiaryImage diaryImage) 
	{
		System.out.println("DiaryRepositoryImpl uploadImage in");
		SQL = "insert into diaryImage values(?,?,?)";
		//template.queryForObject(SQL, new DiaryImageRowMapper(), diaryImage);
		template.update(SQL, null, diaryImage.getDiaryId(), diaryImage.getFilename());
		System.out.println("다이어리 이미지 업로드 완료");
	}

	@Override
	public List<DiaryImage> getdiaryImages(Long diaryId) 
	{
		System.out.println("DiaryRepositoryImpl getdiaryImages in");
		SQL = "select * from diaryimage where diaryId=?";
		System.out.println("diaryId : "+diaryId);
		List<DiaryImage> diaryImages = template.query(SQL, new DiaryImageRowMapper(), new Object[] {diaryId});
		System.out.println(diaryImages.isEmpty());
		return diaryImages;
	}
	
	
}
