package kr.or.ddit.iteams.pms.board.request.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.annotations.Authenticated;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.pms.board.request.service.RequestService;
import kr.or.ddit.validate.groups.board.infoshare.InfoShareInsertGroup;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class RequestInsertController {
	
	@Inject
	private RequestService service;
	
	@RequestMapping(value="/pms/board/request/{proNo}/requestInsert.do",method=RequestMethod.GET)
	public String getForm(
			@ModelAttribute("detail") MasterVO master, Model model
			, @Authenticated MasterVO authMember
			) {
		master.setCrudToken("INSERT");
		model.addAttribute("detail", master);
		
		return "pms/board/request/requestForm";
	}
	
	@RequestMapping(value="/pms/board/request/{proNo}/requestInsert.do",method=RequestMethod.POST)
	public String doProcess(
			@Validated(InfoShareInsertGroup.class) @ModelAttribute("detail") MasterVO master
			, Errors errors
			, Model model
			, @PathVariable String proNo
			, @Authenticated MasterVO authMember
			, RedirectAttributes redirectAttr
			) throws Exception {
		
		String memId = authMember.getMemId();
		
		master.setBoWriter(memId);
		
		String viewName = null;
		String message = null;
		
		if(!errors.hasErrors()) {
			ServiceResult result = service.insertRequestBoard(master);
			switch(result) {
			case OK:
		    	   viewName = "redirect:/pms/board/request/"+ proNo +"/requestList.do";
		    	   message = "정상 처리되었습니다.";
		    	   break;
		    default: 	   
		    	viewName = "pms/board/request/requestForm";
		    	message = "서버오류. 잠시 후 다시 시도해 주세요";
			}
		}else {
			viewName = "pms/board/request/requestForm";
		}
		model.addAttribute("message", message);
		
		return viewName;
	}
}
