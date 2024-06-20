package kr.or.ddit.iteams.outs.message.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.annotations.Authenticated;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.outs.message.service.MessageService;

@Controller
public class MessageDeleteController {
	
	@Inject
	private MessageService service; 
	
	@RequestMapping(value="/outs/message/deleteMessage.do", method=RequestMethod.POST)
	public String doProcess(
		@RequestParam("what") String msgNum, RedirectAttributes redirectAttributes,
		@Authenticated MasterVO master
	) {
		String memId = master.getMemId();
		
		MasterVO vo = new MasterVO();
		vo.setMsgNum(msgNum); 
		vo.setMemId(memId);
		
		ServiceResult result = service.deleteMessage(vo);
		
		String viewName = null;
		String message = null;
		
		switch(result) {
		case OK:
			viewName = "redirect:/outs/message/messageList.do";
			message = "삭제되었습니다.";
			break;
			
		default:
			viewName = "redirect:/outs/message/messageList.do";
			message = "서버 오류, 잠시후 다시 시도 해보세요.";
		}
		
		redirectAttributes.addFlashAttribute("message", message);
		
		return viewName;
	}
	
}
