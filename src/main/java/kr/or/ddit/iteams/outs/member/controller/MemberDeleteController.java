package kr.or.ddit.iteams.outs.member.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.annotations.Authenticated;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.outs.member.service.MemberService;

@Controller
public class MemberDeleteController {
	
	@Inject
	private MemberService service;
	
	@RequestMapping(value="/outs/member/deleteDev.do", method=RequestMethod.POST)
	public String deleteDev(
		@Authenticated MasterVO master,
		RedirectAttributes redirectAttributes,
		@RequestParam("del") String del	// 입력받은 비번 
		, HttpSession session
	) { 
		String memId = master.getMemId();
		
		String dbPass = master.getMemPass();
		
	 	ServiceResult result = service.deletePass(del, dbPass);
	 	
	 	String viewName = null;
	 	
	 	String message = null;
	 	
	 	switch (result) {
		case INVALIDPASSWORD:
			viewName = "redirect:/outs/member/mypageDevInfo.do";
			message = "비밀번호가 일치하지 않습니다.";
			redirectAttributes.addFlashAttribute("message", message);
			break;
		case OK:
			viewName = "redirect:/";
			ServiceResult res = service.deleteMember(memId);
			switch(res) {
			case OK : 
//				session.invalidate();
				viewName = "redirect:/logout.do";
				message = "회원 탈퇴가 완료되었습니다. 다음 주 월요일까지 재가입이 불가능합니다.";
				break;
			default : 
				message = "회원탈퇴중 오류 발생!";
			}
			break;
		default:
			viewName = "redirect:/outs/member/mypageDevInfo.do";
			message = "서버 오류! 고객센터로 문의하세요.";
			break;
		}
	 	
	 	redirectAttributes.addFlashAttribute("message", message);
	 	return viewName;
	}
	
	
	@RequestMapping(value="/outs/member/deleteClient.do", method=RequestMethod.POST)
	public String deleteClient(
		@Authenticated MasterVO master,
		RedirectAttributes redirectAttributes,
		@RequestParam("del") String del	// 입력받은 비번 
		, HttpSession session
	) { 
		String memId = master.getMemId();
		
		String dbPass = master.getMemPass();
		
	 	ServiceResult result = service.deletePass(del, dbPass);
	 	
	 	String viewName = null;
	 	
	 	String message = null;
	 	
	 	switch (result) {
		case INVALIDPASSWORD:
			viewName = "redirect:/outs/member/mypageCllientInfo.do";
			message = "비밀번호가 일치하지 않습니다.";
			redirectAttributes.addFlashAttribute("message", message);
			break;
		case OK:
			viewName = "redirect:/";
			ServiceResult res = service.deleteMember(memId);
			switch(res) {
			case OK : 
				session.invalidate();
				message = "회원 탈퇴가 완료되었습니다. 다음 주 월요일까지 재가입이 불가능합니다.";
				break;
			default : 
				message = "회원탈퇴중 오류 발생!";
			}
			break;
		default:
			viewName = "redirect:/outs/member/mypageCllientInfo.do"; 
			message = "서버 오류! 고객센터로 문의하세요.";
			break;
		}
	 	
	 	redirectAttributes.addFlashAttribute("message", message);
	 	return viewName;
	}
	
	
}
