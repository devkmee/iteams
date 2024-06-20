package kr.or.ddit.iteams.outs.board.projectboard.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.outs.board.projectboard.service.OutsProjectService;

@Controller
public class OutsProjectDeleteController {
	@Inject
	private OutsProjectService service;
	
	@RequestMapping("/outs/board/projectboard/projectDelete.do")
	public String delete(
		@RequestParam("what") String boNum,
		RedirectAttributes redirectAttributes 
	) { 
		MasterVO vo = new MasterVO();
		vo.setBoNum(boNum);
		
		ServiceResult result = service.removeProject(vo);
		
		String viewName = null;
		String message = null;
		
		switch(result) {
		case OK:
			viewName = "redirect:/outs/board/projectboard/projectList.do";
			message = "삭제되었습니다.";
			break;
			
		default:
			viewName = "outs/board/projectboard/projectBoardList";
			message = "서버 오류, 잠시후 다시 시도 해보세요.";
		}
		
		redirectAttributes.addFlashAttribute("message", message);
		
		return viewName;
	}
	
}
