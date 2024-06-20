package kr.or.ddit.iteams.common.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.annotations.Authenticated;
import kr.or.ddit.iteams.common.dao.GetRankDAO;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.outs.board.projectboard.dao.OutsProjectDAO;
import kr.or.ddit.iteams.outs.member.dao.MemberDAO;

@Controller
public class IndexController{
	
	@Inject
	private OutsProjectDAO dao;
	
	@Inject
	private MemberDAO memDao;
	
	@Inject
	private GetRankDAO rank;
	
	@RequestMapping(value="/index.do", method=RequestMethod.GET)
	public String index(HttpServletRequest req, HttpServletResponse resp, Model model
			, @Authenticated MasterVO authMember){
		
		List<MasterVO> projectRank = rank.projectRank();
		List<MasterVO> activeRank = rank.activeRank();
		List<MasterVO> reviewRank = rank.reviewRank();
		
		model.addAttribute("projectRank", projectRank);
		model.addAttribute("activeRank", activeRank);
		model.addAttribute("reviewRank", reviewRank);
		
		if(authMember != null) {
			String role = authMember.getMemRole();
			Map<String, Object> memInfoMap = new HashMap<>();
			if("DEV".equalsIgnoreCase(role)) {
				MasterVO devInfo = memDao.selectDevInfo(authMember.getMemId());
				if(devInfo != null) {
					List<String> techList = Arrays.asList(devInfo.getDevTech().split(","));
					memInfoMap.put("devTech", techList);					
					memInfoMap.put("devJob", devInfo.getDevJob());
					model.addAttribute("devInfo", memInfoMap);
				}
			}		
		}
		
		return "index";
	}
	
	@RequestMapping(value="/index.do", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object getList(@ModelAttribute MasterVO masterVO) {
		List<MasterVO> dataList = dao.selectMainProjectList(masterVO);
		masterVO.setDataList(dataList);
		return masterVO;
	}
	
	@RequestMapping(value="/index.do", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object recList(@Authenticated MasterVO authMember) {
		List<MasterVO> dataList = dao.selectRecProjectList(authMember);
		return dataList;
	}
	
	@RequestMapping("/member/memberList.do")
	public String boardList(HttpServletRequest req, HttpServletResponse resp){
		
		return "member/memberList";
	}
	
}