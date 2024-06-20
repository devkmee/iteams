package kr.or.ddit.iteams.outs.board.notice.controller;

import java.lang.reflect.InvocationTargetException;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.annotations.Authenticated;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.outs.board.notice.service.NoticeBoardService;
import kr.or.ddit.vo.SearchVO;

@Controller
public class NoticeRetrieveController {
	
	@Inject
	private NoticeBoardService service;
	
	@RequestMapping("/outs/board/notice/noticeList.do")
	public String goboardList(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage,
			@ModelAttribute(value="searchVO") SearchVO searchVO,
			Model model
			) {
		MasterVO board = boardListForData(currentPage, searchVO);
		model.addAttribute("data", board);
		return "outs/board/notice/noticeList";
	}

	@RequestMapping(value="/outs/board/notice/noticeList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public MasterVO boardListForData(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage,
			@ModelAttribute(value="searchVO") SearchVO searchVO
			) {
		MasterVO board = new MasterVO();
		board.setCurrentPage(currentPage);
		board.setSearchVO(searchVO);
		service.selectBoardList(board);
		
		return board;
	}
	

	@RequestMapping("/outs/board/notice/noticeView.do")
	public String selectNotice(
			@RequestParam("boNum") String boNum,
			Model model
			) throws IllegalAccessException, InvocationTargetException {
		MasterVO board = new MasterVO();
		board.setBoNum(boNum);
		service.selectBoard(board);
		model.addAttribute("board", board);
		return "outs/board/notice/noticeView";
	}
	
}
