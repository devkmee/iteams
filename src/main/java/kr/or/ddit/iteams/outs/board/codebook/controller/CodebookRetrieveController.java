package kr.or.ddit.iteams.outs.board.codebook.controller;

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
import kr.or.ddit.iteams.outs.board.codebook.service.CodeBoardService;

@Controller
public class CodebookRetrieveController {
	
	@Inject
	private CodeBoardService service;
	
	@RequestMapping("/outs/board/codebook/codeList.do")
	public String CodeBoardList(
			 @RequestParam(value="page", required=false, defaultValue="1")int currentPage
	       , @ModelAttribute("masterVO") MasterVO masterVO
	       , Model model
			) throws IllegalAccessException, InvocationTargetException {
		
		masterVO.setCurrentPage(masterVO.getPage());
		service.CodeBoardList(masterVO);
		
		List<MasterVO> list = service.CodeBoardList(masterVO);
		
		model.addAttribute("list", list);
		SearchWordCollector.makeSearchVO();
		
		return "outs/board/codebook/codeList";
	}
	
	//게시물상세보기
	@RequestMapping("/outs/board/codebook/codeView.do")
	public String CodeBoardDetail(
			@RequestParam("what") String boNum
			, Model model
			) {
		
		int number = Integer.parseInt(boNum);
		MasterVO board = service.retrieveCode(number);
		model.addAttribute("board", board);
		return "outs/board/codebook/codeView";
	}
	
}
