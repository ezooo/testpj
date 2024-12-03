package com.springproject.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springproject.domain.Recommendation;

@Repository	//1. 저는 리파지토리에요
public class RecommendationRepositoryImpl implements RecommendationRepository
{
	// 4. 일단 리스트가 있어야 뭐든 담을 수 있다.
	@Autowired
	List<Recommendation> recommendationList;
	
	public RecommendationRepositoryImpl() 
	{	// 5. 생성자에서 add 해야하나..?
		super();
		System.out.println("RecommendationRepositoryImpl 기본생성자 진입");
		// 6. recommend 객체 생성해서 일단 기본으로 호출할거
		Recommendation recommendation1 = new Recommendation("장소추천1", "용지호수");
		Recommendation recommendation2 = new Recommendation("공원 추천", "올림픽 공원 가봐유");
		recommendationList.add(recommendation1);
		recommendationList.add(recommendation2);
		System.out.println("일단 보여줄거 어레이리스트에 담기 완료. 생성자 탈출");
	}

	@Override	// 2. 게시글 전체 보여주기
	public List<Recommendation> getAllRecommend() 
	{
		System.out.println("RecommendationRepositoryImpl getAllRecommend in");
		// 3. 뭔가 기억이 희미한데... dto 생성자에서 add로 dto를 넣어줘야 했던 것 같다.
		return recommendationList;	// 7. 리스트 리턴. ㅓ컨트롤러로 돌아감
	}

}
