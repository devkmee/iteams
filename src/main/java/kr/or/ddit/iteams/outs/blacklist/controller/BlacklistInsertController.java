package kr.or.ddit.iteams.outs.blacklist.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.annotations.Authenticated;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.outs.blacklist.service.BlacklistService;
import kr.or.ddit.validate.groups.black.BlackInsertGroup;

@Controller
public class BlacklistInsertController {
	
	@Inject
	private BlacklistService service;

	@RequestMapping(value="/outs/blacklist/blackInsert.do", method=RequestMethod.GET)
	public String getForm() {
		
		return "outs/blacklist/blackForm";
	}
	
	@RequestMapping(value="/outs/blacklist/blackInsert.do", method=RequestMethod.POST)
	public String doProcess(
			@Authenticated MasterVO admin,
			@Validated(BlackInsertGroup.class) @ModelAttribute MasterVO member,
			Errors errors,
			RedirectAttributes red
			) {
		String message = null;
		member.setMemId(admin.getMemId());
		if(!errors.hasErrors()) {
			service.insertMemToBlack(member);
			switch(member.getResult()) {
			case OK:
				message = "게시물이 삭제되고 작성자는 블랙리스트로 등록되었습니다";
				break;
			default:
				message = "서버 오류 잠시 후 다시 시도해주세요";
				break;
			}
		}else {
			message = "검증 실패";
		}
		red.addFlashAttribute("message", message);
		return "redirect:/outs/admin/blacklist/repBoardList.do";
	}
	
}
