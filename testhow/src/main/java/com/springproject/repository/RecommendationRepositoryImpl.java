package com.springproject.repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.springproject.domain.Recommendation;

@Repository	//1. 저는 리파지토리에요
public class RecommendationRepositoryImpl implements RecommendationRepository
{
	private List<Recommendation> recommendationList = new ArrayList<Recommendation>();
	
	private JdbcTemplate template;
	@Autowired
	public void setJdbctemplate(DataSource dataSource)
	{
		this.template = new JdbcTemplate(dataSource);
	}
	String SQL;
	
	public RecommendationRepositoryImpl() 
	{
		super();
		System.out.println("RecommendationRepositoryImpl 기본생성자 진입");
	}

	@Override	// 2. 게시글 전체 보여주기
	public List<Recommendation> getAllRecommend() 
	{
		System.out.println("RecommendationRepositoryImpl getAllRecommend in");
		SQL = "select * from recommendation";	//쿼리문 작성
		List<Recommendation> recommendationList = template.query(SQL, new RecommendationRowMapper());
		
		return recommendationList;
	}

	@Override
	public void addRecommend(Recommendation rd) 
	{
		System.out.println("RecommendationRepositoryImpl addRecommend in");

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

			if(rowCount != 0)
			{
				System.out.println("찾아올 레코드가 존재합니다.");
				SQL = "select * from recommendation where recommendId=?";
				recommendation = template.queryForObject(SQL, new RecommendationRowMapper(), new Object[] {recommendId});
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
