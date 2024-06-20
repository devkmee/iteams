package kr.or.ddit.iteams.outs.board.news.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.outs.board.news.service.NewsBoardService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class NewsUpdateController {
	
	@Inject
	private NewsBoardService service;
	
	
	@RequestMapping(value="/outs/board/news/newsUpdate.do",method=RequestMethod.GET)
	public String getFrom(
			@RequestParam("what") String boNum
			, Model model
			) {
		
		int number = Integer.parseInt(boNum);
		
		MasterVO detail = service.retrieveNews(number);
		
		detail.setCrudToken("UPDATE");
		
		model.addAttribute("rNum", number);
		model.addAttribute("detail", detail);
		
		return "outs/board/news/newsForm";
	}
	
	@RequestMapping(value="/outs/board/news/newsUpdate.do",method=RequestMethod.POST)
	public String doProcess(
			@ModelAttribute("detail") MasterVO vo
			,Errors errors
			, RedirectAttributes reAttr
			) throws Exception {
		
		String boNum = vo.getBoNum();
		String viewName = null;
		String message = null;
		
		if(!errors.hasErrors()) {
			service.updateNews(vo);
			viewName = "redirect:/outs/board/news/newsView.do?what=" + boNum;
			message = "수정이 정상적으로 완료되었습니다.";
		}else {
			viewName = "outs/board/news/newsView";
		}
		reAttr.addFlashAttribute("message", message);
		return viewName;
	}
	
}
