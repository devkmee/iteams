package kr.or.ddit.iteams.pms.board.request.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.iteams.common.SearchWordCollector;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.common.vo.PMSTotalVO;
import kr.or.ddit.iteams.pms.board.request.service.RequestService;
import kr.or.ddit.iteams.pms.common.dao.PMSOthersDAO;

@Controller
public class RequestRetrieveController {
	
	@Inject
	private RequestService service;
	
	@Inject
	private PMSOthersDAO dao;
	
	//전체리스트조회하기
	@RequestMapping("/pms/board/request/{proNo}/requestList.do")
	public String getList(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, @ModelAttribute("MasterVO") MasterVO masterVO
			, @PathVariable String proNo
			, Model model
			) throws IllegalAccessException, InvocationTargetException {
		
		masterVO.setCurrentPage(currentPage);
		masterVO.setProNo(proNo);
		
		List<MasterVO> list = service.RequestBoardList(masterVO);
		List<MasterVO> proMemList = dao.selectProjectMember(masterVO);
		
		model.addAttribute("proMemList", proMemList);
		model.addAttribute("list", list);
		model.addAttribute("master", masterVO);
		
		SearchWordCollector.makeSearchVO();
		
		return "pms/board/request/requestList";
	}
	
	//상세보기
	@RequestMapping("/pms/board/request/{proNo}/requestView.do")
	public String getView(
			@RequestParam("what") String boNum
			,Model model
			) {
		int number = Integer.parseInt(boNum);
		
		MasterVO detail = service.retrieveRequest(number);
		
		model.addAttribute("rNum", number);
		model.addAttribute("detail", detail);
		return "pms/board/request/requestView";
	}
}
