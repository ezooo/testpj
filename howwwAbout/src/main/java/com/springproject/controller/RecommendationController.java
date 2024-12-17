package com.springproject.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
		
	//2. 일단 모든 추천글 보기 함수 만들기  READ
	@RequestMapping
	public String getAllRecommend(Model model)
	{								//6.
		System.out.println("RecommendationController getAllRecommend in");
		// 4. 모든 추천글 볼 때 쓸 리스트 객체 생성해놓기 : 이거 나중에 다른 곳에 안쓰면 함수 안에 넣기
		List<Recommendation> recommendationList = recommendationService.getAllRecommend();	//5. 모든 추천글 반환하는 함수 호출하고 그걸 리스트에 담기
		
		//6. 뷰에 가져가서 뿌려줘야하니까 반환받은 어레이리스트 모델에 담아줘야 함 : 그래야 꺼낼 수 있다.
		model.addAttribute("recommendationList", recommendationList);
		
		return "recommendation/recommendations";	//2. 이동할 뷰까지 작성해놓기
	}
	
	// CREATE
	@GetMapping("/addRecommend")
	public String addRecommendation(Model model)
	{
		System.out.println("RecommendationController addRecommendation in");
		// 이 컨트롤러는 게시글 입력창으로 보내주는거 + 보낼 때 모델에 담으라고 모델도 보내줌
		// 폼으로 입력받고 돌아오면 가지고 온 내용을 model 에 넣어서 리파지토리 함수에 줘야 함
		model.addAttribute("recommendation", new Recommendation());	//이걸 안하니까 에러가 나
		return "recommendation/addRecommendation";
	}
	@PostMapping("/addRecommend")
	public String addSubmitRecommendation(@ModelAttribute Recommendation recommendation, Model model)
	{
		System.out.println("RecommendationController addSubmitRecommendation in");
		recommendationService.addRecommend(recommendation);
		
		return "redirect:/recommend";
	}
	
	// READ ONE : 제목만 보여주고 내용은 들어가야 볼 수 있음
	@GetMapping("/recommendation/{recommendId}")
	public String getRecommend(@PathVariable long recommendId, Model model)	//변수 받아온다 
	{//@PathParam("recommendId") long recommendId 이거 쓰려다 생각해보니까 파라미터로 받을거라 필요없을 듯
		//뷰에서 제목 클릭했을 때 게시글아이디 받아와서 --> 보여준다
		// 1. 뷰에서 받아오는게 있어야 하니까 model에 담아오기 -- 없다.그냥 파라미터 날림
		System.out.println("RecommendationController getRecommend in");
		// 3. 받아온 아이디 가지고 레파지토리가서 객체 받아와라. -- 앗.. 모델은 이때 필요하다
		// 근데 이거 모델 new 안해도 되나? --- 안했던 것 같은데...
//		if(recommendId.isPresent())
//		{
//			try
//			{
//				Recommendation recommendation = recommendationService.getRecommend(recommendId.get());
//				// 근데 게시글 받아온게 null 이면 에러남
//				if(recommendation != null)
//				{
//					System.out.println(" 추천 게시글 잘 찾아서 컨트롤러로 돌아옴");
//					model.addAttribute("recommendation",recommendation);	//뷰에 가서 꺼내려면 담아야 함
//					return "recommendation";
//				}
//			}
//			catch(Exception e)
//			{
//				System.out.println("RecommendationController getRecommend 에러에러");
//				e.getStackTrace();
//			}
//		}
		
		try
		{
			Recommendation recommendation = recommendationService.getRecommend(recommendId);
			// 근데 게시글 받아온게 null 이면 에러남
			if(recommendation != null)
			{
				System.out.println(" 추천 게시글 잘 찾아서 컨트롤러로 돌아옴");
				model.addAttribute("recommendation",recommendation);	//뷰에 가서 꺼내려면 담아야 함
				return "recommendation/recommendation";
			}
		}
		catch(Exception e)
		{
			System.out.println("RecommendationController getRecommend 에러에러");
			e.getStackTrace();
		}
		
		System.out.println(" 추천 게시글 못찾음. 목록으로 가라");
		System.out.println("recommendId : "+recommendId);
		return "redirect:/recommend";
	}

	@GetMapping("/recommendation/update/{recommendId}")
	public String updateRecommend(@PathVariable long recommendId, Model model)
	{	//수정해야 하니까 기존 dto 정보 다 받아와야 함 @ModelAttribute Recommendation recommendation
		System.out.println("RecommendationController updateRecommend get in");
		//아니야.... 아이디만 받아와ㅓ서 정보를 찾아옴..
		Recommendation recommendation = recommendationService.getRecommend(recommendId);
		model.addAttribute("recommendation",recommendation);
		System.out.println("수정할 게시글 찾ㅇ아옴. 수정페이지로 감");
		return "recommendation/updateRecommendation";
	}
	
	@PostMapping("/recommendation/update/{recommendId}")
	public String updateSubmitRecommend(@ModelAttribute Recommendation recommendation)
	{
		System.out.println("RecommendationController updateRecommend post in");
		recommendationService.updateRecommend(recommendation);
		
		return "redirect:/recommend/recommendation/{recommendId}";
	}
	
	@GetMapping("/recommendation/delete/{recommendId}")
	public String deleteRecommend(@PathVariable long recommendId)
	{
		System.out.println("RecommendationController deleteRecommend  in");
		recommendationService.deleteRecommend(recommendId);
		
		return "redirect:/recommend";
	}
}
