package com.springproject.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.springproject.domain.DiaryImage;

public class DiaryImageRowMapper implements RowMapper<DiaryImage>
{
	@Override
	public DiaryImage mapRow(ResultSet rs, int rowNum) throws SQLException 
	{
		DiaryImage diaryImage = new DiaryImage();
		diaryImage.setNum(rs.getInt(0));
		diaryImage.setDiaryId(rs.getLong(1));
		diaryImage.setFilename(rs.getString(2));
		
		return diaryImage;
	}

}
