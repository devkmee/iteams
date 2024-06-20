package kr.or.ddit.iteams.pms.board.daily.controller;

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
import kr.or.ddit.iteams.pms.board.daily.service.DailyService;
import kr.or.ddit.validate.groups.board.infoshare.InfoShareInsertGroup;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class DaliyInsertController {
	
	@Inject
	private DailyService service;
	
	//insertform에 들어갈때
		@RequestMapping(value="/pms/board/daily/{proNo}/dailyInsert.do" ,method=RequestMethod.GET)
		public String getForm(
				 @ModelAttribute("detail") MasterVO master, Model model 
				, @Authenticated MasterVO authMember
				) {
			master.setCrudToken("INSERT");
			model.addAttribute("detail", master);
				
			return "pms/board/daily/dailyForm";
		}
		
	
	
	@RequestMapping(value="/pms/board/daily/{proNo}/dailyInsert.do", method=RequestMethod.POST)
	public String doProcess(
			@Validated(InfoShareInsertGroup.class) @ModelAttribute("detail") MasterVO master
			, Errors errors	//이안에 검증결과있음 
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
	       ServiceResult result = service.insertDailyBoard(master);
	       switch(result) {
	       case OK:
	    	   viewName = "redirect:/pms/board/daily/"+ proNo +"/dailyList.do";
	    	   message = "정상 처리되었습니다.";
	    	   break;
	       default:
	    	   viewName = "pms/board/daily/dailyForm";
	    	   message = "서버오류. 잠시 후 다시 시도해 주세요";
	       }
		}else {
			viewName = "pms/board/daily/dailyForm";
		}
		model.addAttribute("message", message);
		
		return viewName;
	}
}
		 
		/* if(!errors.hasErrors()) {
			 master.setProNo(proNo);
			 service.insertDailyBoard(master);
			 ServiceResult result = master.getResult();
			 if(result == ServiceResult.OK) {
				 message = "게시글이 성공적으로 등록되었습니다.";
				 viewName = "redirect:/pms/board/daily/"+ proNo +"/dailyInsert.do";*/
		/*	 }
	    model.addAttribute("master", master);
		return "pms/board/daily/dailyForm";
		 }*/
		
		
	
	
