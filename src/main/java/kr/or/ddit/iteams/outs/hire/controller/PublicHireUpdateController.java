package kr.or.ddit.iteams.outs.hire.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.annotations.Authenticated;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.outs.hire.service.PublicHireService;
import kr.or.ddit.validate.groups.hire.HirePublicRefuseGroup;
import kr.or.ddit.validate.groups.hire.HirePublicUpdateGroup;

@Controller
public class PublicHireUpdateController {
	
	@Inject
	private PublicHireService service;
	
	@RequestMapping(value="/outs/hire/mypage/acceptEdit.do", method=RequestMethod.POST)
	public String acceptInvitation(
			@Authenticated MasterVO dev,
			@Validated(HirePublicUpdateGroup.class) @ModelAttribute("invite") MasterVO invite,
			Errors errors,
			RedirectAttributes redirect
			) {
		invite.setDevId(dev.getMemId());
		
		String message = null;
		if(!errors.hasErrors()) {
			service.updateAcceptInvitation(invite);
			switch(invite.getResult()) {
				case OK:
					message = "초대를 수락했습니다. 프로젝트에 정식 멤버로 등록되었습니다.";
					break;
				case FAILED:
					message = "초대 수락에 실패하였습니다. 고객센터로 문의해주세요.";
					break;
				case DUPLICATED:
					message = "현재 참여 중인 프로젝트가 있습니다.\\r\\n완료 혹은 탈퇴 후 다른 프로젝트에 참여 할 수 있습니다.";
					break;
			}
		}else {
			message = "검증 실패. 고객센터로 문의해주세요.";
		}
		redirect.addFlashAttribute("message", message);
		return "redirect:/outs/member/mypageApplyInvite.do";
	}
	
	
	@RequestMapping(value="/outs/hire/mypage/refuseEdit.do", method=RequestMethod.POST)
	public String refuseInvitation(
			@Validated(HirePublicRefuseGroup.class) @ModelAttribute("invite") MasterVO invite,
			Errors errors,
			RedirectAttributes redirect
			) {
		String message = null;
		if(!errors.hasErrors()) {
			service.updateRefuseInvitation(invite);
			switch(invite.getResult()) {
				case OK:
					message = "초대를 거절했습니다";
					break;
				case FAILED:
					message = "초대 거절에 실패하였습니다. 고객센터로 문의해주세요.";
					break;
			}
		}else {
			message = "검증 실패. 고객센터로 문의해주세요.";
		}
		redirect.addFlashAttribute("message", message);
		return "redirect:/outs/member/mypageApplyInvite.do";
	}
   
   @GetMapping("/outs/hire/reApply.do")
   public ModelAndView reApply(@ModelAttribute MasterVO masterVO
		   , @Authenticated MasterVO authMember) {
	   
	   masterVO.setAppNo(masterVO.getWhat());
	   masterVO.setMemId(authMember.getMemId());
	   service.updateReapply(masterVO);
	   ServiceResult result = masterVO.getResult();
	   String message = null;
	   String viewName = null;
	   
	   if(result == ServiceResult.OK) {
		   message = "정상 처리되었습니다.";
	   } else {
		   message = "정상 처리되지 못했습니다.";
	   }
	   
	   viewName = "redirect:/outs/board/projectboard/projectView.do?what=" + masterVO.getBoNum();
	   return new ModelAndView(viewName, "message", message);
   }

}
