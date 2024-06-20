package kr.or.ddit.iteams.pms.goal.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.annotations.Authenticated;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.pms.goal.service.GoalService;

@RequestMapping("/pms/goal/{proNo}/goalInsert.do")
@Controller
public class GoalInsertController {
	
	@Inject
	private GoalService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public String getForm(
			@Authenticated MasterVO authMember
			,MasterVO masterVO
			,Model model
			) {
		
		model.addAttribute("authMember",authMember);
		return "pms/goal/goalForm";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String doProcess(
			MasterVO masterVO	
			,Model model
			) {
		
		
		service.createGoal(masterVO);
		model.addAttribute("masterVO",masterVO);
		
		return "redirect:/pms/goal/{proNo}/goalList.do";
	}
	
	
}
