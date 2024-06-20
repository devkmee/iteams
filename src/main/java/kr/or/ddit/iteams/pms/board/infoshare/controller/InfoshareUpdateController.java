package kr.or.ddit.iteams.pms.board.infoshare.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.common.vo.PMSTotalVO;
import kr.or.ddit.iteams.pms.board.infoshare.service.InfoshareService;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.validate.groups.board.infoshare.InfoShareUpdateGroup;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class InfoshareUpdateController {
	
	@Inject
	private InfoshareService service;
	
	@RequestMapping(value="/pms/board/infoshare/{proNo}/infoshareUpdate.do",method=RequestMethod.GET)
	public String getView(
			@RequestParam("what") String boNum
			, @RequestParam("proNo") String proNo
			, Model model) {
		
		int number = Integer.parseInt(boNum);
		
		MasterVO detail = service.retrieveInfo(number);
		
		detail.setCrudToken("UPDATE");
		model.addAttribute("rNum", number);
		model.addAttribute("detail", detail);
		
		return "pms/board/infoshare/infoshareForm";
	}
	
	@RequestMapping(value="/pms/board/infoshare/{proNo}/infoshareUpdate.do",method=RequestMethod.POST)
	public String doProcess(
			@Validated(InfoShareUpdateGroup.class)
			@ModelAttribute("detail") MasterVO vo
			, Errors errors
			, RedirectAttributes reAttr
			) throws Exception {
		
		vo.setWorkNum("");
		String proNo = vo.getProNo();
		String boNum = vo.getBoNum();
		String viewName = null;
		String message = null;
		if(!errors.hasErrors()) {
			service.updateInfo(vo);
			viewName = "redirect:/pms/board/infoshare/"+ proNo +"/infoshareView.do?what=" + boNum;
			message = "정상 처리되었습니다.";
		} else {
			viewName = "pms/board/infoshare/infoshareForm";
		}
		
		reAttr.addFlashAttribute("message", message);
		return viewName;
	}
}
