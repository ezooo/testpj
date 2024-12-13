package com.springproject.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.springproject.domain.Recommendation;


public class RecommendationRowMapper implements RowMapper<Recommendation>
{
	@Override
	public Recommendation mapRow(ResultSet rs, int rowNum) throws SQLException 
	{
		System.out.println("RecommendationRowMapper mapRow in");
		Recommendation recommendation = new Recommendation();	//db에 보내기위해서 묶을 객체
		recommendation.setRecommendId(rs.getLong(1));
		recommendation.setUserId(rs.getString(2));
		recommendation.setRecommendTitle(rs.getString(3));
		recommendation.setRecommendContent(rs.getString(4));
		recommendation.setRecommendDate(rs.getString(5));
		
		return recommendation;
	}
	
}
