package com.springproject.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.springproject.domain.Diary;
import com.springproject.domain.Member;
import com.springproject.repository.MemberRepository;
import com.springproject.service.DiaryService;

@Controller	//저는 컨트롤러에요
@RequestMapping(value="/diary")
public class DiaryController 
{
	@Autowired	//객체 생성하기
	private DiaryService diaryService;
	@Autowired
	private MemberRepository mbrp;	//회원등록 돼있는지 확인하려면 멤버리파지토리 멤버리스트 가져와야 함

	@RequestMapping
	public String showDiary(Model model, HttpServletRequest request)
	{
		System.out.println("다이어리 보여주기");
		//로그인 되어있을 때 다이어리 보여주기
		String sessionid = request.getSession(false).getId();
		if(sessionid != null)
		{
			System.out.println("DiaryController showDiary 세션아이디 있다 : 내 다이어리 가기");
			//다이어리 갈 때 내 다이어리 다 보여줘야 함
			List<Diary> list = diaryService.getAllDiary();
			model.addAttribute("diaryList", list);
			return "diary";
		}
		System.out.println("로그인 안되어있다 : 다이어리 구조만 보여주기");
		return "diary_beforeLogin";
	}
	
	@GetMapping("/my")
	public String showMyDiary(Model model, HttpServletRequest request)
	{
		System.out.println("my 다이어리 보여주기");
		
			List<Diary> list = diaryService.getMyDiary();
			model.addAttribute("diaryList", list);
			return "diary";
		
	}
	//세션아이디 가지고 다이어리 가야 함
	//@GetMapping
//	public String requestMemberId(HttpServletRequest request)
//	{
//		System.out.println("diaryController - requestMemberId 매핑");
//		String sessionid = request.getSession(true).getId();	//멤버만 다이어리 쓸 수 있다	//세션 트루해서 멤버 아니라도 구성을 볼 수는 있게 해주자
//																						//세션 안주고 보여주기
//		System.out.println("세션아이디 담기 : "+sessionid);
//		return "redirect:/diary/"+sessionid ;
//	}
	
	@GetMapping("/addDiary")
	public String addDiaryForm(Member member, Diary diary)
	{
		System.out.println("DiaryController - addDiaryForm in");
		System.out.println(member);
		
		//회원등록 돼있는지 확인하려면 멤버리파지토리 멤버리스트 가져와야 함
		List<Member> memberList = mbrp.getAllMember();
		
		if(member == null && !(memberList.contains(member)) )
		{
			System.out.println("멤버 아니다 로그인하세요");
			//로그인 안되어있으면 로그인창으로 보내
			return "login";
		}
		
		return "addDiary";
	}
	
	@PostMapping("/addDiary")
	public String submitDiaryForm(@ModelAttribute Diary diary, HttpServletRequest request)	//모델에 담긴 내용 받아오기
	{	//addDiary 에서 제출한 내용 처리할 메서드
		System.out.println("DiaryController - submitDiaryForm in");
		
		//이미지파일넣기
		MultipartFile picture = diary.getPicture();	//다이어리에서 파일 받아오기
		String savepath = request.getServletContext().getRealPath("/resources/images/");	//저장소 경로 받아오기
		System.out.println(savepath);
		String savename = picture.getOriginalFilename();	//파일 이름 받아오기
		System.out.println("파일이름은 : "+savename);
		File savefile = new File(savepath, savename);	//변수에 저장해둔 경로+ 저장된 이름으로 새 파일 생성
		
		//유효성 검사
		if( picture!=null && !picture.isEmpty())
		{
			System.out.println("submitDiaryForm 사진이 있습니다. 파일 저장하기");
			try
			{
				picture.transferTo(savefile);	//사진을 savepath 경로에 업로드
				System.out.println("파일 업로드 완료 !");
			}
			catch(Exception e)
			{
				System.out.println("submitDiaryForm 파일 작성 에러에러");
			}
		}
		
		diaryService.setNewDiary(diary);	//제출받은거 등록함수에게 주기
		
		return "redirect:/diary";
	}
	
	
	
	@PostMapping
	public  @ResponseBody Diary create(@RequestBody Diary diary)
	{
		System.out.println("diaryController - create 매핑 : 다이어리 만들기 - 다이어리 서비스로 감");
		return diaryService.create(diary);	//다이어리 새로 만들고 돌아오면
	}
}
