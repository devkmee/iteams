package kr.or.ddit.iteams.outs.member.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.annotations.Authenticated;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.outs.login.vo.JoinDevRequestVO;
import kr.or.ddit.iteams.outs.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MemberUpdateController {
	
	@Inject
	private MemberService service;
	
	@RequestMapping(value="/outs/member/devPass.do", method=RequestMethod.POST)
	public String checkDev(
		@Authenticated MasterVO master,
		RedirectAttributes redirectAttributes,
		@RequestParam("memPass") String memPass	// 입력받은 비번 
	) {
		
		String dbPass = master.getMemPass();
		
	 	ServiceResult result = service.updatePass(memPass, dbPass);
	 	
	 	String viewName = null;
	 	
	 	String message = null;
	 	
	 	switch (result) {
		case INVALIDPASSWORD:
			viewName = "redirect:/outs/member/mypageDevInfo.do";
			message = "비밀번호가 일치하지 않습니다.";
			redirectAttributes.addFlashAttribute("message", message);
			break;
		case OK:
			viewName = "redirect:/outs/member/devEdit.do";
			break;
		default:
			viewName = "redirect:/outs/member/mypageDevInfo.do";
			message = "서버 오류! 고객센터로 문의하세요.";
			redirectAttributes.addFlashAttribute("message", message);
			break;
		}
	 	
	 	return viewName;
	}
	
	@RequestMapping(value="/outs/member/clientPass.do", method=RequestMethod.POST)
	public String checkClient(
		@Authenticated MasterVO master,
		RedirectAttributes redirectAttributes,
		@RequestParam("memPass") String memPass	// 입력받은 비번 
	) {
		
		String dbPass = master.getMemPass();
		
	 	ServiceResult result = service.updatePass(memPass, dbPass);
	 	
	 	String viewName = null;
	 	
	 	String message = null;
	 	
	 	switch (result) {
		case INVALIDPASSWORD:
			viewName = "redirect:/outs/member/mypageCllientInfo.do";
			message = "비밀번호가 일치하지 않습니다.";
			redirectAttributes.addFlashAttribute("message", message);
			break;
		case OK:
			viewName = "redirect:/outs/member/clientEdit.do";
			break;
		default:
			viewName = "redirect:/outs/member/mypageCllientInfo.do";
			message = "서버 오류! 고객센터로 문의하세요.";
			redirectAttributes.addFlashAttribute("message", message);
			break;
		}
	 	
	 	return viewName;
	}
	
	@RequestMapping(value="/outs/member/devEdit.do", method=RequestMethod.GET)
	public String getDev(
		Model model, @Authenticated MasterVO master	
	) {
		String memId = master.getMemId();
		
		MasterVO devInfo = service.selectDevInfo(memId);
		
		model.addAttribute("devInfo", devInfo);
		
		return "outs/member/devMyInfoEdit";
	}
	
	@RequestMapping(value="/outs/member/devEdit.do", method=RequestMethod.POST)
	public String updateDev(
		@ModelAttribute("dev") JoinDevRequestVO dev,
		Errors errors, 
		Model model, 
		@Authenticated MasterVO master,
		RedirectAttributes redirectAttributes
	) throws Exception {
		String memId = master.getMemId();
		dev.setMemId(memId);
		
		String viewName = null;
		String message = null;
		
		if(!errors.hasErrors()) {	// 에러가 없을 경우   
			
			ServiceResult result = service.updateDev(dev);
			
			switch(result) {
			case OK:	
				viewName = "redirect:/outs/member/mypageDevInfo.do";
				message = "수정 완료!"; 
				break; 
			default:
				viewName = "outs/member/devMyInfoEdit";
				message = "서버 오류, 잠시후 다시 시도하세요.";
			}
			
		}else {
			viewName = "outs/member/devMyInfoEdit";
			message = "에러 발생, 고객센터에 문의하세요.";
		}
		
		model.addAttribute("message", message); 
		redirectAttributes.addFlashAttribute("message", message); 
		
		return viewName; 
	}
	
	
	@RequestMapping(value="/outs/member/clientEdit.do", method=RequestMethod.GET)
	public String getClient(
		Model model, @Authenticated MasterVO master	
	) {
		String memId = master.getMemId();
		
		MasterVO clientInfo = service.selectClientInfo(memId);
		
		model.addAttribute("clientInfo", clientInfo);
		
		return "outs/member/clientMyInfoEdit";
	}
	
	
	@RequestMapping(value="/outs/member/clientEdit.do", method=RequestMethod.POST)
	public String updateClient(
		@ModelAttribute("client") JoinDevRequestVO client, Errors errors, Model model, @Authenticated MasterVO master,
		RedirectAttributes redirectAttributes
	) throws Exception {
		String memId = master.getMemId();
		client.setMemId(memId);
		
		String viewName = null;
		String message = null;
		
		if(!errors.hasErrors()) {	// 에러가 없을 경우 
			
			ServiceResult result = service.updateClient(client); 
			
			switch(result) {
			case OK:
				viewName = "redirect:/outs/member/mypageCllientInfo.do";
				message = "수정 완료!"; 
				break; 
			default:
				viewName = "outs/member/clientMyInfoEdit";
				message = "서버 오류, 잠시후 다시 시도하세요.";
			}
			
		}else {
			viewName = "outs/member/clientMyInfoEdit";
			message = "에러 발생, 고객센터에 문의하세요.";
		}
		
		model.addAttribute("message", message); 
		redirectAttributes.addFlashAttribute("message", message); 
		
		return viewName; 
	}

	
}
