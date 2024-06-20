package kr.or.ddit.iteams.outs.blacklist.controller;

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
import kr.or.ddit.iteams.outs.blacklist.service.BlacklistService;
import kr.or.ddit.validate.groups.black.BlackRepUpdateGroup;
import kr.or.ddit.validate.groups.black.BlackUpdateGroup;

@Controller
public class BlacklistUpdateController {
	
	@Inject
	private BlacklistService service;
	
	@RequestMapping(value="/outs/admin/blacklist/blackUpdate.do", method=RequestMethod.POST)
	public String BlackToMem(
			@Authenticated MasterVO admin,
			@Validated(BlackUpdateGroup.class) MasterVO black,
			Errors errors,
			RedirectAttributes red
			) {
		String message = null;
		black.setMemId(admin.getMemId());
		if(!errors.hasErrors()) {
			service.updateBlackToMem(black);
			switch (black.getResult()) {
			case OK:
				break;
			default:
				message = "블랙리스트 해제에 실패했습니다";
				break;
			}
		}else {
			message = "블랙리스트 번호가 선택되지 않았습니다";
		}
		red.addFlashAttribute("message", message);
		return "redirect:/outs/admin/blacklist/blackList.do";
	}
	
	
	@RequestMapping(value="/outs/admin/blacklist/clearRep.do", method=RequestMethod.POST)
	public String clearRepBoard(
			@Validated(BlackRepUpdateGroup.class) MasterVO board,
			Errors errors,
			RedirectAttributes red
			) {
		String message = null;
		if(!errors.hasErrors()) {
			service.updateClearBoard(board);
			switch (board.getResult()) {
			case OK:
				message = "해당 게시글의 신고수가 초기화 되었습니다";
				break;
			default:
				message = "신고수 초기화에 실패하였습니다";
				break;
			}
		}else {
			message = "글번호가 선택되지 않았습니다";
		}
		red.addFlashAttribute("message", message);
		return "redirect:/outs/admin/blacklist/repBoardList.do";
	}
	
	
	
}
