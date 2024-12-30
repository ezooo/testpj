package com.springproject.repository;

import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.springproject.domain.Diary;
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
	}
	
	@Override
	public void setNewDiary(Diary diary) 
	{	//다이어리 등록하기
		System.out.println("DiaryRepositoryImpl setNewDiary in");
		
		//String SQL = "insert into diary(diaryId,userId,visit_date,visit_diary,filename) values(?,?,?,?,?)";
		//template.update(SQL, diary.getDiaryId(), diary.getUserId(), diary.getVisit_date(), diary.getVisit_diary(), diary.getFilename());
		
		//String SQL = "insert into diary(diaryId,userId,visit_date,visit_diary) values(?,?,?,?)";
		//template.update(SQL, diary.getDiaryId(), diary.getUserId(), diary.getVisit_date(), diary.getVisit_diary());
		
		SQL = "insert into diary values(?,?,?,?,?,?,?,?,?,?,?)";
		template.update(SQL, diary.getDiaryId(), diary.getUserId(), diary.getVisit_date(), diary.getVisit_location(), diary.getAddress(), 
				diary.getVisit_diary(), diary.getFilename0(), diary.getFilename1(), diary.getFilename2(), diary.getFilename3(), diary.getIsopen());
		
		System.out.println("setNewDiary 쿼리 업데이트 완료");
		//diaryList.add(diary);
		//System.out.println("diaryList 저장완료 "+diaryList);
	}

	@Override
	public List<Diary> getAllDiary() 
	{
		System.out.println("DiaryRepositoryImpl getAllDiary in");
		
		//SQL = "select * from diary where isopen='true'";
		SQL = "select * from diary where isopen='true' order by RAND() LIMIT 5";
		List<Diary> diaries = template.query(SQL, new DiaryRowMapper());	//sql을 
		System.out.println("getAllDiary" +diaries);
		return diaries;
	}

	@Override
	public List<Diary> getMyDiary(String userId) 
	{
		System.out.println("DiaryRepositoryImpl getMyDiary in");
		// 회원 아이디에 맞는 다이어리만 가져와야 함
		SQL = "select * from diary where userId=?";
		List<Diary> myDiary = template.query(SQL, new DiaryRowMapper(), new Object[] {userId});
		
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
		
		System.out.println("수정할 다이어리 아이디 : "+diary.getDiaryId());
		SQL = "update diary set visit_date=?, visit_location=?, address=?, visit_diary=?, isopen=? where diaryId=?";
		template.update(SQL, diary.getVisit_date(), diary.getVisit_location(), diary.getAddress(), diary.getVisit_diary(), diary.getIsopen(), diary.getDiaryId());
		System.out.println("이미지 제외하고 수정완료");
		
		System.out.println("setUpdateDiary 파일 이미지도 수정하기");
		//String[] filenames = {diary.getFilename0(), diary.getFilename1(), diary.getFilename2(), diary.getFilename3()};
		String defaultname = "00000000diary.png";
		
		if(diary.getFilename0() != null && !(diary.getFilename0().isEmpty()) && !(diary.getFilename0().equals(defaultname)) ) 
		{
			SQL = "update diary set filename0=? where diaryId=?";
			template.update(SQL, diary.getFilename0(), diary.getDiaryId());
		}
		if(diary.getFilename1() != null && !(diary.getFilename1().isEmpty()) && !(diary.getFilename1().equals(defaultname)) ) 
		{
			SQL = "update diary set filename1=? where diaryId=?";
			template.update(SQL, diary.getFilename1(), diary.getDiaryId());
		}
		if(diary.getFilename2() != null && !(diary.getFilename2().isEmpty()) && !(diary.getFilename2().equals(defaultname)) ) 
		{
			SQL = "update diary set filename2=? where diaryId=?";
			template.update(SQL, diary.getFilename2(), diary.getDiaryId());
		}
		if(diary.getFilename3() != null && !(diary.getFilename3().isEmpty()) && !(diary.getFilename3().equals(defaultname)) ) 
		{
			SQL = "update diary set filename3=? where diaryId=?";
			template.update(SQL, diary.getFilename3(), diary.getDiaryId());
		}
		
//		if(diary.getFilename()!=null)
//		{
//			System.out.println("수정할 다이어리 아이디 : "+diary.getDiaryId());
//			System.out.println("setUpdateDiary 파일 이미지도 수정");
//			SQL = "update diary set visit_date=?, visit_diary=?, filename0=? where diaryId=?";
//			System.out.println("수정할 방문일: "+diary.getVisit_date());
//			template.update(SQL, diary.getVisit_date(), diary.getVisit_location(), diary.getVisit_diary(), diary.getFilename0(), diary.getFilename1(),
//					diary.getFilename2(), diary.getFilename3(), diary.getDiaryId());
//		}
//		else
//		{
//			System.out.println("수정할 다이어리 아이디 : "+diary.getDiaryId());
//			System.out.println("setUpdateDiary 파일 이미지 수정 안함");
//			SQL = "update diary set visit_date=?, visit_diary=? where diaryId=?";
//			System.out.println("수정할 방문일: "+diary.getVisit_date());
//			template.update(SQL, diary.getVisit_date(), diary.getVisit_diary(), diary.getDiaryId());	
//		}
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
