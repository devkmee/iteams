package kr.or.ddit.iteams.outs.board.notice.controller;

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
import kr.or.ddit.iteams.outs.board.notice.service.NoticeBoardService;
import kr.or.ddit.validate.groups.board.BoardDeleteGroup;

@Controller
public class NoticeDeleteController {
	
	@Inject
	private NoticeBoardService service;

	@RequestMapping(value="/outs/admin/board/notice/noticeDelete.do", method=RequestMethod.POST)
	public String doProcess(
			@Authenticated MasterVO member,
			@Validated(BoardDeleteGroup.class) @ModelAttribute MasterVO board,
			Errors errors,
			RedirectAttributes redirect
			) throws Exception {
		String message = null;
		board.setMemId(member.getMemId());
		if(!errors.hasErrors()) {
			service.deleteBoard(board);
			switch (board.getResult()) {
			case OK:
				message = "글이 성공적으로 삭제되었습니다.";
				break;

			default:
				message = "글 삭제에 실패했습니다. \\r\\n고객센터로 문의해주세요.";
				break;
			}
		}else {
			message = "해당 글의 글번호를 가져올 수 없습니다.";
		}
		redirect.addFlashAttribute("message", message);
		return "redirect:/outs/board/notice/noticeList.do";
	}
	
}
