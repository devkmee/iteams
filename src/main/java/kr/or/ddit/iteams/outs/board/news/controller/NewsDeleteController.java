package kr.or.ddit.iteams.outs.board.news.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.outs.board.news.service.NewsBoardService;

@Controller
@RequestMapping()
public class NewsDeleteController {
	
	@Inject
	private NewsBoardService service;
	
	@RequestMapping(value="/outs/board/news/newsDelete.do",method=RequestMethod.GET)
	public String getFrom(
			@RequestParam("what") String boNum
			,RedirectAttributes reAttr
			,Model model
			) throws Exception {
		
		String viewName = null;
		String message = null;
		
		int number = Integer.parseInt(boNum);
		
		MasterVO masterVO = new MasterVO();
		masterVO.setBoNum(boNum);
		
		int deleteNY = service.deleteNews(masterVO);
		
		if(deleteNY > 0) {
			viewName = "redirect:/outs/board/news/newsList.do";
			message = "삭제가 정상적으로 처리되었습니다.";
		}else {
			viewName = "redirect:/outs/board/news/newsView.do?what=" + boNum;
			message = "삭제가 처리되지 않았습니다.";
		}
		
		reAttr.addFlashAttribute("message", message);
		return viewName;
	}
	
	/*@RequestMapping(value="/outs/board/news/newsDelete.do",method=RequestMethod.POST)
	public String doProcess(
			 Model model) {
		
		
		return "outs/board/news/newsView";
	
	}*/
}
