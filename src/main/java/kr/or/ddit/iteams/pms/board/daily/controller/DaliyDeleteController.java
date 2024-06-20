package kr.or.ddit.iteams.pms.board.daily.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.pms.board.daily.service.DailyService;

@Controller
public class DaliyDeleteController {
	
	@Inject
	private DailyService service;	
	
	
	@RequestMapping(value="/pms/board/daily/{proNo}/dailyDelete.do",method=RequestMethod.GET)
	public String getForm(
		@RequestParam("what") String boNum
		, @RequestParam("proNo") String proNo
		, Model model
		, RedirectAttributes reAttr) throws Exception {
		String viewName = null;
		String message = null;
		int proNumber = Integer.parseInt(proNo);
		int number = Integer.parseInt(boNum);
		MasterVO masterVO = new MasterVO();
		masterVO.setBoNum(boNum);
		int deleteYn = service.deleteDaily(masterVO);  
		
		if(deleteYn > 0) {
			viewName = "redirect:/pms/board/daily/" + proNumber + "/dailyList.do";
			message = "정상 처리되었습니다.";
		} else {
			viewName = "redirect:/pms/board/daily/" + proNo + "/dailyView.do?what=" + boNum;
			message = "정상 처리되지 못했습니다.";
		}
		
		reAttr.addFlashAttribute("message", message);
		return viewName;
	}
	
	@RequestMapping(value="/pms/board/daily/{proNo}/dailyDelete.do",method=RequestMethod.POST)
	public String doProcess(
			@RequestParam("proNo") String proNo
			, Model model) {
		
		
		return "pms/board/daily/dailyView";
	}
}
