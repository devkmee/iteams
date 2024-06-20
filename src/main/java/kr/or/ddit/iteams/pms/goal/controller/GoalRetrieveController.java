package kr.or.ddit.iteams.pms.goal.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.pms.goal.service.GoalService;

@Controller
public class GoalRetrieveController {
	
	@Inject
	private GoalService service;
	
	@RequestMapping("/pms/goal/{proNo}/goalList.do")
	public String getForm(
			MasterVO masterVO
			,Model model
			) {
		
		
		
		service.retrieveGoalList(masterVO);
		model.addAttribute("masterVO", masterVO);
		
		return "pms/goal/goalList";
	}
	
	@RequestMapping("/pms/goal/goalView.do")
	public String doProcess() {
		return "pms/goal/goalView";
	}
	
	
}
