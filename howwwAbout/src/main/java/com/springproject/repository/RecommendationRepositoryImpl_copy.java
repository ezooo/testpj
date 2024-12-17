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

//@Repository	//1. 저는 리파지토리에요
public class RecommendationRepositoryImpl_copy implements RecommendationRepository
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
	
	
	public RecommendationRepositoryImpl_copy() 
	{	// 5. 생성자에서 add 해야하나..?
		super();
		System.out.println("RecommendationRepositoryImpl 기본생성자 진입");
		// 6. recommend 객체 생성해서 일단 기본으로 호출할거
//		Recommendation recommendation1 = new Recommendation("장소추천1", "용지호수", 11111111);
//		Recommendation recommendation2 = new Recommendation("공원 추천", "올림픽 공원 가봐유", 22222222);
//		recommendationList.add(recommendation1);
//		recommendationList.add(recommendation2);
//		System.out.println("일단 보여줄거 어레이리스트에 담기 완료. 생성자 탈출");
	}

	@Override	// 2. 게시글 전체 보여주기
	public List<Recommendation> getAllRecommend() 
	{
		System.out.println("RecommendationRepositoryImpl getAllRecommend in");
		// 3. 뭔가 기억이 희미한데... dto 생성자에서 add로 dto를 넣어줘야 했던 것 같다.
		return recommendationList;	// 7. 리스트 리턴. ㅓ컨트롤러로 돌아감
	}

	@Override
	public void addRecommend(Recommendation recommendation) 
	{
		System.out.println("RecommendationRepositoryImpl addRecommend in");
		//입력받은 내용 모델에 담아서 여기까지 가지고 옴
		//이거를 이제 어레이리스트에 담기
		recommendation.setRecommendId(System.currentTimeMillis());
		
		LocalDateTime today = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년MM월dd일 HH:mm");
		recommendation.setRecommendDate(today.format(formatter));
		
		System.out.println(recommendation.getRecommendId()+" / "+recommendation.getRecommendDate());
		
		recommendationList.add(recommendation);
	}

	
	@Override
	public Recommendation getRecommend(long recommendId) 
	{
		System.out.println("RecommendationRepositoryImpl getRecommend in");
		//추천글리스트에서 글아이디로 해당 글 찾기
		for(Recommendation recommendation : recommendationList)
		{
			
				if(recommendation.getRecommendId()==recommendId)
				{
					System.out.println("해당 게시글 찾았다");
					return recommendation;
				}
			
		}
		System.out.println("없는뎅? 게시글 못찾음");
		return null;
	}


	@Override
	public void updateRecommend(Recommendation recommendation) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void deleteRecommend(long recommendId) {
		// TODO Auto-generated method stub
		
	}

}
