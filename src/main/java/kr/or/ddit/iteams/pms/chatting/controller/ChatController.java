package kr.or.ddit.iteams.pms.chatting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.annotations.Authenticated;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.pms.project.dao.OthersDAO;

@Controller
public class ChatController {
	
	
	
	@RequestMapping("/pms/{proNo}/chat.do")
	public ModelAndView chat(
			@Authenticated MasterVO masterVO
			,Model model
			) {
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("pms/chatting/outer/inner/chat/chat");
		model.addAttribute("masterVO",masterVO);
		return mv;
	}
}