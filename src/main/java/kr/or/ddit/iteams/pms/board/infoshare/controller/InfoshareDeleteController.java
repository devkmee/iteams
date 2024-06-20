package kr.or.ddit.iteams.pms.board.infoshare.controller;


import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.common.vo.PMSTotalVO;
import kr.or.ddit.iteams.pms.board.infoshare.service.InfoshareService;

@Controller
public class InfoshareDeleteController {
	
	@Inject
	private InfoshareService service;
	
	@RequestMapping(value="/pms/board/infoshare/{proNo}/infoshareDelete.do",method=RequestMethod.GET)
	public String getForm(
			@RequestParam("what") String boNum
			, @RequestParam("proNo") String proNo
            , Model model) throws Exception {
		
	
		//model.addAttribute("boWriter", boWriter);
		int proNumber = Integer.parseInt(proNo);
		int number = Integer.parseInt(boNum);
		MasterVO masterVO = new MasterVO();
		masterVO.setBoNum(boNum);
		int deleteYn = service.deleteInfo(masterVO);
		
		String viewName = "redirect:/pms/board/infoshare/" + proNo + "/infoshareList.do";
		
		/*int number = Integer.parseInt(boNum);
		int boNum = vo.getBoNum();
		int deleteYn = service.deleteInfo(boNum);
		String viewName = "redirect:/pms/board/infoshare/" + proNo + "/infoshareList.do";*/
		return viewName;
		
	}
	@RequestMapping(value="/pms/board/infoshare/{proNo}/infoshareDelete.do",method=RequestMethod.POST)
	public String doProcess(
		@RequestParam("proNo") String proNo
			,Model model
			) {
		
		
		return "pms/board/infoshare/infoshareView";
	}
}
