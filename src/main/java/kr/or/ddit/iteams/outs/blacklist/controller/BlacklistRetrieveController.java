package kr.or.ddit.iteams.outs.blacklist.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.common.vo.MemberTotalVO;
import kr.or.ddit.iteams.outs.blacklist.service.BlacklistService;
import kr.or.ddit.vo.SearchVO;

@Controller
public class BlacklistRetrieveController {
	
	@Inject
	private BlacklistService service;

	@RequestMapping("/outs/admin/blacklist/blackList.do")
	public String getList(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage1,
			Model model
			) throws ServletException, IOException {
		
		MasterVO member = new MasterVO();
		member.setCurrentPage(currentPage1);
		
		service.selectBlackList(member);
		model.addAttribute("member", member);
		
		return "outs/blacklist/adminBlackList";
	}
	
	@RequestMapping("/outs/admin/blacklist/repBoardList.do")
	public String getRepList(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage,
			Model model
			) throws ServletException, IOException {
		
		MasterVO rep = new MasterVO();
		rep.setCurrentPage(currentPage);
		
		service.selectRepBoardList(rep);
		model.addAttribute("rep", rep);
		
		return "outs/blacklist/adminRepList";
	}
	
	
	
	
	
	
}
