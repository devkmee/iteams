package kr.or.ddit.iteams.outs.board.qna.controller;

import java.lang.reflect.InvocationTargetException;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.annotations.Authenticated;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.SearchWordCollector;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.outs.board.qna.dao.QNADAO;
import kr.or.ddit.iteams.outs.board.qna.service.QNAService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class QnaRetrieveController {
	
	@Inject
	private QNAService service;
	
	@RequestMapping("/outs/board/qna/qnaList.do")
	public String selectQNAList(
		@RequestParam(name="page", required=false, defaultValue="1") int currentPage,
		@RequestParam(name="qnaKeyword", required=false) String qnaKeyword,
		@RequestParam(name="memId", required=false) String memId,
		Model model
		, @Authenticated MasterVO authMember
		, HttpSession session
		) throws IllegalAccessException, InvocationTargetException {
	MasterVO board = new MasterVO();
	board.setCurrentPage(currentPage);
	board.setDetailSearch(qnaKeyword);
	if(StringUtils.isNotBlank(memId)) board.setMemId(memId);
	
	service.selectQnAList(board);

	model.addAttribute("board", board);
	SearchWordCollector.makeSearchVO();
//	MasterVO search =  (MasterVO) session.getAttribute("searchVO");
//	search.setMemId("");
	  return "outs/board/qna/QNAList";
	}
	
	
	
	
	@RequestMapping("/outs/board/qna/qnaView.do")
	public String selectBoard(
			@Authenticated MasterVO member,
			@RequestParam("boNum") String boNum,
			RedirectAttributes redirect,
			Model model
			) throws IllegalAccessException, InvocationTargetException {
		String viewName = null;
		String message = null;
		
		MasterVO board = new MasterVO();
		board.setBoNum(boNum);
		
		if(member != null) {
			member.setBoNum(boNum);
			ServiceResult auth = service.selectCheckAuth(member);
			switch(auth) {
				case OK:
					service.selectQnA(board);
					viewName = "outs/board/qna/QNAView";
					model.addAttribute("board", board);
					break;
				case FAILED:
					message = "작성자만 열람 할 수 있습니다.";
					viewName = "redirect:/outs/board/qna/qnaList.do";
					redirect.addFlashAttribute("message", message);
					break;
			}
		}else {
			message = "비회원은 열람 할 수 없습니다.";
			viewName = "redirect:/outs/board/qna/qnaList.do";
			redirect.addFlashAttribute("message", message);
		}
		return viewName;
	}
	
	
	
	
	
}
