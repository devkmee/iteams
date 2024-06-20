package kr.or.ddit.iteams.common.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.common.vo.MemberTotalVO;
import kr.or.ddit.iteams.common.vo.PagingVO;
import kr.or.ddit.iteams.outs.board.free.service.FreeBoardService;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.SearchVO;
import lombok.extern.slf4j.Slf4j;
@Controller
@RequestMapping("/outs/board/rep")
@Slf4j
public class RepBoardController {
	@Inject
	private FreeBoardService service; 
	
	@RequestMapping("communityMain.do")
	public String communityMain() {
		return "outs/board/free/communityMain";
		
	}
	
//	@RequestMapping("repBoardList.do")
//	public String freeBoard(
//			@RequestParam(value="page", required=false, defaultValue="1") int currentPage
//			, @ModelAttribute("searchVO") SearchVO searchVO
//			, Model model
//		) {
//			
//			PagingVO<BoardVO> pagingVO = new PagingVO<>();
//			pagingVO.setCurrentPage(currentPage);
//			pagingVO.setSearchVO(searchVO);
//			
//			service.retrieveBoardList(pagingVO);
//			
//			model.addAttribute("pagingVO", pagingVO);
//			
//		return "outs/board/free/freeBoardList";
//		
//	}
//	
//	@RequestMapping("freeBoardDetail.do")
//	public String freeBoardDetail(
//			@RequestParam("what") int boNum
//			, Model model
//		) {
//			BoardVO board = service.retrieveBoard(boNum);
//			
//			model.addAttribute("board", board);
//			
//			return "outs/board/free/freeBoardDetail";
//		}
	
	
	
}
