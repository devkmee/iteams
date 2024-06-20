package kr.or.ddit.iteams.pms.board.infoshare.controller;

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
import kr.or.ddit.iteams.pms.board.infoshare.service.InfoshareService;
import kr.or.ddit.iteams.pms.common.dao.PMSOthersDAO;
import kr.or.ddit.vo.SearchVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class InfoshareRetrieveController {
	
	//의존관계주입 : InfoshareServiceImpl생성 
	//IoC 의존관계역전 
	//게시글 전체보기 
	 @Inject
	 private InfoshareService service;
	 
	 @Inject
	 private PMSOthersDAO dao;
	 
	 
	 //전체보기 
     @RequestMapping("/pms/board/infoshare/{proNo}/infoshareList.do")	
     public String getList(
 			@RequestParam(value="page", required=false, defaultValue="1") int currentPage
 			, @ModelAttribute("MasterVO") MasterVO master
 			, @PathVariable String proNo
 			, Model model
 			)throws IllegalAccessException, InvocationTargetException {
    	 
    	 master.setCurrentPage(currentPage);
    	 master.setProNo(proNo);
    	 log.info("게시판 내용 : {}", master.getBoContent());
    	 List<MasterVO> list = service.InfoBoardList(master);
         List<MasterVO> proMemList = dao.selectProjectMember(master);    	 
    	 
         model.addAttribute("list", list);
         model.addAttribute("master", master);
         model.addAttribute("proMemList", proMemList);
         
         SearchWordCollector.makeSearchVO();
    	 
    	 /*MasterVO master = new MasterVO();
         master.setCurrentPage(currentPage);
         master.setSearchVO(search);
         master.setProNo(proNo);*/
    	 return "pms/board/infoshare/infoshareList";  //루트를 잘 확인하자!!! 
     }
	 
     // 게시글 상세보기
     @RequestMapping("/pms/board/infoshare/{proNo}/infoshareView.do")
      public String getView(
    		  @RequestParam("what") String boNum
    		  , Model model) {
    	 
    	 int number = Integer.parseInt(boNum);
    	 
    	 MasterVO detail = service.retrieveInfo(number);
        
    	 model.addAttribute("rNum", number);
 		 model.addAttribute("detail", detail);	
    	 
    	 return "pms/board/infoshare/infoshareView"; //루트를 잘 확인하자!!!
}	 
     
}
	