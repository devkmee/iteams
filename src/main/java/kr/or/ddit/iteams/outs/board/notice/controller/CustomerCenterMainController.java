package kr.or.ddit.iteams.outs.board.notice.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.outs.board.notice.service.NoticeBoardService;
import kr.or.ddit.iteams.outs.board.qna.service.FAQService;

@Controller
public class CustomerCenterMainController {
	
	@Inject
	private FAQService service;
	
	@RequestMapping("/outs/board/customerMainList.do")
	public String goCustomerCenterMain(Model model) {
		
		MasterVO board = new MasterVO();
		board.setCurrentPage(1);
		service.selectFAQList(board);
		model.addAttribute("board", board);
		return "outs/board/main/customerCenterMain";
	}

}
