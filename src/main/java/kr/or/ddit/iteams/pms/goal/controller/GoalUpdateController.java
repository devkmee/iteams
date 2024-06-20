package kr.or.ddit.iteams.pms.goal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/pms/goal/goalUpdate.do")
@Controller
public class GoalUpdateController {
	
	@RequestMapping(method=RequestMethod.GET)
	public String getForm() {
		return "pms/goal/goalForm";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String doProcess() {
		return "pms/goal/goalView";
	}
	
	
}
