package kr.or.ddit.iteams.outs.board.qna.controller;

import java.lang.reflect.InvocationTargetException;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.annotations.Authenticated;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.outs.board.notice.service.NoticeBoardService;
import kr.or.ddit.iteams.outs.board.qna.service.FAQService;
import kr.or.ddit.validate.groups.board.BoardUpdateGroup;

@Controller
public class FAQUpdateController {
	
	@Inject
	private FAQService service;
	
	@RequestMapping(value="/outs/admin/board/qna/faqUpdate.do", method=RequestMethod.GET)
	public String getFrom(
			@RequestParam("boNum") String boNum,
			Model model
			) throws IllegalAccessException, InvocationTargetException {
		MasterVO board = new MasterVO();
		board.setBoNum(boNum);
		service.selectFAQ(board);
		model.addAttribute("board", board);
		
		return "outs/board/qna/FAQEditForm";
	}
	
	@RequestMapping(value="/outs/admin/board/qna/faqUpdate.do", method=RequestMethod.POST)
	public String doProcess(
			@Authenticated MasterVO member,
			@Validated(BoardUpdateGroup.class) @ModelAttribute MasterVO board,
			Errors errors,
			Model model
			) throws Exception {
		String message = null;
		String viewName = null;
		board.setMemId(member.getMemId());
		
		if(!errors.hasErrors()) {
			service.updateFAQ(board);
			switch(board.getResult()) {
				case OK: 
					viewName = "redirect:/outs/board/qna/faqView.do?boNum="+board.getBoNum();
					break;
				default:
					viewName = "outs/board/qna/FAQEditForm";
					message = "서버 오류 잠시 후 다시 시도해주세요";
			}
		}else {
			viewName = "outs/board/qna/FAQEditForm";
			message = "제목과 내용을 입력해주세요";
		}
		model.addAttribute("message", message);
		model.addAttribute("board", board);
		return viewName;
	}
	
}
