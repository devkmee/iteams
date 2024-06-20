package kr.or.ddit.iteams.outs.board.news.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.iteams.common.SearchWordCollector;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.outs.board.news.service.NewsBoardService;

@Controller
public class NewsRetrieveController {
	
	@Inject 
	private NewsBoardService service;
	
	@RequestMapping("/outs/board/news/newsList.do")
	public String NewsBoardList( 
			@RequestParam(value="page", required=false, defaultValue="1")int currentPage
			, @ModelAttribute("masterVO") MasterVO masterVO
			, Model model
			) throws IllegalAccessException, InvocationTargetException   {
		
		masterVO.setCurrentPage(masterVO.getPage());
		service.NewsBoardList(masterVO);
		
		List<MasterVO> list = service.NewsBoardList(masterVO);
		
		model.addAttribute("list", list);
		SearchWordCollector.makeSearchVO();
		
		return "outs/board/news/newsList";
	}
	
	// 상세보기
	@RequestMapping("/outs/board/news/newsView.do")
	public String NewsBoardDetail(
			@RequestParam("what") String boNum
			, Model model
			) {
		
		int number = Integer.parseInt(boNum);
		
		MasterVO board = service.retrieveNews(number);
		
		model.addAttribute("board", board);
		
		return "outs/board/news/newsView";
	}
	
}


