package kr.or.ddit.iteams.outs.board.projectboard.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.iteams.common.vo.BaseVO;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.outs.board.projectboard.service.ProjectApprovalService;
import kr.or.ddit.vo.SearchVO;

@Controller
public class ProjectApprovalRetrieveController {

	@Inject
	private ProjectApprovalService service;
	
	@RequestMapping("/outs/board/projectboard/approvalView.do")
	public String approveView(@RequestParam("what") String number, Model model) {
		
		MasterVO vo = service.retrieveProject(number);
		
		model.addAttribute("vo", vo);
		model.addAttribute("number", number);
		
		return "outs/board/projectboard/projectApprovalView";
	}
	
	
	@RequestMapping("/outs/admin/board/projectboard/approvalList.do")
	public String approveList(
		@ModelAttribute("base") MasterVO masterVO
		, Model model
	) {
		
		masterVO.setCurrentPage(masterVO.getPage());	
		service.retrieveProjectList(masterVO);
		
		model.addAttribute("base", masterVO);
		
		//return "outs/board/projectboard/projectApprovalList"; 
		return "outs/board/projectboard/adminApprovalList";
	}
}
