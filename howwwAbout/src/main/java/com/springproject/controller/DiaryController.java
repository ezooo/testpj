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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.springproject.domain.Diary;
import com.springproject.domain.Member;
import com.springproject.service.DiaryService;

@Controller	//저는 컨트롤러에요
@RequestMapping(value="/diaries")
public class DiaryController 
{
	@Autowired	//객체 생성하기
	private DiaryService diaryService;
//	@Autowired
//	private MemberRepository mbrp;	//회원등록 돼있는지 확인하려면 멤버리파지토리 멤버리스트 가져와야 함

	@RequestMapping
	public String showDiary(Model model, HttpServletRequest request)
	{
		System.out.println("다이어리 보여주기");
		
		List<Diary> list = diaryService.getAllDiary();
		model.addAttribute("diaryList", list);

		return "diary/diaries";
	}
	
	@GetMapping("/my")
	public String showMyDiary(Model model, HttpServletRequest request)
	{
		System.out.println("내 다이어리 가기");

		String sessionid;
		
		if(request.getSession(false) != null)
		{
			sessionid = request.getSession(false).getId();
			
			if(sessionid != null)
			{
				System.out.println("DiaryController showDiary 세션아이디 있다 : 내 다이어리 가기");
				//다이어리 갈 때 내 다이어리 다 보여줘야 함
				//List<Diary> list = diaryService.getAllDiary();
				//세션에서 유저아이디 꺼내기
				Member mb = (Member) request.getSession(false).getAttribute("member");
				if(mb !=  null)
				{
					String userId = mb.getUserId();
					System.out.println("로그인 한 유저 아이디는 : "+userId);
					List<Diary> list = diaryService.getMyDiary(userId);
					
					model.addAttribute("diaryList", list);
					return "diary/mydiaries";
				}
				System.out.println("세션은 있는데 로그인 안됐음");
				return "redirect:/login";
			}
		}
		System.out.println("로그인 안되어있다 : 다이어리 구조만 보여주기");
		return "redirect:/login";
	}

