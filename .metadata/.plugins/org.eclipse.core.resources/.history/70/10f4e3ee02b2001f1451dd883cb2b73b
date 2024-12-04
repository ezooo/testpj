package com.springproject.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springproject.domain.Recommendation;
import com.springproject.service.RecommendationService;

@Controller	//1.저는컨트롤러입니다
@RequestMapping("/recommend")	//2. 매핑 먼저 해주기
public class RecommendationController 
{
	// 3. 리파지토리랑 연결해둬야 함
//	@Autowired	//new 의 역할. 자동으로 객체생성해줘라
	//RecommendationRepository recommendationRepository
	//3. 생각해보니까 서비스랑 연결하는 것 같다
	@Autowired
	RecommendationService recommendationService;
		
	//2. 일단 모든 추천글 보기 함수 만들기
	@RequestMapping
	public String getAllRecommend(Model model)
	{								//6.
		System.out.println("RecommendationController getAllRecommend in");
		// 4. 모든 추천글 볼 때 쓸 리스트 객체 생성해놓기 : 이거 나중에 다른 곳에 안쓰면 함수 안에 넣기
		List<Recommendation> recommendationList = recommendationService.getAllRecommend();	//5. 모든 추천글 반환하는 함수 호출하고 그걸 리스트에 담기
		
		//6. 뷰에 가져가서 뿌려줘야하니까 반환받은 어레이리스트 모델에 담아줘야 함 : 그래야 꺼낼 수 있다.
		model.addAttribute("recommendationList", recommendationList);
		
		return "recommendations";	//2. 이동할 뷰까지 작성해놓기
	}
	
	// CREATE
	@GetMapping("/addRecommend")
	public String addRecommendation(Model model)
	{
		System.out.println("RecommendationController addRecommendation in");
		// 이 컨트롤러는 게시글 입력창으로 보내주는거 + 보낼 때 모델에 담으라고 모델도 보내줌
		// 폼으로 입력받고 돌아오면 가지고 온 내용을 model 에 넣어서 리파지토리 함수에 줘야 함
		model.addAttribute("recommendation", new Recommendation());	//이걸 안하니까 에러가 나
		return "addRecommendation";
	}
	@PostMapping("/addRecommend")
	public String addSubmitRecommendation(@ModelAttribute Recommendation recommendation, Model model)
	{
		System.out.println("RecommendationController addSubmitRecommendation in");
		recommendationService.addRecommend(recommendation);
		
		return "redirect:/recommend";
	}
	
}
