package kr.or.ddit.iteams.outs.board.free.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.outs.board.free.service.FreeBoardService;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.validate.groups.board.free.FreeBoardUpdateGroup;
import kr.or.ddit.vo.BoardVO;

@Controller
@RequestMapping("outs/board/boardUpdate.do")
public class FreeBoardUpdateController {
	@Inject
	private FreeBoardService service;

	@RequestMapping
	public String getController(@ModelAttribute MasterVO masterVO, Model model) {
		masterVO.setBoNum(masterVO.getWhat());
		MasterVO board = service.retrieveBoard(masterVO);
		model.addAttribute("board", board);
		return "outs/board/free/boardEdit";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String postController(
		@Validated(FreeBoardUpdateGroup.class) @ModelAttribute("board") MasterVO board,
		Errors errors
		, RedirectAttributes reAttr
	) throws Exception {
		String viewName = null;
		String message = null;
			board.setBoNum(board.getWhat());
			service.modifyBoard(board);
			ServiceResult result = board.getResult();
			
			if(!errors.hasErrors()) {
				if(result == ServiceResult.OK) {
					message = "게시글을 성공적으로 수정했습니다.";
				} else {
					message = "게시글 수정에 실패했습니다.";				
				}
			}
		
		reAttr.addFlashAttribute("message", message);
		viewName = "redirect:/outs/board/free/freeBoardDetail.do?what="+board.getBoNum();
		return viewName;
	}
}
