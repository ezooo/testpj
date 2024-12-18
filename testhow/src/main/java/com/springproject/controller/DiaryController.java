package com.springproject.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.springproject.domain.Diary;
import com.springproject.domain.Member;
import com.springproject.repository.MemberRepository;
import com.springproject.service.DiaryService;

@Controller	//저는 컨트롤러에요
@RequestMapping(value="/diaries")
public class DiaryController 
{
	@Autowired	//객체 생성하기
	private DiaryService diaryService;

	@RequestMapping
	public String showDiary(Model model, HttpServletRequest request)
	{
		System.out.println("다이어리 보여주기");
		String sessionid;
		
		if(request.getSession(false) != null)
		{
			sessionid = request.getSession(false).getId();
			
			if(sessionid != null)
			{
				//세션에서 유저아이디 꺼내기
				Member mb = (Member) request.getSession(false).getAttribute("member");
				if(mb !=  null)
				{
					String userId = mb.getUserId();
					System.out.println("로그인 한 유저 아이디는 : "+userId);
					List<Diary> list = diaryService.getMyDiary(userId);
					
					model.addAttribute("diaryList", list);
					return "diaries";
				}
				System.out.println("세션은 있는데 로그인 안됐음");
				return "diary_beforeLogin";
			}
		}
		System.out.println("로그인 안되어있다 : 다이어리 구조만 보여주기");
		return "diary_beforeLogin";
	}

	@GetMapping("/diary/{diaryId}")
	public String getOnediary(@PathVariable Long diaryId, Model model)
	{
		System.out.println("DiaryController getOnediary in");
		Diary diary = diaryService.getOnediary(diaryId);
		model.addAttribute("diary", diary);
		return "diary";
	}

	
	@GetMapping("/addDiary")
	public String addDiaryForm(@ModelAttribute Diary diary, HttpSession session)
	{
		System.out.println("DiaryController - addDiaryForm in");
		//세션에서 로그인된 멤버 정보 가져오기
		Member member = (Member)session.getAttribute("member");
		System.out.println(member);
		
		if(member == null)
		{
			System.out.println("멤버 아니다 로그인하세요");
			//로그인 안되어있으면 로그인창으로 보내
			return "redirect:/login";
		}
		return "addDiary";
	}
	
	@PostMapping("/addDiary")
	public String submitDiaryForm(@ModelAttribute Diary diary, HttpServletRequest request)
	{	
		System.out.println("DiaryController - submitDiaryForm in");
		//다이어리 아이디 주기
		Member mb = (Member)request.getSession().getAttribute("member");
		diary.setUserId(mb.getUserId());
		System.out.println("유저 아이디 넣어주기 : "+diary.getUserId());
		diary.setDiaryId(System.currentTimeMillis());
		System.out.println("다이어리 아이디 넣어주기 : "+diary.getDiaryId());
		System.out.println("다이어리 작성한 내용 받아왔고 userId 랑 diaryId 생성해서 set 완료");

		//이미지파일넣기
		MultipartFile picture = diary.getPicture();
		String savepath = request.getServletContext().getRealPath("/resources/images/");
		System.out.println(savepath);
		String savename = picture.getOriginalFilename();
		System.out.println("파일이름은 : "+savename);
		File savefile = new File(savepath, savename);	//변수에 저장해둔 경로+ 저장된 이름으로 새 파일 생성
		
		//유효성 검사
		if( picture!=null && !picture.isEmpty())
		{
			System.out.println("submitDiaryForm 사진이 있습니다. 파일 저장하기");
			try
			{
				picture.transferTo(savefile);	
				diary.setFilename(savename);	
				System.out.println("파일 업로드 완료 !");
			}
			catch(Exception e)
			{
				System.out.println("submitDiaryForm 파일 작성 에러에러");
			}
		}
		diaryService.setNewDiary(diary);
		return "redirect:/diaries";
	}
	
	@GetMapping("/updateDiary")
	public String updateDiary(@ModelAttribute Diary diary, @RequestParam("id") long diaryId, Model model)
	{																						//다이어리 아이디로 찾은 해당 다이어리 정보 담을 모델
		System.out.println("DiaryController - updateDiary in");
		System.out.println("수정할 다이어리 아이디 받아옴 : "+diaryId);
		Diary diaryById = diaryService.getDiaryById(diaryId);
		System.out.println("다이어리 찾아옴 수정페이지로 이동.. 하기전에 다이어리 정보 모델에 넣기");
		model.addAttribute("diaryById",diaryById);
		return "updateDiary";
	}
	
	@PostMapping("/updateDiary")
	public String setUpdateDiary(@ModelAttribute Diary diary, HttpServletRequest request)
	{
		System.out.println("DiaryController - setUpdateDiary in");
		System.out.println("이미지 파일 수정할 때랑 안할 때 다르게 작업해야 함");
		
		MultipartFile picture = diary.getPicture();	//다이어리에서 파일 받아오기
		
		//유효성 검사
		if( picture!=null && !picture.isEmpty())
		{
			System.out.println("submitDiaryForm 사진이 있습니다.");
			String savepath = request.getServletContext().getRealPath("/resources/images/");	//저장소 경로 받아오기
			System.out.println(savepath);
			String savename = picture.getOriginalFilename();	//파일 이름 받아오기
			System.out.println("파일이름은 : "+savename);
			File savefile = new File(savepath, savename);	//변수에 저장해둔 경로+ 저장된 이름으로 새 파일 생성
			System.out.println("submitDiaryForm 파일 저장하기");
			try
			{
				picture.transferTo(savefile);	//사진을 savepath 경로에 업로드
				diary.setFilename(savename);
				System.out.println("submitDiaryForm 파일 업로드 완료 !");
			}
			catch(Exception e)
			{
				System.out.println("submitDiaryForm 파일 작성 에러에러");
			}
		}
		
		diaryService.setUpdateDiary(diary);

		return "redirect:/diaries";
	}
	
	@GetMapping("/deleteDiary")
	public String deleteDiary(@ModelAttribute Diary diary, @RequestParam("id") long diaryId)
	{
		diaryService.deleteDiary(diaryId);
		return "redirect:/diaries";
	}
}
