package com.springproject.repository;

import java.util.List;
import java.util.Optional;

import com.springproject.domain.Recommendation;

public interface RecommendationRepository 
{
	List<Recommendation> getAllRecommend();
	void addRecommend(Recommendation recommendation);
	Recommendation getRecommend(long recommendId);
	void updateRecommend(Recommendation recommendation);
	void deleteRecommend(long recommendId);
}
