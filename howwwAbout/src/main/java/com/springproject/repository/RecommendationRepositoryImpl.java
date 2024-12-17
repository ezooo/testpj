package com.springproject.repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.springproject.domain.Recommendation;

@Repository	//1. 저는 리파지토리에요
public class RecommendationRepositoryImpl implements RecommendationRepository
{
	// 4. 일단 리스트가 있어야 뭐든 담을 수 있다.
	private List<Recommendation> recommendationList = new ArrayList<Recommendation>();
	
	// 10. db연결
	private JdbcTemplate template;
	// 11. JdbcTemplate 클래스의 속성에 대한 Setter() 메서드
	@Autowired
	public void setJdbctemplate(DataSource dataSource)
	{
		this.template = new JdbcTemplate(dataSource);
	}
	String SQL;
	
	public RecommendationRepositoryImpl() 
	{	// 5. 생성자에서 add 해야하나..?
		super();
		System.out.println("RecommendationRepositoryImpl 기본생성자 진입");
	}

	@Override	// 2. 게시글 전체 보여주기
	public List<Recommendation> getAllRecommend() 
	{
		System.out.println("RecommendationRepositoryImpl getAllRecommend in");
		SQL = "select * from recommendation";	//쿼리문 작성
		List<Recommendation> recommendationList = template.query(SQL, new RecommendationRowMapper());
		
		return recommendationList;	// 7. 리스트 리턴. ㅓ컨트롤러로 돌아감
	}

	@Override
	public void addRecommend(Recommendation rd) 
	{
		System.out.println("RecommendationRepositoryImpl addRecommend in");
		//입력받은 내용 모델에 담아서 여기까지 가지고 옴
		//이거를 이제 어레이리스트에 담기
		rd.setRecommendId(System.currentTimeMillis());
		LocalDateTime today = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년MM월dd일 HH:mm");
        rd.setRecommendDate(today.format(formatter));
		
		SQL = "insert into recommendation values(?,?,?,?,?)";
		template.update(SQL, rd.getRecommendId(), rd.getUserId(), rd.getRecommendTitle(), rd.getRecommendContent(), rd.getRecommendDate());
		System.out.println("addRecommend 완료");
	}

	
	@Override
	public Recommendation getRecommend(long recommendId) 
	{//추천글리스트에서 글아이디로 해당 글 찾기
		System.out.println("RecommendationRepositoryImpl getRecommend in");
		
		Recommendation recommendation = null;
		try
		{
			SQL = "select count(*) from recommendation where recommendId=?";
			int rowCount = template.queryForObject(SQL, Integer.class, recommendId);
			//등록된 전체 목록 중 검색 조건과 일치하는 레코드 갯수를 얻어오는 변수 
			// : 검색조건인 recommendId 가 등록 될 때만 db에 접근하여 목록을 조회하도록 하기 위한 것
			if(rowCount != 0)
			{
				System.out.println("찾아올 레코드가 존재합니다.");
				SQL = "select * from recommendation where recommendId=?";
				recommendation = template.queryForObject(SQL, new Object[] {recommendId}, new RecommendationRowMapper());
			}
		}
		catch(Exception e)
		{
			System.out.println("recommendation 비었다 !! getRecommend() 에러에러");
			e.getStackTrace();
		}
		return recommendation;
	}

	
	@Override
	public void updateRecommend(Recommendation recommendation) 
	{
		System.out.println("RecommendationRepositoryImpl updateRecommend in");
		SQL = "update recommendation set recommendTitle=?, recommendContent=? where recommendId=?";
		template.update(SQL, recommendation.getRecommendTitle(), recommendation.getRecommendContent(), recommendation.getRecommendId());
	}

	@Override
	public void deleteRecommend(long recommendId) 
	{
		System.out.println("RecommendationRepositoryImpl deleteRecommend in");
		SQL = "delete from recommendation where recommendId=?";
		template.update(SQL, recommendId);
	}

}
