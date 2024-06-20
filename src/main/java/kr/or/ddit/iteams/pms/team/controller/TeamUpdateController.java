package kr.or.ddit.iteams.pms.team.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/pms/team/teamUpdate.do")
@Controller
public class TeamUpdateController {
	
	@RequestMapping(method=RequestMethod.GET)
	public String getForm() {
		return "pms/team/teamForm";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String doProcess() {
		return "pms/team/teamView";
	}
}
