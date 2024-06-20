package kr.or.ddit.iteams.outs.board.free.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.outs.board.free.service.FreeBoardService;
import kr.or.ddit.vo.BoardVO;

@Controller
public class FreeBoardDeleteController {
	@Inject
	private FreeBoardService service;
	
	@RequestMapping(value="outs/board/boardDelete.do")
	public String delete(
		@RequestParam("what") String boNum,
		 RedirectAttributes redirectAttributes
	) throws Exception {
		MasterVO board = new MasterVO();
		board.setBoNum(boNum);
		
		ServiceResult result = service.removeBoard(board);
		String viewName = null;
			viewName = "redirect:/outs/board/free/freeBoardList.do";
			if(result == ServiceResult.OK) {
				redirectAttributes.addFlashAttribute("message", "게시글을 삭제했습니다.");				
			} else {
				redirectAttributes.addFlashAttribute("message", "게시글 삭제에 실패했습니다.");
			}
		return viewName;
	}
}
















