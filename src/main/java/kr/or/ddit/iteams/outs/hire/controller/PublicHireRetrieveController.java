package kr.or.ddit.iteams.outs.hire.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.annotations.Authenticated;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.SearchWordCollector;
import kr.or.ddit.iteams.common.utils.CookieUtils;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.common.vo.MemberTotalVO;
import kr.or.ddit.iteams.outs.hire.dao.PublicHireDAO;
import kr.or.ddit.iteams.outs.hire.service.PublicHireService;
import kr.or.ddit.vo.SearchVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class PublicHireRetrieveController {
	
	@Inject
	private PublicHireService service;
	
	@Inject
	private PublicHireDAO dao;
	
	/**
	 * 공개 프로필 목록조회
	 * @param currentPage
	 * @param detailSearch 직무(분야)검색
	 * @param searchVO 기술/언어 검색
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	@RequestMapping(value="/outs/hire/profileList.do")
	public String selectProfileList(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage,
			MasterVO masterVO,
			HttpServletRequest req, HttpServletResponse resp,
			Model model
	) throws IllegalAccessException, InvocationTargetException {
		MasterVO master = new MasterVO();
		masterVO.setCurrentPage(currentPage);
		String searchTech = masterVO.getDevTech();
		if(StringUtils.isNotBlank(searchTech)) {
//			log.info("마지막 콤마 위치 : {}, 검색어 길이 : {}", searchTech.lastIndexOf(","), searchTech.length());
			if(searchTech.lastIndexOf(",") == searchTech.length()-1) {
				searchTech = searchTech.substring(0, searchTech.length() - 1);
				masterVO.setDevTech(searchTech);
//				log.info("콤마 자른 값 : {}",searchTech);
			}
		}
		
		service.selectProfileList(masterVO);
		model.addAttribute("data", masterVO);
		SearchWordCollector.makeSearchVO();
		
		//String traceDevId = TraceCookieUtils.getCookie(req, resp, "profileTrace");
		
		List<String> devIdList = CookieUtils.getCookieTest(req, resp, "profileTrace");
		
		if(devIdList != null) {
			List<MasterVO> traceList = new ArrayList<>();
			for(String devId : devIdList) {
				MasterVO trace = new MasterVO();
				trace.setDevId(devId);
				service.selectProfile(trace);
				traceList.add(trace);
			}
			model.addAttribute("traceList", traceList);		
			
//			trace.setDevId(traceDevId);
//			service.selectProfile(trace);
		}
		
		return "outs/hire/publicProfileList";
	}
	
	/**
	 * 공개 프로필 상세조회
	 * @param devId
	 * @return
	 */
	@RequestMapping("/outs/hire/profileView.do")
	public String selectProfile(
			@RequestParam("devId") String devId,
			@Authenticated MasterVO client,
			HttpServletRequest req, HttpServletResponse resp,
			Model model
			) throws IllegalAccessException, InvocationTargetException {
		
		String cliId = client.getMemId();
		MasterVO dummy = new MasterVO();
		dummy.setDevId(devId);
		dummy.setClientId(cliId);
		int cnt = dao.selectScrapDevNy(dummy);
		
		MasterVO dev = new MasterVO();
		dev.setDevId(devId);
		service.selectProfile(dev);
		client.setDevId(devId);
		service.selectProjectListForInvite(client);
		
		model.addAttribute("dev", dev);
		model.addAttribute("client", client);
		model.addAttribute("scrapCnt", cnt);
		
		//ServiceResult cResult = TraceCookieUtils.cookieChecker(req, resp, , devId);
		CookieUtils.cookieCheckerTest(req, resp, "profileTrace", devId);
		
		return "outs/hire/publicProfileView";
	}
	
	/**
	 * 개발자용 초대받은 프로젝트 목록
	 * @param currentPage
	 * @param dev
	 * @return
	 */
	@RequestMapping("/outs/mypage/hire/inviteList.do")
	public String inviteListForDev(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage,
			@Authenticated MasterVO dev,
			Model model
			) {
		dev.setCurrentPage(currentPage);
		service.selectInviteListForDev(dev);
		model.addAttribute("data", dev);
		
		return "outs/hire/inviteList";
	}
	
	/**
	 * 마이페이지용 특정 클라이언트의 프로필 스크랩 목록
	 * @param client
	 * @param currentPage
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/outs/hire/profilescrabList.do")
	public String myScrabListForClient(
			@Authenticated MasterVO client,
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage,
			Model model
			) {
		client.setCurrentPage(currentPage);
		client.setScreenSize(8);
		service.selectProfileScrabList(client);
		model.addAttribute("data", client);
		
		return "outs/hire/scrabListForClient";
	}

}
