package kr.or.ddit.iteams.outs.member.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.inject.Inject;
import javax.servlet.ServletException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.annotations.Authenticated;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.common.vo.MemberTotalVO;
import kr.or.ddit.iteams.outs.member.service.MemberService;
import kr.or.ddit.vo.SearchVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class AdminRetrieveController {
	
	@Inject
	private MemberService service;
	
	@RequestMapping("/outs/admin/member/mypageAdmin.do")
	public String goMemList(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage1,
			@ModelAttribute("detailSearch") MemberTotalVO detailSearch,
			@ModelAttribute("searchVO") SearchVO searchVO,
			Model model
			) throws ServletException, IOException {
		
		//회원 관리
		MasterVO base = new MasterVO();
		log.info("권한 체크  {} / 서치타입 : {}, 서치워드 : {}",detailSearch.getMemRole(), searchVO.getSearchType(), searchVO.getSearchWord());
		base.setCurrentPage(currentPage1);
		base.setDetailSearch(detailSearch);
		base.setSearchVO(searchVO);
		
		service.retrieveMemberList(base);
		model.addAttribute("base", base);
		
		return "outs/member/adminMemberList";
	}
	
	@RequestMapping("/outs/admin/member/memberView.do")
	public String selectMemberView(
			@ModelAttribute("member") MasterVO member,
			Model model
			) throws IllegalAccessException, InvocationTargetException {
		MasterVO saved = new MasterVO();
		switch (member.getMemRole()) {
		case "DEV":
			saved = service.selectDevInfo(member.getMemId());
			break;

		case "CLIENT":
			saved = service.selectClientInfo(member.getMemId());
			break;
		}
		saved.setMemId(member.getMemId());
		saved.setMemRole(member.getMemRole());
		model.addAttribute("member", saved);

		return "outs/member/memberView";
	}

}
