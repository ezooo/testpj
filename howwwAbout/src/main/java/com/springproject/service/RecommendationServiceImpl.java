package com.springproject.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springproject.domain.Recommendation;
import com.springproject.repository.RecommendationRepository;

@Service
public class RecommendationServiceImpl implements RecommendationService
{
	// 2. 그래도 리파지토리는 생성해놔야함. 함수 호출해야하기때문
	@Autowired
	RecommendationRepository recommendationRepository;
	List<Recommendation> recommendationList;
	
	@Override
	public List<Recommendation> getAllRecommend() 
	{	// 1. 서비스는 별로 하는 일이 없다. 리파지토리 함수 호출하기.
		System.out.println("RecommendationServiceImpl getAllRecommend in");
		recommendationList = recommendationRepository.getAllRecommend();
		return recommendationList;
	}

	@Override
	public void addRecommend(Recommendation recommendation) 
	{
		System.out.println("RecommendationServiceImpl addRecommend in");
		recommendationRepository.addRecommend(recommendation);
	}

}
