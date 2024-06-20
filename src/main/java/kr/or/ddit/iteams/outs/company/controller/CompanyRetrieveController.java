package kr.or.ddit.iteams.outs.company.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.annotations.Authenticated;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.outs.company.dao.CompanyDAO;
import kr.or.ddit.iteams.outs.company.service.ComponyService;
import kr.or.ddit.iteams.outs.hire.service.ApplyProjectService;
import kr.or.ddit.vo.SearchVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class CompanyRetrieveController {
	
	@Inject
	private ComponyService service;
	
	@Inject
	private CompanyDAO dao;
	
	@Inject
	private ApplyProjectService serviceApply;
	
	
	@RequestMapping(value="/outs/company/companyList.do", method=RequestMethod.GET)
	public String companyList (
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage,
			@ModelAttribute(value="searchVO") SearchVO searchVO,
			Model model, 
			@Authenticated MasterVO authMember
			) {
		MasterVO compony = new MasterVO();
		compony.setScreenSize(9);
		compony.setCurrentPage(currentPage);
		compony.setSearchVO(searchVO);
		service.selectComponyList(compony);
		model.addAttribute("data", compony);
		
		MasterVO rank = new MasterVO();
		service.selectRankList(rank);
		model.addAttribute("rankList", rank);
		
		if(authMember != null) {
			String role = authMember.getMemRole();
			if(role.equalsIgnoreCase("DEV")) {
				List<String> recComponyList = dao.selectRecComponyList(authMember);
//				model.addAttribute("recList", recComponyList);
				authMember.setRecList(recComponyList);
			}
		}
				
	return "outs/company/companyList";
	}
	
	
	
	
	@RequestMapping(value="/outs/company/companyView.do", method=RequestMethod.GET)
	public String companyView(
			@Authenticated MasterVO member,
			@RequestParam("cliId") String cliId,
			Model model
			) throws IllegalAccessException, InvocationTargetException {
		MasterVO compony = new MasterVO();
		compony.setCliId(cliId);
		
		String reviewInsertAuth = null;
		String reviewUpDelAuth = null;
		if(member!=null) {
			if(member.getMemRole().equals("DEV")) {
				compony.setMemId(member.getMemId());
				reviewInsertAuth = service.selectCheckReviewInsertAuth(compony);
				reviewUpDelAuth = service.selectCheckUpDelAuth(compony);
			}
		}
		service.selectCompony(compony);
		model.addAttribute("compony", compony);
		model.addAttribute("reviewInsertAuth", reviewInsertAuth);
		model.addAttribute("reviewUpDelAuth", reviewUpDelAuth);
		
		//공고 프로젝트 목록
		MasterVO proBoard = new MasterVO();
		proBoard.setScreenSize(3);
		proBoard.setCurrentPage(1);
		proBoard.setMemId(cliId);
		serviceApply.selectProBoardListForClient(proBoard);
		model.addAttribute("boardList", proBoard);
		
		//추천검색어용 랭크
		MasterVO rank = new MasterVO();
		service.selectRankList(rank);
		model.addAttribute("rankList", rank);
		
		if(member != null) {
			String role = member.getMemRole();
			if(role.equalsIgnoreCase("DEV")) {
				List<String> recComponyList = dao.selectRecComponyList(member);
//				model.addAttribute("recList", recComponyList);
				member.setRecList(recComponyList);
			}
		}

	return "outs/company/companyView";	
	}
	
	
	
	
	
	@RequestMapping(value="/outs/company/reviewList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public MasterVO reviewListForData(
			@RequestParam(value="rePage", required=false, defaultValue="1") int currentPage,
			@RequestParam(value="cliId", required=true) String cliId
			) {
		MasterVO review = new MasterVO();
		review.setCurrentPage(currentPage);
		review.setCliId(cliId);
		service.selectReviewList(review);
		return review;
	}
	
	
	@RequestMapping(value="/outs/company/reviewList.do", method=RequestMethod.GET)
	public String goReplyList(
			@RequestParam(value="rePage", required=false, defaultValue="1") int currentPage,
			@RequestParam(value="cliId", required=true) String cliId,
			Model model,
			HttpServletResponse resp
			) {
		MasterVO review = reviewListForData(currentPage, cliId);
		model.addAttribute("review", review);
		return "outs/company/companyView";
	}
	
	
}
