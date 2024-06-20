package kr.or.ddit.iteams.pms.board.request.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.pms.board.request.service.RequestService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class RequestDeleteController {
	
	@Inject
	private RequestService service;
	
	@RequestMapping(value="/pms/board/request/{proNo}/requestDelete.do",method=RequestMethod.GET)
	public String getForm(
			@RequestParam("what") String boNum
			, @RequestParam("proNo") String proNo
			, Model model
			) throws Exception {

		int proNumber = Integer.parseInt(proNo);
		MasterVO masterVO = new MasterVO();
		masterVO.setBoNum(boNum);
		int deleteYn = service.deleteRequest(masterVO);
		
		String viewName = "redirect:/pms/board/request/" + proNumber + "/requestList.do";

		return viewName;
	}
	
	@RequestMapping(value="/pms/board/request/{proNo}/requestDelete.do",method=RequestMethod.POST)
	public String doProcess(
			@RequestParam("proNo") String proNo
			, Model model
			) {
		return "pms/board/request/requestList";
	}
}
