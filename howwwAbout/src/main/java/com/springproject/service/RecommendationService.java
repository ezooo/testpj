package com.springproject.service;

import java.util.List;
import java.util.Optional;

import com.springproject.domain.Recommendation;

public interface RecommendationService 
{
	List<Recommendation> getAllRecommend();	//1. 모든 게시글 가져오기
	void addRecommend(Recommendation recommendation);
	Recommendation getRecommend(long recommendId);
	void updateRecommend(Recommendation recommendation);
	void deleteRecommend(long recommendId);
}
