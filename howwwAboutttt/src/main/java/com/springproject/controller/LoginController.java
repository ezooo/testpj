package com.springproject.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springproject.domain.Member;
import com.springproject.repository.MemberRepository;

@Controller
@RequestMapping
public class LoginController 
{
	@Autowired
	MemberRepository mr;
	List<Member> memberList;
	
	@GetMapping("/login")
	public String login(Model model)
	{
		model.addAttribute("member", new Member());
		return "login";
	}
	
	@PostMapping("/login")
	public String gologin(@ModelAttribute Member member, HttpServletRequest request)
	{
		System.out.println("로그인하러옴");
		String userId = member.getUserId();
		String userPw = member.getUserPw();
		System.out.println("모델에 받아온 아이디 & 패스워드는 : "+userId+" / "+userPw);
		memberList = mr.getAllMember();
		System.out.println("멤버리스트 받아옴");
		//등록된 회원인지 검사
		for(Member mb : memberList)	//한명씩꺼내
		{
			if(mb.getUserId().equals(userId))
			{
				System.out.println("유효한 아이디다");
				if(mb.getUserPw().equals(userPw))
				{
					System.out.println("비밀번호 일치 : 로그인 성공");
					//세션 생성
					HttpSession ssn = request.getSession(true);
					ssn.setAttribute("member", mb);
					return "redirect:/";
				}
				System.out.println("비밀번호 일치하지않음");
				return "login";
			}
		}
		
//		if(mr.getAllMember().contains(member))
//		{	//리스트에 멈버있는지 검사
//			
//			System.out.println("유효한 아이디다");
//			if(mr.getMember(userId).getUserPw() == userPw)
//			{
//				System.out.println("비밀번호 일치 : 로그인 성공");
//				//세션 생성
//				HttpSession ssn = request.getSession(true);
//				ssn.setAttribute("member", mr.getMember(userId));
//				return "redirect:/diary/addDiary";
//			}
//			return "login";
//		}
		return "login";
	}
}
