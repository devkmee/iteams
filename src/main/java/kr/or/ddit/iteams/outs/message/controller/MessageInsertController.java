package kr.or.ddit.iteams.outs.message.controller;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.annotations.Authenticated;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.outs.message.dao.MessageDAO;
import kr.or.ddit.iteams.outs.message.service.MessageService;
import kr.or.ddit.validate.groups.message.MessageInsertGroup;

@Controller
public class MessageInsertController {
	
	@Inject
	private MessageService service;
	
	@Inject
	private MessageDAO dao;
	
	@ModelAttribute("messageVO")
	public Object getVO() {
		return new MasterVO();
	}
	
	@RequestMapping(value="/outs/message/sendMessage.do", method=RequestMethod.GET)
	public String getFrom(
		Model model, @Authenticated MasterVO master	
	) {
		return "outs/message/messageForm";
	}
	
	@RequestMapping(value="/outs/message/sendMessage.do", method=RequestMethod.POST)
	public String doProcess(
		@Authenticated MasterVO master, @Validated(MessageInsertGroup.class) @ModelAttribute("messageVO") MasterVO msg,
		Errors errors, Model model, RedirectAttributes redirectAttributes
	) {
		String memId = master.getMemId();
		
		msg.setMemId(memId);
		
		String viewName = null; 
		String message = null; 
		String receiver = msg.getMsgReceive();
		
		if(!StringUtils.equals(memId, receiver)) {
			if(!errors.hasErrors()) {	
				ServiceResult result = service.sendMessage(msg);
				switch(result) {
				case OK:
					viewName = "redirect:/outs/message/messageList.do";
					message = receiver + " 님에게 쪽지를 전송했습니다.";
					redirectAttributes.addFlashAttribute("message", message);
					break;
				default:
					viewName = "outs/message/messageForm";
					message = "서버 오류! 받는사람 아이디를 다시 확인해주세요.";
					model.addAttribute("message", message); 
				}

			}else { 
				viewName = "outs/message/messageForm"; 
			} 
		} else {
			viewName = "outs/message/messageForm";
			message = "자신에게는 쪽지를 보낼수 없습니다.";
		}
		
		
		return viewName; 
	}
	
	@RequestMapping(value="/outs/message/sendPMSMessage.do", method=RequestMethod.POST)
	public String doPmsProcess(
			@Authenticated MasterVO master, @Validated(MessageInsertGroup.class) @ModelAttribute("messageVO") MasterVO msg,
			Errors errors, Model model, RedirectAttributes redirectAttributes
			) {
		String memId = master.getMemId();
		
		msg.setMemId(memId);
		
		String viewName = null; 
		String message = null; 
		String receiver = msg.getMsgReceive();
		
		if(!StringUtils.equals(memId, receiver)) {
			if(!errors.hasErrors()) {	
				ServiceResult result = service.sendMessage(msg);
				switch(result) {
				case OK:
					viewName = "redirect:/pms/team/"+ master.getProNo() +"/teamList.do";
					message = receiver + " 님에게 쪽지를 전송했습니다.";
					redirectAttributes.addFlashAttribute("message", message);
					break;
				default:
					viewName = "pms/include/outer/inner/in/second/messageForm";
					message = "서버 오류! 받는사람 아이디를 다시 확인해주세요.";
					model.addAttribute("message", message); 
				}
				
			}else { 
				viewName = "pms/include/outer/inner/in/second/messageForm"; 
			} 
		} else {
			viewName = "pms/include/outer/inner/in/second/messageForm";
			message = "자신에게는 쪽지를 보낼수 없습니다.";
		}
		
		
		return viewName; 
	}
	
	@GetMapping(value="/outs/memberList.do" ,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object getList(@RequestParam("search") String search
			, @Authenticated MasterVO authMember) {
		authMember.setMsgReceive(search);
		return dao.selectMemIdList(authMember);
	}
	
}
