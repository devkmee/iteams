package kr.or.ddit.iteams.pms.board.daily.controller;

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
import kr.or.ddit.iteams.pms.board.daily.service.DailyService;
import kr.or.ddit.iteams.pms.common.dao.PMSOthersDAO;
import kr.or.ddit.vo.SearchVO;

@Controller
public class DaliyRetrieveController {
	
	//의존관계주입 : InDailyServiceImpl생성 
    //IoC 의존관계역전 
	@Inject
    private DailyService service;
	
	@Inject
	private PMSOthersDAO dao;
	
	// 전체 리스트 조회
	@RequestMapping("/pms/board/daily/{proNo}/dailyList.do")
	public String getList(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, @ModelAttribute("MasterVO") MasterVO masterVO
			, @PathVariable String proNo
			, Model model
			) throws IllegalAccessException, InvocationTargetException {
		
		masterVO.setCurrentPage(currentPage);
		masterVO.setProNo(proNo);
		
        List<MasterVO> list = service.DailyBoardList(masterVO);
        List<MasterVO> proMemList = dao.selectProjectMember(masterVO);
        
        model.addAttribute("proMemList", proMemList);
        model.addAttribute("list", list);
        model.addAttribute("master", masterVO);
        
        SearchWordCollector.makeSearchVO();
        
        return "pms/board/daily/dailyList";
	}
	
	
		/*BaseVO base = new BaseVO();
		base.setCurrentPage(currentPage);
		base.setSearchVO(searchVO);
		
		List<PMSTotalVO> list = service.DailyBoardList(base);
		
		model.addAttribute("list", list);
		model.addAttribute("base", base);*/
		
	
	// 상세보기
	@RequestMapping("/pms/board/daily/{proNo}/dailyView.do")
	public String getView(
			@RequestParam("what") String boNum
			, Model model) {
		
		int number = Integer.parseInt(boNum);
		
		MasterVO detail = service.retrieveDaily(number);  
		
		model.addAttribute("rNum", number);
		model.addAttribute("detail", detail);		
		
		return "pms/board/daily/dailyView";
	}
	
	
	}

