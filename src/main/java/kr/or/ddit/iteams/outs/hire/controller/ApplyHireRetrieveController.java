package kr.or.ddit.iteams.outs.hire.controller;

import java.lang.reflect.InvocationTargetException;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.annotations.Authenticated;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.common.vo.OutsTotalVO;
import kr.or.ddit.iteams.outs.hire.service.ApplyProjectService;
import kr.or.ddit.iteams.outs.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ApplyHireRetrieveController {
	
	@Inject
	private ApplyProjectService service;
	
	/**
	 * 마이페이지용 특정 개발자의 지원 프로젝트 목록
	 * @return
	 */
	@RequestMapping("/outs/mypage/hire/applyList.do")
	public String projectListForDev(
			@Authenticated MasterVO master,
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage,
			Model model
			) {
		MasterVO applyPro = new MasterVO();
		String appId = master.getMemId();
		applyPro.setAppId(appId);
		applyPro.setCurrentPage(currentPage);
		
		service.selectApplyListForDev(applyPro);
		model.addAttribute("applyPro", applyPro);
		
		return "outs/hire/hireListForDev";
	}
	
	/**
	 * 마이페이지용 특정 클라이언트의 최근 공고 프로젝트 목록
	 * @return
	 */
	@RequestMapping("/outs/mypage/hire/proList.do") 
	public String projectListForClient( 
		@Authenticated MasterVO master, 
		Model model 
	) { 
		String memId = master.getMemId(); 
		
		MasterVO board = new MasterVO(); 
		board.setScreenSize(6); 
		board.setCurrentPage(1); 
		board.setMemId(memId); 
		
		service.selectProBoardListForClient(board); 
		model.addAttribute("hire", board); 
		
		return "outs/hire/proListForClient"; 
	}
	
	/**
	 * 마이페이지용 특정 프로젝트의 지원자 목록
	 * @param boNum 조회 할 프로젝트 공고 글번호
	 * @return
	 */
	@RequestMapping("/outs/mypage/hire/hireList.do")
	public String applyListOneProj(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage,
			@RequestParam("boNum") String boNum,
			Model model
			) {
		
		OutsTotalVO applyPro = new OutsTotalVO();
		applyPro.setCurrentPage(currentPage);
		applyPro.setBoNum(boNum);
		
		service.selectApplyListOneProject(applyPro);
		model.addAttribute("applyPro", applyPro);
		return "outs/hire/hireListForOne";
	}
	
	
	/**
	 * 마이페이지용 특정 개발자 프로필 조회
	 * @param appId
	 * @param model
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	@RequestMapping("/outs/hire/mypage/applyProfile.do")
	public String selectDevProfile(
			@RequestParam("appNo") String appNo,
			Model model
			) throws IllegalAccessException, InvocationTargetException {
		
		MasterVO dev = new MasterVO();
		dev.setAppNo(appNo);
		service.selectDevProfile(dev);
		model.addAttribute("dev",dev);
		
		return "outs/hire/applyProfileView";
	}
	
}
