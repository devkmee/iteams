package kr.or.ddit.iteams.outs.board.qna.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.annotations.Authenticated;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.outs.board.qna.service.QNAService;
import kr.or.ddit.validate.groups.board.BoardInsertGroup;
import kr.or.ddit.validate.groups.board.QNAInsertGroup;

@Controller
public class QnaInsertController {
	
	@Inject
	private QNAService service;
	
	@RequestMapping(value="/outs/board/qna/questionInsert.do", method=RequestMethod.GET)
	public String goQuestionForm() {
		return "outs/board/qna/FAQForm";
	}
	
	@RequestMapping(value="/outs/board/qna/questionInsert.do", method=RequestMethod.POST)
	public String doQuestionProcess(
		@Authenticated MasterVO member,
		@Validated(BoardInsertGroup.class) @ModelAttribute MasterVO board,
		Errors errors,
		Model model
		) throws Exception {
	String message = null;
	String viewName = null;
	board.setMemId(member.getMemId());
	if(!errors.hasErrors()) {
		service.insertQuestion(board);
		switch(board.getResult()) {
			case OK:
				viewName = "redirect:/outs/board/qna/qnaView.do?boNum="+board.getBoNum();
				break;
			default:
				viewName = "outs/board/qna/FAQForm";
				message = "서버 오류 잠시 후 다시 시도해주세요";
				break;
		}
	}else {
		viewName = "outs/board/qna/FAQForm";
		message = "제목과 내용을 기입해주세요";
	}
	model.addAttribute("message", message);
	model.addAttribute("board", board);
	
	return viewName;
	}
	
	
	
	@RequestMapping(value="/outs/board/qna/answerInsert.do", method=RequestMethod.GET)
	public String goAnswerForm() {
		return "outs/board/qna/AnswerForm";
	}
	
	@RequestMapping(value="/outs/board/qna/answerInsert.do", method=RequestMethod.POST)
	public String doAnswerProcess(
		@Authenticated MasterVO member,
		@Validated(QNAInsertGroup.class) @ModelAttribute MasterVO board,
		Errors errors,
		Model model
		) throws Exception {
	String message = null;
	String viewName = null;
	board.setMemId(member.getMemId());
	if(!errors.hasErrors()) {
		service.insertAnswer(board);
		switch(board.getResult()) {
			case OK:
				viewName = "redirect:/outs/board/qna/qnaView.do?boNum="+board.getBoNum();
				break;
			default:
				viewName = "outs/board/qna/AnswerForm";
				message = "서버 오류 잠시 후 다시 시도해주세요";
				break;
		}
	}else {
		viewName = "outs/board/qna/AnswerForm";
		message = "내용을 기입해주세요";
	}
	model.addAttribute("message", message);
	model.addAttribute("board", board);
	
	return viewName;
	}
	
}
