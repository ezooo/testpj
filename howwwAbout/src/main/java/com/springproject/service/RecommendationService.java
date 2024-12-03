package com.springproject.service;

import java.util.List;

import com.springproject.domain.Recommendation;

public interface RecommendationService 
{
	List<Recommendation> getAllRecommend();	//1. 모든 게시글 가져오기
}
