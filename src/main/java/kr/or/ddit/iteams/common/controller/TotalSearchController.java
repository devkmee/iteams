package kr.or.ddit.iteams.common.controller;

import java.lang.reflect.InvocationTargetException;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.iteams.common.SearchWordCollector;
import kr.or.ddit.iteams.common.service.TotalSearchService;
import kr.or.ddit.iteams.common.vo.MasterVO;

@Controller
public class TotalSearchController {
	
	@Inject
	private TotalSearchService service;
	
	@RequestMapping(value="/outs/totalList.do", method=RequestMethod.GET)
	public String getList(@ModelAttribute MasterVO masterVO
			, Model model) throws IllegalAccessException, InvocationTargetException {
		String viewName = null;
		
		masterVO.setCurrentPage(masterVO.getPage());
		service.selectTotalSearchList(masterVO);
		model.addAttribute("masterVO", masterVO);
		
		SearchWordCollector.makeSearchVO();
		viewName = "outs/totalSearch/totalSearchList";
		return viewName;
	}
}
