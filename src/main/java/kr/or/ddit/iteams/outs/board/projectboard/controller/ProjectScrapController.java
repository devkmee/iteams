package kr.or.ddit.iteams.outs.board.projectboard.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.annotations.Authenticated;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.outs.board.projectboard.service.ProjectScrapService;
import kr.or.ddit.vo.SearchVO;

@Controller
public class ProjectScrapController {

	@Inject
	private ProjectScrapService service;
	
	@RequestMapping(value="/outs/board/projectboard/projectScrap.do", method=RequestMethod.POST)
	public String scrap(
		@ModelAttribute("vo") MasterVO vo, 
		@Authenticated MasterVO master, RedirectAttributes redirectAttributes
	) {
		String memId = master.getMemId();
		String message = null;
		
		vo.setMemId(memId);
		vo.setDevId(memId);
		
		ServiceResult result = service.scrap(vo);
		
		switch(result) {
			case OK:
				message = "스크랩 완료!";
				break;
			case PKDUPLICATED :
				message = "이미 스크랩한 게시물입니다.";
				break;
			default:
				message = "스크랩 실패! 고객센터에 문의하세요.";
				break;
		}
		
		redirectAttributes.addFlashAttribute("message", message);
		
		return "redirect:/outs/board/projectboard/projectView.do?what="+vo.getBoNum();
	}
	
	@RequestMapping(value="/outs/board/projectboard/projectUnscrap.do", method=RequestMethod.POST)
	public String unscrap(
		@ModelAttribute("vo") MasterVO vo, 
		@Authenticated MasterVO master, RedirectAttributes redirectAttributes
	) {
		String memId = master.getMemId();
		String message = null;
		
		vo.setMemId(memId);
		vo.setDevId(memId);
		
		ServiceResult result = service.unscrap(vo);
		
		switch(result) {
			case OK:
				message = "스크랩 해제 완료!";
				break;
			default:
				message = "스크랩 해제 실패! 고객센터에 문의하세요.";
				break;
		}
		
		redirectAttributes.addFlashAttribute("message", message);
		
		return "redirect:/outs/board/projectboard/scrapList.do";
	}
	
	@RequestMapping("/outs/board/projectboard/scrapList.do")
	public String scrapList(
		@RequestParam(value="page", required=false, defaultValue="1") int currentPage
		, @ModelAttribute("searchVO") SearchVO searchVO
		, Model model, @Authenticated MasterVO master
	) {
		String memId = master.getMemId();
		
		MasterVO vo = new MasterVO();
		vo.setCurrentPage(currentPage);
		vo.setSearchVO(searchVO);
		vo.setMemId(memId);
		vo.setDevId(memId);
		
		service.retrieveProjectList(vo);
		
		model.addAttribute("base", vo);
		
		return "outs/board/projectboard/projectScrapList"; 
	}

	
	@RequestMapping("/outs/board/projectboard/scrapView.do")
	public String scrapView(@RequestParam("what") String number, Model model) {
		
		MasterVO vo = service.retrieveProject(number); 
		
		model.addAttribute("vo", vo);
		model.addAttribute("number", number);
		
		return "outs/board/projectboard/projectScrapView"; 
	}
	
}
