package com.springproject.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.springproject.domain.Member;

@Repository
public class MemberRepositoryImpl implements MemberRepository 
{
	@Override
	public List<Member> getAllMember() 
	{
		System.out.println("MemberRepositoryImpl getAllMember 멤버리스트 줘라");
		return null;
	}

}
