package kr.or.ddit.iteams.outs.board.news.controller;

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
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.outs.board.news.service.NewsBoardService;
import kr.or.ddit.validate.groups.InsertGroup;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class NewsInsertController {
	
	@Inject
	private NewsBoardService service;
	
	
	@RequestMapping(value="/outs/board/news/newsInsert.do",method=RequestMethod.GET)
	public String getFrom(
			@ModelAttribute("detail") MasterVO master
			, Model model
			) {
		master.setCrudToken("INSERT");
		model.addAttribute("detail", master);
		return "outs/board/news/newsForm";
	}
	
	@RequestMapping(value="/outs/board/news/newsInsert.do",method=RequestMethod.POST)
	public String doProcess(
			@ModelAttribute("detail")MasterVO master
			,@Authenticated MasterVO authMember
			,Errors errors
			,Model model
			,RedirectAttributes redirectAttr
			) throws Exception {
		
		String memId = authMember.getMemId();
		master.setMemId(memId);
		String viewName = null;
		String message = null;
		
		if(!errors.hasErrors()) {
			ServiceResult result = service.insertNewsBoard(master);
			switch (result) {
			case OK:
				viewName = "redirect:/outs/board/news/newsList.do";
				break;
			default:
				viewName = "outs/board/news/newsForm";
				message = "서버오류입니다. 잠시 후 다시 시도해 주세요";
			}
		}else {
			viewName = "outs/board/news/newsForm";
		}
		model.addAttribute("message", message);
		
		return viewName;
	}
	
}
