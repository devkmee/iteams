package kr.or.ddit.iteams.pms.team.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/pms/team/teamDelete.do")
@Controller
public class TeamDeleteController {
	
	@RequestMapping(method=RequestMethod.GET)
	public String getForm() {
		return "pms/team/teamForm";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String doProcess() {
		return "pms/team/teamList";
	}
}
