package kr.or.ddit.iteams.outs.board.free.controller;

import javax.inject.Inject;

import org.springframework.aop.support.AopUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.annotations.Authenticated;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.outs.board.free.service.FreeBoardService;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.validate.groups.board.free.FreeBoardInsertGroup;
import kr.or.ddit.vo.BoardVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/outs/board/free/boardInsert.do")
public class FreeBoardInsertController {
	
	private FreeBoardService service;
	@Inject
	public void setService(FreeBoardService service) {
		this.service = service;
		log.info("주입된 target : {}, proxy 여부 : {}", service.getClass().getName(), AopUtils.isAopProxy(service));
	}

	@RequestMapping
	public String getController( ) {
		return "outs/board/free/boardForm";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String postController(
		@Validated(FreeBoardInsertGroup.class) @ModelAttribute("board") MasterVO board
		, Errors errors
		, Model model
		, @Authenticated MasterVO authMember
	) throws Exception {
		String viewName = null;
		String message = null;
		board.setMemId(authMember.getMemId());
		if(!errors.hasErrors()) {
			service.createBoard(board);
			ServiceResult result = board.getResult();
			switch(result) {
			case OK:
				viewName = "redirect:/outs/board/free/freeBoardDetail.do?what="+board.getBoNum();
				break;
			default:
				viewName = "outs/board/free/boardForm";
				message = "서버 오류, 잠시뒤 다시 해보셈.";
			}
			
		}else {
			viewName = "outs/board/free/boardForm";
			
		}
		
		model.addAttribute("message", message);
		
		return viewName;
	}
}
