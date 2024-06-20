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
import kr.or.ddit.validate.groups.hire.HireScrabGroup;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class PublicScrabInsertDeleteController {
	
	@Inject
	private PublicHireService service;
	
	/**
	 * 특정 클라이언트의 프로필 스크랩 추가/삭제
	 * @param client
	 * @param red
	 * @param scrab
	 * @param errors
	 * @return
	 */
	@RequestMapping(value="/outs/hire/scrab.do", method=RequestMethod.POST)
	public String scrabDevProfile(
			@Authenticated MasterVO client,
			RedirectAttributes red,
			@Validated(HireScrabGroup.class) @ModelAttribute("scrab") MasterVO scrab,
			Errors errors
			) {
		String message = null;
		scrab.setCliId(client.getMemId());
		
		if(!errors.hasErrors()) {
			service.ProfileScrab(scrab);
			switch (scrab.getResult()) {
			case OK:
				message = "스크랩 "+scrab.getFunction()+"에 성공하였습니다";
				break;
			default:
				message = "스크랩 "+scrab.getFunction()+"에 실패하였습니다";
				break;
			}
		}else {
			message = "검증 실패. 고객센터로 문의해주세요.";
		}
		red.addFlashAttribute("message", message);
		log.info("테스트"+message);
		return "redirect:/outs/hire/profileView.do?devId="+scrab.getDevId();
	}
	
	
	@RequestMapping(value="/outs/hire/scrabdelete.do", method=RequestMethod.POST)
	public String deleteScrabProfile(
			@Authenticated MasterVO client,
			RedirectAttributes red,
			@Validated(HireScrabGroup.class) @ModelAttribute("scrab") MasterVO scrab,
			Errors errors
			) {
		String message = null;
		scrab.setCliId(client.getMemId());
		
		if(!errors.hasErrors()) {
			service.onlyDelectScrab(scrab);
			switch (scrab.getResult()) {
			case OK:
				break;
			default:
				message = "스크랩  삭제에 실패하였습니다 \\r\\n고객센터로 문의해주세요.";
				break;
			}
		}else {
			message = "검증 실패. 고객센터로 문의해주세요.";
		}
		red.addFlashAttribute("message", message);
		return "redirect:/outs/hire/profilescrabList.do";
	}

}
