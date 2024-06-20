 package kr.or.ddit.iteams.outs.board.projectboard.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.annotations.Authenticated;
import kr.or.ddit.iteams.common.SearchWordCollector;
import kr.or.ddit.iteams.common.utils.CookieUtils;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.outs.board.projectboard.dao.OutsProjectDAO;
import kr.or.ddit.iteams.outs.board.projectboard.service.OutsProjectService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class OutsProjectRetrieveController {
	
	@Inject
	OutsProjectService service;
	
	@Inject
	private OutsProjectDAO dao;
	
	@RequestMapping("/outs/board/projectboard/projectView.do")
	public String projectView(@RequestParam("what") String number, Model model
			, @Authenticated MasterVO authMember
			, HttpServletRequest req, HttpServletResponse resp) {
		
		
		MasterVO vo = service.retrieveProject(number);
		
		model.addAttribute("vo", vo);
		model.addAttribute("number", number);
		
		if(authMember != null) {
			if(authMember.getMemRole().equals("DEV")) {
				authMember.setBoNum(number);
				MasterVO appInfo = dao.selectProjectApp(authMember);
				MasterVO ingProject = dao.selectIngProject(authMember);
				if(appInfo != null) {
					model.addAttribute("appInfo", appInfo);
				} else if(ingProject != null) {
					model.addAttribute("ingProject", ingProject);
				}
			}
		}
		
//		ServiceResult result = TraceCookieUtils.cookieChecker(req, resp, "boardTrace", number);
		CookieUtils.cookieCheckerTest(req, resp, "boardTrace", number);
		
		return "outs/board/projectboard/projectBoardView";
	}
	
	@RequestMapping("/outs/board/projectboard/projectAppView.do")
	public String projectAppView(@RequestParam("what") String number, Model model
			, @Authenticated MasterVO authMember) {
		
		MasterVO dummy = new MasterVO();
		dummy.setBoNum(number);
		dummy.setMemId(authMember.getMemId());
		MasterVO vo = service.selectProjectApp(dummy);
		
		model.addAttribute("vo", vo);
		model.addAttribute("number", number);
		
		return "outs/board/projectboard/projectBoardView";
	}

	
//	@RequestMapping("/outs/board/projectboard/projectList.do")
//	public String projectList(
//		@RequestParam(value="page", required=false, defaultValue="1") int currentPage
//		, @ModelAttribute("searchVO") SearchVO searchVO
//		, Model model, HttpServletRequest req, HttpServletResponse resp
//	) throws IllegalAccessException, InvocationTargetException {
//		BaseVO base = new BaseVO(12, 5); 
//		base.setCurrentPage(currentPage); 
//		base.setSearchVO(searchVO); 
//		
//		service.retrieveProjectList(base);
//		
//		model.addAttribute("base", base);
//		
//		String traceNumber = TraceCookieUtils.getCookie(req, resp, "boardTrace");
//		if(StringUtils.isNoneBlank(traceNumber)) {
//		MasterVO trace = new MasterVO();
//		trace.setBoNum(traceNumber);
//		MasterVO traced = service.selectProject(trace);
//		model.addAttribute("traced", traced);
//		}
//		
//		return "outs/board/projectboard/projectBoardList";
//	}
	
	@RequestMapping(value="/outs/board/projectboard/projectList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object projectListFilter(
			@ModelAttribute MasterVO masterVO
			, Model model, HttpServletRequest req, HttpServletResponse resp
			, HttpSession session
			) throws IllegalAccessException, InvocationTargetException {
		masterVO.setScreenSize(12);
		masterVO.setBlockSize(5);
		masterVO.setCurrentPage(masterVO.getPage()); 
		
		String searchJob = masterVO.getProjectJob();
		String searchScale = masterVO.getProjectScale();
		String searchOffice = masterVO.getOfficeNy();
		String searchTech = masterVO.getProjectTech();

		if(searchJob.lastIndexOf(",") == searchJob.length() - 1) {
			searchJob = searchJob.substring(0, searchJob.length());
		}
		if(searchOffice.lastIndexOf(",") == searchOffice.length() - 1) {
			searchOffice = searchOffice.substring(0, searchOffice.length());
		}
		if(searchOffice.lastIndexOf(",") == searchOffice.length() - 1) {
			searchOffice = searchOffice.substring(0, searchOffice.length());
		}
		if(searchTech.lastIndexOf(",") == searchTech.length() - 1) {
			searchTech = searchTech.substring(0, searchTech.length());
		}
		
		if(StringUtils.isNotBlank(searchJob)) {
			masterVO.setProjectJobList(Arrays.asList(searchJob.split(",")));			
		}
		if(StringUtils.isNotBlank(searchScale)) {
			masterVO.setProjectScaleList(Arrays.asList(searchScale.split(",")));			
		}
		if(StringUtils.isNotBlank(searchOffice)) {
			masterVO.setOfficeNyList(Arrays.asList(searchOffice.split(",")));			
		}
		if(StringUtils.isNotBlank(searchTech)) {
			masterVO.setProjectTechList(Arrays.asList(searchTech.split(",")));			
		}

		
		log.info("스택 검색값 : {}", searchTech);
		SearchWordCollector.makeSearchVO();
		MasterVO searchVO = (MasterVO) session.getAttribute("searchVO");
		searchVO.setProjectTech(searchTech);
		searchVO.setProjectJob(searchJob);
		searchVO.setProjectScale(searchScale);
		searchVO.setOfficeNy(searchOffice);

//		log.info("필터 1 : {}, 필터2 : {}, 필터 3: {}", searchVO.getProjectJob(), searchVO.getProjectScale(), searchVO.getOfficeNy());
		
		String memId = masterVO.getMemId().equals("ALL") ? "" : masterVO.getMemId();
		String appId = masterVO.getAppId().equals("ALL") ? "" : masterVO.getAppId();
		masterVO.setMemId(memId);
		masterVO.setAppId(appId);
		
		service.retrieveProjectList(masterVO);
		
		
		return masterVO;
	}
	
	@RequestMapping(value="/outs/board/projectboard/projectList.do")
	public String projectListForm(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, Model model, HttpServletRequest req, HttpServletResponse resp
	) throws IllegalAccessException, InvocationTargetException {
		
//		String traceNumber = TraceCookieUtils.getCookie(req, resp, "boardTrace");
	
		List<String> numberList = CookieUtils.getCookieTest(req, resp, "boardTrace");
		
		if(numberList != null) {
			List<MasterVO> traceList = new ArrayList<>();
			for(String number : numberList) {
				MasterVO trace = new MasterVO();
				trace.setBoNum(number);
				trace = service.selectProject(trace);
				traceList.add(trace);
			}
			model.addAttribute("traceList", traceList);
			
		}
		
		return "outs/board/projectboard/projectBoardList";
	}
	
}
