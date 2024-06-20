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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.annotations.Authenticated;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.outs.board.qna.service.QNAService;
import kr.or.ddit.validate.groups.board.AnswerUpdateGroup;
import kr.or.ddit.validate.groups.board.BoardUpdateGroup;
import kr.or.ddit.validate.groups.board.QNAUpdateGroup;

@Controller
public class QnaUpdateController {
	
	@Inject
	private QNAService service;
	
	@RequestMapping(value="/outs/board/qna/qnaUpdate.do", method=RequestMethod.GET)
	public String goQuestionForm(
			@RequestParam("boNum") String boNum,
			Model model,
			RedirectAttributes redirect
			) throws Exception {
		MasterVO board = new MasterVO();
		board.setBoNum(boNum);	
		String message = null;
		String viewName = null;
		MasterVO saved = new MasterVO();
		saved.setBoNum(boNum);
		service.selectCheckAnswer(board);
		if(board.getResult() == ServiceResult.HAVECHILD) {
			message = "답변이 달린 질문은 수정 할 수 없습니다";
			viewName = "redirect:/outs/board/qna/qnaView.do?boNum="+board.getBoNum();
			redirect.addFlashAttribute("message", message);
		}else {
			viewName = "outs/board/qna/QNAEditForm";

			service.selectQnA(saved);
		}
		model.addAttribute("board", saved);
		return viewName;
	}
	
	@RequestMapping(value="/outs/board/qna/qnaUpdate.do", method=RequestMethod.POST)
	public String doProcess(@Authenticated MasterVO member,
			@Validated(QNAUpdateGroup.class) @ModelAttribute MasterVO board,
			Errors errors,
			Model model
			) throws Exception {
		String message = null;
		String viewName = null;
		board.setMemId(member.getMemId());
		
		if(!errors.hasErrors()) {
			service.updateQuestion(board);
			switch(board.getResult()) {
				case OK: 
					viewName = "redirect:/outs/board/qna/qnaView.do?boNum="+board.getBoNum();
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
	
	
	
	
	
	@RequestMapping(value="/outs/admin/board/qna/AnswerUpdate.do", method=RequestMethod.GET)
	public String goAnswerForm(
			@RequestParam("boNum") String boNum,
			Model model
			) throws IllegalAccessException, InvocationTargetException {
		MasterVO board = new MasterVO();
		board.setBoNum(boNum);
		service.selectQnA(board);
		model.addAttribute("board", board);
		
		return "outs/board/qna/AnserEditForm";
	}
	
	@RequestMapping(value="/outs/admin/board/qna/AnswerUpdate.do", method=RequestMethod.POST)
	public String updateAnswer(
			@Authenticated MasterVO member,
			@Validated(AnswerUpdateGroup.class) @ModelAttribute MasterVO board,
			Errors errors,
			Model model
			) throws Exception {
		String message = null;
		String viewName = null;
		board.setMemId(member.getMemId());
		
		if(!errors.hasErrors()) {
			service.updateAnswer(board);
			switch(board.getResult()) {
				case OK: 
					viewName = "redirect:/outs/board/qna/qnaView.do?boNum="+board.getBoNum();
					break;
				default:
					viewName = "outs/board/qna/AnswerForm";
					message = "서버 오류 잠시 후 다시 시도해주세요";
			}
		}else {
			viewName = "outs/board/qna/AnserEditForm";
			message = "내용을 입력해주세요";
		}
		model.addAttribute("message", message);
		model.addAttribute("board", board);
		return viewName;
	}
}
