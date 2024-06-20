package kr.or.ddit.iteams.outs.board.qna.controller;

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
import kr.or.ddit.iteams.outs.board.notice.service.NoticeBoardService;
import kr.or.ddit.iteams.outs.board.qna.service.QNAService;
import kr.or.ddit.validate.groups.board.BoardDeleteGroup;

@Controller
public class QnaDeleteController {
	
	@Inject
	private QNAService service;
	
	@Inject
	private NoticeBoardService genService;
	
	@RequestMapping(value="/outs/board/qna/questionDelete.do", method=RequestMethod.POST)
	public String deleteQuestion(
			@Authenticated MasterVO member,
			@Validated(BoardDeleteGroup.class) @ModelAttribute MasterVO board,
			Errors errors,
			RedirectAttributes redirect
			) throws Exception {
		String message = null;
		String viewName = null;
	
		if(!errors.hasErrors()) {
			service.selectCheckAnswer(board);
			if(board.getResult() == ServiceResult.HAVECHILD) {
				message = "답변이 달린 질문은 삭제 할 수 없습니다";
				viewName = "redirect:/outs/board/qna/qnaView.do?boNum="+board.getBoNum();
				redirect.addFlashAttribute("message", message);
			}else {
				genService.deleteBoard(board);
				switch (board.getResult()) {
				case OK:
					message = "글이 성공적으로 삭제되었습니다.";
					break;

				default:
					message = "글 삭제에 실패했습니다. \\r\\n고객센터로 문의해주세요.";
					break;
				}
			}
		}else {
			message = "삭제할 글의 글번호를 찾을 수 없습니다.";
		}
		redirect.addFlashAttribute("message", message);
		return "redirect:/outs/board/qna/qnaList.do";
	}
	
	
	@RequestMapping(value="/outs/board/qna/answerDelete.do", method=RequestMethod.POST)
	public String deleteAnwser(
			@Authenticated MasterVO member,
			@Validated(BoardDeleteGroup.class) @ModelAttribute MasterVO board,
			Errors errors,
			RedirectAttributes redirect
			) throws Exception {
		String message = null;
		String viewName = null;

		if (!errors.hasErrors()) {
			genService.deleteBoard(board);
			switch (board.getResult()) {
			case OK:
				message = "글이 성공적으로 삭제되었습니다.";
				break;

			default:
				message = "글 삭제에 실패했습니다. \\r\\n고객센터로 문의해주세요.";
				break;
			}
		} else {
			message = "삭제할 글의 글번호를 찾을 수 없습니다.";
		}
		redirect.addFlashAttribute("message", message);
		return "redirect:/outs/board/qna/qnaList.do";
	}
	
	
	
}
