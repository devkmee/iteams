package kr.or.ddit.iteams.outs.member.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.annotations.Authenticated;
import kr.or.ddit.iteams.common.vo.BaseVO;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.common.vo.MemberTotalVO;
import kr.or.ddit.iteams.outs.hire.service.ApplyProjectService;
import kr.or.ddit.iteams.outs.hire.service.PublicHireService;
import kr.or.ddit.iteams.outs.member.service.MemberService;
import kr.or.ddit.iteams.outs.member.service.MyboardService;
import kr.or.ddit.vo.SearchVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MemberRetrieveController {
	
	@Inject
	private MemberService service;
	
	@Inject
	private MyboardService boardService;
	
	@Inject
	private ApplyProjectService applyService;
	
	@Inject
	private PublicHireService publicService;
	
	
	@RequestMapping("/outs/member/mypageDevInfo.do")
	public String devMyInfo(
			Model model,
			@Authenticated MasterVO master
			) {
		String memId = master.getMemId(); 
		
		MasterVO devInfo = service.selectDevInfo(memId);
		model.addAttribute("devInfo", devInfo);
		
		MasterVO att = service.selectDevAtt(memId);
		model.addAttribute("att", att);
		
		return "outs/member/devMyInfo";
	}
	
	@RequestMapping("/outs/member/mypageBoard.do")
	public String devMyboard(
			Model model,
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage,
			@Authenticated MasterVO member
			) {
		member.setCurrentPage(currentPage);
		
		boardService.selectMyBoardList(member);
		model.addAttribute("data", member);
		
		return "outs/member/devMyboard";
	}
	
	@RequestMapping("/outs/member/mypageApplyInvite.do")
	public String devApplyInvite(
			Model model,
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage,
			@Authenticated MasterVO master
			) {
		String memId = master.getMemId(); 
		
		MasterVO applyPro = new MasterVO(); 
		applyPro.setAppId(memId);
		applyPro.setCurrentPage(currentPage);
		service.selectDevPro(applyPro);
		model.addAttribute("applyPro", applyPro);
		
		MasterVO dev = new MasterVO();
		dev.setMemId(memId);
		dev.setCurrentPage(currentPage);
		service.selectInviteListForDev(dev); 
		model.addAttribute("data", dev);
		return "outs/member/devApplyInvite";
	}
	
	
	@RequestMapping("/outs/member/mypageOngoingEnd.do")
	public String devOngoingEnd(
			Model model,
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage,
			@Authenticated MasterVO master
			) {
		String memId = master.getMemId(); 
		
		MasterVO ongoing = new MasterVO();
		ongoing.setCurrentPage(currentPage);
		ongoing.setMemId(memId); 
		service.selectOngoingProject(ongoing); 
		model.addAttribute("ongoing", ongoing); 
		
		MasterVO end = new MasterVO();
		end.setMemId(memId);
		end.setCurrentPage(currentPage);
		service.selectEndProject(end);
		model.addAttribute("end", end);
		return "outs/member/devOngoingEnd";
	}
		
	
	
//	public String mypageDev(
//		Model model,
//		@Authenticated MasterVO master,
//		@RequestParam(value="page1", required=false, defaultValue="1") int currentPage1,
//		@RequestParam(value="page2", required=false, defaultValue="1") int currentPage2,
//		@RequestParam(value="page3", required=false, defaultValue="1") int currentPage3,
//		@RequestParam(value="page4", required=false, defaultValue="1") int currentPage4
//	) {
//		String memId = master.getMemId(); 
//		
//		MasterVO devInfo = service.selectDevInfo(memId);
//		model.addAttribute("devInfo", devInfo);
//
//		MasterVO applyPro = new MasterVO(); 
//		applyPro.setAppId(memId);
//		applyPro.setCurrentPage(currentPage1);
//		service.selectDevPro(applyPro);
//		model.addAttribute("applyPro", applyPro);
//		
//		MasterVO dev = new MasterVO();
//		dev.setMemId(memId);
//		dev.setCurrentPage(currentPage2);
//		service.selectInviteListForDev(dev); 
//		model.addAttribute("data", dev);
//		
//		MasterVO ongoing = new MasterVO(); 
//		ongoing.setMemId(memId); 
//		ongoing.setCurrentPage(currentPage3); 
//		service.selectOngoingProject(ongoing); 
//		model.addAttribute("ongoing", ongoing); 
//		
//		MasterVO end = new MasterVO();
//		end.setMemId(memId);
//		end.setCurrentPage(currentPage4);
//		service.selectEndProject(end);
//		model.addAttribute("end", end);
//		
//		MasterVO att = service.selectDevAtt(memId);
//		model.addAttribute("att", att);
//		
//		return "outs/member/mypageDev";
//	}
	
	
	
	@RequestMapping("/outs/member/mypageCllientInfo.do")
	public String clientMyInfo(
			Model model,
			@Authenticated MasterVO master
			) {
		String memId = master.getMemId(); 
		
		MasterVO clientInfo = service.selectClientInfo(memId); 
		model.addAttribute("clientInfo", clientInfo);
		
		return "outs/member/clientMyInfo";
	}
	
	@RequestMapping("/outs/member/mypageClientBoard.do")
	public String clientMyboard(
			Model model,
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage,
			@Authenticated MasterVO member
			) {
		member.setCurrentPage(currentPage);
		
		boardService.selectMyBoardList(member);
		model.addAttribute("data", member);
		
		return "outs/member/clientMyboard";
	}
	
	@RequestMapping("/outs/member/mypageClientHire.do")
	public String clientHireProject(
			Model model,
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage,
			@Authenticated MasterVO member
			) {
		String memId = member.getMemId(); 
		
		MasterVO board = new MasterVO();
		board.setScreenSize(6);
		board.setCurrentPage(currentPage);
		board.setMemId(memId); 
		
		applyService.selectProBoardListForClient(board);
		model.addAttribute("board", board);
		
		
		MasterVO invite = new MasterVO();
		invite.setMemId(memId);
		publicService.selectInviteListForClient(invite);
		model.addAttribute("invite", invite);
		
		return "outs/member/clientHire";
	}
	
	@RequestMapping("/outs/member/mypageClientEnd.do")
	public String clientEnd(
			Model model,
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage,
			@Authenticated MasterVO master
			) {
		String memId = master.getMemId(); 
		
		MasterVO end = new MasterVO();
		end.setMemId(memId);
		end.setCurrentPage(currentPage);
		service.selectEndProject(end);
		model.addAttribute("end", end);
		return "outs/member/clientEnd";
	}
	
	
	
	
	
	
	
//	@RequestMapping("/outs/member/mypageClient.do") 
//	public String mypageClient( 
//		@Authenticated MasterVO master, 
//		Model model 
//	) { 
//		String memId = master.getMemId(); 
//		
//		MasterVO board = new MasterVO(); 
//		board.setScreenSize(6); 
//		board.setCurrentPage(1); 
//		board.setMemId(memId); 
//		
//		service.selectBoardForClient(board); 
//		model.addAttribute("board", board); 
//
//		MasterVO clientInfo = service.selectClientInfo(memId); 
//		model.addAttribute("clientInfo", clientInfo);
//		
//		return "outs/member/mypageClient"; 
//	} 
	
}
