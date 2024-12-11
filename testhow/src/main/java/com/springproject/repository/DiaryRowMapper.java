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
		System.out.println("DiaryRowMapper mapRow in");
		Diary diary = new Diary();	//db에 보내기위해서 묶을 객체
		diary.setDiaryId(rs.getLong(1));
		diary.setUserId(rs.getString(2));
		diary.setVisit_date(rs.getString(3));
		diary.setVisit_diary(rs.getString(4));
		diary.setFilename(rs.getString(5));
		
		return diary;
	}
	
}
