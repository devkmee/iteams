package kr.or.ddit.iteams.outs.message.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.annotations.Authenticated;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.outs.message.dao.MessageDAO;
import kr.or.ddit.iteams.outs.message.service.MessageService;

@Controller
public class MessageRetrieveController {
	
	@Inject
	private MessageService service;
	
	@Inject
	private MessageDAO dao;
	
	@RequestMapping("/outs/message/messageList.do")
	public String messageList(
			Model model,
			@Authenticated MasterVO master,
			@RequestParam(value="page1", required=false, defaultValue="1") int currentPage1,
			@RequestParam(value="page2", required=false, defaultValue="1") int currentPage2			
	) { 
	    String memId = master.getMemId();
		
	    MasterVO sendList = new MasterVO();
	    sendList.setMemId(memId);
	    sendList.setCurrentPage(currentPage1);	// 발신함 페이징 
		service.selectSendList(sendList); 
		model.addAttribute("sendList", sendList); 
		
		MasterVO receiveList = new MasterVO();
		receiveList.setMemId(memId);
		receiveList.setCurrentPage(currentPage2); 
		service.selectReceiveList(receiveList); 
		model.addAttribute("receiveList", receiveList); 
		
	    return "outs/message/messageList";
	}
	
	
	@RequestMapping("/outs/message/sendView.do")
	public String sendView(
		@Authenticated MasterVO master, 
		Model model,
		@RequestParam("what") String msgNum
	) {
		String memId = master.getMemId(); 
		
		MasterVO vo = new MasterVO();
		vo.setMemId(memId);
		vo.setMsgNum(msgNum);
		
		MasterVO msg = service.selectSendDetail(vo);
		
		model.addAttribute("msg", msg);
		
		return "outs/message/messageView";
	}
	
	
	@RequestMapping("/outs/message/receiveView.do")
	public String receiveView(
		@Authenticated MasterVO master, 
		Model model, 
		@RequestParam("what") String msgNum
	) {
		String memId = master.getMemId(); 
		
		MasterVO vo = new MasterVO();
		vo.setMemId(memId);
		vo.setMsgNum(msgNum);
		
		MasterVO msg = service.selectReceiveDetail(vo);
		
		service.updateRead(vo);	// 쪽지 읽으면 수신여부 Y로 바꾸는 로직 
		
		model.addAttribute("msg", msg);
		
		return "outs/message/messageView";
	}
	
	@GetMapping(value="/outs/getMessageCount.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object receiveCount(@Authenticated MasterVO authMember) {
		Map<String, Object> resultMap = new HashMap<>();
		int cnt = dao.selectNotReadMessageCount(authMember);
		resultMap.put("count", cnt);
		return resultMap;
	}
	
}
