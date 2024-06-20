package kr.or.ddit.iteams.pms.work.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.annotations.Authenticated;
import kr.or.ddit.iteams.common.SearchWordCollector;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.outs.login.AuthenticatedMember;
import kr.or.ddit.iteams.outs.login.AuthenticationArgumentResolver;
import kr.or.ddit.iteams.pms.common.dao.PMSOthersDAO;
import kr.or.ddit.iteams.pms.work.service.WorkService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class WorkListController {
	
	@Inject
	private WorkService service;
	
	@Inject
	private PMSOthersDAO dao;
	
	@RequestMapping("/pms/work/{proNo}/workList.do")
	public String listView(@Authenticated MasterVO authMember
			, Model model) {
		List<MasterVO> projectMember = dao.selectProjectMember(authMember);
		model.addAttribute("projectMember", projectMember);
		return "pms/work/workList";
	}
	
	@RequestMapping(value="/pms/work/{proNo}/workList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public MasterVO getList(@ModelAttribute("masterVO") MasterVO masterVO
			) throws IllegalAccessException, InvocationTargetException {
		masterVO.setScreenSize(10);
		masterVO.setCurrentPage(masterVO.getPage());
		service.selectWorkList(masterVO);
		
		SearchWordCollector.makeSearchVO();
		return masterVO;
	}
}
