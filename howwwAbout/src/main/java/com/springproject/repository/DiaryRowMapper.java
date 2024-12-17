package com.springproject.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.springproject.domain.Diary;

public class DiaryRowMapper implements RowMapper<Diary>
{
	@Override
	public Diary mapRow(ResultSet rs, int rowNum) throws SQLException 
	{
		Diary diary = new Diary();	//db에 보내기위해서 묶을 객체
		System.out.println("다이어리 로우매퍼 들어옴");
		diary.setDiaryId(rs.getLong(1));
		diary.setUserId(rs.getString(2));
		diary.setVisit_date(rs.getString(3));
		diary.setVisit_location(rs.getString(4));
		diary.setAddress(rs.getString(5));
		diary.setVisit_diary(rs.getString(6));
		diary.setFilename0(rs.getString(7));
		diary.setFilename1(rs.getString(8));
		diary.setFilename2(rs.getString(9));
		diary.setFilename3(rs.getString(10));
		diary.setIsopen(rs.getString(11));
		
		return diary;
	}
}
