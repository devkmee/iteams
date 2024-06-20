package kr.or.ddit.iteams.outs.board.free.controller;

import java.lang.reflect.InvocationTargetException;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.SearchWordCollector;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.common.vo.MemberTotalVO;
import kr.or.ddit.iteams.common.vo.PagingVO;
import kr.or.ddit.iteams.outs.board.free.service.FreeBoardService;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.SearchVO;
import lombok.extern.slf4j.Slf4j;
@Controller
@Slf4j
public class FreeBoardController {
	@Inject
	private FreeBoardService service; 
	
	@RequestMapping("/outs/board/free/communityMain.do")
	public String communityMain() {
		return "outs/board/main/communityMain";
		
	}
	
	@RequestMapping(value="/outs/board/free/freeBoardList.do", method=RequestMethod.GET)
	public String freeBoard(
			 @ModelAttribute("masterVO") MasterVO masterVO
			, Model model
		) throws IllegalAccessException, InvocationTargetException {
			
			masterVO.setCurrentPage(masterVO.getPage());
			
			service.retrieveBoardList(masterVO);
			
			SearchWordCollector.makeSearchVO();
			
		return "outs/board/free/freeBoardList";
		
	}
	
	@RequestMapping("/outs/board/free/freeBoardDetail.do")
	public String freeBoardDetail(
			@ModelAttribute MasterVO masterVO
			, Model model
		) {
		
			masterVO.setBoNum(masterVO.getWhat());
			MasterVO board = service.retrieveBoard(masterVO);
			
			model.addAttribute("board", board);
			
			return "outs/board/free/freeBoardDetail";
		}
	
	
}
