package kr.or.ddit.iteams.outs.board.interview.controller;

import java.lang.reflect.InvocationTargetException;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.iteams.common.SearchWordCollector;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.outs.board.interview.service.InterviewService;
import kr.or.ddit.vo.BoardVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class InterviewRetrieveController {
	//의존관계 주입 : InterviewImpl 생성
	//IoC 의존관계역전
	@Inject
	private InterviewService service;
	
	@RequestMapping(value="/outs/board/interview/interviewList.do", method=RequestMethod.GET)
	public String interviewList(
		 @ModelAttribute("masterVO") MasterVO masterVO
			, Model model
		) throws IllegalAccessException, InvocationTargetException {
			
		masterVO.setScreenSize(9);
		masterVO.setCurrentPage(masterVO.getPage());
		service.retrieveBoardList(masterVO);
		
		SearchWordCollector.makeSearchVO();
		
		model.addAttribute("master", masterVO);
		return "outs/board/interview/interviewList";
	}
	
	//게시글 상세보기
	@RequestMapping("/outs/board/interview/interviewView.do")
	public String interviewView(
			@RequestParam("what") int boNum
			, Model model) {
		
		BoardVO board = service.retrieveBoard(boNum);
		
		model.addAttribute("board", board);
		
		return "outs/board/interview/interviewView";
	}
	
}
