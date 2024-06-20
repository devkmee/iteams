package kr.or.ddit.iteams.outs.hire.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.annotations.Authenticated;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.outs.hire.service.PublicHireService;
import kr.or.ddit.validate.groups.hire.HirePublicInsertGroup;

@Controller
public class PublicHireInsertController {
	
	@Inject
	private PublicHireService service;
	
	//devId proNo 검증
	@RequestMapping(value="/outs/hire/inviteInsert.do", method=RequestMethod.POST)
	public String sendInviteProject(
			@Validated(HirePublicInsertGroup.class) @ModelAttribute("invite") MasterVO invite,
			Errors errors,
			RedirectAttributes redirect
			, @Authenticated MasterVO authMember
			) {
		String message = null;
		if(!errors.hasErrors()) {
			invite.setClientName(authMember.getClientName());
			service.insertInviteDev(invite);
			switch(invite.getResult()) {
			case FAILED: 
				message = "초대에 실패하였습니다. 고객센터로 문의해주세요.";
				break;
			case OK:
				message = "프로젝트 초대장을 보냈습니다";
				break;
			case NONEDEV:
				message = "초대장을 받을 수 없는 개발자입니다";
				break;
			case NONEPRO:
				message = "초대 할 수 없는 프로젝트입니다";
				break;
			}
		}else {
			message = "검증 실패. 고객센터로 문의해주세요.";
		}
		redirect.addFlashAttribute("message", message);
		return "redirect:/outs/hire/profileView.do?devId="+invite.getDevId();
	}

}