	@GetMapping("/diary/{diaryId}")
	public String getOnediary(@PathVariable Long diaryId, Model model)
	{
		System.out.println("DiaryController getOnediary in");
		Diary diary = diaryService.getOnediary(diaryId);
		model.addAttribute("diary", diary);
		//List<DiaryImage> diaryImages = diaryService.getdiaryImages(diaryId);
		//model.addAttribute("diaryImages",diaryImages);
		return "diary/diary";
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
			//model.addAttribute("member", new Member());
			return "redirect:/login";
		}
		return "diary/addDiary";
	}
	
	@PostMapping("/addDiary")
	public String submitDiaryForm(@ModelAttribute Diary diary,
			@RequestParam("uploadFile") MultipartFile[] multifiles, 
			HttpServletRequest req)	//모델에 담긴 내용 받아오기
	{	//addDiary 에서 제출한 내용 처리할 메서드
		System.out.println("DiaryController - submitDiaryForm in");
		//다이어리 아이디 주기
		Member mb = (Member)req.getSession().getAttribute("member");
		diary.setUserId(mb.getUserId());
		System.out.println("유저 아이디 넣어주기 : "+ diary.getUserId());
		diary.setDiaryId(System.currentTimeMillis());
		System.out.println("다이어리 아이디 넣어주기 : "+ diary.getDiaryId());
		
		diary.setVisit_date(req.getParameter("visit_date"));
		diary.setVisit_location(req.getParameter("visit_location"));
		diary.setAddress(req.getParameter("address"));
		diary.setVisit_diary(req.getParameter("visit_diary"));
		diary.setIsopen(req.getParameter("isopen"));
		
		//이미지 처리....
		String[] files = new String[4];	//나중에 파일이름 짓고나면 여기에 저장할 것

		//공통으로 쓸 것 : 저장경로
		String path = "/resources/images";
		String savepath = req.getServletContext().getRealPath(path);
		System.out.println(savepath);
		//공통2 : 새 파일 작성할 변수
		File savefile;
		
		for(int i = 0; i<multifiles.length; i++)
		{
			if(multifiles[i] != null && !(multifiles[i].isEmpty()) )
			{
				System.out.println("addDiary "+i +"번째 이미지가 있습니다.");
				// 실제 파일이름 받아오기
				String file_name = multifiles[i].getOriginalFilename();
				System.out.println(i+"번째 " +file_name);
				// 근데 이거 확장자명이 4자리 일수도 있음 : 확장자명을 내가 줘야 함
				// 스플릿 써서 . 앞까지 잘라냄
				String[] namearr = file_name.split("\\.");
				System.out.println(namearr);
				String filename = namearr[0];
				System.out.println(i +"번째 파일명은 : "+filename);
				
				// 저장을 위한 파일이름 지어주기
				String savename = diary.getDiaryId()+filename+".png" ;
				System.out.println("savename : "+savename);
				
				// 파일 작성하기
				savefile = new File(savepath, savename);
				try 
				{
					multifiles[i].transferTo(savefile);
					System.out.println("addDiary 에서 이미지 저장완료");
				} 
				catch (Exception e) 
				{
					System.out.println("addDiary 에서 이미지 저장 에러에러");
					e.printStackTrace();
				}
				
				// 이제 파일명만 저장하면 되는데
				// 위에 있는 파일명 배열에 도로 넣어보자
				files[i] = savename ;
				System.out.println("새로운 이미지 이름 덮어쓰기 완료 : "+i);
			}
			else
			{
				String savename = "00000000diary.png";
				files[i] = savename ;
			}
		}
		// for문 끝나고나면 이제 배열에 있는거 하나씩 꺼내서 dto 파일이름 저장하기
		diary.setFilename0(files[0]);
		diary.setFilename1(files[1]);
		diary.setFilename2(files[2]);
		diary.setFilename3(files[3]);
		
		diaryService.setNewDiary(diary);	//제출받은거 등록함수에게 주기
		System.out.println("리파지토리 갔다가 컨트롤러 돌아옴");
		
		return "redirect:/diaries/diary/"+diary.getDiaryId() ;
	}
	
	@GetMapping("/updateDiary")
	public String updateDiary(@ModelAttribute Diary diary, @RequestParam("id") long diaryId, Model model)
	{																						//다이어리 아이디로 찾은 해당 다이어리 정보 담을 모델
		System.out.println("DiaryController - updateDiary in");
		System.out.println("수정할 다이어리 아이디 받아옴 : "+diaryId);
		Diary diaryById = diaryService.getDiaryById(diaryId);
		System.out.println("다이어리 찾아옴 수정페이지로 이동.. 하기전에 다이어리 정보 모델에 넣기");
		model.addAttribute("diaryById",diaryById);
		return "diary/updateDiary";
	}
	
	@PostMapping("/updateDiary")
	public String submitUpdateDiary(@ModelAttribute Diary diary, 
			@RequestParam("uploadFile") MultipartFile[] multifiles, HttpServletRequest req)
	{
		System.out.println("DiaryController - submitUpdateDiary in");

//		MultipartFile picture = diary.getPicture();	//다이어리에서 파일 받아오기
//		
//		//유효성 검사
//		if( picture!=null && !picture.isEmpty())
//		{
//			System.out.println("submitDiaryForm 사진이 있습니다.");
//			String savepath = request.getServletContext().getRealPath("/resources/images/");	//저장소 경로 받아오기
//			System.out.println(savepath);
//			String savename = picture.getOriginalFilename();	//파일 이름 받아오기
//			System.out.println("파일이름은 : "+savename);
//			File savefile = new File(savepath, savename);	//변수에 저장해둔 경로+ 저장된 이름으로 새 파일 생성
//			System.out.println("submitDiaryForm 파일 저장하기");
//			try
//			{
//				picture.transferTo(savefile);	//사진을 savepath 경로에 업로드
//				diary.setFilename(savename);
//				System.out.println("submitDiaryForm 파일 업로드 완료 !");
//			}
//			catch(Exception e)
//			{
//				System.out.println("submitDiaryForm 파일 작성 에러에러");
//			}
//		}
		
		//이미지 처리....
		String[] files = new String[4];	//나중에 파일이름 짓고나면 여기에 저장할 것

		//공통으로 쓸 것 : 저장경로
		String path = "/resources/images";
		String savepath = req.getServletContext().getRealPath(path);
		System.out.println(savepath);
		//공통2 : 새 파일 작성할 변수
		File savefile;
		
		for(int i = 0; i<multifiles.length; i++)
		{
			if(multifiles[i] != null && !(multifiles[i].isEmpty()) )
			{
				System.out.println("addDiary "+i +"번째 이미지가 있습니다.");
				// 실제 파일이름 받아오기
				String file_name = multifiles[i].getOriginalFilename();
				System.out.println(i+"번째 " +file_name);
				// 근데 이거 확장자명이 4자리 일수도 있음 : 확장자명을 내가 줘야 함
				// 스플릿 써서 . 앞까지 잘라냄
				String[] namearr = file_name.split("\\.");
				System.out.println(namearr);
				String filename = namearr[0];
				System.out.println(i +"번째 파일명은 : "+filename);
				
				// 저장을 위한 파일이름 지어주기
				String savename = diary.getDiaryId()+filename+".png" ;
				System.out.println("savename : "+savename);
				
				// 파일 작성하기
				savefile = new File(savepath, savename);
				try 
				{
					multifiles[i].transferTo(savefile);
					System.out.println("addDiary 에서 이미지 저장완료");
				} 
				catch (Exception e) 
				{
					System.out.println("addDiary 에서 이미지 저장 에러에러");
					e.printStackTrace();
				}
				
				// 이제 파일명만 저장하면 되는데
				// 위에 있는 파일명 배열에 도로 넣어보자
				files[i] = savename ;
				System.out.println("새로운 이미지 이름 덮어쓰기 완료 : "+i);
			}
			else
			{
				String savename = "00000000diary.png";
				files[i] = savename ;
			}
		}
		// for문 끝나고나면 이제 배열에 있는거 하나씩 꺼내서 dto 파일이름 저장하기
		diary.setFilename0(files[0]);
		diary.setFilename1(files[1]);
		diary.setFilename2(files[2]);
		diary.setFilename3(files[3]);
		
		diaryService.setUpdateDiary(diary);

		return "redirect:/diaries/diary/"+diary.getDiaryId();
	}
	
	@GetMapping("/deleteDiary")
	public String deleteDiary(@ModelAttribute Diary diary, @RequestParam("id") long diaryId)
	{
		diaryService.deleteDiary(diaryId);
		return "redirect:/diaries";
	}

	
}
