package kr.or.ddit.iteams.outs.board.qna.controller;

import java.lang.reflect.InvocationTargetException;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.iteams.common.SearchWordCollector;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.common.vo.MemberTotalVO;
import kr.or.ddit.iteams.outs.board.qna.service.FAQService;
import kr.or.ddit.vo.SearchVO;

@Controller
public class FAQRetrieveController {
	
	@Inject
	private FAQService service;
	
	@RequestMapping("/outs/board/qna/faqList.do")
	public String selectFAQList(
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage,
			@RequestParam(name="qnaKeyword", required=false) String qnaKeyword,
			Model model
			) throws IllegalAccessException, InvocationTargetException {
		MasterVO board = new MasterVO();
		board.setCurrentPage(currentPage);
		board.setDetailSearch(qnaKeyword);
		
		service.selectFAQList(board);
		model.addAttribute("board", board);
		
		SearchWordCollector.makeSearchVO();
		
		return "outs/board/qna/FAQList";
	}
	
	
	
	@RequestMapping("/outs/board/qna/faqView.do")
	public String selectNotice(
			@RequestParam("boNum") String boNum,
			Model model
			) throws IllegalAccessException, InvocationTargetException {
		MasterVO board = new MasterVO();
		board.setBoNum(boNum);
		service.selectFAQ(board);
		model.addAttribute("board", board);
		return "outs/board/qna/FAQView";
	}

}
