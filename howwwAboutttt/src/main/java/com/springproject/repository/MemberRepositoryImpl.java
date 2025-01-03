package com.springproject.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.springproject.domain.Diary;
import com.springproject.domain.Member;

@Repository
public class MemberRepositoryImpl implements MemberRepository 
{
	private List<Member> memberList = new ArrayList<Member>();
	
	
	public MemberRepositoryImpl() 
	{
		super();
		Member member1 = new Member("aaa", "1234");
		Member member2 = new Member("qqq", "qqqq");
		Member member3 = new Member("hhh", "hhhh");
		
		memberList.add(member1);
		memberList.add(member2);
		memberList.add(member3);
		System.out.println("일단 멤버하나 만들어놓음 aaa");
	}

	@Override
	public List<Member> getAllMember() 
	{
		System.out.println("MemberRepositoryImpl getAllMember 멤버리스트 줘라");
		System.out.println(memberList);
		return memberList;
	}

	@Override
	public Member getMember(String userId) 
	{
		System.out.println("아이디에 맞는 유저 찾기");
		for(Member member : memberList)
		{
			if(member.getUserId().equals(userId))
			{
				System.out.println("일치하는 아이디를 찾았습니다.");
				return member;
			}
			
		}
		System.out.println("일치하는 아이디가 없습니다.");
		return null;
	}

}
